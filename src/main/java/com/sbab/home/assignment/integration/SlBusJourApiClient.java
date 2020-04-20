package com.sbab.home.assignment.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbab.home.assignment.db.model.Businformation;
import com.sbab.home.assignment.dto.BusJourResponse;
import com.sbab.home.assignment.service.BusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class SlBusJourApiClient {
    private static final Logger LOG = LoggerFactory.getLogger(SlBusJourApiClient.class);
    RestTemplate restTemplate;

    BusService busServiceImpl;

    @Value("${application.cachedFilepath}")
    String cachedFilepath;
    @Value("${application.apiUrl}")
    String apiUrl;
    @Value("${application.api.offlineMode:false}")
    boolean offlineMode;


    @Autowired
    public SlBusJourApiClient(RestTemplate restTemplate, BusService busServiceImpl) {
        this.restTemplate = restTemplate;
        this.busServiceImpl = busServiceImpl;
    }


    @PostConstruct
    @Scheduled(cron = "0 0 0 * * ?")
    public void fetchDataAndPopulateDatabase() throws IOException {
        String responseBody = offlineMode ? readCachedFile() : getApiResponseData();
        List<BusJourResponse> busJourResponseList = getBusJourResponseList(responseBody);
        persistBusJourData(busJourResponseList);
        LOG.info("Application is ready now");
    }


    private String getApiResponseData() throws IOException {
        String responseBody;
        try {
            // call Api and fetchData
            responseBody = getApiResponse();
            LOG.info("Successfully called api");
        } catch (Exception e) {
            LOG.error("failed calling  api due to error", e);
            // n.w. read or connection time out has occurred read cached file
            LOG.info("reading data from Cached File");
            responseBody = readCachedFile();
        }
        return responseBody;
    }


    private void persistBusJourData(List<BusJourResponse> busJourResponseList) {
        final List<Businformation> businformationList = busJourResponseList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        busServiceImpl.refresh(businformationList);
        LOG.info("Saved all  " + businformationList.size());

    }


    private List<BusJourResponse> getBusJourResponseList(String responseBody) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        String result = jsonNode.get("ResponseData").get("Result").toPrettyString().toLowerCase();
        return objectMapper.readValue(result.toLowerCase(), new TypeReference<List<BusJourResponse>>() {
        });
    }


    // fall back method in case of  sl api end is not available
    private String readCachedFile() throws IOException {
        return Files.readString(Paths.get(cachedFilepath));
    }


    private String getApiResponse() throws URISyntaxException {
        LOG.info("apiUrl=  " + apiUrl);
        URI uri = new URI(apiUrl);
        ResponseEntity<String> apiResponse = restTemplate.getForEntity(uri, String.class);
        return apiResponse.getBody();
    }


    private Businformation convertToDto(BusJourResponse busJourResponse) {
        final Businformation businformation = new Businformation();
        businformation.setBusnumber(busJourResponse.getLinenumber());
        businformation.setBusstopnumber(busJourResponse.getJourneypatternpointnumber());
        businformation.setDirectioncode(busJourResponse.getDirectioncode());
        return businformation;
    }
}

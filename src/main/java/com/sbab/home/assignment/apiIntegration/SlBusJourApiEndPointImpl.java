package com.sbab.home.assignment.apiIntegration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;


@Component
public class SlBusJourApiEndPointImpl implements SlBusJourApiEndPoint {

    private static final Logger LOG = LoggerFactory.getLogger(SlBusJourApiEndPointImpl.class);
    RestTemplate restTemplate;

    @Value("${application.cachedFilepath}")
    String cachedFilepath;
    @Value("${application.apiUrl}")
    String apiUrl;


    @Autowired
    public SlBusJourApiEndPointImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public String getApiResponse() throws URISyntaxException {
        LOG.info("apiUrl =  " + apiUrl);
        URI uri = new URI(apiUrl);
        return restTemplate.getForEntity(uri, String.class).getBody();
    }


    @Override
    public String getFalBackDataResponse() throws IOException {
        return Files.readString(Paths.get(cachedFilepath));
    }
}

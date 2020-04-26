package com.sbab.home.assignment.apiIntegration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbab.home.assignment.db.repository.BusInformationRepository;
import com.sbab.home.assignment.service.BusService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URISyntaxException;


@SpringBootTest
@TestPropertySource(properties = {"application.api.offlineMode=false"})
public class SlBusJourApiClientTest {
    @Autowired
    BusService busServiceImpl;

    @MockBean
    SlBusJourApiEndPoint slBusJourApiEndPointimpl;

    @Autowired
    RestTemplate restTemplate;

    @Qualifier("target")
    @Autowired
    SlBusJourApiClient target;

    String sampleResponse = "{\"StatusCode\":0,\n"
                            + "\"Message\":null,\n"
                            + "\"ExecutionTime\":435,\n"
                            + "\"ResponseData\":\n"
                            + "{\"Version\":\"2020-04-16 00:03\",\"Type\":\"JourneyPatternPointOnLine\",\"Result\":\n"
                            + "[ {\n"
                            + "  \"LineNumber\" : \"1\",\n"
                            + "  \"DirectionCode\" : \"1\",\n"
                            + "  \"JourneyPatternPointNumber\" : \"10008\",\n"
                            + "  \"LastModifiedUtcDateTime\" : \"2018-02-16 00:00:00.000\",\n"
                            + "  \"ExistsFromDate\" : \"2018-02-16 00:00:00.000\"\n"
                            + "}, {\n"
                            + "  \"LineNumber\" : \"1\",\n"
                            + "  \"DirectionCode\" : \"1\",\n"
                            + "  \"JourneyPatternPointNumber\" : \"10012\",\n"
                            + "  \"LastModifiedUtcDateTime\" : \"2012-06-23 00:00:00.000\",\n"
                            + "  \"ExistsFromDate\" : \"2012-06-23 00:00:00.000\"\n"
                            + "}]}}";


    @Before
    public void setUp() throws URISyntaxException {
        slBusJourApiEndPointimpl = new SlBusJourApiEndPointImpl(restTemplate);
        Mockito.when(slBusJourApiEndPointimpl.getApiResponse())
                .thenReturn(sampleResponse);
    }


    @Test
    public void testGetApiResponseData() throws IOException {
        target.fetchDataAndPopulateDatabase();

    }


    @TestConfiguration
    class SlBusJourApiClientTestContextConfiguration {

        @MockBean
        BusService busServiceImpl;

        @MockBean
        BusInformationRepository busInformationRepository;

        @MockBean
        SlBusJourApiEndPoint slBusJourApiEndPointimpl;

        @MockBean
        ObjectMapper objectMapper;


        @Bean
        @Qualifier("target")
        public SlBusJourApiClient slbusJourApiClient() {
            return new SlBusJourApiClient(slBusJourApiEndPointimpl, busServiceImpl, objectMapper);
        }
    }
}

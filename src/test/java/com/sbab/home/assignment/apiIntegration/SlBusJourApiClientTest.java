package com.sbab.home.assignment.apiIntegration;

import com.sbab.home.assignment.service.BusService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;


@TestPropertySource(properties = {"application.api.offlineMode=false"})
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class SlBusJourApiClientTest {

    @Mock
    BusService busServiceImpl;

    @Mock
    SlBusJourApiEndPoint slBusJourApiEndPointimpl;

    @InjectMocks
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


    @Test
    public void testGetApiResponseData1() throws Exception {
        Mockito.when(slBusJourApiEndPointimpl.getApiResponse()).thenReturn(sampleResponse);
        target.fetchDataAndPopulateDatabase();
        verify(busServiceImpl, atLeast(1)).refresh(Mockito.anyList());
    }


    @Test
    public void testGetApiResponseData2() throws IOException, URISyntaxException {
        Mockito.when(slBusJourApiEndPointimpl.getApiResponse()).thenThrow(URISyntaxException.class);
        Mockito.when(slBusJourApiEndPointimpl.getFalBackDataResponse()).thenReturn(sampleResponse);
        target.fetchDataAndPopulateDatabase();
        verify(busServiceImpl, atLeast(1)).refresh(Mockito.anyList());
    }


    @Test
    public void testGetApiResponseData3() throws Exception {
        Mockito.when(slBusJourApiEndPointimpl.getApiResponse()).thenReturn("{\n"
                                                                           + "StatusCode: 1002,\n"
                                                                           + "Message: \"Key is invalid\",\n"
                                                                           + "}");
        Mockito.when(slBusJourApiEndPointimpl.getFalBackDataResponse()).thenReturn(sampleResponse);
        target.fetchDataAndPopulateDatabase();
        verify(busServiceImpl, atLeast(1)).refresh(Mockito.anyList());
    }

}

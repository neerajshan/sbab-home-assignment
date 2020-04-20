package com.sbab.home.assignment.apiIntegration;

import java.io.IOException;
import java.net.URISyntaxException;


public interface SlBusJourApiEndPoint {
    String getApiResponse() throws URISyntaxException;


    String getFalBackDataResponse() throws IOException;
}

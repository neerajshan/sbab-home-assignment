package com.sbab.home.assignment.integrationtest.steps;

import com.sbab.home.assignment.SbabTestProjectApplication;
import com.sbab.home.assignment.integrationtest.dto.HeaderSettingRequestCallback;
import com.sbab.home.assignment.integrationtest.dto.ResponseResultErrorHandler;
import com.sbab.home.assignment.integrationtest.dto.ResponseResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@SpringBootTest(classes = SbabTestProjectApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class DemoProjectIntegrationTest {
    protected static ResponseResults latestResponse = null;

    @Autowired
    protected RestTemplate restTemplate;

    @LocalServerPort
    protected int port;


    protected void executeGet(String url, String mediaType) {
        final Map<String, String> headers = new HashMap<>();
        // headers.put("Accept", "application/json");
        headers.put("Accept", mediaType);
        final HeaderSettingRequestCallback requestCallback = new HeaderSettingRequestCallback(headers);
        final ResponseResultErrorHandler errorHandler = new ResponseResultErrorHandler();

        restTemplate.setErrorHandler(errorHandler);
        latestResponse = restTemplate.execute(url, HttpMethod.GET, requestCallback, response -> {
            if (errorHandler.getHadError()) {
                return (errorHandler.getResults());
            } else {
                return (new ResponseResults(response));
            }
        });
    }


    protected void executePost() {
        final Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        final HeaderSettingRequestCallback requestCallback = new HeaderSettingRequestCallback(headers);
        final ResponseResultErrorHandler errorHandler = new ResponseResultErrorHandler();

        if (restTemplate == null) {
            restTemplate = new RestTemplate();
        }

        restTemplate.setErrorHandler(errorHandler);
        String restEndpointUrl = "http://localhost:" + port;

        latestResponse = restTemplate
                .execute(restEndpointUrl + "/businformation", HttpMethod.POST, requestCallback, response -> {
                    if (errorHandler.getHadError()) {
                        return (errorHandler.getResults());
                    } else {
                        return (new ResponseResults(response));
                    }
                });
    }


}
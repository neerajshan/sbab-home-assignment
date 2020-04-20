package com.neeraj.test.demoproject.integrationtest.steps;

import cucumber.api.java.en.When;


public class AllStopsForBusLineStepDefsIntegrationTest extends DemoProjectIntegrationTest {

    @When("^the client calls /v1/bus/626$")
    public void the_client_calls_find_all_bus_stops_api() {
        String restEndpointUrl = "http://localhost:" + port + "/v1/bus/626";
        String mediaType = "application/json";
        executeGet(restEndpointUrl, mediaType);
    }
}
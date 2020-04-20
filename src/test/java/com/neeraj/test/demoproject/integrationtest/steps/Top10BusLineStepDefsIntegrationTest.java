package com.neeraj.test.demoproject.integrationtest.steps;

import cucumber.api.java.en.When;


public class Top10BusLineStepDefsIntegrationTest extends DemoProjectIntegrationTest {

    @When("^the client calls /v1/bus/topbuslines$")
    public void the_client_calls_top10_bus_lines_api() {
        String restEndpointUrl = "http://localhost:" + port + "/v1/bus/topbuslines";
        String mediaType = "application/json";
        executeGet(restEndpointUrl, mediaType);
    }
}
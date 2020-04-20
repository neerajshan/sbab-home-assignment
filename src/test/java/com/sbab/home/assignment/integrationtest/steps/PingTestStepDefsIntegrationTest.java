package com.sbab.home.assignment.integrationtest.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class PingTestStepDefsIntegrationTest extends DemoProjectIntegrationTest {


    @When("^the client calls /ping$")
    public void the_client_calls_ping() {
        String restEndpointUrl = "http://localhost:" + port + "/ping";
        String mediaType = "text/plain";
        executeGet(restEndpointUrl, mediaType);
    }


    @Then("^the client receives server response (.+)$")
    public void the_client_receives_server_response_ok(String response) {
        assertThat(latestResponse.getBody(), is(response));
    }


}
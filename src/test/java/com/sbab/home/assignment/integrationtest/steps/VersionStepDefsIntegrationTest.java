package com.sbab.home.assignment.integrationtest.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class VersionStepDefsIntegrationTest extends DemoProjectIntegrationTest {


    @When("^the client calls /version$")
    public void the_client_issues_GET_version() {
        String restEndpointUrl = "http://localhost:" + port + "/version";
        String mediaType = "text/plain";
        executeGet(restEndpointUrl, mediaType);
    }


    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        final HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
        assertThat("status code is incorrect : " + latestResponse.getBody(), currentStatusCode.value(), is(statusCode));
    }


    @And("^the client receives server version (.+)$")
    public void the_client_receives_server_version_body(String version) {
        assertThat(latestResponse.getBody(), is(version));
    }

}
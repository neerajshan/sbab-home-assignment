package com.sbab.home.assignment.integrationtest.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class AllBusTestStepDefsIntegrationTest extends DemoProjectIntegrationTest {

    @When("^the client calls /v1/bus/$")
    public void the_client_calls_v_bus() {
        String restEndpointUrl = "http://localhost:" + port + "/v1/bus/";
        String mediaType = "application/json";
        executeGet(restEndpointUrl, mediaType);
    }


    @And("^the client receives response (.+)$")
    public void the_client_receives_response_src_test_resources_fixture_all_bus_feature_response_json(String filePath) throws IOException, JSONException {
        String expectedResponse = Files.readString(Paths.get(filePath));
        JSONAssert.assertEquals(
                latestResponse.getBody(), expectedResponse, JSONCompareMode.LENIENT);
    }


}
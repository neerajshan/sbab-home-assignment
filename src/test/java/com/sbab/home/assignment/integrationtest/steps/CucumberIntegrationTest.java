package com.sbab.home.assignment.integrationtest.steps;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features")
public class CucumberIntegrationTest extends DemoProjectIntegrationTest {
}
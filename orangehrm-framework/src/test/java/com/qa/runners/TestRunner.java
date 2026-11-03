package com.qa.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.qa.stepdefs",
    plugin = {"pretty","html:target/cucumber-reports/report.html",
        "json:target/cucumber-reports/report.json"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
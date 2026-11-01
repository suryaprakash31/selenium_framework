package com.qa.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.qa.stepdefs",
    plugin = {"pretty"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
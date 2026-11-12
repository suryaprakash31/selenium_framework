package com.qa.stepdefs;

import org.openqa.selenium.WebDriver;

import com.qa.utils.ConfigReader;
import com.qa.utils.DriverFactory;
import org.testng.Assert;

import com.qa.pages.DashboardPage;
import com.qa.pages.LoginPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.qa.utils.ScreenshotUtils;
import io.cucumber.java.Scenario;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;

    // Hook: runs before every scenario
    @Before
    public void startBrowser() {
        driver = DriverFactory.createDriver();
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    // Hook: runs after every scenario
    @After
    public void closeBrowser(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] picture = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(picture, "image/png", "Failure screenshot");
            ScreenshotUtils.takeScreenshot(driver, scenario.getName().replaceAll("[^a-zA-Z0-9]", "_"));
        }
        driver.quit();
    }

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        driver.get(ConfigReader.get("baseUrl"));
    }

    @When("the user logs in with username {string} and password {string}")
    public void the_user_logs_in_with_username_and_password(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("the dashboard heading should be {string}")
    public void the_dashboard_heading_should_be(String expectedHeading) {
        Assert.assertEquals(dashboardPage.getHeading(), expectedHeading);
    }

        @Then("the user should see the error message {string}")
    public void the_user_should_see_the_error_message(String expectedMessage) {
        Assert.assertEquals(loginPage.getErrorMessage(), expectedMessage);
    }
}
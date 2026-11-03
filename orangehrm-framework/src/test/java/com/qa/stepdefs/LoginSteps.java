package com.qa.stepdefs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.qa.pages.DashboardPage;
import com.qa.pages.LoginPage;

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
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    // Hook: runs after every scenario
    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        driver.get("https://opensource-demo.orangehrmlive.com");
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
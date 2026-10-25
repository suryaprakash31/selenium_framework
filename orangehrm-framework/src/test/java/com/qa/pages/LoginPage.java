package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    // The "phone numbers": locators, saved ONCE.
    By usernameField = By.name("username");
    By passwordField = By.name("password");
    By loginButton = By.cssSelector("button[type='submit']");
    By errorMessage = By.cssSelector(".oxd-alert-content-text");

    // The constructor: when someone writes "new LoginPage(driver)",
    // the page receives the browser and remembers it.
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Action 1: log in. Same 3 steps as before, now in one place.
    public void login(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    // Action 2: read the red error message and hand it back.
    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }
}
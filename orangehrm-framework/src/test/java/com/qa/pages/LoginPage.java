package com.qa.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    // The browser and the waiter. The page needs them to do its work.
    WebDriver driver;
    WebDriverWait wait;

    // The "phone numbers": locators, saved ONCE.
    By usernameField = By.name("username");
    By passwordField = By.name("password");
    By loginButton = By.cssSelector("button[type='submit']");
    By errorMessage = By.cssSelector(".oxd-alert-content-text");

    // The constructor: when someone writes "new LoginPage(driver)",
    // the page receives the browser and remembers it.
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
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
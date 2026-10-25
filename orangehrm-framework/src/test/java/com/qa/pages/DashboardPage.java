package com.qa.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage extends BasePage {

    // The "phone number": the heading at the top of the page.
    By heading = By.cssSelector(".oxd-topbar-header-breadcrumb-module");

    // The constructor: receives the browser and remembers it.
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    // The action: wait for the heading, read it, and hand the text back.
    public String getHeading() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(heading)).getText();
    }
}
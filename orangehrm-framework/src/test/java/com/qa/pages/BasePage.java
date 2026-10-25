package com.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    WebDriver driver;
    WebDriverWait wait;

    By heading = By.cssSelector(".oxd-topbar-header-breadcrumb-module");

    public BasePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // The action: wait for the heading, read it, and hand the text back.
    public String getHeading() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(heading)).getText();
    }
    
}

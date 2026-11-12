package com.qa.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import com.qa.listeners.TestListener;
import com.qa.utils.ConfigReader;
import com.qa.utils.DriverFactory;

@Listeners(TestListener.class)
public class BaseTest {

    WebDriver driver;
    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.createDriver();
        driver.get(ConfigReader.get("baseUrl"));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    public WebDriver getDriver() {                               // NEW: lets the listener get the browser
        return driver;
    }
    
}

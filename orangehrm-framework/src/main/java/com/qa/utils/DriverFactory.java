package com.qa.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;                                          // NEW
import org.openqa.selenium.firefox.FirefoxDriver;                                    // NEW

public class DriverFactory {

    public static WebDriver createDriver() {
        // Command line (-Dbrowser=...) wins; otherwise use config.properties
        String browser = System.getProperty("browser", ConfigReader.get("browser")); // NEW

        String driverPath = ConfigReader.get("chromeDriverPath");
        if (driverPath != null && !driverPath.isBlank()) {
            System.setProperty("webdriver.chrome.driver", driverPath);
        }

        WebDriver driver;                                                            // NEW
        switch (browser.toLowerCase()) {                                             // NEW
            case "chrome":                                                           // NEW
                driver = new ChromeDriver();                                         // NEW
                break;                                                               // NEW
            case "edge":                                                             // NEW
                driver = new EdgeDriver();                                           // NEW
                break;                                                               // NEW
            case "firefox":                                                          // NEW
                driver = new FirefoxDriver();                                        // NEW
                break;                                                               // NEW
            default:                                                                 // NEW
                throw new IllegalArgumentException(                                  // NEW
                    "Unknown browser: " + browser + " (use chrome, edge or firefox)"); // NEW
        }                                                                            // NEW

        driver.manage().window().maximize();
        return driver;
    }
}
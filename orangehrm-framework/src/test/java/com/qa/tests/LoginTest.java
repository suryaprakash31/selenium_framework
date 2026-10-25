package com.qa.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.pages.DashboardPage;
import com.qa.pages.LoginPage;

public class LoginTest {

    WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    
    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        wait = new WebDriverWait (driver, Duration.ofSeconds(15));
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        driver.get("https://opensource-demo.orangehrmlive.com");
    } 

    @Test 
    public void tc01_validLogin(){
        loginPage.login("Admin", "admin123");
        Assert.assertEquals(dashboardPage.getHeading(), "Dashboard");
    }

    @Test 
    public void tc02_invalidLogin(){
        loginPage.login("Admin", "Wrong_Password");
        Assert.assertEquals(loginPage.getErrorMessage(),"Invalid credentials");
    }

    @AfterMethod 
    public void tearDown(){
        driver.quit();
    }

}

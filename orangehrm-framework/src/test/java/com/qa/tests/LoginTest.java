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

public class LoginTest {

    WebDriver driver;
    WebDriverWait wait;
    
    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        wait = new WebDriverWait (driver, Duration.ofSeconds(15));
        driver.get("https://opensource-demo.orangehrmlive.com");
    } 

    @Test 
    public void tc01_validLogin(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        String heading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".oxd-topbar-header-breadcrumb-module"))).getText();
        Assert.assertEquals(heading, "Dashboard");
    }

    @Test 
    public void tc02_invalidLogin(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("wrong_password");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        String error = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".oxd-alert-content-text"))).getText();
        Assert.assertEquals(error, "Invalid credentials");
    }

    @AfterMethod 
    public void tearDown(){
        driver.quit();
    }

}

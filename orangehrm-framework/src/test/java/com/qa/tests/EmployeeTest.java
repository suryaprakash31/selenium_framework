package com.qa.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EmployeeTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod 
    public void setUp(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://opensource-demo.orangehrmlive.com");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".oxd-topbar-header-breadcrumb-module")));
    }

    void openMenu(String menuName){
        By menuItem = By.xpath("//a[contains(@class,'oxd-main-menu-item')]//span[text()='" + menuName + "']");
        wait.until(ExpectedConditions.elementToBeClickable(menuItem)).click();
    }

    @Test 
    public void tc08_addEmployee(){
        openMenu("PIM");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[normalize-space()='Add Employee']"))).click();

        String lastName = "Auto" + System.currentTimeMillis();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstName"))).sendKeys("Test");
        driver.findElement(By.name("lastName")).sendKeys(lastName);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        String message = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".oxd-toast"))).getText();
        Assert.assertTrue(message.contains("Successfully Saved"), "Unexpected message: " + message);
    }

    @AfterMethod 
    public void tearDown(){
        driver.quit();
    }
    
}

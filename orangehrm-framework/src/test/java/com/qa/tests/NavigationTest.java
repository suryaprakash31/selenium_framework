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

public class NavigationTest {

    WebDriver driver;
    WebDriverWait wait;
    
    @BeforeMethod 
    public void setUp(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
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
    public void tc03_openAdminPage(){
        openMenu("Admin");
        wait.until(ExpectedConditions.urlContains("admin"));
        String heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".oxd-topbar-header-breadcrumb-module"))).getText();
        Assert.assertEquals(heading, "Admin");
    }

    @Test 
    public void tc04_openPimPage(){
        openMenu("PIM");
        wait.until(ExpectedConditions.urlContains("pim"));
        String heading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".oxd-topbar-header-breadcrumb-module"))).getText();
        Assert.assertEquals(heading, "PIM");
    }

    @Test 
    public void tc05_openLeavePage(){
        openMenu("Leave");
        wait.until(ExpectedConditions.urlContains("leave"));
        String heading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".oxd-topbar-header-breadcrumb-module"))).getText();
        Assert.assertEquals(heading, "Leave");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
    
}

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

import com.qa.pages.AdminPage;
import com.qa.pages.EmployeePage;
import com.qa.pages.LoginPage;
import com.qa.pages.MenuPage;

public class EmployeeTest extends BaseTest {

    LoginPage loginPage;
    MenuPage menuPage;
    EmployeePage employeePage;

    @BeforeMethod 
    public void createPagesaAndOpenPIM(){
        loginPage = new LoginPage(driver);
        menuPage = new MenuPage(driver);
        employeePage = new EmployeePage(driver);

        loginPage.login("Admin", "admin123");
        menuPage.openMenu("PIM");
    }

    @Test 
    public void tc08_addEmployee(){
        employeePage.openaddEmployee();
        String lastName = "Auto" + System.currentTimeMillis();
        employeePage.addEmployee("Test", lastName);
        String message = employeePage.getToastMessage();
        Assert.assertTrue(message.contains("Successfully Saved"), "Unexpected message: " + message);
    }

}

package com.qa.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.pages.DashboardPage;
import com.qa.pages.LoginPage;

public class LoginTest extends BaseTest {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    
    @BeforeMethod
    public void createPages(){
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
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

}

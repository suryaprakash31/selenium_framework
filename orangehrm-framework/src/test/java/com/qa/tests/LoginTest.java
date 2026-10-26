package com.qa.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.pages.DashboardPage;
import com.qa.pages.LoginPage;
import com.qa.utils.ExcelUtils;

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

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() throws IOException{
        return ExcelUtils.getSheetData("src/test/resources/testdata/LoginData.xlsx", "InvalidLogin");
    }

    @Test (dataProvider = "invalidLoginData")
    public void tc02_invalidLogin(String username, String password, String expectedMessage){
        loginPage.login(username, password);
        Assert.assertEquals(loginPage.getErrorMessage(),expectedMessage);
    }

}

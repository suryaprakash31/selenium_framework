package com.qa.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.pages.LoginPage;
import com.qa.pages.MenuPage;

public class NavigationTest extends BaseTest {

    LoginPage loginPage;
    MenuPage menuPage;
    
    @BeforeMethod 
    public void createPagesAndLogin(){
        loginPage = new LoginPage(driver);
        menuPage = new MenuPage(driver);
        loginPage.login("Admin", "admin123");
    }

    @Test 
    public void tc03_openAdminPage(){
        menuPage.openMenu("Admin");
        Assert.assertEquals(menuPage.getHeading(), "Admin");
    }

    @Test 
    public void tc04_openPimPage(){
        menuPage.openMenu("PIM");
        Assert.assertEquals(menuPage.getHeading(), "PIM");
    }

    @Test 
    public void tc05_openLeavePage(){
        menuPage.openMenu("Leave");
        Assert.assertEquals(menuPage.getHeading(), "Leave");
    }
    
}

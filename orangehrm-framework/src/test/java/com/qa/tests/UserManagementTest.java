package com.qa.tests;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.pages.AdminPage;
import com.qa.pages.LoginPage;
import com.qa.pages.MenuPage;

public class UserManagementTest extends BaseTest {

    LoginPage loginPage;
    MenuPage menuPage;
    AdminPage adminPage;

    @BeforeMethod 
    public void createPagesAndOpenAdmin(){
        loginPage = new LoginPage(driver);
        menuPage = new MenuPage(driver);
        adminPage = new AdminPage(driver);

        loginPage.login("Admin", "admin123");
        menuPage.openMenu("Admin");
        adminPage.waitForTable();
    }

    @Test 
    public void tc05_SearchByUsername(){
        adminPage.searchByUsername("Admin");
        List<String> rows = adminPage.getRowTexts();
        Assert.assertTrue(rows.size()>0,"no rows found after search");
        for (String rowText: rows){
            Assert.assertTrue(rowText.toLowerCase().contains("admin"), "Row doesnt contain 'admim'" + rowText);
        }
    }

    @Test 
    public void tc06_filterByUserRole(){
        adminPage.filterByRole("ESS");
        List<String> roles = adminPage.getRoles();
        Assert.assertTrue(roles.size()>0, "No roles found after fitering");
        for(String role : roles){
            Assert.assertEquals(role, "ESS");
        }
    }

    @Test 
    public void tc07_tableHasRowsAndCount(){
        Assert.assertTrue(adminPage.getRecordsText().contains("Found"), "Records Found Text Missing");
        Assert.assertTrue(adminPage.getRowTexts().size()>0, "user table has no rows");
    }

   
}
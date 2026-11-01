package com.qa.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.pages.EmployeePage;
import com.qa.pages.LoginPage;
import com.qa.pages.MenuPage;
import com.qa.utils.ExcelUtils;

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

    @DataProvider (name = "employeeData")
    public Object [][] employeeData() throws IOException{
        return ExcelUtils.getSheetData("src/test/resources/testdata/EmployeeData.xlsx", "Employees");
    }

    @Test (dataProvider = "employeeData" )
    public void tc09_addEmployee(String firstName, String lastName, String expextedMessage){
        employeePage.openaddEmployee();
        employeePage.addEmployee(firstName, lastName);
        String message = employeePage.getToastMessage();
        Assert.assertTrue(message.contains(expextedMessage), "Unexpected message: " + message);
    }

}

package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EmployeePage extends BasePage {

    By addEmployeeTab = By.xpath("//a[normalize-space()='Add Employee']");
    By firstNameInput = By.name("firstName");
    By lastNameInput = By.name("lastName");
    By saveButton = By.cssSelector("button[type='submit']");
    By toast = By.cssSelector(".oxd-toast");

    public EmployeePage(WebDriver driver) {
        super(driver);
    }

    public void openaddEmployee(){
        wait.until(ExpectedConditions.elementToBeClickable(addEmployeeTab)).click();
    }

    public void addEmployee(String firstname, String lastName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput)).sendKeys(firstname);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(saveButton).click();

    }

    public String getToastMessage(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(toast)).getText();
    }
    
}

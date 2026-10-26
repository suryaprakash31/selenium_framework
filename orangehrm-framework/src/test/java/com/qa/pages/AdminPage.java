package com.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AdminPage extends BasePage {

    By recordsText = By.xpath("//*[contains(normalize-space(.),'Records Found') and not(*)]");
    By tableRows = By.cssSelector(".oxd-table-body .oxd-table-card");
    By roleDropdown = By.xpath(
            "//label[text()='User Role']/parent::div/following-sibling::div//div[contains(@class,'oxd-select-text-input')]");
    By userNameInput = By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input");
    By searchButton = By.cssSelector("button[type='submit']");

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    public void waitForTable(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(recordsText));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tableRows));
    }
    
    public void searchByUsername(String username){
        String before = driver.findElement(recordsText).getText();
        driver.findElement(userNameInput).sendKeys(username);
        driver.findElement(searchButton).click();
        waitForRefresh(before);
    }

    public void filterByRole(String role){
        String before = driver.findElement(recordsText).getText();
        selectFromDropdown(roleDropdown, role);
        driver.findElement(searchButton).click();
        waitForRefresh(before);

    }

    public String getRecordsText() {
        return driver.findElement(recordsText).getText();
    }

    public List<String> getRowTexts(){
        List<WebElement> rows = driver.findElements(tableRows);
        List<String> texts = new ArrayList<>();
        for (WebElement row: rows){
            texts.add(row.getText());
        }
        return texts;
    }

    public List<String> getRoles(){
        List<WebElement> rows = driver.findElements(tableRows);
        List<String> roles = new ArrayList<>();
        for (WebElement row: rows){
            roles.add(row.findElements(By.cssSelector(".oxd-table-cell")).get(2).getText());
        }
        return roles;
    }

    private void selectFromDropdown(By dropdown, String optionText) {
        wait.until(ExpectedConditions.elementToBeClickable(dropdown)).click();
        By option = By.xpath("//div[@role='listbox']//span[text()='" + optionText + "']");
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }

    private void waitForRefresh(String before) {
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(recordsText, before)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tableRows));
    }

    
    
}

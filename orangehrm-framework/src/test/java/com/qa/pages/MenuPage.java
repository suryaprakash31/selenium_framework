package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MenuPage extends BasePage {

    public MenuPage(WebDriver driver) {
        super(driver);
    }

    public void openMenu(String menuName){
        By menuItem = By.xpath("//a[contains(@class,'oxd-main-menu-item')]//span[text()='" + menuName + "']");
        wait.until(ExpectedConditions.elementToBeClickable(menuItem)).click();
        wait.until(ExpectedConditions.urlContains(menuName.toLowerCase()));
    }
    

    
}

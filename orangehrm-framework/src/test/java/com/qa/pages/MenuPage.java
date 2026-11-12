package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MenuPage extends BasePage {

    private static final Logger log = LogManager.getLogger(MenuPage.class);

    public MenuPage(WebDriver driver) {
        super(driver);
    }

    public void openMenu(String menuName){
        log.info("Opening menu: {}", menuName);
        By menuItem = By.xpath("//a[contains(@class,'oxd-main-menu-item')]//span[text()='" + menuName + "']");
        wait.until(ExpectedConditions.elementToBeClickable(menuItem)).click();
        wait.until(ExpectedConditions.urlContains(menuName.toLowerCase()));
    }
    

    
}

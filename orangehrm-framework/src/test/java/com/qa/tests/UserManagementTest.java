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

public class UserManagementTest {

    WebDriver driver;
    WebDriverWait wait;

    By recordsText = By.xpath("//*[contains(normalize-space(.),'Records Found') and not(*)]");
    By tableRows = By.cssSelector(".oxd-table-body .oxd-table-card");
    By roleDropdown = By.xpath(
            "//label[text()='User Role']/parent::div/following-sibling::div//div[contains(@class,'oxd-select-text-input')]");

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get("https://opensource-demo.orangehrmlive.com");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".oxd-topbar-header-breadcrumb-module")));

        openMenu("Admin");
        wait.until(ExpectedConditions.visibilityOfElementLocated(recordsText));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tableRows));
    }

    void openMenu(String menuName) {
        By menuItem = By.xpath("//a[contains(@class,'oxd-main-menu-item')]//span[text()='" + menuName + "']");
        wait.until(ExpectedConditions.elementToBeClickable(menuItem)).click();
    }

    void selectFromDropdown(By dropdown, String optionText) {
        wait.until(ExpectedConditions.elementToBeClickable(dropdown)).click();
        By option = By.xpath("//div[@role='listbox']//span[text()='" + optionText + "']");
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }

    @Test
    public void tc05_searchByUsername() {
        String before = driver.findElement(recordsText).getText();

        driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input"))
                .sendKeys("Admin");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(recordsText, before)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tableRows));

        List<WebElement> rows = driver.findElements(tableRows);
        Assert.assertTrue(rows.size() > 0, "No rows found after search");

        for (WebElement row : rows) {
            Assert.assertTrue(row.getText().toLowerCase().contains("admin"),
                    "Row does not contain 'admin': " + row.getText());
        }
    }

    @Test
    public void tc06_filterByUserRole() {
        String before = driver.findElement(recordsText).getText();

        selectFromDropdown(roleDropdown, "ESS");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(recordsText, before)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tableRows));

        List<WebElement> rows = driver.findElements(tableRows);
        Assert.assertTrue(rows.size() > 0, "No rows found after filtering");

        for (WebElement row : rows) {
            String role = row.findElements(By.cssSelector(".oxd-table-cell")).get(2).getText();
            Assert.assertEquals(role, "ESS");
        }
    }

    @Test
    public void tc07_tableHasRowsAndCount() {
        String count = driver.findElement(recordsText).getText();
        Assert.assertTrue(count.contains("Found"), "Records Found text missing: " + count);

        List<WebElement> rows = driver.findElements(tableRows);
        Assert.assertTrue(rows.size() > 0, "Users table has no rows");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
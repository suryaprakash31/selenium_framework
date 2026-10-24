import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DropdownDemo {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".product_sort_container")));
        Select dropdown = new Select(dropdownElement);

        dropdown.selectByVisibleText("Price (low to high)");

        Select freshDropdown = new Select(driver.findElement(By.cssSelector(".product_sort_container")));
String selected = freshDropdown.getFirstSelectedOption().getText();
        System.out.println("Selected sort: " + selected);

        String firstProduct = driver.findElement(By.cssSelector(".inventory_item_name")).getText();
        String firstPrice = driver.findElement(By.cssSelector(".inventory_item_price")).getText();
        System.out.println("First product: " + firstProduct + " at " + firstPrice);

        if (firstPrice.equals("$7.99")) {
            System.out.println("PASS: Cheapest product is first");
        } else {
            System.out.println("FAIL: Expected '$7.99' but found '" + firstPrice + "'");
        }

        driver.quit();
    }
}
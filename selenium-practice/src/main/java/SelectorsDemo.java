import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectorsDemo {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com");

        // Login using CSS selectors (#id)
        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("#login-button")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));

        // CSS by class: first "Add to cart" button on the page
        driver.findElement(By.cssSelector(".btn_inventory")).click();

        // CSS by attribute: one specific product's button
        driver.findElement(By.cssSelector("[data-test='add-to-cart-sauce-labs-bike-light']")).click();

        // CSS by class: read the cart badge
        String badge = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".shopping_cart_badge"))).getText();
        System.out.println("Cart badge: " + badge);

        // XPath by text
        String productName = driver.findElement(
                By.xpath("//div[contains(text(),'Bike Light')]")).getText();
        System.out.println("Product: " + productName);

        if (badge.equals("2")) {
            System.out.println("PASS: Two items in cart");
        } else {
            System.out.println("FAIL: Expected '2' but found '" + badge + "'");
        }

        driver.quit();
    }
}
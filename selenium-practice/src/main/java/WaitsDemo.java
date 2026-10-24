import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitsDemo {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));

        driver.get("https://www.saucedemo.com");

        driver.findElement(By.id("user-name")).sendKeys("performance_glitch_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button"))).click();

        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));
        // WebElement heading = driver.findElement(By.className("title"));
        System.out.println("Heading: " + heading.getText());

        if (heading.getText().equals("Products")) {
            System.out.println("PASS: Login successful");
        } else {
            System.out.println("FAIL: Expected 'Products' but found '" + heading.getText() + "'");
        }

        driver.quit();
    }
}
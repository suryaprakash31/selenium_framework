import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LocatorsDemo {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        String heading = driver.findElement(By.className("title")).getText();
        System.out.println("Heading: " + heading);

        if (heading.equals("Products")) {
            System.out.println("PASS: Login successful");
        } else {
            System.out.println("FAIL: Expected 'Products' but found '" + heading + "'");
        }

        driver.quit();
    }
}
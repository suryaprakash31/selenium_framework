import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstSelenium {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("https://example.com");

        String actualTitle = driver.getTitle();
        System.out.println("Page title: " + actualTitle);

        if (actualTitle.equals("Example Domain")) {
            System.out.println("PASS: Title is correct");
        } else {
            System.out.println("FAIL: Expected 'Example Domain' but found '" + actualTitle + "'");
        }

        driver.quit();
    }
}
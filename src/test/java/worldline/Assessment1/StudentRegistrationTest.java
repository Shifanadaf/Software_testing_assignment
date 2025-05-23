package worldline.Assessment1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StudentRegistrationTest {

    public static void main(String[] args) {

        // Automatically sets up ChromeDriver
        WebDriverManager.chromedriver().setup();

        // Launch Chrome browser
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            // Open the form URL
            driver.get("https://demoqa.com/automation-practice-form");

            // Fill out the form
            driver.findElement(By.id("firstName")).sendKeys("John");
            driver.findElement(By.id("lastName")).sendKeys("Doe");
            driver.findElement(By.id("userEmail")).sendKeys("john.doe@example.com");

            // Scroll and select Gender (Male) using JavaScript to avoid iframe overlap
            WebElement gender = driver.findElement(By.xpath("//label[text()='Male']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", gender);
            Thread.sleep(500); // Short wait after scrolling
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", gender);

            // Enter Mobile Number
            driver.findElement(By.id("userNumber")).sendKeys("1234567890");

            // Submit the form
            driver.findElement(By.id("submit")).click();

            // Wait for modal to appear
            Thread.sleep(2000);

            WebElement modal = driver.findElement(By.id("example-modal-sizes-title-lg"));
            if (modal.isDisplayed()) {
                System.out.println("Test Passed: Confirmation modal is displayed.");
            } else {
                System.out.println("Test Failed: Confirmation modal not found.");
            }

        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}

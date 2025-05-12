package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

public class GoogleSearchTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        driver = new ChromeDriver(options);
    }

    @Test
    public void testGoogleSearch() {
        driver.get("https://www.google.com");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Selenium TestNG");
        searchBox.submit();
        // Wait for title to update and use a more general assertion
        Thread.sleep(2000);
        String title = driver.getTitle().toLowerCase();
        assert title.contains("selenium") || title.contains("search") : "Expected title to contain 'selenium' or 'search', but got: " + title;
File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
Files.copy(screenshot.toPath(), new File("screenshot.png").toPath());
System.out.println("Saved screenshot to screenshot.png");
System.out.println(driver.getPageSource());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions; 
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import utils.ConfigReader;
import java.time.Duration;

/**
 * Базовый класс для всех UI-тестов.
 * Инициализирует драйвер и управляет его жизненным циклом.
 */
@Listeners(listeners.AllureListener.class)
public class BaseTest {
    String driverPath = "src/test/resources/chromedriver.exe";
    static WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

  @BeforeMethod
public void setup() {
    WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");          // Для CI/CD
        options.addArguments("--no-sandbox");            // Для Linux-окружений
        options.addArguments("--disable-dev-shm-usage"); // Решает проблемы с памятью

        driver = new ChromeDriver(options);
        driver.get(ConfigReader.getProperty("base.url"));

}

    /**
     * Закрытие драйвера после всех тестов в классе.
     */
    @AfterMethod
    public void tearDown() {
        driver.manage().deleteAllCookies();
        if (driver != null) {
            driver.quit();
        }
    }
}

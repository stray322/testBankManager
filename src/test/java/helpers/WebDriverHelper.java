package helpers;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;

import java.time.Duration;

/**
 * Утилитный класс для работы с ожиданиями и драйвером.
 */
public class WebDriverHelper {

    private static final int TIMEOUT = ConfigReader.getTimeout();

    public static Alert waitForAlert(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                .until(ExpectedConditions.alertIsPresent());
    }

    // Приватный конструктор, чтобы запретить создание экземпляров класса
    private WebDriverHelper() {}

    /**
     * Ожидает видимости элемента на странице.
     * @param driver Экземпляр WebDriver.
     * @param element Веб-элемент.
     * @return Видимый элемент.
     */
    public static WebElement waitForVisibility(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                .until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Ожидает кликабельности элемента.
     * @param driver Экземпляр WebDriver.
     * @param element Веб-элемент.
     * @return Кликабельный элемент.
     */
    public static WebElement waitForClickable(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Ожидает исчезновения элемента.
     * @param driver Экземпляр WebDriver.
     * @param element Веб-элемент.
     */
    public static void waitForInvisibility(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                .until(ExpectedConditions.invisibilityOf(element));
    }
}

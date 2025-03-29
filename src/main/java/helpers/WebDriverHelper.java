package helpers;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Утилитный класс для работы с ожиданиями и драйвером.
 */
public class WebDriverHelper {

    public static Alert waitForAlert(WebDriver driver, int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.alertIsPresent());
    }

    // Приватный конструктор, чтобы запретить создание экземпляров класса
    private WebDriverHelper() {}

    /**
     * Ожидает видимости элемента на странице.
     * @param driver Экземпляр WebDriver.
     * @param element Веб-элемент.
     * @param timeout Время ожидания в секундах.
     * @return Видимый элемент.
     */
    public static WebElement waitForVisibility(WebDriver driver, WebElement element, int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Ожидает кликабельности элемента.
     * @param driver Экземпляр WebDriver.
     * @param element Веб-элемент.
     * @param timeout Время ожидания в секундах.
     * @return Кликабельный элемент.
     */
    public static WebElement waitForClickable(WebDriver driver, WebElement element, int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Ожидает исчезновения элемента.
     * @param driver Экземпляр WebDriver.
     * @param element Веб-элемент.
     * @param timeout Время ожидания в секундах.
     */
    public static void waitForInvisibility(WebDriver driver, WebElement element, int timeout) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.invisibilityOf(element));
    }
}

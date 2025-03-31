package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Базовый класс для всех страниц.
 * Реализует инициализацию элементов через PageFactory.
 */
public abstract class BasePage {
    protected WebDriver driver;

    /**
     * Конструктор базового класса.
     * @param driver Экземпляр WebDriver.
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Открывает указанный URL.
     * @param url Адрес страницы.
     */
    public void open(String url) {
        driver.get(url);
    }

    /**
     * Возвращает текущий URL страницы.
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}

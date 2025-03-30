package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import helpers.WebDriverHelper;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Page Object для страницы списка клиентов (сортировка и удаление).
 */
public class CustomersPage extends BasePage {

    // Локаторы элементов
    @FindBy(css = "table.table tbody tr")
    private List<WebElement> customerRows;

    @FindBy(css = "button[ng-click='showCust()']")
    private WebElement addCustomerButton;

    @FindBy(css = "a[ng-click*='fName']")
    private WebElement sortByNameButton;

    @FindBy(css = "input[ng-model='searchCustomer']")
    private WebElement searchInput;

    @FindBy(css = "button[ng-click='deleteCust(cust)']")
    private WebElement deleteButton;

    /**
     * Конструктор класса.
     * @param driver Экземпляр WebDriver.
     */
    public CustomersPage(WebDriver driver) {
        super(driver);
    }

    public void clickCustomersB() {
        WebDriverHelper.waitForClickable(driver, addCustomerButton, 10).click();
    }
    /**
     * Сортирует клиентов по имени (First Name).
     */
    public void sortCustomersByName() {
        WebDriverHelper.waitForClickable(driver, sortByNameButton, 10).click();
        WebDriverHelper.waitForClickable(driver, sortByNameButton, 10).click();
    }

    /**
     * Сортирует клиентов по имени (First Name).
     */
    public void sortCustomersByNamed() {
        WebDriverHelper.waitForClickable(driver, sortByNameButton, 10).click();
        WebDriverHelper.waitForClickable(driver, sortByNameButton, 10).click();
        WebDriverHelper.waitForClickable(driver, sortByNameButton, 10).click();
    }

    /**
     * Возвращает список имен клиентов из таблицы.
     */
    public List<String> getCustomerNames() {
        return customerRows.stream()
                .map(row -> row.findElement(By.cssSelector("td:nth-child(1)")).getText())
                .collect(Collectors.toList());
    }

    /**
     * Удаляет клиента с именем, длина которого ближе к средней.
     * @return Имя удаленного клиента
     */
    public String deleteCustomerWithAverageNameLength() {
        WebDriverHelper.waitForClickable(driver, addCustomerButton, 10).click();
        List<String> names = getCustomerNames();
        if (names.isEmpty()) {
            throw new RuntimeException("Нет клиентов для удаления!");
        }

        double averageLength = names.stream()
                .mapToInt(String::length)
                .average()
                .orElse(0.0);

        String nameToDelete = names.stream()
                .min((n1, n2) -> {
                    double diff1 = Math.abs(n1.length() - averageLength);
                    double diff2 = Math.abs(n2.length() - averageLength);
                    return Double.compare(diff1, diff2);
                })
                .orElse("");

        searchInput.sendKeys(nameToDelete);
        WebDriverHelper.waitForClickable(driver, deleteButton, 5).click();
        return nameToDelete;
    }

    /**
     * Проверяет наличие клиента после добавления.
     * @param customerName Имя клиента
     * @return true если клиент найден
     */
    public boolean isCustomerAdded(String customerName) {
        WebDriverHelper.waitForClickable(driver, addCustomerButton, 10).click();
        searchInput.sendKeys(customerName);
        boolean isPresent = !customerRows.isEmpty();
        searchInput.clear();
        return isPresent;
    }

    /**
     * Проверяет наличие клиента в таблице.
     * @param customerName Имя клиента
     * @return true если клиент присутствует
     */
    public boolean isCustomerPresent(String customerName) {
        WebDriverHelper.waitForClickable(driver, addCustomerButton, 10).click();
        return getCustomerNames().contains(customerName);
    }
}
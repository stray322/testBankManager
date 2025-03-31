package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import helpers.WebDriverHelper;
import org.testng.Assert;
import enums.SortOrder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Page Object для страницы списка клиентов банка.
 * Позволяет сортировать клиентов, удалять их и проверять их наличие в таблице.
 */

public class CustomersPage extends BasePage {

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

    private static final int ASCENDING_CLICKS = 2;
    private static final int DESCENDING_CLICKS = 3;

    /**
     * Конструктор класса.
     * @param driver Экземпляр WebDriver.
     */
    public CustomersPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Нажимает кнопку перехода к списку клиентов.
     * @return Текущий экземпляр CustomersPage.
     */
    @Step("Нажатие кнопки 'Customers'")
    public CustomersPage clickCustomersButton() {
        WebDriverHelper.waitForClickable(driver, addCustomerButton).click();
        return this;
    }

    /**
     * Возвращает список имен клиентов из таблицы.
     * @return Список имен клиентов.
     */
    @Step("Получение списка имен клиентов")
    public List<String> getCustomerNames() {
        return customerRows.stream()
                .map(row -> row.findElement(By.cssSelector("td:nth-child(1)")).getText())
                .collect(Collectors.toList());
    }

    /**
     * Выполняет клики для сортировки.
     * @param clicks количество кликов
     */
    private void performSorting(int clicks) {
        for (int i = 0; i < clicks; i++) {
            WebDriverHelper.waitForClickable(driver, sortByNameButton).click();
        }
    }

    /**
     * Сортирует клиентов по имени в указанном порядке.
     * @param order Направление сортировки (ASCENDING/DESCENDING).
     * @return Текущий экземпляр CustomersPage.
     */
    @Step("Сортировка клиентов по имени: {order}")
    public CustomersPage sortCustomers(SortOrder order) {
        int clicks = (order == SortOrder.ASCENDING) ? ASCENDING_CLICKS : DESCENDING_CLICKS;
        performSorting(clicks);
        return this;
    }

    /**
     * Проверяет, что список клиентов отсортирован корректно.
     * @param expectedOrder Ожидаемое направление сортировки.
     */
    @Step("Проверка сортировки: {expectedOrder}")
    public void verifySortOrder(SortOrder expectedOrder) {
        List<String> actualNames = getCustomerNames();
        List<String> expectedSorted = new ArrayList<>(actualNames);
        expectedSorted.sort(expectedOrder.getComparator());
        Assert.assertEquals(actualNames, expectedSorted, "Некорректная сортировка");
    }
    /**
     * Удаляет клиента с именем, длина которого ближе к средней.
     * @return Имя удаленного клиента
     */
    @Step("Удаление клиента с наиболее близкой к средней длиной имени")
    public String deleteCustomerWithAverageNameLength() {
        List<String> names = getCustomerNames();
        if (names.isEmpty()) {
            throw new RuntimeException("Нет клиентов для удаления!");
        }

        double averageLength = names.stream()
                .mapToInt(String::length)
                .average()
                .orElse(0.0);

        String nameToDelete = names.stream()
                .min(Comparator.comparingDouble(name -> Math.abs(name.length() - averageLength)))
                .orElse("");

        searchInput.sendKeys(nameToDelete);
        WebDriverHelper.waitForClickable(driver, deleteButton).click();
        clearSearch();
        return nameToDelete;
    }

    /**
     * Выполняет поиск клиента по имени.
     * @param customerName Имя клиента для поиска.
     * @return Текущий экземпляр {@link CustomersPage} для цепочки вызовов.
     */
    @Step("Поиск клиента: {customerName}")
    public CustomersPage searchCustomer(String customerName) {
        searchInput.sendKeys(customerName);
        return this;
    }

    /**
     * Проверяет, что клиент присутствует в таблице.
     * @param customerName Имя клиента.
     */
    @Step("Проверка наличия клиента: {customerName}")
    public void verifyCustomerPresent(String customerName) {
        searchCustomer(customerName);
        boolean isPresent = !customerRows.isEmpty();
        clearSearch();
        Assert.assertTrue(isPresent, "Клиент '" + customerName + "' не найден");
    }

    /**
     * Проверяет, что клиент отсутствует в таблице.
     * @param customerName Имя клиента.
     */
    @Step("Проверка отсутствия клиента: {customerName}")
    public void verifyCustomerNotPresent(String customerName) {
        searchCustomer(customerName);
        boolean isPresent = !customerRows.isEmpty();
        clearSearch();
        Assert.assertFalse(isPresent, "Клиент '" + customerName + "' не был удален");
    }

    /**
     * Удаляет клиента, если он присутствует в таблице.
     * @param customerName Имя клиента для удаления.
     * @return Текущий экземпляр {@link CustomersPage} для цепочки вызовов.
     */
    @Step("Удаление клиента, если он присутствует: {customerName}")
    public CustomersPage deleteCustomerIfPresent(String customerName) {
        searchInput.sendKeys(customerName);
        if (!customerRows.isEmpty()) {
            WebDriverHelper.waitForClickable(driver, deleteButton).click();
        }
        return this;
    }

    /**
     * Очищает поле поиска клиента.
     * @return Текущий экземпляр CustomersPage.
     */
    @Step("Очистка поля поиска")
    public CustomersPage clearSearch() {
        searchInput.clear();
        return this;
    }
}

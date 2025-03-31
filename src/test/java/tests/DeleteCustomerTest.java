package tests;

import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CustomersPage;
import pages.ManagerPage;
import utils.DataGenerator;

/**
 * Тестовый класс для проверки функциональности удаления клиентов.
 * Убеждается, что клиенты корректно удаляются из системы.
 */
@Epic("UI Тесты")
@Feature("Управление клиентами")
public class DeleteCustomerTest extends BaseTest {

    private String postCode;
    private String firstName;
    private String lastName;
    /**
     * Подготавливает тестовые данные перед каждым тестом.
     * Генерирует случайные данные клиента и добавляет его в систему.
     */
    @BeforeMethod
    public void createCustomerTest() {
        ManagerPage managerPage = new ManagerPage(driver);
        postCode = DataGenerator.generatePostCode();
        firstName = DataGenerator.generateNameFromPostCode(postCode);
        lastName = DataGenerator.generateLastName();
        managerPage.addCustomer(firstName, lastName, postCode);
    }

    @Test(description = "Удаление клиента с длиной имени, близкой к средней")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Пользователь удаляет клиента из таблицы")
    @Description("Проверка, что клиент удаляется из таблицы")
    public void deleteCustomerTest() {
        CustomersPage customersPage = new CustomersPage(driver);
        String deletedCustomerName = customersPage.clickCustomersButton().deleteCustomerWithAverageNameLength();
        customersPage.clickCustomersButton().verifyCustomerNotPresent(deletedCustomerName);
    }

    /**
     * Выполняет очистку данных после каждого теста.
     * Удаляет клиента, если он остался в системе, и очищает поле поиска.
     */
    @AfterMethod
    public void cleanup() {
        // Удаление клиента, если он остался
        CustomersPage customersPage = new CustomersPage(driver);
        customersPage
                .searchCustomer(firstName)
                .clearSearch()
                .deleteCustomerIfPresent(firstName)
                .clearSearch();
    }
}

package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.CustomersPage;
import pages.ManagerPage;
import utils.DataGenerator;

/**
 * Тест на добавление клиента.
 */
@Epic("UI Тесты")
@Feature("Управление клиентами")
public class AddCustomerTest extends BaseTest {

    @Test(description = "Добавление клиента с валидными данными")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Пользователь успешно добавляет клиента через форму")
    @Description("Проверка, что клиент создается с корректным Post Code и First Name")
    public void addCustomerWithValidDataTest() {

        String postCode = DataGenerator.generatePostCode();
        String firstName = DataGenerator.generateNameFromPostCode(postCode);
        String lastName = DataGenerator.generateLastName();

        ManagerPage managerPage = new ManagerPage(driver);
        managerPage.addCustomer(firstName, lastName, postCode);

        CustomersPage customersPage = new CustomersPage(driver);
        customersPage.clickCustomersButton().verifyCustomerPresent(firstName);
    }
}

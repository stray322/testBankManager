package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CustomersPage;
import pages.ManagerPage;
import utils.DataGenerator;

@Epic("UI Тесты")
@Feature("Управление клиентами")
public class DeleteCustomerTest extends BaseTest {

    @BeforeMethod
    public void createTestCustomer() {
        ManagerPage managerPage = new ManagerPage(driver);
        String postCode = DataGenerator.generatePostCode();
        String firstName = DataGenerator.generateNameFromPostCode(postCode);
        String lastName = DataGenerator.generateLastName();
        managerPage.addCustomer(firstName, lastName, postCode);
    }

    @Test(description = "Удаление клиента с длиной имени, близкой к средней")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Пользователь удаляет клиента из таблицы")
    @Description("Проверка, что клиент удаляется из таблицы")
    public void testDeleteCustomer() {
        CustomersPage customersPage = new CustomersPage(driver);
        String deletedCustomerName = customersPage.deleteCustomerWithAverageNameLength();

        Assert.assertFalse(
                customersPage.isCustomerPresent(deletedCustomerName),
                "Клиент '" + deletedCustomerName + "' не был удален"
        );
    }
}
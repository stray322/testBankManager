package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.CustomersPage;
import enums.SortOrder;

@Epic("UI Тесты")
@Feature("Управление клиентами")
public class SortCustomersTest extends BaseTest {

    @Test(description = "Проверка сортировки клиентов по имени")
    @Severity(SeverityLevel.NORMAL)
    @Story("Сортировка клиентов по возрастанию и убыванию")
    public void sortCustomersByNameTest() {
        CustomersPage customersPage = new CustomersPage(driver);
        customersPage.clickCustomersButton();

        customersPage.sortCustomers(SortOrder.ASCENDING)
                .verifySortOrder(SortOrder.ASCENDING);

        customersPage.sortCustomers(SortOrder.DESCENDING)
                .verifySortOrder(SortOrder.DESCENDING);
    }
}

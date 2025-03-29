package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CustomersPage;

import java.util.Collections;
import java.util.List;

@Epic("UI Тесты")
@Feature("Управление клиентами")
public class SortCustomersTest extends BaseTest {

    @Test(description = "Проверка сортировки клиентов по имени")
    @Severity(SeverityLevel.NORMAL)
    @Story("Сортировка клиентов по возрастанию и убыванию")
    public void testSortCustomersByName() {
        CustomersPage customersPage = new CustomersPage(driver);
        customersPage.clickCustomersB();
        customersPage.sortCustomersByName();

        List<String> sortedAscending = customersPage.getCustomerNames();
        Assert.assertEquals(
                sortedAscending.stream().sorted().toList(),
                sortedAscending,
                "Сортировка по возрастанию не работает"
        );

        customersPage.sortCustomersByNamed();

        List<String> sortedDescending = customersPage.getCustomerNames();
        Assert.assertEquals(
                sortedDescending.stream().sorted(Collections.reverseOrder()).toList(),
                sortedDescending,
                "Сортировка по убыванию не работает"
        );
    }
}
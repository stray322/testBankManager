package pages;

import helpers.WebDriverHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object для страницы менеджера банка.
 * Позволяет добавлять новых клиентов через веб-интерфейс.
 */

public class ManagerPage extends BasePage {

    @FindBy(css = "button[ng-click='addCust()']")
    private WebElement addCustomerButton;

    @FindBy(css = "input[ng-model='fName']")
    private WebElement firstNameInput;

    @FindBy(css = "input[ng-model='lName']")
    private WebElement lastNameInput;

    @FindBy(css = "input[ng-model='postCd']")
    private WebElement postCodeInput;

    @FindBy(css = "button[type='submit']")
    private WebElement submitButton;

    /**
     * Конструктор класса.
     * @param driver Экземпляр WebDriver.
     */

    public ManagerPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Добавляет нового клиента через UI.
     * @param firstName Имя клиента.
     * @param lastName Фамилия клиента.
     * @param postCode Почтовый индекс.
     * @return Текущий экземпляр ManagerPage.
     */
    @Step("Добавление клиента: {firstName} {lastName}, почтовый индекс: {postCode}")
    public ManagerPage addCustomer(String firstName, String lastName, String postCode) {
        return clickAddCustomerButton()
                .enterFirstName(firstName)
                .enterLastName(lastName)
                .enterPostCode(postCode)
                .submitForm()
                .handleAlert();
    }

    /**
     * Нажимает кнопку добавления клиента.
     * @return Текущий экземпляр ManagerPage.
     */
    @Step("Нажатие кнопки 'Add Customer'")
    public ManagerPage clickAddCustomerButton() {
        WebDriverHelper.waitForClickable(driver, addCustomerButton).click();
        return this;
    }

    /**
     * Вводит имя клиента.
     * @param firstName Имя клиента.
     * @return Текущий экземпляр ManagerPage.
     */
    @Step("Ввод имени: {firstName}")
    public ManagerPage enterFirstName(String firstName) {
        WebDriverHelper.waitForVisibility(driver, firstNameInput).sendKeys(firstName);
        return this;
    }

    /**
     * Вводит фамилию клиента.
     * @param lastName Фамилия клиента.
     * @return Текущий экземпляр ManagerPage.
     */
    @Step("Ввод фамилии: {lastName}")
    public ManagerPage enterLastName(String lastName) {
        WebDriverHelper.waitForVisibility(driver, lastNameInput).sendKeys(lastName);
        return this;
    }

    /**
     * Вводит почтовый индекс.
     * @param postCode Почтовый индекс.
     * @return Текущий экземпляр ManagerPage.
     */
    @Step("Ввод почтового индекса: {postCode}")
    public ManagerPage enterPostCode(String postCode) {
        WebDriverHelper.waitForVisibility(driver, postCodeInput).sendKeys(postCode);
        return this;
    }

    /**
     * Отправляет форму.
     * @return Текущий экземпляр ManagerPage.
     */
    @Step("Отправка формы")
    public ManagerPage submitForm() {
        WebDriverHelper.waitForClickable(driver, submitButton).click();
        return this;
    }

    /**
     * Обрабатывает всплывающее уведомление.
     * @return Текущий экземпляр ManagerPage.
     */
    @Step("Обработка алерта")
    public ManagerPage handleAlert() {
        Alert alert = WebDriverHelper.waitForAlert(driver);
        alert.accept();
        return this;
    }
}

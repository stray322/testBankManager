package pages;

import helpers.WebDriverHelper;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ManagerPage extends BasePage {

    @FindBy(css = "button[ng-click='addCust()']")
    private WebElement addCustomerButton;

    @FindBy(css = "input[ng-model='fName']")
    private WebElement firstNameInput;

    // Добавлено поле для фамилии
    @FindBy(css = "input[ng-model='lName']")
    private WebElement lastNameInput;

    @FindBy(css = "input[ng-model='postCd']")
    private WebElement postCodeInput;

    @FindBy(css = "button[type='submit']")
    private WebElement submitButton;

    public ManagerPage(WebDriver driver) {
        super(driver);
    }

    public void addCustomer(String firstName, String lastName, String postCode) {
        WebDriverHelper.waitForClickable(driver, addCustomerButton, 10).click();

        WebDriverHelper.waitForVisibility(driver, firstNameInput, 5).sendKeys(firstName);

        WebDriverHelper.waitForVisibility(driver, lastNameInput, 5).sendKeys(lastName);

        WebDriverHelper.waitForVisibility(driver, postCodeInput, 5).sendKeys(postCode);

        WebDriverHelper.waitForClickable(driver, submitButton, 5).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }
}
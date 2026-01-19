package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutInformationPage {
    private final By checkoutInformationTitle = By.xpath("//span[@data-test='title']");
    private final By firstNameInput=By.id("first-name");
    private final By lastNameInput=By.id("last-name");
    private final By postalCodeInput=By.id("postal-code");
    private final By continueButton=By.id("continue");
    private final By errorMessage = By.xpath("//h3[@data-test='error']");
    private final WebDriver driver;

    public CheckoutInformationPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Get the checkout information page title")
    public String getCheckoutInformationTitle() {
        return driver.findElement(checkoutInformationTitle).getText();
    }
    @Step("Fill checkout form with firstName: {firstName}, lastName: {lastName}, postalCode: {postalCode}")
    public void setUserInformationForm(String firstName, String lastName, String postalCode){
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(postalCodeInput).sendKeys(postalCode);
    }
    @Step("Click 'Continue' button on checkout information page")
    public void clickContinue() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(continueButton));
        driver.findElement(continueButton).click();
    }
    @Step("Check if error message is displayed on checkout information page")
    public boolean isErrorDisplayed() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}

package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final By loginLogo=By.xpath("//div[@class='login_logo']");
    private final By usernameInputField=By.id("user-name");
    private final By passwordInputField=By.id("password");
    private final By loginButton=By.id("login-button");
    private final By errorMessage =By.xpath("//h3[@data-test='error']");
    private final WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Get login page logo text")
    public String getLoginLogo(){
       return driver.findElement(loginLogo).getText();
    }
    @Step("Login with username: {username} and password: {password}")
    public void login(String username, String password) {
        driver.findElement(usernameInputField).sendKeys(username);
        driver.findElement(passwordInputField).sendKeys(password);
        clickLoginButton();
    }
    @Step("Click Login button")
    public void clickLoginButton() {
        WebElement loginBtn = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(loginButton));
        loginBtn.click();
    }
    @Step("Check if login error message is displayed")
    public boolean isErrorMessageDisplayed() {
        return !driver.findElements(errorMessage).isEmpty()
                && driver.findElement(errorMessage).isDisplayed();
    }

    @Step("Check if Products page is opened")
    public boolean isProductsPageOpened() {
        return driver.getCurrentUrl().contains("inventory");
    }
}
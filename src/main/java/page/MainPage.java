package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final By sauceLogoHeader=By.xpath("//div[@class='login_logo' and text()='Swag Labs']");
    private final WebDriver driver;
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Check if logo text is displayed")
    public boolean isSauceLogoDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(sauceLogoHeader));
        return driver.findElement(sauceLogoHeader).isDisplayed();
    }
}
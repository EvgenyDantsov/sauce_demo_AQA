package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutOverviewPage {
    private final By checkoutOverviewTitle = By.xpath("//span[@data-test='title']");
    private final By finishButton = By.id("finish");

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    private final WebDriver driver;

    @Step("Get checkout overview page title")
    public String getCheckoutOverviewTitle() {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(checkoutOverviewTitle))
                .getText();
    }

    @Step("Click 'Finish' button on checkout overview page")
    public void clickFinishButton() {
        driver.findElement(finishButton).click();
    }
}

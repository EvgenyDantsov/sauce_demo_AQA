package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage {
    private final By checkoutCompleteTitle= By.xpath("//span[@data-test='title']");
    private final By backhomeButton=By.id("back-to-products");
    private final WebDriver driver;

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Get checkout overview page title")
    public String getCheckoutCompleteTitle() {
        return driver.findElement(checkoutCompleteTitle).getText();
    }
    @Step("Click 'Finish' button on checkout overview page")
    public void clickBackhomeButton(){
        driver.findElement(backhomeButton).click();
    }
}

package page;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private final By cartTitle = By.xpath("//span[@data-test='title']");
    private static final String PRODUCT_ACTION_BUTTON_XPATH ="//div[@class='cart_item'][.//div[text()='%s']]//button";
    private final By shoppingCartBadge=By.xpath("//span[@data-test='shopping-cart-badge']");
    private final WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getCartTitle() {
        return driver.findElement(cartTitle).getText();
    }
    private By productRemoveButton(String productName) {
        return By.xpath(String.format(PRODUCT_ACTION_BUTTON_XPATH, productName));
    }
    public String getRemoveButtonText(String productName) {
        return driver.findElement(productRemoveButton(productName)).getText();
    }
    public void clickRemoveButton(String productName) {
        WebElement productActionButton = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(
                        productRemoveButton(productName)
                ));
        productActionButton.click();
    }
    public int getCartItemsCount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            WebElement badge = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(shoppingCartBadge)
            );
            return Integer.parseInt(badge.getText());
        } catch (TimeoutException e) {
            return 0;
        }
    }
}
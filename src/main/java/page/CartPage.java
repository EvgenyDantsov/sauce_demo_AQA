package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private final By cartTitle = By.xpath("//span[@data-test='title']");
    private final WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getCartTitle() {
        return driver.findElement(cartTitle).getText();
    }
}
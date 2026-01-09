package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {
    private static final String PRODUCT_NAME_XPATH="//div[@data-test='inventory-item-name' and text()='%s']";
    private static final String PRODUCT_CONTAINER_XPATH =
            "//div[@class='inventory_item'][.//div[text()='%s']]";
    private static final String PRODUCT_DESCRIPTION_XPATH =
            PRODUCT_CONTAINER_XPATH + "//div[@data-test='inventory-item-desc']";
    private static final String PRODUCT_PRICE_XPATH =
            PRODUCT_CONTAINER_XPATH + "//div[@data-test='inventory-item-price']";
    private static final String PRODUCT_ACTION_BUTTON_XPATH ="//div[@class='inventory_item'][.//div[text()='%s']]//button";
    private final By productsLabel =By.xpath("//span[@data-test='title']");
    private final By shoppingCartLink =By.xpath("//a[@data-test='shopping-cart-link']");
    private final By shoppingCartBadge=By.xpath("//span[@data-test='shopping-cart-badge']");
    private final By footer=By.xpath("//footer[@data-test='footer']");
    private final WebDriver driver;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }
    private By productName(String productName) {
        return By.xpath(String.format(PRODUCT_NAME_XPATH, productName));
    }
    private By productActionButton(String productName) {
        return By.xpath(String.format(PRODUCT_ACTION_BUTTON_XPATH, productName));
    }
    public String getActionButtonText(String productName) {
        return driver.findElement(productActionButton(productName)).getText();
    }
    public void clickProductActionButton(String productName) {
        driver.findElement(productActionButton(productName)).click();
    }
    private By productDescription(String productName) {
        return By.xpath(String.format(PRODUCT_DESCRIPTION_XPATH, productName));
    }

    private By productPrice(String productName) {
        return By.xpath(String.format(PRODUCT_PRICE_XPATH, productName));
    }
    public int getCartItemsCount() {
        if (driver.findElements(shoppingCartBadge).isEmpty()) {
            return 0;
        }
        return Integer.parseInt(driver.findElement(shoppingCartBadge).getText());
    }
}
package page;

import enums.SortProducts;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductsPage {
    private static final String PRODUCT_NAME_XPATH = "//div[@data-test='inventory-item-name' and text()='%s']";
    private static final String PRODUCT_CONTAINER_XPATH = "//div[@class='inventory_item'][.//div[text()='%s']]";
    private static final String PRODUCT_DESCRIPTION_XPATH = PRODUCT_CONTAINER_XPATH + "//div[@data-test='inventory-item-desc']";
    private static final String PRODUCT_PRICE_XPATH = PRODUCT_CONTAINER_XPATH + "//div[@data-test='inventory-item-price']";
    private static final String PRODUCT_ACTION_BUTTON_XPATH = "//div[@class='inventory_item'][.//div[text()='%s']]//button";
    private final By inventoryItems = By.xpath("//div[@class='inventory_item']");

    private final By productNameInside = By.xpath(".//div[@data-test='inventory-item-name']");

    private final By productDescriptionInside = By.xpath(".//div[@data-test='inventory-item-desc']");

    private final By productPriceInside = By.xpath(".//div[@data-test='inventory-item-price']");

    private final By productActionButtonInside = By.xpath(".//button");
    private final By productsTitle = By.xpath("//span[@data-test='title']");
    private final By shoppingCartLink = By.xpath("//a[@data-test='shopping-cart-link']");
    private final By shoppingCartBadge = By.xpath("//span[@data-test='shopping-cart-badge']");
    private final By sortDropdown = By.xpath("//select[@data-test='product-sort-container']");
    private final By footer = By.xpath("//footer[@data-test='footer']");
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

    @Step("Get action button text for product: {productName}")
    public String getActionButtonText(String productName) {
        return driver.findElement(productActionButton(productName)).getText();
    }

    @Step("Click action button for product: {productName}")
    public void clickProductActionButton(String productName) {
        WebElement productActionButton = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(
                        productActionButton(productName)
                ));
        productActionButton.click();
    }

    @Step("Click shopping cart button")
    public void clickShoppingCartButton() {
        WebElement shoppingCartButton = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(shoppingCartLink));
        shoppingCartButton.click();
    }
    @Step("Select product sort option: {sortOption}")
    public void selectSortOption(SortProducts sortOption) {
        WebElement dropdown = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(sortDropdown));

        Select select = new Select(dropdown);
        select.selectByVisibleText(sortOption.getVisibleText());
    }

    private By productDescription(String productName) {
        return By.xpath(String.format(PRODUCT_DESCRIPTION_XPATH, productName));
    }

    private By productPrice(String productName) {
        return By.xpath(String.format(PRODUCT_PRICE_XPATH, productName));
    }

    @Step("Get number of items in the cart")
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

    @Step("Get products page title")
    public String getProductsTitle() {
        return driver.findElement(productsTitle).getText();
    }

    @Step("Get total number of products on the page")
    public int getProductsCount() {
        return driver.findElements(inventoryItems).size();
    }

    @Step("Verify all products have name, description, price and action button")
    public void verifyAllProductsHaveRequiredElements() {
        List<WebElement> products = driver.findElements(inventoryItems);

        for (WebElement product : products) {
            String name = product.findElement(productNameInside).getText();

            if (product.findElements(productDescriptionInside).isEmpty()) {
                throw new AssertionError(
                        "Product '" + name + "' does not have description"
                );
            }

            if (product.findElements(productPriceInside).isEmpty()) {
                throw new AssertionError(
                        "Product '" + name + "' does not have price"
                );
            }

            if (product.findElements(productActionButtonInside).isEmpty()) {
                throw new AssertionError(
                        "Product '" + name + "' does not have action button"
                );
            }
        }
    }

    @Step("Check if product price for {productName} matches format $XX.XX")
    public boolean isProductPriceFormatValid(String productName) {
        String priceText = driver.findElement(productPrice(productName)).getText();
        // Проверяем формат: $XX.XX
        return priceText.matches("\\$\\d+\\.\\d{2}");
    }

    @Step("Check if footer is displayed")
    public boolean isFooterDisplayed() {
        return !driver.findElements(footer).isEmpty();
    }
    public List<Double> getAllProductPrices() {
        List<WebElement> priceElements = driver.findElements(By.className("inventory_item_price"));

        List<Double> prices = new ArrayList<>();
        for (WebElement price : priceElements) {
            prices.add(Double.parseDouble(price.getText().replace("$", "")));
        }
        return prices;
    }
}
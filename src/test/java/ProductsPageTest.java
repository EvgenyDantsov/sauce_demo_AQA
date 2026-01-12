import factory.WebDriverUtil;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.CartPage;
import page.LoginPage;
import page.ProductsPage;
import util.BaseTest;

import static org.junit.Assert.*;

public class ProductsPageTest extends BaseTest {
    private static final String BROWSER = "chrome";
    private WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;

    @Before
    public void webDriver() {
        driver = WebDriverUtil.getWebDriver(BROWSER);
        driver.get(BASE_URL);
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test
    @DisplayName("Products page title is displayed correctly after login")
    public void openProductsPageAfterLoginTest() {
        assertEquals("Products", productsPage.getProductsTitle());
    }

    @Test
    @DisplayName("Products list is displayed and contains items")
    public void productsListIsDisplayedTest() {
        assertTrue(productsPage.getProductsCount() > 0);
    }

    @Test
    @DisplayName("All products have name, description, price and action button")
    public void shouldDisplayRequiredElementsForEachProductTest() {
        productsPage.verifyAllProductsHaveRequiredElements();
    }

    @Test
    @DisplayName("Product price has correct format $XX.XX")
    public void productsPriceHasCorrectFormatTest() {
        assertTrue(productsPage.isProductPriceFormatValid("Sauce Labs Backpack"));
    }

    @Test
    @DisplayName("Product button text changes to 'Remove' after adding to cart")
    public void removeProductForCartFromProductsPageTest() {
        productsPage.clickProductActionButton("Sauce Labs Backpack");
        assertEquals("Remove", productsPage.getActionButtonText("Sauce Labs Backpack"));
    }

    @Test
    @DisplayName("Button text toggles between 'Add to cart' and 'Remove' when product is added/removed")
    public void buttonTextTogglesOnAddRemoveTest() {
        productsPage.clickProductActionButton("Sauce Labs Backpack");
        productsPage.clickProductActionButton("Sauce Labs Backpack");
        assertEquals("Add to cart", productsPage.getActionButtonText("Sauce Labs Backpack"));
    }

    @Test
    @DisplayName("Footer should be visible on Products page")
    public void footerIsDisplayedOnProductPageTest() {
        assertTrue(productsPage.isFooterDisplayed());
    }

    @After
    public void quitBrowser() {
        // Закрываем браузер
        WebDriverUtil.quitDriver(driver);
    }
}
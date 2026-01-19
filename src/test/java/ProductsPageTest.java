import enums.SortProducts;
import factory.WebDriverUtil;
import io.qameta.allure.Issue;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.CartPage;
import page.HeaderMenu;
import page.LoginPage;
import page.ProductsPage;
import util.BaseTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class ProductsPageTest extends BaseTest {
    private static final String BROWSER = "chrome";
    private static final String PRODUCT_NAME = "Sauce Labs Backpack";
    private WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    HeaderMenu headerMenu;

    @Before
    public void webDriver() {
        driver = WebDriverUtil.getWebDriver(BROWSER);
        driver.get(BASE_URL);
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        headerMenu = new HeaderMenu(driver);
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
        assertTrue(productsPage.isProductPriceFormatValid(PRODUCT_NAME));
    }

    @Test
    @DisplayName("Product button text changes to 'Remove' after adding to cart")
    public void removeProductForCartFromProductsPageTest() {
        productsPage.clickProductActionButton(PRODUCT_NAME);
        assertEquals("Remove", productsPage.getActionButtonText(PRODUCT_NAME));
    }

    @Test
    @DisplayName("Button text toggles between 'Add to cart' and 'Remove' when product is added/removed")
    public void buttonTextTogglesOnAddRemoveTest() {
        productsPage.clickProductActionButton(PRODUCT_NAME);
        productsPage.clickProductActionButton(PRODUCT_NAME);
        assertEquals("Add to cart", productsPage.getActionButtonText(PRODUCT_NAME));
    }

    @Test
    @DisplayName("Footer should be visible on Products page")
    public void footerIsDisplayedOnProductPageTest() {
        assertTrue(productsPage.isFooterDisplayed());
    }

    @Issue("SAUCE-RESET-001")
    @Test
    @DisplayName("Reset App State clears cart and resets product buttons to 'Add to cart'")
    public void resetAppStateShouldClearCartAndResetProductButtonTest() {
        productsPage.clickProductActionButton(PRODUCT_NAME);
        headerMenu.clickMenuButton();
        headerMenu.clickResetAppState();
        assertEquals(0, productsPage.getCartItemsCount());
        assertEquals("Add to cart", productsPage.getActionButtonText(PRODUCT_NAME));
    }

    @Test
    @DisplayName("Products can be sorted by price from low to high")
    public void productsCanBeSortedByPriceLowToHighTest() {
        productsPage.selectSortOption(SortProducts.PRICE_LOW_TO_HIGH);
        List<Double> prices = productsPage.getAllProductPrices();
        List<Double> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices);
        assertEquals(sortedPrices, prices);
    }

    @After
    public void quitBrowser() {
        // Закрываем браузер
        WebDriverUtil.quitDriver(driver);
    }
}
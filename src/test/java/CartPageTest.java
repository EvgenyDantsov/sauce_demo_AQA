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

import static org.junit.Assert.assertEquals;

public class CartPageTest extends BaseTest {
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
    public void openCartPageAfterLoginTest() {
        productsPage.clickShoppingCartButton();
        assertEquals("Your Cart", cartPage.getCartTitle());
    }
    @Test
    @DisplayName("Removing product from cart should reset cart item counter")
    public void removeProductFromCartUpdatesCartItemCountTest() {
        String productName = "Sauce Labs Backpack";
        productsPage.clickProductActionButton(productName);
        productsPage.clickShoppingCartButton();
        cartPage.clickRemoveButton(productName);
        assertEquals(0, cartPage.getCartItemsCount());
    }
    @After
    public void quitBrowser() {
        // Закрываем браузер
        WebDriverUtil.quitDriver(driver);
    }
}

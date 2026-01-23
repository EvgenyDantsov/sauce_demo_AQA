import factory.WebDriverUtil;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import page.LoginPage;
import page.ProductsPage;
import util.BaseTest;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CartItemCountParameterizedTest extends BaseTest {
    private static final String BROWSER = "chrome";
    LoginPage loginPage;
    ProductsPage productsPage;
    private final List<String> productsToAdd;
    private final int expectedCount;
    private WebDriver driver;

    public CartItemCountParameterizedTest(List<String> productsToAdd, int expectedCount) {
        this.productsToAdd = productsToAdd;
        this.expectedCount = expectedCount;
    }

    @Parameterized.Parameters(name = "{index}: adding {0} -> expected count={1}")
    public static Object[][] getData() {
        return new Object[][]{
                {List.of("Sauce Labs Backpack"), 1},
                {List.of("Sauce Labs Backpack", "Sauce Labs Bike Light"), 2},
                {List.of("Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt"), 3}
        };
    }

    @Before
    public void webDriver() {
        driver = WebDriverUtil.getWebDriver(BROWSER);
        driver.get(BASE_URL);
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test
    @DisplayName("Cart item counter updates correctly when adding multiple products")
    public void cartItemCountTest() {
        for (String product : productsToAdd) {
            productsPage.clickProductActionButton(product);
        }
        assertEquals(expectedCount, productsPage.getCartItemsCount());
    }

    @After
    public void quitBrowser() {
        // Закрываем браузер
        WebDriverUtil.quitDriver(driver);
    }
}
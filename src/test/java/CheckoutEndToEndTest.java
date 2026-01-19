import factory.WebDriverUtil;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.*;
import util.BaseTest;

import static org.junit.Assert.assertEquals;

public class CheckoutEndToEndTest extends BaseTest {
    private static final String BROWSER = "chrome";
    private static final String PRODUCT_NAME = "Sauce Labs Backpack";
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    CheckoutInformationPage checkoutInformationPage;
    CheckoutOverviewPage checkoutOverviewPage;
    CheckoutCompletePage checkoutCompletePage;
    private WebDriver driver;

    @Before
    public void webDriver() {
        driver = WebDriverUtil.getWebDriver(BROWSER);
        driver.get(BASE_URL);
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutInformationPage = new CheckoutInformationPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test
    @DisplayName("User can successfully complete checkout flow from login to order confirmation")
    public void userCanCompleteCheckoutFlowSuccessfullyTest() {
        productsPage.clickProductActionButton(PRODUCT_NAME);
        productsPage.clickShoppingCartButton();
        cartPage.clickCheckoutButton();
        checkoutInformationPage.setUserInformationForm("Tom", "Holland", "12345");
        checkoutInformationPage.clickContinue();
        checkoutOverviewPage.clickFinishButton();
        assertEquals("Checkout: Complete!", checkoutCompletePage.getCheckoutCompleteTitle());
        }

    @After
    public void quitBrowser() {
        // Закрываем браузер
        WebDriverUtil.quitDriver(driver);
    }
}

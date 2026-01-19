import factory.WebDriverUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.CartPage;
import page.HeaderMenu;
import page.LoginPage;
import page.ProductsPage;
import util.BaseTest;

import static org.junit.Assert.assertEquals;

public class LogoutUserTest extends BaseTest {
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
        headerMenu=new HeaderMenu(driver);
        loginPage.login("standard_user", "secret_sauce");
    }
    @Test
    public void logoutUserTest(){
        headerMenu.clickMenuButton();
        headerMenu.clickLogout();
        assertEquals("Swag Labs", loginPage.getLoginLogo());
    }
    @After
    public void quitBrowser() {
        // Закрываем браузер
        WebDriverUtil.quitDriver(driver);
    }
}

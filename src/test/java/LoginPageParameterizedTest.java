import factory.WebDriverUtil;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import page.LoginPage;
import util.BaseTest;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class LoginPageParameterizedTest extends BaseTest {
    private static final String BROWSER = "chrome";
    private final String username;
    private final String password;
    private final boolean isLoginSuccessful;
    private WebDriver driver;
    LoginPage loginPage;

    public LoginPageParameterizedTest(String username, String password, boolean isLoginSuccessful) {
        this.username = username;
        this.password = password;
        this.isLoginSuccessful=isLoginSuccessful;
    }
    @Parameterized.Parameters(name="Test {index}")
    public static Object[][] getLoginBox() {
        return new Object[][]{
                {"standard_user", "secret_sauce", true},
                {"", "", false},
                {"locked_out_user", "secret_sauce", false},
                {"invalidUser", "invalidPassword", false}
        };
    }
    @Before
    public void webDriver() {
        driver = WebDriverUtil.getWebDriver(BROWSER);
        driver.get(BASE_URL);
        loginPage = new LoginPage(driver);
    }
    @Test
    public void swagLabsLoginTest() {
        loginPage.login(username, password);
        if (isLoginSuccessful) {
            assertTrue(
                    "Products page should be opened after successful login",
                    loginPage.isProductsPageOpened()
            );
        } else {
            assertTrue(
                    "Error message should be displayed for invalid login",
                    loginPage.isErrorMessageDisplayed()
            );
        }
    }

    @After
    public void quitBrowser() {
        // Закрываем браузер
        WebDriverUtil.quitDriver(driver);
    }
}

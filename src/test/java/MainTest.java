import factory.WebDriverUtil;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.MainPage;
import util.BaseTest;

import static org.junit.Assert.assertTrue;

public class MainTest extends BaseTest {
    private static final String BROWSER = "chrome";
    private WebDriver driver;
    MainPage mainPage;

    @Before
    public void webDriver() {
        driver = WebDriverUtil.getWebDriver(BROWSER);
        driver.get(BASE_URL);
        mainPage = new MainPage(driver);
    }

    @Test
    @DisplayName("Swag labs logo is displayed on main page")
    public void sauceLogoTest() {
        assertTrue("Логотип Swag Labs должен отображаться на главной странице", mainPage.isSauceLogoDisplayed());
    }

    @After
    public void quitBrowser() {
        // Закрываем браузер
        WebDriverUtil.quitDriver(driver);
    }
}

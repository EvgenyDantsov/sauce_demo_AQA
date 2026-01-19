import factory.WebDriverUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import page.*;
import util.BaseTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class CheckoutPageParameterizedTest extends BaseTest {
    private static final String BROWSER = "chrome";
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    CheckoutInformationPage checkoutInformationPage;
    CheckoutOverviewPage checkoutOverviewPage;
    private final String firstName;
    private final String lastName;
    private final String postalCode;
    private final boolean shouldProceed;
    private WebDriver driver;

    public CheckoutPageParameterizedTest(String firstName, String lastName, String postalCode, boolean shouldProceed) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.postalCode = postalCode;
        this.shouldProceed = shouldProceed;
    }

    @Parameterized.Parameters(name = "firstName={0}, lastName={1}, postalCode={2}")
    public static Object[][] getData() {
        return new Object[][]{
                {"Tom", "Holland", "12345", true},
                {"Tom", "Holland", "", false},
                {"Tom", "", "12345", false},
                {"", "Holland", "12345", false},
                {"", "", "", false}
        };
    }

    @Before
    public void webDriver() {
        driver = WebDriverUtil.getWebDriver(BROWSER);
        driver.get(BASE_URL);
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage=new CartPage(driver);
        checkoutInformationPage =new CheckoutInformationPage(driver);
        checkoutOverviewPage =new CheckoutOverviewPage(driver);
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test
    public void checkoutTest() {
        String productsName = "Sauce Labs Backpack";
        productsPage.clickProductActionButton(productsName);
        productsPage.clickShoppingCartButton();
        cartPage.clickCheckoutButton();
        checkoutInformationPage.setUserInformationForm(firstName,lastName,postalCode);
        checkoutInformationPage.clickContinue();
        if(shouldProceed){
            assertEquals("Checkout: Overview", checkoutOverviewPage.getCheckoutOverviewTitle());
        }else{
            assertTrue(checkoutInformationPage.isErrorDisplayed());
            assertEquals("Checkout: Your Information", checkoutInformationPage.getCheckoutInformationTitle());
        }
    }

    @After
    public void quitBrowser() {
        // Закрываем браузер
        WebDriverUtil.quitDriver(driver);
    }
}

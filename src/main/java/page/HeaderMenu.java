package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HeaderMenu {
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By resetAppStateMenu = By.id("reset_sidebar_link");
    private final By logoutMenu = By.id("logout_sidebar_link");
    private final WebDriver driver;

    public HeaderMenu(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click menu button")
    public void clickMenuButton() {
        driver.findElement(menuButton).click();
    }

    @Step("Click 'Reset App State' in menu")
    public void clickResetAppState() {
        driver.findElement(resetAppStateMenu).click();
    }

    @Step("Click 'Logout' in menu")
    public void clickLogout() {
        WebElement logoutBtn = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(logoutMenu));
        logoutBtn.click();
    }
}

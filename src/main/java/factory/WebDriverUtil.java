package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverUtil {
    public static WebDriver getWebDriver(String browser) {
        WebDriver driver = null;
        // Используем конструкцию switch для выбора нужного браузера в котором тестируем
        switch (browser.toLowerCase()) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "yandex":
                // Устанавливаем путь к исполняемому файлу Яндекс.Браузера
                System.setProperty("webdriver.chrome.driver", "G:/projects/yandexDriver/yandexdriver.exe");
                driver = new ChromeDriver();
                break;
        }
        return driver;
    }

    public static void quitDriver(WebDriver driver) {
        driver.quit();
    }
}

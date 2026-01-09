package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WebDriverUtil {
    private static Properties loadConfig() {
        Properties config = new Properties();
        try (InputStream input = WebDriverUtil.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            config.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки config.properties", e);
        }
        return config;
    }
    public static WebDriver getWebDriver(String browser) {
        Properties config = loadConfig();
        WebDriver driver = null;
        // Используем конструкцию switch для выбора нужного браузера в котором тестируем
        switch (browser.toLowerCase()) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", config.getProperty("chrome.driver"));
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

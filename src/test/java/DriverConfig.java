import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverConfig {
    public static WebDriver createDriver() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--window-size=1800,960");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");

        if (Boolean.parseBoolean(ConfigReader.get("headless", "true"))) {
            options.addArguments("--headless=new");
        }

        String seleniumUrl = ConfigReader.get("selenium.url", "http://selenium:4444/wd/hub");

        return new RemoteWebDriver(new URL(seleniumUrl), options);
    }
}
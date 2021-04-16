package Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class DriverConfig {
    public static WebDriver driver = null;

    public static WebDriver getDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "./.idea/drivers/chromedriver.exe");
            driver = new ChromeDriver();
        }
        return driver;
    }

        public static void initialize() {
            getDriver();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.get("http://localhost:4200");
        }

        public static void quit() {
            driver.quit();
            driver = null;
        }
    }

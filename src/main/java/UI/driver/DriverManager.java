package UI.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static UI.constants.Paths.DRIVERS_PATH;
import static UI.constants.TimeoutVariable.*;


public class DriverManager {


    public static WebDriver createDriver() {
        Config config = new Config();
        WebDriver driver = null;
        if (config.getOsType().equals("linux")) {
            if (config.getBrowserType().equals("chrome")){
                System.setProperty("webdriver.chrome.driver", DRIVERS_PATH + "chromedriver");
                driver = new ChromeDriver();
            }
            if (config.getBrowserType().equals("firefox")) {
                System.setProperty("webdriver.gecko.driver", DRIVERS_PATH + "geckodriver");
                driver = new FirefoxDriver();
            }
        }
        if (config.getOsType().equals("windows")) {
            if (config.getBrowserType().equals("chrome")){
                System.setProperty("webdriver.chrome.driver", DRIVERS_PATH + "chromedriver.exe");
                driver = new ChromeDriver();
            }
            if (config.getBrowserType().equals("firefox")) {
                System.setProperty("webdriver.gecko.driver", DRIVERS_PATH + "geckodriver.exe");
                driver = new FirefoxDriver();
            }
        }

        assert driver != null;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(IMPLICIT_ELEMENT_WAIT, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(IMPLICIT_PAGE_WAIT, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(IMPLICIT_SCRIPT_WAIT, TimeUnit.SECONDS);

        return driver;
    }

}

package SKIP_UI.base;

import UI.driver.DriverManager;
import UI.pages.authorization.AuthorizationPage;
import UI.pages.base.BasePage;
import UI.pages.log.Log4j2Example;
import UI.pages.log.Log4j2Example;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.net.MalformedURLException;


public class BaseTest {

    protected WebDriver driver;
    protected AuthorizationPage authorizationPage;
    protected Log4j2Example log;
    protected BasePage basePage;


    @BeforeClass(alwaysRun = true)
    public void openBrowser() throws MalformedURLException {
        driver = DriverManager.createDriver();
        //driver = CustomDriver.getWebDriver();


    }

    @BeforeClass(dependsOnMethods = "openBrowser")
    public void initPage() {
        authorizationPage = new AuthorizationPage(driver);
        basePage = new BasePage(driver);
        log = new Log4j2Example(driver);
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
    }


    public void logFailure(Throwable e) {
        Log4j2Example.logger(e);

    }
}
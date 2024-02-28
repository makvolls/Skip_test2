package Frontend.pages.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BasePage {

    public static final String SUDIS_AUTHORIZATION_PAGE_URL = "http://idp.int.sudis.at-consulting.ru/idp/account/";
    public static final int IMPLICIT_WAIT = 10;
    public static final int EXPLICIT_WAIT = 10;
    protected WebDriver driver;
    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    /**
     * Follow certain link
     * **/

    public void openUrl(String url){
        driver.get(url);
    }

    /**
     * Open main page on SKIP
     * **/

    public void openMainPage(){
        driver.get("http://skip.rtech.ru/");
    }

    /**
     * Open SUDIS Authorization page
     * **/

    public void openAuthorizationPage(){
        openUrl(SUDIS_AUTHORIZATION_PAGE_URL);
    }

    /**
     * Find certain element on page and scroll to it
     * **/

    public WebElement findElement(String locator){
        WebElement element = driver.findElement(By.xpath(locator));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView()", element);
        return element;
    }

    /**
     * Find certain elements on page and store them in the list
     * **/

    public List<WebElement> findElements(String locator){
        return driver.findElements(By.xpath(locator));
    }

    /**
     * Click on certain element
     * **/

    public void click(String locator){
        findElement(locator).click();
    }

    /**
     * Clear value in certain element
     * **/

    public void clear(String locator){
        findElement(locator).clear();
    }

    /**
     * Send value to certain element
     * **/

    public void sendKeys(String locator, String text){
        findElement(locator).sendKeys(text);
    }

    /**
     * Clear value in certain element and send another value to it
     * **/

    public void sendKeysWithClear(String locator, String text){
        clear(locator);
        sendKeys(locator, text);
    }

    /**
     * Get value from certain element
     * **/

    public String getText(String locator){
        return findElement(locator).getText();
    }

    // setup and teardown methods:

    /**
     * Setup for driver
     * **/

    public void setup(){
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(6, TimeUnit.SECONDS);
//        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    /**
     * Close and quit browser
     * **/

    public void tearDown(){
        driver.close();
        driver.quit();
    }


    public void waitElementDisplay(String locator, int second){
        new WebDriverWait(driver, second).until(d-> findElement(locator).isDisplayed());
    }

    /**
     * Wait for certain element on the page
     * **/

    public  void waitElementDisplay(String locator){
        waitElementDisplay(locator, EXPLICIT_WAIT);
    }

    /**
     * Authorization with SUDIS
     * **/

    public void authorization(){
        openAuthorizationPage();
        waitElementDisplay("//button[@class=\"form-button form-button_last form-button_minor\"]");
        click("//button[@class=\"form-button form-button_last form-button_minor\"]");
        sendKeys("//input[@id=\"login\"]","authorization_test");
        sendKeys("//input[@id=\"password\"]","crjhjcnm");
        click("//button[@type=\"submit\"]");
        waitElementDisplay("//div[@class=\"col-sm-6 b-title\"]");
    }

    /**
     * Get text from certain element
     * **/

    public String getTextFromBlock(String locator){
        waitElementDisplay(locator);
        return getText(locator);
    }
}
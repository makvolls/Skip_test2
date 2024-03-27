package Frontend.pages.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static Frontend.common.Config.CLEAR_COOKIES_AND_STORAGE;
import static Frontend.constants.Constant.URLS.*;
import static Frontend.constants.Constant.TimeoutVariable.*;
import static Frontend.pages.base.BasePageXpath.XPATH.*;
import static Frontend.pages.base.BasePageXpath.AuthorizationParameters.*;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    //WebDriverWait wait = new WebDriverWait(driver, IMPLICIT_WAIT);

    /**
     * Follow certain link
     **/

    public void openUrl(String url) {
        driver.get(url);
    }

    /**
     * Open main page on SKIP
     **/

    public void openMainPage() {
        driver.get(SKIP_MAIN_PAGE_URL);
    }

    /**
     * Open SUDIS Authorization page
     **/

    public void openAuthorizationPage() {
        openUrl(SUDIS_AUTHORIZATION_PAGE_URL);
    }

    /**
     * Find certain element on page and scroll to it
     **/

    public WebElement findElement(String locator) {
        WebElement element = driver.findElement(By.xpath(locator));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView()", element);
        return element;
    }

    /**
     * Find certain elements on page and store them in the list
     **/

    public List<WebElement> findElements(String locator) {
        return driver.findElements(By.xpath(locator));
    }

    /**
     * Click on certain element
     **/

    public void click(String locator) {
        findElement(locator).click();
    }

    /**
     * Clear value in certain element
     **/

    public void clear(String locator) {
        findElement(locator).clear();
    }

    /**
     * Send value to certain element
     **/

    public void sendKeys(String locator, String text) {
        findElement(locator).sendKeys(text);
    }

    /**
     * Clear value in certain element and send another value to it
     **/

    public void sendKeysWithClear(String locator, String text) {
        clear(locator);
        sendKeys(locator, text);
    }

    /**
     * Get value from certain element
     **/

    public String getText(String locator) {
        return findElement(locator).getText();
    }

    // setup and teardown methods:

    /**
     * Setup for driver
     **/

    public void setup() {
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(6, TimeUnit.SECONDS);
//        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    /**
     * Close and quit browser
     **/

    public void tearDown() {
        driver.close();
        driver.quit();
    }


    public void waitElementDisplay(String locator, int second) {
        new WebDriverWait(driver, second).until(d -> findElement(locator).isDisplayed());
    }

    /**
     * Wait for certain element on the page
     **/

    public void waitElementDisplay(String locator) {
        waitElementDisplay(locator, EXPLICIT_WAIT);
    }

    /**
     * Authorization with SUDIS
     **/

    public void authorization() {
        openAuthorizationPage();
        waitElementDisplay(BUTTON_SIGN_IN_WITH_PASSWORD);
        click(BUTTON_SIGN_IN_WITH_PASSWORD);
        sendKeys(INPUT_FIELD_LOGIN, SUDIS_LOGIN);
        sendKeys(INPUT_FIELD_PASSWORD, SUDIS_PASSWORD);
        click(BUTTON_SIGN_IN);
        waitElementDisplay(TITLE_PAGE);
    }

    /**
     * Get text from certain element
     **/

    public String getTextFromBlock(String locator) {
        waitElementDisplay(locator);
        return getText(locator);
    }

    /**
     * Clear cookies
     **/

    public void clearCookiesAndLocalStorage() {
        if (CLEAR_COOKIES_AND_STORAGE) {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            driver.manage().deleteAllCookies();
            javascriptExecutor.executeScript("window.sessionStorage.clear()");
        }
    }

//    /**
//     * Checking displaying certain element on the page
//     * @return true - Если элемент не отображается на странице
//     * ; false - Если элемент отображается на странице
//     * **/
//
//    public boolean isBlockNotDisplayed(String locator){
//        return wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
//    }



    public void moveToElement(WebDriver driver, String locator) {
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(By.xpath(locator));
        actions.moveToElement(element).build().perform();
    }

    public void scrollDown(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void scrollUp(WebDriver driver) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0)");
    }
    public void clearInputField(WebDriver driver, By locator) {
        WebElement inputField = driver.findElement(locator);
        inputField.clear();

    }
}

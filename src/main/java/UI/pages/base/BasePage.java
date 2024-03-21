package UI.pages.base;

import UI.constants.TimeoutVariable;
import UI.pages.listproviders.ListProvidersPage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import static UI.constants.TimeoutVariable.EXPLICIT_ELEMENT_PRESENCE_WAIT;
import static UI.constants.TimeoutVariable.EXPLICIT_WAIT;
import static UI.constants.Urls.START_PAGE;
import static UI.pages.userprofile.UserProfileSystemPage.locatorNameBlock;
import static UI.pages.userprofile.UserProfileSystemPage.locatorNameLabel;
import static java.lang.Thread.sleep;


public class BasePage {

    public static String locatorButton(String nameButton) {
        return "//span[text()[contains(.,'" + nameButton + "')]]/parent::button";
    }

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void openURl(String url) {
        driver.get(url);
    }

    public void openStartPage() {
        openURl(START_PAGE);
    }

    public List<WebElement> findElements(String locator) {
        return driver.findElements(By.xpath(locator));
    }

    public WebElement findFirstVisibleElement(String locator) {
        List<WebElement> elements = findElements(locator);
        WebElement element = null;
        for (WebElement el : elements) {
            if (el.isDisplayed()) {
                element = el;
                break;
            }
        }
        return element;
    }

    public WebElement findElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }

    public void waitElementIsClickable(String locator, int second) {
        try {
            new WebDriverWait(driver, second).until(ExpectedConditions.elementToBeClickable(findElement(locator)));
        } catch (Exception ignored) {
        }
    }

    public void waitElementIsPresence(String locator, int second) {
        try {
            new WebDriverWait(driver, second).until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
        } catch (Exception ignored) {
        }
    }

    public void waitElementIsPresence(String locator) {
        try {
            waitElementIsPresence(locator, EXPLICIT_ELEMENT_PRESENCE_WAIT);
        } catch (Exception ignored) {
        }
    }

    public void waitElementIsVisible(String locator, int second) {
        try {
            new WebDriverWait(driver, second).until(ExpectedConditions.visibilityOf(findElement(locator)));
        } catch (Exception ignored) {
        }
    }

    public void waitElementIsInvisible(String locator, int second) {
        try {
            new WebDriverWait(driver, second).until(ExpectedConditions.invisibilityOfAllElements(findElements(locator)));

        } catch (Exception ignored) {

        }

    }

    public void waitElementIsVisible(String locator) {
        waitElementIsVisible(locator, EXPLICIT_WAIT);
    }

    public void waitElementIsInvisible(String locator) {
        waitElementIsInvisible(locator, EXPLICIT_WAIT);
    }

    public void click(String locator) {
        findElement(locator).click();
    }

    public void clickOnElement(WebElement element) {
        element.click();
    }

    public void doubleClick(String locator) {
        new Actions(driver).doubleClick(findElement(locator)).perform();
    }

    public void clear(String locator) {
        findElement(locator).clear();
    }

    public void sendKeys(String locator, String text) {
        waitElementIsPresence(locator);
        findElement(locator).sendKeys(text);
    }

    public String getText(String locator) {
        return findElement(locator).getText();
    }
    public String getAttributeValue(String locator) {
        return findElement(locator).getAttribute("value");
    }

    public boolean isElementDisplay(String locator) {
        try {
            return findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDisplayBlock(String blockName) {
        return isElementDisplay(locatorNameBlock(blockName));
    }

    public boolean isDisplayLabel(String labelName) {
        return isElementDisplay(locatorNameLabel(labelName));
    }

    public boolean isDisplayButton(String buttonName) {
        return isElementDisplay(locatorButton(buttonName));

    }

    public void waitLoading() throws InterruptedException {
        sleep(1);
        long start = System.currentTimeMillis();
        while (isElementDisplay("//div[@id='statusDialog']//img[@src[contains(.,'javax')]]")) {
            sleep(100, TimeoutVariable.IMPLICIT_ELEMENT_WAIT);
            if (System.currentTimeMillis() - start > 5 * 60 * 1000) {
                throw new RuntimeException("Ожидание окончания загрузки - таймаут");
            }
        }
    }

    public void setSelectWithSendKeys(String nameLabel, String value) {
        String elLocator = ListProvidersPage.dropdownElement(value);
        click(ListProvidersPage.locatorLabelList(nameLabel));
        sendKeys(ListProvidersPage.locatorInputLabel(nameLabel), value);
        waitElementIsVisible(elLocator);
        click(elLocator);
        waitElementIsInvisible(elLocator);

    }

    public void setLabelText(String nameLabel, String value) {
        click(ListProvidersPage.locatorLabelList(nameLabel));
        sendKeys(ListProvidersPage.locatorInputLabel(nameLabel), value);

}
    public void clickButton(String buttonName) {
        waitElementIsPresence(locatorButton(buttonName));
        waitElementIsVisible(locatorButton(buttonName));
        waitElementIsClickable(locatorButton(buttonName), 10);
        click(locatorButton(buttonName));
    }

}

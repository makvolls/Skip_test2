package SKIP_API;

import Frontend.pages.base.BasePage;
import Frontend.providers.Providers;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.swing.*;
import java.util.List;

import static Frontend.pages.constructorGo.ConstructorGoXpath.MENU;
import static Frontend.pages.constructorGo.ConstructorGoXpath.SHIELD;
import static Frontend.providers.Providers.*;

public class SKIP_112 {

    private WebDriver driver;
    private BasePage basePage;
    private Frontend.providers.Providers providers;
    String newValue, newValue2, newValue3, newValue4;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        basePage = new BasePage(driver);
        providers = new Providers(driver);
        basePage.setup();
    }
//    @AfterTest
//    public void tearDown() {
//        basePage.tearDown();
//    }

    @Test
    public void step01() {

        basePage.authorization();
        basePage.openMainPage();
        basePage.waitElementDisplay(MENU);
        basePage.click(MENU);
        basePage.waitElementDisplay(SHIELD);
        basePage.click(SHIELD);
        basePage.waitElementDisplay(ACCESS_RIGHTS);
        basePage.moveToElement(driver, ACCESS_RIGHTS);
        basePage.waitElementDisplay(PROVIDERS_PAGE);
        basePage.click(PROVIDERS_PAGE);
        basePage.waitElementDisplay(LIST_PROVIDERS_FORM);
        basePage.waitElementDisplay(PEN_PICTOGRAM);
        basePage.click(PEN_PICTOGRAM);
        basePage.waitElementDisplay(BLOCK_PUT_PROVIDER);
        basePage.click(PUT_CONTROL_SUBJECT);
        basePage.waitElementDisplay(NAME_CS);
        WebElement name_cs = basePage.findElement(NAME_CS);
        WebElement block_cs = basePage.findElement(BLOCK_CS);
        WebElement entry_field_name = basePage.findElement(ENTRY_FIELD_NAME);
        WebElement entry_field_no_fax = basePage.findElement(ENTRY_FIELD_NUMBER_FAX);
        WebElement button_post_cs = basePage.findElement(BUTTON_POST_CS);
        WebElement list_cs = basePage.findElement(LIST_CS);
        WebElement button_close = basePage.findElement(BUTTON_CLOSE);
        WebElement button_save = basePage.findElement(BUTTON_SAVE);
        WebElement number_fax = basePage.findElement(NUMBER_FAX);
        WebElement number_fax_from_table = basePage.findElement(NUMBER_FAX_FROM_TABLE);


        Assert.assertTrue(block_cs.getText().contains("Субъекты контроля"));
        Assert.assertTrue(name_cs.getText().contains("Наименование субъекта контроля"));
        Assert.assertTrue(entry_field_name.isDisplayed());
        Assert.assertTrue(entry_field_no_fax.isDisplayed());
        Assert.assertTrue(button_post_cs.isDisplayed() && button_post_cs.isEnabled());
        Assert.assertTrue(list_cs.getText().contains("Список субъектов контроля"));
        Assert.assertTrue(button_close.isDisplayed() && button_close.isEnabled() && button_close.getText().contains("Закрыть"));
        Assert.assertTrue(button_save.isDisplayed() && button_save.isEnabled() && button_save.getText().contains("Сохранить"));
        Assert.assertTrue(number_fax.getText().contains("Номер факса"));
        Assert.assertTrue(number_fax_from_table.getText().contains("Номер факса"));
    }

    @Test
    public void step02() {
        basePage.click(BUTTON_SAVE);
        basePage.waitElementDisplay(CHANGES_SAVED);
        WebElement element = basePage.findElement(CHANGES_SAVED);
        Assert.assertTrue(element.getText().contains("Изменения сохранены"));
        WebElement put_provider = basePage.findElement(BLOCK_PUT_PROVIDER);
        WebElement subblock = basePage.findElement(SUBBLOCK_INFO_PROVIDER);
        WebElement checkbox = basePage.findElement(CHECKBOX_TRAINING_PROVIDER);
        String checkedValue = checkbox.getAttribute("class");
        WebElement code = basePage.findElement(CODE_PROVIDER);
        WebElement checkbox_used = basePage.findElement(CHECKBOX_USED);
        WebElement checkbox_archival = basePage.findElement(CHECKBOX_ARCHIVAL);
        WebElement name_provider = basePage.findElement(NAME_PROVIDER_PUT);
        WebElement headquarters = basePage.findElement(HEADQUARTERS);
        WebElement included = basePage.findElement(INCLUDED_IN_THE_STRUCTURE);
        WebElement save = basePage.findElement(SAVE);
        WebElement close = basePage.findElement(CLOSE);
        WebElement provider_includes = basePage.findElement(PROVIDER_INCLUDES);
        WebElement provider_inc_org = basePage.findElement(PROVIDER_INCLUDES_ORGANIZATION);

        Assert.assertTrue(put_provider.getText().contains("Редактирование провайдера"));
        Assert.assertTrue(subblock.getText().equals("Информация о провайдере"));
        Assert.assertTrue(checkbox.isDisplayed() && checkbox.isEnabled());
        Assert.assertTrue(!checkbox.isSelected());
        Assert.assertTrue(checkedValue.contains("el-checkbox el-checkbox--large"));
        Assert.assertTrue(code.getText().equals("Код провайдера"));
        Assert.assertTrue(checkbox_used.getText().contains("Используется"));
        Assert.assertTrue(checkbox_archival.getText().contains("Архивный"));
        Assert.assertTrue(name_provider.getText().contains("Наименование провайдера"));
        Assert.assertTrue(headquarters.getText().contains("Головное подразделение"));
        Assert.assertTrue(included.getText().contains("Входит в структуру (тер. орган)"));
        Assert.assertTrue(save.isDisplayed() && save.isEnabled() && save.getText().contains("Сохранить"));
        Assert.assertTrue(close.isDisplayed() && close.isEnabled() && close.getText().contains("Закрыть"));
        Assert.assertTrue(provider_includes.getText().contains("Провайдер включает в себя следующие субъекты контроля:"));
        Assert.assertTrue(provider_inc_org.getText().contains("Провайдер включает в себя следующие организации:"));
    }

    @Test
    public void step03() {
        basePage.click(PUT_CONTROL_SUBJECT);
        basePage.waitElementDisplay(NAME_CS);
        basePage.click(BUTTON_POST_CS);
        basePage.waitElementDisplay(CANNOT_BE_EMPTY);
        WebElement cannot_be_empty = basePage.findElement(CANNOT_BE_EMPTY);
        Assert.assertTrue(cannot_be_empty.getText().equals("Не может быть пустым"));

    }

    @Test
    public void step04() throws InterruptedException {
        basePage.sendKeys(ENTRY_FIELD_NAME, "ж");
        basePage.sendKeys(ENTRY_FIELD_NUMBER_FAX, "3");
        basePage.waitElementDisplay(BUTTON_POST_CS);
        basePage.click(BUTTON_POST_CS);
        Thread.sleep(1000);
        List<WebElement> el = driver.findElements(By.xpath(PRE_LAST_NAME_CS));
        int count = el.size();
        boolean result = false;
        String locator = "(//h2[text()='Список субъектов контроля']" +
                "//parent::div//parent::div//label[text()='Наименование субъекта контроля'])[%d]//parent::div//input";


        for (int i = 0; i < count; i++) {
            if (driver.findElement(By.xpath(String.format(locator, (i + 1)))).getAttribute("value").equals("ж")) {

                result = true;
                newValue = String.format(locator, i + 1);
                break;
            }
        }
        Assert.assertTrue(result);

        String locator2 = "(//h2[text()='Список субъектов контроля']" +
                "//parent::div//parent::div//label[text()='Номер факса'])[%d]//parent::div//input";
        List<WebElement> el2 = driver.findElements(By.xpath(LAST_CS_NUMBER_FAX));
        int count2 = el2.size();
        boolean result2 = false;

        for (int i2 = 0; i2 < count2; i2++) {
            if (driver.findElement(By.xpath(String.format(locator2, (i2 + 1)))).getAttribute("value").equals("3")) {
                result2 = true;
                break;
            }
        }
        Assert.assertTrue(result2);
    }

    @Test
    public void step05() {
        basePage.click(BUTTON_POST_CS);
        basePage.waitElementDisplay(ALREADY_EXISTS);
        WebElement text = basePage.findElement(ALREADY_EXISTS);
        Assert.assertTrue(text.getText().equals("уже существует"));
        Assert.assertTrue(text.getCssValue("Color").equals("rgb(245, 108, 108)"));



    }

    @Test
    public void step06() throws InterruptedException {
        basePage.click(newValue);
        basePage.click(newValue + "//ancestor::div[contains(@class,'subject-item')]//button");
        basePage.waitElementDisplay("//i[@class='el-icon el-notification__closeBtn']");
        basePage.click("//i[@class='el-icon el-notification__closeBtn']");
        basePage.waitElementDisplay(ENTRY_FIELD_NAME);
        basePage.clear(ENTRY_FIELD_NAME);
        basePage.clear(ENTRY_FIELD_NUMBER_FAX);
        basePage.sendKeys(ENTRY_FIELD_NAME, "Х");
        basePage.sendKeys(ENTRY_FIELD_NUMBER_FAX, "ь");
        basePage.waitElementDisplay(BUTTON_POST_CS);
        basePage.click(BUTTON_POST_CS);
        Thread.sleep(1000);
        List<WebElement> el = driver.findElements(By.xpath(PRE_LAST_NAME_CS));
        int count = el.size();
        boolean result = false;
        String locator = "(//h2[text()='Список субъектов контроля']" +
                "//parent::div//parent::div//label[text()='Наименование субъекта контроля'])[%d]//parent::div//input";


        for (int i = 0; i < count; i++) {
            if (driver.findElement(By.xpath(String.format(locator, (i + 1)))).getAttribute("value").equals("Х")) {

                result = true;
                newValue2 = String.format(locator, i + 1);
                break;
            }
        }
        Assert.assertTrue(result);

        String locator2 = "(//h2[text()='Список субъектов контроля']" +
                "//parent::div//parent::div//label[text()='Номер факса'])[%d]//parent::div//input";
        List<WebElement> el2 = driver.findElements(By.xpath(LAST_CS_NUMBER_FAX));
        int count2 = el2.size();
        boolean result2 = false;

        for (int i2 = 0; i2 < count2; i2++) {
            if (driver.findElement(By.xpath(String.format(locator2, (i2 + 1)))).getAttribute("value").equals("ь")) {
                result2 = true;
                break;
            }
        }
        Assert.assertTrue(result2);
        basePage.click(newValue2);
        basePage.click(newValue2 + "//ancestor::div[contains(@class,'subject-item')]//button");

    }

    @Test
    public void step07() throws InterruptedException {
        basePage.waitElementDisplay(ENTRY_FIELD_NAME);
        Thread.sleep(2000);
        basePage.clear(ENTRY_FIELD_NAME);
        basePage.clear(ENTRY_FIELD_NUMBER_FAX);
        basePage.sendKeysWithClear(ENTRY_FIELD_NAME, "????");
        basePage.sendKeysWithClear(ENTRY_FIELD_NUMBER_FAX, "!!!");
        basePage.waitElementDisplay(BUTTON_POST_CS);
        basePage.click(BUTTON_POST_CS);
        Thread.sleep(1000);
        List<WebElement> el = driver.findElements(By.xpath(PRE_LAST_NAME_CS));
        int count = el.size();
        boolean result = false;
        String locator = "(//h2[text()='Список субъектов контроля']" +
                "//parent::div//parent::div//label[text()='Наименование субъекта контроля'])[%d]//parent::div//input";


        for (int i = 0; i < count; i++) {
            if (driver.findElement(By.xpath(String.format(locator, (i + 1)))).getAttribute("value").equals("????")) {

                result = true;
                newValue3 = String.format(locator, i + 1);
                break;
            }
        }
        Assert.assertTrue(result);

        String locator2 = "(//h2[text()='Список субъектов контроля']" +
                "//parent::div//parent::div//label[text()='Номер факса'])[%d]//parent::div//input";
        List<WebElement> el2 = driver.findElements(By.xpath(LAST_CS_NUMBER_FAX));
        int count2 = el2.size();
        boolean result2 = false;

        for (int i2 = 0; i2 < count2; i2++) {
            if (driver.findElement(By.xpath(String.format(locator2, (i2 + 1)))).getAttribute("value").equals("!!!")) {
                result2 = true;
                break;
            }
        }
        Assert.assertTrue(result2);
        basePage.click(newValue3);
        basePage.click(newValue3 + "//ancestor::div[contains(@class,'subject-item')]//button");

    }
    @Test
    public void step08() throws InterruptedException {
        basePage.waitElementDisplay(ENTRY_FIELD_NAME);
        Thread.sleep(2000);
        basePage.clear(ENTRY_FIELD_NAME);
        basePage.sendKeysWithClear(ENTRY_FIELD_NAME, "124231");
        basePage.clear(ENTRY_FIELD_NUMBER_FAX);
        Thread.sleep(3000);
        basePage.clear(ENTRY_FIELD_NUMBER_FAX);
        basePage.waitElementDisplay(BUTTON_POST_CS);
        basePage.click(BUTTON_POST_CS);
        Thread.sleep(1000);
        List<WebElement> el = driver.findElements(By.xpath(PRE_LAST_NAME_CS));
        int count = el.size();
        boolean result = false;
        String locator = "(//h2[text()='Список субъектов контроля']" +
                "//parent::div//parent::div//label[text()='Наименование субъекта контроля'])[%d]//parent::div//input";


        for (int i = 0; i < count; i++) {
            if (driver.findElement(By.xpath(String.format(locator, (i + 1)))).getAttribute("value").equals("124231")) {

                result = true;
                newValue4 = String.format(locator, i + 1);
                break;
            }
        }
        Assert.assertTrue(result);

        String locator2 = "(//h2[text()='Список субъектов контроля']" +
                "//parent::div//parent::div//label[text()='Номер факса'])[%d]//parent::div//input";
        List<WebElement> el2 = driver.findElements(By.xpath(LAST_CS_NUMBER_FAX));
        int count2 = el2.size();
        boolean result2 = false;

        for (int i2 = 0; i2 < count2; i2++) {
            if (driver.findElement(By.xpath(String.format(locator2, (i2 + 1)))).getAttribute("value").isEmpty()) {
                result2 = true;
                break;
            }
        }
        Assert.assertTrue(result2);
        basePage.click(newValue4);
        basePage.click(newValue4 + "//ancestor::div[contains(@class,'subject-item')]//button");

    }
    }



package SKIP_API;

import Frontend.pages.base.BasePage;
import Frontend.providers.Providers;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static Frontend.pages.constructorGo.ConstructorGoXpath.MENU;
import static Frontend.pages.constructorGo.ConstructorGoXpath.SHIELD;
import static Frontend.providers.Providers.*;

public class SKIP_83 {

    private WebDriver driver;
    private BasePage basePage;
    private Frontend.providers.Providers providers;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        basePage = new BasePage(driver);
        providers = new Providers(driver);
        basePage.setup();
        providers.setup();
    }

    @AfterTest
    public void tearDown() {
        basePage.tearDown();
    }


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
        WebElement block = basePage.findElement(LIST_PROVIDERS_FORM);
        WebElement subblock = basePage.findElement(LIST_PROVIDER_SUBBLOCK);
        WebElement quantity = basePage.findElement(QUANTITY);
        WebElement name_provider = basePage.findElement(NAME_PROVIDER);
        WebElement table_providers = basePage.findElement(TABLE_PROVIDERS);
        WebElement pen = basePage.findElement(PEN_PICTOGRAM);
        WebElement pagination = basePage.findElement(PAGINATION);
        WebElement display_by = basePage.findElement(DISPLAY_BY);
        WebElement pagination2 = basePage.findElement(PAGINATION2);


        Assert.assertTrue(block.getText().contains("Список провайдеров"));
        Assert.assertTrue(subblock.getText().contains("Список провайдеров"));
        Assert.assertTrue(quantity.getText().contains("Найдено:"));
        Assert.assertTrue(name_provider.getText().contains("Наименование провайдера"));
        Assert.assertTrue(table_providers.isDisplayed());
        Assert.assertTrue(pen.isDisplayed() && pen.isEnabled());
        Assert.assertTrue(pagination.getText().contains("1 - 10 из"));
        Assert.assertTrue(display_by.getText().contains("Отображать по"));
        Assert.assertTrue(pagination2.isDisplayed() && pagination2.isEnabled());

    }

    @Test
    public void step02() {

        basePage.click(PAGINATION2);
        basePage.waitElementDisplay(PAGINATION3);
        WebElement pagination3 = basePage.findElement(PAGINATION3);

        Assert.assertTrue(pagination3.getText().contains("10"));
        Assert.assertTrue(pagination3.getText().contains("20"));
        Assert.assertTrue(pagination3.getText().contains("50"));
    }

    @Test
    public void step03() {

        basePage.click(PAGINATION_20);
        WebElement pagination = basePage.findElement(PAGINATION);

        Assert.assertTrue(pagination.getText().contains("1 - 20 из"));
    }

    @Test
    public void step04() {

        basePage.waitElementDisplay(LIST_PROVIDERS_FORM);
        basePage.scrollDown(driver);
        basePage.waitElementDisplay(PAGINATION2);
        basePage.click(PAGINATION2);
        basePage.waitElementDisplay(PAGINATION3);
        basePage.click(PAGINATION_50);
        WebElement pagination = basePage.findElement(PAGINATION);

        Assert.assertTrue(pagination.getText().contains("1 - 50 из"));
    }

    @Test
    public void step05() {

        basePage.scrollUp(driver);
        basePage.waitElementDisplay(PEN_PICTOGRAM);
        basePage.click(PEN_PICTOGRAM);
        basePage.waitElementDisplay(BLOCK_PUT_PROVIDER);
        WebElement put_provider = basePage.findElement(BLOCK_PUT_PROVIDER);

        Assert.assertTrue(put_provider.getText().contains("Редактирование провайдера"));
    }

    @Test
    public void step06() {
        basePage.click(DOOR);
        basePage.openUrl("http://skip.rtech.ru/sign-in/6");
        basePage.openUrl("http://skip.rtech.ru/sign-in/6");
        basePage.waitElementDisplay(MENU);
        basePage.click(MENU);
        basePage.waitElementDisplay(SHIELD);
        basePage.click(SHIELD);
        basePage.waitElementDisplay(ACCESS_RIGHTS);
        basePage.moveToElement(driver, ACCESS_RIGHTS);
        basePage.waitElementDisplay(PROVIDERS_PAGE);
        basePage.click(PROVIDERS_PAGE);
        basePage.waitElementDisplay(LIST_PROVIDERS_FORM);
        WebElement providers_page = basePage.findElement(LIST_PROVIDER_SUBBLOCK);
        List<WebElement> table_provider = basePage.findElements(TABLE_PROVIDERS);


        Assert.assertTrue(providers_page.getText().contains("Список провайдеров"));
        Assert.assertTrue(table_provider.isEmpty());


    }
}


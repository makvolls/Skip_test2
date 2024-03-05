package SKIP_API;

import Frontend.pages.base.BasePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SKIP_60 {

    private WebDriver driver;
    private BasePage basePage;
    String USER = "//div[@class='header__container_navigation-item']/p";
    String BASE = "//span[@class='el-breadcrumb__inner']";
    String NO_USER = "//pre[@style='word-wrap: break-word; white-space: pre-wrap;']";
    String START_PAGE_URL = "http://skip.rtech.ru/sign-in/1";
    String START_PAGE_URL2 = "http://skip.rtech.ru/sign-in/7";


    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        basePage = new BasePage(driver);
        basePage.setup();
    }

    @AfterTest
    public void tearDown() {
        basePage.tearDown();
    }


    @Test
    public void step01() {

        basePage.openUrl(START_PAGE_URL);

        basePage.openUrl(START_PAGE_URL);


        basePage.waitElementDisplay(USER, 10);

        WebElement user = driver.findElement(By.xpath(USER));
        Assert.assertTrue(user.getText().contains("Колокольцев В.А."));
        WebElement base = driver.findElement(By.xpath(BASE));
        Assert.assertTrue(base.getText().contains("Главная"));
    }

    @Test
    public void step02() {
        basePage.openUrl(START_PAGE_URL2);
        basePage.waitElementDisplay(NO_USER, 10);
        WebElement noUser = driver.findElement(By.xpath(NO_USER));
        Assert.assertTrue(noUser.getText().contains("{\"error\":\"Запись для Permissions::User с id =  не найдена\"}"));


    }
}


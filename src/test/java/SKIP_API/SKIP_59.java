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

import static Frontend.pages.userProfile.UserProfile.*;

public class SKIP_59 {
    private WebDriver driver;
    private BasePage basePage;

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

        basePage.authorization();
        basePage.openMainPage();
        basePage.waitElementDisplay(USER_NAME);
        basePage.click(USER_NAME);
        basePage.waitElementDisplay(PROFILE_USER_SYSTEM);
        WebElement block = driver.findElement(By.xpath(PROFILE_USER_SYSTEM));
        WebElement subblock1 = driver.findElement(By.xpath(INFO_USER));
        WebElement fio = driver.findElement(By.xpath(FIO_USER_FIELD));
        WebElement name = driver.findElement(By.xpath(NAME));
        WebElement organization = driver.findElement(By.xpath(ORGANIZATION_FIELD));
        WebElement otdel = driver.findElement(By.xpath(OTDEL_DELOPROIZVODSTVA));
        WebElement field = driver.findElement(By.xpath(JOB_TITLE_FIELD));
        WebElement boss = driver.findElement(By.xpath(DEPUTY_CHIEF));
        WebElement provider = driver.findElement(By.xpath(PROVIDER));
        WebElement provider_name = driver.findElement(By.xpath(PROVIDER_NAME));
        WebElement role_provider = driver.findElement(By.xpath(ROLES_PROVIDER));
        WebElement all_rights = driver.findElement(By.xpath(ALL_RIGHTS));
        WebElement info = driver.findElement(By.xpath(INFO));
        WebElement units = driver.findElement(By.xpath(AVAILABLE_UNITS));
        WebElement click_insc = driver.findElement(By.xpath(CLICK_INSCRIPTION));

        Assert.assertTrue(block.getText().contains("Профиль пользователя системы"));
        Assert.assertTrue(subblock1.getText().contains("Информация о пользователе"));
        Assert.assertTrue(fio.getText().contains("ФИО пользователя"));
        Assert.assertTrue(name.getText().contains("Проверка Авторизации СУДИС"));
        Assert.assertTrue(organization.getText().contains("Организация"));
        Assert.assertTrue(otdel.getText().contains("Отдел делопроизводства и режима Главного управления вневедомственной" +
                " охраны Министерства внутренних дел Российской Федерации"));
        Assert.assertTrue(field.getText().contains("Должность"));
        Assert.assertTrue(boss.getText().contains("Заместитель начальника"));
        Assert.assertTrue(provider.getText().contains("Провайдер"));
        Assert.assertTrue(provider_name.getText().contains("Департамент делопроизводства и работы с " +
                "обращениями граждан и организаций Министерства внутренних дел Российской Федерации (ДДО МВД России)"));
        Assert.assertTrue(role_provider.getText().contains("Роли в провайдере"));
        Assert.assertTrue(all_rights.getText().contains("Все права (ДДО МВД России)"));
        Assert.assertTrue(info.getText().contains("Информация о подразделениях"));
        Assert.assertTrue(units.getText().contains("Доступные подразделения"));
        Assert.assertTrue(click_insc.getText().contains("Отдел делопроизводства и режима ГУВО МВД России (ОДиР ГУВО)"));
        Assert.assertTrue(click_insc.isEnabled() && click_insc.isDisplayed());
    }

}

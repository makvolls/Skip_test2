package SKIP_API;

import Frontend.pages.base.BasePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SKIP_58 {

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

        basePage.openMainPage();
        basePage.waitElementDisplay("//button[@class='form-button form-button_last form-button_minor']");
        String expectedRedirectUrl = "http://idp.int.sudis.at-consulting.ru/idp/authentication";
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedRedirectUrl, currentUrl);
    }

}

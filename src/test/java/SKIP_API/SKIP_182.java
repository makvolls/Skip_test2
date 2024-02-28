package SKIP_API;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Frontend.pages.base.BasePage;
import Frontend.pages.control.PutUnderControlPage;

public class SKIP_182{
    private WebDriver driver;
    private BasePage basePage;
    private PutUnderControlPage putUnderControlPage;
    @BeforeTest
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        basePage = new BasePage(driver);
        putUnderControlPage = new PutUnderControlPage(driver);
        basePage.setup();
    }

    @AfterTest
    public void tearDown(){
        basePage.tearDown();
    }

    @Test
    public void step01(){
        basePage.authorization();
        basePage.openMainPage();
        putUnderControlPage.openPagePutUnderControl();
        String titlePage = putUnderControlPage.getTitleFromPage();
        Assert.assertTrue(titlePage.equals("Данные документа, поступившего на контроль"));
    }

    @Test
    public void step02(){
        putUnderControlPage.pressAddAssignment();
        String titleBlock = putUnderControlPage.getTitleFromBlock();
        Assert.assertTrue(titleBlock.equals("Поручение 1"));
        Assert.assertTrue(putUnderControlPage.checkFieldsHeadExecutor());
    }

}

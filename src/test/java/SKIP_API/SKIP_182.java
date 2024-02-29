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
//        driver = CommonActions.createDriver();
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
        String titleBlock = putUnderControlPage.getTitleAssignmentBlock();
        Assert.assertTrue(titleBlock.equals("Поручение 1"));
        Assert.assertTrue(putUnderControlPage.checkFieldsHeadExecutor());
    }

    @Test
    public void step03(){
        putUnderControlPage.pressAddCoExecutor();
        Assert.assertTrue(putUnderControlPage.isFieldsCoExecutorHeadExecutorRevealed());
    }

    @Test
    public void step04(){
        putUnderControlPage.inputRandomValueCoExecutorTextField();
        putUnderControlPage.clickButtonYesCoExecutor();
        Assert.assertTrue(putUnderControlPage.isAddLetterButtonActive());
    }

    @Test
    public void step05(){
        putUnderControlPage.pressButtonAddLetterCoExecutorHeadExecutor();
        Assert.assertTrue(putUnderControlPage.isFieldsAddLetterCoExecutorHeadExecutorRevealed());
    }

    @Test
    public void step06(){
        putUnderControlPage.pressButtonDeleteCoExOneHeadEx();
        Assert.assertTrue(putUnderControlPage.isModalWindowRevealed());
    }

    @Test
    public void step07(){
        putUnderControlPage.pressYesDeleteWindowCoExecutor();
        Assert.assertTrue(putUnderControlPage.isBlockCoExecutorNotDisplayed());
    }

    @Test
    public void step08(){
        putUnderControlPage.pressAddCoExecutor();
        putUnderControlPage.clickArrowIconCoExecutorHeadExecutor();
        Assert.assertTrue(putUnderControlPage.isBlockCoExecutorOneNotDisplayed());
    }

    @Test
    public void step09(){
        putUnderControlPage.clickArrowIconCoExecutorHeadExecutor();
        Assert.assertTrue(putUnderControlPage.isFieldsCoExecutorHeadExecutorRevealed());
    }

}

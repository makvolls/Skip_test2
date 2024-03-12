package SKIP_API;

import Frontend.pages.base.BasePage;
import Frontend.pages.control.PutUnderControlPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SKIP_184 {
    private WebDriver driver;
    private BasePage basePage;
    private PutUnderControlPage putUnderControlPage;
    private static final Logger logger = Logger.getLogger(SKIP_184.class);

    @BeforeTest
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        // In case to run local driver that has a version 122
        //driver = CommonActions.createDriver();
        basePage = new BasePage(driver);
        putUnderControlPage = new PutUnderControlPage(driver);
        basePage.setup();
        logger.info("Chrome driver is running.");
    }

    @AfterTest
    public void tearDown(){
        basePage.tearDown();
        logger.info("Chrome driver is closing.");
    }

    @Test
    public void step01(){
        try{
            basePage.authorization();
            logger.info("Authorization is succeed.");
            basePage.openMainPage();
            logger.info("Main page is open.");
            putUnderControlPage.openPagePutUnderControl();
            logger.info("Click on button - 'Постановка на контроль'.");
            String titlePage = putUnderControlPage.getTitleFromPage();
            Assert.assertTrue(titlePage.equals("Данные документа, поступившего на контроль"));
            logger.info("Step 1 is successfully completed!");
        } catch (TimeoutException timeoutException) {
            logger.error("TimeoutException - " + timeoutException.getMessage());
            Assert.fail();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            logger.error("NoSuchElementException - " + e.getMessage());
            Assert.fail();
        } catch (Exception e) {
            logger.error("Exception - " + e.getMessage());
            Assert.fail();
        } catch (AssertionError ae) {
            logger.error("AssertionError - " + ae.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step02(){
        try{
            putUnderControlPage.pressAddAssignment();
            logger.info("Press button - 'Добавить поручение'.");
            Assert.assertTrue(putUnderControlPage.isAssignmentOneRevealed());
            logger.info("Step 2 is successfully completed!");
        } catch (TimeoutException timeoutException) {
            logger.error("TimeoutException - " + timeoutException.getMessage());
            Assert.fail();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            logger.error("NoSuchElementException - " + e.getMessage());
            Assert.fail();
        } catch (Exception e) {
            logger.error("Exception - " + e.getMessage());
            Assert.fail();
        } catch (AssertionError ae) {
            logger.error("AssertionError - " + ae.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step03(){
        try{
            putUnderControlPage.pressAddAssignment();
            logger.info("Press button - 'Добавить поручение'.");
            Assert.assertTrue(putUnderControlPage.isAssignmentTwoRevealed());
            logger.info("Step 3 is successfully completed!");
        } catch (TimeoutException timeoutException) {
            logger.error("TimeoutException - " + timeoutException.getMessage());
            Assert.fail();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            logger.error("NoSuchElementException - " + e.getMessage());
            Assert.fail();
        } catch (Exception e) {
            logger.error("Exception - " + e.getMessage());
            Assert.fail();
        } catch (AssertionError ae) {
            logger.error("AssertionError - " + ae.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step04(){
        try{
            putUnderControlPage.inputBigValueAssignmentNumber();
            logger.info("Input value with multiple symbols to the text field " +
                    "- 'Номер поручения (пункт, абзац и т.д.)'.");
            Assert.assertTrue(putUnderControlPage.getValueAssignmentTwo().contains("ааааа"));
            logger.info("Step 4 is successfully completed!");
        } catch (TimeoutException timeoutException) {
            logger.error("TimeoutException - " + timeoutException.getMessage());
            Assert.fail();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            logger.error("NoSuchElementException - " + e.getMessage());
            Assert.fail();
        } catch (Exception e) {
            logger.error("Exception - " + e.getMessage());
            Assert.fail();
        } catch (AssertionError ae) {
            logger.error("AssertionError - " + ae.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step05(){
        try{
            putUnderControlPage.holdCursorAssignmentTwoTitle();
            logger.info("Hold cursor on the element with title - 'ааааа...'.");
            Assert.assertTrue(putUnderControlPage.isTooltipAppeared());
            logger.info("Step 5 is successfully completed!");
        } catch (TimeoutException timeoutException) {
            logger.error("TimeoutException - " + timeoutException.getMessage());
            Assert.fail();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            logger.error("NoSuchElementException - " + e.getMessage());
            Assert.fail();
        } catch (Exception e) {
            logger.error("Exception - " + e.getMessage());
            Assert.fail();
        } catch (AssertionError ae) {
            logger.error("AssertionError - " + ae.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step06(){
        try{
            putUnderControlPage.pressButtonSaveExecution();
            logger.info("Press button - 'Сохранить' in the execution block.");
            Assert.assertTrue(putUnderControlPage.isButtonSaveOnPage());
            logger.info("Step 6 is successfully completed!");
        } catch (TimeoutException timeoutException) {
            logger.error("TimeoutException - " + timeoutException.getMessage());
            Assert.fail();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            logger.error("NoSuchElementException - " + e.getMessage());
            Assert.fail();
        } catch (Exception e) {
            logger.error("Exception - " + e.getMessage());
            Assert.fail();
        } catch (AssertionError ae) {
            logger.error("AssertionError - " + ae.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step07(){
        try{
            putUnderControlPage.chooseFirstAssignment();
            logger.info("Choose first assignment in dropdown menu.");
            putUnderControlPage.pressButtonRedirectAssignment();
            logger.info("Press button - 'Перейти' in the block " +
                    "'Поручения, включенные в документ и подлежащие контролю'.");
            Assert.assertTrue(putUnderControlPage.isFirstAssignmentSelected());
            logger.info("Step 7 is successfully completed!");
        } catch (TimeoutException timeoutException) {
            logger.error("TimeoutException - " + timeoutException.getMessage());
            Assert.fail();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            logger.error("NoSuchElementException - " + e.getMessage());
            Assert.fail();
        } catch (Exception e) {
            logger.error("Exception - " + e.getMessage());
            Assert.fail();
        } catch (AssertionError ae) {
            logger.error("AssertionError - " + ae.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step08(){
        try{
            putUnderControlPage.pressTittleNoActiveAssignment();
            logger.info("Press on title with no active assignment - 'Поручение 1'.");
            Thread.sleep(2000);
            Assert.assertTrue(putUnderControlPage.isNamedAssignmentSelected("аааааааааааааааааааа"));
            logger.info("Step 8 is successfully completed!");
        } catch (TimeoutException timeoutException) {
            logger.error("TimeoutException - " + timeoutException.getMessage());
            Assert.fail();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            logger.error("NoSuchElementException - " + e.getMessage());
            Assert.fail();
        } catch (Exception e) {
            logger.error("Exception - " + e.getMessage());
            Assert.fail();
        } catch (AssertionError ae) {
            logger.error("AssertionError - " + ae.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step09(){
        try{
            putUnderControlPage.chooseFirstAssignment();
            logger.info("Choose first assignment in dropdown menu.");
            putUnderControlPage.pressButtonRedirectAssignment();
            logger.info("Press button - 'Перейти' in the block " +
                    "'Поручения, включенные в документ и подлежащие контролю'.");
            Assert.assertTrue(putUnderControlPage.isFirstAssignmentSelected());
            logger.info("Step 9 is successfully completed!");
        } catch (TimeoutException timeoutException) {
            logger.error("TimeoutException - " + timeoutException.getMessage());
            Assert.fail();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            logger.error("NoSuchElementException - " + e.getMessage());
            Assert.fail();
        } catch (Exception e) {
            logger.error("Exception - " + e.getMessage());
            Assert.fail();
        } catch (AssertionError ae) {
            logger.error("AssertionError - " + ae.getMessage());
            Assert.fail();
        }
    }
}

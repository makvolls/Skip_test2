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

public class SKIP_185 {
    private WebDriver driver;
    private BasePage basePage;
    private PutUnderControlPage putUnderControlPage;
    private static final Logger logger = Logger.getLogger(SKIP_185.class);

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
            putUnderControlPage.pressAddAssignment();
            logger.info("Press button - 'Добавить поручение'.");
            putUnderControlPage.pressAddCoExecutorHeadEx();
            logger.info("Press button - 'Добавить соисполнителя'.");
            putUnderControlPage.clickButtonYesCoExecutor();
            logger.info("Press radio button 'Да' in the element 'Факт предоставления'.");
            putUnderControlPage.pressButtonAddLetterCoExecutorHeadExecutor();
            logger.info("Press button 'Добавить письмо от соисполнителя' in the element " +
                    "'Соисполнитель головного исполнителя'.");
            putUnderControlPage.pressButtonDeleteLetterOneHeadEx();
            logger.info("Press button 'Удалить' in the block 'Письмо 1'.");
            Assert.assertTrue(putUnderControlPage.isBlockLetterOneNotDisplayed());
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
            putUnderControlPage.pressButtonAddLetterCoExecutorHeadExecutor();
            logger.info("Press button 'Добавить письмо от соисполнителя' in the element " +
                    "'Соисполнитель головного исполнителя'.");
            putUnderControlPage.inputValueLetterDateTextField("тест");
            logger.info("Input in the text field 'Дата...' value - 'тест'.");
            putUnderControlPage.pressButtonDeleteLetterOneHeadEx();
            logger.info("Press button 'Удалить' in the block 'Письмо 1'.");
            Assert.assertTrue(putUnderControlPage.isBlockLetterOneNotDisplayed());
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
            putUnderControlPage.pressButtonAddLetterCoExecutorHeadExecutor();
            logger.info("Press button 'Добавить письмо от соисполнителя' in the element " +
                    "'Соисполнитель головного исполнителя'.");
            putUnderControlPage.pressButtonAddLetterCoExecutorHeadExecutor();
            logger.info("Press button 'Добавить письмо от соисполнителя' in the element " +
                    "'Соисполнитель головного исполнителя'.");
            putUnderControlPage.inputValueLetterDateTextFieldSecond("тест");
            logger.info("Input in the text field 'Дата...' value - 'тест' in the block 'Письмо 2'.");
            putUnderControlPage.pressButtonDeleteLetterOneHeadEx();
            logger.info("Press button 'Удалить' in the block 'Письмо 1'.");
            Assert.assertTrue(putUnderControlPage.isFieldsAddLetterCoExecutorHeadExecutorRevealed());
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
}

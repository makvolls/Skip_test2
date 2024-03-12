package SKIP_API;

import Frontend.common.CommonActions;
import Frontend.pages.base.BasePage;
import Frontend.pages.control.PutUnderControlPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;

public class SKIP_183 {
    private WebDriver driver;
    private BasePage basePage;
    private PutUnderControlPage putUnderControlPage;
    private static final Logger logger = Logger.getLogger(SKIP_183.class);

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
            putUnderControlPage.pressButtonAddExecutor();
            logger.info("Press button - 'Добавить исполнителя'.");
            putUnderControlPage.pressButtonAddCoExecutorExecutor();
            logger.info("Press button - 'Добавить соисполнителя'.");
            putUnderControlPage.pressButtonDeleteCoExecutorExecutor();
            logger.info("Press button - 'Удалить' в блоке 'Соисполнитель 1'.");
            Assert.assertTrue(putUnderControlPage.isModalWindowRevealed());
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
            putUnderControlPage.pressNoDeleteWindowCoExecutor();
            logger.info("Press 'Нет' in the modal window.");
            Assert.assertTrue(putUnderControlPage.isCoExecutorOneOnPage());
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
            putUnderControlPage.pressButtonDeleteCoExecutorExecutor();
            logger.info("Press button - 'Удалить' в блоке 'Соисполнитель 1'.");
            putUnderControlPage.pressYesDeleteWindowCoExecutorExecutor();
            logger.info("Press 'Да' in the modal window.");
            Assert.assertTrue(putUnderControlPage.isBlockCoExecutorOneNotDisplayed());
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
            putUnderControlPage.pressButtonAddCoExecutorExecutor();
            logger.info("Press button - 'Добавить соисполнителя'.");
            putUnderControlPage.clickButtonYesCoExecutor();
            logger.info("Press 'Да' on the element 'Факт предоставления'.");
            putUnderControlPage.pressButtonAddLetterCoExecutorHeadExecutor();
            logger.info("Press button 'Добавить письмо от соисполнителя'.");
            putUnderControlPage.pressButtonDeleteLetterOneHeadEx();
            logger.info("Press button 'Удалить'.");
            Assert.assertTrue(putUnderControlPage.isBlockLetterOneNotDisplayed());
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
            putUnderControlPage.pressButtonAddLetterCoExecutorHeadExecutor();
            logger.info("Press button 'Добавить письмо от соисполнителя'.");
            putUnderControlPage.inputRandomValueLetterDateTextField();
            logger.info("Input random value to the text field 'Дата'.");
            putUnderControlPage.pressButtonDeleteLetterOneHeadEx();
            logger.info("Press button 'Удалить' in the block 'Письмо 1'.");
            Assert.assertTrue(putUnderControlPage.isBlockLetterOneNotDisplayed());
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
            putUnderControlPage.pressButtonAddLetterCoExecutorHeadExecutor();
            logger.info("Press button 'Добавить письмо от соисполнителя'.");
            putUnderControlPage.pressButtonAddCoExecutorExecutor();
            logger.info("Press button 'Добавить соисполнителя'");
            putUnderControlPage.inputRandomValueCoExOrg();
            logger.info("Input to the text field 'Подразделение соисполнителя' random value.");
            putUnderControlPage.pressButtonDeleteCoExecutorExecutor();
            logger.info("Press button 'Удалить'.");
            putUnderControlPage.pressYesDeleteWindowCoExecutorExecutor();
            logger.info("Press button 'Yes'.");
            Assert.assertTrue(putUnderControlPage.isBlockLetterOneNotDisplayed());
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
            putUnderControlPage.clickButtonYesCoExecutor();
            logger.info("Press 'Да' on the element 'Факт предоставления'.");
            putUnderControlPage.pressButtonAddLetterCoExecutorHeadExecutor();
            logger.info("Press button 'Добавить письмо от соисполнителя'.");
            putUnderControlPage.pressButtonAddLetterCoExecutorHeadExecutor();
            logger.info("Press button 'Добавить письмо от соисполнителя'.");
            putUnderControlPage.inputRandomValueLetterDateTextField();
            logger.info("Input to the text field 'Дата, № док' random value.");
            putUnderControlPage.pressButtonDeleteLetterOneHeadEx();
            logger.info("Press button 'Удалить'.");
            String titleLetterOne = putUnderControlPage.getTitleLetterOne();
            Assert.assertTrue(titleLetterOne.equals("Письмо 1"));
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
}

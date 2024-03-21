package SKIP_UI;

import SKIP_UI.base.BaseTest;

import UI.constants.Users;
import UI.pages.base.BasePage;
import UI.pages.main.MainPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static UI.constants.Urls.*;

public class SKIP_60 extends BaseTest {
public static MainPage mainPage;

    @AfterClass
    public void afterClass() {
        driver.quit(); // закрываем браузер
    }


    @BeforeClass
    public void beforeClass() {
        basePage = new BasePage(driver);
        mainPage = new MainPage(driver);

        //authorizationPage.authorization(HOMEPAGE_SKIP, Users.TEST);
    }


    @Test(enabled = true, description = "[SKIP-60 Step 1 АС] Шаги:"
            + "1. В браузере перейти по ссылке 1 из описания"
            + "Ожидаемая реакция:"
            + "Отобразилась страница Base page с уже пройденной авторизацией под Колокольцевым В.А.")
    public void SKIP_60_step_01() {
        try {
            basePage.openURl(USER_TEST_AUT);

            Assert.assertTrue(mainPage.isDisplayFioUser("Колокольцев В.А."));
            Assert.assertTrue(mainPage.isDisplayHomePage("Главная"));


        } catch (Exception e) {
            logFailure(e);
        }
    }

    @Test(enabled = true, description = "[SKIP-60 Step 2 АС] Шаги:"
            + "1. В новой вкладке перейти по ссылке 2 из описания"
            + "Ожидаемая реакция:"
            + "{'error':'Запись для Permissions::User с id =  не найдена'}")
    public void SKIP_60_step_02() {
        try {
            basePage.openURl(USER_FAILED_AUT);

            Assert.assertTrue(mainPage.isDisplayErrorAuthUser("error \"Запись для Permissions::User с id =  не найдена\""));



        } catch (Exception e) {
            logFailure(e);
        }
    }

}
package SKIP_UI;

import SKIP_UI.base.BaseTest;
import UI.constants.Users;
import UI.pages.base.BasePage;
import org.testng.Assert;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static UI.constants.Urls.*;

public class SKIP_58 extends BaseTest {

    @AfterClass
    public void afterClass() {
        driver.quit(); // закрываем браузер
    }

    @BeforeClass
    public void beforeClass() {
        basePage = new BasePage(driver);
   //     authorizationPage.authorization(HOMEPAGE_SKIP, Users.TEST);

    }

    @Test(enabled = true, description = "[SKIP-58 Step 1 АС] Шаги:"
            + "1. В браузере перейти по ссылке из описания"
            + "Ожидаемая реакция:"
            + "Произошел редирект на страницу авторизации СУДИС"
            + "http://idp.int.sudis.at-consulting.ru/idp/authentication")
    public void SKIP_58_step_01() {
        try {
            basePage.openURl(HOMEPAGE_SKIP);
            authorizationPage.clickButtonLoginWithPassword();


            Assert.assertTrue(authorizationPage.isDisplayAuthForm("Авторизация"));


        } catch (Exception e) {
            logFailure(e);
        }
    }

}
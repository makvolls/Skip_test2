package SKIP_UI;

import SKIP_UI.base.BaseTest;
import UI.constants.Users;
import UI.pages.base.BasePage;
import UI.pages.main.MainPage;
import UI.pages.userprofile.UserProfileSystemPage;
import org.testng.Assert;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static UI.constants.Urls.HOMEPAGE_SKIP;

public class SKIP_59 extends BaseTest {
    public static MainPage mainPage;
    public static UserProfileSystemPage userProfileSystem;

    @AfterClass
    public void afterClass() {
        driver.quit(); // закрываем браузер
    }


    @BeforeClass
    public void beforeClass() {
        basePage = new BasePage(driver);
        authorizationPage.authorization(HOMEPAGE_SKIP, Users.TEST);
        mainPage = new MainPage(driver);
        userProfileSystem = new UserProfileSystemPage(driver);


    }

    @Test(enabled = true, description = "[SKIP-59 Step 1 АС] Шаги:"
            + "1. Перейти по ссылке из условия"
            + "2. Авторизоваться под ДЛ"
            + "3. Нажать на 'Проверка А.С'(профиль пользователя)"
            + "Ожидаемая реакция:"
            + "Отобразился Профиль пользователя системы, где отображаются поля:"
            + "  Информация о пользователе"
            + "  ФИО пользователя"
            + "Проверка Авторизации СУДИС"
            + "  Организация"
            + "Отдел делопроизводства и режима"
            + " Главного управления вневедомственной охраны Министерства внутренних дел Российской Федерации"
            + "  Должность"
            + "Заместитель начальника"
            + "  Провайдер"
            + "Отдел делопроизводства и режима ГУВО МВД России (ОДиР ГУВО)"
            + "  Роли в провайдере"
            + "Все права"
            + "  Информация о подразделениях"
            + "  Доступные подразделения"
            + "Департамент делопроизводства и работы с обращениями"
            + " граждан и организаций Министерства внутренних дел Российской Федерации (ДДО МВД России)"
            + "  (Подразделение подсвечено синимцветом и кликабельно)"
            + "  При нажатии отдает 404, так как пока нет задачи на переход в другой провайдер")
    public void SKIP_59_step_01() {
        try {
            mainPage.clickUserFio("Проверка А.С.");

            Assert.assertTrue(userProfileSystem.isDisplayBlock("Профиль пользователя системы"));
            Assert.assertTrue(userProfileSystem.isDisplayBlock("Информация о пользователе"));
            Assert.assertTrue(userProfileSystem.isDisplayLabel("ФИО пользователя"));
            Assert.assertTrue(userProfileSystem.isDisplayTextInLabel("ФИО пользователя","Проверка Авторизации СУДИС"));
            Assert.assertTrue(userProfileSystem.isDisplayLabel("Организация"));
            Assert.assertTrue(userProfileSystem.isDisplayTextInLabel("Организация","Отдел делопроизводства и режима Главного управления вневедомственной охраны Министерства внутренних дел Российской Федерации"));
            Assert.assertTrue(userProfileSystem.isDisplayLabel("Должность"));
            Assert.assertTrue(userProfileSystem.isDisplayTextInLabel("Должность","Заместитель начальника"));
            Assert.assertTrue(userProfileSystem.isDisplayLabel("Провайдер"));
            Assert.assertTrue(userProfileSystem.isDisplayTextInLabel("Провайдер","Департамент делопроизводства и работы с обращениями граждан и организаций Министерства внутренних дел Российской Федерации (ДДО МВД России)"));
            Assert.assertTrue(userProfileSystem.isDisplayLabel("Роли в провайдере"));
            Assert.assertTrue(userProfileSystem.isDisplayTextInLabel("Роли в провайдере","Все права (ДДО МВД России)"));
            Assert.assertTrue(userProfileSystem.isDisplayBlock("Информация о подразделениях"));
            Assert.assertTrue(userProfileSystem.isDisplayLabel("Доступные подразделения"));
            Assert.assertTrue(userProfileSystem.isDisplayTextLinkInLabel("Доступные подразделения","Отдел делопроизводства и режима ГУВО МВД России (ОДиР ГУВО)"));


        } catch (Exception e) {
            logFailure(e);
        }
    }

}
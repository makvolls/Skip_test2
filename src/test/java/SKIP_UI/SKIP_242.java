
package SKIP_UI;

import SKIP_UI.base.BaseTest;
import UI.constants.Users;
import UI.pages.base.BasePage;
import UI.pages.stagingOnControl.StagingOnControllPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static UI.constants.Urls.HOMEPAGE_SKIP;


public class SKIP_242 extends BaseTest {
    protected static StagingOnControllPage stagingPage;

    @AfterClass
    public void afterClass() {
        driver.quit(); // закрываем браузер
    }


    @BeforeClass
    public void beforeClass() {
        stagingPage = new StagingOnControllPage(driver);
        basePage = new BasePage(driver);
        authorizationPage.authorization(HOMEPAGE_SKIP, Users.TEST);
    }


    @Test(enabled = true, description = "[GISMU-242 Step 1 АС] Шаги:"
            + "Нажать кнопку 'Постановка на контроль'."
            + "Ожидаемая реакция:"
            + "Отображается форма 'Данные документа, поступившие на контроль'.")
    public void GISMU_242_step_01() {
        try {
            stagingPage.clickButton("Постановка на контроль");

            Assert.assertTrue(stagingPage.isDisplayBlock("Данные документа, поступившего на контроль"));


        } catch (Exception e) {
            logFailure(e);
        }
    }

    @Test(enabled = true, description = "[GISMU-242 Step 2 АС] Шаги:"
            + "Раскрыть блок 'Данные документа СЭД', нажав на иконку ' '."
            + "В выпадающем списке поля 'Откуда поступил документ' выбрать любое значение из справочника."
            + "Очистить поле"
            + "В выпадающем списке поля 'Откуда поступил документ' внести значение 'Тест'"
            + "Ожидаемая реакция:"
            + "В выпадающем списке поля отображается внесенное знаение 'Тест'."
            + "Для данного поля есть возможность так же указать значение из справочника.")
    public void GISMU_242_step_02() {
        try {
            stagingPage.openBlockInForm("Данные документа СЭД");
            stagingPage.setSelectWithSendKeys("Откуда поступил документ","100");
            stagingPage.clearDropdownField("Откуда поступил документ");
            stagingPage.setLabelText("Откуда поступил документ","Тест");
            stagingPage.clickNameService();

            Assert.assertTrue(stagingPage.isDisplayTextInDropdownLabel("Откуда поступил документ","Тест"));


        } catch (Exception e) {
            logFailure(e);
        }
    }

    @Test(enabled = true, description = "[GISMU-242 Step 3 АС] Шаги:"
            + "Повторить дейтвия шага 2 для поля 'Подпись'"
            + "Ожидаемая реакция:"
            + "В выпадающем списке поля отображается внесенное знаение 'Тест'."
            + "Для данного поля есть возможность так же указать значение из справочника.")
    public void GISMU_242_step_03() {
        try {
            stagingPage.setSelectWithSendKeys("Подписал","1sed");
            stagingPage.clearDropdownField("Подписал");
            stagingPage.setLabelText("Подписал","Тест");
            stagingPage.clickNameService();

            Assert.assertTrue(stagingPage.isDisplayTextInDropdownLabel("Подписал","Тест"));


        } catch (Exception e) {
            logFailure(e);
        }
    }

    @Test(enabled = true, description = "[GISMU-242 Step 4 АС] Шаги:"
            + "Повторить дейтвия шага 2 для поля 'Получатель'"
            + "Ожидаемая реакция:"
            + "В выпадающем списке поля отображается внесенное знаение 'Тест'."
            + "Для данного поля есть возможность так же указать значение из справочника.")
    public void GISMU_242_step_04() {
        try {
            stagingPage.setSelectWithSendKeys("Получатель","1sed");
            stagingPage.clearDropdownField("Получатель");
            stagingPage.setLabelText("Получатель","Тест");
            stagingPage.clickNameService();

            Assert.assertTrue(stagingPage.isDisplayTextInDropdownLabel("Получатель","Тест"));


        } catch (Exception e) {
            logFailure(e);
        }
    }

    @Test(enabled = true, description = "[GISMU-242 Step 5 АС] Шаги:"
            + "В блоке 'Поручения, включенные в документ и подлежащие контролю' нажать кнопку 'Добавить поручение'."
            + "В блоке 'Головной исполнитель поручения' нажать кнопку 'Добавит соискателя'."
            + "В выпадающем списке поля 'Соисполнитель' выбрать любое значение из справочника."
            + "Очистить поле"
            + "В выпадающем списке поля 'Соисполнитель' внести значение 'Тест'"
            + "Ожидаемая реакция:"
            + "выпадающем списке поля отображается внесенное знаение 'Тест'."
            + "Для данного поля есть возможность так же указать значение из справочника.")
    public void GISMU_242_step_05() {
        try {
            stagingPage.clickButton("Добавить поручение");
            stagingPage.clickButton("Добавить соисполнителя");
            stagingPage.setSelectWithSendKeys("Соисполнитель","123");
            stagingPage.clearDropdownField("Соисполнитель");
            stagingPage.setLabelText("Соисполнитель","Тест");
            stagingPage.clickNameService();

            Assert.assertTrue(stagingPage.isDisplayTextInDropdownLabel("Соисполнитель","Тест"));



        } catch (Exception e) {
            logFailure(e);
        }
    }

}
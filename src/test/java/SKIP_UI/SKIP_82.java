package SKIP_UI;

import SKIP_UI.base.BaseTest;
import UI.constants.Users;
import UI.pages.base.BasePage;
import UI.pages.listproviders.ListProvidersPage;
import UI.pages.main.MainPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static UI.constants.Urls.HOMEPAGE_SKIP;

public class SKIP_82 extends BaseTest {
    public static MainPage mainPage;
    public static ListProvidersPage listProvidersPage;
    public static String provider = "Департамент делопроизводства и работы с обращениями граждан и организаций Министерства внутренних дел Российской Федерации (ДДО МВД России)";

    @AfterClass
    public void afterClass() {
        driver.quit(); // закрываем браузер
    }


    @BeforeClass
    public void beforeClass() {
        basePage = new BasePage(driver);
        authorizationPage.authorization(HOMEPAGE_SKIP, Users.TEST);
        mainPage = new MainPage(driver);
        listProvidersPage = new ListProvidersPage(driver);

    }


    @Test(enabled = true, description = "[SKIP-82 Step 1 АС] Шаги:"
            + "1.В браузере перейти по ссылке 1 из описания"
            + "2.В браузере перейти по ссылке 2 из описания"
            + "Ожидаемая реакция:"
            + "Отобразилась форма 'Список провайдеров' и таблица 'Список провайдеров'. На форме 'Список провайдеров' отобразились:"
            + " строка поиска 'Наименование провайдера', кнопка 'Искать', кнопка 'Очистить'.")
    public void SKIP_82_step_01() {
        try {
            listProvidersPage.waitLoading();
            driver.get("http://skip.rtech.ru/administration/permissions/providers");

            Assert.assertTrue(listProvidersPage.isDisplayBlock("Список провайдеров"));
            Assert.assertTrue(listProvidersPage.isDisplayNameTableProvider("Список провайдеров"));
            Assert.assertTrue(listProvidersPage.isDisplayLabelInListProvider("Наименование провайдера"));
            Assert.assertTrue(listProvidersPage.isDisplayButton("Искать"));
            Assert.assertTrue(listProvidersPage.isDisplayButton("Очистить"));


        } catch (Exception e) {
            logFailure(e);
        }
    }

    @Test(enabled = true, description = "[SKIP-82 Step 2 АС] Шаги:"
            + "1. Нажать на строку поиска 'Наименование провайдера'"
            + "Ожидаемая реакция:"
            + "Отобразился выпадающий список с наименованиями провайдеров")
    public void SKIP_82_step_02() {
        try {
            listProvidersPage.clickLabelListProvider();


            Assert.assertTrue(listProvidersPage.isDisplayTextInDropDownList("Департамент делопроизводства и работы с обращениями граждан и организаций Министерства внутренних дел Российской Федерации (ДДО МВД России)"));
            Assert.assertTrue(listProvidersPage.isDisplayTextInDropDownList("Отдел делопроизводства и режима Департамента информационных технологий, связи и защиты информации Министерства внутренних дел Российской Федерации (ОДиР ДИТСиЗИ МВД России)"));
            Assert.assertTrue(listProvidersPage.isDisplayTextInDropDownList("Отдел делопроизводства и режима ГУВО МВД России (ОДиР ГУВО)"));
            Assert.assertTrue(listProvidersPage.isDisplayTextInDropDownList("Отдел делопроизводства и режима ФЭД МВД (Отдел делопроизводства и режима ФЭД МВД)"));
            Assert.assertTrue(listProvidersPage.isDisplayTextInDropDownList("Управление делопроизводства и режима Главного управления Министерства внутренних дел Российской Федерации по городу Москве (УДиР ГУ МВД России по г. Москве)"));
            Assert.assertTrue(listProvidersPage.isDisplayTextInDropDownList("Отдел документационнного обеспечения и режима секретности ФКУ \"ГИАЦ МВД РОССИИ\" (ОДОиРС ФКУ ГИАЦ МВД РОССИИ)"));
            Assert.assertTrue(listProvidersPage.isDisplayTextInDropDownList("Отдел делопроизводства и режима ОУ МВД России (ОДиР ОУ МВД России)"));
          //  Assert.assertTrue(listProvidersPage.isDisplayTextInDropDownList("10 Отдел делопроизводства и режима ФКУ \"ГЦСиЗИ МВД России\" (ОДиР ФКУ \"ГЦСиЗИ МВД России\")"));
          //  Assert.assertTrue(listProvidersPage.isDisplayTextInDropDownList("Отдел делопроизводства и режима Федеральное государственное казенное учреждение «Экспертно-криминалистический центр Министерства внутренних дел Российской Федерации» (ОДиР ФГКУ ЭКЦ МВД России)"));

        } catch (Exception e) {
            logFailure(e);
        }

    }
    @Test(enabled = true, description = "[SKIP-82 Step 3 АС] Шаги:"
            + "1.Выбрать любой провайдер из выпадающего списка"
            + "2.Нажать 'Искать'"
            + "Ожидаемая реакция:"
            + "В таблице 'Список провайдеров' отобразился данный провайдер")
    public void SKIP_82_step_03() {
        try {
            listProvidersPage.setSelectWithSendKeys("Наименование провайдера","Отдел");
            listProvidersPage.clickButton("Искать");

            Assert.assertTrue(listProvidersPage.isDisplayTNameProviderInTable("Список провайдеров","Департамент делопроизводства и работы с обращениями граждан и организаций Министерства внутренних дел Российской Федерации (ДДО МВД России)"));



        } catch (Exception e) {
            logFailure(e);
        }
    }

    @Test(enabled = true, description = "[SKIP-82 Step 4 АС] Шаги:"
            + "1.Нажать 'Очистить'"
            + "Ожидаемая реакция:"
            + "Строка поиска 'Наименование провайдера' очистилась, В таблице 'Список провайдеров' отображаются все провайдеры")
    public void SKIP_82_step_04() {
        try {



        } catch (Exception e) {
            logFailure(e);
        }
    }

    @Test(enabled = true, description = "[SKIP-82 Step 5 АС] Шаги:"
            + "1.В строку поиска ввести 'отдел'"
            + "Ожидаемая реакция:"
            + "В выпадающем списке отображаются провайдеры в названии которых присутствует слово 'отдел'(регистр не учитывается)")
    public void SKIP_82_step_05() {
        try {



        } catch (Exception e) {
            logFailure(e);
        }
    }

    @Test(enabled = true, description = "[SKIP-82 Step 6 АС] Шаги:"
            + "1.В строку поиска ввести 'ОТДЕЛ'"
            + "Ожидаемая реакция:"
            + "В выпадающем списке отображаются провайдеры в названии которых присутствует слово 'ОТДЕЛ'(регистр не учитывается)")
    public void SKIP_82_step_06() {
        try {



        } catch (Exception e) {
            logFailure(e);
        }
    }

    @Test(enabled = true, description = "[SKIP-82 Step 7 АС] Шаги:"
            + "1.В строку поиска ввести 'а(кириллица)'"
            + "Ожидаемая реакция:"
            + "В выпадающем списке отображаются провайдеры в названии которых присутствует буква 'а'(регистр не учитывается)")
    public void SKIP_82_step_07() {
        try {



        } catch (Exception e) {
            logFailure(e);
        }
    }

    @Test(enabled = true, description = "[SKIP-82 Step 8 АС] Шаги:"
            + "1.В строку поиска ввести 'параправв'"
            + "Ожидаемая реакция:"
            + "В выпадающем списке отображается надпись 'Данных, удовлетворяющих условиям фильтра, не найдено'")
    public void SKIP_82_step_08() {
        try {



        } catch (Exception e) {
            logFailure(e);
        }
    }

    @Test(enabled = true, description = "[SKIP-82 Step 9 АС] Шаги:"
            + "1.Нажать 'Очистить'"
            + "2.Нажать на строку поиска 'Наименование провайдера' и из выпадающего списка выбрать 3-й провайдер"
            + "3.Ввести в строку поиска слово 'отдел' "
            + "4.Нажать левой кнопкой мыши на пустую часть формы(правая)"
            + "Ожидаемая реакция:"
            + "Введенные данные исчезли из строки поиска, в строке поиска остался выбранный провайдер(После реализации задачи SKIP2023-419: Введенные символы не должны пропадать, то есть должна быть возможность ввести произвольный текст и искать по нему)")
    public void SKIP_82_step_09() {
        try {



        } catch (Exception e) {
            logFailure(e);
        }
    }

}
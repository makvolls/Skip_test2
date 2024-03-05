package Frontend.pages.userProfile;

import Frontend.pages.base.BasePage;
import org.openqa.selenium.WebDriver;

public class UserProfile extends BasePage {

    public UserProfile(WebDriver driver) {
        super(driver);
    }

    // Xpath on this page:
    /**
     * Xpath for button with text - "Профиль пользователя"
     * **/
    public static final String USER_NAME = "//p[text()='Проверка А.С.']";

    /**
     * Xpath for text - "Профиль пользователя системы(название блока)"
     * **/

    public static final String PROFILE_USER_SYSTEM = "//h2[normalize-space() = 'Профиль пользователя системы']";
    /**
     * Xpath for text - "Информация о пользователе(название подблока)"
     * **/
    public static final String INFO_USER="//h2[normalize-space() = 'Информация о пользователе']";
    /**
     * Xpath for text - "ФИО пользователя"
     * **/
    public static final String FIO_USER_FIELD="//h4[normalize-space() = 'ФИО пользователя']";
    /**
     * Xpath for text - "Надпись Проверка Авторизации СУДИС"
     * **/
    public static final String NAME="//p[normalize-space() = 'Проверка Авторизации СУДИС']";
    /**
     * Xpath for text - "Надпись Организация"
     * **/
    public static final String ORGANIZATION_FIELD="//h4[normalize-space() = 'Организация']";
    /**
     * Xpath for text - "Отдел делопроизводства и режима Главного управления вневедомственной охраны Министерства
     * внутренних дел Российской Федерации"
     * **/
    public static final String OTDEL_DELOPROIZVODSTVA=
            "//p[normalize-space() = 'Отдел делопроизводства и режима Главного управления вневедомственной охраны " +
                    "Министерства внутренних дел Российской Федерации']";
    /**
     * Xpath for text - "Надпись Должность"
     * **/
    public static final String JOB_TITLE_FIELD="//h4[normalize-space() = 'Должность']";
    /**
     * Xpath for text - "Надпись Заместитель начальника"
     * **/
    public static final String DEPUTY_CHIEF="//p[normalize-space() = 'Заместитель начальника']";
    /**
     * Xpath for text - "Надпись Провайдер"
     * **/
    public static final String PROVIDER="//h4[normalize-space() = 'Провайдер']";
    /**
     * Xpath for text - "Надпись Департамент делопроизводства и работы с обращениями граждан и организаций
     * Министерства внутренних дел Российской Федерации (ДДО МВД России)"
     * **/
    public static final String PROVIDER_NAME="//p[normalize-space() = 'Департамент делопроизводства и работы" +
            " с обращениями граждан и организаций Министерства внутренних дел Российской Федерации (ДДО МВД России)']";
    /**
     * Xpath for text - "Надпись Роли в провайдере"
     * **/
    public static final String ROLES_PROVIDER="//h4[normalize-space() = 'Роли в провайдере']";
    /**
     * Xpath for text - "Надпись Все права"
     * **/
    public static final String ALL_RIGHTS="//p[normalize-space() = 'Все права (ДДО МВД России)']";
    /**
     * Xpath for text - "Надпись Информация о подразделениях"
     * **/
    public static final String INFO="//h2[normalize-space() = 'Информация о подразделениях']";
    /**
     * Xpath for text - "Надпись Доступные подразделения"
     * **/
    public static final String AVAILABLE_UNITS="//h4[normalize-space() = 'Доступные подразделения']";
    /**
     * Xpath for text and click - "Надпись Отдел делопроизводства и режима ГУВО МВД России (ОДиР ГУВО)"
     * **/
    public static final String CLICK_INSCRIPTION="//span[@class='el-link__inner']";

}

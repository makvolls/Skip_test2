package Frontend.pages.constructorGo;

import Frontend.pages.base.BasePage;
import org.openqa.selenium.WebDriver;

public class ConstructorGoXpath extends BasePage {
    public ConstructorGoXpath(WebDriver driver) {
        super(driver);
    }


    /**
     * Xpath for pictogram - "Администрирование"
     * **/
    public static final String SHIELD = "//li[@class='el-side-navigation-sub-menu'][1]";
    /**
     * Xpath for button with text - "Конструктор(поле из выпадающего списка)"
     * **/
    public static final String CONSTRUCTOR = "//li[@class='el-side-navigation-menu-item']/span[text()='Конструктор']";
    /**
     * Xpath for pictogram - "Пиктограмма синий квадрат с 4 квадратами внутри"
     * **/
    public static final String MENU = "//li[@class='el-side-navigation-menu-item navigation__collapse']";
    /**
     * Xpath for text - "Табличные формы главного окна"
     * **/
    public static final String BLOCK = "//div[normalize-space() = 'Табличные формы главного окна']";
    /**
     * Xpath for text - "Список форм"
     * **/
    public static final String SUBBLOCK = "//h2[@class='title']";
    /**
     * Xpath for text - "Список форм"
     * **/
    public static final String LIST = "//p[@class='info']";
    /**
     * Xpath for button with text - "Кнопка создать"
     * **/
    public static final String CREATE = "//*[normalize-space() ='Создать']";
    /**
     * Xpath for text - "Дата"
     * **/
    public static final String DATE = "//td[@class='el-table_2_column_5 el-table__cell'][1]";
    /**
     * Xpath for text - "Наименование"
     * **/
    public static final String NAME = "//td[@class='el-table_2_column_6 is-center el-table__cell'][1]";
    /**
     * Xpath for button - "Пиктограмма глаз"
     * **/
    public static final String EYE = "//button[@class='el-button is-text el-tooltip__trigger'][1]";
    /**
     * Xpath for button - "Пиктограмма карандаш"
     * **/
    public static final String PEN = "//button[@class='el-button is-text el-tooltip__trigger'][2]";
    /**
     * Xpath for button - "Пиктограмма урны"
     * **/
    public static final String URN = "//button[@class='el-button is-text el-tooltip__trigger'][3]";
    /**
     * Xpath for text - "Тултип глаза"
     * **/
    public static final String TOOLTIP_EYE = "//span[normalize-space() = 'Не отображать на главном окне']";
    /**
     * Xpath for text - "Тултип карандаша"
     * **/
    public static final String TOOLTIP_PEN = "//div[@class='el-popper is-dark']/span[normalize-space() = 'Редактировать']";
    /**
     * Xpath for text - "Тултип урны"
     * **/
    public static final String TOOLTIP_URN = "//span[normalize-space() = 'Удалить']";



}
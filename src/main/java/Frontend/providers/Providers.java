package Frontend.providers;

import Frontend.pages.base.BasePage;
import org.openqa.selenium.WebDriver;

public class Providers extends BasePage {
    public Providers(WebDriver driver) {
        super(driver);
    }


    private BasePage basePage;

    //XPath
    /**
     * "Права доступа"
     **/
    public static final String ACCESS_RIGHTS = "//span[normalize-space()='Права доступа']";
    public static final String PROVIDERS_PAGE = "//li[@role='menuitem']/span[text()='Список провайдеров']";
    public static final String LIST_PROVIDERS_FORM = "//div[@class='card-header']/h2[normalize-space()='Список провайдеров']";
    public static final String LIST_PROVIDER_SUBBLOCK = "//h2[@class='title']";
    public static final String QUANTITY = "//p[@class='info']";
    public static final String NAME_PROVIDER = "//div[normalize-space()='Наименование провайдера']/table[@class='el-table__header']";
    public static final String TABLE_PROVIDERS = "//table[@class='el-table__body']";
    public static final String PEN_PICTOGRAM = "//button[@class='el-button is-text'][1]";
    public static final String PAGINATION = "//span[@class='el-pagination__total is-first']";
    public static final String DISPLAY_BY = "//span[@class='el-pagination__sizes-text']";
    public static final String PAGINATION2 = "//span[@class='el-pagination__sizes-text']/following-sibling::div";
    public static final String PAGINATION3 = "//div[@class='el-select-dropdown__wrap el-scrollbar__wrap el-scrollbar__" +
            "wrap--hidden-default']";
    public static final String PAGINATION_20 = "//span[text()='20']";
    public static final String PAGINATION_50 = "//span[text()='50']";
    public static final String DOOR = "//p[text()='Проверка А.С.']/parent::div/following-sibling::div/following-sibling::div";

    //PUT Providers
    /**
     * Block text "Редактирование провайдера"
     **/
    public static final String BLOCK_PUT_PROVIDER = "//h2[text()='Редактирование провайдера']";
    /**
     * Subblock text "Информация о провайдере"
     **/
    public static final String SUBBLOCK_INFO_PROVIDER="//h3[normalize-space()='Информация о провайдере']";
    /**
     * check-box "Учебный провайдер"
     **/
    public static final String CHECKBOX_TRAINING_PROVIDER="//input[@value='Учебный провайдер']/parent::span/parent::label";
    /**
     * Text "Код провайдера"
     **/
    public static final String CODE_PROVIDER="//p[text()='Код провайдера']";
    /**
     * CHECKBOX "Используется"
     **/
    public static final String CHECKBOX_USED="//span[text()='Используется']/parent::label";
    /**
     * CHECKBOX "Архивный"
     **/
    public static final String CHECKBOX_ARCHIVAL="//span[text()='Архивный']/parent::label";
    /**
     * Text "Наименование провайдера"
     **/
    public static final String NAME_PROVIDER_PUT="//p[normalize-space()='Наименование провайдера']";
    /**
     * Text "Головное подразделение"
     **/
    public static final String HEADQUARTERS="//p[normalize-space()='Головное подразделение']";
    /**
     * Text "Входит в структуру (тер орган)"
     **/
    public static final String INCLUDED_IN_THE_STRUCTURE ="//p[normalize-space()='Входит в структуру (тер. орган)']";
    /**
     * Button "Сохранить"
     **/
    public static final String SAVE="//div[@class='provider-info__buttons']//span[contains(.,'Сохранить')]";
    /**
     * Button "Закрыть"
     **/
    public static final String CLOSE="//button[@class='el-button']/span[normalize-space()='Закрыть']";
    /**
     * Text "Провайдер включает в себя следующие субъекты контроля: "
     **/
    public static final String PROVIDER_INCLUDES="//h3[normalize-space()='Провайдер включает в себя следующие субъекты контроля:']";
    /**
     * Text "Провайдер включает в себя следующие организации:"
     **/
    public static final String PROVIDER_INCLUDES_ORGANIZATION="//h3[text()='Провайдер включает в себя следующие организации:']";
    /**
     * Text "Изменения сохранены(всплывающее окно)"
     **/
    public static final String CHANGES_SAVED="//div[@class='el-message el-message--success is-center']";
    /**
     * Button "Редактировать субъекты контроля"
     **/
    public static final String PUT_CONTROL_SUBJECT="//button[@data-v-9cde2469]";

    //PUT Control subject
    /**
     * Text "Наименование субъекта контроля"
     **/
    public static final String NAME_CS="(//label[normalize-space()='Наименование субъекта контроля'])[1]";
    /**
     * Text block "Субъекты контроля"
     **/
    public static final String BLOCK_CS="//h2[normalize-space()='Субъекты контроля']";
    /**
     * Field "Наименование субъекта контроля"
     **/
    public static final String ENTRY_FIELD_NAME="(//div[@class='el-input__wrapper'])[1]";
    /**
     * Text "Номер факса"
     **/
    public static final String NUMBER_FAX="(//label[normalize-space()='Номер факса'])[1]";
    /**
     * Field "Номер факса"
     **/
    public static final String ENTRY_FIELD_NUMBER_FAX="(//input[@placeholder='Введите номер'])[1]";
    /**
     * Button "Добавить субъект контроля"
     **/
    public static final String BUTTON_POST_CS="(//button[@class='el-button el-button--primary el-button--large'])[1]";
    /**
     * Text "Список субъектов контроля"
     **/
    public static final String LIST_CS="//h2[normalize-space()='Список субъектов контроля']";
    /**
     * Button "Закрыть"
     **/
    public static final String BUTTON_CLOSE="//button[@class='el-button el-button--large']";
    /**
     * Button "Сохранить"
     **/
    public static final String BUTTON_SAVE="//button[@class='el-button el-button--primary el-button--large']/span[text()='Сохранить']";


    //Methods:


}

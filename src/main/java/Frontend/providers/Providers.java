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
    public static final String BLOCK_PUT_PROVIDER = "//h2[text()='Редактирование провайдера']";
    public static final String DOOR = "//p[text()='Проверка А.С.']/parent::div/following-sibling::div/following-sibling::div";


    //Methods:


}

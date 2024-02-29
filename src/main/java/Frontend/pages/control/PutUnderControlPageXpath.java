package Frontend.pages.control;

public class PutUnderControlPageXpath {
    /**
     * Xpath for button with text - "Постановка на контроль"
     * Относится к MainPage.
     * **/
    public static final String PUT_UNDER_CONTROL_BUTTON_XPATH = "//button[@class=\"el-button el-button--primary\"]";
    /**
     * Xpath for title page - "Данные документа, поступившего на контроль"
     * **/
    public static final String putUnderControlButton =
            "//h2[contains(text(),'Данные документа, поступившего на контроль')]";

    /**
     * Xpath for button with text - "Добавить поручение"
     * **/
    public static final String addAssignmentButton = "//button[@class=\"el-button is-plain form__item_223\"]";

    /**
     * Xpath for title block - "Поручение 1"
     * **/
    public static final String AssignmentBlockTitle = "//span[@class=\"el-only-" +
            "child__content el-tooltip__trigger el-tooltip__trigger\"]";

    /**
     * Xpath for title block - "Головной исполнитель поручения"
     * **/

    public static final String headExecutorBlockTitle = "//h3[contains(text(),'Головной исполнитель поручения')]";

    /**
     * Xpath for text from button - "Добавить соисполнителя"
     * **/

    public static final String addCoExecutorButtonText = "//div[@class='el-card is-always-shadow " +
            "assignment-section__tab-pane']//div[3]//button[1]//span[1]";

    /**
     * Xpath for element - Icon in the form of arrow for revealing/hiding a block
     * "Соисполнители головного исполнителя поручения"
     * **/

    public static final String arrowIconCoExecutorHeadExecutor = "(//*[name()='svg'])[57]";

    /**
     * Xpath for title block - "Соисполнители головного исполнителя поручения"
     * **/

    public static final String coExecutorHeadExecutorBlockTitle = "//h3[contains(text()," +
            "'Соисполнители головного исполнителя поручения')]";

    /**
     * Xpath for title sub block - "Соисполнитель 1"
     * **/

    public static final String coExecutorOneHeadExecutorSubBlockTitle = "//span[@class=\"subtitle_medium\"]";

    /**
     * Xpath for element - Icon in the form of arrow for revealing/hiding a block "Соисполнитель 1"
     * **/

    public static final String arrowIconCoExecutorOneHeadExecutor = "(//*[name()='svg'])[70]";

    /**
     * Xpath for button "Удалить" for sub block - "Соисполнитель 1"
     * **/

//    public static final String buttonDeleteCoExecutorHeadExecutor = "//a[@class='el-button " +
//            "el-button--primary is-link link']//span[1]";

    public static final String buttonDeleteCoExecutorHeadExecutor = "(//a[@class='el-button " +
            "el-button--primary is-link link']//span[1])[1]";


    /**
     * Xpath for element (button) - "Добавить письмо от соисполнителя"
     * **/

    public static final String addLetterCoExecutorHeadExecutor = "//div[@class='el-collapse " +
            "co-executor']//div[@class='form__row']//span[1]";

    /**
     * Xpath for element (button) - "Добавить письмо от соисполнителя" ,
     * если нажат радио баттон "Да" в чекбоксе с заголовком - "Факт предоставления"
     * **/

    public static final String addLetterCoExecutorAfterCheckBox = "//a[@class=\"el-button " +
            "el-button--primary is-link link item__btn\"]";

    /**
     * Xpath for text field with title - "Соисполнитель"
     * **/

    public static final String textFieldCoExecutorHeadExecutor = "(//input[@class=\"el-input__inner\"])[44]";

    /**
     * Xpath for radio button "Да" on the element with title - "Факт предоставления"
     * **/

    public static final String radioButtonYesCoExecutorHeadExecutor = "(//span[@class=\"el-radio__inner\"])[1]";

    /**
     * Xpath for radio button "Нет" on the element with title - "Факт предоставления"
     * **/

    public static final String radioButtonNoCoExecutorHeadExecutor = "(//span[@class=\"el-radio__inner\"])[2]";

    /**
     * Xpath for title - "Письмо 1" in sub block co executor head executor
     * **/

    public static final String titleLetterOneCoExecutorHeadExecutor = "//span[@class=\"subtitle subtitle_medium\"]";

    /**
     * Xpath for button "Удалить" for sub block "Письмо 1" co executor head executor
     * **/

    public static final String buttonDeleteLetterCoExecutorHeadExecutor = "(//a[@class='el-button " +
            "el-button--primary is-link link']//span[1])[2]";

    /**
     * Xpath for title - "Дата, № док." in sub block "Письмо 1" co executor head executor
     * **/

    public static final String titleTextFieldDateLetterCoExHeadEx = "(//label)[57]";

    /**
     * Xpath for title - "Примечание" in sub block "Письмо 1" co executor head executor
     * **/

    public static final String titleTextFieldNoteLetterCoExHeadEx = "(//label)[58]";

    /**
     * Xpath for title "Вы действительно хотите удалить соисполнителя ?"
     * when button "Удалить" was pressed and modal window was revealed
     * **/

    public static final String titleTextWindowDeleteCoExecutor = "//p[contains(text()," +
            "'Вы действительно хотите удалить соисполнителя ?')]";

    /**
     * Xpath for button - "Нет"
     * when button "Удалить" was pressed and modal window was revealed
     * **/

    public static final String buttonNoWindowDeleteCoExecutor = "//div[@class='el-overlay " +
            "is-message-box']//button[1]//span[1]";

    /**
     * Xpath for button - "Да"
     * when button "Удалить" was pressed and modal window was revealed
     * **/

    public static final String buttonYesWindowDeleteCoExecutor = "//div[@class='el-overlay " +
            "is-message-box']//button[2]//span[1]";

    /**
     * Xpath for button - "Да" clickable
     * when button "Удалить" was pressed and modal window was revealed
     * **/

    public static final String buttonYesWindowDeletePressCoExecutor = "(//button[@class=\"el-button " +
            "el-button--primary\"])[4]";

    /**
     Xpath for element - Icon in the form of arrow for revealing/hiding a block
     * "Соисполнители головного исполнителя поручения" after deletion one
     * of elements
     * **/

    public static final String arrowIconCoExecutorHeadExecutorAfterDeletion = "(//*[name()='svg'])[68]";
}

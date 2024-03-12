package Frontend.pages.control;

public class PutUnderControlPageXpath {
    /**
     * Xpath для кнопки - "Постановка на контроль"
     * Относится к MainPage.
     * **/
    public static final String PUT_UNDER_CONTROL_BUTTON_XPATH = "//button[@class=\"el-button el-button--primary\"]";
    /**
     * Xpath для заголовка - "Данные документа, поступившего на контроль"
     * **/
    public static final String putUnderControlButton =
            "//h2[contains(text(),'Данные документа, поступившего на контроль')]";

    /**
     * Xpath для кнопки - "Добавить поручение"
     * **/
    public static final String addAssignmentButton = "//button[@class=\"el-button is-plain form__item_223\"]";

    /**
     * Xpath для заголовка - "Поручение 1"
     * **/
    public static final String assignmentOneBlockTitle = "//span[@class=\"el-only-" +
            "child__content el-tooltip__trigger el-tooltip__trigger\"]";

    /**
     * Xpath для заголовка - "Поручение 2"
     * **/

    public static final String assignmentTwoBlockTitle = "(//span[@class=\"el-only-child__content " +
            "el-tooltip__trigger el-tooltip__trigger\"])[2]";

    /**
     * Xpath для поля с заголовком - "Номер поручения (пункт, абзац и т.д.)"
     * **/

    public static final String assignmentNumberFieldTitle = "(//label)[41]";

    /**
     * Xpath для поля с заголовком - "Тематика поручения"
     * **/

    public static final String assignmentThemeFieldTitle = "(//label)[42]";

    /**
     * Xpath для поля с заголовком - "Содержание поручения"
     * **/

    public static final String assignmentContentFieldTitle = "(//label)[43]";

    /**
     * Xpath для поля с заголовком - "Примечание"
     * **/

    public static final String assignmentNoteFieldTitle = "(//label)[44]";

    /**
     * Xpath для поля с заголовком - "Количество поручений 1"
     * **/

    public static final String assignmentCountBlock = "(//span[@class=\"info\"])[7]";

    /**
     * Xpath для текстового поля - "Номер поручения (пункт, абзац и т.д.)"
     * для внесения данных в блоке "Поручение 1"
     * **/

    public static final String assignmentNumberFieldInput = "(//input[@class=\"el-input__inner\"])[35]";

    /**
     * Xpath для текстового поля/списка - "Номер поручения (пункт, абзац и т.д.)"
     * для внесения данных в блоке "Поручения, включенные в документ и подлежащие контролю"
     * **/

    public static final String assignmentAllFieldInput = "(//input[@class=\"el-input__inner\"])[33]";

    /**
     * Xpath для первого элемента в выпадающем списке "Номер поручения (пункт, абзац и т.д.)"
     * в блоке "Поручения, включенные в документ и подлежащие контролю"
     * **/

    public static final String assignmentDropDownFirstElement = "(//li[@role='menuitem'])[183]";

    /**
     * Xpath для кнопки "Перейти" (кнопка в виде "стрелочки")
     * в блоке "Поручения, включенные в документ и подлежащие контролю"
     * **/

    public static final String assignmentButtonRedirect = "(//button[@class=\"el-button " +
            "el-button--primary btn__icon\"])[2]";

    /**
     * Xpath для формы "Поручение X" , где x - номер поручения,
     * для выбранного элемента на форме со списком поручений
     * **/

    public static final String assignmentActiveElement = "//div[@class=\"el-tabs__item is-top is-active\"]//span";

    /**
     * Xpath для вкладки неактивной вкладки в блоке "Поручения, включенные в документ и подлежащие контролю"
     * ; для первого неактивного элемента (Если выбрано "Поручение 2" , то первым неактивным элмементом
     * будет "Поручение 1")
     * **/

    public static final String assignmentNoActiveElement = "(//div[@class=\"el-tabs__item is-top\"]//span)[1]";

    /**
     * Xpath для заголовка - "Головной исполнитель поручения"
     * **/

    public static final String headExecutorBlockTitle = "//h3[contains(text(),'Головной исполнитель поручения')]";

    /**
     * Xpath кнопки - "Добавить соисполнителя"
     * **/

    public static final String addCoExecutorButtonText = "//*[@id=\"app\"]/div/div/div/div/div/" +
            "div[2]/div/div[3]/div/div[2]/div[2]/button/span";

    /**
     * Xpath кнопки - "Добавить соисполнителя" - кликабельная
     * в блоке "Головной исполнитель поручения"
     * **/

    public static final String getAddCoExecutorButtonPress = "(//button[@class=\"el-button el-button--primary\"])[1]";

    /**
     * Xpath элемента - Icon in the form of arrow for revealing/hiding a block
     * "Соисполнители головного исполнителя поручения"
     * **/

    public static final String arrowIconCoExecutorHeadExecutor = "(//*[name()='svg'])[57]";

    /**
     * Xpath для заголовка - "Соисполнители головного исполнителя поручения"
     * **/

    public static final String coExecutorHeadExecutorBlockTitle = "//h3[contains(text()," +
            "'Соисполнители головного исполнителя поручения')]";

    /**
     * Xpath для заголовка - "Соисполнитель 1"
     * **/

    public static final String coExecutorOneHeadExecutorSubBlockTitle = "//span[@class=\"subtitle_medium\"]";

    /**
     * Xpath для элемента - Icon in the form of arrow for revealing/hiding a block "Соисполнитель 1"
     * **/

    public static final String arrowIconCoExecutorOneHeadExecutor = "(//*[name()='svg'])[70]";

    /**
     * Xpath для элемента - пиктограммы в виде "стрелочки" в блоке "Соисполнитель 1"
     * **/

    public static final String arrowIconCoExOneHead = "(//*[name()='svg'])[68]";

    /**
     * Xpath для кнопки  "Удалить" в подблоке - "Соисполнитель 1"
     * **/

    public static final String buttonDeleteCoExecutorHeadExecutor = "(//a[@class='el-button " +
            "el-button--primary is-link link']//span[1])[1]";


    /**
     * Xpath для кнопки - "Добавить письмо от соисполнителя"
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
     * Xpath для заголовка текстового поля - "Соисполнитель"
     * **/

    public static final String textFieldCoExecutorHeadExecutor = "(//input[@class=\"el-input__inner\"])[44]";

    /**
     * Xpath для радио баттона "Да" в элементе с заголовком - "Факт предоставления"
     * **/

    public static final String radioButtonYesCoExecutorHeadExecutor = "(//span[@class=\"el-radio__inner\"])[1]";

    /**
     * Xpath для радио баттона "Нет" в элементе с заголовком - "Факт предоставления"
     * **/

    public static final String radioButtonNoCoExecutorHeadExecutor = "(//span[@class=\"el-radio__inner\"])[2]";

    /**
     * Xpath для заголовка - "Письмо 1" в подблоке "Соисполнитель головного исполнителя"
     * **/

    public static final String titleLetterOneCoExecutorHeadExecutor = "//span[@class=\"subtitle subtitle_medium\"]";

    /**
     * Xpath для кнопки "Удалить" для подблока "Письмо 1" в подблоке "Соисполнитель головного исполнителя"
     * **/

    public static final String buttonDeleteLetterCoExecutorHeadExecutor = "(//a[@class='el-button " +
            "el-button--primary is-link link']//span[1])[2]";

    /**
     * Xpath для кнопки "Удалить" для подблока "Письмо 2" в подблоке "Соисполнитель головного исполнителя"
     * **/

    public static final String buttonDeleteLetterCoExecutorHeadSecond = "(//a[@class='el-button " +
            "el-button--primary is-link link']//span[1])[3]";

    /**
     * Xpath для заголовка - "Дата, № док." в подблоке "Письмо 1" в подблоке "Соисполнитель головного исполнителя"
     * **/

    public static final String titleTextFieldDateLetterCoExHeadEx = "(//label)[57]";

    /**
     * Xpath для текстового поля - "Дата, № док." в подблоке "Письмо 1" в подблоке "Соисполнитель головного исполнителя"
     * для внесения данных
     * **/

    public static final String inputTextFieldDateLetterCoExHeadEx = "(//input[@class=\"el-input__inner\"])[45]";

    /**
     * Xpath для текстового поля - "Дата, № док." в подблоке "Письмо 2" в подблоке "Соисполнитель головного исполнителя"
     * для внесения данных
     * **/

    public static final String inputTextFieldDateLetterCoExHeadExSecond = "(//input[@class=\"el-input__inner\"])[46]";

    /**
     * Xpath для заголовка - "Примечание" в подблоке "Письмо 1" в подблоке "Соисполнитель головного исполнителя"
     * **/

    public static final String titleTextFieldNoteLetterCoExHeadEx = "(//label)[58]";

    /**
     * Xpath заголовка "Вы действительно хотите удалить соисполнителя ?"
     * когда кнопка "Удалить" была нажата и открылось модальное окно
     * **/

    public static final String titleTextWindowDeleteCoExecutor = "//p[contains(text()," +
            "'Вы действительно хотите удалить соисполнителя ?')]";

    /**
     * Xpath для кнопки - "Нет"
     * когда кнопка "Удалить" была нажата и открылось модальное окно
     * **/

    public static final String buttonNoWindowDeleteCoExecutor = "//div[@class='el-overlay " +
            "is-message-box']//button[1]//span[1]";

    /**
     * Xpath для кнопки - "Да"
     * когда кнопка "Удалить" была нажата и открылось модальное окно
     * **/

    public static final String buttonYesWindowDeleteCoExecutor = "//div[@class='el-overlay " +
            "is-message-box']//button[2]//span[1]";

    /**
     * Xpath для кнопки - "Да" (кликабельная)
     * когда кнопка "Удалить" была нажата и открылось модальное окно
     * - (Для головного исполнителя)
     * **/

    public static final String buttonYesWindowDeletePressCoExecutor = "(//button[@class=\"el-button " +
            "el-button--primary\"])[4]";

    /**
     * Xpath для кнопки - "Да" (кликабельная)
     * когда кнопка "Удалить" была нажата и открылось модальное окно
     * - (Для исполнителя)
     * **/

    public static final String buttonYesWindowDeletePressCoExecutorEx = "(//button[@class=\"el-button " +
            "el-button--primary\"])[5]";

    /**
     * Xpath для элемента - Icon in the form of arrow for revealing/hiding в блоке
     * "Соисполнители головного исполнителя поручения" после
     * удаления одного из элементов
     * **/

    public static final String arrowIconCoExecutorHeadExecutorAfterDeletion = "(//*[name()='svg'])[68]";

    /**
     * Xpath для элемента - пиктограммы в виде "стрелочки" в блоке
     * "Соисполнители головного исполнителя поручения" после
     * **/

    public static final String arrowIconCoExHeadEx = "(//*[name()='svg'])[66]";

    /**
     * Xpath для кнопки - "Добавить исполнителя в МВД России (территориальном органе МВД России)"
     * **/

    public static final String buttonAddExecutor = "(//button[@class=\"el-button " +
            "el-button--primary is-link link\"])[5]";

    /**
     * Xpath для кнопки - "Добавить соисполнителя"
     * **/

    public static final String buttonAddCoExecutorExecutor = "(//button[@class=\"el-button el-button--primary\"])[2]";

    /**
     * Xpath для кнопки - "Удалить" (кликабельная)
     * в блоке "Соисполнитель 1" (Для исполнителя)
     * **/

    public static final String buttonDeleteCoExecutorExecutor = "//a[@class=\"el-button " +
            "el-button--primary is-link link\"]";

    /**
     *  Xpath для кнопки - "Нет" (кликабельная)
     * в блоке "Соисполнитель 1" (Для исполнителя)
     * **/

    public static final String buttonNoWindowDeletePressCoExecutor = "(//button[@class=\"el-button\"])[2]";

    /**
     * Xpath для текстового поля "Дата, № док"
     * для внесения данных
     * **/

    public static final String textFieldDateLetterCoEx = "(//input[@class=\"el-input__inner\"])[64]";

    /**
     * Xpath для поля "Подразделение соисполнителя" в
     * блоке "Соисполнитель 2"
     * **/

    public static final String textFieldCoExecutorOrgEx = "(//input[@class=\"el-input__inner\"])[65]";

    /**
     * Xpath для поля "Подразделение соисполнителя" в блоке
     * "Соисполнитель 1"
     * **/

    public static final String textFieldCoExecutorOrgOne = "(//input[@class=\"el-input__inner\"])[59]";

    /**
     * Xpath для тултипа , который появится при
     * наведении на заголовок "Поручение 2"
     * при изменении названия на длинное
     * **/

    public static final String toolTipAssignmentTwo = "//div[@class=\"el-popper is-dark\"]";

    /**
     * Xpath для кнопки "Сохранить"
     * в блоке "Ход исполнения"
     * внизу страницы
     * **/

    public static final String buttonSaveExecution = "(//button[@class=\"el-button el-button--primary\"])[2]";


}

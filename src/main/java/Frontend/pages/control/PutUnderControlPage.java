package Frontend.pages.control;

import Frontend.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static Frontend.constants.Constant.TimeoutVariable.IMPLICIT_WAIT;
import static Frontend.pages.control.PutUnderControlPageXpath.*;

public class PutUnderControlPage extends BasePage {

    public PutUnderControlPage(WebDriver driver) {
        super(driver);
    }

    WebDriverWait wait = new WebDriverWait(driver, IMPLICIT_WAIT);
    Actions actions = new Actions(driver);

    // Methods:
    /**
     * Нажать на кнопку - "Постановка на контроль"
     * **/
    public void openPagePutUnderControl(){
        waitElementDisplay(PUT_UNDER_CONTROL_BUTTON_XPATH);
        click(PUT_UNDER_CONTROL_BUTTON_XPATH);
    }
    /**
     * Получить текст из заголовка - Постановка на контроль"
     * **/

    public String getTitleFromPage(){
        waitElementDisplay(putUnderControlButton);
        return getText(putUnderControlButton);
    }
    /**
     * Нажать на кнопку - "Добавить поручение"
     * **/

    public void pressAddAssignment(){
        waitElementDisplay(addAssignmentButton);
        click(addAssignmentButton);
    }

    /**
     * Получить текст из заголовка - "Поручение 1"
     * **/

    public String getTitleAssignmentBlock(){
        waitElementDisplay(assignmentOneBlockTitle);
        return getText(assignmentOneBlockTitle);
    }

    /**
     * Проверка полей в блоке "Головной исполнитель"
     * @return true - Если отобразился блок с заголовоком - "Поручение 1",
     * отобразился блок с заголовком - "Головной исполнитель поручения",
     * отобразилась пиктограмма в виде "стрелочки",
     * отбразилась кнопка с текстом - "Добавить соисполнителя " (с пробелом)
     * ; false - Если одно из условий не выполняется
     *
     **/

    public boolean isFieldsHeadExecutorRevealed(){
        String assignmentBlock = getTextFromBlock(assignmentOneBlockTitle);
        String headExecutorBlock = getTextFromBlock(headExecutorBlockTitle);
        boolean buttonOnPage = findElement(arrowIconCoExecutorHeadExecutor).isDisplayed();
        String addCoExecutorButton = getTextFromBlock(addCoExecutorButtonText);
        if (assignmentBlock.equals("Поручение 1") && (headExecutorBlock.equals("Головной исполнитель поручения"))
                && buttonOnPage && addCoExecutorButton.equals("Добавить соисполнителя")){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Нажать на кнопку "Добавить соисполнителя"
     * **/

    public void pressAddCoExecutor(){
        waitElementDisplay(addCoExecutorButtonText);
        click(addCoExecutorButtonText);
    }

    /**
     * Нажать на кнопку "Добавить соисполнителя"
     * в блоке "Головной исполнитель поручения"
     * **/

    public void pressAddCoExecutorHeadEx(){
        waitElementDisplay(getAddCoExecutorButtonPress);
        click(getAddCoExecutorButtonPress);
    }

    /**
     * Проверка полей в подблоке "Соисполнитель 1"
     * @return true - Если отображается блок "Соисполнители головного исполнителя поручения",
     * отобразился блок с заголовком - "Соисполнитель 1",
     * отобразилась пиктограмма в виде "стрелочки",
     * отобразилась кнопка с тесктом - "Удалить",
     * отобразилось текстовое поле с заголовком - "Соисполнитель",
     * отобразился чек-бокс (с радио-баттонами) с заголовком - "Факт представления",
     * отобразилась кнопка с текстом - "Добавить письмо от соисполнителя",
     * ; false - Если одно из условий не выполняется
     * **/

    public boolean isFieldsCoExecutorHeadExecutorRevealed(){
        waitElementDisplay(coExecutorOneHeadExecutorSubBlockTitle);
        String coExecutorBlock = getTextFromBlock(coExecutorHeadExecutorBlockTitle);
        String coExecutorOneSubBlock = getTextFromBlock(coExecutorOneHeadExecutorSubBlockTitle);
        boolean isButtonHideOnPage = findElement(arrowIconCoExOneHead).isDisplayed();
        String buttonDelete = getTextFromBlock(buttonDeleteCoExecutorHeadExecutor);
        boolean isTitleCoExecutorOnPage = false;
        boolean isCheckBoxTitleCoExecutorOnPage = false;
        List<WebElement> titles = driver.findElements(By.tagName("label"));
        for (WebElement titleCoExecutor: titles){
            String text = titleCoExecutor.getText();
            if (text.equals("Соисполнитель")){
                isTitleCoExecutorOnPage = true;
            }
            if (text.equals("Факт представления")){
                isCheckBoxTitleCoExecutorOnPage = true;
            }
        }
        String addLetterTitle = getTextFromBlock(addLetterCoExecutorHeadExecutor);
        // Add check that button - "addLetter" is disabled
        if (coExecutorBlock.equals("Соисполнители головного исполнителя поручения")
        && coExecutorOneSubBlock.equals("Соисполнитель 1")
        && isButtonHideOnPage
        && buttonDelete.equals("Удалить")
        && isTitleCoExecutorOnPage
        && isCheckBoxTitleCoExecutorOnPage
        && addLetterTitle.equals("Добавить письмо от соисполнителя")
        ){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Внести случайное значение в поле - "Соисполнитель"
     * **/

    public void inputRandomValueCoExecutorTextField(){
        waitElementDisplay(textFieldCoExecutorHeadExecutor);
        sendKeys(textFieldCoExecutorHeadExecutor,"тест");
    }

    /**
     * Нажать на поле - "Соисполнитель"
     * **/

    public void textFieldCoExecutorClick(){
        click(textFieldCoExecutorHeadExecutor);
    }

    /**
     * Нажать на радио баттон "Да" в элементе с заголовоком - "Факт предоставления"
     * **/

    public void clickButtonYesCoExecutor(){
        waitElementDisplay(radioButtonYesCoExecutorHeadExecutor);
        click(radioButtonYesCoExecutorHeadExecutor);
    }

    /**
     * Нажать на радио баттон "Нет" в элементе с заголовоком - "Факт предоставления"
     * **/

    public void clickButtonNoCoExecutor(){
        waitElementDisplay(radioButtonNoCoExecutorHeadExecutor);
        click(radioButtonNoCoExecutorHeadExecutor);
    }
    /**
     * Кнопка - "Добавить письмо от соисполнителя" активна
     * ; if true - активна
     * ; if false - неактивна
     * **/

    public boolean isAddLetterButtonActive(){
        waitElementDisplay(addLetterCoExecutorAfterCheckBox);
        return findElement(addLetterCoExecutorAfterCheckBox).isEnabled();
    }

    /**
     * Нажать на кнопку - "Добавить письмо от соисполнителя"
     * в блоке "Головной исполнитель"
     * **/

    public void pressButtonAddLetterCoExecutorHeadExecutor(){
        waitElementDisplay(addLetterCoExecutorAfterCheckBox);
        click(addLetterCoExecutorAfterCheckBox);
    }

    /**
     *  Проверка отображения полей в подблоке "Письмо"
     * @return true - Если отображается заголовок - "Письмо 1",
     * отображается кнопка - "Удалить",
     * отображается текстовое поле с заголовком - "Дата, № док.",
     * отображается текстовое поле с заголовком - "Примечание"
     * ; false - Если одно из условий не выполняется
     * **/

    public boolean isFieldsAddLetterCoExecutorHeadExecutorRevealed(){
        waitElementDisplay(titleLetterOneCoExecutorHeadExecutor);
        String titleLetterOne = getTextFromBlock(titleLetterOneCoExecutorHeadExecutor);
        String buttonDelete = getTextFromBlock(buttonDeleteLetterCoExecutorHeadExecutor);
        String textFieldDateAndNumber = getTextFromBlock(titleTextFieldDateLetterCoExHeadEx);
        String textFieldNote = getTextFromBlock(titleTextFieldNoteLetterCoExHeadEx);
        if (titleLetterOne.equals("Письмо 1")
        && buttonDelete.equals("Удалить")
        && textFieldDateAndNumber.equals("Дата, № док.")
        && textFieldNote.equals("Примечание")
        ) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Нажать на кнопку - "Удалить" в под блоке "Письмо 1"
     * в блоке "Соисполнитель головного исполнителя"
     * **/

    public void pressButtonDeleteLetterOneHeadEx(){
        waitElementDisplay(buttonDeleteLetterCoExecutorHeadExecutor);
        click(buttonDeleteLetterCoExecutorHeadExecutor);
    }

    /**
     * Нажать на кнопку - "Удалить" в под блоке "Письмо 2"
     * в блоке "Соисполнитель головного исполнителя"
     * **/

    public void pressButtonDeleteLetterSecondHeadEx(){
        waitElementDisplay(buttonDeleteLetterCoExecutorHeadSecond);
        click(buttonDeleteLetterCoExecutorHeadSecond);
    }

    /**
     * Нажать на кнопку - "Удалить" в под блоке "Соисполнитель 1"
     * в блоке "Головной исполнитель"
     * **/

    public void pressButtonDeleteCoExOneHeadEx(){
        waitElementDisplay(buttonDeleteCoExecutorHeadExecutor);
        click(buttonDeleteCoExecutorHeadExecutor);
    }

    /**
     * Проверка отображения модального окна после нажатия на кнопку "Удалить"
     * в подблоке "Соисполнитель 1" (Головной исполнитель)
     * @return true - Отобразилось окно с текстом - "Вы действительно хотите удалить соисполнителя ?",
     * отобразились кликабельные кнопки - "Да" и "Нет"
     * ; false - Если условия не выполняются
     * **/

    public boolean isModalWindowRevealed(){
        waitElementDisplay(titleTextWindowDeleteCoExecutor);
        String textOnWindow = getTextFromBlock(titleTextWindowDeleteCoExecutor);
        String buttonNo = getTextFromBlock(buttonNoWindowDeleteCoExecutor);
        String buttonYes = getTextFromBlock(buttonYesWindowDeleteCoExecutor);
        boolean isNoClickable = findElement(buttonNoWindowDeleteCoExecutor).isEnabled();
        boolean isYesClickable = findElement(buttonYesWindowDeleteCoExecutor).isEnabled();

        if (textOnWindow.equals("Вы действительно хотите удалить соисполнителя ?")
        && buttonNo.equals("Нет")
        && buttonYes.equals("Да")
        && isNoClickable
        && isYesClickable
        ){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Нажать на кнопку - "Да" в модальном окне удаления "Соисполнитель 1"
     * в блоке "Головной исполнитель"
     * **/

    public void pressYesDeleteWindowCoExecutor(){
        waitElementDisplay(buttonYesWindowDeletePressCoExecutor);
        click(buttonYesWindowDeletePressCoExecutor);
    }

    /**
     * Нажать на кнопку - "Да" в модальном окне удаления "Соисполнитель 1"
     * в блоке "Исполнитель"
     * **/

    public void pressYesDeleteWindowCoExecutorExecutor(){
        waitElementDisplay(buttonYesWindowDeletePressCoExecutorEx);
        click(buttonYesWindowDeletePressCoExecutorEx);
    }

    /**
     * Проверить заголовок - "Соисполнители головного исполнителя поручения"
     * НЕ отображается
     * **/

    public boolean isBlockCoExecutorNotDisplayed(){
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(coExecutorHeadExecutorBlockTitle)));
    }

    /**
     * Нажать на элемент для скрытия/раскрытия блока - "Соисполнители головного исполнителя поручения"
     * после удаления
     * **/

    public void clickArrowIconCoExecutorHeadExecutor(){
        waitElementDisplay(arrowIconCoExecutorHeadExecutorAfterDeletion);
        click(arrowIconCoExecutorHeadExecutorAfterDeletion);
    }

    /**
     * Нажать на элемент для скрытия/раскрытия блока - "Соисполнители головного исполнителя поручения"
     * **/

    public void clickArrowIconCoExHeadEx(){
        waitElementDisplay(arrowIconCoExHeadEx);
        click(arrowIconCoExHeadEx);
    }

    /**
     * Проверить заголовок - "Соисполнитель 1"
     * НЕ отображается
     * **/

    public boolean isBlockCoExecutorOneNotDisplayed(){
        return wait.until(ExpectedConditions.invisibilityOfElementLocated
                (By.xpath(coExecutorOneHeadExecutorSubBlockTitle)));
    }

    /**
     * Нажать на кнопку - "Добавить исполнителя в МВД России (территориальном органе МВД России)"
     * **/

    public void pressButtonAddExecutor(){
        waitElementDisplay(buttonAddExecutor);
        click(buttonAddExecutor);
    }

    /**
     * Нажать на кнопку - "Добавить соисполнителя" в блоке -
     * "Исполнитель в МВД России"
     * **/

    public void pressButtonAddCoExecutorExecutor(){
        waitElementDisplay(buttonAddCoExecutorExecutor);
        click(buttonAddCoExecutorExecutor);
    }

    /**
     * Нажать на кнопку - "Удалить" в блоке -
     * "Соисполнитель 1"
     * **/

    public void pressButtonDeleteCoExecutorExecutor(){
        waitElementDisplay(buttonDeleteCoExecutorExecutor);
        click(buttonDeleteCoExecutorExecutor);
    }

    /**
     * Нажать на кнопку  - "Нет" в модальном окне удаления "Соисполнитель 1"
     * **/

    public void pressNoDeleteWindowCoExecutor(){
        waitElementDisplay(buttonNoWindowDeletePressCoExecutor);
        click(buttonNoWindowDeletePressCoExecutor);
    }

    /**
     * Под блок с заголовком - "Соисполнитель 1" отображается на странице
     * @return true - "Соисполнитель 1" отображается на странице
     * : false - не отображается на странице
     * **/

    public boolean isCoExecutorOneOnPage(){
        waitElementDisplay(coExecutorOneHeadExecutorSubBlockTitle);
        String coExecutorOneSubBlock = getTextFromBlock(coExecutorOneHeadExecutorSubBlockTitle);
        return coExecutorOneSubBlock.equals("Соисполнитель 1");
    }

    /**
     * Под блок с заголовком - "Письмо 1" отображается на странице
     * @return true - "Письмо 1" не отображается на странице
     * ; false - отображается на странице
     * **/

    public boolean isBlockLetterOneNotDisplayed(){
        return wait.until(ExpectedConditions.invisibilityOfElementLocated
                (By.xpath(titleLetterOneCoExecutorHeadExecutor)));
    }

    /**
     * Внести в поле - "Дата, № док"
     * в блоке "Письмо 1" случайное значение
     * **/

    public void inputRandomValueLetterDateTextField(){
        waitElementDisplay(textFieldDateLetterCoEx);
        sendKeys(textFieldDateLetterCoEx,"тест");
    }

    /**
     * Внести в поле - "Дата, № док"
     * в блоке "Письмо 1" случайное значение
     * (новый)
     * **/

    public void inputValueLetterDateTextField(String text){
        waitElementDisplay(inputTextFieldDateLetterCoExHeadEx);
        sendKeys(inputTextFieldDateLetterCoExHeadEx,text);
    }

    /**
     * Внести в поле - "Дата, № док"
     * в блоке "Письмо 2" случайное значение
     * (новый)
     * **/

    public void inputValueLetterDateTextFieldSecond(String text){
        waitElementDisplay(inputTextFieldDateLetterCoExHeadExSecond);
        sendKeys(inputTextFieldDateLetterCoExHeadExSecond,text);
    }

    /**
     * Внести в поле "Подразделение соисполнителя"
     * случайное значение
     * **/

    public void inputRandomValueCoExOrg(){
        waitElementDisplay(textFieldCoExecutorOrgEx);
        sendKeys(textFieldCoExecutorOrgEx,"тест");
    }

    /**
     * Получение заголовка "Подразделение соисполнителя" в блоке
     * "Соисполнитель 1"
     * **/

    public String getTextFromOrgCoExecutor(){
         return getTextFromBlock(textFieldCoExecutorOrgOne);
    }

    /**
     * Получение заголовка "Дата, № док"
     * в блоке "Письмо 1"
     * **/

    public String getDateFromLetterOne(){
        return getTextFromBlock(textFieldDateLetterCoEx);
    }

    /**
     * Получение заголовка "Письмо 1"
     * **/

    public String getTitleLetterOne(){
        return getTextFromBlock(titleLetterOneCoExecutorHeadExecutor);
    }

    /**
     * Проверка вкладки с заголовком - "Поручение 1"
     * @return true - Если отображается заголовок с текстом - "Поручение 1",
     * отображается поле с заголовком - "Номер поручения (пункт, абзац и т.д.)",
     * отображается поле с заголовком - "Тематика поручения",
     * отображается поле с заголовком -  "Содержание поручения",
     * отображается поле с заголовком - "Примечание",
     * отображается поле с заголовком - "Количество поручений 1"
     * ; false - Если одно из условий не выполняется
     * **/

    public boolean isAssignmentOneRevealed(){
        String title = getTextFromBlock(assignmentOneBlockTitle);
        String assignmentNumberField = getTextFromBlock(assignmentNumberFieldTitle);
        String assignmentTheme = getTextFromBlock(assignmentThemeFieldTitle);
        String assignmentContent = getTextFromBlock(assignmentContentFieldTitle);
        String assignmentNote = getTextFromBlock(assignmentNoteFieldTitle);
        String assignmentCount = getTextFromBlock(assignmentCountBlock);
        if (title.equals("Поручение 1")
        && assignmentNumberField.equals("Номер поручения (пункт, абзац и т.д.)")
        && assignmentTheme.equals("Тематика поручения")
        && assignmentContent.equals("Содержание поручения")
        && assignmentNote.equals("Примечание")
        && assignmentCount.equals("Количество поручений 1")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Проверка вкладки с заголовком - "Поручение 2"
     * @return true - Если отображается заголовок с текстом - "Поручение 1",
     * отображается поле с заголовком - "Номер поручения (пункт, абзац и т.д.)",
     * отображается поле с заголовком - "Тематика поручения",
     * отображается поле с заголовком -  "Содержание поручения",
     * отображается поле с заголовком - "Примечание",
     * отображается поле с заголовком - "Количество поручений 2"
     * ; false - Если одно из условий не выполняется
     * **/

    public boolean isAssignmentTwoRevealed(){
        String title = getTextFromBlock(assignmentTwoBlockTitle);
        String assignmentNumberField = getTextFromBlock(assignmentNumberFieldTitle);
        String assignmentTheme = getTextFromBlock(assignmentThemeFieldTitle);
        String assignmentContent = getTextFromBlock(assignmentContentFieldTitle);
        String assignmentNote = getTextFromBlock(assignmentNoteFieldTitle);
        String assignmentCount = getTextFromBlock(assignmentCountBlock);
        if (title.equals("Поручение 2")
        && assignmentNumberField.equals("Номер поручения (пункт, абзац и т.д.)")
        && assignmentTheme.equals("Тематика поручения")
        && assignmentContent.equals("Содержание поручения")
        && assignmentNote.equals("Примечание")
        && assignmentCount.equals("Количество поручений 2")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Внести в поле "Номер поручения (пункт, абзац и т.д.)"
     * значения , состоящего из 20-ти символов
     * **/

    public void inputBigValueAssignmentNumber(){
        waitElementDisplay(assignmentNumberFieldInput);
        sendKeysWithClear(assignmentNumberFieldInput,"аааааааааааааааааааа");
        //sendKeys(assignmentNumberFieldInput,"аааааааааааааааааааа");
    }

    /**
     * Получить значение заголовка - "Поручение 2"
     * при переименовании вкладки
     * **/

    public String getValueAssignmentTwo(){
        return getTextFromBlock(assignmentTwoBlockTitle);
    }

    /**
     * Удерживать курсор на элементе
     * - заголовке "Поручение 2"
     * **/

    public void holdCursorAssignmentTwoTitle(){
        WebElement assignmentTwoTitle = driver.findElement(By.xpath(assignmentTwoBlockTitle));
        actions.moveToElement(assignmentTwoTitle).perform();
    }

    /**
     * Проверка на отображение тултипа
     * при наведении курсора на элемент с заголовком
     * - "Поручение 2" - тест
     * @return true - Если тултип появился
     * ; false - Если не появился
     * **/

    public boolean isTooltipAppeared(){
        waitElementDisplay(toolTipAssignmentTwo);
        return findElement(toolTipAssignmentTwo).isDisplayed();
    }

    /**
     * Нажать на кнопку "Сохранить"
     * в блоке "Ход исполнения"
     * внизу страницы
     * **/

    public void pressButtonSaveExecution(){
        waitElementDisplay(buttonSaveExecution);
        click(buttonSaveExecution);
    }

    /**
     * Проверка на отображение элемента
     * - кнопки "Сохранить"
     * в блоке "Ход исполнения"
     * @return true - Если кнопка "Сохранить"
     * отображается на странице
     * ; false - Если не отображается
     * **/

    public boolean isButtonSaveOnPage(){
        waitElementDisplay(buttonSaveExecution);
        return findElement(buttonSaveExecution).isDisplayed();
    }

    /**
     * Кликнуть на текстовое поле и
     * выбрать первое поручение в блоке
     * "Поручения, включенные в документ и подлежащие контролю"
     * **/

    public void chooseFirstAssignment(){
        waitElementDisplay(assignmentAllFieldInput);
        click(assignmentAllFieldInput);
        waitElementDisplay(assignmentDropDownFirstElement);
        click(assignmentDropDownFirstElement);
    }

    /**
     * Нажать на кнопку "Перейти"
     * в блоке "Поручения, включенные в документ и подлежащие контролю"
     * **/

    public void pressButtonRedirectAssignment(){
        waitElementDisplay(assignmentButtonRedirect);
        click(assignmentButtonRedirect);
    }

    /**
     * Проверка на отображение "Поручение 1"
     * в виде активной вкладки в элементе со списком поручений
     * @return true - Если текущая активная вкладка -
     * вкладка с текстом "Поручение 1"
     * ; false - Если активная вкладка с любым другим текстом
     * **/

    public boolean isFirstAssignmentSelected(){
        waitElementDisplay(assignmentActiveElement);
        return findElement(assignmentActiveElement).getText().equals("Поручение 1");
    }

    /**
     * Нажать на первую неактивную вкладку в блоке
     * "Поручения, включенные в документ и подлежащие контролю"
     * **/

    public void pressTittleNoActiveAssignment(){
        waitElementDisplay(assignmentNoActiveElement);
        click(assignmentNoActiveElement);
    }

    /**
     * Проверка на отображение "Поручение X"
     * в виде активной вкладки в элементе со списком поручений
     * , где вместо "Поручение X" может быть
     * любое наименование , имя вкладки передается в качестве
     * аргумента
     * @return true - вкладка с наименованием , которое передается в качестве аргумента
     * ; false - Если текущая активная вкладка с другим текстом
     * **/

    public boolean isNamedAssignmentSelected(String name){
        waitElementDisplay(assignmentActiveElement);
        return findElement(assignmentActiveElement).getText().equals(name);
    }

}
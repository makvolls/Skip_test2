package Frontend.pages.control;

import Frontend.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    // Methods:
    /**
     * Wait for button and click - "Постановка на контроль"
     * **/
    public void openPagePutUnderControl(){
        waitElementDisplay(PUT_UNDER_CONTROL_BUTTON_XPATH);
        click(PUT_UNDER_CONTROL_BUTTON_XPATH);
    }
    /**
     * Get text from page title - Постановка на контроль"
     * **/

    public String getTitleFromPage(){
        waitElementDisplay(putUnderControlButton);
        return getText(putUnderControlButton);
    }
    /**
     * Press on button "Добавить поручение"
     * **/

    public void pressAddAssignment(){
        waitElementDisplay(addAssignmentButton);
        click(addAssignmentButton);
    }

    /**
     * Get text from block title - "Поручение 1"
     * **/

    public String getTitleAssignmentBlock(){
        waitElementDisplay(AssignmentBlockTitle);
        return getText(AssignmentBlockTitle);
    }

    /**
     * Check fields of "Head Executor" block
     *
     * @return true - Если отобразился блок с заголовоком - "Поручение 1",
     * отобразился блок с заголовком - "Головной исполнитель поручения",
     * отобразилась пиктограмма в виде "стрелочки",
     * отбразилась кнопка с текстом - "Добавить соисполнителя " (с пробелом)
     * ; false - Если одно из условий не выполняется
     *
     **/

    public boolean checkFieldsHeadExecutor(){
        String assignmentBlock = getTextFromBlock(AssignmentBlockTitle);
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
     * Press on button "Добавить соисполнителя"
     * **/

    public void pressAddCoExecutor(){
        waitElementDisplay(addCoExecutorButtonText);
        click(addCoExecutorButtonText);
    }

    /**
     * Check fields of "Co executor one" sub block
     *
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
        boolean isButtonHideOnPage = findElement(arrowIconCoExecutorOneHeadExecutor).isDisplayed();
        String buttonDelete = getTextFromBlock(buttonDeleteCoExecutorHeadExecutor);
        boolean isTitleCoExecutorOnPage = false;
        boolean isCheckBoxTitleCoExecutorOnPage = false;
        List<WebElement> titles = driver.findElements(By.tagName("label"));
        for (WebElement titleCoExecutor: titles){
            String text = titleCoExecutor.getText();
            if (text.equals("Соисполнитель")){
                //System.out.println("Текстовое поле с заголовком 'Соисполнитель' отобразилось на странице.");
                isTitleCoExecutorOnPage = true;
            }
            if (text.equals("Факт представления")){
                //System.out.println("Чекбокс с заголовком 'Факт представления' отобразился на странице.");
                isCheckBoxTitleCoExecutorOnPage = true;
            }
        }
        String addLetterTitle = getTextFromBlock(addLetterCoExecutorHeadExecutor);
        // Add check that button - "addLetter" is disabled
//        boolean isButtonDisabled = findElement(addLetterCoExecutorHeadExecutor).isEnabled();
        if (coExecutorBlock.equals("Соисполнители головного исполнителя поручения")
        && coExecutorOneSubBlock.equals("Соисполнитель 1")
        && isButtonHideOnPage
        && buttonDelete.equals("Удалить")
        && isTitleCoExecutorOnPage
        && isCheckBoxTitleCoExecutorOnPage
        && addLetterTitle.equals("Добавить письмо от соисполнителя")
//        && isButtonDisabled
        ){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Input value to text field - "Соисполнитель"
     * **/

    public void inputRandomValueCoExecutorTextField(){
        waitElementDisplay(textFieldCoExecutorHeadExecutor);
        sendKeys(textFieldCoExecutorHeadExecutor,"тест");
    }

    /**
     * Click on the radio button "Да" on form with name - "Факт предоставления"
     * **/

    public void clickButtonYesCoExecutor(){
        waitElementDisplay(radioButtonYesCoExecutorHeadExecutor);
        click(radioButtonYesCoExecutorHeadExecutor);
    }

    /**
     * Click on the radio button "Нет" on form with name - "Факт предоставления"
     * **/

    public void clickButtonNoCoExecutor(){
        waitElementDisplay(radioButtonNoCoExecutorHeadExecutor);
        click(radioButtonNoCoExecutorHeadExecutor);
    }
    /**
     * Button - "Добавить письмо от соисполнителя" активна
     * ; if true - активна
     * ; if false - неактивна
     * **/

    public boolean isAddLetterButtonActive(){
        waitElementDisplay(addLetterCoExecutorAfterCheckBox);
        return findElement(addLetterCoExecutorAfterCheckBox).isEnabled();
    }

    /**
     * Click on button - "Добавить письмо от соисполнителя"
     * **/

    public void pressButtonAddLetterCoExecutorHeadExecutor(){
        click(textFieldCoExecutorHeadExecutor);
        waitElementDisplay(addLetterCoExecutorAfterCheckBox);
        click(addLetterCoExecutorAfterCheckBox);
    }

    /**
     * Check fields in "Письмо" sub block
     *
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
     * Click on button - "Удалить" in sub block "Письмо 1"
     * co executor head executor
     * **/

    public void pressButtonDeleteLetterOneHeadEx(){
        waitElementDisplay(buttonDeleteLetterCoExecutorHeadExecutor);
        click(buttonDeleteLetterCoExecutorHeadExecutor);
    }

    /**
     * Click on button - "Удалить" in sub block "Соисполнитель 1"
     * head executor
     * **/

    public void pressButtonDeleteCoExOneHeadEx(){
        waitElementDisplay(buttonDeleteCoExecutorHeadExecutor);
        click(buttonDeleteCoExecutorHeadExecutor);
    }

    /**
     * Modal window with text after press on button "Удалить"
     * in sub block "Соисполнитель 1" head executor
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
     * Click on button - "Да" to delete co executor head executor
     *
     * **/

    public void pressYesDeleteWindowCoExecutor(){
        click(buttonYesWindowDeletePressCoExecutor);
    }

    /**
     * Check title - "Соисполнители головного исполнителя поручения"
     * НЕ отображается
     * **/

    public boolean isBlockCoExecutorNotDisplayed(){
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(coExecutorHeadExecutorBlockTitle)));
        //return isBlockNotDisplayed(coExecutorHeadExecutorBlockTitle);
    }

    /**
     * Click on the element to hide/reveal block - "Соисполнители головного исполнителя поручения"
     * **/

    public void clickArrowIconCoExecutorHeadExecutor(){
        waitElementDisplay(arrowIconCoExecutorHeadExecutorAfterDeletion);
        click(arrowIconCoExecutorHeadExecutorAfterDeletion);
    }

    /**
     * Check title - "Соисполнитель 1"
     * НЕ отображается
     * **/

    public boolean isBlockCoExecutorOneNotDisplayed(){
        return wait.until(ExpectedConditions.invisibilityOfElementLocated
                (By.xpath(coExecutorOneHeadExecutorSubBlockTitle)));
        //return isBlockNotDisplayed(coExecutorOneHeadExecutorSubBlockTitle);
    }

}
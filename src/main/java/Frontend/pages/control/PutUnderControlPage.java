package Frontend.pages.control;

import Frontend.pages.base.BasePage;
import org.openqa.selenium.WebDriver;

public class PutUnderControlPage extends BasePage {

    public PutUnderControlPage(WebDriver driver) {
        super(driver);
    }

    // Xpath on this page:
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
     * **/

    public static final String arrowIcon = "//button[@id='el-collapse-head-7807']//i[@class='el-icon " +
            "el-collapse-item__arrow']//*[name()='svg']//*[name()='path' and contains(@fill,'currentCol')]";

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

    public String getTitleFromBlock(){
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
     * @return false - Если одно из условий не выполняется
     *
     **/

    public boolean checkFieldsHeadExecutor(){
        String assignmentBlock = getTextFromBlock(AssignmentBlockTitle);
        String headExecutorBlock = getTextFromBlock(headExecutorBlockTitle);
        boolean buttonOnPage = findElement(arrowIcon).isDisplayed();
        String addCoExecutorButton = getTextFromBlock(addCoExecutorButtonText);
        if (assignmentBlock.equals("Поручение 1") && (headExecutorBlock.equals("Головной исполнитель поручения"))
                && buttonOnPage && addCoExecutorButton.equals("Добавить соисполнителя ")){
            return true;
        } else {
            return false;
        }
    }


}
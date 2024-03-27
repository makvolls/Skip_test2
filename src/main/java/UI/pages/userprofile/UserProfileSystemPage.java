package UI.pages.userprofile;

import UI.pages.base.BasePage;
import org.openqa.selenium.WebDriver;

public class UserProfileSystemPage extends BasePage {
    public UserProfileSystemPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Xpath for button with text - "Профиль пользователя"
     **/
    public static final String USER_NAME = "//p[text()='Проверка А.С.']";

    /**
     * Xpath for text - локатор названия блока / подблока
     **/

    public static String locatorNameBlock(String blockName) {
        return "//div/h2[@class = 'title' and text()='" + blockName + "']";
    }

    /**
     * Локатор названия поля блока "Профили пользователя".
     */
    public static String locatorNameLabel(String labelName) {
        return "//div/h4 [@class = 'profile__text_title' and text() = '" + labelName + "']";
    }

    public static String locatorTextInLabel(String labelName, String text) {
        return locatorNameLabel(labelName) + "/following-sibling::p[text()= '" + text + "']";
    }

    public static String locatorTextLinkInLabel(String labelName, String textLink) {
        return locatorNameLabel(labelName) + "/following::span[text()= '" + textLink + "']";
    }

    public static final String CLICK_INSCRIPTION = "//span[@class='el-link__inner']";


}

package UI.pages.authorization;

import UI.constants.Users;
import UI.pages.base.BasePage;
import org.openqa.selenium.WebDriver;


public class AuthorizationPage extends BasePage {

    public AuthorizationPage(WebDriver driver) {
        super(driver);
    }


    private static final String authorizationPageLocator = "//section[//form[//div[normalize-space(text()) = 'Авторизация']]]";
    private static final String loginWithPasswordButtonLocator = "//button[contains(text(), 'Войти по') and contains(text(), 'паролю')]";
    private static final String loginInputLocator = "//input[@placeholder = 'Логин']";
    private static final String passwordInputLocator = "//input[@placeholder = 'Пароль']";
    private static final String enterButtonLocator =  "//button[normalize-space(text()) = 'Войти']";

    public static String authForm (String formName) {
        return "//*[contains(text(),'"+ formName +"')]/ancestor::article[@class = 'auth']";
    }

    public void openAuthorizationPage(String url) {
        openURl(url);
        waitElementIsPresence(authorizationPageLocator);
    }

    public boolean isAuthorizationPageOpen() {
        return isElementDisplay(authorizationPageLocator);
    }

    public void clickButtonLoginWithPassword() {
        if (isElementDisplay(loginWithPasswordButtonLocator))
            click(loginWithPasswordButtonLocator);
        waitElementIsVisible(loginInputLocator);
    }

    public void sendLogin(String login) {
        sendKeys(loginInputLocator, login);
    }

    public void sendPassword(String password) {
        sendKeys(passwordInputLocator, password);
    }

    public void clickEnter() {
        click(enterButtonLocator);
    }

    public void authorization(String startPage, Users user) {
        openAuthorizationPage(startPage);
        clickButtonLoginWithPassword();
        sendAuthorization(user);
        clickEnter();

    }

    public void sendAuthorization(Users user) {
        sendLogin(user.getLogin());
        sendPassword(user.getPassword());
    }
    public boolean isDisplayAuthForm(String formName){
        return isElementDisplay(authForm(formName)) ;

    }
}

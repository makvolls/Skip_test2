package UI.pages.main;

import UI.pages.base.BasePage;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {
    public static String nameUser(String FioUser){
        return "//*[normalize-space(text())='" + FioUser + "']";
    }
    public static String errorAuthUser = "//*[@id='/error']";
    public static String locatorHomePage = "//span[@class = 'el-breadcrumb__item']/span[@role = 'link']";

    public MainPage(WebDriver driver) {
        super(driver);
    }
    public boolean isDisplayFioUser(String FioUser) {
        return isElementDisplay(nameUser(FioUser));

    }
    public boolean isDisplayErrorAuthUser(String errorUser) {
        String textError = getText(errorAuthUser);
        return textError.contains(errorUser);

    }
    public boolean isDisplayHomePage(String homePage) {
        String nameForm = getText(locatorHomePage);
        return nameForm.contains(homePage);
    }
    public void clickUserFio(String FioUser){
        waitElementIsVisible(FioUser);
        click(nameUser(FioUser));
    }

}

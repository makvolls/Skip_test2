package UI.pages.stagingOnControl;

import UI.pages.base.BasePage;
import org.openqa.selenium.WebDriver;

public class StagingOnControllPage  extends BasePage {
    public StagingOnControllPage(WebDriver driver) {
        super(driver);
    }

    public static String locatorBlockInForm (String nameBlock){
        return "//button[@class='el-collapse-item__header']/following::h1[contains(.,'" + nameBlock + "')]";
    }
    public void openBlockInForm(String nameBlock){
        waitElementIsVisible(locatorBlockInForm(nameBlock));
        waitElementIsPresence(locatorBlockInForm(nameBlock));
        click(locatorBlockInForm(nameBlock));
    }
}

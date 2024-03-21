package UI.pages.listproviders;

import UI.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ListProvidersPage extends BasePage {
    public static String locatorLabelList(String blockName) {
        return "//div [@class = 'el-form-item__label' and text()[contains(.,'" + blockName + "')]]/parent::div";
    }

    public static String locatorNameProviderInTable(String nameTable, String nameProvider) {
        return "//h2[text() ='" + nameTable + "']/following::td/..//span[contains(.,' " + nameProvider + " ')]";
    }

    public static String locatorTextDropdownList = "//div[@class = 'el-vl__window el-select-dropdown__list']/div";

    public static String locatorLabelNameProvider = "//div [@class = 'el-form-item__label' and text()[contains(.,'Наименование провайдера')]]/following-sibling::div";

    public ListProvidersPage(WebDriver driver) {
        super(driver);
    }

    public static String locatorTableNameProvider(String nameTableProvider) {
        return "//h2[@class= 'title' and text() = '" + nameTableProvider + "']";
    }

    public boolean isDisplayNameTableProvider(String nameTableProvider) {
        return isElementDisplay(locatorTableNameProvider(nameTableProvider));
    }

    public boolean isDisplayLabelInListProvider(String labelName) {
        return isElementDisplay(locatorLabelList(labelName));
    }

    public void clickLabelListProvider() {
        waitElementIsPresence(locatorLabelNameProvider);
        click(locatorLabelNameProvider);
    }

    public String getTextDropdownList() {
        List<WebElement> listProvider = driver.findElements(By.xpath(locatorTextDropdownList));
        String result = null;
        for (WebElement webElement : listProvider) {
            result = webElement.getText();
        }
        return result;
    }


    public boolean isDisplayTextInDropDownList(String nameText) {
        String listValue = getTextDropdownList();
        return listValue.contains(nameText);

    }

    public boolean isDisplayTNameProviderInTable(String nameTable, String nameProvide) {
        return isElementDisplay(locatorNameProviderInTable(nameTable, nameProvide));
    }
}
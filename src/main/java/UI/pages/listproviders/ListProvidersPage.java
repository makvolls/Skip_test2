package UI.pages.listproviders;

import UI.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static UI.pages.userprofile.UserProfileSystemPage.locatorNameLabel;

public class ListProvidersPage extends BasePage {
    public static String locatorLabelList(String labelName) {
        return "//label[contains(.,'" + labelName + "')]/following-sibling::div";
    }
    public static String locatorFilterNotFound(String textFilter) {
        return "//div/p[contains(.,'" + textFilter + "')]";
    }
    public static String locatorInputLabel(String nameLabel) {
        return "//label[contains(.,'" + nameLabel + "')]/following::input[@role = 'button']";
    }
    public static String dropdownElement(String value) {
        return "//div[@class = 'el-popper is-pure is-light el-dropdown__popper remote-select__popover' and not(contains(@style, 'display: none;'))]/descendant::ul/li[contains(.,'" + value + "')]";
    }

    public static String locatorNameProviderInTable(String nameTable, String nameProvider) {
        return "//h2[text() ='" + nameTable + "']/following::td/..//span[text() = '" + nameProvider + "']";
    }

    public static String locatorTextDropdownList = "//ul[@class = 'el-dropdown-menu']/li";

    public static String locatorLabelNameProvider = "//label[contains(.,'Наименование провайдера')]/following-sibling::div";

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

    public List<String> getTextDropdownList() {
        List<WebElement> listProvider = driver.findElements(By.xpath(locatorTextDropdownList));
        List<String> listValue = new ArrayList<>();
        int count = listProvider.size();
        for (int i = 0; i <count ; i++) {
            if (isElementDisplay(locatorTextDropdownList)) {
                String text = listProvider.get(i).getText();
                listValue.add(text);

            }

        }
        return listValue;

    }
    public boolean isDisplayTextInDropDownList(String nameText) {
        List<String> listValue = getTextDropdownList();
        return listValue.contains(nameText);

    }
    public boolean isDisplayTNameProviderInTable(String nameTable, String nameProvide) {
        return isElementDisplay(locatorNameProviderInTable(nameTable, nameProvide));
    }
    public boolean isDisplayMassageFilterNotFound(String textFilter) {
        return isElementDisplay(locatorFilterNotFound(textFilter));
    }

    public boolean isDisplayTextInLabel(String nameLabel, String text) {
        String value = getTextInLabel(nameLabel);
        return value.contains(text);
    }
}
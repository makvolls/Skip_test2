package SKIP_API;

import Frontend.pages.base.BasePage;
import Frontend.providers.Providers;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static Frontend.pages.constructorGo.ConstructorGoXpath.MENU;
import static Frontend.pages.constructorGo.ConstructorGoXpath.SHIELD;
import static Frontend.providers.Providers.*;

public class SKIP_108 {

    private WebDriver driver;
    private BasePage basePage;
    private Frontend.providers.Providers providers;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        basePage = new BasePage(driver);
        providers = new Providers(driver);
        basePage.setup();
        providers.setup();
    }
  //  @AfterTest
   // public void tearDown() {
   //    basePage.tearDown();
  //  }

    @Test
    public void step01() {

        basePage.authorization();
        basePage.openMainPage();
        basePage.waitElementDisplay(MENU);
        basePage.click(MENU);
        basePage.waitElementDisplay(SHIELD);
        basePage.click(SHIELD);
        basePage.waitElementDisplay(ACCESS_RIGHTS);
        basePage.moveToElement(driver, ACCESS_RIGHTS);
        basePage.waitElementDisplay(PROVIDERS_PAGE);
        basePage.click(PROVIDERS_PAGE);
        basePage.waitElementDisplay(LIST_PROVIDERS_FORM);
        basePage.waitElementDisplay(PEN_PICTOGRAM);
        basePage.click(PEN_PICTOGRAM);
        basePage.waitElementDisplay(BLOCK_PUT_PROVIDER);
        WebElement put_provider = basePage.findElement(BLOCK_PUT_PROVIDER);
        WebElement subblock = basePage.findElement(SUBBLOCK_INFO_PROVIDER);
        WebElement checkbox = basePage.findElement(CHECKBOX_TRAINING_PROVIDER);
        String checkedValue = checkbox.getAttribute("class");
        WebElement code = basePage.findElement(CODE_PROVIDER);
        WebElement checkbox_used = basePage.findElement(CHECKBOX_USED);
        WebElement checkbox_archival = basePage.findElement(CHECKBOX_ARCHIVAL);
        WebElement name_provider = basePage.findElement(NAME_PROVIDER_PUT);
        WebElement headquarters = basePage.findElement(HEADQUARTERS);
        WebElement included = basePage.findElement(INCLUDED_IN_THE_STRUCTURE);
        WebElement save = basePage.findElement(SAVE);
        WebElement close = basePage.findElement(CLOSE);
        WebElement provider_includes = basePage.findElement(PROVIDER_INCLUDES);
        WebElement provider_inc_org = basePage.findElement(PROVIDER_INCLUDES_ORGANIZATION);

        Assert.assertTrue(put_provider.getText().contains("Редактирование провайдера"));
        Assert.assertTrue(subblock.getText().equals("Информация о провайдере"));
        Assert.assertTrue(checkbox.isDisplayed() && checkbox.isEnabled());
        Assert.assertTrue(!checkbox.isSelected());
        Assert.assertTrue(checkedValue.contains("el-checkbox el-checkbox--large"));
        Assert.assertTrue(code.getText().equals("Код провайдера"));
        Assert.assertTrue(checkbox_used.getText().contains("Используется"));
        Assert.assertTrue(checkbox_archival.getText().contains("Архивный"));
        Assert.assertTrue(name_provider.getText().contains("Наименование провайдера"));
        Assert.assertTrue(headquarters.getText().contains("Головное подразделение"));
        Assert.assertTrue(included.getText().contains("Входит в структуру (тер. орган)"));
        Assert.assertTrue(save.isDisplayed() && save.isEnabled() && save.getText().contains("Сохранить"));
        Assert.assertTrue(close.isDisplayed() && close.isEnabled() && close.getText().contains("Закрыть"));
        Assert.assertTrue(provider_includes.getText().contains("Провайдер включает в себя следующие субъекты контроля:"));
        Assert.assertTrue(provider_inc_org.getText().contains("Провайдер включает в себя следующие организации:"));

    }

    @Test
    public void step02() {
        basePage.click(CHECKBOX_TRAINING_PROVIDER);
        WebElement checkbox = basePage.findElement(CHECKBOX_TRAINING_PROVIDER);
        String checkedValue = checkbox.getAttribute("class");
        Assert.assertTrue(checkedValue.contains("el-checkbox el-checkbox--large is-checked"));
    }

    @Test
    public void step03() {

        basePage.click(SAVE);
        basePage.waitElementDisplay(CHANGES_SAVED);
        WebElement element = basePage.findElement(CHANGES_SAVED);
        Assert.assertTrue(element.getText().contains("Изменения сохранены"));
    }

    @Test
    public void step04() {
        basePage.click(CHECKBOX_TRAINING_PROVIDER);
        basePage.click(SAVE);
        basePage.waitElementDisplay(CHANGES_SAVED);
        WebElement element = basePage.findElement(CHANGES_SAVED);
        WebElement checkbox = basePage.findElement(CHECKBOX_TRAINING_PROVIDER);
        String checkedValue = checkbox.getAttribute("class");
        Assert.assertTrue(element.getText().contains("Изменения сохранены"));
        Assert.assertTrue(checkedValue.contains("el-checkbox el-checkbox--large"));

    }

    @Test
    public void step05() {
        basePage.click(CHECKBOX_ARCHIVAL);
        basePage.click(CHECKBOX_USED);
        WebElement checkbox1 = basePage.findElement(CHECKBOX_ARCHIVAL);
        WebElement checkbox2 = basePage.findElement(CHECKBOX_USED);
        Assert.assertTrue(checkbox1.getAttribute("class").contains("el-checkbox el-checkbox--large is-disabled"));
        Assert.assertTrue(checkbox2.getAttribute("class").contains("el-checkbox el-checkbox--large is-disabled"));
    }

    @Test
    public void step06() {

        basePage.click(PUT_CONTROL_SUBJECT);
        basePage.waitElementDisplay(NAME_CS);
        WebElement name_cs = basePage.findElement(NAME_CS);
        WebElement block_cs = basePage.findElement(BLOCK_CS);
        WebElement entry_field_name = basePage.findElement(ENTRY_FIELD_NAME);
        WebElement entry_field_no_fax = basePage.findElement(ENTRY_FIELD_NUMBER_FAX);
        WebElement button_post_cs = basePage.findElement(BUTTON_POST_CS);
        WebElement list_cs = basePage.findElement(LIST_CS);
        WebElement button_close = basePage.findElement(BUTTON_CLOSE);
        WebElement button_save = basePage.findElement(BUTTON_SAVE);
        WebElement number_fax=basePage.findElement(NUMBER_FAX);


        Assert.assertTrue(block_cs.getText().contains("Субъекты контроля"));
        Assert.assertTrue(name_cs.getText().contains("Наименование субъекта контроля"));
        Assert.assertTrue(entry_field_name.isDisplayed());
        Assert.assertTrue(entry_field_no_fax.isDisplayed());
        Assert.assertTrue(button_post_cs.isDisplayed() && button_post_cs.isEnabled());
        Assert.assertTrue(list_cs.getText().contains("Список субъектов контроля"));
        Assert.assertTrue(button_close.isDisplayed() && button_close.isEnabled() && button_close.getText().contains("Закрыть"));
        Assert.assertTrue(button_save.isDisplayed() && button_save.isEnabled() && button_save.getText().contains("Сохранить"));
        Assert.assertTrue(number_fax.getText().contains("Номер факса"));

    }

    @Test
    public void step07() {

        basePage.click(BUTTON_CLOSE);

        WebElement put_provider = basePage.findElement(BLOCK_PUT_PROVIDER);
        WebElement subblock = basePage.findElement(SUBBLOCK_INFO_PROVIDER);
        WebElement checkbox = basePage.findElement(CHECKBOX_TRAINING_PROVIDER);
        String checkedValue = checkbox.getAttribute("class");
        WebElement code = basePage.findElement(CODE_PROVIDER);
        WebElement checkbox_used = basePage.findElement(CHECKBOX_USED);
        WebElement checkbox_archival = basePage.findElement(CHECKBOX_ARCHIVAL);
        WebElement name_provider = basePage.findElement(NAME_PROVIDER_PUT);
        WebElement headquarters = basePage.findElement(HEADQUARTERS);
        WebElement included = basePage.findElement(INCLUDED_IN_THE_STRUCTURE);
        WebElement save = basePage.findElement(SAVE);
        WebElement close = basePage.findElement(CLOSE);
        WebElement provider_includes = basePage.findElement(PROVIDER_INCLUDES);
        WebElement provider_inc_org = basePage.findElement(PROVIDER_INCLUDES_ORGANIZATION);

        Assert.assertTrue(put_provider.getText().contains("Редактирование провайдера"));
        Assert.assertTrue(subblock.getText().equals("Информация о провайдере"));
        Assert.assertTrue(checkbox.isDisplayed() && checkbox.isEnabled());
        Assert.assertTrue(!checkbox.isSelected());
        Assert.assertTrue(checkedValue.contains("el-checkbox el-checkbox--large"));
        Assert.assertTrue(code.getText().equals("Код провайдера"));
        Assert.assertTrue(checkbox_used.getText().contains("Используется"));
        Assert.assertTrue(checkbox_archival.getText().contains("Архивный"));
        Assert.assertTrue(name_provider.getText().contains("Наименование провайдера"));
        Assert.assertTrue(headquarters.getText().contains("Головное подразделение"));
        Assert.assertTrue(included.getText().contains("Входит в структуру (тер. орган)"));
        Assert.assertTrue(save.isDisplayed() && save.isEnabled() && save.getText().contains("Сохранить"));
        Assert.assertTrue(close.isDisplayed() && close.isEnabled() && close.getText().contains("Закрыть"));
        Assert.assertTrue(provider_includes.getText().contains("Провайдер включает в себя следующие субъекты контроля:"));
        Assert.assertTrue(provider_inc_org.getText().contains("Провайдер включает в себя следующие организации:"));
    }

    @Test
    public void step08(){
        basePage.click(CLOSE);
        basePage.waitElementDisplay(LIST_PROVIDERS_FORM);
        WebElement block = basePage.findElement(LIST_PROVIDERS_FORM);

        Assert.assertTrue(block.getText().contains("Список провайдеров"));
    }

}

package SKIP_API;

import Frontend.pages.base.BasePage;
import Frontend.pages.constructorGo.ConstructorGoXpath;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

import static Frontend.pages.constructorGo.ConstructorGoXpath.*;

public class SKIP_204 {

    private WebDriver driver;
    private BasePage basePage;
    private ConstructorGoXpath constructorGoXpath;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        basePage = new BasePage(driver);
        constructorGoXpath = new ConstructorGoXpath(driver);
        basePage.setup();
    }

    @AfterTest
    public void tearDown() {
        basePage.tearDown();
    }


    @Test
    public void step01() {

        basePage.authorization();
        basePage.openMainPage();
        basePage.waitElementDisplay(MENU);
        basePage.click(MENU);
        basePage.waitElementDisplay(SHIELD);
        basePage.click(SHIELD);
        basePage.waitElementDisplay(CONSTRUCTOR);
        basePage.click(CONSTRUCTOR);
        basePage.waitElementDisplay(BLOCK);
        WebElement block = driver.findElement(By.xpath(BLOCK));

        Assert.assertTrue(block.getText().contains("Табличные формы главного окна"));
        WebElement subblock = driver.findElement(By.xpath(SUBBLOCK));
        Assert.assertTrue(subblock.getText().contains("Список форм"));
        WebElement list = driver.findElement(By.xpath(LIST));
        Assert.assertTrue(list.getText().contains("Всего:"));
        WebElement create = driver.findElement(By.xpath(CREATE));
        Assert.assertTrue(create.getText().contains("Создать"));
        WebElement date = driver.findElement(By.xpath(DATE));
        String dateText = date.getText();
        String pattern = "\\d{2}\\.\\d{2}\\.\\d{4} \\d{2}:\\d{2}:\\d{2}";
        Assert.assertTrue(Pattern.matches(pattern, dateText));
        WebElement name = driver.findElement(By.xpath(NAME));
        Assert.assertNotNull(name);
        WebElement glass = driver.findElement(By.xpath(EYE));
        Assert.assertNotNull(glass);
        WebElement pen = driver.findElement(By.xpath(PEN));
        Assert.assertNotNull(pen);
        WebElement urn = driver.findElement(By.xpath(URN));
        Assert.assertNotNull(urn);


    }

    @Test

    public void step02() {

        basePage.moveToElement(driver, EYE);
        basePage.waitElementDisplay(TOOLTIP_EYE);
        WebElement tooltip = driver.findElement(By.xpath(TOOLTIP_EYE));
        String tooltipText = tooltip.getText();
        Assert.assertTrue(tooltipText.contains("Не отображать на главном окне"));
    }

    @Test

    public void step03() {
        basePage.moveToElement(driver, PEN);
        basePage.waitElementDisplay(TOOLTIP_PEN);

        WebElement tooltip = driver.findElement(By.xpath(TOOLTIP_PEN));
        String tooltipText = tooltip.getText();

        Assert.assertTrue(tooltipText.contains("Редактировать"));
    }

    @Test

    public void step04() {
        basePage.moveToElement(driver, URN);
        basePage.waitElementDisplay(TOOLTIP_URN);

        WebElement tooltip = driver.findElement(By.xpath(TOOLTIP_URN));
        String tooltipText = tooltip.getText();

        Assert.assertTrue(tooltipText.contains("Удалить"));
    }


}



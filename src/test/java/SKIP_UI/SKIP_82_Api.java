package SKIP_UI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SKIP_82_Api {
    WebDriver driver;
    Actions actions;
    String sudisAuthorization = "http://idp.int.sudis.at-consulting.ru/idp/account/";
    String authorizationIdOne = "http://skip.rtech.ru/sign-in/1";
    String providersList = "http://skip.rtech.ru/administration/permissions/providers";
    String login = "authorization_test";
    String password = "crjhjcnm";

    @BeforeTest
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "src/main/java/drivers/chrome/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        actions = new Actions(driver);
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void step01(){
        driver.get(sudisAuthorization);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement singInButton = driver.findElement(By.
                xpath("//button[@class=\"form-button form-button_last form-button_minor\"]"));
        singInButton.click();
        WebElement loginField = driver.findElement(By.
                xpath("//input[@id=\"login\"]"));
        loginField.sendKeys(login);
        WebElement passwordField = driver.findElement(By.
                xpath("//input[@id=\"password\"]"));
        passwordField.sendKeys(password);
        WebElement signInAnotherPageButton = driver.findElement(By.
                xpath("//button[@type=\"submit\"]"));
        signInAnotherPageButton.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.get(authorizationIdOne);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.get(providersList);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement titlePage = driver.findElement(By.
                xpath("(//h2[contains(text(),'Список провайдеров')])[1]"));
        WebElement titleTable = driver.findElement(By.
                xpath("(//h2[contains(text(),'Список провайдеров')])[2]"));
        WebElement fieldForSearch = driver.findElement(By.
                xpath("//div[@class=\"el-form-item__label\"]"));
        WebElement buttonSearch = driver.findElement(By.
                xpath("//div[@class='el-card is-never-shadow v-space-card']//button[1]//span[1]"));
        WebElement buttonClear = driver.findElement(By.
                xpath("//div[@class='el-card is-never-shadow v-space-card']//button[2]//span[1]"));

        String titlePageText = titlePage.getText();
        String titleTableText = titleTable.getText();
        String fieldForSearchText = fieldForSearch.getText();
        String buttonSearchText = buttonSearch.getText();
        String  buttonClearText = buttonClear.getText();

        System.out.println("Title page - " + titlePageText);
        System.out.println("Title table - " + titleTableText);
        System.out.println("Field for search - " + fieldForSearchText);
        System.out.println("Button 'Search' - " + buttonSearchText);
        System.out.println("Button 'Clear' - " + buttonClearText);
    }

    @Test
    public void step02(){
        WebElement fieldForSearch = driver.findElement(By.
                xpath("//span[@class='el-select-v2__placeholder is-transparent']"));
        fieldForSearch.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement listFirstElement = driver.findElement(By.
                xpath("//li[@class=\"el-select-dropdown__option-item\"][1]//span[1]"));
        String listFirstElementText = listFirstElement.getText();
        System.out.println("First element value - " + listFirstElementText);
        Assert.assertTrue(listFirstElementText.equals("Департамент делопроизводства и работы с " +
                "обращениями граждан и организаций Министерства внутренних дел Российской Федерации (ДДО МВД России)"));
    }

    @Test
    public void step03(){
        WebElement listFirstElement = driver.findElement(By.
                xpath("//li[@class=\"el-select-dropdown__option-item\"][1]//span[1]"));
        listFirstElement.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement buttonSearch = driver.findElement(By.
                xpath("//div[@class='el-card is-never-shadow v-space-card']//button[1]//span[1]"));
        buttonSearch.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement elementInSearchTable = driver.findElement(By.
                xpath("//div[contains(text(),'Департамент делопроизводства и работы с обращениям')]"));
        String elementInSearchTableText = elementInSearchTable.getText();
        System.out.println("Found element name - " + elementInSearchTableText);
        Assert.assertTrue(elementInSearchTableText.equals("Департамент делопроизводства и работы с " +
                "обращениями граждан и организаций Министерства внутренних дел Российской Федерации (ДДО МВД России)"));
    }

    @Test
    public void step04(){
        WebElement buttonClear = driver.findElement(By.
                xpath("//div[@class='el-card is-never-shadow v-space-card']//button[2]//span[1]"));
        buttonClear.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement fieldForSearch = driver.findElement(By.
                xpath("//span[@class='el-select-v2__placeholder is-transparent']"));
        String fieldForSearchPlaceholder = fieldForSearch.getText();
        System.out.println("Text in search field - " + fieldForSearchPlaceholder);
        WebElement countOfFoundElements = driver.findElement(By.
                xpath("//p[@class=\"info\"]"));
        String providersCount = countOfFoundElements.getText();
        System.out.println("Provider's elements pn the page : " + providersCount);
        Assert.assertTrue(fieldForSearchPlaceholder.equals("Введите текст"));
    }

    @Test
    public void step05(){
        WebElement fieldForSearch = driver.findElement(By.
                xpath("//input[@class=\"el-select-v2__combobox-input\"]"));
        fieldForSearch.sendKeys("отдел");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement listFirstElement = driver.findElement(By.
                xpath("//li[@class=\"el-select-dropdown__option-item\"][1]//span[1]"));
        String listFirstElementText = listFirstElement.getText();
        System.out.println("First element name - " + listFirstElementText);
        Assert.assertTrue(listFirstElementText.contains("Отдел"));
    }

    @Test
    public void step06(){
        WebElement emptyForm = driver.findElement(By.
                xpath("//div[@class=\"base-content\"]"));
        actions.moveToElement(emptyForm).click().build().perform();
        WebElement buttonClear = driver.findElement(By.
                xpath("//div[@class='el-card is-never-shadow v-space-card']//button[2]//span[1]"));
        buttonClear.click();
        WebElement fieldForSearch = driver.findElement(By.
                xpath("//input[@class=\"el-select-v2__combobox-input\"]"));
        fieldForSearch.sendKeys("ОТДЕЛ");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement listFirstElement = driver.findElement(By.
                xpath("//li[@class=\"el-select-dropdown__option-item\"][1]//span[1]"));
        String listFirstElementText = listFirstElement.getText();
        System.out.println("First element name - " + listFirstElementText);
        Assert.assertTrue(listFirstElementText.contains("Отдел"));
    }

    @Test
    public void step07(){
        WebElement emptyForm = driver.findElement(By.
                xpath("//div[@class=\"base-content\"]"));
        actions.moveToElement(emptyForm).click().build().perform();
        WebElement buttonClear = driver.findElement(By.
                xpath("//div[@class='el-card is-never-shadow v-space-card']//button[2]//span[1]"));
        buttonClear.click();
        WebElement fieldForSearch = driver.findElement(By.
                xpath("//input[@class=\"el-select-v2__combobox-input\"]"));
        fieldForSearch.sendKeys("а");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement listFirstElement = driver.findElement(By.
                xpath("//li[@class=\"el-select-dropdown__option-item\"][1]//span[1]"));
        String listFirstElementText = listFirstElement.getText();
        System.out.println("First element name - " + listFirstElementText);
        Assert.assertTrue(listFirstElementText.contains("а"));
    }

    @Test
    public void step08(){
        WebElement fieldForSearch = driver.findElement(By.
                xpath("//input[@class=\"el-select-v2__combobox-input\"]"));
        fieldForSearch.sendKeys("параправв");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement emptyElementsMessageWindow = driver.findElement(By.
                xpath("//p[@class=\"el-select-v2__empty\"]"));
        String emptyElementsMessageWindowText = emptyElementsMessageWindow.getText();
        Assert.assertTrue(emptyElementsMessageWindowText.
                equals("Данных, удовлетворяющих условиям фильтра, не найдено"));
    }

    @Test
    public void step09(){
        WebElement emptyForm = driver.findElement(By.
                xpath("//div[@class=\"base-content\"]"));
        actions.moveToElement(emptyForm).click().build().perform();
        WebElement buttonClear = driver.findElement(By.
                xpath("//div[@class='el-card is-never-shadow v-space-card']//button[2]//span[1]"));
        buttonClear.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement fieldForSearch = driver.findElement(By.
                xpath("//span[@class='el-select-v2__placeholder is-transparent']"));
        fieldForSearch.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement listThirdElement = driver.findElement(By.
                xpath("//li[@class=\"el-select-dropdown__option-item\"][3]//span[1]"));
        listThirdElement.click();
        String listThirdElementText = listThirdElement.getText();
        System.out.println("Third element in the list - " + listThirdElementText);
        WebElement fieldForSearchInput = driver.findElement(By.
                xpath("//input[@class=\"el-select-v2__combobox-input\"]"));
        fieldForSearchInput.sendKeys("отдел");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement providerTitle = driver.findElement(By.
                xpath("//div[@class=\"el-form-item__label\"]"));
        actions.moveToElement(providerTitle).click().build().perform();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String currentProvider = driver.findElement(By.
                xpath("//span[@class=\"el-select-v2__placeholder\"]")).getText();
        System.out.println("Current provider - " + currentProvider);
        Assert.assertTrue(currentProvider.equals(listThirdElementText));
    }
}

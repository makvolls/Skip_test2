package SKIP_API;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.Set;

public class SKIP_30 {
    WebDriver driver; // Initialize driver
    JavascriptExecutor js; // Initialize Javascript executor for switching between tabs
    String firstTabHandle; // Descriptor for first tab
    String secondTabHandle; // Descriptor for second tab
    String thirdTabHandle; // Descriptor for third tab
    String fourthTabHandle; // Descriptor for fourth tab
    // Links for test:
    String sudisAuthorization = "http://idp.int.sudis.at-consulting.ru/idp/account/"; // First link
    String authorizationCheck = "http://api.skip.rtech.ru/v1/permissions/users/profile"; // Second link
    String skipAuthorization = "http://api.skip.rtech.ru/v1/auth/sign_in"; // Third link
    String logOut = "http://api.skip.rtech.ru/v1/auth/sign_out"; // Fourth link
    String withoutSudisAuthorization = "http://api.skip.rtech.ru/v1/auth/sign_in?user_id=1"; // Fifth link
    // Authorization parameters:
    String login = "authorization_test";
    String password = "crjhjcnm";
    @BeforeTest
    void setup() {
        // Get chromedriver actual version and setup it
        //WebDriverManager.chromedriver().setup();
        // or can get from drivers.chrome, version = 121
        System.setProperty("webdriver.chrome.driver", "src/main/java/drivers/chrome/chromedriver.exe");
        // Create new chrome driver
        driver = new ChromeDriver();
        // Set full screen window
        driver.manage().window().maximize();
        // Assign value to js executor
        js = (JavascriptExecutor) driver;
    }

    @AfterTest
    void tearDown() {
        // Close browser window
        driver.quit();
    }


    @Test
    void step01() {
        // Open page
        driver.get(sudisAuthorization);
        // Get the current window handle
        firstTabHandle = driver.getWindowHandle();
        // Wait 5 sec
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement singInButton = driver.findElement(By.
                xpath("//button[@class=\"form-button form-button_last form-button_minor\"]"));
        singInButton.click();
        // Find login and password elements and send values to it
        WebElement loginField = driver.findElement(By.
                xpath("//input[@id=\"login\"]"));
        loginField.sendKeys(login);
        WebElement passwordField = driver.findElement(By.
                xpath("//input[@id=\"password\"]"));
        passwordField.sendKeys(password);
        WebElement signInAnotherPageButton = driver.findElement(By.
                xpath("//button[@type=\"submit\"]"));
        signInAnotherPageButton.click();
        // Wait 1 sec
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Get title of the page for check
        WebElement titlePage = driver.findElement(By.
                xpath("//div[@class=\"col-sm-6 b-title\"]"));
        String title = titlePage.getText();
        // Output title as a string representation to the console
        System.out.println("Title - " + title);
        // Assert title
        Assert.assertTrue(title.equals("ЛИЧНЫЙ КАБИНЕТ ПОЛЬЗОВАТЕЛЯ"));
    }

    @Test
    public void step02(){
        // open new window (tab)
        js.executeScript("window.open()");
        // get intel about all window handles
        Set<String> allWindowHandles = driver.getWindowHandles();
        // Loop through each window handle and switch to the new tab
        for (String handle : allWindowHandles) {
            if (!handle.equals(firstTabHandle)) {
                driver.switchTo().window(handle);
            }
        }
        // open second link in the new window (tab)
        driver.get(authorizationCheck);
        secondTabHandle = driver.getWindowHandle();
        // Wait 1 sec
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement json = driver.findElement(By.
                xpath("//pre"));
        String jsonValue = json.getText();
        System.out.println("Value - " + jsonValue);
        Assert.assertTrue(jsonValue.contains("Unauthorized"));
    }

    @Test
    public void step03(){
        js.executeScript("window.open()");
        Set<String> allWindowHandles = driver.getWindowHandles();
        // Loop through each window handle and switch to the new tab
        for (String handle : allWindowHandles) {
            if (!handle.equals(firstTabHandle) && !handle.equals(secondTabHandle)) {
                driver.switchTo().window(handle);
            }
        }
        driver.get(skipAuthorization);
        thirdTabHandle = driver.getWindowHandle();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement quitButton = driver.findElement(By.
                xpath("//section[@class=\"header__container_navigation\"]/div[4]"));
        quitButton.click();
        driver.get(skipAuthorization);
        try {
            Thread.sleep(7000);
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
        // Wait 1 sec
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement message = driver.findElement(By.
                xpath("//span[@class=\"el-table__empty-text\"]"));
        String textOnPage = message.getText();
        System.out.println("Message - " + textOnPage);
        Assert.assertTrue(textOnPage.equals("Данных, удовлетворяющих условиям фильтра, не найдено"));
    }

    @Test
    public void step04(){
        driver.switchTo().window(secondTabHandle);
        driver.get(authorizationCheck);
        // Wait 1 sec
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement json = driver.findElement(By.
                xpath("//pre"));
        String jsonValue = json.getText();
        System.out.println("Value - " + jsonValue);
        Assert.assertTrue(jsonValue.contains("Проверка Авторизации СУДИС"));
    }

    @Test
    public void step05(){
        driver.switchTo().window(thirdTabHandle);
        driver.get(logOut);
        // Wait 20 sec
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement titlePage = driver.findElement(By.
                xpath("//div[@class=\"form__row__col form__row__col_caption " +
                        "auth-form__title js-form-toggler\"]"));
        String titleText = titlePage.getText();
        System.out.println("Title page name - " + titleText);
        Assert.assertTrue(titleText.equals("АВТОРИЗАЦИЯ"));
    }

    @Test
    public void step06(){
        js.executeScript("window.open()");
        Set<String> allWindowHandles = driver.getWindowHandles();
        // Loop through each window handle and switch to the new tab
        for (String handle : allWindowHandles) {
            if (!handle.equals(firstTabHandle) && !handle.equals(secondTabHandle) && !handle.equals(thirdTabHandle)) {
                driver.switchTo().window(handle);
            }
        }
        driver.get(skipAuthorization);
        fourthTabHandle = driver.getWindowHandle();
        try {
            Thread.sleep(7000);
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
        // Wait 1 sec
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement message = driver.findElement(By.
                xpath("//span[@class=\"el-table__empty-text\"]"));
        String textOnPage = message.getText();
        System.out.println("Message - " + textOnPage);
        Assert.assertTrue(textOnPage.equals("Данных, удовлетворяющих условиям фильтра, не найдено"));
    }

    @Test
    public void step07(){
        driver.switchTo().window(secondTabHandle);
        driver.get(authorizationCheck);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement json = driver.findElement(By.
                xpath("//pre"));
        String jsonValue = json.getText();
        System.out.println("Value - " + jsonValue);
        Assert.assertTrue(jsonValue.contains("Проверка Авторизации СУДИС"));
    }

    @Test
    public void step08(){
        driver.switchTo().window(firstTabHandle);
        driver.get(sudisAuthorization);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement titlePage = driver.findElement(By.
                xpath("//div[@class=\"col-sm-6 b-title\"]"));
        String title = titlePage.getText();
        System.out.println("Title - " + title);
        Assert.assertTrue(title.equals("ЛИЧНЫЙ КАБИНЕТ ПОЛЬЗОВАТЕЛЯ"));
    }

    @Test
    public void step09(){
        driver.switchTo().window(thirdTabHandle);
        driver.get(logOut);
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement titlePage = driver.findElement(By.
                xpath("//div[@class=\"form__row__col form__row__col_caption " +
                        "auth-form__title js-form-toggler\"]"));
        String titleText = titlePage.getText();
        System.out.println("Title page name - " + titleText);
        Assert.assertTrue(titleText.equals("АВТОРИЗАЦИЯ"));
    }

    @Test
    public void step10(){
        driver.switchTo().window(firstTabHandle);
        driver.get(sudisAuthorization);
        try {
            Thread.sleep(5000);
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
        WebElement titlePage = driver.findElement(By.
                xpath("//div[@class=\"col-sm-6 b-title\"]"));
        String title = titlePage.getText();
        System.out.println("Title - " + title);
        Assert.assertTrue(title.equals("ЛИЧНЫЙ КАБИНЕТ ПОЛЬЗОВАТЕЛЯ"));
    }

    @Test
    public void step11(){
        driver.switchTo().window(secondTabHandle);
        driver.get(authorizationCheck);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement json = driver.findElement(By.
                xpath("//pre"));
        String jsonValue = json.getText();
        System.out.println("Value - " + jsonValue);
        Assert.assertTrue(jsonValue.contains("Unauthorized"));
    }

    @Test
    public void step12(){
        driver.switchTo().window(thirdTabHandle);
        driver.get(withoutSudisAuthorization);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement pageTitle = driver.findElement(By.
                xpath("//*[@class=\"title\"]"));
        String titleText = pageTitle.getText();
        System.out.println("Title text - " + titleText);
        Assert.assertTrue(titleText.equals("Таблица документов, стоящих на контроле"));
    }

    @Test
    public void step13(){
        driver.switchTo().window(secondTabHandle);
        driver.get(authorizationCheck);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement json = driver.findElement(By.
                xpath("//pre"));
        String jsonValue = json.getText();
        System.out.println("Value - " + jsonValue);
        Assert.assertTrue(jsonValue.contains("Колокольцев Владимир Александрович"));
    }

    @Test
    public void step14(){
        driver.switchTo().window(thirdTabHandle);
        driver.get(logOut);
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        WebElement titlePage = driver.findElement(By.
//                xpath("//div[@class=\"form__row__col form__row__col_caption " +
//                        "auth-form__title js-form-toggler\"]"));
//        String titleText = titlePage.getText();
//        System.out.println("Title page name - " + titleText);
//        Assert.assertTrue(titleText.equals("АВТОРИЗАЦИЯ"));
        WebElement pageTitle = driver.findElement(By.
                xpath("//*[@class=\"title\"]"));
        String titleText = pageTitle.getText();
        System.out.println("Title text - " + titleText);
        Assert.assertTrue(titleText.equals("Таблица документов, стоящих на контроле"));
    }

    @Test
    public void step15(){
        driver.switchTo().window(secondTabHandle);
        driver.get(authorizationCheck);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement json = driver.findElement(By.
                xpath("//pre"));
        String jsonValue = json.getText();
        System.out.println("Value - " + jsonValue);
//        Assert.assertTrue(jsonValue.contains("Unauthorized"));
        Assert.assertTrue(jsonValue.contains("Проверка"));
    }

    @Test
    public void step16(){
        driver.switchTo().window(firstTabHandle);
        driver.get(sudisAuthorization);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        WebElement titlePage = driver.findElement(By.
//                xpath("//div[@class=\"form__row__col form__row__col_caption " +
//                        "auth-form__title js-form-toggler\"]"));
//        String titleText = titlePage.getText();
//        System.out.println("Title page name - " + titleText);
//        Assert.assertTrue(titleText.equals("АВТОРИЗАЦИЯ"));
        WebElement titlePage = driver.findElement(By.
                xpath("//div[@class=\"col-sm-6 b-title\"]"));
        String title = titlePage.getText();
        System.out.println("Title - " + title);
        Assert.assertTrue(title.equals("ЛИЧНЫЙ КАБИНЕТ ПОЛЬЗОВАТЕЛЯ"));
    }
}

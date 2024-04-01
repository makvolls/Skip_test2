package UI.pages.testlink;


import autotest.core.util.Assistant;
import UI.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.testng.AssertJUnit.assertTrue;

public class CreateStepTitleFromLinkCase {


    String testCaseNumber = "108"; // задаем номер кейса

    String fileName = "C:\\test\\" + testCaseNumber + ".txt"; // путь и имя файла, где будет лежать результат

    String initials = "АС";

    String nameProject = "SKIP";

    WebDriver driver = DriverManager.createDriver();

    @AfterClass
    public void afterClass() {
        driver.quit();  // закрываем браузер
    }


    public static void write(String fileName, String text) {
        //Определяем файл
        File file = new File(fileName);

        try {
            //проверяем, что если файл не существует то создаем его
            if (!file.exists()) {
                file.createNewFile();
            }

            //PrintWriter обеспечит возможности записи в файл
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            try {
                //Записываем текст в файл
                out.print(text);
            } finally {
                //После чего мы должны закрыть файл
                //Иначе файл не запишется
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String[] getValueProgect(By path) {
        List<WebElement> elemList = driver.findElements(path);
        String[] arr = null;
        arr = new String[elemList.size()];

        WebElement element;
        int i;
        for (Iterator i$ = elemList.iterator(); i$.hasNext(); arr[i] = element.getText()) {
            element = (WebElement) i$.next();
            i = elemList.indexOf(element);
        }
        return arr;
    }

    public void clickNameProject(int number) {
        driver.findElement(By.xpath("(//select[@name='testproject']//option)[" + number + "]")).click();
    }

    @Test(enabled = true, description = "")
    public void createSteps() {
        try {
            int temp = 1;
            driver.manage().window().maximize();
            driver.get("http://172.24.160.223/testlink/index.php");
            driver.findElement(By.xpath(".//*[@id='login']")).sendKeys("omakartsov");
            driver.findElement(By.xpath(".//input[@name = 'tl_password']")).sendKeys("123456");
            driver.findElement(By.xpath("//input[@class ='big_button']")).click();

            driver.switchTo().frame((WebElement) driver.findElement(By.name("titlebar")));
            String[] name = getValueProgect(By.xpath("//select[@name='testproject']//option"));
            int size = name.length;
            for (int i = 0; i < size; i++) {
                if (name[i].contains(nameProject)) {
                    temp = i + 1;
                    break;
                }
            }
            clickNameProject(temp);
            driver.findElement(By.xpath(".//*[@id='searchTC']/input[3]")).sendKeys(testCaseNumber);
            driver.findElement(By.xpath(".//*[@id='searchTC']/img")).click();
            sleep(5000);
            driver.switchTo().defaultContent();
            driver.switchTo().frame((WebElement) driver.findElement(By.name("mainframe")));
            List<WebElement> steps = driver.findElements(By.xpath(".//*[contains(@id,'step_row')]//td[2]"));
            List<WebElement> expected = driver.findElements(By.xpath(".//*[contains(@id,'step_row')]//td[3]"));
            if (nameProject.contains("SKIP")) {
                nameProject = "SKIP";
            }

            String allCases = "\n";
            allCases = allCases + "package SKIP_UI;\n\n";
            allCases = allCases + "import SKIP_UI.base.BaseTest;\n";
          //  allCases = allCases + "import UI.constants.Users;\n";
          //  allCases = allCases + "import UI.pages.base.BasePage;\n\n";
          //  allCases = allCases + "import UI.pages.listproviders.ListProvidersPage;\n";
          //  allCases = allCases + "import UI.pages.main.MainPage;\n";
            allCases = allCases + "import org.testng.annotations.AfterClass;\n";
            allCases = allCases + "import org.testng.annotations.BeforeClass;\n";
            allCases = allCases + "import org.testng.annotations.Test;\n";
          //  allCases = allCases + "import core.util.Browser;\n\n";


            allCases = allCases + "public class " + nameProject + "_" + testCaseNumber + " extends BaseTest {\n\n";
            allCases = allCases + "@AfterClass\n";
            allCases = allCases + "public void afterClass() {\n";
            allCases = allCases + "driver.quit(); // закрываем браузер\n";
//            allCases = allCases + "tearDown(b); // закрываем браузер\n";
            allCases = allCases + "}\n\n\n";

            allCases = allCases + "@BeforeClass\n";
            allCases = allCases + "public void beforeClass() {\n";
//            allCases = allCases + "b=initBrowser();//инициализируем браузер\n";
//            allCases = allCases + "b.openHost();//открытие главной страницы\n";
//            allCases = allCases + "page = new BasePage(b);\n";
            allCases = allCases + "}\n\n\n";


            String stepNumber = "";
            String endLine = "\" \n + \"";
            int k;
            for (int i = 0; i < steps.size(); i++) {
                k = i + 1;
                if (k > 9) {
                    stepNumber = Integer.toString(k);
                } else {
                    stepNumber = "0" + Integer.toString(k);
                }
                allCases = allCases + "@Test(enabled = true, description = \"[GISMU-" + testCaseNumber + " Step " + k + " " + initials + "] Шаги:\"\n";
                String text = steps.get(i).getText().trim().replace("\"", "'");
                text = text.replace("\n", endLine);
                allCases = allCases + "+ \"" + text + endLine + "Ожидаемая реакция:" + endLine;
                text = expected.get(i).getText().trim().replace("\"", "'");
                text = text.replace("\n", endLine);
                allCases = allCases + text + "\")" + "\n";
                allCases = allCases + "public void GISMU_" + testCaseNumber + "_step_" + stepNumber + "() {\n";
                allCases = allCases + "try {\n";
                allCases = allCases + "\n\n\n";
                allCases = allCases + "} catch (Exception e) {\n";
                allCases = allCases + "logFailure(e);\n}\n}\n\n";
            }
            allCases = allCases + "}";

            // запись в файл
            write(fileName, allCases);

        } catch (Exception e) {
            Assistant.logException(e);  //записываем в лог информацию по исключению
            assertTrue(false);//снимаем скриншот с браузера и выходим
        }
    }
}





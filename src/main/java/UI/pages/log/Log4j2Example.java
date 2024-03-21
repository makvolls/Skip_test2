package UI.pages.log;


import UI.pages.base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;


public class Log4j2Example extends BasePage {

    private static final Logger logger = LogManager.getLogger();

    public Log4j2Example(WebDriver driver) {
        super(driver);
    }

    public static void logger(Throwable e) {

        logger.fatal("Вызвано исключение", e);
        try {
            logger.error("Информация об ошибке");
        } catch (Exception var4) {
            logger.error("Произошла ошибка при попытке сделать снимок страницы браузера.");
        }
        throw new RuntimeException(e);
    }

}
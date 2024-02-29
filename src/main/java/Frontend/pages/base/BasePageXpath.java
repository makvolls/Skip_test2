package Frontend.pages.base;

import static Frontend.constants.Constant.TimeoutVariable.*;
import static Frontend.constants.Constant.URLS.*;

public class BasePageXpath {

    public static class XPATH {
        // For authorization:
        public static final String BUTTON_SIGN_IN_WITH_PASSWORD = "//button[@class=\"form-button " +
                "form-button_last form-button_minor\"]";
        public static final String INPUT_FIELD_LOGIN = "//input[@id=\"login\"]";
        public static final String INPUT_FIELD_PASSWORD = "//input[@id=\"password\"]";
        public static final String BUTTON_SIGN_IN = "//button[@type=\"submit\"]";
        public static final String TITLE_PAGE = "//div[@class=\"col-sm-6 b-title\"]";
    }

    public static class AuthorizationParameters {
        // For SUDIS page:
        public static final String SUDIS_LOGIN = "authorization_test";
        public static final String SUDIS_PASSWORD = "crjhjcnm";
    }
}

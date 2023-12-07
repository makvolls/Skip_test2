package SKIP_API;

import BasicPageTestApi.BasicApiTest;
import DTO.ThemesDto.RootElementThemes;
import DTO.ThemesDto.RootThemes;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ThemesRequests extends BasicApiTest {
    RootThemes actualThemesList;
    RootElementThemes createdValue;
    RootElementThemes changedValue;

    /**
     * GET request
     * **/

    @Test
    public void getActualThemesList(){
        actualThemesList = getThemesList(1);
        Assert.assertTrue(actualThemesList.data[0].name.contains("Тест-Здание-191")); // Can change
    }

    /**
     * POST request
     * **/

    @Test
    public void createThemesValue(){
        /**
         * In this step we create value with id 30
         * and name "Automation Test"
         * **/
        createdValue = createValueThemes(1,"Automation Test",true);
        actualThemesList = getThemesList(1);
        Assert.assertTrue(actualThemesList.data[0].name.contains("Automation Test"));
    }

    /**
     * PUT request
     * **/

    @Test
    public void changeThemesValue(){
        /**
         * In this step we change name value with id 30
         * to "New Automation Value" and change state
         * excluded to false
         * Then we get list with values and check
         * that element 0 has name "New Automation Value"
         * **/
        changedValue = changeThemesValue(1,30,"New Automation Value",false);
        actualThemesList = getThemesList(1);
        Assert.assertTrue(actualThemesList.data[0].name.contains("New Automation Value"));
    }

    /**
     * DELETE request
     * **/
    @Test
    public void deleteThemesValue(){
        /**
         * In this step we
         * delete value that we created
         * previously
         * **/
        deleteThemesValue(1,30);
    }
}

package SKIP_API;

import BasicPageTestApi.BasicApiTest;
import DTO.UrgenciesDTO.RootElementUrgencies;
import DTO.UrgenciesDTO.RootUrgencies;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetUrgencies extends BasicApiTest {
    RootUrgencies actualUrgenciesList;
    RootElementUrgencies createdValue;
    RootElementUrgencies changedValue;

    /**
     * GET request
     * **/
    @Test
    public void getActualUrgenciesList(){
        actualUrgenciesList = getUrgenciesList(1);
        Assert.assertTrue(actualUrgenciesList.data[2].name.contains("гмгнп"));// Can change
    }

    /**
     * POST request
     * **/
    @Test
    public void createUrgenciesValue(){
        createdValue = createValue(1,"AutomationTest",true);
        //Assert.assertTrue(createdValue.getData().name.equals("AutomationTest"));
        /**
         * In this step we create value with id 19
         * and name = "AutomationTest
         * "**/
    }

    /**
     * PUT request
     * **/
    @Test
    public void changeValue(){
        changedValue = changeValue(1,19,"New Test Automation", false);
        actualUrgenciesList = getUrgenciesList(1);
        //Assert.assertTrue(actualUrgenciesList.data[1].name.contains("New Test Automation"));
        /**
         * In this step we change name value with id 19
         * to "New Test Automation" and also
         * change state excluded to false.
         * Then get list with values , we should
         * get value with id 19 with name "New Test Automation"
         * **/
    }

    /**
     * DELETE request
     * **/
    @Test
    public void deleteValue(){
        deleteValue(1,19);
        /**
         * In this step we delete value that we created
         * previously with id 19
         * **/
    }

    /**
     * POST request (reorder)
     * **/

    @Test
    public void reorderValues(){
        /**
         * In this step we reorder o**/
    }
}

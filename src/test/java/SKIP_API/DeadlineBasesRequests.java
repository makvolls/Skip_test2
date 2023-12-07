package SKIP_API;

import BasicPageTestApi.BasicApiTest;
import DTO.DeadlineBasesDto.RootDeadlineBases;
import DTO.DeadlineBasesDto.RootElementDeadlineBases;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeadlineBasesRequests extends BasicApiTest {
    RootDeadlineBases actualDeadlineBasesList;
    RootElementDeadlineBases createdValue;
    RootElementDeadlineBases changedValue;

    /**
     * GET request
     * **/
    @Test
    public void getDeadlineBasesListTest(){
        actualDeadlineBasesList = getDeadlineBasesList(1);
        Assert.assertTrue(actualDeadlineBasesList.data[0].name.contains("Й"));
    }

    /**
     * POST request
     * **/
    @Test
    public void createDeadlineBasesValueTest(){
        /**
         * In this step we create value with id - 72
         * name - Test Automation
         * short_name - SHORT_Test
         * note - (empty string)
         * duration_id - 3_days
         * deadline_only - true
         * excluded - true
         * NOTICE!!! - In field duration_id we should choose ONLY (if we want positive plot) 3_days, 10_days, 30_days, 1_month
         * **/
        createdValue = createValueDeadlineBases(1,"Test Automation", "SHORT_Test","","3_days",true, true);
        actualDeadlineBasesList = getDeadlineBasesList(1);
        Assert.assertTrue(actualDeadlineBasesList.data[0].name.contains("Test Automation"));
    }

    /**
     * PUT request
     * **/

    @Test
    public void changeDeadlineBasesValueTest(){
        /**
         * In this step we change value with id -
         * to name -
         * excluded - false
         * Then we get list with values and check
         * that element 0 has name "New Automation Value"
         * "**/
        changedValue = changeDeadlineBasesValue(1,72,"New Automation Value","","","3_days", true, false);
        actualDeadlineBasesList = getDeadlineBasesList(1);
        Assert.assertTrue(actualDeadlineBasesList.data[0].name.contains("New Automation Value"));

    }

    /**
     * DELETE request
     * **/

    @Test
    public void deleteDeadlineBasesValue(){
        deleteDeadlineBasesValue(1,72);
    }


}

package SKIP_API;

import BasicPageTestApi.BasicApiTest;
import DTO.ImageMarksDto.RootElementImageMarks;
import DTO.ImageMarksDto.RootImageMarks;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ImageMarksRequests extends BasicApiTest {
    RootImageMarks actualImageMarksList;
    RootElementImageMarks createdValue;
    RootElementImageMarks changedValue;

    /**
     * GET request
     * **/
    @Test
    public void getImageMarksListTest(){
        actualImageMarksList = getImageMarksList(1);
        Assert.assertTrue(actualImageMarksList.data[0].name.contains("Test Auto"));
    }

    /**
     * POST request
     * **/
    @Test
    public void createImageMarksValue(){
        /**
         * In this step we create value with id 16
         * and name "Test Automation" , short_name - "testtest",
         * excluded - true
         * **/
        createdValue = createValueImageMarks(1,"Test Automation","testtest",true);
        actualImageMarksList = getImageMarksList(1);
        Assert.assertTrue(actualImageMarksList.data[0].name.contains("Test Automation"));
    }

    /**
     * PUT request
     * **/
    @Test
    public void changeImageMarksValue(){
        /**
         * In this step we change name value with id 16
         * to "New Automation Value" and change state
         * excluded to false and also change
         * short_name to "TESTTEST"
         * Then we get list with values and check
         * that element 0 has name "New Automation Value"
         * **/
        changedValue = changeImageMarksValue(1,16,"New Automation Value","TESTTEST",false);
        actualImageMarksList = getImageMarksList(1);
        Assert.assertTrue(actualImageMarksList.data[0].short_name.contains("TESTTEST"));
    }

    /**
     * DELETE request
     * **/
    @Test
    public void deleteImageMarksValueTest(){
        deleteImageMarksValue(1,16);
    }
}

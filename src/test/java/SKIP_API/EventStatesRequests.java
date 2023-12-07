package SKIP_API;

import BasicPageTestApi.BasicApiTest;
import DTO.EventStatesDto.RootElementEventStates;
import DTO.EventStatesDto.RootEventStates;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EventStatesRequests extends BasicApiTest {
    RootEventStates actualEventStatesList;
    RootElementEventStates createdValue;
    RootElementEventStates changedValue;


    /**
     * GET request
     * **/
    @Test
    public void getActualEventStatesList(){
        actualEventStatesList = getEventStatesList(1);
        Assert.assertTrue(actualEventStatesList.data[0].name.contains("тест232")); // Can change
    }

    /**
     * POST request
     * **/
    @Test
    public void createEventStatesValue(){
        /**
         * In this step we create value with id 32
         * and name "Test Automation
         * "**/
        createdValue = createValueEventStates(1,"Test Automation",true);
        actualEventStatesList = getEventStatesList(1);
        Assert.assertTrue(actualEventStatesList.data[0].name.contains("Test Automation"));
    }

    /**
     * PUT request
     * **/
    @Test
    public void changeEventStateValue(){
        /**
         * In this step we change name value with id 32
         * to "New Automation Value" and change state
         * excluded to false
         * Then we get list with values and check
         * that element 0 has name "New Automation Value"
         * **/
        changedValue = changeEventStatesValue(1,32,"New Automation Value", false);
        actualEventStatesList = getEventStatesList(1);
        Assert.assertTrue(actualEventStatesList.data[0].name.contains("New Automation Value"));
    }

    /**
     * DELETE request
     * **/
    @Test
    public void deleteEventStateValue(){
        /**
         * In this step we
         * delete value that we created
         * previously
         * **/
        deleteEventStateValue(1,32);
    }
}

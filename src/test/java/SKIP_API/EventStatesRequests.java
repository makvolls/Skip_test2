package SKIP_API;

import API.BasicApiOld;
import API.BasicEventStates;
import API.DTO.EventStatesDto.RootElementEventStates;
import API.DTO.EventStatesDto.RootEventStates;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EventStatesRequests extends BasicApiOld {
    RootEventStates actualEventStatesList;
    RootElementEventStates createdValue;
    RootElementEventStates changedValue;


    /**
     * GET request
     * **/
    @Test
    public void getActualEventStatesList(){
        BasicEventStates.getEventStatesList(1);
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
        BasicEventStates.createEventStates(1,"Test Automation22",true);
        BasicEventStates.getEventStatesList(1);
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
        BasicEventStates.updateEventStates(1,34,"New Automation Value1", false);
        BasicEventStates.getEventStatesList(1);
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
        BasicEventStates.deleteEventStateValue(1,34);
    }
}

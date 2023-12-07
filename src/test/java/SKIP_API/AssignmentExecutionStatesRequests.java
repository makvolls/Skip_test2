package SKIP_API;

import BasicPageTestApi.BasicApiTest;
import DTO.AssignmentExecutionStatesDto.RootAssignmentExecutionStates;
import DTO.AssignmentExecutionStatesDto.RootElementAssignmentExecutionStates;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AssignmentExecutionStatesRequests extends BasicApiTest {
    RootAssignmentExecutionStates actualAssignmentExecutionStates;
    RootElementAssignmentExecutionStates createdValue;
    RootElementAssignmentExecutionStates changedValue;

    /**
     * GET request
     * **/

    @Test
    public void getActualAssignmentExecutionStates(){
        actualAssignmentExecutionStates = getAssignmentExecutionStatesList(1);
        Assert.assertTrue(actualAssignmentExecutionStates.data[0].name.contains("123"));
    }

    /**
     * POST request
     * **/
    @Test
    public void createAssignmentExecutionStatesValue(){
        /**
         * In this step we create value with id 40
         * and name "Test Automation" , short_name - "Test",
         * excluded - true.
         * **/
        createdValue = createValueAssignmentExecutionStates(1,"Test Automation","Test",true);
        actualAssignmentExecutionStates = getAssignmentExecutionStatesList(1);
        Assert.assertTrue(actualAssignmentExecutionStates.data[0].short_name.contains("Test"));
    }

    /**
     * PUT request
     * **/
    @Test
    public void changeAssignmentExecutionStatesValue(){
        /**
         * In this step we change name value with id 40
         * to "New Automation Value" and change state
         * excluded to false and also change
         * short_name to "T"
         * Then we get list with values and check
         * that element 0 has name "New Automation Value"
         * **/
        changedValue = changeAssignmentExecutionStatesValue(1,40,"New Automation","T", false);
        actualAssignmentExecutionStates = getAssignmentExecutionStatesList(1);
        Assert.assertTrue(actualAssignmentExecutionStates.data[0].name.contains("New Automation"));
    }

    /**
     * DELETE request
     * **/
    @Test
    public void deleteAssignmentExecutionStatesValue(){
        deleteAssignmentExecutionStatesValue(1,40);
    }
}

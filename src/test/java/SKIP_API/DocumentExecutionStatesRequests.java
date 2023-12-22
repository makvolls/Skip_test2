package SKIP_API;

import API.BasicApiOld;
import API.BasicDocumentExecutionStates;
import API.DTO.DocumentExecutionStatesDto.RootDocumentExecutionStates;
import API.DTO.DocumentExecutionStatesDto.RootElementDocumentExecutionStates;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DocumentExecutionStatesRequests extends BasicApiOld {
    RootDocumentExecutionStates actualDocumentExecutionStatesList;
    RootElementDocumentExecutionStates createdValue;
    RootElementDocumentExecutionStates changedValue;

    /**
     * GET request
     * **/
    @Test
    public void getDocumentExecutionStatesListTest(){
        BasicDocumentExecutionStates.getDocumentExecutionStatesList(1);
        Assert.assertTrue(actualDocumentExecutionStatesList.data[0].name.contains("TESTTESTTEST"));
    }

    /**
     * POST request
     * **/
    @Test
    public void createDocumentExecutionStatesValue(){
        /**
         * In this step we create value with id 23
         * and name "Test Automation" , short_name - "test",
         * excluded - true
         * **/
        BasicDocumentExecutionStates.createDocumentExecutionStates(1,"Test Automation","test",true);
        BasicDocumentExecutionStates.getDocumentExecutionStatesList(1);
        Assert.assertTrue(actualDocumentExecutionStatesList.data[0].name.contains("Test Automation"));
    }

    /**
     * PUT request
     * **/
    @Test
    public void changeDocumentExecutionStatesValue(){
        /**
         * In this step we change name value with id 23
         * to "New Automation Value" and change state
         * excluded to false and also change
         * short_name to "T"
         * Then we get list with values and check
         * that element 0 has name "New Automation Value"
         * **/
        BasicDocumentExecutionStates.updateDocumentExecutionStatesValue(1,23,"New Automation Value","TTTT",false);
        BasicDocumentExecutionStates.getDocumentExecutionStatesList(1);
        Assert.assertTrue(actualDocumentExecutionStatesList.data[0].name.contains("New Automation Value"));
        Assert.assertTrue(actualDocumentExecutionStatesList.data[0].short_name.contains("TTTT"));
    }
    /**
     * DELETE request
     * **/
    @Test
    public void deleteDocumentExecutionStatesValueTest(){
        BasicDocumentExecutionStates.deleteDocumentExecutionStatesValue(1,23);
    }
}

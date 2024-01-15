package SKIP_API;

import API.BasicApi;
import API.BasicAssignmentExecutionStates;
import API.DTO.AssignmentExecutionStatesDto.CreateValueAssignmentExecutionStates;
import API.DTO.AssignmentExecutionStatesDto.CreateValueAssignmentExecutionStatesName;
import API.DTO.AssignmentExecutionStatesDto.RootAssignmentExecutionStates;
import API.DTO.AssignmentExecutionStatesDto.RootElementAssignmentExecutionStates;
import API.DTO.ErrorsDTO.RootErrorNameShortName;
import API.DTO.ErrorsDTO.RootErrorNoRights;
import API.DTO.ErrorsDTO.RootNameError;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class SKIP_44 {

    RootNameError valueErr,valueErr3;
    RootElementAssignmentExecutionStates newValue,newValue2;
    RootErrorNameShortName valueErr2;
    RootAssignmentExecutionStates listValue;
    RootErrorNoRights valueErr4;
    RootElementAssignmentExecutionStates newValue3;
    @AfterClass
            public void delete(){
        BasicAssignmentExecutionStates.deleteAssignmentExecutionStatesValue(1,newValue.data.id);
        BasicAssignmentExecutionStates.deleteAssignmentExecutionStatesValue(1,newValue2.data.id);
        BasicAssignmentExecutionStates.deleteAssignmentExecutionStatesValue(1,newValue3.data.id);

    }


    @Test
    public void step01(){
        valueErr= BasicAssignmentExecutionStates.createValueErrorNameAssignmentExecutionStates
                (1,"","",true);
        Assert.assertTrue(valueErr.errors.name[0].equals("не может быть пустым"));

    }

    @Test
    public void step02(){
        newValue=BasicAssignmentExecutionStates.createValueAssignmentExecutionStates
                (1,"v","",true);
        Assert.assertTrue(newValue.data.name.equals("v"));
        Assert.assertTrue(newValue.data.excluded);
    }

    @Test
    public void step03(){
        newValue2=BasicAssignmentExecutionStates.createValueAssignmentExecutionStates
                (1,"5","l",false);
        Assert.assertTrue(newValue2.data.name.equals("5"));
        Assert.assertTrue(newValue2.data.short_name.equals("l"));
        Assert.assertTrue(!newValue2.data.excluded);
    }

    @Test
    public void step04(){

     valueErr2= BasicAssignmentExecutionStates.createValueErrorNameShortNameAssignmentExecutionStates
             (1,"5","l",false);
     Assert.assertTrue(valueErr2.errors.name[0].equals("уже существует"));
     Assert.assertTrue(valueErr2.errors.short_name[0].equals("уже существует"));
    }

    @Test
    public void step05(){
        valueErr3=BasicAssignmentExecutionStates.createValueErrorNameAssignmentExecutionStates
                (1,"5","",false);
        Assert.assertTrue(valueErr3.errors.name[0].equals("уже существует"));
    }

    @Test
    public void step06(){
        listValue=BasicAssignmentExecutionStates.getAssignmentExecutionStatesList(1);
        Assert.assertEquals(1,listValue.data[0].priority);
        Assert.assertEquals("5",listValue.data[0].name);
    }
    @Test
    public void step07(){
        valueErr4=BasicAssignmentExecutionStates.createValueErrorNoRightsAssignmentExecutionStates
                (6,"5","",false);
        Assert.assertTrue(valueErr4.error.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void step08(){
        newValue3=BasicAssignmentExecutionStates.createValueAssignmentExecutionStatesName(1,"vd23svdjds");
        Assert.assertTrue(newValue3.data.name.equals("vd23svdjds"));
    }



}

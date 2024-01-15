package SKIP_API;

import API.BasicEventStates;
import API.DTO.ErrorsDTO.EventStateErrorsDto.RootEventStateErrorName;
import API.DTO.ErrorsDTO.RootErrorNoRights;
import API.DTO.EventStatesDto.RootElementEventStates;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class SKIP_22 {
RootElementEventStates createValue,createValue2;
RootEventStateErrorName createErrorValue,createErrorValue2;
RootErrorNoRights createErrorValue3;

@AfterClass
public void deleteValue(){
    BasicEventStates.deleteEventStateValue(1,createValue.getData().id);
    BasicEventStates.deleteEventStateValue(1,createValue2.getData().id);
}



    @Test
    public void step01(){
        createValue= BasicEventStates.createEventStates(1,"Кейc",true);
        Assert.assertTrue(createValue.data.name.equals("Кейc"));
        Assert.assertTrue(createValue.data.excluded);
    }

    @Test
    public void step02(){
    createErrorValue= BasicEventStates.createEsErrorName(1,"Кейc",true);
    Assert.assertTrue(createErrorValue.errors.name[0].equals("уже существует"));
    }

    @Test
    public void step03(){
    createErrorValue2=BasicEventStates.createEsErrorName(1,"",true);
    Assert.assertTrue(createErrorValue2.errors.name[0].equals("не может быть пустым"));
    }
    @Test
    public void step04(){
    createValue2=BasicEventStates.createEventStates(1,"z",false);
        Assert.assertTrue(createValue2.data.name.equals("z"));
        Assert.assertTrue(!createValue2.data.excluded);
    }
   @Test
    public void step05(){
    createErrorValue3=BasicEventStates.createEsErrorNoRights(6,"ТЕСТ",false);
    Assert.assertTrue(createErrorValue3.error.equals("Доступ к ресурсу запрещен"));
   }

}

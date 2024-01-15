package SKIP_API;

import API.BasicEvents;
import API.DTO.ErrorsDTO.EventsErrorsDto.RootEventStateError;
import API.DTO.ErrorsDTO.EventsErrorsDto.RootNoEventsStatesErrorDto;
import API.DTO.ErrorsDTO.RootErrorNameShortName;
import API.DTO.ErrorsDTO.RootErrorNoRights;
import API.DTO.ErrorsDTO.RootNameError;
import API.DTO.EventsDto.RootEventsDto;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class SKIP_31error {

    int id;
    RootEventsDto newValue,newValue2,newValue3,newValue4,updateValue;
    RootNameError createErrName,updateErrName;
    RootErrorNameShortName createErrNameSn;
    RootErrorNoRights createErrNoRights,updateErrNoRights;
    RootEventStateError createNoId,updateNoId;
    RootNoEventsStatesErrorDto updateErNoESid;


    @AfterClass
    public void deletedEvents(){
        BasicEvents.deleteEvents(1,newValue.data.id);
        BasicEvents.deleteEvents(1,newValue2.data.id);
        BasicEvents.deleteEvents(1,newValue3.data.id);
        BasicEvents.deleteEvents(1,newValue4.data.id);
    }



    @Test
    public void step01(){

        createErrName=BasicEvents.createErrNameEvents(1,"","",true,null);
        Assert.assertTrue(createErrName.errors.name[0].equals("не может быть пустым"));
    }

    @Test
    public void step02(){
        newValue= BasicEvents.createEvents(1,"m","m",true,1);
       Assert.assertTrue(newValue.data.name.equals("m"));
       Assert.assertTrue(newValue.data.short_name.equals("m"));
       Assert.assertTrue(newValue.data.excluded);
       Assert.assertEquals(newValue.data.event_states[0].id, 1);
        id = newValue.data.getId();

    }

    @Test
    public void step03(){

createErrNameSn=BasicEvents.createErrNameShortNameEvents(1,"m","m",true,1);
Assert.assertTrue(createErrNameSn.errors.name[0].equals("уже существует"));
Assert.assertTrue(createErrNameSn.errors.short_name[0].equals("уже существует"));
    }

    @Test
    public void step04(){

newValue2=BasicEvents.createEvents(1,"tests1","tests1",false,1);
        Assert.assertTrue(!newValue2.data.excluded);
        Assert.assertEquals(newValue2.data.event_states[0].id, 1);
    }

    @Test
    public void step05(){

createErrNoRights=BasicEvents.createErrNoRightsEvents(6,"tests1","tests1",false,1);
Assert.assertTrue(createErrNoRights.error.equals("Доступ к ресурсу запрещен"));

    }
@Test
    public void step06(){

    newValue3=BasicEvents.createEvents(1,"testsfsg","",false,1);
    Assert.assertTrue(newValue3.data.name.equals("testsfsg"));
    Assert.assertTrue(newValue3.data.short_name.equals(""));
        Assert.assertTrue(!newValue3.data.excluded);
        Assert.assertEquals(newValue3.data.event_states[0].id, 1);
}

@Test
    public void step07(){
createNoId=BasicEvents.createErrEventsNoId(1,"testhtgrf","",true,5,1000);
Assert.assertTrue(createNoId.errors.event_state_ids[0].equals("имеет непредусмотренное значение: 1000"));
}

@Test
    public void step08(){
    newValue4= BasicEvents.createEvents(1,"rtyн","",true,2,3);
    Assert.assertTrue(newValue4.data.name.equals("rtyн"));
    Assert.assertTrue(newValue4.data.short_name.equals(""));
    Assert.assertTrue(newValue4.data.excluded);
    Assert.assertEquals(newValue4.data.event_states[0].id, 3);
    Assert.assertEquals(newValue4.data.event_states[1].id, 2);
}

@Test
    public void step09(){
        updateValue=BasicEvents.updateEvents(1,id,"vgdfs12342","gdsv12342",true,1,2);
        Assert.assertTrue(updateValue.data.name.equals("vgdfs12342"));
        Assert.assertTrue(updateValue.data.short_name.equals("gdsv12342"));
        Assert.assertEquals(updateValue.data.event_states[0].id,1);
        Assert.assertEquals(updateValue.data.event_states[1].id,2);
}


@Test
    public void step10(){
        updateErrName=BasicEvents.updateErrNameEvents(1,id,"","",true,1);
        Assert.assertTrue(updateErrName.errors.name[0].equals("не может быть пустым"));
}

@Test
    public void step11(){
        updateErrNoRights=BasicEvents.updateErrNoRightsEvents(6,id,"","",true,1);
        Assert.assertTrue(updateErrNoRights.error.equals("Доступ к ресурсу запрещен"));
}

@Test
    public void step12(){
        updateNoId=BasicEvents.updateErrEventsNoId(1,id,"rtyн","",true,1,1000);
        Assert.assertTrue(updateNoId.errors.event_state_ids[0].equals("имеет непредусмотренное значение: 1000"));
}

@Test
    public void step13(){
       updateErNoESid=BasicEvents.noEventsStatesError(1,id,"rtyн","",true,10000);
       Assert.assertTrue(updateErNoESid.error.equals("Запись для Classifiers::EventState с id = [10000] не найдена"));
}


}

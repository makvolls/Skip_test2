package SKIP_API;

import API.BasicEventStates;
import API.DTO.ErrorsDTO.EventStateErrorsDto.RootEventStateErrorName;
import API.DTO.EventStatesDto.RootElementEventStates;
import API.DTO.EventStatesDto.RootEventStates;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class SKIP_24 {

    RootElementEventStates updateES,updateES2,updateES3;
    RootEventStateErrorName updateESerN,updateESerN2;
    RootEventStates actualES,actualES2;
    String name;
    int id1;

    @AfterClass

    public void updateValueEnd(){
BasicEventStates.updateEventStates(1,id1,"trwe1",true);
    }


    @Test

    public void step01(){

        actualES2=BasicEventStates.getEventStatesList(1);
        id1=actualES2.data[0].id;
        updateES=BasicEventStates.updateEventStates(1,id1,"Подписание",false);
        Assert.assertTrue(updateES.data.name.equals("Подписание"));
        Assert.assertTrue(!updateES.data.excluded);

    }

    @Test
    public void step02(){
        updateES2=BasicEventStates.updateEventStates(1,id1,"п",true);
        Assert.assertTrue(updateES2.data.name.equals("п"));
        Assert.assertTrue(updateES2.data.excluded);
    }

    @Test
    public void step03(){
        updateESerN=BasicEventStates.updateEsErrorName(1,id1,"",false);
        Assert.assertTrue(updateESerN.errors.name[0].equals("не может быть пустым"));

    }

    @Test
    public void step04(){
        actualES=BasicEventStates.getEventStatesList(1);
        name = actualES.data[1].getName();
        updateESerN2=BasicEventStates.updateEsErrorName(1,id1,name,false);
        Assert.assertTrue(updateESerN2.errors.name[0].equals("уже существует"));

    }

    @Test

    public void step05(){
        updateES3=BasicEventStates.updateEventStates(1,id1,"Подписание",false);
        Assert.assertTrue(updateES3.data.name.equals("Подписание"));
        Assert.assertTrue(!updateES3.data.excluded);
    }

}

package SKIP_API;

import API.BasicPeriodicity;
import API.DTO.ErrorsDTO.RootErrorNoRights;
import API.DTO.ErrorsDTO.RootNameError;
import API.DTO.PeriodicityDto.RootPerWithDataPut;
import API.DTO.PeriodicityDto.RootPeriodicityGet;
import org.testng.Assert;
import org.testng.annotations.Test;

import static API.BasicPeriodicity.*;

public class SKIP_35 {
    int id;
    String name;
    RootPeriodicityGet actualPeriodicity;
    RootPerWithDataPut updateValue;
    RootNameError updateErrorValue;
    RootNameError updateErrorExists;
    RootErrorNoRights updateErrorNoRight, updateErrorNoRight2;
    RootPerWithDataPut updateWithoutExcluded;
    int priority;





    @Test

    public void step01() {


        actualPeriodicity=BasicPeriodicity.getPeriodicity(1,"","priority","asc");
        //Assert.assertTrue(actualPeriodicity.getData().equals(actualPeriodicity.data));
        Assert.assertNotNull(actualPeriodicity.data);

        id = actualPeriodicity.data[0].getId();
        name = actualPeriodicity.data[1].getName();
    }

    @Test

    public void step02(){

       updateValue=BasicPeriodicity.updatePeriodicity(1,id,"test12",false);
       //priority = updateValue.getData().getPriority();
       Assert.assertTrue(updateValue.data.getName().equals("test12"));
       Assert.assertEquals(false,false);
    }

@Test

    public void step03(){

        updateErrorValue= createErrorPeriodicity(1, id,"",false);
        Assert.assertTrue(updateErrorValue.getErrors().getName()[0].equals("не может быть пустым"));
}


@Test

public void step04(){
        updateErrorExists=createErrorPeriodicity(1,id,name,true);
        Assert.assertTrue((updateErrorExists.getErrors().getName()[0].equals("уже существует")));
}

@Test
    public void step05(){

        updateErrorNoRight=createNoRightsError(8,id,name,true);
        Assert.assertTrue(updateErrorNoRight.error.equals("Доступ к ресурсу запрещен"));
}

@Test
    public void step06(){
    updateWithoutExcluded=createWithoutExcluded(8,id,"grgrewc1");
    Assert.assertTrue(updateWithoutExcluded.data.getName().equals("grgrewc1"));
}
    @Test
    public void step07(){

        updateErrorNoRight2=createNoRightsError(6,id,name,true);
        Assert.assertTrue(updateErrorNoRight2.error.equals("Доступ к ресурсу запрещен"));
    }

}

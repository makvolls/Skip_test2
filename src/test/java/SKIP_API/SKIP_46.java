package SKIP_API;

import API.BasicPeriodicity;
import API.DTO.ErrorsDTO.RootErrorNoRights;
import API.DTO.ErrorsDTO.RootNameError;
import API.DTO.PeriodicityDto.RootPeriodicityGet;
import API.DTO.PeriodicityDto.RootPeriodicityPostDto;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class SKIP_46 {
    RootNameError updateValueError;
    RootPeriodicityPostDto updateValue,updateValue2;
    RootNameError updateValueError2;
    RootPeriodicityGet actualPeriodicity;
    RootErrorNoRights updateValueError3;
    RootPeriodicityPostDto updateValue3;

@AfterClass

    public void delete(){

    BasicPeriodicity.deletePeriodicity(1,updateValue.getData().id);
    BasicPeriodicity.deletePeriodicity(1,updateValue2.getData().id);
    BasicPeriodicity.deletePeriodicity(1,updateValue3.getData().id);
}




    @Test
    public void step01(){
        updateValueError= BasicPeriodicity.postPeriodicityNameError(1,"",true);
        Assert.assertTrue(updateValueError.errors.getName()[0].equals("не может быть пустым"));
    }

    @Test

    public void step02(){
        updateValue= BasicPeriodicity.postPeriodicity(1,"ю",true);
        Assert.assertTrue(updateValue.data.name.equals("ю"));
    }

    @Test
    public void step03(){
        updateValue2= BasicPeriodicity.postPeriodicity(1,"4",false);
        Assert.assertTrue(updateValue2.data.name.equals("4"));
       Assert.assertTrue(!updateValue2.data.excluded);
    }

    @Test
    public void step04(){
        updateValueError2= BasicPeriodicity.postPeriodicityExistError(1,"8",false);
        Assert.assertTrue(updateValueError2.errors.name[0].equals("уже существует"));
}

@Test
    public void step05(){
    actualPeriodicity=BasicPeriodicity.getPeriodicity(1,"","priority","asc");
    Assert.assertEquals(actualPeriodicity.data[0].priority, 1);
}

@Test
    public void step06(){
        updateValueError3=BasicPeriodicity.PostNoRightsError(6,"8",false);
        Assert.assertTrue(updateValueError3.error.equals("Доступ к ресурсу запрещен"));
}

@Test
    public void step07(){
    updateValue3=BasicPeriodicity.postPerWithoutExcluded(1,"gfrdff");
    Assert.assertTrue(updateValue3.data.name.equals("gfrdff"));
}

}

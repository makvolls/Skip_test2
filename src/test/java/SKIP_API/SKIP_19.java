package SKIP_API;

import API.BasicControlPeriod;
import API.DTO.ControlPeriodStatesDto.RootPostControlDto;
import API.DTO.ErrorsDTO.RootErrorNameShortName;
import API.DTO.ErrorsDTO.RootNameError;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class SKIP_19 {

    RootPostControlDto createValue,createValue2,createValue3;
    RootErrorNameShortName createValueErr;
    RootNameError createValueErr2;

    @AfterClass
    public void deleted(){
    BasicControlPeriod.deleteControlPeriod(1,createValue.data.id);
    BasicControlPeriod.deleteControlPeriod(1,createValue2.data.id);
    BasicControlPeriod.deleteControlPeriod(1,createValue3.data.id);
}


    @Test
    public void step01(){

        createValue= BasicControlPeriod.postControlPeriod(1,"тест123","тес12",false);
        Assert.assertTrue(createValue.data.name.equals("тест123"));
        Assert.assertTrue(createValue.data.short_name.equals("тес12"));
        Assert.assertTrue(!createValue.data.excluded);
    }

    @Test
    public void step02(){
        createValue2= BasicControlPeriod.postControlPeriod(1,"З","з",false);
        Assert.assertTrue(createValue2.data.name.equals("З"));
        Assert.assertTrue(createValue2.data.short_name.equals("з"));
    }

    @Test
    public void step03(){
        createValueErr=BasicControlPeriod.postErrNameShControl(1,"","з",false);
        Assert.assertTrue(createValueErr.getErrors().getName()[0].equals("не может быть пустым"));
        Assert.assertTrue(createValueErr.getErrors().getShort_name()[0].equals("уже существует"));
    }

@Test
    public void step04(){
    createValue3=BasicControlPeriod.postControlPeriod(1,"rfewt1","",true);
    Assert.assertTrue(createValue3.data.name.equals("rfewt1"));
    Assert.assertTrue(createValue3.data.excluded);
}

@Test
    public void step05(){
createValueErr2=BasicControlPeriod.postErrNameCp(1,"","rfew",true);
Assert.assertTrue(createValueErr2.errors.name[0].equals("не может быть пустым"));
}

}



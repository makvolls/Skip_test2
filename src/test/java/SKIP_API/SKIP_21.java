package SKIP_API;

import API.BasicControlPeriod;
import API.DTO.ControlPeriodStatesDto.RootControlDto;
import API.DTO.ControlPeriodStatesDto.RootPutCpsDto;
import API.DTO.ErrorsDTO.ControlPeriodErrorsDto.RootCprErrorPutShortName;
import API.DTO.ErrorsDTO.ControlPeriodErrorsDto.RootCpsErrorPutNs;
import API.DTO.ErrorsDTO.RootErrorNoRights;
import API.DTO.ErrorsDTO.RootNameError;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SKIP_21 {

    int id1;
    int id2;
    String name1;
    String name2;
    String short_name1;
    String short_name2;

    RootControlDto actualCpS;
    RootPutCpsDto updateValue,updateValue2;
    RootNameError updateErValue;
    RootCprErrorPutShortName updateErValue2;
    RootCpsErrorPutNs updateErValue3;
    RootErrorNoRights updateErValue4;

    @Test
    public void step01(){
        actualCpS= BasicControlPeriod.getControlPeriod(1,"priority","asc");
        Assert.assertNotNull(actualCpS.data);
        id1 = actualCpS.data[0].getId();
        id2 = actualCpS.data[1].getId();
        name1 = actualCpS.data[0].getName();
        name2 = actualCpS.data[1].getName();
        short_name1=actualCpS.data[0].getShort_name();
        short_name2=actualCpS.data[1].getShort_name();
    }

    @Test
    public void step02(){
        updateValue=BasicControlPeriod.putCps(1,id1,"Тестикc","ъ",true);
        Assert.assertTrue(updateValue.data.name.equals("Тестикc"));
        Assert.assertTrue(updateValue.data.short_name.equals("ъ"));
        Assert.assertTrue(updateValue.data.excluded);
    }

    @Test
    public void step03(){
    updateErValue=BasicControlPeriod.putErCps(1,id1,name2,"ъ",true);
        Assert.assertTrue(updateErValue.errors.getName()[0].equals("уже существует"));
    }
    @Test
    public void step04(){
        updateErValue2=BasicControlPeriod.putErCpsSn(1,id1,"Тест32",short_name2,true);
    Assert.assertTrue(updateErValue2.errors.getShort_name()[0].equals("уже существует"));

    }
@Test
    public void step05(){
        updateErValue3=BasicControlPeriod.putErrCpsNs(1,id1,"",short_name2,true);
        Assert.assertTrue(updateErValue3.errors.getName()[0].equals("не может быть пустым"));
        Assert.assertTrue(updateErValue3.errors.getShort_name()[0].equals("уже существует"));
}

@Test
    public void step06(){
        updateValue2=BasicControlPeriod.putCps(1,id1,"Тестикc","",false);
        Assert.assertTrue(updateValue2.data.name.equals("Тестикc"));
        Assert.assertTrue(updateValue2.data.short_name.equals(""));
        Assert.assertTrue(!updateValue2.data.excluded);
}

@Test
    public void step07(){
        updateErValue4= BasicControlPeriod.putErrCpsNoRights(6,id1,"Тестик","",false);
        Assert.assertTrue(updateErValue4.error.equals("Доступ к ресурсу запрещен"));
}
}

package SKIP_API;

import API.BasicSecurity;
import API.DTO.ErrorsDTO.RootErrorNoRights;
import API.DTO.ErrorsDTO.RootNameError;
import API.DTO.ErrorsDTO.RootShortNameError;
import API.DTO.SecurityClassificationsDto.RootPostSecurityDto;
import API.DTO.SecurityClassificationsDto.RootSecurityDto;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class SKIP_36 {

    RootNameError errValue,errValue2;
    RootPostSecurityDto newValue,newValue2;
    RootShortNameError errValue3;
    RootErrorNoRights errValue4;
    RootSecurityDto listValue;


    @AfterClass
    public void Delete(){
        BasicSecurity.deleteSecurity(1,newValue.data.id);
        BasicSecurity.deleteSecurity(1,newValue2.data.id);
    }


    @Test
    public void step01(){
    errValue= BasicSecurity.postErrNameSecurity(1,"","",true);
        Assert.assertTrue(errValue.errors.name[0].equals("не может быть пустым"));
    }

    @Test

    public void step02(){
        newValue=BasicSecurity.postSecurity(1,"b","",true);
        Assert.assertTrue(newValue.data.name.equals("b"));
        Assert.assertTrue(newValue.data.excluded);
    }

    @Test
    public void step03(){
        errValue2=BasicSecurity.postErrNameSecurity(1,"b","",true);
        Assert.assertTrue(errValue2.errors.name[0].equals("уже существует"));
    }

    @Test
    public void step04(){
        newValue2=BasicSecurity.postSecurity(1,"bfsd","fgea",false);
        Assert.assertTrue(newValue2.data.name.equals("bfsd"));
        Assert.assertTrue(newValue2.data.short_name.equals("fgea"));
        Assert.assertTrue(!newValue2.data.excluded);
    }

    @Test
    public void step05(){
    errValue3=BasicSecurity.postErrShortNameSecurity(1,"bfsafsd","fgea",false);
    Assert.assertTrue(errValue3.errors.short_name[0].equals("уже существует"));
    }

    @Test
    public void step06(){
        errValue4=BasicSecurity.postErrNoRightsSecurity(6,"bfsafsd","fgea",false);
        Assert.assertTrue(errValue4.error.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void step07(){
        listValue=BasicSecurity.getSecurity(1);
         Assert.assertEquals(1,listValue.data[0].priority);

    }

}

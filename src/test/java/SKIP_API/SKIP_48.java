package SKIP_API;

import API.BasicUrgencies;
import API.DTO.ErrorsDTO.RootErrorNoRights;
import API.DTO.ErrorsDTO.RootNameError;
import API.DTO.UrgenciesDTO.CreateValueUrgenciesOnlyName;
import API.DTO.UrgenciesDTO.RootElementUrgencies;
import API.DTO.UrgenciesDTO.RootUrgencies;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class SKIP_48 {
    String name;

    RootUrgencies listValues;
    RootElementUrgencies newValue;

    RootNameError errValue,errValue2;
    RootErrorNoRights errorNoRights,errorNoRights2;
    RootElementUrgencies newValue2;

    @AfterClass
    public void delete(){
        BasicUrgencies.deleteUrgencies(1,newValue.data.id);
        BasicUrgencies.deleteUrgencies(1,newValue2.data.id);
    }

    @Test
    public void step01(){
        listValues= BasicUrgencies.getUrgenciesList(1);
        Assert.assertNotNull(listValues);
        name=listValues.data[0].getName();
    }

    @Test
    public void step02(){
        newValue=BasicUrgencies.createUrgencies(1,"testik",true);
        Assert.assertTrue(newValue.data.name.equals("testik"));
        Assert.assertTrue(newValue.data.excluded);
    }
    @Test
    public void step03(){

        errValue=BasicUrgencies.createErrNameUrgencies(1,"",true);
        Assert.assertTrue(errValue.errors.name[0].equals("не может быть пустым"));
    }

    @Test
    public void step04(){
        errValue2=BasicUrgencies.createErrNameUrgencies(1,name,true);
        Assert.assertTrue(errValue2.errors.name[0].equals("уже существует"));
    }

    @Test
    public void step05(){

        errorNoRights=BasicUrgencies.createErrNoRightsUrgencies(8,"vsdvfds",true);
                Assert.assertTrue(errorNoRights.error.equals("Доступ к ресурсу запрещен"));

    }

    @Test
    public void step06(){
        newValue2=BasicUrgencies.createUrgenciesOnlyName(1,"gfregfd");
        Assert.assertTrue(newValue2.data.name.equals("gfregfd"));
        Assert.assertTrue(!newValue2.data.excluded);
    }

    @Test
    public void step08(){

        errorNoRights2=BasicUrgencies.createErrNoRightsUrgencies(6,"vsdvfdgs",true);
        Assert.assertTrue(errorNoRights2.error.equals("Доступ к ресурсу запрещен"));

    }
}

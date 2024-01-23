package SKIP_API;

import API.BasicDocumentExecutionStates;
import API.DTO.DocumentExecutionStatesDto.RootDocumentExecutionStates;
import API.DTO.DocumentExecutionStatesDto.RootElementDocumentExecutionStates;
import API.DTO.ErrorsDTO.RootErrorNoRights;
import API.DTO.ErrorsDTO.RootNameError;
import API.DTO.ErrorsDTO.RootShortNameError;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SKIP_40 {
    int idAut1=1;
    int idAut2=6;
    int idAut3=8;

    int id_classifiers;
    String name_classifiers;
    String short_name_classifiers;

    RootDocumentExecutionStates listValues;
    RootElementDocumentExecutionStates updateValue,updateValue2,updateValue3;
    RootNameError errValue,errValue2;
    RootShortNameError errValue3;
    RootErrorNoRights errValue4,errValue5;

    @Test
    public void step01(){
        listValues= BasicDocumentExecutionStates.getDocumentExecutionStatesList(idAut1);
        Assert.assertNotNull(listValues.data.length);
        id_classifiers=listValues.data[0].id;
        name_classifiers=listValues.data[1].name;
        short_name_classifiers=listValues.data[1].short_name;
        System.out.println(id_classifiers);
        System.out.println(name_classifiers);
        System.out.println(short_name_classifiers);
    }

    @Test
    public void step02(){
        updateValue=BasicDocumentExecutionStates.updateDocumentExecutionStatesValue(idAut1,id_classifiers,"grsf",
                "vsdvds",false);
        Assert.assertTrue(!updateValue.data.excluded);
        Assert.assertTrue(updateValue.data.id==id_classifiers);
        Assert.assertTrue(updateValue.data.name.equals("grsf"));
        Assert.assertTrue(updateValue.data.short_name.equals("vsdvds"));
        Assert.assertTrue(!updateValue.data.deleted);
    }

    @Test
    public void step03(){
        errValue=BasicDocumentExecutionStates.updateDocumentExecutionStatesValueErrorName(idAut1,id_classifiers,"",
                "vsdvds",false);
        Assert.assertTrue(errValue.errors.name[0].equals("не может быть пустым"));
    }
    @Test
    public void step04(){
        errValue2=BasicDocumentExecutionStates.updateDocumentExecutionStatesValueErrorName(idAut1,id_classifiers,name_classifiers,
                "vsdvds",true);
        Assert.assertTrue(errValue2.errors.name[0].equals("уже существует"));
    }

    @Test
    public void step05(){

        errValue3=BasicDocumentExecutionStates.updateDocumentExecutionStatesValueErrorShortName(idAut1,id_classifiers,
                "dsbdfb", short_name_classifiers,true);
        Assert.assertTrue(errValue3.errors.short_name[0].equals("уже существует"));
    }

    @Test
    public void step06(){
        errValue4=BasicDocumentExecutionStates.updateDocumentExecutionStatesValueErrorNoRights(idAut3,id_classifiers,
                "dsbdfb", "gsrwgsd",true);
        Assert.assertTrue(errValue4.error.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void step07(){
        updateValue2=BasicDocumentExecutionStates.updateDocumentExecutionStatesNameShortName(idAut3,id_classifiers,
                "dsbdfbc", "gsrwgsdc");
        Assert.assertTrue(!updateValue2.data.excluded);
        Assert.assertTrue(updateValue2.data.id==id_classifiers);
        Assert.assertTrue(updateValue2.data.name.equals("dsbdfbc"));
        Assert.assertTrue(updateValue2.data.short_name.equals("gsrwgsdc"));
        Assert.assertTrue(!updateValue2.data.deleted);

    }

    @Test
    public void step08(){
        errValue5=BasicDocumentExecutionStates.updateDocumentExecutionStatesValueErrorNoRightsWithOutExcluded(idAut2,
                id_classifiers, "dsbdfbc", "gsrwgsdc");
        Assert.assertTrue(errValue5.error.equals("Доступ к ресурсу запрещен"));

    }

    @Test
    public void step09(){

        updateValue3=BasicDocumentExecutionStates.updateDocumentExecutionStatesWithoutBody(idAut1,id_classifiers);
        Assert.assertTrue(!updateValue3.data.excluded);
        Assert.assertTrue(updateValue3.data.id==id_classifiers);
        Assert.assertTrue(updateValue3.data.name.equals("dsbdfbc"));
        Assert.assertTrue(updateValue3.data.short_name.equals("gsrwgsdc"));
        Assert.assertTrue(!updateValue3.data.deleted);
    }




}

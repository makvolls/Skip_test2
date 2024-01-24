package SKIP_API;

import API.BasicClassifiers;
import API.BasicControlPeriod;
import API.DTO.ControlPeriodStatesDto.RootControlDto;
import API.DTO.ControlPeriodStatesDto.RootPostControlDto;
import API.DTO.ErrorsDTO.RootErrorNameShortName;
import API.DTO.ErrorsDTO.RootNameError;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SKIP_139 {

    int idAut1=1;
    int idAut2=3;

    RootPostControlDto createValue,createValue2,createValue3;
    RootErrorNameShortName createValueErr;
    RootNameError createValueErr2;
    RootControlDto listValues;

    int id1;
    String name1;
    int id2;
    String name2;
    int id3;
    String name3;


    @AfterClass
    public void deleted(){
    BasicControlPeriod.deleteControlPeriod(1,createValue.data.id);
    BasicControlPeriod.deleteControlPeriod(1,createValue2.data.id);
    BasicControlPeriod.deleteControlPeriod(1,createValue3.data.id);
}


    @Test
    public void step01(){

        createValue= BasicControlPeriod.postControlPeriod(idAut1,"тест123","тес12",false);
        Assert.assertTrue(createValue.data.name.equals("тест123"));
        Assert.assertTrue(createValue.data.short_name.equals("тес12"));
        Assert.assertTrue(!createValue.data.excluded);
        id1=createValue.data.id;
        name1=createValue.data.name;
    }

    @Test
    public void step02(){
        createValue2= BasicControlPeriod.postControlPeriod(idAut1,"З","з",false);
        Assert.assertTrue(createValue2.data.name.equals("З"));
        Assert.assertTrue(createValue2.data.short_name.equals("з"));
        id2=createValue2.data.id;
        name2=createValue2.data.name;
    }

    @Test
    public void step03(){
        createValueErr=BasicControlPeriod.postErrNameShControl(idAut1,"","з",false);
        Assert.assertTrue(createValueErr.getErrors().getName()[0].equals("не может быть пустым"));
        Assert.assertTrue(createValueErr.getErrors().getShort_name()[0].equals("уже существует"));
    }

@Test
    public void step04(){
    createValue3=BasicControlPeriod.postControlPeriod(idAut1,"rfewt1","",true);
    Assert.assertTrue(createValue3.data.name.equals("rfewt1"));
    Assert.assertTrue(createValue3.data.excluded);
    id3=createValue3.data.id;
    name3=createValue3.data.name;
}

@Test
    public void step05(){
createValueErr2=BasicControlPeriod.postErrNameCp(idAut1,"","rfew",true);
Assert.assertTrue(createValueErr2.errors.name[0].equals("не может быть пустым"));
}

@Test
    public void step06(){
        listValues=BasicControlPeriod.getControlPeriod(idAut2,"priority","asc");
        Assert.assertEquals(id1,listValues.data[2].id);
        Assert.assertEquals(name1,listValues.data[2].name);
    Assert.assertEquals(id2,listValues.data[1].id);
    Assert.assertEquals(name2,listValues.data[1].name);
    Assert.assertEquals(id3,listValues.data[0].id);
    Assert.assertEquals(name3,listValues.data[0].name);

}

@Test
    public void step07(){
    Response response = given()
            .header("Test-Authorization", idAut1)
            .log().all()
            .get(BasicClassifiers.API_CLASSIFIERS)
            .then().log().all()
            .extract().response();
    JsonPath jsonPath = response.jsonPath();
    String name_classifiers = jsonPath.get("data.name[8]");
    String type_classifiers=jsonPath.get("data.type[8]");

    Assert.assertTrue(name_classifiers.equals("Состояние контрольного срока"));
    Assert.assertTrue(type_classifiers.equals("global"));
}
}





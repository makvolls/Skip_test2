package SKIP_API;

import API.BasicClassifiers;
import API.BasicControlPeriod;
import API.BasicEventStates;
import API.DTO.ErrorsDTO.EventStateErrorsDto.RootEventStateErrorName;
import API.DTO.ErrorsDTO.RootErrorNoRights;
import API.DTO.EventStatesDto.RootElementEventStates;
import API.DTO.EventStatesDto.RootEventStates;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SKIP_22 {
RootElementEventStates createValue,createValue2;
RootEventStateErrorName createErrorValue,createErrorValue2;
RootErrorNoRights createErrorValue3;
RootEventStates listValues;
int idAut1=1;
int idAut2=6;
    int id1;
    String name1;
    int id2;
    String name2;
    int id3;
    String name3;

@AfterClass
public void deleteValue(){
    BasicEventStates.deleteEventStateValue(1,createValue.getData().id);
    BasicEventStates.deleteEventStateValue(1,createValue2.getData().id);
}



    @Test
    public void step01(){
        createValue= BasicEventStates.createEventStates(idAut1,"Кейc",true);
        Assert.assertTrue(createValue.data.name.equals("Кейc"));
        Assert.assertTrue(createValue.data.excluded);
        id1=createValue.data.id;
        name1=createValue.data.name;
    }

    @Test
    public void step02(){
    createErrorValue= BasicEventStates.createEsErrorName(idAut1,"Кейc",true);
    Assert.assertTrue(createErrorValue.errors.name[0].equals("уже существует"));
    }

    @Test
    public void step03(){
    createErrorValue2=BasicEventStates.createEsErrorName(idAut1,"",true);
    Assert.assertTrue(createErrorValue2.errors.name[0].equals("не может быть пустым"));
    }
    @Test
    public void step04(){
    createValue2=BasicEventStates.createEventStates(idAut1,"z",false);
        Assert.assertTrue(createValue2.data.name.equals("z"));
        Assert.assertTrue(!createValue2.data.excluded);
        id2=createValue2.data.id;
        name2=createValue2.data.name;
    }
   @Test
    public void step05(){
    createErrorValue3=BasicEventStates.createEsErrorNoRights(idAut2,"ТЕСТ",false);
    Assert.assertTrue(createErrorValue3.error.equals("Доступ к ресурсу запрещен"));
   }
    @Test
    public void step06(){
        listValues= BasicEventStates.getEventStatesList(3);
        Assert.assertEquals(id1,listValues.data[1].id);
        Assert.assertEquals(name1,listValues.data[1].name);
        Assert.assertEquals(id2,listValues.data[0].id);
        Assert.assertEquals(name2,listValues.data[0].name);


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
        String name_classifiers = jsonPath.get("data.name[9]");
        String type_classifiers=jsonPath.get("data.type[9]");

        Assert.assertTrue(name_classifiers.equals("Состояние события"));
        Assert.assertTrue(type_classifiers.equals("global"));
    }

}

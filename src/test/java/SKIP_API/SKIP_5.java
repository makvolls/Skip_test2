package SKIP_API;

import API.BasicRoles;
import API.DTO.ErrorsDTO.RootError;
import API.DTO.RolesDto.RootResponseRolesDto;
import Utils.RandomGenerateText;
import autotest.core.util.Assistant;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SKIP_5 {


    int idAut1 = 1;
    int idAut2 = 3;
    int id1, id2, id3, id4;
    String name1 = RandomGenerateText.generateUniqeTextRussianString(7);
    String name2 = RandomGenerateText.generateUniqeTextRussianString(7);
    String name3 = RandomGenerateText.generateUniqeTextRussianString(7);
    String name4 = RandomGenerateText.generateUniqeTextRussianString(7);

    @BeforeClass
    public void newRoles() {

        JsonObject object = new JsonObject();
        object.addProperty("name", name1);
        object.addProperty("desc", "1");
        object.addProperty("global", true);

        JsonArray rights_ids = new JsonArray();
        rights_ids.add("update_users");
        object.add("rights_ids", rights_ids);
        Response response0 = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut2)
                .body(object.toString())
                .post(BasicRoles.API_ROLES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath0 = response0.jsonPath();
        int id11 = jsonPath0.getInt("data.id");
        id1 = id11;

        JsonObject object2 = new JsonObject();
        object2.addProperty("name", name2);
        object2.addProperty("desc", "1");
        object2.addProperty("global", true);

        JsonArray rights_ids2 = new JsonArray();
        rights_ids2.add("update_users");
        object2.add("rights_ids", rights_ids);
        Response response2 = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut2)
                .body(object2.toString())
                .post(BasicRoles.API_ROLES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath2 = response2.jsonPath();
        int id22 = jsonPath2.getInt("data.id");
        id2 = id22;

        JsonObject object3 = new JsonObject();
        object3.addProperty("name", name3);
        object3.addProperty("desc", "1");
        object3.addProperty("global", false);

        JsonArray rights_ids3 = new JsonArray();
        rights_ids3.add("create_users");
        object3.add("rights_ids", rights_ids);
        Response response3 = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(object3.toString())
                .post(BasicRoles.API_ROLES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath3 = response3.jsonPath();
        int id33 = jsonPath3.getInt("data.id");
        id3 = id33;

        JsonObject object4 = new JsonObject();
        object4.addProperty("name", name4);
        object4.addProperty("desc", "1");
        object4.addProperty("global", false);

        JsonArray rights_ids4 = new JsonArray();
        rights_ids4.add("create_users");
        object4.add("rights_ids", rights_ids);
        Response response4 = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut2)
                .body(object4.toString())
                .post(BasicRoles.API_ROLES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath4 = response4.jsonPath();
        int id44 = jsonPath4.getInt("data.id");
        id4 = id44;

        BasicRoles.deleteRole(idAut2, id4);


    }

    @AfterClass
    public void delete() {
        BasicRoles.deleteRole(idAut2, id1);
        BasicRoles.deleteRole(idAut2, id2);
        BasicRoles.deleteRole(idAut1, id3);
    }


    @Test
    public void Step01() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut2)
                .get(BasicRoles.API_ROLES+"/"+id1)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String name=jsonPath.getString("data.name");
        int id=jsonPath.getInt("data.id");
        Assert.assertTrue(name.equals(name1));
        Assert.assertTrue(id==id1);

    }
    @Test
    public void Step02() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut2)
                .get(BasicRoles.API_ROLES+"/"+id2)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String name=jsonPath.getString("data.name");
        int id=jsonPath.getInt("data.id");
        Assert.assertTrue(name.equals(name2));
        Assert.assertTrue(id==id2);

    }
    @Test
    public void Step03() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut2)
                .get(BasicRoles.API_ROLES+"/"+id3)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
     String error=jsonPath.getString("error");
     Assert.assertTrue(error.equals("Доступ к ресурсу запрещен"));

    }
    @Test
    public void Step04() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut2)
                .get(BasicRoles.API_ROLES+"/"+id4)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error=jsonPath.getString("error");
        Assert.assertTrue(error.equals("Запись для Permissions::Role с id = "+id4+" не найдена"));

    }

    @Test
    public void Step05() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .get(BasicRoles.API_ROLES+"/"+id1)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String name=jsonPath.getString("data.name");
        int id=jsonPath.getInt("data.id");
        Assert.assertTrue(name.equals(name1));
        Assert.assertTrue(id==id1);

    }


}

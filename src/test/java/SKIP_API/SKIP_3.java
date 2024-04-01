package SKIP_API;

import API.BasicRoles;
import API.BasicUser;
import API.DTO.ErrorsDTO.RolesErrors.RootNameErrors;
import API.DTO.ErrorsDTO.RootError;
import API.DTO.RolesDto.RootResponseRolesDto;
import Utils.RandomGenerateText;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.IDynamicGraph;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;

public class SKIP_3 {
    int idAut1 = 1;
    int idAut2 = 2;
    int idAut6=6;

    int idRole, idRole2, idRole3;
    String test1 = RandomGenerateText.generateUniqeTextRussianString(7);
    String test2 = RandomGenerateText.generateUniqeTextRussianString(7);
    String test3 = RandomGenerateText.generateUniqeTextRussianString(7);
    String test5 = RandomGenerateText.generateUniqeTextRussianString(7);

    int idUserB;
    int idRoleB;
    String test4 = RandomGenerateText.generateUniqeTextRussianString(7);
    RootError delete;

    @BeforeClass
    public void post0001() {

        JsonObject object = new JsonObject();
        object.addProperty("name", test4);
        object.addProperty("desc", "1");
        object.addProperty("global", true);

        JsonArray rights_ids = new JsonArray();
        rights_ids.add("context_search");
        object.add("rights_ids", rights_ids);
        Response response0 = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(object.toString())
                .post(BasicRoles.API_ROLES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath0 = response0.jsonPath();
        int id = jsonPath0.getInt("data.id");
        idRoleB = id;

        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut2)
                .params("official_id", "5b3bf8a6b48f00020e000001")
                .get(BasicUser.API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<String> official_id = jsonPath.get("data");

        if (official_id.isEmpty()) {

            JsonObject object1 = new JsonObject();
            JsonArray array = new JsonArray();
            array.add(idRoleB);
            object1.add("role_ids", array);
            object1.addProperty("control_subject_id", (String) null);

            JsonObject object3 = new JsonObject();
            object3.addProperty("official_id", "5b3bf8a6b48f00020e000001");
            JsonArray array3 = new JsonArray();
            array3.add(idRoleB);
            object3.add("role_ids", array3);
            object3.addProperty("control_subject_id", (String) null);
            Response response2 = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", idAut2)
                    .body(object3.toString())
                    .post(BasicUser.API_USER)
                    .then().log().all()
                    .extract().response();
            JsonPath jsonPath2 = response2.jsonPath();
            int id2 = jsonPath2.getInt("data.id");
            idUserB = id2;

        } else {

            Response response1 = given()
                    .when()
                    .header("Test-Authorization", idAut2)
                    .params("official_id", "5b3bf8a6b48f00020e000001")
                    .get(BasicUser.API_USER)
                    .then().log().all()
                    .extract().response();
            JsonPath jsonPath1 = response1.jsonPath();
            idUserB = jsonPath1.get("data[0].id");

            JsonObject object1 = new JsonObject();
            JsonArray array = new JsonArray();
            array.add(idRoleB);
            object1.add("role_ids", array);
            object1.addProperty("control_subject_id", (String) null);

            Response response2 = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", idAut2)
                    .body(object1.toString())
                    .put(BasicUser.API_USER + "/" + idUserB)
                    .then().log().all()
                    .extract().response();
        }
    }

    @AfterClass
    public void Step14(){
        Response response = given()
                .header("Test-Authorization", idAut1)
                .delete(BasicRoles.API_ROLES+"/"+idRole2)
                .then().log().all()
                .extract().response();
        int SC=response.statusCode();
        Assert.assertTrue(SC==204);
        Response response2 = given()
                .header("Test-Authorization", idAut1)
                .delete(BasicRoles.API_ROLES+"/"+idRole2)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response2.jsonPath();
        String error=jsonPath.getString("error");
        Assert.assertTrue(error.equals("Запись для Permissions::Role с id = "+idRole2+" не найдена"));
    }
    @AfterClass
    public void Step15(){
        Response response = given()
                .header("Test-Authorization", idAut1)
                .delete(BasicRoles.API_ROLES+"/"+idRole3)
                .then().log().all()
                .extract().response();
        int SC=response.statusCode();
        Assert.assertTrue(SC==204);
    }
    @AfterClass
    public void delete() {

        JsonObject object1 = new JsonObject();
        JsonArray array = new JsonArray();
        array.add(5);
        object1.add("role_ids", array);
        object1.addProperty("control_subject_id", (String) null);

        Response response2 = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut2)
                .body(object1.toString())
                .put(BasicUser.API_USER + "/" + idUserB)
                .then().log().all()
                .extract().response();

        BasicRoles.deleteRole(idAut1, idRoleB);
        BasicUser.deleteUser(idAut2, idUserB);


    }
    @Test
    public void Step01() {
        JsonObject object = new JsonObject();
        object.addProperty("id", "");
        object.addProperty("name", test1);
        object.addProperty("desc", "1");
        object.addProperty("full_name", "string");
        object.addProperty("global", true);

        JsonArray rights_ids = new JsonArray();
        rights_ids.add("update_users");
        object.add("rights_ids", rights_ids);
        object.addProperty("provider_id", "string");

        Response response0 = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(object.toString())
                .post(BasicRoles.API_ROLES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath0 = response0.jsonPath();
        int id = jsonPath0.getInt("data.id");
        idRole = id;
        String name0 = jsonPath0.getString("data.name");
        boolean global = jsonPath0.getBoolean("data.global");
        String right_ids = jsonPath0.getString("data.rights_ids[0]");

        Assert.assertTrue(name0.equals(test1));
        Assert.assertTrue(global);
        Assert.assertTrue(right_ids.equals("update_users"));
    }

    @Test
    public void Step02() {
        JsonObject object = new JsonObject();
        object.addProperty("id", "");
        object.addProperty("name", test1);
        object.addProperty("desc", "1");
        object.addProperty("full_name", "string");
        object.addProperty("global", false);

        JsonArray rights_ids = new JsonArray();
        rights_ids.add("update_users");
        object.add("rights_ids", rights_ids);
        object.addProperty("provider_id", "string");

        Response response0 = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(object.toString())
                .post(BasicRoles.API_ROLES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath0 = response0.jsonPath();
        int id = jsonPath0.getInt("data.id");
        idRole2 = id;
        String name0 = jsonPath0.getString("data.name");
        boolean global = jsonPath0.getBoolean("data.global");
        String right_ids = jsonPath0.getString("data.rights_ids[0]");

        Assert.assertTrue(name0.equals(test1));
        Assert.assertTrue(!global);
        Assert.assertTrue(right_ids.equals("update_users"));
    }

    @Test
    public void Step03() {
        JsonObject object = new JsonObject();
        object.addProperty("id", "");
        object.addProperty("name", "");
        object.addProperty("desc", "1");
        object.addProperty("full_name", "string");
        object.addProperty("global", false);

        JsonArray rights_ids = new JsonArray();
        rights_ids.add("update_users");
        object.add("rights_ids", rights_ids);
        object.addProperty("provider_id", "string");

        Response response0 = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(object.toString())
                .post(BasicRoles.API_ROLES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath0 = response0.jsonPath();
        String error = jsonPath0.getString("errors.name[0]");
        Assert.assertTrue(error.equals("не может быть пустым"));
    }

    @Test
    public void Step04() {
        JsonObject object = new JsonObject();
        object.addProperty("id", "");
        object.addProperty("name", test1);
        object.addProperty("desc", "1");
        object.addProperty("full_name", "string");
        object.addProperty("global", false);

        JsonArray rights_ids = new JsonArray();
        rights_ids.add("");
        object.add("rights_ids", rights_ids);
        object.addProperty("provider_id", "string");

        Response response0 = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(object.toString())
                .post(BasicRoles.API_ROLES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath0 = response0.jsonPath();
        String error1 = jsonPath0.getString("errors.full_name[0]");
        String error2 = jsonPath0.getString("errors.rights_ids[0]");
        Assert.assertTrue(error1.equals("уже существует"));
        Assert.assertTrue(error2.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void Step05() {
        JsonObject object = new JsonObject();
        object.addProperty("id", "");
        object.addProperty("name", test2);
        object.addProperty("desc", "1");
        object.addProperty("full_name", "string");
        object.addProperty("global", false);

        JsonArray rights_ids = new JsonArray();
        rights_ids.add("update_users");
        object.add("rights_ids", rights_ids);
        object.addProperty("provider_id", "string");

        Response response0 = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(object.toString())
                .put(BasicRoles.API_ROLES + "/" + idRole)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath0 = response0.jsonPath();
        String name0 = jsonPath0.getString("data.name");
        boolean global = jsonPath0.getBoolean("data.global");
        String right_ids = jsonPath0.getString("data.rights_ids[0]");

        Assert.assertTrue(name0.equals(test2));
        Assert.assertTrue(!global);
        Assert.assertTrue(right_ids.equals("update_users"));
    }

    @Test
    public void Step06() {
        JsonObject object = new JsonObject();
        object.addProperty("id", "");
        object.addProperty("name", test2);
        object.addProperty("desc", "1");
        object.addProperty("full_name", "string");
        object.addProperty("global", true);

        JsonArray rights_ids = new JsonArray();
        rights_ids.add("update_users");
        object.add("rights_ids", rights_ids);
        object.addProperty("provider_id", "string");

        Response response0 = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(object.toString())
                .put(BasicRoles.API_ROLES + "/" + idRole2)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath0 = response0.jsonPath();
        String name0 = jsonPath0.getString("data.name");
        boolean global = jsonPath0.getBoolean("data.global");
        String right_ids = jsonPath0.getString("data.rights_ids[0]");

        Assert.assertTrue(name0.equals(test2));
        Assert.assertTrue(global);
        Assert.assertTrue(right_ids.equals("update_users"));
    }

    @Test
    public void Step07() {
        JsonObject object = new JsonObject();
        object.addProperty("id", "");
        object.addProperty("name", test2);
        object.addProperty("desc", "1");
        object.addProperty("full_name", "string");
        object.addProperty("global", true);

        JsonArray rights_ids = new JsonArray();
        rights_ids.add("update_users");
        rights_ids.add("setup_banners");
        object.add("rights_ids", rights_ids);
        object.addProperty("provider_id", "string");

        Response response0 = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(object.toString())
                .put(BasicRoles.API_ROLES + "/" + idRole2)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath0 = response0.jsonPath();
        String name0 = jsonPath0.getString("data.name");
        boolean global = jsonPath0.getBoolean("data.global");
        String right_ids = jsonPath0.getString("data.rights_ids[0]");
        String right_ids2 = jsonPath0.getString("data.rights_ids[1]");

        Assert.assertTrue(name0.equals(test2));
        Assert.assertTrue(global);
        Assert.assertTrue(right_ids.equals("update_users"));
        Assert.assertTrue(right_ids2.equals("setup_banners"));

    }
    @Test
    public void Step08() {
        JsonObject object = new JsonObject();
        object.addProperty("id", "");
        object.addProperty("name", test3);
        object.addProperty("desc", "1");
        object.addProperty("full_name", "string");
        object.addProperty("global", true);

        JsonArray rights_ids = new JsonArray();
        rights_ids.add("update_users");
        object.add("rights_ids", rights_ids);
        object.addProperty("provider_id", "string");

        Response response0 = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(object.toString())
                .post(BasicRoles.API_ROLES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath0 = response0.jsonPath();
        int id = jsonPath0.getInt("data.id");
        idRole3 = id;
        String name0 = jsonPath0.getString("data.name");
        boolean global = jsonPath0.getBoolean("data.global");
        String right_ids = jsonPath0.getString("data.rights_ids[0]");

        Assert.assertTrue(name0.equals(test3));
        Assert.assertTrue(global);
        Assert.assertTrue(right_ids.equals("update_users"));
    }
    @Test
    public void Step09() {
        JsonObject object = new JsonObject();
        object.addProperty("id", "");
        object.addProperty("name", test3);
        object.addProperty("desc", "1");
        object.addProperty("full_name", "string");
        object.addProperty("global", false);

        JsonArray rights_ids = new JsonArray();
        rights_ids.add("update_users");
        object.add("rights_ids", rights_ids);
        object.addProperty("provider_id", "string");

        Response response0 = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(object.toString())
                .put(BasicRoles.API_ROLES+"/"+idRole3)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath0 = response0.jsonPath();
        int id = jsonPath0.getInt("data.id");
        String name0 = jsonPath0.getString("data.name");
        boolean global = jsonPath0.getBoolean("data.global");
        String right_ids = jsonPath0.getString("data.rights_ids[0]");

        Assert.assertTrue(id==idRole3);
        Assert.assertTrue(name0.equals(test3));
        Assert.assertTrue(!global);
        Assert.assertTrue(right_ids.equals("update_users"));
    }
    @Test
    public void Step10() {
        JsonObject object = new JsonObject();
        object.addProperty("id", "");
        object.addProperty("name", test5);
        object.addProperty("desc", "1");
        object.addProperty("full_name", "string");
        object.addProperty("global", false);

        JsonArray rights_ids = new JsonArray();
        rights_ids.add("update_users");
        object.add("rights_ids", rights_ids);
        object.addProperty("provider_id", "string");

        Response response0 = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(object.toString())
                .put(BasicRoles.API_ROLES+"/"+idRoleB)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath0 = response0.jsonPath();

        String error=jsonPath0.getString("errors.base[0]");
        Assert.assertTrue(error.equals("Данную роль используют пользователи в других провайдерах, поэтому чек-бокс «Глобальная роль» снять нельзя"));
    }
    @Test
    public void Step11() {
        JsonObject object = new JsonObject();
        object.addProperty("id", "");
        object.addProperty("name", test5);
        object.addProperty("desc", "1");
        object.addProperty("full_name", "string");
        object.addProperty("global", false);

        JsonArray rights_ids = new JsonArray();
        rights_ids.add("update_users");
        object.add("rights_ids", rights_ids);
        object.addProperty("provider_id", "string");

        Response response0 = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut6)
                .body(object.toString())
                .put(BasicRoles.API_ROLES+"/"+idRoleB)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath0 = response0.jsonPath();

        String error=jsonPath0.getString("error");
        Assert.assertTrue(error.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void Step12(){
        Response response = given()
                .header("Test-Authorization", idAut1)
                .delete(BasicRoles.API_ROLES+"/"+idRole)
                .then().log().all()
                .extract().response();
        int SC=response.statusCode();
        Assert.assertTrue(SC==204);
    }
    @Test
    public void Step13(){
        Response response = given()
                .header("Test-Authorization", idAut1)
                .delete(BasicRoles.API_ROLES+"/"+idRole)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error=jsonPath.getString("error");
        Assert.assertTrue(error.equals("Запись для Permissions::Role с id = "+idRole+" не найдена"));
    }

}

package SKIP_API;

import API.BasicRoles;
import API.BasicUrgencies;
import API.BasicUser;
import API.DTO.RolesDto.RootResponseRolesDto;
import API.DTO.UserDto.RootResponseUserDto;
import autotest.core.util.Assistant;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SKIP_49 {
    int idAut1 = 1;
    int idAut8 = 8;
    int idAut6 = 6;
    int id1, id2;

    RootResponseUserDto newUser;
    RootResponseUserDto upUser;
    RootResponseRolesDto newRole;

    int idAut=1;
    int idUser;
    int idRole;

    private static String API_URGENCIES = "http://api.skip.rtech.ru/v1/classifiers/urgencies";
    String test1 = Assistant.generateUniqeTextRussianString(5);
    String test2 = Assistant.generateUniqeTextRussianString(10);
    String test3 = Assistant.generateUniqeTextRussianString(6);
    String test4 = Assistant.generateUniqeTextRussianString(6);
    String test5 = Assistant.generateUniqeTextRussianString(6);
    String test6 = Assistant.generateUniqeTextRussianString(7);

    @BeforeClass
    public void step00() {
        newRole = BasicRoles.createRoles(idAut, test1, true, "index_users",
                "show_classifiers",
                "update_classifiers",
                "create_classifiers_items",
                "destroy_classifiers_items",
                "sort_classifiers_items",
                "search_classifiers_items");
        idRole = newRole.data.id;


        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .params("official_id", "5a78c244ba8f00005b000001")
                .get(BasicUser.API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<String> official_id = jsonPath.get("data");

        if (official_id.isEmpty()) {
            newUser = BasicUser.createUser(idAut, "5a78c244ba8f00005b000001", idRole);
            idUser = newUser.data.id;
        } else {

            Response response1 = given()
                    .when()
                    .header("Test-Authorization", idAut)
                    .params("official_id", "5a78c244ba8f00005b000001")
                    .get(BasicUser.API_USER)
                    .then().log().all()
                    .extract().response();
            JsonPath jsonPath1 = response1.jsonPath();
            idUser = jsonPath1.get("data[0].id");
            upUser = BasicUser.updateUser(1, idUser, idRole);
            idUser = upUser.data.id;

            step01();


        }

    }





    @AfterClass
    public void delete() {

        BasicUser.updateUser(idAut, idUser, 5);
        BasicRoles.deleteRole(idAut, idRole);
        BasicUser.deleteUser(idAut, idUser);


        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", test2);
        requestBody.put("excluded", "true");
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestBody)
                .put(API_URGENCIES + "/" + id1)
                .then().log().all()
                .extract().response();
        BasicUrgencies.deleteUrgencies(1, id1);

        Map<String, String> requestBody2 = new HashMap<>();
        requestBody.put("name", test4);
        requestBody.put("excluded", "true");
        Response response2 = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestBody2)
                .put(API_URGENCIES + "/" + id2)
                .then().log().all()
                .extract().response();
        BasicUrgencies.deleteUrgencies(1, id2);
    }


    @Test
    public void step01() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", test1);
        requestBody.put("excluded", "true");
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestBody)
                .post(API_URGENCIES)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        // Get required parameters values from the response body
        int id = jsonPath.get("data.id");
        id1 = id;
        String name = jsonPath.get("data.name");
        int priority = jsonPath.get("data.priority");
        Boolean excluded = jsonPath.get("data.excluded");
        Boolean deleted = jsonPath.get("data.deleted");
        // Assert values
        Assert.assertTrue(name.equals(test1));
        Assert.assertTrue(priority == 1);
        Assert.assertTrue(excluded);
        Assert.assertTrue(!deleted);

    }

    @Test
    public void step02() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "v");
        requestBody.put("excluded", "false");
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestBody)
                .put(API_URGENCIES + "/" + id1)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        // Get required parameters values from the response body
        int id = jsonPath.get("data.id");
        String name = jsonPath.get("data.name");
        Boolean excluded = jsonPath.get("data.excluded");
        Boolean deleted = jsonPath.get("data.deleted");
        // Assert values
        Assert.assertTrue(id == id1);
        Assert.assertTrue(name.equals("v"));
        Assert.assertTrue(!excluded);
        Assert.assertTrue(!deleted);

    }

    @Test
    public void step03() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "");
        requestBody.put("excluded", "false");
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestBody)
                .put(API_URGENCIES + "/" + id1)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.get("errors.name[0]");
        Assert.assertTrue(error.equals("не может быть пустым"));

    }

    @Test
    public void step04() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", ">");
        requestBody.put("excluded", "false");
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestBody)
                .put(API_URGENCIES + "/" + id1)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        // Get required parameters values from the response body
        int id = jsonPath.get("data.id");
        String name = jsonPath.get("data.name");
        Boolean excluded = jsonPath.get("data.excluded");
        Boolean deleted = jsonPath.get("data.deleted");
        // Assert values
        Assert.assertTrue(id == id1);
        Assert.assertTrue(name.equals(">"));
        Assert.assertTrue(!excluded);
        Assert.assertTrue(!deleted);

    }

    @Test
    public void step05() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", test3);
        requestBody.put("excluded", "true");
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestBody)
                .post(API_URGENCIES)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        // Get required parameters values from the response body
        int id = jsonPath.get("data.id");
        id2 = id;
        String name = jsonPath.get("data.name");
        int priority = jsonPath.get("data.priority");
        Boolean excluded = jsonPath.get("data.excluded");
        Boolean deleted = jsonPath.get("data.deleted");
        // Assert values
        Assert.assertTrue(name.equals(test3));
        Assert.assertTrue(priority == 1);
        Assert.assertTrue(excluded);
        Assert.assertTrue(!deleted);

    }

    @Test
    public void step06() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", test3);
        requestBody.put("excluded", "true");
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestBody)
                .put(API_URGENCIES + "/" + id1)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.get("errors.name[0]");
        Assert.assertTrue(error.equals("уже существует"));

    }

    @Test
    public void step07() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", test5);
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idUser)
                .body(requestBody)
                .put(API_URGENCIES + "/" + id1)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        // Get required parameters values from the response body
        int id = jsonPath.get("data.id");
        String name = jsonPath.get("data.name");
        Boolean excluded = jsonPath.get("data.excluded");
        Boolean deleted = jsonPath.get("data.deleted");
        // Assert values
        Assert.assertTrue(id == id1);
        Assert.assertTrue(name.equals(test5));
        Assert.assertTrue(!excluded);
        Assert.assertTrue(!deleted);

    }

    @Test
    public void step08() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", test5);
        requestBody.put("excluded", "false");
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idUser)
                .body(requestBody)
                .put(API_URGENCIES + "/" + id1)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.get("error");
        Assert.assertTrue(error.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void step09() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", test5);
        requestBody.put("excluded", "false");
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut6)
                .body(requestBody)
                .put(API_URGENCIES + "/" + id1)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.get("error");
        Assert.assertTrue(error.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void step10() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", test6);
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestBody)
                .put(API_URGENCIES + "/" + id1)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        // Get required parameters values from the response body
        int id = jsonPath.get("data.id");
        String name = jsonPath.get("data.name");
        Boolean excluded = jsonPath.get("data.excluded");
        Boolean deleted = jsonPath.get("data.deleted");
        // Assert values
        Assert.assertTrue(id == id1);
        Assert.assertTrue(name.equals(test6));
        Assert.assertTrue(!excluded);
        Assert.assertTrue(!deleted);
    }

}
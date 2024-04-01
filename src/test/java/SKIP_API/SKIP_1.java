package SKIP_API;

import API.BasicRoles;
import API.BasicUser;
import Utils.RandomGenerateText;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class SKIP_1 {
    int idAut = 1;
    int idUser;
    int idRole;
    String test1 = RandomGenerateText.generateUniqeTextRussianString(7);

    @BeforeClass
    public void post0001() {

        JsonObject object = new JsonObject();
        object.addProperty("name", test1);
        object.addProperty("desc", "ASC");
        object.addProperty("global", true);

        JsonArray rights_ids = new JsonArray();
        rights_ids.add("context_search");
        object.add("rights_ids", rights_ids);
        Response response0 = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut)
                .body(object.toString())
                .post(BasicRoles.API_ROLES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath0 = response0.jsonPath();
        int id = jsonPath0.getInt("data.id");
        idRole = id;

        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut)
                .params("official_id", "5b3bf8a6b48f00020e000001")
                .get(BasicUser.API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<String> official_id = jsonPath.get("data");

        if (official_id.isEmpty()) {
            JsonObject object1 = new JsonObject();
            JsonArray array = new JsonArray();
            array.add(idRole);
            object1.add("role_ids", array);
            object1.addProperty("control_subject_id", (String) null);

            JsonObject object3 = new JsonObject();
            object3.addProperty("official_id", "5b3bf8a6b48f00020e000001");
            JsonArray array3 = new JsonArray();
            array3.add(idRole);
            object3.add("role_ids", array3);
            object3.addProperty("control_subject_id", (String) null);
            Response response2 = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", idAut)
                    .body(object3.toString())
                    .post(BasicUser.API_USER)
                    .then().log().all()
                    .extract().response();
            JsonPath jsonPath2 = response2.jsonPath();
            int id2 = jsonPath2.getInt("data.id");
            idUser = id2;

        } else {

            Response response1 = given()
                    .when()
                    .header("Test-Authorization", idAut)
                    .params("official_id", "5b3bf8a6b48f00020e000001")
                    .get(BasicUser.API_USER)
                    .then().log().all()
                    .extract().response();
            JsonPath jsonPath1 = response1.jsonPath();
            idUser = jsonPath1.get("data[0].id");

            JsonObject object1 = new JsonObject();
            JsonArray array = new JsonArray();
            array.add(idRole);
            object1.add("role_ids", array);
            object1.addProperty("control_subject_id", (String) null);

            Response response2 = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", idAut)
                    .body(object1.toString())
                    .put(BasicUser.API_USER + "/" + idUser)
                    .then().log().all()
                    .extract().response();
        }
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
                .header("Test-Authorization", idAut)
                .body(object1.toString())
                .put(BasicUser.API_USER + "/" + idUser)
                .then().log().all()
                .extract().response();

        BasicRoles.deleteRole(idAut, idRole);
        BasicUser.deleteUser(idAut, idUser);
    }

    @Test
    public void Step1() {

        Response response = given()
                .when()
                .header("Test-Authorization", idAut)
                .get(BasicUser.API_USER + "/" + 1)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String name = jsonPath.getString("data.full_name");
        Assert.assertTrue(name.equals("Колокольцев Владимир Александрович"));
    }

    @Test
    public void Step2() {
        Response response = given()
                .when()
                .header("Test-Authorization", idUser)
                .get(BasicUser.API_USER + "/" + 2)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("error");

        Assert.assertTrue(error.equals("Доступ к ресурсу запрещен"));
    }

}

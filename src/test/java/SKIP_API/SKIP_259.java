package SKIP_API;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SKIP_259 {
    int idAut1 = 1;
    int idAut2 = 2;
    int idAut6 = 6;
    int idES1, idES2, id1, id2;

    String API_ES = "http://api.skip.rtech.ru/v1/classifiers/assignment_execution_states";
    String API_PERSONAL_SETTING = "http://api.skip.rtech.ru/v1/settings/personal_settings";

    @BeforeTest
    public void step001() {

        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .get(API_ES)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int idE = jsonPath.getInt("data[0].id");
        int idE2 = jsonPath.getInt("data[1].id");
        idES1 = idE;
        idES2 = idE2;
    }

    @Test
    public void step01() {
        JsonObject requestPayload = new JsonObject();
        JsonArray assignments = new JsonArray();
        JsonObject assignment = new JsonObject();
        assignment.addProperty("assignment_execution_state_id", idES1);
        assignment.addProperty("color", "#000000");

        assignments.add(assignment);
        requestPayload.add("assignment_execution_state_colors", assignments);
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .put(API_PERSONAL_SETTING)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int idData = jsonPath.getInt("data.id");
        int id = jsonPath.getInt("data.assignment_execution_state_colors[-1].id");
        String color = jsonPath.getString("data.assignment_execution_state_colors[-1].color");
        int idA = jsonPath.getInt("data.assignment_execution_state_colors[-1].assignment_execution_state.id");
        id1 = id;

        Assert.assertTrue(idData == 1);
        Assert.assertNotNull(id);
        Assert.assertTrue(color.equals("#000000"));
        Assert.assertTrue(idA == idES1);
    }

    @Test
    public void step02() {
        JsonObject requestPayload = new JsonObject();
        JsonArray assignments = new JsonArray();
        JsonObject assignment = new JsonObject();
        assignment.addProperty("assignment_execution_state_id", idES1);
        assignment.addProperty("color", "dfsvdfs");

        assignments.add(assignment);
        requestPayload.add("assignment_execution_state_colors", assignments);
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .put(API_PERSONAL_SETTING)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.assignment_execution_state_colors.0.color[0]");
        Assert.assertTrue(error.equals("имеет неверное значение"));
    }

    @Test
    public void step03() {
        JsonObject requestPayload = new JsonObject();
        JsonArray assignments = new JsonArray();
        JsonObject assignment = new JsonObject();
        assignment.addProperty("assignment_execution_state_id", idES1);
        assignment.addProperty("color", "#0000000");

        assignments.add(assignment);
        requestPayload.add("assignment_execution_state_colors", assignments);
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .put(API_PERSONAL_SETTING)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.assignment_execution_state_colors.0.color[0]");
        Assert.assertTrue(error.equals("имеет неверное значение"));
    }

    @Test
    public void step04() {
        JsonObject requestPayload = new JsonObject();
        JsonArray assignments = new JsonArray();
        JsonObject assignment = new JsonObject();
        assignment.addProperty("assignment_execution_state_id", idES1);
        assignment.addProperty("color", "#0000FF");

        assignments.add(assignment);
        requestPayload.add("assignment_execution_state_colors", assignments);
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .put(API_PERSONAL_SETTING)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int idData = jsonPath.getInt("data.id");
        int id = jsonPath.getInt("data.assignment_execution_state_colors[-1].id");
        String color = jsonPath.getString("data.assignment_execution_state_colors[-1].color");
        int idA = jsonPath.getInt("data.assignment_execution_state_colors[-1].assignment_execution_state.id");
        id2 = id;

        Assert.assertTrue(idData == 1);
        Assert.assertNotNull(id);
        Assert.assertTrue(color.equals("#0000FF"));
        Assert.assertTrue(idA == idES1);
    }

    @Test
    public void step05() {
        JsonObject requestPayload = new JsonObject();
        JsonArray assignments = new JsonArray();
        JsonObject assignment = new JsonObject();
        assignment.addProperty("id", id1);

        assignments.add(assignment);
        requestPayload.add("assignment_execution_state_colors", assignments);
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .put(API_PERSONAL_SETTING)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int idData = jsonPath.getInt("data.id");
        int id = jsonPath.getInt("data.assignment_execution_state_colors[-2].id");
        String color = jsonPath.getString("data.assignment_execution_state_colors[-2].color");
        int idA = jsonPath.getInt("data.assignment_execution_state_colors[-2].assignment_execution_state.id");

        Assert.assertTrue(idData == 1);
        Assert.assertTrue(id == id1);
        Assert.assertTrue(color.equals("#000000"));
        Assert.assertTrue(idA == idES1);
    }

    @Test
    public void step06() {
        JsonObject requestPayload = new JsonObject();
        JsonArray assignments = new JsonArray();
        JsonObject assignment = new JsonObject();
        assignment.addProperty("id", id1);
        assignment.addProperty("assignment_execution_state_id", idES2);

        assignments.add(assignment);
        requestPayload.add("assignment_execution_state_colors", assignments);
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .put(API_PERSONAL_SETTING)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int idData = jsonPath.getInt("data.id");
        int id = jsonPath.getInt("data.assignment_execution_state_colors[-1].id");
        String color = jsonPath.getString("data.assignment_execution_state_colors[-1].color");
        int idA = jsonPath.getInt("data.assignment_execution_state_colors[-1].assignment_execution_state.id");

        Assert.assertTrue(idData == 1);
        Assert.assertTrue(id == id1);
        Assert.assertTrue(color.equals("#000000"));
        Assert.assertTrue(idA == idES2);
    }

    @Test
    public void step07() {
        JsonObject requestPayload = new JsonObject();
        JsonArray assignments = new JsonArray();
        JsonObject assignment = new JsonObject();
        assignment.addProperty("id", id1);
        assignment.addProperty("assignment_execution_state_id", 2314312);

        assignments.add(assignment);
        requestPayload.add("assignment_execution_state_colors", assignments);
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .put(API_PERSONAL_SETTING)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.assignment_execution_state_colors.0.assignment_execution_state_id[0]");

        Assert.assertTrue(error.equals("имеет непредусмотренное значение"));
    }

    public void step08() {
        JsonObject requestPayload = new JsonObject();
        JsonArray assignments = new JsonArray();
        JsonObject assignment = new JsonObject();
        assignment.addProperty("assignment_execution_state_id", idES2);

        assignments.add(assignment);
        requestPayload.add("assignment_execution_state_colors", assignments);
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .put(API_PERSONAL_SETTING)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.assignment_execution_state_colors.0.color[0]");

        Assert.assertTrue(error.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step09() {
        JsonObject requestPayload = new JsonObject();
        JsonArray assignments = new JsonArray();
        JsonObject assignment = new JsonObject();
        assignment.addProperty("id", id1);
        assignment.addProperty("assignment_execution_state_id", idES1);
        assignment.addProperty("color", "#0000FF");
        assignment.addProperty("deleted", true);

        assignments.add(assignment);
        requestPayload.add("assignment_execution_state_colors", assignments);
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .put(API_PERSONAL_SETTING)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String data = jsonPath.getString("data");
        Assert.assertTrue(!data.contains("id:" + id1));
    }

    @Test
    public void step10() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .get(API_PERSONAL_SETTING)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String data = jsonPath.getString("data");
        Assert.assertTrue(!data.contains("id:" + id1));
        Assert.assertTrue(data.contains("id:" + id2));
    }

    @Test
    public void step11() {
        JsonObject requestPayload = new JsonObject();
        JsonArray assignments = new JsonArray();
        JsonObject assignment = new JsonObject();
        assignment.addProperty("assignment_execution_state_id", idES1);
        assignment.addProperty("color", "#000000");

        assignments.add(assignment);
        requestPayload.add("assignment_execution_state_colors", assignments);
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut2)
                .body(requestPayload.toString())
                .put(API_PERSONAL_SETTING)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int idData = jsonPath.getInt("data.id");
        int id = jsonPath.getInt("data.assignment_execution_state_colors[-1].id");
        String color = jsonPath.getString("data.assignment_execution_state_colors[-1].color");
        int idA = jsonPath.getInt("data.assignment_execution_state_colors[-1].assignment_execution_state.id");
        id1 = id;

        Assert.assertTrue(idData == 2);
        Assert.assertNotNull(id);
        Assert.assertTrue(color.equals("#000000"));
        Assert.assertTrue(idA == idES1);
    }

    @Test
    public void step12() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut2)
                .get(API_PERSONAL_SETTING)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int idData = jsonPath.getInt("data.id");
        int id = jsonPath.getInt("data.assignment_execution_state_colors[-1].id");
        String color = jsonPath.getString("data.assignment_execution_state_colors[-1].color");
        int idA = jsonPath.getInt("data.assignment_execution_state_colors[-1].assignment_execution_state.id");
        id1 = id;

        Assert.assertTrue(idData == 2);
        Assert.assertNotNull(id);
        Assert.assertTrue(color.equals("#000000"));
        Assert.assertTrue(idA == idES1);
    }

    @Test
    public void step13() {
        JsonObject requestPayload = new JsonObject();
        JsonArray assignments = new JsonArray();
        JsonObject assignment = new JsonObject();
        assignment.addProperty("assignment_execution_state_id", idES1);
        assignment.addProperty("color", "#000000");

        assignments.add(assignment);
        requestPayload.add("assignment_execution_state_colors", assignments);
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut6)
                .body(requestPayload.toString())
                .put(API_PERSONAL_SETTING)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("error");
        Assert.assertTrue(error.equals("Доступ к ресурсу запрещен"));
    }
}
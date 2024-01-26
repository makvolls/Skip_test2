package SKIP_API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SKIP_45 {
    int id1;
    int idAut1=1;
    int idAut2=6;



    public static String API_ASSIGNMENT_EXECUTION_STATES = "http://api.skip.rtech.ru/v1/classifiers/assignment_execution_states";

    @AfterClass
    public void delete(){
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .log().all()
                .delete(API_ASSIGNMENT_EXECUTION_STATES + "/" + id1)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

    }
    @Test
    public void Step01() {
        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", "test1");
        classifiers.put("short_name", "t1");
        classifiers.put("excluded", "true");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(classifiers)
                .log().all()
                .post(API_ASSIGNMENT_EXECUTION_STATES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.get("data.id");
        String name = jsonPath.get("data.name");
        String short_name = jsonPath.get("data.short_name");
        int priority = jsonPath.get("data.priority");
        Boolean deleted = jsonPath.get("data.deleted");
        Boolean excluded = jsonPath.get("data.excluded");
        Assert.assertNotNull(id);
        Assert.assertTrue(name.equals("test1"));
        Assert.assertTrue(short_name.equals("t1"));
        Assert.assertTrue(excluded);
        Assert.assertTrue(priority == 1);
        Assert.assertTrue(!deleted);

        id1 = id;


    }

    @Test
    public void Step02() {
        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", "b");
        classifiers.put("short_name", "w");
        classifiers.put("excluded", "false");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(classifiers)
                .log().all()
                .put(API_ASSIGNMENT_EXECUTION_STATES + "/" + id1)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        Boolean excluded = jsonPath.get("data.excluded");
        String name = jsonPath.get("data.name");
        String short_name = jsonPath.get("data.short_name");
        Assert.assertTrue(name.equals("b"));
        Assert.assertTrue(short_name.equals("w"));
        Assert.assertTrue(!excluded);
    }

    @Test
    public void Step03() {
        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", "");
        classifiers.put("short_name", "");
        classifiers.put("excluded", "false");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(classifiers)
                .log().all()
                .put(API_ASSIGNMENT_EXECUTION_STATES + "/" + id1)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
      String errors=jsonPath.get("errors.name[0]");
      Assert.assertTrue(errors.equals("не может быть пустым"));
    }

    @Test
    public void Step04() {
        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", ",");
        classifiers.put("short_name", "");
        classifiers.put("excluded", "false");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(classifiers)
                .log().all()
                .put(API_ASSIGNMENT_EXECUTION_STATES + "/" + id1)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        Boolean excluded = jsonPath.get("data.excluded");
        String name = jsonPath.get("data.name");
        String short_name = jsonPath.get("data.short_name");
        Assert.assertTrue(name.equals(","));
        Assert.assertTrue(short_name.equals(""));
        Assert.assertTrue(!excluded);
    }



}

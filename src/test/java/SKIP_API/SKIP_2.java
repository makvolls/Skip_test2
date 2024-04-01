package SKIP_API;

import API.BasicRoles;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class SKIP_2 {
    int id;
    String name;
    String full_name;
    int idAut1 = 1;


    @Test
    public void Step1() {


        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .get(BasicRoles.API_ROLES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        ArrayList<String> data = jsonPath.get("data");
        int id0 = jsonPath.getInt("data[-1].id");
        String name0 = jsonPath.getString("data[-1].name");
        String full_name0 = jsonPath.getString("data[-1].full_name");
        id = id0;
        name = name0;
        full_name = full_name0;
        Assert.assertTrue(!data.isEmpty());
    }


    @Test
    public void Step2() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .params("full_name", name)
                .get(BasicRoles.API_ROLES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int id0 = jsonPath.getInt("data[0].id");
        String name0 = jsonPath.getString("data[0].name");

        Assert.assertTrue(id0 == id);
        Assert.assertTrue(name0.equals(name));

    }

    @Test
    public void Step3() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .params("full_name", name.toUpperCase())
                .get(BasicRoles.API_ROLES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int id0 = jsonPath.getInt("data[0].id");
        String name0 = jsonPath.getString("data[0].name");

        Assert.assertTrue(id0 == id);
        Assert.assertTrue(name0.equals(name));
    }

    @Test
    public void Step4() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .params("full_name", full_name)
                .get(BasicRoles.API_ROLES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int id0 = jsonPath.getInt("data[0].id");
        String name0 = jsonPath.getString("data[0].name");
        String full_name0 = jsonPath.getString("data[0].full_name");

        Assert.assertTrue(id0 == id);
        Assert.assertTrue(name0.equals(name));
        Assert.assertTrue(full_name0.equals(full_name));
    }

    @Test
    public void Step5() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .params("excluded_id", id)
                .get(BasicRoles.API_ROLES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String data = jsonPath.getString("data[]");
        Assert.assertTrue(!data.contains(name));
        Assert.assertTrue(!data.contains(full_name));
    }
}

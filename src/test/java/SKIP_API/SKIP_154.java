package SKIP_API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class SKIP_154 {
    int idAut1 = 1;
    int idAut6 = 6;

    String API_FIELDS = "http://api.skip.rtech.ru/v1/constructor/fields";


    @Test
    public void step01() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .get(API_FIELDS)
                .then().log().all()
                .extract().response();


        JsonPath jsonPath = response.jsonPath();
        List<String> data = jsonPath.get("data");
        Assert.assertNotNull(data);


    }

    @Test
    public void step02() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut6)
                .get(API_FIELDS)
                .then().log().all()
                .extract().response();


        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("error");
        Assert.assertTrue(error.equals("Доступ к ресурсу запрещен"));
    }



}

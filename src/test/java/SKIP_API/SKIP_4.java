package SKIP_API;

import API.DTO.Rights;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SKIP_4 {
    public static String API_RIGHTS = "http://api.skip.rtech.ru/v1/permissions/rights";

    @Test
    public void Step01() {
        Response response = given()
                .header("Test-Authorization", 1)
                .log().all()
                .get(API_RIGHTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String rights = jsonPath.get("data").toString();

        Assert.assertTrue(rights.equals(Rights.rights));

    }
}



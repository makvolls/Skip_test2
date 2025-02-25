package SKIP_API;

import io.restassured.response.Response;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class SKIP_4 {
    int idAut1 = 1;

    public static String API_RIGHTS = "http://api.skip.rtech.ru/v1/permissions/rights";

    @Test
    public void Step01() throws IOException, JSONException {
        String expectedJson = new String(Files.readAllBytes(Paths
                .get("src/test/java/SKIP_API/json_files/SKIP_4/SKIP_4_step01.json")));
        Response response = given()
                .header("Test-Authorization", idAut1)
                .log().all()
                .get(API_RIGHTS)
                .then().log().all()
                .extract().response();
        String jsonResponse = response.getBody().asString();

        JSONAssert.assertEquals(expectedJson, jsonResponse, true);

    }
}



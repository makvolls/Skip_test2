package SKIP_API;

import io.restassured.response.Response;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class SKIP_226 {
    int idAut1 = 1;
    String API_TABULAR_REPORTS = "http://api.skip.rtech.ru/v1/constructor/tabular_reports/classifiers";

    @Test
    public void step01() throws IOException, JSONException {

        String expectedJson = new String(Files.readAllBytes(Paths
                .get("src/test/java/SKIP_API/json_files/SKIP_226/SKIP_226_step01.json")));
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .get(API_TABULAR_REPORTS)
                .then().log().all()
                .extract().response();
        String jsonResponse = response.getBody().asString();

        JSONAssert.assertEquals(expectedJson, jsonResponse, true);
    }
}
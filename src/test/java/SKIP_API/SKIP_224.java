package SKIP_API;

import Utils.RandomGenerateText;
import com.google.gson.JsonObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class SKIP_224 {

    int idAut1 = 1;
    String API_CONSTRUCTOR_TABLES = "http://api.skip.rtech.ru/v1/constructor/tables";
    int idTables;
    String test1 = RandomGenerateText.generateUniqeTextRussianString(7);


    @Test
    public void step01() {
        JsonObject requestPayload = new JsonObject();
        requestPayload.addProperty("name", test1);
        requestPayload.addProperty("row_type_id", "documents");
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .post(API_CONSTRUCTOR_TABLES)
                .then().log().all()
                .extract().response();


        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.getInt("data.id");
        idTables = id;
        String name = jsonPath.getString("data.name");
        String documents = jsonPath.getString("data.row_type.id");
        boolean mainwindow = jsonPath.getBoolean("data.display_on_mainwindow");

        Assert.assertNotNull(id);
        Assert.assertTrue(name.equals(test1));
        Assert.assertTrue(documents.equals("documents"));
        Assert.assertTrue(mainwindow);
    }

    @Test
    public void step02() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .delete(API_CONSTRUCTOR_TABLES + "/" + idTables)
                .then().log().all()
                .extract().response();

        Assert.assertTrue(response.statusCode() == 204);
    }

    @Test
    public void step03() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .get(API_CONSTRUCTOR_TABLES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        ArrayList<String> data = jsonPath.get("data");

        Assert.assertTrue(!data.toString().contains(test1));

    }

}

package SKIP_API;

import com.google.gson.JsonObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SKIP_217 {

    String API_DOCUMENTS = "http://api.skip.rtech.ru/v1/documents";
    int idAut1 = 1;
    int idDoc;


    @Test
    public void step01() {
        JsonObject requestPayload = new JsonObject();
        requestPayload.addProperty("skip_reg_date", "2024-01-17T06:06:31.485Z");
        requestPayload.addProperty("control_official_id", "54e35f6179fa007422000001");
        requestPayload.addProperty("document_name", "Тест 404");
        requestPayload.addProperty("document_execution_state_id", 11);
        requestPayload.addProperty("skip_document_type_id", 32);
        requestPayload.addProperty("internal", false);
        requestPayload.addProperty("short_description", "404");


        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        int id = jsonPath.getInt("data.id");
        String skip_reg_date = jsonPath.getString("data.skip_reg_date");
        String control_official_id = jsonPath.getString("data.control_official.id");
        String document_name = jsonPath.getString("data.document_name");
        int document_execution_state_id = jsonPath.get("data.document_execution_state.id");
        int skip_document_type_id = jsonPath.getInt("data.skip_document_type.id");
        boolean internal = jsonPath.getBoolean("data.internal");
        String short_description = jsonPath.getString("data.short_description");
        String control_subject = jsonPath.getString("data.control_subject");
        idDoc = id;


        Assert.assertNotNull(id);
        Assert.assertTrue(skip_reg_date.equals("2024-01-17T06:06:31.485Z"));
        Assert.assertTrue(control_official_id.equals("54e35f6179fa007422000001"));
        Assert.assertTrue(document_name.equals("Тест 404"));
        Assert.assertTrue(document_execution_state_id == 11);
        Assert.assertTrue(skip_document_type_id == 32);
        Assert.assertTrue(!internal);
        Assert.assertTrue(short_description.equals("404"));
        Assert.assertNull(control_subject);

    }

    @Test
    public void step02() {
        JsonObject requestPayload = new JsonObject();
        requestPayload.addProperty("skip_reg_date", "2024-01-17T06:06:31.485Z");
        requestPayload.addProperty("control_official_id", "54e35f6179fa007422000001");
        requestPayload.addProperty("document_name", "Тест 404");
        requestPayload.addProperty("document_execution_state_id", 11);
        requestPayload.addProperty("skip_document_type_id", 32);
        requestPayload.addProperty("internal", false);
        requestPayload.addProperty("short_description", "404");


        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .put(API_DOCUMENTS + "/" + idDoc)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        int id = jsonPath.getInt("data.id");
        String skip_reg_date = jsonPath.getString("data.skip_reg_date");
        String control_official_id = jsonPath.getString("data.control_official.id");
        String document_name = jsonPath.getString("data.document_name");
        int document_execution_state_id = jsonPath.get("data.document_execution_state.id");
        int skip_document_type_id = jsonPath.getInt("data.skip_document_type.id");
        boolean internal = jsonPath.getBoolean("data.internal");
        String short_description = jsonPath.getString("data.short_description");
        String control_subject = jsonPath.getString("data.control_subject");
        idDoc = id;


        Assert.assertNotNull(id);
        Assert.assertTrue(skip_reg_date.equals("2024-01-17T06:06:31.485Z"));
        Assert.assertTrue(control_official_id.equals("54e35f6179fa007422000001"));
        Assert.assertTrue(document_name.equals("Тест 404"));
        Assert.assertTrue(document_execution_state_id == 11);
        Assert.assertTrue(skip_document_type_id == 32);
        Assert.assertTrue(!internal);
        Assert.assertTrue(short_description.equals("404"));
        Assert.assertNull(control_subject);

    }
}
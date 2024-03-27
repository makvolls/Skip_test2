package SKIP_API;

import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.keyStore;

public class SKIP_239 {

    // Endpoints:
    private static String API_DOCUMENTS = "http://api.skip.rtech.ru/v1/documents";
    private static String API_IMAGES_SKIP = "http://api.skip.rtech.ru/v1/images";
    private static String API_USER = "http://api.skip.rtech.ru/v1/permissions/users";
    private static String API_DOC_TYPE = "http://api.skip.rtech.ru/v1/classifiers/document_types";
    private static String API_DOCUMENT_EXECUTION_STATES = "http://api.skip.rtech.ru" +
            "/v1/classifiers/document_execution_states";
    private static String API_OSHS_MVD_OFFICIALS="http://api.skip.rtech.ru/v1/oshs/mvd/officials";

    // Authorization parameters:
    int userIdOne = 1; // User that have all rights, role id = 29
    int userIdSix = 6; // User that have right - "show_roles"

    // Values for document
    int idDocumentExecutionState; // Created document execution state value
    int idDocumentType; // Created document type value
    String idRandomOfficial; // Random official

    // Variables
    int idCreatedDocument; // Document's id that will be created in step 1

    // Name for created element
    String nameTemplate = "SKIP_239_Autotest";
    String shortNameTemplate = "SKIP_239";

    // Files
    File txtFile = new File("C:\\Users\\mkalugin\\Desktop\\Archives for testcases\\SKIP_362\\txt_file.txt");
    File docxFile = new File("C:\\Users\\mkalugin\\Desktop\\Archives for testcases\\SKIP_362\\docx_file.docx");

    // Logger
    private static final Logger logger = Logger.getLogger(SKIP_239.class);

    @BeforeTest(enabled = true)
    public void setup(){
        logger.info("Before test method is running.");

        // Check that user with id 6 has role with id = 5
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .get(API_USER + "/6")
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String roleId = jsonPath.getString("data.roles[0].id");

        if (roleId.equals("5")){
            System.out.println("User has role id - 5");
        } else {
            System.out.println("User has role id - " + roleId);
            // Change role id
            String requestBody = "{\"role_ids\": [5]}";
            Response responseChangeRoleId = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", 1)
                    .body(requestBody)
                    .put(API_USER + "/6")
                    .then().log().all()
                    .extract().response();

            JsonPath jsonPathChangeRoleId = responseChangeRoleId.jsonPath();

            String roleIdUpdated = jsonPathChangeRoleId.getString("data.roles[0].id");

            System.out.println("New role id - " + roleIdUpdated);
        }

        // Create document execution state
        Map<String, String> requestBodyDocumentExSt = new HashMap<>();
        requestBodyDocumentExSt.put("name", nameTemplate);
        requestBodyDocumentExSt.put("short_name", shortNameTemplate);
        requestBodyDocumentExSt.put("excluded", "false");
        Response responseDocumentExecutionStateCreate = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBodyDocumentExSt)
                .post(API_DOCUMENT_EXECUTION_STATES)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathDocumentExecutionStateCreate = responseDocumentExecutionStateCreate.jsonPath();
        idDocumentExecutionState = jsonPathDocumentExecutionStateCreate.getInt("data.id");
        System.out.println("Id document ex state - " + idDocumentExecutionState);

        // Create document type
        Map<String,Object> requestBodyDocumentType = new HashMap<>();
        requestBodyDocumentType.put("name",nameTemplate);
        requestBodyDocumentType.put("short_name",shortNameTemplate);
        requestBodyDocumentType.put("internal", false);
        requestBodyDocumentType.put("genitive_name",shortNameTemplate);
        requestBodyDocumentType.put("excluded", true);
        Response responseDocumentTypeCreate = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBodyDocumentType)
                .post(API_DOC_TYPE)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathDocumentTypeCreate = responseDocumentTypeCreate.jsonPath();
        idDocumentType = jsonPathDocumentTypeCreate.getInt("data.id");
        System.out.println("Created document type id - " + idDocumentType);

        // Random official from officials_oshs_mvd
        Response responseOfficials = given()
                .when()
                .params("items","100")
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .get(API_OSHS_MVD_OFFICIALS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathOfficials = responseOfficials.jsonPath();

        List<String> idOfficials = jsonPathOfficials.getList("data.id");
        Random randomOff = new Random();
        String randomIdOff = idOfficials.get(randomOff.nextInt(idOfficials.size()));
        System.out.println("Random id : " + randomIdOff);
        idRandomOfficial = randomIdOff;
    }

    @AfterTest(enabled = true)
    public void tearDown(){
        logger.info("After test method is running.");

        // Delete document execution state
        Response responseDESDeleteFirst = (Response) given()
                .header("Test-Authorization", 1)
                .delete(API_DOCUMENT_EXECUTION_STATES + String.format("/%s",idDocumentExecutionState))
                .then().log().all()
                .extract();

        //JsonPath jsonPathDESDeleteFirst = responseDESDeleteFirst.jsonPath();

        // Delete document type
        Response responseDocumentType = (Response) given()
                .header("Test-Authorization", 1)
                .delete(API_DOC_TYPE + String.format("/%s",idDocumentType))
                .then().log().all()
                .extract();

        // JsonPath jsonPathDocumentType = responseDocumentType.jsonPath();
    }

    @Test
    public void step01(){
        try {
            JsonObject requestBody = new JsonObject();
            requestBody.addProperty("skip_reg_date", "2024-01-17T06:06:31.485Z");
            requestBody.addProperty("control_official_id", idRandomOfficial);
            requestBody.addProperty("document_name", "name");
            requestBody.addProperty("document_execution_state_id", idDocumentExecutionState);
            requestBody.addProperty("skip_document_type_id", idDocumentType);
            requestBody.addProperty("internal", false);
            requestBody.addProperty("short_description", "desc");

            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .body(requestBody.toString())
                    .when()
                    .post(API_DOCUMENTS)
                    .then().log().all()
                    .extract().response();

            JsonPath jsonPath = response.jsonPath();

            idCreatedDocument = jsonPath.getInt("data.id");
            logger.info("Created document's id - " + idCreatedDocument);
            String shortDesc = jsonPath.getString("data.short_description");
            String documentName = jsonPath.getString("data.document_name");
            Assert.assertEquals(shortDesc, "desc");
            Assert.assertEquals(documentName, "name");

            logger.info("Step 1 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step02(){
        try {
            Response response = given()
                    .when()
                    .multiPart("document_id",idCreatedDocument)
                    .multiPart("name","first")
                    .multiPart("original_file",txtFile)
                    .multiPart("mark_id", 24)
                    .contentType(ContentType.MULTIPART)
                    .header("Test-Authorization", userIdOne)
                    .post(API_IMAGES_SKIP)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            String name = jsonPath.getString("data.name");
            String fullName = jsonPath.getString("data.full_name");
            int markId = jsonPath.getInt("data.mark.id");

            Assert.assertEquals(name, "first");
            Assert.assertEquals(markId, 24);
            Assert.assertTrue(fullName.contains("txt"));

            logger.info("Step 2 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step03(){
        try {
            Response response = given()
                    .when()
                    .multiPart("document_id",idCreatedDocument)
                    .multiPart("name","first")
                    .multiPart("original_file",docxFile)
                    .multiPart("mark_id", 24)
                    .contentType(ContentType.MULTIPART)
                    .header("Test-Authorization", userIdOne)
                    .post(API_IMAGES_SKIP)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            String name = jsonPath.getString("data.name");
            String fullName = jsonPath.getString("data.full_name");
            int markId = jsonPath.getInt("data.mark.id");

            Assert.assertEquals(name, "first");
            Assert.assertEquals(markId, 24);
            Assert.assertTrue(fullName.contains("docx"));

            logger.info("Step 3 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step04(){
        try {
            Response response = given()
                    .when()
                    .params("document_id",idCreatedDocument)
                    .params("query","")
                    .params("with_text",false)
                    .params("page","")
                    .params("items","")
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            String fullNameFirst = jsonPath.getString("data[0].full_name");
            String fullNameSecond = jsonPath.getString("data[1].full_name");
            logger.info("Full name first element : " + fullNameFirst);
            logger.info("Full name second element : " + fullNameSecond);
            Assert.assertTrue(fullNameFirst.contains("txt"));
            Assert.assertTrue(fullNameSecond.contains("docx"));

            logger.info("Step 4 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step05(){
        try {
            Response response = given()
                    .when()
                    .params("document_id",idCreatedDocument)
                    .params("query","docx")
                    .params("with_text",true)
                    .params("page","")
                    .params("items","")
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            String fullNameFirst = jsonPath.getString("data[0].full_name");
            List<Object> data = jsonPath.getList("data");
            logger.info("Full name first element : " + fullNameFirst);
            logger.info("Element's count : " + data.toArray().length);
            Assert.assertTrue(fullNameFirst.contains("docx"));
            Assert.assertTrue(data.toArray().length == 1);

            logger.info("Step 5 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step06(){
        try {
            Response response = given()
                    .when()
                    .params("document_id",idCreatedDocument)
                    .params("query","txt")
                    .params("with_text",true)
                    .params("page","")
                    .params("items","")
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            String fullNameFirst = jsonPath.getString("data[0].full_name");
            List<Object> data = jsonPath.getList("data");
            logger.info("Full name first element : " + fullNameFirst);
            logger.info("Element's count : " + data.toArray().length);
            Assert.assertTrue(fullNameFirst.contains("txt"));
            Assert.assertTrue(data.toArray().length == 1);

            logger.info("Step 6 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step07(){
        try {
            Response response = given()
                    .when()
                    .params("document_id",idCreatedDocument)
                    .params("query","Привет")
                    .params("with_text",true)
                    .params("page","")
                    .params("items","")
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            List<Object> data = jsonPath.getList("data");
            logger.info("Element's count : " + data.toArray().length);
            Assert.assertTrue(data.toArray().length == 2);

            logger.info("Step 7 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step08(){
        try {
            Response response = given()
                    .when()
                    .params("document_id",idCreatedDocument)
                    .params("query","Hello")
                    .params("with_text",true)
                    .params("page","")
                    .params("items","")
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            List<Object> data = jsonPath.getList("data");
            logger.info("Element's count : " + data.toArray().length);
            Assert.assertTrue(data.toArray().length == 0);

            logger.info("Step 8 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step09(){
        try {
            Response response = given()
                    .when()
                    .params("document_id",idCreatedDocument)
                    .params("query","Привет docx")
                    .params("with_text",true)
                    .params("page","")
                    .params("items","")
                    .header("Test-Authorization", userIdSix)
                    .get(API_IMAGES_SKIP)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            String recognizedTextFirst = jsonPath.getString("data[0].recognized_text");
            String recognizedTextSecond = jsonPath.getString("data[1].recognized_text");
            logger.info("Text from first element : " + recognizedTextFirst);
            logger.info("Text from second element : " + recognizedTextSecond);
            Assert.assertTrue(recognizedTextFirst.contains("Привет"));
            Assert.assertTrue(recognizedTextFirst.contains("docx"));
            Assert.assertTrue(recognizedTextSecond.contains("Привет"));
            Assert.assertFalse(recognizedTextSecond.contains("docx"));

            logger.info("Step 9 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }
}

package SKIP_API;

import com.google.gson.JsonArray;
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
import java.util.*;

import static io.restassured.RestAssured.given;

public class SKIP_241 {

    // Endpoints:
    private static String API_DOCUMENTS = "http://api.skip.rtech.ru/v1/documents";
    private static String API_IMAGES_SKIP = "http://api.skip.rtech.ru/v1/images";
    private static String API_USER = "http://api.skip.rtech.ru/v1/permissions/users";
    private static String API_DOC_TYPE = "http://api.skip.rtech.ru/v1/classifiers/document_types";
    private static String API_DOCUMENT_EXECUTION_STATES = "http://api.skip.rtech.ru" +
            "/v1/classifiers/document_execution_states";
    private static String API_OSHS_MVD_OFFICIALS="http://api.skip.rtech.ru/v1/oshs/mvd/officials";
    private static String API_EXECUTIONS="http://api.skip.rtech.ru/v1/executions";
    private static String API_EVENTS="http://api.skip.rtech.ru/v1/executions/events";
    private static String API_IMAGES_SEARCH="http://api.skip.rtech.ru/v1/images/search_classifiers";

    // Authorization parameters:
    int userIdOne = 1; // User that have all rights, role id = 29

    // Values for document
    int idDocumentExecutionState; // Created document execution state value
    int idDocumentType; // Created document type value
    String idRandomOfficial; // Random official
    String fullNameImage; // Image's full name form step 2
    List<String> imagesFirst; // Images that have deadline date - "07.03.2024"
    List<String> imagesSecond; // Images that haven't deadline date - "07.03.2024"

    // Variables
    int idCreatedDocument; // Document's id that will be created in prediction
    int idExecutorFirst; // Executor's id that will be created in prediction first element
    int idExecutorSecond; // Executor's id that will be created in prediction fourth element
    int idExecutionFirst; // Execution's id that will be created in prediction first element
    int idExecutionSecond; // Execution's id that will be created in prediction second element
    int idEventFirst; // Event's id that will be created in prediction first element
    int idEventSecond; // Event's id that will be created in prediction second element
    int idEventThird; // Event's id that will be created in prediction third element

    // Name for created element
    String nameTemplate = "SKIP_241_Autotest";
    String shortNameTemplate = "SKIP_241";

    // Files
    File txtFile = new File("C:\\Users\\mkalugin\\Desktop\\Archives for testcases\\SKIP_362\\txt_file.txt");
    File docxFile = new File("C:\\Users\\mkalugin\\Desktop\\Archives for testcases\\SKIP_362\\docx_file.docx");

    // Logger
    private static final Logger logger = Logger.getLogger(SKIP_241.class);

    @BeforeTest(enabled = true)
    public void setup(){
        logger.info("Before test method is running.");

        // Check that user with id 6 has role with id = 5
        Response response = null;
        String roleId = null;
        try {
            response = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", 1)
                    .get(API_USER + "/6")
                    .then().log().all()
                    .extract().response();

            JsonPath jsonPath = response.jsonPath();

            roleId = jsonPath.getString("data.roles[0].id");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }

        if (roleId.equals("5")){
            logger.info("User has role id - 5");
        } else {
            logger.info("User has role id - " + roleId);
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
            logger.info("New role id - " + roleIdUpdated);
        }

        // Create document execution state
        try {
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
            logger.info("Id document ex state - " + idDocumentExecutionState);
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }

        // Create document type
        try {
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
            logger.info("Created document type id - " + idDocumentType);
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }

        // Random official from officials_oshs_mvd
        try {
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
            logger.info("Random id : " + randomIdOff);
            idRandomOfficial = randomIdOff;
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }

        // First step in prediction

        // Create document

        try {
            JsonObject requestBody = new JsonObject();
            requestBody.addProperty("skip_reg_date", "2024-01-17T06:06:31.485Z");
            requestBody.addProperty("control_official_id", idRandomOfficial);
            requestBody.addProperty("document_name", "name");
            requestBody.addProperty("document_execution_state_id", idDocumentExecutionState);
            requestBody.addProperty("skip_document_type_id", idDocumentType);
            requestBody.addProperty("internal", false);
            requestBody.addProperty("short_description", "desc");
            JsonArray assignments = new JsonArray();
            JsonObject assignmentFirst = new JsonObject();
            assignmentFirst.addProperty("number","1");
            assignmentFirst.addProperty("content","content");
            JsonArray executorsFirst = new JsonArray();
            JsonObject executorFirst = new JsonObject();
            executorFirst.addProperty("organization_id","6426a7c3379100001e000001");
            executorsFirst.add(executorFirst);
            assignmentFirst.add("executors",executorsFirst);
            assignments.add(assignmentFirst);
            JsonObject assignmentSecond = new JsonObject();
            assignmentSecond.addProperty("number","1");
            assignmentSecond.addProperty("content","content");
            assignments.add(assignmentSecond);
            JsonObject assignmentThird = new JsonObject();
            assignmentThird.addProperty("number","1");
            assignmentThird.addProperty("content","content");
            assignments.add(assignmentThird);
            JsonObject assignmentFourth = new JsonObject();
            assignmentFourth.addProperty("number","1");
            assignmentFourth.addProperty("content","content");
            JsonArray executorsSecond = new JsonArray();
            JsonObject executorSecond = new JsonObject();
            executorSecond.addProperty("organization_id","6426a7c3379100001e000001");
            executorsSecond.add(executorSecond);
            assignmentFourth.add("executors",executorsSecond);
            assignments.add(assignmentFourth);
            JsonObject assignmentFifth = new JsonObject();
            assignmentFifth.addProperty("number","1");
            assignmentFifth.addProperty("content","content");
            assignments.add(assignmentFifth);
            JsonObject assignmentSixth = new JsonObject();
            assignmentSixth.addProperty("number","1");
            assignmentSixth.addProperty("content","content");
            assignments.add(assignmentSixth);
            requestBody.add("assignments", assignments);

            Response responseDocument = given()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .body(requestBody.toString())
                    .when()
                    .post(API_DOCUMENTS)
                    .then().log().all()
                    .extract().response();

            JsonPath jsonPathDocument = responseDocument.jsonPath();
            logger.info("Document is created!");

            idCreatedDocument = jsonPathDocument.getInt("data.id");
            idExecutorFirst = jsonPathDocument.getInt("data.assignments[0].executors[0].id");
            idExecutorSecond = jsonPathDocument.getInt("data.assignments[3].executors[0].id");
            logger.info("Created document id - " + idCreatedDocument);
            logger.info("Executor first id - " + idExecutorFirst);
            logger.info("Executor second id - " + idExecutorSecond);

        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
        // Second step in prediction

        try {
            Response responseExecutionsFirst = given()
                    .when()
                    .multiPart("executor_id", idExecutorFirst)
                    .multiPart("deadline_date", "07.03.2024")
                    .multiPart("deadline_basis_id", 104)
                    .multiPart("document_name","test")
                    .multiPart("document_type","Documents::Document")
                    .multiPart("document_id",idCreatedDocument)
                    .multiPart("images[][original_file]",txtFile)
                    .contentType(ContentType.MULTIPART)
                    .header("Test-Authorization", userIdOne)
                    .post(API_EXECUTIONS)
                    .then().log().all()
                    .extract().response();
            JsonPath jsonPathExecutionsFirst = responseExecutionsFirst.jsonPath();

            idExecutionFirst = jsonPathExecutionsFirst.getInt("data.id");
            logger.info("Created execution id - " + idExecutionFirst);

        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }

        try {
            Response responseExecutionsSecond = given()
                    .when()
                    .multiPart("executor_id", idExecutorSecond)
                    .multiPart("deadline_date", "18.03.2024")
                    .multiPart("deadline_basis_id", 104)
                    .multiPart("document_name","test")
                    .multiPart("document_type","Documents::Document")
                    .multiPart("document_id",idCreatedDocument)
                    .multiPart("images[][original_file]",docxFile)
                    .contentType(ContentType.MULTIPART)
                    .header("Test-Authorization", userIdOne)
                    .post(API_EXECUTIONS)
                    .then().log().all()
                    .extract().response();
            JsonPath jsonPathExecutionsSecond = responseExecutionsSecond.jsonPath();

            idExecutionSecond = jsonPathExecutionsSecond.getInt("data.id");
            logger.info("Created execution id - " + idExecutionSecond);

        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }

        // Third step in prediction

        try {
            Response responseEventFirst = given()
                    .when()
                    .multiPart("execution_id", idExecutionFirst)
                    .multiPart("event_id", 149)
                    .multiPart("state_id", 60)
                    .multiPart("basis_id",104)
                    .multiPart("control_state_id",64)
                    .multiPart("document_type","Documents::Document")
                    .multiPart("document_id",idCreatedDocument)
                    .multiPart("images[][original_file]",txtFile)
                    .contentType(ContentType.MULTIPART)
                    .header("Test-Authorization", 1)
                    .post(API_EVENTS)
                    .then().log().all()
                    .extract().response();
            JsonPath jsonPathEventFirst = responseEventFirst.jsonPath();

            idEventFirst = jsonPathEventFirst.getInt("data.id");
            logger.info("Event was created with id - " + idEventFirst);

        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }

        // Fourth step in prediction

        try {
            Response responseEventSecond = given()
                    .when()
                    .multiPart("execution_id", idExecutionFirst)
                    .multiPart("event_id", 149)
                    .multiPart("state_id", 60)
                    .multiPart("basis_id",104)
                    .multiPart("control_state_id",64)
                    .multiPart("document_type","Documents::Document")
                    .multiPart("document_id",idCreatedDocument)
                    .multiPart("images[][original_file]",docxFile)
                    .contentType(ContentType.MULTIPART)
                    .header("Test-Authorization", 1)
                    .post(API_EVENTS)
                    .then().log().all()
                    .extract().response();
            JsonPath jsonPathEventSecond = responseEventSecond.jsonPath();

            idEventSecond = jsonPathEventSecond.getInt("data.id");
            logger.info("Event was created with id - " + idEventSecond);

        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }

        // Fifth step in prediction

        try {
            Response responseEventThird = given()
                    .when()
                    .multiPart("execution_id", idExecutionSecond)
                    .multiPart("event_id", 149)
                    .multiPart("state_id", 60)
                    .multiPart("basis_id",104)
                    .multiPart("control_state_id",64)
                    .multiPart("document_type","Documents::Document")
                    .multiPart("document_id",idCreatedDocument)
                    .multiPart("images[][original_file]",docxFile)
                    .contentType(ContentType.MULTIPART)
                    .header("Test-Authorization", 1)
                    .post(API_EVENTS)
                    .then().log().all()
                    .extract().response();
            JsonPath jsonPathEventThird = responseEventThird.jsonPath();

            idEventThird = jsonPathEventThird.getInt("data.id");
            logger.info("Event was created with id - " + idEventThird);

        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }

    }

    @AfterTest(enabled = true)
    public void tearDown(){
        logger.info("After test method is running.");

        // Delete document execution state
        try {
            Response responseDESDeleteFirst = (Response) given()
                    .header("Test-Authorization", 1)
                    .delete(API_DOCUMENT_EXECUTION_STATES + String.format("/%s",idDocumentExecutionState))
                    .then().log().all()
                    .extract();

            //JsonPath jsonPathDESDeleteFirst = responseDESDeleteFirst.jsonPath();
            logger.info("Document execution state is deleted!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }

        // Delete document type
        try {
            Response responseDocumentType = (Response) given()
                    .header("Test-Authorization", 1)
                    .delete(API_DOC_TYPE + String.format("/%s",idDocumentType))
                    .then().log().all()
                    .extract();

            // JsonPath jsonPathDocumentType = responseDocumentType.jsonPath();
            logger.info("Document type is deleted!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step01(){
        try {
            Response response = given()
                    .when()
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SEARCH)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            String nameFirstField = jsonPath.getString("data.fields[0].name");
            logger.info("First field name : " + nameFirstField);
            Assert.assertEquals(nameFirstField, "Наименование ЭО");
            String nameSecondField = jsonPath.getString("data.fields[1].name");
            logger.info("Second field name : " + nameSecondField);
            Assert.assertEquals(nameSecondField, "Номер поручения");
            String nameThirdField = jsonPath.getString("data.fields[2].name");
            logger.info("Third field name : " + nameThirdField);
            Assert.assertEquals(nameThirdField, "Контрольный срок");
            String nameFourthField = jsonPath.getString("data.fields[3].name");
            logger.info("Fourth field name : " + nameFourthField);
            Assert.assertEquals(nameFourthField, "Исполнитель");
            String nameFifthField = jsonPath.getString("data.fields[4].name");
            logger.info("Fifth field name : " + nameFifthField);
            Assert.assertEquals(nameFifthField, "Событие");
            String nameSixthField = jsonPath.getString("data.fields[5].name");
            logger.info("Sixth field name : " + nameSixthField);
            Assert.assertEquals(nameSixthField, "Дата события");

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
                    .params("document_id",idCreatedDocument)
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            fullNameImage = jsonPath.getString("data[0].full_name");
            logger.info("Image's full name first element - " + fullNameImage);
            List<Integer> imagesIds = jsonPath.getList("data.id");
            logger.info("Array size : " + imagesIds.size());
            Assert.assertTrue(!imagesIds.isEmpty());

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
                    .params("document_id",idCreatedDocument)
                    .params("search_fields[][field_id]", "images.full_name")
                    .params("search_fields[][condition_id]", "equal")
                    .params("search_fields[][value]", fullNameImage)
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();
            List<String> fullNameResponse = jsonPath.getList("data.full_name");
            logger.info("Search result : " + fullNameResponse);
            for (String fullName : fullNameResponse){
                Assert.assertEquals(fullName, "txt_file.txt");
            }

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
                    .params("search_fields[][field_id]", "images.full_name")
                    .params("search_fields[][condition_id]", "equal")
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();
            String error = jsonPath.getString("errors.search_fields.0.value[0]");
            logger.info("Error message : " + error);
            Assert.assertEquals(error, "не может быть пустым");

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
                    .params("search_fields[][field_id]", "images.full_name")
                    .params("search_fields[][value]", fullNameImage)
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();
            String error = jsonPath.getString("errors.search_fields.0.condition_id[0]");
            logger.info("Error message : " + error);
            Assert.assertEquals(error, "не может быть пустым");

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
                    .params("search_fields[][condition_id]", "equal")
                    .params("search_fields[][value]", fullNameImage)
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();
            String error = jsonPath.getString("errors.search_fields.0.field_id[0]");
            logger.info("Error message : " + error);
            Assert.assertEquals(error, "не может быть пустым");

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
                    .params("search_fields[][field_id]", "images.full_name")
                    .params("search_fields[][condition_id]", "equal")
                    .params("search_fields[][value]", "txt_file")
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();
            String data = jsonPath.getString("data");
            logger.info("Response data :" + data);
            Assert.assertEquals(data, "[]");

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
                    .params("search_fields[][field_id]", "images.full_name")
                    .params("search_fields[][condition_id]", "not_equal")
                    .params("search_fields[][value]", fullNameImage)
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();
            List<String> fullNameResponse = jsonPath.getList("data.full_name");
            logger.info("Search result : " + fullNameResponse);
            for (String fullName : fullNameResponse){
                Assert.assertNotEquals(fullName, "txt_file.pdf");
            }

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
                    .params("search_fields[][field_id]", "images.full_name")
                    .params("search_fields[][condition_id]", "regexp")
                    .params("search_fields[][value]", "tx")
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();
            List<String> fullNameResponse = jsonPath.getList("data.full_name");
            logger.info("Search result : " + fullNameResponse);
            for (String fullName : fullNameResponse){
                Assert.assertTrue(fullName.contains("tx"));
            }

            logger.info("Step 9 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step10(){
        try {
            Response response = given()
                    .when()
                    .params("document_id",idCreatedDocument)
                    .params("search_fields[][field_id]", "images.assignment_number")
                    .params("search_fields[][condition_id]", "equal")
                    .params("search_fields[][value]", "1")
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();
            List<String> fullNameResponse = jsonPath.getList("data.full_name");
            logger.info("Search result : " + fullNameResponse);
            Assert.assertTrue(!fullNameResponse.isEmpty());

            logger.info("Step 10 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step11(){
        try {
            Response response = given()
                    .when()
                    .params("document_id",idCreatedDocument)
                    .params("search_fields[][field_id]", "images.assignment_number")
                    .params("search_fields[][condition_id]", "not_equal")
                    .params("search_fields[][value]", "1")
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();
            String data = jsonPath.getString("data");
            logger.info("Response data :" + data);
            Assert.assertEquals(data, "[]");

            logger.info("Step 11 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step12(){
        try {
            Response response = given()
                    .when()
                    .params("document_id",idCreatedDocument)
                    .params("search_fields[][field_id]", "images.assignment_number")
                    .params("search_fields[][condition_id]", "empty")
                    .params("search_fields[][value]", "1")
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();
            String data = jsonPath.getString("data");
            logger.info("Response data :" + data);
            Assert.assertEquals(data, "[]");

            logger.info("Step 12 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step13(){
        try {
            Response response = given()
                    .when()
                    .params("document_id",idCreatedDocument)
                    .params("search_fields[][field_id]", "images.deadline_date")
                    .params("search_fields[][condition_id]", "equal")
                    .params("search_fields[][value]", "2024-03-07")
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();
            imagesFirst = jsonPath.getList("data.full_name");
            logger.info("Search result : " + imagesFirst);
            Assert.assertTrue(!imagesFirst.isEmpty());

            logger.info("Step 13 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step14(){
        try {
            Response response = given()
                    .when()
                    .params("document_id",idCreatedDocument)
                    .params("search_fields[][field_id]", "images.deadline_date")
                    .params("search_fields[][condition_id]", "not_equal")
                    .params("search_fields[][value]", "2024-03-07")
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();
            imagesSecond = jsonPath.getList("data.full_name");
            logger.info("Image's full name from previous request : " + imagesFirst);
            logger.info("Search result : " + imagesSecond);
            Assert.assertFalse(imagesFirst.equals(imagesSecond));

            logger.info("Step 14 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step15(){
        try {
            Response response = given()
                    .when()
                    .params("document_id",idCreatedDocument)
                    .params("search_fields[][field_id]", "images.deadline_date")
                    .params("search_fields[][condition_id]", "interval")
                    .params("search_fields[][value][from]", "2024-03-07")
                    .params("search_fields[][value][to]", "2024-03-18")
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();
            List<String> intervalImages = jsonPath.getList("data.full_name");
            logger.info("Images with deadline interval from 2024-03-07 to 2024-03-18 : " + intervalImages);
            Assert.assertTrue(!intervalImages.isEmpty());

            logger.info("Step 15 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step16(){
        try {
            Response response = given()
                    .when()
                    .params("document_id",idCreatedDocument)
                    .params("search_fields[][field_id]", "images.deadline_date")
                    .params("search_fields[][condition_id]", "interval")
                    .params("search_fields[][value][from]", "2024-03-18")
                    .params("search_fields[][value][to]", "2024-03-18")
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();
            List<String> DateImages = jsonPath.getList("data.full_name");
            logger.info("Images with deadline date 2024-03-18 : " + DateImages);
            Assert.assertTrue(!DateImages.isEmpty());

            logger.info("Step 16 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step17(){
        try {
            Response response = given()
                    .when()
                    .params("document_id",idCreatedDocument)
                    .params("search_fields[][field_id]", "images.deadline_date")
                    .params("search_fields[][condition_id]", "empty")
                    .params("search_fields[][value]", "2024-03-07")
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();
            String data = jsonPath.getString("data");
            logger.info("Response data :" + data);
            Assert.assertEquals(data, "[]");

            logger.info("Step 17 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step18(){
        try {
            Response response = given()
                    .when()
                    .params("document_id",idCreatedDocument)
                    .params("search_fields[][field_id]", "images.executor_organization_id")
                    .params("search_fields[][condition_id]", "equal")
                    .params("search_fields[][value]", "6426a7c3379100001e000001")
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();
            List<String> organizationImages = jsonPath.getList("data.full_name");
            logger.info("Images with executor that have organization 6426a7c3379100001e000001 : " + organizationImages);
            Assert.assertTrue(!organizationImages.isEmpty());

            logger.info("Step 18 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step19(){
        try {
            Response response = given()
                    .when()
                    .params("document_id",idCreatedDocument)
                    .params("search_fields[][field_id]", "images.executor_organization_id")
                    .params("search_fields[][condition_id]", "not_equal")
                    .params("search_fields[][value]", "6426a7c3379100001e000001")
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();
            String data = jsonPath.getString("data");
            logger.info("Response data :" + data);
            Assert.assertEquals(data, "[]");

            logger.info("Step 19 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step20(){
        try {
            Response response = given()
                    .when()
                    .params("document_id",idCreatedDocument)
                    .params("search_fields[][field_id]", "images.executor_organization_id")
                    .params("search_fields[][condition_id]", "empty")
                    .params("search_fields[][value]", "6426a7c3379100001e000001")
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();
            String data = jsonPath.getString("data");
            logger.info("Response data :" + data);
            Assert.assertEquals(data, "[]");

            logger.info("Step 20 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step21(){
        try {
            Response response = given()
                    .when()
                    .params("document_id",idCreatedDocument)
                    .params("search_fields[][field_id]", "images.classifier_event_id")
                    .params("search_fields[][condition_id]", "equal")
                    .params("search_fields[][value]", 152)
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();
            imagesFirst = jsonPath.getList("data.full_name");
            logger.info("Images with executor that have event_id = 152 : " + imagesFirst);
            Assert.assertTrue(!imagesFirst.isEmpty());

            logger.info("Step 21 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step22(){
        try {
            Response response = given()
                    .when()
                    .params("document_id",idCreatedDocument)
                    .params("search_fields[][field_id]", "images.classifier_event_id")
                    .params("search_fields[][condition_id]", "not_equal")
                    .params("search_fields[][value]", 152)
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();
            imagesSecond = jsonPath.getList("data.full_name");
            logger.info("Image's full name from previous request : " + imagesFirst);
            logger.info("Images with executor that haven't event_id = 152 : " + imagesSecond);
            Assert.assertFalse(imagesFirst.equals(imagesSecond));

            logger.info("Step 22 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step23(){
        try {
            Response response = given()
                    .when()
                    .params("document_id",idCreatedDocument)
                    .params("search_fields[][field_id]", "images.classifier_event_id")
                    .params("search_fields[][condition_id]", "empty")
                    .params("search_fields[][value]", 152)
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();
            String data = jsonPath.getString("data");
            logger.info("Response data :" + data);
            Assert.assertEquals(data, "[]");

            logger.info("Step 23 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step24(){
        try {
            Response response = given()
                    .when()
                    .params("document_id",idCreatedDocument)
                    .params("search_fields[][field_id]", "images.event_date")
                    .params("search_fields[][condition_id]", "equal")
                    .params("search_fields[][value]", "2024-03-18")
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();
//            imagesFirst = jsonPath.getList("data.full_name");
//            logger.info("Images with executor that have event date 2024-03-18 : " + imagesFirst);
//            Assert.assertTrue(!imagesFirst.isEmpty());
            imagesFirst = jsonPath.getList("data");
            logger.info("Response data :" + imagesFirst);
            Assert.assertTrue(imagesFirst.isEmpty());

            logger.info("Step 24 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step25(){
        try {
            Response response = given()
                    .when()
                    .params("document_id",idCreatedDocument)
                    .params("search_fields[][field_id]", "images.event_date")
                    .params("search_fields[][condition_id]", "not_equal")
                    .params("search_fields[][value]", "2024-03-18")
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();
            imagesSecond = jsonPath.getList("data.full_name");
            logger.info("Images with executor that haven't event date 2024-03-18 : " + imagesSecond);
            Assert.assertFalse(imagesFirst.equals(imagesSecond));

            logger.info("Step 25 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step26(){
        try {
            Response response = given()
                    .when()
                    .params("document_id",idCreatedDocument)
                    .params("search_fields[][field_id]", "images.event_date")
                    .params("search_fields[][condition_id]", "interval")
                    .params("search_fields[][value][from]", "2024-03-07")
                    .params("search_fields[][value][to]", "2024-03-18")
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();
            List<String> eventDateIntervalImages = jsonPath.getList("data.full_name");
            logger.info("Images with event date interval from 2024-03-07 to 2024-03-18 : " + eventDateIntervalImages);
            Assert.assertTrue(eventDateIntervalImages.isEmpty());

            logger.info("Step 26 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step27(){
        try {
            Response response = given()
                    .when()
                    .params("document_id",idCreatedDocument)
                    .params("search_fields[][field_id]", "images.event_date")
                    .params("search_fields[][condition_id]", "interval")
                    .params("search_fields[][value][from]", "2024-03-18")
                    .params("search_fields[][value][to]", "2024-03-18")
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();
            List<String> eventDateIntervalImages = jsonPath.getList("data.full_name");
            logger.info("Images with event date 2024-03-18 : " + eventDateIntervalImages);
            Assert.assertTrue(eventDateIntervalImages.isEmpty());

            logger.info("Step 27 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

}

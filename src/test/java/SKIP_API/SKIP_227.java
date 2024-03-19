package SKIP_API;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SKIP_227 {

    // Endpoints:
    private static String API_DOCUMENTS = "http://api.skip.rtech.ru/v1/documents";

    // Authorization parameters:
    int userIdOne = 1; // User that have all rights, role id = 29

    // Variables :
    int idDocument;
    int idAssignment;

    // Logger
    private static final Logger logger = Logger.getLogger(SKIP_223.class);

    @BeforeTest(enabled = false)
    public void setup(){
        logger.info("Before test method is running.");
    }

    @AfterTest(enabled = false)
    public void tearDown(){
        logger.info("After test method is running.");
    }

    @Test
    public void step01(){
        try {
            JsonObject requestDocuments = new JsonObject();

            requestDocuments.addProperty("skip_reg_date", "2024-02-20T06:06:31.485Z");
            requestDocuments.addProperty("control_official_id", "54e35f6179fa007422000001");
            requestDocuments.addProperty("control_subject_id", "83");
            requestDocuments.addProperty("document_name", "Тест 429");
            requestDocuments.addProperty("document_execution_state_id", "11");
            requestDocuments.addProperty("skip_document_type_id", "32");
            requestDocuments.addProperty("internal", false);
            requestDocuments.addProperty("short_description", "429");

            JsonArray assignments = new JsonArray();
            JsonObject assignment = new JsonObject();
            assignment.addProperty("number","string");
            assignment.addProperty("content","string");
            assignment.addProperty("note","string");
            JsonArray theme_id = new JsonArray();
            theme_id.add(11);
            assignment.add("theme_ids",theme_id);
            assignments.add(assignment);
            requestDocuments.add("assignments", assignments);

            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .body(requestDocuments.toString())
                    .when()
                    .post(API_DOCUMENTS);
            JsonPath jsonPath = response.jsonPath();

            idDocument = jsonPath.getInt("data.id");
            idAssignment =  jsonPath.getInt("data.assignments[0].id");
            logger.info("Document's id: " + idDocument);
            logger.info("Assignment's id: " + idAssignment);
            Assert.assertEquals(jsonPath.getString("data.assignments[0].number"),"string");
            Assert.assertEquals(jsonPath.getString("data.assignments[0].content"),"string");
            Assert.assertEquals(jsonPath.getString("data.assignments[0].note"),"string");

            logger.info("Step 1 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step02(){
        try {
            JsonObject requestBody = new JsonObject();
            JsonArray assignments = new JsonArray();
            JsonObject assignment = new JsonObject();
            assignment.addProperty("id",idAssignment);
            assignment.addProperty("deleted", true);
            assignments.add(assignment);
            requestBody.add("assignments", assignments);

            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .body(requestBody.toString())
                    .when()
                    .put(API_DOCUMENTS + String.format("/%s",idDocument));
            JsonPath jsonPath = response.jsonPath();

            String assignmentResponse = jsonPath.getString("data.assignments");
            logger.info("assignment value - " + assignmentResponse);
            Assert.assertEquals(assignmentResponse,"[]");

            logger.info("Step 2 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step03(){
        try {
            JsonObject requestDocuments = new JsonObject();

            requestDocuments.addProperty("skip_reg_date", "2024-02-20T06:06:31.485Z");
            requestDocuments.addProperty("control_official_id", "54e35f6179fa007422000001");
            requestDocuments.addProperty("control_subject_id", "83");
            requestDocuments.addProperty("document_name", "Тест 429");
            requestDocuments.addProperty("document_execution_state_id", "11");
            requestDocuments.addProperty("skip_document_type_id", "32");
            requestDocuments.addProperty("internal", false);
            requestDocuments.addProperty("short_description", "429");

            JsonArray assignments = new JsonArray();
            JsonObject assignment = new JsonObject();
            assignment.addProperty("number","string");
            assignment.addProperty("content","string");
            assignment.addProperty("note","string");
            JsonArray theme_id = new JsonArray();
            theme_id.add(11);
            assignment.add("theme_ids",theme_id);
            assignments.add(assignment);
            requestDocuments.add("assignments", assignments);

            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .body(requestDocuments.toString())
                    .when()
                    .post(API_DOCUMENTS);
            JsonPath jsonPath = response.jsonPath();

            idDocument = jsonPath.getInt("data.id");
            idAssignment =  jsonPath.getInt("data.assignments[0].id");
            logger.info("Document's id: " + idDocument);
            logger.info("Assignment's id: " + idAssignment);
            Assert.assertEquals(jsonPath.getString("data.assignments[0].number"),"string");
            Assert.assertEquals(jsonPath.getString("data.assignments[0].content"),"string");
            Assert.assertEquals(jsonPath.getString("data.assignments[0].note"),"string");

            logger.info("Step 3 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step04(){
        try {
            JsonObject requestBody = new JsonObject();
            JsonArray assignments = new JsonArray();
            JsonObject assignment = new JsonObject();
            assignment.addProperty("id",idAssignment);
            assignment.addProperty("deleted", false);
            assignments.add(assignment);
            requestBody.add("assignments", assignments);

            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .body(requestBody.toString())
                    .when()
                    .put(API_DOCUMENTS + String.format("/%s",idDocument));
            JsonPath jsonPath = response.jsonPath();

            Assert.assertEquals(jsonPath.getString("data.assignments[0].number"),"string");
            Assert.assertEquals(jsonPath.getString("data.assignments[0].content"),"string");
            Assert.assertEquals(jsonPath.getString("data.assignments[0].note"),"string");
            logger.info("Assignment is not deleted!");
            logger.info("Step 4 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step05(){
        try {
            JsonObject requestBody = new JsonObject();
            JsonArray assignments = new JsonArray();
            JsonObject assignment = new JsonObject();
            assignment.addProperty("id",idAssignment);
            assignment.addProperty("note", "new_string");
            assignments.add(assignment);
            requestBody.add("assignments", assignments);

            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .body(requestBody.toString())
                    .when()
                    .put(API_DOCUMENTS + String.format("/%s",idDocument));
            JsonPath jsonPath = response.jsonPath();

            String note = jsonPath.getString("data.assignments[0].note");
            logger.info("Note value - " + note);
            Assert.assertEquals(note, "new_string");

            logger.info("Step 5 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step06(){
        try {
            JsonObject requestBody = new JsonObject();
            JsonArray assignments = new JsonArray();
            JsonObject assignment = new JsonObject();
            assignment.addProperty("id",idAssignment);
            assignment.addProperty("deleted", true);
            assignments.add(assignment);
            requestBody.add("assignments", assignments);

            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .body(requestBody.toString())
                    .when()
                    .put(API_DOCUMENTS + String.format("/%s",idDocument));
            JsonPath jsonPath = response.jsonPath();

            String assignmentResponse = jsonPath.getString("data.assignments");
            logger.info("assignment value - " + assignmentResponse);
            Assert.assertEquals(assignmentResponse,"[]");

            logger.info("Step 6 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }
}

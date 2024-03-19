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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class SKIP_223 {

    // Endpoints:
    private static String API_DOCUMENT_SED = "http://api.skip.rtech.ru/v1/sed/documents";
    private static String API_DOCUMENTS = "http://api.skip.rtech.ru/v1/documents";
    private static String API_IMAGES_SED = "http://api.skip.rtech.ru/v1/sed_images";
    private static String API_USER = "http://api.skip.rtech.ru/v1/permissions/users";
    private static String API_CONTROL_SUBJECTS = "http://api.skip.rtech.ru/v1/permissions/control_subjects";
    private static String API_DOC_TYPE = "http://api.skip.rtech.ru/v1/classifiers/document_types";
    private static String API_DOCUMENT_EXECUTION_STATES = "http://api.skip.rtech.ru" +
            "/v1/classifiers/document_execution_states";
    private static String API_OSHS_MVD_OFFICIALS= "http://api.skip.rtech.ru/v1/oshs/mvd/officials";

    // Authorization parameters:
    int userIdOne = 1; // User that have all rights, role id = 29
    int userIdSix = 6; // User that have right - "show_roles"

    // Values for document
    int idControlSubject; // Created control subject value
    int idDocumentExecutionState; // Created document execution state value
    int idDocumentType; // Created document type value
    String idRandomOfficial; // Random official
    int idMark = 24; // Value for mark_id from testcase
    int idCreatedDocument; // Document's id that will be created in step 2
    int idImageNotRecognized = 36; // Value for image's id from testcase

    // UUID document that has 10 links from SED
    String uuid = "02b2ac9dd0ecb336f15a4dc312dfcd1eb33f252e27cab32d2162ffbec1f624e510";

    // Name for created element
    String nameTemplate = "SKIP_223_Autotest";
    String shortNameTemplate = "SKIP_223";

    // Variables for image's fields
    // First file :

    String sed_id_first;
    String name_first;
    String full_name_first;
    String pdf_name_first;
    String original_name_first;
    String system_code_first;
    String upload_date_first;
    String archived_status_first;
    String pdf_url_first;
    String original_url_first;
    String pdf_download_url_first;
    String original_download_url_first;

    // Second file :

    String sed_id_second;
    String name_second;
    String full_name_second;
    String pdf_name_second;
    String original_name_second;
    String system_code_second;
    String upload_date_second;
    String archived_status_second;
    String pdf_url_second;
    String original_url_second;
    String pdf_download_url_second;
    String original_download_url_second;

    // Third file :

    String sed_id_third;
    String name_third;
    String full_name_third;
    String pdf_name_third;
    String original_name_third;
    String system_code_third;
    String upload_date_third;
    String archived_status_third;
    String pdf_url_third;
    String original_url_third;
    String pdf_download_url_third;
    String original_download_url_third;

    // Fourth file :

    String sed_id_fourth;
    String name_fourth;
    String full_name_fourth;
    String pdf_name_fourth;
    String original_name_fourth;
    String system_code_fourth;
    String upload_date_fourth;
    String archived_status_fourth;
    String pdf_url_fourth;
    String original_url_fourth;
    String pdf_download_url_fourth;
    String original_download_url_fourth;

    // Fifth file :

    String sed_id_fifth;
    String name_fifth;
    String full_name_fifth;
    String pdf_name_fifth;
    String original_name_fifth;
    String system_code_fifth;
    String upload_date_fifth;
    String archived_status_fifth;
    String pdf_url_fifth;
    String original_url_fifth;
    String pdf_download_url_fifth;
    String original_download_url_fifth;

    // Sixth file :

    String sed_id_sixth;
    String name_sixth;
    String full_name_sixth;
    String pdf_name_sixth;
    String original_name_sixth;
    String system_code_sixth;
    String upload_date_sixth;
    String archived_status_sixth;
    String pdf_url_sixth;
    String original_url_sixth;
    String pdf_download_url_sixth;
    String original_download_url_sixth;

    // Seventh file :

    String sed_id_seventh;
    String name_seventh;
    String full_name_seventh;
    String pdf_name_seventh;
    String original_name_seventh;
    String system_code_seventh;
    String upload_date_seventh;
    String archived_status_seventh;
    String pdf_url_seventh;
    String original_url_seventh;
    String pdf_download_url_seventh;
    String original_download_url_seventh;

    // Eighth file :

    String sed_id_eighth;
    String name_eighth;
    String full_name_eighth;
    String pdf_name_eighth;
    String original_name_eighth;
    String system_code_eighth;
    String upload_date_eighth;
    String archived_status_eighth;
    String pdf_url_eighth;
    String original_url_eighth;
    String pdf_download_url_eighth;
    String original_download_url_eighth;

    // Ninth file :

    String sed_id_ninth;
    String name_ninth;
    String full_name_ninth;
    String pdf_name_ninth;
    String original_name_ninth;
    String system_code_ninth;
    String upload_date_ninth;
    String archived_status_ninth;
    String pdf_url_ninth;
    String original_url_ninth;
    String pdf_download_url_ninth;
    String original_download_url_ninth;

    // Tenth file :

    String sed_id_tenth;
    String name_tenth;
    String full_name_tenth;
    String pdf_name_tenth;
    String original_name_tenth;
    String system_code_tenth;
    String upload_date_tenth;
    String archived_status_tenth;
    String pdf_url_tenth;
    String original_url_tenth;
    String pdf_download_url_tenth;
    String original_download_url_tenth;

    // Logger
    private static final Logger logger = Logger.getLogger(SKIP_223.class);

    @BeforeTest
    public void setup(){
        logger.info("Before test method is running.");

        // Check that user with id 6 has role with id = 5
        try {
            Response response = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", 1)
                    .get(API_USER + "/6");
            JsonPath jsonPath = response.jsonPath();
            String roleId = jsonPath.getString("data.roles[0].id");
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
                        .put(API_USER + "/6");
                JsonPath jsonPathChangeRoleId = responseChangeRoleId.jsonPath();
                String roleIdUpdated = jsonPathChangeRoleId.getString("data.roles[0].id");
                logger.info("New role id - " + roleIdUpdated);
            }
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }

        // Create control_subject
        try {
            String requestBody = String.format(
                    "{\"control_subjects\":[{\"name\": \"%s\",\"fax_number\":\"%s\",\"deleted\":%s,\"provider_id\":\"%s\"}]}"
                    , nameTemplate, "1", false, "525e9f767da3000002000001");
            Response responseControlSubjectCreate = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", 1)
                    .body(requestBody)
                    .put(API_CONTROL_SUBJECTS);
            JsonPath jsonPathControlSubjectsCreate = responseControlSubjectCreate.jsonPath();
            int id = jsonPathControlSubjectsCreate.getInt("data.find { it.name == '" + nameTemplate + "' }.id");
            logger.info("Created element's id - " + id);
            idControlSubject = id;
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
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
                    .post(API_DOCUMENT_EXECUTION_STATES);
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
                    .post(API_DOC_TYPE);
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
                    .get(API_OSHS_MVD_OFFICIALS);

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
    }

    @AfterTest
    public void tearDown(){
        logger.info("After test method is running.");

        // Delete control_subject_id
        try {
            String requestBodyCS = "{\n" +
                    "  \"control_subjects\": [\n" +
                    "    {\n" +
                    "      \"id\": " + idControlSubject + ",\n" +
                    "      \"provider_id\": \"525e9f767da3000002000001\",\n" +
                    "      \"deleted\": true\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";
            Response responseControlSubjectDelete = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", 1)
                    .body(requestBodyCS)
                    .put(API_CONTROL_SUBJECTS);
            logger.info("Created control subject was deleted.");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }

        // Delete document execution state
        try {
            Response responseDESDeleteFirst = (Response) given()
                    .header("Test-Authorization", 1)
                    .delete(API_DOCUMENT_EXECUTION_STATES + String.format("/%s",idDocumentExecutionState));
            logger.info("Created document execution state was deleted.");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }

        // Delete document type
        try {
            Response responseDocumentType = (Response) given()
                    .header("Test-Authorization", 1)
                    .delete(API_DOC_TYPE + String.format("/%s",idDocumentType));
            logger.info("Created document type was deleted.");
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
                    .get(API_DOCUMENT_SED + "/" + uuid);
            JsonPath jsonPath = response.jsonPath();

            Assert.assertNotNull(jsonPath.getString("data"));
            logger.info("Data is not null.");

            sed_id_first = jsonPath.getString("data.images[0].sed_id");
            name_first = jsonPath.getString("data.images[0].name");full_name_first = jsonPath.getString("data.images[0].full_name");
            pdf_name_first = jsonPath.getString("data.images[0].pdf_name");
            original_name_first = jsonPath.getString("data.images[0].original_name");
            system_code_first = jsonPath.getString("data.images[0].system_code");
            upload_date_first = jsonPath.getString("data.images[0].upload_date");
            archived_status_first = jsonPath.getString("data.images[0].archived_status");
            pdf_url_first = jsonPath.getString("data.images[0].pdf_url");
            original_url_first = jsonPath.getString("data.images[0].original_url");
            pdf_download_url_first = jsonPath.getString("data.images[0].pdf_download_url");
            original_download_url_first = jsonPath.getString("data.images[0].original_download_url");

            sed_id_second = jsonPath.getString("data.images[1].sed_id");
            name_second = jsonPath.getString("data.images[1].name");
            full_name_second = jsonPath.getString("data.images[1].full_name");
            pdf_name_second = jsonPath.getString("data.images[1].pdf_name");
            original_name_second = jsonPath.getString("data.images[1].original_name");
            system_code_second = jsonPath.getString("data.images[1].system_code");
            upload_date_second = jsonPath.getString("data.images[1].upload_date");
            archived_status_second = jsonPath.getString("data.images[1].archived_status");
            pdf_url_second = jsonPath.getString("data.images[1].pdf_url");
            original_url_second = jsonPath.getString("data.images[1].original_url");
            pdf_download_url_second = jsonPath.getString("data.images[1].pdf_download_url");
            original_download_url_second = jsonPath.getString("data.images[1].original_download_url");

            sed_id_third = jsonPath.getString("data.images[2].sed_id");
            name_third = jsonPath.getString("data.images[2].name");
            full_name_third = jsonPath.getString("data.images[2].full_name");
            pdf_name_third = jsonPath.getString("data.images[2].pdf_name");
            original_name_third = jsonPath.getString("data.images[2].original_name");
            system_code_third = jsonPath.getString("data.images[2].system_code");
            upload_date_third = jsonPath.getString("data.images[2].upload_date");
            archived_status_third = jsonPath.getString("data.images[2].archived_status");
            pdf_url_third = jsonPath.getString("data.images[2].pdf_url");
            original_url_third = jsonPath.getString("data.images[2].original_url");
            pdf_download_url_third = jsonPath.getString("data.images[2].pdf_download_url");
            original_download_url_third = jsonPath.getString("data.images[2].original_download_url");

            sed_id_fourth = jsonPath.getString("data.images[3].sed_id");
            name_fourth = jsonPath.getString("data.images[3].name");
            full_name_fourth = jsonPath.getString("data.images[3].full_name");
            pdf_name_fourth = jsonPath.getString("data.images[3].pdf_name");
            original_name_fourth = jsonPath.getString("data.images[3].original_name");
            system_code_fourth = jsonPath.getString("data.images[3].system_code");
            upload_date_fourth = jsonPath.getString("data.images[3].upload_date");
            archived_status_fourth = jsonPath.getString("data.images[3].archived_status");
            pdf_url_fourth = jsonPath.getString("data.images[3].pdf_url");
            original_url_fourth = jsonPath.getString("data.images[3].original_url");
            pdf_download_url_fourth = jsonPath.getString("data.images[3].pdf_download_url");
            original_download_url_fourth = jsonPath.getString("data.images[3].original_download_url");

            sed_id_fifth = jsonPath.getString("data.images[4].sed_id");
            name_fifth = jsonPath.getString("data.images[4].name");
            full_name_fifth = jsonPath.getString("data.images[4].full_name");
            pdf_name_fifth = jsonPath.getString("data.images[4].pdf_name");
            original_name_fifth = jsonPath.getString("data.images[4].original_name");
            system_code_fifth = jsonPath.getString("data.images[4].system_code");
            upload_date_fifth = jsonPath.getString("data.images[4].upload_date");
            archived_status_fifth = jsonPath.getString("data.images[4].archived_status");
            pdf_url_fifth = jsonPath.getString("data.images[4].pdf_url");
            original_url_fifth = jsonPath.getString("data.images[4].original_url");
            pdf_download_url_fifth = jsonPath.getString("data.images[4].pdf_download_url");
            original_download_url_fifth = jsonPath.getString("data.images[4].original_download_url");

            sed_id_sixth = jsonPath.getString("data.images[5].sed_id");
            name_sixth = jsonPath.getString("data.images[5].name");
            full_name_sixth = jsonPath.getString("data.images[5].full_name");
            pdf_name_sixth = jsonPath.getString("data.images[5].pdf_name");
            original_name_sixth = jsonPath.getString("data.images[5].original_name");
            system_code_sixth = jsonPath.getString("data.images[5].system_code");
            upload_date_sixth = jsonPath.getString("data.images[5].upload_date");
            archived_status_sixth = jsonPath.getString("data.images[5].archived_status");
            pdf_url_sixth = jsonPath.getString("data.images[5].pdf_url");
            original_url_sixth = jsonPath.getString("data.images[5].original_url");
            pdf_download_url_sixth = jsonPath.getString("data.images[5].pdf_download_url");
            original_download_url_sixth = jsonPath.getString("data.images[5].original_download_url");

            sed_id_seventh = jsonPath.getString("data.images[6].sed_id");
            name_seventh = jsonPath.getString("data.images[6].name");
            full_name_seventh = jsonPath.getString("data.images[6].full_name");
            pdf_name_seventh = jsonPath.getString("data.images[6].pdf_name");
            original_name_seventh = jsonPath.getString("data.images[6].original_name");
            system_code_seventh = jsonPath.getString("data.images[6].system_code");
            upload_date_seventh = jsonPath.getString("data.images[6].upload_date");
            archived_status_seventh = jsonPath.getString("data.images[6].archived_status");
            pdf_url_seventh = jsonPath.getString("data.images[6].pdf_url");
            original_url_seventh = jsonPath.getString("data.images[6].original_url");
            pdf_download_url_seventh = jsonPath.getString("data.images[6].pdf_download_url");
            original_download_url_seventh = jsonPath.getString("data.images[6].original_download_url");

            sed_id_eighth = jsonPath.getString("data.images[7].sed_id");
            name_eighth = jsonPath.getString("data.images[7].name");
            full_name_eighth = jsonPath.getString("data.images[7].full_name");
            pdf_name_eighth = jsonPath.getString("data.images[7].pdf_name");
            original_name_eighth = jsonPath.getString("data.images[7].original_name");
            system_code_eighth = jsonPath.getString("data.images[7].system_code");
            upload_date_eighth = jsonPath.getString("data.images[7].upload_date");
            archived_status_eighth = jsonPath.getString("data.images[7].archived_status");
            pdf_url_eighth = jsonPath.getString("data.images[7].pdf_url");
            original_url_eighth = jsonPath.getString("data.images[7].original_url");
            pdf_download_url_eighth = jsonPath.getString("data.images[7].pdf_download_url");
            original_download_url_eighth = jsonPath.getString("data.images[7].original_download_url");

            sed_id_ninth = jsonPath.getString("data.images[8].sed_id");
            name_ninth = jsonPath.getString("data.images[8].name");
            full_name_ninth = jsonPath.getString("data.images[8].full_name");
            pdf_name_ninth = jsonPath.getString("data.images[8].pdf_name");
            original_name_ninth = jsonPath.getString("data.images[8].original_name");
            system_code_ninth = jsonPath.getString("data.images[8].system_code");
            upload_date_ninth = jsonPath.getString("data.images[8].upload_date");
            archived_status_ninth = jsonPath.getString("data.images[8].archived_status");
            pdf_url_ninth = jsonPath.getString("data.images[8].pdf_url");
            original_url_ninth = jsonPath.getString("data.images[8].original_url");
            pdf_download_url_ninth = jsonPath.getString("data.images[8].pdf_download_url");
            original_download_url_ninth = jsonPath.getString("data.images[8].original_download_url");

            sed_id_tenth = jsonPath.getString("data.images[9].sed_id");
            name_tenth = jsonPath.getString("data.images[9].name");
            full_name_tenth = jsonPath.getString("data.images[9].full_name");
            pdf_name_tenth = jsonPath.getString("data.images[9].pdf_name");
            original_name_tenth = jsonPath.getString("data.images[9].original_name");
            system_code_tenth = jsonPath.getString("data.images[9].system_code");
            upload_date_tenth = jsonPath.getString("data.images[9].upload_date");
            archived_status_tenth = jsonPath.getString("data.images[9].archived_status");
            pdf_url_tenth = jsonPath.getString("data.images[9].pdf_url");
            original_url_tenth = jsonPath.getString("data.images[9].original_url");
            pdf_download_url_tenth = jsonPath.getString("data.images[9].pdf_download_url");
            original_download_url_tenth = jsonPath.getString("data.images[9].original_download_url");
            logger.info("Stored all images data in variables.");
            logger.info("File names: " + name_first + " , " + name_second + " , " + name_third + " , " +
                    name_fourth + " , " + name_fifth + " , " + name_sixth + " , " + name_seventh +
                    " , " + name_eighth + " , " + name_ninth + " , " + name_tenth);
            logger.info("Step 1 successfully completed!");
        } catch (AssertionError assertionError) {
            logger.error("Assertion error - " + assertionError.getMessage());
            Assert.fail();
        } catch (Exception exception) {
            logger.error("Exception error - " + exception.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step02(){
        JsonObject requestDocuments = new JsonObject();
        requestDocuments.addProperty("skip_reg_date", "2024-02-20T06:06:31.485Z");
        requestDocuments.addProperty("control_official_id", idRandomOfficial);
        requestDocuments.addProperty("control_subject_id", idControlSubject);
        requestDocuments.addProperty("document_name", "Тест 429");
        requestDocuments.addProperty("document_execution_state_id", idDocumentExecutionState);
        requestDocuments.addProperty("skip_document_type_id", idDocumentType);
        requestDocuments.addProperty("internal", false);
        requestDocuments.addProperty("short_description", "429");

        JsonArray imagesArray = new JsonArray();
        JsonObject imageFirst = new JsonObject();
        JsonObject imageSecond = new JsonObject();
        JsonObject imageThird = new JsonObject();
        JsonObject imageFourth = new JsonObject();
        JsonObject imageFifth = new JsonObject();
        JsonObject imageSixth = new JsonObject();
        JsonObject imageSeventh = new JsonObject();
        JsonObject imageEighth = new JsonObject();
        JsonObject imageNinth = new JsonObject();
        JsonObject imageTenth = new JsonObject();

        imageFirst.addProperty("sed_id", sed_id_first);
        imageFirst.addProperty("system_code", system_code_first);
        imageFirst.addProperty("name", name_first);
        imageFirst.addProperty("upload_date", upload_date_first);
        imageFirst.addProperty("archived_status", archived_status_first);
        imageFirst.addProperty("original_name", original_name_first);
        imageFirst.addProperty("original_url", original_url_first);
        imageFirst.addProperty("original_download_url", original_download_url_first);
        imageFirst.addProperty("mark_id", idMark);
        imageFirst.addProperty("pdf_name", pdf_name_first);
        imageFirst.addProperty("pdf_url", pdf_url_first);
        imageFirst.addProperty("pdf_download_url", pdf_download_url_first);

        imageSecond.addProperty("sed_id", sed_id_second);
        imageSecond.addProperty("system_code", system_code_second);
        imageSecond.addProperty("name", name_second);
        imageSecond.addProperty("upload_date", upload_date_second);
        imageSecond.addProperty("archived_status", archived_status_second);
        imageSecond.addProperty("original_name", original_name_second);
        imageSecond.addProperty("original_url", original_url_second);
        imageSecond.addProperty("original_download_url", original_download_url_second);
        imageSecond.addProperty("mark_id", idMark);
        imageSecond.addProperty("pdf_name", pdf_name_second);
        imageSecond.addProperty("pdf_url", pdf_url_second);
        imageSecond.addProperty("pdf_download_url", pdf_download_url_second);

        imageThird.addProperty("sed_id", sed_id_third);
        imageThird.addProperty("system_code", system_code_third);
        imageThird.addProperty("name", name_third);
        imageThird.addProperty("upload_date", upload_date_third);
        imageThird.addProperty("archived_status", archived_status_third);
        imageThird.addProperty("original_name", original_name_third);
        imageThird.addProperty("original_url", original_url_third);
        imageThird.addProperty("original_download_url", original_download_url_third);
        imageThird.addProperty("mark_id", idMark);
        imageThird.addProperty("pdf_name", pdf_name_third);
        imageThird.addProperty("pdf_url", pdf_url_third);
        imageThird.addProperty("pdf_download_url", pdf_download_url_third);

        imageFourth.addProperty("sed_id", sed_id_fourth);
        imageFourth.addProperty("system_code", system_code_fourth);
        imageFourth.addProperty("name", name_fourth);
        imageFourth.addProperty("upload_date", upload_date_fourth);
        imageFourth.addProperty("archived_status", archived_status_fourth);
        imageFourth.addProperty("original_name", original_name_fourth);
        imageFourth.addProperty("original_url", original_url_fourth);
        imageFourth.addProperty("original_download_url", original_download_url_fourth);
        imageFourth.addProperty("mark_id", idMark);
        imageFourth.addProperty("pdf_name", pdf_name_fourth);
        imageFourth.addProperty("pdf_url", pdf_url_fourth);
        imageFourth.addProperty("pdf_download_url", pdf_download_url_fourth);

        imageFifth.addProperty("sed_id", sed_id_fifth);
        imageFifth.addProperty("system_code", system_code_fifth);
        imageFifth.addProperty("name", name_fifth);
        imageFifth.addProperty("upload_date", upload_date_fifth);
        imageFifth.addProperty("archived_status", archived_status_fifth);
        imageFifth.addProperty("original_name", original_name_fifth);
        imageFifth.addProperty("original_url", original_url_fifth);
        imageFifth.addProperty("original_download_url", original_download_url_fifth);
        imageFifth.addProperty("mark_id", idMark);
        imageFifth.addProperty("pdf_name", pdf_name_fifth);
        imageFifth.addProperty("pdf_url", pdf_url_fifth);
        imageFifth.addProperty("pdf_download_url", pdf_download_url_fifth);

        imageSixth.addProperty("sed_id", sed_id_sixth);
        imageSixth.addProperty("system_code", system_code_sixth);
        imageSixth.addProperty("name", name_sixth);
        imageSixth.addProperty("upload_date", upload_date_sixth);
        imageSixth.addProperty("archived_status", archived_status_sixth);
        imageSixth.addProperty("original_name", original_name_sixth);
        imageSixth.addProperty("original_url", original_url_sixth);
        imageSixth.addProperty("original_download_url", original_download_url_sixth);
        imageSixth.addProperty("mark_id", idMark);
        imageSixth.addProperty("pdf_name", pdf_name_sixth);
        imageSixth.addProperty("pdf_url", pdf_url_sixth);
        imageSixth.addProperty("pdf_download_url", pdf_download_url_sixth);

        imageSeventh.addProperty("sed_id", sed_id_seventh);
        imageSeventh.addProperty("system_code", system_code_seventh);
        imageSeventh.addProperty("name", name_seventh);
        imageSeventh.addProperty("upload_date", upload_date_seventh);
        imageSeventh.addProperty("archived_status", archived_status_seventh);
        imageSeventh.addProperty("original_name", original_name_seventh);
        imageSeventh.addProperty("original_url", original_url_seventh);
        imageSeventh.addProperty("original_download_url", original_download_url_seventh);
        imageSeventh.addProperty("mark_id", idMark);
        imageSeventh.addProperty("pdf_name", pdf_name_seventh);
        imageSeventh.addProperty("pdf_url", pdf_url_seventh);
        imageSeventh.addProperty("pdf_download_url", pdf_download_url_seventh);

        imageEighth.addProperty("sed_id", sed_id_eighth);
        imageEighth.addProperty("system_code", system_code_eighth);
        imageEighth.addProperty("name", name_eighth);
        imageEighth.addProperty("upload_date", upload_date_eighth);
        imageEighth.addProperty("archived_status", archived_status_eighth);
        imageEighth.addProperty("original_name", original_name_eighth);
        imageEighth.addProperty("original_url", original_url_eighth);
        imageEighth.addProperty("original_download_url", original_download_url_eighth);
        imageEighth.addProperty("mark_id", idMark);
        imageEighth.addProperty("pdf_name", pdf_name_eighth);
        imageEighth.addProperty("pdf_url", pdf_url_eighth);
        imageEighth.addProperty("pdf_download_url", pdf_download_url_eighth);

        imageNinth.addProperty("sed_id", sed_id_ninth);
        imageNinth.addProperty("system_code", system_code_ninth);
        imageNinth.addProperty("name", name_ninth);
        imageNinth.addProperty("upload_date", upload_date_ninth);
        imageNinth.addProperty("archived_status", archived_status_ninth);
        imageNinth.addProperty("original_name", original_name_ninth);
        imageNinth.addProperty("original_url", original_url_ninth);
        imageNinth.addProperty("original_download_url", original_download_url_ninth);
        imageNinth.addProperty("mark_id", idMark);
        imageNinth.addProperty("pdf_name", pdf_name_ninth);
        imageNinth.addProperty("pdf_url", pdf_url_ninth);
        imageNinth.addProperty("pdf_download_url", pdf_download_url_ninth);

        imageTenth.addProperty("sed_id", sed_id_tenth);
        imageTenth.addProperty("system_code", system_code_tenth);
        imageTenth.addProperty("name", name_tenth);
        imageTenth.addProperty("upload_date", upload_date_tenth);
        imageTenth.addProperty("archived_status", archived_status_tenth);
        imageTenth.addProperty("original_name", original_name_tenth);
        imageTenth.addProperty("original_url", original_url_tenth);
        imageTenth.addProperty("original_download_url", original_download_url_tenth);
        imageTenth.addProperty("mark_id", idMark);
        imageTenth.addProperty("pdf_name", pdf_name_tenth);
        imageTenth.addProperty("pdf_url", pdf_url_tenth);
        imageTenth.addProperty("pdf_download_url", pdf_download_url_tenth);

        imagesArray.add(imageFirst);
        imagesArray.add(imageSecond);
        imagesArray.add(imageThird);
        imagesArray.add(imageFourth);
        imagesArray.add(imageFifth);
        imagesArray.add(imageSixth);
        imagesArray.add(imageSeventh);
        imagesArray.add(imageEighth);
        imagesArray.add(imageNinth);
        imagesArray.add(imageTenth);
        requestDocuments.add("sed_images", imagesArray);

        try{
            Response response = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .body(requestDocuments.toString())
                    .post(API_DOCUMENTS);
            JsonPath jsonPath = response.jsonPath();

            idCreatedDocument = jsonPath.getInt("data.id");
            logger.info("Created document's id - " + idCreatedDocument);

            List<String> imagesNames = jsonPath.getList("data.sed_images.name");
            Assert.assertNotNull(imagesNames);

            logger.info("Document was created with files : " + imagesNames);
            logger.info("Step 2 successfully completed!");
        } catch (AssertionError assertionError) {
            logger.error("Assertion error - " + assertionError.getMessage());
            Assert.fail();
        } catch (Exception exception) {
            logger.error("Exception error - " + exception.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step03(){
        try {
            Response response = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .get(API_DOCUMENTS + String.format("/%s", idCreatedDocument));
            JsonPath jsonPath = response.jsonPath();

            List<String> imagesNames = jsonPath.getList("data.sed_images.name");
            Assert.assertNotNull(imagesNames);

            logger.info("Document was created with files : " + imagesNames);
            logger.info("Step 3 successfully completed!");
        } catch (AssertionError assertionError) {
            logger.error("Assertion error - " + assertionError.getMessage());
            Assert.fail();
        } catch (Exception exception) {
            logger.error("Exception error - " + exception.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step04(){
        // Шаг не автоматизирован.
        /**
         * 1) Connect to database.
         * 2) Execute script  :
         * SELECT id, name, recognition_state
         * FROM public.images_sed_images
         * WHERE recognition_state is null;
         * 3) Get first id and store it - idImageNotRecognized
         * **/
        logger.info("Step 4 successfully completed!");
    }

    @Test
    public void step05(){
        try {
            Response response = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .post(API_IMAGES_SED + String.format("/%s/recognize", idImageNotRecognized));

            int statusCode = response.getStatusCode();
            Assert.assertTrue(statusCode == 204);
            logger.info("Status code after recognition : " + statusCode);
            logger.info("Step 5 successfully completed!");
        } catch (AssertionError assertionError) {
            logger.error("Assertion error - " + assertionError.getMessage());
            Assert.fail();
        } catch (Exception exception) {
            logger.error("Exception error - " + exception.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step06(){
        try {
            Response response = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdSix)
                    .post(API_IMAGES_SED + String.format("/%s/recognize", idImageNotRecognized));
            JsonPath jsonPath = response.jsonPath();

            String errorMessage = jsonPath.getString("error");
            logger.info("Error message - " + errorMessage);
            Assert.assertEquals(errorMessage, "Доступ к ресурсу запрещен");
            logger.info("Step 6 successfully completed!");
        } catch (AssertionError assertionError) {
            logger.error("Assertion error - " + assertionError.getMessage());
            Assert.fail();
        } catch (Exception exception) {
            logger.error("Exception error - " + exception.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step07(){
        try {
            Response response = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .put(API_IMAGES_SED + String.format("/%s", idImageNotRecognized));
            JsonPath jsonPath = response.jsonPath();

            String recognition_state = jsonPath.getString("data.recognition_state");
            logger.info("Recognition state - " + recognition_state);
            Assert.assertEquals(recognition_state, "recognized");
            logger.info("Step 7 successfully completed!");
        } catch (AssertionError assertionError) {
            logger.error("Assertion error - " + assertionError.getMessage());
            Assert.fail();
        } catch (Exception exception) {
            logger.error("Exception error - " + exception.getMessage());
            Assert.fail();
        }
    }
}

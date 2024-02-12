package SKIP_API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.given;

public class SKIP_172 {

    private static String API_DOCUMENTS = "http://api.skip.rtech.ru/v1/documents";
    // Variables for setup
    private static String API_USER = "http://api.skip.rtech.ru/v1/permissions/users";
    private static String API_CONTROL_SUBJECTS = "http://api.skip.rtech.ru/v1/permissions/control_subjects";
    private static String API_DOC_TYPE = "http://api.skip.rtech.ru/v1/classifiers/document_types";
    private static String API_DOCUMENT_EXECUTION_STATES = "http://api.skip.rtech.ru" +
            "/v1/classifiers/document_execution_states";
    private static String API_OSHS_MVD_OFFICIALS="http://api.skip.rtech.ru/v1/oshs/mvd/officials";

    int userIdOne = 1; // User that have all rights, role id = 29
    int userIdSix = 6; // User that have right = "index_roles", role id = 5
    int idCreatedDocument; // Document's id that will be created in step 7
    int idCreatedAssignment; // Assignment's id that will be created in step 7
    int idCreatedLinkFirst; // Link's id that will be created in step 7 first element
    int idCreatedLinkSecond; // Link's id that will be created in step 7 second element
    int idControlSubject; // Created control subject's id
    int idDocumentExecutionState; // Created document execution state's id
    int idDocumentType; // Created document type's id
    String idRandomOfficial; // Random official's id
    int idDocumentFirst; // Id first element from documents list
    int idDocumentSecond; // Id second element from documents list

    // Set parameters "name", "short_name" for created elements
    String nameTemplate = "SKIP_172_Autotest";
    String shortNameTemplate = "SKIP_172";
    // Values of documents from SED
    String uidFirstDocSED = "0289a43bc2359182f95e399d29dc1674510ff22a7f8e5d9c13b01a74dd56fe1430";
    String regNumberFirstDocSED = "65c5d0d9d8fe8d1036000003";
    String regDateFirstDocSED = "09.02.2024";
    String uidSecondDocSED = "0271d3b0343b8307f22b6bf06ede9ea78771dbef2f290e69e6699cf7d34e28bea8";
    String regNumberSecondDocSED = "65801d629443000003000001";
    String regDateSecondDocSED = "02.01.2024";
    String regNumberSecondDocSEDShort = "111/960";



    @BeforeTest
    public void setup(){
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
        // Create control_subject
        String requestBody = String.format(
                "{\"control_subjects\":[{\"name\": \"%s\",\"fax_number\":\"%s\",\"deleted\":%s,\"provider_id\":\"%s\"}]}"
                , nameTemplate, "1", false, "525e9f767da3000002000001");
        Response responseControlSubjectCreate = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBody)
                .put(API_CONTROL_SUBJECTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathControlSubjectsCreate = responseControlSubjectCreate.jsonPath();

        int id = jsonPathControlSubjectsCreate.getInt("data.find { it.name == '" + nameTemplate + "' }.id");
        System.out.println("Created element's id - " + id);
        idControlSubject = id;
        // Create document execution state
        Map<String, String> requestBodyDocumentExStOne = new HashMap<>();
        requestBodyDocumentExStOne.put("name", nameTemplate);
        requestBodyDocumentExStOne.put("short_name", shortNameTemplate);
        requestBodyDocumentExStOne.put("excluded", "false");
        Response responseDocumentExecutionStateCreate = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBodyDocumentExStOne)
                .post(API_DOCUMENT_EXECUTION_STATES)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathDocumentExecutionStateCreate = responseDocumentExecutionStateCreate.jsonPath();
        idDocumentExecutionState = jsonPathDocumentExecutionStateCreate.getInt("data.id");
        System.out.println("Id document ex states - " + idDocumentExecutionState);
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
        // Get control_official_id from officials_oshs_mvd
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
        // Get 2 random values of documents from SKIP
        Response responseDocumentsList = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .get(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPathDocumentsList = responseDocumentsList.jsonPath();

        idDocumentFirst = jsonPathDocumentsList.getInt("data[0].id");
        idDocumentSecond = jsonPathDocumentsList.getInt("data[1].id");
    }

    @Test
    public void step01(){
        String requestBody = "{\n" +
                "    \"skip_reg_date\": \"2024-01-17T06:06:31.485Z\",\n" +
                "    \"control_official_id\": \"" + idRandomOfficial + "\",\n" +
                "    \"control_subject_id\": " + idControlSubject + ",\n" +
                "    \"document_name\": \"Тест 404\",\n" +
                "    \"document_execution_state_id\": " + idDocumentExecutionState + ",\n" +
                "    \"skip_document_type_id\": " + idDocumentType + ",\n" +
                "    \"internal\": false,\n" +
                "    \"short_description\": \"404\",\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"number\": \"1\",\n" +
                "            \"content\": \"content\",\n" +
                "            \"note\": \"note\",\n" +
                "            \"links\": []\n" +
                "         }\n" +
                "    ]\n" +
                "}";
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .body(requestBody)
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String links = jsonPath.getString("data.assignments[0].links");
        System.out.println("Links value : " + links);
        Assert.assertTrue(links.equals("[]"));
    }

    @Test
    public void step02(){
        String requestBody = "{\n" +
                "\t\"skip_reg_date\": \"2024-01-17T06:06:31.485Z\",\n" +
                "    \"control_official_id\": \"" + idRandomOfficial + "\",\n" +
                "    \"control_subject_id\": " + idControlSubject + ",\n" +
                "    \"document_name\": \"Тест 404\",\n" +
                "    \"document_execution_state_id\": " + idDocumentExecutionState + ",\n" +
                "    \"skip_document_type_id\": " + idDocumentType + ",\n" +
                "    \"internal\": false,\n" +
                "    \"short_description\": \"404\",\n" +
                "    \"assignments\": [\n" +
                "       {\n" +
                "           \"number\": \"1\",\n" +
                "           \"content\": \"content\",\n" +
                "           \"note\": \"note\",\n" +
                "           \"links\": [\n" +
                "               {\n" +
                "                   \"document_type\": \"Documents::Document\",\n" +
                "                   \"document_id\": " + idDocumentSecond + ",\n" +
                "                   \"note\": \"test\",\n" +
                "                   \"sed_document\": {\n" +
                "                       \"uid\": \"" + uidSecondDocSED + "\",\n" +
                "                       \"reg_number\": \"" + regNumberSecondDocSEDShort + "\",\n" +
                "                       \"reg_date\": \"" + regDateSecondDocSED + "\"\n" +
                "                   }\n" +
                "               }\n" +
                "           ]\n" +
                "       }\n" +
                "   ]\n" +
                "}";
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .body(requestBody)
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessageFirst = jsonPath.getString("errors.assignments.0.links.0.document_id[0]");
        String errorMessageSecond = jsonPath.getString("errors.assignments.0.links.0.sed_document[0]");

        System.out.println("First error message : " + errorMessageFirst);
        System.out.println("Second error message : " + errorMessageSecond);

        Assert.assertTrue(errorMessageFirst.equals("are mutually exclusive"));
        Assert.assertTrue(errorMessageSecond.equals("are mutually exclusive"));
    }

    @Test
    public void step03(){
        String requestBody = "{\n" +
                "    \"skip_reg_date\": \"2024-01-17T06:06:31.485Z\",\n" +
                "    \"control_official_id\": \"" + idRandomOfficial + "\",\n" +
                "    \"control_subject_id\": " + idControlSubject + ",\n" +
                "    \"document_name\": \"Тест 404\",\n" +
                "    \"document_execution_state_id\": " + idDocumentExecutionState + ",\n" +
                "    \"skip_document_type_id\": " + idDocumentType + ",\n" +
                "    \"internal\": false,\n" +
                "    \"short_description\": \"404\",\n" +
                "    \"assignments\": [\n" +
                "       {\n" +
                "           \"number\": \"1\",\n" +
                "           \"content\": \"content\",\n" +
                "           \"note\": \"note\",\n" +
                "           \"links\": [\n" +
                "               {\n" +
                "                   \"document_type\": \"Documents::Document\",\n" +
                "                   \"document_id\": " + idDocumentFirst + "\n" +
                "               }\n" +
                "           ]\n" +
                "       }\n" +
                "   ]\n" +
                "}";
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .body(requestBody)
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        int documentId = jsonPath.getInt("data.assignments[0].links[0].document_id");
        String documentType = jsonPath.getString("data.assignments[0].links[0].document_type");

        System.out.println("Document id linked document - " + documentId);
        System.out.println("Document type linked document - " + documentType);

        Assert.assertTrue(documentId == idDocumentFirst);
        Assert.assertTrue(documentType.equals("Documents::Document"));
    }

    @Test
    public void step04(){
        String requestBody = "{\n" +
                "\t\"skip_reg_date\": \"2024-01-17T06:06:31.485Z\",\n" +
                "    \"control_official_id\": \"" + idRandomOfficial + "\",\n" +
                "    \"control_subject_id\": " + idControlSubject + ",\n" +
                "    \"document_name\": \"Тест 404\",\n" +
                "    \"document_execution_state_id\": " + idDocumentExecutionState + ",\n" +
                "    \"skip_document_type_id\": " + idDocumentType + ",\n" +
                "    \"internal\": false,\n" +
                "    \"short_description\": \"404\",\n" +
                "    \"assignments\": [\n" +
                "       {\n" +
                "           \"number\": \"1\",\n" +
                "           \"content\": \"content\",\n" +
                "           \"note\": \"note\",\n" +
                "           \"links\": [\n" +
                "               {\n" +
                "                   \"document_type\": \"Sed::Document\",\n" +
                "                   \"note\": \"test\",\n" +
                "                   \"sed_document\": {\n" +
                "                       \"uid\": \"" + uidSecondDocSED + "\",\n" +
                "                       \"reg_number\": \"" + regNumberSecondDocSEDShort + "\",\n" +
                "                       \"reg_date\": \"" + regDateSecondDocSED + "\"\n" +
                "                   }\n" +
                "               }\n" +
                "           ]\n" +
                "       }\n" +
                "   ]\n" +
                "}";
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .body(requestBody)
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String nameLink = jsonPath.getString("data.assignments[0].links[0].name");
        String matchName = "От " + regDateSecondDocSED + " № " + regNumberSecondDocSEDShort;

        System.out.println("Name : " + nameLink);
        // Need to check
        Assert.assertTrue(nameLink.equals(matchName));
    }

    @Test
    public void step05(){
        String requestBody = "{\n" +
                "\t\"skip_reg_date\": \"2024-01-17T06:06:31.485Z\",\n" +
                "    \"control_official_id\": \"" + idRandomOfficial + "\",\n" +
                "    \"control_subject_id\": " + idControlSubject + ",\n" +
                "    \"document_name\": \"Тест 404\",\n" +
                "    \"document_execution_state_id\": " + idDocumentExecutionState + ",\n" +
                "    \"skip_document_type_id\": " + idDocumentType + ",\n" +
                "    \"internal\": false,\n" +
                "    \"short_description\": \"404\",\n" +
                "    \"assignments\": [\n" +
                "       {\n" +
                "           \"number\": \"1\",\n" +
                "           \"content\": \"content\",\n" +
                "           \"note\": \"note\",\n" +
                "           \"links\": [\n" +
                "               {\n" +
                "                   \"document_type\": \"Documents::Document\"\n" +
                "               }\n" +
                "           ]\n" +
                "       }\n" +
                "   ]\n" +
                "}";
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .body(requestBody)
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorFirstMessage = jsonPath.getString("errors.assignments.0.links.0.document_id[0]");
        String errorSecondMessage = jsonPath.getString("errors.assignments.0.links.0.sed_document[0]");

        System.out.println("Document id error - " + errorFirstMessage);
        System.out.println("Sed document error - " + errorSecondMessage);

        Assert.assertTrue(errorFirstMessage.equals("are missing, exactly one parameter must be provided"));
        Assert.assertTrue(errorSecondMessage.equals("are missing, exactly one parameter must be provided"));
    }

    @Test
    public void step06(){
        String requestBody = "{\n" +
                "\t\"skip_reg_date\": \"2024-01-17T06:06:31.485Z\",\n" +
                "    \"control_official_id\": \"" + idRandomOfficial + "\",\n" +
                "    \"control_subject_id\": " + idControlSubject + ",\n" +
                "    \"document_name\": \"Тест 404\",\n" +
                "    \"document_execution_state_id\": " + idDocumentExecutionState + ",\n" +
                "    \"skip_document_type_id\": " + idDocumentType + ",\n" +
                "    \"internal\": false,\n" +
                "    \"short_description\": \"404\",\n" +
                "    \"assignments\": [\n" +
                "       {\n" +
                "           \"number\": \"1\",\n" +
                "           \"content\": \"content\",\n" +
                "           \"note\": \"note\",\n" +
                "           \"links\": [\n" +
                "               {\n" +
                "                   \"document_type\": \"Sed::Document\",\n" +
                "                   \"document_id\": " + idDocumentSecond + ",\n" +
                "                   \"note\": \"test\",\n" +
                "                   \"sed_document\": {\n" +
                "                       \"uid\": \"" + uidFirstDocSED + "\",\n" +
                "                       \"reg_number\": \"" + regNumberFirstDocSED + "\",\n" +
                "                       \"reg_date\": \"" + regDateFirstDocSED + "\"\n" +
                "                   }\n" +
                "               }\n" +
                "           ]\n" +
                "       }\n" +
                "   ]\n" +
                "}";
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .body(requestBody)
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorFirstMessage = jsonPath.getString("errors.assignments.0.links.0.document_id[0]");
        String errorSecondMessage = jsonPath.getString("errors.assignments.0.links.0.sed_document[0]");

        System.out.println("Document id error - " + errorFirstMessage);
        System.out.println("Sed document error - " + errorSecondMessage);

        Assert.assertTrue(errorFirstMessage.equals("are mutually exclusive"));
        Assert.assertTrue(errorSecondMessage.equals("are mutually exclusive"));
    }

    @Test
    public void step07(){
        String requestBody = "{\n" +
                "\t\"skip_reg_date\": \"2024-01-17T06:06:31.485Z\",\n" +
                "    \"control_official_id\": \"" + idRandomOfficial + "\",\n" +
                "    \"control_subject_id\": " + idControlSubject + ",\n" +
                "    \"document_name\": \"Тест 404\",\n" +
                "    \"document_execution_state_id\": " + idDocumentExecutionState + ",\n" +
                "    \"skip_document_type_id\": " + idDocumentType + ",\n" +
                "    \"internal\": false,\n" +
                "    \"short_description\": \"404\",\n" +
                "    \"assignments\": [\n" +
                "       {\n" +
                "           \"number\": \"1\",\n" +
                "           \"content\": \"content\",\n" +
                "           \"note\": \"note\",\n" +
                "           \"links\": [\n" +
                "               {\n" +
                "                   \"document_type\": \"Sed::Document\",\n" +
                "                   \"note\": \"t\",\n" +
                "                   \"sed_document\": {\n" +
                "                       \"uid\": \"" + uidFirstDocSED + "\",\n" +
                "                       \"reg_number\": \"" + regNumberFirstDocSED + "\",\n" +
                "                       \"reg_date\": \"" + regDateFirstDocSED + "\"\n" +
                "                   }\n" +
                "               },\n" +
                "               {\n" +
                "                       \"document_type\": \"Documents::Document\",\n" +
                "                       \"document_id\": " + idDocumentFirst + "\n" +
                "               }\n" +
                "           ]\n" +
                "       }\n" +
                "   ]\n" +
                "}";
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .body(requestBody)
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        idCreatedDocument = jsonPath.getInt("data.id");
        idCreatedAssignment = jsonPath.getInt("data.assignments[0].id");
        idCreatedLinkFirst = jsonPath.getInt("data.assignments[0].links[0].id");
        idCreatedLinkSecond = jsonPath.getInt("data.assignments[0].links[1].id");

        System.out.println("Id created document - " + idCreatedDocument);
        System.out.println("Id created assignment - " + idCreatedAssignment);
        System.out.println("Id created link first element - " + idCreatedLinkFirst);
        System.out.println("Id created link second element - " + idCreatedLinkSecond);

        String documentTypeFirst = jsonPath.getString("data.assignments[0].links[0].document_type");
        String documentTypeSecond = jsonPath.getString("data.assignments[0].links[1].document_type");

        System.out.println("Document type first element - " + documentTypeFirst);
        System.out.println("Document type second element - " + documentTypeSecond);

        Assert.assertTrue(documentTypeFirst.equals("Documents::Document"));
        Assert.assertTrue(documentTypeSecond.equals("Sed::Document"));
    }

    @Test
    public void step08(){
        String requestBody = "{\n" +
                "\t\"skip_reg_date\": \"2024-01-17T06:06:31.485Z\",\n" +
                "    \"control_official_id\": \"" + idRandomOfficial + "\",\n" +
                "    \"control_subject_id\": " + idControlSubject + ",\n" +
                "    \"document_name\": \"Тест 404\",\n" +
                "    \"document_execution_state_id\": " + idDocumentExecutionState + ",\n" +
                "    \"skip_document_type_id\": " + idDocumentType + ",\n" +
                "    \"internal\": false,\n" +
                "    \"short_description\": \"404\",\n" +
                "    \"assignments\": [\n" +
                "       {\n" +
                "           \"number\": \"1\",\n" +
                "           \"content\": \"content\",\n" +
                "           \"note\": \"note\",\n" +
                "           \"links\": [\n" +
                "               {\n" +
                "                   \"document_type\": \"Sed::Document\",\n" +
                "                   \"note\": \"t\",\n" +
                "                   \"sed_document\": {\n" +
                "                       \"uid\": \"" + uidFirstDocSED + "\",\n" +
                "                       \"reg_number\": \"" + regNumberFirstDocSED + "\",\n" +
                "                       \"reg_date\": \"" + regDateFirstDocSED + "\"\n" +
                "                   }\n" +
                "               },\n" +
                "               {\n" +
                "                   \"document_type\": \"Sed::Document\",\n" +
                "                   \"note\": \"t\",\n" +
                "                   \"sed_document\": {\n" +
                "                       \"uid\": \"" + uidFirstDocSED + "\",\n" +
                "                       \"reg_number\": \"" + regNumberFirstDocSED + "\",\n" +
                "                       \"reg_date\": \"" + regDateFirstDocSED + "\"\n" +
                "                   }\n" +
                "               }\n" +
                "           ]\n" +
                "       }\n" +
                "   ]\n" +
                "}";
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .body(requestBody)
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.assignments.0.documents.1.first_document_id[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("уже существует"));
    }

    @Test
    public void step09(){
        String requestBody = "{\n" +
                "\t\"skip_reg_date\": \"2024-01-17T06:06:31.485Z\",\n" +
                "    \"control_official_id\": \"" + idRandomOfficial + "\",\n" +
                "    \"control_subject_id\": " + idControlSubject + ",\n" +
                "    \"document_name\": \"Тест 404\",\n" +
                "    \"document_execution_state_id\": " + idDocumentExecutionState + ",\n" +
                "    \"skip_document_type_id\": " + idDocumentType + ",\n" +
                "    \"internal\": false,\n" +
                "    \"short_description\": \"404\",\n" +
                "    \"assignments\": [\n" +
                "       {\n" +
                "           \"number\": \"1\",\n" +
                "           \"content\": \"content\",\n" +
                "           \"note\": \"note\",\n" +
                "           \"links\": [\n" +
                "               {\n" +
                "                       \"document_type\": \"Documents::Document\",\n" +
                "                       \"document_id\": " + idDocumentFirst + "\n" +
                "               },\n" +
                "               {\n" +
                "                       \"document_type\": \"Documents::Document\",\n" +
                "                       \"document_id\": " + idDocumentFirst + "\n" +
                "               }\n" +
                "           ]\n" +
                "       }\n" +
                "   ]\n" +
                "}";
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .body(requestBody)
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.assignments.0.documents.1.first_document_id[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("уже существует"));
    }

    @Test
    public void step10(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .get(API_DOCUMENTS + String.format("/%s", idCreatedDocument))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        List<Integer> idLinks = jsonPath.getList("data.assignments[0].links.id");
        System.out.println("Links id - " + idLinks);
        List<Integer> idSortedLinks = new ArrayList<>(idLinks);
        Collections.sort(idSortedLinks,Collections.reverseOrder());
        System.out.println("Sorted links id - " + idSortedLinks);
        Assert.assertTrue(idLinks.equals(idSortedLinks));
    }

    @Test
    public void step11(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"links\": [\n" +
                "               \n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .body(requestBody)
                .put(API_DOCUMENTS + String.format("/%s", idCreatedDocument))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String documentTypeFirst = jsonPath.getString("data.assignments[0].links[0].document_type");
        String documentTypeSecond = jsonPath.getString("data.assignments[0].links[1].document_type");

        System.out.println("Document type first element - " + documentTypeFirst);
        System.out.println("Document type second element - " + documentTypeSecond);

        Assert.assertTrue(documentTypeFirst.equals("Documents::Document"));
        Assert.assertTrue(documentTypeSecond.equals("Sed::Document"));
    }

    @Test
    public void step12(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"links\": [\n" +
                "                {\n" +
                "                    \"id\": " + idCreatedLinkFirst + ",\n" +
                "                    \"document_type\": \"Documents::Document\",\n" +
                "                    \"document_id\": 106,\n" +
                "                    \"note\": \"s\"\n" +
                "         \n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": " + idCreatedLinkSecond + ",\n" +
                "                   \"document_type\": \"Sed::Document\",\n" +
                "                   \"note\": \"t\",\n" +
                "                   \"sed_document\": {\n" +
                "                       \"uid\": \"" + uidFirstDocSED + "\",\n" +
                "                       \"reg_number\": \"" + regNumberFirstDocSED + "g" + "\",\n" +
                "                       \"reg_date\": \"" + regDateFirstDocSED + "\"\n" +
                "                    }\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .body(requestBody)
                .put(API_DOCUMENTS + String.format("/%s", idCreatedDocument))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String noteFirstElement = jsonPath.getString("data.assignments[0].links[0].note");
        String nameSecondElement = jsonPath.getString("data.assignments[0].links[1].name");

        System.out.println("Added note - " + noteFirstElement);
        System.out.println("Added letter to name - " + nameSecondElement);

        Assert.assertTrue(noteFirstElement.equals("s"));
        Assert.assertTrue(nameSecondElement.contains("g"));
    }

    @AfterTest
    public void tearDown(){
        // Delete created control_subject
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
                .put(API_CONTROL_SUBJECTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathControlSubjectsDelete = responseControlSubjectDelete.jsonPath();
        // Delete created document execution states
        Response responseDESDeleteFirst = (Response) given()
                .header("Test-Authorization", 1)
                .delete(API_DOCUMENT_EXECUTION_STATES + String.format("/%s",idDocumentExecutionState))
                .then().log().all()
                .extract();

        //JsonPath jsonPathDESDeleteFirst = responseDESDeleteFirst.jsonPath();
        // Delete created document type
        Response responseDocumentType = (Response) given()
                .header("Test-Authorization", 1)
                .delete(API_DOC_TYPE + String.format("/%s",idDocumentType))
                .then().log().all()
                .extract();

        // JsonPath jsonPathDocumentType = responseDocumentType.jsonPath();
    }
}

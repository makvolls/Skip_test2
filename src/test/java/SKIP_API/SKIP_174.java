package SKIP_API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.given;

public class SKIP_174 {
    private static String API_DOCUMENTS = "http://api.skip.rtech.ru/v1/documents";
    // Variables for setup
    private static String API_USER = "http://api.skip.rtech.ru/v1/permissions/users";
    private static String API_CONTROL_SUBJECTS = "http://api.skip.rtech.ru/v1/permissions/control_subjects";
    private static String API_DOC_TYPE = "http://api.skip.rtech.ru/v1/classifiers/document_types";
    private static String API_DOCUMENT_EXECUTION_STATES = "http://api.skip.rtech.ru" +
            "/v1/classifiers/document_execution_states";
    private static String API_OSHS_MVD_OFFICIALS="http://api.skip.rtech.ru/v1/oshs/mvd/officials";
    private static String API_ORGANIZATIONS="http://api.skip.rtech.ru/v1/oshs/mvd/organizations";
    private static String API_OSHS = "http://api.skip.rtech.ru/v1/oshs";

    int userIdOne = 1; // User that have all rights, role id = 29
    int userIdSix = 6; // User that have right = "index_roles", role id = 5
    int idCreatedDocument; // Document id that will be created in step 11
    int idCreatedAssignment; // Assignment id that will be created in step 11
    int idCreatedExecutor; // Executor id that will be created in step 11
    int idCreatedCoExecutorFirst; // Co executor id that will be created in step 11 first element
    int idCreatedCoExecutorSecond; // Co executor id that will be created in step 11 second element
    int idCreatedCoExecutorThird; // Co executor id that will be created in step 11 third element
    int idControlSubject; // Created control subject's id
    int idDocumentExecutionStateOne; // Created document execution state's id first element
    int idDocumentType; // Created document type's id
    String idRandomOfficial; // Random official's id
    String idRandomEsdOrganization; // Random ESD organization's id
    String idRandomOrganization; // Random organization's id
    String idRandomOrganizationSecond; // Random organization's id second value
    String nameTemplate = "SKIP_174_Autotest";
    String shortNameTemplate = "SKIP_174";

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
        idDocumentExecutionStateOne = jsonPathDocumentExecutionStateCreate.getInt("data.id");
        System.out.println("Id document ex states first element- " + idDocumentExecutionStateOne);

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

        // Get executor_id from organization_oshs_mvd
        Response responseOrganizationEsd = given()
                .when()
                .params("types[]","Oshs::Esd::Organization")
                .params("items","200")
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .get(API_OSHS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathOrganizationEsd = responseOrganizationEsd.jsonPath();
        List<String> idOrganizationsEsd = jsonPathOrganizationEsd.getList("data.id");
        Random randomOrgEsd = new Random();
        String randomIdOrgEsd = idOrganizationsEsd.get(randomOrgEsd.nextInt(idOrganizationsEsd.size()));
        System.out.println("Random esd id - " + randomIdOrgEsd);
        idRandomEsdOrganization = randomIdOrgEsd;

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

        // Get organization's id from mvd organizations
        Response responseOrganizationsMvd = given()
                .when()
                .params("items","100")
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .get(API_ORGANIZATIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathOrganizationsMvd = responseOrganizationsMvd.jsonPath();
        List<String> idOrganizationsMvd = jsonPathOrganizationsMvd.getList("data.id");
        Random randomOrgMvd = new Random();
        String randomIdOrgMvd = idOrganizationsMvd.get(randomOrgMvd.nextInt(idOrganizationsMvd.size()));
        System.out.println("Random organization's id: " + randomIdOrgMvd);
        idRandomOrganization = randomIdOrgMvd;

        // Second random value from mvd organizations
        Response responseOrganizationsMvdSecond = given()
                .when()
                .params("page","2")
                .params("items","100")
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .get(API_ORGANIZATIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathOrganizationsMvdSecond = responseOrganizationsMvdSecond.jsonPath();
        List<String> idOrganizationsMvdSecondPage = jsonPathOrganizationsMvdSecond.getList("data.id");
        Random randomOrgMvdSecond = new Random();
        String randomIdOrgMvdSecond = idOrganizationsMvdSecondPage.
                get(randomOrgMvdSecond.nextInt(idOrganizationsMvdSecondPage.size()));
        System.out.println("Random organization's id second value : " + randomOrgMvdSecond);
        idRandomOrganizationSecond = randomIdOrgMvdSecond;
    }

    @Test
    public void step01(){
        String requestBody = "{\n" +
                "    \"skip_reg_date\": \"2024-01-17T06:06:31.485Z\",\n" +
                "    \"control_official_id\": \"" + idRandomOfficial + "\",\n" +
                "    \"control_subject_id\": " + idControlSubject + ",\n" +
                "    \"document_name\": \"Тест 404\",\n" +
                "    \"document_execution_state_id\": " + idDocumentExecutionStateOne + ",\n" +
                "    \"skip_document_type_id\": " + idDocumentType + ",\n" +
                "    \"internal\": false,\n" +
                "    \"short_description\": \"404\",\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"number\": \"1\",\n" +
                "            \"content\": \"content\",\n" +
                "            \"executors\": [\n" +
                "                {\n" +
                "                    \"organization_id\": \"" + idRandomOrganization + "\",\n" +
                "                    \"co_executors\": [\n" +
                "                        {\n" +
                "                               \"organization_short_title\": \"string\",\n" +
                "                               \"organization_id\": \"" + idRandomOrganization + "\",\n" +
                "                               \"organization_type\": \"Oshs::Mvd::Organization\",\n" +
                "                               \"co_executor_id\": \"" + idRandomOfficial + "\",\n" +
                "                               \"co_executor_name\": \"string\",\n" +
                "                               \"head_co_executor_id\": \"" + idRandomOfficial + "\",\n" +
                "                               \"head_co_executor_name\": \"string\",\n" +
                "                               \"provided\": true\n" +
                "                        }\n" +
                "                    ]\n" +
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
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String idOrganization = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[0].organization_id");
        String organizationType = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[0].organization_type");
        String idCoExecutor = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[0].co_executor_id");
        String nameCoExecutor = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[0].co_executor_name");
        String idHeadCoExecutor = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[0].head_co_executor_id");
        String nameHeadCoExecutor = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[0].head_co_executor_name");

        System.out.println("Id organization - " + idOrganization);
        System.out.println("Organization type - " + organizationType);
        System.out.println("Id co executor - " + idCoExecutor);
        System.out.println("Name co executor - " + nameCoExecutor);
        System.out.println("Id head co executor - " + idHeadCoExecutor);
        System.out.println("Name head co executor - " + nameHeadCoExecutor);

        Assert.assertTrue(idOrganization.equals(idRandomOrganization));
        Assert.assertTrue(organizationType.equals("Oshs::Mvd::Organization"));
        Assert.assertTrue(idCoExecutor.equals(idRandomOfficial));
        Assert.assertTrue(nameCoExecutor.equals("string"));
        Assert.assertTrue(idHeadCoExecutor.equals(idRandomOfficial));
        Assert.assertTrue(nameHeadCoExecutor.equals("string"));
    }

    @Test
    public void step02(){
        String requestBody = "{\n" +
                "    \"skip_reg_date\": \"2024-01-17T06:06:31.485Z\",\n" +
                "    \"control_official_id\": \"" + idRandomOfficial + "\",\n" +
                "    \"control_subject_id\": " + idControlSubject + ",\n" +
                "    \"document_name\": \"Тест 404\",\n" +
                "    \"document_execution_state_id\": " + idDocumentExecutionStateOne + ",\n" +
                "    \"skip_document_type_id\": " + idDocumentType + ",\n" +
                "    \"internal\": false,\n" +
                "    \"short_description\": \"404\",\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"number\": \"1\",\n" +
                "            \"content\": \"content\",\n" +
                "            \"executors\": [\n" +
                "                {\n" +
                "                    \"organization_id\": \"" + idRandomOrganization + "\",\n" +
                "                    \"co_executors\": [\n" +
                "                    ]\n" +
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
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String coExecutors = jsonPath.getString("data.assignments[0].executors[0].co_executors");

        System.out.println("Co executors - " + coExecutors);

        Assert.assertTrue(coExecutors.equals("[]"));
    }

    @Test
    public void step03(){
        String requestBody = "{\n" +
                "    \"skip_reg_date\": \"2024-01-17T06:06:31.485Z\",\n" +
                "    \"control_official_id\": \"" + idRandomOfficial + "\",\n" +
                "    \"control_subject_id\": " + idControlSubject + ",\n" +
                "    \"document_name\": \"Тест 404\",\n" +
                "    \"document_execution_state_id\": " + idDocumentExecutionStateOne + ",\n" +
                "    \"skip_document_type_id\": " + idDocumentType + ",\n" +
                "    \"internal\": false,\n" +
                "    \"short_description\": \"404\",\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"number\": \"1\",\n" +
                "            \"content\": \"content\",\n" +
                "            \"executors\": [\n" +
                "                {\n" +
                "                    \"organization_id\": \"" + idRandomOrganization + "\",\n" +
                "                    \"co_executors\": [\n" +
                "                        {\n" +
                "                               \"organization_short_title\": \"string\",\n" +
                "                               \"organization_id\": \"" + idRandomOrganization + "\",\n" +
                "                               \"organization_type\": \"Oshs::Esd::Organization\",\n" +
                "                               \"co_executor_id\": \"" + idRandomOfficial + "\",\n" +
                "                               \"co_executor_name\": \"string\",\n" +
                "                               \"head_co_executor_id\": \"" + idRandomOfficial + "\",\n" +
                "                               \"head_co_executor_name\": \"string\",\n" +
                "                               \"provided\": true\n" +
                "                        }\n" +
                "                    ]\n" +
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
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.
                getString("errors.assignments.0.executors.0.co_executors.0.organization_id[0]");

        System.out.println("Error message : " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step04(){
        String requestBody = "{\n" +
                "    \"skip_reg_date\": \"2024-01-17T06:06:31.485Z\",\n" +
                "    \"control_official_id\": \"" + idRandomOfficial + "\",\n" +
                "    \"control_subject_id\": " + idControlSubject + ",\n" +
                "    \"document_name\": \"Тест 404\",\n" +
                "    \"document_execution_state_id\": " + idDocumentExecutionStateOne + ",\n" +
                "    \"skip_document_type_id\": " + idDocumentType + ",\n" +
                "    \"internal\": false,\n" +
                "    \"short_description\": \"404\",\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"number\": \"1\",\n" +
                "            \"content\": \"content\",\n" +
                "            \"executors\": [\n" +
                "                {\n" +
                "                    \"organization_id\": \"" + idRandomOrganization + "\",\n" +
                "                    \"co_executors\": [\n" +
                "                        {\n" +
                "                               \"organization_short_title\": \"string\",\n" +
                "                               \"organization_id\": \"" + idRandomEsdOrganization + "\",\n" +
                "                               \"organization_type\": \"Oshs::Esd::Organization\",\n" +
                "                               \"co_executor_id\": \"" + idRandomOfficial + "\",\n" +
                "                               \"co_executor_name\": \"string\",\n" +
                "                               \"head_co_executor_id\": \"" + idRandomOfficial + "\",\n" +
                "                               \"head_co_executor_name\": \"string\",\n" +
                "                               \"provided\": true\n" +
                "                        }\n" +
                "                    ]\n" +
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
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String idOrganization = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[0].organization_id");
        String organizationType = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[0].organization_type");
        String idCoExecutor = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[0].co_executor_id");
        String nameCoExecutor = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[0].co_executor_name");
        String idHeadCoExecutor = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[0].head_co_executor_id");
        String nameHeadCoExecutor = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[0].head_co_executor_name");

        System.out.println("Id organization - " + idOrganization);
        System.out.println("Organization type - " + organizationType);
        System.out.println("Id co executor - " + idCoExecutor);
        System.out.println("Name co executor - " + nameCoExecutor);
        System.out.println("Id head co executor - " + idHeadCoExecutor);
        System.out.println("Name head co executor - " + nameHeadCoExecutor);

        Assert.assertTrue(idOrganization.equals(idRandomEsdOrganization));
        Assert.assertTrue(organizationType.equals("Oshs::Esd::Organization"));
        Assert.assertTrue(idCoExecutor.equals(idRandomOfficial));
        Assert.assertTrue(nameCoExecutor.equals("string"));
        Assert.assertTrue(idHeadCoExecutor.equals(idRandomOfficial));
        Assert.assertTrue(nameHeadCoExecutor.equals("string"));
    }

    @Test
    public void step05(){
        String requestBody = "{\n" +
                "    \"skip_reg_date\": \"2024-01-17T06:06:31.485Z\",\n" +
                "    \"control_official_id\": \"" + idRandomOfficial + "\",\n" +
                "    \"control_subject_id\": " + idControlSubject + ",\n" +
                "    \"document_name\": \"Тест 404\",\n" +
                "    \"document_execution_state_id\": " + idDocumentExecutionStateOne + ",\n" +
                "    \"skip_document_type_id\": " + idDocumentType + ",\n" +
                "    \"internal\": false,\n" +
                "    \"short_description\": \"404\",\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"number\": \"1\",\n" +
                "            \"content\": \"content\",\n" +
                "            \"executors\": [\n" +
                "                {\n" +
                "                    \"organization_id\": \"" + idRandomOrganization + "\",\n" +
                "                    \"co_executors\": [\n" +
                "                        {\n" +
                "                               \"organization_short_title\": \"string\",\n" +
                "                               \"organization_id\": \"" + idRandomOfficial + "\",\n" +
                "                               \"organization_type\": \"Oshs::Mvd::Officials\",\n" +
                "                               \"co_executor_id\": \"" + idRandomOfficial + "\",\n" +
                "                               \"co_executor_name\": \"string\",\n" +
                "                               \"head_co_executor_id\": \"" + idRandomOfficial + "\",\n" +
                "                               \"head_co_executor_name\": \"string\",\n" +
                "                               \"provided\": true\n" +
                "                        }\n" +
                "                    ]\n" +
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
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.
                getString("errors.assignments.0.executors.0.co_executors.0.organization_type[0]");

        System.out.println("Error message : " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step06(){
        String requestBody = "{\n" +
                "    \"skip_reg_date\": \"2024-01-17T06:06:31.485Z\",\n" +
                "    \"control_official_id\": \"" + idRandomOfficial + "\",\n" +
                "    \"control_subject_id\": " + idControlSubject + ",\n" +
                "    \"document_name\": \"Тест 404\",\n" +
                "    \"document_execution_state_id\": " + idDocumentExecutionStateOne + ",\n" +
                "    \"skip_document_type_id\": " + idDocumentType + ",\n" +
                "    \"internal\": false,\n" +
                "    \"short_description\": \"404\",\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"number\": \"1\",\n" +
                "            \"content\": \"content\",\n" +
                "            \"executors\": [\n" +
                "                {\n" +
                "                    \"organization_id\": \"" + idRandomOrganization + "\",\n" +
                "                    \"co_executors\": [\n" +
                "                        {\n" +
                "                               \"organization_short_title\": \"\",\n" +
                "                               \"organization_id\": \"" + idRandomOrganization + "\",\n" +
                "                               \"organization_type\": \"Oshs::Mvd::Organization\",\n" +
                "                               \"co_executor_id\": \"" + idRandomOfficial + "\",\n" +
                "                               \"co_executor_name\": \"string\",\n" +
                "                               \"head_co_executor_id\": \"" + idRandomOfficial + "\",\n" +
                "                               \"head_co_executor_name\": \"string\",\n" +
                "                               \"provided\": true\n" +
                "                        }\n" +
                "                    ]\n" +
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
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.
                getString("errors.assignments.0.executors.0.co_executors.0.organization_short_title[0]");

        System.out.println("Error message : " + errorMessage);

        Assert.assertTrue(errorMessage.equals("не может быть пустым"));
    }

    @Test
    public void step07(){
        String requestBody = "{\n" +
                "    \"skip_reg_date\": \"2024-01-17T06:06:31.485Z\",\n" +
                "    \"control_official_id\": \"" + idRandomOfficial + "\",\n" +
                "    \"control_subject_id\": " + idControlSubject + ",\n" +
                "    \"document_name\": \"Тест 404\",\n" +
                "    \"document_execution_state_id\": " + idDocumentExecutionStateOne + ",\n" +
                "    \"skip_document_type_id\": " + idDocumentType + ",\n" +
                "    \"internal\": false,\n" +
                "    \"short_description\": \"404\",\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"number\": \"1\",\n" +
                "            \"content\": \"content\",\n" +
                "            \"executors\": [\n" +
                "                {\n" +
                "                    \"organization_id\": \"" + idRandomOrganization + "\",\n" +
                "                    \"co_executors\": [\n" +
                "                        {\n" +
                "                               \"organization_short_title\": \"test1\"\n" +
                "                        }\n" +
                "                    ]\n" +
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
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String organizationShortTitle = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[0].organization_short_title");

        System.out.println("Organization short title - " + organizationShortTitle);

        Assert.assertTrue(organizationShortTitle.equals("test1"));
    }

    @Test
    public void step08(){
        String requestBody = "{\n" +
                "    \"skip_reg_date\": \"2024-01-17T06:06:31.485Z\",\n" +
                "    \"control_official_id\": \"" + idRandomOfficial + "\",\n" +
                "    \"control_subject_id\": " + idControlSubject + ",\n" +
                "    \"document_name\": \"Тест 404\",\n" +
                "    \"document_execution_state_id\": " + idDocumentExecutionStateOne + ",\n" +
                "    \"skip_document_type_id\": " + idDocumentType + ",\n" +
                "    \"internal\": false,\n" +
                "    \"short_description\": \"404\",\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"number\": \"1\",\n" +
                "            \"content\": \"content\",\n" +
                "            \"executors\": [\n" +
                "                {\n" +
                "                    \"organization_id\": \"" + idRandomOrganization + "\",\n" +
                "                    \"co_executors\": [\n" +
                "                        {\n" +
                "                               \"organization_short_title\": \"test1\",\n" +
                "                               \"organization_type\": \"Oshs::Mvd::Organization\"\n" +
                "                        }\n" +
                "                    ]\n" +
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
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.
                getString("errors.assignments.0.executors.0.co_executors.0.organization_id[0]");

        System.out.println("Error message : " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step09(){
        String requestBody = "{\n" +
                "    \"skip_reg_date\": \"2024-01-17T06:06:31.485Z\",\n" +
                "    \"control_official_id\": \"" + idRandomOfficial + "\",\n" +
                "    \"control_subject_id\": " + idControlSubject + ",\n" +
                "    \"document_name\": \"Тест 404\",\n" +
                "    \"document_execution_state_id\": " + idDocumentExecutionStateOne + ",\n" +
                "    \"skip_document_type_id\": " + idDocumentType + ",\n" +
                "    \"internal\": false,\n" +
                "    \"short_description\": \"404\",\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"number\": \"1\",\n" +
                "            \"content\": \"content\",\n" +
                "            \"executors\": [\n" +
                "                {\n" +
                "                    \"organization_id\": \"" + idRandomOrganization + "\",\n" +
                "                    \"co_executors\": [\n" +
                "                        {\n" +
                "                               \"organization_short_title\": \"test1\",\n" +
                "                               \"organization_id\": \"" + idRandomOrganization + "\",\n" +
                "                               \"organization_type\": \"Oshs::Mvd::Organization\"\n" +
                "                        }\n" +
                "                    ]\n" +
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
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String organizationType = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[0].organization_type");
        String idOrganization = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[0].organization_id");
        String organizationShortTitle = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[0].organization_short_title");

        System.out.println("Organization type - " + organizationType);
        System.out.println("Id organization - " + idOrganization);
        System.out.println("Organization short title - " + organizationShortTitle);

        Assert.assertTrue(organizationType.equals("Oshs::Mvd::Organization"));
        Assert.assertTrue(idOrganization.equals(idRandomOrganization));
        Assert.assertTrue(organizationShortTitle.equals("test1"));
    }

    @Test
    public void step10(){
        String requestBody = "{\n" +
                "    \"skip_reg_date\": \"2024-01-17T06:06:31.485Z\",\n" +
                "    \"control_official_id\": \"" + idRandomOfficial + "\",\n" +
                "    \"control_subject_id\": " + idControlSubject + ",\n" +
                "    \"document_name\": \"Тест 404\",\n" +
                "    \"document_execution_state_id\": " + idDocumentExecutionStateOne + ",\n" +
                "    \"skip_document_type_id\": " + idDocumentType + ",\n" +
                "    \"internal\": false,\n" +
                "    \"short_description\": \"404\",\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"number\": \"1\",\n" +
                "            \"content\": \"content\",\n" +
                "            \"executors\": [\n" +
                "                {\n" +
                "                    \"organization_id\": \"" + idRandomOrganization + "\",\n" +
                "                    \"co_executors\": [\n" +
                "                        {\n" +
                "                               \"organization_short_title\": \"A\",\n" +
                "                               \"organization_id\": \"" + idRandomOrganization + "\",\n" +
                "                               \"organization_type\": \"Oshs::Mvd::Organization\",\n" +
                "                               \"co_executor_id\": \"" + idRandomOfficial + "\",\n" +
                "                               \"co_executor_name\": \"string\",\n" +
                "                               \"head_co_executor_id\": \"" + idRandomOfficial + "\",\n" +
                "                               \"head_co_executor_name\": \"string\",\n" +
                "                               \"provided\": true\n" +
                "                        },\n" +
                "                        {\n" +
                "                               \"organization_short_title\": \"A\",\n" +
                "                               \"organization_id\": \"" + idRandomOrganization + "\",\n" +
                "                               \"organization_type\": \"Oshs::Mvd::Organization\",\n" +
                "                               \"co_executor_id\": \"" + idRandomOfficial + "\",\n" +
                "                               \"co_executor_name\": \"string\",\n" +
                "                               \"head_co_executor_id\": \"" + idRandomOfficial + "\",\n" +
                "                               \"head_co_executor_name\": \"string\",\n" +
                "                               \"provided\": true\n" +
                "                        }\n" +
                "                    ]\n" +
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
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.
                getString("errors.assignments.0.executors.0.co_executors.1.organization_id[0]");

        System.out.println("Error message : " + errorMessage);

        Assert.assertTrue(errorMessage.equals("уже существует"));
    }

    @Test
    public void step11(){
        String requestBody = "{\n" +
                "    \"skip_reg_date\": \"2024-01-17T06:06:31.485Z\",\n" +
                "    \"control_official_id\": \"" + idRandomOfficial + "\",\n" +
                "    \"control_subject_id\": " + idControlSubject + ",\n" +
                "    \"document_name\": \"Тест 404\",\n" +
                "    \"document_execution_state_id\": " + idDocumentExecutionStateOne + ",\n" +
                "    \"skip_document_type_id\": " + idDocumentType + ",\n" +
                "    \"internal\": false,\n" +
                "    \"short_description\": \"404\",\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"number\": \"1\",\n" +
                "            \"content\": \"content\",\n" +
                "            \"executors\": [\n" +
                "                {\n" +
                "                    \"organization_id\": \"" + idRandomOrganization + "\",\n" +
                "                    \"co_executors\": [\n" +
                "                        {\n" +
                "                               \"organization_short_title\": \"A\",\n" +
                "                               \"organization_id\": \"" + idRandomOrganization + "\",\n" +
                "                               \"organization_type\": \"Oshs::Mvd::Organization\",\n" +
                "                               \"co_executor_id\": \"" + idRandomOfficial + "\",\n" +
                "                               \"co_executor_name\": \"string\",\n" +
                "                               \"head_co_executor_id\": \"" + idRandomOfficial + "\",\n" +
                "                               \"head_co_executor_name\": \"string\",\n" +
                "                               \"provided\": true\n" +
                "                        },\n" +
                "                        {\n" +
                "                               \"organization_short_title\": \"A\",\n" +
                "                               \"organization_id\": \"" + idRandomOrganizationSecond + "\",\n" +
                "                               \"organization_type\": \"Oshs::Mvd::Organization\",\n" +
                "                               \"co_executor_id\": \"" + idRandomOfficial + "\",\n" +
                "                               \"co_executor_name\": \"string\",\n" +
                "                               \"head_co_executor_id\": \"" + idRandomOfficial + "\",\n" +
                "                               \"head_co_executor_name\": \"string\",\n" +
                "                               \"provided\": true\n" +
                "                        },\n" +
                "                        {\n" +
                "                               \"organization_short_title\": \"Aбв\",\n" +
                "                               \"organization_id\": \"" + idRandomEsdOrganization + "\",\n" +
                "                               \"organization_type\": \"Oshs::Esd::Organization\",\n" +
                "                               \"co_executor_id\": \"" + idRandomOfficial + "\",\n" +
                "                               \"co_executor_name\": \"А\",\n" +
                "                               \"head_co_executor_id\": \"" + idRandomOfficial + "\",\n" +
                "                               \"head_co_executor_name\": \"А\",\n" +
                "                               \"provided\": true\n" +
                "                        }\n" +
                "                    ]\n" +
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
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        idCreatedDocument = jsonPath.getInt("data.id");
        idCreatedAssignment = jsonPath.getInt("data.assignments[0].id");
        idCreatedExecutor = jsonPath.getInt("data.assignments[0].executors[0].id");
        idCreatedCoExecutorFirst = jsonPath.getInt("data.assignments[0].executors[0].co_executors[0].id");
        idCreatedCoExecutorSecond = jsonPath.getInt("data.assignments[0].executors[0].co_executors[1].id");
        idCreatedCoExecutorThird  = jsonPath.getInt("data.assignments[0].executors[0].co_executors[2].id");

        System.out.println("Id created document - " + idCreatedDocument);
        System.out.println("Id created assignment - " + idCreatedAssignment);
        System.out.println("Id created executor - " + idCreatedExecutor);
        System.out.println("Id created co executor first element - " + idCreatedCoExecutorFirst);
        System.out.println("Id created co executor second element - " + idCreatedCoExecutorSecond);
        System.out.println("Id created co executor third element - " + idCreatedCoExecutorThird);

        String shortTitleFirst = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[0].organization_short_title");
        String organizationIdFirst = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[0].organization_id");
        String organizationTypeFirst = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[0].organization_type");
        String coExecutorIdFirst = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[0].co_executor_id");
        String coExecutorNameFirst = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[0].co_executor_name");
        String coHeadExecutorIdFirst = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[0].head_co_executor_id");
        String coHeadExecutorNameFirst = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[0].head_co_executor_name");
        String providedFirst = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[0].provided");
        String shortTitleSecond = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[1].organization_short_title");
        String organizationIdSecond = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[1].organization_id");
        String organizationTypeSecond = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[1].organization_type");
        String coExecutorIdSecond = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[1].co_executor_id");
        String coExecutorNameSecond = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[1].co_executor_name");
        String coHeadExecutorIdSecond = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[1].head_co_executor_id");
        String coHeadExecutorNameSecond = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[1].head_co_executor_name");
        String providedSecond = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[1].provided");
        String shortTitleThird = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[2].organization_short_title");
        String organizationIdThird = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[2].organization_id");
        String organizationTypeThird = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[2].organization_type");
        String coExecutorIdThird = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[2].co_executor_id");
        String coExecutorNameThird = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[2].co_executor_name");
        String coHeadExecutorIdThird = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[2].head_co_executor_id");
        String coHeadExecutorNameThird = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[2].head_co_executor_name");
        String providedThird = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[2].provided");

        System.out.println("Short title first co executor - " + shortTitleFirst);
        System.out.println("Organization id first co executor - " + organizationIdFirst);
        System.out.println("Organization type first co executor - " + organizationTypeFirst);
        System.out.println("Co executor id first - " + coExecutorIdFirst);
        System.out.println("Co executor name first - " + coExecutorNameFirst);
        System.out.println("Co head executor id first - " + coHeadExecutorIdFirst);
        System.out.println("Co head executor name first - " + coHeadExecutorNameFirst);
        System.out.println("Provided first co executor - " + providedFirst);
        System.out.println("Short title second co executor - " + shortTitleSecond);
        System.out.println("Organization id second co executor - " + organizationIdSecond);
        System.out.println("Organization type second co executor - " + organizationTypeSecond);
        System.out.println("Co executor id second - " + coExecutorIdSecond);
        System.out.println("Co executor name second - " + coExecutorNameSecond);
        System.out.println("Co head executor id second - " + coHeadExecutorIdSecond);
        System.out.println("Co head executor name second - " + coHeadExecutorNameSecond);
        System.out.println("Provided second co executor - " + providedSecond);
        System.out.println("Short title third co executor - " + shortTitleThird);
        System.out.println("Organization id third co executor - " + organizationIdThird);
        System.out.println("Organization type third co executor - " + organizationTypeThird);
        System.out.println("Co executor id third - " + coExecutorIdThird);
        System.out.println("Co head executor name third - " + coExecutorNameThird);
        System.out.println("Co head executor id third - " + coHeadExecutorIdThird);
        System.out.println("Co head executor name third - " + coHeadExecutorNameThird);
        System.out.println("Provided third co executor - " + providedThird);


        Assert.assertTrue(shortTitleFirst.equals("A"));
        Assert.assertTrue(organizationIdFirst.equals(idRandomOrganization));
        Assert.assertTrue(organizationTypeFirst.equals("Oshs::Mvd::Organization"));
        Assert.assertTrue(coExecutorIdFirst.equals(idRandomOfficial));
        Assert.assertTrue(coExecutorNameFirst.equals("string"));
        Assert.assertTrue(coHeadExecutorIdFirst.equals(idRandomOfficial));
        Assert.assertTrue(coHeadExecutorNameFirst.equals("string"));
        Assert.assertTrue(providedFirst.equals("true"));
        Assert.assertTrue(shortTitleSecond.equals("A"));
        Assert.assertTrue(organizationIdSecond.equals(idRandomOrganizationSecond));
        Assert.assertTrue(organizationTypeSecond.equals("Oshs::Mvd::Organization"));
        Assert.assertTrue(coExecutorIdSecond.equals(idRandomOfficial));
        Assert.assertTrue(coExecutorNameSecond.equals("string"));
        Assert.assertTrue(coHeadExecutorIdSecond.equals(idRandomOfficial));
        Assert.assertTrue(coHeadExecutorNameSecond.equals("string"));
        Assert.assertTrue(providedSecond.equals("true"));
        Assert.assertTrue(shortTitleThird.equals("Aбв"));
        Assert.assertTrue(organizationIdThird.equals(idRandomEsdOrganization));
        Assert.assertTrue(organizationTypeThird.equals("Oshs::Esd::Organization"));
        Assert.assertTrue(coExecutorIdThird.equals(idRandomOfficial));
        Assert.assertTrue(coExecutorNameThird.equals("А"));
        Assert.assertTrue(coHeadExecutorIdThird.equals(idRandomOfficial));
        Assert.assertTrue(coHeadExecutorNameThird.equals("А"));
        Assert.assertTrue(providedThird.equals("true"));
    }

    @Test
    public void step12(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .get(API_DOCUMENTS + String.format("/%s",idCreatedDocument))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        List<Integer> idCoExecutors = jsonPath.
                getList("data.assignments[0].executors[0].co_executors.id");
        System.out.println("Co executors ids : " + idCoExecutors);
        List<Integer> idCoExecutorsSorted = new ArrayList<>(idCoExecutors);
        Collections.sort(idCoExecutorsSorted);
        System.out.println("Co executors ids sorted : " + idCoExecutorsSorted);
        Assert.assertTrue(idCoExecutors.equals(idCoExecutorsSorted));
    }

    @Test
    public void step13(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"executors\": [\n" +
                "                {\n" +
                "                    \"id\": " + idCreatedExecutor + ",\n" +
                "                    \"co_executors\": []\n" +
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
                .put(API_DOCUMENTS + String.format("/%s",idCreatedDocument))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();


        int coExecutorFirst = jsonPath.getInt("data.assignments[0].executors[0].co_executors[0].id");
        int coExecutorSecond = jsonPath.getInt("data.assignments[0].executors[0].co_executors[1].id");
        int coExecutorThird = jsonPath.getInt("data.assignments[0].executors[0].co_executors[2].id");
        System.out.println("First id - " + coExecutorFirst);
        System.out.println("Second id - " + coExecutorSecond);
        System.out.println("Third id - " + coExecutorThird);
        Assert.assertNotNull(coExecutorFirst);
        Assert.assertNotNull(coExecutorSecond);
        Assert.assertNotNull(coExecutorThird);
    }

    @Test
    public void step14(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"executors\": [\n" +
                "                {\n" +
                "                    \"id\": " + idCreatedExecutor + ",\n" +
                "                    \"co_executors\": [\n" +
                "                        {\n" +
                "                            \"id\": " + idCreatedCoExecutorThird + ",\n" +
                "                            \"organization_id\": \"658d3dd057b6000002000001\",\n" +
                "                            \"organization_short_title\": \"test1\",\n" +
                "                            \"organization_type\": \"Oshs::Mvd::Organization\",\n" +
                "                            \"co_executor_id\": \"652e404ca779000006000001\",\n" +
                "                            \"co_executor_name\": \"test3\",\n" +
                "                            \"head_co_executor_id\": \"5a72362e0a9900001b000001\",\n" +
                "                            \"head_co_executor_name\": \"test2\",\n" +
                "                            \"provided\": true\n" +
                "                        }\n" +
                "                    ]\n" +
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
                .put(API_DOCUMENTS + String.format("/%s",idCreatedDocument))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String shortTitleChange = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[2].organization_short_title");
        String organizationIdChange = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[2].organization_id");
        String organizationTypeChange = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[2].organization_type");
        String coExecutorIdChange = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[2].co_executor_id");
        String coExecutorNameChange = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[2].co_executor_name");
        String coHeadExecutorIdChange = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[2].head_co_executor_id");
        String coHeadExecutorNameChange = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[2].head_co_executor_name");
        String providedChange = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[2].provided");

        System.out.println("Short title co executor - " + shortTitleChange);
        System.out.println("Organization id co executor - " + organizationIdChange);
        System.out.println("Organization type co executor - " + organizationTypeChange);
        System.out.println("Co executor id - " + coExecutorIdChange);
        System.out.println("Co head executor name - " + coExecutorNameChange);
        System.out.println("Co head executor id - " + coHeadExecutorIdChange);
        System.out.println("Co head executor name - " + coHeadExecutorNameChange);
        System.out.println("Provided co executor - " + providedChange);

        Assert.assertTrue(shortTitleChange.equals("test1"));
        Assert.assertTrue(organizationIdChange.equals("658d3dd057b6000002000001"));
        Assert.assertTrue(organizationTypeChange.equals("Oshs::Mvd::Organization"));
        Assert.assertTrue(coExecutorIdChange.equals("652e404ca779000006000001"));
        Assert.assertTrue(coExecutorNameChange.equals("test3"));
        Assert.assertTrue(coHeadExecutorIdChange.equals("5a72362e0a9900001b000001"));
        Assert.assertTrue(coHeadExecutorNameChange.equals("test2"));
        Assert.assertTrue(providedChange.equals("true"));
    }

    @Test
    public void step15(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"executors\": [\n" +
                "                {\n" +
                "                    \"id\": " + idCreatedExecutor + ",\n" +
                "                    \"co_executors\": [\n" +
                "                        {\n" +
                "                            \"id\": " + idCreatedCoExecutorSecond + ",\n" +
                "                            \"organization_id\": \"\",\n" +
                "                            \"organization_short_title\": \"test1\",\n" +
                "                            \"organization_type\": \"Oshs::Mvd::Organization\",\n" +
                "                            \"co_executor_id\": \"652e404ca779000006000001\",\n" +
                "                            \"co_executor_name\": \"test3\",\n" +
                "                            \"head_co_executor_id\": \"5a72362e0a9900001b000001\",\n" +
                "                            \"head_co_executor_name\": \"test2\",\n" +
                "                            \"provided\": true\n" +
                "                        }\n" +
                "                    ]\n" +
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
                .put(API_DOCUMENTS + String.format("/%s",idCreatedDocument))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.
                getString("errors.assignments.0.executors.0.co_executors.0.organization_id[0]");

        System.out.println("Error message : " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step16(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"executors\": [\n" +
                "                {\n" +
                "                    \"id\": " + idCreatedExecutor + ",\n" +
                "                    \"co_executors\": [\n" +
                "                        {\n" +
                "                            \"id\": " + idCreatedCoExecutorSecond + ",\n" +
                "                            \"organization_id\": \"658d3dd057b6000002000001\",\n" +
                "                            \"organization_short_title\": \"test1\",\n" +
                "                            \"organization_type\": \"Oshs::Mvd::Organization\",\n" +
                "                            \"co_executor_id\": \"652e404ca779000006000001\",\n" +
                "                            \"co_executor_name\": \"test3\",\n" +
                "                            \"head_co_executor_id\": \"5a72362e0a9900001b000001\",\n" +
                "                            \"head_co_executor_name\": \"test2\",\n" +
                "                            \"provided\": true\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": " + idCreatedCoExecutorFirst + ",\n" +
                "                            \"organization_id\": \"658d3dd057b6000002000001\",\n" +
                "                            \"organization_short_title\": \"test1\",\n" +
                "                            \"organization_type\": \"Oshs::Mvd::Organization\",\n" +
                "                            \"co_executor_id\": \"652e404ca779000006000001\",\n" +
                "                            \"co_executor_name\": \"test3\",\n" +
                "                            \"head_co_executor_id\": \"5a72362e0a9900001b000001\",\n" +
                "                            \"head_co_executor_name\": \"test2\",\n" +
                "                            \"provided\": true\n" +
                "                        }\n" +
                "                    ]\n" +
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
                .put(API_DOCUMENTS + String.format("/%s",idCreatedDocument))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessageFirst = jsonPath.
                getString("errors.assignments.0.executors.0.co_executors.0.organization_id[0]");
        String errorMessageSecond = jsonPath.
                getString("errors.assignments.0.executors.0.co_executors.1.organization_id[0]");

        System.out.println("First message - " + errorMessageFirst);
        System.out.println("Second message - " + errorMessageSecond);

        Assert.assertTrue(errorMessageFirst.equals("уже существует"));
        Assert.assertTrue(errorMessageSecond.equals("уже существует"));
    }

    @Test
    public void step17(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"executors\": [\n" +
                "                {\n" +
                "                    \"id\": " + idCreatedExecutor + ",\n" +
                "                    \"co_executors\": [\n" +
                "                        {\n" +
                "                            \"id\": " + idCreatedCoExecutorFirst + ",\n" +
                "                            \"organization_id\": \"цафуыквапрп\",\n" +
                "                            \"organization_short_title\": \"test1\",\n" +
                "                            \"organization_type\": \"Oshs::Mvd::Organization\",\n" +
                "                            \"co_executor_id\": \"652e404ca779000006000001\",\n" +
                "                            \"co_executor_name\": \"test3\",\n" +
                "                            \"head_co_executor_id\": \"5a72362e0a9900001b000001\",\n" +
                "                            \"head_co_executor_name\": \"test2\",\n" +
                "                            \"provided\": true\n" +
                "                        }\n" +
                "                    ]\n" +
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
                .put(API_DOCUMENTS + String.format("/%s",idCreatedDocument))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.
                getString("errors.assignments.0.executors.0.co_executors.0.organization_id[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
    }


    @Test
    public void step18(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"executors\": [\n" +
                "                {\n" +
                "                    \"id\": " + idCreatedExecutor + ",\n" +
                "                    \"co_executors\": [\n" +
                "                        {\n" +
                "                            \"id\": " + idCreatedCoExecutorFirst + ",\n" +
                "                            \"organization_id\": \"5a72362e0a9900001b000001\",\n" +
                "                            \"organization_short_title\": \"test1\",\n" +
                "                            \"organization_type\": \"Oshs::Mvd::Organization\",\n" +
                "                            \"co_executor_id\": \"652e404ca779000006000001\",\n" +
                "                            \"co_executor_name\": \"test3\",\n" +
                "                            \"head_co_executor_id\": \"5a72362e0a9900001b000001\",\n" +
                "                            \"head_co_executor_name\": \"test2\",\n" +
                "                            \"provided\": true\n" +
                "                        }\n" +
                "                    ]\n" +
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
                .put(API_DOCUMENTS + String.format("/%s",idCreatedDocument))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.
                getString("errors.assignments.0.executors.0.co_executors.0.organization_id[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
    }


    @Test
    public void step19(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"executors\": [\n" +
                "                {\n" +
                "                    \"id\": " + idCreatedExecutor + ",\n" +
                "                    \"co_executors\": [\n" +
                "                        {\n" +
                "                            \"id\": " + idCreatedCoExecutorFirst + ",\n" +
                "                            \"organization_id\": \"5a72362e0a9900001b000001\",\n" +
                "                            \"organization_short_title\": \"test1\",\n" +
                "                            \"organization_type\": \"Oshs::Mvd::Officials\",\n" +
                "                            \"co_executor_id\": \"652e404ca779000006000001\",\n" +
                "                            \"co_executor_name\": \"test3\",\n" +
                "                            \"head_co_executor_id\": \"5a72362e0a9900001b000001\",\n" +
                "                            \"head_co_executor_name\": \"test2\",\n" +
                "                            \"provided\": true\n" +
                "                        }\n" +
                "                    ]\n" +
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
                .put(API_DOCUMENTS + String.format("/%s",idCreatedDocument))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.
                getString("errors.assignments.0.executors.0.co_executors.0.organization_type[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
    }


    @Test
    public void step20(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"executors\": [\n" +
                "                {\n" +
                "                    \"id\": " + idCreatedExecutor + ",\n" +
                "                    \"co_executors\": [\n" +
                "                        {\n" +
                "                            \"id\": " + idCreatedCoExecutorFirst + ",\n" +
                "                            \"organization_id\": \"5a72362e0a9900001b000001\",\n" +
                "                            \"organization_short_title\": \"\",\n" +
                "                            \"organization_type\": \"Oshs::Mvd::Organization\",\n" +
                "                            \"co_executor_id\": \"652e404ca779000006000001\",\n" +
                "                            \"co_executor_name\": \"test3\",\n" +
                "                            \"head_co_executor_id\": \"5a72362e0a9900001b000001\",\n" +
                "                            \"head_co_executor_name\": \"test2\",\n" +
                "                            \"provided\": true\n" +
                "                        }\n" +
                "                    ]\n" +
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
                .put(API_DOCUMENTS + String.format("/%s",idCreatedDocument))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.
                getString("errors.assignments.0.executors.0.co_executors.0.organization_short_title[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("не может быть пустым"));
    }


    @Test
    public void step21(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"executors\": [\n" +
                "                {\n" +
                "                    \"id\": " + idCreatedExecutor + ",\n" +
                "                    \"co_executors\": [\n" +
                "                        {\n" +
                "                            \"organization_id\": \"56f3df4ecb26000004000001\",\n" +
                "                            \"organization_short_title\": \"t\",\n" +
                "                            \"organization_type\": \"Oshs::Mvd::Organization\",\n" +
                "                            \"co_executor_id\": \"652e404ca779000006000001\",\n" +
                "                            \"co_executor_name\": \"test3\",\n" +
                "                            \"head_co_executor_id\": \"5a72362e0a9900001b000001\",\n" +
                "                            \"head_co_executor_name\": \"test2\",\n" +
                "                            \"provided\": true\n" +
                "                        }\n" +
                "                    ]\n" +
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
                .put(API_DOCUMENTS + String.format("/%s",idCreatedDocument))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String shortTitleCreated = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[3].organization_short_title");
        String organizationIdCreated = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[3].organization_id");
        String organizationTypeCreated = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[3].organization_type");
        String coExecutorIdCreated = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[3].co_executor_id");
        String coExecutorNameCreated = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[3].co_executor_name");
        String coHeadExecutorIdCreated = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[3].head_co_executor_id");
        String coHeadExecutorNameCreated = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[3].head_co_executor_name");
        String providedCreated = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[3].provided");

        System.out.println("Short title co executor - " + shortTitleCreated);
        System.out.println("Organization id co executor - " + organizationIdCreated);
        System.out.println("Organization type co executor - " + organizationTypeCreated);
        System.out.println("Co executor id - " + coExecutorIdCreated);
        System.out.println("Co head executor name - " + coExecutorNameCreated);
        System.out.println("Co head executor id - " + coHeadExecutorIdCreated);
        System.out.println("Co head executor name - " + coHeadExecutorNameCreated);
        System.out.println("Provided co executor - " + providedCreated);

        Assert.assertTrue(shortTitleCreated.equals("t"));
        Assert.assertTrue(organizationIdCreated.equals("56f3df4ecb26000004000001"));
        Assert.assertTrue(organizationTypeCreated.equals("Oshs::Mvd::Organization"));
        Assert.assertTrue(coExecutorIdCreated.equals("652e404ca779000006000001"));
        Assert.assertTrue(coExecutorNameCreated.equals("test3"));
        Assert.assertTrue(coHeadExecutorIdCreated.equals("5a72362e0a9900001b000001"));
        Assert.assertTrue(coHeadExecutorNameCreated.equals("test2"));
        Assert.assertTrue(providedCreated.equals("true"));
    }


    @Test
    public void step22(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"executors\": [\n" +
                "                {\n" +
                "                    \"id\": " + idCreatedExecutor + ",\n" +
                "                    \"co_executors\": [\n" +
                "                        {\n" +
                "                            \"organization_id\": \"658d3dd057b6000002000001\",\n" +
                "                            \"organization_short_title\": \"t\",\n" +
                "                            \"organization_type\": \"Oshs::Mvd::Organization\",\n" +
                "                            \"co_executor_id\": \"652e404ca779000006000001\",\n" +
                "                            \"co_executor_name\": \"test3\",\n" +
                "                            \"head_co_executor_id\": \"5a72362e0a9900001b000001\",\n" +
                "                            \"head_co_executor_name\": \"test2\",\n" +
                "                            \"provided\": true\n" +
                "                        }\n" +
                "                    ]\n" +
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
                .put(API_DOCUMENTS + String.format("/%s",idCreatedDocument))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.
                getString("errors.assignments.0.executors.0.co_executors.0.organization_id[0]");

        System.out.println("Error message - "  + errorMessage);

        Assert.assertTrue(errorMessage.equals("уже существует"));
    }


    @AfterTest
    public void tearDown(){
        // Delete created control subject
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
        // Delete created document execution state
        Response responseDESDeleteFirst = (Response) given()
                .header("Test-Authorization", 1)
                .delete(API_DOCUMENT_EXECUTION_STATES + String.format("/%s",idDocumentExecutionStateOne))
                .then().log().all()
                .extract();

        //JsonPath jsonPathDESDeleteFirst = responseDESDeleteFirst.jsonPath();
        // Delete crated document type
        Response responseDocumentType = (Response) given()
                .header("Test-Authorization", 1)
                .delete(API_DOC_TYPE + String.format("/%s",idDocumentType))
                .then().log().all()
                .extract();

        // JsonPath jsonPathDocumentType = responseDocumentType.jsonPath();
    }
}

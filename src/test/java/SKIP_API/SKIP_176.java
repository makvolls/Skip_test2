package SKIP_API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.given;


public class SKIP_176 {

    private static String API_DOCUMENTS = "http://api.skip.rtech.ru/v1/documents";
    // Variables for setup
    private static String API_USER = "http://api.skip.rtech.ru/v1/permissions/users";
    private static String API_CONTROL_SUBJECTS = "http://api.skip.rtech.ru/v1/permissions/control_subjects";
    private static String API_DOC_TYPE = "http://api.skip.rtech.ru/v1/classifiers/document_types";
    private static String API_DOCUMENT_EXECUTION_STATES = "http://api.skip.rtech.ru" +
            "/v1/classifiers/document_execution_states";
    private static String API_PERIODICITY = "http://api.skip.rtech.ru/v1/classifiers/periodicity";
    private static String API_DEADLINE_BASES = "http://api.skip.rtech.ru/v1/classifiers/deadline_bases";
    private static String API_OSHS_MVD_OFFICIALS="http://api.skip.rtech.ru/v1/oshs/mvd/officials";
    private static String API_ORGANIZATIONS="http://api.skip.rtech.ru/v1/oshs/mvd/organizations";
    private static String API_OSHS = "http://api.skip.rtech.ru/v1/oshs";

    int userIdOne = 1; // User that have all rights, role id = 29
    int userIdSix = 6; // User that have right = "index_roles", role id = 5, it doesn't use in this testcase
    int idCreatedDocument; // Document's id that will be created in step 15
    int idCreatedAssignment; // Assignment's id that will be created in step 15
    int idCreatedHeadExecutor; // Head executor's id that will be created in step 15
    int idCreatedCoExHeadExecutor; // Co executor's id that will be created in step 15
    int idCreatedLetterFirst; // Letter's id created in step 15 first element
    int idCreatedLetterSecond; // Letter's id created in step 15 second element
    int idCreatedLetterThird; // Letter's id created in step 15 third element
    int idCreatedSedDocumentSecond; // Sed document's id created in step 11 , should equal 2
    int idCreatedSedDocumentFirst; // Sed document's id created in step 12, should equal 1
    int idControlSubject; // Created control subject's id
    int idDocumentExecutionStateOne; // Created document execution state's id first element
    int idDocumentExecutionStateSecond; // Created document execution state's id second element
    int idDocumentType; // Created document type's id
    int idDeadlineBases; // Created deadline base's id first element
    String idRandomOfficial; // Random official's id
    String idRandomEsdOrganization; // Random ESD organization's id
    String idRandomMvdOrganization; // Random Mvd organization's id
    int idDocumentFirst; // Document id first element from list of documents
    int idDocumentSecond; // Document id second element from list of documents
    int idDocumentThird; // Document id  third element from list of documents
    int idDocumentFourth; // Document id fourth element from list of documents
    // Set parameters "name", "short_name" for created elements
    String nameTemplate = "SKIP_176_Autotest";
    String shortNameTemplate = "SKIP_176";
    // Values of documents from SED
    String uidFirstDocSED = "0289a43bc2359182f95e399d29dc1674510ff22a7f8e5d9c13b01a74dd56fe1430";
    String regNumberFirstDocSED = "65c5d0d9d8fe8d1036000003";
    String regDateFirstDocSEDAnotherValue = "2024-02-12T06:28:40.541Z";
    String regDateFirstDocSED = "09.02.2024"; // Can use instead of "regDateFirstDocSEDAnotherValue" variable
    String uidSecondDocSED = "0271d3b0343b8307f22b6bf06ede9ea78771dbef2f290e69e6699cf7d34e28bea8";
    String regNumberSecondDocSED = "65801d629443000003000001";
    String regDateSecondDocSED = "02.01.2024";

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
        // Create document execution state 2 values
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
        System.out.println("Id document ex states first - " + idDocumentExecutionStateOne);
        Map<String, String> requestBodyDocumentExStSecond = new HashMap<>();
        requestBodyDocumentExStSecond.put("name", nameTemplate + "e");
        requestBodyDocumentExStSecond.put("short_name", shortNameTemplate + "e");
        requestBodyDocumentExStSecond.put("excluded", "false");
        Response responseDocumentExecutionStateCreateSecond = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBodyDocumentExStSecond)
                .post(API_DOCUMENT_EXECUTION_STATES)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathDocumentExecutionStateCreateSecond = responseDocumentExecutionStateCreateSecond.jsonPath();
        idDocumentExecutionStateSecond = jsonPathDocumentExecutionStateCreateSecond.getInt("data.id");
        System.out.println("Id document ex states second - " + idDocumentExecutionStateSecond);
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
        // Create/Check periodicity
        Response responsePeriodicity = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .get(API_PERIODICITY)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathPeriodicity = responsePeriodicity.jsonPath();
        List<String> namesPeriodicity = jsonPathPeriodicity.getList("data.name");
        for (String namePeriodicity: namesPeriodicity) {
            if (namePeriodicity.equals("Разовая")){
                System.out.println("Periodicity with name - 'Разовая' is existed. It has id - 44.");
            }
            if (namePeriodicity.equals("Раз в год")){
                System.out.println("Periodicity with name - 'Раз в год' is existed. It has id - 43.");
            }
            if (namePeriodicity.equals("Раз в полгода")){
                System.out.println("Periodicity with name - 'Раз в полгода' is existed. It has id - 42.");
            }
            if (namePeriodicity.equals("Раз в квартал")){
                System.out.println("Periodicity with name - 'Раз в квартал' is existed. It has id - 41.");
            }
            if (namePeriodicity.equals("Каждый месяц")){
                System.out.println("Periodicity with name - 'Каждый месяц' is existed. It has id - 40.");
            }
            if (namePeriodicity.equals("Каждую декаду")){
                System.out.println("Periodicity with name - 'Каждую декаду' is existed. It has id - 39.");
            }
            if (namePeriodicity.equals("Раз в две недели")){ // used this in testcase
                System.out.println("Periodicity with name - 'Раз в две недели' is existed. It has id - 38.");
            }
            if (namePeriodicity.equals("Каждую неделю")){
                System.out.println("Periodicity with name - 'Каждую неделю' is existed. It has id - 37.");
            }
            if (namePeriodicity.equals("Раз в два дня")){
                System.out.println("Periodicity with name - 'Раз в два дня' is existed. It has id - 36.");
            }
            if (namePeriodicity.equals("Каждый день")){
                System.out.println("Periodicity with name - 'Каждый день' is existed. It has id - 35.");
            }
        }
        // Create deadline bases
        Map<String, Object> requestBodyDeadlineBasesFirst = new HashMap<>();
        requestBodyDeadlineBasesFirst.put("name",nameTemplate);
        requestBodyDeadlineBasesFirst.put("short_name",shortNameTemplate);
        requestBodyDeadlineBasesFirst.put("note","1");
        requestBodyDeadlineBasesFirst.put("duration_id","3_days");
        requestBodyDeadlineBasesFirst.put("deadline_only",false);
        requestBodyDeadlineBasesFirst.put("excluded",false);
        Response responseDeadlineBasesCreateFirst = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBodyDeadlineBasesFirst)
                .post(API_DEADLINE_BASES)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathDeadlineBasesCreateFirst = responseDeadlineBasesCreateFirst.jsonPath();
        idDeadlineBases = jsonPathDeadlineBasesCreateFirst.getInt("data.id");
        System.out.println("Deadline bases first element id - " + idDeadlineBases);
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
        // Get head_executor_id from organization_oshs_mvd
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
        // Get random mvd organization
        Response responseOrganizationMvd = given()
                .when()
                .params("items","100")
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .get(API_ORGANIZATIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathOrganizationMvd = responseOrganizationMvd.jsonPath();
        List<String> idOrganizationMvd = jsonPathOrganizationMvd.getList("data.id");
        Random randomOrgMvd = new Random();
        String randomIdOrgMvd = idOrganizationMvd.get(randomOrgMvd.nextInt(idOrganizationMvd.size()));
        System.out.println("Random mvd id - " + randomIdOrgMvd);
        idRandomMvdOrganization = randomIdOrgMvd;

        // Get random values of Documents from SKIP
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
        idDocumentThird = jsonPathDocumentsList.getInt("data[2].id");
        idDocumentFourth = jsonPathDocumentsList.getInt("data[3].id");
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
                "            \"head_executor\": {\n" +
                "                \"head_executor_id\": \"" + idRandomEsdOrganization + "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBases + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"period_in_days\": 5,\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"organization_short_title\": \"test\",\n" +
                "                        \"co_executor_name\": \"fewsgwrfdasd\",\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"document_name\": \"string\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
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

        String document_name = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[0].document_name");

        System.out.println("Document name - " + document_name);

        Assert.assertTrue(document_name.equals("string"));
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
                "            \"head_executor\": {\n" +
                "                \"head_executor_id\": \"" + idRandomEsdOrganization + "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBases + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"period_in_days\": 5,\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"organization_short_title\": \"test\",\n" +
                "                        \"co_executor_name\": \"fewsgwrfdasd\",\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"document_type\": \"Documents::Document\",\n" +
                "                                \"document_name\": \"string\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
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
                getString("errors.assignments.0.head_executor.co_executors.0.letters.0.document_id[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
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
                "            \"head_executor\": {\n" +
                "                \"head_executor_id\": \"" + idRandomEsdOrganization + "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBases + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"period_in_days\": 5,\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"organization_short_title\": \"test\",\n" +
                "                        \"co_executor_name\": \"fewsgwrfdasd\",\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"document_id\": " + idDocumentFirst + ",\n" +
                "                                \"document_type\": \"Documents::Document\",\n" +
                "                                \"document_name\": \"string\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
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

        String document_name = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[0].document_name");
        String document_type = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[0].document_type");
        int document_id = jsonPath.
                getInt("data.assignments[0].head_executor.co_executors[0].letters[0].document_id");

        System.out.println("Document name - " + document_name);
        System.out.println("Document type - " + document_type);
        System.out.println("Document id - " + document_id);

        Assert.assertTrue(document_name.equals("string"));
        Assert.assertTrue(document_type.equals("Documents::Document"));
        Assert.assertTrue(document_id == idDocumentFirst);
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
                "            \"head_executor\": {\n" +
                "                \"head_executor_id\": \"" + idRandomEsdOrganization + "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBases + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"period_in_days\": 5,\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"organization_short_title\": \"test\",\n" +
                "                        \"co_executor_name\": \"fewsgwrfdasd\",\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"document_id\": " + null + ",\n" +
                "                                \"document_type\": \"Documents::Document\",\n" +
                "                                \"document_name\": \"string\",\n" +
                "                                \"note\": \"string\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
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
                getString("errors.assignments.0.head_executor.co_executors.0.letters.0.document_id[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
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
                "            \"head_executor\": {\n" +
                "                \"head_executor_id\": \"" + idRandomEsdOrganization + "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBases + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"period_in_days\": 5,\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"organization_short_title\": \"test\",\n" +
                "                        \"co_executor_name\": \"fewsgwrfdasd\",\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"document_id\": " + idDocumentFirst + ",\n" +
                "                                \"document_type\": \"Documents::Sed\",\n" +
                "                                \"document_name\": \"string\",\n" +
                "                                \"note\": \"string\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
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
                getString("errors.assignments.0.head_executor.co_executors.0.letters.0.document_type[0]");

        System.out.println("Error message - " + errorMessage);

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
                "            \"head_executor\": {\n" +
                "                \"head_executor_id\": \"" + idRandomEsdOrganization + "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBases + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"period_in_days\": 5,\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"organization_short_title\": \"test\",\n" +
                "                        \"co_executor_name\": \"fewsgwrfdasd\",\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"document_id\": " + 113214323 + ",\n" +
                "                                \"document_type\": \"Documents::Document\",\n" +
                "                                \"document_name\": \"string\",\n" +
                "                                \"note\": \"string\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
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
                getString("errors.assignments.0.head_executor.co_executors.0.letters.0.document_id[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
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
                "            \"head_executor\": {\n" +
                "                \"head_executor_id\": \"" + idRandomEsdOrganization + "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBases + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"period_in_days\": 5,\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"organization_short_title\": \"test\",\n" +
                "                        \"co_executor_name\": \"fewsgwrfdasd\",\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"document_id\": " + 113214323 + ",\n" +
                "                                \"document_type\": \"Sed::Document\",\n" +
                "                                \"document_name\": \"string\",\n" +
                "                                \"note\": \"string\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
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
                getString("errors.assignments.0.head_executor.co_executors.0.letters.0.document_id[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
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
                "            \"head_executor\": {\n" +
                "                \"head_executor_id\": \"" + idRandomEsdOrganization + "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBases + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"period_in_days\": 5,\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"organization_short_title\": \"test\",\n" +
                "                        \"co_executor_name\": \"fewsgwrfdasd\",\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"document_id\": " + idDocumentFirst + ",\n" +
                "                                \"document_type\": \"Documents::Document\",\n" +
                "                                \"document_name\": \"string\",\n" +
                "                                \"note\": \"string\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
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

        String document_name = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[0].document_name");
        String document_type = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[0].document_type");
        int document_id = jsonPath.
                getInt("data.assignments[0].head_executor.co_executors[0].letters[0].document_id");
        String note = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[0].note");

        System.out.println("Document name - " + document_name);
        System.out.println("Document type - " + document_type);
        System.out.println("Document id - " + document_id);
        System.out.println("Note - " + note);

        Assert.assertTrue(document_name.equals("string"));
        Assert.assertTrue(document_type.equals("Documents::Document"));
        Assert.assertTrue(document_id == idDocumentFirst);
        Assert.assertTrue(note.equals("string"));
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
                "            \"head_executor\": {\n" +
                "                \"head_executor_id\": \"" + idRandomEsdOrganization + "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBases + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"period_in_days\": 5,\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"organization_short_title\": \"test\",\n" +
                "                        \"co_executor_name\": \"fewsgwrfdasd\",\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"document_id\": " + idDocumentFirst + ",\n" +
                "                                \"document_type\": \"Documents::Document\",\n" +
                "                                \"document_name\": \"string\",\n" +
                "                                \"note\": \"string\"\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"document_id\": " + idDocumentFirst + ",\n" +
                "                                \"document_type\": \"Documents::Document\",\n" +
                "                                \"document_name\": \"string\",\n" +
                "                                \"note\": \"string\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
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
        getString("errors.assignments.0.head_executor.co_executors.0.letters.1.document_id[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("уже существует"));
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
                "            \"head_executor\": {\n" +
                "                \"head_executor_id\": \"" + idRandomEsdOrganization + "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBases + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"period_in_days\": 5,\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"organization_short_title\": \"test\",\n" +
                "                        \"co_executor_name\": \"fewsgwrfdasd\",\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"document_id\": " + idDocumentFirst + ",\n" +
                "                                \"document_type\": \"Documents::Document\",\n" +
                "                                \"document_name\": \"string\",\n" +
                "                                \"note\": \"string\"\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"document_id\": " + idDocumentSecond + ",\n" +
                "                                \"document_type\": \"Documents::Document\",\n" +
                "                                \"document_name\": \"string\",\n" +
                "                                \"note\": \"string\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
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

        String document_name_first = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[0].document_name");
        String document_type_first = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[0].document_type");
        int document_id_first = jsonPath.
                getInt("data.assignments[0].head_executor.co_executors[0].letters[0].document_id");
        String note_first = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[0].note");
        String document_name_second = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[1].document_name");
        String document_type_second = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[1].document_type");
        int document_id_second = jsonPath.
                getInt("data.assignments[0].head_executor.co_executors[0].letters[1].document_id");
        String note_second = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[1].note");

        System.out.println("Document name first element - " + document_name_first);
        System.out.println("Document type first element - " + document_type_first);
        System.out.println("Document id first element - " + document_id_first);
        System.out.println("Note first element - " + note_first);
        System.out.println("Document name second element - " + document_name_second);
        System.out.println("Document type second element - " + document_type_second);
        System.out.println("Document id second element - " + document_id_second);
        System.out.println("Note second element - " + note_second);

        Assert.assertTrue(document_name_first.equals("string"));
        Assert.assertTrue(document_type_first.equals("Documents::Document"));
        Assert.assertTrue(document_id_first == idDocumentFirst);
        Assert.assertTrue(note_first.equals("string"));
        Assert.assertTrue(document_name_second.equals("string"));
        Assert.assertTrue(document_type_second.equals("Documents::Document"));
        Assert.assertTrue(document_id_second == idDocumentSecond);
        Assert.assertTrue(note_second.equals("string"));
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
                "            \"head_executor\": {\n" +
                "                \"head_executor_id\": \"" + idRandomEsdOrganization + "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBases + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"period_in_days\": 5,\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"organization_short_title\": \"test\",\n" +
                "                        \"co_executor_name\": \"fewsgwrfdasd\",\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"document_type\": \"Sed::Document\",\n" +
                "                                \"document_name\": \"testSedDoc\",\n" +
                "                                \"note\": \"s\",\n" +
                "                                \"sed_document\": {\n" +
                "                                    \"uid\": \"" + uidFirstDocSED + "\",\n" +
                "                                    \"reg_number\": \"" + regNumberFirstDocSED + "\",\n" +
                "                                    \"reg_date\": \"" + regDateFirstDocSEDAnotherValue + "\"\n" +
                "                                }\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
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

        String document_type = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[0].document_type");
        String document_name = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[0].document_name");
        String note = jsonPath.getString("data.assignments[0].head_executor.co_executors[0].letters[0].note");
        idCreatedSedDocumentSecond = jsonPath.
                getInt("data.assignments[0].head_executor.co_executors[0].letters[0].document_id");

        System.out.println("Document type - " + document_type);
        System.out.println("Document name - " + document_name);
        System.out.println("Note - " + note);
        System.out.println("Created sed document's id - " + idCreatedSedDocumentSecond);

        Assert.assertTrue(document_type.equals("Sed::Document"));
        Assert.assertTrue(document_name.equals("testSedDoc"));
        Assert.assertTrue(note.equals("s"));
    }

    @Test
    public void step12(){
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
                "            \"head_executor\": {\n" +
                "                \"head_executor_id\": \"" + idRandomEsdOrganization + "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBases + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"period_in_days\": 5,\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"organization_short_title\": \"test\",\n" +
                "                        \"co_executor_name\": \"fewsgwrfdasd\",\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"document_type\": \"Sed::Document\",\n" +
                "                                \"document_name\": \"testSedDoc\",\n" +
                "                                \"note\": \"s\",\n" +
                "                                \"sed_document\": {\n" +
                "                                    \"uid\": \"" + uidSecondDocSED + "\",\n" +
                "                                    \"reg_number\": \"" + regNumberSecondDocSED + "\",\n" +
                "                                    \"reg_date\": \"" + regDateSecondDocSED + "\"\n" +
                "                                }\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
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

        String document_type = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[0].document_type");
        String document_name = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[0].document_name");
        String note = jsonPath.getString("data.assignments[0].head_executor.co_executors[0].letters[0].note");
        idCreatedSedDocumentFirst = jsonPath.
                getInt("data.assignments[0].head_executor.co_executors[0].letters[0].document_id");

        System.out.println("Document type - " + document_type);
        System.out.println("Document name - " + document_name);
        System.out.println("Note - " + note);
        System.out.println("Created sed document's id - " + idCreatedSedDocumentFirst);

        Assert.assertTrue(document_type.equals("Sed::Document"));
        Assert.assertTrue(document_name.equals("testSedDoc"));
        Assert.assertTrue(note.equals("s"));
    }

    @Test
    public void step13(){
        // Header for tests
        idCreatedSedDocumentSecond = 2;
        idCreatedSedDocumentFirst = 1;
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
                "            \"head_executor\": {\n" +
                "                \"head_executor_id\": \"" + idRandomEsdOrganization + "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBases + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"period_in_days\": 5,\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"organization_short_title\": \"test\",\n" +
                "                        \"co_executor_name\": \"fewsgwrfdasd\",\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"document_id\": " + idCreatedSedDocumentSecond + ",\n" +
                "                                \"document_type\": \"Sed::Document\",\n" +
                "                                \"document_name\": \"testS\",\n" +
                "                                \"note\": \"s\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
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

        String document_name = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[0].document_name");

        System.out.println("Document name - " + document_name);

        Assert.assertTrue(document_name.equals("testS"));
    }

    @Test
    public void step14(){
        // Header for tests
        idCreatedSedDocumentSecond = 2;
        idCreatedSedDocumentFirst = 1;
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
                "            \"head_executor\": {\n" +
                "                \"head_executor_id\": \"" + idRandomEsdOrganization + "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBases + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"period_in_days\": 5,\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"organization_short_title\": \"test\",\n" +
                "                        \"co_executor_name\": \"fewsgwrfdasd\",\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"document_id\": " + idCreatedSedDocumentSecond + ",\n" +
                "                                \"document_type\": \"Sed::Document\",\n" +
                "                                \"document_name\": \"t\",\n" +
                "                                \"note\": \"s\"\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"document_id\": " + idCreatedSedDocumentSecond + ",\n" +
                "                                \"document_type\": \"Sed::Document\",\n" +
                "                                \"document_name\": \"t\",\n" +
                "                                \"note\": \"s\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
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
                getString("errors.assignments.0.head_executor.co_executors.0.letters.1.document_id[0]");

        System.out.println("Error message  - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("уже существует"));
    }

    @Test
    public void step15(){
        // Header for tests
        idCreatedSedDocumentSecond = 2;
        idCreatedSedDocumentFirst = 1;
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
                "            \"head_executor\": {\n" +
                "                \"head_executor_id\": \"" + idRandomEsdOrganization + "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBases + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"period_in_days\": 5,\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"organization_short_title\": \"test\",\n" +
                "                        \"co_executor_name\": \"fewsgwrfdasd\",\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"document_id\": " + idCreatedSedDocumentSecond + ",\n" +
                "                                \"document_type\": \"Sed::Document\",\n" +
                "                                \"document_name\": \"t\",\n" +
                "                                \"note\": \"s\"\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"document_id\": " + idDocumentFirst + ",\n" +
                "                                \"document_type\": \"Documents::Document\",\n" +
                "                                \"document_name\": \"string\",\n" +
                "                                \"note\": \"string\"\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"document_id\": " + idDocumentSecond + ",\n" +
                "                                \"document_type\": \"Documents::Document\",\n" +
                "                                \"document_name\": \"string\",\n" +
                "                                \"note\": \"string\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
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
        idCreatedHeadExecutor = jsonPath.getInt("data.assignments[0].head_executor.id");
        idCreatedCoExHeadExecutor = jsonPath.getInt("data.assignments[0].head_executor.co_executors[0].id");
        idCreatedLetterFirst = jsonPath.getInt("data.assignments[0].head_executor.co_executors[0].letters[0].id");
        idCreatedLetterSecond = jsonPath.getInt("data.assignments[0].head_executor.co_executors[0].letters[1].id");
        idCreatedLetterThird = jsonPath.getInt("data.assignments[0].head_executor.co_executors[0].letters[2].id");

        String document_name_first = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[0].document_name");
        String document_type_first = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[0].document_type");
        int document_id_first = jsonPath.
                getInt("data.assignments[0].head_executor.co_executors[0].letters[0].document_id");
        String note_first = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[0].note");
        String document_name_second = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[1].document_name");
        String document_type_second = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[1].document_type");
        int document_id_second = jsonPath.
                getInt("data.assignments[0].head_executor.co_executors[0].letters[1].document_id");
        String note_second = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[1].note");
        String document_name_third = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[2].document_name");
        String document_type_third = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[2].document_type");
        int document_id_third = jsonPath.
                getInt("data.assignments[0].head_executor.co_executors[0].letters[2].document_id");
        String note_third = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[2].note");

        System.out.println("Created document id - " + idCreatedDocument);
        System.out.println("Created assignment id - " + idCreatedAssignment);
        System.out.println("Created head executor id - " + idCreatedHeadExecutor);
        System.out.println("Created co executor id - " + idCreatedCoExHeadExecutor);
        System.out.println("Created first letter id - " + idCreatedLetterFirst);
        System.out.println("Created second letter id - " + idCreatedLetterSecond);
        System.out.println("Created third letter id - " + idCreatedLetterThird);

        System.out.println("Document name first element - " + document_name_first);
        System.out.println("Document type first element - " + document_type_first);
        System.out.println("Document id first element - " + document_id_first);
        System.out.println("Note first element - " + note_first);
        System.out.println("Document name second element - " + document_name_second);
        System.out.println("Document type second element - " + document_type_second);
        System.out.println("Document id second element - " + document_id_second);
        System.out.println("Note second element - " + note_second);
        System.out.println("Document name third element - " + document_name_third);
        System.out.println("Document type third element - " + document_type_third);
        System.out.println("Document id third element - " + document_id_third);
        System.out.println("Note third element - " + note_third);

        Assert.assertTrue(document_name_first.equals("t"));
        Assert.assertTrue(document_type_first.equals("Sed::Document"));
        Assert.assertTrue(document_id_first == idCreatedSedDocumentSecond);
        Assert.assertTrue(note_first.equals("s"));
        Assert.assertTrue(document_name_second.equals("string"));
        Assert.assertTrue(document_type_second.equals("Documents::Document"));
        Assert.assertTrue(document_id_second == idDocumentFirst);
        Assert.assertTrue(note_second.equals("string"));
        Assert.assertTrue(document_name_third.equals("string"));
        Assert.assertTrue(document_type_third.equals("Documents::Document"));
        Assert.assertTrue(document_id_third == idDocumentSecond);
        Assert.assertTrue(note_third.equals("string"));
    }

    @Test
    public void step16(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .get(API_DOCUMENTS + String.format("/%s",idCreatedDocument))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        List<Integer> idLetters = jsonPath.
                getList("data.assignments[0].head_executor.co_executors[0].letters.id");
        System.out.println("Ids letters - " + idLetters);
        List<Integer> idLettersSorted = new ArrayList<>(idLetters);
        Collections.sort(idLettersSorted);
        System.out.println("Sorted ids letter - " + idLettersSorted);
        Assert.assertTrue(idLetters.equals(idLettersSorted));
    }

    @Test
    public void step17(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"head_executor\": {\n" +
                "                \"id\": " + idCreatedHeadExecutor + ",\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"id\": " + idCreatedCoExHeadExecutor + ",\n" +
                "                        \"letters\": [\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
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

        List<Integer> idLetters = jsonPath.
                getList("data.assignments[0].head_executor.co_executors[0].letters.id");
        System.out.println("Ids letters - " + idLetters);
        List<Integer> idLettersSorted = new ArrayList<>(idLetters);
        Collections.sort(idLettersSorted);
        System.out.println("Sorted ids letter - " + idLettersSorted);
        Assert.assertTrue(idLetters.equals(idLettersSorted));
    }

    @Test
    public void step18(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"head_executor\": {\n" +
                "                \"id\": " + idCreatedHeadExecutor + ",\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"id\": " + idCreatedCoExHeadExecutor + ",\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"document_type\": \"Documents::Document\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
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

        String errorDocumentName = jsonPath.
                getString("errors.assignments.0.head_executor.co_executors.0.letters.0.document_name[0]");
        String errorDocumentId = jsonPath.
                getString("errors.assignments.0.head_executor.co_executors.0.letters.0.document_id[0]");

        System.out.println("Document name error message - " + errorDocumentName);
        System.out.println("Document id error message - " + errorDocumentId);

        Assert.assertTrue(errorDocumentName.equals("не может быть пустым"));
        Assert.assertTrue(errorDocumentId.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step19(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"head_executor\": {\n" +
                "                \"id\": " + idCreatedHeadExecutor + ",\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"id\": " + idCreatedCoExHeadExecutor + ",\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"document_id\": " + idDocumentThird + ",\n" +
                "                                \"document_type\": \"Documents::Document\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
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
                getString("errors.assignments.0.head_executor.co_executors.0.letters.0.document_name[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("не может быть пустым"));
    }

    @Test
    public void step20(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"head_executor\": {\n" +
                "                \"id\": " + idCreatedHeadExecutor + ",\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"id\": " + idCreatedCoExHeadExecutor + ",\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"id\": " + idCreatedLetterThird + ",\n" +
                "                                \"document_id\": " + idDocumentThird + ",\n" +
                "                                \"document_type\": \"Documents::Document\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
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

        int document_id_third_letter = jsonPath.
                getInt("data.assignments[0].head_executor.co_executors[0].letters[2].document_id");

        System.out.println("Changed document id - " + document_id_third_letter);

        Assert.assertTrue(document_id_third_letter == idDocumentThird);
    }

    @Test
    public void step21(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"head_executor\": {\n" +
                "                \"id\": " + idCreatedHeadExecutor + ",\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"id\": " + idCreatedCoExHeadExecutor + ",\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"id\": " + null + ",\n" +
                "                                \"document_id\": " + idDocumentThird + ",\n" +
                "                                \"document_type\": \"Documents::Document\",\n" +
                "                                \"document_name\": \"test3\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
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
                getString("errors.assignments.0.head_executor.co_executors.0.letters.0.document_id[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("уже существует"));
    }

    @Test
    public void step22(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"head_executor\": {\n" +
                "                \"id\": " + idCreatedHeadExecutor + ",\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"id\": " + idCreatedCoExHeadExecutor + ",\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"id\": " + idCreatedLetterThird + ",\n" +
                "                                \"document_id\": " + idDocumentThird + ",\n" +
                "                                \"document_type\": \"fsadegvsfd\",\n" +
                "                                \"document_name\": \"test3\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
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
                getString("errors.assignments.0.head_executor.co_executors.0.letters.0.document_type[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step23(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"head_executor\": {\n" +
                "                \"id\": " + idCreatedHeadExecutor + ",\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"id\": " + idCreatedCoExHeadExecutor + ",\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"id\": " + idCreatedLetterThird + ",\n" +
                "                                \"document_id\": " + 82635478 + ",\n" +
                "                                \"document_type\": \"Documents::Document\",\n" +
                "                                \"document_name\": \"vdsvbfdbvfd0\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
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
                getString("errors.assignments.0.head_executor.co_executors.0.letters.0.document_id[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step24(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"head_executor\": {\n" +
                "                \"id\": " + idCreatedHeadExecutor + ",\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"id\": " + idCreatedCoExHeadExecutor + ",\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"id\": " + idCreatedLetterThird + ",\n" +
                "                                \"document_id\": " + 82635478 + ",\n" +
                "                                \"document_type\": \"Sed::Document\",\n" +
                "                                \"document_name\": \"vdsvbfdbvfd0\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
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
                getString("errors.assignments.0.head_executor.co_executors.0.letters.0.document_id[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step25(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"head_executor\": {\n" +
                "                \"id\": " + idCreatedHeadExecutor + ",\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"id\": " + idCreatedCoExHeadExecutor + ",\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"id\": " + idCreatedLetterThird + ",\n" +
                "                                \"document_id\": " + idCreatedSedDocumentSecond + ",\n" +
                "                                \"document_type\": \"Sed::Document\",\n" +
                "                                \"document_name\": \"vdsvbfdbvfd0\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
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
                getString("errors.assignments.0.head_executor.co_executors.0.letters.0.document_id[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("уже существует"));
    }

    @Test
    public void step26(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"head_executor\": {\n" +
                "                \"id\": " + idCreatedHeadExecutor + ",\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"id\": " + idCreatedCoExHeadExecutor + ",\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"id\": " + idCreatedLetterThird + ",\n" +
                "                                \"document_id\": " + idCreatedSedDocumentFirst + ",\n" +
                "                                \"document_type\": \"Sed::Document\",\n" +
                "                                \"document_name\": \"vdsvbfdbvfd0\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
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

        int document_id = jsonPath.
                getInt("data.assignments[0].head_executor.co_executors[0].letters[2].document_id");
        String document_type = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[2].document_type");
        String document_name = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[2].document_name");

        System.out.println("Document id - " + document_id);
        System.out.println("Document type - " + document_type);
        System.out.println("Document name - " + document_name);

        Assert.assertTrue(document_id == idCreatedSedDocumentFirst);
        Assert.assertTrue(document_type.equals("Sed::Document"));
        Assert.assertTrue(document_name.equals("vdsvbfdbvfd0"));
    }

    @Test
    public void step27(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"head_executor\": {\n" +
                "                \"id\": " + idCreatedHeadExecutor + ",\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"id\": " + idCreatedCoExHeadExecutor + ",\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"document_id\": " + idDocumentFourth + ",\n" +
                "                                \"document_type\": \"Documents::Document\",\n" +
                "                                \"document_name\": \"vdsvbfdbvfd0\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
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

        String document_name_fourth = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[3].document_name");
        String document_type_fourth = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[3].document_type");
        int document_id_fourth = jsonPath.
                getInt("data.assignments[0].head_executor.co_executors[0].letters[3].document_id");

        System.out.println("Created letter document name - " + document_name_fourth);
        System.out.println("Created letter document type - " + document_type_fourth);
        System.out.println("Created letter document id - " + document_id_fourth);

        Assert.assertTrue(document_name_fourth.equals("vdsvbfdbvfd0"));
        Assert.assertTrue(document_type_fourth.equals("Documents::Document"));
        Assert.assertTrue(document_id_fourth == idDocumentFourth);
    }

    @Test
    public void step28(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"head_executor\": {\n" +
                "                \"id\": " + idCreatedHeadExecutor + ",\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"id\": " + idCreatedCoExHeadExecutor + ",\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"id\": " + idCreatedLetterThird + ",\n" +
                "                                \"document_type\": \"Documents::Document\",\n" +
                "                                \"document_name\": \"vdsvbfdbvfd0\",\n" +
                "                                \"deleted\": true\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
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

        List<Integer> idLetters = jsonPath.
                getList("data.assignments[0].head_executor.co_executors[0].letters.id");
        System.out.println("Ids letters - " + idLetters);
        boolean isDeleted = false;
        if (idLetters.contains(idCreatedLetterThird)){
            System.out.println(String.format("Element with id %s is not deleted!",idCreatedLetterThird));
        } else {
            System.out.println(String.format("Element with id %s is deleted!",idCreatedLetterThird));
            isDeleted = true;
        }
        Assert.assertTrue(isDeleted);
    }

    @Test
    public void step29(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"head_executor\": {\n" +
                "                \"id\": " + idCreatedHeadExecutor + ",\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"id\": " + idCreatedCoExHeadExecutor + ",\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"id\": " + idCreatedLetterThird + ",\n" +
                "                                \"document_type\": \"Documents::Document\",\n" +
                "                                \"document_name\": \"vdsvbfdbvfd0\",\n" +
                "                                \"deleted\": true\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
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
                getString("errors.assignments.0.head_executor.co_executors.0.letters.0.base[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("запись не найдена"));
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
        // Delete created document_execution_states 2 values that was created in beforetest class
        // First element
        Response responseDESDeleteFirst = (Response) given()
                .header("Test-Authorization", 1)
                .delete(API_DOCUMENT_EXECUTION_STATES + String.format("/%s",idDocumentExecutionStateOne))
                .then().log().all()
                .extract();

        //JsonPath jsonPathDESDeleteFirst = responseDESDeleteFirst.jsonPath();
        // Second element
        Response responseDESDeleteSecond = (Response) given()
                .header("Test-Authorization", 1)
                .delete(API_DOCUMENT_EXECUTION_STATES + String.format("/%s",idDocumentExecutionStateSecond))
                .then().log().all()
                .extract();

        // JsonPath jsonPathDESDeleteSecond = responseDESDeleteSecond.jsonPath();
        // Delete created document type
        Response responseDocumentType = (Response) given()
                .header("Test-Authorization", 1)
                .delete(API_DOC_TYPE + String.format("/%s",idDocumentType))
                .then().log().all()
                .extract();

        // JsonPath jsonPathDocumentType = responseDocumentType.jsonPath();
        // Delete created deadline bases
        Response responseDeadlineBaseDeleteFirst = (Response) given()
                .header("Test-Authorization", 1)
                .delete(API_DEADLINE_BASES + String.format("/%s",idDeadlineBases))
                .then().log().all()
                .extract();

        // JsonPath jsonPathDeadlineBaseDeleteFirst = responseDeadlineBaseDeleteFirst.jsonPath();
    }
}

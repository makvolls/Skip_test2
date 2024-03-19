package SKIP_API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.given;

public class SKIP_150 {
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
    int userIdSix = 6; // User that have right = "index_roles", role id = 5
    int idCreatedDocument; // Document id that will be created in step 8
    int idCreatedAssignment; // Assignment id that will be created in step 8
    int idCreatedHeadExecutor; // Head executor id that will be created in step 8
    int idCreatedCoExecutor; // Co executor id that will be created in step 8
    int idFirstLetter; // Letter id that will be created in step 8 first element
    int idSecondLetter; // Letter id that will be created in step 8 second element
    int idControlSubject; // Created control subject's id
    int idDocumentExecutionStateOne; // Created document execution state's id first element
    int idDocumentType; // Created document type's id
    int idDocumentExecutionStateSecond; // Created document execution state's id second element
    int idDeadlineBases; // Created deadline bases id
    String idRandomOfficial; // Random official's id
    String idRandomEsdOrganization; // Random ESD organization's id
    int idDocumentFirst; // Document id first element from list of documents
    int idDocumentSecond; // Document id second element from list of documents
    int idDocumentThird; // Document id  third element from list of documents
    // Set parameters "name", "short_name" for created elements
    String nameTemplate = "SKIP_150_Autotest";
    String shortNameTemplate = "SKIP_150";

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
        // First element
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
        // Second element
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
        System.out.println("Id document ex states second element- " + idDocumentExecutionStateSecond);
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
                "            \"note\": \"note\",\n" +
                "            \"head_executor\": {\n" +
                "                \"head_executor_id\": \"" + idRandomEsdOrganization + "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBases + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"period_in_days\": 5,\n" +
                "                \"note\": \"note\",\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"co_executor_id\": \"" + idRandomEsdOrganization + "\",\n" +
                "                        \"co_executor_name\": \"string\",\n" +
                "                        \"provided\": true,\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"document_type\": \"Documents::Document\",\n" +
                "                                \"document_id\": " + idDocumentFirst + ",\n" +
                "                                \"document_name\": \"string\",\n" +
                "                                \"note\": \"string\",\n" +
                "                                \"sed_document\": {\n" +
                "                                    \"uid\": \"string\",\n" +
                "                                    \"reg_number\": \"string\",\n" +
                "                                    \"reg_date\": \"2024-02-11T19:32:06.799Z\"\n" +
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

        String nameLetter = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[0].document_name");
        String typeDocumentLetter = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[0].document_type");
        String noteLetter = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[0].note");
        String documentIdLetter = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[0].document_id");

        System.out.println("Name - " + nameLetter);
        System.out.println("Document type - " + typeDocumentLetter);
        System.out.println("Note - " + noteLetter);
        System.out.println("Document id - " + documentIdLetter);

        Assert.assertTrue(nameLetter.equals("string"));
        Assert.assertTrue(typeDocumentLetter.equals("Documents::Document"));
        Assert.assertTrue(noteLetter.equals("string"));
        Assert.assertTrue(documentIdLetter.equals("3"));
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
                "            \"note\": \"note\",\n" +
                "            \"head_executor\": {\n" +
                "                \"head_executor_id\": \"" + idRandomEsdOrganization + "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBases + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"period_in_days\": 5,\n" +
                "                \"note\": \"note\",\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"co_executor_id\": \"" + idRandomEsdOrganization + "\",\n" +
                "                        \"co_executor_name\": \"string\",\n" +
                "                        \"provided\": true,\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"document_type\": \"Documents::Document\",\n" +
                "                                \"document_id\": " + idDocumentFirst + ",\n" +
                "                                \"document_name\": \"\",\n" +
                "                                \"note\": \"string\",\n" +
                "                                \"sed_document\": {\n" +
                "                                    \"uid\": \"string\",\n" +
                "                                    \"reg_number\": \"string\",\n" +
                "                                    \"reg_date\": \"2024-02-11T19:32:06.799Z\"\n" +
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

        String errorMessage = jsonPath.
                getString("errors.assignments.0.head_executor.co_executors.0.letters.0.document_name[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("не может быть пустым"));
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
                "            \"note\": \"note\",\n" +
                "            \"head_executor\": {\n" +
                "                \"head_executor_id\": \"" + idRandomEsdOrganization + "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBases + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"period_in_days\": 5,\n" +
                "                \"note\": \"note\",\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"co_executor_id\": \"" + idRandomEsdOrganization + "\",\n" +
                "                        \"co_executor_name\": \"string\",\n" +
                "                        \"provided\": true,\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"document_type\": \"Documents::Document\",\n" +
                "                                \"document_id\": " + 10000 + ",\n" +
                "                                \"document_name\": \"test\",\n" +
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
                "            \"note\": \"note\",\n" +
                "            \"head_executor\": {\n" +
                "                \"head_executor_id\": \"" + idRandomEsdOrganization + "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBases + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"period_in_days\": 5,\n" +
                "                \"note\": \"note\",\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"co_executor_id\": \"" + idRandomEsdOrganization + "\",\n" +
                "                        \"co_executor_name\": \"string\",\n" +
                "                        \"provided\": true,\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"document_type\": \"Documents::Document\",\n" +
                "                                \"document_id\": \"string\",\n" +
                "                                \"document_name\": \"test\",\n" +
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

        Assert.assertTrue(errorMessage.equals("имеет неверное значение"));
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
                "            \"note\": \"note\",\n" +
                "            \"head_executor\": {\n" +
                "                \"head_executor_id\": \"" + idRandomEsdOrganization + "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBases + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"period_in_days\": 5,\n" +
                "                \"note\": \"note\",\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"co_executor_id\": \"" + idRandomEsdOrganization + "\",\n" +
                "                        \"co_executor_name\": \"string\",\n" +
                "                        \"provided\": true,\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"document_type\": \"Sed::Document\",\n" +
                "                                \"document_id\": " + idDocumentFirst + ",\n" +
                "                                \"document_name\": \"test\",\n" +
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
                "            \"note\": \"note\",\n" +
                "            \"head_executor\": {\n" +
                "                \"head_executor_id\": \"" + idRandomEsdOrganization + "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBases + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"period_in_days\": 5,\n" +
                "                \"note\": \"note\",\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"co_executor_id\": \"" + idRandomEsdOrganization + "\",\n" +
                "                        \"co_executor_name\": \"string\",\n" +
                "                        \"provided\": true,\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"document_type\": \"Sed::Document\",\n" +
                "                                \"document_id\": \"string\",\n" +
                "                                \"document_name\": \"test\",\n" +
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

        Assert.assertTrue(errorMessage.equals("имеет неверное значение"));
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
                "            \"note\": \"note\",\n" +
                "            \"head_executor\": {\n" +
                "                \"head_executor_id\": \"" + idRandomEsdOrganization + "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBases + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"period_in_days\": 5,\n" +
                "                \"note\": \"note\",\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"co_executor_id\": \"" + idRandomEsdOrganization + "\",\n" +
                "                        \"co_executor_name\": \"string\",\n" +
                "                        \"provided\": true,\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"document_type\": \"Documents::Document\",\n" +
                "                                \"document_id\": " + idDocumentFirst + ",\n" +
                "                                \"document_name\": \"test\",\n" +
                "                                \"note\": \"string\"\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"document_type\": \"Documents::Document\",\n" +
                "                                \"document_id\": " + idDocumentFirst + ",\n" +
                "                                \"document_name\": \"test\",\n" +
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
                "            \"note\": \"note\",\n" +
                "            \"head_executor\": {\n" +
                "                \"head_executor_id\": \"" + idRandomEsdOrganization + "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBases + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"period_in_days\": 5,\n" +
                "                \"note\": \"note\",\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"co_executor_id\": \"" + idRandomEsdOrganization + "\",\n" +
                "                        \"co_executor_name\": \"string\",\n" +
                "                        \"provided\": true,\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"document_type\": \"Documents::Document\",\n" +
                "                                \"document_id\": " + idDocumentFirst + ",\n" +
                "                                \"document_name\": \"1\",\n" +
                "                                \"note\": \"string\"\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"document_type\": \"Documents::Document\",\n" +
                "                                \"document_id\": " + idDocumentSecond + ",\n" +
                "                                \"document_name\": \"2\",\n" +
                "                                \"note\": \"string\"\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"document_type\": \"Documents::Document\",\n" +
                "                                \"document_id\": " + idDocumentThird + ",\n" +
                "                                \"document_name\": \"3\",\n" +
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
        idCreatedCoExecutor = jsonPath.getInt("data.assignments[0].head_executor.co_executors[0].id");
        idFirstLetter = jsonPath.getInt("data.assignments[0].head_executor.co_executors[0].letters[0].id");
        idSecondLetter = jsonPath.getInt("data.assignments[0].head_executor.co_executors[0].letters[1].id");
        String documentNameLetterFirst = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[0].document_name");
        String documentNameLetterSecond = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[1].document_name");
        String documentNameLetterThird = jsonPath.
                getString("data.assignments[0].head_executor.co_executors[0].letters[2].document_name");

        System.out.println("Id created document - " + idCreatedDocument);
        System.out.println("Id created assignment - " + idCreatedAssignment);
        System.out.println("Id created head executor - " + idCreatedHeadExecutor);
        System.out.println("Id created co executor - " + idCreatedCoExecutor);
        System.out.println("Id first letter - " + idFirstLetter);
        System.out.println("Id second letter - " + idSecondLetter);

        Assert.assertTrue(documentNameLetterFirst.equals("1"));
        Assert.assertTrue(documentNameLetterSecond.equals("2"));
        Assert.assertTrue(documentNameLetterThird.equals("3"));
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
                "            \"note\": \"note\",\n" +
                "            \"head_executor\": {\n" +
                "                \"head_executor_id\": \"" + idRandomEsdOrganization + "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBases + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"period_in_days\": 5,\n" +
                "                \"note\": \"note\",\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"co_executor_id\": \"" + idRandomEsdOrganization + "\",\n" +
                "                        \"co_executor_name\": \"string\",\n" +
                "                        \"provided\": true,\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"document_type\": \"Documents::Document\",\n" +
                "                                \"document_id\": " + idDocumentFirst + ",\n" +
                "                                \"document_name\": \"1\",\n" +
                "                                \"note\": \"string\"\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"document_type\": \"Documents::Document\",\n" +
                "                                \"document_id\": " + idDocumentSecond + ",\n" +
                "                                \"document_name\": \"2\",\n" +
                "                                \"note\": \"string\"\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"document_type\": \"Documents::Document\",\n" +
                "                                \"document_id\": " + idDocumentThird + ",\n" +
                "                                \"document_name\": \"3\",\n" +
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
                .header("Test-Authorization", userIdSix)
                .body(requestBody)
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("error");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void step10(){
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
    public void step11(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdSix)
                .get(API_DOCUMENTS + String.format("/%s",idCreatedDocument))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("error");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("Доступ к ресурсу запрещен"));

    }

    @Test
    public void step12(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
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
    public void step13(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"head_executor\": {\n" +
                "                \"id\": " + idCreatedHeadExecutor + ",\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"id\": " + idCreatedCoExecutor + ",\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"id\": " + idFirstLetter + ",\n" +
                "                                \"document_type\": \"Documents::Document\",\n" +
                "                                \"document_id\": 101,\n" +
                "                                \"document_name\": \"1\",\n" +
                "                                \"note\": \"string\",\n" +
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

        Assert.assertFalse(idLetters.contains(idFirstLetter));
    }

    @Test
    public void step14(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"head_executor\": {\n" +
                "                \"id\": " + idCreatedHeadExecutor + ",\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"id\": " + idCreatedCoExecutor + ",\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"id\": " + idSecondLetter + ",\n" +
                "                                \"document_type\": \"Documents::Document\",\n" +
                "                                \"document_id\": " + idDocumentSecond + ",\n" +
                "                                \"document_name\": \"\"\n" +
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
    public void step15(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"head_executor\": {\n" +
                "                \"id\": " + idCreatedHeadExecutor + ",\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"id\": " + idCreatedCoExecutor + ",\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"id\": " + idSecondLetter + ",\n" +
                "                                \"document_type\": \"Documents::Document\",\n" +
                "                                \"document_id\": " + 10000 + ",\n" +
                "                                \"document_name\": \"1\"\n" +
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
    public void step16(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"head_executor\": {\n" +
                "                \"id\": " + idCreatedHeadExecutor + ",\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"id\": " + idCreatedCoExecutor + ",\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"id\": " + idSecondLetter + ",\n" +
                "                                \"document_type\": \"Documents::Document\",\n" +
                "                                \"document_id\": \"string\",\n" +
                "                                \"document_name\": \"1\"\n" +
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

        Assert.assertTrue(errorMessage.equals("имеет неверное значение"));
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
                "                        \"id\": " + idCreatedCoExecutor + ",\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"id\": " + idSecondLetter + ",\n" +
                "                                \"document_type\": \"Sed::Document\",\n" +
                "                                \"document_id\": 100 ,\n" +
                "                                \"document_name\": \"1\"\n" +
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
    public void step18(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"head_executor\": {\n" +
                "                \"id\": " + idCreatedHeadExecutor + ",\n" +
                "                \"co_executors\": [\n" +
                "                    {\n" +
                "                        \"id\": " + idCreatedCoExecutor + ",\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"id\": " + idSecondLetter + ",\n" +
                "                                \"document_type\": \"Sed::Document\",\n" +
                "                                \"document_id\": \"string\",\n" +
                "                                \"document_name\": \"1\"\n" +
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

        Assert.assertTrue(errorMessage.equals("имеет неверное значение"));
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
                "                        \"id\": " + idCreatedCoExecutor + ",\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"id\": " + idSecondLetter + ",\n" +
                "                                \"document_type\": \"Documents::Document\",\n" +
                "                                \"document_id\": " + idDocumentFirst + ",\n" +
                "                                \"document_name\": \"1\"\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"document_type\": \"Documents::Document\",\n" +
                "                                \"document_id\": " + idDocumentFirst + ",\n" +
                "                                \"document_name\": \"1\"\n" +
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
                getString("errors.assignments.0.head_executor.co_executors.0.letters.1.document_id[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("уже существует"));
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
                "                        \"id\": " + idCreatedCoExecutor + ",\n" +
                "                        \"letters\": [\n" +
                "                            {\n" +
                "                                \"id\": " + idSecondLetter + ",\n" +
                "                                \"document_type\": \"Documents::Document\",\n" +
                "                                \"document_id\": " + idDocumentFirst + ",\n" +
                "                                \"document_name\": \"1\"\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"document_type\": \"Documents::Document\",\n" +
                "                                \"document_id\": " + idDocumentFirst + ",\n" +
                "                                \"document_name\": \"1\"\n" +
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
                .header("Test-Authorization", userIdSix)
                .body(requestBody)
                .put(API_DOCUMENTS + String.format("/%s",idCreatedDocument))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("error");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("Доступ к ресурсу запрещен"));
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
        // Delete created document execution state 2 values
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
        // Delete crated document type
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
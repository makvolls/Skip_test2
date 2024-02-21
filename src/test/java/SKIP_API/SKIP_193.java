package SKIP_API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.given;

public class SKIP_193 {
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
    int idCreatedDocument; // Document's id that will be created in step 1
    int idCreatedAssignment; // Assignment's id that will be created in step 1
    int idCreatedExecutors; // Executor's id that will be created in step 1
    int idCreatedExecutions; // Execution's id that will be created in step 1
    int idControlSubject; // Created control subject's id
    int idDocumentExecutionStateOne; // Created document execution state's id first element
    int idDocumentExecutionStateSecond; // Created document execution state's id second element
    int idDocumentType; // Created document type's id
    int idDeadlineBasesFirst; // Created deadline base's id first element
    int idDeadlineBasesSecond; // Created deadline base's id second element
    String idRandomOfficial; // Random official's id
    String idRandomEsdOrganization; // Random ESD organization's id
    String idRandomMvdOrganization; // Random Mvd organization's id
    // Set parameters "name", "short_name" for created elements
    String nameTemplate = "SKIP_193_Autotest";
    String shortNameTemplate = "SKIP_193";
    String uuid; // Uuid that will be used un this testcase

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

        // Create deadline bases 2 values
        // First element
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
        idDeadlineBasesFirst = jsonPathDeadlineBasesCreateFirst.getInt("data.id");
        System.out.println("Deadline bases first element id - " + idDeadlineBasesFirst);

        // Second element
        Map<String, Object> requestBodyDeadlineBasesSecond = new HashMap<>();
        requestBodyDeadlineBasesSecond.put("name",nameTemplate + "e");
        requestBodyDeadlineBasesSecond.put("short_name",shortNameTemplate + "e");
        requestBodyDeadlineBasesSecond.put("note","1");
        requestBodyDeadlineBasesSecond.put("duration_id","3_days");
        requestBodyDeadlineBasesSecond.put("deadline_only",false);
        requestBodyDeadlineBasesSecond.put("excluded",false);
        Response responseDeadlineBasesCreateSecond = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBodyDeadlineBasesSecond)
                .post(API_DEADLINE_BASES)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathDeadlineBasesCreateSecond = responseDeadlineBasesCreateSecond.jsonPath();
        idDeadlineBasesSecond = jsonPathDeadlineBasesCreateSecond.getInt("data.id");
        System.out.println("Deadline bases second element id - " + idDeadlineBasesSecond);


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

        // Get random esd organization
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

        // Random uuid generator for this testcase
        uuid = UUID.randomUUID().toString();
        System.out.println("Created random uuid - " + uuid);
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
        // Delete created deadline bases 2 values
        // First element
        Response responseDeadlineBaseDeleteFirst = (Response) given()
                .header("Test-Authorization", 1)
                .delete(API_DEADLINE_BASES + String.format("/%s", idDeadlineBasesFirst))
                .then().log().all()
                .extract();

        // JsonPath jsonPathDeadlineBaseDeleteFirst = responseDeadlineBaseDeleteFirst.jsonPath();
        // Second element
        Response responseDeadlineBaseDeleteSecond = (Response) given()
                .header("Test-Authorization", 1)
                .delete(API_DEADLINE_BASES + String.format("/%s", idDeadlineBasesSecond))
                .then().log().all()
                .extract();

        // JsonPath jsonPathDeadlineBaseDeleteSecond = responseDeadlineBaseDeleteSecond.jsonPath();
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
                "            \"executor_deadline_calculation\": {\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"start_date\": \"2024-02-16T13:44:34.154Z\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBasesFirst + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"deadline_date\": \"2024-02-19T13:44:34.154Z\",\n" +
                "                \"periodicity_day\": 5,\n" +
                "                \"note\": \"string\",\n" +
                "                \"deadlines\": [\n" +
                "                    {\n" +
                "                        \"id\": \"" + uuid + "\",\n" +
                "                        \"deadline_date\": \"2024-02-16T13:44:34.154Z\",\n" +
                "                        \"deadline_basis_id\": " + idDeadlineBasesSecond + ",\n" +
                "                        \"description\": \"string\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"executors\": [\n" +
                "                {\n" +
                "                    \"organization_id\": \"" + idRandomMvdOrganization + "\",\n" +
                "                    \"executions\": [\n" +
                "                        {\n" +
                "                            \"deadline_id\": \"" + uuid + "\",\n" +
                "                            \"deadline_basis_id\": " + idDeadlineBasesSecond + ",\n" +
                "                            \"note\": \"string\",\n" +
                "                            \"actual_info\": \"string\"\n" +
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
        idCreatedExecutors = jsonPath.getInt("data.assignments[0].executors[0].id");
        idCreatedExecutions = jsonPath.getInt("data.assignments[0].executors[0].executions[0].id");

        String deadlinesId = jsonPath.
                getString("data.assignments[0].executor_deadline_calculation.deadlines[0].id");
        String deadlineDate = jsonPath.
                getString("data.assignments[0].executor_deadline_calculation.deadlines[0].deadline_date");
        int deadlineBasesId = jsonPath.
                getInt("data.assignments[0].executor_deadline_calculation.deadlines[0].deadline_basis.id");
        String deadlineDescription = jsonPath.
                getString("data.assignments[0].executor_deadline_calculation.deadlines[0].description");
        String deadlineIdExecutions = jsonPath.
                getString("data.assignments[0].executors[0].executions[0].deadline_id");
        int deadlineBasesIdExecutions = jsonPath.
                getInt("data.assignments[0].executors[0].executions[0].deadline_basis.id");
        String noteExecutions = jsonPath.getString("data.assignments[0].executors[0].executions[0].note");
        String actualInfoExecutions = jsonPath.
                getString("data.assignments[0].executors[0].executions[0].actual_info");

        System.out.println("Created document id - " + idCreatedDocument);
        System.out.println("Created assignment id - " + idCreatedAssignment);
        System.out.println("Created executors id - " + idCreatedExecutors);
        System.out.println("Created executions id - " + idCreatedExecutions);

        System.out.println("Deadline id from 'deadlines' - " + deadlinesId);
        System.out.println("Deadline date from 'deadlines' - " + deadlineDate);
        System.out.println("Deadline bases id from 'deadlines' - " + deadlineBasesId);
        System.out.println("Description from 'deadlines' - " + deadlineDescription);
        System.out.println("Deadline id from 'executions' - " + deadlineIdExecutions);
        System.out.println("Deadline bases id from 'executions' - " + deadlineBasesIdExecutions);
        System.out.println("Note from 'executions' - " + noteExecutions);
        System.out.println("Actual info from 'executions' - " + actualInfoExecutions);

        Assert.assertTrue(deadlinesId.equals(uuid));
        Assert.assertTrue(deadlineDate.equals("2024-02-16T13:44:34.154Z"));
        Assert.assertTrue(deadlineBasesId == idDeadlineBasesSecond);
        Assert.assertTrue(deadlineDescription.equals("string"));
        Assert.assertTrue(deadlineIdExecutions.equals(uuid));
        Assert.assertTrue(deadlineBasesIdExecutions == idDeadlineBasesSecond);
        Assert.assertTrue(noteExecutions.equals("string"));
        Assert.assertTrue(actualInfoExecutions.equals("string"));
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
                "            \"executor_deadline_calculation\": {\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"start_date\": \"2024-02-16T13:44:34.154Z\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBasesFirst + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"deadline_date\": \"2024-02-19T13:44:34.154Z\",\n" +
                "                \"periodicity_day\": 5,\n" +
                "                \"note\": \"string\",\n" +
                "                \"deadlines\": [\n" +
                "                    {\n" +
                "                        \"id\": \"" + uuid + "\",\n" +
                "                        \"deadline_date\": \"2024-02-16T13:44:34.154Z\",\n" +
                "                        \"deadline_basis_id\": " + idDeadlineBasesSecond + ",\n" +
                "                        \"description\": \"string\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"executors\": [\n" +
                "                {\n" +
                "                    \"organization_id\": \"" + idRandomMvdOrganization + "\",\n" +
                "                    \"executions\": [\n" +
                "                        {\n" +
                "                            \"deadline_id\": \"" + uuid + "\",\n" +
                "                            \"deadline_basis_id\": " + 1000 + ",\n" +
                "                            \"note\": \"string\",\n" +
                "                            \"actual_info\": \"string\"\n" +
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
                getString("errors.assignments.0.executors.0.executions.0.deadline_basis_id[0]");

        System.out.println("Error message- " + errorMessage);

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
                "            \"executor_deadline_calculation\": {\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"start_date\": \"2024-02-16T13:44:34.154Z\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBasesFirst + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"deadline_date\": \"2024-02-19T13:44:34.154Z\",\n" +
                "                \"periodicity_day\": 5,\n" +
                "                \"note\": \"string\",\n" +
                "                \"deadlines\": [\n" +
                "                    {\n" +
                "                        \"id\": \"" + uuid + "\",\n" +
                "                        \"deadline_date\": \"2024-02-16T13:44:34.154Z\",\n" +
                "                        \"deadline_basis_id\": " + idDeadlineBasesSecond + ",\n" +
                "                        \"description\": \"string\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"executors\": [\n" +
                "                {\n" +
                "                    \"organization_id\": \"" + idRandomMvdOrganization + "\",\n" +
                "                    \"executions\": [\n" +
                "                        {\n" +
                "                            \"deadline_id\": \"" + uuid + "\",\n" +
                "                            \"note\": \"string\",\n" +
                "                            \"actual_info\": \"string\"\n" +
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
                getString("errors.assignments.0.executors.0.executions.0.deadline_basis_id[0]");

        System.out.println("Error message- " + errorMessage);

        Assert.assertTrue(errorMessage.equals("не может быть пустым"));
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
                "            \"executor_deadline_calculation\": {\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"start_date\": \"2024-02-16T13:44:34.154Z\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBasesFirst + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"deadline_date\": \"2024-02-19T13:44:34.154Z\",\n" +
                "                \"periodicity_day\": 5,\n" +
                "                \"note\": \"string\"\n" +
                "            },\n" +
                "            \"executors\": [\n" +
                "                {\n" +
                "                    \"organization_id\": \"" + idRandomMvdOrganization + "\",\n" +
                "                    \"executions\": [\n" +
                "                        {\n" +
                "                            \"deadline_id\": \"" + uuid + "\",\n" +
                "                            \"deadline_basis_id\": " + idDeadlineBasesSecond + ",\n" +
                "                            \"note\": \"string\",\n" +
                "                            \"actual_info\": \"string\"\n" +
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
                getString("errors.assignments.0.executors.0.executions.0.deadline_id[0]");

        System.out.println("Error message- " + errorMessage);

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
                "            \"executor_deadline_calculation\": {\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"start_date\": \"2024-02-16T13:44:34.154Z\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBasesFirst + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"deadline_date\": \"2024-02-19T13:44:34.154Z\",\n" +
                "                \"periodicity_day\": 5,\n" +
                "                \"note\": \"string\",\n" +
                "                \"deadlines\": [\n" +
                "                    {\n" +
                "                        \"id\": \"" + uuid + "\",\n" +
                "                        \"deadline_date\": \"2024-02-16T13:44:34.154Z\",\n" +
                "                        \"deadline_basis_id\": " + idDeadlineBasesSecond + ",\n" +
                "                        \"description\": \"string\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"executors\": [\n" +
                "                {\n" +
                "                    \"organization_id\": \"" + idRandomMvdOrganization + "\",\n" +
                "                    \"executions\": [\n" +
                "                        {\n" +
                "                            \"deadline_basis_id\": " + idDeadlineBasesSecond + ",\n" +
                "                            \"note\": \"string\",\n" +
                "                            \"actual_info\": \"string\"\n" +
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
                getString("errors.assignments.0.executors.0.executions.0.deadline_id[0]");

        System.out.println("Error message- " + errorMessage);

        Assert.assertTrue(errorMessage.equals("не может быть пустым"));
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
                "            \"executor_deadline_calculation\": {\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"start_date\": \"2024-02-16T13:44:34.154Z\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBasesFirst + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"deadline_date\": \"2024-02-19T13:44:34.154Z\",\n" +
                "                \"periodicity_day\": 5,\n" +
                "                \"note\": \"string\",\n" +
                "                \"deadlines\": [\n" +
                "                    {\n" +
                "                        \"id\": \"" + uuid + "\",\n" +
                "                        \"deadline_date\": \"2024-02-16T13:44:34.154Z\",\n" +
                "                        \"deadline_basis_id\": " + idDeadlineBasesSecond + ",\n" +
                "                        \"description\": \"string\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"executors\": [\n" +
                "                {\n" +
                "                    \"organization_id\": \"" + idRandomMvdOrganization + "\",\n" +
                "                    \"executions\": [\n" +
                "                        {\n" +
                "                            \"deadline_id\": \"" + uuid + "\",\n" +
                "                            \"deadline_basis_id\": " + idDeadlineBasesSecond + ",\n" +
                "                            \"note\": \"string\",\n" +
                "                            \"actual_info\": \"string\"\n" +
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
    public void step07(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .get(API_DOCUMENTS + String.format("/%s",idCreatedDocument))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String deadlineDate = jsonPath.
                getString("data.assignments[0].executor_deadline_calculation.deadlines[0].deadline_date");
        int deadlineBasesId = jsonPath.
                getInt("data.assignments[0].executor_deadline_calculation.deadlines[0].deadline_basis.id");
        String deadlineDescription = jsonPath.
                getString("data.assignments[0].executor_deadline_calculation.deadlines[0].description");
        int deadlineBasesIdExecutions = jsonPath.
                getInt("data.assignments[0].executors[0].executions[0].deadline_basis.id");
        String noteExecutions = jsonPath.getString("data.assignments[0].executors[0].executions[0].note");
        String actualInfoExecutions = jsonPath.
                getString("data.assignments[0].executors[0].executions[0].actual_info");

        System.out.println("Deadline date from 'deadlines' - " + deadlineDate);
        System.out.println("Deadline bases id from 'deadlines' - " + deadlineBasesId);
        System.out.println("Description from 'deadlines' - " + deadlineDescription);
        System.out.println("Deadline bases id from 'executions' - " + deadlineBasesIdExecutions);
        System.out.println("Note from 'executions' - " + noteExecutions);
        System.out.println("Actual info from 'executions' - " + actualInfoExecutions);

        Assert.assertTrue(deadlineDate.equals("2024-02-16T13:44:34.154Z"));
        Assert.assertTrue(deadlineBasesId == idDeadlineBasesSecond);
        Assert.assertTrue(deadlineDescription.equals("string"));
        Assert.assertTrue(deadlineBasesIdExecutions == idDeadlineBasesSecond);
        Assert.assertTrue(noteExecutions.equals("string"));
        Assert.assertTrue(actualInfoExecutions.equals("string"));

    }

    @Test
    public void step08(){
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
    public void step09(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .put(API_DOCUMENTS + String.format("/%s",idCreatedDocument))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String deadlineDate = jsonPath.
                getString("data.assignments[0].executor_deadline_calculation.deadlines[0].deadline_date");
        int deadlineBasesId = jsonPath.
                getInt("data.assignments[0].executor_deadline_calculation.deadlines[0].deadline_basis.id");
        String deadlineDescription = jsonPath.
                getString("data.assignments[0].executor_deadline_calculation.deadlines[0].description");
        int deadlineBasesIdExecutions = jsonPath.
                getInt("data.assignments[0].executors[0].executions[0].deadline_basis.id");
        String noteExecutions = jsonPath.getString("data.assignments[0].executors[0].executions[0].note");
        String actualInfoExecutions = jsonPath.
                getString("data.assignments[0].executors[0].executions[0].actual_info");

        System.out.println("Deadline date from 'deadlines' - " + deadlineDate);
        System.out.println("Deadline bases id from 'deadlines' - " + deadlineBasesId);
        System.out.println("Description from 'deadlines' - " + deadlineDescription);
        System.out.println("Deadline bases id from 'executions' - " + deadlineBasesIdExecutions);
        System.out.println("Note from 'executions' - " + noteExecutions);
        System.out.println("Actual info from 'executions' - " + actualInfoExecutions);

        Assert.assertTrue(deadlineDate.equals("2024-02-16T13:44:34.154Z"));
        Assert.assertTrue(deadlineBasesId == idDeadlineBasesSecond);
        Assert.assertTrue(deadlineDescription.equals("string"));
        Assert.assertTrue(deadlineBasesIdExecutions == idDeadlineBasesSecond);
        Assert.assertTrue(noteExecutions.equals("string"));
        Assert.assertTrue(actualInfoExecutions.equals("string"));
    }

    @Test
    public void step10(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"executors\": [\n" +
                "                {\n" +
                "                    \"id\": " + idCreatedExecutors + ",\n" +
                "                    \"executions\": [\n" +
                "                        {\n" +
                "                            \"id\": " + idCreatedExecutions + ",\n" +
                "                            \"deadline_id\": \"" + uuid + "\",\n" +
                "                            \"deadline_basis_id\": " + idDeadlineBasesFirst + ",\n" +
                "                            \"note\": \"changed string\",\n" +
                "                            \"actual_info\": \"changed string\"\n" +
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

        int deadlineBases = jsonPath.getInt("data.assignments[0].executors[0].executions[0].deadline_basis.id");
        String note = jsonPath.getString("data.assignments[0].executors[0].executions[0].note");
        String actualInfo = jsonPath.getString("data.assignments[0].executors[0].executions[0].actual_info");

        System.out.println("Changed deadline bases id - " + deadlineBases);
        System.out.println("Changed note - " + note);
        System.out.println("Changed actual info - " + actualInfo);

        Assert.assertTrue(deadlineBases == idDeadlineBasesFirst);
        Assert.assertTrue(note.equals("changed string"));
        Assert.assertTrue(actualInfo.equals("changed string"));
    }

    @Test
    public void step11(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"executors\": [\n" +
                "                {\n" +
                "                    \"id\": " + idCreatedExecutors + ",\n" +
                "                    \"executions\": [\n" +
                "                        {\n" +
                "                            \"id\": " + idCreatedExecutions + ",\n" +
                "                            \"deadline_id\": \"" + uuid + "\",\n" +
                "                            \"deadline_basis_id\": " + 1000 + ",\n" +
                "                            \"note\": \"changed string\",\n" +
                "                            \"actual_info\": \"changed string\"\n" +
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
                getString("errors.assignments.0.executors.0.executions.0.deadline_basis_id[0]");

        System.out.println("Error message- " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step12(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"executors\": [\n" +
                "                {\n" +
                "                    \"id\": " + idCreatedExecutors + ",\n" +
                "                    \"executions\": [\n" +
                "                        {\n" +
                "                            \"id\": " + idCreatedExecutions + ",\n" +
                "                            \"deadline_id\": \"" + uuid + "\",\n" +
                "                            \"deadline_basis_id\": " + null + ",\n" +
                "                            \"note\": \"changed string\",\n" +
                "                            \"actual_info\": \"changed string\"\n" +
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
                getString("errors.assignments.0.executors.0.executions.0.deadline_basis_id[0]");

        System.out.println("Error message- " + errorMessage);

        Assert.assertTrue(errorMessage.equals("не может быть пустым"));
    }

    @Test
    public void step13(){
        // Value in "deadline_id" - uid that wasn't created in any steps
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"executors\": [\n" +
                "                {\n" +
                "                    \"id\": " + idCreatedExecutors + ",\n" +
                "                    \"executions\": [\n" +
                "                        {\n" +
                "                            \"id\": " + idCreatedExecutions + ",\n" +
                "                            \"deadline_id\": \"" + "4b0b225b-41db-4f7a-ae11-764fedd40123"+ "\",\n" +
                "                            \"deadline_basis_id\": " + idDeadlineBasesFirst + ",\n" +
                "                            \"note\": \"changed string\",\n" +
                "                            \"actual_info\": \"changed string\"\n" +
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
                getString("errors.assignments.0.executors.0.executions.0.deadline_id[0]");

        System.out.println("Error message- " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step14(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"executors\": [\n" +
                "                {\n" +
                "                    \"id\": " + idCreatedExecutors + ",\n" +
                "                    \"executions\": [\n" +
                "                        {\n" +
                "                            \"id\": " + idCreatedExecutions + ",\n" +
                "                            \"deadline_id\": \"\",\n" +
                "                            \"deadline_basis_id\": " + idDeadlineBasesFirst + ",\n" +
                "                            \"note\": \"changed string\",\n" +
                "                            \"actual_info\": \"changed string\"\n" +
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
                getString("errors.assignments.0.executors.0.executions.0.deadline_id[0]");

        System.out.println("Error message- " + errorMessage);

        Assert.assertTrue(errorMessage.equals("не может быть пустым"));
    }

    @Test
    public void step15(){
        String requestBody = "";
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

}

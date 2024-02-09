package SKIP_API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.util.*;

import static io.restassured.RestAssured.given;

public class SKIP_151 {
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
    // Need to add esd organizations
    int userIdOne = 1; // User that have all rights, role id = 29
    int userIdSix = 6; // User that have right = "index_roles", role id = 5
    int idCreatedDocument; // Document's id that will be created in step 2
    int idCreatedAssignment; // Assignment's id that will be created in step 2
    int idCreatedHeadExecutor; // Head executor's id that will be created in step 2
    int idCreatedDeadlines; // Deadline's id that will be created in step 2
    int idControlSubject; // Created control subject's id
    int idDocumentExecutionStateOne; // Created document execution state's id first element
    int idDocumentExecutionStateSecond; // Created document execution state's id second element
    int idDocumentType; // Created document type's id
    int idDeadlineBasesOne; // Created deadline base's id first element
    int idDeadlineBasesSecond; // Created deadline base's id second element
    String idRandomOrganization; // Random organization's id
    String idRandomOfficial; // Random official's id


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
                , "SKIP_151_Autotest", "1", false, "525e9f767da3000002000001");
        Response responseControlSubjectCreate = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBody)
                .put(API_CONTROL_SUBJECTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathControlSubjectsCreate = responseControlSubjectCreate.jsonPath();

        int id = jsonPathControlSubjectsCreate.getInt("data.find { it.name == '" + "SKIP_151_Autotest" + "' }.id");
        System.out.println("Created element's id - " + id);
        idControlSubject = id;
        // Create document execution state 2 values
        Map<String, String> requestBodyDocumentExStOne = new HashMap<>();
        requestBodyDocumentExStOne.put("name", "SKIP_151_Autotest_First");
        requestBodyDocumentExStOne.put("short_name", "SKIP_151");
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
        // Create second element
        Map<String, String> requestBodyDocumentExStSecond = new HashMap<>();
        requestBodyDocumentExStSecond.put("name", "SKIP_151_Autotest_Second");
        requestBodyDocumentExStSecond.put("short_name", "SKIP_151e");
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
        requestBodyDocumentType.put("name","SKIP_151_Autotest");
        requestBodyDocumentType.put("short_name","SKIP_151");
        requestBodyDocumentType.put("internal", false);
        requestBodyDocumentType.put("genitive_name","SKIP_151");
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
        // Create first element
        Map<String, Object> requestBodyDeadlineBasesFirst = new HashMap<>();
        requestBodyDeadlineBasesFirst.put("name","SKIP_151_Autotest_First");
        requestBodyDeadlineBasesFirst.put("short_name","1");
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
        idDeadlineBasesOne = jsonPathDeadlineBasesCreateFirst.getInt("data.id");
        System.out.println("Deadline bases first element id - " + idDeadlineBasesOne);

        // Create second element
        Map<String, Object> requestBodyDeadlineBasesSecond = new HashMap<>();
        requestBodyDeadlineBasesSecond.put("name","SKIP_151_Autotest_Second");
        requestBodyDeadlineBasesSecond.put("short_name","2");
        requestBodyDeadlineBasesSecond.put("note","2");
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
        // Get head_executor_id from organization_oshs_mvd
//        Response responseOrganizations = given()
//                .when()
//                .params("items","100")
//                .header("Content-Type", "application/json")
//                .header("Test-Authorization", 1)
//                .get(API_ORGANIZATIONS)
//                .then().log().all()
//                .extract().response();
//
//        JsonPath jsonPathOrganizations = responseOrganizations.jsonPath();
//
//        List<String> idOrganizations = jsonPathOrganizations.getList("data.id");
//        Random randomOrg = new Random();
//        String randomIdOrg = idOrganizations.get(randomOrg.nextInt(idOrganizations.size()));
//        System.out.println("Random id : " + randomIdOrg);
//        idRandomOrganization = randomIdOrg;
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
        idRandomOrganization = randomIdOrgEsd;
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
    }
    @Test
    public void step01(){
        String requestBody = "{\n" +
                "    \"skip_reg_date\": \"2024-01-30T06:06:31.485Z\",\n" +
                "    \"control_official_id\": \"" + idRandomOfficial + "\",\n" +
                "    \"control_subject_id\": " + idControlSubject + ",\n" +
                "    \"document_name\": \"Тест 364\",\n" +
                "    \"document_execution_state_id\": " + idDocumentExecutionStateOne + ",\n" +
                "    \"skip_document_type_id\": " + idDocumentType + ",\n" +
                "    \"internal\": false,\n" +
                "    \"short_description\": \"364\",\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"number\": \"1\",\n" +
                "            \"content\": \"content\",\n" +
                "            \"head_executor\": {\n" +
                "                \"head_executor_id\": \"" + idRandomOrganization +  "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBasesOne + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-02-05T10:56:02.433Z\",\n" +
                "                \"deadlines\":[\n" +
                "                    {\n" +
                "                        \"deadline_date\": \"2024-02-05T10:56:02.433Z\",\n" +
                "                        \"deadline_basis_id\": " + idDeadlineBasesSecond + ",\n" +
                "                        \"description\": \"test description\"\n" +
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

        String deadlineDateHeadExecutorDeadlines = jsonPath.
                getString("data.assignments[0].head_executor.deadlines[0].deadline_date");
        int deadlineBasesId = jsonPath.getInt("data.assignments[0].head_executor.deadlines[0].deadline_basis.id");
        String descriptionHeadExecutorDeadlines = jsonPath.
                getString("data.assignments[0].head_executor.deadlines[0].description");

        System.out.println("Deadline date - " + deadlineDateHeadExecutorDeadlines);
        System.out.println("Deadline basis id - " + deadlineBasesId);
        System.out.println("Description - " + descriptionHeadExecutorDeadlines);

        Assert.assertTrue(deadlineDateHeadExecutorDeadlines.equals("2024-02-05T10:56:02.433Z"));
        Assert.assertTrue(deadlineBasesId == idDeadlineBasesSecond);
        Assert.assertTrue(descriptionHeadExecutorDeadlines.equals("test description"));
    }

    @Test
    public void step02(){
        String requestBody = "{\n" +
                "    \"skip_reg_date\": \"2024-01-30T06:06:31.485Z\",\n" +
                "    \"control_official_id\": \"" + idRandomOfficial + "\",\n" +
                "    \"control_subject_id\": " + idControlSubject + ",\n" +
                "    \"document_name\": \"Тест 364\",\n" +
                "    \"document_execution_state_id\": " + idDocumentExecutionStateOne + ",\n" +
                "    \"skip_document_type_id\": " + idDocumentType + ",\n" +
                "    \"internal\": false,\n" +
                "    \"short_description\": \"364\",\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"number\": \"1\",\n" +
                "            \"content\": \"content\",\n" +
                "            \"head_executor\": {\n" +
                "                \"head_executor_id\": \"" + idRandomOrganization +  "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBasesOne + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-02-05T10:56:02.433Z\",\n" +
                "                \"deadlines\":[\n" +
                "                    {\n" +
                "                        \"deadline_date\": \"2024-02-05T10:56:02.433Z\",\n" +
                "                        \"deadline_basis_id\": " + idDeadlineBasesSecond + ",\n" +
                "                        \"description\": \"test description\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"deadline_date\": \"2024-02-02T10:56:02.433Z\",\n" +
                "                        \"deadline_basis_id\": " + idDeadlineBasesSecond + ",\n" +
                "                        \"description\": \"test description\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"deadline_date\": \"2024-01-22T10:56:02.433Z\",\n" +
                "                        \"deadline_basis_id\": " + idDeadlineBasesSecond + ",\n" +
                "                        \"description\": \"test description\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"deadline_date\": \"2024-01-27T10:56:02.433Z\",\n" +
                "                        \"deadline_basis_id\": " + idDeadlineBasesSecond + ",\n" +
                "                        \"description\": \"test description\"\n" +
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

        // Store document's id in variable, it will be used with GET and PUT requests
        idCreatedDocument = jsonPath.get("data.id");
        // Store created ids in variables, it will be used with PUT requests
        idCreatedAssignment = jsonPath.get("data.assignments[0].id");
        idCreatedHeadExecutor = jsonPath.get("data.assignments[0].head_executor.id");
        // Get deadline's id with the latest date - 2024-02-05
        idCreatedDeadlines = jsonPath.get("data.assignments[0].head_executor.deadlines[3].id");

        String deadlineDateHeadExecutorDeadlinesFirst = jsonPath.
                getString("data.assignments[0].head_executor.deadlines[0].deadline_date");
        int deadlineBasisIdHeadExecutorDeadlinesFirst = jsonPath.
                getInt("data.assignments[0].head_executor.deadlines[0].deadline_basis.id");
        String descriptionHeadExecutorDeadlinesFirst = jsonPath.
                getString("data.assignments[0].head_executor.deadlines[0].description");
        String deadlineDateHeadExecutorDeadlinesSecond = jsonPath.
                getString("data.assignments[0].head_executor.deadlines[1].deadline_date");
        int deadlineBasisIdHeadExecutorDeadlinesSecond = jsonPath.
                getInt("data.assignments[0].head_executor.deadlines[1].deadline_basis.id");
        String descriptionHeadExecutorDeadlinesSecond = jsonPath.
                getString("data.assignments[0].head_executor.deadlines[1].description");
        String deadlineDateHeadExecutorDeadlinesThird = jsonPath.
                getString("data.assignments[0].head_executor.deadlines[2].deadline_date");
        int deadlineBasisIdHeadExecutorDeadlinesThird = jsonPath.
                getInt("data.assignments[0].head_executor.deadlines[2].deadline_basis.id");
        String descriptionHeadExecutorDeadlinesThird = jsonPath.
                getString("data.assignments[0].head_executor.deadlines[2].description");
        String deadlineDateHeadExecutorDeadlinesFourth = jsonPath.
                getString("data.assignments[0].head_executor.deadlines[3].deadline_date");
        int deadlineBasisIdHeadExecutorDeadlinesFourth = jsonPath.
                getInt("data.assignments[0].head_executor.deadlines[3].deadline_basis.id");
        String descriptionHeadExecutorDeadlinesFourth = jsonPath.
                getString("data.assignments[0].head_executor.deadlines[3].description");


        System.out.println("New assignment - " + idCreatedAssignment);
        System.out.println("New head executor - " + idCreatedHeadExecutor);
        System.out.println("New deadline - " + idCreatedDeadlines);

        System.out.println("Deadline date first element - " + deadlineDateHeadExecutorDeadlinesFirst);
        System.out.println("Deadline basis id first element - " + deadlineBasisIdHeadExecutorDeadlinesFirst);
        System.out.println("Description first element - " + descriptionHeadExecutorDeadlinesFirst);
        System.out.println("Deadline date second element - " + deadlineDateHeadExecutorDeadlinesSecond);
        System.out.println("Deadline basis id second element - " + deadlineBasisIdHeadExecutorDeadlinesSecond);
        System.out.println("Description second element - " + descriptionHeadExecutorDeadlinesSecond);
        System.out.println("Deadline date third element - " + deadlineDateHeadExecutorDeadlinesThird);
        System.out.println("Deadline basis id third element - " + deadlineBasisIdHeadExecutorDeadlinesThird);
        System.out.println("Description third element - " + descriptionHeadExecutorDeadlinesThird);
        System.out.println("Deadline date fourth element - " + deadlineDateHeadExecutorDeadlinesFourth);
        System.out.println("Deadline basis id fourth element - " + deadlineBasisIdHeadExecutorDeadlinesFourth);
        System.out.println("Description fourth element - " + descriptionHeadExecutorDeadlinesFourth);
        // check order of date
        Assert.assertTrue(deadlineDateHeadExecutorDeadlinesFirst.equals("2024-01-22T10:56:02.433Z"));
        Assert.assertTrue(deadlineBasisIdHeadExecutorDeadlinesFirst == idDeadlineBasesSecond);
        Assert.assertTrue(descriptionHeadExecutorDeadlinesFirst.equals("test description"));
        Assert.assertTrue(deadlineDateHeadExecutorDeadlinesSecond.equals("2024-01-27T10:56:02.433Z"));
        Assert.assertTrue(deadlineBasisIdHeadExecutorDeadlinesSecond == idDeadlineBasesSecond);
        Assert.assertTrue(descriptionHeadExecutorDeadlinesSecond.equals("test description"));
        Assert.assertTrue(deadlineDateHeadExecutorDeadlinesThird.equals("2024-02-02T10:56:02.433Z"));
        Assert.assertTrue(deadlineBasisIdHeadExecutorDeadlinesThird == idDeadlineBasesSecond);
        Assert.assertTrue(descriptionHeadExecutorDeadlinesThird.equals("test description"));
        Assert.assertTrue(deadlineDateHeadExecutorDeadlinesFourth.equals("2024-02-05T10:56:02.433Z"));
        Assert.assertTrue(deadlineBasisIdHeadExecutorDeadlinesFourth == idDeadlineBasesSecond);
        Assert.assertTrue(descriptionHeadExecutorDeadlinesFourth.equals("test description"));
    }

    @Test
    public void step03(){
        String requestBody = "{\n" +
                "    \"skip_reg_date\": \"2024-01-30T06:06:31.485Z\",\n" +
                "    \"control_official_id\": \"" + idRandomOfficial + "\",\n" +
                "    \"control_subject_id\": " + idControlSubject + ",\n" +
                "    \"document_name\": \"Тест 364\",\n" +
                "    \"document_execution_state_id\": " + idDocumentExecutionStateOne + ",\n" +
                "    \"skip_document_type_id\": " + idDocumentType + ",\n" +
                "    \"internal\": false,\n" +
                "    \"short_description\": \"364\",\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"number\": \"1\",\n" +
                "            \"content\": \"content\",\n" +
                "            \"head_executor\": {\n" +
                "                \"head_executor_id\": \"" + idRandomOrganization +  "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBasesOne + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-02-05T10:56:02.433Z\",\n" +
                "                \"deadlines\":[\n" +
                "                    {\n" +
                "                        \"deadline_date\": \"1\",\n" +
                "                        \"deadline_basis_id\": " + idDeadlineBasesSecond + "\n" +
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
                getString("errors.assignments.0.head_executor.deadlines.0.deadline_date[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет неверное значение"));
    }

    @Test
    public void step04(){
        String requestBody = "{\n" +
                "    \"skip_reg_date\": \"2024-01-30T06:06:31.485Z\",\n" +
                "    \"control_official_id\": \"" + idRandomOfficial + "\",\n" +
                "    \"control_subject_id\": " + idControlSubject + ",\n" +
                "    \"document_name\": \"Тест 364\",\n" +
                "    \"document_execution_state_id\": " + idDocumentExecutionStateOne + ",\n" +
                "    \"skip_document_type_id\": " + idDocumentType + ",\n" +
                "    \"internal\": false,\n" +
                "    \"short_description\": \"364\",\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"number\": \"1\",\n" +
                "            \"content\": \"content\",\n" +
                "            \"head_executor\": {\n" +
                "                \"head_executor_id\": \"" + idRandomOrganization +  "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBasesOne + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-02-05T10:56:02.433Z\",\n" +
                "                \"deadlines\":[\n" +
                "                    {\n" +
                "                        \"deadline_date\": \"2024-01-17T08:17:01.707Z\",\n" +
                "                        \"deadline_basis_id\": " + idDeadlineBasesSecond + "\n" +
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
                getString("errors.assignments.0.head_executor.deadlines.0.deadline_date[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("не может быть меньше даты начала"));
    }

    @Test
    public void step05(){
        String requestBody = "{\n" +
                "    \"skip_reg_date\": \"2024-01-30T06:06:31.485Z\",\n" +
                "    \"control_official_id\": \"" + idRandomOfficial + "\",\n" +
                "    \"control_subject_id\": " + idControlSubject + ",\n" +
                "    \"document_name\": \"Тест 364\",\n" +
                "    \"document_execution_state_id\": " + idDocumentExecutionStateOne + ",\n" +
                "    \"skip_document_type_id\": " + idDocumentType + ",\n" +
                "    \"internal\": false,\n" +
                "    \"short_description\": \"364\",\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"number\": \"1\",\n" +
                "            \"content\": \"content\",\n" +
                "            \"head_executor\": {\n" +
                "                \"head_executor_id\": \"" + idRandomOrganization +  "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBasesOne + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-02-05T10:56:02.433Z\",\n" +
                "                \"deadlines\":[\n" +
                "                    {\n" +
                "                        \"deadline_date\": \"2024-02-05T10:56:02.433Z\",\n" +
                "                        \"deadline_basis_id\": " + 1000 + "\n" +
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
                getString("errors.assignments.0.head_executor.deadlines.0.deadline_basis_id[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step06(){
        String requestBody = "{\n" +
                "    \"skip_reg_date\": \"2024-01-30T06:06:31.485Z\",\n" +
                "    \"control_official_id\": \"" + idRandomOfficial + "\",\n" +
                "    \"control_subject_id\": " + idControlSubject + ",\n" +
                "    \"document_name\": \"Тест 364\",\n" +
                "    \"document_execution_state_id\": " + idDocumentExecutionStateOne + ",\n" +
                "    \"skip_document_type_id\": " + idDocumentType + ",\n" +
                "    \"internal\": false,\n" +
                "    \"short_description\": \"364\",\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"number\": \"1\",\n" +
                "            \"content\": \"content\",\n" +
                "            \"head_executor\": {\n" +
                "                \"head_executor_id\": \"" + idRandomOrganization +  "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBasesOne + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-02-05T10:56:02.433Z\",\n" +
                "                \"deadlines\":[\n" +
                "                    {\n" +
                "                        \"deadline_date\": \"2024-01-30T08:17:01.707Z\"\n" +
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
                getString("errors.assignments.0.head_executor.deadlines.0.deadline_basis_id[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("не может быть пустым"));
    }

    @Test
    public void step07(){
        String requestBody = "{\n" +
                "    \"skip_reg_date\": \"2024-01-30T06:06:31.485Z\",\n" +
                "    \"control_official_id\": \"" + idRandomOfficial + "\",\n" +
                "    \"control_subject_id\": " + idControlSubject + ",\n" +
                "    \"document_name\": \"Тест 364\",\n" +
                "    \"document_execution_state_id\": " + idDocumentExecutionStateOne + ",\n" +
                "    \"skip_document_type_id\": " + idDocumentType + ",\n" +
                "    \"internal\": false,\n" +
                "    \"short_description\": \"364\",\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"number\": \"1\",\n" +
                "            \"content\": \"content\",\n" +
                "            \"head_executor\": {\n" +
                "                \"head_executor_id\": \"" + idRandomOrganization +  "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBasesOne + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-02-05T10:56:02.433Z\",\n" +
                "                \"deadlines\":[\n" +
                "                    {\n" +
                "                        \"deadline_basis_id\": " + idDeadlineBasesSecond + ",\n" +
                "                        \"description\": \"test description\"\n" +
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
                getString("errors.assignments.0.head_executor.deadlines.0.deadline_date[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("не может быть пустым"));
    }

    @Test
    public void step08(){
        String requestBody = "{\n" +
                "    \"skip_reg_date\": \"2024-01-30T06:06:31.485Z\",\n" +
                "    \"control_official_id\": \"" + idRandomOfficial + "\",\n" +
                "    \"control_subject_id\": " + idControlSubject + ",\n" +
                "    \"document_name\": \"Тест 364\",\n" +
                "    \"document_execution_state_id\": " + idDocumentExecutionStateOne + ",\n" +
                "    \"skip_document_type_id\": " + idDocumentType + ",\n" +
                "    \"internal\": false,\n" +
                "    \"short_description\": \"364\",\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"number\": \"1\",\n" +
                "            \"content\": \"content\",\n" +
                "            \"head_executor\": {\n" +
                "                \"head_executor_id\": \"" + idRandomOrganization +  "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBasesOne + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-02-05T10:56:02.433Z\",\n" +
                "                \"deadlines\":[\n" +
                "                    {\n" +
                "                        \"deadline_date\": \"2024-01-27T10:56:02.433Z\",\n" +
                "                        \"deadline_basis_id\": " + idDeadlineBasesSecond + ",\n" +
                "                        \"description\": \"test description\"\n" +
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
    public void step09(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .get(API_DOCUMENTS + String.format("/%s",idCreatedDocument))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        List<String> deadlineDates = jsonPath.getList("data.assignments[0].head_executor.deadlines.deadline_date");
        System.out.println("Deadline dates - " + deadlineDates);
        List<String> deadlineDatesSorted = new ArrayList<>(deadlineDates);
        Collections.sort(deadlineDatesSorted);
        System.out.println("Deadline dates sorted - " + deadlineDatesSorted);
        Assert.assertTrue(deadlineDates.equals(deadlineDatesSorted));
    }

    @Test
    public void step10(){
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
    public void step11(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .put(API_DOCUMENTS + String.format("/%s",idCreatedDocument))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        List<String> deadlineDates = jsonPath.getList("data.assignments[0].head_executor.deadlines.deadline_date");
        System.out.println("Deadline dates - " + deadlineDates);
        List<String> deadlineDatesSorted = new ArrayList<>(deadlineDates);
        Collections.sort(deadlineDatesSorted);
        System.out.println("Deadline dates sorted - " + deadlineDatesSorted);
        Assert.assertTrue(deadlineDates.equals(deadlineDatesSorted));
    }

    @Test
    public void step12(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"number\": \"1\",\n" +
                "            \"content\": \"content\",\n" +
                "            \"head_executor\": {\n" +
                "                \"id\": " + idCreatedHeadExecutor + ",\n" +
                "                \"head_executor_id\": \"" + idRandomOrganization +  "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBasesOne + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-02-05T10:56:02.433Z\",\n" +
                "                \"deadlines\": [\n" +
                "                    {\n" +
                "                        \"id\": " + idCreatedDeadlines + ",\n" +
                "                        \"deadline_date\": \"2024-02-04T10:56:02.433Z\",\n" +
                "                        \"deadline_basis_id\": " + idDeadlineBasesSecond + ",\n" +
                "                        \"description\": \"changed desc\"\n" +
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


        String deadlineDateHeadExecutorDeadlines = jsonPath.
                getString("data.assignments[0].head_executor.deadlines[3].deadline_date");
        int deadlineBasisIdHeadExecutorDeadlines = jsonPath.
                getInt("data.assignments[0].head_executor.deadlines[3].deadline_basis.id");
        String descriptionHeadExecutorDeadlines = jsonPath.
                getString("data.assignments[0].head_executor.deadlines[3].description");

        System.out.println("Deadline date - " + deadlineDateHeadExecutorDeadlines);
        System.out.println("Deadline basis id - " + deadlineBasisIdHeadExecutorDeadlines);
        System.out.println("Description - " + descriptionHeadExecutorDeadlines);

        // Assert and check changed strings
        Assert.assertTrue(deadlineDateHeadExecutorDeadlines.equals("2024-02-04T10:56:02.433Z"));
        Assert.assertTrue(deadlineBasisIdHeadExecutorDeadlines == idDeadlineBasesSecond);
        Assert.assertTrue(descriptionHeadExecutorDeadlines.equals("changed desc"));
    }

    @Test
    public void step13(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"number\": \"1\",\n" +
                "            \"content\": \"content\",\n" +
                "            \"head_executor\": {\n" +
                "                \"id\": " + idCreatedHeadExecutor + ",\n" +
                "                \"head_executor_id\": \"" + idRandomOrganization +  "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBasesOne + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-02-05T10:56:02.433Z\",\n" +
                "                \"deadlines\": [\n" +
                "                    {\n" +
                "                        \"id\": " + idCreatedDeadlines + ",\n" +
                "                        \"deadline_date\": \"2024-01-17T10:56:02.433Z\",\n" +
                "                        \"deadline_basis_id\": " + idDeadlineBasesSecond + ",\n" +
                "                        \"description\": \"changed desc\"\n" +
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

        String errorMessage = jsonPath.getString("errors.assignments.0.head_executor.deadlines.0.deadline_date[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("не может быть меньше даты начала"));
    }

    @Test
    public void step14(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"number\": \"1\",\n" +
                "            \"content\": \"content\",\n" +
                "            \"head_executor\": {\n" +
                "                \"id\": " + idCreatedHeadExecutor + ",\n" +
                "                \"head_executor_id\": \"" + idRandomOrganization +  "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBasesOne + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-02-05T10:56:02.433Z\",\n" +
                "                \"deadlines\": [\n" +
                "                    {\n" +
                "                        \"id\": " + idCreatedDeadlines + ",\n" +
                "                        \"deadline_date\": \"2024-02-04T10:56:02.433Z\",\n" +
                "                        \"deadline_basis_id\": " + 1000 + ",\n" +
                "                        \"description\": \"changed desc\"\n" +
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
                getString("errors.assignments.0.head_executor.deadlines.0.deadline_basis_id[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step15(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"number\": \"1\",\n" +
                "            \"content\": \"content\",\n" +
                "            \"head_executor\": {\n" +
                "                \"id\": " + idCreatedHeadExecutor + ",\n" +
                "                \"head_executor_id\": \"" + idRandomOrganization +  "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBasesOne + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-02-05T10:56:02.433Z\",\n" +
                "                \"deadlines\": [\n" +
                "                    {\n" +
                "                        \"id\": " + idCreatedDeadlines + ",\n" +
                "                        \"deadline_date\": \"1\",\n" +
                "                        \"deadline_basis_id\": " + idDeadlineBasesSecond + ",\n" +
                "                        \"description\": \"changed desc\"\n" +
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

        String errorMessage = jsonPath.getString("errors.assignments.0.head_executor.deadlines.0.deadline_date[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет неверное значение"));
    }

    @Test
    public void step16(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"number\": \"1\",\n" +
                "            \"content\": \"content\",\n" +
                "            \"head_executor\": {\n" +
                "                \"id\": " + idCreatedHeadExecutor + ",\n" +
                "                \"head_executor_id\": \"" + idRandomOrganization +  "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBasesOne + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-02-05T10:56:02.433Z\",\n" +
                "                \"deadlines\": [\n" +
                "                    {\n" +
                "                        \"id\": " + idCreatedDeadlines + ",\n" +
                "                        \"deadline_date\": \"\",\n" +
                "                        \"deadline_basis_id\": " + idDeadlineBasesSecond + ",\n" +
                "                        \"description\": \"changed desc\"\n" +
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

        String errorMessage = jsonPath.getString("errors.assignments.0.head_executor.deadlines.0.deadline_date[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("не может быть пустым"));
    }

    @Test
    public void step17(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"number\": \"1\",\n" +
                "            \"content\": \"content\",\n" +
                "            \"head_executor\": {\n" +
                "                \"id\": " + idCreatedHeadExecutor + ",\n" +
                "                \"head_executor_id\": \"" + idRandomOrganization +  "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBasesOne + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-02-05T10:56:02.433Z\",\n" +
                "                \"deadlines\": [\n" +
                "                    {\n" +
                "                        \"id\": " + idCreatedDeadlines + ",\n" +
                "                        \"deadline_date\": \"2024-02-04T10:56:02.433Z\",\n" +
                "                        \"deadline_basis_id\": " + null + ",\n" +
                "                        \"description\": \"changed desc\"\n" +
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
                getString("errors.assignments.0.head_executor.deadlines.0.deadline_basis_id[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("не может быть пустым"));
    }

    @Test
    public void step18(){
        String requestBody = "{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": " + idCreatedAssignment + ",\n" +
                "            \"number\": \"1\",\n" +
                "            \"content\": \"content\",\n" +
                "            \"head_executor\": {\n" +
                "                \"id\": " + idCreatedHeadExecutor + ",\n" +
                "                \"head_executor_id\": \"" + idRandomOrganization +  "\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBasesOne + ",\n" +
                "                \"periodicity_id\": " + 38 + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionStateSecond + ",\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2024-02-05T10:56:02.433Z\",\n" +
                "                \"deadlines\": [\n" +
                "                    {\n" +
                "                        \"id\": " + idCreatedDeadlines + ",\n" +
                "                        \"deadline_date\": \"2024-02-04T10:56:02.433Z\",\n" +
                "                        \"deadline_basis_id\": " + idDeadlineBasesSecond + ",\n" +
                "                        \"description\": \"changed desc\"\n" +
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
        // Delete created control_subject
        String requestBodyCS = String.format("{\n" +
                "  \"control_subjects\": [\n" +
                "    {\n" +
                "      \"id\": 155,\n" +
                "      \"name\": \"SKIP_151_Autotest\",\n" +
                "      \"fax_number\": \"1\",\n" +
                "      \"deleted\": true,\n" +
                "      \"provider_id\": \"525e9f767da3000002000001\"\n" +
                "    }\n" +
                "  ]\n" +
                "}");
        Response responseControlSubjectDelete = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBodyCS)
                .put(API_CONTROL_SUBJECTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathControlSubjectsDelete = responseControlSubjectDelete.jsonPath();

        List<Integer> idList = jsonPathControlSubjectsDelete.getList("data.id");
        for (int id: idList){
            if (id == 155) {
                System.out.println("Control subject with id = 155 is existed!");
            }
        }
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
        // Check deletion
        Response responseDES = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .get(API_DOCUMENT_EXECUTION_STATES)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathDES = responseDES.jsonPath();
        List<String> namesDES = jsonPathDES.getList("data.name");
        for (String name: namesDES) {
            if (name.equals("SKIP_151_Autotest_First")){
                System.out.println("Element with name - SKIP_151_Autotest_First is existed!");
            }
            if (name.equals("SKIP_151_Autotest_Second")){
                System.out.println("Element with name - SKIP_151_Autotest_Second is existed!");
            }
        }
        // Delete created document type
        Response responseDocumentType = (Response) given()
                .header("Test-Authorization", 1)
                .delete(API_DOC_TYPE + String.format("/%s",idDocumentType))
                .then().log().all()
                .extract();

        // JsonPath jsonPathDocumentType = responseDocumentType.jsonPath();
        Response responseDocumentTypeGet = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .get(API_DOC_TYPE)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathDocumentTypeGet = responseDocumentTypeGet.jsonPath();
        List<String> names = jsonPathDocumentTypeGet.getList("data.name");
        for (String name: names){
            if (name.equals("SKIP_151_Autotest")){
                System.out.println("Document type with name = SKIP_151_Autotest is existed!");
            }
        }
        // Delete created deadline bases 2 values
        // First element
        Response responseDeadlineBaseDeleteFirst = (Response) given()
                .header("Test-Authorization", 1)
                .delete(API_DEADLINE_BASES + String.format("/%s",idDeadlineBasesOne))
                .then().log().all()
                .extract();

        // JsonPath jsonPathDeadlineBaseDeleteFirst = responseDeadlineBaseDeleteFirst.jsonPath();
        // Second element
        Response responseDeadlineBaseDeleteSecond = (Response) given()
                .header("Test-Authorization", 1)
                .delete(API_DEADLINE_BASES + String.format("/%s",idDeadlineBasesSecond))
                .then().log().all()
                .extract();

        // JsonPath jsonPathDeadlineBaseDeleteSecond = responseDeadlineBaseDeleteSecond.jsonPath();
        // Check deletion
        Response responseDeadlineBasesGet = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .get(API_DOC_TYPE)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathDeadlineBasesGet = responseDeadlineBasesGet.jsonPath();
        List<String> namesDeadlineBases = jsonPathDeadlineBasesGet.getList("data.name");
        for (String name: namesDeadlineBases) {
            if (name.equals("SKIP_151_Autotest_First")){
                System.out.println("Deadline base with name = SKIP_151_Autotest_First is existed!");
            }
            if (name.equals("SKIP_151_Autotest_Second")){
                System.out.println("Deadline base with name = SKIP_151_Autotest_Second is existed!");
            }
        }
    }
}

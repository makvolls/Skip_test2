package SKIP_API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class SKIP_201 {

    private static String API_DOCUMENTS = "http://api.skip.rtech.ru/v1/documents";
    private static String API_CONTROL_SUBJECTS = "http://api.skip.rtech.ru/v1/permissions/control_subjects";
    private static String API_DOC_TYPE = "http://api.skip.rtech.ru/v1/classifiers/document_types";
    private static String API_DOCUMENT_EXECUTION_STATES = "http://api.skip.rtech.ru" +
            "/v1/classifiers/document_execution_states";
    private static String API_OSHS_MVD_OFFICIALS="http://api.skip.rtech.ru/v1/oshs/mvd/officials";
    private static String API_ORGANIZATIONS="http://api.skip.rtech.ru/v1/oshs/mvd/organizations";

    // Variables for setup

    int userIdOne = 1; // User that have all rights, role id = 29
    int idCreatedDocument; // Document's id that will be created in step 1
    int idControlSubject; // Created control subject's id
    int idDocumentExecutionStateOne; // Created document execution state's id
    int idDocumentType; // Created document type's id
    String idRandomOfficial; // Random official's id
    String idRandomOrganization; // Random organization's id
    String idRandomOrganizationSecond; // Random organization's id second value
    String nameTemplate = "SKIP_201_Autotest";
    String shortNameTemplate = "SKIP_201";
    @BeforeTest
    public void setup(){

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

    @Test
    public void step01(){
        String requestBody = "{\n" +
                "    \"skip_reg_date\": \"2024-01-16T06:06:31.485Z\",\n" +
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
                "                    \"organization_id\": \"" + idRandomOrganizationSecond + "\",\n" +
                "                    \"executor_id\": \"" + idRandomOfficial + "\",\n" +
                "                    \"control_employee_id\": \"" + idRandomOfficial + "\",\n" +
                "                    \"executor_head_id\": \"" + idRandomOfficial + "\",\n" +
                "                    \"organization_head_id\": \"" + idRandomOfficial + "\",\n" +
                "                    \"execution_deputy_head_id\": \"" + idRandomOfficial + "\",\n" +
                "                    \"control_deputy_head_id\": \"" + idRandomOfficial + "\",\n" +
                "                    \"co_executors\": [\n" +
                "                        {\n" +
                "                            \"organization_short_title\": \"string\",\n" +
                "                            \"organization_id\": \"" + idRandomOrganization + "\",\n" +
                "                            \"organization_type\": \"Oshs::Mvd::Organization\",\n" +
                "                            \"co_executor_id\": \"" + idRandomOfficial + "\",\n" +
                "                            \"co_executor_name\": \"string\",\n" +
                "                            \"head_co_executor_id\": \"" + idRandomOfficial + "\",\n" +
                "                            \"head_co_executor_name\": \"string\",\n" +
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
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        idCreatedDocument = jsonPath.
                getInt("data.id");

        String executorPhone = jsonPath.
                getString("data.assignments[0].executors[0].executor.phone");
        String controlEmployeePhone = jsonPath.
                getString("data.assignments[0].executors[0].control_employee.phone");
        String executorHeadPhone = jsonPath.
                getString("data.assignments[0].executors[0].executor_head.phone");
        String organizationHeadPhone= jsonPath.
                getString("data.assignments[0].executors[0].organization_head.phone");
        String executionDeputyHeadPhone = jsonPath.
                getString("data.assignments[0].executors[0].execution_deputy_head.phone");
        String controlDeputyHeadPhone = jsonPath.
                getString("data.assignments[0].executors[0].control_deputy_head.phone");
        String coExecutorPhone = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[0].co_executor_phone");
        String headCoExecutorPhone = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[0].head_co_executor_phone");

        System.out.println("Created document id - " + idCreatedDocument);

        System.out.println("Executor phone - " + executorPhone);
        System.out.println("Control employee phone - " + controlEmployeePhone);
        System.out.println("Executor head phone - " + executorHeadPhone);
        System.out.println("Organization head phone - " + organizationHeadPhone);
        System.out.println("Execution deputy head phone - " + executionDeputyHeadPhone);
        System.out.println("Control deputy head phone - " + controlDeputyHeadPhone);
        System.out.println("Co executor phone - " + coExecutorPhone);
        System.out.println("Head co executor phone - " + headCoExecutorPhone);

        Assert.assertNotNull(executorPhone);
        Assert.assertNotNull(controlEmployeePhone);
        Assert.assertNotNull(executorHeadPhone);
        Assert.assertNotNull(organizationHeadPhone);
        Assert.assertNotNull(executionDeputyHeadPhone);
        Assert.assertNotNull(controlDeputyHeadPhone);
        Assert.assertNotNull(coExecutorPhone);
        Assert.assertNotNull(headCoExecutorPhone);
    }

    @Test
    public void step02(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .get(API_DOCUMENTS + String.format("/%s",idCreatedDocument))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String executorPhone = jsonPath.
                getString("data.assignments[0].executors[0].executor.phone");
        String controlEmployeePhone = jsonPath.
                getString("data.assignments[0].executors[0].control_employee.phone");
        String executorHeadPhone = jsonPath.
                getString("data.assignments[0].executors[0].executor_head.phone");
        String organizationHeadPhone= jsonPath.
                getString("data.assignments[0].executors[0].organization_head.phone");
        String executionDeputyHeadPhone = jsonPath.
                getString("data.assignments[0].executors[0].execution_deputy_head.phone");
        String controlDeputyHeadPhone = jsonPath.
                getString("data.assignments[0].executors[0].control_deputy_head.phone");
        String coExecutorPhone = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[0].co_executor_phone");
        String headCoExecutorPhone = jsonPath.
                getString("data.assignments[0].executors[0].co_executors[0].head_co_executor_phone");

        System.out.println("Executor phone - " + executorPhone);
        System.out.println("Control employee phone - " + controlEmployeePhone);
        System.out.println("Executor head phone - " + executorHeadPhone);
        System.out.println("Organization head phone - " + organizationHeadPhone);
        System.out.println("Execution deputy head phone - " + executionDeputyHeadPhone);
        System.out.println("Control deputy head phone - " + controlDeputyHeadPhone);
        System.out.println("Co executor phone - " + coExecutorPhone);
        System.out.println("Head co executor phone - " + headCoExecutorPhone);

        Assert.assertNotNull(executorPhone);
        Assert.assertNotNull(controlEmployeePhone);
        Assert.assertNotNull(executorHeadPhone);
        Assert.assertNotNull(organizationHeadPhone);
        Assert.assertNotNull(executionDeputyHeadPhone);
        Assert.assertNotNull(controlDeputyHeadPhone);
        Assert.assertNotNull(coExecutorPhone);
        Assert.assertNotNull(headCoExecutorPhone);
    }
}

package SKIP_API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SKIP_138 {
    private static String API_DOCUMENTS = "http://api.skip.rtech.ru/v1/documents";
    private static String API_DEADLINE_BASES = "http://api.skip.rtech.ru/v1/classifiers/deadline_bases";
    private static String API_PERIODICITY = "http://api.skip.rtech.ru/v1/classifiers/periodicity";
    private static String API_USER = "http://api.skip.rtech.ru/v1/permissions/users";
    private static String API_CONTROL_SUBJECTS = "http://api.skip.rtech.ru/v1/permissions/control_subjects";
    private static String API_DOC_TYPE = "http://api.skip.rtech.ru/v1/classifiers/document_types";
    private static String API_DOCUMENT_EXECUTION_STATES = "http://api.skip.rtech.ru" +
            "/v1/classifiers/document_execution_states";
    private static String API_THEMES = "http://api.skip.rtech.ru/v1/classifiers/themes";
    private static String API_ASSIGNMENT_EXECUTION_STATES = "http://api.skip.rtech.ru" +
            "/v1/classifiers/assignment_execution_states";
    int idDocumentToRemember;
    int idAssignmentToRemember;
    int idHeadExecutor;



    @BeforeTest
    public void setup() {
        // Check that user with id 6 has role id = 5
        Response responseUser = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .get(API_USER + "/6")
                .then().log().all()
                .extract().response();

        JsonPath jsonPathUser = responseUser.jsonPath();

        String roleId = jsonPathUser.getString("data.roles[0].id");

        if (roleId.equals("5")) {
            System.out.println("User has role id - 5");
        } else {
            System.out.println("User has role id - " + roleId);
            // Change role id
            String requestBodyUser = "{\"role_ids\": [5]}";
            Response responseChangeRoleId = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", 1)
                    .body(requestBodyUser)
                    .put(API_USER + "/6")
                    .then().log().all()
                    .extract().response();

            JsonPath jsonPathChangeRoleId = responseChangeRoleId.jsonPath();

            String roleIdUpdated = jsonPathChangeRoleId.getString("data.roles[0].id");

            System.out.println("New role id - " + roleIdUpdated);
            // Check that control subject with id 83 is existed
            Response responseControlSubject = given()
                    .when()
                    .params("provider_id","525e9f767da3000002000001")
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", 1)
                    .get(API_CONTROL_SUBJECTS)
                    .then().log().all()
                    .extract().response();

            JsonPath jsonPathControlSubjects = responseControlSubject.jsonPath();

            String dataControlSubjects = jsonPathControlSubjects.getString("data.id");

            if (dataControlSubjects.contains("83")){
                System.out.println("Control subject with id = 83 already existed!");
            } else {
                System.out.println("Control subject with id = 83 doesn't exist.");
                String requestBody = String.format(
                        "{\"control_subjects\":[{\"name\": \"%s\",\"fax_number\":\"%s\"," +
                                "\"deleted\":%s,\"provider_id\":\"%s\"}]}"
                        , "куку", "123", false, "525e9f767da3000002000001");
                Response responseControlSubjectCreate = given()
                        .when()
                        .header("Content-Type", "application/json")
                        .header("Test-Authorization", 1)
                        .body(requestBody)
                        .put(API_CONTROL_SUBJECTS)
                        .then().log().all()
                        .extract().response();

                JsonPath jsonPathControlSubjectsCreate = responseControlSubjectCreate.jsonPath();

                System.out.println("Control subjects with 83 was created!");
            }
            // Check that document execution state with id 11 is existed
            Response responseDocumentExecutionState = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", 1)
                    .get(API_DOCUMENT_EXECUTION_STATES)
                    .then().log().all()
                    .extract().response();

            JsonPath jsonPathDocumentExecutionState = responseDocumentExecutionState.jsonPath();

            String dataDocumentExecutionState = jsonPathDocumentExecutionState.getString("data.id");

            if (dataDocumentExecutionState.contains("11")){
                System.out.println("Document execution state with id = 11 already existed!");
            } else {
                System.out.println("Document execution state with id = 11 isn't exist.");
                Map<String, String> requestBody = new HashMap<>();
                requestBody.put("name", "ПРА12");
                requestBody.put("short_name", "");
                requestBody.put("excluded", "false");
                Response responseDocumentExecutionStateCreate = given()
                        .when()
                        .header("Content-Type", "application/json")
                        .header("Test-Authorization", 1)
                        .body(requestBody)
                        .post(API_DOCUMENT_EXECUTION_STATES)
                        .then().log().all()
                        .extract().response();

                JsonPath jsonPathDocumentExecutionStateCreate = responseDocumentExecutionStateCreate.jsonPath();
            }

            // Check that assignment execution state with id = 47 is existed
            Response responseAssignmentExecutionState = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", 1)
                    .get(API_ASSIGNMENT_EXECUTION_STATES)
                    .then().log().all()
                    .extract().response();

            JsonPath jsonPathAssignmentExecutionState = responseAssignmentExecutionState.jsonPath();

            String dataAssignmentExecutionState = jsonPathAssignmentExecutionState.getString("data.id");

            if (dataAssignmentExecutionState.contains("47")){
                System.out.println("Assignment execution state with id = 47 already existed!");
            } else {
                System.out.println("Assignment execution state with id = 47 isn't exist.");
                Map<String, String> requestBody = new HashMap<>();
                requestBody.put("name", "vdsvdjs");
                requestBody.put("short_name", "");
                requestBody.put("excluded", "false");
                Response responseDocumentExecutionStateCreate = given()
                        .when()
                        .header("Content-Type", "application/json")
                        .header("Test-Authorization", 1)
                        .body(requestBody)
                        .post(API_ASSIGNMENT_EXECUTION_STATES)
                        .then().log().all()
                        .extract().response();

                JsonPath jsonPathDocumentExecutionStateCreate = responseDocumentExecutionStateCreate.jsonPath();
            }
            // Check that document type with id 32 is existed
            Response responseDocumentType = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", 1)
                    .get(API_DOC_TYPE)
                    .then().log().all()
                    .extract().response();

            JsonPath jsonPathDocumentType = responseDocumentType.jsonPath();

            List<Integer> idDocTypes = jsonPathDocumentType.getList("data.id");
            int elementToCheck = 32;
            boolean isElementInArray = false;

            for (int i=0; i < idDocTypes.size(); i++){
                if (idDocTypes.get(i) == elementToCheck){
                    isElementInArray = true;
                    break;
                }
            }

            if (isElementInArray) {
                System.out.println("Document type with id = 32 already existed!");
            } else {
                System.out.println("Document type with id = 32 doesn't existed!");
                Map<String,Object> requestBody = new HashMap<>();
                requestBody.put("name","пвыпвы");
                requestBody.put("short_name","пвы");
                requestBody.put("internal", false);
                requestBody.put("genitive_name","");
                requestBody.put("excluded", true);
                Response responseDocumentTypeCreate = given()
                        .when()
                        .header("Content-Type", "application/json")
                        .header("Test-Authorization", 1)
                        .body(requestBody)
                        .post(API_DOC_TYPE)
                        .then().log().all()
                        .extract().response();

                JsonPath jsonPathDocumentTypeCreate = responseDocumentTypeCreate.jsonPath();
            }
            // Check that themes with id 4,5 is existed
            Response responseThemes = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", 1)
                    .get(API_THEMES)
                    .then().log().all()
                    .extract().response();

            JsonPath jsonPathThemes = responseThemes.jsonPath();

            List<Integer> idThemes = jsonPathThemes.getList("data.id");

            if (idThemes.contains(4) && idThemes.contains(5)){
                System.out.println("Elements in the list!");
            } else {
                System.out.println("Elements isn't in the list!");
                // Create themes with id = 4
                Map<String,Object> requestBodyFour = new HashMap<>();
                requestBodyFour.put("name","я");
                requestBodyFour.put("excluded", false);
                Response responseThemesCreateFour = given()
                        .when()
                        .header("Content-Type", "application/json")
                        .header("Test-Authorization", 1)
                        .body(requestBodyFour)
                        .post(API_THEMES)
                        .then().log().all()
                        .extract().response();

                JsonPath jsonPathThemesCreateFour = responseThemesCreateFour.jsonPath();
                // Create themes with id = 5
                Map<String,Object> requestBodyFive = new HashMap<>();
                requestBodyFive.put("name","У");
                requestBodyFive.put("excluded", false);
                Response responseThemesCreateFive = given()
                        .when()
                        .header("Content-Type", "application/json")
                        .header("Test-Authorization", 1)
                        .body(requestBodyFive)
                        .post(API_THEMES)
                        .then().log().all()
                        .extract().response();

                JsonPath jsonPathThemesCreateFive = responseThemesCreateFive.jsonPath();
            }
            // Check that deadline base with id 88 is existed
            Response responseGetListDeadlineBase = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", 1)
                    .get(API_DEADLINE_BASES)
                    .then().log().all()
                    .extract().response();

            JsonPath jsonPathGetListDeadlineBase = responseGetListDeadlineBase.jsonPath();

            List<Integer> idDeadlineBases = jsonPathGetListDeadlineBase.getList("data.id");
            int elementToFindDeadlineBases = 88;
            boolean isElementInArrayDeadlineBases = false;

            for (int i = 0; i < idDeadlineBases.size(); i++) {
                if (idDeadlineBases.get(i) == elementToFindDeadlineBases) {
                    isElementInArrayDeadlineBases = true;
                    break;
                }
            }

            if (isElementInArrayDeadlineBases) {
                System.out.println("Element with id = 88 is existed!");
            } else {
                System.out.println("Element with id = 88 doesn't exist! You should create this element!");
                Map<String, Object> requestBody = new HashMap<>();
                requestBody.put("name", "SKIP_138 test");
                requestBody.put("short_name", "skip138");
                requestBody.put("note", "for auto test");
                requestBody.put("duration_id", "3_days");
                requestBody.put("deadline_only", true);
                requestBody.put("excluded", true);
                Response responseCreateDeadlineBase = given()
                        .when()
                        .header("Content-Type", "application/json")
                        .header("Test-Authorization", 1)
                        .body(requestBody)
                        .post(API_DEADLINE_BASES)
                        .then().log().all()
                        .extract().response();

                JsonPath jsonPathCreateDeadlineBase = responseCreateDeadlineBase.jsonPath();
            }

            // Check that periodicity with id 38 is existed
            Response responseGetListPeriodicity = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", 1)
                    .get(API_PERIODICITY)
                    .then().log().all()
                    .extract().response();

            JsonPath jsonPathGetListPeriodicity = responseGetListPeriodicity.jsonPath();

            List<Integer> idPeriodicity = jsonPathGetListPeriodicity.getList("data.id");
            int elementToFindPeriodicity = 38;
            boolean isElementInArrayPeriodicity = false;

            for (int i = 0; i < idPeriodicity.size(); i++) {
                if (idPeriodicity.get(i) == elementToFindPeriodicity) {
                    isElementInArrayPeriodicity = true;
                    break;
                }
            }

            if (isElementInArrayPeriodicity) {
                System.out.println("Element with id = 32 is existed!");
            } else {
                System.out.println("Element with id = 32 doesn't exist! You should create this element!");
                Map<String, Object> requestBody = new HashMap<>();
                requestBody.put("name", "аyitв");
                requestBody.put("excluded", true);
                Response responseCreatePeriodicity = given()
                        .when()
                        .header("Content-Type", "application/json")
                        .header("Test-Authorization", 1)
                        .body(requestBody)
                        .post(API_PERIODICITY)
                        .then().log().all()
                        .extract().response();

                JsonPath jsonPathCreatePeriodicity = responseCreatePeriodicity.jsonPath();
            }

        }
    }

    @Test
    public void step01(){
        String requestBody = "{\"skip_reg_date\":\"2024-01-17T06:06:31.485Z\"," +
                "\"control_official_id\":\"54e35f6179fa007422000001\"," +
                "\"control_subject_id\":83," +
                "\"document_name\":\"Тест 404\"," +
                "\"document_execution_state_id\":11," +
                "\"skip_document_type_id\":32," +
                "\"internal\":false," +
                "\"short_description\":\"404\"," +
                "\"assignments\":[" +
                                "{\"number\":\"1\"," +
                                "\"content\":\"content\"," +
                                "\"note\":\"note\"," +
                                "\"theme_ids\":[5,4]}]}";
        // body - new File("src/test/java/SKIP_API/json_files/SKIP_138/SKIP_138_step01.json"
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBody)
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String headExecutor = jsonPath.get("data.assignments[0].head_executor");
        String skipRegDate = jsonPath.getString("data.skip_reg_date");
        String controlOfficialId = jsonPath.getString("data.control_official.id");
        String controlSubjectId = jsonPath.getString("data.control_subject.id");
        String documentName = jsonPath.getString("data.document_name");
        String documentExecutionStateId = jsonPath.getString("data.document_execution_state.id");
        String documentTypeId = jsonPath.getString("data.skip_document_type.id");
        String internal = jsonPath.getString("data.internal");
        String shortDescription = jsonPath.getString("data.short_description");
        String assignmentsNumberFirstEl = jsonPath.getString("data.assignments[0].number");
        String assignmentsContentFirstEl = jsonPath.getString("data.assignments[0].content");
        String assignmentsNoteFirstEl = jsonPath.getString("data.assignments[0].note");
        String assignmentsFirstThemeIdFirstEl = jsonPath.getString("data.assignments[0].themes[0].id");
        String assignmentsSecondThemeIdFirstEl = jsonPath.getString("data.assignments[0].themes[1].id");

        System.out.println("Head executor is " + headExecutor);
        System.out.println("Skip reg date - " + skipRegDate);
        System.out.println("Control official id - " + controlOfficialId);
        System.out.println("Control subject id - " + controlSubjectId);
        System.out.println("Document name - " + documentName);
        System.out.println("Document execution state id - " + documentExecutionStateId);
        System.out.println("Document type - " + documentTypeId);
        System.out.println("Internal - " + internal);
        System.out.println("Short description - " + shortDescription);
        System.out.println("Assignment number first element - " + assignmentsNumberFirstEl);
        System.out.println("Assignment content first element - " + assignmentsContentFirstEl);
        System.out.println("Assignment note first element - " + assignmentsNoteFirstEl);
        System.out.println("Assignment first theme first element - " + assignmentsFirstThemeIdFirstEl);
        System.out.println("Assignment second theme first element - " + assignmentsSecondThemeIdFirstEl);

        Assert.assertNull(headExecutor);
        Assert.assertTrue(skipRegDate.equals("2024-01-17T06:06:31.485Z"));
        Assert.assertTrue(controlOfficialId.equals("54e35f6179fa007422000001"));
        Assert.assertTrue(controlSubjectId.equals("83"));
        Assert.assertTrue(documentName.equals("Тест 404"));
        Assert.assertTrue(documentExecutionStateId.equals("11"));
        Assert.assertTrue(documentTypeId.equals("32"));
        Assert.assertTrue(internal.equals("false"));
        Assert.assertTrue(shortDescription.equals("404"));
        Assert.assertTrue(assignmentsNumberFirstEl.equals("1"));
        Assert.assertTrue(assignmentsContentFirstEl.equals("content"));
        Assert.assertTrue(assignmentsNoteFirstEl.equals("note"));
        Assert.assertTrue(assignmentsFirstThemeIdFirstEl.equals("4"));
        Assert.assertTrue(assignmentsSecondThemeIdFirstEl.equals("5"));
    }

    @Test
    public void step02(){
        String requestBody = "{\"skip_reg_date\":\"2024-01-17T06:06:31.485Z\"," +
                "\"control_official_id\":\"54e35f6179fa007422000001\"," +
                "\"control_subject_id\":83," +
                "\"document_name\":\"Тест 404\"," +
                "\"document_execution_state_id\":11," +
                "\"skip_document_type_id\":32," +
                "\"internal\":false," +
                "\"short_description\":\"404\"," +
                "\"assignments\":[" +
                                "{\"number\":\"1\"," +
                                "\"content\":\"content\"," +
                                "\"note\":\"note\"," +
                                "\"theme_ids\":[5,4]," +
                                "\"executors\":[{\"organization_id\":\"6426a7c3379100001e000001\"}]," +
                                "\"head_executor\":{\"head_executor_id\":\"629dad05d5bf00000b000001\"," +
                                                    "\"deadline_basis_id\":88," +
                                                    "\"periodicity_id\":38," +
                                                    "\"execution_state_id\":47," +
                                                    "\"start_date\":\"2024-01-18T08:17:01.707Z\"," +
                                                    "\"deadline_date\":\"2024-01-18T08:17:01.707Z\"," +
                                                    "\"period_in_days\":5," +
                                                    "\"note\":\"note\"}}" +
                "]}";
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBody)
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        idDocumentToRemember = jsonPath.get("data.id");
        idAssignmentToRemember = jsonPath.get("data.assignments[0].id");
        idHeadExecutor = jsonPath.get("data.assignments[0].head_executor.id");
        String skipRegDate = jsonPath.getString("data.skip_reg_date");
        String controlOfficialId = jsonPath.getString("data.control_official.id");
        String controlSubjectId = jsonPath.getString("data.control_subject.id");
        String documentName = jsonPath.getString("data.document_name");
        String documentExecutionStateId = jsonPath.getString("data.document_execution_state.id");
        String documentTypeId = jsonPath.getString("data.skip_document_type.id");
        String internal = jsonPath.getString("data.internal");
        String shortDescription = jsonPath.getString("data.short_description");
        String assignmentsNumberFirstEl = jsonPath.getString("data.assignments[0].number");
        String assignmentsContentFirstEl = jsonPath.getString("data.assignments[0].content");
        String assignmentsNoteFirstEl = jsonPath.getString("data.assignments[0].note");
        String assignmentsFirstThemeIdFirstEl = jsonPath.getString("data.assignments[0].themes[0].id");
        String assignmentsSecondThemeIdFirstEl = jsonPath.getString("data.assignments[0].themes[1].id");
        String assignmentsExecutorsOrganizationId = jsonPath.
                getString("data.assignments[0].executors[0].organization.id");
        String assignmentsHeadExecutorHeadExecutorId = jsonPath.
                getString("data.assignments[0].head_executor.head_executor.id");
        String assignmentsHeadExecutorDeadlineBasisId = jsonPath.
                getString("data.assignments[0].head_executor.deadline_basis.id");
        String assignmentsHeadExecutorPeriodicityId = jsonPath.
                getString("data.assignments[0].head_executor.periodicity.id");
        String assignmentsHeadExecutorExecutionStateId = jsonPath.
                getString("data.assignments[0].head_executor.execution_state.id");
        String assignmentsHeadExecutorStartDate = jsonPath.
                getString("data.assignments[0].head_executor.start_date");
        String assignmentsHeadExecutorDeadlineDate = jsonPath.
                getString("data.assignments[0].head_executor.deadline_date");
        String assignmentsHeadExecutorPeriodInDays = jsonPath.
                getString("data.assignments[0].head_executor.period_in_days");
        String assignmentsHeadExecutorNote = jsonPath.
                getString("data.assignments[0].head_executor.note");


        System.out.println("Id created element - " + idDocumentToRemember);
        System.out.println("Id created assignment - " + idAssignmentToRemember);
        System.out.println("Id created head executor - " + idHeadExecutor);
        System.out.println("Skip reg date - " + skipRegDate);
        System.out.println("Control official id - " + controlOfficialId);
        System.out.println("Control subject id - " + controlSubjectId);
        System.out.println("Document name - " + documentName);
        System.out.println("Document execution state id - " + documentExecutionStateId);
        System.out.println("Document type - " + documentTypeId);
        System.out.println("Internal - " + internal);
        System.out.println("Short description - " + shortDescription);
        System.out.println("Assignment number first element - " + assignmentsNumberFirstEl);
        System.out.println("Assignment content first element - " + assignmentsContentFirstEl);
        System.out.println("Assignment note first element - " + assignmentsNoteFirstEl);
        System.out.println("Assignment first theme first element - " + assignmentsFirstThemeIdFirstEl);
        System.out.println("Assignment second theme first element - " + assignmentsSecondThemeIdFirstEl);
        System.out.println("Executors organization id - " + assignmentsExecutorsOrganizationId);
        System.out.println("Head executor head executor id - " + assignmentsHeadExecutorHeadExecutorId);
        System.out.println("Head executor deadline basis id - " + assignmentsHeadExecutorDeadlineBasisId);
        System.out.println("Head executor periodicity id - " + assignmentsHeadExecutorPeriodicityId);
        System.out.println("Head executor execution state id - " + assignmentsHeadExecutorExecutionStateId);
        System.out.println("Head executor start date id - " + assignmentsHeadExecutorStartDate);
        System.out.println("Head executor deadline date id - " + assignmentsHeadExecutorDeadlineDate);
        System.out.println("Head executor period in days id - " + assignmentsHeadExecutorPeriodInDays);
        System.out.println("Head executor note id - " + assignmentsHeadExecutorNote);

        Assert.assertTrue(skipRegDate.equals("2024-01-17T06:06:31.485Z"));
        Assert.assertTrue(controlOfficialId.equals("54e35f6179fa007422000001"));
        Assert.assertTrue(controlSubjectId.equals("83"));
        Assert.assertTrue(documentName.equals("Тест 404"));
        Assert.assertTrue(documentExecutionStateId.equals("11"));
        Assert.assertTrue(documentTypeId.equals("32"));
        Assert.assertTrue(internal.equals("false"));
        Assert.assertTrue(shortDescription.equals("404"));
        Assert.assertTrue(assignmentsNumberFirstEl.equals("1"));
        Assert.assertTrue(assignmentsContentFirstEl.equals("content"));
        Assert.assertTrue(assignmentsNoteFirstEl.equals("note"));
        Assert.assertTrue(assignmentsFirstThemeIdFirstEl.equals("4"));
        Assert.assertTrue(assignmentsSecondThemeIdFirstEl.equals("5"));
        Assert.assertTrue(assignmentsExecutorsOrganizationId.equals("6426a7c3379100001e000001"));
        Assert.assertTrue(assignmentsHeadExecutorHeadExecutorId.equals("629dad05d5bf00000b000001"));
        Assert.assertTrue(assignmentsHeadExecutorDeadlineBasisId.equals("88"));
        Assert.assertTrue(assignmentsHeadExecutorPeriodicityId.equals("38"));
        Assert.assertTrue(assignmentsHeadExecutorExecutionStateId.equals("47"));
        Assert.assertTrue(assignmentsHeadExecutorStartDate.equals("2024-01-18T08:17:01.707Z"));
        Assert.assertTrue(assignmentsHeadExecutorDeadlineDate.equals("2024-01-18T08:17:01.707Z"));
        Assert.assertTrue(assignmentsHeadExecutorPeriodInDays.equals("5"));
        Assert.assertTrue(assignmentsHeadExecutorNote.equals("note"));
    }

    @Test
    public void step03(){
        String requestBody = "{\"skip_reg_date\":\"2024-01-17T06:06:31.485Z\"," +
                "\"control_official_id\":\"54e35f6179fa007422000001\"," +
                "\"control_subject_id\":83," +
                "\"document_name\":\"Тест 404\"," +
                "\"document_execution_state_id\":11," +
                "\"skip_document_type_id\":32," +
                "\"internal\":false," +
                "\"short_description\":\"404\"," +
                "\"assignments\":[" +
                "{\"number\":\"1\"," +
                "\"content\":\"content\"," +
                "\"note\":\"note\"," +
                "\"theme_ids\":[5,4]," +
                "\"executors\":[{\"organization_id\":\"6426a7c3379100001e000001\"}]," +
                "\"head_executor\":{\"head_executor_id\":\"\"," + // empty string
                "\"deadline_basis_id\":88," +
                "\"periodicity_id\":38," +
                "\"execution_state_id\":47," +
                "\"start_date\":\"2024-01-18T08:17:01.707Z\"," +
                "\"deadline_date\":\"2024-01-18T08:17:01.707Z\"," +
                "\"period_in_days\":5," +
                "\"note\":\"note\"}}]}";
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBody)
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.assignments.0.head_executor.head_executor_id[0]");

        System.out.println(errorMessage);

        Assert.assertTrue(errorMessage.equals("не может быть пустым"));
    }

    @Test
    public void step04(){
        String requestBody = "{\"skip_reg_date\":\"2024-01-17T06:06:31.485Z\"," +
                "\"control_official_id\":\"54e35f6179fa007422000001\"," +
                "\"control_subject_id\":83," +
                "\"document_name\":\"Тест 404\"," +
                "\"document_execution_state_id\":11," +
                "\"skip_document_type_id\":32," +
                "\"internal\":false," +
                "\"short_description\":\"404\"," +
                "\"assignments\":[" +
                "{\"number\":\"1\"," +
                "\"content\":\"content\"," +
                "\"note\":\"note\"," +
                "\"theme_ids\":[5,4]," +
                "\"executors\":[{\"organization_id\":\"6426a7c3379100001e000001\"}]," +
                "\"head_executor\":{\"head_executor_id\":\"123\"," +
                "\"deadline_basis_id\":88," +
                "\"periodicity_id\":38," +
                "\"execution_state_id\":47," +
                "\"start_date\":\"2024-01-18T08:17:01.707Z\"," +
                "\"deadline_date\":\"2024-01-18T08:17:01.707Z\"," +
                "\"period_in_days\":5," +
                "\"note\":\"note\"}}]}";
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBody)
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.assignments.0.head_executor.head_executor_id[0]");

        System.out.println(errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step05(){
        String requestBody = "{\"skip_reg_date\":\"2024-01-17T06:06:31.485Z\"," +
                "\"control_official_id\":\"54e35f6179fa007422000001\"," +
                "\"control_subject_id\":83," +
                "\"document_name\":\"Тест 404\"," +
                "\"document_execution_state_id\":11," +
                "\"skip_document_type_id\":32," +
                "\"internal\":false," +
                "\"short_description\":\"404\"," +
                "\"assignments\":[" +
                "{\"number\":\"1\"," +
                "\"content\":\"content\"," +
                "\"note\":\"note\"," +
                "\"theme_ids\":[5,4]," +
                "\"executors\":[{\"organization_id\":\"6426a7c3379100001e000001\"}]," +
                "\"head_executor\":{\"head_executor_id\":\"5b166903d4b100007b000001\"," +
                "\"periodicity_id\":38," +
                "\"execution_state_id\":47," +
                "\"start_date\":\"2024-01-18T08:17:01.707Z\"," +
                "\"deadline_date\":\"2024-01-18T08:17:01.707Z\"," +
                "\"period_in_days\":5," +
                "\"note\":\"note\"}}]}";
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBody)
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.assignments.0.head_executor.deadline_basis_id[0]");

        System.out.println(errorMessage);

        Assert.assertTrue(errorMessage.equals("не может быть пустым"));
    }

    @Test
    public void step06(){
        String requestBody = "{\"skip_reg_date\":\"2024-01-17T06:06:31.485Z\"," +
                "\"control_official_id\":\"54e35f6179fa007422000001\"," +
                "\"control_subject_id\":83," +
                "\"document_name\":\"Тест 404\"," +
                "\"document_execution_state_id\":11," +
                "\"skip_document_type_id\":32," +
                "\"internal\":false," +
                "\"short_description\":\"404\"," +
                "\"assignments\":[" +
                "{\"number\":\"1\"," +
                "\"content\":\"content\"," +
                "\"note\":\"note\"," +
                "\"theme_ids\":[5,4]," +
                "\"executors\":[{\"organization_id\":\"6426a7c3379100001e000001\"}]," +
                "\"head_executor\":{\"head_executor_id\":\"5b166903d4b100007b000001\"," +
                "\"deadline_basis_id\":0," +
                "\"periodicity_id\":38," +
                "\"execution_state_id\":47," +
                "\"start_date\":\"2024-01-18T08:17:01.707Z\"," +
                "\"deadline_date\":\"2024-01-18T08:17:01.707Z\"," +
                "\"period_in_days\":5," +
                "\"note\":\"note\"}}]}";
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBody)
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.assignments.0.head_executor.deadline_basis_id[0]");

        System.out.println(errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step07(){
        String requestBody = "{\"skip_reg_date\":\"2024-01-17T06:06:31.485Z\"," +
                "\"control_official_id\":\"54e35f6179fa007422000001\"," +
                "\"control_subject_id\":83," +
                "\"document_name\":\"Тест 404\"," +
                "\"document_execution_state_id\":11," +
                "\"skip_document_type_id\":32," +
                "\"internal\":false," +
                "\"short_description\":\"404\"," +
                "\"assignments\":[" +
                "{\"number\":\"1\"," +
                "\"content\":\"content\"," +
                "\"note\":\"note\"," +
                "\"theme_ids\":[5,4]," +
                "\"executors\":[{\"organization_id\":\"6426a7c3379100001e000001\"}]," +
                "\"head_executor\":{\"head_executor_id\":\"5b166903d4b100007b000001\"," +
                "\"deadline_basis_id\":88," +
                "\"execution_state_id\":47," +
                "\"start_date\":\"2024-01-18T08:17:01.707Z\"," +
                "\"deadline_date\":\"2024-01-18T08:17:01.707Z\"," +
                "\"period_in_days\":5," +
                "\"note\":\"note\"}}]}";
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBody)
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.assignments.0.head_executor.periodicity_id[0]");

        System.out.println(errorMessage);

        Assert.assertTrue(errorMessage.equals("не может быть пустым"));
    }

    @Test
    public void step08(){
        String requestBody = "{\"skip_reg_date\":\"2024-01-17T06:06:31.485Z\"," +
                "\"control_official_id\":\"54e35f6179fa007422000001\"," +
                "\"control_subject_id\":83," +
                "\"document_name\":\"Тест 404\"," +
                "\"document_execution_state_id\":11," +
                "\"skip_document_type_id\":32," +
                "\"internal\":false," +
                "\"short_description\":\"404\"," +
                "\"assignments\":[" +
                "{\"number\":\"1\"," +
                "\"content\":\"content\"," +
                "\"note\":\"note\"," +
                "\"theme_ids\":[5,4]," +
                "\"executors\":[{\"organization_id\":\"6426a7c3379100001e000001\"}]," +
                "\"head_executor\":{\"head_executor_id\":\"5b166903d4b100007b000001\"," +
                "\"deadline_basis_id\":88," +
                "\"periodicity_id\":0," +
                "\"execution_state_id\":47," +
                "\"start_date\":\"2024-01-18T08:17:01.707Z\"," +
                "\"deadline_date\":\"2024-01-18T08:17:01.707Z\"," +
                "\"period_in_days\":5," +
                "\"note\":\"note\"}}]}";
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBody)
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.assignments.0.head_executor.periodicity_id[0]");

        System.out.println(errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step09(){
        String requestBody = "{\"skip_reg_date\":\"2024-01-17T06:06:31.485Z\"," +
                "\"control_official_id\":\"54e35f6179fa007422000001\"," +
                "\"control_subject_id\":83," +
                "\"document_name\":\"Тест 404\"," +
                "\"document_execution_state_id\":11," +
                "\"skip_document_type_id\":32," +
                "\"internal\":false," +
                "\"short_description\":\"404\"," +
                "\"assignments\":[" +
                "{\"number\":\"1\"," +
                "\"content\":\"content\"," +
                "\"note\":\"note\"," +
                "\"theme_ids\":[5,4]," +
                "\"executors\":[{\"organization_id\":\"6426a7c3379100001e000001\"}]," +
                "\"head_executor\":{\"head_executor_id\":\"5b166903d4b100007b000001\"," +
                "\"deadline_basis_id\":88," +
                "\"periodicity_id\":38," +
                "\"execution_state_id\":47," +
                "\"start_date\":\"\"," +
                "\"deadline_date\":\"2024-01-18T08:17:01.707Z\"," +
                "\"period_in_days\":5," +
                "\"note\":\"note\"}}]}";
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBody)
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.assignments.0.head_executor.start_date[0]");

        System.out.println(errorMessage);

        Assert.assertTrue(errorMessage.equals("не может быть пустым"));
    }

    @Test
    public void step10(){
        String requestBody = "{\"skip_reg_date\":\"2024-01-17T06:06:31.485Z\"," +
                "\"control_official_id\":\"54e35f6179fa007422000001\"," +
                "\"control_subject_id\":83," +
                "\"document_name\":\"Тест 404\"," +
                "\"document_execution_state_id\":11," +
                "\"skip_document_type_id\":32," +
                "\"internal\":false," +
                "\"short_description\":\"404\"," +
                "\"assignments\":[" +
                "{\"number\":\"1\"," +
                "\"content\":\"content\"," +
                "\"note\":\"note\"," +
                "\"theme_ids\":[5,4]," +
                "\"executors\":[{\"organization_id\":\"6426a7c3379100001e000001\"}]," +
                "\"head_executor\":{\"head_executor_id\":\"5b166903d4b100007b000001\"," +
                "\"deadline_basis_id\":88," +
                "\"periodicity_id\":38," +
                "\"execution_state_id\":47," +
                "\"start_date\":\"123456\"," +
                "\"deadline_date\":\"2024-01-18T08:17:01.707Z\"," +
                "\"period_in_days\":5," +
                "\"note\":\"note\"}}]}";
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBody)
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.assignments.0.head_executor.start_date[0]");

        System.out.println(errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет неверное значение"));
    }

    @Test
    public void step11(){
        String requestBody = "{\"skip_reg_date\":\"2024-01-17T06:06:31.485Z\"," +
                "\"control_official_id\":\"54e35f6179fa007422000001\"," +
                "\"control_subject_id\":83," +
                "\"document_name\":\"Тест 404\"," +
                "\"document_execution_state_id\":11," +
                "\"skip_document_type_id\":32," +
                "\"internal\":false," +
                "\"short_description\":\"404\"," +
                "\"assignments\":[" +
                "{\"number\":\"1\"," +
                "\"content\":\"content\"," +
                "\"note\":\"note\"," +
                "\"theme_ids\":[5,4]," +
                "\"executors\":[{\"organization_id\":\"6426a7c3379100001e000001\"}]," +
                "\"head_executor\":{\"head_executor_id\":\"5b166903d4b100007b000001\"," +
                "\"deadline_basis_id\":88," +
                "\"periodicity_id\":38," +
                "\"execution_state_id\":47," +
                "\"start_date\":\"2024-01-18T08:17:01.707Z\"," +
                "\"deadline_date\":\"2023-01-18T08:17:01.707Z\"," +
                "\"period_in_days\":5," +
                "\"note\":\"note\"}}]}";
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBody)
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.assignments.0.head_executor.deadline_date[0]");

        System.out.println(errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step12(){
        String requestBody = "{\"skip_reg_date\":\"2024-01-17T06:06:31.485Z\"," +
                "\"control_official_id\":\"54e35f6179fa007422000001\"," +
                "\"control_subject_id\":83," +
                "\"document_name\":\"Тест 404\"," +
                "\"document_execution_state_id\":11," +
                "\"skip_document_type_id\":32," +
                "\"internal\":false," +
                "\"short_description\":\"404\"," +
                "\"assignments\":[" +
                "{\"number\":\"1\"," +
                "\"content\":\"content\"," +
                "\"note\":\"note\"," +
                "\"theme_ids\":[5,4]," +
                "\"executors\":[{\"organization_id\":\"6426a7c3379100001e000001\"}]," +
                "\"head_executor\":{\"head_executor_id\":\"5b166903d4b100007b000001\"," +
                "\"deadline_basis_id\":88," +
                "\"periodicity_id\":38," +
                "\"execution_state_id\":47," +
                "\"start_date\":\"2024-01-18T08:17:01.707Z\"," +
                "\"deadline_date\":\"123456\"," +
                "\"period_in_days\":5," +
                "\"note\":\"note\"}}]}";
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBody)
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.assignments.0.head_executor.deadline_date[0]");

        System.out.println(errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет неверное значение"));
    }

    @Test
    public void step13(){
        String requestBody = "{\"skip_reg_date\":\"2024-01-17T06:06:31.485Z\"," +
                "\"control_official_id\":\"54e35f6179fa007422000001\"," +
                "\"control_subject_id\":83," +
                "\"document_name\":\"Тест 404\"," +
                "\"document_execution_state_id\":11," +
                "\"skip_document_type_id\":32," +
                "\"internal\":false," +
                "\"short_description\":\"404\"," +
                "\"assignments\":[" +
                "{\"number\":\"1\"," +
                "\"content\":\"content\"," +
                "\"note\":\"note\"," +
                "\"theme_ids\":[5,4]," +
                "\"executors\":[{\"organization_id\":\"6426a7c3379100001e000001\"}]," +
                "\"head_executor\":{\"head_executor_id\":\"5b166903d4b100007b000001\"," +
                "\"deadline_basis_id\":88," +
                "\"periodicity_id\":38," +
                "\"start_date\":\"2024-01-18T08:17:01.707Z\"," +
                "\"deadline_date\":\"2024-01-18T08:17:01.707Z\"," +
                "\"period_in_days\":5," +
                "\"note\":\"note\"}}]}";
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBody)
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.assignments.0.head_executor.execution_state_id[0]");

        System.out.println(errorMessage);

        Assert.assertTrue(errorMessage.equals("не может быть пустым"));
    }

    @Test
    public void step14(){
        String requestBody = "{\"skip_reg_date\":\"2024-01-17T06:06:31.485Z\"," +
                "\"control_official_id\":\"54e35f6179fa007422000001\"," +
                "\"control_subject_id\":83," +
                "\"document_name\":\"Тест 404\"," +
                "\"document_execution_state_id\":11," +
                "\"skip_document_type_id\":32," +
                "\"internal\":false," +
                "\"short_description\":\"404\"," +
                "\"assignments\":[" +
                "{\"number\":\"1\"," +
                "\"content\":\"content\"," +
                "\"note\":\"note\"," +
                "\"theme_ids\":[5,4]," +
                "\"executors\":[{\"organization_id\":\"6426a7c3379100001e000001\"}]," +
                "\"head_executor\":{\"head_executor_id\":\"5b166903d4b100007b000001\"," +
                "\"deadline_basis_id\":88," +
                "\"periodicity_id\":38," +
                "\"execution_state_id\":0," +
                "\"start_date\":\"2024-01-18T08:17:01.707Z\"," +
                "\"deadline_date\":\"2024-01-18T08:17:01.707Z\"," +
                "\"period_in_days\":5," +
                "\"note\":\"note\"}}]}";
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBody)
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.assignments.0.head_executor.execution_state_id[0]");

        System.out.println(errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step15(){
        String requestBody = "{\"skip_reg_date\":\"2024-01-17T06:06:31.485Z\"," +
                "\"control_official_id\":\"54e35f6179fa007422000001\"," +
                "\"control_subject_id\":83," +
                "\"document_name\":\"Тест 404\"," +
                "\"document_execution_state_id\":11," +
                "\"skip_document_type_id\":32," +
                "\"internal\":false," +
                "\"short_description\":\"404\"," +
                "\"assignments\":[" +
                "{\"number\":\"1\"," +
                "\"content\":\"content\"," +
                "\"note\":\"note\"," +
                "\"theme_ids\":[5,4]," +
                "\"executors\":[{\"organization_id\":\"6426a7c3379100001e000001\"}]," +
                "\"head_executor\":{\"head_executor_id\":\"5b166903d4b100007b000001\"," +
                "\"deadline_basis_id\":88," +
                "\"periodicity_id\":45," +
                "\"execution_state_id\":47," +
                "\"start_date\":\"2024-01-18T08:17:01.707Z\"," +
                "\"deadline_date\":\"2024-01-18T08:17:01.707Z\"," +
                "\"period_in_days\":0," +
                "\"note\":\"note\"}}]}";
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBody)
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.assignments.0.head_executor.period_in_days[0]");

        System.out.println(errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step16(){
        String requestBody = "{\"skip_reg_date\":\"2024-01-17T06:06:31.485Z\"," +
                "\"control_official_id\":\"54e35f6179fa007422000001\"," +
                "\"control_subject_id\":83," +
                "\"document_name\":\"Тест 404\"," +
                "\"document_execution_state_id\":11," +
                "\"skip_document_type_id\":32," +
                "\"internal\":false," +
                "\"short_description\":\"404\"," +
                "\"assignments\":[" +
                "{\"number\":\"1\"," +
                "\"content\":\"content\"," +
                "\"note\":\"note\"," +
                "\"theme_ids\":[5,4]," +
                "\"executors\":[{\"organization_id\":\"6426a7c3379100001e000001\"}]," +
                "\"head_executor\":{\"head_executor_id\":\"5b166903d4b100007b000001\"," +
                "\"deadline_basis_id\":88," +
                "\"periodicity_id\":45," +
                "\"execution_state_id\":47," +
                "\"start_date\":\"2024-01-18T08:17:01.707Z\"," +
                "\"deadline_date\":\"2024-01-18T08:17:01.707Z\"," +
                "\"note\":\"note\"}}]}";
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBody)
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.assignments.0.head_executor.period_in_days[0]");

        System.out.println(errorMessage);

        Assert.assertTrue(errorMessage.equals("не может быть пустым"));
    }

    @Test
    public void step17(){
        String requestBody = "{\"skip_reg_date\":\"2024-01-17T06:06:31.485Z\"," +
                "\"control_official_id\":\"54e35f6179fa007422000001\"," +
                "\"control_subject_id\":83," +
                "\"document_name\":\"Тест 404\"," +
                "\"document_execution_state_id\":11," +
                "\"skip_document_type_id\":32," +
                "\"internal\":false," +
                "\"short_description\":\"404\"," +
                "\"assignments\":[" +
                "{\"number\":\"1\"," +
                "\"content\":\"content\"," +
                "\"note\":\"note\"," +
                "\"theme_ids\":[5,4]," +
                "\"executors\":[{\"organization_id\":\"6426a7c3379100001e000001\"}]," +
                "\"head_executor\":{\"head_executor_id\":\"5b166903d4b100007b000001\"," +
                "\"deadline_basis_id\":88," +
                "\"periodicity_id\":45," +
                "\"execution_state_id\":47," +
                "\"start_date\":\"2024-01-18T08:17:01.707Z\"," +
                "\"deadline_date\":\"2024-01-18T08:17:01.707Z\"," +
                "\"note\":\"note\"}}]}";
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 6)
                .body(requestBody)
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("error");

        System.out.println(errorMessage);

        Assert.assertTrue(errorMessage.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void step18(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .get(API_DOCUMENTS + String.format("/%s", idDocumentToRemember))
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String skipRegDate = jsonPath.getString("data.skip_reg_date");
        String controlOfficialId = jsonPath.getString("data.control_official.id");
        String controlSubjectId = jsonPath.getString("data.control_subject.id");
        String documentName = jsonPath.getString("data.document_name");
        String documentExecutionStateId = jsonPath.getString("data.document_execution_state.id");
        String documentTypeId = jsonPath.getString("data.skip_document_type.id");
        String internal = jsonPath.getString("data.internal");
        String shortDescription = jsonPath.getString("data.short_description");
        String assignmentsNumberFirstEl = jsonPath.getString("data.assignments[0].number");
        String assignmentsContentFirstEl = jsonPath.getString("data.assignments[0].content");
        String assignmentsNoteFirstEl = jsonPath.getString("data.assignments[0].note");
        String assignmentsFirstThemeIdFirstEl = jsonPath.getString("data.assignments[0].themes[0].id");
        String assignmentsSecondThemeIdFirstEl = jsonPath.getString("data.assignments[0].themes[1].id");
        String assignmentsExecutorsOrganizationId = jsonPath.
                getString("data.assignments[0].executors[0].organization.id");
        String assignmentsHeadExecutorHeadExecutorId = jsonPath.
                getString("data.assignments[0].head_executor.head_executor.id");
        String assignmentsHeadExecutorDeadlineBasisId = jsonPath.
                getString("data.assignments[0].head_executor.deadline_basis.id");
        String assignmentsHeadExecutorPeriodicityId = jsonPath.
                getString("data.assignments[0].head_executor.periodicity.id");
        String assignmentsHeadExecutorExecutionStateId = jsonPath.
                getString("data.assignments[0].head_executor.execution_state.id");
        String assignmentsHeadExecutorStartDate = jsonPath.
                getString("data.assignments[0].head_executor.start_date");
        String assignmentsHeadExecutorDeadlineDate = jsonPath.
                getString("data.assignments[0].head_executor.deadline_date");
        String assignmentsHeadExecutorPeriodInDays = jsonPath.
                getString("data.assignments[0].head_executor.period_in_days");
        String assignmentsHeadExecutorNote = jsonPath.
                getString("data.assignments[0].head_executor.note");

        System.out.println("Skip reg date - " + skipRegDate);
        System.out.println("Control official id - " + controlOfficialId);
        System.out.println("Control subject id - " + controlSubjectId);
        System.out.println("Document name - " + documentName);
        System.out.println("Document execution state id - " + documentExecutionStateId);
        System.out.println("Document type - " + documentTypeId);
        System.out.println("Internal - " + internal);
        System.out.println("Short description - " + shortDescription);
        System.out.println("Assignment number first element - " + assignmentsNumberFirstEl);
        System.out.println("Assignment content first element - " + assignmentsContentFirstEl);
        System.out.println("Assignment note first element - " + assignmentsNoteFirstEl);
        System.out.println("Assignment first theme first element - " + assignmentsFirstThemeIdFirstEl);
        System.out.println("Assignment second theme first element - " + assignmentsSecondThemeIdFirstEl);
        System.out.println("Executors organization id - " + assignmentsExecutorsOrganizationId);
        System.out.println("Head executor head executor id - " + assignmentsHeadExecutorHeadExecutorId);
        System.out.println("Head executor deadline basis id - " + assignmentsHeadExecutorDeadlineBasisId);
        System.out.println("Head executor periodicity id - " + assignmentsHeadExecutorPeriodicityId);
        System.out.println("Head executor execution state id - " + assignmentsHeadExecutorExecutionStateId);
        System.out.println("Head executor start date id - " + assignmentsHeadExecutorStartDate);
        System.out.println("Head executor deadline date id - " + assignmentsHeadExecutorDeadlineDate);
        System.out.println("Head executor period in days id - " + assignmentsHeadExecutorPeriodInDays);
        System.out.println("Head executor note id - " + assignmentsHeadExecutorNote);

        Assert.assertTrue(skipRegDate.equals("2024-01-17T06:06:31.485Z"));
        Assert.assertTrue(controlOfficialId.equals("54e35f6179fa007422000001"));
        Assert.assertTrue(controlSubjectId.equals("83"));
        Assert.assertTrue(documentName.equals("Тест 404"));
        Assert.assertTrue(documentExecutionStateId.equals("11"));
        Assert.assertTrue(documentTypeId.equals("32"));
        Assert.assertTrue(internal.equals("false"));
        Assert.assertTrue(shortDescription.equals("404"));
        Assert.assertTrue(assignmentsNumberFirstEl.equals("1"));
        Assert.assertTrue(assignmentsContentFirstEl.equals("content"));
        Assert.assertTrue(assignmentsNoteFirstEl.equals("note"));
        Assert.assertTrue(assignmentsFirstThemeIdFirstEl.equals("4"));
        Assert.assertTrue(assignmentsSecondThemeIdFirstEl.equals("5"));
        Assert.assertTrue(assignmentsExecutorsOrganizationId.equals("6426a7c3379100001e000001"));
        Assert.assertTrue(assignmentsHeadExecutorHeadExecutorId.equals("629dad05d5bf00000b000001"));
        Assert.assertTrue(assignmentsHeadExecutorDeadlineBasisId.equals("88"));
        Assert.assertTrue(assignmentsHeadExecutorPeriodicityId.equals("38"));
        Assert.assertTrue(assignmentsHeadExecutorExecutionStateId.equals("47"));
        Assert.assertTrue(assignmentsHeadExecutorStartDate.equals("2024-01-18T08:17:01.707Z"));
        Assert.assertTrue(assignmentsHeadExecutorDeadlineDate.equals("2024-01-18T08:17:01.707Z"));
        Assert.assertTrue(assignmentsHeadExecutorPeriodInDays.equals("5"));
        Assert.assertTrue(assignmentsHeadExecutorNote.equals("note"));
    }

    @Test
    public void step19(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 6)
                .get(API_DOCUMENTS + String.format("/%s", idDocumentToRemember))
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("error");

        System.out.println(errorMessage);

        Assert.assertTrue(errorMessage.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void step20(){
        String requestBody = String.format("{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": %s,\n" +
                "            \"head_executor\": {\n" +
                "                \"id\": %s,\n" +
                "                \"head_executor_id\": \"5b166903d4b100007b000001\",\n" +
                "                \"deadline_basis_id\": 88,\n" +
                "                \"periodicity_id\": 38,\n" +
                "                \"execution_state_id\": 47,\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2026-02-05T10:56:02.433Z\"\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}", idAssignmentToRemember, idHeadExecutor);
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBody)
                .put(API_DOCUMENTS + String.format("/%s", idDocumentToRemember))
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String assignmentsExecutorsOrganizationId = jsonPath.
                getString("data.assignments[0].executors[0].organization.id");
        String assignmentsHeadExecutorHeadExecutorId = jsonPath.
                getString("data.assignments[0].head_executor.head_executor.id");
        String assignmentsHeadExecutorDeadlineBasisId = jsonPath.
                getString("data.assignments[0].head_executor.deadline_basis.id");
        String assignmentsHeadExecutorPeriodicityId = jsonPath.
                getString("data.assignments[0].head_executor.periodicity.id");
        String assignmentsHeadExecutorExecutionStateId = jsonPath.
                getString("data.assignments[0].head_executor.execution_state.id");
        String assignmentsHeadExecutorStartDate = jsonPath.
                getString("data.assignments[0].head_executor.start_date");
        String assignmentsHeadExecutorDeadlineDate = jsonPath.
                getString("data.assignments[0].head_executor.deadline_date");

        System.out.println("Executors organization id - " + assignmentsExecutorsOrganizationId);
        System.out.println("Head executor head executor id - " + assignmentsHeadExecutorHeadExecutorId);
        System.out.println("Head executor deadline basis id - " + assignmentsHeadExecutorDeadlineBasisId);
        System.out.println("Head executor periodicity id - " + assignmentsHeadExecutorPeriodicityId);
        System.out.println("Head executor execution state id - " + assignmentsHeadExecutorExecutionStateId);
        System.out.println("Head executor start date id - " + assignmentsHeadExecutorStartDate);
        System.out.println("Head executor deadline date id - " + assignmentsHeadExecutorDeadlineDate);

        Assert.assertTrue(assignmentsExecutorsOrganizationId.equals("6426a7c3379100001e000001"));
        Assert.assertTrue(assignmentsHeadExecutorHeadExecutorId.equals("5b166903d4b100007b000001"));
        Assert.assertTrue(assignmentsHeadExecutorDeadlineBasisId.equals("88"));
        Assert.assertTrue(assignmentsHeadExecutorPeriodicityId.equals("38"));
        Assert.assertTrue(assignmentsHeadExecutorExecutionStateId.equals("47"));
        Assert.assertTrue(assignmentsHeadExecutorStartDate.equals("2024-01-18T08:17:01.707Z"));
        Assert.assertTrue(assignmentsHeadExecutorDeadlineDate.equals("2026-02-05T10:56:02.433Z"));

    }

    @Test
    public void step21(){
        String requestBody = String.format("{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": %s,\n" +
                "            \"head_executor\": {\n" +
                "                \"id\": %s,\n" +
                "                \"head_executor_id\": \"\",\n" +
                "                \"deadline_basis_id\": 88,\n" +
                "                \"periodicity_id\": 38,\n" +
                "                \"execution_state_id\": 47,\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2026-02-05T10:56:02.433Z\"\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}", idAssignmentToRemember, idHeadExecutor);
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBody)
                .put(API_DOCUMENTS + String.format("/%s", idDocumentToRemember))
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.assignments.0.head_executor.head_executor_id[0]");

        System.out.println("Error message: " + errorMessage);

        Assert.assertTrue(errorMessage.equals("не может быть пустым"));
    }

    @Test
    public void step22(){
        String requestBody = String.format("{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": %s,\n" +
                "            \"head_executor\": {\n" +
                "                \"id\": %s,\n" +
                "                \"head_executor_id\": \"123\",\n" +
                "                \"deadline_basis_id\": 88,\n" +
                "                \"periodicity_id\": 38,\n" +
                "                \"execution_state_id\": 47,\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2026-02-05T10:56:02.433Z\"\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}", idAssignmentToRemember, idHeadExecutor);
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBody)
                .put(API_DOCUMENTS + String.format("/%s", idDocumentToRemember))
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.assignments.0.head_executor.head_executor_id[0]");

        System.out.println("Error message: " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
    }
    @Test
    public void step23(){
        String requestBody = String.format("{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": %s,\n" +
                "            \"head_executor\": {\n" +
                "                \"id\": %s,\n" +
                "                \"head_executor_id\": \"5b166903d4b100007b000001\",\n" +
                "                \"deadline_basis_id\": 0,\n" +
                "                \"periodicity_id\": 38,\n" +
                "                \"execution_state_id\": 47,\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2026-02-05T10:56:02.433Z\"\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}", idAssignmentToRemember, idHeadExecutor);
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBody)
                .put(API_DOCUMENTS + String.format("/%s", idDocumentToRemember))
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.assignments.0.head_executor.deadline_basis_id[0]");

        System.out.println("Error message: " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step24(){
        String requestBody = String.format("{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": %s,\n" +
                "            \"head_executor\": {\n" +
                "                \"id\": %s,\n" +
                "                \"head_executor_id\": \"5b166903d4b100007b000001\",\n" +
                "                \"deadline_basis_id\": 88,\n" +
                "                \"periodicity_id\": 0,\n" +
                "                \"execution_state_id\": 47,\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2026-02-05T10:56:02.433Z\"\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}", idAssignmentToRemember, idHeadExecutor);
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBody)
                .put(API_DOCUMENTS + String.format("/%s", idDocumentToRemember))
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.assignments.0.head_executor.periodicity_id[0]");

        System.out.println("Error message: " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step25(){
        String requestBody = String.format("{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": %s,\n" +
                "            \"head_executor\": {\n" +
                "                \"id\": %s,\n" +
                "                \"head_executor_id\": \"5b166903d4b100007b000001\",\n" +
                "                \"deadline_basis_id\": 88,\n" +
                "                \"periodicity_id\": 38,\n" +
                "                \"execution_state_id\": 47,\n" +
                "                \"start_date\": \"123456\",\n" +
                "                \"deadline_date\": \"2026-02-05T10:56:02.433Z\"\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}", idAssignmentToRemember, idHeadExecutor);
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBody)
                .put(API_DOCUMENTS + String.format("/%s", idDocumentToRemember))
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.assignments.0.head_executor.start_date[0]");

        System.out.println("Error message: " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет неверное значение"));
    }

    @Test
    public void step26(){
        String requestBody = String.format("{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": %s,\n" +
                "            \"head_executor\": {\n" +
                "                \"id\": %s,\n" +
                "                \"head_executor_id\": \"5b166903d4b100007b000001\",\n" +
                "                \"deadline_basis_id\": 88,\n" +
                "                \"periodicity_id\": 38,\n" +
                "                \"execution_state_id\": 47,\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2023-01-18T08:17:01.707Z\"\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}", idAssignmentToRemember, idHeadExecutor);
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBody)
                .put(API_DOCUMENTS + String.format("/%s", idDocumentToRemember))
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.assignments.0.head_executor.deadline_date[0]");

        System.out.println("Error message: " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step27(){
        String requestBody = String.format("{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": %s,\n" +
                "            \"head_executor\": {\n" +
                "                \"id\": %s,\n" +
                "                \"head_executor_id\": \"5b166903d4b100007b000001\",\n" +
                "                \"deadline_basis_id\": 88,\n" +
                "                \"periodicity_id\": 38,\n" +
                "                \"execution_state_id\": 47,\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"123456\"\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}", idAssignmentToRemember, idHeadExecutor);
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBody)
                .put(API_DOCUMENTS + String.format("/%s", idDocumentToRemember))
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.assignments.0.head_executor.deadline_date[0]");

        System.out.println("Error message: " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет неверное значение"));
    }

    @Test
    public void step28(){
        String requestBody = String.format("{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": %s,\n" +
                "            \"head_executor\": {\n" +
                "                \"id\": %s,\n" +
                "                \"head_executor_id\": \"5b166903d4b100007b000001\",\n" +
                "                \"deadline_basis_id\": 88,\n" +
                "                \"periodicity_id\": 38,\n" +
                "                \"execution_state_id\": 0,\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2025-02-05T10:56:02.433Z\"\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}", idAssignmentToRemember, idHeadExecutor);
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBody)
                .put(API_DOCUMENTS + String.format("/%s", idDocumentToRemember))
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.assignments.0.head_executor.execution_state_id[0]");

        System.out.println("Error message: " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
    }
    @Test
    public void step29(){
        String requestBody = String.format("{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": %s,\n" +
                "            \"head_executor\": {\n" +
                "                \"id\": %s,\n" +
                "                \"head_executor_id\": \"5b166903d4b100007b000001\",\n" +
                "                \"deadline_basis_id\": 88,\n" +
                "                \"periodicity_id\": 45,\n" +
                "                \"execution_state_id\": 47,\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2025-02-05T10:56:02.433Z\",\n" +
                "                \"period_in_days\": 0\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}", idAssignmentToRemember, idHeadExecutor);
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBody)
                .put(API_DOCUMENTS + String.format("/%s", idDocumentToRemember))
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.assignments.0.head_executor.period_in_days[0]");

        System.out.println("Error message: " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step30(){
        String requestBody = String.format("{\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\": %s,\n" +
                "            \"head_executor\": {\n" +
                "                \"id\": %s,\n" +
                "                \"head_executor_id\": \"5b166903d4b100007b000001\",\n" +
                "                \"deadline_basis_id\": 88,\n" +
                "                \"periodicity_id\": 45,\n" +
                "                \"execution_state_id\": 47,\n" +
                "                \"start_date\": \"2024-01-18T08:17:01.707Z\",\n" +
                "                \"deadline_date\": \"2025-02-05T10:56:02.433Z\",\n" +
                "                \"period_in_days\": 10\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}", idAssignmentToRemember, idHeadExecutor);
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 6)
                .body(requestBody)
                .put(API_DOCUMENTS + String.format("/%s", idDocumentToRemember))
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("error");

        System.out.println("Error message: " + errorMessage);

        Assert.assertTrue(errorMessage.equals("Доступ к ресурсу запрещен"));
    }

}

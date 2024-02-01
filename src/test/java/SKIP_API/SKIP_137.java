package SKIP_API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SKIP_137 {
    private static String API_DOCUMENTS = "http://api.skip.rtech.ru/v1/documents";
    public static String API_USER = "http://api.skip.rtech.ru/v1/permissions/users";
    public static String API_CONTROL_SUBJECTS = "http://api.skip.rtech.ru/v1/permissions/control_subjects";
    public static String API_DOC_TYPE = "http://api.skip.rtech.ru/v1/classifiers/document_types";
    public static String API_DOCUMENT_EXECUTION_STATES = "http://api.skip.rtech.ru/v1/classifiers/document_execution_states";
    public static String API_THEMES = "http://api.skip.rtech.ru/v1/classifiers/themes";
    int idToRemember; // Get id in step 3 and use it throughout testcase

    @BeforeTest
    public void setup(){
        // Check that user with id 6 has role id = 5
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

        // Check that control subject with id 60 is existed
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
        //System.out.println(dataControlSubjects);
        if (dataControlSubjects.contains("60")){
            System.out.println("Control subject with id = 60 already existed!");
        } else {
            System.out.println("Control subject with id = 60 doesn't exist.");
            String requestBody = String.format(
                    "{\"control_subjects\":[{\"name\": \"%s\",\"fax_number\":\"%s\",\"deleted\":%s,\"provider_id\":\"%s\"}]}"
                    , "Тест Зад", "1", false, "525e9f767da3000002000001");
            Response responseControlSubjectCreate = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", 1)
                    .body(requestBody)
                    .put(API_CONTROL_SUBJECTS)
                    .then().log().all()
                    .extract().response();

            JsonPath jsonPathControlSubjectsCreate = responseControlSubjectCreate.jsonPath();

            System.out.println("Control subjects with 60 was created!");
        }
        // Check that document execution state with id 11 is exited
        Response responseDocumentExecutionState = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .get(API_DOCUMENT_EXECUTION_STATES)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathDocumentExecutionState = responseDocumentExecutionState.jsonPath();

        String dataDocumentExecutionState = jsonPathDocumentExecutionState.getString("data.id");
        //System.out.println(dataDocumentExecutionState);
        if (dataDocumentExecutionState.contains("11")){
            System.out.println("Document execution state with id = 11 already existed!");
        } else {
            System.out.println("Document execution state with id = 11 doesn't exist.");
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

        // Check that themes with id 16,17,30,9 is existed
        Response responseThemes = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .get(API_THEMES)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathThemes = responseThemes.jsonPath();

        List<Integer> idThemes = jsonPathThemes.getList("data.id");

        if (idThemes.contains(16) && idThemes.contains(17) && idThemes.contains(30) && idThemes.contains(9)){
            System.out.println("Elements in the list!");
        } else {
            System.out.println("Elements isn't in the list!");
            // Create theme with id = 16
            Map<String,Object> requestBodySixteen = new HashMap<>();
            requestBodySixteen.put("name","jg");
            requestBodySixteen.put("excluded", false);
            Response responseThemesCreateSixteen = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", 1)
                    .body(requestBodySixteen)
                    .post(API_THEMES)
                    .then().log().all()
                    .extract().response();

            JsonPath jsonPathThemesCreateSixteen = responseThemesCreateSixteen.jsonPath();
            // Create theme with id = 17
            Map<String,Object> requestBodySeventeen = new HashMap<>();
            requestBodySeventeen.put("name","j");
            requestBodySeventeen.put("excluded", false);
            Response responseThemesCreateSeventeen = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", 1)
                    .body(requestBodySeventeen)
                    .post(API_THEMES)
                    .then().log().all()
                    .extract().response();

            JsonPath jsonPathThemesCreateSeventeen = responseThemesCreateSeventeen.jsonPath();
            // Create theme with id = 30
            Map<String,Object> requestBodyThirty = new HashMap<>();
            requestBodyThirty.put("name","New Automation Value");
            requestBodyThirty.put("excluded", false);
            Response responseThemesCreateThirty = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", 1)
                    .body(requestBodyThirty)
                    .post(API_THEMES)
                    .then().log().all()
                    .extract().response();

            JsonPath jsonPathThemesCreateThirty = responseThemesCreateThirty.jsonPath();
            // Create theme with id = 9
            Map<String,Object> requestBodyNine = new HashMap<>();
            requestBodyNine.put("name","№");
            requestBodyNine.put("excluded", false);
            Response responseThemesCreateNine = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", 1)
                    .body(requestBodyNine)
                    .post(API_THEMES)
                    .then().log().all()
                    .extract().response();

            JsonPath jsonPathThemesCreateNine = responseThemesCreateNine.jsonPath();
        }
    }

    @Test
    public void step01(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_137/SKIP_137_step01.json"))
                .post(API_DOCUMENTS)
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
        String assignmentsExecutorsFirstEl = jsonPath.getString("data.assignments[0].executors");
        String assignmentsNumberSecondEl = jsonPath.getString("data.assignments[1].number");
        String assignmentsContentSecondEl = jsonPath.getString("data.assignments[1].content");
        String assignmentsNoteSecondEl = jsonPath.getString("data.assignments[1].note");
        String assignmentsFirstThemeIdSecondEl = jsonPath.getString("data.assignments[1].themes[0].id");
        String assignmentsSecondThemeIdSecondEl = jsonPath.getString("data.assignments[1].themes[1].id");
        String assignmentsExecutorsSecondEl = jsonPath.getString("data.assignments[1].executors");

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
        System.out.println("Assignment executors first element - " + assignmentsExecutorsFirstEl);
        System.out.println("Assignment number second element - " + assignmentsNumberSecondEl);
        System.out.println("Assignment content second element - " + assignmentsContentSecondEl);
        System.out.println("Assignment note second element - " + assignmentsNoteSecondEl);
        System.out.println("Assignment first theme second element - " + assignmentsFirstThemeIdSecondEl);
        System.out.println("Assignment second theme second element - " + assignmentsSecondThemeIdSecondEl);
        System.out.println("Assignment executors second element - " + assignmentsExecutorsSecondEl);

        Assert.assertTrue(skipRegDate.equals("2024-01-16T06:06:31.485Z"));
        Assert.assertTrue(controlOfficialId.equals("5b3bf8a6b48f00020e000001"));
        Assert.assertTrue(controlSubjectId.equals("60"));
        Assert.assertTrue(documentName.equals("Тест 358 document name"));
        Assert.assertTrue(documentExecutionStateId.equals("11"));
        Assert.assertTrue(documentTypeId.equals("32"));
        Assert.assertTrue(internal.equals("false"));
        Assert.assertTrue(shortDescription.equals("Test 358 short description"));
        Assert.assertTrue(assignmentsNumberFirstEl.equals("1"));
        Assert.assertTrue(assignmentsContentFirstEl.equals("content"));
        Assert.assertTrue(assignmentsNoteFirstEl.equals("note"));
        Assert.assertTrue(assignmentsFirstThemeIdFirstEl.equals("17"));
        Assert.assertTrue(assignmentsSecondThemeIdFirstEl.equals("16"));
        Assert.assertTrue(assignmentsExecutorsFirstEl.equals("[]"));
        Assert.assertTrue(assignmentsNumberSecondEl.equals("2"));
        Assert.assertTrue(assignmentsContentSecondEl.equals("content-content"));
        Assert.assertTrue(assignmentsNoteSecondEl.equals("note-note"));
        Assert.assertTrue(assignmentsFirstThemeIdSecondEl.equals("9"));
        Assert.assertTrue(assignmentsSecondThemeIdSecondEl.equals("30"));
        Assert.assertTrue(assignmentsExecutorsSecondEl.equals("[]"));
    }

    @Test
    public void step02(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_137/SKIP_137_step02.json"))
                .post(API_DOCUMENTS)
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
        String assignmentsExecutorsFirstEl = jsonPath.
                getString("data.assignments[0].executors[0].organization.id");
        String assignmentsNumberSecondEl = jsonPath.getString("data.assignments[1].number");
        String assignmentsContentSecondEl = jsonPath.getString("data.assignments[1].content");
        String assignmentsNoteSecondEl = jsonPath.getString("data.assignments[1].note");
        String assignmentsFirstThemeIdSecondEl = jsonPath.getString("data.assignments[1].themes[0].id");
        String assignmentsSecondThemeIdSecondEl = jsonPath.getString("data.assignments[1].themes[1].id");
        String assignmentsExecutorsSecondEl = jsonPath.
                getString("data.assignments[1].executors[0].organization.id");

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
        System.out.println("Assignment executors first element - " + assignmentsExecutorsFirstEl);
        System.out.println("Assignment number second element - " + assignmentsNumberSecondEl);
        System.out.println("Assignment content second element - " + assignmentsContentSecondEl);
        System.out.println("Assignment note second element - " + assignmentsNoteSecondEl);
        System.out.println("Assignment first theme second element - " + assignmentsFirstThemeIdSecondEl);
        System.out.println("Assignment second theme second element - " + assignmentsSecondThemeIdSecondEl);
        System.out.println("Assignment executors second element - " + assignmentsExecutorsSecondEl);

        Assert.assertTrue(skipRegDate.equals("2024-01-16T06:06:31.485Z"));
        Assert.assertTrue(controlOfficialId.equals("5b3bf8a6b48f00020e000001"));
        Assert.assertTrue(controlSubjectId.equals("60"));
        Assert.assertTrue(documentName.equals("Тест 358 document name"));
        Assert.assertTrue(documentExecutionStateId.equals("11"));
        Assert.assertTrue(documentTypeId.equals("32"));
        Assert.assertTrue(internal.equals("false"));
        Assert.assertTrue(shortDescription.equals("Test 358 short description"));
        Assert.assertTrue(assignmentsNumberFirstEl.equals("1"));
        Assert.assertTrue(assignmentsContentFirstEl.equals("content"));
        Assert.assertTrue(assignmentsNoteFirstEl.equals("note"));
        Assert.assertTrue(assignmentsFirstThemeIdFirstEl.equals("17"));
        Assert.assertTrue(assignmentsSecondThemeIdFirstEl.equals("16"));
        Assert.assertTrue(assignmentsExecutorsFirstEl.equals("6426a7c3379100001e000001"));
        Assert.assertTrue(assignmentsNumberSecondEl.equals("2"));
        Assert.assertTrue(assignmentsContentSecondEl.equals("content-content"));
        Assert.assertTrue(assignmentsNoteSecondEl.equals("note-note"));
        Assert.assertTrue(assignmentsFirstThemeIdSecondEl.equals("9"));
        Assert.assertTrue(assignmentsSecondThemeIdSecondEl.equals("30"));
        Assert.assertTrue(assignmentsExecutorsSecondEl.equals("55a8cc52267e000941000001"));
    }

    @Test
    public void step03(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_137/SKIP_137_step03.json"))
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        idToRemember = jsonPath.get("data.id");
        System.out.println("Id created element - " + idToRemember);

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
        String assignmentsExecutorsFirstElOrganization = jsonPath.
                getString("data.assignments[0].executors[0].organization.id");
        String assignmentsExecutorsFirstElExecutor = jsonPath.
                getString("data.assignments[0].executors[0].executor.id");
        String assignmentsExecutorsFirstElControlEmployee = jsonPath.
                getString("data.assignments[0].executors[0].control_employee.id");
        String assignmentsExecutorsFirstElExecutorHead = jsonPath.
                getString("data.assignments[0].executors[0].executor_head.id");
        String assignmentsExecutorsFirstElOrganizationHead = jsonPath.
                getString("data.assignments[0].executors[0].organization_head.id");
        String assignmentsExecutorsFirstElExecutionDeputyHead = jsonPath.
                getString("data.assignments[0].executors[0].execution_deputy_head.id");
        String assignmentsExecutorsFirstElControlDeputyHead = jsonPath.
                getString("data.assignments[0].executors[0].control_deputy_head.id");
        String assignmentsNumberSecondEl = jsonPath.getString("data.assignments[1].number");
        String assignmentsContentSecondEl = jsonPath.getString("data.assignments[1].content");
        String assignmentsNoteSecondEl = jsonPath.getString("data.assignments[1].note");
        String assignmentsFirstThemeIdSecondEl = jsonPath.getString("data.assignments[1].themes[0].id");
        String assignmentsSecondThemeIdSecondEl = jsonPath.getString("data.assignments[1].themes[1].id");
        String assignmentsExecutorsSecondElOrganization = jsonPath.
                getString("data.assignments[1].executors[0].organization.id");
        String assignmentsExecutorsSecondElExecutor = jsonPath.
                getString("data.assignments[1].executors[0].executor.id");
        String assignmentsExecutorsSecondElControlEmployee = jsonPath.
                getString("data.assignments[1].executors[0].control_employee.id");
        String assignmentsExecutorsSecondElExecutorHead = jsonPath.
                getString("data.assignments[1].executors[0].executor_head.id");
        String assignmentsExecutorsSecondElOrganizationHead = jsonPath.
                getString("data.assignments[1].executors[0].organization_head.id");
        String assignmentsExecutorsSecondElExecutionDeputyHead = jsonPath.
                getString("data.assignments[1].executors[0].execution_deputy_head.id");
        String assignmentsExecutorsSecondElControlDeputyHead = jsonPath.
                getString("data.assignments[1].executors[0].control_deputy_head.id");

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
        System.out.println("Assignment executors first element organization - "
                + assignmentsExecutorsFirstElOrganization);
        System.out.println("Assignment executors first element executor - "
                + assignmentsExecutorsFirstElExecutor);
        System.out.println("Assignment executors first element control employee - "
                + assignmentsExecutorsFirstElControlEmployee);
        System.out.println("Assignment executors first element executor head - "
                + assignmentsExecutorsFirstElExecutorHead);
        System.out.println("Assignment executors first element organization head - "
                + assignmentsExecutorsFirstElOrganizationHead);
        System.out.println("Assignment executors first element execution deputy head - "
                + assignmentsExecutorsFirstElExecutionDeputyHead);
        System.out.println("Assignment executors first element control deputy head - "
                + assignmentsExecutorsFirstElControlDeputyHead);
        System.out.println("Assignment number second element - " + assignmentsNumberSecondEl);
        System.out.println("Assignment content second element - " + assignmentsContentSecondEl);
        System.out.println("Assignment note second element - " + assignmentsNoteSecondEl);
        System.out.println("Assignment first theme second element - " + assignmentsFirstThemeIdSecondEl);
        System.out.println("Assignment second theme second element - " + assignmentsSecondThemeIdSecondEl);
        System.out.println("Assignment executors second element organization - "
                + assignmentsExecutorsSecondElOrganization);
        System.out.println("Assignment executors second element executor - "
                + assignmentsExecutorsSecondElExecutor);
        System.out.println("Assignment executors second element control employee - "
                + assignmentsExecutorsSecondElControlEmployee);
        System.out.println("Assignment executors second element executor head - "
                + assignmentsExecutorsSecondElExecutorHead);
        System.out.println("Assignment executors second element organization head - "
                + assignmentsExecutorsSecondElOrganizationHead);
        System.out.println("Assignment executors second element execution deputy head - "
                + assignmentsExecutorsSecondElExecutionDeputyHead);
        System.out.println("Assignment executors second element control deputy head - "
                + assignmentsExecutorsSecondElControlDeputyHead);

        Assert.assertTrue(skipRegDate.equals("2024-01-16T06:06:31.485Z"));
        Assert.assertTrue(controlOfficialId.equals("5b3bf8a6b48f00020e000001"));
        Assert.assertTrue(controlSubjectId.equals("60"));
        Assert.assertTrue(documentName.equals("Тест 358 document name"));
        Assert.assertTrue(documentExecutionStateId.equals("11"));
        Assert.assertTrue(documentTypeId.equals("32"));
        Assert.assertTrue(internal.equals("false"));
        Assert.assertTrue(shortDescription.equals("Test 358 short description"));
        Assert.assertTrue(assignmentsNumberFirstEl.equals("1"));
        Assert.assertTrue(assignmentsContentFirstEl.equals("content"));
        Assert.assertTrue(assignmentsNoteFirstEl.equals("note"));
        Assert.assertTrue(assignmentsFirstThemeIdFirstEl.equals("17"));
        Assert.assertTrue(assignmentsSecondThemeIdFirstEl.equals("16"));
        Assert.assertTrue(assignmentsExecutorsFirstElOrganization.equals("6426a7c3379100001e000001"));
        Assert.assertTrue(assignmentsExecutorsFirstElExecutor.equals("56683141a64619317d000001"));
        Assert.assertTrue(assignmentsExecutorsFirstElControlEmployee.equals("53bd047d393f000828000001"));
        Assert.assertTrue(assignmentsExecutorsFirstElExecutorHead.equals("55132cdc17bf000663000001"));
        Assert.assertTrue(assignmentsExecutorsFirstElOrganizationHead.equals("5582e86af5f5011355000001"));
        Assert.assertTrue(assignmentsExecutorsFirstElExecutionDeputyHead.equals("533ee3b263fc00097d000001"));
        Assert.assertTrue(assignmentsExecutorsFirstElControlDeputyHead.equals("54db770074e700c167000001"));
        Assert.assertTrue(assignmentsNumberSecondEl.equals("2"));
        Assert.assertTrue(assignmentsContentSecondEl.equals("content-content"));
        Assert.assertTrue(assignmentsNoteSecondEl.equals("note-note"));
        Assert.assertTrue(assignmentsFirstThemeIdSecondEl.equals("9"));
        Assert.assertTrue(assignmentsSecondThemeIdSecondEl.equals("30"));
        Assert.assertTrue(assignmentsExecutorsSecondElOrganization.equals("55a8cc52267e000941000001"));
        Assert.assertTrue(assignmentsExecutorsSecondElExecutor.equals("56a0c409044e000dc8000001"));
        Assert.assertTrue(assignmentsExecutorsSecondElControlEmployee.equals("56d3ff213ad301926a000001"));
        Assert.assertTrue(assignmentsExecutorsSecondElExecutorHead.equals("5666c02d350b117255000001"));
        Assert.assertTrue(assignmentsExecutorsSecondElOrganizationHead.equals("546b68a7b08300085a000001"));
        Assert.assertTrue(assignmentsExecutorsSecondElExecutionDeputyHead.equals("55928410e18a0e153e000001"));
        Assert.assertTrue(assignmentsExecutorsSecondElControlDeputyHead.equals("571954972b3000000c000001"));
    }

    @Test
    public void step04(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_137/SKIP_137_step04.json"))
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String firstOrganizationErrorMessage = jsonPath.
                getString("errors.assignments.0.executors.0.organization_id[0]");
        String secondOrganizationErrorMessage = jsonPath.
                getString("errors.assignments.1.executors.0.organization_id[0]");

        System.out.println("First message - " + firstOrganizationErrorMessage);
        System.out.println("Second message - " + secondOrganizationErrorMessage);

        Assert.assertTrue(firstOrganizationErrorMessage.equals("не может быть пустым"));
        Assert.assertTrue(secondOrganizationErrorMessage.equals("не может быть пустым"));
    }

    @Test
    public void step05(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_137/SKIP_137_step05.json"))
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String firstOrganizationErrorMessage = jsonPath.
                getString("errors.assignments.0.executors.1.organization_id[0]");
        String secondOrganizationErrorMessage = jsonPath.
                getString("errors.assignments.1.executors.1.organization_id[0]");

        System.out.println("First message - " + firstOrganizationErrorMessage);
        System.out.println("Second message - " + secondOrganizationErrorMessage);

        Assert.assertTrue(firstOrganizationErrorMessage.equals("уже существует"));
        Assert.assertTrue(secondOrganizationErrorMessage.equals("уже существует"));
    }

    @Test
    public void step06(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_137/SKIP_137_step06.json"))
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String organizationErrorMessage = jsonPath.
                getString("errors.assignments.0.executors.0.organization_id[0]");

        System.out.println("Error message - " + organizationErrorMessage);

        Assert.assertTrue(organizationErrorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step07(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_137/SKIP_137_step07.json"))
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String executorErrorMessage = jsonPath.getString("errors.assignments.0.executors.0.executor_id[0]");

        System.out.println("Error message - " + executorErrorMessage);

        Assert.assertTrue(executorErrorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step08(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_137/SKIP_137_step08.json"))
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String controlEmployeeErrorMessage = jsonPath.
                getString("errors.assignments.0.executors.0.control_employee_id[0]");

        System.out.println("Error message - " + controlEmployeeErrorMessage);

        Assert.assertTrue(controlEmployeeErrorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step09(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_137/SKIP_137_step09.json"))
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String executorHeadErrorMessage = jsonPath.
                getString("errors.assignments.0.executors.0.executor_head_id[0]");

        System.out.println("Error message - " + executorHeadErrorMessage);

        Assert.assertTrue(executorHeadErrorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step10(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_137/SKIP_137_step10.json"))
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String organizationHeadErrorMessage = jsonPath.
                getString("errors.assignments.0.executors.0.organization_head_id[0]");

        System.out.println("Error message - " + organizationHeadErrorMessage);

        Assert.assertTrue(organizationHeadErrorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step11(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_137/SKIP_137_step11.json"))
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String executionDeputyHeadErrorMessage = jsonPath.
                getString("errors.assignments.0.executors.0.execution_deputy_head_id[0]");

        System.out.println("Error message - " + executionDeputyHeadErrorMessage);

        Assert.assertTrue(executionDeputyHeadErrorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step12(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_137/SKIP_137_step12.json"))
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String controlDeputyHeadErrorMessage = jsonPath.
                getString("errors.assignments.0.executors.0.control_deputy_head_id[0]");

        System.out.println("Error message - " + controlDeputyHeadErrorMessage);

        Assert.assertTrue(controlDeputyHeadErrorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step13(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 6)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_137/SKIP_137_step12.json"))
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.getString("error");

        System.out.println("Error message - " + error);

        Assert.assertTrue(error.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void step14(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .get(API_DOCUMENTS + String.format("/%s", idToRemember))
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
        String assignmentsExecutorsFirstElOrganization = jsonPath.
                getString("data.assignments[0].executors[0].organization.id");
        String assignmentsExecutorsFirstElExecutor = jsonPath.
                getString("data.assignments[0].executors[0].executor.id");
        String assignmentsExecutorsFirstElControlEmployee = jsonPath.
                getString("data.assignments[0].executors[0].control_employee.id");
        String assignmentsExecutorsFirstElExecutorHead = jsonPath.
                getString("data.assignments[0].executors[0].executor_head.id");
        String assignmentsExecutorsFirstElOrganizationHead = jsonPath.
                getString("data.assignments[0].executors[0].organization_head.id");
        String assignmentsExecutorsFirstElExecutionDeputyHead = jsonPath.
                getString("data.assignments[0].executors[0].execution_deputy_head.id");
        String assignmentsExecutorsFirstElControlDeputyHead = jsonPath.
                getString("data.assignments[0].executors[0].control_deputy_head.id");
        String assignmentsNumberSecondEl = jsonPath.getString("data.assignments[1].number");
        String assignmentsContentSecondEl = jsonPath.getString("data.assignments[1].content");
        String assignmentsNoteSecondEl = jsonPath.getString("data.assignments[1].note");
        String assignmentsFirstThemeIdSecondEl = jsonPath.getString("data.assignments[1].themes[0].id");
        String assignmentsSecondThemeIdSecondEl = jsonPath.getString("data.assignments[1].themes[1].id");
        String assignmentsExecutorsSecondElOrganization = jsonPath.
                getString("data.assignments[1].executors[0].organization.id");
        String assignmentsExecutorsSecondElExecutor = jsonPath.
                getString("data.assignments[1].executors[0].executor.id");
        String assignmentsExecutorsSecondElControlEmployee = jsonPath.
                getString("data.assignments[1].executors[0].control_employee.id");
        String assignmentsExecutorsSecondElExecutorHead = jsonPath.
                getString("data.assignments[1].executors[0].executor_head.id");
        String assignmentsExecutorsSecondElOrganizationHead = jsonPath.
                getString("data.assignments[1].executors[0].organization_head.id");
        String assignmentsExecutorsSecondElExecutionDeputyHead = jsonPath.
                getString("data.assignments[1].executors[0].execution_deputy_head.id");
        String assignmentsExecutorsSecondElControlDeputyHead = jsonPath.
                getString("data.assignments[1].executors[0].control_deputy_head.id");

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
        System.out.println("Assignment executors first element organization - "
                + assignmentsExecutorsFirstElOrganization);
        System.out.println("Assignment executors first element executor - "
                + assignmentsExecutorsFirstElExecutor);
        System.out.println("Assignment executors first element control employee - "
                + assignmentsExecutorsFirstElControlEmployee);
        System.out.println("Assignment executors first element executor head - "
                + assignmentsExecutorsFirstElExecutorHead);
        System.out.println("Assignment executors first element organization head - "
                + assignmentsExecutorsFirstElOrganizationHead);
        System.out.println("Assignment executors first element execution deputy head - "
                + assignmentsExecutorsFirstElExecutionDeputyHead);
        System.out.println("Assignment executors first element control deputy head - "
                + assignmentsExecutorsFirstElControlDeputyHead);
        System.out.println("Assignment number second element - " + assignmentsNumberSecondEl);
        System.out.println("Assignment content second element - " + assignmentsContentSecondEl);
        System.out.println("Assignment note second element - " + assignmentsNoteSecondEl);
        System.out.println("Assignment first theme second element - " + assignmentsFirstThemeIdSecondEl);
        System.out.println("Assignment second theme second element - " + assignmentsSecondThemeIdSecondEl);
        System.out.println("Assignment executors second element organization - "
                + assignmentsExecutorsSecondElOrganization);
        System.out.println("Assignment executors second element executor - "
                + assignmentsExecutorsSecondElExecutor);
        System.out.println("Assignment executors second element control employee - "
                + assignmentsExecutorsSecondElControlEmployee);
        System.out.println("Assignment executors second element executor head - "
                + assignmentsExecutorsSecondElExecutorHead);
        System.out.println("Assignment executors second element organization head - "
                + assignmentsExecutorsSecondElOrganizationHead);
        System.out.println("Assignment executors second element execution deputy head - "
                + assignmentsExecutorsSecondElExecutionDeputyHead);
        System.out.println("Assignment executors second element control deputy head - "
                + assignmentsExecutorsSecondElControlDeputyHead);

        Assert.assertTrue(skipRegDate.equals("2024-01-16T06:06:31.485Z"));
        Assert.assertTrue(controlOfficialId.equals("5b3bf8a6b48f00020e000001"));
        Assert.assertTrue(controlSubjectId.equals("60"));
        Assert.assertTrue(documentName.equals("Тест 358 document name"));
        Assert.assertTrue(documentExecutionStateId.equals("11"));
        Assert.assertTrue(documentTypeId.equals("32"));
        Assert.assertTrue(internal.equals("false"));
        Assert.assertTrue(shortDescription.equals("Test 358 short description"));
        Assert.assertTrue(assignmentsNumberFirstEl.equals("1"));
        Assert.assertTrue(assignmentsContentFirstEl.equals("content"));
        Assert.assertTrue(assignmentsNoteFirstEl.equals("note"));
        Assert.assertTrue(assignmentsFirstThemeIdFirstEl.equals("17"));
        Assert.assertTrue(assignmentsSecondThemeIdFirstEl.equals("16"));
        Assert.assertTrue(assignmentsExecutorsFirstElOrganization.equals("6426a7c3379100001e000001"));
        Assert.assertTrue(assignmentsExecutorsFirstElExecutor.equals("56683141a64619317d000001"));
        Assert.assertTrue(assignmentsExecutorsFirstElControlEmployee.equals("53bd047d393f000828000001"));
        Assert.assertTrue(assignmentsExecutorsFirstElExecutorHead.equals("55132cdc17bf000663000001"));
        Assert.assertTrue(assignmentsExecutorsFirstElOrganizationHead.equals("5582e86af5f5011355000001"));
        Assert.assertTrue(assignmentsExecutorsFirstElExecutionDeputyHead.equals("533ee3b263fc00097d000001"));
        Assert.assertTrue(assignmentsExecutorsFirstElControlDeputyHead.equals("54db770074e700c167000001"));
        Assert.assertTrue(assignmentsNumberSecondEl.equals("2"));
        Assert.assertTrue(assignmentsContentSecondEl.equals("content-content"));
        Assert.assertTrue(assignmentsNoteSecondEl.equals("note-note"));
        Assert.assertTrue(assignmentsFirstThemeIdSecondEl.equals("9"));
        Assert.assertTrue(assignmentsSecondThemeIdSecondEl.equals("30"));
        Assert.assertTrue(assignmentsExecutorsSecondElOrganization.equals("55a8cc52267e000941000001"));
        Assert.assertTrue(assignmentsExecutorsSecondElExecutor.equals("56a0c409044e000dc8000001"));
        Assert.assertTrue(assignmentsExecutorsSecondElControlEmployee.equals("56d3ff213ad301926a000001"));
        Assert.assertTrue(assignmentsExecutorsSecondElExecutorHead.equals("5666c02d350b117255000001"));
        Assert.assertTrue(assignmentsExecutorsSecondElOrganizationHead.equals("546b68a7b08300085a000001"));
        Assert.assertTrue(assignmentsExecutorsSecondElExecutionDeputyHead.equals("55928410e18a0e153e000001"));
        Assert.assertTrue(assignmentsExecutorsSecondElControlDeputyHead.equals("571954972b3000000c000001"));
    }

    @Test
    public void step15(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .get(API_DOCUMENTS + "/1000")
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("error");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("Запись для Documents::Document с id = 1000 не найдена"));
    }

    @Test
    public void step16(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 6)
                .get(API_DOCUMENTS + String.format("/%s", idToRemember))
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.getString("error");

        System.out.println("Error message - " + error);

        Assert.assertTrue(error.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void step17(){
        // Add another one executor to assignment with id = 14
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_137/SKIP_137_step17.json"))
                .put(API_DOCUMENTS + String.format("/%s", idToRemember))
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String assignmentsNumberFirstEl = jsonPath.getString("data.assignments[0].number");
        String assignmentsContentFirstEl = jsonPath.getString("data.assignments[0].content");
        String assignmentsNoteFirstEl = jsonPath.getString("data.assignments[0].note");
        String assignmentsFirstThemeIdFirstEl = jsonPath.getString("data.assignments[0].themes[0].id");
        String assignmentsSecondThemeIdFirstEl = jsonPath.getString("data.assignments[0].themes[1].id");
        String assignmentsExecutorsFirstElOrganization = jsonPath.
                getString("data.assignments[0].executors[0].organization.id");
        String assignmentsExecutorsFirstElExecutor = jsonPath.
                getString("data.assignments[0].executors[0].executor.id");
        String assignmentsExecutorsFirstElControlEmployee = jsonPath.
                getString("data.assignments[0].executors[0].control_employee.id");
        String assignmentsExecutorsFirstElExecutorHead = jsonPath.
                getString("data.assignments[0].executors[0].executor_head.id");
        String assignmentsExecutorsFirstElOrganizationHead = jsonPath.
                getString("data.assignments[0].executors[0].organization_head.id");
        String assignmentsExecutorsFirstElExecutionDeputyHead = jsonPath.
                getString("data.assignments[0].executors[0].execution_deputy_head.id");
        String assignmentsExecutorsFirstElControlDeputyHead = jsonPath.
                getString("data.assignments[0].executors[0].control_deputy_head.id");
        String assignmentsExecutorsSecondElOrganization = jsonPath.
                getString("data.assignments[1].executors[0].organization.id");
        String assignmentsExecutorsSecondElExecutor = jsonPath.
                getString("data.assignments[1].executors[0].executor.id");
        String assignmentsExecutorsSecondElControlEmployee = jsonPath.
                getString("data.assignments[1].executors[0].control_employee.id");
        String assignmentsExecutorsSecondElExecutorHead = jsonPath.
                getString("data.assignments[1].executors[0].executor_head.id");
        String assignmentsExecutorsSecondElOrganizationHead = jsonPath.
                getString("data.assignments[1].executors[0].organization_head.id");
        String assignmentsExecutorsSecondElExecutionDeputyHead = jsonPath.
                getString("data.assignments[1].executors[0].execution_deputy_head.id");
        String assignmentsExecutorsSecondElControlDeputyHead = jsonPath.
                getString("data.assignments[1].executors[0].control_deputy_head.id");
        String assignmentsExecutorsThirdElOrganization = jsonPath.
                getString("data.assignments[2].executors[0].organization.id");
        String assignmentsExecutorsThirdElExecutor = jsonPath.
                getString("data.assignments[2].executors[0].executor.id");
        String assignmentsExecutorsThirdElControlEmployee = jsonPath.
                getString("data.assignments[2].executors[0].control_employee.id");
        String assignmentsExecutorsThirdElExecutorHead = jsonPath.
                getString("data.assignments[2].executors[0].executor_head.id");
        String assignmentsExecutorsThirdElOrganizationHead = jsonPath.
                getString("data.assignments[2].executors[0].organization_head.id");
        String assignmentsExecutorsThirdElExecutionDeputyHead = jsonPath.
                getString("data.assignments[2].executors[0].execution_deputy_head.id");
        String assignmentsExecutorsThirdElControlDeputyHead = jsonPath.
                getString("data.assignments[2].executors[0].control_deputy_head.id");
        String assignmentsExecutorsThirdElOrganizationSecondEx = jsonPath.
                getString("data.assignments[2].executors[1].organization.id");
        String assignmentsExecutorsThirdElExecutorSecondEx = jsonPath.
                getString("data.assignments[2].executors[1].executor.id");
        String assignmentsExecutorsThirdElControlEmployeeSecondEx = jsonPath.
                getString("data.assignments[2].executors[1].control_employee.id");
        String assignmentsExecutorsThirdElExecutorHeadSecondExd = jsonPath.
                getString("data.assignments[2].executors[1].executor_head.id");
        String assignmentsExecutorsThirdElOrganizationHeadSecondEx = jsonPath.
                getString("data.assignments[2].executors[1].organization_head.id");
        String assignmentsExecutorsThirdElExecutionDeputyHeadSecondEx = jsonPath.
                getString("data.assignments[2].executors[1].execution_deputy_head.id");
        String assignmentsExecutorsThirdElControlDeputyHeadSecondEx = jsonPath.
                getString("data.assignments[2].executors[1].control_deputy_head.id");



        System.out.println("Assignment number first element - " + assignmentsNumberFirstEl);
        System.out.println("Assignment content first element - " + assignmentsContentFirstEl);
        System.out.println("Assignment note first element - " + assignmentsNoteFirstEl);
        System.out.println("Assignment first theme first element - " + assignmentsFirstThemeIdFirstEl);
        System.out.println("Assignment second theme first element - " + assignmentsSecondThemeIdFirstEl);
        System.out.println("Assignment executors first element organization - "
                + assignmentsExecutorsFirstElOrganization);
        System.out.println("Assignment executors first element executor - "
                + assignmentsExecutorsFirstElExecutor);
        System.out.println("Assignment executors first element control employee - "
                + assignmentsExecutorsFirstElControlEmployee);
        System.out.println("Assignment executors first element executor head - "
                + assignmentsExecutorsFirstElExecutorHead);
        System.out.println("Assignment executors first element organization head - "
                + assignmentsExecutorsFirstElOrganizationHead);
        System.out.println("Assignment executors first element execution deputy head - "
                + assignmentsExecutorsFirstElExecutionDeputyHead);
        System.out.println("Assignment executors first element control deputy head - "
                + assignmentsExecutorsFirstElControlDeputyHead);
        System.out.println("Assignment executors second element organization - "
                + assignmentsExecutorsSecondElOrganization);
        System.out.println("Assignment executors second element executor - "
                + assignmentsExecutorsSecondElExecutor);
        System.out.println("Assignment executors second element control employee - "
                + assignmentsExecutorsSecondElControlEmployee);
        System.out.println("Assignment executors second element executor head - "
                + assignmentsExecutorsSecondElExecutorHead);
        System.out.println("Assignment executors second element organization head - "
                + assignmentsExecutorsSecondElOrganizationHead);
        System.out.println("Assignment executors second element execution deputy head - "
                + assignmentsExecutorsSecondElExecutionDeputyHead);
        System.out.println("Assignment executors second element control deputy head - "
                + assignmentsExecutorsSecondElControlDeputyHead);
        System.out.println("Assignment executors third element organization - "
                + assignmentsExecutorsThirdElOrganization);
        System.out.println("Assignment executors third element executor - "
                + assignmentsExecutorsThirdElExecutor);
        System.out.println("Assignment executors third element control employee - "
                + assignmentsExecutorsThirdElControlEmployee);
        System.out.println("Assignment executors third element executor head - "
                + assignmentsExecutorsThirdElExecutorHead);
        System.out.println("Assignment executors third element organization head - "
                + assignmentsExecutorsThirdElOrganizationHead);
        System.out.println("Assignment executors third element execution deputy head - "
                + assignmentsExecutorsThirdElExecutionDeputyHead);
        System.out.println("Assignment executors third element control deputy head - "
                + assignmentsExecutorsThirdElControlDeputyHead);
        System.out.println("Assignment executors third element organization second executor - "
                + assignmentsExecutorsThirdElOrganizationSecondEx);
        System.out.println("Assignment executors third element executor second executor - "
                + assignmentsExecutorsThirdElExecutorSecondEx);
        System.out.println("Assignment executors third element control employee second executor - "
                + assignmentsExecutorsThirdElControlEmployeeSecondEx);
        System.out.println("Assignment executors third element executor head second executor - "
                + assignmentsExecutorsThirdElExecutorHeadSecondExd);
        System.out.println("Assignment executors third element organization head second executor - "
                + assignmentsExecutorsThirdElOrganizationHeadSecondEx);
        System.out.println("Assignment executors third element execution deputy head second executor - "
                + assignmentsExecutorsThirdElExecutionDeputyHeadSecondEx);
        System.out.println("Assignment executors third element control deputy head second executor - "
                + assignmentsExecutorsThirdElControlDeputyHeadSecondEx);

        Assert.assertTrue(assignmentsNumberFirstEl.equals("1"));
        Assert.assertTrue(assignmentsContentFirstEl.equals("content"));
        Assert.assertTrue(assignmentsNoteFirstEl.equals("note"));
        Assert.assertTrue(assignmentsFirstThemeIdFirstEl.equals("17"));
        Assert.assertTrue(assignmentsSecondThemeIdFirstEl.equals("16"));
        Assert.assertTrue(assignmentsExecutorsFirstElOrganization.equals("6426a7c3379100001e000001"));
        Assert.assertTrue(assignmentsExecutorsFirstElExecutor.equals("56683141a64619317d000001"));
        Assert.assertTrue(assignmentsExecutorsFirstElControlEmployee.equals("53bd047d393f000828000001"));
        Assert.assertTrue(assignmentsExecutorsFirstElExecutorHead.equals("55132cdc17bf000663000001"));
        Assert.assertTrue(assignmentsExecutorsFirstElOrganizationHead.equals("5582e86af5f5011355000001"));
        Assert.assertTrue(assignmentsExecutorsFirstElExecutionDeputyHead.equals("533ee3b263fc00097d000001"));
        Assert.assertTrue(assignmentsExecutorsFirstElControlDeputyHead.equals("54db770074e700c167000001"));
        Assert.assertTrue(assignmentsExecutorsSecondElOrganization.equals("55a8cc52267e000941000001"));
        Assert.assertTrue(assignmentsExecutorsSecondElExecutor.equals("56a0c409044e000dc8000001"));
        Assert.assertTrue(assignmentsExecutorsSecondElControlEmployee.equals("56d3ff213ad301926a000001"));
        Assert.assertTrue(assignmentsExecutorsSecondElExecutorHead.equals("5666c02d350b117255000001"));
        Assert.assertTrue(assignmentsExecutorsSecondElOrganizationHead.equals("546b68a7b08300085a000001"));
        Assert.assertTrue(assignmentsExecutorsSecondElExecutionDeputyHead.equals("55928410e18a0e153e000001"));
        Assert.assertTrue(assignmentsExecutorsSecondElControlDeputyHead.equals("571954972b3000000c000001"));
        Assert.assertTrue(assignmentsExecutorsThirdElOrganization.equals("56f54131966b00473c000001"));
        Assert.assertTrue(assignmentsExecutorsThirdElExecutor.equals("56683141a64619317d000001"));
        Assert.assertTrue(assignmentsExecutorsThirdElControlEmployee.equals("56683141a64619317d000001"));
        Assert.assertTrue(assignmentsExecutorsThirdElExecutorHead.equals("56683141a64619317d000001"));
        Assert.assertTrue(assignmentsExecutorsThirdElOrganizationHead.equals("56683141a64619317d000001"));
        Assert.assertTrue(assignmentsExecutorsThirdElExecutionDeputyHead.equals("56683141a64619317d000001"));
        Assert.assertTrue(assignmentsExecutorsThirdElControlDeputyHead.equals("56683141a64619317d000001"));
        Assert.assertTrue(assignmentsExecutorsThirdElOrganizationSecondEx.equals("54623ebf1244001e4d000001"));
        Assert.assertTrue(assignmentsExecutorsThirdElExecutorSecondEx.equals("56683141a64619317d000001"));
        Assert.assertTrue(assignmentsExecutorsThirdElControlEmployeeSecondEx.equals("56683141a64619317d000001"));
        Assert.assertTrue(assignmentsExecutorsThirdElExecutorHeadSecondExd.equals("56683141a64619317d000001"));
        Assert.assertTrue(assignmentsExecutorsThirdElOrganizationHeadSecondEx.equals("56683141a64619317d000001"));
        Assert.assertTrue(assignmentsExecutorsThirdElExecutionDeputyHeadSecondEx.equals("56683141a64619317d000001"));
        Assert.assertTrue(assignmentsExecutorsThirdElControlDeputyHeadSecondEx.equals("56683141a64619317d000001"));
    }

    @Test
    public void step18(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .put(API_DOCUMENTS + String.format("/%s", idToRemember))
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
        String assignmentsExecutorsFirstElOrganization = jsonPath.
                getString("data.assignments[0].executors[0].organization.id");
        String assignmentsExecutorsFirstElExecutor = jsonPath.
                getString("data.assignments[0].executors[0].executor.id");
        String assignmentsExecutorsFirstElControlEmployee = jsonPath.
                getString("data.assignments[0].executors[0].control_employee.id");
        String assignmentsExecutorsFirstElExecutorHead = jsonPath.
                getString("data.assignments[0].executors[0].executor_head.id");
        String assignmentsExecutorsFirstElOrganizationHead = jsonPath.
                getString("data.assignments[0].executors[0].organization_head.id");
        String assignmentsExecutorsFirstElExecutionDeputyHead = jsonPath.
                getString("data.assignments[0].executors[0].execution_deputy_head.id");
        String assignmentsExecutorsFirstElControlDeputyHead = jsonPath.
                getString("data.assignments[0].executors[0].control_deputy_head.id");
        String assignmentsExecutorsSecondElOrganization = jsonPath.
                getString("data.assignments[1].executors[0].organization.id");
        String assignmentsExecutorsSecondElExecutor = jsonPath.
                getString("data.assignments[1].executors[0].executor.id");
        String assignmentsExecutorsSecondElControlEmployee = jsonPath.
                getString("data.assignments[1].executors[0].control_employee.id");
        String assignmentsExecutorsSecondElExecutorHead = jsonPath.
                getString("data.assignments[1].executors[0].executor_head.id");
        String assignmentsExecutorsSecondElOrganizationHead = jsonPath.
                getString("data.assignments[1].executors[0].organization_head.id");
        String assignmentsExecutorsSecondElExecutionDeputyHead = jsonPath.
                getString("data.assignments[1].executors[0].execution_deputy_head.id");
        String assignmentsExecutorsSecondElControlDeputyHead = jsonPath.
                getString("data.assignments[1].executors[0].control_deputy_head.id");
        String assignmentsExecutorsThirdElOrganization = jsonPath.
                getString("data.assignments[2].executors[0].organization.id");
        String assignmentsExecutorsThirdElExecutor = jsonPath.
                getString("data.assignments[2].executors[0].executor.id");
        String assignmentsExecutorsThirdElControlEmployee = jsonPath.
                getString("data.assignments[2].executors[0].control_employee.id");
        String assignmentsExecutorsThirdElExecutorHead = jsonPath.
                getString("data.assignments[2].executors[0].executor_head.id");
        String assignmentsExecutorsThirdElOrganizationHead = jsonPath.
                getString("data.assignments[2].executors[0].organization_head.id");
        String assignmentsExecutorsThirdElExecutionDeputyHead = jsonPath.
                getString("data.assignments[2].executors[0].execution_deputy_head.id");
        String assignmentsExecutorsThirdElControlDeputyHead = jsonPath.
                getString("data.assignments[2].executors[0].control_deputy_head.id");
        String assignmentsExecutorsThirdElOrganizationSecondEx = jsonPath.
                getString("data.assignments[2].executors[1].organization.id");
        String assignmentsExecutorsThirdElExecutorSecondEx = jsonPath.
                getString("data.assignments[2].executors[1].executor.id");
        String assignmentsExecutorsThirdElControlEmployeeSecondEx = jsonPath.
                getString("data.assignments[2].executors[1].control_employee.id");
        String assignmentsExecutorsThirdElExecutorHeadSecondExd = jsonPath.
                getString("data.assignments[2].executors[1].executor_head.id");
        String assignmentsExecutorsThirdElOrganizationHeadSecondEx = jsonPath.
                getString("data.assignments[2].executors[1].organization_head.id");
        String assignmentsExecutorsThirdElExecutionDeputyHeadSecondEx = jsonPath.
                getString("data.assignments[2].executors[1].execution_deputy_head.id");
        String assignmentsExecutorsThirdElControlDeputyHeadSecondEx = jsonPath.
                getString("data.assignments[2].executors[1].control_deputy_head.id");



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
        System.out.println("Assignment executors first element organization - "
                + assignmentsExecutorsFirstElOrganization);
        System.out.println("Assignment executors first element executor - "
                + assignmentsExecutorsFirstElExecutor);
        System.out.println("Assignment executors first element control employee - "
                + assignmentsExecutorsFirstElControlEmployee);
        System.out.println("Assignment executors first element executor head - "
                + assignmentsExecutorsFirstElExecutorHead);
        System.out.println("Assignment executors first element organization head - "
                + assignmentsExecutorsFirstElOrganizationHead);
        System.out.println("Assignment executors first element execution deputy head - "
                + assignmentsExecutorsFirstElExecutionDeputyHead);
        System.out.println("Assignment executors first element control deputy head - "
                + assignmentsExecutorsFirstElControlDeputyHead);
        System.out.println("Assignment executors second element organization - "
                + assignmentsExecutorsSecondElOrganization);
        System.out.println("Assignment executors second element executor - "
                + assignmentsExecutorsSecondElExecutor);
        System.out.println("Assignment executors second element control employee - "
                + assignmentsExecutorsSecondElControlEmployee);
        System.out.println("Assignment executors second element executor head - "
                + assignmentsExecutorsSecondElExecutorHead);
        System.out.println("Assignment executors second element organization head - "
                + assignmentsExecutorsSecondElOrganizationHead);
        System.out.println("Assignment executors second element execution deputy head - "
                + assignmentsExecutorsSecondElExecutionDeputyHead);
        System.out.println("Assignment executors second element control deputy head - "
                + assignmentsExecutorsSecondElControlDeputyHead);
        System.out.println("Assignment executors third element organization - "
                + assignmentsExecutorsThirdElOrganization);
        System.out.println("Assignment executors third element executor - "
                + assignmentsExecutorsThirdElExecutor);
        System.out.println("Assignment executors third element control employee - "
                + assignmentsExecutorsThirdElControlEmployee);
        System.out.println("Assignment executors third element executor head - "
                + assignmentsExecutorsThirdElExecutorHead);
        System.out.println("Assignment executors third element organization head - "
                + assignmentsExecutorsThirdElOrganizationHead);
        System.out.println("Assignment executors third element execution deputy head - "
                + assignmentsExecutorsThirdElExecutionDeputyHead);
        System.out.println("Assignment executors third element control deputy head - "
                + assignmentsExecutorsThirdElControlDeputyHead);
        System.out.println("Assignment executors third element organization second executor - "
                + assignmentsExecutorsThirdElOrganizationSecondEx);
        System.out.println("Assignment executors third element executor second executor - "
                + assignmentsExecutorsThirdElExecutorSecondEx);
        System.out.println("Assignment executors third element control employee second executor - "
                + assignmentsExecutorsThirdElControlEmployeeSecondEx);
        System.out.println("Assignment executors third element executor head second executor - "
                + assignmentsExecutorsThirdElExecutorHeadSecondExd);
        System.out.println("Assignment executors third element organization head second executor - "
                + assignmentsExecutorsThirdElOrganizationHeadSecondEx);
        System.out.println("Assignment executors third element execution deputy head second executor - "
                + assignmentsExecutorsThirdElExecutionDeputyHeadSecondEx);
        System.out.println("Assignment executors third element control deputy head second executor - "
                + assignmentsExecutorsThirdElControlDeputyHeadSecondEx);

        Assert.assertTrue(skipRegDate.equals("2024-01-16T06:06:31.485Z"));
        Assert.assertTrue(controlOfficialId.equals("5b3bf8a6b48f00020e000001"));
        Assert.assertTrue(controlSubjectId.equals("60"));
        Assert.assertTrue(documentName.equals("Тест 358 document name"));
        Assert.assertTrue(documentExecutionStateId.equals("11"));
        Assert.assertTrue(documentTypeId.equals("32"));
        Assert.assertTrue(internal.equals("false"));
        Assert.assertTrue(shortDescription.equals("Test 358 short description"));
        Assert.assertTrue(assignmentsNumberFirstEl.equals("1"));
        Assert.assertTrue(assignmentsContentFirstEl.equals("content"));
        Assert.assertTrue(assignmentsNoteFirstEl.equals("note"));
        Assert.assertTrue(assignmentsFirstThemeIdFirstEl.equals("17"));
        Assert.assertTrue(assignmentsSecondThemeIdFirstEl.equals("16"));
        Assert.assertTrue(assignmentsExecutorsFirstElOrganization.equals("6426a7c3379100001e000001"));
        Assert.assertTrue(assignmentsExecutorsFirstElExecutor.equals("56683141a64619317d000001"));
        Assert.assertTrue(assignmentsExecutorsFirstElControlEmployee.equals("53bd047d393f000828000001"));
        Assert.assertTrue(assignmentsExecutorsFirstElExecutorHead.equals("55132cdc17bf000663000001"));
        Assert.assertTrue(assignmentsExecutorsFirstElOrganizationHead.equals("5582e86af5f5011355000001"));
        Assert.assertTrue(assignmentsExecutorsFirstElExecutionDeputyHead.equals("533ee3b263fc00097d000001"));
        Assert.assertTrue(assignmentsExecutorsFirstElControlDeputyHead.equals("54db770074e700c167000001"));
        Assert.assertTrue(assignmentsExecutorsSecondElOrganization.equals("55a8cc52267e000941000001"));
        Assert.assertTrue(assignmentsExecutorsSecondElExecutor.equals("56a0c409044e000dc8000001"));
        Assert.assertTrue(assignmentsExecutorsSecondElControlEmployee.equals("56d3ff213ad301926a000001"));
        Assert.assertTrue(assignmentsExecutorsSecondElExecutorHead.equals("5666c02d350b117255000001"));
        Assert.assertTrue(assignmentsExecutorsSecondElOrganizationHead.equals("546b68a7b08300085a000001"));
        Assert.assertTrue(assignmentsExecutorsSecondElExecutionDeputyHead.equals("55928410e18a0e153e000001"));
        Assert.assertTrue(assignmentsExecutorsSecondElControlDeputyHead.equals("571954972b3000000c000001"));
        Assert.assertTrue(assignmentsExecutorsThirdElOrganization.equals("56f54131966b00473c000001"));
        Assert.assertTrue(assignmentsExecutorsThirdElExecutor.equals("56683141a64619317d000001"));
        Assert.assertTrue(assignmentsExecutorsThirdElControlEmployee.equals("56683141a64619317d000001"));
        Assert.assertTrue(assignmentsExecutorsThirdElExecutorHead.equals("56683141a64619317d000001"));
        Assert.assertTrue(assignmentsExecutorsThirdElOrganizationHead.equals("56683141a64619317d000001"));
        Assert.assertTrue(assignmentsExecutorsThirdElExecutionDeputyHead.equals("56683141a64619317d000001"));
        Assert.assertTrue(assignmentsExecutorsThirdElControlDeputyHead.equals("56683141a64619317d000001"));
        Assert.assertTrue(assignmentsExecutorsThirdElOrganizationSecondEx.equals("54623ebf1244001e4d000001"));
        Assert.assertTrue(assignmentsExecutorsThirdElExecutorSecondEx.equals("56683141a64619317d000001"));
        Assert.assertTrue(assignmentsExecutorsThirdElControlEmployeeSecondEx.equals("56683141a64619317d000001"));
        Assert.assertTrue(assignmentsExecutorsThirdElExecutorHeadSecondExd.equals("56683141a64619317d000001"));
        Assert.assertTrue(assignmentsExecutorsThirdElOrganizationHeadSecondEx.equals("56683141a64619317d000001"));
        Assert.assertTrue(assignmentsExecutorsThirdElExecutionDeputyHeadSecondEx.equals("56683141a64619317d000001"));
        Assert.assertTrue(assignmentsExecutorsThirdElControlDeputyHeadSecondEx.equals("56683141a64619317d000001"));
    }

    @Test
    public void step19(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_137/SKIP_137_step19.json"))
                .put(API_DOCUMENTS + String.format("/%s", idToRemember))
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String firstOrganizationErrorMessage = jsonPath.
                getString("errors.assignments.0.executors.0.organization_id[0]");

        System.out.println("Error message - " + firstOrganizationErrorMessage);

        Assert.assertTrue(firstOrganizationErrorMessage.equals("не может быть пустым"));
    }

    @Test
    public void step20(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_137/SKIP_137_step20.json"))
                .put(API_DOCUMENTS + String.format("/%s", idToRemember))
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String organizationErrorMessage = jsonPath.
                getString("errors.assignments.0.executors.0.organization_id[0]");

        System.out.println("Error message - " + organizationErrorMessage);

        Assert.assertTrue(organizationErrorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step21(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_137/SKIP_137_step21.json"))
                .put(API_DOCUMENTS + String.format("/%s", idToRemember))
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String firstOrganizationErrorMessage = jsonPath.
                getString("errors.assignments.0.executors.1.organization_id[0]");
        String secondOrganizationErrorMessage = jsonPath.
                getString("errors.assignments.1.executors.1.organization_id[0]");

        System.out.println("First message - " + firstOrganizationErrorMessage);
        System.out.println("Second message - " + secondOrganizationErrorMessage);

        Assert.assertTrue(firstOrganizationErrorMessage.equals("уже существует"));
        Assert.assertTrue(secondOrganizationErrorMessage.equals("уже существует"));
    }

    @Test
    public void step22(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_137/SKIP_137_step22.json"))
                .put(API_DOCUMENTS + String.format("/%s", idToRemember))
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String executorErrorMessage = jsonPath.getString("errors.assignments.0.executors.0.executor_id[0]");

        System.out.println("Error message - " + executorErrorMessage);

        Assert.assertTrue(executorErrorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step23(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_137/SKIP_137_step23.json"))
                .put(API_DOCUMENTS + String.format("/%s", idToRemember))
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String controlEmployeeErrorMessage = jsonPath.
                getString("errors.assignments.0.executors.0.control_employee_id[0]");

        System.out.println("Error message - " + controlEmployeeErrorMessage);

        Assert.assertTrue(controlEmployeeErrorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step24(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_137/SKIP_137_step24.json"))
                .put(API_DOCUMENTS + String.format("/%s", idToRemember))
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String executorHeadErrorMessage = jsonPath.
                getString("errors.assignments.0.executors.0.executor_head_id[0]");

        System.out.println("Error message - " + executorHeadErrorMessage);

        Assert.assertTrue(executorHeadErrorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step25(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_137/SKIP_137_step25.json"))
                .put(API_DOCUMENTS + String.format("/%s", idToRemember))
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String organizationHeadErrorMessage = jsonPath.
                getString("errors.assignments.0.executors.0.organization_head_id[0]");

        System.out.println("Error message - " + organizationHeadErrorMessage);

        Assert.assertTrue(organizationHeadErrorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step26(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_137/SKIP_137_step26.json"))
                .put(API_DOCUMENTS + String.format("/%s", idToRemember))
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String executionDeputyHeadErrorMessage = jsonPath.
                getString("errors.assignments.0.executors.0.execution_deputy_head_id[0]");

        System.out.println("Error message - " + executionDeputyHeadErrorMessage);

        Assert.assertTrue(executionDeputyHeadErrorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step27(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_137/SKIP_137_step27.json"))
                .put(API_DOCUMENTS + String.format("/%s", idToRemember))
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String controlDeputyHeadErrorMessage = jsonPath.
                getString("errors.assignments.0.executors.0.control_deputy_head_id[0]");

        System.out.println("Error message - " + controlDeputyHeadErrorMessage);

        Assert.assertTrue(controlDeputyHeadErrorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step28(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 6)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_137/SKIP_137_step27.json"))
                .put(API_DOCUMENTS + String.format("/%s", idToRemember))
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.getString("error");

        System.out.println("Error message - " + error);

        Assert.assertTrue(error.equals("Доступ к ресурсу запрещен"));
    }
}

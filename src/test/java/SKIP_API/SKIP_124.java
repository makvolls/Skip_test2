package SKIP_API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SKIP_124 {

    private static String API_DOCUMENTS = "http://api.skip.rtech.ru/v1/documents";
    String idDocuments;
    String nonExistValue = "1000"; // Used in step 08

    @Test
    public void step01(){
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("skip_reg_date","2023-12-20T06:06:31.485Z");
        requestBody.put("control_official_id","59e9be1f83c0000040000001");
        requestBody.put("control_subject_id","47");
        requestBody.put("document_name","Тест 205");
        requestBody.put("document_execution_state_id","1");
        requestBody.put("skip_document_type_id","1");
        requestBody.put("internal","false");
        requestBody.put("short_description","Test 205");
        Response response = given()
                .when()
                .header("Content-Type","application/json")
                .header("Test-Authorization", 1)
                .body(requestBody)
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        // Get required parameters values from the response body
        String skipRegDate = jsonPath.get("data.skip_reg_date").toString();
        String controlOfficialId = jsonPath.get("data.control_official.id").toString();
        String controlSubjectId = jsonPath.get("data.control_subject.id").toString();
        String documentName = jsonPath.get("data.document_name").toString();
        String documentExecutionStateId = jsonPath.get("data.document_execution_state.id").toString();
        String skipDocumentTypeId = jsonPath.get("data.skip_document_type.id").toString();
        String internal = jsonPath.get("data.internal").toString();
        String shortDescription = jsonPath.get("data.short_description").toString();
        String assignments = jsonPath.get("data.assignments").toString();

        // Assert values
        Assert.assertTrue(skipRegDate.equals("2023-12-20T06:06:31.485Z"));
        Assert.assertTrue(controlOfficialId.equals("59e9be1f83c0000040000001"));
        Assert.assertTrue(controlSubjectId.equals("47"));
        Assert.assertTrue(documentName.equals("Тест 205"));
        Assert.assertTrue(documentExecutionStateId.equals("1"));
        Assert.assertTrue(skipDocumentTypeId.equals("1"));
        Assert.assertTrue(internal.equals("false"));
        Assert.assertTrue(shortDescription.equals("Test 205"));
        Assert.assertTrue(assignments.equals("[]"));
    }

    @Test
    public void step02(){
        Response response = given()
                .when()
                .header("Content-Type","application/json")
                .header("Test-Authorization", 1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_124_step02.json"))
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String skipRegDate = jsonPath.get("data.skip_reg_date").toString();
        String controlOfficialId = jsonPath.get("data.control_official.id").toString();
        String controlSubjectId = jsonPath.get("data.control_subject.id").toString();
        String documentName = jsonPath.get("data.document_name").toString();
        String documentExecutionStateId = jsonPath.get("data.document_execution_state.id").toString();
        String skipDocumentTypeId = jsonPath.get("data.skip_document_type.id").toString();
        String internal = jsonPath.get("data.internal").toString();
        String shortDescription = jsonPath.get("data.short_description").toString();
        String assignmentsNumber = jsonPath.get("data.assignments[0].number").toString();
        String assignmentsContent = jsonPath.get("data.assignments[0].content").toString();

        Assert.assertTrue(skipRegDate.equals("2023-12-20T06:06:31.485Z"));
        Assert.assertTrue(controlOfficialId.equals("59e9be1f83c0000040000001"));
        Assert.assertTrue(controlSubjectId.equals("47"));
        Assert.assertTrue(documentName.equals("Тест 329"));
        Assert.assertTrue(documentExecutionStateId.equals("1"));
        Assert.assertTrue(skipDocumentTypeId.equals("1"));
        Assert.assertTrue(internal.equals("false"));
        Assert.assertTrue(shortDescription.equals("Test 329"));
        Assert.assertTrue(assignmentsNumber.equals("1234"));
        Assert.assertTrue(assignmentsContent.equals("assignments1"));
    }

    @Test
    public void step03(){
        Response response = given()
                .when()
                .header("Content-Type","application/json")
                .header("Test-Authorization", 1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_124_step03.json"))
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String numberError = jsonPath.get("errors.assignments.0.number[0]").toString();
        String contentError = jsonPath.get("errors.assignments.0.content[0]").toString();

        Assert.assertTrue(numberError.equals("не может быть пустым"));
        Assert.assertTrue(contentError.equals("не может быть пустым"));
    }

    @Test
    public void step04(){
        Response response = given()
                .when()
                .header("Content-Type","application/json")
                .header("Test-Authorization", 1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_124_step04.json"))
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        // Get id created element and store it in the variable
        idDocuments = jsonPath.get("data.id").toString();

        String skipRegDate = jsonPath.get("data.skip_reg_date").toString();
        String controlOfficialId = jsonPath.get("data.control_official.id").toString();
        String controlSubjectId = jsonPath.get("data.control_subject.id").toString();
        String documentName = jsonPath.get("data.document_name").toString();
        String documentExecutionStateId = jsonPath.get("data.document_execution_state.id").toString();
        String skipDocumentTypeId = jsonPath.get("data.skip_document_type.id").toString();
        String internal = jsonPath.get("data.internal").toString();
        String shortDescription = jsonPath.get("data.short_description").toString();
        String assignmentsNumber = jsonPath.get("data.assignments[0].number").toString();
        String assignmentsContent = jsonPath.get("data.assignments[0].content").toString();
        String assignmentNote = jsonPath.get("data.assignments[0].note").toString();
        String assignmentThemeIdFirst = jsonPath.get("data.assignments[0].themes[0].id").toString();
        String assignmentThemeIdSecond = jsonPath.get("data.assignments[0].themes[1].id").toString();

        Assert.assertTrue(skipRegDate.equals("2023-12-20T06:06:31.485Z"));
        Assert.assertTrue(controlOfficialId.equals("59e9be1f83c0000040000001"));
        Assert.assertTrue(controlSubjectId.equals("47"));
        Assert.assertTrue(documentName.equals("Тест 329"));
        Assert.assertTrue(documentExecutionStateId.equals("1"));
        Assert.assertTrue(skipDocumentTypeId.equals("1"));
        Assert.assertTrue(internal.equals("false"));
        Assert.assertTrue(shortDescription.equals("Test 329"));
        Assert.assertTrue(assignmentsNumber.equals("12345"));
        Assert.assertTrue(assignmentsContent.equals("content"));
        Assert.assertTrue(assignmentNote.equals("note"));
        Assert.assertTrue(assignmentThemeIdFirst.equals("35"));
        Assert.assertTrue(assignmentThemeIdSecond.equals("3"));
    }

    @Test
    public void step05(){
        Response response = given()
                .when()
                .header("Content-Type","application/json")
                .header("Test-Authorization", 1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_124_step05.json"))
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String skipRegDate = jsonPath.get("data.skip_reg_date").toString();
        String controlOfficialId = jsonPath.get("data.control_official.id").toString();
        String controlSubjectId = jsonPath.get("data.control_subject.id").toString();
        String documentName = jsonPath.get("data.document_name").toString();
        String documentExecutionStateId = jsonPath.get("data.document_execution_state.id").toString();
        String skipDocumentTypeId = jsonPath.get("data.skip_document_type.id").toString();
        String internal = jsonPath.get("data.internal").toString();
        String shortDescription = jsonPath.get("data.short_description").toString();
        String assignmentsNumberFirstElement = jsonPath.get("data.assignments[0].number").toString();
        String assignmentsContentFirstElement = jsonPath.get("data.assignments[0].content").toString();
        String assignmentNoteFirstElement = jsonPath.get("data.assignments[0].note").toString();
        String assignmentThemeIdFirstFirstElementAssign = jsonPath.get("data.assignments[0].themes[0].id").toString();
        String assignmentThemeIdSecondFirstElementAssign = jsonPath.get("data.assignments[0].themes[1].id").toString();
        // Second element of assignments
        String assignmentsNumberSecondElement = jsonPath.get("data.assignments[1].number").toString();
        String assignmentsContentSecondElement = jsonPath.get("data.assignments[1].content").toString();
        String assignmentNoteSecondElement = jsonPath.get("data.assignments[1].note").toString();
        String assignmentThemeIdFirstSecondElementAssign = jsonPath.get("data.assignments[1].themes[0].id").toString();
        String assignmentThemeIdSecondSecondElementAssign = jsonPath.get("data.assignments[1].themes[1].id").toString();

        Assert.assertTrue(skipRegDate.equals("2023-12-20T06:06:31.485Z"));
        Assert.assertTrue(controlOfficialId.equals("59e9be1f83c0000040000001"));
        Assert.assertTrue(controlSubjectId.equals("47"));
        Assert.assertTrue(documentName.equals("Тест 329 document name"));
        Assert.assertTrue(documentExecutionStateId.equals("1"));
        Assert.assertTrue(skipDocumentTypeId.equals("1"));
        Assert.assertTrue(internal.equals("false"));
        Assert.assertTrue(shortDescription.equals("Test 329 short description"));
        Assert.assertTrue(assignmentsNumberFirstElement.equals("12345"));
        Assert.assertTrue(assignmentsContentFirstElement.equals("content"));
        Assert.assertTrue(assignmentNoteFirstElement.equals("note"));
        Assert.assertTrue(assignmentThemeIdFirstFirstElementAssign.equals("35"));
        Assert.assertTrue(assignmentThemeIdSecondFirstElementAssign.equals("3"));
        Assert.assertTrue(assignmentsNumberSecondElement.equals("67890"));
        Assert.assertTrue(assignmentsContentSecondElement.equals("content-content"));
        Assert.assertTrue(assignmentNoteSecondElement.equals("note-note"));
        Assert.assertTrue(assignmentThemeIdFirstSecondElementAssign.equals("6"));
        Assert.assertTrue(assignmentThemeIdSecondSecondElementAssign.equals("7"));
    }

    @Test
    public void step06(){
        Response response = given()
                .when()
                .header("Content-Type","application/json")
                .header("Test-Authorization", 6)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_124_step05.json"))
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.get("error").toString();
        Assert.assertTrue(error.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void step07(){
        Response response = given()
                .when()
                .header("Content-Type","application/json")
                .header("Test-Authorization", 1)
                //.body(new File("src/test/java/SKIP_API/json_files/SKIP_124_step05.json"))
                .get(API_DOCUMENTS + String.format("/%s",idDocuments)) // "/218" - put id to url and get response
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String skipRegDate = jsonPath.get("data.skip_reg_date").toString();
        String controlOfficialId = jsonPath.get("data.control_official.id").toString();
        String controlSubjectId = jsonPath.get("data.control_subject.id").toString();
        String documentName = jsonPath.get("data.document_name").toString();
        String documentExecutionStateId = jsonPath.get("data.document_execution_state.id").toString();
        String skipDocumentTypeId = jsonPath.get("data.skip_document_type.id").toString();
        String internal = jsonPath.get("data.internal").toString();
        String shortDescription = jsonPath.get("data.short_description").toString();
        String assignmentsNumber = jsonPath.get("data.assignments[0].number").toString();
        String assignmentsContent = jsonPath.get("data.assignments[0].content").toString();
        String assignmentNote = jsonPath.get("data.assignments[0].note").toString();
        String assignmentThemeIdFirst = jsonPath.get("data.assignments[0].themes[0].id").toString();
        String assignmentThemeIdSecond = jsonPath.get("data.assignments[0].themes[1].id").toString();

        Assert.assertTrue(skipRegDate.equals("2023-12-20T06:06:31.485Z"));
        Assert.assertTrue(controlOfficialId.equals("59e9be1f83c0000040000001"));
        Assert.assertTrue(controlSubjectId.equals("47"));
        Assert.assertTrue(documentName.equals("Тест 329"));
        Assert.assertTrue(documentExecutionStateId.equals("1"));
        Assert.assertTrue(skipDocumentTypeId.equals("1"));
        Assert.assertTrue(internal.equals("false"));
        Assert.assertTrue(shortDescription.equals("Test 329"));
        Assert.assertTrue(assignmentsNumber.equals("12345"));
        Assert.assertTrue(assignmentsContent.equals("content"));
        Assert.assertTrue(assignmentNote.equals("note"));
        Assert.assertTrue(assignmentThemeIdFirst.equals("35"));
        Assert.assertTrue(assignmentThemeIdSecond.equals("3"));
    }

    @Test
    public void step08(){
        Response response = given()
                .when()
                .header("Content-Type","application/json")
                .header("Test-Authorization", 1)
                //.body(new File("src/test/java/SKIP_API/json_files/SKIP_124_step05.json"))
                .get(API_DOCUMENTS + String.format("/%s",nonExistValue))
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.get("error").toString();
        Assert.assertTrue(error.equals(
                String.format("Запись для Documents::Document с id = %s не найдена",nonExistValue)));
    }

    @Test
    public void step09(){
        Response response = given()
                .when()
                .header("Content-Type","application/json")
                .header("Test-Authorization", 6)
                //.body(new File("src/test/java/SKIP_API/json_files/SKIP_124_step05.json"))
                .get(API_DOCUMENTS + String.format("/%s",idDocuments))
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.get("error").toString();
        Assert.assertTrue(error.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void step10(){ // Need approve
        Response response = given()
                .when()
                .header("Content-Type","application/json")
                .header("Test-Authorization", 1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_124_step10.json"))
                .put(API_DOCUMENTS + String.format("/%s",idDocuments))
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String skipRegDate = jsonPath.get("data.skip_reg_date").toString();
        String controlOfficialId = jsonPath.get("data.control_official.id").toString();
        String controlSubjectId = jsonPath.get("data.control_subject.id").toString();
        String documentName = jsonPath.get("data.document_name").toString();
        String documentExecutionStateId = jsonPath.get("data.document_execution_state.id").toString();
        String skipDocumentTypeId = jsonPath.get("data.skip_document_type.id").toString();
        String internal = jsonPath.get("data.internal").toString();
        String shortDescription = jsonPath.get("data.short_description").toString();
        String assignmentsNumberFirstElement = jsonPath.get("data.assignments[0].number").toString();
        String assignmentsContentFirstElement = jsonPath.get("data.assignments[0].content").toString();
        String assignmentNoteFirstElement = jsonPath.get("data.assignments[0].note").toString();
        String assignmentThemeIdFirstFirstElementAssign = jsonPath.get("data.assignments[0].themes[0].id").toString();
        String assignmentThemeIdSecondFirstElementAssign = jsonPath.get("data.assignments[0].themes[1].id").toString();
        String assignmentsNumberSecondElement = jsonPath.get("data.assignments[1].number").toString();
        String assignmentsContentSecondElement = jsonPath.get("data.assignments[1].content").toString();
        String assignmentNoteSecondElement = jsonPath.get("data.assignments[1].note").toString();
        String assignmentThemeIdFirstSecondElementAssign = jsonPath.get("data.assignments[1].themes[0].id").toString();
        String assignmentThemeIdSecondSecondElementAssign = jsonPath.get("data.assignments[1].themes[1].id").toString();

        Assert.assertTrue(skipRegDate.equals("2023-12-20T06:06:31.485Z"));
        Assert.assertTrue(controlOfficialId.equals("59e9be1f83c0000040000001"));
        Assert.assertTrue(controlSubjectId.equals("47"));
        Assert.assertTrue(documentName.equals("Тест 329"));
        Assert.assertTrue(documentExecutionStateId.equals("1"));
        Assert.assertTrue(skipDocumentTypeId.equals("1"));
        Assert.assertTrue(internal.equals("false"));
        Assert.assertTrue(shortDescription.equals("Test 329"));
        Assert.assertTrue(assignmentsNumberFirstElement.equals("12345"));
        Assert.assertTrue(assignmentsContentFirstElement.equals("content"));
        Assert.assertTrue(assignmentNoteFirstElement.equals("note"));
        Assert.assertTrue(assignmentThemeIdFirstFirstElementAssign.equals("35"));
        Assert.assertTrue(assignmentThemeIdSecondFirstElementAssign.equals("3"));
        Assert.assertTrue(assignmentsNumberSecondElement.equals("111"));
        Assert.assertTrue(assignmentsContentSecondElement.equals("some text"));
        Assert.assertTrue(assignmentNoteSecondElement.equals("some note"));
        Assert.assertTrue(assignmentThemeIdFirstSecondElementAssign.equals("17"));
        Assert.assertTrue(assignmentThemeIdSecondSecondElementAssign.equals("16"));

    }

    @Test
    public void step11(){
        Response response = given()
                .when()
                .header("Content-Type","application/json")
                .header("Test-Authorization", 1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_124_step11.json"))
                .put(API_DOCUMENTS + String.format("/%s",idDocuments))
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String numberError = jsonPath.get("errors.assignments.1.number[0]").toString();
        String contentError = jsonPath.get("errors.assignments.1.content[0]").toString();

        Assert.assertTrue(numberError.equals("не может быть пустым"));
        Assert.assertTrue(contentError.equals("не может быть пустым"));
    }

    @Test
    public void step12(){
        Response response = given()
                .when()
                .header("Content-Type","application/json")
                .header("Test-Authorization", 6)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_124_step10.json"))
                .put(API_DOCUMENTS + String.format("/%s",idDocuments))
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.get("error").toString();
        //System.out.println(error);
        Assert.assertTrue(error.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void step13(){
        Response response = given()
                .when()
                .header("Content-Type","application/json")
                .header("Test-Authorization", 1)
                //.body(new File("src/test/java/SKIP_API/json_files/SKIP_124_step11.json"))
                .put(API_DOCUMENTS + String.format("/%s",idDocuments))
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String skipRegDate = jsonPath.get("data.skip_reg_date").toString();
        String controlOfficialId = jsonPath.get("data.control_official.id").toString();
        String controlSubjectId = jsonPath.get("data.control_subject.id").toString();
        String documentName = jsonPath.get("data.document_name").toString();
        String documentExecutionStateId = jsonPath.get("data.document_execution_state.id").toString();
        String skipDocumentTypeId = jsonPath.get("data.skip_document_type.id").toString();
        String internal = jsonPath.get("data.internal").toString();
        String shortDescription = jsonPath.get("data.short_description").toString();
        String assignmentsNumberFirstElement = jsonPath.get("data.assignments[0].number").toString();
        String assignmentsContentFirstElement = jsonPath.get("data.assignments[0].content").toString();
        String assignmentNoteFirstElement = jsonPath.get("data.assignments[0].note").toString();
        String assignmentThemeIdFirstFirstElementAssign = jsonPath.get("data.assignments[0].themes[0].id").toString();
        String assignmentThemeIdSecondFirstElementAssign = jsonPath.get("data.assignments[0].themes[1].id").toString();
        String assignmentsNumberSecondElement = jsonPath.get("data.assignments[1].number").toString();
        String assignmentsContentSecondElement = jsonPath.get("data.assignments[1].content").toString();
        String assignmentNoteSecondElement = jsonPath.get("data.assignments[1].note").toString();
        String assignmentThemeIdFirstSecondElementAssign = jsonPath.get("data.assignments[1].themes[0].id").toString();
        String assignmentThemeIdSecondSecondElementAssign = jsonPath.get("data.assignments[1].themes[1].id").toString();

        Assert.assertTrue(skipRegDate.equals("2023-12-20T06:06:31.485Z"));
        Assert.assertTrue(controlOfficialId.equals("59e9be1f83c0000040000001"));
        Assert.assertTrue(controlSubjectId.equals("47"));
        Assert.assertTrue(documentName.equals("Тест 329"));
        Assert.assertTrue(documentExecutionStateId.equals("1"));
        Assert.assertTrue(skipDocumentTypeId.equals("1"));
        Assert.assertTrue(internal.equals("false"));
        Assert.assertTrue(shortDescription.equals("Test 329"));
        Assert.assertTrue(assignmentsNumberFirstElement.equals("12345"));
        Assert.assertTrue(assignmentsContentFirstElement.equals("content"));
        Assert.assertTrue(assignmentNoteFirstElement.equals("note"));
        Assert.assertTrue(assignmentThemeIdFirstFirstElementAssign.equals("35"));
        Assert.assertTrue(assignmentThemeIdSecondFirstElementAssign.equals("3"));
        Assert.assertTrue(assignmentsNumberSecondElement.equals("111"));
        Assert.assertTrue(assignmentsContentSecondElement.equals("some text"));
        Assert.assertTrue(assignmentNoteSecondElement.equals("some note"));
        Assert.assertTrue(assignmentThemeIdFirstSecondElementAssign.equals("17"));
        Assert.assertTrue(assignmentThemeIdSecondSecondElementAssign.equals("16"));
    }
}

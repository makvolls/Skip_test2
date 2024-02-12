package SKIP_API;

import API.BasicAssignmentExecutionStates;
import API.BasicDeadlineBases;
import API.DTO.AssignmentExecutionStatesDto.RootAssignmentExecutionStates;
import API.DTO.DeadlineBasesDto.RootDeadlineBases;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class SKIP_166 {
    int idAut1 = 1;


    RootDeadlineBases DeadlineBases;
    RootAssignmentExecutionStates AssignmentEs;
    int idDeadlineBases1, idDeadlineBases2;
    int idAssignmentEs1, idAssignmentEs2;
    int idDocuments1;
    int idAssigments1;
    String API_DOCUMENTS = "http://api.skip.rtech.ru/v1/documents";
    int idPeriodicity1 = 38;
    int idPeriodicity2 = 50;


    @BeforeClass
    public void deadlineBases() {
        DeadlineBases = BasicDeadlineBases.getDeadlineBasesList(idAut1);
        idDeadlineBases1 = DeadlineBases.data[0].id;
        idDeadlineBases2 = DeadlineBases.data[1].id;
    }

    @BeforeClass
    public void assignmentEs() {
        AssignmentEs = BasicAssignmentExecutionStates.getAssignmentExecutionStatesList(idAut1);
        idAssignmentEs1 = AssignmentEs.data[0].id;
        idAssignmentEs2 = AssignmentEs.data[1].id;
    }

    @Test
    public void step01() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_166/SKIP_166_step01.json"))
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.getString("errors.assignments.0.executor_deadline_calculation.periodicity_day[0]");
        Assert.assertTrue(error.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step02() {

        // Параметры запроса
        JsonObject requestPayload = new JsonObject();
        requestPayload.addProperty("skip_reg_date", "2023-12-20T06:06:31.485Z");
        requestPayload.addProperty("control_official_id", "59e9be1f83c0000040000001");
        requestPayload.addProperty("control_subject_id", 47);
        requestPayload.addProperty("document_name", "Тест 205");
        requestPayload.addProperty("document_execution_state_id", 1);
        requestPayload.addProperty("skip_document_type_id", 1);
        requestPayload.addProperty("internal", false);
        requestPayload.addProperty("short_description", "Test 205");

        JsonArray assignmentsArray = new JsonArray();

        JsonObject assignment = new JsonObject();
        assignment.addProperty("number", "test");
        assignment.addProperty("content", "test");

        JsonObject executorDeadlineCalculation = new JsonObject();
        executorDeadlineCalculation.addProperty("periodicity_id", idPeriodicity1);
        executorDeadlineCalculation.addProperty("start_date", "2024-02-07T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("deadline_basis_id", idDeadlineBases1);
        executorDeadlineCalculation.addProperty("execution_state_id", idAssignmentEs1);
        executorDeadlineCalculation.addProperty("deadline_date", "2024-02-07T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("periodicity_day", 1);
        executorDeadlineCalculation.addProperty("note", "test");

        assignment.add("executor_deadline_calculation", executorDeadlineCalculation);
        assignmentsArray.add(assignment);

        requestPayload.add("assignments", assignmentsArray);

        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        // Проверка кода ответа
        Assert.assertEquals(201, response.statusCode());

        // Проверка содержимого ответа
        JsonPath jsonPath = response.jsonPath();
        int idEdc = jsonPath.get("data.assignments[0].executor_deadline_calculation.id");
        String startDate = jsonPath.get("data.assignments[0].executor_deadline_calculation.start_date");
        String deadlineDate = jsonPath.get("data.assignments[0].executor_deadline_calculation.deadline_date");
        int periodicityDay = jsonPath.get("data.assignments[0].executor_deadline_calculation.periodicity_day");
        String note = jsonPath.getString("data.assignments[0].executor_deadline_calculation.note");
        int periodicityId = jsonPath.get("data.assignments[0].executor_deadline_calculation.periodicity.id");
        int deadlaneBasesId = jsonPath.get("data.assignments[0].executor_deadline_calculation.deadline_basis.id");
        int assigmentsEs = jsonPath.get("data.assignments[0].executor_deadline_calculation.execution_state.id");
        int assigmentsId = jsonPath.get("data.assignments[0].id");
        int docId = jsonPath.get("data.id");

        idAssigments1 = assigmentsId;
        idDocuments1 = docId;


        Assert.assertTrue(startDate.equals("2024-02-07T06:18:47.149Z"));
        Assert.assertTrue(deadlineDate.equals("2024-02-07T06:18:47.149Z"));
        Assert.assertTrue(idEdc != 0);
        Assert.assertTrue(periodicityDay == 1);
        Assert.assertTrue(note.equals("test"));
        Assert.assertTrue(periodicityId == idPeriodicity1);
        Assert.assertTrue(deadlaneBasesId == idDeadlineBases1);
        Assert.assertTrue(assigmentsEs == idAssignmentEs1);

    }

    @Test
    public void step03() {

        // Параметры запроса
        JsonObject requestPayload = new JsonObject();
        requestPayload.addProperty("skip_reg_date", "2023-12-20T06:06:31.485Z");
        requestPayload.addProperty("control_official_id", "59e9be1f83c0000040000001");
        requestPayload.addProperty("control_subject_id", 47);
        requestPayload.addProperty("document_name", "Тест 205");
        requestPayload.addProperty("document_execution_state_id", 1);
        requestPayload.addProperty("skip_document_type_id", 1);
        requestPayload.addProperty("internal", false);
        requestPayload.addProperty("short_description", "Test 205");

        JsonArray assignmentsArray = new JsonArray();

        JsonObject assignment = new JsonObject();
        assignment.addProperty("number", "test");
        assignment.addProperty("content", "test");

        JsonObject executorDeadlineCalculation = new JsonObject();
        executorDeadlineCalculation.addProperty("periodicity_id", idPeriodicity1);
        executorDeadlineCalculation.addProperty("start_date", "2024-02-07T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("deadline_basis_id", idDeadlineBases1);
        executorDeadlineCalculation.addProperty("execution_state_id", idAssignmentEs1);


        assignment.add("executor_deadline_calculation", executorDeadlineCalculation);
        assignmentsArray.add(assignment);

        requestPayload.add("assignments", assignmentsArray);

        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        // Проверка кода ответа
        Assert.assertEquals(201, response.statusCode());

        // Проверка содержимого ответа
        JsonPath jsonPath = response.jsonPath();
        int idEdc = jsonPath.get("data.assignments[0].executor_deadline_calculation.id");
        String startDate = jsonPath.get("data.assignments[0].executor_deadline_calculation.start_date");
        String deadlineDate = jsonPath.get("data.assignments[0].executor_deadline_calculation.deadline_date");
        String periodicityDay = jsonPath.get("data.assignments[0].executor_deadline_calculation.periodicity_day");
        String note = jsonPath.getString("data.assignments[0].executor_deadline_calculation.note");
        int periodicityId = jsonPath.get("data.assignments[0].executor_deadline_calculation.periodicity.id");
        int deadlaneBasesId = jsonPath.get("data.assignments[0].executor_deadline_calculation.deadline_basis.id");
        int assigmentsEs = jsonPath.get("data.assignments[0].executor_deadline_calculation.execution_state.id");
        int assigmentsId = jsonPath.get("data.assignments[0].id");
        int docId = jsonPath.get("data.id");

        idAssigments1 = assigmentsId;
        idDocuments1 = docId;


        Assert.assertTrue(startDate.equals("2024-02-07T06:18:47.149Z"));
        Assert.assertNull(deadlineDate);
        Assert.assertTrue(idEdc != 0);
        Assert.assertNull(periodicityDay);
        Assert.assertNull(note);
        Assert.assertTrue(periodicityId == idPeriodicity1);
        Assert.assertTrue(deadlaneBasesId == idDeadlineBases1);
        Assert.assertTrue(assigmentsEs == idAssignmentEs1);

    }

    @Test
    public void step04() {

        // Параметры запроса
        JsonObject requestPayload = new JsonObject();
        requestPayload.addProperty("skip_reg_date", "2023-12-20T06:06:31.485Z");
        requestPayload.addProperty("control_official_id", "59e9be1f83c0000040000001");
        requestPayload.addProperty("control_subject_id", 47);
        requestPayload.addProperty("document_name", "Тест 205");
        requestPayload.addProperty("document_execution_state_id", 1);
        requestPayload.addProperty("skip_document_type_id", 1);
        requestPayload.addProperty("internal", false);
        requestPayload.addProperty("short_description", "Test 205");

        JsonArray assignmentsArray = new JsonArray();

        JsonObject assignment = new JsonObject();
        assignment.addProperty("number", "test");
        assignment.addProperty("content", "test");

        JsonObject executorDeadlineCalculation = new JsonObject();
        executorDeadlineCalculation.addProperty("periodicity_id", idPeriodicity2);
        executorDeadlineCalculation.addProperty("start_date", "2024-02-07T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("deadline_basis_id", idDeadlineBases1);
        executorDeadlineCalculation.addProperty("execution_state_id", idAssignmentEs1);


        assignment.add("executor_deadline_calculation", executorDeadlineCalculation);
        assignmentsArray.add(assignment);

        requestPayload.add("assignments", assignmentsArray);

        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();


        // Проверка содержимого ответа
        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.assignments.0.executor_deadline_calculation.periodicity_day[0]");
        Assert.assertTrue(error.equals("не может быть пустым"));

    }

    @Test
    public void step05() {

        // Параметры запроса
        JsonObject requestPayload = new JsonObject();
        requestPayload.addProperty("skip_reg_date", "2023-12-20T06:06:31.485Z");
        requestPayload.addProperty("control_official_id", "59e9be1f83c0000040000001");
        requestPayload.addProperty("control_subject_id", 47);
        requestPayload.addProperty("document_name", "Тест 205");
        requestPayload.addProperty("document_execution_state_id", 1);
        requestPayload.addProperty("skip_document_type_id", 1);
        requestPayload.addProperty("internal", false);
        requestPayload.addProperty("short_description", "Test 205");

        JsonArray assignmentsArray = new JsonArray();

        JsonObject assignment = new JsonObject();
        assignment.addProperty("number", "test");
        assignment.addProperty("content", "test");

        JsonObject executorDeadlineCalculation = new JsonObject();
        executorDeadlineCalculation.addProperty("start_date", "2024-02-07T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("deadline_basis_id", idDeadlineBases1);
        executorDeadlineCalculation.addProperty("execution_state_id", idAssignmentEs1);
        executorDeadlineCalculation.addProperty("deadline_date", "2024-02-07T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("periodicity_day", 1);
        executorDeadlineCalculation.addProperty("note", "test");

        assignment.add("executor_deadline_calculation", executorDeadlineCalculation);
        assignmentsArray.add(assignment);

        requestPayload.add("assignments", assignmentsArray);

        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.assignments.0.executor_deadline_calculation.periodicity_id[0]");
        Assert.assertTrue(error.equals("не может быть пустым"));
    }

    @Test
    public void step06() {

        // Параметры запроса
        JsonObject requestPayload = new JsonObject();
        requestPayload.addProperty("skip_reg_date", "2023-12-20T06:06:31.485Z");
        requestPayload.addProperty("control_official_id", "59e9be1f83c0000040000001");
        requestPayload.addProperty("control_subject_id", 47);
        requestPayload.addProperty("document_name", "Тест 205");
        requestPayload.addProperty("document_execution_state_id", 1);
        requestPayload.addProperty("skip_document_type_id", 1);
        requestPayload.addProperty("internal", false);
        requestPayload.addProperty("short_description", "Test 205");

        JsonArray assignmentsArray = new JsonArray();

        JsonObject assignment = new JsonObject();
        assignment.addProperty("number", "test");
        assignment.addProperty("content", "test");

        JsonObject executorDeadlineCalculation = new JsonObject();
        executorDeadlineCalculation.addProperty("periodicity_id", 2142354653);
        executorDeadlineCalculation.addProperty("start_date", "2024-02-07T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("deadline_basis_id", idDeadlineBases1);
        executorDeadlineCalculation.addProperty("execution_state_id", idAssignmentEs1);
        executorDeadlineCalculation.addProperty("deadline_date", "2024-02-07T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("periodicity_day", 1);
        executorDeadlineCalculation.addProperty("note", "test");

        assignment.add("executor_deadline_calculation", executorDeadlineCalculation);
        assignmentsArray.add(assignment);

        requestPayload.add("assignments", assignmentsArray);

        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.assignments.0.executor_deadline_calculation.periodicity_id[0]");
        Assert.assertTrue(error.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step07() {

        // Параметры запроса
        JsonObject requestPayload = new JsonObject();
        requestPayload.addProperty("skip_reg_date", "2023-12-20T06:06:31.485Z");
        requestPayload.addProperty("control_official_id", "59e9be1f83c0000040000001");
        requestPayload.addProperty("control_subject_id", 47);
        requestPayload.addProperty("document_name", "Тест 205");
        requestPayload.addProperty("document_execution_state_id", 1);
        requestPayload.addProperty("skip_document_type_id", 1);
        requestPayload.addProperty("internal", false);
        requestPayload.addProperty("short_description", "Test 205");

        JsonArray assignmentsArray = new JsonArray();

        JsonObject assignment = new JsonObject();
        assignment.addProperty("number", "test");
        assignment.addProperty("content", "test");

        JsonObject executorDeadlineCalculation = new JsonObject();
        executorDeadlineCalculation.addProperty("periodicity_id", idPeriodicity1);
        executorDeadlineCalculation.addProperty("start_date", "");
        executorDeadlineCalculation.addProperty("deadline_basis_id", idDeadlineBases1);
        executorDeadlineCalculation.addProperty("execution_state_id", idAssignmentEs1);
        executorDeadlineCalculation.addProperty("deadline_date", "2024-02-07T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("periodicity_day", 1);
        executorDeadlineCalculation.addProperty("note", "test");

        assignment.add("executor_deadline_calculation", executorDeadlineCalculation);
        assignmentsArray.add(assignment);

        requestPayload.add("assignments", assignmentsArray);

        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.assignments.0.executor_deadline_calculation.start_date[0]");
        Assert.assertTrue(error.equals("не может быть пустым"));
    }

    @Test
    public void step08() {

        // Параметры запроса
        JsonObject requestPayload = new JsonObject();
        requestPayload.addProperty("skip_reg_date", "2023-12-20T06:06:31.485Z");
        requestPayload.addProperty("control_official_id", "59e9be1f83c0000040000001");
        requestPayload.addProperty("control_subject_id", 47);
        requestPayload.addProperty("document_name", "Тест 205");
        requestPayload.addProperty("document_execution_state_id", 1);
        requestPayload.addProperty("skip_document_type_id", 1);
        requestPayload.addProperty("internal", false);
        requestPayload.addProperty("short_description", "Test 205");

        JsonArray assignmentsArray = new JsonArray();

        JsonObject assignment = new JsonObject();
        assignment.addProperty("number", "test");
        assignment.addProperty("content", "test");

        JsonObject executorDeadlineCalculation = new JsonObject();
        executorDeadlineCalculation.addProperty("periodicity_id", idPeriodicity1);
        executorDeadlineCalculation.addProperty("start_date", "3213453641234");
        executorDeadlineCalculation.addProperty("deadline_basis_id", idDeadlineBases1);
        executorDeadlineCalculation.addProperty("execution_state_id", idAssignmentEs1);
        executorDeadlineCalculation.addProperty("deadline_date", "2024-02-07T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("periodicity_day", 1);
        executorDeadlineCalculation.addProperty("note", "test");

        assignment.add("executor_deadline_calculation", executorDeadlineCalculation);
        assignmentsArray.add(assignment);

        requestPayload.add("assignments", assignmentsArray);

        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.assignments.0.executor_deadline_calculation.start_date[0]");
        Assert.assertTrue(error.equals("имеет неверное значение"));
    }

    @Test
    public void step09() {

        // Параметры запроса
        JsonObject requestPayload = new JsonObject();
        requestPayload.addProperty("skip_reg_date", "2023-12-20T06:06:31.485Z");
        requestPayload.addProperty("control_official_id", "59e9be1f83c0000040000001");
        requestPayload.addProperty("control_subject_id", 47);
        requestPayload.addProperty("document_name", "Тест 205");
        requestPayload.addProperty("document_execution_state_id", 1);
        requestPayload.addProperty("skip_document_type_id", 1);
        requestPayload.addProperty("internal", false);
        requestPayload.addProperty("short_description", "Test 205");

        JsonArray assignmentsArray = new JsonArray();

        JsonObject assignment = new JsonObject();
        assignment.addProperty("number", "test");
        assignment.addProperty("content", "test");

        JsonObject executorDeadlineCalculation = new JsonObject();
        executorDeadlineCalculation.addProperty("periodicity_id", idPeriodicity1);
        executorDeadlineCalculation.addProperty("start_date", "2024-02-07T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("execution_state_id", idAssignmentEs1);
        executorDeadlineCalculation.addProperty("deadline_date", "2024-02-07T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("periodicity_day", 1);
        executorDeadlineCalculation.addProperty("note", "test");

        assignment.add("executor_deadline_calculation", executorDeadlineCalculation);
        assignmentsArray.add(assignment);

        requestPayload.add("assignments", assignmentsArray);

        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.assignments.0.executor_deadline_calculation.deadline_basis_id[0]");
        Assert.assertTrue(error.equals("не может быть пустым"));
    }

    @Test
    public void step10() {

        // Параметры запроса
        JsonObject requestPayload = new JsonObject();
        requestPayload.addProperty("skip_reg_date", "2023-12-20T06:06:31.485Z");
        requestPayload.addProperty("control_official_id", "59e9be1f83c0000040000001");
        requestPayload.addProperty("control_subject_id", 47);
        requestPayload.addProperty("document_name", "Тест 205");
        requestPayload.addProperty("document_execution_state_id", 1);
        requestPayload.addProperty("skip_document_type_id", 1);
        requestPayload.addProperty("internal", false);
        requestPayload.addProperty("short_description", "Test 205");

        JsonArray assignmentsArray = new JsonArray();

        JsonObject assignment = new JsonObject();
        assignment.addProperty("number", "test");
        assignment.addProperty("content", "test");

        JsonObject executorDeadlineCalculation = new JsonObject();
        executorDeadlineCalculation.addProperty("periodicity_id", idPeriodicity1);
        executorDeadlineCalculation.addProperty("start_date", "2024-02-07T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("deadline_basis_id", "13222658746541");
        executorDeadlineCalculation.addProperty("execution_state_id", idAssignmentEs1);
        executorDeadlineCalculation.addProperty("deadline_date", "2024-02-07T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("periodicity_day", 1);
        executorDeadlineCalculation.addProperty("note", "test");

        assignment.add("executor_deadline_calculation", executorDeadlineCalculation);
        assignmentsArray.add(assignment);

        requestPayload.add("assignments", assignmentsArray);

        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.assignments.0.executor_deadline_calculation.deadline_basis_id[0]");
        Assert.assertTrue(error.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step11() {

        // Параметры запроса
        JsonObject requestPayload = new JsonObject();
        requestPayload.addProperty("skip_reg_date", "2023-12-20T06:06:31.485Z");
        requestPayload.addProperty("control_official_id", "59e9be1f83c0000040000001");
        requestPayload.addProperty("control_subject_id", 47);
        requestPayload.addProperty("document_name", "Тест 205");
        requestPayload.addProperty("document_execution_state_id", 1);
        requestPayload.addProperty("skip_document_type_id", 1);
        requestPayload.addProperty("internal", false);
        requestPayload.addProperty("short_description", "Test 205");

        JsonArray assignmentsArray = new JsonArray();

        JsonObject assignment = new JsonObject();
        assignment.addProperty("number", "test");
        assignment.addProperty("content", "test");

        JsonObject executorDeadlineCalculation = new JsonObject();
        executorDeadlineCalculation.addProperty("periodicity_id", idPeriodicity1);
        executorDeadlineCalculation.addProperty("start_date", "2024-02-07T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("deadline_basis_id", idDeadlineBases1);
        executorDeadlineCalculation.addProperty("deadline_date", "2024-02-07T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("periodicity_day", 1);
        executorDeadlineCalculation.addProperty("note", "test");

        assignment.add("executor_deadline_calculation", executorDeadlineCalculation);
        assignmentsArray.add(assignment);

        requestPayload.add("assignments", assignmentsArray);

        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.assignments.0.executor_deadline_calculation.execution_state_id[0]");
        Assert.assertTrue(error.equals("не может быть пустым"));
    }

    @Test
    public void step12() {

        // Параметры запроса
        JsonObject requestPayload = new JsonObject();
        requestPayload.addProperty("skip_reg_date", "2023-12-20T06:06:31.485Z");
        requestPayload.addProperty("control_official_id", "59e9be1f83c0000040000001");
        requestPayload.addProperty("control_subject_id", 47);
        requestPayload.addProperty("document_name", "Тест 205");
        requestPayload.addProperty("document_execution_state_id", 1);
        requestPayload.addProperty("skip_document_type_id", 1);
        requestPayload.addProperty("internal", false);
        requestPayload.addProperty("short_description", "Test 205");

        JsonArray assignmentsArray = new JsonArray();

        JsonObject assignment = new JsonObject();
        assignment.addProperty("number", "test");
        assignment.addProperty("content", "test");

        JsonObject executorDeadlineCalculation = new JsonObject();
        executorDeadlineCalculation.addProperty("periodicity_id", idPeriodicity1);
        executorDeadlineCalculation.addProperty("start_date", "2024-02-07T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("deadline_basis_id", idDeadlineBases1);
        executorDeadlineCalculation.addProperty("execution_state_id", 1232154257);
        executorDeadlineCalculation.addProperty("deadline_date", "2024-02-07T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("periodicity_day", 1);
        executorDeadlineCalculation.addProperty("note", "test");

        assignment.add("executor_deadline_calculation", executorDeadlineCalculation);
        assignmentsArray.add(assignment);

        requestPayload.add("assignments", assignmentsArray);

        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.assignments.0.executor_deadline_calculation.execution_state_id[0]");
        Assert.assertTrue(error.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step13() {

        // Параметры запроса
        JsonObject requestPayload = new JsonObject();
        requestPayload.addProperty("skip_reg_date", "2023-12-20T06:06:31.485Z");
        requestPayload.addProperty("control_official_id", "59e9be1f83c0000040000001");
        requestPayload.addProperty("control_subject_id", 47);
        requestPayload.addProperty("document_name", "Тест 205");
        requestPayload.addProperty("document_execution_state_id", 1);
        requestPayload.addProperty("skip_document_type_id", 1);
        requestPayload.addProperty("internal", false);
        requestPayload.addProperty("short_description", "Test 205");

        JsonArray assignmentsArray = new JsonArray();

        JsonObject assignment = new JsonObject();
        assignment.addProperty("number", "test");
        assignment.addProperty("content", "test");

        JsonObject executorDeadlineCalculation = new JsonObject();
        executorDeadlineCalculation.addProperty("periodicity_id", idPeriodicity1);
        executorDeadlineCalculation.addProperty("start_date", "2024-02-07T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("deadline_basis_id", idDeadlineBases1);
        executorDeadlineCalculation.addProperty("execution_state_id", idAssignmentEs1);
        executorDeadlineCalculation.addProperty("deadline_date", "2024-02-06T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("periodicity_day", 1);
        executorDeadlineCalculation.addProperty("note", "test");

        assignment.add("executor_deadline_calculation", executorDeadlineCalculation);
        assignmentsArray.add(assignment);

        requestPayload.add("assignments", assignmentsArray);

        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.assignments.0.executor_deadline_calculation.deadline_date[0]");
        Assert.assertTrue(error.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step14() {

        // Параметры запроса
        JsonObject requestPayload = new JsonObject();
        requestPayload.addProperty("skip_reg_date", "2023-12-20T06:06:31.485Z");
        requestPayload.addProperty("control_official_id", "59e9be1f83c0000040000001");
        requestPayload.addProperty("control_subject_id", 47);
        requestPayload.addProperty("document_name", "Тест 205");
        requestPayload.addProperty("document_execution_state_id", 1);
        requestPayload.addProperty("skip_document_type_id", 1);
        requestPayload.addProperty("internal", false);
        requestPayload.addProperty("short_description", "Test 205");

        JsonArray assignmentsArray = new JsonArray();

        JsonObject assignment = new JsonObject();
        assignment.addProperty("number", "test");
        assignment.addProperty("content", "test");

        JsonObject executorDeadlineCalculation = new JsonObject();
        executorDeadlineCalculation.addProperty("periodicity_id", idPeriodicity1);
        executorDeadlineCalculation.addProperty("start_date", "2024-02-07T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("deadline_basis_id", idDeadlineBases1);
        executorDeadlineCalculation.addProperty("execution_state_id", idAssignmentEs1);
        executorDeadlineCalculation.addProperty("deadline_date", "07.02.2024");
        executorDeadlineCalculation.addProperty("periodicity_day", 1);
        executorDeadlineCalculation.addProperty("note", "test");

        assignment.add("executor_deadline_calculation", executorDeadlineCalculation);
        assignmentsArray.add(assignment);

        requestPayload.add("assignments", assignmentsArray);

        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.assignments.0.executor_deadline_calculation.deadline_date[0]");
        Assert.assertTrue(error.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step15() {

        // Параметры запроса
        JsonObject requestPayload = new JsonObject();
        requestPayload.addProperty("skip_reg_date", "2023-12-20T06:06:31.485Z");
        requestPayload.addProperty("control_official_id", "59e9be1f83c0000040000001");
        requestPayload.addProperty("control_subject_id", 47);
        requestPayload.addProperty("document_name", "Тест 205");
        requestPayload.addProperty("document_execution_state_id", 1);
        requestPayload.addProperty("skip_document_type_id", 1);
        requestPayload.addProperty("internal", false);
        requestPayload.addProperty("short_description", "Test 205");

        JsonArray assignmentsArray = new JsonArray();

        JsonObject assignment = new JsonObject();
        assignment.addProperty("number", "test");
        assignment.addProperty("content", "test");

        JsonObject executorDeadlineCalculation = new JsonObject();
        executorDeadlineCalculation.addProperty("periodicity_id", idPeriodicity1);
        executorDeadlineCalculation.addProperty("start_date", "2024-02-07T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("deadline_basis_id", idDeadlineBases1);
        executorDeadlineCalculation.addProperty("execution_state_id", idAssignmentEs1);
        executorDeadlineCalculation.addProperty("deadline_date", "2024-02-07T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("periodicity_day", 1.5);
        executorDeadlineCalculation.addProperty("note", "test");

        assignment.add("executor_deadline_calculation", executorDeadlineCalculation);
        assignmentsArray.add(assignment);

        requestPayload.add("assignments", assignmentsArray);

        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        // Проверка кода ответа
        Assert.assertEquals(201, response.statusCode());

        // Проверка содержимого ответа
        JsonPath jsonPath = response.jsonPath();
        int idEdc = jsonPath.get("data.assignments[0].executor_deadline_calculation.id");
        String startDate = jsonPath.get("data.assignments[0].executor_deadline_calculation.start_date");
        String deadlineDate = jsonPath.get("data.assignments[0].executor_deadline_calculation.deadline_date");
        int periodicityDay = jsonPath.get("data.assignments[0].executor_deadline_calculation.periodicity_day");
        String note = jsonPath.getString("data.assignments[0].executor_deadline_calculation.note");
        int periodicityId = jsonPath.get("data.assignments[0].executor_deadline_calculation.periodicity.id");
        int deadlaneBasesId = jsonPath.get("data.assignments[0].executor_deadline_calculation.deadline_basis.id");
        int assigmentsEs = jsonPath.get("data.assignments[0].executor_deadline_calculation.execution_state.id");
        int assigmentsId = jsonPath.get("data.assignments[0].id");
        int docId = jsonPath.get("data.id");

        idAssigments1 = assigmentsId;
        idDocuments1 = docId;


        Assert.assertTrue(startDate.equals("2024-02-07T06:18:47.149Z"));
        Assert.assertTrue(deadlineDate.equals("2024-02-07T06:18:47.149Z"));
        Assert.assertTrue(idEdc != 0);
        Assert.assertTrue(periodicityDay == 1);
        Assert.assertTrue(note.equals("test"));
        Assert.assertTrue(periodicityId == idPeriodicity1);
        Assert.assertTrue(deadlaneBasesId == idDeadlineBases1);
        Assert.assertTrue(assigmentsEs == idAssignmentEs1);

    }

    @Test
    public void step16() {

        // Параметры запроса
        JsonObject requestPayload = new JsonObject();
        requestPayload.addProperty("skip_reg_date", "2023-12-20T06:06:31.485Z");
        requestPayload.addProperty("control_official_id", "59e9be1f83c0000040000001");
        requestPayload.addProperty("control_subject_id", 47);
        requestPayload.addProperty("document_name", "Тест 205");
        requestPayload.addProperty("document_execution_state_id", 1);
        requestPayload.addProperty("skip_document_type_id", 1);
        requestPayload.addProperty("internal", false);
        requestPayload.addProperty("short_description", "Test 205");

        JsonArray assignmentsArray = new JsonArray();

        JsonObject assignment = new JsonObject();
        assignment.addProperty("number", "test");
        assignment.addProperty("content", "test");

        JsonObject executorDeadlineCalculation = new JsonObject();
        executorDeadlineCalculation.addProperty("periodicity_id", idPeriodicity1);
        executorDeadlineCalculation.addProperty("start_date", "2024-02-07T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("deadline_basis_id", idDeadlineBases1);
        executorDeadlineCalculation.addProperty("execution_state_id", idAssignmentEs1);
        executorDeadlineCalculation.addProperty("deadline_date", "2024-02-07T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("periodicity_day", true);
        executorDeadlineCalculation.addProperty("note", "test");

        assignment.add("executor_deadline_calculation", executorDeadlineCalculation);
        assignmentsArray.add(assignment);

        requestPayload.add("assignments", assignmentsArray);

        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.assignments.0.executor_deadline_calculation.periodicity_day[0]");
        String error2 = jsonPath.getString("errors.assignments.0.executor_deadline_calculation.periodicity_day[1]");
        Assert.assertTrue(error.equals("имеет неверное значение"));
        Assert.assertTrue(error2.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step17() {

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .when()
                .get(API_DOCUMENTS + "/" + idDocuments1)
                .then().log().all()
                .extract().response();


        // Проверка содержимого ответа
        JsonPath jsonPath = response.jsonPath();
        int idEdc = jsonPath.get("data.assignments[0].executor_deadline_calculation.id");
        String startDate = jsonPath.get("data.assignments[0].executor_deadline_calculation.start_date");
        String deadlineDate = jsonPath.get("data.assignments[0].executor_deadline_calculation.deadline_date");
        int periodicityDay = jsonPath.get("data.assignments[0].executor_deadline_calculation.periodicity_day");
        String note = jsonPath.getString("data.assignments[0].executor_deadline_calculation.note");
        int periodicityId = jsonPath.get("data.assignments[0].executor_deadline_calculation.periodicity.id");
        int deadlaneBasesId = jsonPath.get("data.assignments[0].executor_deadline_calculation.deadline_basis.id");
        int assigmentsEs = jsonPath.get("data.assignments[0].executor_deadline_calculation.execution_state.id");


        Assert.assertTrue(startDate.equals("2024-02-07T06:18:47.149Z"));
        Assert.assertTrue(deadlineDate.equals("2024-02-07T06:18:47.149Z"));
        Assert.assertTrue(idEdc != 0);
        Assert.assertTrue(periodicityDay == 1);
        Assert.assertTrue(note.equals("test"));
        Assert.assertTrue(periodicityId == idPeriodicity1);
        Assert.assertTrue(deadlaneBasesId == idDeadlineBases1);
        Assert.assertTrue(assigmentsEs == idAssignmentEs1);

    }

    @Test
    public void step18() {

        JsonObject requestPayload = new JsonObject();

        JsonArray assignmentsArray = new JsonArray();

        JsonObject assignment = new JsonObject();
        assignment.addProperty("id", idAssigments1);

        JsonObject executorDeadlineCalculation = new JsonObject();
        executorDeadlineCalculation.addProperty("periodicity_id", idPeriodicity2);
        executorDeadlineCalculation.addProperty("start_date", "2024-02-05T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("deadline_basis_id", idDeadlineBases2);
        executorDeadlineCalculation.addProperty("execution_state_id", idAssignmentEs2);
        executorDeadlineCalculation.addProperty("deadline_date", "2024-02-06T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("periodicity_day", 20);
        executorDeadlineCalculation.addProperty("note", "test1");

        assignment.add("executor_deadline_calculation", executorDeadlineCalculation);
        assignmentsArray.add(assignment);

        requestPayload.add("assignments", assignmentsArray);

        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .put(API_DOCUMENTS + "/" + idDocuments1)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int idEdc = jsonPath.get("data.assignments[0].executor_deadline_calculation.id");
        String startDate = jsonPath.get("data.assignments[0].executor_deadline_calculation.start_date");
        String deadlineDate = jsonPath.get("data.assignments[0].executor_deadline_calculation.deadline_date");
        int periodicityDay = jsonPath.get("data.assignments[0].executor_deadline_calculation.periodicity_day");
        String note = jsonPath.getString("data.assignments[0].executor_deadline_calculation.note");
        int periodicityId = jsonPath.get("data.assignments[0].executor_deadline_calculation.periodicity.id");
        int deadlaneBasesId = jsonPath.get("data.assignments[0].executor_deadline_calculation.deadline_basis.id");
        int assigmentsEs = jsonPath.get("data.assignments[0].executor_deadline_calculation.execution_state.id");


        Assert.assertTrue(startDate.equals("2024-02-05T06:18:47.149Z"));
        Assert.assertTrue(deadlineDate.equals("2024-02-06T06:18:47.149Z"));
        Assert.assertTrue(idEdc != 0);
        Assert.assertTrue(periodicityDay == 20);
        Assert.assertTrue(note.equals("test1"));
        Assert.assertTrue(periodicityId == idPeriodicity2);
        Assert.assertTrue(deadlaneBasesId == idDeadlineBases2);
        Assert.assertTrue(assigmentsEs == idAssignmentEs2);

    }

    @Test
    public void step19() {

        JsonObject requestPayload = new JsonObject();

        JsonArray assignmentsArray = new JsonArray();

        JsonObject assignment = new JsonObject();
        assignment.addProperty("id", idAssigments1);

        JsonObject executorDeadlineCalculation = new JsonObject();

        assignment.add("executor_deadline_calculation", executorDeadlineCalculation);
        assignmentsArray.add(assignment);

        requestPayload.add("assignments", assignmentsArray);

        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .put(API_DOCUMENTS + "/" + idDocuments1)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int idEdc = jsonPath.get("data.assignments[0].executor_deadline_calculation.id");
        String startDate = jsonPath.get("data.assignments[0].executor_deadline_calculation.start_date");
        String deadlineDate = jsonPath.get("data.assignments[0].executor_deadline_calculation.deadline_date");
        int periodicityDay = jsonPath.get("data.assignments[0].executor_deadline_calculation.periodicity_day");
        String note = jsonPath.getString("data.assignments[0].executor_deadline_calculation.note");
        int periodicityId = jsonPath.get("data.assignments[0].executor_deadline_calculation.periodicity.id");
        int deadlaneBasesId = jsonPath.get("data.assignments[0].executor_deadline_calculation.deadline_basis.id");
        int assigmentsEs = jsonPath.get("data.assignments[0].executor_deadline_calculation.execution_state.id");


        Assert.assertTrue(startDate.equals("2024-02-05T06:18:47.149Z"));
        Assert.assertTrue(deadlineDate.equals("2024-02-06T06:18:47.149Z"));
        Assert.assertTrue(idEdc != 0);
        Assert.assertTrue(periodicityDay == 20);
        Assert.assertTrue(note.equals("test1"));
        Assert.assertTrue(periodicityId == idPeriodicity2);
        Assert.assertTrue(deadlaneBasesId == idDeadlineBases2);
        Assert.assertTrue(assigmentsEs == idAssignmentEs2);

    }

    @Test
    public void step20() {

        JsonObject requestPayload = new JsonObject();

        JsonArray assignmentsArray = new JsonArray();

        JsonObject assignment = new JsonObject();
        assignment.addProperty("id", idAssigments1);

        JsonObject executorDeadlineCalculation = new JsonObject();
        executorDeadlineCalculation.addProperty("periodicity_id", (Number) null);
        executorDeadlineCalculation.addProperty("start_date", "2024-02-05T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("deadline_basis_id", idDeadlineBases2);
        executorDeadlineCalculation.addProperty("execution_state_id", idAssignmentEs2);
        executorDeadlineCalculation.addProperty("deadline_date", "2024-02-06T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("periodicity_day", 20);
        executorDeadlineCalculation.addProperty("note", "test1");

        assignment.add("executor_deadline_calculation", executorDeadlineCalculation);
        assignmentsArray.add(assignment);

        requestPayload.add("assignments", assignmentsArray);

        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .put(API_DOCUMENTS + "/" + idDocuments1)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.assignments.0.executor_deadline_calculation.periodicity_id[0]");
        Assert.assertTrue(error.equals("не может быть пустым"));

    }

    @Test
    public void step21() {

        JsonObject requestPayload = new JsonObject();

        JsonArray assignmentsArray = new JsonArray();

        JsonObject assignment = new JsonObject();
        assignment.addProperty("id", idAssigments1);

        JsonObject executorDeadlineCalculation = new JsonObject();
        executorDeadlineCalculation.addProperty("periodicity_id", 1234241255);
        executorDeadlineCalculation.addProperty("start_date", "2024-02-05T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("deadline_basis_id", idDeadlineBases2);
        executorDeadlineCalculation.addProperty("execution_state_id", idAssignmentEs2);
        executorDeadlineCalculation.addProperty("deadline_date", "2024-02-06T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("periodicity_day", 20);
        executorDeadlineCalculation.addProperty("note", "test1");

        assignment.add("executor_deadline_calculation", executorDeadlineCalculation);
        assignmentsArray.add(assignment);

        requestPayload.add("assignments", assignmentsArray);

        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .put(API_DOCUMENTS + "/" + idDocuments1)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.assignments.0.executor_deadline_calculation.periodicity_id[0]");
        Assert.assertTrue(error.equals("имеет непредусмотренное значение"));

    }

    @Test
    public void step22() {

        JsonObject requestPayload = new JsonObject();

        JsonArray assignmentsArray = new JsonArray();

        JsonObject assignment = new JsonObject();
        assignment.addProperty("id", idAssigments1);

        JsonObject executorDeadlineCalculation = new JsonObject();
        executorDeadlineCalculation.addProperty("periodicity_id", idPeriodicity2);
        executorDeadlineCalculation.addProperty("start_date", "");
        executorDeadlineCalculation.addProperty("deadline_basis_id", idDeadlineBases2);
        executorDeadlineCalculation.addProperty("execution_state_id", idAssignmentEs2);
        executorDeadlineCalculation.addProperty("deadline_date", "2024-02-06T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("periodicity_day", 20);
        executorDeadlineCalculation.addProperty("note", "test1");

        assignment.add("executor_deadline_calculation", executorDeadlineCalculation);
        assignmentsArray.add(assignment);

        requestPayload.add("assignments", assignmentsArray);

        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .put(API_DOCUMENTS + "/" + idDocuments1)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.assignments.0.executor_deadline_calculation.start_date[0]");
        Assert.assertTrue(error.equals("не может быть пустым"));

    }

    @Test
    public void step23() {

        JsonObject requestPayload = new JsonObject();

        JsonArray assignmentsArray = new JsonArray();

        JsonObject assignment = new JsonObject();
        assignment.addProperty("id", idAssigments1);

        JsonObject executorDeadlineCalculation = new JsonObject();
        executorDeadlineCalculation.addProperty("periodicity_id", idPeriodicity2);
        executorDeadlineCalculation.addProperty("start_date", "2024-02-05");
        executorDeadlineCalculation.addProperty("deadline_basis_id", idDeadlineBases2);
        executorDeadlineCalculation.addProperty("execution_state_id", idAssignmentEs2);
        executorDeadlineCalculation.addProperty("deadline_date", "2024-02-06T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("periodicity_day", 20);
        executorDeadlineCalculation.addProperty("note", "test1");

        assignment.add("executor_deadline_calculation", executorDeadlineCalculation);
        assignmentsArray.add(assignment);

        requestPayload.add("assignments", assignmentsArray);

        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .put(API_DOCUMENTS + "/" + idDocuments1)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int idEdc = jsonPath.get("data.assignments[0].executor_deadline_calculation.id");
        String startDate = jsonPath.get("data.assignments[0].executor_deadline_calculation.start_date");
        String deadlineDate = jsonPath.get("data.assignments[0].executor_deadline_calculation.deadline_date");
        int periodicityDay = jsonPath.get("data.assignments[0].executor_deadline_calculation.periodicity_day");
        String note = jsonPath.getString("data.assignments[0].executor_deadline_calculation.note");
        int periodicityId = jsonPath.get("data.assignments[0].executor_deadline_calculation.periodicity.id");
        int deadlaneBasesId = jsonPath.get("data.assignments[0].executor_deadline_calculation.deadline_basis.id");
        int assigmentsEs = jsonPath.get("data.assignments[0].executor_deadline_calculation.execution_state.id");


        Assert.assertTrue(startDate.equals("2024-02-05T00:00:00.000Z"));
        Assert.assertTrue(deadlineDate.equals("2024-02-06T06:18:47.149Z"));
        Assert.assertTrue(idEdc != 0);
        Assert.assertTrue(periodicityDay == 20);
        Assert.assertTrue(note.equals("test1"));
        Assert.assertTrue(periodicityId == idPeriodicity2);
        Assert.assertTrue(deadlaneBasesId == idDeadlineBases2);
        Assert.assertTrue(assigmentsEs == idAssignmentEs2);

    }

    @Test
    public void step24() {

        JsonObject requestPayload = new JsonObject();

        JsonArray assignmentsArray = new JsonArray();

        JsonObject assignment = new JsonObject();
        assignment.addProperty("id", idAssigments1);

        JsonObject executorDeadlineCalculation = new JsonObject();
        executorDeadlineCalculation.addProperty("periodicity_id", idPeriodicity2);
        executorDeadlineCalculation.addProperty("start_date", "05.02.2024");
        executorDeadlineCalculation.addProperty("deadline_basis_id", idDeadlineBases2);
        executorDeadlineCalculation.addProperty("execution_state_id", idAssignmentEs2);
        executorDeadlineCalculation.addProperty("deadline_date", "2024-02-06T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("periodicity_day", 20);
        executorDeadlineCalculation.addProperty("note", "test1");

        assignment.add("executor_deadline_calculation", executorDeadlineCalculation);
        assignmentsArray.add(assignment);

        requestPayload.add("assignments", assignmentsArray);

        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .put(API_DOCUMENTS + "/" + idDocuments1)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int idEdc = jsonPath.get("data.assignments[0].executor_deadline_calculation.id");
        String startDate = jsonPath.get("data.assignments[0].executor_deadline_calculation.start_date");
        String deadlineDate = jsonPath.get("data.assignments[0].executor_deadline_calculation.deadline_date");
        int periodicityDay = jsonPath.get("data.assignments[0].executor_deadline_calculation.periodicity_day");
        String note = jsonPath.getString("data.assignments[0].executor_deadline_calculation.note");
        int periodicityId = jsonPath.get("data.assignments[0].executor_deadline_calculation.periodicity.id");
        int deadlaneBasesId = jsonPath.get("data.assignments[0].executor_deadline_calculation.deadline_basis.id");
        int assigmentsEs = jsonPath.get("data.assignments[0].executor_deadline_calculation.execution_state.id");


        Assert.assertTrue(startDate.equals("2024-02-05T00:00:00.000Z"));
        Assert.assertTrue(deadlineDate.equals("2024-02-06T06:18:47.149Z"));
        Assert.assertTrue(idEdc != 0);
        Assert.assertTrue(periodicityDay == 20);
        Assert.assertTrue(note.equals("test1"));
        Assert.assertTrue(periodicityId == idPeriodicity2);
        Assert.assertTrue(deadlaneBasesId == idDeadlineBases2);
        Assert.assertTrue(assigmentsEs == idAssignmentEs2);

    }

    @Test
    public void step25() {

        JsonObject requestPayload = new JsonObject();

        JsonArray assignmentsArray = new JsonArray();

        JsonObject assignment = new JsonObject();
        assignment.addProperty("id", idAssigments1);

        JsonObject executorDeadlineCalculation = new JsonObject();
        executorDeadlineCalculation.addProperty("periodicity_id", idPeriodicity2);
        executorDeadlineCalculation.addProperty("start_date", "05022024");
        executorDeadlineCalculation.addProperty("deadline_basis_id", idDeadlineBases2);
        executorDeadlineCalculation.addProperty("execution_state_id", idAssignmentEs2);
        executorDeadlineCalculation.addProperty("deadline_date", "2024-02-06T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("periodicity_day", 20);
        executorDeadlineCalculation.addProperty("note", "test1");

        assignment.add("executor_deadline_calculation", executorDeadlineCalculation);
        assignmentsArray.add(assignment);

        requestPayload.add("assignments", assignmentsArray);

        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .put(API_DOCUMENTS + "/" + idDocuments1)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.assignments.0.executor_deadline_calculation.start_date[0]");
        Assert.assertTrue(error.equals("имеет неверное значение"));

    }

    @Test
    public void step26() {

        JsonObject requestPayload = new JsonObject();

        JsonArray assignmentsArray = new JsonArray();

        JsonObject assignment = new JsonObject();
        assignment.addProperty("id", idAssigments1);

        JsonObject executorDeadlineCalculation = new JsonObject();
        executorDeadlineCalculation.addProperty("periodicity_id", idPeriodicity2);
        executorDeadlineCalculation.addProperty("start_date", "2024-02-05");
        executorDeadlineCalculation.addProperty("deadline_basis_id", (Number) null);
        executorDeadlineCalculation.addProperty("execution_state_id", idAssignmentEs2);
        executorDeadlineCalculation.addProperty("deadline_date", "2024-02-06T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("periodicity_day", 20);
        executorDeadlineCalculation.addProperty("note", "test1");

        assignment.add("executor_deadline_calculation", executorDeadlineCalculation);
        assignmentsArray.add(assignment);

        requestPayload.add("assignments", assignmentsArray);

        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .put(API_DOCUMENTS + "/" + idDocuments1)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.assignments.0.executor_deadline_calculation.deadline_basis_id[0]");
        Assert.assertTrue(error.equals("не может быть пустым"));

    }

    @Test
    public void step27() {

        JsonObject requestPayload = new JsonObject();

        JsonArray assignmentsArray = new JsonArray();

        JsonObject assignment = new JsonObject();
        assignment.addProperty("id", idAssigments1);

        JsonObject executorDeadlineCalculation = new JsonObject();
        executorDeadlineCalculation.addProperty("periodicity_id", idPeriodicity2);
        executorDeadlineCalculation.addProperty("start_date", "2024-02-05");
        executorDeadlineCalculation.addProperty("deadline_basis_id", 1234567212);
        executorDeadlineCalculation.addProperty("execution_state_id", idAssignmentEs2);
        executorDeadlineCalculation.addProperty("deadline_date", "2024-02-06T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("periodicity_day", 20);
        executorDeadlineCalculation.addProperty("note", "test1");

        assignment.add("executor_deadline_calculation", executorDeadlineCalculation);
        assignmentsArray.add(assignment);

        requestPayload.add("assignments", assignmentsArray);

        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .put(API_DOCUMENTS + "/" + idDocuments1)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.assignments.0.executor_deadline_calculation.deadline_basis_id[0]");
        Assert.assertTrue(error.equals("имеет непредусмотренное значение"));

    }

    @Test
    public void step28() {

        JsonObject requestPayload = new JsonObject();

        JsonArray assignmentsArray = new JsonArray();

        JsonObject assignment = new JsonObject();
        assignment.addProperty("id", idAssigments1);

        JsonObject executorDeadlineCalculation = new JsonObject();
        executorDeadlineCalculation.addProperty("periodicity_id", idPeriodicity2);
        executorDeadlineCalculation.addProperty("start_date", "2024-02-05");
        executorDeadlineCalculation.addProperty("deadline_basis_id", idDeadlineBases2);
        executorDeadlineCalculation.addProperty("execution_state_id", (Number) null);
        executorDeadlineCalculation.addProperty("deadline_date", "2024-02-06T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("periodicity_day", 20);
        executorDeadlineCalculation.addProperty("note", "test1");

        assignment.add("executor_deadline_calculation", executorDeadlineCalculation);
        assignmentsArray.add(assignment);

        requestPayload.add("assignments", assignmentsArray);

        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .put(API_DOCUMENTS + "/" + idDocuments1)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.assignments.0.executor_deadline_calculation.execution_state_id[0]");
        Assert.assertTrue(error.equals("не может быть пустым"));

    }

    @Test
    public void step29() {

        JsonObject requestPayload = new JsonObject();

        JsonArray assignmentsArray = new JsonArray();

        JsonObject assignment = new JsonObject();
        assignment.addProperty("id", idAssigments1);

        JsonObject executorDeadlineCalculation = new JsonObject();
        executorDeadlineCalculation.addProperty("periodicity_id", idPeriodicity2);
        executorDeadlineCalculation.addProperty("start_date", "2024-02-05");
        executorDeadlineCalculation.addProperty("deadline_basis_id", idDeadlineBases2);
        executorDeadlineCalculation.addProperty("execution_state_id", 1342645324);
        executorDeadlineCalculation.addProperty("deadline_date", "2024-02-06T06:18:47.149Z");
        executorDeadlineCalculation.addProperty("periodicity_day", 20);
        executorDeadlineCalculation.addProperty("note", "test1");

        assignment.add("executor_deadline_calculation", executorDeadlineCalculation);
        assignmentsArray.add(assignment);

        requestPayload.add("assignments", assignmentsArray);

        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .put(API_DOCUMENTS + "/" + idDocuments1)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.assignments.0.executor_deadline_calculation.execution_state_id[0]");
        Assert.assertTrue(error.equals("имеет непредусмотренное значение"));

    }

    @Test
    public void step30() {

        JsonObject requestPayload = new JsonObject();

        JsonArray assignmentsArray = new JsonArray();

        JsonObject assignment = new JsonObject();
        assignment.addProperty("id", idAssigments1);

        JsonObject executorDeadlineCalculation = new JsonObject();
        executorDeadlineCalculation.addProperty("periodicity_id", idPeriodicity2);
        executorDeadlineCalculation.addProperty("start_date", "2024-02-05");
        executorDeadlineCalculation.addProperty("deadline_basis_id", idDeadlineBases2);
        executorDeadlineCalculation.addProperty("execution_state_id", idAssignmentEs2);
        executorDeadlineCalculation.addProperty("deadline_date", "04.02.2024");
        executorDeadlineCalculation.addProperty("periodicity_day", 20);
        executorDeadlineCalculation.addProperty("note", "test1");

        assignment.add("executor_deadline_calculation", executorDeadlineCalculation);
        assignmentsArray.add(assignment);

        requestPayload.add("assignments", assignmentsArray);

        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .put(API_DOCUMENTS + "/" + idDocuments1)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.assignments.0.executor_deadline_calculation.deadline_date[0]");
        Assert.assertTrue(error.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step31() {

        JsonObject requestPayload = new JsonObject();

        JsonArray assignmentsArray = new JsonArray();

        JsonObject assignment = new JsonObject();
        assignment.addProperty("id", idAssigments1);

        JsonObject executorDeadlineCalculation = new JsonObject();
        executorDeadlineCalculation.addProperty("periodicity_id", idPeriodicity2);
        executorDeadlineCalculation.addProperty("start_date", "2024-02-05");
        executorDeadlineCalculation.addProperty("deadline_basis_id", idDeadlineBases2);
        executorDeadlineCalculation.addProperty("execution_state_id", idAssignmentEs2);
        executorDeadlineCalculation.addProperty("deadline_date", "08.03.2024");
        executorDeadlineCalculation.addProperty("periodicity_day", 20);
        executorDeadlineCalculation.addProperty("note", "test1");

        assignment.add("executor_deadline_calculation", executorDeadlineCalculation);
        assignmentsArray.add(assignment);

        requestPayload.add("assignments", assignmentsArray);

        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .put(API_DOCUMENTS + "/" + idDocuments1)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int idEdc = jsonPath.get("data.assignments[0].executor_deadline_calculation.id");
        String startDate = jsonPath.get("data.assignments[0].executor_deadline_calculation.start_date");
        String deadlineDate = jsonPath.get("data.assignments[0].executor_deadline_calculation.deadline_date");
        int periodicityDay = jsonPath.get("data.assignments[0].executor_deadline_calculation.periodicity_day");
        String note = jsonPath.getString("data.assignments[0].executor_deadline_calculation.note");
        int periodicityId = jsonPath.get("data.assignments[0].executor_deadline_calculation.periodicity.id");
        int deadlaneBasesId = jsonPath.get("data.assignments[0].executor_deadline_calculation.deadline_basis.id");
        int assigmentsEs = jsonPath.get("data.assignments[0].executor_deadline_calculation.execution_state.id");


        Assert.assertTrue(startDate.equals("2024-02-05T00:00:00.000Z"));
        Assert.assertTrue(deadlineDate.equals("2024-03-08T00:00:00.000Z"));
        Assert.assertTrue(idEdc != 0);
        Assert.assertTrue(periodicityDay == 20);
        Assert.assertTrue(note.equals("test1"));
        Assert.assertTrue(periodicityId == idPeriodicity2);
        Assert.assertTrue(deadlaneBasesId == idDeadlineBases2);
        Assert.assertTrue(assigmentsEs == idAssignmentEs2);
    }

    @Test
    public void step32() {

        JsonObject requestPayload = new JsonObject();

        JsonArray assignmentsArray = new JsonArray();

        JsonObject assignment = new JsonObject();
        assignment.addProperty("id", idAssigments1);

        JsonObject executorDeadlineCalculation = new JsonObject();
        executorDeadlineCalculation.addProperty("periodicity_id", idPeriodicity2);
        executorDeadlineCalculation.addProperty("start_date", "2024-02-05");
        executorDeadlineCalculation.addProperty("deadline_basis_id", idDeadlineBases2);
        executorDeadlineCalculation.addProperty("execution_state_id", idAssignmentEs2);
        executorDeadlineCalculation.addProperty("deadline_date", "08032024");
        executorDeadlineCalculation.addProperty("periodicity_day", 20);
        executorDeadlineCalculation.addProperty("note", "test1");

        assignment.add("executor_deadline_calculation", executorDeadlineCalculation);
        assignmentsArray.add(assignment);

        requestPayload.add("assignments", assignmentsArray);

        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .put(API_DOCUMENTS + "/" + idDocuments1)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.assignments.0.executor_deadline_calculation.deadline_date[0]");
        Assert.assertTrue(error.equals("имеет неверное значение"));
    }

    @Test
    public void step33() {

        JsonObject requestPayload = new JsonObject();

        JsonArray assignmentsArray = new JsonArray();

        JsonObject assignment = new JsonObject();
        assignment.addProperty("id", idAssigments1);

        JsonObject executorDeadlineCalculation = new JsonObject();
        executorDeadlineCalculation.addProperty("periodicity_id", idPeriodicity2);
        executorDeadlineCalculation.addProperty("start_date", "2024-02-05");
        executorDeadlineCalculation.addProperty("deadline_basis_id", idDeadlineBases2);
        executorDeadlineCalculation.addProperty("execution_state_id", idAssignmentEs2);
        executorDeadlineCalculation.addProperty("deadline_date", "08.03.2024");
        executorDeadlineCalculation.addProperty("periodicity_day", (Number) null);
        executorDeadlineCalculation.addProperty("note", "test1");

        assignment.add("executor_deadline_calculation", executorDeadlineCalculation);
        assignmentsArray.add(assignment);

        requestPayload.add("assignments", assignmentsArray);

        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .put(API_DOCUMENTS + "/" + idDocuments1)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.assignments.0.executor_deadline_calculation.periodicity_day[0]");
        Assert.assertTrue(error.equals("не может быть пустым"));
    }
}


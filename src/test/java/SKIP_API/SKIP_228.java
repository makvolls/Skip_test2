package SKIP_API;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;

public class SKIP_228 {

    int idAut1 = 1;
    int idAut6=6;
    int idAut2=2;
    int idDocument, idAssignments, idExecutions, idHeadExecutor, idCoExecutors, idLetters, idExecutorDC, idExecutors,
    idLetters2,idCoExecutors2;
    String API_DOCUMENTS = "http://api.skip.rtech.ru/v1/documents";
    String API_OSHS="http://api.skip.rtech.ru/v1/oshs/mvd/officials";
    String API_CS="http://api.skip.rtech.ru/v1/permissions/control_subjects";
    String API_DES="http://api.skip.rtech.ru/v1/classifiers/document_execution_states";
    String API_DT="http://api.skip.rtech.ru/v1/classifiers/document_types";
    String API_ASSIGNMENTS="http://api.skip.rtech.ru/v1/assignments/";
    String API_EXECUTIONS_EVENTS="http://api.skip.rtech.ru/v1/executions/events";
    String uuidFirst;
    String uuidSecond;
    String idOshs;
    int idCs,idDES,idDT;

    @BeforeClass
    public void step001() {
        uuidFirst = UUID.randomUUID().toString();
        uuidSecond = UUID.randomUUID().toString();
        System.out.println("Created random uuid first value - " + uuidFirst);
        System.out.println("Created random uuid second value - " + uuidSecond);
    }
    @BeforeClass
    public void step002(){
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .when()
                .get(API_OSHS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String data_id=jsonPath.getString("data[0].id");
        idOshs=data_id;
    }

    @BeforeClass
    public void step003(){
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .when()
                .get(API_CS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int data_id=jsonPath.get("data[0].id");
        idCs=data_id;
    }
    @BeforeClass
    public void step004(){
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .when()
                .get(API_DES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int data_id=jsonPath.get("data[0].id");
        idDES=data_id;
    }
    @BeforeClass
    public void step005(){
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .when()
                .get(API_DT)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int data_id=jsonPath.get("data[0].id");
        idDT=data_id;
    }
    @Test
    public void step01() {
        JsonObject requestPayload = new JsonObject();
        requestPayload.addProperty("skip_reg_date", "2024-03-11T06:35:49.038Z");
        requestPayload.addProperty("short_description", "string");
        requestPayload.addProperty("control_official_id", idOshs);
        requestPayload.addProperty("document_name", "string");
        requestPayload.addProperty("document_execution_state_id", idDES);
        requestPayload.addProperty("skip_document_type_id", idDT);
        requestPayload.addProperty("internal", true);
        requestPayload.addProperty("control_subject_id", idCs);
        requestPayload.addProperty("not_sed_document", true);
        requestPayload.addProperty("sed_reg_number", "string");
        requestPayload.addProperty("sed_document_date", "2024-03-11T06:35:49.038Z");
        requestPayload.addProperty("single_number", "string");
        requestPayload.addProperty("ddo_external_document_date", "2024-03-11T06:35:49.038Z");
        requestPayload.addProperty("ddo_external_document_number", "string");
        requestPayload.addProperty("external_reg_date", "2024-03-11T06:35:49.038Z");
        requestPayload.addProperty("external_document_number", "string");
        requestPayload.addProperty("sed_document_type", "string");
        requestPayload.addProperty("resolution", "string");
        requestPayload.addProperty("sed_note", "string");
        requestPayload.addProperty("document_date", "2024-03-11T06:35:49.038Z");
        requestPayload.addProperty("document_number", "string");
        requestPayload.addProperty("note", "string");

        JsonArray assignments = new JsonArray();JsonObject assignment = new JsonObject();
        assignment.addProperty("number", "string");
        assignment.addProperty("content", "string");
        assignment.addProperty("note", "string");

        JsonObject headExecutor = new JsonObject();
        headExecutor.addProperty("head_executor_id", "59d4e38e453d000062000001");
        headExecutor.addProperty("deadline_basis_id", 91);
        headExecutor.addProperty("periodicity_id", 35);
        headExecutor.addProperty("execution_state_id", 30);
        headExecutor.addProperty("start_date", "2024-03-11T06:35:49.038Z");
        headExecutor.addProperty("deadline_date", "2024-03-11T06:35:49.038Z");

        JsonArray deadlines = new JsonArray();
        JsonObject deadline = new JsonObject();
        deadline.addProperty("id", uuidFirst);
        deadline.addProperty("deadline_date", "2024-03-11T06:35:49.038Z");
        deadline.addProperty("deadline_basis_id", 91);
        deadline.addProperty("content", "string");
        deadlines.add(deadline);

        headExecutor.add("deadlines", deadlines);

        JsonArray coExecutors = new JsonArray();
        JsonObject coExecutor = new JsonObject();
        coExecutor.addProperty("co_executor_id", "629dad05d5bf00000b000001");
        coExecutor.addProperty("co_executor_name", "string");
        coExecutor.addProperty("provided", true);

        JsonArray letters = new JsonArray();
        JsonObject letter = new JsonObject();
        letter.addProperty("document_type", "Documents::Document");
        letter.addProperty("document_id", 106);
        letter.addProperty("document_name", "string");
        letter.addProperty("note", "string");
        letters.add(letter);

        coExecutor.add("letters", letters);
        coExecutors.add(coExecutor);
        headExecutor.add("co_executors", coExecutors);

        assignment.add("head_executor", headExecutor);

        JsonObject executorDeadlineCalculation = new JsonObject();
        executorDeadlineCalculation.addProperty("periodicity_id", 35);
        executorDeadlineCalculation.addProperty("start_date", "2024-03-11T06:35:49.038Z");
        executorDeadlineCalculation.addProperty("deadline_basis_id", 91);
        executorDeadlineCalculation.addProperty("execution_state_id", 30);
        executorDeadlineCalculation.addProperty("deadline_date", "2024-03-11T06:35:49.038Z");

        JsonArray executorDeadlines = new JsonArray();
        JsonObject executorDeadline = new JsonObject();

        executorDeadline.addProperty("id", uuidSecond);
        executorDeadline.addProperty("deadline_date", "2024-03-11T06:35:49.038Z");
        executorDeadline.addProperty("deadline_basis_id", 91);
        executorDeadline.addProperty("content", "string");

        executorDeadlines.add(executorDeadline);

        executorDeadlineCalculation.add("deadlines", executorDeadlines);

        assignment.add("executor_deadline_calculation", executorDeadlineCalculation);

        JsonArray executors = new JsonArray();
        JsonObject executor = new JsonObject();
        executor.addProperty("organization_id", "553fa47d06c422325e000001");

        JsonArray executorCoExecutors = new JsonArray();
        JsonObject executorCoExecutor = new JsonObject();
        executorCoExecutor.addProperty("organization_short_title", "string");
        executorCoExecutor.addProperty("organization_id", "553fa47d06c4223261000001");
        executorCoExecutor.addProperty("organization_type", "Oshs::Mvd::Organization");
        executorCoExecutor.addProperty("organization_name", "name");
        executorCoExecutor.addProperty("provided", true);

        JsonArray executorLetters2 = new JsonArray();
        JsonArray letters2 = new JsonArray();
        JsonObject letter2 = new JsonObject();
        letter2.addProperty("document_type", "Documents::Document");
        letter2.addProperty("document_id", 106);
        letter2.addProperty("document_name", "string");
        letter2.addProperty("note", "string");
        letters2.add(letter2);
        executorLetters2.add(letter2);

        executorCoExecutor.add("letters", executorLetters2);
        executorCoExecutors.add(executorCoExecutor);
        executor.add("co_executors", executorCoExecutors);

        JsonArray executions = new JsonArray();
        JsonObject execution = new JsonObject();
        execution.addProperty("deadline_id", uuidSecond);
        execution.addProperty("deadline_basis_id", 91);
        execution.addProperty("note", "string");
        execution.addProperty("actual_info", "string");
        executions.add(execution);

        executor.add("executions", executions);
        executors.add(executor);

        assignment.add("executors", executors);
        assignments.add(assignment);

        requestPayload.add("assignments", assignments);


        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.getInt("data.id");
        int idA = jsonPath.getInt("data.assignments[0].id");
        int idE = jsonPath.getInt("data.assignments[0].executors[0].executions[0].id");
        int idH = jsonPath.getInt("data.assignments[0].head_executor.id");
        int idC = jsonPath.getInt("data.assignments[0].executors[0].co_executors[0].id");
        int idC2=jsonPath.getInt("data.assignments[0].head_executor.co_executors[0].id");
        int idL = jsonPath.getInt("data.assignments[0].executors[0].co_executors[0].letters[0].id");
        int idL2=jsonPath.getInt("data.assignments[0].head_executor.co_executors[0].letters[0].id");
        int idEdc = jsonPath.getInt("data.assignments[0].executor_deadline_calculation.id");
        int idEx = jsonPath.getInt("data.assignments[0].executors[0].id");

        idDocument = id;
        idAssignments = idA;
        idExecutions = idE;
        idHeadExecutor = idH;
        idCoExecutors = idC;
        idLetters = idL;
        idExecutorDC = idEdc;
        idExecutors = idEx;
        idLetters2=idL2;
        idCoExecutors2=idC2;

        String data = jsonPath.getString("data");
        String control_official_id = jsonPath.getString("data.control_official.id");
        int control_subject_id=jsonPath.getInt("data.control_subject.id");
        int desId=jsonPath.getInt("data.document_execution_state.id");
        int skipDocTypeId=jsonPath.getInt("data.skip_document_type.id");

        Assert.assertTrue(!data.isEmpty());
        Assert.assertTrue(control_official_id.equals(idOshs));
        Assert.assertTrue(control_subject_id==idCs);
        Assert.assertTrue(desId==idDES);
        Assert.assertTrue(skipDocTypeId==idDT);
    }

    @Test
    public void step02(){
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .when()
                .get(API_DOCUMENTS+"/"+idDocument+"/changes")
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String data=jsonPath.getString("data");
        int idUser=jsonPath.getInt("data[0].changed_by.id");
        String full_nameUser=jsonPath.getString("data[0].changed_by.full_name");
        String object=jsonPath.getString("data[0].object");
        String action=jsonPath.getString("data[0].action");
        int changes_count=jsonPath.getInt("data[0].changes_count");
        String field=jsonPath.getString("data[0].changes[0].field");
        String old_value=jsonPath.getString("data[0].changes[0].old_value");
        String new_value=jsonPath.getString("data[0].changes[0].new_value");
        String field2=jsonPath.getString("data[0].changes[1].field");
        String old_value2=jsonPath.getString("data[0].changes[1].old_value");
        String new_value2=jsonPath.getString("data[0].changes[1].new_value");
        String object2=jsonPath.getString("data[1].object");
        String action2=jsonPath.getString("data[1].action");
        int changes_count2=jsonPath.getInt("data[1].changes_count");
        String object3=jsonPath.getString("data[2].object");
        String action3=jsonPath.getString("data[2].action");
        int changes_count3=jsonPath.getInt("data[2].changes_count");
        String object4=jsonPath.getString("data[3].object");
        String action4=jsonPath.getString("data[3].action");
        int changes_count4=jsonPath.getInt("data[3].changes_count");
        String object5=jsonPath.getString("data[4].object");
        String action5=jsonPath.getString("data[4].action");
        int changes_count5=jsonPath.getInt("data[4].changes_count");
        String object6=jsonPath.getString("data[5].object");
        String action6=jsonPath.getString("data[5].action");
        int changes_count6=jsonPath.getInt("data[5].changes_count");
        String object7=jsonPath.getString("data[6].object");
        String action7=jsonPath.getString("data[6].action");
        int changes_count7=jsonPath.getInt("data[6].changes_count");
        String object8=jsonPath.getString("data[7].object");
        String action8=jsonPath.getString("data[7].action");
        int changes_count8=jsonPath.getInt("data[7].changes_count");
        String object9=jsonPath.getString("data[8].object");
        String action9=jsonPath.getString("data[8].action");
        int changes_count9=jsonPath.getInt("data[8].changes_count");
        String object10=jsonPath.getString("data[9].object");
        String action10=jsonPath.getString("data[9].action");
        int changes_count10=jsonPath.getInt("data[9].changes_count");


        Assert.assertTrue(!data.isEmpty());
        Assert.assertTrue(idUser==1);
        Assert.assertTrue(full_nameUser.equals("Колокольцев Владимир Александрович"));
        Assert.assertTrue(object.equals("Ход исполнения"));
        Assert.assertTrue(action.equals("Создание"));
        Assert.assertTrue(changes_count==5);
        Assert.assertTrue(field.equals("Дата контрольного срока"));
        Assert.assertNull(old_value);
        Assert.assertTrue(new_value.equals("11.03.2024"));
        Assert.assertTrue(field2.equals("Основание даты контроля"));
        Assert.assertNull(old_value2);
        Assert.assertTrue(new_value2.equals("ТестМесяц"));
        Assert.assertTrue(object2.equals("Письмо соисполнителя исполнителя в МВД России"));
        Assert.assertTrue(action2.equals("Создание"));
        Assert.assertTrue(changes_count2==2);
        Assert.assertTrue(object3.equals("Соисполнитель исполнителя в МВД России"));
        Assert.assertTrue(action3.equals("Создание"));
        Assert.assertTrue(changes_count3==2);
        Assert.assertTrue(object4.equals("Исполнитель в МВД России"));
        Assert.assertTrue(action4.equals("Создание"));
        Assert.assertTrue(changes_count4==1);
        Assert.assertTrue(object5.equals("Срок исполнения исполнителя"));
        Assert.assertTrue(action5.equals("Создание"));
        Assert.assertTrue(changes_count5==3);
        Assert.assertTrue(object6.equals("Сроки исполнения поручения для исполнителей"));
        Assert.assertTrue(action6.equals("Создание"));
        Assert.assertTrue(changes_count6==5);
        Assert.assertTrue(object7.equals("Письмо соисполнителя головного исполнителя"));
        Assert.assertTrue(action7.equals("Создание"));
        Assert.assertTrue(changes_count7==2);
        Assert.assertTrue(object8.equals("Соисполнитель Головного исполнителя"));
        Assert.assertTrue(action8.equals("Создание"));
        Assert.assertTrue(changes_count8==2);
        Assert.assertTrue(object9.equals("Срок исполнения Головного исполнителя"));
        Assert.assertTrue(action9.equals("Создание"));
        Assert.assertTrue(changes_count9==3);
        Assert.assertTrue(object10.equals("Головной исполнитель поручения"));
        Assert.assertTrue(action10.equals("Создание"));
        Assert.assertTrue(changes_count10==6);

    }

    @Test
    public void step03(){
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .params("items",20)
                .when()
                .get(API_ASSIGNMENTS+idAssignments+"/changes")
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String data=jsonPath.getString("data");
        int idUser=jsonPath.getInt("data[0].changed_by.id");
        String full_nameUser=jsonPath.getString("data[0].changed_by.full_name");
        String object=jsonPath.getString("data[0].object");
        String action=jsonPath.getString("data[0].action");
        int changes_count=jsonPath.getInt("data[0].changes_count");
        String field=jsonPath.getString("data[0].changes[0].field");
        String old_value=jsonPath.getString("data[0].changes[0].old_value");
        String new_value=jsonPath.getString("data[0].changes[0].new_value");
        String field2=jsonPath.getString("data[0].changes[1].field");
        String old_value2=jsonPath.getString("data[0].changes[1].old_value");
        String new_value2=jsonPath.getString("data[0].changes[1].new_value");
        String object2=jsonPath.getString("data[1].object");
        String action2=jsonPath.getString("data[1].action");
        int changes_count2=jsonPath.getInt("data[1].changes_count");
        String object3=jsonPath.getString("data[2].object");
        String action3=jsonPath.getString("data[2].action");
        int changes_count3=jsonPath.getInt("data[2].changes_count");
        String object4=jsonPath.getString("data[3].object");
        String action4=jsonPath.getString("data[3].action");
        int changes_count4=jsonPath.getInt("data[3].changes_count");
        String object5=jsonPath.getString("data[4].object");
        String action5=jsonPath.getString("data[4].action");
        int changes_count5=jsonPath.getInt("data[4].changes_count");
        String object6=jsonPath.getString("data[5].object");
        String action6=jsonPath.getString("data[5].action");
        int changes_count6=jsonPath.getInt("data[5].changes_count");
        String object7=jsonPath.getString("data[6].object");
        String action7=jsonPath.getString("data[6].action");
        int changes_count7=jsonPath.getInt("data[6].changes_count");
        String object8=jsonPath.getString("data[7].object");
        String action8=jsonPath.getString("data[7].action");
        int changes_count8=jsonPath.getInt("data[7].changes_count");
        String object9=jsonPath.getString("data[8].object");
        String action9=jsonPath.getString("data[8].action");
        int changes_count9=jsonPath.getInt("data[8].changes_count");
        String object10=jsonPath.getString("data[9].object");
        String action10=jsonPath.getString("data[9].action");
        int changes_count10=jsonPath.getInt("data[9].changes_count");
        String object11=jsonPath.getString("data[10].object");
        String action11=jsonPath.getString("data[10].action");
        int changes_count11=jsonPath.getInt("data[10].changes_count");


        Assert.assertTrue(!data.isEmpty());
        Assert.assertTrue(idUser==1);
        Assert.assertTrue(full_nameUser.equals("Колокольцев Владимир Александрович"));
        Assert.assertTrue(object.equals("Ход исполнения"));
        Assert.assertTrue(action.equals("Создание"));
        Assert.assertTrue(changes_count==5);
        Assert.assertTrue(field.equals("Дата контрольного срока"));
        Assert.assertNull(old_value);
        Assert.assertTrue(new_value.equals("11.03.2024"));
        Assert.assertTrue(field2.equals("Основание даты контроля"));
        Assert.assertNull(old_value2);
        Assert.assertTrue(new_value2.equals("ТестМесяц"));
        Assert.assertTrue(object2.equals("Письмо соисполнителя исполнителя в МВД России"));
        Assert.assertTrue(action2.equals("Создание"));
        Assert.assertTrue(changes_count2==2);
        Assert.assertTrue(object3.equals("Соисполнитель исполнителя в МВД России"));
        Assert.assertTrue(action3.equals("Создание"));
        Assert.assertTrue(changes_count3==2);
        Assert.assertTrue(object4.equals("Исполнитель в МВД России"));
        Assert.assertTrue(action4.equals("Создание"));
        Assert.assertTrue(changes_count4==1);
        Assert.assertTrue(object5.equals("Срок исполнения исполнителя"));
        Assert.assertTrue(action5.equals("Создание"));
        Assert.assertTrue(changes_count5==3);
        Assert.assertTrue(object6.equals("Сроки исполнения поручения для исполнителей"));
        Assert.assertTrue(action6.equals("Создание"));
        Assert.assertTrue(changes_count6==5);
        Assert.assertTrue(object7.equals("Письмо соисполнителя головного исполнителя"));
        Assert.assertTrue(action7.equals("Создание"));
        Assert.assertTrue(changes_count7==2);
        Assert.assertTrue(object8.equals("Соисполнитель Головного исполнителя"));
        Assert.assertTrue(action8.equals("Создание"));
        Assert.assertTrue(changes_count8==2);
        Assert.assertTrue(object9.equals("Срок исполнения Головного исполнителя"));
        Assert.assertTrue(action9.equals("Создание"));
        Assert.assertTrue(changes_count9==3);
        Assert.assertTrue(object10.equals("Головной исполнитель поручения"));
        Assert.assertTrue(action10.equals("Создание"));
        Assert.assertTrue(changes_count10==6);
        Assert.assertTrue(object11.equals("Поручение"));
        Assert.assertTrue(action11.equals("Создание"));
        Assert.assertTrue(changes_count11==3);

    }

    @Test
    public void step04() {
        JsonObject requestPayload = new JsonObject();
        requestPayload.addProperty("skip_reg_date", "2024-03-11T06:35:49.038Z");
        requestPayload.addProperty("short_description", "111");
        requestPayload.addProperty("control_official_id", idOshs);
        requestPayload.addProperty("document_name", "111");
        requestPayload.addProperty("document_execution_state_id", idDES);
        requestPayload.addProperty("skip_document_type_id", idDT);
        requestPayload.addProperty("internal", true);
        requestPayload.addProperty("control_subject_id", idCs);
        requestPayload.addProperty("not_sed_document", true);
        requestPayload.addProperty("sed_reg_number", "1");
        requestPayload.addProperty("sed_document_date", "2024-03-10T06:35:49.038Z");
        requestPayload.addProperty("single_number", "string");
        requestPayload.addProperty("ddo_external_document_date", "2024-03-10T06:35:49.038Z");
        requestPayload.addProperty("ddo_external_document_number", "string");
        requestPayload.addProperty("external_reg_date", "2024-03-11T06:35:49.038Z");
        requestPayload.addProperty("external_document_number", "string");
        requestPayload.addProperty("sed_document_type", "string");
        requestPayload.addProperty("resolution", "111");
        requestPayload.addProperty("sed_note", "111");
        requestPayload.addProperty("document_date", "2024-03-10T06:35:49.038Z");
        requestPayload.addProperty("document_number", "string");
        requestPayload.addProperty("note", "111");

        JsonArray assignments = new JsonArray();
        JsonObject assignment = new JsonObject();
        assignment.addProperty("id",idAssignments);
        assignment.addProperty("number", "1");
        assignment.addProperty("content", "111");
        assignment.addProperty("note", "111");

        JsonObject headExecutor = new JsonObject();
        headExecutor.addProperty("id",idHeadExecutor);
        headExecutor.addProperty("head_executor_id", "59d4e38e453d000062000001");
        headExecutor.addProperty("deadline_basis_id", 91);
        headExecutor.addProperty("periodicity_id", 36);
        headExecutor.addProperty("execution_state_id", 30);
        headExecutor.addProperty("start_date", "2024-03-10T06:35:49.038Z");
        headExecutor.addProperty("deadline_date", "2024-03-10T06:35:49.038Z");

        JsonArray deadlines = new JsonArray();
        JsonObject deadline = new JsonObject();
        deadline.addProperty("id", uuidFirst);
        deadline.addProperty("deadline_date", "2024-03-10T06:35:49.038Z");
        deadline.addProperty("deadline_basis_id", 91);
        deadline.addProperty("content", "111");
        deadlines.add(deadline);

        headExecutor.add("deadlines", deadlines);

        JsonArray coExecutors = new JsonArray();
        JsonObject coExecutor = new JsonObject();
        coExecutor.addProperty("id",idCoExecutors2);
        coExecutor.addProperty("co_executor_id", "629dad05d5bf00000b000001");
        coExecutor.addProperty("co_executor_name", "111");
        coExecutor.addProperty("provided", true);

        JsonArray letters = new JsonArray();
        JsonObject letter = new JsonObject();
        letter.addProperty("id",idLetters2);
        letter.addProperty("document_type", "Documents::Document");
        letter.addProperty("document_id", 106);
        letter.addProperty("document_name", "111");
        letter.addProperty("note", "111");
        letters.add(letter);

        coExecutor.add("letters", letters);
        coExecutors.add(coExecutor);
        headExecutor.add("co_executors", coExecutors);

        assignment.add("head_executor", headExecutor);

        JsonObject executorDeadlineCalculation = new JsonObject();
        executorDeadlineCalculation.addProperty("id",idExecutorDC);
        executorDeadlineCalculation.addProperty("periodicity_id", 36);
        executorDeadlineCalculation.addProperty("start_date", "2024-03-10T06:35:49.038Z");
        executorDeadlineCalculation.addProperty("deadline_basis_id", 91);
        executorDeadlineCalculation.addProperty("execution_state_id", 30);
        executorDeadlineCalculation.addProperty("deadline_date", "2024-03-10T06:35:49.038Z");

        JsonArray executorDeadlines = new JsonArray();
        JsonObject executorDeadline = new JsonObject();

        executorDeadline.addProperty("id", uuidSecond);
        executorDeadline.addProperty("deadline_date", "2024-03-11T06:35:49.038Z");
        executorDeadline.addProperty("deadline_basis_id", 91);
        executorDeadline.addProperty("content", "111");

        executorDeadlines.add(executorDeadline);

        executorDeadlineCalculation.add("deadlines", executorDeadlines);

        assignment.add("executor_deadline_calculation", executorDeadlineCalculation);

        JsonArray executors = new JsonArray();
        JsonObject executor = new JsonObject();
        executor.addProperty("id",idExecutors);
        executor.addProperty("organization_id", "553fa47d06c422325e000001");

        JsonArray executorCoExecutors = new JsonArray();
        JsonObject executorCoExecutor = new JsonObject();
        executorCoExecutor.addProperty("id",idCoExecutors);
        executorCoExecutor.addProperty("organization_short_title", "111");
        executorCoExecutor.addProperty("organization_id", "553fa47d06c4223261000001");
        executorCoExecutor.addProperty("organization_type", "Oshs::Mvd::Organization");
        executorCoExecutor.addProperty("provided", true);

        JsonArray executorLetters2 = new JsonArray();
        JsonArray letters2 = new JsonArray();
        JsonObject letter2 = new JsonObject();
        letter2.addProperty("id",idLetters);
        letter2.addProperty("document_type", "Documents::Document");
        letter2.addProperty("document_name", "111");
        letter2.addProperty("note", "111");
        letters2.add(letter2);
        executorLetters2.add(letter2);

        executorCoExecutor.add("letters", executorLetters2);
        executorCoExecutors.add(executorCoExecutor);
        executor.add("co_executors", executorCoExecutors);

        JsonArray executions = new JsonArray();
        JsonObject execution = new JsonObject();
        execution.addProperty("id",idExecutions);
        execution.addProperty("deadline_id", uuidSecond);
        execution.addProperty("deadline_basis_id", 91);
        execution.addProperty("note", "111");
        execution.addProperty("actual_info", "111");
        executions.add(execution);

        executor.add("executions", executions);
        executors.add(executor);

        assignment.add("executors", executors);
        assignments.add(assignment);

        requestPayload.add("assignments", assignments);


        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .put(API_DOCUMENTS+"/"+idDocument)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String data = jsonPath.getString("data");
        String control_official_id = jsonPath.getString("data.control_official.id");
        int control_subject_id=jsonPath.getInt("data.control_subject.id");
        int desId=jsonPath.getInt("data.document_execution_state.id");
        int skipDocTypeId=jsonPath.getInt("data.skip_document_type.id");
        int idDoc=jsonPath.getInt("data.id");
        int idAs=jsonPath.getInt("data.assignments[0].id");
        int idEx=jsonPath.getInt("data.assignments[0].executors[0].executions[0].id");
        int idH=jsonPath.getInt("data.assignments[0].head_executor.id");
        int idC=jsonPath.getInt("data.assignments[0].executors[0].co_executors[0].id");
        int idL=jsonPath.getInt("data.assignments[0].executors[0].co_executors[0].letters[0].id");
        int idEdc=jsonPath.getInt("data.assignments[0].executor_deadline_calculation.id");
        int idE=jsonPath.getInt("data.assignments[0].executors[0].id");
        int idL2=jsonPath.getInt("data.assignments[0].head_executor.co_executors[0].letters[0].id");
        int idC2=jsonPath.get("data.assignments[0].head_executor.co_executors[0].id");



        Assert.assertTrue(!data.isEmpty());
        Assert.assertTrue(control_official_id.equals(idOshs));
        Assert.assertTrue(control_subject_id==idCs);
        Assert.assertTrue(desId==idDES);
        Assert.assertTrue(skipDocTypeId==idDT);
        Assert.assertTrue(idDoc==idDocument);
        Assert.assertTrue(idAs==idAssignments);
        Assert.assertTrue(idEx==idExecutions);
        Assert.assertTrue(idH==idHeadExecutor);
        Assert.assertTrue(idC==idCoExecutors);
        Assert.assertTrue(idL==idLetters);
        Assert.assertTrue(idEdc==idExecutorDC);
        Assert.assertTrue(idE==idExecutors);
        Assert.assertTrue(idL2==idLetters2);
        Assert.assertTrue(idC2==idCoExecutors2);
    }

    @Test
    public void step05(){
        Response response = given()
                .config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs
                        ("multipart/form-data", ContentType.TEXT)))
                .header("Test-Authorization", idAut1)
                .formParam("execution_id", idExecutions)
                .formParam("event_id", 149)
                .formParam("state_id", 60)
                .formParam("document_id", idDocument)
                .formParam("document_type", "Documents::Document")
                .formParam("control_state_id",60)
                .when()
                .post(API_EXECUTIONS_EVENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int idState=jsonPath.getInt("data.state.id");
        int idEvent=jsonPath.getInt("data.event.id");
        String docType=jsonPath.getString("data.document_type");
        int idDoc=jsonPath.getInt("data.document_id");
        int idExec=jsonPath.getInt("data.execution_id");

        Assert.assertTrue(idState==60);
        Assert.assertTrue(idEvent==149);
        Assert.assertTrue(docType.equals("Documents::Document"));
        Assert.assertTrue(idDoc==idDocument);
        Assert.assertTrue(idExec==idExecutions);
    }
    @Test
    public void step06(){
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .when()
                .get(API_DOCUMENTS+"/"+idDocument+"/changes")
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String data=jsonPath.getString("data");
        int idUser=jsonPath.getInt("data[0].changed_by.id");
        String full_nameUser=jsonPath.getString("data[0].changed_by.full_name");
        String object=jsonPath.getString("data[0].object");
        String action=jsonPath.getString("data[0].action");
        int changes_count=jsonPath.getInt("data[0].changes_count");
        String field=jsonPath.getString("data[0].changes[0].field");
        String old_value=jsonPath.getString("data[0].changes[0].old_value");
        String new_value=jsonPath.getString("data[0].changes[0].new_value");
        String object2=jsonPath.getString("data[1].object");
        String action2=jsonPath.getString("data[1].action");
        int changes_count2=jsonPath.getInt("data[1].changes_count");
        String object3=jsonPath.getString("data[2].object");
        String action3=jsonPath.getString("data[2].action");
        int changes_count3=jsonPath.getInt("data[2].changes_count");
        String object4=jsonPath.getString("data[3].object");
        String action4=jsonPath.getString("data[3].action");
        int changes_count4=jsonPath.getInt("data[3].changes_count");
        String object5=jsonPath.getString("data[4].object");
        String action5=jsonPath.getString("data[4].action");
        int changes_count5=jsonPath.getInt("data[4].changes_count");
        String object6=jsonPath.getString("data[5].object");
        String action6=jsonPath.getString("data[5].action");
        int changes_count6=jsonPath.getInt("data[5].changes_count");
        String object7=jsonPath.getString("data[6].object");
        String action7=jsonPath.getString("data[6].action");
        int changes_count7=jsonPath.getInt("data[6].changes_count");
        String object8=jsonPath.getString("data[7].object");
        String action8=jsonPath.getString("data[7].action");
        int changes_count8=jsonPath.getInt("data[7].changes_count");
        String object9=jsonPath.getString("data[8].object");
        String action9=jsonPath.getString("data[8].action");
        int changes_count9=jsonPath.getInt("data[8].changes_count");
        String object10=jsonPath.getString("data[9].object");
        String action10=jsonPath.getString("data[9].action");
        int changes_count10=jsonPath.getInt("data[9].changes_count");


        Assert.assertTrue(!data.isEmpty());
        Assert.assertTrue(idUser==1);
        Assert.assertTrue(full_nameUser.equals("Колокольцев Владимир Александрович"));
        Assert.assertTrue(object.equals("Ход исполнения"));
        Assert.assertTrue(action.equals("Изменение"));
        Assert.assertTrue(changes_count==1);
        Assert.assertTrue(field.equals("Состояние контроля"));
        Assert.assertTrue(old_value.equals("Исполняется"));
        Assert.assertTrue(new_value.equals("bdfbfdfvrefrrrrsssss123456"));
        Assert.assertTrue(object2.equals("Событие"));
        Assert.assertTrue(action2.equals("Создание"));
        Assert.assertTrue(changes_count2==4);
        Assert.assertTrue(object3.equals("Ход исполнения"));
        Assert.assertTrue(action3.equals("Изменение"));
        Assert.assertTrue(changes_count3==2);
        Assert.assertTrue(object4.equals("Письмо соисполнителя исполнителя в МВД России"));
        Assert.assertTrue(action4.equals("Изменение"));
        Assert.assertTrue(changes_count4==2);
        Assert.assertTrue(object5.equals("Исполнитель в МВД России"));
        Assert.assertTrue(action5.equals("Изменение"));
        Assert.assertTrue(changes_count5==1);
        Assert.assertTrue(object6.equals("Срок исполнения исполнителя"));
        Assert.assertTrue(action6.equals("Изменение"));
        Assert.assertTrue(changes_count6==1);
        Assert.assertTrue(object7.equals("Сроки исполнения поручения для исполнителей"));
        Assert.assertTrue(action7.equals("Изменение"));
        Assert.assertTrue(changes_count7==3);
        Assert.assertTrue(object8.equals("Письмо соисполнителя головного исполнителя"));
        Assert.assertTrue(action8.equals("Изменение"));
        Assert.assertTrue(changes_count8==2);
        Assert.assertTrue(object9.equals("Соисполнитель Головного исполнителя"));
        Assert.assertTrue(action9.equals("Изменение"));
        Assert.assertTrue(changes_count9==1);
        Assert.assertTrue(object10.equals("Соисполнитель Головного исполнителя"));
        Assert.assertTrue(action10.equals("Изменение"));
        Assert.assertTrue(changes_count10==1);
    }

    @Test
    public void step07(){
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .params("items",20)
                .when()
                .get(API_ASSIGNMENTS+idAssignments+"/changes")
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String data=jsonPath.getString("data");
        int idUser=jsonPath.getInt("data[0].changed_by.id");
        String full_nameUser=jsonPath.getString("data[0].changed_by.full_name");
        String object=jsonPath.getString("data[0].object");
        String action=jsonPath.getString("data[0].action");
        int changes_count=jsonPath.getInt("data[0].changes_count");
        String object2=jsonPath.getString("data[1].object");
        String action2=jsonPath.getString("data[1].action");
        int changes_count2=jsonPath.getInt("data[1].changes_count");
        String object3=jsonPath.getString("data[2].object");
        String action3=jsonPath.getString("data[2].action");
        int changes_count3=jsonPath.getInt("data[2].changes_count");
        String object4=jsonPath.getString("data[3].object");
        String action4=jsonPath.getString("data[3].action");
        int changes_count4=jsonPath.getInt("data[3].changes_count");
        String object5=jsonPath.getString("data[4].object");
        String action5=jsonPath.getString("data[4].action");
        int changes_count5=jsonPath.getInt("data[4].changes_count");
        String object6=jsonPath.getString("data[5].object");
        String action6=jsonPath.getString("data[5].action");
        int changes_count6=jsonPath.getInt("data[5].changes_count");
        String object7=jsonPath.getString("data[6].object");
        String action7=jsonPath.getString("data[6].action");
        int changes_count7=jsonPath.getInt("data[6].changes_count");
        String object8=jsonPath.getString("data[7].object");
        String action8=jsonPath.getString("data[7].action");
        int changes_count8=jsonPath.getInt("data[7].changes_count");
        String object9=jsonPath.getString("data[8].object");
        String action9=jsonPath.getString("data[8].action");
        int changes_count9=jsonPath.getInt("data[8].changes_count");
        String object10=jsonPath.getString("data[9].object");
        String action10=jsonPath.getString("data[9].action");
        int changes_count10=jsonPath.getInt("data[9].changes_count");
        String object11=jsonPath.getString("data[10].object");
        String action11=jsonPath.getString("data[10].action");
        int changes_count11=jsonPath.getInt("data[10].changes_count");
        String object12=jsonPath.getString("data[11].object");
        String action12=jsonPath.getString("data[11].action");
        int changes_count12=jsonPath.getInt("data[11].changes_count");
        String object13=jsonPath.getString("data[12].object");
        String action13=jsonPath.getString("data[12].action");
        int changes_count13=jsonPath.getInt("data[12].changes_count");
        String object14=jsonPath.getString("data[13].object");
        String action14=jsonPath.getString("data[13].action");
        int changes_count14=jsonPath.getInt("data[13].changes_count");
        String object15=jsonPath.getString("data[14].object");
        String action15=jsonPath.getString("data[14].action");
        int changes_count15=jsonPath.getInt("data[14].changes_count");
        String object16=jsonPath.getString("data[15].object");
        String action16=jsonPath.getString("data[15].action");
        int changes_count16=jsonPath.getInt("data[15].changes_count");
        String object17=jsonPath.getString("data[16].object");
        String action17=jsonPath.getString("data[16].action");
        int changes_count17=jsonPath.getInt("data[16].changes_count");
        String object18=jsonPath.getString("data[17].object");
        String action18=jsonPath.getString("data[17].action");
        int changes_count18=jsonPath.getInt("data[17].changes_count");
        String object19=jsonPath.getString("data[18].object");
        String action19=jsonPath.getString("data[18].action");
        int changes_count19=jsonPath.getInt("data[18].changes_count");
        String object20=jsonPath.getString("data[19].object");
        String action20=jsonPath.getString("data[19].action");
        int changes_count20=jsonPath.getInt("data[19].changes_count");


        Assert.assertTrue(!data.isEmpty());
        Assert.assertTrue(idUser==1);
        Assert.assertTrue(full_nameUser.equals("Колокольцев Владимир Александрович"));
        Assert.assertTrue(object.equals("Ход исполнения"));
        Assert.assertTrue(action.equals("Изменение"));
        Assert.assertTrue(changes_count==1);
        Assert.assertTrue(object2.equals("Событие"));
        Assert.assertTrue(action2.equals("Создание"));
        Assert.assertTrue(changes_count2==4);
        Assert.assertTrue(object3.equals("Ход исполнения"));
        Assert.assertTrue(action3.equals("Изменение"));
        Assert.assertTrue(changes_count3==2);
        Assert.assertTrue(object4.equals("Письмо соисполнителя исполнителя в МВД России"));
        Assert.assertTrue(action4.equals("Изменение"));
        Assert.assertTrue(changes_count4==2);
        Assert.assertTrue(object5.equals("Исполнитель в МВД России"));
        Assert.assertTrue(action5.equals("Изменение"));
        Assert.assertTrue(changes_count5==1);
        Assert.assertTrue(object6.equals("Срок исполнения исполнителя"));
        Assert.assertTrue(action6.equals("Изменение"));
        Assert.assertTrue(changes_count6==1);
        Assert.assertTrue(object7.equals("Сроки исполнения поручения для исполнителей"));
        Assert.assertTrue(action7.equals("Изменение"));
        Assert.assertTrue(changes_count7==3);
        Assert.assertTrue(object8.equals("Письмо соисполнителя головного исполнителя"));
        Assert.assertTrue(action8.equals("Изменение"));
        Assert.assertTrue(changes_count8==2);
        Assert.assertTrue(object9.equals("Соисполнитель Головного исполнителя"));
        Assert.assertTrue(action9.equals("Изменение"));
        Assert.assertTrue(changes_count9==1);
        Assert.assertTrue(object10.equals("Соисполнитель Головного исполнителя"));
        Assert.assertTrue(action10.equals("Изменение"));
        Assert.assertTrue(changes_count10==1);
        Assert.assertTrue(object11.equals("Срок исполнения Головного исполнителя"));
        Assert.assertTrue(action11.equals("Изменение"));
        Assert.assertTrue(changes_count11==2);
        Assert.assertTrue(object12.equals("Головной исполнитель поручения"));
        Assert.assertTrue(action12.equals("Изменение"));
        Assert.assertTrue(changes_count12==3);
        Assert.assertTrue(object13.equals("Поручение"));
        Assert.assertTrue(action13.equals("Изменение"));
        Assert.assertTrue(changes_count13==3);
        Assert.assertTrue(object14.equals("Ход исполнения"));
        Assert.assertTrue(action14.equals("Создание"));
        Assert.assertTrue(changes_count14==5);
        Assert.assertTrue(object15.equals("Письмо соисполнителя исполнителя в МВД России"));
        Assert.assertTrue(action15.equals("Создание"));
        Assert.assertTrue(changes_count15==2);
        Assert.assertTrue(object16.equals("Соисполнитель исполнителя в МВД России"));
        Assert.assertTrue(action16.equals("Создание"));
        Assert.assertTrue(changes_count16==2);
        Assert.assertTrue(object17.equals("Исполнитель в МВД России"));
        Assert.assertTrue(action17.equals("Создание"));
        Assert.assertTrue(changes_count17==1);
        Assert.assertTrue(object18.equals("Срок исполнения исполнителя"));
        Assert.assertTrue(action18.equals("Создание"));
        Assert.assertTrue(changes_count18==3);
        Assert.assertTrue(object19.equals("Сроки исполнения поручения для исполнителей"));
        Assert.assertTrue(action19.equals("Создание"));
        Assert.assertTrue(changes_count19==5);
        Assert.assertTrue(object20.equals("Письмо соисполнителя головного исполнителя"));
        Assert.assertTrue(action20.equals("Создание"));
        Assert.assertTrue(changes_count20==2);

    }

    @Test
    public void step08() {
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut6)
                .params("items", 20)
                .when()
                .get(API_ASSIGNMENTS + idAssignments + "/changes")
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String error=jsonPath.getString("error");
        Assert.assertTrue(error.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void step09(){
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut6)
                .when()
                .get(API_DOCUMENTS+"/"+idDocument+"/changes")
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error=jsonPath.getString("error");
        Assert.assertTrue(error.equals("Доступ к ресурсу запрещен"));
    }
    @Test
    public void step10(){
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut2)
                .when()
                .get(API_DOCUMENTS+"/"+idDocument+"/changes")
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String data=jsonPath.getString("data");
        int idUser=jsonPath.getInt("data[0].changed_by.id");
        String full_nameUser=jsonPath.getString("data[0].changed_by.full_name");
        String object=jsonPath.getString("data[0].object");
        String action=jsonPath.getString("data[0].action");
        int changes_count=jsonPath.getInt("data[0].changes_count");
        String field=jsonPath.getString("data[0].changes[0].field");
        String old_value=jsonPath.getString("data[0].changes[0].old_value");
        String new_value=jsonPath.getString("data[0].changes[0].new_value");
        String object2=jsonPath.getString("data[1].object");
        String action2=jsonPath.getString("data[1].action");
        int changes_count2=jsonPath.getInt("data[1].changes_count");
        String object3=jsonPath.getString("data[2].object");
        String action3=jsonPath.getString("data[2].action");
        int changes_count3=jsonPath.getInt("data[2].changes_count");
        String object4=jsonPath.getString("data[3].object");
        String action4=jsonPath.getString("data[3].action");
        int changes_count4=jsonPath.getInt("data[3].changes_count");
        String object5=jsonPath.getString("data[4].object");
        String action5=jsonPath.getString("data[4].action");
        int changes_count5=jsonPath.getInt("data[4].changes_count");
        String object6=jsonPath.getString("data[5].object");
        String action6=jsonPath.getString("data[5].action");
        int changes_count6=jsonPath.getInt("data[5].changes_count");
        String object7=jsonPath.getString("data[6].object");
        String action7=jsonPath.getString("data[6].action");
        int changes_count7=jsonPath.getInt("data[6].changes_count");
        String object8=jsonPath.getString("data[7].object");
        String action8=jsonPath.getString("data[7].action");
        int changes_count8=jsonPath.getInt("data[7].changes_count");
        String object9=jsonPath.getString("data[8].object");
        String action9=jsonPath.getString("data[8].action");
        int changes_count9=jsonPath.getInt("data[8].changes_count");
        String object10=jsonPath.getString("data[9].object");
        String action10=jsonPath.getString("data[9].action");
        int changes_count10=jsonPath.getInt("data[9].changes_count");


        Assert.assertTrue(!data.isEmpty());
        Assert.assertTrue(idUser==1);
        Assert.assertTrue(full_nameUser.equals("Колокольцев Владимир Александрович"));
        Assert.assertTrue(object.equals("Ход исполнения"));
        Assert.assertTrue(action.equals("Изменение"));
        Assert.assertTrue(changes_count==1);
        Assert.assertTrue(field.equals("Состояние контроля"));
        Assert.assertTrue(old_value.equals("Исполняется"));
        Assert.assertTrue(new_value.equals("bdfbfdfvrefrrrrsssss123456"));
        Assert.assertTrue(object2.equals("Событие"));
        Assert.assertTrue(action2.equals("Создание"));
        Assert.assertTrue(changes_count2==4);
        Assert.assertTrue(object3.equals("Ход исполнения"));
        Assert.assertTrue(action3.equals("Изменение"));
        Assert.assertTrue(changes_count3==2);
        Assert.assertTrue(object4.equals("Письмо соисполнителя исполнителя в МВД России"));
        Assert.assertTrue(action4.equals("Изменение"));
        Assert.assertTrue(changes_count4==2);
        Assert.assertTrue(object5.equals("Исполнитель в МВД России"));
        Assert.assertTrue(action5.equals("Изменение"));
        Assert.assertTrue(changes_count5==1);
        Assert.assertTrue(object6.equals("Срок исполнения исполнителя"));
        Assert.assertTrue(action6.equals("Изменение"));
        Assert.assertTrue(changes_count6==1);
        Assert.assertTrue(object7.equals("Сроки исполнения поручения для исполнителей"));
        Assert.assertTrue(action7.equals("Изменение"));
        Assert.assertTrue(changes_count7==3);
        Assert.assertTrue(object8.equals("Письмо соисполнителя головного исполнителя"));
        Assert.assertTrue(action8.equals("Изменение"));
        Assert.assertTrue(changes_count8==2);
        Assert.assertTrue(object9.equals("Соисполнитель Головного исполнителя"));
        Assert.assertTrue(action9.equals("Изменение"));
        Assert.assertTrue(changes_count9==1);
        Assert.assertTrue(object10.equals("Соисполнитель Головного исполнителя"));
        Assert.assertTrue(action10.equals("Изменение"));
        Assert.assertTrue(changes_count10==1);
    }


}
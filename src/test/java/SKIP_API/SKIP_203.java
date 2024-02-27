package SKIP_API;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class SKIP_203 {
    String API_DOCUMENTS = "http://api.skip.rtech.ru/v1/documents";
    String API_DEADLINE_BASES = "http://api.skip.rtech.ru/v1/classifiers/deadline_bases";
    String API_EXECUTIONS = "http://api.skip.rtech.ru/v1/executions";

    int idDocument;
    int idExecutor;
    int idDb;
    int idAut1 = 1;

    String sedDoc = "http://incoming-documents.sed2.rtech.ru/cards/65c5d0d9d8fe8d1036000003";
    String uidDoc = "0289a43bc2359182f95e399d29dc1674510ff22a7f8e5d9c13b01a74dd56fe1430";
    String regNoDoc = "111/108";
    String dateDoc = "09.02.2024";
    String idProvider = "525e9f767da3000002000001";


    @BeforeClass
    public void step001() {

        // Параметры запроса
        JsonObject requestPayload = new JsonObject();
        requestPayload.addProperty("skip_reg_date", "2024-01-17T06:06:31.485Z");
        requestPayload.addProperty("control_official_id", "54e35f6179fa007422000001");
        requestPayload.addProperty("control_subject_id", 83);
        requestPayload.addProperty("document_name", "Тест 404");
        requestPayload.addProperty("document_execution_state_id", 11);
        requestPayload.addProperty("skip_document_type_id", 32);
        requestPayload.addProperty("internal", false);
        requestPayload.addProperty("short_description", "404");

        JsonArray assignmentsArray = new JsonArray();
        JsonObject assignments = new JsonObject();

        assignments.addProperty("number", 1);
        assignments.addProperty("content", "content");

        JsonArray executorsArray = new JsonArray();
        JsonObject executors = new JsonObject();

        executors.addProperty("organization_id", "6426a7c3379100001e000001");


        executorsArray.add(executors);
        assignments.add("executors", executorsArray);

        assignmentsArray.add(assignments);
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
        int idDoc = jsonPath.getInt("data.id");
        int idExec = jsonPath.getInt("data.assignments[0].executors[0].id");

        idDocument = idDoc;
        idExecutor = idExec;

    }

    @BeforeClass
    public void step002() {
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .when()
                .get(API_DEADLINE_BASES)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.getInt("data[0].id");
        idDb = id;

    }

    @Test
    public void step01() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\txt.txt";
        File file = new File(filePath);
        String deadlineDate = "19.11.2024";
        String documentName = "test";
        String documentType = "Documents::Document";
        String fileContent = "t";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                .multiPart("document_name", documentName)
                .multiPart("document_type", documentType)
                .multiPart("document_id", idDocument)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("sed_document[url]", sedDoc)
                .multiPart("sed_document[reg_number]", regNoDoc)
                .multiPart("sed_document[reg_date]", dateDoc)
                .multiPart("sed_document[provider_id]", idProvider)
                .multiPart("content", fileContent)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.getInt("data.id");
        String dDate = jsonPath.getString("data.deadline_date");
        int dbId = jsonPath.getInt("data.deadline_basis.id");
        int basisId = jsonPath.getInt("data.events[0].basis.id");
        String name = jsonPath.getString("data.events[0].document_name");
        String docType = jsonPath.getString("data.events[0].document_type");
        int docId = jsonPath.getInt("data.events[0].document_id");
        String docUrl = jsonPath.getString("data.events[0].document_url");
        String content = jsonPath.getString("data.events[0].content");
        String userName = jsonPath.getString("data.events[0].updated_by.name");
        int imagesId = jsonPath.getInt("data.events[0].images[0].id");
        String fullNameImages = jsonPath.getString("data.events[0].images[0].full_name");
        String imagesUrl = jsonPath.getString("data.events[0].images[0].url");

        Assert.assertNotNull(id);
        Assert.assertTrue(dDate.equals("2024-11-19T00:00:00.000Z"));
        Assert.assertTrue(dbId == idDb);
        Assert.assertTrue(basisId == idDb);
        Assert.assertTrue(name.equals(documentName));
        Assert.assertTrue(docType.equals(documentType));
        Assert.assertTrue(docId != 0);
        Assert.assertNotNull(docUrl);
        Assert.assertTrue(content.equals(fileContent));
        Assert.assertTrue(userName.equals("Колокольцев В.А."));
        Assert.assertNotNull(imagesId);
        Assert.assertTrue(fullNameImages.equals("txt.txt"));
        Assert.assertNotNull(imagesUrl);
    }

    @Test
    public void step02() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\txt.txt";
        File file = new File(filePath);
        String deadlineDate = "20.11.2024";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.getInt("data.id");
        String dDate = jsonPath.getString("data.deadline_date");
        int dbId = jsonPath.getInt("data.deadline_basis.id");
        int basisId = jsonPath.getInt("data.events[0].basis.id");
        String name = jsonPath.getString("data.events[0].document_name");
        String docType = jsonPath.getString("data.events[0].document_type");
        String docId = jsonPath.get("data.events[0].document_id");
        String docUrl = jsonPath.getString("data.events[0].document_url");
        String content = jsonPath.getString("data.events[0].content");
        String userName = jsonPath.getString("data.events[0].updated_by.name");
        int imagesId = jsonPath.getInt("data.events[0].images[0].id");
        String fullNameImages = jsonPath.getString("data.events[0].images[0].full_name");
        String imagesUrl = jsonPath.getString("data.events[0].images[0].url");

        Assert.assertNotNull(id);
        Assert.assertTrue(dDate.equals("2024-11-20T00:00:00.000Z"));
        Assert.assertTrue(dbId == idDb);
        Assert.assertTrue(basisId == idDb);
        Assert.assertNull(name);
        Assert.assertNull(docType);
        Assert.assertNull(docId);
        Assert.assertNull(docUrl);
        Assert.assertNull(content);
        Assert.assertTrue(userName.equals("Колокольцев В.А."));
        Assert.assertNotNull(imagesId);
        Assert.assertTrue(fullNameImages.equals("txt.txt"));
        Assert.assertNotNull(imagesUrl);
    }

    @Test
    public void step03() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\txt.txt";
        File file = new File(filePath);
        String deadlineDate = "20.11.2024";
        String documentType = "Documents::Document";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                .multiPart("document_type", documentType)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.deadline_date[0]");

        Assert.assertTrue(error.equals("уже существует"));
    }

    @Test
    public void step04() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\txt.txt";
        File file = new File(filePath);
        String deadlineDate = "21.11.2024";
        String documentType = "Documents::Document";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                .multiPart("document_type", documentType)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.document_id[0]");

        Assert.assertTrue(error.equals("имеет непредусмотренное значение"));
    }


    @Test
    public void step05() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\txt.txt";
        File file = new File(filePath);
        String deadlineDate = "21.11.2024";
        String documentType = "Documents::Document";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                .multiPart("document_type", documentType)
                .multiPart("document_id", idDocument)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.getInt("data.id");
        String dDate = jsonPath.getString("data.deadline_date");
        int dbId = jsonPath.getInt("data.deadline_basis.id");
        int basisId = jsonPath.getInt("data.events[0].basis.id");
        String name = jsonPath.getString("data.events[0].document_name");
        String docType = jsonPath.getString("data.events[0].document_type");
        int docId = jsonPath.get("data.events[0].document_id");
        String docUrl = jsonPath.getString("data.events[0].document_url");
        String content = jsonPath.getString("data.events[0].content");
        String userName = jsonPath.getString("data.events[0].updated_by.name");
        int imagesId = jsonPath.getInt("data.events[0].images[0].id");
        String fullNameImages = jsonPath.getString("data.events[0].images[0].full_name");
        String imagesUrl = jsonPath.getString("data.events[0].images[0].url");

        Assert.assertNotNull(id);
        Assert.assertTrue(dDate.equals("2024-11-21T00:00:00.000Z"));
        Assert.assertTrue(dbId == idDb);
        Assert.assertTrue(basisId == idDb);
        Assert.assertNull(name);
        Assert.assertTrue(docType.equals("Documents::Document"));
        Assert.assertTrue(docId == idDocument);
        Assert.assertNotNull(docUrl);
        Assert.assertNull(content);
        Assert.assertTrue(userName.equals("Колокольцев В.А."));
        Assert.assertNotNull(imagesId);
        Assert.assertTrue(fullNameImages.equals("txt.txt"));
        Assert.assertNotNull(imagesUrl);
    }

    @Test
    public void step06() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\txt.txt";
        File file = new File(filePath);
        String deadlineDate = "22.11.2024";
        String documentType = "Sed::Document";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                .multiPart("document_type", documentType)
                .multiPart("document_id", idDocument)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.document_id[0]");

        Assert.assertTrue(error.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step07() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\txt.txt";
        File file = new File(filePath);
        String deadlineDate = "22.11.2024";
        String documentType = "Sed::Document";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                .multiPart("document_type", documentType)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error1 = jsonPath.getString("errors.sed_document.url[0]");
        String error2 = jsonPath.getString("errors.sed_document.reg_number[0]");
        String error3 = jsonPath.getString("errors.sed_document.reg_date[0]");
        String error4 = jsonPath.getString("errors.sed_document.provider_id[0]");

        Assert.assertTrue(error1.equals("не может быть пустым"));
        Assert.assertTrue(error2.equals("не может быть пустым"));
        Assert.assertTrue(error3.equals("не может быть пустым"));
        Assert.assertTrue(error4.equals("не может быть пустым"));

    }

    @Test
    public void step08() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\txt.txt";
        File file = new File(filePath);
        String deadlineDate = "22.11.2024";
        String documentType = "Sed::Document";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                .multiPart("document_type", documentType)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("sed_document[url]", sedDoc)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error1 = jsonPath.getString("errors.sed_document.reg_number[0]");
        String error2 = jsonPath.getString("errors.sed_document.reg_date[0]");
        String error3 = jsonPath.getString("errors.sed_document.provider_id[0]");

        Assert.assertTrue(error1.equals("не может быть пустым"));
        Assert.assertTrue(error2.equals("не может быть пустым"));
        Assert.assertTrue(error3.equals("не может быть пустым"));
    }

    @Test
    public void step09() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\txt.txt";
        File file = new File(filePath);
        String deadlineDate = "22.11.2024";
        String documentType = "Sed::Document";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                .multiPart("document_type", documentType)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("sed_document[url]", sedDoc)
                .multiPart("sed_document[reg_number]", regNoDoc)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error1 = jsonPath.getString("errors.sed_document.reg_date[0]");
        String error2 = jsonPath.getString("errors.sed_document.provider_id[0]");

        Assert.assertTrue(error1.equals("не может быть пустым"));
        Assert.assertTrue(error2.equals("не может быть пустым"));
    }

    @Test
    public void step10() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\txt.txt";
        File file = new File(filePath);
        String deadlineDate = "22.11.2024";
        String documentType = "Sed::Document";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                .multiPart("document_type", documentType)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("sed_document[url]", sedDoc)
                .multiPart("sed_document[reg_number]", regNoDoc)
                .multiPart("sed_document[reg_date]", dateDoc)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.sed_document.provider_id[0]");

        Assert.assertTrue(error.equals("не может быть пустым"));
    }

    @Test
    public void step11() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\txt.txt";
        File file = new File(filePath);
        String deadlineDate = "22.11.2024";
        String documentType = "Sed::Document";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                .multiPart("document_type", documentType)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("sed_document[url]", sedDoc)
                .multiPart("sed_document[reg_number]", regNoDoc)
                .multiPart("sed_document[reg_date]", dateDoc)
                .multiPart("sed_document[provider_id]", idProvider)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.getInt("data.id");
        String dDate = jsonPath.getString("data.deadline_date");
        int dbId = jsonPath.getInt("data.deadline_basis.id");
        int basisId = jsonPath.getInt("data.events[0].basis.id");
        String name = jsonPath.getString("data.events[0].document_name");
        String docType = jsonPath.getString("data.events[0].document_type");
        int docId = jsonPath.getInt("data.events[0].document_id");
        String docUrl = jsonPath.getString("data.events[0].document_url");
        String content = jsonPath.getString("data.events[0].content");
        String userName = jsonPath.getString("data.events[0].updated_by.name");
        int imagesId = jsonPath.getInt("data.events[0].images[0].id");
        String fullNameImages = jsonPath.getString("data.events[0].images[0].full_name");
        String imagesUrl = jsonPath.getString("data.events[0].images[0].url");

        Assert.assertNotNull(id);
        Assert.assertTrue(dDate.equals("2024-11-22T00:00:00.000Z"));
        Assert.assertTrue(dbId == idDb);
        Assert.assertTrue(basisId == idDb);
        Assert.assertNull(name);
        Assert.assertTrue(docType.equals(documentType));
        Assert.assertTrue(docId != 0);
        Assert.assertNotNull(docUrl);
        Assert.assertNull(content);
        Assert.assertTrue(userName.equals("Колокольцев В.А."));
        Assert.assertNotNull(imagesId);
        Assert.assertTrue(fullNameImages.equals("txt.txt"));
        Assert.assertNotNull(imagesUrl);
    }

    @Test
    public void step12() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\txt.txt";
        File file = new File(filePath);
        String deadlineDate = "24.11.2024";
        String documentType = "Sed::Document";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                .multiPart("document_type", documentType)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("sed_document[url]", sedDoc)
                .multiPart("sed_document[reg_number]", regNoDoc)
                .multiPart("sed_document[reg_date]", dateDoc)
                .multiPart("sed_document[provider_id]", idProvider)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.executor_id[0]");

        Assert.assertTrue(error.equals("не может быть пустым"));
    }

    @Test
    public void step13() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\txt.txt";
        File file = new File(filePath);
        String deadlineDate = "24.11.2024";
        String documentType = "Sed::Document";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", "1335421523")
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                .multiPart("document_type", documentType)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("sed_document[url]", sedDoc)
                .multiPart("sed_document[reg_number]", regNoDoc)
                .multiPart("sed_document[reg_date]", dateDoc)
                .multiPart("sed_document[provider_id]", idProvider)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.executor_id[0]");

        Assert.assertTrue(error.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step14() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\txt.txt";
        File file = new File(filePath);
        String documentType = "Sed::Document";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_basis_id", idDb)
                .multiPart("document_type", documentType)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("sed_document[url]", sedDoc)
                .multiPart("sed_document[reg_number]", regNoDoc)
                .multiPart("sed_document[reg_date]", dateDoc)
                .multiPart("sed_document[provider_id]", idProvider)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.deadline_date[0]");

        Assert.assertTrue(error.equals("не может быть пустым"));
    }

    @Test
    public void step15() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\txt.txt";
        File file = new File(filePath);
        String deadlineDate = "13142411";
        //   String documentName = "test";
        String documentType = "Sed::Document";
        //  String fileContent = "t";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                //  .multiPart("document_name", documentName)
                .multiPart("document_type", documentType)
                // .multiPart("document_id", idDocument)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("sed_document[url]", sedDoc)
                .multiPart("sed_document[reg_number]", regNoDoc)
                .multiPart("sed_document[reg_date]", dateDoc)
                .multiPart("sed_document[provider_id]", idProvider)
                //    .multiPart("content", fileContent)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.deadline_date[0]");

        Assert.assertTrue(error.equals("имеет неверное значение"));
    }

    @Test
    public void step16() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\txt.txt";
        File file = new File(filePath);
        String deadlineDate = "13.11.2024";
        //   String documentName = "test";
        String documentType = "Sed::Document";
        //  String fileContent = "t";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                // .multiPart("deadline_basis_id", idDb)
                //  .multiPart("document_name", documentName)
                .multiPart("document_type", documentType)
                // .multiPart("document_id", idDocument)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("sed_document[url]", sedDoc)
                .multiPart("sed_document[reg_number]", regNoDoc)
                .multiPart("sed_document[reg_date]", dateDoc)
                .multiPart("sed_document[provider_id]", idProvider)
                //    .multiPart("content", fileContent)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.deadline_basis_id[0]");

        Assert.assertTrue(error.equals("не может быть пустым"));
    }

    @Test
    public void step17() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\txt.txt";
        File file = new File(filePath);
        String deadlineDate = "13.11.2024";
        //   String documentName = "test";
        String documentType = "Sed::Document";
        //  String fileContent = "t";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", "13425233")
                //  .multiPart("document_name", documentName)
                .multiPart("document_type", documentType)
                // .multiPart("document_id", idDocument)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("sed_document[url]", sedDoc)
                .multiPart("sed_document[reg_number]", regNoDoc)
                .multiPart("sed_document[reg_date]", dateDoc)
                .multiPart("sed_document[provider_id]", idProvider)
                //    .multiPart("content", fileContent)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.deadline_basis_id[0]");

        Assert.assertTrue(error.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step18() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл 0 байт.txt";
        File file = new File(filePath);
        String deadlineDate = "13.11.2024";
        //   String documentName = "test";
        String documentType = "Sed::Document";
        //  String fileContent = "t";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                //  .multiPart("document_name", documentName)
                .multiPart("document_type", documentType)
                // .multiPart("document_id", idDocument)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("sed_document[url]", sedDoc)
                .multiPart("sed_document[reg_number]", regNoDoc)
                .multiPart("sed_document[reg_date]", dateDoc)
                .multiPart("sed_document[provider_id]", idProvider)
                //    .multiPart("content", fileContent)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.images.0.original_file[0]");

        Assert.assertTrue(error.equals("Файл размером 0 байт не может быть добавлен"));
    }

    @Test
    public void step19() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл 0 байт.txt";
        File file = new File(filePath);
        String filePath2 = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\Файл 353 мб.mp4";
        File file2 = new File(filePath2);
        String deadlineDate = "13.11.2024";
        //   String documentName = "test";
        String documentType = "Sed::Document";
        //  String fileContent = "t";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                //  .multiPart("document_name", documentName)
                .multiPart("document_type", documentType)
                // .multiPart("document_id", idDocument)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("sed_document[url]", sedDoc)
                .multiPart("sed_document[reg_number]", regNoDoc)
                .multiPart("sed_document[reg_date]", dateDoc)
                .multiPart("sed_document[provider_id]", idProvider)
                //    .multiPart("content", fileContent)
                .multiPart("images[][original_file]", file)
                .multiPart("images[][original_file]", file2)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error1 = jsonPath.getString("errors.images.0.original_file[0]");
        String error2 = jsonPath.getString("errors.images.1.original_file[0]");


        Assert.assertTrue(error1.equals("Файл размером 0 байт не может быть добавлен"));
        Assert.assertTrue(error2.equals("Превышен максимальный размер загружаемого файла (300 МБ)"));
    }

    @Test
    public void step20() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\290.mp4";
        File file = new File(filePath);
        String deadlineDate = "13.11.2024";
        //   String documentName = "test";
        String documentType = "Sed::Document";
        //  String fileContent = "t";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                //  .multiPart("document_name", documentName)
                .multiPart("document_type", documentType)
                // .multiPart("document_id", idDocument)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("sed_document[url]", sedDoc)
                .multiPart("sed_document[reg_number]", regNoDoc)
                .multiPart("sed_document[reg_date]", dateDoc)
                .multiPart("sed_document[provider_id]", idProvider)
                //    .multiPart("content", fileContent)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.getInt("data.id");
        String dDate = jsonPath.getString("data.deadline_date");
        int dbId = jsonPath.getInt("data.deadline_basis.id");
        int basisId = jsonPath.getInt("data.events[0].basis.id");
        String name = jsonPath.getString("data.events[0].document_name");
        String docType = jsonPath.getString("data.events[0].document_type");
        int docId = jsonPath.getInt("data.events[0].document_id");
        String docUrl = jsonPath.getString("data.events[0].document_url");
        String content = jsonPath.getString("data.events[0].content");
        String userName = jsonPath.getString("data.events[0].updated_by.name");
        int imagesId = jsonPath.getInt("data.events[0].images[0].id");
        String fullNameImages = jsonPath.getString("data.events[0].images[0].full_name");
        String imagesUrl = jsonPath.getString("data.events[0].images[0].url");

        Assert.assertNotNull(id);
        Assert.assertTrue(dDate.equals("2024-11-13T00:00:00.000Z"));
        Assert.assertTrue(dbId == idDb);
        Assert.assertTrue(basisId == idDb);
        Assert.assertNull(name);
        Assert.assertTrue(docType.equals(documentType));
        Assert.assertTrue(docId != 0);
        Assert.assertNotNull(docUrl);
        Assert.assertNull(content);
        Assert.assertTrue(userName.equals("Колокольцев В.А."));
        Assert.assertNotNull(imagesId);
        Assert.assertTrue(fullNameImages.equals("290.mp4"));
        Assert.assertNotNull(imagesUrl);
    }

    @Test
    public void step21() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\dll файл.dll";
        File file = new File(filePath);
        String deadlineDate = "01.11.2024";
        //   String documentName = "test";
        String documentType = "Sed::Document";
        //  String fileContent = "t";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                //  .multiPart("document_name", documentName)
                .multiPart("document_type", documentType)
                // .multiPart("document_id", idDocument)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("sed_document[url]", sedDoc)
                .multiPart("sed_document[reg_number]", regNoDoc)
                .multiPart("sed_document[reg_date]", dateDoc)
                .multiPart("sed_document[provider_id]", idProvider)
                //    .multiPart("content", fileContent)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.getString("errors.images.0.original_file[0]");
        Assert.assertTrue(error.equals("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));
    }

    @Test
    public void step22() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл accdb.accdb";
        File file = new File(filePath);
        String deadlineDate = "01.11.2024";
        //   String documentName = "test";
        String documentType = "Sed::Document";
        //  String fileContent = "t";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                //  .multiPart("document_name", documentName)
                .multiPart("document_type", documentType)
                // .multiPart("document_id", idDocument)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("sed_document[url]", sedDoc)
                .multiPart("sed_document[reg_number]", regNoDoc)
                .multiPart("sed_document[reg_date]", dateDoc)
                .multiPart("sed_document[provider_id]", idProvider)
                //    .multiPart("content", fileContent)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.getString("errors.images.0.original_file[0]");
        Assert.assertTrue(error.equals("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));
    }

    @Test
    public void step23() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл accdc.accdc";
        File file = new File(filePath);
        String deadlineDate = "01.11.2024";
        //   String documentName = "test";
        String documentType = "Sed::Document";
        //  String fileContent = "t";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                //  .multiPart("document_name", documentName)
                .multiPart("document_type", documentType)
                // .multiPart("document_id", idDocument)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("sed_document[url]", sedDoc)
                .multiPart("sed_document[reg_number]", regNoDoc)
                .multiPart("sed_document[reg_date]", dateDoc)
                .multiPart("sed_document[provider_id]", idProvider)
                //    .multiPart("content", fileContent)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.getString("errors.images.0.original_file[0]");
        Assert.assertTrue(error.equals("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));
    }

    @Test
    public void step24() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл accde.accde";
        File file = new File(filePath);
        String deadlineDate = "01.11.2024";
        //   String documentName = "test";
        String documentType = "Sed::Document";
        //  String fileContent = "t";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                //  .multiPart("document_name", documentName)
                .multiPart("document_type", documentType)
                // .multiPart("document_id", idDocument)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("sed_document[url]", sedDoc)
                .multiPart("sed_document[reg_number]", regNoDoc)
                .multiPart("sed_document[reg_date]", dateDoc)
                .multiPart("sed_document[provider_id]", idProvider)
                //    .multiPart("content", fileContent)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.getString("errors.images.0.original_file[0]");
        Assert.assertTrue(error.equals("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));
    }

    @Test
    public void step25() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл accdp.accdp";
        File file = new File(filePath);
        String deadlineDate = "01.11.2024";
        //   String documentName = "test";
        String documentType = "Sed::Document";
        //  String fileContent = "t";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                //  .multiPart("document_name", documentName)
                .multiPart("document_type", documentType)
                // .multiPart("document_id", idDocument)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("sed_document[url]", sedDoc)
                .multiPart("sed_document[reg_number]", regNoDoc)
                .multiPart("sed_document[reg_date]", dateDoc)
                .multiPart("sed_document[provider_id]", idProvider)
                //    .multiPart("content", fileContent)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.getString("errors.images.0.original_file[0]");
        Assert.assertTrue(error.equals("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));
    }

    @Test
    public void step26() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл accdr.accdr";
        File file = new File(filePath);
        String deadlineDate = "01.11.2024";
        //   String documentName = "test";
        String documentType = "Sed::Document";
        //  String fileContent = "t";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                //  .multiPart("document_name", documentName)
                .multiPart("document_type", documentType)
                // .multiPart("document_id", idDocument)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("sed_document[url]", sedDoc)
                .multiPart("sed_document[reg_number]", regNoDoc)
                .multiPart("sed_document[reg_date]", dateDoc)
                .multiPart("sed_document[provider_id]", idProvider)
                //    .multiPart("content", fileContent)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.getString("errors.images.0.original_file[0]");
        Assert.assertTrue(error.equals("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));
    }

    @Test
    public void step27() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл cab.cab";
        File file = new File(filePath);
        String deadlineDate = "01.11.2024";
        //   String documentName = "test";
        String documentType = "Sed::Document";
        //  String fileContent = "t";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                //  .multiPart("document_name", documentName)
                .multiPart("document_type", documentType)
                // .multiPart("document_id", idDocument)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("sed_document[url]", sedDoc)
                .multiPart("sed_document[reg_number]", regNoDoc)
                .multiPart("sed_document[reg_date]", dateDoc)
                .multiPart("sed_document[provider_id]", idProvider)
                //    .multiPart("content", fileContent)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.getString("errors.images.0.original_file[0]");
        Assert.assertTrue(error.equals("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));
    }

    @Test
    public void step28() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл deb.deb";
        File file = new File(filePath);
        String deadlineDate = "01.11.2024";
        //   String documentName = "test";
        String documentType = "Sed::Document";
        //  String fileContent = "t";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                //  .multiPart("document_name", documentName)
                .multiPart("document_type", documentType)
                // .multiPart("document_id", idDocument)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("sed_document[url]", sedDoc)
                .multiPart("sed_document[reg_number]", regNoDoc)
                .multiPart("sed_document[reg_date]", dateDoc)
                .multiPart("sed_document[provider_id]", idProvider)
                //    .multiPart("content", fileContent)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.getString("errors.images.0.original_file[0]");
        Assert.assertTrue(error.equals("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));
    }

    @Test
    public void step29() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл elf.elf";
        File file = new File(filePath);
        String deadlineDate = "01.11.2024";
        //   String documentName = "test";
        String documentType = "Sed::Document";
        //  String fileContent = "t";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                //  .multiPart("document_name", documentName)
                .multiPart("document_type", documentType)
                // .multiPart("document_id", idDocument)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("sed_document[url]", sedDoc)
                .multiPart("sed_document[reg_number]", regNoDoc)
                .multiPart("sed_document[reg_date]", dateDoc)
                .multiPart("sed_document[provider_id]", idProvider)
                //    .multiPart("content", fileContent)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.getString("errors.images.0.original_file[0]");
        Assert.assertTrue(error.equals("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));
    }

    @Test
    public void step30() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл eps.eps";
        File file = new File(filePath);
        String deadlineDate = "01.11.2024";
        //   String documentName = "test";
        String documentType = "Sed::Document";
        //  String fileContent = "t";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                //  .multiPart("document_name", documentName)
                .multiPart("document_type", documentType)
                // .multiPart("document_id", idDocument)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("sed_document[url]", sedDoc)
                .multiPart("sed_document[reg_number]", regNoDoc)
                .multiPart("sed_document[reg_date]", dateDoc)
                .multiPart("sed_document[provider_id]", idProvider)
                //    .multiPart("content", fileContent)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.getString("errors.images.0.original_file[0]");
        Assert.assertTrue(error.equals("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));
    }

    @Test
    public void step31() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл exe.exe";
        File file = new File(filePath);
        String deadlineDate = "01.11.2024";
        //   String documentName = "test";
        String documentType = "Sed::Document";
        //  String fileContent = "t";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                //  .multiPart("document_name", documentName)
                .multiPart("document_type", documentType)
                // .multiPart("document_id", idDocument)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("sed_document[url]", sedDoc)
                .multiPart("sed_document[reg_number]", regNoDoc)
                .multiPart("sed_document[reg_date]", dateDoc)
                .multiPart("sed_document[provider_id]", idProvider)
                //    .multiPart("content", fileContent)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.getString("errors.images.0.original_file[0]");
        Assert.assertTrue(error.equals("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));
    }

    @Test
    public void step32() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл iso.iso";
        File file = new File(filePath);
        String deadlineDate = "01.11.2024";
        //   String documentName = "test";
        String documentType = "Sed::Document";
        //  String fileContent = "t";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                //  .multiPart("document_name", documentName)
                .multiPart("document_type", documentType)
                // .multiPart("document_id", idDocument)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("sed_document[url]", sedDoc)
                .multiPart("sed_document[reg_number]", regNoDoc)
                .multiPart("sed_document[reg_date]", dateDoc)
                .multiPart("sed_document[provider_id]", idProvider)
                //    .multiPart("content", fileContent)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.getString("errors.images.0.original_file[0]");
        Assert.assertTrue(error.equals("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));
    }

    @Test
    public void step33() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл jar.jar";
        File file = new File(filePath);
        String deadlineDate = "01.11.2024";
        //   String documentName = "test";
        String documentType = "Sed::Document";
        //  String fileContent = "t";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                //  .multiPart("document_name", documentName)
                .multiPart("document_type", documentType)
                // .multiPart("document_id", idDocument)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("sed_document[url]", sedDoc)
                .multiPart("sed_document[reg_number]", regNoDoc)
                .multiPart("sed_document[reg_date]", dateDoc)
                .multiPart("sed_document[provider_id]", idProvider)
                //    .multiPart("content", fileContent)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.getString("errors.images.0.original_file[0]");
        Assert.assertTrue(error.equals("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));
    }

    @Test
    public void step34() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл mdb.mdb";
        File file = new File(filePath);
        String deadlineDate = "01.11.2024";
        //   String documentName = "test";
        String documentType = "Sed::Document";
        //  String fileContent = "t";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                //  .multiPart("document_name", documentName)
                .multiPart("document_type", documentType)
                // .multiPart("document_id", idDocument)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("sed_document[url]", sedDoc)
                .multiPart("sed_document[reg_number]", regNoDoc)
                .multiPart("sed_document[reg_date]", dateDoc)
                .multiPart("sed_document[provider_id]", idProvider)
                //    .multiPart("content", fileContent)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.getString("errors.images.0.original_file[0]");
        Assert.assertTrue(error.equals("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));
    }

    @Test
    public void step35() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл mdt.mdt";
        File file = new File(filePath);
        String deadlineDate = "01.11.2024";
        //   String documentName = "test";
        String documentType = "Sed::Document";
        //  String fileContent = "t";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                //  .multiPart("document_name", documentName)
                .multiPart("document_type", documentType)
                // .multiPart("document_id", idDocument)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("sed_document[url]", sedDoc)
                .multiPart("sed_document[reg_number]", regNoDoc)
                .multiPart("sed_document[reg_date]", dateDoc)
                .multiPart("sed_document[provider_id]", idProvider)
                //    .multiPart("content", fileContent)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.getString("errors.images.0.original_file[0]");
        Assert.assertTrue(error.equals("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));
    }

    @Test
    public void step36() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл msi.msi";
        File file = new File(filePath);
        String deadlineDate = "01.11.2024";
        //   String documentName = "test";
        String documentType = "Sed::Document";
        //  String fileContent = "t";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                //  .multiPart("document_name", documentName)
                .multiPart("document_type", documentType)
                // .multiPart("document_id", idDocument)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("sed_document[url]", sedDoc)
                .multiPart("sed_document[reg_number]", regNoDoc)
                .multiPart("sed_document[reg_date]", dateDoc)
                .multiPart("sed_document[provider_id]", idProvider)
                //    .multiPart("content", fileContent)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.getString("errors.images.0.original_file[0]");
        Assert.assertTrue(error.equals("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));
    }

    @Test
    public void step37() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\Файл ocx.ocx";
        File file = new File(filePath);
        String deadlineDate = "01.11.2024";
        //   String documentName = "test";
        String documentType = "Sed::Document";
        //  String fileContent = "t";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                //  .multiPart("document_name", documentName)
                .multiPart("document_type", documentType)
                // .multiPart("document_id", idDocument)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("sed_document[url]", sedDoc)
                .multiPart("sed_document[reg_number]", regNoDoc)
                .multiPart("sed_document[reg_date]", dateDoc)
                .multiPart("sed_document[provider_id]", idProvider)
                //    .multiPart("content", fileContent)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.getString("errors.images.0.original_file[0]");
        Assert.assertTrue(error.equals("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));
    }

    @Test
    public void step38() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл rpm.rpm";
        File file = new File(filePath);
        String deadlineDate = "01.11.2024";
        //   String documentName = "test";
        String documentType = "Sed::Document";
        //  String fileContent = "t";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                //  .multiPart("document_name", documentName)
                .multiPart("document_type", documentType)
                // .multiPart("document_id", idDocument)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("sed_document[url]", sedDoc)
                .multiPart("sed_document[reg_number]", regNoDoc)
                .multiPart("sed_document[reg_date]", dateDoc)
                .multiPart("sed_document[provider_id]", idProvider)
                //    .multiPart("content", fileContent)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.getString("errors.images.0.original_file[0]");
        Assert.assertTrue(error.equals("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));
    }

    @Test
    public void step39() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл scr.scr";
        File file = new File(filePath);
        String deadlineDate = "01.11.2024";
        //   String documentName = "test";
        String documentType = "Sed::Document";
        //  String fileContent = "t";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                //  .multiPart("document_name", documentName)
                .multiPart("document_type", documentType)
                // .multiPart("document_id", idDocument)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("sed_document[url]", sedDoc)
                .multiPart("sed_document[reg_number]", regNoDoc)
                .multiPart("sed_document[reg_date]", dateDoc)
                .multiPart("sed_document[provider_id]", idProvider)
                //    .multiPart("content", fileContent)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.getString("errors.images.0.original_file[0]");
        Assert.assertTrue(error.equals("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));
    }

    @Test
    public void step40() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\Файл класс.class";
        File file = new File(filePath);
        String deadlineDate = "01.11.2024";
        //   String documentName = "test";
        String documentType = "Sed::Document";
        //  String fileContent = "t";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                //  .multiPart("document_name", documentName)
                .multiPart("document_type", documentType)
                // .multiPart("document_id", idDocument)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("sed_document[url]", sedDoc)
                .multiPart("sed_document[reg_number]", regNoDoc)
                .multiPart("sed_document[reg_date]", dateDoc)
                .multiPart("sed_document[provider_id]", idProvider)
                //    .multiPart("content", fileContent)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.getString("errors.images.0.original_file[0]");
        Assert.assertTrue(error.equals("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));
    }

    @Test
    public void step41() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\file.dssds";
        File file = new File(filePath);
        String deadlineDate = "01.11.2024";
        //   String documentName = "test";
        String documentType = "Sed::Document";
        //  String fileContent = "t";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                //  .multiPart("document_name", documentName)
                .multiPart("document_type", documentType)
                // .multiPart("document_id", idDocument)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("sed_document[url]", sedDoc)
                .multiPart("sed_document[reg_number]", regNoDoc)
                .multiPart("sed_document[reg_date]", dateDoc)
                .multiPart("sed_document[provider_id]", idProvider)
                //    .multiPart("content", fileContent)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        int id = jsonPath.getInt("data.id");
        String dDate = jsonPath.getString("data.deadline_date");
        int dbId = jsonPath.getInt("data.deadline_basis.id");
        int basisId = jsonPath.getInt("data.events[0].basis.id");
        String name = jsonPath.getString("data.events[0].document_name");
        String docType = jsonPath.getString("data.events[0].document_type");
        int docId = jsonPath.getInt("data.events[0].document_id");
        String docUrl = jsonPath.getString("data.events[0].document_url");
        String content = jsonPath.getString("data.events[0].content");
        String userName = jsonPath.getString("data.events[0].updated_by.name");
        int imagesId = jsonPath.getInt("data.events[0].images[0].id");
        String fullNameImages = jsonPath.getString("data.events[0].images[0].full_name");
        String imagesUrl = jsonPath.getString("data.events[0].images[0].url");

        Assert.assertNotNull(id);
        Assert.assertTrue(dDate.equals("2024-11-01T00:00:00.000Z"));
        Assert.assertTrue(dbId == idDb);
        Assert.assertTrue(basisId == idDb);
        Assert.assertNull(name);
        Assert.assertTrue(docType.equals(documentType));
        Assert.assertTrue(docId != 0);
        Assert.assertNotNull(docUrl);
        Assert.assertNull(content);
        Assert.assertTrue(userName.equals("Колокольцев В.А."));
        Assert.assertNotNull(imagesId);
        Assert.assertTrue(fullNameImages.equals("file.dssds"));
        Assert.assertNotNull(imagesUrl);
    }

    @Test
    public void step42() {
        String deadlineDate = "02.11.2024";
        //   String documentName = "test";
        String documentType = "Sed::Document";
        //  String fileContent = "t";
        Response response = given()
                .header("Content-Type", "multipart/form-data")
                .header("Test-Authorization", idAut1)
                .multiPart("executor_id", idExecutor)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDb)
                //  .multiPart("document_name", documentName)
                .multiPart("document_type", documentType)
                // .multiPart("document_id", idDocument)
                .multiPart("sed_document[uid]", uidDoc)
                .multiPart("sed_document[url]", sedDoc)
                .multiPart("sed_document[reg_number]", regNoDoc)
                .multiPart("sed_document[reg_date]", dateDoc)
                .multiPart("sed_document[provider_id]", idProvider)
                //    .multiPart("content", fileContent)
                //   .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        int id = jsonPath.getInt("data.id");
        String dDate = jsonPath.getString("data.deadline_date");
        int dbId = jsonPath.getInt("data.deadline_basis.id");
        int basisId = jsonPath.getInt("data.events[0].basis.id");
        String name = jsonPath.getString("data.events[0].document_name");
        String docType = jsonPath.getString("data.events[0].document_type");
        int docId = jsonPath.getInt("data.events[0].document_id");
        String docUrl = jsonPath.getString("data.events[0].document_url");
        String content = jsonPath.getString("data.events[0].content");
        String userName = jsonPath.getString("data.events[0].updated_by.name");
        int images = jsonPath.get("data.events[0].images.size()");

        Assert.assertNotNull(id);
        Assert.assertTrue(dDate.equals("2024-11-02T00:00:00.000Z"));
        Assert.assertTrue(dbId == idDb);
        Assert.assertTrue(basisId == idDb);
        Assert.assertNull(name);
        Assert.assertTrue(docType.equals(documentType));
        Assert.assertTrue(docId != 0);
        Assert.assertNotNull(docUrl);
        Assert.assertNull(content);
        Assert.assertTrue(userName.equals("Колокольцев В.А."));
        Assert.assertTrue(images == 0);

    }
}
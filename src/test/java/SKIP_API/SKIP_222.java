package SKIP_API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class SKIP_222 {

    int idAut1 = 1;

    int idReport = 155;//id рапорт events
    int idReference = 156;//id справка events
    int idReported = 60;//id ES доложено
    int idES = 56;//id ES для проверки
    int idBasis;
    int idDoc;
    int idExecutorsDoc;
    int idExecutions;
    String API_BASIS = "http://api.skip.rtech.ru/v1/classifiers/deadline_bases";
    String API_DOCUMENTS = "http://api.skip.rtech.ru/v1/documents";
    String API_EXECUTIONS = "http://api.skip.rtech.ru/v1/executions";
    String API_EVENTS = "http://api.skip.rtech.ru/v1/executions/events";


    @BeforeClass
    public void s1() {
        Response response = given()
                .when()
                .header("Test-Authorization", 1)
                .get(API_BASIS)
                .then().log().all()
                .extract().response();


        JsonPath jsonPath = response.jsonPath();
        int idB = jsonPath.getInt("data[0].id");
        idBasis = idB;
    }

    @BeforeClass
    public void s2() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_222/SKIP_222_beforeClass.json"))
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        int idDocument = jsonPath.getInt("data.id");
        idDoc = idDocument;
        int idExec = jsonPath.getInt("data.assignments[0].executors[0].id");
        idExecutorsDoc = idExec;
    }

    @Test
    public void step01() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\txt.txt";
        File file = new File(filePath);
        Response response = given()
                .contentType("multipart/form-data")
                .header("Test-Authorization", idAut1)
                .formParam("executor_id", idExecutorsDoc)
                .formParam("deadline_date", "07.03.2024")
                .formParam("deadline_basis_id", idBasis)
                .formParam("document_name", "test")
                .formParam("document_type", "Documents::Document")
                .formParam("document_id", idDoc)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EXECUTIONS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int idExec = jsonPath.getInt("data.id");
        idExecutions = idExec;
        Assert.assertNotNull(idExec);
    }

    @Test
    public void step02() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\txt.txt";
        File file = new File(filePath);
        Response response = given()
                .contentType("multipart/form-data")
                .header("Test-Authorization", idAut1)
                .formParam("execution_id", idExecutions)
                .formParam("event_id", idReport)
                .formParam("state_id", idReported)
                .formParam("basis_id", idBasis)
                .formParam("document_type", "Documents::Document")
                .formParam("document_id", idDoc)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EVENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        int esId = jsonPath.getInt("data.state.id");
        String nameState = jsonPath.getString("data.state.name");
        String docType = jsonPath.getString("data.document_type");
        int docId = jsonPath.getInt("data.document_id");
        int basis = jsonPath.getInt("data.basis.id");
        int report = jsonPath.getInt("data.event.id");
        String name = jsonPath.getString("data.event.name");

        Assert.assertTrue(esId == idReported);
        Assert.assertTrue(nameState.equals("Доложено"));
        Assert.assertTrue(docType.equals("Documents::Document"));
        Assert.assertTrue(docId == idDoc);
        Assert.assertTrue(basis == idBasis);
        Assert.assertTrue(report == idReport);
        Assert.assertTrue(name.equals("Рапорт"));

    }

    @Test
    public void step03() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .get(API_DOCUMENTS + "/" + idDoc)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        int assignments_count = jsonPath.getInt("data.stats.assignments_count");
        int report_count = jsonPath.getInt("data.stats.report_count");
        int reference_count = jsonPath.getInt("data.stats.reference_count");

        Assert.assertTrue(assignments_count == 3);
        Assert.assertTrue(report_count == 1);
        Assert.assertTrue(reference_count == 0);
    }

    @Test
    public void step04() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\txt.txt";
        File file = new File(filePath);
        Response response = given()
                .contentType("multipart/form-data")
                .header("Test-Authorization", idAut1)
                .formParam("execution_id", idExecutions)
                .formParam("event_id", idReport)
                .formParam("state_id", idReported)
                .formParam("basis_id", idBasis)
                .formParam("document_type", "Documents::Document")
                .formParam("document_id", idDoc)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EVENTS)
                .then().log().all()
                .extract().response();

        Response response2 = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .get(API_DOCUMENTS + "/" + idDoc)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath2 = response2.jsonPath();

        int assignments_count = jsonPath2.getInt("data.stats.assignments_count");
        int report_count = jsonPath2.getInt("data.stats.report_count");
        int reference_count = jsonPath2.getInt("data.stats.reference_count");

        Assert.assertTrue(assignments_count == 3);
        Assert.assertTrue(report_count == 2);
        Assert.assertTrue(reference_count == 0);

    }

    @Test
    public void step05() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\txt.txt";
        File file = new File(filePath);
        Response response = given()
                .contentType("multipart/form-data")
                .header("Test-Authorization", idAut1)
                .formParam("execution_id", idExecutions)
                .formParam("event_id", idReport)
                .formParam("state_id", idES)
                .formParam("basis_id", idBasis)
                .formParam("document_type", "Documents::Document")
                .formParam("document_id", idDoc)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EVENTS)
                .then().log().all()
                .extract().response();

        Response response2 = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .get(API_DOCUMENTS + "/" + idDoc)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath2 = response2.jsonPath();

        int assignments_count = jsonPath2.getInt("data.stats.assignments_count");
        int report_count = jsonPath2.getInt("data.stats.report_count");
        int reference_count = jsonPath2.getInt("data.stats.reference_count");

        Assert.assertTrue(assignments_count == 3);
        Assert.assertTrue(report_count == 2);
        Assert.assertTrue(reference_count == 0);

    }

    @Test
    public void step06() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\txt.txt";
        File file = new File(filePath);
        Response response = given()
                .contentType("multipart/form-data")
                .header("Test-Authorization", idAut1)
                .formParam("execution_id", idExecutions)
                .formParam("event_id", idReference)
                .formParam("state_id", idES)
                .formParam("basis_id", idBasis)
                .formParam("document_type", "Documents::Document")
                .formParam("document_id", idDoc)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EVENTS)
                .then().log().all()
                .extract().response();

        Response response2 = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .get(API_DOCUMENTS + "/" + idDoc)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath2 = response2.jsonPath();

        int assignments_count = jsonPath2.getInt("data.stats.assignments_count");
        int report_count = jsonPath2.getInt("data.stats.report_count");
        int reference_count = jsonPath2.getInt("data.stats.reference_count");

        Assert.assertTrue(assignments_count == 3);
        Assert.assertTrue(report_count == 2);
        Assert.assertTrue(reference_count == 0);

    }

    @Test
    public void step07() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\txt.txt";
        File file = new File(filePath);
        Response response = given()
                .contentType("multipart/form-data")
                .header("Test-Authorization", idAut1)
                .formParam("execution_id", idExecutions)
                .formParam("event_id", idReference)
                .formParam("state_id", idReported)
                .formParam("basis_id", idBasis)
                .formParam("document_type", "Documents::Document")
                .formParam("document_id", idDoc)
                .multiPart("images[][original_file]", file)
                .when()
                .post(API_EVENTS)
                .then().log().all()
                .extract().response();

        Response response2 = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .get(API_DOCUMENTS + "/" + idDoc)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath2 = response2.jsonPath();

        int assignments_count = jsonPath2.getInt("data.stats.assignments_count");
        int report_count = jsonPath2.getInt("data.stats.report_count");
        int reference_count = jsonPath2.getInt("data.stats.reference_count");

        Assert.assertTrue(assignments_count == 3);
        Assert.assertTrue(report_count == 2);
        Assert.assertTrue(reference_count == 1);

    }
}

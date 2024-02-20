package SKIP_API;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class SKIP_199 {

    String API_DOCUMENT = "http://api.skip.rtech.ru/v1/documents";
    String API_SED_DOCUMENT="http://api.skip.rtech.ru/v1/sed/documents";

    int idAut1 = 1;
    String url1 = "http://incoming-documents.sed2.rtech.ru/cards/65c5d0d9d8fe8d1036000003";
    String uid1 = "0289a43bc2359182f95e399d29dc1674510ff22a7f8e5d9c13b01a74dd56fe1430";
    String reg_number1 = "65c5d0d9d8fe8d1036000003";
    String date1 = "09.02.2024";
    String url2 = "http://incoming-documents.sed2.rtech.ru/cards/65801d629443000003000001";
    String uid2 = "0271d3b0343b8307f22b6bf06ede9ea78771dbef2f290e69e6699cf7d34e28bea8";
    String reg_number2 = "111/960";
    String date2 = "02.01.2024";
    String providerId = "525e9f767da3000002000001";
    int idDoc;
    int idDoc2;

    int idDocument;

    int idLinks1;
    int idLinks2;
    int idLinks3;
    int idLinks4;

    @BeforeClass
    public void step001() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .get(API_DOCUMENT)
                .then().log().all()
                .extract().response();


        JsonPath jsonPath = response.jsonPath();
        List<String> data = jsonPath.get("data");
        int id = jsonPath.get("data[0].id");
        int id2 = jsonPath.get("data[1].id");
        idDoc = id;
        idDoc2 = id2;
        Assert.assertNotNull(data);


    }

    @Test
    public void step01() {

        JsonObject requestPayload = new JsonObject();
        requestPayload.addProperty("skip_reg_date", "2024-01-17T06:06:31.485Z");
        requestPayload.addProperty("control_official_id", "54e35f6179fa007422000001");
        requestPayload.addProperty("control_subject_id", 83);
        requestPayload.addProperty("document_name", "Тест 404");
        requestPayload.addProperty("document_execution_state_id", 11);
        requestPayload.addProperty("skip_document_type_id", 32);
        requestPayload.addProperty("internal", false);
        requestPayload.addProperty("short_description", "404");

        JsonArray linksArray = new JsonArray();

        JsonObject links = new JsonObject();


        linksArray.add(links);

        requestPayload.add("links", linksArray);


        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .post(API_DOCUMENT)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String error1 = jsonPath.get("errors.documents.0.second_document[0]");
        String error2 = jsonPath.get("errors.documents.0.second_document_type[0]");
        String error3 = jsonPath.get("errors.documents.0.second_document_id[0]");

        Assert.assertTrue(error1.equals("не может отсутствовать"));
        Assert.assertTrue(error2.equals("имеет непредусмотренное значение"));
        Assert.assertTrue(error3.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step02() {


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

        JsonArray linksArray = new JsonArray();

        JsonObject links = new JsonObject();

        links.addProperty("document_type", "Documents::Document");
        links.addProperty("document_id", idDoc);
        links.addProperty("note", "test");


        JsonObject sedDocument = new JsonObject();

        sedDocument.addProperty("uid", uid2);
        sedDocument.addProperty("url", url2);
        sedDocument.addProperty("reg_number", reg_number2);
        sedDocument.addProperty("reg_date", date2);
        sedDocument.addProperty("provider_id", providerId);


        links.add("sed_document", sedDocument);

        linksArray.add(links);
        requestPayload.add("links", linksArray);


        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .post(API_DOCUMENT)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error1 = jsonPath.get("errors.links.0.document_id[0]");
        String error2 = jsonPath.get("errors.links.0.sed_document[0]");

        Assert.assertTrue(error1.equals("are mutually exclusive"));
        Assert.assertTrue(error2.equals("are mutually exclusive"));
    }

    @Test
    public void step03() {


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

        JsonArray linksArray = new JsonArray();

        JsonObject links = new JsonObject();

        links.addProperty("document_type", "Documents::Document");
        links.addProperty("document_id", idDoc2);

        linksArray.add(links);
        requestPayload.add("links", linksArray);


        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .post(API_DOCUMENT)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.get("data.links[0].id");
        String name = jsonPath.getString("data.links[0].name");
        String url = jsonPath.getString("data.links[0].url");
        String note = jsonPath.getString("data.links[0].note");
        int docId = jsonPath.getInt("data.links[0].document_id");
        String docType = jsonPath.getString("data.links[0].document_type");

        Assert.assertNotNull(id);
        Assert.assertNotNull(name);
        Assert.assertTrue(url.equals("http://skip.rtech.ru/document/" + idDoc2));
        Assert.assertNull(note);
        Assert.assertTrue(docId == idDoc2);
        Assert.assertTrue(docType.equals("Documents::Document"));
    }

    @Test
    public void step04() {


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

        JsonArray linksArray = new JsonArray();

        JsonObject links = new JsonObject();

        links.addProperty("document_type", "Sed::Document");
        links.addProperty("note", "t");


        JsonObject sedDocument = new JsonObject();

        sedDocument.addProperty("uid", uid2);
        sedDocument.addProperty("url", url2);
        sedDocument.addProperty("reg_number", reg_number2);
        sedDocument.addProperty("reg_date", date2);
        sedDocument.addProperty("provider_id", providerId);


        links.add("sed_document", sedDocument);

        linksArray.add(links);
        requestPayload.add("links", linksArray);


        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .post(API_DOCUMENT)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.get("data.links[0].id");
        String name = jsonPath.getString("data.links[0].name");
        String url = jsonPath.getString("data.links[0].url");
        String note = jsonPath.getString("data.links[0].note");
        int docId = jsonPath.getInt("data.links[0].document_id");
        String docType = jsonPath.getString("data.links[0].document_type");

        Assert.assertNotNull(id);
        Assert.assertNotNull(name);
        Assert.assertTrue(url.equals(url2));
        Assert.assertTrue(note.equals("t"));
        Assert.assertNotNull(docId);
        Assert.assertTrue(docType.equals("Sed::Document"));
    }

    @Test
    public void step05() {


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

        JsonArray linksArray = new JsonArray();

        JsonObject links = new JsonObject();

        links.addProperty("document_type", "Documents::Document");


        linksArray.add(links);
        requestPayload.add("links", linksArray);


        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .post(API_DOCUMENT)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error1 = jsonPath.get("errors.links.0.document_id[0]");
        String error2 = jsonPath.get("errors.links.0.sed_document[0]");

        Assert.assertTrue(error1.equals("are missing, exactly one parameter must be provided"));
        Assert.assertTrue(error2.equals("are missing, exactly one parameter must be provided"));
    }

    @Test
    public void step06() {


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

        JsonArray linksArray = new JsonArray();

        JsonObject links = new JsonObject();

        links.addProperty("document_type", "Sed::Document");
        links.addProperty("document_id", idDoc2);
        links.addProperty("note", "test");


        JsonObject sedDocument = new JsonObject();

        sedDocument.addProperty("uid", uid2);
        sedDocument.addProperty("url", url2);
        sedDocument.addProperty("reg_number", reg_number2);
        sedDocument.addProperty("reg_date", date2);
        sedDocument.addProperty("provider_id", providerId);


        links.add("sed_document", sedDocument);

        linksArray.add(links);
        requestPayload.add("links", linksArray);


        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .post(API_DOCUMENT)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error1 = jsonPath.get("errors.links.0.document_id[0]");
        String error2 = jsonPath.get("errors.links.0.sed_document[0]");

        Assert.assertTrue(error1.equals("are mutually exclusive"));
        Assert.assertTrue(error2.equals("are mutually exclusive"));
    }

    @Test
    public void step07() {


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

        JsonArray linksArray = new JsonArray();

        JsonObject links = new JsonObject();

        links.addProperty("document_type", "Sed::Document");
        links.addProperty("note", "t");


        JsonObject sedDocument = new JsonObject();

        sedDocument.addProperty("uid", uid2);
        sedDocument.addProperty("url", url2);
        sedDocument.addProperty("reg_number", reg_number2);
        sedDocument.addProperty("reg_date", date2);
        sedDocument.addProperty("provider_id", providerId);


        JsonObject document = new JsonObject();

        document.addProperty("document_type", "Documents::Document");
        document.addProperty("document_id", idDoc2);


        links.add("sed_document", sedDocument);

        linksArray.add(links);
        linksArray.add(document);
        requestPayload.add("links", linksArray);


        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .post(API_DOCUMENT)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.get("data.links[1].id");
        String name = jsonPath.getString("data.links[1].name");
        String url = jsonPath.getString("data.links[1].url");
        String note = jsonPath.getString("data.links[1].note");
        int docId = jsonPath.getInt("data.links[1].document_id");
        String docType = jsonPath.getString("data.links[1].document_type");

        Assert.assertNotNull(id);
        Assert.assertTrue(name.equals("От " + date2 + " № " + reg_number2));
        Assert.assertTrue(url.equals(url2));
        Assert.assertTrue(note.equals("t"));
        Assert.assertNotNull(docId);
        Assert.assertTrue(docType.equals("Sed::Document"));


        int id2 = jsonPath.get("data.links[0].id");
        String name2 = jsonPath.getString("data.links[0].name");
        String url2 = jsonPath.getString("data.links[0].url");
        String note2 = jsonPath.getString("data.links[0].note");
        int docId2 = jsonPath.getInt("data.links[0].document_id");
        String docType2 = jsonPath.getString("data.links[0].document_type");

        Assert.assertNotNull(id2);
        Assert.assertNotNull(name2);
        Assert.assertTrue(url2.equals("http://skip.rtech.ru/document/" + idDoc2));
        Assert.assertNull(note2);
        Assert.assertTrue(docId2 == idDoc2);
        Assert.assertTrue(docType2.equals("Documents::Document"));

        int idDoc = jsonPath.getInt("data.id");
        idDocument = idDoc;


    }

    @Test
    public void step08() {


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

        JsonArray linksArray = new JsonArray();

        JsonObject links = new JsonObject();

        links.addProperty("document_type", "Sed::Document");
        links.addProperty("note", "t");

        JsonObject sedDocument = new JsonObject();

        sedDocument.addProperty("uid", uid2);
        sedDocument.addProperty("url", url2);
        sedDocument.addProperty("reg_number", reg_number2);
        sedDocument.addProperty("reg_date", date2);
        sedDocument.addProperty("provider_id", providerId);

        JsonObject links2 = new JsonObject();

        links2.addProperty("document_type", "Sed::Document");
        links2.addProperty("note", "t");

        JsonObject sedDocument2 = new JsonObject();

        sedDocument2.addProperty("uid", uid2);
        sedDocument2.addProperty("url", url2);
        sedDocument2.addProperty("reg_number", reg_number2);
        sedDocument2.addProperty("reg_date", date2);
        sedDocument2.addProperty("provider_id", providerId);


        links2.add("sed_document", sedDocument2);
        links.add("sed_document", sedDocument);

        linksArray.add(links);
        linksArray.add(links2);
        requestPayload.add("links", linksArray);


        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .post(API_DOCUMENT)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.get("errors.documents.1.first_document_id[0]");

        Assert.assertTrue(error.equals("уже существует"));
    }

    @Test
    public void step09() {


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

        JsonArray linksArray = new JsonArray();

        JsonObject link = new JsonObject();

        link.addProperty("document_type", "Documents::Document");
        link.addProperty("document_id", idDoc2);


        JsonObject link2 = new JsonObject();

        link2.addProperty("document_type", "Documents::Document");
        link2.addProperty("document_id", idDoc2);


        linksArray.add(link);
        linksArray.add(link2);
        requestPayload.add("links", linksArray);


        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .post(API_DOCUMENT)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.get("errors.documents.1.first_document_id[0]");

        Assert.assertTrue(error.equals("уже существует"));

    }

    @Test
    public void step10() {


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

        JsonArray linksArray = new JsonArray();

        JsonObject links = new JsonObject();

        links.addProperty("document_type", "Sed::Document");

        JsonObject sedDocument = new JsonObject();


        links.add("sed_document", sedDocument);

        linksArray.add(links);
        requestPayload.add("links", linksArray);


        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .post(API_DOCUMENT)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error1 = jsonPath.get("errors.documents.0.second_document[0]");
        String error2 = jsonPath.get("errors.documents.0.second_document_type[0]");
        String error3 = jsonPath.get("errors.documents.0.second_document_id[0]");

        Assert.assertTrue(error1.equals("не может отсутствовать"));
        Assert.assertTrue(error2.equals("имеет непредусмотренное значение"));
        Assert.assertTrue(error3.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step11() {


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

        JsonArray linksArray = new JsonArray();

        JsonObject links = new JsonObject();

        links.addProperty("document_type", "Sed::Document");


        JsonObject sedDocument = new JsonObject();

        sedDocument.addProperty("url", url2);
        sedDocument.addProperty("reg_number", reg_number2);
        sedDocument.addProperty("reg_date", date2);
        sedDocument.addProperty("provider_id", providerId);


        links.add("sed_document", sedDocument);

        linksArray.add(links);
        requestPayload.add("links", linksArray);


        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .post(API_DOCUMENT)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.links.0.sed_document.uid[0]");

        Assert.assertTrue(error.equals("не может быть пустым"));
    }

    @Test
    public void step12() {


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

        JsonArray linksArray = new JsonArray();

        JsonObject links = new JsonObject();

        links.addProperty("document_type", "Sed::Document");


        JsonObject sedDocument = new JsonObject();

        sedDocument.addProperty("uid", uid2);
        sedDocument.addProperty("reg_number", reg_number2);
        sedDocument.addProperty("reg_date", date2);
        sedDocument.addProperty("provider_id", providerId);


        links.add("sed_document", sedDocument);

        linksArray.add(links);
        requestPayload.add("links", linksArray);


        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .post(API_DOCUMENT)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.links.0.sed_document.url[0]");

        Assert.assertTrue(error.equals("не может быть пустым"));
    }

    @Test
    public void step13() {


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

        JsonArray linksArray = new JsonArray();

        JsonObject links = new JsonObject();

        links.addProperty("document_type", "Sed::Document");


        JsonObject sedDocument = new JsonObject();

        sedDocument.addProperty("uid", uid2);
        sedDocument.addProperty("url", url2);
        sedDocument.addProperty("reg_date", date2);
        sedDocument.addProperty("provider_id", providerId);


        links.add("sed_document", sedDocument);

        linksArray.add(links);
        requestPayload.add("links", linksArray);


        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .post(API_DOCUMENT)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.links.0.sed_document.reg_number[0]");

        Assert.assertTrue(error.equals("не может быть пустым"));
    }

    @Test
    public void step14() {


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

        JsonArray linksArray = new JsonArray();

        JsonObject links = new JsonObject();

        links.addProperty("document_type", "Sed::Document");


        JsonObject sedDocument = new JsonObject();

        sedDocument.addProperty("uid", uid2);
        sedDocument.addProperty("url", url2);
        sedDocument.addProperty("reg_number", reg_number2);
        sedDocument.addProperty("provider_id", providerId);


        links.add("sed_document", sedDocument);

        linksArray.add(links);
        requestPayload.add("links", linksArray);


        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .post(API_DOCUMENT)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.links.0.sed_document.reg_date[0]");

        Assert.assertTrue(error.equals("не может быть пустым"));
    }

    @Test
    public void step15() {


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

        JsonArray linksArray = new JsonArray();

        JsonObject links = new JsonObject();

        links.addProperty("document_type", "Sed::Document");


        JsonObject sedDocument = new JsonObject();

        sedDocument.addProperty("uid", uid2);
        sedDocument.addProperty("url", url2);
        sedDocument.addProperty("reg_number", reg_number2);
        sedDocument.addProperty("reg_date", date2);


        links.add("sed_document", sedDocument);

        linksArray.add(links);
        requestPayload.add("links", linksArray);


        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .post(API_DOCUMENT)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.links.0.sed_document.provider_id[0]");

        Assert.assertTrue(error.equals("не может быть пустым"));
    }

    @Test
    public void step16() {
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .when()
                .get(API_DOCUMENT + "/" + idDocument)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int id1 = jsonPath.getInt("data.links[0].id");
        int id2 = jsonPath.getInt("data.links[1].id");

        Assert.assertTrue(id1 > id2);
        idLinks1 = id1;
        idLinks2 = id2;
    }

    @Test
    public void step17() {

        JsonObject requestPayload = new JsonObject();
        JsonArray linksArray = new JsonArray();

        JsonObject links = new JsonObject();

        linksArray.add(links);
        requestPayload.add("links", linksArray);


        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .put(API_DOCUMENT + "/" + idDocument)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error1 = jsonPath.get("errors.documents.0.second_document[0]");
        String error2 = jsonPath.get("errors.documents.0.second_document_type[0]");
        String error3 = jsonPath.get("errors.documents.0.second_document_id[0]");

        Assert.assertTrue(error1.equals("не может отсутствовать"));
        Assert.assertTrue(error2.equals("имеет непредусмотренное значение"));
        Assert.assertTrue(error3.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step18() {

        JsonObject requestPayload = new JsonObject();
        JsonArray linksArray = new JsonArray();

        JsonObject links = new JsonObject();

        links.addProperty("id", idLinks1);
        links.addProperty("document_type", "Documents::Document");
        links.addProperty("document_id", idDoc2);
        links.addProperty("note", "s");

        JsonObject links2 = new JsonObject();

        links2.addProperty("id", idLinks2);
        links2.addProperty("document_type", "Sed::Document");

        JsonObject sedDocument = new JsonObject();

        sedDocument.addProperty("uid", uid1);
        sedDocument.addProperty("url", url1);
        sedDocument.addProperty("reg_number", reg_number1);
        sedDocument.addProperty("reg_date", date1);
        sedDocument.addProperty("provider_id", providerId);


        linksArray.add(links);

        links2.add("sed_document", sedDocument);

        linksArray.add(links2);

        requestPayload.add("links", linksArray);


        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .put(API_DOCUMENT + "/" + idDocument)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.get("data.links[1].id");
        String name = jsonPath.getString("data.links[1].name");
        String url = jsonPath.getString("data.links[1].url");
        String note = jsonPath.getString("data.links[1].note");
        int docId = jsonPath.getInt("data.links[1].document_id");
        String docType = jsonPath.getString("data.links[1].document_type");

        Assert.assertNotNull(id);
        Assert.assertTrue(name.equals("От " + date1 + " № " + reg_number1));
        Assert.assertTrue(url.equals(url1));
        Assert.assertTrue(note.equals("t"));
        Assert.assertNotNull(docId);
        Assert.assertTrue(docType.equals("Sed::Document"));


        int id2 = jsonPath.get("data.links[0].id");
        String name2 = jsonPath.getString("data.links[0].name");
        String url2 = jsonPath.getString("data.links[0].url");
        String note2 = jsonPath.getString("data.links[0].note");
        int docId2 = jsonPath.getInt("data.links[0].document_id");
        String docType2 = jsonPath.getString("data.links[0].document_type");

        Assert.assertNotNull(id2);
        Assert.assertNotNull(name2);
        Assert.assertTrue(url2.equals("http://skip.rtech.ru/document/" + idDoc2));
        Assert.assertTrue(note2.equals("s"));
        Assert.assertTrue(docId2 == idDoc2);
        Assert.assertTrue(docType2.equals("Documents::Document"));

    }

    @Test
    public void step19() {

        JsonObject requestPayload = new JsonObject();
        JsonArray linksArray = new JsonArray();

        JsonObject links = new JsonObject();

        links.addProperty("document_type", "Documents::Document");
        links.addProperty("document_id", idDoc2);
        links.addProperty("note", "s");

        JsonObject links2 = new JsonObject();

        links2.addProperty("id", idLinks2);
        links2.addProperty("document_type", "Sed::Document");

        JsonObject sedDocument = new JsonObject();

        sedDocument.addProperty("uid", uid1);
        sedDocument.addProperty("url", url1);
        sedDocument.addProperty("reg_number", reg_number1);
        sedDocument.addProperty("reg_date", date1);
        sedDocument.addProperty("provider_id", providerId);


        linksArray.add(links);

        links2.add("sed_document", sedDocument);

        linksArray.add(links2);

        requestPayload.add("links", linksArray);


        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .put(API_DOCUMENT + "/" + idDocument)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.documents.0.first_document_id[0]");
        Assert.assertTrue(error.equals("уже существует"));

    }

    @Test
    public void step20() {

        JsonObject requestPayload = new JsonObject();
        JsonArray linksArray = new JsonArray();

        JsonObject links = new JsonObject();

        links.addProperty("id", idLinks1);
        links.addProperty("document_type", "Documents::Document");
        links.addProperty("document_id", idDoc2);
        links.addProperty("note", "s");

        JsonObject links2 = new JsonObject();

        links2.addProperty("document_type", "Sed::Document");

        JsonObject sedDocument = new JsonObject();

        sedDocument.addProperty("uid", uid1);
        sedDocument.addProperty("url", url1);
        sedDocument.addProperty("reg_number", reg_number1);
        sedDocument.addProperty("reg_date", date1);
        sedDocument.addProperty("provider_id", providerId);


        linksArray.add(links);

        links2.add("sed_document", sedDocument);

        linksArray.add(links2);

        requestPayload.add("links", linksArray);


        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .put(API_DOCUMENT + "/" + idDocument)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.documents.1.first_document_id[0]");
        Assert.assertTrue(error.equals("уже существует"));

    }

    @Test
    public void step21() {

        JsonObject requestPayload = new JsonObject();
        JsonArray linksArray = new JsonArray();

        JsonObject links = new JsonObject();

        links.addProperty("id", idLinks1);
        links.addProperty("document_type", "Sed::Document");
        links.addProperty("document_id", idDoc2);
        links.addProperty("note", "s");

        JsonObject links2 = new JsonObject();

        links2.addProperty("id", idLinks2);
        links2.addProperty("document_type", "Sed::Document");

        JsonObject sedDocument = new JsonObject();

        sedDocument.addProperty("uid", uid1);
        sedDocument.addProperty("url", url1);
        sedDocument.addProperty("reg_number", reg_number1);
        sedDocument.addProperty("reg_date", date1);
        sedDocument.addProperty("provider_id", providerId);


        linksArray.add(links);

        links2.add("sed_document", sedDocument);

        linksArray.add(links2);

        requestPayload.add("links", linksArray);


        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .put(API_DOCUMENT + "/" + idDocument)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("errors.documents.0.second_document[0]");
        Assert.assertTrue(error.equals("не может отсутствовать"));
        String error2 = jsonPath.getString("errors.documents.0.second_document_id[0]");
        Assert.assertTrue(error2.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step22() {

        JsonObject requestPayload = new JsonObject();
        JsonArray linksArray = new JsonArray();

        JsonObject links = new JsonObject();

        links.addProperty("id", idLinks1);
        links.addProperty("document_type", "Documents::Document");
        links.addProperty("document_id", idDoc2);
        links.addProperty("note", "s");

        JsonObject links2 = new JsonObject();

        links2.addProperty("id", idLinks2);
        links2.addProperty("document_type", "Sed::Document");

        JsonObject sedDocument = new JsonObject();

        sedDocument.addProperty("uid", uid2);


        linksArray.add(links);

        links2.add("sed_document", sedDocument);

        linksArray.add(links2);

        requestPayload.add("links", linksArray);


        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .put(API_DOCUMENT + "/" + idDocument)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.get("data.links[1].id");
        String name = jsonPath.getString("data.links[1].name");
        String url = jsonPath.getString("data.links[1].url");
        String note = jsonPath.getString("data.links[1].note");
        int docId = jsonPath.getInt("data.links[1].document_id");
        String docType = jsonPath.getString("data.links[1].document_type");

        Assert.assertNotNull(id);
        Assert.assertTrue(name.equals("От " + date2 + " № " + reg_number2));
        Assert.assertTrue(url.equals(url2));
        Assert.assertTrue(note.equals("t"));
        Assert.assertNotNull(docId);
        Assert.assertTrue(docType.equals("Sed::Document"));


        int id2 = jsonPath.get("data.links[0].id");
        String name2 = jsonPath.getString("data.links[0].name");
        String url2 = jsonPath.getString("data.links[0].url");
        String note2 = jsonPath.getString("data.links[0].note");
        int docId2 = jsonPath.getInt("data.links[0].document_id");
        String docType2 = jsonPath.getString("data.links[0].document_type");

        Assert.assertNotNull(id2);
        Assert.assertNotNull(name2);
        Assert.assertTrue(url2.equals("http://skip.rtech.ru/document/" + idDoc2));
        Assert.assertTrue(note2.equals("s"));
        Assert.assertTrue(docId2 == idDoc2);
        Assert.assertTrue(docType2.equals("Documents::Document"));

    }

    @Test
    public void step23() {

        JsonObject requestPayload = new JsonObject();
        JsonArray linksArray = new JsonArray();

        JsonObject links = new JsonObject();

        links.addProperty("id", idLinks1);
        links.addProperty("document_type", "Documents::Document");
        links.addProperty("document_id", idDoc2);
        links.addProperty("note", "s");

        JsonObject links2 = new JsonObject();

        links2.addProperty("id", idLinks2);
        links2.addProperty("document_type", "Sed::Document");

        JsonObject sedDocument = new JsonObject();


        linksArray.add(links);

        links2.add("sed_document", sedDocument);

        linksArray.add(links2);

        requestPayload.add("links", linksArray);


        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .put(API_DOCUMENT + "/" + idDocument)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.get("data.links[1].id");
        String name = jsonPath.getString("data.links[1].name");
        String url = jsonPath.getString("data.links[1].url");
        String note = jsonPath.getString("data.links[1].note");
        int docId = jsonPath.getInt("data.links[1].document_id");
        String docType = jsonPath.getString("data.links[1].document_type");

        Assert.assertNotNull(id);
        Assert.assertTrue(name.equals("От " + date2 + " № " + reg_number2));
        Assert.assertTrue(url.equals(url2));
        Assert.assertTrue(note.equals("t"));
        Assert.assertNotNull(docId);
        Assert.assertTrue(docType.equals("Sed::Document"));


        int id2 = jsonPath.get("data.links[0].id");
        String name2 = jsonPath.getString("data.links[0].name");
        String url2 = jsonPath.getString("data.links[0].url");
        String note2 = jsonPath.getString("data.links[0].note");
        int docId2 = jsonPath.getInt("data.links[0].document_id");
        String docType2 = jsonPath.getString("data.links[0].document_type");

        Assert.assertNotNull(id2);
        Assert.assertNotNull(name2);
        Assert.assertTrue(url2.equals("http://skip.rtech.ru/document/" + idDoc2));
        Assert.assertTrue(note2.equals("s"));
        Assert.assertTrue(docId2 == idDoc2);
        Assert.assertTrue(docType2.equals("Documents::Document"));

    }

    @Test
    public void step24() {

        JsonObject requestPayload = new JsonObject();
        JsonArray linksArray = new JsonArray();

        JsonObject links = new JsonObject();

        links.addProperty("document_type", "Documents::Document");
        links.addProperty("document_id", idDoc);
        links.addProperty("note", "s");

        JsonObject links2 = new JsonObject();

        links2.addProperty("id", idLinks2);
        links2.addProperty("document_type", "Sed::Document");

        JsonObject sedDocument = new JsonObject();


        sedDocument.addProperty("uid", uid1);
        sedDocument.addProperty("url", url1);
        sedDocument.addProperty("reg_number", reg_number1);
        sedDocument.addProperty("reg_date", date1);
        sedDocument.addProperty("provider_id", providerId);


        linksArray.add(links);

        links2.add("sed_document", sedDocument);

        linksArray.add(links2);

        requestPayload.add("links", linksArray);


        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .put(API_DOCUMENT + "/" + idDocument)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.get("data.links[2].id");
        String name = jsonPath.getString("data.links[2].name");
        String url = jsonPath.getString("data.links[2].url");
        String note = jsonPath.getString("data.links[2].note");
        int docId = jsonPath.getInt("data.links[2].document_id");
        String docType = jsonPath.getString("data.links[2].document_type");

        Assert.assertNotNull(id);
        Assert.assertTrue(name.equals("От " + date1 + " № " + reg_number1));
        Assert.assertTrue(url.equals(url1));
        Assert.assertTrue(note.equals("t"));
        Assert.assertNotNull(docId);
        Assert.assertTrue(docType.equals("Sed::Document"));


        int id2 = jsonPath.get("data.links[1].id");
        String name2 = jsonPath.getString("data.links[1].name");
        String url2 = jsonPath.getString("data.links[1].url");
        String note2 = jsonPath.getString("data.links[1].note");
        int docId2 = jsonPath.getInt("data.links[1].document_id");
        String docType2 = jsonPath.getString("data.links[1].document_type");

        Assert.assertNotNull(id2);
        Assert.assertNotNull(name2);
        Assert.assertTrue(url2.equals("http://skip.rtech.ru/document/" + idDoc2));
        Assert.assertTrue(note2.equals("s"));
        Assert.assertTrue(docId2 == idDoc2);
        Assert.assertTrue(docType2.equals("Documents::Document"));

        int id3 = jsonPath.get("data.links[0].id");
        String name3 = jsonPath.getString("data.links[0].name");
        String url3 = jsonPath.getString("data.links[0].url");
        String note3 = jsonPath.getString("data.links[0].note");
        int docId3 = jsonPath.getInt("data.links[0].document_id");
        String docType3 = jsonPath.getString("data.links[0].document_type");

        Assert.assertNotNull(id3);
        Assert.assertNotNull(name3);
        Assert.assertTrue(url3.equals("http://skip.rtech.ru/document/" + idDoc));
        Assert.assertTrue(note3.equals("s"));
        Assert.assertTrue(docId3 == idDoc);
        Assert.assertTrue(docType3.equals("Documents::Document"));

        idLinks3=id3;

    }


    @Test
    public void step25() {

        JsonObject requestPayload = new JsonObject();
        JsonArray linksArray = new JsonArray();

        JsonObject links = new JsonObject();

        links.addProperty("id", idLinks3);
        links.addProperty("document_type", "Documents::Document");
        links.addProperty("document_id", idDoc);
        links.addProperty("note", "ss");

        JsonObject links2 = new JsonObject();

        links2.addProperty("document_type", "Sed::Document");

        JsonObject sedDocument = new JsonObject();


        sedDocument.addProperty("uid", uid2);


        linksArray.add(links);

        links2.add("sed_document", sedDocument);

        linksArray.add(links2);

        requestPayload.add("links", linksArray);


        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .put(API_DOCUMENT + "/" + idDocument)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.get("data.links[3].id");
        String name = jsonPath.getString("data.links[3].name");
        String url = jsonPath.getString("data.links[3].url");
        String note = jsonPath.getString("data.links[3].note");
        int docId = jsonPath.getInt("data.links[3].document_id");
        String docType = jsonPath.getString("data.links[3].document_type");

        Assert.assertNotNull(id);
        Assert.assertTrue(name.equals("От " + date1 + " № " + reg_number1));
        Assert.assertTrue(url.equals(url1));
        Assert.assertTrue(note.equals("t"));
        Assert.assertNotNull(docId);
        Assert.assertTrue(docType.equals("Sed::Document"));


        int id2 = jsonPath.get("data.links[1].id");
        String name2 = jsonPath.getString("data.links[1].name");
        String url2s = jsonPath.getString("data.links[1].url");
        String note2 = jsonPath.getString("data.links[1].note");
        int docId2 = jsonPath.getInt("data.links[1].document_id");
        String docType2 = jsonPath.getString("data.links[1].document_type");

        Assert.assertNotNull(id2);
        Assert.assertNotNull(name2);
        Assert.assertTrue(url2s.equals("http://skip.rtech.ru/document/" + idDoc));
        Assert.assertTrue(note2.equals("ss"));
        Assert.assertTrue(docId2 == idDoc);
        Assert.assertTrue(docType2.equals("Documents::Document"));

        int id3 = jsonPath.get("data.links[2].id");
        String name3 = jsonPath.getString("data.links[2].name");
        String url3 = jsonPath.getString("data.links[2].url");
        String note3 = jsonPath.getString("data.links[2].note");
        int docId3 = jsonPath.getInt("data.links[2].document_id");
        String docType3 = jsonPath.getString("data.links[2].document_type");

        Assert.assertNotNull(id3);
        Assert.assertNotNull(name3);
        Assert.assertTrue(url3.equals("http://skip.rtech.ru/document/" + idDoc2));
        Assert.assertTrue(note3.equals("s"));
        Assert.assertTrue(docId3 == idDoc2);
        Assert.assertTrue(docType3.equals("Documents::Document"));


        int id4 = jsonPath.get("data.links[3].id");
        String name4 = jsonPath.getString("data.links[3].name");
        String url4 = jsonPath.getString("data.links[3].url");
        String note4 = jsonPath.getString("data.links[3].note");
        int docId4 = jsonPath.getInt("data.links[3].document_id");
        String docType4 = jsonPath.getString("data.links[3].document_type");

        Assert.assertNotNull(id4);
        Assert.assertTrue(name4.equals("От " + date1 + " № " + reg_number1));
        Assert.assertTrue(url4.equals(url1));
        Assert.assertTrue(note4.equals("t"));
        Assert.assertNotNull(docId4);
        Assert.assertTrue(docType4.equals("Sed::Document"));

        idLinks4=id4;
    }
    @Test
    public void step26() {

        JsonObject requestPayload = new JsonObject();
        JsonArray linksArray = new JsonArray();

        JsonObject links = new JsonObject();

        links.addProperty("id", idLinks3);
        links.addProperty("deleted", true);

        JsonObject links2 = new JsonObject();

        links2.addProperty("id", idLinks4);
        links2.addProperty("deleted", true);



        linksArray.add(links);


        linksArray.add(links2);

        requestPayload.add("links", linksArray);


        // Отправка POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .when()
                .put(API_DOCUMENT + "/" + idDocument)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.get("data.links[0].id");
        String name = jsonPath.getString("data.links[0].name");
        String url = jsonPath.getString("data.links[0].url");
        String note = jsonPath.getString("data.links[0].note");
        int docId = jsonPath.getInt("data.links[0].document_id");
        String docType = jsonPath.getString("data.links[0].document_type");

        Assert.assertNotNull(id);
        Assert.assertTrue(name.equals("От " + date2 + " № " + reg_number2));
        Assert.assertTrue(url.equals(url2));
        Assert.assertNull(note);
        Assert.assertNotNull(docId);
        Assert.assertTrue(docType.equals("Sed::Document"));


        int id2 = jsonPath.get("data.links[1].id");
        String name2 = jsonPath.getString("data.links[1].name");
        String url2 = jsonPath.getString("data.links[1].url");
        String note2 = jsonPath.getString("data.links[1].note");
        int docId2 = jsonPath.getInt("data.links[1].document_id");
        String docType2 = jsonPath.getString("data.links[1].document_type");

        Assert.assertNotNull(id2);
        Assert.assertNotNull(name2);
        Assert.assertTrue(url2.equals("http://skip.rtech.ru/document/" + idDoc2));
        Assert.assertTrue(note2.equals("s"));
        Assert.assertTrue(docId2 == idDoc2);
        Assert.assertTrue(docType2.equals("Documents::Document"));

    }


    @Test
    public void step27(){
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .when()
                .get(API_SED_DOCUMENT + "/" + uid2)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String sed_security_text=jsonPath.getString("data.sed_security_text");
        Assert.assertNotNull(sed_security_text);
    }


}
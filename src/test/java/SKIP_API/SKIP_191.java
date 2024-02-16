package SKIP_API;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SKIP_191 {

    String API_DOCUMENTS = "http://api.skip.rtech.ru/v1/documents";

    String sedDoc1 = "http://incoming-documents.sed2.rtech.ru/cards/65c5d0d9d8fe8d1036000003";
    String uidDoc1 = "0289a43bc2359182f95e399d29dc1674510ff22a7f8e5d9c13b01a74dd56fe1430";
    String regNoDoc1 = "65c5d0d9d8fe8d1036000003";
    String dateDoc1 = "09.02.2024";
    String idProvider = "525e9f767da3000002000001";
    String sedDoc2 = "http://incoming-documents.sed2.rtech.ru/cards/65801d629443000003000001";
    String uidDoc2 = "0271d3b0343b8307f22b6bf06ede9ea78771dbef2f290e69e6699cf7d34e28bea8";
    String regNoDoc2 = "65801d629443000003000001";
    String dateDoc2 = "02.01.2024";
    String sedDoc3 = "http://outgoing-documents.sed2.rtech.ru/cards/65cf2402c4ed17aec300001f";
    String uidDoc3 = "0535ede017eefc24815d90de292eb240eb80025c56d1342bd918be1cd4871e5674";
    String regNoDoc3 = "65cf2402c4ed17aec300001f";
    String dateDoc3 = "16.02.2024";
    int idDocument;
    int idSed1;
    int idSed2;
    int idSed3;

    int idAut1 = 1;

    @Test
    public void step01() {

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

        JsonArray sedLinksArray = new JsonArray();

        JsonObject sedLinks = new JsonObject();
        sedLinks.addProperty("uid", uidDoc1);
        sedLinks.addProperty("url", sedDoc1);
        sedLinks.addProperty("reg_number", regNoDoc1);
        sedLinks.addProperty("reg_date", dateDoc1);
        sedLinks.addProperty("provider_id", idProvider);


        sedLinksArray.add(sedLinks);

        requestPayload.add("sed_links", sedLinksArray);


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
        int id = jsonPath.get("data.sed_links[0].id");
        String name = jsonPath.get("data.sed_links[0].name");
        String url = jsonPath.get("data.sed_links[0].url");

        Assert.assertNotNull(id);
        Assert.assertTrue(name.equals("От 09.02.2024 № 65c5d0d9d8fe8d1036000003"));
        Assert.assertTrue(url.equals("http://incoming-documents.sed2.rtech.ru/cards/65c5d0d9d8fe8d1036000003"));
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

        JsonArray sedLinksArray = new JsonArray();

        JsonObject sedLinks = new JsonObject();
        sedLinks.addProperty("url", sedDoc1);
        sedLinks.addProperty("reg_number", regNoDoc1);
        sedLinks.addProperty("reg_date", dateDoc1);
        sedLinks.addProperty("provider_id", idProvider);


        sedLinksArray.add(sedLinks);

        requestPayload.add("sed_links", sedLinksArray);


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

        String error = jsonPath.get("errors.sed_links.0.uid[0]");
        Assert.assertTrue(error.equals("не может быть пустым"));
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

        JsonArray sedLinksArray = new JsonArray();

        JsonObject sedLinks = new JsonObject();
        sedLinks.addProperty("uid", uidDoc1);
        sedLinks.addProperty("reg_number", regNoDoc1);
        sedLinks.addProperty("reg_date", dateDoc1);
        sedLinks.addProperty("provider_id", idProvider);


        sedLinksArray.add(sedLinks);

        requestPayload.add("sed_links", sedLinksArray);


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

        String error = jsonPath.get("errors.sed_links.0.url[0]");
        Assert.assertTrue(error.equals("не может быть пустым"));
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

        JsonArray sedLinksArray = new JsonArray();

        JsonObject sedLinks = new JsonObject();
        sedLinks.addProperty("uid", uidDoc1);
        sedLinks.addProperty("url", sedDoc1);
        sedLinks.addProperty("reg_date", dateDoc1);
        sedLinks.addProperty("provider_id", idProvider);


        sedLinksArray.add(sedLinks);

        requestPayload.add("sed_links", sedLinksArray);


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

        String error = jsonPath.get("errors.sed_links.0.reg_number[0]");
        Assert.assertTrue(error.equals("не может быть пустым"));
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

        JsonArray sedLinksArray = new JsonArray();

        JsonObject sedLinks = new JsonObject();
        sedLinks.addProperty("uid", uidDoc1);
        sedLinks.addProperty("url", sedDoc1);
        sedLinks.addProperty("reg_number", regNoDoc1);
        sedLinks.addProperty("provider_id", idProvider);


        sedLinksArray.add(sedLinks);

        requestPayload.add("sed_links", sedLinksArray);


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

        String error = jsonPath.get("errors.sed_links.0.reg_date[0]");
        Assert.assertTrue(error.equals("не может быть пустым"));
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

        JsonArray sedLinksArray = new JsonArray();

        JsonObject sedLinks = new JsonObject();
        sedLinks.addProperty("uid", uidDoc1);
        sedLinks.addProperty("url", sedDoc1);
        sedLinks.addProperty("reg_number", regNoDoc1);
        sedLinks.addProperty("reg_date", dateDoc1);


        sedLinksArray.add(sedLinks);

        requestPayload.add("sed_links", sedLinksArray);


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

        String error = jsonPath.get("errors.sed_links.0.provider_id[0]");
        Assert.assertTrue(error.equals("не может быть пустым"));
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

        JsonArray sedLinksArray = new JsonArray();

        JsonObject sedLinks = new JsonObject();


        sedLinksArray.add(sedLinks);

        requestPayload.add("sed_links", sedLinksArray);


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

        String error = jsonPath.get("errors.documents.0.second_document[0]");
        Assert.assertTrue(error.equals("не может отсутствовать"));
        String error2 = jsonPath.get("errors.documents.0.second_document_type[0]");
        Assert.assertTrue(error2.equals("имеет непредусмотренное значение"));
        String error3 = jsonPath.get("errors.documents.0.second_document_id[0]");
        Assert.assertTrue(error3.equals("имеет непредусмотренное значение"));
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

        JsonArray sedLinksArray = new JsonArray();

        JsonObject sedLinks = new JsonObject();
        sedLinks.addProperty("uid", uidDoc3);
        sedLinks.addProperty("url", sedDoc3);
        sedLinks.addProperty("reg_number", regNoDoc3);
        sedLinks.addProperty("reg_date", dateDoc3);
        sedLinks.addProperty("provider_id", idProvider);


        sedLinksArray.add(sedLinks);

        requestPayload.add("sed_links", sedLinksArray);


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
        int id = jsonPath.get("data.sed_links[0].id");
        String name = jsonPath.get("data.sed_links[0].name");
        String url = jsonPath.get("data.sed_links[0].url");

        Assert.assertNotNull(id);
        Assert.assertTrue(name.equals("От 16.02.2024 № 65cf2402c4ed17aec300001f"));
        Assert.assertTrue(url.equals("http://outgoing-documents.sed2.rtech.ru/cards/65cf2402c4ed17aec300001f"));
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

        JsonArray sedLinksArray = new JsonArray();

        JsonObject sedLinks = new JsonObject();
        sedLinks.addProperty("uid", "1324456475452341334563756452313425665432314253654735241");
        sedLinks.addProperty("url", "14235364786970865435241ё324253678697086543214567");
        sedLinks.addProperty("reg_number", "13252436465523413ё21352463");
        sedLinks.addProperty("reg_date", "18.02.2024");
        sedLinks.addProperty("provider_id", idProvider);


        sedLinksArray.add(sedLinks);

        requestPayload.add("sed_links", sedLinksArray);


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
        int id = jsonPath.get("data.sed_links[0].id");
        String name = jsonPath.get("data.sed_links[0].name");
        String url = jsonPath.get("data.sed_links[0].url");

        Assert.assertNotNull(id);
        Assert.assertTrue(name.equals("От 18.02.2024 № 13252436465523413ё21352463"));
        Assert.assertTrue(url.equals("14235364786970865435241ё324253678697086543214567"));
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

        JsonArray sedLinksArray = new JsonArray();

        JsonObject sedLinks = new JsonObject();
        sedLinks.addProperty("uid", "1324456475452341334563756452313425665432314253654735241");
        sedLinks.addProperty("url", "14235364786970865435241ё324253678697086543214567");
        sedLinks.addProperty("reg_number", "13252436465523413ё21352463");
        sedLinks.addProperty("reg_date", "122432421");
        sedLinks.addProperty("provider_id", idProvider);


        sedLinksArray.add(sedLinks);

        requestPayload.add("sed_links", sedLinksArray);


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
        String error = jsonPath.get("errors.sed_links.0.reg_date[0]");
        Assert.assertTrue(error.equals("имеет неверное значение"));
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

        JsonArray sedLinksArray = new JsonArray();

        JsonObject sedLinks = new JsonObject();
        sedLinks.addProperty("uid", "1324456475452341334563756452313425665432314253654735241");
        sedLinks.addProperty("url", "14235364786970865435241ё324253678697086543214567");
        sedLinks.addProperty("reg_number", "13252436465523413ё21352463");
        sedLinks.addProperty("reg_date", "18.02.2024");
        sedLinks.addProperty("provider_id", "212354634587");


        sedLinksArray.add(sedLinks);

        requestPayload.add("sed_links", sedLinksArray);


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
        String error = jsonPath.get("errors.documents.0.provider_id[0]");
        Assert.assertTrue(error.equals("имеет непредусмотренное значение"));
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

        JsonArray sedLinksArray = new JsonArray();

        JsonObject sedLinks = new JsonObject();
        sedLinks.addProperty("uid", uidDoc3);
        sedLinks.addProperty("url", sedDoc3);
        sedLinks.addProperty("reg_number", regNoDoc3);
        sedLinks.addProperty("reg_date", dateDoc3);
        sedLinks.addProperty("provider_id", idProvider);
        JsonObject sedLinks2 = new JsonObject();
        sedLinks2.addProperty("uid", uidDoc1);
        sedLinks2.addProperty("url", sedDoc1);
        sedLinks2.addProperty("reg_number", regNoDoc1);
        sedLinks2.addProperty("reg_date", dateDoc1);
        sedLinks2.addProperty("provider_id", idProvider);
        JsonObject sedLinks3 = new JsonObject();
        sedLinks3.addProperty("uid", uidDoc2);
        sedLinks3.addProperty("url", sedDoc2);
        sedLinks3.addProperty("reg_number", regNoDoc2);
        sedLinks3.addProperty("reg_date", dateDoc2);
        sedLinks3.addProperty("provider_id", idProvider);


        sedLinksArray.add(sedLinks);
        sedLinksArray.add(sedLinks2);
        sedLinksArray.add(sedLinks3);

        requestPayload.add("sed_links", sedLinksArray);


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
        int id = jsonPath.get("data.sed_links[0].id");
        String name = jsonPath.get("data.sed_links[0].name");
        String url = jsonPath.get("data.sed_links[0].url");
        idSed1=id;

        Assert.assertNotNull(id);
        Assert.assertTrue(name.equals("От 16.02.2024 № 65cf2402c4ed17aec300001f"));
        Assert.assertTrue(url.equals("http://outgoing-documents.sed2.rtech.ru/cards/65cf2402c4ed17aec300001f"));

        int id2 = jsonPath.get("data.sed_links[1].id");
        String name2 = jsonPath.get("data.sed_links[1].name");
        String url2 = jsonPath.get("data.sed_links[1].url");

        idSed2=id2;
        Assert.assertNotNull(id2);
        Assert.assertTrue(name2.equals("От 09.02.2024 № 65c5d0d9d8fe8d1036000003"));
        Assert.assertTrue(url2.equals("http://incoming-documents.sed2.rtech.ru/cards/65c5d0d9d8fe8d1036000003"));

        int id3 = jsonPath.get("data.sed_links[2].id");
        String name3 = jsonPath.get("data.sed_links[2].name");
        String url3 = jsonPath.get("data.sed_links[2].url");
        idSed3=id3;

        Assert.assertNotNull(id3);
        Assert.assertTrue(name3.equals("От 02.01.2024 № 65801d629443000003000001"));
        Assert.assertTrue(url3.equals("http://incoming-documents.sed2.rtech.ru/cards/65801d629443000003000001"));

        int idDoc = jsonPath.get("data.id");
        idDocument = idDoc;
    }

    @Test
    public void step13() {

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .when()
                .get(API_DOCUMENTS+"/"+idDocument)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.get("data.sed_links[0].id");
        String name = jsonPath.get("data.sed_links[0].name");
        String url = jsonPath.get("data.sed_links[0].url");

        Assert.assertTrue(id==idSed1);
        Assert.assertTrue(name.equals("От " + dateDoc3 + " № "+regNoDoc3));
        Assert.assertTrue(url.equals("http://outgoing-documents.sed2.rtech.ru/cards/65cf2402c4ed17aec300001f"));

        int id2 = jsonPath.get("data.sed_links[1].id");
        String name2 = jsonPath.get("data.sed_links[1].name");
        String url2 = jsonPath.get("data.sed_links[1].url");

        Assert.assertTrue(id2==idSed2);
        Assert.assertTrue(name2.equals("От " + dateDoc1 + " № "+regNoDoc1));
        Assert.assertTrue(url2.equals("http://incoming-documents.sed2.rtech.ru/cards/65c5d0d9d8fe8d1036000003"));

        int id3 = jsonPath.get("data.sed_links[2].id");
        String name3 = jsonPath.get("data.sed_links[2].name");
        String url3 = jsonPath.get("data.sed_links[2].url");

        Assert.assertTrue(id3==idSed3);
        Assert.assertTrue(name3.equals("От " + dateDoc2 + " № "+regNoDoc2));
        Assert.assertTrue(url3.equals("http://incoming-documents.sed2.rtech.ru/cards/65801d629443000003000001"));

    }
}
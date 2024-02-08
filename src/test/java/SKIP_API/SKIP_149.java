package SKIP_API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SKIP_149 {

    int idAut1 = 1;
    int idAut6 = 6;
    int idAut2 = 2;
    int idDoc, idDoc2;
    int txt, txt2;


    String API_IMAGES = "http://api.skip.rtech.ru/v1/images";
    String API_DOCUMENTS = "http://api.skip.rtech.ru/v1/documents";


    @BeforeClass
    public void step001() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .get(API_DOCUMENTS)
                .then().log().all()
                .extract().response();


        JsonPath jsonPath = response.jsonPath();
        List<String> data = jsonPath.get("data");
        int id = jsonPath.get("data[0].id");
        idDoc = id;
        Assert.assertNotNull(data);


    }

    @BeforeClass
    public void step002() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut2)
                .get(API_DOCUMENTS)
                .then().log().all()
                .extract().response();


        JsonPath jsonPath = response.jsonPath();
        List<String> data = jsonPath.get("data");
        int id = jsonPath.get("data[0].id");
        idDoc2 = id;
        Assert.assertNotNull(data);


    }

    @Test
    public void step01() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\txt.txt";
        File file = new File(filePath);
        Response response = given()
                .multiPart("original_file", file)
                .formParam("document_id", idDoc)
                .when()
                .header("Test-Authorization", idAut1)
                .post(API_IMAGES)
                .then()
                .log()
                .all()
                .extract()
                .response();

        int statusCode = response.statusCode();
        String responseBody = response.body().asString();
        System.out.println("Статус код: " + statusCode);
        System.out.println("Тело ответа: " + responseBody);
        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.get("data.id");
        String url = jsonPath.get("data.url");
        String original_url = jsonPath.get("data.original_url");
        String download_url = jsonPath.get("data.download_url");
        String original_download_url = jsonPath.get("data.original_download_url");
        String pdf_state = jsonPath.get("data.pdf_state");
        String name = jsonPath.getString("data.name");

        txt = id;
        Assert.assertTrue(id != 0);
        Assert.assertNotNull(url.length());
        Assert.assertNotNull(original_url.length());
        Assert.assertNotNull(download_url.length());
        Assert.assertNotNull(original_download_url.length());
        Assert.assertTrue(pdf_state.equals("waiting"));

    }

    @Test
    public void step02() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .delete(API_IMAGES + "/" + txt)
                .then().log().all()
                .extract().response();
        int statusCode = response.statusCode();
        Assert.assertTrue(statusCode == 204);
    }

    @Test
    public void step03() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .delete(API_IMAGES + "/" + txt)
                .then().log().all()
                .extract().response();
        int statusCode = response.statusCode();
        Assert.assertTrue(statusCode == 204);
    }

    @Test
    public void step04() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .get(API_IMAGES + "/" + txt)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.get("data.id");
        String url = jsonPath.get("data.url");
        String original_url = jsonPath.get("data.original_url");
        String download_url = jsonPath.get("data.download_url");
        String original_download_url = jsonPath.get("data.original_download_url");
        String name = jsonPath.getString("data.name");
        Boolean deleted = jsonPath.get("data.deleted");

        txt = id;
        Assert.assertTrue(id == txt);
        Assert.assertNotNull(url.length());
        Assert.assertNotNull(original_url.length());
        Assert.assertNotNull(download_url.length());
        Assert.assertNotNull(original_download_url.length());
        Assert.assertTrue(deleted);


    }

    @Test
    public void step05() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .get(API_DOCUMENTS + "/" + idDoc)
                .then().log().all()
                .extract().response();


        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.get("data.id");
        List<Integer> idI = jsonPath.get("data.images");
        Assert.assertTrue(id == idDoc);


        Assert.assertTrue(!idI.contains(txt));
    }

    @Test
    public void step06() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .delete(API_IMAGES + "/" + 2000)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.get("error");
        Assert.assertTrue(error.equals("Запись для Images::Image с id = 2000 не найдена"));
    }

    @Test
    public void step07() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut6)
                .delete(API_IMAGES + "/" + txt)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.get("error");
        Assert.assertTrue(error.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void step08() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\txt.txt";
        File file = new File(filePath);
        Response response = given()
                .multiPart("original_file", file)
                .formParam("document_id", idDoc2)
                .when()
                .header("Test-Authorization", idAut2)
                .post(API_IMAGES)
                .then()
                .log()
                .all()
                .extract()
                .response();

        int statusCode = response.statusCode();
        String responseBody = response.body().asString();
        System.out.println("Статус код: " + statusCode);
        System.out.println("Тело ответа: " + responseBody);
        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.get("data.id");
        String url = jsonPath.get("data.url");
        String original_url = jsonPath.get("data.original_url");
        String download_url = jsonPath.get("data.download_url");
        String original_download_url = jsonPath.get("data.original_download_url");
        String pdf_state = jsonPath.get("data.pdf_state");

        txt2 = id;
        Assert.assertTrue(id != 0);
        Assert.assertNotNull(url.length());
        Assert.assertNotNull(original_url.length());
        Assert.assertNotNull(download_url.length());
        Assert.assertNotNull(original_download_url.length());
        Assert.assertTrue(pdf_state.equals("waiting"));

    }

    @Test
    public void step09() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .delete(API_IMAGES + "/" + txt2)
                .then().log().all()
                .extract().response();
        int statusCode = response.statusCode();
        Assert.assertTrue(statusCode == 204);
    }

}
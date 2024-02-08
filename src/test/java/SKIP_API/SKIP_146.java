package SKIP_API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SKIP_146 {

    int idAut1 = 1;
    int idAut6 = 6;
    int idDoc;
    int doc, txt;


    String API_IMAGES = "http://api.skip.rtech.ru/v1/images";
    String API_DOCUMENTS = "http://api.skip.rtech.ru/v1/documents";


    @BeforeClass
    public void step00() {
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
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\doc.doc";
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

        doc = id;

        Assert.assertTrue(id != 0);
        Assert.assertNotNull(url.length());
        Assert.assertNotNull(original_url.length());
        Assert.assertNotNull(download_url.length());
        Assert.assertNotNull(original_download_url.length());
        Assert.assertTrue(pdf_state.equals("waiting"));


    }

    @Test
    public void step03() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .get(API_DOCUMENTS + "/" + idDoc)
                .then().log().all()
                .extract().response();


        JsonPath jsonPath = response.jsonPath();

        int idDocs = jsonPath.get("data.images[0].id");
        int idTxt = jsonPath.get("data.images[1].id");
        Assert.assertTrue(idDocs == doc);
        Assert.assertTrue(idTxt == txt);

    }

    @Test
    public void step04() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut6)
                .get(API_DOCUMENTS + "/" + idDoc)
                .then().log().all()
                .extract().response();


        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.get("error");
        Assert.assertTrue(error.equals("Доступ к ресурсу запрещен"));
    }
}

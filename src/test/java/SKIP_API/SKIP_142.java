package SKIP_API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SKIP_142 {
    int idAut1=1;
    int idDoc;

    String API_IMAGES = "http://api.skip.rtech.ru/v1/images";
    String API_DOCUMENTS = "http://api.skip.rtech.ru/v1/documents";



    @Test
    public void step01() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .get(API_DOCUMENTS)
                .then().log().all()
                .extract().response();


        JsonPath jsonPath = response.jsonPath();
        List <String> data=jsonPath.get("data");
        int id=jsonPath.get("data[0].id");
        idDoc=id;
        Assert.assertNotNull(data);


    }


    @Test
    public void step02() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл 0 байт.txt";
        File file = new File(filePath);
        Response response = RestAssured.given()
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
        String error=jsonPath.get("errors.original_file[0]");

        Assert.assertTrue(error.equals("Файл размером 0 байт не может быть добавлен"));
    }
    @Test
    public void step03() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл 4.77кб.jfif";
        File file = new File(filePath);
        Response response = RestAssured.given()
                .multiPart("original_file", file)
                .formParam("document_id", idDoc)
                .formParam("name","test")
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
        int id=jsonPath.get("data.id");
        String name=jsonPath.get("data.name");
        String url=jsonPath.get("data.url");
        String original_url=jsonPath.get("data.original_url");
        String download_url=jsonPath.get("data.download_url");
        String original_download_url=jsonPath.get("data.original_download_url");
        String pdf_state=jsonPath.get("data.pdf_state");

        Assert.assertTrue(id!=0);
        Assert.assertTrue(name.equals("test"));
        Assert.assertNotNull(url.length());
        Assert.assertNotNull(original_url.length());
        Assert.assertNotNull(download_url.length());
        Assert.assertNotNull(original_download_url.length());
        Assert.assertTrue(pdf_state.equals("waiting"));

    }

    @Test
    public void step04() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\Файл 50 мб.jpg";
        File file = new File(filePath);
        Response response = RestAssured.given()
                .multiPart("original_file", file)
                .formParam("document_id", idDoc)
                .formParam("name","test2")
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
        int id=jsonPath.get("data.id");
        String name=jsonPath.get("data.name");
        String url=jsonPath.get("data.url");
        String original_url=jsonPath.get("data.original_url");
        String download_url=jsonPath.get("data.download_url");
        String original_download_url=jsonPath.get("data.original_download_url");
        String pdf_state=jsonPath.get("data.pdf_state");

        Assert.assertTrue(id!=0);
        Assert.assertTrue(name.equals("test2"));
        Assert.assertNotNull(url.length());
        Assert.assertNotNull(original_url.length());
        Assert.assertNotNull(download_url.length());
        Assert.assertNotNull(original_download_url.length());
        Assert.assertTrue(pdf_state.equals("waiting"));

    }

    @Test
    public void step05() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл exe.exe";
        File file = new File(filePath);
        Response response = RestAssured.given()
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
        String error=jsonPath.get("errors.original_file[0]");

        Assert.assertTrue(error.contains("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));

    }

    @Test
    public void step06() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\Файл класс.class";
        File file = new File(filePath);
        Response response = RestAssured.given()
                .multiPart("original_file", file)
                .formParam("document_id", idDoc)
                .formParam("name","test3")
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
        String error=jsonPath.get("errors.original_file[0]");

        Assert.assertTrue(error.contains("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));

    }

    @Test
    public void step07() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл 290мб.mp4";
        File file = new File(filePath);
        Response response = RestAssured.given()
                .multiPart("original_file", file)
                .formParam("document_id", idDoc)
                .formParam("name","test3")
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
        int id=jsonPath.get("data.id");
        String name=jsonPath.get("data.name");
        String url=jsonPath.get("data.url");
        String original_url=jsonPath.get("data.original_url");
        String download_url=jsonPath.get("data.download_url");
        String original_download_url=jsonPath.get("data.original_download_url");
        String pdf_state=jsonPath.get("data.pdf_state");

        Assert.assertTrue(id!=0);
        Assert.assertTrue(name.equals("test3"));
        Assert.assertNotNull(url.length());
        Assert.assertNotNull(original_url.length());
        Assert.assertNotNull(download_url.length());
        Assert.assertNotNull(original_download_url.length());
        Assert.assertTrue(pdf_state.equals("waiting"));

    }


    @Test
    public void step08() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\Файл 353 мб.mp4";
        File file = new File(filePath);
        Response response = RestAssured.given()
                .multiPart("original_file", file)
                .formParam("document_id", idDoc)
                .formParam("name","test3")
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
        String error=jsonPath.get("errors.original_file[0]");

        Assert.assertTrue(error.contains("Превышен максимальный размер загружаемого файла (300 МБ)"));

    }

    @Test
    public void step09() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\dll файл.dll";
        File file = new File(filePath);
        Response response = RestAssured.given()
                .multiPart("original_file", file)
                .formParam("document_id", idDoc)
                .formParam("name","test3")
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
        String error=jsonPath.get("errors.original_file[0]");

        Assert.assertTrue(error.contains("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));

    }

    @Test
    public void step10() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл ocx.ocx";
        File file = new File(filePath);
        Response response = RestAssured.given()
                .multiPart("original_file", file)
                .formParam("document_id", idDoc)
                .formParam("name","test3")
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
        String error=jsonPath.get("errors.original_file[0]");

        Assert.assertTrue(error.contains("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));

    }

    @Test
    public void step11() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл scr.scr";
        File file = new File(filePath);
        Response response = RestAssured.given()
                .multiPart("original_file", file)
                .formParam("document_id", idDoc)
                .formParam("name","test3")
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
        String error=jsonPath.get("errors.original_file[0]");

        Assert.assertTrue(error.contains("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));

    }

    @Test
    public void step12() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл msi.msi";
        File file = new File(filePath);
        Response response = RestAssured.given()
                .multiPart("original_file", file)
                .formParam("document_id", idDoc)
                .formParam("name","test3")
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
        String error=jsonPath.get("errors.original_file[0]");

        Assert.assertTrue(error.contains("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));

    }
    @Test
    public void step13() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл elf.elf";
        File file = new File(filePath);
        Response response = RestAssured.given()
                .multiPart("original_file", file)
                .formParam("document_id", idDoc)
                .formParam("name","test3")
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
        String error=jsonPath.get("errors.original_file[0]");

        Assert.assertTrue(error.contains("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));

    }

    @Test
    public void step14() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл deb.deb";
        File file = new File(filePath);
        Response response = RestAssured.given()
                .multiPart("original_file", file)
                .formParam("document_id", idDoc)
                .formParam("name","test3")
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
        String error=jsonPath.get("errors.original_file[0]");

        Assert.assertTrue(error.contains("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));

    }

    @Test
    public void step15() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл rpm.rpm";
        File file = new File(filePath);
        Response response = RestAssured.given()
                .multiPart("original_file", file)
                .formParam("document_id", idDoc)
                .formParam("name","test3")
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
        String error=jsonPath.get("errors.original_file[0]");

        Assert.assertTrue(error.contains("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));

    }

    @Test
    public void step16() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл eps.eps";
        File file = new File(filePath);
        Response response = RestAssured.given()
                .multiPart("original_file", file)
                .formParam("document_id", idDoc)
                .formParam("name","test3")
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
        String error=jsonPath.get("errors.original_file[0]");

        Assert.assertTrue(error.contains("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));

    }

    @Test
    public void step17() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл iso.iso";
        File file = new File(filePath);
        Response response = RestAssured.given()
                .multiPart("original_file", file)
                .formParam("document_id", idDoc)
                .formParam("name","test3")
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
        String error=jsonPath.get("errors.original_file[0]");

        Assert.assertTrue(error.contains("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));

    }

    @Test
    public void step18() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл cab.cab";
        File file = new File(filePath);
        Response response = RestAssured.given()
                .multiPart("original_file", file)
                .formParam("document_id", idDoc)
                .formParam("name","test3")
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
        String error=jsonPath.get("errors.original_file[0]");

        Assert.assertTrue(error.contains("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));

    }

    @Test
    public void step19() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл jar.jar";
        File file = new File(filePath);
        Response response = RestAssured.given()
                .multiPart("original_file", file)
                .formParam("document_id", idDoc)
                .formParam("name","test3")
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
        String error=jsonPath.get("errors.original_file[0]");

        Assert.assertTrue(error.contains("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));

    }

    @Test
    public void step20() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл accdb.accdb";
        File file = new File(filePath);
        Response response = RestAssured.given()
                .multiPart("original_file", file)
                .formParam("document_id", idDoc)
                .formParam("name","test3")
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
        String error=jsonPath.get("errors.original_file[0]");

        Assert.assertTrue(error.contains("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));

    }

    @Test
    public void step21() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл accde.accde";
        File file = new File(filePath);
        Response response = RestAssured.given()
                .multiPart("original_file", file)
                .formParam("document_id", idDoc)
                .formParam("name","test3")
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
        String error=jsonPath.get("errors.original_file[0]");

        Assert.assertTrue(error.contains("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));

    }

    @Test
    public void step22() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл accdp.accdp";
        File file = new File(filePath);
        Response response = RestAssured.given()
                .multiPart("original_file", file)
                .formParam("document_id", idDoc)
                .formParam("name","test3")
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
        String error=jsonPath.get("errors.original_file[0]");

        Assert.assertTrue(error.contains("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));

    }
    @Test
    public void step23() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл accdr.accdr";
        File file = new File(filePath);
        Response response = RestAssured.given()
                .multiPart("original_file", file)
                .formParam("document_id", idDoc)
                .formParam("name","test3")
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
        String error=jsonPath.get("errors.original_file[0]");

        Assert.assertTrue(error.contains("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));

    }

    @Test
    public void step24() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл accdc.accdc";
        File file = new File(filePath);
        Response response = RestAssured.given()
                .multiPart("original_file", file)
                .formParam("document_id", idDoc)
                .formParam("name","test3")
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
        String error=jsonPath.get("errors.original_file[0]");

        Assert.assertTrue(error.contains("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));

    }
    @Test
    public void step25() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл mdb.mdb";
        File file = new File(filePath);
        Response response = RestAssured.given()
                .multiPart("original_file", file)
                .formParam("document_id", idDoc)
                .formParam("name","test3")
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
        String error=jsonPath.get("errors.original_file[0]");

        Assert.assertTrue(error.contains("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));

    }

    @Test
    public void step26() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл mdt.mdt";
        File file = new File(filePath);
        Response response = RestAssured.given()
                .multiPart("original_file", file)
                .formParam("document_id", idDoc)
                .formParam("name","test3")
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
        String error=jsonPath.get("errors.original_file[0]");

        Assert.assertTrue(error.contains("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));

    }
    @Test
    public void step27() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы с расширениями\\файл свое расширение.dымамаа";
        File file = new File(filePath);
        Response response = RestAssured.given()
                .multiPart("original_file", file)
                .formParam("document_id", idDoc)
                .formParam("name","test3")
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
        int id=jsonPath.get("data.id");
        String name=jsonPath.get("data.name");
        String url=jsonPath.get("data.url");
        String original_url=jsonPath.get("data.original_url");
        String download_url=jsonPath.get("data.download_url");
        String original_download_url=jsonPath.get("data.original_download_url");
        String pdf_state=jsonPath.get("data.pdf_state");

        Assert.assertTrue(id!=0);
        Assert.assertTrue(name.equals("test3"));
        Assert.assertNotNull(url.length());
        Assert.assertNotNull(original_url.length());
        Assert.assertNotNull(download_url.length());
        Assert.assertNotNull(original_download_url.length());
        Assert.assertTrue(pdf_state.equals("waiting"));

    }
}

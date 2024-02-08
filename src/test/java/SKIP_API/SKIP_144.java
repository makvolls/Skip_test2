package SKIP_API;

import io.restassured.RestAssured;
import io.restassured.internal.RestAssuredResponseOptionsGroovyImpl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

import static io.restassured.RestAssured.given;
import static java.lang.Thread.sleep;

public class SKIP_144 {

    int idAut1 = 1;
    int idDoc;
    int csv, doc, docx, gif, htm, html, jpeg, jpg, png, tif, tiff, txt, dymaa;


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
        List<String> data = jsonPath.get("data");
        int id = jsonPath.get("data[0].id");
        idDoc = id;
        Assert.assertNotNull(data);


    }

    @Test
    public void step02() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\csv.csv";
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

        csv = id;

        Assert.assertTrue(id != 0);
        Assert.assertNotNull(url.length());
        Assert.assertNotNull(original_url.length());
        Assert.assertNotNull(download_url.length());
        Assert.assertNotNull(original_download_url.length());
        Assert.assertTrue(pdf_state.equals("waiting"));

    }

    @Test
    public void step03() {
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
    public void step04() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\docx.docx";
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

        docx = id;

        Assert.assertTrue(id != 0);
        Assert.assertNotNull(url.length());
        Assert.assertNotNull(original_url.length());
        Assert.assertNotNull(download_url.length());
        Assert.assertNotNull(original_download_url.length());
        Assert.assertTrue(pdf_state.equals("waiting"));


    }

    @Test
    public void step05() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\gif.gif";
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

        gif = id;

        Assert.assertTrue(id != 0);
        Assert.assertNotNull(url.length());
        Assert.assertNotNull(original_url.length());
        Assert.assertNotNull(download_url.length());
        Assert.assertNotNull(original_download_url.length());
        Assert.assertTrue(pdf_state.equals("waiting"));


    }

    @Test
    public void step06() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\htm.htm";
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

        htm = id;

        Assert.assertTrue(id != 0);
        Assert.assertNotNull(url.length());
        Assert.assertNotNull(original_url.length());
        Assert.assertNotNull(download_url.length());
        Assert.assertNotNull(original_download_url.length());
        Assert.assertTrue(pdf_state.equals("waiting"));


    }

    @Test
    public void step07() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\html.html";
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

        html = id;

        Assert.assertTrue(id != 0);
        Assert.assertNotNull(url.length());
        Assert.assertNotNull(original_url.length());
        Assert.assertNotNull(download_url.length());
        Assert.assertNotNull(original_download_url.length());
        Assert.assertTrue(pdf_state.equals("waiting"));


    }

    @Test
    public void step08() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\jpeg.jpeg";
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

        jpeg = id;

        Assert.assertTrue(id != 0);
        Assert.assertNotNull(url.length());
        Assert.assertNotNull(original_url.length());
        Assert.assertNotNull(download_url.length());
        Assert.assertNotNull(original_download_url.length());
        Assert.assertTrue(pdf_state.equals("waiting"));


    }

    @Test
    public void step09() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\jpg.jpg";
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

        jpg = id;

        Assert.assertTrue(id != 0);
        Assert.assertNotNull(url.length());
        Assert.assertNotNull(original_url.length());
        Assert.assertNotNull(download_url.length());
        Assert.assertNotNull(original_download_url.length());
        Assert.assertTrue(pdf_state.equals("waiting"));


    }

    @Test
    public void step10() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\png.png";
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

        png = id;

        Assert.assertTrue(id != 0);
        Assert.assertNotNull(url.length());
        Assert.assertNotNull(original_url.length());
        Assert.assertNotNull(download_url.length());
        Assert.assertNotNull(original_download_url.length());
        Assert.assertTrue(pdf_state.equals("waiting"));


    }

    @Test
    public void step11() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\tif.tif";
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

        tif = id;

        Assert.assertTrue(id != 0);
        Assert.assertNotNull(url.length());
        Assert.assertNotNull(original_url.length());
        Assert.assertNotNull(download_url.length());
        Assert.assertNotNull(original_download_url.length());
        Assert.assertTrue(pdf_state.equals("waiting"));


    }

    @Test
    public void step12() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\tiff.tiff";
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

        tiff = id;

        Assert.assertTrue(id != 0);
        Assert.assertNotNull(url.length());
        Assert.assertNotNull(original_url.length());
        Assert.assertNotNull(download_url.length());
        Assert.assertNotNull(original_download_url.length());
        Assert.assertTrue(pdf_state.equals("waiting"));


    }

    @Test
    public void step13() {
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
    public void step14() throws InterruptedException {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\файл свое расширение.dымамаа";
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

        dymaa = id;

        Assert.assertTrue(id != 0);
        Assert.assertNotNull(url.length());
        Assert.assertNotNull(original_url.length());
        Assert.assertNotNull(download_url.length());
        Assert.assertNotNull(original_download_url.length());
        Assert.assertTrue(pdf_state.equals("waiting"));


    }
    @Test
    public void step15() {
        boolean allChecksPassed = false;
        int attemptNum = 1;

        while (!allChecksPassed && attemptNum <= 10) {
            // Выполняем запрос
            Response response = given()
                    .when()
                    .header("Test-Authorization", idAut1)
                    .get(API_IMAGES + "/" + doc)
                    .then().log().all()
                    .extract().response();

            JsonPath jsonPath = response.jsonPath();
            String pdf_state = jsonPath.get("data.pdf_state");
            int id = jsonPath.get("data.id");
            String url = jsonPath.get("data.url");
            String original_url = jsonPath.get("data.original_url");
            String download_url = jsonPath.get("data.download_url");
            String original_download_url = jsonPath.get("data.original_download_url");
            String pdf_url = jsonPath.get("data.pdf_url");
            String pdf_download_url = jsonPath.get("data.pdf_download_url");

            // Проверяем условия
            if (pdf_state.equals("attached") &&
                    id == doc &&
                    url != null &&
                    original_url != null &&
                    download_url != null &&
                    original_download_url != null &&
                    pdf_url != null &&
                    pdf_download_url != null) {
                allChecksPassed = true;
            } else {
                // Выводим информацию о текущей попытке
                System.out.println("Attempt #" + attemptNum + " failed. Retrying...");

                // Увеличиваем число попыток
                attemptNum++;

                // Пауза перед следующей попыткой, чтобы не создавать слишком большую нагрузку
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // Если все проверки пройдены успешно, выводим сообщение о завершении
        System.out.println("All checks passed!");
    }


    @Test
    public void step16() {
        boolean allChecksPassed = false;
        int attemptNum = 1;

        while (!allChecksPassed && attemptNum <= 10) {
            // Выполняем запрос
            Response response = given()
                    .when()
                    .header("Test-Authorization", idAut1)
                    .get(API_IMAGES + "/" + docx)
                    .then().log().all()
                    .extract().response();

            JsonPath jsonPath = response.jsonPath();
            String pdf_state = jsonPath.get("data.pdf_state");
            int id = jsonPath.get("data.id");
            String url = jsonPath.get("data.url");
            String original_url = jsonPath.get("data.original_url");
            String download_url = jsonPath.get("data.download_url");
            String original_download_url = jsonPath.get("data.original_download_url");
            String pdf_url = jsonPath.get("data.pdf_url");
            String pdf_download_url = jsonPath.get("data.pdf_download_url");

            // Проверяем условия
            if (pdf_state.equals("attached") &&
                    id == docx &&
                    url != null &&
                    original_url != null &&
                    download_url != null &&
                    original_download_url != null &&
                    pdf_url != null &&
                    pdf_download_url != null) {
                allChecksPassed = true;
            } else {
                // Выводим информацию о текущей попытке
                System.out.println("Attempt #" + attemptNum + " failed. Retrying...");

                // Увеличиваем число попыток
                attemptNum++;

                // Пауза перед следующей попыткой, чтобы не создавать слишком большую нагрузку
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // Если все проверки пройдены успешно, выводим сообщение о завершении
        System.out.println("All checks passed!");
    }




    @Test
    public void step17(){
    boolean allChecksPassed = false;
    int attemptNum = 1;

        while (!allChecksPassed && attemptNum <= 10) {
        // Выполняем запрос
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .get(API_IMAGES + "/" + gif)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String pdf_state = jsonPath.get("data.pdf_state");
        int id = jsonPath.get("data.id");
        String url = jsonPath.get("data.url");
        String original_url = jsonPath.get("data.original_url");
        String download_url = jsonPath.get("data.download_url");
        String original_download_url = jsonPath.get("data.original_download_url");
        String pdf_url = jsonPath.get("data.pdf_url");
        String pdf_download_url = jsonPath.get("data.pdf_download_url");

        // Проверяем условия
        if (pdf_state.equals("attached") &&
                id == gif &&
                url != null &&
                original_url != null &&
                download_url != null &&
                original_download_url != null &&
                pdf_url != null &&
                pdf_download_url != null) {
            allChecksPassed = true;
        } else {
            // Выводим информацию о текущей попытке
            System.out.println("Attempt #" + attemptNum + " failed. Retrying...");

            // Увеличиваем число попыток
            attemptNum++;

            // Пауза перед следующей попыткой, чтобы не создавать слишком большую нагрузку
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Если все проверки пройдены успешно, выводим сообщение о завершении
        System.out.println("All checks passed!");
}


@Test
    public void step18(){
    boolean allChecksPassed = false;
    int attemptNum = 1;

    while (!allChecksPassed && attemptNum <= 10) {
        // Выполняем запрос
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .get(API_IMAGES + "/" + htm)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String pdf_state = jsonPath.get("data.pdf_state");
        int id = jsonPath.get("data.id");
        String url = jsonPath.get("data.url");
        String original_url = jsonPath.get("data.original_url");
        String download_url = jsonPath.get("data.download_url");
        String original_download_url = jsonPath.get("data.original_download_url");
        String pdf_url = jsonPath.get("data.pdf_url");
        String pdf_download_url = jsonPath.get("data.pdf_download_url");

        // Проверяем условия
        if (pdf_state.equals("attached") &&
                id == htm &&
                url != null &&
                original_url != null &&
                download_url != null &&
                original_download_url != null &&
                pdf_url != null &&
                pdf_download_url != null) {
            allChecksPassed = true;
        } else {
            // Выводим информацию о текущей попытке
            System.out.println("Attempt #" + attemptNum + " failed. Retrying...");

            // Увеличиваем число попыток
            attemptNum++;

            // Пауза перед следующей попыткой, чтобы не создавать слишком большую нагрузку
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Если все проверки пройдены успешно, выводим сообщение о завершении
    System.out.println("All checks passed!");
}


    @Test
    public void step19(){
        boolean allChecksPassed = false;
        int attemptNum = 1;

        while (!allChecksPassed && attemptNum <= 10) {
            // Выполняем запрос
            Response response = given()
                    .when()
                    .header("Test-Authorization", idAut1)
                    .get(API_IMAGES + "/" + html)
                    .then().log().all()
                    .extract().response();

            JsonPath jsonPath = response.jsonPath();
            String pdf_state = jsonPath.get("data.pdf_state");
            int id = jsonPath.get("data.id");
            String url = jsonPath.get("data.url");
            String original_url = jsonPath.get("data.original_url");
            String download_url = jsonPath.get("data.download_url");
            String original_download_url = jsonPath.get("data.original_download_url");
            String pdf_url = jsonPath.get("data.pdf_url");
            String pdf_download_url = jsonPath.get("data.pdf_download_url");

            // Проверяем условия
            if (pdf_state.equals("attached") &&
                    id == html &&
                    url != null &&
                    original_url != null &&
                    download_url != null &&
                    original_download_url != null &&
                    pdf_url != null &&
                    pdf_download_url != null) {
                allChecksPassed = true;
            } else {
                // Выводим информацию о текущей попытке
                System.out.println("Attempt #" + attemptNum + " failed. Retrying...");

                // Увеличиваем число попыток
                attemptNum++;

                // Пауза перед следующей попыткой, чтобы не создавать слишком большую нагрузку
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // Если все проверки пройдены успешно, выводим сообщение о завершении
        System.out.println("All checks passed!");
    }


    @Test
    public void step20(){
        boolean allChecksPassed = false;
        int attemptNum = 1;

        while (!allChecksPassed && attemptNum <= 10) {
            // Выполняем запрос
            Response response = given()
                    .when()
                    .header("Test-Authorization", idAut1)
                    .get(API_IMAGES + "/" + jpeg)
                    .then().log().all()
                    .extract().response();

            JsonPath jsonPath = response.jsonPath();
            String pdf_state = jsonPath.get("data.pdf_state");
            int id = jsonPath.get("data.id");
            String url = jsonPath.get("data.url");
            String original_url = jsonPath.get("data.original_url");
            String download_url = jsonPath.get("data.download_url");
            String original_download_url = jsonPath.get("data.original_download_url");
            String pdf_url = jsonPath.get("data.pdf_url");
            String pdf_download_url = jsonPath.get("data.pdf_download_url");

            // Проверяем условия
            if (pdf_state.equals("attached") &&
                    id == jpeg &&
                    url != null &&
                    original_url != null &&
                    download_url != null &&
                    original_download_url != null &&
                    pdf_url != null &&
                    pdf_download_url != null) {
                allChecksPassed = true;
            } else {
                // Выводим информацию о текущей попытке
                System.out.println("Attempt #" + attemptNum + " failed. Retrying...");

                // Увеличиваем число попыток
                attemptNum++;

                // Пауза перед следующей попыткой, чтобы не создавать слишком большую нагрузку
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // Если все проверки пройдены успешно, выводим сообщение о завершении
        System.out.println("All checks passed!");
    }


    @Test
    public void step21(){
        boolean allChecksPassed = false;
        int attemptNum = 1;

        while (!allChecksPassed && attemptNum <= 10) {
            // Выполняем запрос
            Response response = given()
                    .when()
                    .header("Test-Authorization", idAut1)
                    .get(API_IMAGES + "/" + jpg)
                    .then().log().all()
                    .extract().response();

            JsonPath jsonPath = response.jsonPath();
            String pdf_state = jsonPath.get("data.pdf_state");
            int id = jsonPath.get("data.id");
            String url = jsonPath.get("data.url");
            String original_url = jsonPath.get("data.original_url");
            String download_url = jsonPath.get("data.download_url");
            String original_download_url = jsonPath.get("data.original_download_url");
            String pdf_url = jsonPath.get("data.pdf_url");
            String pdf_download_url = jsonPath.get("data.pdf_download_url");

            // Проверяем условия
            if (pdf_state.equals("attached") &&
                    id == jpg &&
                    url != null &&
                    original_url != null &&
                    download_url != null &&
                    original_download_url != null &&
                    pdf_url != null &&
                    pdf_download_url != null) {
                allChecksPassed = true;
            } else {
                // Выводим информацию о текущей попытке
                System.out.println("Attempt #" + attemptNum + " failed. Retrying...");

                // Увеличиваем число попыток
                attemptNum++;

                // Пауза перед следующей попыткой, чтобы не создавать слишком большую нагрузку
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // Если все проверки пройдены успешно, выводим сообщение о завершении
        System.out.println("All checks passed!");
    }


    @Test
    public void step22(){
    boolean allChecksPassed = false;
    int attemptNum = 1;

        while (!allChecksPassed && attemptNum <= 10) {
        // Выполняем запрос
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .get(API_IMAGES + "/" + png)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String pdf_state = jsonPath.get("data.pdf_state");
        int id = jsonPath.get("data.id");
        String url = jsonPath.get("data.url");
        String original_url = jsonPath.get("data.original_url");
        String download_url = jsonPath.get("data.download_url");
        String original_download_url = jsonPath.get("data.original_download_url");
        String pdf_url = jsonPath.get("data.pdf_url");
        String pdf_download_url = jsonPath.get("data.pdf_download_url");

        // Проверяем условия
        if (pdf_state.equals("attached") &&
                id == png &&
                url != null &&
                original_url != null &&
                download_url != null &&
                original_download_url != null &&
                pdf_url != null &&
                pdf_download_url != null) {
            allChecksPassed = true;
        } else {
            // Выводим информацию о текущей попытке
            System.out.println("Attempt #" + attemptNum + " failed. Retrying...");

            // Увеличиваем число попыток
            attemptNum++;

            // Пауза перед следующей попыткой, чтобы не создавать слишком большую нагрузку
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Если все проверки пройдены успешно, выводим сообщение о завершении
        System.out.println("All checks passed!");
}


@Test
    public void step23(){
    boolean allChecksPassed = false;
    int attemptNum = 1;

    while (!allChecksPassed && attemptNum <= 10) {
        // Выполняем запрос
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .get(API_IMAGES + "/" + tif)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String pdf_state = jsonPath.get("data.pdf_state");
        int id = jsonPath.get("data.id");
        String url = jsonPath.get("data.url");
        String original_url = jsonPath.get("data.original_url");
        String download_url = jsonPath.get("data.download_url");
        String original_download_url = jsonPath.get("data.original_download_url");
        String pdf_url = jsonPath.get("data.pdf_url");
        String pdf_download_url = jsonPath.get("data.pdf_download_url");

        // Проверяем условия
        if (pdf_state.equals("attached") &&
                id == tif &&
                url != null &&
                original_url != null &&
                download_url != null &&
                original_download_url != null &&
                pdf_url != null &&
                pdf_download_url != null) {
            allChecksPassed = true;
        } else {
            // Выводим информацию о текущей попытке
            System.out.println("Attempt #" + attemptNum + " failed. Retrying...");

            // Увеличиваем число попыток
            attemptNum++;

            // Пауза перед следующей попыткой, чтобы не создавать слишком большую нагрузку
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Если все проверки пройдены успешно, выводим сообщение о завершении
    System.out.println("All checks passed!");
}

    @Test
    public void step24(){
        boolean allChecksPassed = false;
        int attemptNum = 1;

        while (!allChecksPassed && attemptNum <= 10) {
            // Выполняем запрос
            Response response = given()
                    .when()
                    .header("Test-Authorization", idAut1)
                    .get(API_IMAGES + "/" + tiff)
                    .then().log().all()
                    .extract().response();

            JsonPath jsonPath = response.jsonPath();
            String pdf_state = jsonPath.get("data.pdf_state");
            int id = jsonPath.get("data.id");
            String url = jsonPath.get("data.url");
            String original_url = jsonPath.get("data.original_url");
            String download_url = jsonPath.get("data.download_url");
            String original_download_url = jsonPath.get("data.original_download_url");
            String pdf_url = jsonPath.get("data.pdf_url");
            String pdf_download_url = jsonPath.get("data.pdf_download_url");

            // Проверяем условия
            if (pdf_state.equals("attached") &&
                    id == tiff &&
                    url != null &&
                    original_url != null &&
                    download_url != null &&
                    original_download_url != null &&
                    pdf_url != null &&
                    pdf_download_url != null) {
                allChecksPassed = true;
            } else {
                // Выводим информацию о текущей попытке
                System.out.println("Attempt #" + attemptNum + " failed. Retrying...");

                // Увеличиваем число попыток
                attemptNum++;

                // Пауза перед следующей попыткой, чтобы не создавать слишком большую нагрузку
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // Если все проверки пройдены успешно, выводим сообщение о завершении
        System.out.println("All checks passed!");
    }

    @Test
    public void step25(){
    boolean allChecksPassed = false;
    int attemptNum = 1;

        while (!allChecksPassed && attemptNum <= 10) {
        // Выполняем запрос
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .get(API_IMAGES + "/" + txt)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String pdf_state = jsonPath.get("data.pdf_state");
        int id = jsonPath.get("data.id");
        String url = jsonPath.get("data.url");
        String original_url = jsonPath.get("data.original_url");
        String download_url = jsonPath.get("data.download_url");
        String original_download_url = jsonPath.get("data.original_download_url");
        String pdf_url = jsonPath.get("data.pdf_url");
        String pdf_download_url = jsonPath.get("data.pdf_download_url");

        // Проверяем условия
        if (pdf_state.equals("attached") &&
                id == txt &&
                url != null &&
                original_url != null &&
                download_url != null &&
                original_download_url != null &&
                pdf_url != null &&
                pdf_download_url != null) {
            allChecksPassed = true;
        } else {
            // Выводим информацию о текущей попытке
            System.out.println("Attempt #" + attemptNum + " failed. Retrying...");

            // Увеличиваем число попыток
            attemptNum++;

            // Пауза перед следующей попыткой, чтобы не создавать слишком большую нагрузку
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Если все проверки пройдены успешно, выводим сообщение о завершении
        System.out.println("All checks passed!");
}

@Test
    public void step26(){
    boolean allChecksPassed = false;
    int attemptNum = 1;

    while (!allChecksPassed && attemptNum <= 10) {
        // Выполняем запрос
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .get(API_IMAGES + "/" + csv)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String pdf_state = jsonPath.get("data.pdf_state");
        int id = jsonPath.get("data.id");
        String url = jsonPath.get("data.url");
        String original_url = jsonPath.get("data.original_url");
        String download_url = jsonPath.get("data.download_url");
        String original_download_url = jsonPath.get("data.original_download_url");
        String pdf_url = jsonPath.get("data.pdf_url");
        String pdf_download_url = jsonPath.get("data.pdf_download_url");

        // Проверяем условия
        if (pdf_state.equals("attached") &&
                id == csv &&
                url != null &&
                original_url != null &&
                download_url != null &&
                original_download_url != null &&
                pdf_url != null &&
                pdf_download_url != null) {
            allChecksPassed = true;
        } else {
            // Выводим информацию о текущей попытке
            System.out.println("Attempt #" + attemptNum + " failed. Retrying...");

            // Увеличиваем число попыток
            attemptNum++;

            // Пауза перед следующей попыткой, чтобы не создавать слишком большую нагрузку
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Если все проверки пройдены успешно, выводим сообщение о завершении
    System.out.println("All checks passed!");
}

    @Test
    public void step27() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .get(API_IMAGES + "/" + dymaa)
                .then().log().all()
                .extract().response();


        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.get("data.id");
        String url = jsonPath.get("data.url");
        String original_url = jsonPath.get("data.original_url");
        String download_url = jsonPath.get("data.download_url");
        String original_download_url = jsonPath.get("data.original_download_url");
        String pdf_state = jsonPath.get("data.pdf_state");


        Assert.assertTrue(id != 0);
        Assert.assertNotNull(url.length());
        Assert.assertNotNull(original_url.length());
        Assert.assertNotNull(download_url.length());
        Assert.assertNotNull(original_download_url.length());
        Assert.assertTrue(pdf_state.equals("conversion_failed"));


    }


}
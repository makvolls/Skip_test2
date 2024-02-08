package SKIP_API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SKIP_148 {

    int idAut1 = 1;
    int idAut6 = 6;
    int idDoc,idIM1,idIM2;
    String nameI1,nameI2;
    int csv,csv2;


    String API_IMAGES = "http://api.skip.rtech.ru/v1/images";
    String API_DOCUMENTS = "http://api.skip.rtech.ru/v1/documents";
    String API_IMAGINE_MARKS="http://api.skip.rtech.ru/v1/classifiers/image_marks";


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


        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .put(API_IMAGES + "/" + csv)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.get("data.id");
        String name = jsonPath.get("data.name");

        Assert.assertTrue(id == csv);
        Assert.assertTrue(name.equals("csv"));
    }
    @Test
    public void step04() {


        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .params("mark_id", "мвытомыв")
                .put(API_IMAGES + "/" + csv)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.get("errors.mark_id[0]");


        Assert.assertTrue(error.equals("имеет неверное значение"));

    }

    @Test
    public void step05() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .get(API_IMAGINE_MARKS)
                .then().log().all()
                .extract().response();


        JsonPath jsonPath = response.jsonPath();
        List<String> data = jsonPath.get("data");
        int id = jsonPath.get("data[0].id");
        int id2 = jsonPath.get("data[1].id");
        String name1=jsonPath.get("data[0].name");
        String name2=jsonPath.get("data[1].name");


        idIM1 = id;
        idIM2 = id2;
        nameI1=name1;
        nameI2=name2;
        Assert.assertNotNull(data);


    }
    @Test
    public void step06() {


        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .params("mark_id", idIM1)
                .put(API_IMAGES + "/" + csv)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.get("data.id");
        String name = jsonPath.get("data.name");
        int idM=jsonPath.get("data.mark.id");
        String nameI=jsonPath.get("data.mark.name");

        Assert.assertTrue(id == csv);
        Assert.assertTrue(name.equals("csv"));
        Assert.assertTrue(idM==idIM1);
        Assert.assertTrue(nameI.equals(nameI1));


    }

    @Test
    public void step07() {
        String filePath = "C:\\Users\\mvolobuev\\Desktop\\файлы для прокидывания\\Файлы для изменения в pdf\\csv.csv";
        File file = new File(filePath);
        Response response = given()
                .multiPart("original_file", file)
                .formParam("document_id", idDoc)
                .params("mark_id", idIM1)
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

        csv2 = id;
        String name = jsonPath.get("data.name");

        Assert.assertTrue(id == csv2);
        Assert.assertTrue(name.equals("csv"));

        int idM=jsonPath.get("data.mark.id");
        String nameI=jsonPath.get("data.mark.name");

        Assert.assertTrue(idM==idIM1);
        Assert.assertTrue(nameI.equals(nameI1));

    }

    @Test
    public void step08() {


        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .params("mark_id", idIM2)
                .put(API_IMAGES + "/" + csv2)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.get("data.id");
        String name = jsonPath.get("data.name");
        int idM=jsonPath.get("data.mark.id");
        String nameI=jsonPath.get("data.mark.name");

        Assert.assertTrue(id == csv2);
        Assert.assertTrue(name.equals("csv"));
        Assert.assertTrue(idM==idIM2);
        Assert.assertTrue(nameI.equals(nameI2));


    }

}
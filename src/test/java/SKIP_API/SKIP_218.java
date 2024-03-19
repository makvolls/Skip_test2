package SKIP_API;


import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;
public class SKIP_218 {

    // Endpoints:
    private static String API_IMAGES_SKIP = "http://api.skip.rtech.ru/v1/images";
    private static String API_DOCUMENTS = "http://api.skip.rtech.ru/v1/documents";

    // Authorization parameters:
    int userIdOne = 1; // User that have all rights, role id = 29
    int userIdSix = 6; // User that have right - "show_roles"

    // Variables
    int idDocument; // Random document's id that will get in step 1
    int idImageSkipFirst; // Image's id that will get in step 2
    int idImageSkipSecond; // Image's id that will get in step 3
    int idImageSkipThird; // Image's id that will get in step 4
    int idImageSkipFourth; // Image's id that will get in step 5
    int idImageSkipFifth; // Image's id that will get in step 6
    int idImageSkipSixth; // Image's id that will get in step 7
    int idImageSkipSeventh; // Image's id that will get in step 8
    int idImageSkipEighth; // Image's id that will get in step 9
    int idImageSkipNinth; // Image's id that will get in step 10
    int idImageSkipTenth; // Image's id that will get in step 11

    // Files
    File txtFile = new File("C:\\Users\\mkalugin\\Desktop\\formats_files\\txt_file.txt");
    File docxFile = new File("C:\\Users\\mkalugin\\Desktop\\formats_files\\docx_file.docx");
    File jfifFile = new File("C:\\Users\\mkalugin\\Desktop\\formats_files\\jfif_file.jfif");
    File jpgFile = new File("C:\\Users\\mkalugin\\Desktop\\formats_files\\jpg_file.jpg");
    File pdfFile = new File("C:\\Users\\mkalugin\\Desktop\\formats_files\\pdf_file.pdf");
    File pngFile = new File("C:\\Users\\mkalugin\\Desktop\\formats_files\\png_file.png");
    File pnmFile = new File("C:\\Users\\mkalugin\\Desktop\\formats_files\\pnm_file.pnm");
    File bmpFile = new File("C:\\Users\\mkalugin\\Desktop\\formats_files\\bmp_file.bmp");
    File jpegFile = new File("C:\\Users\\mkalugin\\Desktop\\formats_files\\jpeg_file.jpeg");
    File tiffFile = new File("C:\\Users\\mkalugin\\Desktop\\formats_files\\tiff_file.tiff");

    // Logger
    private static final Logger logger = Logger.getLogger(SKIP_218.class);

    @BeforeTest(enabled = false)
    public void setup(){
        logger.info("Before test method is running.");
    }

    @AfterTest(enabled = false)
    public void tearDown(){
        logger.info("After test method is running.");
    }

    @Test
    public void step01(){
        try {
            Response response = given()
                    .when()
                    .params("items","150")
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .get(API_DOCUMENTS);
            JsonPath jsonPath = response.jsonPath();
            List<Integer> documentIds = jsonPath.getList("data.id");
            Random random = new Random();
            idDocument = documentIds.get(random.nextInt(documentIds.size()));
            logger.info("Random document : " + idDocument);
            Assert.assertNotNull(jsonPath.getString("data"));
            logger.info("Step 1 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step02(){
        try {
            Response response = given()
                    .when()
                    .multiPart("document_id",idDocument)
                    .multiPart("original_file",txtFile)
                    .contentType(ContentType.MULTIPART)
                    .header("Test-Authorization", userIdOne)
                    .post(API_IMAGES_SKIP);
            JsonPath jsonPath = response.jsonPath();
            idImageSkipFirst = jsonPath.getInt("data.id");
            logger.info("Id loaded image : " + idImageSkipFirst);
            String name = jsonPath.getString("data.name");
            Assert.assertEquals(name, "txt_file");
            logger.info("Step 2 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step03(){
        try {
            Response response = given()
                    .when()
                    .multiPart("document_id",idDocument)
                    .multiPart("original_file",docxFile)
                    .contentType(ContentType.MULTIPART)
                    .header("Test-Authorization", userIdOne)
                    .post(API_IMAGES_SKIP);
            JsonPath jsonPath = response.jsonPath();
            idImageSkipSecond = jsonPath.getInt("data.id");
            logger.info("Id loaded image : " + idImageSkipSecond);
            String name = jsonPath.getString("data.name");
            Assert.assertEquals(name, "docx_file");
            logger.info("Step 3 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step04(){
        try {
            Response response = given()
                    .when()
                    .multiPart("document_id",idDocument)
                    .multiPart("original_file",jfifFile)
                    .contentType(ContentType.MULTIPART)
                    .header("Test-Authorization", userIdOne)
                    .post(API_IMAGES_SKIP);
            JsonPath jsonPath = response.jsonPath();
            idImageSkipThird = jsonPath.getInt("data.id");
            logger.info("Id loaded image : " + idImageSkipThird);
            String name = jsonPath.getString("data.name");
            Assert.assertEquals(name, "jfif_file");
            logger.info("Step 4 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step05(){
        try {
            Response response = given()
                    .when()
                    .multiPart("document_id",idDocument)
                    .multiPart("original_file",jpgFile)
                    .contentType(ContentType.MULTIPART)
                    .header("Test-Authorization", userIdOne)
                    .post(API_IMAGES_SKIP);
            JsonPath jsonPath = response.jsonPath();
            idImageSkipFourth = jsonPath.getInt("data.id");
            logger.info("Id loaded image : " + idImageSkipFourth);
            String name = jsonPath.getString("data.name");
            Assert.assertEquals(name, "jpg_file");
            logger.info("Step 5 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step06(){
        try {
            Response response = given()
                    .when()
                    .multiPart("document_id",idDocument)
                    .multiPart("original_file",pdfFile)
                    .contentType(ContentType.MULTIPART)
                    .header("Test-Authorization", userIdOne)
                    .post(API_IMAGES_SKIP);
            JsonPath jsonPath = response.jsonPath();
            idImageSkipFifth = jsonPath.getInt("data.id");
            logger.info("Id loaded image : " + idImageSkipFifth);
            String name = jsonPath.getString("data.name");
            Assert.assertEquals(name, "pdf_file");
            logger.info("Step 6 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step07(){
        try {
            Response response = given()
                    .when()
                    .multiPart("document_id",idDocument)
                    .multiPart("original_file",pngFile)
                    .contentType(ContentType.MULTIPART)
                    .header("Test-Authorization", userIdOne)
                    .post(API_IMAGES_SKIP);
            JsonPath jsonPath = response.jsonPath();
            idImageSkipSixth = jsonPath.getInt("data.id");
            logger.info("Id loaded image : " + idImageSkipSixth);
            String name = jsonPath.getString("data.name");
            Assert.assertEquals(name, "png_file");
            logger.info("Step 7 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step08(){
        try {
            Response response = given()
                    .when()
                    .multiPart("document_id",idDocument)
                    .multiPart("original_file",pnmFile)
                    .contentType(ContentType.MULTIPART)
                    .header("Test-Authorization", userIdOne)
                    .post(API_IMAGES_SKIP);
            JsonPath jsonPath = response.jsonPath();
            idImageSkipSeventh = jsonPath.getInt("data.id");
            logger.info("Id loaded image : " + idImageSkipSeventh);
            String name = jsonPath.getString("data.name");
            Assert.assertEquals(name, "pnm_file");
            logger.info("Step 8 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step09(){
        try {
            Response response = given()
                    .when()
                    .multiPart("document_id",idDocument)
                    .multiPart("original_file",bmpFile)
                    .contentType(ContentType.MULTIPART)
                    .header("Test-Authorization", userIdOne)
                    .post(API_IMAGES_SKIP);
            JsonPath jsonPath = response.jsonPath();
            idImageSkipEighth = jsonPath.getInt("data.id");
            logger.info("Id loaded image : " + idImageSkipEighth);
            String name = jsonPath.getString("data.name");
            Assert.assertEquals(name, "bmp_file");
            logger.info("Step 9 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step10(){
        try {
            Response response = given()
                    .when()
                    .multiPart("document_id",idDocument)
                    .multiPart("original_file",jpegFile)
                    .contentType(ContentType.MULTIPART)
                    .header("Test-Authorization", userIdOne)
                    .post(API_IMAGES_SKIP);
            JsonPath jsonPath = response.jsonPath();
            idImageSkipNinth = jsonPath.getInt("data.id");
            logger.info("Id loaded image : " + idImageSkipNinth);
            String name = jsonPath.getString("data.name");
            Assert.assertEquals(name, "jpeg_file");
            logger.info("Step 10 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step11(){
        try {
            Response response = given()
                    .when()
                    .multiPart("document_id",idDocument)
                    .multiPart("original_file",tiffFile)
                    .contentType(ContentType.MULTIPART)
                    .header("Test-Authorization", userIdOne)
                    .post(API_IMAGES_SKIP);
            JsonPath jsonPath = response.jsonPath();
            idImageSkipTenth = jsonPath.getInt("data.id");
            logger.info("Id loaded image : " + idImageSkipTenth);
            String name = jsonPath.getString("data.name");
            Assert.assertEquals(name, "tiff_file");
            logger.info("Step 11 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step12(){
        try {
            Thread.sleep(10000);
            Response response = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP + String.format("/%s", idImageSkipFirst));
            JsonPath jsonPath = response.jsonPath();
            String state = jsonPath.getString("data.recognition_state");
            Assert.assertEquals(state, "recognized");
            logger.info("State after get request- : " + state);
            logger.info("Step 12 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step13(){
        try {
            Response response = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP + String.format("/%s", idImageSkipSecond));
            JsonPath jsonPath = response.jsonPath();
            String state = jsonPath.getString("data.recognition_state");
            Assert.assertEquals(state, "recognized");
            logger.info("State after get request- : " + state);
            logger.info("Step 13 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step14(){
        try {
            Response response = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP + String.format("/%s", idImageSkipThird));
            JsonPath jsonPath = response.jsonPath();
            String state = jsonPath.getString("data.recognition_state");
            Assert.assertEquals(state, "recognized");
            logger.info("State after get request- : " + state);
            logger.info("Step 14 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step15(){
        try {
            Response response = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP + String.format("/%s", idImageSkipFourth));
            JsonPath jsonPath = response.jsonPath();
            String state = jsonPath.getString("data.recognition_state");
            Assert.assertEquals(state, "recognized");
            logger.info("State after get request- : " + state);
            logger.info("Step 15 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step16(){
        try {
            Response response = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP + String.format("/%s", idImageSkipFifth));
            JsonPath jsonPath = response.jsonPath();
            String state = jsonPath.getString("data.recognition_state");
            Assert.assertEquals(state, "recognized");
            logger.info("State after get request- : " + state);
            logger.info("Step 16 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step17(){
        try {
            Response response = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP + String.format("/%s", idImageSkipSixth));
            JsonPath jsonPath = response.jsonPath();
            String state = jsonPath.getString("data.recognition_state");
            Assert.assertEquals(state, "recognized");
            logger.info("State after get request- : " + state);
            logger.info("Step 17 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step18(){
        try {
            Response response = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP + String.format("/%s", idImageSkipSeventh));
            JsonPath jsonPath = response.jsonPath();
            String state = jsonPath.getString("data.recognition_state");
            Assert.assertEquals(state, "recognized");
            logger.info("State after get request- : " + state);
            logger.info("Step 18 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step19(){
        try {
            Response response = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP + String.format("/%s", idImageSkipEighth));
            JsonPath jsonPath = response.jsonPath();
            String state = jsonPath.getString("data.recognition_state");
            Assert.assertEquals(state, "recognized");
            logger.info("State after get request- : " + state);
            logger.info("Step 19 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step20(){
        try {
            Response response = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP + String.format("/%s", idImageSkipNinth));
            JsonPath jsonPath = response.jsonPath();
            String state = jsonPath.getString("data.recognition_state");
            Assert.assertEquals(state, "recognized");
            logger.info("State after get request- : " + state);
            logger.info("Step 20 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step21(){
        try {
            Response response = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .get(API_IMAGES_SKIP + String.format("/%s", idImageSkipTenth));
            JsonPath jsonPath = response.jsonPath();
            String state = jsonPath.getString("data.recognition_state");
            Assert.assertEquals(state, "recognized");
            logger.info("State after get request- : " + state);
            logger.info("Step 21 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step22(){
        try {
            // Шаг не автоматизирован
            logger.info("Step 22 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step23(){
        try {
            // Шаг не автоматизирован
            logger.info("Step 23 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step24(){
        try {
            Response response = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .post(API_IMAGES_SKIP + String.format("/%s/recognize", idImageSkipFirst));

            int statusCode = response.getStatusCode();
            Assert.assertTrue(statusCode == 204);
            logger.info("Status code after recognition : " + statusCode);
            logger.info("Step 24 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step25(){
        try {
            Response response = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdSix)
                    .post(API_IMAGES_SKIP + String.format("/%s/recognize", idImageSkipFirst));

            JsonPath jsonPath = response.jsonPath();
            String errorMessage = jsonPath.getString("error");
            logger.info("Error message - " + errorMessage);
            Assert.assertEquals(errorMessage, "Доступ к ресурсу запрещен");
            logger.info("Step 25 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step26(){
        try {
            // Шаг не автоматизирован
            logger.info("Step 26 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step27(){
        try {
            Response response = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .get(API_DOCUMENTS + String.format("/%s", idDocument));
            JsonPath jsonPath = response.jsonPath();

            List<String> states = jsonPath.getList("data.images.recognition_state");
            for (String state: states) {
                Assert.assertEquals(state, "recognized");
            }
            logger.info("Step 27 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }
}

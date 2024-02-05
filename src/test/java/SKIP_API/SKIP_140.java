package SKIP_API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SKIP_140 {

    String API_DOCUMENTS = "http://api.skip.rtech.ru/v1/documents";

    int idAut1 = 1;

    int idDocuments, idDocuments2;
    int idAssigments;
    int idHeadCoExecutors;
    String officialIdHeadCoExecutors;

    @Test
    public void step01() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_140/SKIP_140_step01.json"))
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        int id = jsonPath.get("data.id");
        idDocuments = id;
        int idA = jsonPath.get("data.assignments[0].id");
        idAssigments = idA;
        int idH = jsonPath.get("data.assignments[0].head_co_executors[0].id");
        idHeadCoExecutors = idH;
        String idO = jsonPath.get("data.assignments[0].head_co_executors[0].co_executor_id");
        officialIdHeadCoExecutors = idO;
        String name = jsonPath.get("data.assignments[0].head_co_executors[0].co_executor_name");
        Boolean provider = jsonPath.get("data.assignments[0].head_co_executors[0].provided");

        Assert.assertTrue(idO.equals("59d4e38e453d000062000001"));
        Assert.assertTrue(name.equals("test1"));
        Assert.assertTrue(provider);
    }

    @Test
    public void step02() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_140/SKIP_140_step02.json"))
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.get("errors.assignments.0.head_co_executors.0.co_executor_id[0]");
        Assert.assertTrue(error.equals("имеет непредусмотренное значение"));

    }

    @Test
    public void step03() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_140/SKIP_140_step03.json"))
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.get("errors.assignments.0.head_co_executors.0.co_executor_name[0]");
        Assert.assertTrue(error.equals("не может быть пустым"));

    }

    @Test
    public void step04() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_140/SKIP_140_step04.json"))
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        int idH = jsonPath.get("data.assignments[0].head_co_executors[0].id");
        String idO = jsonPath.get("data.assignments[0].head_co_executors[0].co_executor_id");
        String name = jsonPath.get("data.assignments[0].head_co_executors[0].co_executor_name");
        Boolean provider = jsonPath.get("data.assignments[0].head_co_executors[0].provided");

        Assert.assertNotNull(idH);
        Assert.assertNull(idO);
        Assert.assertTrue(name.equals("test1"));
        Assert.assertTrue(provider);
    }

    @Test
    public void step05() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_140/SKIP_140_step05.json"))
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.get("errors.assignments.0.head_co_executors.0.co_executor_name[0]");
        Assert.assertTrue(error.equals("не может быть пустым"));

    }

    @Test
    public void step06() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_140/SKIP_140_step06.json"))
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        int idH = jsonPath.get("data.assignments[0].head_co_executors[0].id");
        String idO = jsonPath.get("data.assignments[0].head_co_executors[0].co_executor_id");
        String name = jsonPath.get("data.assignments[0].head_co_executors[0].co_executor_name");
        Boolean provider = jsonPath.get("data.assignments[0].head_co_executors[0].provided");

        Assert.assertNotNull(idH);
        Assert.assertTrue(idO.equals("59d4e38e453d000062000001"));
        Assert.assertTrue(name.equals("test1"));
        Assert.assertTrue(!provider);
    }

    @Test
    public void step07() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_140/SKIP_140_step07.json"))
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        int idH = jsonPath.get("data.assignments[0].head_co_executors[0].id");
        String idO = jsonPath.get("data.assignments[0].head_co_executors[0].co_executor_id");
        String name = jsonPath.get("data.assignments[0].head_co_executors[0].co_executor_name");
        Boolean provider = jsonPath.get("data.assignments[0].head_co_executors[0].provided");

        Assert.assertNotNull(idH);
        Assert.assertTrue(idO.equals("59d4e38e453d000062000001"));
        Assert.assertTrue(name.equals("А"));
        Assert.assertTrue(!provider);
    }

    @Test
    public void step08() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_140/SKIP_140_step08.json"))
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.get("errors.assignments.0.head_co_executors.0.co_executor_id[0]");
        Assert.assertTrue(error.equals("имеет непредусмотренное значение"));

    }

    @Test
    public void step09() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_140/SKIP_140_step09.json"))
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.get("errors.assignments.0.head_co_executors.0.co_executor_id[0]");
        Assert.assertTrue(error.equals("имеет непредусмотренное значение"));

    }

    @Test
    public void step10() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_140/SKIP_140_step10.json"))
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.get("errors.assignments.0.head_co_executors.1.co_executor_id[0]");
        Assert.assertTrue(error.equals("уже существует"));

    }

    @Test
    public void step11() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_140/SKIP_140_step11.json"))
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        int idH = jsonPath.get("data.assignments[0].head_co_executors[0].id");
        String idO = jsonPath.get("data.assignments[0].head_co_executors[0].co_executor_id");
        String name = jsonPath.get("data.assignments[0].head_co_executors[0].co_executor_name");
        Boolean provider = jsonPath.get("data.assignments[0].head_co_executors[0].provided");
        int idH2 = jsonPath.get("data.assignments[0].head_co_executors[1].id");
        String idO2 = jsonPath.get("data.assignments[0].head_co_executors[1].co_executor_id");
        String name2 = jsonPath.get("data.assignments[0].head_co_executors[1].co_executor_name");
        Boolean provider2 = jsonPath.get("data.assignments[0].head_co_executors[1].provided");
        int idH3 = jsonPath.get("data.assignments[0].head_co_executors[2].id");
        String idO3 = jsonPath.get("data.assignments[0].head_co_executors[2].co_executor_id");
        String name3 = jsonPath.get("data.assignments[0].head_co_executors[2].co_executor_name");
        Boolean provider3 = jsonPath.get("data.assignments[0].head_co_executors[2].provided");
        int idDoc = jsonPath.get("data.id");

        idDocuments2 = idDoc;
        Assert.assertNotNull(idH);
        Assert.assertTrue(idO.equals("58b691c20d16000057000001"));
        Assert.assertTrue(name.equals("tesвt1"));
        Assert.assertTrue(!provider);
        Assert.assertNotNull(idH2);
        Assert.assertTrue(idO2.equals("58b7c3a5b84f000006000001"));
        Assert.assertTrue(name2.equals("tesвt1"));
        Assert.assertTrue(!provider2);
        Assert.assertNotNull(idH3);
        Assert.assertTrue(idO3.equals("594b7ea7787d000018000001"));
        Assert.assertTrue(name3.equals("tesвt1"));
        Assert.assertTrue(!provider3);

    }


    @Test
    public void step12() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .get(API_DOCUMENTS + "/" + idDocuments2)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int idH = jsonPath.get("data.assignments[0].head_co_executors[0].id");
        String idO = jsonPath.get("data.assignments[0].head_co_executors[0].co_executor_id");
        String name = jsonPath.get("data.assignments[0].head_co_executors[0].co_executor_name");
        Boolean provider = jsonPath.get("data.assignments[0].head_co_executors[0].provided");
        int idH2 = jsonPath.get("data.assignments[0].head_co_executors[1].id");
        String idO2 = jsonPath.get("data.assignments[0].head_co_executors[1].co_executor_id");
        String name2 = jsonPath.get("data.assignments[0].head_co_executors[1].co_executor_name");
        Boolean provider2 = jsonPath.get("data.assignments[0].head_co_executors[1].provided");
        int idH3 = jsonPath.get("data.assignments[0].head_co_executors[2].id");
        String idO3 = jsonPath.get("data.assignments[0].head_co_executors[2].co_executor_id");
        String name3 = jsonPath.get("data.assignments[0].head_co_executors[2].co_executor_name");
        Boolean provider3 = jsonPath.get("data.assignments[0].head_co_executors[2].provided");
        int idDoc = jsonPath.get("data.id");

        Assert.assertTrue(idDoc == idDocuments2);
        Assert.assertNotNull(idH);
        Assert.assertTrue(idO.equals("58b691c20d16000057000001"));
        Assert.assertTrue(name.equals("tesвt1"));
        Assert.assertTrue(!provider);
        Assert.assertNotNull(idH2);
        Assert.assertTrue(idO2.equals("58b7c3a5b84f000006000001"));
        Assert.assertTrue(name2.equals("tesвt1"));
        Assert.assertTrue(!provider2);
        Assert.assertNotNull(idH3);
        Assert.assertTrue(idO3.equals("594b7ea7787d000018000001"));
        Assert.assertTrue(name3.equals("tesвt1"));
        Assert.assertTrue(!provider3);
        int i = idH;
        int i2 = idH + 1;
        int i3 = idH + 2;
        Assert.assertTrue(i == idH);
        Assert.assertTrue(i2 == idH2);
        Assert.assertTrue(i3 == idH3);
    }

    @Test
    public void step13() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .get(API_DOCUMENTS + "/" + "1234567891011121314")
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.get("error");
        Assert.assertTrue(error.equals("Запись для Documents::Document с id = 1234567891011121314 не найдена"));

    }

    @Test
    public void step14() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_140/SKIP_140_step14.json"))
                .put(API_DOCUMENTS + "/" + idDocuments)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        List<String> head_co_executors = jsonPath.get("data.assignments[1].head_co_executors");

        Assert.assertTrue(head_co_executors.isEmpty());


    }

    @Test
    public void step15() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_140/SKIP_140_step15.json"))
                .put(API_DOCUMENTS + "/" + idDocuments)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.get("errors.assignments.0.head_co_executors.1.co_executor_id[0]");
        Assert.assertTrue(error.equals("уже существует"));

    }

    @Test
    public void step16() {
        String requestBody = "{\n" +
                "    \"skip_reg_date\": \"2024-01-17T06:06:31.485Z\",\n" +
                "    \"control_official_id\": \"54e35f6179fa007422000001\",\n" +
                "    \"control_subject_id\": 83,\n" +
                "    \"document_name\": \"Тест 404\",\n" +
                "    \"document_execution_state_id\": 11,\n" +
                "    \"skip_document_type_id\": 32,\n" +
                "    \"internal\": false,\n" +
                "    \"short_description\": \"404\",\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\":" + idAssigments + ",\n" +
                "\n" +
                "          \"head_co_executors\": [\n" +
                "          {\n" +
                "        \n" +
                "          \"co_executor_id\": \"59d4e38e453d000062000001\",\n" +
                "          \"co_executor_name\": \"test123545465\",\n" +
                "          \"provided\": true\n" +
                "        }\n" +
                "      ]\n" +
                " \n" +
                "        }\n" +
                "    ]\n" +
                "}";

        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestBody)
                .put(API_DOCUMENTS + "/" + idDocuments)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.get("errors.assignments.0.head_co_executors.0.co_executor_id[0]");
        Assert.assertTrue(error.equals("уже существует"));
    }


    @Test
    public void step17() {
        String requestBody = "{\n" +
                "    \"skip_reg_date\": \"2024-01-17T06:06:31.485Z\",\n" +
                "    \"control_official_id\": \"54e35f6179fa007422000001\",\n" +
                "    \"control_subject_id\": 83,\n" +
                "    \"document_name\": \"Тест 404\",\n" +
                "    \"document_execution_state_id\": 11,\n" +
                "    \"skip_document_type_id\": 32,\n" +
                "    \"internal\": false,\n" +
                "    \"short_description\": \"404\",\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\":" + idAssigments + ",\n" +
                "\n" +
                "          \"head_co_executors\": [\n" +
                "          {\n" +
                "        \n" +
                "          \"id\":" + idHeadCoExecutors + ",\n" +
                "          \"co_executor_id\": \"59d4e38e453d000062000001\",\n" +
                "          \"co_executor_name\": \"test2\",\n" +
                "          \"provided\": true\n" +
                "        }\n" +
                "      ]\n" +
                " \n" +
                "        }\n" +
                "    ]\n" +
                "}";

        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestBody)
                .put(API_DOCUMENTS + "/" + idDocuments)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        int idAs = jsonPath.get("data.assignments[0].id");
        int idH = jsonPath.get("data.assignments[0].head_co_executors[0].id");
        String idO = jsonPath.get("data.assignments[0].head_co_executors[0].co_executor_id");
        String name = jsonPath.get("data.assignments[0].head_co_executors[0].co_executor_name");
        Boolean provider = jsonPath.get("data.assignments[0].head_co_executors[0].provided");

        Assert.assertTrue(idAs == idAssigments);
        Assert.assertTrue(idH == idHeadCoExecutors);
        Assert.assertTrue(idO.equals("59d4e38e453d000062000001"));
        Assert.assertTrue(name.equals("test2"));
        Assert.assertTrue(provider);

    }

    @Test
    public void step18() {
        String requestBody = "{\n" +
                "    \"skip_reg_date\": \"2024-01-17T06:06:31.485Z\",\n" +
                "    \"control_official_id\": \"54e35f6179fa007422000001\",\n" +
                "    \"control_subject_id\": 83,\n" +
                "    \"document_name\": \"Тест 404\",\n" +
                "    \"document_execution_state_id\": 11,\n" +
                "    \"skip_document_type_id\": 32,\n" +
                "    \"internal\": false,\n" +
                "    \"short_description\": \"404\",\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\":" + idAssigments + ",\n" +
                "\n" +
                "          \"head_co_executors\": [\n" +
                "          {\n" +
                "        \n" +
                "          \"id\":" + idHeadCoExecutors + ",\n" +
                "                    \"co_executor_id\": \"59d4e38e453d000062000001\",\n" +
                "                    \"co_executor_name\": \"test2\",\n" +
                "                    \"provided\": false,\n" +
                "                    \"deleted\":true\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestBody)
                .put(API_DOCUMENTS + "/" + idDocuments)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        List <String> assigments=jsonPath.get("data.assignments[0].head_co_executors[0].id");

        Assert.assertNull(assigments);
    }

    @Test
    public void step19() {
        String requestBody = "{\n" +
                "    \"skip_reg_date\": \"2024-01-17T06:06:31.485Z\",\n" +
                "    \"control_official_id\": \"54e35f6179fa007422000001\",\n" +
                "    \"control_subject_id\": 83,\n" +
                "    \"document_name\": \"Тест 404\",\n" +
                "    \"document_execution_state_id\": 11,\n" +
                "    \"skip_document_type_id\": 32,\n" +
                "    \"internal\": false,\n" +
                "    \"short_description\": \"404\",\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"id\":" + idAssigments + ",\n" +
                "\n" +
                "          \"head_co_executors\": [\n" +
                "          {\n" +
                "        \n" +
                "          \"co_executor_id\": \"5267c2d637ce000122000001\",\n" +
                "          \"co_executor_name\": \"test2\",\n" +
                "          \"provided\": false\n" +
                "        }\n" +
                "      ]\n" +
                " \n" +
                "        }\n" +
                "    ]\n" +
                "}";

        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestBody)
                .put(API_DOCUMENTS + "/" + idDocuments)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

      String error=jsonPath.get("errors.assignments.0.head_co_executors.0.co_executor_id[0]");
      Assert.assertTrue(error.equals("имеет непредусмотренное значение"));

    }

}
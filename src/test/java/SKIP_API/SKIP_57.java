package SKIP_API;

import com.google.gson.JsonObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SKIP_57 {
    int idAut1 = 1;
    int idAut6 = 6;
    String name,name2,name3,name4,name5,name6;
    int idPeriodicity,idSC,idDES,idAES,idIM,idUrgencies;
    String API_PERIODICITY = "http://api.skip.rtech.ru/v1/classifiers/periodicity";
    String API_SECURITY_CLASSIFICATIONS="http://api.skip.rtech.ru/v1/classifiers/security_classifications";
    String API_DES="http://api.skip.rtech.ru/v1/classifiers/document_execution_states";
    String API_AES="http://api.skip.rtech.ru/v1/classifiers/assignment_execution_states";
    String API_IM="http://api.skip.rtech.ru/v1/classifiers/image_marks";
    String API_URGENCIES="http://api.skip.rtech.ru/v1/classifiers/urgencies";

    @Test
    public void step01() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .get(API_PERIODICITY)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        List<Integer> priorityId = jsonPath.getList("data.priority");
        List<Integer> sort = new ArrayList<>(priorityId);
        Collections.sort(sort);
        int priority = jsonPath.getInt("data[0].priority");
        int priority2 = jsonPath.getInt("data[1].priority");
        int priority3 = jsonPath.getInt("data[2].priority");
        int priority4 = jsonPath.getInt("data[3].priority");
        int priority5 = jsonPath.getInt("data[4].priority");
        int priority6 = jsonPath.getInt("data[5].priority");
        int priority7 = jsonPath.getInt("data[6].priority");
        int priority8 = jsonPath.getInt("data[7].priority");
        int priority9 = jsonPath.getInt("data[8].priority");
        int priority10 = jsonPath.getInt("data[9].priority");
        String n = jsonPath.getString("data[0].name");
        name = n;

        Assert.assertTrue(priority == 1);
        Assert.assertTrue(priority2 == priority + 1);
        Assert.assertTrue(priority3 == priority2 + 1);
        Assert.assertTrue(priority4 == priority3 + 1);
        Assert.assertTrue(priority5 == priority4 + 1);
        Assert.assertTrue(priority6 == priority5 + 1);
        Assert.assertTrue(priority7 == priority6 + 1);
        Assert.assertTrue(priority8 == priority7 + 1);
        Assert.assertTrue(priority9 == priority8 + 1);
        Assert.assertTrue(priority10 == priority9 + 1);
        Assert.assertTrue(priorityId.equals(sort));

    }

    @Test
    public void step02() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .params("name", name)
                .get(API_PERIODICITY)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String n = jsonPath.getString("data[0].name");
        Assert.assertTrue(n.equals(name));
    }

    @Test
    public void step03() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .params("name", "fsdacc")
                .get(API_PERIODICITY)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        ArrayList<String> data = jsonPath.getJsonObject("data");
        Assert.assertTrue(data.isEmpty());
    }

    @Test
    public void step04() {
        JsonObject requestPayload = new JsonObject();
        requestPayload.addProperty("name", "task_257");
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .post(API_PERIODICITY)
                .then().log().all()
                .extract().response();


        JsonPath jsonPath = response.jsonPath();
        int idPer = jsonPath.getInt("data.id");
        idPeriodicity = idPer;
        String name = jsonPath.getString("data.name");
        int priority = jsonPath.getInt("data.priority");
        boolean deleted = jsonPath.getBoolean("data.deleted");
        boolean excluded = jsonPath.getBoolean("data.excluded");

        Assert.assertTrue(name.equals("task_257"));
        Assert.assertTrue(priority == 1);
        Assert.assertTrue(!deleted);
        Assert.assertTrue(excluded);

    }

    @Test
    public void step05() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .delete(API_PERIODICITY + "/" + idPeriodicity)
                .then().log().all()
                .extract().response();


        JsonPath jsonPath = response.jsonPath();
        Assert.assertTrue(response.statusCode() == 204);
    }

    @Test
    public void step06() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .get(API_PERIODICITY)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<String> data = jsonPath.getList("data");
        Assert.assertTrue(!data.contains("task_257"));
    }

    @Test
    public void step07(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut6)
                .get(API_PERIODICITY)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error=jsonPath.getString("error");
        Assert.assertTrue(error.equals("Доступ к ресурсу запрещен"));
    }
    @Test
    public void step08(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .get(API_SECURITY_CLASSIFICATIONS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<Integer> priorityId = jsonPath.getList("data.priority");
        List<Integer> sort = new ArrayList<>(priorityId);
        Collections.sort(sort);
        int priority = jsonPath.getInt("data[0].priority");
        int priority2 = jsonPath.getInt("data[1].priority");
        int priority3 = jsonPath.getInt("data[2].priority");
        int priority4 = jsonPath.getInt("data[3].priority");
        int priority5 = jsonPath.getInt("data[4].priority");
        int priority6 = jsonPath.getInt("data[5].priority");
        int priority7 = jsonPath.getInt("data[6].priority");
        int priority8 = jsonPath.getInt("data[7].priority");
        int priority9 = jsonPath.getInt("data[8].priority");
        int priority10 = jsonPath.getInt("data[9].priority");
        String n = jsonPath.getString("data[0].name");
        name2 = n;

        Assert.assertTrue(priority == 1);
        Assert.assertTrue(priority2 == priority + 1);
        Assert.assertTrue(priority3 == priority2 + 1);
        Assert.assertTrue(priority4 == priority3 + 1);
        Assert.assertTrue(priority5 == priority4 + 1);
        Assert.assertTrue(priority6 == priority5 + 1);
        Assert.assertTrue(priority7 == priority6 + 1);
        Assert.assertTrue(priority8 == priority7 + 1);
        Assert.assertTrue(priority9 == priority8 + 1);
        Assert.assertTrue(priority10 == priority9 + 1);
        Assert.assertTrue(priorityId.equals(sort));
    }

    @Test
    public void step09() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .params("name", name2)
                .get(API_SECURITY_CLASSIFICATIONS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String n = jsonPath.getString("data[0].name");
        Assert.assertTrue(n.equals(name2));
    }

    @Test
    public void step10() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .params("name", "fsdacc")
                .get(API_SECURITY_CLASSIFICATIONS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        ArrayList<String> data = jsonPath.getJsonObject("data");
        Assert.assertTrue(data.isEmpty());
    }

    @Test
    public void step11() {
        JsonObject requestPayload = new JsonObject();
        requestPayload.addProperty("name", "task_257");
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .post(API_SECURITY_CLASSIFICATIONS)
                .then().log().all()
                .extract().response();


        JsonPath jsonPath = response.jsonPath();
        int idScl = jsonPath.getInt("data.id");
        idSC = idScl;
        String name = jsonPath.getString("data.name");
        int priority = jsonPath.getInt("data.priority");
        boolean deleted = jsonPath.getBoolean("data.deleted");
        boolean excluded = jsonPath.getBoolean("data.excluded");

        Assert.assertTrue(name.equals("task_257"));
        Assert.assertTrue(priority == 1);
        Assert.assertTrue(!deleted);
        Assert.assertTrue(excluded);

    }

    @Test
    public void step12() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .delete(API_SECURITY_CLASSIFICATIONS + "/" + idSC)
                .then().log().all()
                .extract().response();


        JsonPath jsonPath = response.jsonPath();
        Assert.assertTrue(response.statusCode() == 204);
    }

    @Test
    public void step13() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .get(API_SECURITY_CLASSIFICATIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<String> data = jsonPath.getList("data");
        Assert.assertTrue(!data.contains("task_257"));
    }

    @Test
    public void step14(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut6)
                .get(API_SECURITY_CLASSIFICATIONS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error=jsonPath.getString("error");
        Assert.assertTrue(error.equals("Доступ к ресурсу запрещен"));
    }
    @Test
    public void step15(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .get(API_DES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<Integer> priorityId = jsonPath.getList("data.priority");
        List<Integer> sort = new ArrayList<>(priorityId);
        Collections.sort(sort);
        int priority = jsonPath.getInt("data[0].priority");
        int priority2 = jsonPath.getInt("data[1].priority");
        int priority3 = jsonPath.getInt("data[2].priority");
        int priority4 = jsonPath.getInt("data[3].priority");
        int priority5 = jsonPath.getInt("data[4].priority");
        int priority6 = jsonPath.getInt("data[5].priority");
        int priority7 = jsonPath.getInt("data[6].priority");
        int priority8 = jsonPath.getInt("data[7].priority");
        int priority9 = jsonPath.getInt("data[8].priority");
        int priority10 = jsonPath.getInt("data[9].priority");
        String n = jsonPath.getString("data[0].name");
        name3 = n;

        Assert.assertTrue(priority == 1);
        Assert.assertTrue(priority2 == priority + 1);
        Assert.assertTrue(priority3 == priority2 + 1);
        Assert.assertTrue(priority4 == priority3 + 1);
        Assert.assertTrue(priority5 == priority4 + 1);
        Assert.assertTrue(priority6 == priority5 + 1);
        Assert.assertTrue(priority7 == priority6 + 1);
        Assert.assertTrue(priority8 == priority7 + 1);
        Assert.assertTrue(priority9 == priority8 + 1);
        Assert.assertTrue(priority10 == priority9 + 1);
        Assert.assertTrue(priorityId.equals(sort));
    }

    @Test
    public void step16() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .params("name", name3)
                .get(API_DES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String n = jsonPath.getString("data[0].name");
        Assert.assertTrue(n.equals(name3));
    }

    @Test
    public void step17() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .params("name", "fsdacc")
                .get(API_DES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        ArrayList<String> data = jsonPath.getJsonObject("data");
        Assert.assertTrue(data.isEmpty());
    }

    @Test
    public void step18() {
        JsonObject requestPayload = new JsonObject();
        requestPayload.addProperty("name", "task_257");
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .post(API_DES)
                .then().log().all()
                .extract().response();


        JsonPath jsonPath = response.jsonPath();
        int idD = jsonPath.getInt("data.id");
        idDES = idD;
        String name = jsonPath.getString("data.name");
        int priority = jsonPath.getInt("data.priority");
        boolean deleted = jsonPath.getBoolean("data.deleted");
        boolean excluded = jsonPath.getBoolean("data.excluded");

        Assert.assertTrue(name.equals("task_257"));
        Assert.assertTrue(priority == 1);
        Assert.assertTrue(!deleted);
        Assert.assertTrue(excluded);

    }

    @Test
    public void step19() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .delete(API_DES + "/" + idDES)
                .then().log().all()
                .extract().response();


        JsonPath jsonPath = response.jsonPath();
        Assert.assertTrue(response.statusCode() == 204);
    }

    @Test
    public void step20() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .get(API_DES)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<String> data = jsonPath.getList("data");
        Assert.assertTrue(!data.contains("task_257"));
    }

    @Test
    public void step21(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut6)
                .get(API_DES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error=jsonPath.getString("error");
        Assert.assertTrue(error.equals("Доступ к ресурсу запрещен"));
    }
    @Test
    public void step22(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .get(API_AES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<Integer> priorityId = jsonPath.getList("data.priority");
        List<Integer> sort = new ArrayList<>(priorityId);
        Collections.sort(sort);
        int priority = jsonPath.getInt("data[0].priority");
        int priority2 = jsonPath.getInt("data[1].priority");
        int priority3 = jsonPath.getInt("data[2].priority");
        int priority4 = jsonPath.getInt("data[3].priority");
        int priority5 = jsonPath.getInt("data[4].priority");
        int priority6 = jsonPath.getInt("data[5].priority");
        int priority7 = jsonPath.getInt("data[6].priority");
        int priority8 = jsonPath.getInt("data[7].priority");
        int priority9 = jsonPath.getInt("data[8].priority");
        int priority10 = jsonPath.getInt("data[9].priority");
        String n = jsonPath.getString("data[0].name");
        name4 = n;

        Assert.assertTrue(priority == 1);
        Assert.assertTrue(priority2 == priority + 1);
        Assert.assertTrue(priority3 == priority2 + 1);
        Assert.assertTrue(priority4 == priority3 + 1);
        Assert.assertTrue(priority5 == priority4 + 1);
        Assert.assertTrue(priority6 == priority5 + 1);
        Assert.assertTrue(priority7 == priority6 + 1);
        Assert.assertTrue(priority8 == priority7 + 1);
        Assert.assertTrue(priority9 == priority8 + 1);
        Assert.assertTrue(priority10 == priority9 + 1);
        Assert.assertTrue(priorityId.equals(sort));
    }

    @Test
    public void step23() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .params("name", name4)
                .get(API_AES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String n = jsonPath.getString("data[0].name");
        Assert.assertTrue(n.equals(name4));
    }

    @Test
    public void step24() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .params("name", "fsdacc")
                .get(API_AES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        ArrayList<String> data = jsonPath.getJsonObject("data");
        Assert.assertTrue(data.isEmpty());
    }

    @Test
    public void step25() {
        JsonObject requestPayload = new JsonObject();
        requestPayload.addProperty("name", "task_257");
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .post(API_AES)
                .then().log().all()
                .extract().response();


        JsonPath jsonPath = response.jsonPath();
        int idD = jsonPath.getInt("data.id");
        idAES = idD;
        String name = jsonPath.getString("data.name");
        int priority = jsonPath.getInt("data.priority");
        boolean deleted = jsonPath.getBoolean("data.deleted");
        boolean excluded = jsonPath.getBoolean("data.excluded");

        Assert.assertTrue(name.equals("task_257"));
        Assert.assertTrue(priority == 1);
        Assert.assertTrue(!deleted);
        Assert.assertTrue(excluded);

    }

    @Test
    public void step26() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .delete(API_AES + "/" + idAES)
                .then().log().all()
                .extract().response();


        JsonPath jsonPath = response.jsonPath();
        Assert.assertTrue(response.statusCode() == 204);
    }

    @Test
    public void step27() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .get(API_AES)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<String> data = jsonPath.getList("data");
        Assert.assertTrue(!data.contains("task_257"));
    }

    @Test
    public void step28(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut6)
                .get(API_AES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error=jsonPath.getString("error");
        Assert.assertTrue(error.equals("Доступ к ресурсу запрещен"));
    }
    @Test
    public void step29(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .get(API_IM)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<Integer> priorityId = jsonPath.getList("data.priority");
        List<Integer> sort = new ArrayList<>(priorityId);
        Collections.sort(sort);
        int priority = jsonPath.getInt("data[0].priority");
        int priority2 = jsonPath.getInt("data[1].priority");
        int priority3 = jsonPath.getInt("data[2].priority");
        int priority4 = jsonPath.getInt("data[3].priority");
        int priority5 = jsonPath.getInt("data[4].priority");
        int priority6 = jsonPath.getInt("data[5].priority");
        int priority7 = jsonPath.getInt("data[6].priority");
        int priority8 = jsonPath.getInt("data[7].priority");
        int priority9 = jsonPath.getInt("data[8].priority");
        int priority10 = jsonPath.getInt("data[9].priority");
        String n = jsonPath.getString("data[0].name");
        name5 = n;

        Assert.assertTrue(priority == 1);
        Assert.assertTrue(priority2 == priority + 1);
        Assert.assertTrue(priority3 == priority2 + 1);
        Assert.assertTrue(priority4 == priority3 + 1);
        Assert.assertTrue(priority5 == priority4 + 1);
        Assert.assertTrue(priority6 == priority5 + 1);
        Assert.assertTrue(priority7 == priority6 + 1);
        Assert.assertTrue(priority8 == priority7 + 1);
        Assert.assertTrue(priority9 == priority8 + 1);
        Assert.assertTrue(priority10 == priority9 + 1);
        Assert.assertTrue(priorityId.equals(sort));
    }

    @Test
    public void step30() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .params("name", name5)
                .get(API_IM)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String n = jsonPath.getString("data[0].name");
        Assert.assertTrue(n.equals(name5));
    }

    @Test
    public void step31() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .params("name", "fsdacc")
                .get(API_IM)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        ArrayList<String> data = jsonPath.getJsonObject("data");
        Assert.assertTrue(data.isEmpty());
    }

    @Test
    public void step32() {
        JsonObject requestPayload = new JsonObject();
        requestPayload.addProperty("name", "task_257");
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .post(API_IM)
                .then().log().all()
                .extract().response();


        JsonPath jsonPath = response.jsonPath();
        int idD = jsonPath.getInt("data.id");
        idIM = idD;
        String name = jsonPath.getString("data.name");
        int priority = jsonPath.getInt("data.priority");
        boolean deleted = jsonPath.getBoolean("data.deleted");
        boolean excluded = jsonPath.getBoolean("data.excluded");

        Assert.assertTrue(name.equals("task_257"));
        Assert.assertTrue(priority == 1);
        Assert.assertTrue(!deleted);
        Assert.assertTrue(excluded);

    }

    @Test
    public void step33() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .delete(API_IM + "/" + idIM)
                .then().log().all()
                .extract().response();


        JsonPath jsonPath = response.jsonPath();
        Assert.assertTrue(response.statusCode() == 204);
    }

    @Test
    public void step34() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .get(API_IM)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<String> data = jsonPath.getList("data");
        Assert.assertTrue(!data.contains("task_257"));
    }

    @Test
    public void step35(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut6)
                .get(API_IM)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error=jsonPath.getString("error");
        Assert.assertTrue(error.equals("Доступ к ресурсу запрещен"));
    }
    @Test
    public void step36(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .get(API_URGENCIES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<Integer> priorityId = jsonPath.getList("data.priority");
        List<Integer> sort = new ArrayList<>(priorityId);
        Collections.sort(sort);
        int priority = jsonPath.getInt("data[0].priority");
        int priority2 = jsonPath.getInt("data[1].priority");
        int priority3 = jsonPath.getInt("data[2].priority");
        int priority4 = jsonPath.getInt("data[3].priority");
        int priority5 = jsonPath.getInt("data[4].priority");
        int priority6 = jsonPath.getInt("data[5].priority");
        int priority7 = jsonPath.getInt("data[6].priority");
        int priority8 = jsonPath.getInt("data[7].priority");
        int priority9 = jsonPath.getInt("data[8].priority");
        int priority10 = jsonPath.getInt("data[9].priority");
        String n = jsonPath.getString("data[0].name");
        name6 = n;

        Assert.assertTrue(priority == 1);
        Assert.assertTrue(priority2 == priority + 1);
        Assert.assertTrue(priority3 == priority2 + 1);
        Assert.assertTrue(priority4 == priority3 + 1);
        Assert.assertTrue(priority5 == priority4 + 1);
        Assert.assertTrue(priority6 == priority5 + 1);
        Assert.assertTrue(priority7 == priority6 + 1);
        Assert.assertTrue(priority8 == priority7 + 1);
        Assert.assertTrue(priority9 == priority8 + 1);
        Assert.assertTrue(priority10 == priority9 + 1);
        Assert.assertTrue(priorityId.equals(sort));
    }

    @Test
    public void step37() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .params("name", name6)
                .get(API_URGENCIES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String n = jsonPath.getString("data[0].name");
        Assert.assertTrue(n.equals(name6));
    }

    @Test
    public void step38() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .params("name", "fsdacc")
                .get(API_URGENCIES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        ArrayList<String> data = jsonPath.getJsonObject("data");
        Assert.assertTrue(data.isEmpty());
    }

    @Test
    public void step39() {
        JsonObject requestPayload = new JsonObject();
        requestPayload.addProperty("name", "task_257");
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(requestPayload.toString())
                .post(API_URGENCIES)
                .then().log().all()
                .extract().response();


        JsonPath jsonPath = response.jsonPath();
        int idD = jsonPath.getInt("data.id");
        idUrgencies = idD;
        String name = jsonPath.getString("data.name");
        int priority = jsonPath.getInt("data.priority");
        boolean deleted = jsonPath.getBoolean("data.deleted");
        boolean excluded = jsonPath.getBoolean("data.excluded");

        Assert.assertTrue(name.equals("task_257"));
        Assert.assertTrue(priority == 1);
        Assert.assertTrue(!deleted);
        Assert.assertTrue(excluded);

    }

    @Test
    public void step40() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .delete(API_URGENCIES + "/" + idUrgencies)
                .then().log().all()
                .extract().response();


        JsonPath jsonPath = response.jsonPath();
        Assert.assertTrue(response.statusCode() == 204);
    }

    @Test
    public void step41() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .get(API_URGENCIES)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<String> data = jsonPath.getList("data");
        Assert.assertTrue(!data.contains("task_257"));
    }

    @Test
    public void step42(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut6)
                .get(API_URGENCIES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error=jsonPath.getString("error");
        Assert.assertTrue(error.equals("Доступ к ресурсу запрещен"));
    }
}


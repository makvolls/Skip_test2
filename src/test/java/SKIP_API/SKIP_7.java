package SKIP_API;

import API.BasicRoles;
import API.BasicUser;
import Utils.RandomGenerateText;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class SKIP_7 {
    int idAut1 = 1;
    int idAut2 = 2;
    String test1 = RandomGenerateText.generateUniqeTextRussianString(7);
    String test2 = RandomGenerateText.generateUniqeTextRussianString(7);
    String test3 = RandomGenerateText.generateUniqeTextRussianString(7);
    String test4 = RandomGenerateText.generateUniqeTextRussianString(7);
    int id1, id2, id3, id4;//id1,id2-глобальные; id3-не глобальная текущего провайдера; id4-не глобальная другого провайдера;
    int idUser;

    @AfterClass
    public void deleteUser() {
        JsonObject object1 = new JsonObject();
        JsonArray array = new JsonArray();
        array.add(5);
        object1.add("role_ids", array);
        object1.addProperty("control_subject_id", (String) null);

        Response response2 = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(object1.toString())
                .put(BasicUser.API_USER + "/" + idUser)
                .then().log().all()
                .extract().response();
        BasicUser.deleteUser(idAut1, idUser);
        BasicRoles.deleteRole(idAut1, id1);
        BasicRoles.deleteRole(idAut1, id2);
        BasicRoles.deleteRole(idAut1, id3);
        BasicRoles.deleteRole(idAut2, id4);
    }

    @BeforeTest
    public void createRoles1() {
        JsonObject object = new JsonObject();

        object.addProperty("name", test1);
        object.addProperty("desc", "ASC");
        object.addProperty("global", true);

        JsonArray rights_ids = new JsonArray();
        rights_ids.add("index_users");
        object.add("rights_ids", rights_ids);
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(object.toString())
                .post(BasicRoles.API_ROLES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.getInt("data.id");
        id1 = id;
    }

    @BeforeTest
    public void createRoles2() {
        JsonObject object = new JsonObject();

        object.addProperty("name", test2);
        object.addProperty("desc", "ASC");
        object.addProperty("global", true);

        JsonArray rights_ids = new JsonArray();
        rights_ids.add("index_users");
        object.add("rights_ids", rights_ids);
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(object.toString())
                .post(BasicRoles.API_ROLES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.getInt("data.id");
        id2 = id;
    }

    @BeforeTest
    public void createRoles3() {
        JsonObject object = new JsonObject();

        object.addProperty("name", test3);
        object.addProperty("desc", "ASC");
        object.addProperty("global", false);

        JsonArray rights_ids = new JsonArray();
        rights_ids.add("index_users");
        object.add("rights_ids", rights_ids);
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(object.toString())
                .post(BasicRoles.API_ROLES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.getInt("data.id");
        id3 = id;
    }

    @BeforeTest
    public void createRoles4() {
        JsonObject object = new JsonObject();

        object.addProperty("name", test4);
        object.addProperty("desc", "ASC");
        object.addProperty("global", false);

        JsonArray rights_ids = new JsonArray();
        rights_ids.add("index_users");
        object.add("rights_ids", rights_ids);
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut2)
                .body(object.toString())
                .post(BasicRoles.API_ROLES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.getInt("data.id");
        id4 = id;
    }

    @Test
    public void Step01() {
        JsonObject object = new JsonObject();
        object.addProperty("official_id", "528f027454c8000041000001");
        JsonArray array = new JsonArray();
        array.add(id1);
        object.add("role_ids", array);

        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(object.toString())
                .post(BasicUser.API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.getInt("data.id");
        String official_id = jsonPath.getString("data.official_id");
        String provider_id = jsonPath.getString("data.provider_id");
        String full_name = jsonPath.get("data.full_name");
        String position = jsonPath.getString("data.position");
        String organization_short_title = jsonPath.get("data.organization_short_title");
        String provider_name = jsonPath.getString("data.provider_name");
        int role_id = jsonPath.getInt("data.roles[0].id");
        idUser = id;

        Assert.assertTrue(official_id.equals("528f027454c8000041000001"));
        Assert.assertTrue(provider_id.equals("525e9f767da3000002000001"));
        Assert.assertTrue(full_name.equals("Столярова Юлия Валентиновна"));
        Assert.assertTrue(position.equals("Заместитель начальника"));
        Assert.assertTrue(organization_short_title.equals("ОДиР ГУВО МВД России"));
        Assert.assertTrue(provider_name.equals("Департамент делопроизводства и работы с обращениями граждан и " +
                "организаций Министерства внутренних дел Российской Федерации (ДДО МВД России)"));
        Assert.assertTrue(role_id == id1);


    }

    @Test
    public void Step02() {
        JsonObject object = new JsonObject();
        object.addProperty("official_id", "528f027454c8000041000001");
        JsonArray array = new JsonArray();
        array.add(id1);
        object.add("role_ids", array);

        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(object.toString())
                .post(BasicUser.API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<String> error = jsonPath.get("errors.official_id");
        Assert.assertTrue(error.contains("уже существует"));
    }

    @Test
    public void Step03() {
        JsonObject object = new JsonObject();
        object.addProperty("official_id", "528f027454c800004100000");
        JsonArray array = new JsonArray();
        array.add(id1);
        object.add("role_ids", array);

        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(object.toString())
                .post(BasicUser.API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<String> error = jsonPath.get("errors.official_id");
        Assert.assertTrue(error.contains("имеет непредусмотренное значение"));
    }

    @Test
    public void Step04() {
        String object = "{\n" +
                "  \"official_id\": \"528f027454c8000041000001\",\n" +
                "  \"role_ids\": []\n" +
                "}";

        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(object)
                .post(BasicUser.API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<String> error = jsonPath.get("errors.role_ids");
        Assert.assertTrue(error.contains("не может быть пустым"));
    }

    @Test
    public void Step05() {
        String object = "{\n" +
                "  \"official_id\": \"\",\n" +
                "  \"role_ids\": []\n" +
                "}";

        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(object.toString())
                .post(BasicUser.API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.get("errors.official_id[0]");
        Assert.assertTrue(error.contains("не может быть пустым"));
        String error2 = jsonPath.get("errors.role_ids[0]");
        Assert.assertTrue(error2.contains("не может быть пустым"));
    }

    @Test
    public void Step06() {
        Response response = given()
                .header("Test-Authorization", idAut1)
                .delete(BasicUser.API_USER + "/" + idUser)
                .then().log().all()
                .extract().response();
        int SC = response.statusCode();
        Assert.assertTrue(SC == 204);
    }

    @Test
    public void Step07() {
        JsonObject object = new JsonObject();
        object.addProperty("official_id", "528f027454c8000041000001");
        JsonArray array = new JsonArray();
        array.add(id4);
        object.add("role_ids", array);

        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(object.toString())
                .post(BasicUser.API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<String> error = jsonPath.get("errors.role_ids");
        Assert.assertTrue(error.contains("имеет непредусмотренное значение: " + id4));
    }

    @Test
    public void Step08() {
        JsonObject object = new JsonObject();
        object.addProperty("official_id", "528f027454c8000041000001");
        JsonArray array = new JsonArray();
        array.add(id1);
        object.add("role_ids", array);

        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(object.toString())
                .post(BasicUser.API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.getInt("data.id");
        String official_id = jsonPath.getString("data.official_id");
        String provider_id = jsonPath.getString("data.provider_id");
        String full_name = jsonPath.get("data.full_name");
        String position = jsonPath.getString("data.position");
        String organization_short_title = jsonPath.get("data.organization_short_title");
        String provider_name = jsonPath.getString("data.provider_name");
        int role_id = jsonPath.getInt("data.roles[0].id");
        idUser = id;

        Assert.assertTrue(official_id.equals("528f027454c8000041000001"));
        Assert.assertTrue(provider_id.equals("525e9f767da3000002000001"));
        Assert.assertTrue(full_name.equals("Столярова Юлия Валентиновна"));
        Assert.assertTrue(position.equals("Заместитель начальника"));
        Assert.assertTrue(organization_short_title.equals("ОДиР ГУВО МВД России"));
        Assert.assertTrue(provider_name.equals("Департамент делопроизводства и работы с обращениями граждан и " +
                "организаций Министерства внутренних дел Российской Федерации (ДДО МВД России)"));
        Assert.assertTrue(role_id == id1);


    }

    @Test
    public void Step09() {
        JsonObject object = new JsonObject();
        JsonArray array = new JsonArray();
        array.add(id2);
        object.add("role_ids", array);

        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(object.toString())
                .put(BasicUser.API_USER + "/" + idUser)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String official_id = jsonPath.getString("data.official_id");
        String provider_id = jsonPath.getString("data.provider_id");
        String full_name = jsonPath.get("data.full_name");
        String position = jsonPath.getString("data.position");
        String organization_short_title = jsonPath.get("data.organization_short_title");
        String provider_name = jsonPath.getString("data.provider_name");
        int role_id = jsonPath.getInt("data.roles[0].id");

        Assert.assertTrue(official_id.equals("528f027454c8000041000001"));
        Assert.assertTrue(provider_id.equals("525e9f767da3000002000001"));
        Assert.assertTrue(full_name.equals("Столярова Юлия Валентиновна"));
        Assert.assertTrue(position.equals("Заместитель начальника"));
        Assert.assertTrue(organization_short_title.equals("ОДиР ГУВО МВД России"));
        Assert.assertTrue(provider_name.equals("Департамент делопроизводства и работы с обращениями граждан и " +
                "организаций Министерства внутренних дел Российской Федерации (ДДО МВД России)"));
        Assert.assertTrue(role_id == id2);
    }

    @Test
    public void Step10() {
        JsonObject object = new JsonObject();
        object.addProperty("official_id", "528f027454c8000041000001");
        JsonArray array = new JsonArray();
        array.add(id4);
        object.add("role_ids", array);

        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(object.toString())
                .put(BasicUser.API_USER + "/" + idUser)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<String> error = jsonPath.get("errors.role_ids");
        Assert.assertTrue(error.contains("имеет непредусмотренное значение: " + id4));
    }

    @Test
    public void Step11() {
        JsonObject object = new JsonObject();
        JsonArray array = new JsonArray();
        array.add(id3);
        object.add("role_ids", array);

        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(object.toString())
                .put(BasicUser.API_USER + "/" + idUser)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String official_id = jsonPath.getString("data.official_id");
        String provider_id = jsonPath.getString("data.provider_id");
        String full_name = jsonPath.get("data.full_name");
        String position = jsonPath.getString("data.position");
        String organization_short_title = jsonPath.get("data.organization_short_title");
        String provider_name = jsonPath.getString("data.provider_name");
        int role_id = jsonPath.getInt("data.roles[0].id");

        Assert.assertTrue(official_id.equals("528f027454c8000041000001"));
        Assert.assertTrue(provider_id.equals("525e9f767da3000002000001"));
        Assert.assertTrue(full_name.equals("Столярова Юлия Валентиновна"));
        Assert.assertTrue(position.equals("Заместитель начальника"));
        Assert.assertTrue(organization_short_title.equals("ОДиР ГУВО МВД России"));
        Assert.assertTrue(provider_name.equals("Департамент делопроизводства и работы с обращениями граждан и " +
                "организаций Министерства внутренних дел Российской Федерации (ДДО МВД России)"));
        Assert.assertTrue(role_id == id3);
    }

    @Test
    public void Step12() {
        String object = "{\n" +
                "        \"role_ids\": []\n" +
                "        }";

        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(object.toString())
                .put(BasicUser.API_USER + "/" + idUser)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<String> error = jsonPath.get("errors.role_ids");
        Assert.assertTrue(error.contains("не может быть пустым"));
    }

    @Test
    public void Step13() {
        JsonObject object = new JsonObject();
        JsonArray array = new JsonArray();
        array.add(id3);
        object.add("role_ids", array);

        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(object.toString())
                .put(BasicUser.API_USER + "/" + idAut2)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("error");
        Assert.assertTrue(error.equals("Доступ к ресурсу запрещен"));
    }
}


//
//    @Test
//    public void Step08() {
//        int[] id = {search1.getData().id};
//        Map<String, Object> user = new HashMap<>();
//        user.put("official_id", "528f027454c8000041000001");
//        user.put("role_ids", id);
//        Response response = given()
//                .header("Content-Type", "application/json")
//                .header("Test-Authorization", 1)
//                .body(user)
//                .log().all()
//                .post(BasicUser.API_USER)
//                .then().log().all()
//                .extract().response();
//        JsonPath jsonPath = response.jsonPath();
//        List<Integer> ids = jsonPath.get("data.roles.id");
//        List<String> full_name = jsonPath.get("data.roles.full_name");
//
//        Assert.assertTrue(ids.contains(search1.data.id));
//        Assert.assertTrue(full_name.contains(search1.data.full_name));
//
//    }
//
//    @Test
//    public void Step09() {
//        int[] id = {7};
//        Map<String, Object> user = new HashMap<>();
//        user.put("role_ids", id);
//        Response response = given()
//                .header("Content-Type", "application/json")
//                .header("Test-Authorization", 1)
//                .body(user)
//                .log().all()
//                .put(BasicUser.API_USER + "/7")
//                .then().log().all()
//                .extract().response();
//        JsonPath jsonPath = response.jsonPath();
//        List<Integer> ids = jsonPath.get("data.roles.id");
//        List<String> full_name = jsonPath.get("data.roles.full_name");
//
//        Assert.assertTrue(ids.contains(7));
//        Assert.assertTrue(full_name.contains("тест33 (ДДО МВД России)"));
//
//    }
//
//    @Test
//    public void Step10() {
//        int[] id = {31};
//        Map<String, Object> user = new HashMap<>();
//        user.put("role_ids", id);
//        Response response = given()
//                .header("Content-Type", "application/json")
//                .header("Test-Authorization", 1)
//                .body(user)
//                .log().all()
//                .put(BasicUser.API_USER + "/7")
//                .then().log().all()
//                .extract().response();
//        JsonPath jsonPath = response.jsonPath();
//        List<String> errorRole_id = jsonPath.get("errors.role_ids");
//
//        Assert.assertTrue(errorRole_id.contains("имеет непредусмотренное значение: 31"));
//    }
//
//    @Test
//    public void Step11() {
//        int[] id = {29};
//        Map<String, Object> user = new HashMap<>();
//        user.put("role_ids", id);
//        Response response = given()
//                .header("Content-Type", "application/json")
//                .header("Test-Authorization", 1)
//                .body(user)
//                .log().all()
//                .put(BasicUser.API_USER + "/7")
//                .then().log().all()
//                .extract().response();
//        JsonPath jsonPath = response.jsonPath();
//        List<Integer> ids = jsonPath.get("data.roles.id");
//        List<String> full_name = jsonPath.get("data.roles.full_name");
//
//        Assert.assertTrue(ids.contains(29));
//        Assert.assertTrue(full_name.contains("Все права"));
//    }
//
//    @Test
//    public void Step12() {
//        int[] id = {};
//        Map<String, Object> user = new HashMap<>();
//        user.put("role_ids", id);
//        Response response = given()
//                .header("Content-Type", "application/json")
//                .header("Test-Authorization", 1)
//                .body(user)
//                .log().all()
//                .put(BasicUser.API_USER + "/7")
//                .then().log().all()
//                .extract().response();
//        JsonPath jsonPath = response.jsonPath();
//        List<String> errorRole_id = jsonPath.get("errors.role_ids");
//
//        Assert.assertTrue(errorRole_id.contains("не может быть пустым"));
//    }
//
//    @Test
//    public void Step13() {
//        int[] id = {};
//        Map<String, Object> user = new HashMap<>();
//        user.put("role_ids", id);
//        Response response = given()
//                .header("Content-Type", "application/json")
//                .header("Test-Authorization", 1)
//                .body(user)
//                .log().all()
//                .put(BasicUser.API_USER + "/3")
//                .then().log().all()
//                .extract().response();
//        JsonPath jsonPath = response.jsonPath();
//        String error = jsonPath.get("error");
//
//        Assert.assertTrue(error.contains("Доступ к ресурсу запрещен"));
//    }
//
//
//}

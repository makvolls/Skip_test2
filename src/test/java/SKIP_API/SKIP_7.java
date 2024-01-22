package SKIP_API;

import API.BasicRoles;
import API.BasicUser;
import API.DTO.ErrorsDTO.RootError;
import API.DTO.RolesDto.RootResponseRolesDto;
import API.DTO.UserDto.RootResponseUserDto;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SKIP_7 {

    RootResponseRolesDto createRolesGlobal, createRoles;
    RootResponseUserDto createUser;
    RootResponseRolesDto search1;
    RootResponseRolesDto search2;

    String global = "forTest7Global (ОДиР ГУВО МВД России)";
    String notGlobal = "forTest7";

    @AfterClass
    public void deleteUser() {
        BasicUser.deleteUser(1, 7);
    }
    @BeforeTest
    public void createRoles() {
        search1 = BasicRoles.getRole(3, 275);
        if (!search1.getData().full_name.equals(global)) {
            createRolesGlobal = BasicRoles.createRoles(3, "forTest7Global", true, "index_users");
        }
        search2 = BasicRoles.getRole(1, 273);
        if (!search2.getData().full_name.equals(notGlobal)) {
            createRolesGlobal = BasicRoles.createRoles(1, "forTest7", false, "index_users");
        }

    }
    @Test
    public void Step01() {
        int[] id = {search1.getData().id};
        createUser = BasicUser.createUser(1, "528f027454c8000041000001", id);//
        Assert.assertTrue(createUser.data.official_id.equals("528f027454c8000041000001"));
        Assert.assertTrue(createUser.data.provider_id.equals("525e9f767da3000002000001"));
        Assert.assertTrue(createUser.data.full_name.equals("Столярова Юлия Валентиновна"));
        Assert.assertTrue(createUser.data.position.equals("Заместитель начальника"));
        Assert.assertTrue(createUser.data.organization_short_title.equals("ОДиР ГУВО МВД России"));
        Assert.assertTrue(createUser.data.provider_name.equals("Департамент делопроизводства и работы с обращениями " +
                "граждан и организаций Министерства внутренних дел Российской Федерации (ДДО МВД России)"));
        Assert.assertTrue(createUser.data.roles[0].id == search1.data.id);//createRolesGlobal.data.id
        Assert.assertTrue(createUser.data.roles[0].full_name.equals(search1.data.full_name));
    }

    @Test
    public void Step02() {
        int[] id = {search1.getData().id};
        Map<String, Object> user = new HashMap<>();
        user.put("official_id", "528f027454c8000041000001");
        user.put("role_ids", id);
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(user)
                .log().all()
                .post(BasicUser.API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<String> error = jsonPath.get("errors.official_id");
        Assert.assertTrue(error.contains("уже существует"));

    }
    @Test
    public void Step03() {
        int[] id = {search1.getData().id};
        Map<String, Object> user = new HashMap<>();
        user.put("official_id", "528f027454c800004100000");
        user.put("role_ids", id);
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(user)
                .log().all()
                .post(BasicUser.API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<String> error = jsonPath.get("errors.official_id");
        Assert.assertTrue(error.contains("не может отсутствовать"));
    }
    @Test
    public void Step04() {
        Map<String, Object> user = new HashMap<>();
        user.put("official_id", "528f027454c8000041000001");
        user.put("role_ids", "");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(user)
                .log().all()
                .post(BasicUser.API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<String> error = jsonPath.get("errors.role_ids");
        Assert.assertTrue(error.contains("не может быть пустым"));
    }
    @Test
    public void Step05() {
        Map<String, Object> user = new HashMap<>();
        user.put("official_id", "");
        user.put("role_ids", "");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(user)
                .log().all()
                .post(BasicUser.API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<String> errorOfficial_id = jsonPath.get("errors.official_id");
        List<String> errorRole_id = jsonPath.get("errors.role_ids");

        Assert.assertTrue(errorOfficial_id.contains("не может быть пустым"));
        Assert.assertTrue(errorRole_id.contains("не может быть пустым"));
    }
    @Test
    public void Step06() {
        BasicUser.deleteUser(1, 7);


    }

    @Test
    public void Step07() {
        int[] id = {31};
        Map<String, Object> user = new HashMap<>();
        user.put("official_id", "528f027454c8000041000001");
        user.put("role_ids", id);
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(user)
                .log().all()
                .post(BasicUser.API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<String> errorRole_id = jsonPath.get("errors.role_ids");

        Assert.assertTrue(errorRole_id.contains("имеет непредусмотренное значение: 31"));
    }
    @Test
    public void Step08() {
        int[] id = {search1.getData().id};
        Map<String, Object> user = new HashMap<>();
        user.put("official_id", "528f027454c8000041000001");
        user.put("role_ids", id);
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(user)
                .log().all()
                .post(BasicUser.API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<Integer> ids = jsonPath.get("data.roles.id");
        List<String> full_name = jsonPath.get("data.roles.full_name");

        Assert.assertTrue(ids.contains(search1.data.id));
        Assert.assertTrue(full_name.contains(search1.data.full_name));

    }
    @Test
    public void Step09() {
        int[] id = {7};
        Map<String, Object> user = new HashMap<>();
        user.put("role_ids", id);
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(user)
                .log().all()
                .put(BasicUser.API_USER + "/7")
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<Integer> ids = jsonPath.get("data.roles.id");
        List<String> full_name = jsonPath.get("data.roles.full_name");

        Assert.assertTrue(ids.contains(7));
        Assert.assertTrue(full_name.contains("тест33 (ДДО МВД России)"));

    }
    @Test
    public void Step10() {
        int[] id = {31};
        Map<String, Object> user = new HashMap<>();
        user.put("role_ids", id);
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(user)
                .log().all()
                .put(BasicUser.API_USER + "/7")
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<String> errorRole_id = jsonPath.get("errors.role_ids");

        Assert.assertTrue(errorRole_id.contains("имеет непредусмотренное значение: 31"));
    }
    @Test
    public void Step11() {
        int[] id = {29};
        Map<String, Object> user = new HashMap<>();
        user.put("role_ids", id);
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(user)
                .log().all()
                .put(BasicUser.API_USER + "/7")
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<Integer> ids = jsonPath.get("data.roles.id");
        List<String> full_name = jsonPath.get("data.roles.full_name");

        Assert.assertTrue(ids.contains(29));
        Assert.assertTrue(full_name.contains("Все права"));
    }
    @Test
    public void Step12() {
        int[] id = {};
        Map<String, Object> user = new HashMap<>();
        user.put("role_ids", id);
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(user)
                .log().all()
                .put(BasicUser.API_USER + "/7")
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<String> errorRole_id = jsonPath.get("errors.role_ids");

        Assert.assertTrue(errorRole_id.contains("не может быть пустым"));
    }
    @Test
    public void Step13() {
        int[] id = {};
        Map<String, Object> user = new HashMap<>();
        user.put("role_ids", id);
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(user)
                .log().all()
                .put(BasicUser.API_USER + "/3")
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.get("error");

        Assert.assertTrue(error.contains("Доступ к ресурсу запрещен"));
    }


}

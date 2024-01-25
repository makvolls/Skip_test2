package SKIP_API;

import API.BasicSecurity;
import API.DTO.SecurityClassificationsDto.RootPostSecurityDto;
import autotest.core.util.Assistant;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SKIP_37 {
    RootPostSecurityDto create, create1;
    String name = Assistant.generateUniqeTextRussianString(4);
    String short_name = Assistant.generateUniqeTextRussianString(5);
    String name1 = Assistant.generateUniqeTextRussianString(4);
    String short_name1 = Assistant.generateUniqeTextRussianString(5);
    String upName = Assistant.generateUniqeTextRussianString(9);


    @AfterClass
    public void upSecurity() {
        Map<String, Object> security = new HashMap<>();
        security.put("name", upName);
        security.put("short_name", "");
        security.put("excluded", false);
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(security)
                .log().all()
                .put(BasicSecurity.API_SECURITY_CLASSIFICATIONS + "/" + create.data.id)
                .then().log().all()
                .extract().response();

    }


    @Test
    public void Step01() {
        create = BasicSecurity.postSecurity(1, name, short_name, true);
        Assert.assertTrue(create.data.name.equals(name));
        Assert.assertTrue(create.data.short_name.equals(short_name));
        Assert.assertTrue(create.data.priority == 1);
        Assert.assertTrue(create.data.excluded);
        Assert.assertTrue(!create.data.deleted);
    }

    @Test
    public void Step02() {
        Map<String, Object> security = new HashMap<>();
        security.put("name", "q");
        security.put("short_name", "q");
        security.put("excluded", false);
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(security)
                .log().all()
                .put(BasicSecurity.API_SECURITY_CLASSIFICATIONS + "/" + create.data.id)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String upName = jsonPath.get("data.name");
        String upShort_name = jsonPath.get("data.short_name");
        boolean upExcluded = jsonPath.get("data.excluded");

        Assert.assertTrue(upName.contains("q"));
        Assert.assertTrue(upShort_name.contains("q"));
        Assert.assertTrue(!upExcluded);

    }

    @Test
    public void Step03() {
        Map<String, Object> security = new HashMap<>();
        security.put("name", "");
        security.put("short_name", "");
        security.put("excluded", false);
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(security)
                .log().all()
                .put(BasicSecurity.API_SECURITY_CLASSIFICATIONS + "/" + create.data.id)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<String> error = jsonPath.get("errors.name");

        Assert.assertTrue(error.contains("не может быть пустым"));
    }

    @Test
    public void Step04() {
        Map<String, Object> security = new HashMap<>();
        security.put("name", "^");
        security.put("short_name", "");
        security.put("excluded", false);
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(security)
                .log().all()
                .put(BasicSecurity.API_SECURITY_CLASSIFICATIONS + "/" + create.data.id)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String upName = jsonPath.get("data.name");
        String upShort_name = jsonPath.get("data.short_name");
        boolean upExcluded = jsonPath.get("data.excluded");

        Assert.assertTrue(upName.contains("^"));
        Assert.assertTrue(upShort_name.contains(""));
        Assert.assertTrue(!upExcluded);
    }

    @Test
    public void Step05() {
        create1 = BasicSecurity.postSecurity(1, name1, short_name1, true);
        Assert.assertTrue(create1.data.name.equals(name1));
        Assert.assertTrue(create1.data.short_name.equals(short_name1));
        Assert.assertTrue(create1.data.priority == 1);
        Assert.assertTrue(create1.data.excluded);
        Assert.assertTrue(!create1.data.deleted);
    }

    @Test
    public void Step06() {
        Map<String, Object> security = new HashMap<>();
        security.put("name", name1);
        security.put("short_name", short_name1);
        security.put("excluded", true);
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(security)
                .log().all()
                .put(BasicSecurity.API_SECURITY_CLASSIFICATIONS + "/" + create.data.id)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<String> nameError = jsonPath.get("errors.name");
        List<String> short_nameError = jsonPath.get("errors.name");

        Assert.assertTrue(nameError.contains("уже существует"));
        Assert.assertTrue(short_nameError.contains("уже существует"));

    }

    @Test
    public void Step07() {
        Map<String, Object> security = new HashMap<>();
        security.put("name", name1);
        security.put("short_name", short_name1);
        security.put("excluded", true);
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 6)
                .body(security)
                .log().all()
                .put(BasicSecurity.API_SECURITY_CLASSIFICATIONS + "/" + create.data.id)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.get("error");

        Assert.assertTrue(error.contains("Доступ к ресурсу запрещен"));
    }

}

package SKIP_API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SKIP_32 {
    public static String API_DEADLINE_BASES = "http://api.skip.rtech.ru/v1/classifiers/deadline_bases/durations";

    @Test
    public void Step01() {
        Response response = given()
                .when()
                .header("Test-Authorization", 1)
                .params("name", "деп")
                .get(API_DEADLINE_BASES)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<String> id = jsonPath.get("data.id");
        List<String> name = jsonPath.get("data.name");

        Assert.assertTrue(id.get(0).contains("3_days"));
        Assert.assertTrue(id.get(1).contains("10_days"));
        Assert.assertTrue(id.get(2).contains("30_days"));
        Assert.assertTrue(id.get(3).contains("1_month"));
        Assert.assertTrue(name.get(0).contains("3 дня"));
        Assert.assertTrue(name.get(1).contains("10 дней"));
        Assert.assertTrue(name.get(2).contains("30 дней"));
        Assert.assertTrue(name.get(3).contains("1 месяц"));

    }

    @Test
    public void Step02() {
        Response response = given()
                .when()
                .header("Test-Authorization", 6)
                .params("name", "деп")
                .get(API_DEADLINE_BASES)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.get("error");

        Assert.assertTrue(error.equals("Доступ к ресурсу запрещен"));
    }
}

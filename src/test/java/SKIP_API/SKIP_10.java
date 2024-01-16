package SKIP_API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class SKIP_10 {
    public static String API_ORG = "http://api.skip.rtech.ru/v1/oshs/mvd/organizations";

    @Test
    public void Step01() {
        Response response = given()
                .when()
                .header("Test-Authorization", 1)
                .get(API_ORG)
                .then().log().all()
                .body("data", notNullValue())
                .body("data.id", notNullValue())
                .body("data.full_title", notNullValue())
                .body("data.short_title", notNullValue())
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<String> data = jsonPath.get("data");

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            result.add(i);
            Assert.assertTrue(result.size() != 0);
        }


    }

    @Test
    public void Step02() {
        Response response = given()
                .when()
                .header("Test-Authorization", 1)
                .params("title", "гу")
                .get(API_ORG)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<String> full_title = jsonPath.get("data.full_title");
        List<String> short_title = jsonPath.get("data.short_title");

        List<Integer> st = new ArrayList<>();
        List<Integer> ft = new ArrayList<>();

        for (int i = 0; i < full_title.size(); i++) {
            if (!full_title.get(i).contains("гу") && !full_title.get(i).contains("ГУ"))
                st.add(i);
            else ft.add(i);
        }

        for (int i = 0; i < st.size(); i++) {
            Assert.assertTrue(short_title.get(st.get(i)).contains("гу") || short_title.get(st.get(i)).contains("ГУ"));

        }
        for (int i = 0; i < ft.size(); i++) {
            Assert.assertTrue(full_title.get(ft.get(i)).contains("гу") || full_title.get(ft.get(i)).contains("ГУ"));
        }
        Assert.assertTrue(st.size() + ft.size() == 10);

    }

    @Test
    public void Step03() {
        Response response = given()
                .when()
                .header("Test-Authorization", 1)
                .params("title", "ддо")
                .params("items", "100")
                .get(API_ORG)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        List<String> full_title = jsonPath.get("data.full_title");
        List<String> short_title = jsonPath.get("data.short_title");

        List<Integer> st = new ArrayList<>();
        List<Integer> ft = new ArrayList<>();

        for (int i = 0; i < full_title.size(); i++) {
            if (!full_title.get(i).contains("ддо") && !full_title.get(i).contains("ДДО"))
                st.add(i);
            else ft.add(i);
        }

        for (int i = 0; i < st.size(); i++) {
            Assert.assertTrue(short_title.get(st.get(i)).contains("ддо") || short_title.get(st.get(i)).contains("ДДО"));

        }
        for (int i = 0; i < ft.size(); i++) {
            Assert.assertTrue(full_title.get(ft.get(i)).contains("ддо") || full_title.get(ft.get(i)).contains("ДДО"));
        }
    }

    @Test
    public void Step04() {
        Response response = given()
                .when()
                .header("Test-Authorization", 1)
                .params("title", "гккккккккккккк")
                .get(API_ORG)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<String> results = jsonPath.get("data");
        Assert.assertTrue(results.size() == 0);
    }


}

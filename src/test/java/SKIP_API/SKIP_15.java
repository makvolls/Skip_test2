package SKIP_API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class SKIP_15 {

    public static String API_OSHS_MVD_OFFICIALS = "http://api.skip.rtech.ru/v1/oshs/mvd/officials";

    @Test
    public void step01() {
        Response response = given()
                .when()
                .header("Test-Authorization", 3)
                .get(API_OSHS_MVD_OFFICIALS)
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
    public void step02() {
        Response response = given()
                .when()
                .header("Test-Authorization", 3)
                .params("last_name", "кол")
                .get(API_OSHS_MVD_OFFICIALS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<String> last_name = jsonPath.get("data.last_name");

        List<Integer> ln = new ArrayList<>();

        for (int i = 0; i < last_name.size(); i++) {
            if (!last_name.get(i).contains("кол") && !last_name.get(i).equals("Кол"))
                ln.add(i);
        }

        for (int i = 0; i < ln.size(); i++) {
            Assert.assertTrue(last_name.get(ln.get(i)).contains("кол") || last_name.get(ln.get(i)).contains("Кол"));
        }

    }
        @Test
        public void step03 () {
            Response response = given()
                    .when()
                    .header("Test-Authorization", 3)
                    .params("last_name", "с")
                    .get(API_OSHS_MVD_OFFICIALS)
                    .then().log().all()
                    .extract().response();

            JsonPath jsonPath = response.jsonPath();
            List<String> last_name = jsonPath.get("data.last_name");

            List<Integer> ln = new ArrayList<>();

            for (int i = 0; i < last_name.size(); i++) {
                if (!last_name.get(i).contains("с") && !last_name.get(i).equals("С"))
                    ln.add(i);
            }

            for (int i = 0; i < ln.size(); i++) {
                Assert.assertTrue(last_name.get(ln.get(i)).contains("с") || last_name.get(ln.get(i)).contains("С"));
            }

        }


    @Test
    public void step04 () {
        Response response = given()
                .when()
                .header("Test-Authorization", 3)
                .params("last_name", "c")
                .get(API_OSHS_MVD_OFFICIALS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<String> data = jsonPath.get("data");

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            result.add(i);
            Assert.assertTrue(data.size() == 0);
        }
    }
    @Test
    public void step05() {
        Response response = given()
                .when()
                .header("Test-Authorization", 3)
                .params("last_name", "колокольцев")
                .get(API_OSHS_MVD_OFFICIALS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<String> last_name = jsonPath.get("data.last_name");

        List<Integer> ln = new ArrayList<>();

        for (int i = 0; i < last_name.size(); i++) {
            if (!last_name.get(i).contains("колокольцев") && !last_name.get(i).equals("Колокольцев"))
                ln.add(i);
        }

        for (int i = 0; i < ln.size(); i++) {
            Assert.assertTrue(last_name.get(ln.get(i)).contains("колокольцев") || last_name.get(ln.get(i)).contains("Колокольцев"));
        }

    }

    @Test
    public void step06() {
        Response response = given()
                .when()
                .header("Test-Authorization", 3)
                .params("last_name", "СТОЛЯРОВА")
                .get(API_OSHS_MVD_OFFICIALS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<String> last_name = jsonPath.get("data.last_name");

        List<Integer> ln = new ArrayList<>();

        for (int i = 0; i < last_name.size(); i++) {
            if (!last_name.get(i).contains("столярова") && !last_name.get(i).equals("Столярова"))
                ln.add(i);
        }

        for (int i = 0; i < ln.size(); i++) {
            Assert.assertTrue(last_name.get(ln.get(i)).contains("столярова") || last_name.get(ln.get(i)).contains("Столярова"));
        }
    }

        @Test
        public void step07 () {
            Response response = given()
                    .when()
                    .header("Test-Authorization", 3)
                    .params("last_name", "пыварпва")
                    .get(API_OSHS_MVD_OFFICIALS)
                    .then().log().all()
                    .extract().response();

            JsonPath jsonPath = response.jsonPath();
            List<String> data = jsonPath.get("data");

            List<Integer> result = new ArrayList<>();

            for (int i = 0; i < data.size(); i++) {
                result.add(i);
                Assert.assertTrue(data.size() == 0);
            }
        }


    }

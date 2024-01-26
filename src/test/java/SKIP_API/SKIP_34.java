package SKIP_API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class SKIP_34 {
    public static String API_CLASSIFIERS = "http://api.skip.rtech.ru/v1/classifiers";

    @Test
    public void Step01() {
        Response response = given()
                .when()
                .header("Test-Authorization", 1)
                .get(API_CLASSIFIERS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<String> name = jsonPath.get("data.name");
        List<String> type = jsonPath.get("data.type");
        List<String> path = jsonPath.get("data.path");


        Assert.assertTrue(type.get(0).contains("local"));
        Assert.assertTrue(type.get(1).contains("global"));
        Assert.assertTrue(type.get(2).contains("global"));
        Assert.assertTrue(type.get(3).contains("global"));
        Assert.assertTrue(type.get(4).contains("global"));
        Assert.assertTrue(type.get(5).contains("global"));
        Assert.assertTrue(type.get(6).contains("global"));
        Assert.assertTrue(type.get(7).contains("global"));
        Assert.assertTrue(type.get(8).contains("global"));
        Assert.assertTrue(type.get(9).contains("global"));
        Assert.assertTrue(type.get(10).contains("global"));
        Assert.assertTrue(type.get(11).contains("local"));
        Assert.assertTrue(name.get(0).contains("Вид документа"));
        Assert.assertTrue(name.get(1).contains("Гриф"));
        Assert.assertTrue(name.get(2).contains("Основание срока исполнения поручения"));
        Assert.assertTrue(name.get(3).contains("Отметки на электронных образах"));
        Assert.assertTrue(name.get(4).contains("Периодичность"));
        Assert.assertTrue(name.get(5).contains("Событие"));
        Assert.assertTrue(name.get(6).contains("Состояние исполнения документа"));
        Assert.assertTrue(name.get(7).contains("Состояние исполнения поручения (мероприятия)"));
        Assert.assertTrue(name.get(8).contains("Состояние контрольного срока"));
        Assert.assertTrue(name.get(9).contains("Состояние события"));
        Assert.assertTrue(name.get(10).contains("Срочность"));
        Assert.assertTrue(name.get(11).contains("Тематика"));
        Assert.assertTrue(path.get(0).contains("/v1/classifiers/document_types"));
        Assert.assertTrue(path.get(1).contains("/v1/classifiers/security_classifications"));
        Assert.assertTrue(path.get(2).contains("/v1/classifiers/deadline_bases"));
        Assert.assertTrue(path.get(3).contains("/v1/classifiers/image_marks"));
        Assert.assertTrue(path.get(4).contains("/v1/classifiers/periodicity"));
        Assert.assertTrue(path.get(5).contains("/v1/classifiers/events"));
        Assert.assertTrue(path.get(6).contains("/v1/classifiers/document_execution_states"));
        Assert.assertTrue(path.get(7).contains("/v1/classifiers/assignment_execution_states"));
        Assert.assertTrue(path.get(8).contains("/v1/classifiers/control_period_states"));
        Assert.assertTrue(path.get(9).contains("/v1/classifiers/event_states"));
        Assert.assertTrue(path.get(10).contains("/v1/classifiers/urgencies"));
        Assert.assertTrue(path.get(11).contains("/v1/classifiers/themes"));

    }

    @Test
    public void Step02() {
        Response response = given()
                .when()
                .header("Test-Authorization", 6)
                .params("name", "деп")
                .get(API_CLASSIFIERS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.get("error");

        Assert.assertTrue(error.equals("Доступ к ресурсу запрещен"));
    }
}

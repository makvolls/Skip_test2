package SKIP_API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SKIP_202 {

    String API_ROWS = "http://api.skip.rtech.ru/v1/constructor/rows";
    String API_SEPARATORS = "http://api.skip.rtech.ru/v1/constructor/separators";
    int idAut1 = 1;

    @Test
    public void step01() {
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .when()
                .get(API_ROWS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.getInt("data[0].id");
        String name = jsonPath.getString("data[0].name");
        String comment = jsonPath.getString("data[0].comment");
        int id1 = jsonPath.getInt("data[1].id");
        String name1 = jsonPath.getString("data[1].name");
        String comment1 = jsonPath.getString("data[1].comment");
        int id2 = jsonPath.getInt("data[2].id");
        String name2 = jsonPath.getString("data[2].name");
        String comment2 = jsonPath.getString("data[2].comment");
        int id3 = jsonPath.getInt("data[3].id");
        String name3 = jsonPath.getString("data[3].name");
        String comment3 = jsonPath.getString("data[3].comment");

        Assert.assertTrue(id == 1);
        Assert.assertTrue(name.equals("documents"));
        Assert.assertTrue(comment.equals("Документы"));
        Assert.assertTrue(id1 == 2);
        Assert.assertTrue(name1.equals("assignments"));
        Assert.assertTrue(comment1.equals("Поручения"));
        Assert.assertTrue(id2 == 3);
        Assert.assertTrue(name2.equals("deadlines"));
        Assert.assertTrue(comment2.equals("Сроки"));
        Assert.assertTrue(id3 == 4);
        Assert.assertTrue(name3.equals("events"));
        Assert.assertTrue(comment3.equals("События"));
    }

    @Test
    public void step02() {
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .when()
                .get(API_SEPARATORS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        int id = jsonPath.getInt("data[0].id");
        String name = jsonPath.getString("data[0].name");
        String comment = jsonPath.getString("data[0].comment");
        int id1 = jsonPath.getInt("data[1].id");
        String name1 = jsonPath.getString("data[1].name");
        String comment1 = jsonPath.getString("data[1].comment");
        int id2 = jsonPath.getInt("data[2].id");
        String name2 = jsonPath.getString("data[2].name");
        String comment2 = jsonPath.getString("data[2].comment");
        int id3 = jsonPath.getInt("data[3].id");
        String name3 = jsonPath.getString("data[3].name");
        String comment3 = jsonPath.getString("data[3].comment");
        int id4 = jsonPath.getInt("data[4].id");
        String name4 = jsonPath.getString("data[4].name");
        String comment4 = jsonPath.getString("data[4].comment");
        int id5 = jsonPath.getInt("data[5].id");
        String name5 = jsonPath.getString("data[5].name");
        String comment5 = jsonPath.getString("data[5].comment");
        int id6 = jsonPath.getInt("data[6].id");
        String name6 = jsonPath.getString("data[6].name");
        String comment6 = jsonPath.getString("data[6].comment");
        int id7 = jsonPath.getInt("data[7].id");
        String name7 = jsonPath.getString("data[7].name");
        String comment7 = jsonPath.getString("data[7].comment");
        int id8 = jsonPath.getInt("data[8].id");
        String name8 = jsonPath.getString("data[8].name");
        String comment8 = jsonPath.getString("data[8].comment");

        Assert.assertTrue(id == 1);
        Assert.assertTrue(name.equals(","));
        Assert.assertTrue(comment.equals("Запятая"));
        Assert.assertTrue(id1 == 2);
        Assert.assertTrue(name1.equals(";"));
        Assert.assertTrue(comment1.equals("Точка с запятой"));
        Assert.assertTrue(id2 == 3);
        Assert.assertTrue(name2.equals("/"));
        Assert.assertTrue(comment2.equals("Косая черта"));
        Assert.assertTrue(id3 == 4);
        Assert.assertTrue(name3.equals("\\"));
        Assert.assertTrue(comment3.equals("Обратная косая черта"));
        Assert.assertTrue(id4 == 5);
        Assert.assertTrue(name4.equals("."));
        Assert.assertTrue(comment4.equals("Точка"));
        Assert.assertTrue(id5 == 6);
        Assert.assertTrue(name5.equals("!"));
        Assert.assertTrue(comment5.equals("Восклицательный знак"));
        Assert.assertTrue(id6 == 7);
        Assert.assertTrue(name6.equals("_"));
        Assert.assertTrue(comment6.equals("Нижнее подчеркивание"));
        Assert.assertTrue(id7 == 8);
        Assert.assertTrue(name7.equals("|"));
        Assert.assertTrue(comment7.equals("Вертикальная черта"));
        Assert.assertTrue(id8 == 9);
        Assert.assertTrue(name8.equals("№"));
        Assert.assertTrue(comment8.equals("Знак номера"));

    }
}
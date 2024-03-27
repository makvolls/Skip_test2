package SKIP_API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SKIP_237 {
    int idAut1=1;
    String API_CONSTRUCTOR_TABLES="http://api.skip.rtech.ru/v1/constructor/tables";
    int idCT;
    String nameCT, created_atCT, updated_atCT;
    boolean display_on_mainwindowCT;

    @Test
    public void step01(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .get(API_CONSTRUCTOR_TABLES)
                .then().log().all()
                .extract().response();


        JsonPath jsonPath = response.jsonPath();
        String data=jsonPath.getString("data");
        int id=jsonPath.getInt("data[0].id");
        String name=jsonPath.getString("data[0].name");
        String created_at=jsonPath.getString("data[0].created_at");
        String updated_at=jsonPath.getString("data[0].updated_at");
        boolean display_on_mainwindow=jsonPath.getBoolean("data[0].display_on_mainwindow");

        idCT=id;
        nameCT=name;
        created_atCT=created_at;
        updated_atCT=updated_at;
        display_on_mainwindowCT=display_on_mainwindow;

        Assert.assertNotNull(data);
    }

    @Test
    public void step02(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .get(API_CONSTRUCTOR_TABLES+"/"+idCT)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        int id=jsonPath.getInt("data.id");
        String name=jsonPath.getString("data.name");
        String created_at=jsonPath.getString("data.created_at");
        String updated_at=jsonPath.getString("data.updated_at");
        boolean display_on_mainwindow=jsonPath.getBoolean("data.display_on_mainwindow");

        Assert.assertTrue(id==idCT);
        Assert.assertTrue(name.equals(nameCT));
        Assert.assertTrue(created_at.equals(created_atCT));
        Assert.assertTrue(updated_at.equals(updated_at));
        Assert.assertTrue(display_on_mainwindow==display_on_mainwindowCT);
    }
    @Test
    public void step03(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .get(API_CONSTRUCTOR_TABLES+"/"+"13432632431535")
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error=jsonPath.getString("error");
        Assert.assertTrue(error.equals("Запись для Constructor::Tables::Table с id = 13432632431535 не найдена"));
    }

}

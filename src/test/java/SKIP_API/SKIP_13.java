package SKIP_API;

import API.BasicProviders;
import API.DTO.ProvidersDTO.RootSearchProvider;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class SKIP_13 {
    public static String API_PROVIDERS = "http://api.skip.rtech.ru/v1/permissions/providers/";
    RootSearchProvider updProvider;
    String idProv = "525e9f767da3000002000001";
    String notValidIdProv = "525e9f767da300000200000";
    int idAut1=1;
    int idAut3=3;

    @Test
    public void Step01() {
        updProvider = BasicProviders.updateProvider(idAut3, idProv, true);
        Assert.assertTrue(updProvider.getData().training);

    }

    @Test
    public void Step02() {
        updProvider = BasicProviders.updateProvider(idAut3, idProv, false);
        Assert.assertTrue(!updProvider.getData().training);
    }

    @Test
    public void Step03() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .put(API_PROVIDERS + notValidIdProv)
                .then().log().all()
                .statusCode(404)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.get("error");
        Assert.assertTrue(error.contains("Запись для Permissions::Provider с id = 525e9f767da300000200000 не найдена"));
    }

    @Test
    public void Step04() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .put(API_PROVIDERS + idProv)
                .then().log().all()
                .statusCode(422)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<String> error = jsonPath.get("errors.training");
        Assert.assertTrue(error.contains("не может быть пустым"));
    }


}

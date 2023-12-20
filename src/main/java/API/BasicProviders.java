package API;

import API.DTO.ProvidersDTO.RootPermissions;

import static io.restassured.RestAssured.given;

public class BasicProviders {
    public static String API_PROVIDERS= "http://api.skip.rtech.ru/v1/permissions/providers";

    /**
     * Получение списка провайдеров
     * @param id - авторизуемого пользователя
     * **//*

    public static RootPermissions getProviders(int id) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .get(API_PROVIDERS).then()
                .log().all()
                .statusCode(200)
                .extract();
        RootPermissions actualProviders = response.body().as(RootPermissions.class);
        return actualProviders;
    }*/

    /**
     * Получение списка провайдеров
     * @param id - авторизуемого пользователя
     * **/
    public static RootPermissions getProviders(int id){
        RootPermissions actualProviders = BasicApi.get(API_PROVIDERS, id).as(RootPermissions.class);
        return actualProviders;
    }
}

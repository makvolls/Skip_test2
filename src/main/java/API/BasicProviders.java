package API;

import API.DTO.ErrorsDTO.RootError;
import API.DTO.ProvidersDTO.RootPermissions;
import API.DTO.ProvidersDTO.RootSearchProvider;
import API.DTO.ProvidersDTO.RootUpProvider;

import static io.restassured.RestAssured.given;

public class BasicProviders {
    public static String API_PROVIDERS = "http://api.skip.rtech.ru/v1/permissions/providers";

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
     *
     * @param id - авторизуемого пользователя
     **/
    public static RootPermissions getProviders(int id) {
        RootPermissions actualProviders = BasicApi.get(API_PROVIDERS, id).as(RootPermissions.class);
        return actualProviders;
    }
    public static RootSearchProvider getProvider(int id, String idProvider){
        RootSearchProvider actual = BasicApi.get(API_PROVIDERS + "/" + idProvider, id).as(RootSearchProvider.class);
        return actual;
    }

    public static RootError getProvidersNoAccess(int id) {
        RootError error = BasicApi.getError(API_PROVIDERS, id).as(RootError.class);
        return error;


    }

    public static RootError getProviderNoAccess(int id, String idProvider) {
        RootError error = BasicApi.getError(API_PROVIDERS+ "/" + idProvider, id).as(RootError.class);
        return error;
    }
    public static RootSearchProvider updateProvider(int id, String idProvider, boolean training){
        RootUpProvider value = new RootUpProvider(training);
        RootSearchProvider actual = BasicApi.put(API_PROVIDERS+ "/" + idProvider, id, value)
                .as(RootSearchProvider.class);
        return actual;
    }
    public static RootError updateProviderNoAccess(int id, String idProvider, boolean training){
        RootUpProvider value = new RootUpProvider(training);
        RootError error = BasicApi.putErrorsNoRights(API_PROVIDERS+ "/" + idProvider, id, value).as(RootError.class);
        return error;
    }

    public static RootPermissions getProvidersName(int id,String name) {

        RootPermissions actualProviders = BasicApi.get(API_PROVIDERS, id).as(RootPermissions.class);
        return actualProviders;
    }

}

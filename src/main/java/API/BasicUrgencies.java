package API;

import API.DTO.ErrorsDTO.RootErrorNoRights;
import API.DTO.ErrorsDTO.RootNameError;
import API.DTO.UrgenciesDTO.CreateValueClass;
import API.DTO.UrgenciesDTO.CreateValueUrgenciesOnlyName;
import API.DTO.UrgenciesDTO.RootElementUrgencies;
import API.DTO.UrgenciesDTO.RootUrgencies;

import static io.restassured.RestAssured.given;

public class BasicUrgencies {

    public static String API_URGENCIES = "http://api.skip.rtech.ru/v1/classifiers/urgencies";

 /*   *//**
     * Получение значений справочника
     *
     * @param id - id авторизуемого пользователя
     **//*

    public RootUrgencies getUrgenciesList(int id) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .get(API_URGENCIES).then()
                .log().all()
                .statusCode(200)
                .extract();
        RootUrgencies actualUrgencies = response.body().as(RootUrgencies.class);
        return actualUrgencies;
    }

    *//**
     * Создание значение справочника
     *
     * @param id       - id авторизованного пользователя
     * @param name     - Название справочника
     * @param excluded - элемент исключения значения из справочника
     *//*

    public RootElementUrgencies createUrgencies(int id, String name, boolean excluded) {
        CreateValueClass value = new CreateValueClass(name, excluded);
        value.setName(name);
        value.setExcluded(excluded);
        ExtractableResponse<Response> response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", id)
                .body(value)
                .post(API_URGENCIES).then()
                .log().all()
                .statusCode(201)
                .extract();

        RootElementUrgencies createdValue = response.body().as(RootElementUrgencies.class);
        return createdValue;
    }

    *//**
     * Изменение значения справочника
     *
     * @param id       - id авторизованного пользователя
     * @param idValue  - id значения , в котором изменяется значение
     * @param name     - Название справочника
     * @param excluded - элемент исключения значения из справочника
     **//*

    public RootElementUrgencies updateUrgencies(int id, int idValue, String name, boolean excluded) {
        CreateValueClass value = new CreateValueClass(name, excluded);
        value.setName(name);
        value.setExcluded(excluded);
        ExtractableResponse<Response> response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", id)
                .body(value)
                .put(API_URGENCIES + "/" + idValue).then()
                .log().all()
                .statusCode(200)
                .extract();

        RootElementUrgencies newValue = response.body().as(RootElementUrgencies.class);
        return newValue;
    }

    *//**
     * Удаление значения справочника
     *
     * @param id      - авторизованного пользователя
     * @param idValue - id значения , которое будет удалено
     **//*

    public void deleteUrgencies(int id, int idValue) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .delete(API_URGENCIES + "/" + idValue).then()
                .log().all()
                .statusCode(204)
                .extract();
    }*/

    /**
     * Получение значений справочника
     *
     * @param id - id авторизуемого пользователя
     **/

    public static RootUrgencies getUrgenciesList(int id) {
        RootUrgencies actualUrgencies = BasicApi.get(API_URGENCIES, id).as(RootUrgencies.class);
        return actualUrgencies;
    }

    /**
     * Создание значение справочника
     *
     * @param id       - id авторизованного пользователя
     * @param name     - Название справочника
     * @param excluded - элемент исключения значения из справочника
     */

    public static RootElementUrgencies createUrgencies(int id, String name, boolean excluded) {
        CreateValueClass value = new CreateValueClass(name, excluded);
        value.setName(name);
        value.setExcluded(excluded);

        RootElementUrgencies createdValue = BasicApi.post(API_URGENCIES, id, value).as(RootElementUrgencies.class);
        return createdValue;


    }

    /**
     * Изменение значения справочника
     *
     * @param id       - id авторизованного пользователя
     * @param idValue  - id значения , в котором изменяется значение
     * @param name     - Название справочника
     * @param excluded - элемент исключения значения из справочника
     **/

    public RootElementUrgencies updateUrgencies(int id, int idValue, String name, boolean excluded) {
        CreateValueClass value = new CreateValueClass(name, excluded);
        value.setName(name);
        value.setExcluded(excluded);
        RootElementUrgencies newValue = BasicApi.put(API_URGENCIES + "/" + idValue, id, value).as(RootElementUrgencies.class);
        return newValue;


    }
    public static void deleteUrgencies(int id, int idValue){
        BasicApi.delete(API_URGENCIES+ "/" + idValue, id);
        return;
    }
    public static RootNameError createErrNameUrgencies(int id, String name, boolean excluded) {
        CreateValueClass value = new CreateValueClass(name, excluded);
        value.setName(name);
        value.setExcluded(excluded);

        RootNameError createdValue = BasicApi.postError(API_URGENCIES, id, value).as(RootNameError.class);
        return createdValue;


    }

    public static RootErrorNoRights createErrNoRightsUrgencies(int id, String name, boolean excluded) {
        CreateValueClass value = new CreateValueClass(name, excluded);
        value.setName(name);
        value.setExcluded(excluded);

        RootErrorNoRights createdValue = BasicApi.postErrorNoRights(API_URGENCIES, id, value).as(RootErrorNoRights.class);
        return createdValue;


    }

    public static RootElementUrgencies createUrgenciesOnlyName(int id, String name) {
        CreateValueUrgenciesOnlyName value = new CreateValueUrgenciesOnlyName(name);
        value.setName(name);

        RootElementUrgencies createdValue = BasicApi.post(API_URGENCIES, id, value).as(RootElementUrgencies.class);
        return createdValue;


    }

}

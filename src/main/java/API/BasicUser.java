package API;

import API.DTO.ErrorsDTO.RootError;
import API.DTO.UserDto.*;

import static io.restassured.RestAssured.given;

public class BasicUser {
    public static String API_USER = "http://api.skip.rtech.ru/v1/permissions/users";

    /**
     * Получение пользователя по id
     *
     * @param idAut - id авторизованного пользователя
     * @param id    - id получаемого пользователя
     *//*
    public RootUserWithId getUser(int idAut, int id) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", idAut)
                .get(API_USER + "/" + id).then()
                .log().all()
                .statusCode(200)
                .extract();
        RootUserWithId actualUser = response.body().as(RootUserWithId.class);

        return actualUser;
    }

    *//**
     * Получение пользователей
     *
     * @param idAut - id авторизованного пользователя
     *//*

    public RootUserDto getUsers(int idAut) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", idAut)
                .get(API_USER).then()
                .log().all()
                .statusCode(200)
                .extract();
        RootUserDto actualUser = response.body().as(RootUserDto.class);

        return actualUser;

    }
    *//**
     * Создание пользователя
     *
     * @param id - id авторизованного пользователя
     * @param official_id - id создаваемого ДЛ
     * @param role_ids - id прав(если больше одной, указывать через запятую)
     *//*

    public RootResponseUserDto createUser(int id, String official_id, int...role_ids){
        RootCreateUserDto user = new RootCreateUserDto(official_id, role_ids);
        user.setOfficial_id(official_id);
        user.setRole_ids(role_ids);
        ExtractableResponse<Response> response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", id)
                .body(user)
                .post(API_USER).then()
                .log().all()
                .statusCode(201)
                .extract();

        RootResponseUserDto actualUser = response.body().as(RootResponseUserDto.class);
        return actualUser;
    }

    *//**
     * Изменение пользователя
     *
     * @param id - id авторизованного пользователя
     * @param idUser - id изменяемого ДЛ
     * @param role_ids - id роли(если больше одной, указывать через запятую)
     *//*

    public RootResponseUserDto updateUser(int id, int idUser, int...role_ids){
        RootUpdateUserDto user = new RootUpdateUserDto(role_ids);
        user.setRole_ids(role_ids);
        ExtractableResponse<Response> response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", id)
                .body(user)
                .put(API_USER + "/" + idUser).then()
                .log().all()
                .statusCode(200)
                .extract();

        RootResponseUserDto actualUser = response.body().as(RootResponseUserDto.class);
        return actualUser;
    }

    *//**
     * Удаление ДЛ
     *
     * @param id - авторизованного пользователя
     * @param idUser - id значения, которое будет удалено
     * **//*

    public void deleteUser(int id, String idUser) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .delete(API_USER + "/" + idUser).then()
                .log().all()
                .statusCode(204)
                .extract();
    }*/

    /**
     * Получение пользователя по id
     *
     * @param idAut - id авторизованного пользователя
     * @param id    - id получаемого пользователя
     */

    public static RootUserWithId getUser(int idAut, int id){
        RootUserWithId actualUser = BasicApi.get(API_USER + "/" + id, idAut).as(RootUserWithId.class);
        return actualUser;

    }
    public static RootError getErrorUser(int idAut, int id){
        RootError error = BasicApi.getError(API_USER + "/" + id, idAut).as(RootError.class);
        return error;

    }

    /**
     * Получение пользователей
     *
     * @param idAut - id авторизованного пользователя
     */

    public static RootUserDto getUsers(int idAut){
        RootUserDto actualUser = BasicApi.get(API_USER, idAut).as(RootUserDto.class);
        return actualUser;
    }
    /**
     * Создание пользователя
     *
     * @param id - id авторизованного пользователя
     * @param official_id - id создаваемого ДЛ
     * @param role_ids - id прав(если больше одной, указывать через запятую)
     */

    public static RootResponseUserDto createUser(int id, String official_id, int...role_ids) {
        RootCreateUserDto user = new RootCreateUserDto(official_id, role_ids);
        user.setOfficial_id(official_id);
        user.setRole_ids(role_ids);
        RootResponseUserDto actualUser = BasicApi.post(API_USER, id, user).as(RootResponseUserDto.class);
        return actualUser;


    }

    /**
     * Изменение пользователя
     *
     * @param id - id авторизованного пользователя
     * @param idUser - id изменяемого ДЛ
     * @param role_ids - id роли(если больше одной, указывать через запятую)
     */

    public static RootResponseUserDto updateUser(int id, int idUser, int...role_ids) {
        RootUpdateUserDto user = new RootUpdateUserDto(role_ids);
        user.setRole_ids(role_ids);
        RootResponseUserDto actualUser = BasicApi.put(API_USER + "/" + idUser, id, user).body().as(RootResponseUserDto.class);
        return actualUser;
    }

    /**
     * Удаление ДЛ
     *
     * @param id - авторизованного пользователя
     * @param idUser - id значения, которое будет удалено
     * **/

    public static void deleteUser(int id, String idUser){
        BasicApi.delete(API_USER + "/" + idUser, id);
        return;
    }

}

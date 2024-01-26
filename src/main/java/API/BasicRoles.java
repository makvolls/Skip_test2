package API;

import API.DTO.ErrorsDTO.RolesErrors.RootNameErrors;
import API.DTO.ErrorsDTO.RootError;
import API.DTO.RolesDto.*;
import API.DTO.UserDto.RootUserWithIdCS;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BasicRoles {
    public static String API_ROLES = "http://api.skip.rtech.ru/v1/permissions/roles";


    /**
     * Получение списка ролей
     *
     * @param id - id авторизуемого пользователя
     */

    public static RootRolesDto getRoles(int id) {
        RootRolesDto actualRoles = BasicApi.get(API_ROLES, id).body().as(RootRolesDto.class);
        return actualRoles;
    }
    public static RootError getNoAccessRoles(int id) {
        RootError error = BasicApi.getError(API_ROLES, id).body().as(RootError.class);
        return error;
    }

    public static RootGetRolesWithId getRolesWithId(int autId,int idRoles) {
        RootGetRolesWithId actualRoles = BasicApi.get(API_ROLES + "/"+ idRoles,autId).body().as(RootGetRolesWithId.class);
        return actualRoles;
    }
    /**
     * Получение списка ролей
     *
     * @param id - id авторизуемого пользователя
     */

    public static RootResponseRolesDto getRole(int id, int idRole) {
        RootResponseRolesDto actualRoles = BasicApi.get(API_ROLES + "/" + idRole, id).body()
                .as(RootResponseRolesDto.class);
        return actualRoles;
    }
    public static RootError getErrorRole(int id, int idRole) {
        RootError error = BasicApi.getError(API_ROLES + "/" + idRole, id).body().as(RootError.class);
        return error;
    }
    public static RootError getNoResult(int id, int idRole) {
        RootError error = BasicApi.getErrorNoFound(API_ROLES + "/" + idRole, id).body().as(RootError.class);
        return error;
    }

    public static RootRolesDto getRolesParams(int id, String full_name, String excluded_id){
        Response response = (Response) given()
                .header("Test-Authorization", id)
                .param("full_name", full_name)
                .param("excluded_id", excluded_id)
                .get(API_ROLES).then()
                .log().all()
                .statusCode(200)
                .extract();

        RootRolesDto actualRoles = response.as(RootRolesDto.class);
        return actualRoles;
    }

    /**
     * Создание роли
     *
     * @param id         - id авторизуемого пользователя
     * @param name       - наименование роли
     * @param global     - глобальная роль, true false
     * @param rights_ids - наименование прав(если больше одной, указывать через запятую в кавычках)
     */

    public static RootResponseRolesDto createRoles(int id, String name, boolean global, String... rights_ids) {
        RootCreateRolesDto roles = new RootCreateRolesDto(name, "1", global, rights_ids);
        roles.setName(name);
        roles.setDesc("1");
        roles.setGlobal(global);
        roles.setRights_ids(rights_ids);

        RootResponseRolesDto createdRoles = BasicApi.post(API_ROLES, id, roles).body().as(RootResponseRolesDto.class);
        return createdRoles;

    }
    public static RootNameErrors createErrorRoles(int id, String name, boolean global, String... rights_ids){
        RootCreateRolesDto roles = new RootCreateRolesDto(name, "1", global, rights_ids);
        roles.setName(name);
        roles.setDesc("1");
        roles.setGlobal(global);
        roles.setRights_ids(rights_ids);
        RootNameErrors error = BasicApi.postError(API_ROLES, id, roles).as(RootNameErrors.class);
        return error;
    }
    public static RootError createNoAccessRoles(int id, String name, boolean global, String... rights_ids){
        RootCreateRolesDto roles = new RootCreateRolesDto(name, "1", global, rights_ids);
        roles.setName(name);
        roles.setDesc("1");
        roles.setGlobal(global);
        roles.setRights_ids(rights_ids);
        RootError error = BasicApi.postError(API_ROLES, id, roles).as(RootError.class);
        return error;
    }

    public static RootNameErrors updateErrorRoles(int id, int idRoles, String name, boolean global, String... rights_ids){
        RootCreateRolesDto roles = new RootCreateRolesDto(name, "1", global, rights_ids);
        roles.setName(name);
        roles.setDesc("1");
        roles.setGlobal(global);
        roles.setRights_ids(rights_ids);
        RootNameErrors error = BasicApi.putErrors(API_ROLES + "/" + idRoles, id, roles).as(RootNameErrors.class);
        return error;
    }
    public static RootError updateErrorRolesNoAccess(int id, int idRoles, String name, boolean global, String... rights_ids) {
        RootCreateRolesDto roles = new RootCreateRolesDto(name, "1", global, rights_ids);
        roles.setName(name);
        roles.setDesc("1");
        roles.setGlobal(global);
        roles.setRights_ids(rights_ids);
        RootError error = BasicApi.putErrorsNoRights(API_ROLES + "/" + idRoles, id, roles).as(RootError.class);
        return error;
    }


    /**
     * Изменение роли
     *
     * @param id         - авторизуемого пользователя
     * @param idRoles    - id изменяемой роли
     * @param name       - наименование роли
     * @param global     - глобальная роль, true false
     * @param rights_ids - наименование прав(если больше одной, указывать через запятую в кавычках)
     */

    public static RootResponseRolesDto updateRole(int id, int idRoles, String name, boolean global, String... rights_ids) {
        RootCreateRolesDto roles = new RootCreateRolesDto(name, "1", global, rights_ids);
        roles.setName(name);
        roles.setDesc("1");
        roles.setGlobal(global);
        roles.setRights_ids(rights_ids);

        RootResponseRolesDto actualRoles = BasicApi.put(API_ROLES + "/" + idRoles, id, roles).body().as(RootResponseRolesDto.class);
        return actualRoles;


    }



        /**
         * Удаление роли
         *
         * @param id      - авторизуемого пользователя
         * @param idRoles - id удаляемой роли
         */

    public static void deleteRole(int id, int idRoles) {
        BasicApi.delete(API_ROLES+ "/" + idRoles, id);
        return;


    }

    public static void deleteRoleWithParams(int id, int idRoles) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .params("replace_with",32)
                .delete(API_ROLES + "/" + idRoles).then()
                .log().all()
                .statusCode(204)
                .extract();
    }




    public static RootError deleteRoleТotFound(int id, int idRoles){
        RootError error = BasicApi.deleteNotFound(API_ROLES+ "/" + idRoles, id).as(RootError.class);
        return error;
    }
/*    *//**
     * Получение списка ролей
     *
     * @param id - id авторизуемого пользователя
     *//*

    public RootRolesDto getRoles(int id) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .get(API_ROLES).then()
                .log().all()
                .statusCode(200)
                .extract();

        RootRolesDto actualRoles = response.body().as(RootRolesDto.class);
        return actualRoles;

    }

    *//**
     * Создание роли
     *
     * @param id         - id авторизуемого пользователя
     * @param name       - наименование роли
     * @param global     - глобальная роль, true false
     * @param rights_ids - наименование прав(если больше одной, указывать через запятую в кавычках)
     *//*

    public RootResponseRolesDto createRoles(int id, String name, boolean global, String... rights_ids) {
        RootCreateRolesDto roles = new RootCreateRolesDto(name, "1", global, rights_ids);
        roles.setName(name);
        roles.setDesc("1");
        roles.setGlobal(global);
        roles.setRights_ids(rights_ids);

        ExtractableResponse<Response> response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", id)
                .body(roles)
                .post(API_ROLES).then()
                .log().all()
                .statusCode(201)
                .extract();


        RootResponseRolesDto createdRoles = response.body().as(RootResponseRolesDto.class);
        return createdRoles;


    }

    *//**
     * Изменение роли
     *
     * @param id         - авторизуемого пользователя
     * @param idRoles    - id изменяемой роли
     * @param name       - наименование роли
     * @param global     - глобальная роль, true false
     * @param rights_ids - наименование прав(если больше одной, указывать через запятую в кавычках)
     *//*

    public RootResponseRolesDto updateRole(int id, int idRoles, String name, boolean global, String... rights_ids) {
        RootCreateRolesDto roles = new RootCreateRolesDto(name, "1", global, rights_ids);
        roles.setName(name);
        roles.setDesc("1");
        roles.setGlobal(global);
        roles.setRights_ids(rights_ids);

        ExtractableResponse<Response> response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", id)
                .body(roles)
                .put(API_ROLES + "/" + idRoles).then()
                .log().all()
                .statusCode(200)
                .extract();


        RootResponseRolesDto actualRoles = response.body().as(RootResponseRolesDto.class);
        return actualRoles;

    }

    *//**
     * Удаление роли
     *
     * @param id      - авторизуемого пользователя
     * @param idRoles - id удаляемой роли
     *//*

    public static void deleteRole(int id, int idRoles) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .delete(API_ROLES + "/" + idRoles).then()
                .log().all()
                .statusCode(204)
                .extract();
    }*/
}

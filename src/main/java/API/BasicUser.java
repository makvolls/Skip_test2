package API;

import API.DTO.ErrorsDTO.ControlSubjects.RootUnintendedValue;
import API.DTO.ErrorsDTO.ErrorNotException;
import API.DTO.ErrorsDTO.RootError;
import API.DTO.ErrorsDTO.UserErrors.UserErrorUnauthorized;
import API.DTO.RolesDto.RootPutWithRole_ids;
import API.DTO.UserDto.*;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;

public class BasicUser {
    public static String API_USER = "http://api.skip.rtech.ru/v1/permissions/users";
    public static String API_USER_PROFILE="http://api.skip.rtech.ru/v1/permissions/users/profile";
    public static String API_USER_CURRENT="http://api.skip.rtech.ru/v1/permissions/users/current";
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
    public static GetUserRootDto getUsersNoId(int idAut){
        GetUserRootDto actualUsers = BasicApi.get(API_USER, idAut).as(GetUserRootDto.class);
        return actualUsers;

    }

    public static RootGetUserCurrent getUserCurrent(int idAut){
        RootGetUserCurrent actualUser = BasicApi.get(API_USER_CURRENT, idAut).as(RootGetUserCurrent.class);
        return actualUser;

    }

    public static RootError getUserError(int idAut){
        RootError actualUser = BasicApi.getError(API_USER, idAut).as(RootError.class);
        return actualUser;

    }

    public static RootError getUserErrorWithId(int idAut,int id){
        RootError actualUser = BasicApi.getError(API_USER + "/" + id, idAut).as(RootError.class);
        return actualUser;

    }

    /**
     * Получение сообщения об ошибке с кодом "403"
     * при попытке получить сведения о пользователе
     * @param idAut - id авторизованного пользователя
     * @param id - id получаемого пользователя
     * **/

    public static RootError getErrorUser(int idAut, int id){
        RootError error = BasicApi.getError(API_USER + "/" + id, idAut).as(RootError.class);
        return error;

    }
    public static UserErrorUnauthorized getErrorUserUnauthorized(int idAut){
        UserErrorUnauthorized error = BasicApi.getErrorUnauthorized(API_USER, idAut).as(UserErrorUnauthorized.class);
        return error;

    }

    public static RootGetRequestUser getUserProfile(int idAut){
        RootGetRequestUser actualUser = BasicApi.get(API_USER_PROFILE, idAut).as(RootGetRequestUser.class);
        return actualUser;

    }

    /**
     * Получение пользователей
     *
     * @param idAut - id авторизованного пользователя
     */

//    public static RootUserDto getUsers(int idAut){
//        RootUserDto actualUser = BasicApi.get(API_USER, idAut).as(RootUserDto.class);
//        return actualUser;
//    }

    /**
     * Получение списка пользователей
     * @param idAut - id авторизованного пользователя
     * **/

    public static RootUsersList getUsers(int idAut){
        RootUsersList actualUser = BasicApi.get(API_USER, idAut).as(RootUsersList.class);
        return actualUser;
    }

    /**
     * Получение пользователя по значению id - "Субъекты контроля"
     * @param idAut - id авторизованного пользователя
     * @param control_subject_id - значение id элемента "Субъекты контроля"
     * **/
    public static RootUserDto getAndSearchCS(int idAut, int control_subject_id){
        RootUserDto actualUser = BasicApi.getAndSearch(API_USER, idAut, control_subject_id).as(RootUserDto.class);
        return actualUser;
    }

    /**
     * Попытка получения пользователя по значаению id - "Субъекты контроля"
     *
     **/


    public static RootUnintendedValue searchCSAndGetError(int idAut, int...control_subject_id){
        RootUnintendedValue error = BasicApi.getAndSearchError(API_USER, idAut, control_subject_id).
                as(RootUnintendedValue.class);
        return error;
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
    public static RootError createUserError(int id, String official_id, int...role_ids) {
        RootCreateUserDto user = new RootCreateUserDto(official_id, role_ids);
        user.setOfficial_id(official_id);
        user.setRole_ids(role_ids);
        RootError actualUser = BasicApi.postErrorNoRights(API_USER, id, user).as(RootError.class);
        return actualUser;
    }

    /**
     * Создание пользователя с указанием параметра
     * "Субъекты контроля"
     *
     * @param id - id авторизованного пользователя
     * @param official_id - id создаваемого ДЛ
     * @param role_ids - id прав(указывается в массиве данных)
     * @param control_subject_id - id пераметра "Субъекты контроля"
     * **/

    public static RootResponseUserDto createUserWithCS(int id, String official_id, int[] role_ids, int control_subject_id){
        RootCreateUserWithCS user = new RootCreateUserWithCS(official_id, role_ids, control_subject_id);
        user.setOfficial_id(official_id);
        user.setRole_ids(role_ids);
        user.setControl_subject_id(control_subject_id);
        RootResponseUserDto actualUser = BasicApi.post(API_USER, id, user).as(RootResponseUserDto.class);
        return actualUser;
    }

    /**
     * Создание пользователя с указанием параметра
     * "Субъекты контроля" и получение сообщение об ошибке
     * - "Доступ к ресурсу запрещен"
     * **/

    public static RootError createUserAndGetError(int id, String official_id, int[] role_ids, int control_subject_id){
        RootCreateUserWithCS user = new RootCreateUserWithCS(official_id,role_ids, control_subject_id);
        user.setOfficial_id(official_id);
        user.setRole_ids(role_ids);
        user.setControl_subject_id(control_subject_id);
        RootError error = BasicApi.postErrorAccessDenied(API_USER,id,user).as(RootError.class);
        return error;
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
        RootResponseUserDto actualUser = BasicApi.put(API_USER + "/" + idUser, id, user)
                .body().as(RootResponseUserDto.class);
        return actualUser;
    }

    /**
     * Изменение пользователя
     *
     * @param id - id авторизованного пользователя
     * @param idUser - id изменяемого ДЛ
     * @param role_ids - id ролей (указывается в массиве - {} )
     * **/

    public static RootResponseUserDto updateUserRoleArray(int id, int idUser, int[] role_ids){
        RootUpdateUserDto user = new RootUpdateUserDto(role_ids);
        user.setRole_ids(role_ids);
        RootResponseUserDto actualUser = BasicApi.put(API_USER + "/" + idUser, id, user)
                .body().as(RootResponseUserDto.class);
        return actualUser;
    }




    public static RootUserWithIdCS updateRoleWithRole_ids(int id, int idRoles, int... role_ids) {
        RootPutWithRole_ids roles = new RootPutWithRole_ids(role_ids);
        roles.setRole_ids(role_ids);

        RootUserWithIdCS actualRoles = BasicApi.put(API_USER + "/" + idRoles, id, roles).body().as(RootUserWithIdCS.class);
        return actualRoles;

    }




    /**
     * Получение ошибки - "Доступ к ресурсу запрещен"
     * при попытке внести изменения пользователю
     * Стастус ошибки - 403
     * @param id - id авторизованного пользователя
     * @param idUser - id изменяемого ДЛ
     * @param role_ids - id ролей
     * **/

    public static RootError updateUserRoleError(int id, int idUser, int... role_ids){
        RootUpdateUserDto user = new RootUpdateUserDto(role_ids);
        user.setRole_ids(role_ids);
        RootError errorMessage = BasicApi.putErrors(API_USER + "/" + idUser, id, user)
                .body().as(RootError.class);
        return errorMessage;
    }
    public static RootError updateUserRoleErrorNoRights(int id, int idUser, int... role_ids){
        RootUpdateUserDto user = new RootUpdateUserDto(role_ids);
        user.setRole_ids(role_ids);
        RootError errorMessage = BasicApi.putErrorsNoRights(API_USER + "/" + idUser, id, user)
                .body().as(RootError.class);
        return errorMessage;
    }


    /**
     * Изменение у пользователя параметра "Субъекты контроля"
     * @param id - id авторизованного пользователя
     * @param idUser - id изменяемого ДЛ
     * @param role_ids - id роли - указывается в массиве - {}
     * @param control_subject_id - id параметра "Субъекты контроля"
     * **/

    public static RootResponseUserDto updateUserControlSubjectsId(int id, int idUser, int[] role_ids, int control_subject_id){
        RootUpdateUserCSDto user = new RootUpdateUserCSDto(role_ids, control_subject_id);
        user.setRole_ids(role_ids);
        user.setControl_subject_id(control_subject_id);
        RootResponseUserDto actualUser = BasicApi.put(
                API_USER + "/" + idUser, id, user).body().as(RootResponseUserDto.class);
        return actualUser;
    }

    /**
     * Изменение у пользователя параметра "Субъекты контроля"
     * на null
     * @param id - id авторизованного пользователя
     * @param idUser - id изменяемого ДЛ
     * @param role_ids - id роли - указывается в массиве - {}
     * @param control_subject_id - указать ""(пустую строку), чтобы
     * присвоить null параметру "control_subject"
     * **/


    public static RootResponseUserDto updateUserControlSubjectNull(int id, int idUser,
                                                                   int[] role_ids, String  control_subject_id){
        RootUpdateUserCSNull user = new RootUpdateUserCSNull(role_ids, control_subject_id);
        user.setRole_ids(role_ids);
        user.setControl_subject_id(control_subject_id);
        RootResponseUserDto actualUser = BasicApi.put(
                API_USER + "/" + idUser, id, user).body().as(RootResponseUserDto.class);
        return actualUser;
    }

    /**
     * Получение ошибки со статусом "500"
     * при попытке внести изменения в "Субъекты контроля"
     * @param id - id авторизованного пользователя
     * @param idUser - id изменяемого ДЛ
     * @param role_ids - id роли - указывается в массиве - {}
     * @param control_subject_id - id параметра "Субъекты контроля"
     * **/

    public static ErrorNotException updateUserCSAndGetError(int id, int idUser, int[] role_ids, int control_subject_id){
        RootUpdateUserCSDto user = new RootUpdateUserCSDto(role_ids, control_subject_id);
        user.setRole_ids(role_ids);
        user.setControl_subject_id(control_subject_id);
        ErrorNotException errorMessage = BasicApi.putErrorFiveHundred(
                API_USER + "/" + idUser, id, user).body().as(ErrorNotException.class);
        return errorMessage;
    }

    /**
     * Получение ошибки со статусом "422"
     * при попытке внести изменения в "Субъекты контроля"
     * в другом провайдере
     * @param id - id авторизованного пользователя
     * @param idUser - id изменяемого ДЛ
     * @param role_ids - id роли - указывается в массиве - {}
     * @param control_subject_id - id параметра "Субъекты контроля"
     * **/

    public static RootUnintendedValue updateUserOtherProvider(int id, int idUser, int[] role_ids, int control_subject_id){
        RootUpdateUserCSDto user = new RootUpdateUserCSDto(role_ids, control_subject_id);
        user.setRole_ids(role_ids);
        user.setControl_subject_id(control_subject_id);
        RootUnintendedValue errorMessage = BasicApi.putErrors(
                API_USER + "/" + idUser, id, user).body().as(RootUnintendedValue.class);
        return errorMessage;
    }

    /**
     * Получение ошибки со стастусом "422"
     * при попытке внести изменения в "Субъекты контроля"
     * при передаче нескольких значений
     * @param id - id авторизованного пользователя
     * @param idUser - id изменяемого ДЛ
     * @param role_ids - id роли - указывается в массиве - {}
     * @param control_subject_id - id параметра "Субъекты контроля"
     * **/

    public static RootUnintendedValue updateUserSeveralIds(int id, int idUser, int[] role_ids, int[] control_subject_id){
        RootUpdateUserCSArrayId user = new RootUpdateUserCSArrayId(role_ids,control_subject_id);
        user.setRole_ids(role_ids);
        user.setControl_subject_id(control_subject_id);
        RootUnintendedValue errorMessage = BasicApi.putErrors(
                API_USER + "/" + idUser, id, user).body().as(RootUnintendedValue.class);
        return errorMessage;
    }

    /**
     * Удаление ДЛ
     *
     * @param id - авторизованного пользователя
     * @param idUser - id значения, которое будет удалено
     * Значение параметра idUser в формате строки
     * **/

    public static void deleteUser(int id, int idUser){
        BasicApi.delete(API_USER + "/" + idUser, id);
        return;
    }

    /**
     * Удаление ДЛ
     * @param id - авторизованного пользователя
     * @param idUser - id значения, которое будет удалено
     * **/

    public static void deleteUserIntTest(int id, int idUser){
        BasicApi.delete(API_USER + "/" + idUser, id);
    }

    /**
     * Получение сообщения , что пользователя с данным id не найдено
     * @param idAut - id авторизованного пользователя
     * @param id - id пользователя, который удален
     * **/
    public static RootError deletedUserCheck(int idAut, int id){
        RootError errorMessage = BasicApi.deleteNotFound(API_USER + "/" + id, idAut).body().as(RootError.class);
        return errorMessage;
    }
    public static RootError deletedUserNoRights(int idAut, int id){
        RootError errorMessage = BasicApi.deleteErrorNoRights(API_USER + "/" + id, idAut).body().as(RootError.class);
        return errorMessage;
    }


}

package BasicPageTestApi;

import DTO.ControlPeriodStatesDto.RootControlDto;
import DTO.DirectoryDto.RootDirectoryDto;
import DTO.RolesDto.DataResponseRolesDto;
import DTO.RolesDto.RootCreateRolesDto;
import DTO.RolesDto.RootResponseRolesDto;
import DTO.SecurityClassificationsDto.RootSecurityDto;
import DTO.UserDto.RootCreateUserDto;
import DTO.UserDto.RootResponseUserDto;
import DTO.UserDto.RootUpdateUserDto;
import DTO.UserDto.RootUserDto;
import DTO.EventsDto.RootListDto;
import DTO.RolesDto.RootRolesDto;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BasicApiTest {

    public static String API_USER = "http://api.skip.rtech.ru/v1/permissions/users";
    public static String API_EVENT = "http://api.skip.rtech.ru/v1/classifiers/events";
    public static String API_CLASSIFIERS = "http://api.skip.rtech.ru/v1/classifiers";
    public static String API_CONTROL_PERIOD = "http://api.skip.rtech.ru/v1/classifiers/control_period_states";
    public static String API_ROLES = "http://api.skip.rtech.ru/v1/permissions/roles";
    public static String API_SECURITY_CLASSIFICATIONS = "http://api.skip.rtech.ru/v1/classifiers/security_classifications";


    public BasicApiTest() {
    }


    /**
     * Получение пользователя по id
     *
     * @param idAut - id авторизованного пользователя
     * @param id - id получаемого пользователя
     */

    public RootUserDto getUser(int idAut, int id) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", idAut)
                .get(API_USER + "/" + id).then()
                .log().all()
                .statusCode(200)
                .extract();
        RootUserDto actualUser = response.body().as(RootUserDto.class);

        return actualUser;

    }

    /**
     * Получение пользователей
     *
     * @param idAut - id авторизованного пользователя
     */

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

    /**
     * Создание пользователя
     *
     * @param id - id авторизованного пользователя
     * @param official_id - id создаваемого ДЛ
     * @param role_ids - id прав(если больше одной, указывать через запятую)
     */

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





    /**
     * Получение списка событий
     *
     * @param id - id авторизованного пользователя
     */

    public RootListDto getListEvents(int id) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .get(API_EVENT).then()
                .log().all()
                .statusCode(200)
                .extract();

        RootListDto actualList = response.body().as(RootListDto.class);
        return actualList;

    }

    /**
     * Получение списка справочников
     *
     * @param id - id авторизуемого пользователя
     */

    public RootDirectoryDto getClassifiers(int id) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .get(API_CLASSIFIERS).then()
                .log().all()
                .statusCode(200)
                .extract();

        RootDirectoryDto actualClassifiers = response.body().as(RootDirectoryDto.class);
        return actualClassifiers;
    }

    /**
     * Получение контрольного периода
     *
     * @param id - id авторизуемого пользователя
     * @param sort - сортировка по name, short_name, priority
     * @param sort_ord - сортировка по desc, asc
     */

    public RootControlDto getControlPeriod(int id, String sort, String sort_ord) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .param("sort_by", sort)
                .param("sort_order", sort_ord)
                .get(API_CONTROL_PERIOD).then()
                .log().all()
                .statusCode(200)
                .extract();

        RootControlDto actualControl = response.body().as(RootControlDto.class);
        return actualControl;
    }

    /**
     * Получение списка ролей
     *
     * @param id - id авторизуемого пользователя
     */

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

    /**
     * Получение списка грифов
     *
     * @param id - id авторизуемого пользователя
     */

    public RootSecurityDto getSecurity(int id) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .get(API_SECURITY_CLASSIFICATIONS).then()
                .log().all()
                .statusCode(200)
                .extract();

        RootSecurityDto actualSecurity = response.body().as(RootSecurityDto.class);
        return actualSecurity;
    }

    /**
     * Создание роли
     *
     * @param id - id авторизуемого пользователя
     * @param name - наименование роли
     * @param global - глобальная роль, true false
     * @param rights_ids - наименование прав(если больше одной, указывать через запятую в кавычках)
     */

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

    /**
     * Изменение роли
     *
     * @param  id - авторизуемого пользователя
     * @param  idRoles  - id изменяемой роли
     * @param name - наименование роли
     * @param global - глобальная роль, true false
     * @param  rights_ids - наименование прав(если больше одной, указывать через запятую в кавычках)
     */

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

    /**
     * Удаление роли
     *@param  id - авторизуемого пользователя
     *@param  idRoles  - id удаляемой роли
     */

    public void deleteRole(int id, int idRoles) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .delete(API_ROLES + "/" + idRoles).then()
                .log().all()
                .statusCode(204)
                .extract();
    }




}

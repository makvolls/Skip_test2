package BasicPageTestApi;

import DTO.AssignmentExecutionStatesDto.CreateValueAssignmentExecutionStates;
import DTO.AssignmentExecutionStatesDto.RootAssignmentExecutionStates;
import DTO.AssignmentExecutionStatesDto.RootElementAssignmentExecutionStates;
import DTO.ControlPeriodStatesDto.RootControlDto;
import DTO.DeadlineBasesDto.CreateValueDeadlineBases;
import DTO.DeadlineBasesDto.RootDeadlineBases;
import DTO.DeadlineBasesDto.RootElementDeadlineBases;
import DTO.DirectoryDto.RootDirectoryDto;
import DTO.DocumentExecutionStatesDto.CreateValueDocumentExecutionStates;
import DTO.DocumentExecutionStatesDto.RootDocumentExecutionStates;
import DTO.DocumentExecutionStatesDto.RootElementDocumentExecutionStates;
import DTO.EventStatesDto.CreateValueClassEventStates;
import DTO.EventStatesDto.RootElementEventStates;
import DTO.EventStatesDto.RootEventStates;
import DTO.ImageMarksDto.CreateValueImageMarks;
import DTO.ImageMarksDto.RootElementImageMarks;
import DTO.ImageMarksDto.RootImageMarks;
import DTO.ProvidersDTO.RootPermissions;
import DTO.RolesDto.RootCreateRolesDto;
import DTO.RolesDto.RootResponseRolesDto;
import DTO.SecurityClassificationsDto.RootSecurityDto;
import DTO.ThemesDto.CreateValueClassThemes;
import DTO.ThemesDto.RootElementThemes;
import DTO.ThemesDto.RootThemes;
import DTO.UrgenciesDTO.CreateValueClass;
import DTO.UrgenciesDTO.RootElementUrgencies;
import DTO.UrgenciesDTO.RootUrgencies;
import DTO.UserDto.*;
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
    public static String API_PROVIDERS= "http://api.skip.rtech.ru/v1/permissions/providers";
    public static String API_URGENCIES = "http://api.skip.rtech.ru/v1/classifiers/urgencies";
    public static String API_THEMES = "http://api.skip.rtech.ru/v1/classifiers/themes";
    public static String API_EVENT_STATES = "http://api.skip.rtech.ru/v1/classifiers/event_states";
    public static String API_ASSIGNMENT_EXECUTION_STATES = "http://api.skip.rtech.ru/v1/classifiers/assignment_execution_states";
    public static String API_DOCUMENT_EXECUTION_STATES = "http://api.skip.rtech.ru/v1/classifiers/document_execution_states";
    public static String API_IMAGE_MARKS = "http://api.skip.rtech.ru/v1/classifiers/image_marks";
    public static String API_DEADLINE_BASES = "http://api.skip.rtech.ru/v1/classifiers/deadline_bases";

    /**
     * Periodicity /
     * Document_types
     * **/



    public BasicApiTest() {
    }


    /**
     * Получение пользователя по id
     *
     * @param idAut - id авторизованного пользователя
     * @param id - id получаемого пользователя
     */

//    public RootUserDto getUser(int idAut, int id) {
//        ExtractableResponse<Response> response = given()
//                .header("Test-Authorization", idAut)
//                .get(API_USER + "/" + id).then()
//                .log().all()
//                .statusCode(200)
//                .extract();
//        RootUserDto actualUser = response.body().as(RootUserDto.class);
//
//        return actualUser;
//
//    }

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

    /**
     * Получение списка провайдеров
     * @param id - авторизуемого пользователя
     * **/

    public RootPermissions getProviders(int id) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .get(API_PROVIDERS).then()
                .log().all()
                .statusCode(200)
                .extract();
        RootPermissions actualProviders = response.body().as(RootPermissions.class);
        return actualProviders;
    }

    /**
     * Изменение провайдера
     * @param id - авторизуемого пользователя
     * Maybe later
     * **/

    /**
     *Справочник "Срочность"
      **/

    /**
     * Получение значений справочника
     * @param id - id авторизуемого пользователя
     * **/

    public RootUrgencies getUrgenciesList(int id){
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .get(API_URGENCIES).then()
                .log().all()
                .statusCode(200)
                .extract();
        RootUrgencies actualUrgencies = response.body().as(RootUrgencies.class);
        return actualUrgencies;
    }


    /**
     * Создание значение справочника
     *
     * @param id - id авторизованного пользователя
     * @param name - Название справочника
     * @param excluded - элемент исключения значения из справочника
     */

    public RootElementUrgencies createValue(int id, String name, boolean excluded){
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

    /**
     * Изменение значения справочника
     *
     * @param id - id авторизованного пользователя
     * @param idValue - id значения , в котором изменяется значение
     * @param name - Название справочника
     * @param excluded - элемент исключения значения из справочника
     * **/

    public RootElementUrgencies changeValue(int id, int idValue, String name, boolean excluded){
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

    /**
     * Удаление значения справочника
     *
     * @param id - авторизованного пользователя
     * @param idValue - id значения , которое будет удалено
     * **/

    public void deleteValue(int id, int idValue) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .delete(API_URGENCIES + "/" + idValue).then()
                .log().all()
                .statusCode(204)
                .extract();
    }

    /**
     * POST request (reorder)
     * **/

    /**
     * Справочник "Тематика"
     * **/

    /**
     * Получение значений справочника
     * @param id - id авторизуемого пользователя
     * **/

    public RootThemes getThemesList(int id){
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .get(API_THEMES).then()
                .log().all()
                .statusCode(200)
                .extract();
        RootThemes actualThemes = response.body().as(RootThemes.class);
        return actualThemes;
    }

    /**
     * Создание значение справочника
     *
     * @param id - id авторизованного пользователя
     * @param name - Название справочника
     * @param excluded - элемент исключения значения из справочника
     */

    public RootElementThemes createValueThemes(int id, String name, boolean excluded){
        CreateValueClassThemes value = new CreateValueClassThemes(name, excluded);
        value.setName(name);
        value.setExcluded(excluded);
        ExtractableResponse<Response> response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", id)
                .body(value)
                .post(API_THEMES).then()
                .log().all()
                .statusCode(201)
                .extract();

        RootElementThemes createdValue = response.body().as(RootElementThemes.class);
        return createdValue;
    }

    /**
     * Изменение значения справочника
     *
     * @param id - id авторизованного пользователя
     * @param idValue - id значения , в котором изменяется значение
     * @param name - Название справочника
     * @param excluded - элемент исключения значения из справочника
     * **/

    public RootElementThemes changeThemesValue(int id, int idValue, String name, boolean excluded){
        CreateValueClassThemes value = new CreateValueClassThemes(name, excluded);
        value.setName(name);
        value.setExcluded(excluded);
        ExtractableResponse<Response> response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", id)
                .body(value)
                .put(API_THEMES + "/" + idValue).then()
                .log().all()
                .statusCode(200)
                .extract();

        RootElementThemes newValue = response.body().as(RootElementThemes.class);
        return newValue;
    }

    /**
     * Удаление значения справочника
     *
     * @param id - авторизованного пользователя
     * @param idValue - id значения , которое будет удалено
     * **/

    public void deleteThemesValue(int id, int idValue) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .delete(API_THEMES + "/" + idValue).then()
                .log().all()
                .statusCode(204)
                .extract();
    }

    /**
     * POST request (reorder)
     * **/

    /**
     * Справочник "Состояние события"
     * **/

    /**
     * Получение значений справочника
     * @param id - id авторизуемого пользователя
     * **/

    public RootEventStates getEventStatesList(int id){
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .get(API_EVENT_STATES).then()
                .log().all()
                .statusCode(200)
                .extract();
        RootEventStates actualEventStates = response.body().as(RootEventStates.class);
        return actualEventStates;
    }

    /**
     * Создание значение справочника
     *
     * @param id - id авторизованного пользователя
     * @param name - Название справочника
     * @param excluded - элемент исключения значения из справочника
     */

    public RootElementEventStates createValueEventStates(int id, String name, boolean excluded){
        CreateValueClassEventStates value = new CreateValueClassEventStates(name, excluded);
        value.setName(name);
        value.setExcluded(excluded);
        ExtractableResponse<Response> response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", id)
                .body(value)
                .post(API_EVENT_STATES).then()
                .log().all()
                .statusCode(201)
                .extract();

        RootElementEventStates createdValue = response.body().as(RootElementEventStates.class);
        return createdValue;
    }

    /**
     * Изменение значения справочника
     *
     * @param id - id авторизованного пользователя
     * @param idValue - id значения , в котором изменяется значение
     * @param name - Название справочника
     * @param excluded - элемент исключения значения из справочника
     * **/

    public RootElementEventStates changeEventStatesValue(int id, int idValue, String name, boolean excluded){
        CreateValueClassEventStates value = new CreateValueClassEventStates(name, excluded);
        value.setName(name);
        value.setExcluded(excluded);
        ExtractableResponse<Response> response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", id)
                .body(value)
                .put(API_EVENT_STATES + "/" + idValue).then()
                .log().all()
                .statusCode(200)
                .extract();

        RootElementEventStates newValue = response.body().as(RootElementEventStates.class);
        return newValue;
    }

    /**
     * Удаление значения справочника
     *
     * @param id - авторизованного пользователя
     * @param idValue - id значения , которое будет удалено
     * **/

    public void deleteEventStateValue(int id, int idValue) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .delete(API_EVENT_STATES + "/" + idValue).then()
                .log().all()
                .statusCode(204)
                .extract();
    }

    /**
     * POST request (reorder)
     * **/

    /**
     * Справочник "Состояние исполнения поручения"
     * "**/

    /**
     * Получение значений справочника
     * @param id - id авторизуемого пользователя
     * **/

    public RootAssignmentExecutionStates getAssignmentExecutionStatesList(int id){
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .get(API_ASSIGNMENT_EXECUTION_STATES).then()
                .log().all()
                .statusCode(200)
                .extract();
        RootAssignmentExecutionStates actualAssignmentExecutionStates= response.body().as(RootAssignmentExecutionStates.class);
        return actualAssignmentExecutionStates;
    }

    /**
     * Создание значение справочника
     *
     * @param id - id авторизованного пользователя
     * @param name - Название справочника
     * @param short_name - Краткое наименование справочника
     * @param excluded - элемент исключения значения из справочника
     */

    public RootElementAssignmentExecutionStates createValueAssignmentExecutionStates(int id, String name, String short_name, boolean excluded){
        CreateValueAssignmentExecutionStates value = new CreateValueAssignmentExecutionStates(name, short_name, excluded);
        value.setName(name);
        value.setShort_name(short_name);
        value.setExcluded(excluded);
        ExtractableResponse<Response> response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", id)
                .body(value)
                .post(API_ASSIGNMENT_EXECUTION_STATES).then()
                .log().all()
                .statusCode(201)
                .extract();

        RootElementAssignmentExecutionStates createdValue = response.body().as(RootElementAssignmentExecutionStates.class);
        return createdValue;
    }

    /**
     * Изменение значения справочника
     *
     * @param id - id авторизованного пользователя
     * @param idValue - id значения , в котором изменяется значение
     * @param name - Название справочника
     * @param short_name - Краткое название справочника
     * @param excluded - элемент исключения значения из справочника
     * **/

    public RootElementAssignmentExecutionStates changeAssignmentExecutionStatesValue(int id, int idValue, String name, String short_name, boolean excluded){
        CreateValueAssignmentExecutionStates value = new CreateValueAssignmentExecutionStates(name, short_name, excluded);
        value.setName(name);
        value.setShort_name(short_name);
        value.setExcluded(excluded);
        ExtractableResponse<Response> response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", id)
                .body(value)
                .put(API_ASSIGNMENT_EXECUTION_STATES + "/" + idValue).then()
                .log().all()
                .statusCode(200)
                .extract();

        RootElementAssignmentExecutionStates newValue = response.body().as(RootElementAssignmentExecutionStates.class);
        return newValue;
    }

    /**
     * Удаление значения справочника
     *
     * @param id - авторизованного пользователя
     * @param idValue - id значения , которое будет удалено
     * **/

    public void deleteAssignmentExecutionStatesValue(int id, int idValue) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .delete(API_ASSIGNMENT_EXECUTION_STATES + "/" + idValue).then()
                .log().all()
                .statusCode(204)
                .extract();
    }

    /**
     * POST request (reorder)
     * **/


    /**
     * Справочник "Состояние исполнения документа"
     * **/

    /**
     * Получение значений справочника
     * @param id - id авторизуемого пользователя
     * **/

    public RootDocumentExecutionStates getDocumentExecutionStatesList(int id){
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .get(API_DOCUMENT_EXECUTION_STATES).then()
                .log().all()
                .statusCode(200)
                .extract();
        RootDocumentExecutionStates actualDocumentExecutionStates= response.body().as(RootDocumentExecutionStates.class);
        return actualDocumentExecutionStates;
    }

    /**
     * Создание значение справочника
     *
     * @param id - id авторизованного пользователя
     * @param name - Название справочника
     * @param short_name - Краткое наименование справочника
     * @param excluded - элемент исключения значения из справочника
     */

    public RootElementDocumentExecutionStates createValueDocumentExecutionStates(int id, String name, String short_name, boolean excluded){
        CreateValueDocumentExecutionStates value = new CreateValueDocumentExecutionStates(name, short_name, excluded);
        value.setName(name);
        value.setShort_name(short_name);
        value.setExcluded(excluded);
        ExtractableResponse<Response> response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", id)
                .body(value)
                .post(API_DOCUMENT_EXECUTION_STATES).then()
                .log().all()
                .statusCode(201)
                .extract();

        RootElementDocumentExecutionStates createdValue = response.body().as(RootElementDocumentExecutionStates.class);
        return createdValue;
    }

    /**
     * Изменение значения справочника
     *
     * @param id - id авторизованного пользователя
     * @param idValue - id значения , в котором изменяется значение
     * @param name - Название справочника
     * @param short_name - Краткое название справочника
     * @param excluded - элемент исключения значения из справочника
     * **/

    public RootElementDocumentExecutionStates changeDocumentExecutionStatesValue(int id, int idValue, String name, String short_name, boolean excluded){
        CreateValueDocumentExecutionStates value = new CreateValueDocumentExecutionStates(name, short_name, excluded);
        value.setName(name);
        value.setShort_name(short_name);
        value.setExcluded(excluded);
        ExtractableResponse<Response> response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", id)
                .body(value)
                .put(API_DOCUMENT_EXECUTION_STATES + "/" + idValue).then()
                .log().all()
                .statusCode(200)
                .extract();

        RootElementDocumentExecutionStates newValue = response.body().as(RootElementDocumentExecutionStates.class);
        return newValue;
    }

    /**
     * Удаление значения справочника
     *
     * @param id - авторизованного пользователя
     * @param idValue - id значения , которое будет удалено
     * **/

    public void deleteDocumentExecutionStatesValue(int id, int idValue) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .delete(API_DOCUMENT_EXECUTION_STATES + "/" + idValue).then()
                .log().all()
                .statusCode(204)
                .extract();
    }

    /**
     * POST request (reorder)
     * **/


    /**
     * Справочник "Отметки на электронных образах"
     * **/

    /**
     * Получение значений справочника
     * @param id - id авторизуемого пользователя
     * **/

    public RootImageMarks getImageMarksList(int id){
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .get(API_IMAGE_MARKS).then()
                .log().all()
                .statusCode(200)
                .extract();
        RootImageMarks actualImageMarks= response.body().as(RootImageMarks.class);
        return actualImageMarks;
    }

    /**
     * Создание значение справочника
     *
     * @param id - id авторизованного пользователя
     * @param name - Название справочника
     * @param short_name - Краткое наименование справочника
     * @param excluded - элемент исключения значения из справочника
     */

    public RootElementImageMarks createValueImageMarks(int id, String name, String short_name, boolean excluded){
        CreateValueImageMarks value = new CreateValueImageMarks(name, short_name, excluded);
        value.setName(name);
        value.setShort_name(short_name);
        value.setExcluded(excluded);
        ExtractableResponse<Response> response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", id)
                .body(value)
                .post(API_IMAGE_MARKS).then()
                .log().all()
                .statusCode(201)
                .extract();

        RootElementImageMarks createdValue = response.body().as(RootElementImageMarks.class);
        return createdValue;
    }

    /**
     * Изменение значения справочника
     *
     * @param id - id авторизованного пользователя
     * @param idValue - id значения , в котором изменяется значение
     * @param name - Название справочника
     * @param short_name - Краткое название справочника
     * @param excluded - элемент исключения значения из справочника
     * **/

    public RootElementImageMarks changeImageMarksValue(int id, int idValue, String name, String short_name, boolean excluded){
        CreateValueImageMarks value = new CreateValueImageMarks(name, short_name, excluded);
        value.setName(name);
        value.setShort_name(short_name);
        value.setExcluded(excluded);
        ExtractableResponse<Response> response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", id)
                .body(value)
                .put(API_IMAGE_MARKS + "/" + idValue).then()
                .log().all()
                .statusCode(200)
                .extract();

        RootElementImageMarks newValue = response.body().as(RootElementImageMarks.class);
        return newValue;
    }

    /**
     * Удаление значения справочника
     *
     * @param id - авторизованного пользователя
     * @param idValue - id значения , которое будет удалено
     * **/

    public void deleteImageMarksValue(int id, int idValue) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .delete(API_IMAGE_MARKS + "/" + idValue).then()
                .log().all()
                .statusCode(204)
                .extract();
    }

    /**
     * POST request (reorder)
     * **/


    /**
     * Справочник "Основание контрольного срока"
     * **/

    /**
     * Получение значений справочника
     * @param id - id авторизуемого пользователя
     * **/

    public RootDeadlineBases getDeadlineBasesList(int id){
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .get(API_DEADLINE_BASES).then()
                .log().all()
                .statusCode(200)
                .extract();
        RootDeadlineBases actualDeadlineBases= response.body().as(RootDeadlineBases.class);
        return actualDeadlineBases;
    }

    /**
     * Создание значение справочника
     *
     * @param id - id авторизованного пользователя
     * @param name - Название справочника
     * @param short_name - Краткое наименование справочника
     * @param note - Заметка для справочника
     * @param duration_id - id срока поручения
     * @param deadline_only - элемент ...
     * @param excluded - элемент исключения значения из справочника
     */

    public RootElementDeadlineBases createValueDeadlineBases(int id, String name, String short_name, String note, String duration_id, boolean deadline_only, boolean excluded){
        CreateValueDeadlineBases value = new CreateValueDeadlineBases(name, short_name, note, duration_id, deadline_only, excluded);
        value.setName(name);
        value.setShort_name(short_name);
        value.setNote(note);
        value.setDuration_id(duration_id);
        value.setDeadline_only(deadline_only);
        value.setExcluded(excluded);
        ExtractableResponse<Response> response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", id)
                .body(value)
                .post(API_DEADLINE_BASES).then()
                .log().all()
                .statusCode(201)
                .extract();

        RootElementDeadlineBases createdValue = response.body().as(RootElementDeadlineBases.class);
        return createdValue;
    }

    /**
     * Изменение значения справочника
     *
     * @param id - id авторизованного пользователя
     * @param idValue - id справочника, в котором измениться значение
     * @param name - Название справочника
     * @param short_name - Краткое наименование справочника
     * @param note - Заметка для справочника
     * @param duration_id - id срока поручения
     * @param deadline_only - элемент ...
     * @param excluded - элемент исключения значения из справочника
     * **/

    public RootElementDeadlineBases changeDeadlineBasesValue(int id, int idValue, String name, String short_name, String note, String duration_id, boolean deadline_only, boolean excluded){
        CreateValueDeadlineBases value = new CreateValueDeadlineBases(name, short_name, note, duration_id, deadline_only, excluded);
        value.setName(name);
        value.setShort_name(short_name);
        value.setNote(note);
        value.setDuration_id(duration_id);
        value.setDeadline_only(deadline_only);
        value.setExcluded(excluded);
        ExtractableResponse<Response> response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", id)
                .body(value)
                .put(API_DEADLINE_BASES + "/" + idValue).then()
                .log().all()
                .statusCode(200)
                .extract();

        RootElementDeadlineBases newValue = response.body().as(RootElementDeadlineBases.class);
        return newValue;
    }

    /**
     * Удаление значения справочника
     *
     * @param id - авторизованного пользователя
     * @param idValue - id значения , которое будет удалено
     * **/

    public void deleteDeadlineBasesValue(int id, int idValue) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .delete(API_DEADLINE_BASES + "/" + idValue).then()
                .log().all()
                .statusCode(204)
                .extract();
    }

    /**
     * POST request (reorder)
     * **/



}

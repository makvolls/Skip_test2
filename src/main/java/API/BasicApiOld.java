package API;

import API.DTO.AssignmentExecutionStatesDto.CreateValueAssignmentExecutionStates;
import API.DTO.AssignmentExecutionStatesDto.RootAssignmentExecutionStates;
import API.DTO.AssignmentExecutionStatesDto.RootElementAssignmentExecutionStates;
import API.DTO.DeadlineBasesDto.CreateValueDeadlineBases;
import API.DTO.DeadlineBasesDto.RootDeadlineBases;
import API.DTO.DeadlineBasesDto.RootElementDeadlineBases;
import API.DTO.DocumentExecutionStatesDto.CreateValueDocumentExecutionStates;
import API.DTO.DocumentExecutionStatesDto.RootDocumentExecutionStates;
import API.DTO.DocumentExecutionStatesDto.RootElementDocumentExecutionStates;
import API.DTO.EventStatesDto.CreateValueClassEventStates;
import API.DTO.EventStatesDto.RootElementEventStates;
import API.DTO.EventStatesDto.RootEventStates;
import API.DTO.ImageMarksDto.CreateValueImageMarks;
import API.DTO.ImageMarksDto.RootElementImageMarks;
import API.DTO.ImageMarksDto.RootImageMarks;
import API.DTO.ThemesDto.CreateValueClassThemes;
import API.DTO.ThemesDto.RootElementThemes;
import API.DTO.ThemesDto.RootThemes;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BasicApiOld {









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



    public BasicApiOld() {
    }




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



































    /**
     * Изменение провайдера
     * @param id - авторизуемого пользователя
     * Maybe later
     * **/

    /**
     *Справочник "Срочность"
      **/










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

    /*public RootThemes getThemesList(int id){
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .get(API_THEMES).then()
                .log().all()
                .statusCode(200)
                .extract();
        RootThemes actualThemes = response.body().as(RootThemes.class);
        return actualThemes;
    }*/

    /**
     * Создание значение справочника
     *
     * @param id - id авторизованного пользователя
     * @param name - Название справочника
     * @param excluded - элемент исключения значения из справочника
     */

    /*public RootElementThemes createValueThemes(int id, String name, boolean excluded){
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
    }*/

    /**
     * Изменение значения справочника
     *
     * @param id - id авторизованного пользователя
     * @param idValue - id значения , в котором изменяется значение
     * @param name - Название справочника
     * @param excluded - элемент исключения значения из справочника
     * **/

   /* public RootElementThemes changeThemesValue(int id, int idValue, String name, boolean excluded){
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
    }*/

    /**
     * Удаление значения справочника
     *
     * @param id - авторизованного пользователя
     * @param idValue - id значения , которое будет удалено
     * **/

   /* public void deleteThemesValue(int id, int idValue) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .delete(API_THEMES + "/" + idValue).then()
                .log().all()
                .statusCode(204)
                .extract();
    }*/

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

    /*public RootEventStates getEventStatesList(int id){
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .get(API_EVENT_STATES).then()
                .log().all()
                .statusCode(200)
                .extract();
        RootEventStates actualEventStates = response.body().as(RootEventStates.class);
        return actualEventStates;
    }*/

    /**
     * Создание значение справочника
     *
     * @param id - id авторизованного пользователя
     * @param name - Название справочника
     * @param excluded - элемент исключения значения из справочника
     */

    /*public RootElementEventStates createValueEventStates(int id, String name, boolean excluded){
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

    *//**
     * Изменение значения справочника
     *
     * @param id - id авторизованного пользователя
     * @param idValue - id значения , в котором изменяется значение
     * @param name - Название справочника
     * @param excluded - элемент исключения значения из справочника
     * **//*

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
    }*/

    /**
     * Удаление значения справочника
     *
     * @param id - авторизованного пользователя
     * @param idValue - id значения , которое будет удалено
     * **/

    /*public void deleteEventStateValue(int id, int idValue) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .delete(API_EVENT_STATES + "/" + idValue).then()
                .log().all()
                .statusCode(204)
                .extract();
    }*/

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

    /*public RootAssignmentExecutionStates getAssignmentExecutionStatesList(int id){
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .get(API_ASSIGNMENT_EXECUTION_STATES).then()
                .log().all()
                .statusCode(200)
                .extract();
        RootAssignmentExecutionStates actualAssignmentExecutionStates= response.body().as(RootAssignmentExecutionStates.class);
        return actualAssignmentExecutionStates;
    }*/

    /**
     * Создание значение справочника
     *
     * @param id - id авторизованного пользователя
     * @param name - Название справочника
     * @param short_name - Краткое наименование справочника
     * @param excluded - элемент исключения значения из справочника
     *//*

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
    }*/

    /**
     * Изменение значения справочника
     *
     * @param id - id авторизованного пользователя
     * @param idValue - id значения , в котором изменяется значение
     * @param name - Название справочника
     * @param short_name - Краткое название справочника
     * @param excluded - элемент исключения значения из справочника
     * **/

    /*public RootElementAssignmentExecutionStates changeAssignmentExecutionStatesValue(int id, int idValue, String name, String short_name, boolean excluded){
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
    }*/

    /**
     * Удаление значения справочника
     *
     * @param id - авторизованного пользователя
     * @param idValue - id значения , которое будет удалено
     * **//*

    public void deleteAssignmentExecutionStatesValue(int id, int idValue) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .delete(API_ASSIGNMENT_EXECUTION_STATES + "/" + idValue).then()
                .log().all()
                .statusCode(204)
                .extract();
    }*/

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

    /*public RootDocumentExecutionStates getDocumentExecutionStatesList(int id){
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .get(API_DOCUMENT_EXECUTION_STATES).then()
                .log().all()
                .statusCode(200)
                .extract();
        RootDocumentExecutionStates actualDocumentExecutionStates= response.body().as(RootDocumentExecutionStates.class);
        return actualDocumentExecutionStates;
    }*/

   /* *//**
     * Создание значение справочника
     *
     * @param id - id авторизованного пользователя
     * @param name - Название справочника
     * @param short_name - Краткое наименование справочника
     * @param excluded - элемент исключения значения из справочника
     *//*

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
    }*/

    /**
     * Изменение значения справочника
     *
     * @param id - id авторизованного пользователя
     * @param idValue - id значения , в котором изменяется значение
     * @param name - Название справочника
     * @param short_name - Краткое название справочника
     * @param excluded - элемент исключения значения из справочника
     * **//*

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

    *//**
     * Удаление значения справочника
     *
     * @param id - авторизованного пользователя
     * @param idValue - id значения , которое будет удалено
     * **//*

    public void deleteDocumentExecutionStatesValue(int id, int idValue) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .delete(API_DOCUMENT_EXECUTION_STATES + "/" + idValue).then()
                .log().all()
                .statusCode(204)
                .extract();
    }*/

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

    /*public RootImageMarks getImageMarksList(int id){
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .get(API_IMAGE_MARKS).then()
                .log().all()
                .statusCode(200)
                .extract();
        RootImageMarks actualImageMarks= response.body().as(RootImageMarks.class);
        return actualImageMarks;
    }*/

    /**
     * Создание значение справочника
     *
     * @param id - id авторизованного пользователя
     * @param name - Название справочника
     * @param short_name - Краткое наименование справочника
     * @param excluded - элемент исключения значения из справочника
     */

   /* public RootElementImageMarks createValueImageMarks(int id, String name, String short_name, boolean excluded){
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
    }*/

    /**
     * Изменение значения справочника
     *
     * @param id - id авторизованного пользователя
     * @param idValue - id значения , в котором изменяется значение
     * @param name - Название справочника
     * @param short_name - Краткое название справочника
     * @param excluded - элемент исключения значения из справочника
     * **/

   /* public RootElementImageMarks changeImageMarksValue(int id, int idValue, String name, String short_name, boolean excluded){
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
    }*/

    /**
     * Удаление значения справочника
     *
     * @param id - авторизованного пользователя
     * @param idValue - id значения , которое будет удалено
     * **/

    /*public void deleteImageMarksValue(int id, int idValue) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .delete(API_IMAGE_MARKS + "/" + idValue).then()
                .log().all()
                .statusCode(204)
                .extract();
    }*/

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

    /*public RootDeadlineBases getDeadlineBasesList(int id){
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .get(API_DEADLINE_BASES).then()
                .log().all()
                .statusCode(200)
                .extract();
        RootDeadlineBases actualDeadlineBases= response.body().as(RootDeadlineBases.class);
        return actualDeadlineBases;
    }

    *//**
     * Создание значение справочника
     *
     * @param id - id авторизованного пользователя
     * @param name - Название справочника
     * @param short_name - Краткое наименование справочника
     * @param note - Заметка для справочника
     * @param duration_id - id срока поручения
     * @param deadline_only - элемент ...
     * @param excluded - элемент исключения значения из справочника
     *//*

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

    *//**
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
     * **//*

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

    *//**
     * Удаление значения справочника
     *
     * @param id - авторизованного пользователя
     * @param idValue - id значения , которое будет удалено
     * **//*

    public void deleteDeadlineBasesValue(int id, int idValue) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .delete(API_DEADLINE_BASES + "/" + idValue).then()
                .log().all()
                .statusCode(204)
                .extract();
    }

    *//**
     * POST request (reorder)
     * **/



}

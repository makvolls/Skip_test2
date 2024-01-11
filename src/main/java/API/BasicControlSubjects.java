package API;

import API.DTO.ControlSubjects.RootControlSubjects;

import API.DTO.ErrorsDTO.RootError;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BasicControlSubjects {
    public static String API_CONTROL_SUBJECTS = "http://api.skip.rtech.ru/v1/permissions/control_subjects";

    /**
     * Получение списка субъектов контроля
     * @param id - авторизуемого пользователя
     * **/

    public static RootControlSubjects getControlSubjects(int id){
        Response response = (Response) given()
                .header("Test-Authorization", id)
                .param("provider_id", "525e9f767da3000002000001")
                .get(API_CONTROL_SUBJECTS).then()
                .log().all()
                .statusCode(200)
                .extract();

        RootControlSubjects actualControlSubjects = response.as(RootControlSubjects.class);
        return actualControlSubjects;
    }

    /**
     * Получение сообщения об ошибке
     * - "доступ к ресурсу запрещен"
     * при GET запросе
     * **/
    public static RootError getErrorMessage(int id){
        RootError error = BasicApi.getErrorControlSubjects(API_CONTROL_SUBJECTS, id).as(RootError.class);
        return error;
    }

    /**
     * Создание элемента "Субъекты контроля"
     * @param id - id авторизуемого пользователя
     * @param name - значение параметра "Наименование"
     * @param fax_number - значение параметра "fax_number"
     * @param deleted - значение параметра "deleted"
     * @param provider_id - значение параметра "provider_id"
     * **/


    public static RootControlSubjects createControlSubjectAndGetList(int id, String name, String fax_number,
                                                                     String provider_id, boolean deleted){
        String requestBody = String.format(
                "{\"control_subjects\":[{\"name\": \"%s\",\"fax_number\":\"%s\",\"deleted\":%s,\"provider_id\":\"%s\"}]}"
                , name, fax_number, deleted, provider_id);
        RootControlSubjects listWithNewElement = BasicApi.putControlSubjects(API_CONTROL_SUBJECTS, id, requestBody)
                .body().as(RootControlSubjects.class);
        return listWithNewElement;
    }

    /**
     * Получение сообщения об ошибке - "Доступ к ресурсу запрещен"
     * при PUT запросе - при создании элемента (не передаем id в запросе)
     * **/

    public static RootError putErrorMessageWithoutId(int id, String name, String fax_number,
                                            String provider_id, boolean deleted){
        String requestBody = String.format(
                "{\"control_subjects\":[{\"name\": \"%s\",\"fax_number\":\"%s\",\"deleted\":%s,\"provider_id\":\"%s\"}]}"
                , name, fax_number, deleted, provider_id);
        RootError error = BasicApi.putErrorControlSubjects(API_CONTROL_SUBJECTS, id, requestBody).as(RootError.class);
        return error;
    }

    /** Получение сообщения об ошибке - "Доступ к ресурсу запрещен"
     * при PUT запросе - при редактировании или удалении элемента
     * (передаем id в запросе)
     * **/

    public static RootError putErrorMessageWithId(int id, int idValue, String name,
                                                  String fax_number, String provider_id,
                                                  boolean deleted){
        String requestBody = String
                .format("{\"control_subjects\":[{\"id\": %s,\"name\": \"%s\"," +
                                "\"fax_number\":\"%s\",\"deleted\":%s,\"provider_id\":\"%s\"}]}", idValue,
                        name, fax_number, deleted, provider_id);
        RootError error = BasicApi.putErrorControlSubjects(API_CONTROL_SUBJECTS, id, requestBody).as(RootError.class);
        return error;
    }



    /**
     * Редактирование элемента "Субъекты контроля"
     * @param id - id авторизуемого пользователя
     * @param idValue - id значения , в котором изменяется значение
     * @param name - значение параметра "Наименование"
     * @param fax_number - значение параметра "fax_number"
     * @param deleted - значение параметра "deleted"
     * @param provider_id - значение параметра "provider_id"
     * **/

    public static RootControlSubjects changeControlSubjectsAndGetList(int id, int idValue, String name,
                                                                      String fax_number, String provider_id,
                                                                      boolean deleted){
        String requestBody = String
                .format("{\"control_subjects\":[{\"id\": %s,\"name\": \"%s\"," +
                        "\"fax_number\":\"%s\",\"deleted\":%s,\"provider_id\":\"%s\"}]}", idValue,
                        name, fax_number, deleted, provider_id);
        RootControlSubjects listWithChangedElement = BasicApi.putControlSubjects(API_CONTROL_SUBJECTS, id, requestBody)
                .body().as(RootControlSubjects.class);
        return listWithChangedElement;
    }

    /**
     * Удаление элемента "Субъекты контроля"
     * @param id - id авторизуемого пользователя
     * @param idValue - id значения , которое будет удалено
     * @param provider_id - значение параметра "provider_id"
     * Значение параметра "deleted" всегда true
     * **/

    public static RootControlSubjects deleteControlSubjectElementAndGetList(int id, int idValue, String provider_id){
        String requestBody = String.format("{\"control_subjects\":[{\"id\": %s,\"deleted\": true," +
                "\"provider_id\":\"%s\"}]}", idValue, provider_id);
        RootControlSubjects listWithoutDeletedElement = BasicApi.putControlSubjects(
                API_CONTROL_SUBJECTS, id, requestBody).body().as(RootControlSubjects.class);
        return listWithoutDeletedElement;
    }


}

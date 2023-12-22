package API;

import API.BasicApi;
import API.DTO.ControlSubjects.CreateControlSubjects;
import API.DTO.ControlSubjects.RootControlSubjects;

import API.DTO.ControlSubjects.RootPutRequestControlSubjects;
import API.DTO.ErrorsDTO.RootError;
import API.DTO.RolesDto.RootRolesDto;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BasicControlSubjects {
    public static String API_CONTROL_SUBJECTS = "http://api.skip.rtech.ru/v1/permissions/control_subjects";

    /**
     * Получение списка субъектов контроля
     *
     * @param id - авторизуемого пользователя
     * **/

//    public static RootControlSubjects getControlSubjectsList(int id){
//        RootControlSubjects actualControlSubjects = BasicApi.get(API_CONTROL_SUBJECTS, id).
//                body().as(RootControlSubjects.class);
//        return actualControlSubjects;
//    }

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

//    public static RootControlSubjects createControlSubjects(int id, String name, String  fax_number,
//                                                            boolean deleted, String provider_id){
//        CreateControlSubjects requestData = new CreateControlSubjects(name, fax_number, provider_id, deleted);
//        requestData.setName(name);
//        requestData.setFax_number(fax_number);
//        requestData.setProvider_id(provider_id);
//        requestData.setDeleted(deleted);
//
//        RootControlSubjects createdElement = BasicApi.put(API_CONTROL_SUBJECTS, id, requestData)
//                .body().as(RootControlSubjects.class);
//        return  createdElement;
//    }
//
//    public static RootPutRequestControlSubjects createControlSubjectsArray(int id, String name, String fax_number,
//                                                                           boolean deleted, String provider_id){
//        CreateControlSubjects requestData = new CreateControlSubjects(name, fax_number
//        , provider_id, deleted);
//        requestData.setName(name);
//        requestData.setFax_number(fax_number);
//        requestData.setProvider_id(provider_id);
//        requestData.setDeleted(deleted);
//
//        RootPutRequestControlSubjects createdElement = BasicApi.put(API_CONTROL_SUBJECTS, id, requestData).body()
//                .as(RootPutRequestControlSubjects.class);
//        return createdElement;
//    }

    /**
     * PUT request for "Control Subjects"
     * for create a value
     * **/

    public static RootControlSubjects createControlSubjectsAndShowList(int id, String name, String fax_number,
                                                                       String provider_id, boolean deleted){
//        CreateControlSubjects bodyValues = new CreateControlSubjects(name, fax_number, provider_id, deleted);
//        bodyValues.setName(name);
//        bodyValues.setFax_number(fax_number);
//        bodyValues.setProvider_id(provider_id);
//        bodyValues.setDeleted(deleted);

        //RootPutRequestControlSubjects body = new RootPutRequestControlSubjects(bodyValues);
        //String requestTemplate = String.format("{\"control_subjects\":[{\"name\":%s,\"fax_number\":%s,\"deleted\":%s,\"provider_id\":%s}]}", name, fax_number, deleted, provider_id);
        String anotherRequestTemplate = String.format("{\"control_subjects\":[{\"name\": \"%s\",\"fax_number\":\"%s\",\"deleted\":%s,\"provider_id\":\"%s\"}]}", name, fax_number, deleted, provider_id);

        ExtractableResponse<Response> response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", id)
                .body(anotherRequestTemplate) // Possible it could be "body", I need to found out it
                .put(API_CONTROL_SUBJECTS).then()
                .log().all()
                .statusCode(200)
                .extract();
        RootControlSubjects listWithNewElement = response.body().as(RootControlSubjects.class);
        return listWithNewElement;
    }

    public static RootControlSubjects createControlSubjectAndGetList(int id, String name, String fax_number,
                                                                     String provider_id, boolean deleted){
        String requestBody = String.format(
                "{\"control_subjects\":[{\"name\": \"%s\",\"fax_number\":\"%s\",\"deleted\":%s,\"provider_id\":\"%s\"}]}"
                , name, fax_number, deleted, provider_id);
        RootControlSubjects listWithNewElement = BasicApi.putControlSubjects(API_CONTROL_SUBJECTS, id, requestBody)
                .body().as(RootControlSubjects.class);
        return listWithNewElement;
    }

//    public static RootControlSubjects createStrictSetControlSubject(int id){
//        String requestBody = "{\"control_subjects\":[{\"name\": \"!String creation\",\"fax_number\":\"1\",\"deleted\":false,\"provider_id\":\"525e9f767da3000002000001\"}]}";
//
//        ExtractableResponse<Response> response = given()
//                .header("Content-Type", "application/json")
//                .header("Test-Authorization", id)
//                .body(requestBody)
//                .put(API_CONTROL_SUBJECTS).then()
//                .log().all()
//                .statusCode(200)
//                .extract();
//        RootControlSubjects listWithNewElement = response.body().as(RootControlSubjects.class);
//        return listWithNewElement;
//    }
//    {
//        "":[
//        {
//           "":"",
//           "":""
//        }
//        ]
//    }
}

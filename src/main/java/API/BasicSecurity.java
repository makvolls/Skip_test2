package API;

import API.DTO.ErrorsDTO.RootErrorNoRights;
import API.DTO.ErrorsDTO.RootNameError;
import API.DTO.ErrorsDTO.RootShortNameError;
import API.DTO.EventsDto.RequestEventsDto;
import API.DTO.SecurityClassificationsDto.RequestSecurityClassificationDto;
import API.DTO.SecurityClassificationsDto.RootPostSecurityDto;
import API.DTO.SecurityClassificationsDto.RootSecurityDto;

import static io.restassured.RestAssured.given;

public class BasicSecurity {
    public static String API_SECURITY_CLASSIFICATIONS = "http://api.skip.rtech.ru/v1/classifiers/security_classifications";

    /**
     * Получение списка грифов
     *
     * @param id - id авторизуемого пользователя
     *//*

    public RootSecurityDto getSecurity(int id) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .get(API_SECURITY_CLASSIFICATIONS).then()
                .log().all()
                .statusCode(200)
                .extract();

        RootSecurityDto actualSecurity = response.body().as(RootSecurityDto.class);
        return actualSecurity;
    }*/
    /**
     * Получение списка грифов
     *
     * @param id - id авторизуемого пользователя
     */

    public static RootSecurityDto getSecurity(int id){
        RootSecurityDto actualSecurity = BasicApi.get(API_SECURITY_CLASSIFICATIONS, id).as(RootSecurityDto.class);
        return actualSecurity;
    }

    public static RootNameError postErrNameSecurity(int id,String name,String short_name,boolean excluded){
        RequestSecurityClassificationDto value = new RequestSecurityClassificationDto(name,short_name, excluded);
        value.setName(name);
        value.setShort_name(short_name);
        value.setExcluded(excluded);
        RootNameError createdErValue = BasicApi.postError(API_SECURITY_CLASSIFICATIONS, id, value).body().as(RootNameError.class);
        return createdErValue;
    }
    public static RootPostSecurityDto postSecurity(int id, String name, String short_name, boolean excluded){
        RequestSecurityClassificationDto value = new RequestSecurityClassificationDto(name,short_name, excluded);
        value.setName(name);
        value.setShort_name(short_name);
        value.setExcluded(excluded);
        RootPostSecurityDto createdErValue = BasicApi.post(API_SECURITY_CLASSIFICATIONS, id, value).body().as(RootPostSecurityDto.class);
        return createdErValue;
    }
    public static RootShortNameError postErrShortNameSecurity(int id, String name, String short_name, boolean excluded){
        RequestSecurityClassificationDto value = new RequestSecurityClassificationDto(name,short_name, excluded);
        value.setName(name);
        value.setShort_name(short_name);
        value.setExcluded(excluded);
        RootShortNameError createdErValue = BasicApi.postError(API_SECURITY_CLASSIFICATIONS, id, value).body().as(RootShortNameError.class);
        return createdErValue;
    }
    public static RootErrorNoRights postErrNoRightsSecurity(int id, String name, String short_name, boolean excluded){
        RequestSecurityClassificationDto value = new RequestSecurityClassificationDto(name,short_name, excluded);
        value.setName(name);
        value.setShort_name(short_name);
        value.setExcluded(excluded);
        RootErrorNoRights createdErValue = BasicApi.postErrorNoRights(API_SECURITY_CLASSIFICATIONS, id, value).body().as(RootErrorNoRights.class);
        return createdErValue;
    }

    public static void deleteSecurity(int id, int idEvents) {
        BasicApi.delete(API_SECURITY_CLASSIFICATIONS + "/" + idEvents, id);
        return;
    }
}

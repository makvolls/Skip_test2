package API;

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

}

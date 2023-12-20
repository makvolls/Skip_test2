package API;

import API.DTO.EventsDto.RootListDto;

import static io.restassured.RestAssured.given;

public class BasicEvents {
    public static String API_EVENT = "http://api.skip.rtech.ru/v1/classifiers/events";


    /**
     * Получение списка событий
     *
     * @param id - id авторизованного пользователя
     */
    public static RootListDto getListEvents(int id){
        RootListDto actualList = BasicApi.get(API_EVENT, id).as(RootListDto.class);
        return actualList;
    }

    /**
     * Получение списка событий
     *
     * @param id - id авторизованного пользователя
     *//*

    public static RootListDto getListEvents(int id) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .get(API_EVENT).then()
                .log().all()
                .statusCode(200)
                .extract();

        RootListDto actualList = response.body().as(RootListDto.class);
        return actualList;

    }*/

}

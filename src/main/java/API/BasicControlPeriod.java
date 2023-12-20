package API;

import API.DTO.ControlPeriodStatesDto.RootControlDto;

import static io.restassured.RestAssured.given;

public class BasicControlPeriod {
    public static String API_CONTROL_PERIOD = "http://api.skip.rtech.ru/v1/classifiers/control_period_states";

    /**
     * Получение контрольного периода
     *
     * @param id - id авторизуемого пользователя
     * @param sort - сортировка по name, short_name, priority
     * @param sort_ord - сортировка по desc, asc
     *//*

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
    }*/

    /**
     * Получение контрольного периода
     *
     * @param id - id авторизуемого пользователя
     * @param sort - сортировка по name, short_name, priority
     * @param sort_ord - сортировка по desc, asc
     */

    public static RootControlDto getControlPeriod (int id, String sort, String sort_ord){

        RootControlDto actualControl = BasicApi.getSort(API_CONTROL_PERIOD, id, sort, sort_ord).as(RootControlDto.class);
        return actualControl;
    }
}

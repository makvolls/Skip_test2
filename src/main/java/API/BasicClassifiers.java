package API;

import API.DTO.DirectoryDto.RootDirectoryDto;

import static io.restassured.RestAssured.given;

public class BasicClassifiers {
    public static String API_CLASSIFIERS = "http://api.skip.rtech.ru/v1/classifiers";



//    public RootDirectoryDto getClassifiers(int id) {
//        ExtractableResponse<Response> response = given()
//                .header("Test-Authorization", id)
//                .get(API_CLASSIFIERS).then()
//                .log().all()
//                .statusCode(200)
//                .extract();
//
//        RootDirectoryDto actualClassifiers = response.body().as(RootDirectoryDto.class);
//        return actualClassifiers;
//    }
    /**
     * Получение списка справочников
     *
     * @param id - id авторизуемого пользователя
     */
    public RootDirectoryDto getClassifiers(int id) {

        RootDirectoryDto actualClassifiers = BasicApi.get(API_CLASSIFIERS, id).body().as(RootDirectoryDto.class);
        return actualClassifiers;
    }
}

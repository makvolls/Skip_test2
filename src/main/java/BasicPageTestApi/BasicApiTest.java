package BasicPageTestApi;

import DTO.ControlPeriodStatesDto.RootControlDto;
import DTO.DirectoryDto.RootDirectoryDto;
import DTO.SecurityClassificationsDto.RootSecurityDto;
import DTO.UserDto.RootUserDto;
import DTO.EventsDto.RootListDto;
import DTO.RolesDto.RootRolesDto;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BasicApiTest {

    public static String API_USER_GET = "http://api.skip.rtech.ru/v1/permissions/users";
    public static String API_EVENT_GET = "http://api.skip.rtech.ru/v1/classifiers/events";
    public static String API_CLASSIFIERS_GET = "http://api.skip.rtech.ru/v1/classifiers";
    public static String API_CONTROL_PERIOD_GET = "http://api.skip.rtech.ru/v1/classifiers/control_period_states";
    public static String API_ROLES_GET = "http://api.skip.rtech.ru/v1/permissions/roles";
    public static String API_ROLES_TO_ID_GET = "http://api.skip.rtech.ru/v1/permissions/roles";
    public static String API_SECURITY_CLASSIFICATIONS_GET = "http://api.skip.rtech.ru/v1/classifiers/security_classifications";

    public BasicApiTest() {
    }



    /**
     * Получение пользователя по id
     *
     * @idAut - авторизованного пользователя
     * @id - получаемого пользователя
     */

    public RootUserDto getUser(int idAut, int id){
         ExtractableResponse<Response> response = given()
                .header("Test-Authorization", idAut)
                .get(API_USER_GET + "/" + id).then()
                .log().all()
                .statusCode(200)
                .extract();
        RootUserDto actualUser = response.body().as(RootUserDto.class);

        return actualUser;

    }

    /**
     * Получение списка событий
     *
     * @id - получаемого пользователя
     */

    public RootListDto getListEvents(int id) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .get(API_EVENT_GET).then()
                .log().all()
                .statusCode(200)
                .extract();

         RootListDto actualList = response.body().as(RootListDto.class);
        return actualList;

    }
    /**
     * Получение списка справочников
     *
     * @id - авторизуемого пользователя
     */

    public RootDirectoryDto getClassifiers( int id){
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .get(API_CLASSIFIERS_GET).then()
                .log().all()
                .statusCode(200)
                .extract();

        RootDirectoryDto actualClassifiers = response.body().as(RootDirectoryDto.class);
        return actualClassifiers;
    }

    /**
     * Получение контрольного периода
     *
     * @id - авторизуемого пользователя
     * @sort - сортировка по name, short_name, priority
     * @sort_ord - сортировка по desc, asc
     */

    public RootControlDto getControlPeriod(int id, String sort, String sort_ord){
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .param("sort_by", sort)
                .param("sort_order", sort_ord)
                .get(API_CONTROL_PERIOD_GET).then()
                .log().all()
                .statusCode(200)
                .extract();

        RootControlDto actualControl = response.body().as(RootControlDto.class);
        return actualControl;
    }
    /**
     * Получение списка ролей
     *
     * @id - авторизуемого пользователя
     */

    public RootRolesDto getRoles(int id){
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .get(API_ROLES_GET).then()
                .log().all()
                .statusCode(200)
                .extract();

        RootRolesDto actualRoles = response.body().as(RootRolesDto.class);
        return actualRoles;

    }
    /**
     * Получение списка грифов
     *
     * @id - авторизуемого пользователя
     */

    public RootSecurityDto getSecurity(int id){
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .get(API_SECURITY_CLASSIFICATIONS_GET).then()
                .log().all()
                .statusCode(200)
                .extract();

        RootSecurityDto actualSecurity = response.body().as(RootSecurityDto.class);
        return actualSecurity;
    }









    }

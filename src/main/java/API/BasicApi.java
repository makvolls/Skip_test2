package API;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BasicApi {

    static public ExtractableResponse<Response> get(String path, int id) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .get(path).then()
                .log().all()
                .statusCode(200)
                .extract();
        return response;
    }



    static public ExtractableResponse<Response> getSort(String path, int id, String sort, String sort_ord) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .param("sort_by", sort)
                .param("sort_order", sort_ord)
                .get(path).then()
                .log().all()
                .statusCode(200)
                .extract();
        return response;
    }

    static public ExtractableResponse<Response> getError(String path, int id) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .get(path).then()
                .log().all()
                //.statusCode(403)
                .extract();
        return response;
    }

    /**
     * @ Method for "Control Subjects"
     * with parameter - provider_id
     * **/

    static public ExtractableResponse<Response> getErrorControlSubjects(String path, int id) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .param("provider_id","525e9f767da3000002000001")
                .get(path).then()
                .log().all()
                .statusCode(403)
                .extract();
        return response;
    }

    static public ExtractableResponse<Response> post(String path, int id, Object request) {
        ExtractableResponse<Response> response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", id)
                .body(request)
                .post(path).then()
                .log().all()
                .statusCode(201)
                .extract();
        return response;

    }
    static public ExtractableResponse<Response> postError(String path, int id, Object request) {
        ExtractableResponse<Response> response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", id)
                .body(request)
                .post(path).then()
                .log().all()
                //.statusCode(422)
                .extract();
        return response;
    }

    static public ExtractableResponse<Response> put(String path, int id, Object request) {
        ExtractableResponse<Response> response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", id)
                .body(request)
                .put(path).then()
                .log().all()
                .statusCode(200)
                .extract();
        return response;


    }

    /**
     *  PUT request for "Control Subjects"
     * **/

    static public ExtractableResponse<Response> putControlSubjects(String path, int id, String requestBody){
        ExtractableResponse<Response> response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", id)
                .body(requestBody)
                .put(path).then()
                .log().all()
                .statusCode(200)
                .extract();
        return response;
    }

    static public ExtractableResponse<Response> putErrors(String path, int id, Object request) {
        ExtractableResponse<Response> response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", id)
                .body(request)
                .put(path).then()
                .log().all()
                //.statusCode(422)
                .extract();
        return response;


    }

    public static void delete(String path, int id) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .delete(path).then()
                .log().all()
                .statusCode(204)
                .extract();
        return;

    }

    public static ExtractableResponse<Response> deleteNotFound(String path, int id) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .delete(path).then()
                .log().all()
                //.statusCode(404)
                .extract();
        return response;
    }
}

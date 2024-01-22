package API;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BasicApi {

    /**
     * GET request
     * **/

    static public ExtractableResponse<Response> get(String path, int id) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .get(path).then()
                .log().all()
                .statusCode(200)
                .extract();
        return response;
    }

    /**
     * Get request and find value depended on "control_subject_id" value
     * **/

    static public ExtractableResponse<Response> getAndSearch(String path, int id, int control_subject_id){
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .param("control_subject_id",control_subject_id)
                .get(path).then()
                .log().all()
                .statusCode(200)
                .extract();
        return response;
    }

    /**
     * Get request and find value depended on "control_subject_id"
     * value with error - 422
     * **/

    static public ExtractableResponse<Response> getAndSearchError(String path, int id, int[] control_subject_id){
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .param("control_subject_id",control_subject_id)
                .get(path).then()
                .log().all()
                .extract();
        return response;
    }

    /**
     * GET request with sorting capabilities
     * **/

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

    /**
     * Get error message while try to get user data,
     * status code - 403
     * **/

    static public ExtractableResponse<Response> getError(String path, int id) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .get(path).then()
                .log().all()
                .statusCode(403)
                .extract();
        return response;
    }
    static public ExtractableResponse<Response> getErrorNoFound(String path, int id) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .get(path).then()
                .log().all()
                .statusCode(404)
                .extract();
        return response;
    }

    /**
     * Method "Control Subjects" for GET request
     * and getting error message "Not enough rights"
     * status code - 403
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

    /**
     * POST request
     * **/

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

    static public ExtractableResponse<Response> postCalcDeadlines(String path, int id, Object request) {
        ExtractableResponse<Response> response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", id)
                .body(request)
                .post(path).then()
                .log().all()
                .statusCode(200)
                .extract();
        return response;

    }

    /**
     *  POST request and getting error with status code - 422
     * **/

    static public ExtractableResponse<Response> postError(String path, int id, Object request) {
        ExtractableResponse<Response> response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", id)
                .body(request)
                .post(path).then()
                .log().all()
                .statusCode(422)
                .extract();
        return response;
    }

    static public ExtractableResponse<Response> postErrorWithoutBody(String path, int id) {
        ExtractableResponse<Response> response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", id)
                .post(path).then()
                .log().all()
                .statusCode(422)
                .extract();
        return response;
    }

    /**
     *  POST request and getting error with status code - 403
     * **/

    static public ExtractableResponse<Response> postErrorAccessDenied(String path, int id, Object request) {
        ExtractableResponse<Response> response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", id)
                .body(request)
                .post(path).then()
                .log().all()
                .statusCode(403)
                .extract();
        return response;
    }


    /**
     * Method "Control Subjects" for PUT request
     * and getting error message "Not enough rights"
     * status code - 403
     * **/

    static public ExtractableResponse<Response> putErrorControlSubjects(String path, int id, String requestBody){
        ExtractableResponse<Response> response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", id)
                .body(requestBody)
                .put(path).then()
                .log().all()
                .statusCode(403)
                .extract();
        return response;
    }

    /**
     * PUT request
     * **/

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
    static public ExtractableResponse<Response> putNoBody(String path, int id) {
        ExtractableResponse<Response> response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", id)
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

    /**
     * PUT request and get error message
     * "Already exist" , "Cannot be empty"
     * and status - 422
     * **/

    static public ExtractableResponse<Response> putControlSubjectsAndGetError(String path, int id, String requestBody){
        ExtractableResponse<Response> response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", id)
                .body(requestBody)
                .put(path).then()
                .log().all()
                .statusCode(422)
                .extract();
        return response;
    }

    /**
     * PUT request and get error message
     * with status 500
     * **/

    static public ExtractableResponse<Response> putErrorFiveHundred(String path, int id, Object request) {
        ExtractableResponse<Response> response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", id)
                .body(request)
                .put(path).then()
                .log().all()
                .statusCode(500)
                .extract();
        return response;
    }

    /**
     * PUT request without error assert
     * **/

    static public ExtractableResponse<Response> putErrors(String path, int id, Object request) {
        ExtractableResponse<Response> response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", id)
                .body(request)
                .put(path).then()
                .log().all()
                .statusCode(422)
                .extract();
        return response;


    }

    /**
     * DELETE request with status code 204
     * **/

    public static void delete(String path, int id) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .delete(path).then()
                .log().all()
                .statusCode(204)
                .extract();
        return;

    }

    /**
     * Check deleted element and get error message with status code 404
     * **/

    public static ExtractableResponse<Response> deleteNotFound(String path, int id) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .delete(path).then()
                .log().all()
                .statusCode(404)
                .extract();
        return response;
    }
    public static ExtractableResponse<Response> deleteErrorNoRights(String path, int id) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .delete(path).then()
                .log().all()
                .statusCode(403)
                .extract();
        return response;
    }


    static public ExtractableResponse<Response> postErrorNoRights(String path, int id, Object request) {
        ExtractableResponse<Response> response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", id)
                .body(request)
                .post(path).then()
                .log().all()
                .statusCode(403)
                .extract();
        return response;
    }


    static public ExtractableResponse<Response> putErrorsNoRights(String path, int id, Object request) {
        ExtractableResponse<Response> response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", id)
                .body(request)
                .put(path).then()
                .log().all()
                .statusCode(403)
                .extract();
        return response;


    }
    static public ExtractableResponse<Response> putErrorsNoEventStatesId(String path, int id, Object request) {
        ExtractableResponse<Response> response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", id)
                .body(request)
                .put(path).then()
                .log().all()
                .statusCode(404)
                .extract();
        return response;


    }

    static public ExtractableResponse<Response> getErrorUnauthorized(String path, int id) {
        ExtractableResponse<Response> response = given()
                .header("Test-Authorization", id)
                .get(path).then()
                .log().all()
                .statusCode(401)
                .extract();
        return response;
    }
}

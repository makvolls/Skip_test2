package API;

import API.DTO.ErrorsDTO.RootErrorNoRights;
import API.DTO.ErrorsDTO.RootNameError;
import API.DTO.PeriodicityDto.*;
import API.DTO.PeriodicityDto.RootPerWithoutExcluded;

import static io.restassured.RestAssured.given;

public class BasicPeriodicity {

    public static String API_GET_PERIODICITY = "http://api.skip.rtech.ru/v1/classifiers/periodicity";


    public static RootPeriodicityGet getPeriodicity(int id, String name, String sort, String sort_ord) {
        RootPeriodicityGet actualPeriodicity = BasicApi.getSort(API_GET_PERIODICITY, id, sort, sort_ord).body().as(RootPeriodicityGet.class);
        return actualPeriodicity;
    }


    // public static RootPeriodicityGet getPeriodicity(int id, String name, String sort, String sort_ord) {
    //    ExtractableResponse<Response> response = given()
    //          .header("Test-Authorization", id)
    //           .param("name", name)
    //         .param("sort_by", sort)
    //         .param("sort_order", sort_ord)
    //        .get(API_GET_PERIODICITY).then()
    //        .log().all()
    //       .statusCode(200)
    //        .extract();
    //   RootPeriodicityGet actualPeriodicity = response.body().as(RootPeriodicityGet.class);
    //   return actualPeriodicity;
    // }

    public static RootPerWithDataPut updatePeriodicity(int id, int idPeriodicity, String name, boolean excluded) {
        RootPeriodicityPut periodicity = new RootPeriodicityPut(name, excluded);
        periodicity.setName(name);
        periodicity.setExcluded(excluded);
        RootPerWithDataPut actualPeriodicity = BasicApi.put(API_GET_PERIODICITY + "/" + idPeriodicity, id, periodicity)
                .body().as(RootPerWithDataPut.class);
        return actualPeriodicity;
    }

    //public static RootPerWithDataPut updatePeriodicity(int id, int idPeriodicity, String name, boolean excluded) {
    //  RootPeriodicityPut value = new RootPeriodicityPut(name, excluded);
    //  value.setName(name);
    //  value.setExcluded(excluded);
    //  ExtractableResponse<Response> response = given()
    //       .header("Content-Type", "application/json")
    //     .header("Test-Authorization", id)
    //   .body(value)
    //    .put(API_GET_PERIODICITY + "/" + idPeriodicity)
    //    .then()
    //   .log().all()
    //   .statusCode(200)
    //   .extract();


    //    RootPerWithDataPut updatePeriodicity = response.body().as(RootPerWithDataPut.class);
    //   return updatePeriodicity;

    //  }

    public static RootNameError createErrorPeriodicity(int id, int idPeriodicity, String name, boolean excluded) {
        RootPeriodicityPut periodicity = new RootPeriodicityPut(name, excluded);
        periodicity.setName(name);
        periodicity.setExcluded(excluded);
        RootNameError periodicityNameError = BasicApi.putErrors(API_GET_PERIODICITY + "/" + idPeriodicity,
                        id, periodicity)
                .as(RootNameError.class);
        return periodicityNameError;
    }

    //public static RootPeriodicityNameError createErrorPeriodicity(int id,int idPeriodicity, String name, boolean excluded){
    // RootPeriodicityPut periodicity = new RootPeriodicityPut(name, excluded);
    //   periodicity.setName(name);
    //  periodicity.setExcluded(excluded);
    //   ExtractableResponse<Response> response = given()
    //         .header("Content-Type", "application/json")
    //         .header("Test-Authorization", id)
    //       .body(periodicity)
    //       .put(API_GET_PERIODICITY + "/" + idPeriodicity).then()
    //      .log().all()
    //     .statusCode(422)
    //     .extract();

    // RootPeriodicityNameError periodicityNameError = response.body().as(RootPeriodicityNameError.class);
    //    return periodicityNameError;
    // }

    /*public static RootPeriodicityExistsError createExistsPerError(int id, int idPeriodicity, String name, boolean excluded) {
        RootPeriodicityPut periodicity = new RootPeriodicityPut(name, excluded);
     !!!   periodicity.setName(name);
        periodicity.setExcluded(excluded);
        RootPeriodicityExistsError periodicityExistsError = BasicApi.putErrors(API_GET_PERIODICITY + "/" + idPeriodicity,
                        id, periodicity)
                .body().as(RootPeriodicityExistsError.class);
        return periodicityExistsError;
    }

     */

    //  public static RootPeriodicityExistsError createExistsPerError(int id,int idPeriodicity, String name, boolean excluded){
    //   RootPeriodicityPut periodicity=new RootPeriodicityPut(name, excluded);
    //    periodicity.setName(name);
    //   periodicity.setExcluded(excluded);
    //    ExtractableResponse<Response> response = given()
    //          .header("Content-Type", "application/json")
    //          .header("Test-Authorization", id)
    //         .body(periodicity)
    //        .put(API_GET_PERIODICITY + "/" + idPeriodicity).then()
    //      .log().all()
    //       .statusCode(422)
    //       .extract();
    //   RootPeriodicityExistsError periodicityExistsError=response.body().as(RootPeriodicityExistsError.class);
    //   return periodicityExistsError;
    // }

    public static RootErrorNoRights createNoRightsError(int id, int idPeriodicity, String name, boolean excluded) {
        RootPeriodicityPut periodicity = new RootPeriodicityPut(name, excluded);
        periodicity.setName(name);
        periodicity.setExcluded(excluded);
        RootErrorNoRights error = BasicApi.putErrorsNoRights(API_GET_PERIODICITY + "/" + idPeriodicity, id,
                        periodicity)
                .as(RootErrorNoRights.class);
        return error;
    }
    //  public static RootPeriodicityNoRights createNoRightsError(int id,int idPeriodicity, String name, boolean excluded){
    //     RootPeriodicityPut periodicity=new RootPeriodicityPut(name, excluded);
    //   periodicity.setName(name);
    //   periodicity.setExcluded(excluded);
    //  ExtractableResponse<Response> response = given()
    //      .header("Content-Type", "application/json")
    //     .header("Test-Authorization", id)
    //   .body(periodicity)
    //   .put(API_GET_PERIODICITY + "/" + idPeriodicity).then()
    //   .log().all()
    //   .statusCode(403)
    //   .extract();
    //   RootPeriodicityNoRights periodicityNoRights=response.body().as(RootPeriodicityNoRights.class);
    //  return periodicityNoRights;

    //  }

    public static RootPerWithDataPut createWithoutExcluded(int id, int idPeriodicity, String name) {
        RootPerWithoutExcluded periodicity = new RootPerWithoutExcluded(name);
        periodicity.setName(name);
        RootPerWithDataPut createWithoutExcluded = BasicApi.put(API_GET_PERIODICITY + "/" + idPeriodicity, id, periodicity)
                .body().as(RootPerWithDataPut.class);
        return createWithoutExcluded;
    }


    //    public static RootPerWithDataPut createWithoutExcluded ( int id, int idPeriodicity, String name){
    //   RootPerWithoutExcluded periodicity = new RootPerWithoutExcluded(name);
    //        periodicity.setName(name);
    //      ExtractableResponse<Response> response = given()
    //           .header("Content-Type", "application/json")
    //            .header("Test-Authorization", id)
    //          .body(periodicity)
    //        .put(API_GET_PERIODICITY + "/" + idPeriodicity).then()
    //         .log().all()
    //      .statusCode(200)
    //        .extract();
    //   RootPerWithDataPut createWithoutExcluded = response.body().as(RootPerWithDataPut.class);
    //  return createWithoutExcluded;
    // }


    public static RootNameError postPeriodicityNameError(int id, String name, boolean excluded) {
        RootPeriodicityPut periodicity = new RootPeriodicityPut(name, excluded);
        periodicity.setName(name);
        periodicity.setExcluded(excluded);
        RootNameError postPeriodicity = BasicApi.postError(API_GET_PERIODICITY, id, periodicity)
                .as(RootNameError.class);
        return postPeriodicity;
    }

    public static RootPeriodicityPostDto postPeriodicity(int id, String name, boolean excluded) {
        RootPeriodicityPut periodicity = new RootPeriodicityPut(name, excluded);
        periodicity.setName(name);
        periodicity.setExcluded(excluded);
        RootPeriodicityPostDto postPer = BasicApi.post(API_GET_PERIODICITY, id, periodicity)
                .as(RootPeriodicityPostDto.class);
        return postPer;
    }

    public static RootNameError postPeriodicityExistError(int id, String name, boolean excluded) {
        RootPeriodicityPut periodicity = new RootPeriodicityPut(name, excluded);
        periodicity.setName(name);
        periodicity.setExcluded(excluded);
        RootNameError postPer = BasicApi.postError(API_GET_PERIODICITY, id, periodicity)
                .as(RootNameError.class);
        return postPer;
    }

    public static RootErrorNoRights PostNoRightsError(int id, String name, boolean excluded) {
        RootPeriodicityPut periodicity = new RootPeriodicityPut(name, excluded);
        periodicity.setName(name);
        periodicity.setExcluded(excluded);
        RootErrorNoRights error = BasicApi.postErrorNoRights(API_GET_PERIODICITY, id, periodicity)
                .as(RootErrorNoRights.class);
        return error;
    }

    public static void deletePeriodicity(int id, int idPeriodicity) {
        BasicApi.delete(API_GET_PERIODICITY + "/" + idPeriodicity, id);
        return;
    }

    public static RootPeriodicityPostDto postPerWithoutExcluded(int id, String name ){
        RootPerWithoutExcluded periodicity=new RootPerWithoutExcluded(name);
        periodicity.setName(name);
        RootPeriodicityPostDto createPer=BasicApi.post(API_GET_PERIODICITY, id, periodicity).as(RootPeriodicityPostDto.class);
        return createPer;
    }

}
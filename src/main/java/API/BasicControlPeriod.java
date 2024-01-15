package API;

import API.DTO.ControlPeriodStatesDto.*;
import API.DTO.ControlPeriodStatesDto.*;
import API.DTO.ErrorsDTO.ControlPeriodErrorsDto.*;
import API.DTO.ErrorsDTO.RootErrorNameShortName;
import API.DTO.ErrorsDTO.RootErrorNoRights;
import API.DTO.ErrorsDTO.RootNameError;

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
     * @param id       - id авторизуемого пользователя
     * @param sort     - сортировка по name, short_name, priority
     * @param sort_ord - сортировка по desc, asc
     */

    public static RootControlDto getControlPeriod(int id, String sort, String sort_ord) {

        RootControlDto actualControl = BasicApi.getSort(API_CONTROL_PERIOD, id, sort, sort_ord).as(RootControlDto.class);
        return actualControl;
    }

    public static RootPostControlDto postControlPeriod(int id, String name, String short_name, boolean excluded) {
        RootCreateControlDto controlPeriod = new RootCreateControlDto(name, short_name, excluded);
        controlPeriod.setName(name);
        controlPeriod.setShort_name(short_name);
        controlPeriod.setExcluded(excluded);
        RootPostControlDto postControl = BasicApi.post(API_CONTROL_PERIOD, id, controlPeriod).as(RootPostControlDto.class);
        return postControl;
    }

    public static void deleteControlPeriod(int id, int idControlPeriod) {
        BasicApi.delete(API_CONTROL_PERIOD + "/" + idControlPeriod, id);
        return;
    }

    public static RootErrorNameShortName postErrNameShControl(int id, String name, String short_name,
                                                              boolean excluded) {
        RootCreateControlDto controlPeriod = new RootCreateControlDto(name, short_name, excluded);
        controlPeriod.setName(name);
        controlPeriod.setShort_name(short_name);
        controlPeriod.setExcluded(excluded);
        RootErrorNameShortName postControlErrNS = BasicApi.postError(API_CONTROL_PERIOD, id, controlPeriod)
                .as(RootErrorNameShortName.class);
        return postControlErrNS;
    }

    public static RootNameError postErrNameCp(int id, String name, String short_name,
                                              boolean excluded) {
        RootCreateControlDto controlPeriod = new RootCreateControlDto(name, short_name, excluded);
        controlPeriod.setName(name);
        controlPeriod.setShort_name(short_name);
        controlPeriod.setExcluded(excluded);
        RootNameError postCpErrName = BasicApi.postError(API_CONTROL_PERIOD, id, controlPeriod).as(RootNameError.class);
        return postCpErrName;

    }

    public static  RootPutCpsDto putCps(int id, int idCps, String name, String short_name,
                                       boolean excluded) {
        RootCreateControlDto cps = new  RootCreateControlDto(name, short_name, excluded);
        cps.setName(name);
        cps.setShort_name(short_name);
        cps.setExcluded(excluded);
        RootPutCpsDto putCps = BasicApi.put(API_CONTROL_PERIOD + "/" + idCps, id, cps).as( RootPutCpsDto.class);
        return putCps;


    }

    public static RootNameError putErCps(int id, int idCps, String name, String short_name,
                                               boolean excluded) {
        RootCreateControlDto cps = new  RootCreateControlDto(name, short_name, excluded);
        cps.setName(name);
        cps.setShort_name(short_name);
        cps.setExcluded(excluded);
        RootNameError putErCps = BasicApi.putErrors(API_CONTROL_PERIOD + "/" + idCps, id, cps).as(RootNameError.class);
        return putErCps;
    }

    public static RootCprErrorPutShortName putErCpsSn(int id, int idCps, String name, String short_name,
                                                      boolean excluded) {
        RootCreateControlDto cps = new  RootCreateControlDto(name, short_name, excluded);
        cps.setName(name);
        cps.setShort_name(short_name);
        cps.setExcluded(excluded);
        RootCprErrorPutShortName putErCpsSn = BasicApi.putErrors(API_CONTROL_PERIOD + "/" + idCps, id, cps).as(RootCprErrorPutShortName.class);
        return putErCpsSn;

    }
    public static RootCpsErrorPutNs putErrCpsNs(int id, int idCps, String name, String short_name,
                                                boolean excluded){
        RootCreateControlDto cps=new  RootCreateControlDto(name, short_name, excluded);
        cps.setName(name);
        cps.setShort_name(short_name);
        cps.setExcluded(excluded);
        RootCpsErrorPutNs putErCpsNs=BasicApi.putErrors(API_CONTROL_PERIOD + "/" + idCps, id, cps)
                .as(RootCpsErrorPutNs.class);
        return putErCpsNs;
    }

    public static RootErrorNoRights putErrCpsNoRights(int id, int idCps, String name, String short_name,
                                                      boolean excluded){
        RootCreateControlDto cps=new  RootCreateControlDto(name, short_name, excluded);
        cps.setName(name);
        cps.setShort_name(short_name);
        cps.setExcluded(excluded);
        RootErrorNoRights putErrCpsNr=BasicApi.putErrorsNoRights(API_CONTROL_PERIOD + "/" + idCps, id, cps)
                .as(RootErrorNoRights.class);
        return putErrCpsNr;
    }

}
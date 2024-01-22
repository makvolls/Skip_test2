package API;

import API.DTO.CalcDeadlinesDto.*;
import API.DTO.ErrorsDTO.CalcDeadlinesErrors.*;
import API.DTO.ErrorsDTO.RootErrorNoRights;

public class BasicCalcDeadlines {

    public static String API_CALC_DEADLINES = "http://api.skip.rtech.ru/v1/assignments/calc_deadlines";

    public static RootStartDateDeriodicityOdError postCalcDeadlinesErr(int id) {

        RootStartDateDeriodicityOdError err = BasicApi.postErrorWithoutBody(API_CALC_DEADLINES, id).body().as(RootStartDateDeriodicityOdError.class);
        return err;
    }

    public static RootCalcDeadlinesDto postCalcDeadlines(int id,String start_date, int periodicity_id, int period_in_days) {
        RequestCalcDeadlinesDto value = new RequestCalcDeadlinesDto(start_date,periodicity_id,period_in_days);
        value.setPeriodicity_id(periodicity_id);
        value.setStart_date(start_date);
        value.setPeriod_in_days(period_in_days);
        RootCalcDeadlinesDto valueCd = BasicApi.postCalcDeadlines(API_CALC_DEADLINES, id, value).body().as(RootCalcDeadlinesDto.class);
        return valueCd;

    }

    public static RootPeriodInDaysError postCalcDeadlinesError(int id,String start_date, int periodicity_id) {
        RequestCalcDeadlinesWithoutPeriodInDaysDto value =
                new RequestCalcDeadlinesWithoutPeriodInDaysDto(start_date,periodicity_id);
        value.setPeriodicity_id(periodicity_id);
        value.setStart_date(start_date);
        RootPeriodInDaysError valueError = BasicApi.postError(API_CALC_DEADLINES, id, value)
                .as(RootPeriodInDaysError.class);
        return valueError;
    }

    public static RootPeriodInDaysError postCalcDeadlinesError2(int id,String start_date, int periodicity_id,int period_in_days) {
        RequestCalcDeadlinesDto value =
                new RequestCalcDeadlinesDto(start_date,periodicity_id,period_in_days);
        value.setPeriodicity_id(periodicity_id);
        value.setStart_date(start_date);
        value.setPeriod_in_days(period_in_days);
        RootPeriodInDaysError valueError = BasicApi.postError(API_CALC_DEADLINES, id, value)
                .as(RootPeriodInDaysError.class);
        return valueError;
    }

    public static RootPeriodicityIdPeriodInDaysError postCalcDeadlinesError3(int id, String start_date, int periodicity_id) {
        RequestCalcDeadlinesWithoutPeriodInDaysDto value =
                new RequestCalcDeadlinesWithoutPeriodInDaysDto(start_date,periodicity_id);
        value.setPeriodicity_id(periodicity_id);
        value.setStart_date(start_date);
        RootPeriodicityIdPeriodInDaysError valueError = BasicApi.postError(API_CALC_DEADLINES, id, value)
                .as(RootPeriodicityIdPeriodInDaysError.class);
        return valueError;
    }

    public static RootCdTypeError postCalcDeadlinesError4(int id,String type, String start_date, String deadline_date, int periodicity_id) {
        RequestCalcDeadlinesWithType value =
                new RequestCalcDeadlinesWithType(type,start_date,deadline_date,periodicity_id);
        value.setType(type);
        value.setPeriodicity_id(periodicity_id);
        value.setStart_date(start_date);
        value.setDeadline_date(deadline_date);
        RootCdTypeError valueError = BasicApi.postError(API_CALC_DEADLINES, id, value)
                .as(RootCdTypeError.class);
        return valueError;
    }

    public static RootCdStartDateError postCalcDeadlinesError5(int id, String type, String start_date, String deadline_date, int periodicity_id) {
        RequestCalcDeadlinesWithType value =
                new RequestCalcDeadlinesWithType(type,start_date,deadline_date,periodicity_id);
        value.setType(type);
        value.setPeriodicity_id(periodicity_id);
        value.setStart_date(start_date);
        value.setDeadline_date(deadline_date);
        RootCdStartDateError valueError = BasicApi.postError(API_CALC_DEADLINES, id, value)
                .as(RootCdStartDateError.class);
        return valueError;
    }

    public static RootCdDeadlineDateError postCalcDeadlinesError6(int id, String type, String start_date, String deadline_date, int periodicity_id) {
        RequestCalcDeadlinesWithType value =
                new RequestCalcDeadlinesWithType(type,start_date,deadline_date,periodicity_id);
        value.setType(type);
        value.setPeriodicity_id(periodicity_id);
        value.setStart_date(start_date);
        value.setDeadline_date(deadline_date);
        RootCdDeadlineDateError valueError = BasicApi.postError(API_CALC_DEADLINES, id, value)
                .as(RootCdDeadlineDateError.class);
        return valueError;
    }

    public static RootCdDeadlineDateError postCalcDeadlinesError7(int id, String type, String start_date, int periodicity_id) {
        RequestWithOutDeadlineDateDto value =
                new RequestWithOutDeadlineDateDto(type,start_date,periodicity_id);
        value.setType(type);
        value.setPeriodicity_id(periodicity_id);
        value.setStart_date(start_date);
        RootCdDeadlineDateError valueError = BasicApi.postError(API_CALC_DEADLINES, id, value)
                .as(RootCdDeadlineDateError.class);
        return valueError;
    }


    public static RootCalcDeadlinesDto postCalcDeadlinesWithExecutorDeadlines(int id, String type, String start_date,
                                                                              String deadline_date, int periodicity_id) {
        RequestCalcDeadlinesWithType value =
                new RequestCalcDeadlinesWithType(type,start_date,deadline_date,periodicity_id);
        value.setType(type);
        value.setPeriodicity_id(periodicity_id);
        value.setStart_date(start_date);
        value.setDeadline_date(deadline_date);
        RootCalcDeadlinesDto actualValue = BasicApi.postCalcDeadlines(API_CALC_DEADLINES, id, value)
                .as(RootCalcDeadlinesDto.class);
        return actualValue;
    }

    public static RootCalcDeadlinesDto postCalcDeadlinesFull(int id, String type, String start_date,
                                                                              String deadline_date, int periodicity_id,
                                                             int period_in_days) {
        FullRequestCdDto value =
                new FullRequestCdDto(type,start_date,deadline_date,periodicity_id,period_in_days);
        value.setType(type);
        value.setPeriodicity_id(periodicity_id);
        value.setStart_date(start_date);
        value.setDeadline_date(deadline_date);
        value.setPeriod_in_days(period_in_days);
        RootCalcDeadlinesDto actualValue = BasicApi.postCalcDeadlines(API_CALC_DEADLINES, id, value)
                .as(RootCalcDeadlinesDto.class);
        return actualValue;
    }

    public static RootErrorNoRights postCalcDeadlinesErrorNoRights(int id, String type, String start_date,
                                                                   String deadline_date, int periodicity_id) {
        RequestCalcDeadlinesWithType value =
                new RequestCalcDeadlinesWithType(type,start_date,deadline_date,periodicity_id);
        value.setType(type);
        value.setPeriodicity_id(periodicity_id);
        value.setStart_date(start_date);
        value.setDeadline_date(deadline_date);
        RootErrorNoRights actualValue = BasicApi.postErrorNoRights(API_CALC_DEADLINES, id, value)
                .as(RootErrorNoRights.class);
        return actualValue;
    }

}

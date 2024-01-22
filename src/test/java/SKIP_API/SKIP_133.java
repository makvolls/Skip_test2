package SKIP_API;

import API.BasicCalcDeadlines;
import API.DTO.CalcDeadlinesDto.RootCalcDeadlinesDto;
import API.DTO.ErrorsDTO.CalcDeadlinesErrors.*;
import API.DTO.ErrorsDTO.RootErrorNoRights;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SKIP_133 {
    int other = 45;
    int one_time = 44;
    int once_every_six_months = 42;
    int every_month = 40;
    int once_every_two_weeks = 38;
    int every_week = 37;
    int every_day = 35;
    int without_preset_frequency = 33;

    int idAut1 = 1;
    int idAut2 = 6;


    RootStartDateDeriodicityOdError errValue;
    RootCalcDeadlinesDto value;
    RootPeriodInDaysError errValue2, errValue3;
    RootPeriodicityIdPeriodInDaysError errValue4;
    RootCdTypeError errValue5;
    RootCdStartDateError errValue6, errValue7, errValue8;
    RootCdDeadlineDateError errvalue9, errValue10, errValue11;
    RootCalcDeadlinesDto value2, value3, value4, value5, value6, value7, value8;
    RootErrorNoRights errorNoRights;


    @Test
    public void step01() {
        errValue = BasicCalcDeadlines.postCalcDeadlinesErr(idAut1);
        Assert.assertTrue(errValue.errors.start_date[0].equals("не может быть пустым"));
        Assert.assertTrue(errValue.errors.periodicity_id[0].equals("не может быть пустым"));
    }

    @Test
    public void step02() {
        value = BasicCalcDeadlines.postCalcDeadlines(idAut1, "2022-01-15T06:49:44.546Z", without_preset_frequency,
                30);
        Assert.assertNotNull(value.data.executor_deadlines.length);
        Assert.assertNotNull(value.data.head_executor_deadlines.length);
    }

    @Test
    public void step03() {
        errValue2 = BasicCalcDeadlines.postCalcDeadlinesError(idAut1, "2022-01-15T06:49:44.546Z", without_preset_frequency);
        Assert.assertTrue(errValue2.errors.period_in_days[0].equals("не может быть пустым для заданной периодичности"));
    }

    @Test
    public void step04() {

        errValue3 = BasicCalcDeadlines.postCalcDeadlinesError2(idAut1, "2022-01-15T06:49:44.546Z", without_preset_frequency,
                0);
        Assert.assertTrue(errValue3.errors.period_in_days[0].equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step05() {

        errValue4 = BasicCalcDeadlines.postCalcDeadlinesError3(idAut1, "2022-01-15T06:49:44.546Z", 200);
        Assert.assertTrue(errValue4.errors.periodicity_id[0].equals("имеет непредусмотренное значение"));
        Assert.assertTrue(errValue4.errors.period_in_days[0].equals("не может быть пустым для заданной периодичности"));
    }

    @Test
    public void step06() {

        errValue5 = BasicCalcDeadlines.postCalcDeadlinesError4(idAut1, "test", "2023-01-15T06:49:44.546Z",
                "2024-01-15T06:49:44.546Z", every_week);
        Assert.assertTrue(errValue5.errors.type[0].equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step07() {

        errValue6 = BasicCalcDeadlines.postCalcDeadlinesError5(idAut1, "head_executor", "",
                "2024-01-15T06:49:44.546Z", every_week);
        Assert.assertTrue(errValue6.errors.start_date[0].equals("не может быть пустым"));

    }

    @Test
    public void step08() {
        errValue7 = BasicCalcDeadlines.postCalcDeadlinesError5(idAut1, "head_executor", "ffff",
                "2024-01-15T06:49:44.546Z", every_week);
        Assert.assertTrue(errValue7.errors.start_date[0].equals("имеет неверное значение"));
    }

    @Test
    public void step09() {
        errValue8 = BasicCalcDeadlines.postCalcDeadlinesError5(idAut1, "head_executor", "2009-01-15T06:49:44.546Z",
                "2024-01-15T06:49:44.546Z", every_week);
        Assert.assertTrue(errValue8.errors.start_date[0].equals("не может быть ранее 01.01.2010"));
    }

    @Test
    public void step10() {

        errvalue9 = BasicCalcDeadlines.postCalcDeadlinesError6(idAut1, "head_executor",
                "2024-01-16T06:49:44.546Z", "2024-01-15T06:49:44.546Z", every_week);
        Assert.assertTrue(errvalue9.errors.deadline_date[0].equals("не может быть меньше даты начала"));

    }

    @Test
    public void step11() {
        errValue10 = BasicCalcDeadlines.postCalcDeadlinesError6(idAut1, "head_executor",
                "2024-01-16T06:49:44.546Z", "test", every_week);
        Assert.assertTrue(errValue10.errors.deadline_date[0].equals("имеет неверное значение"));
    }

    @Test
    public void step12() {

        errValue11 = BasicCalcDeadlines.postCalcDeadlinesError7(idAut1, "head_executor",
                "2024-01-16T06:49:44.546Z", one_time);
        Assert.assertTrue(errValue11.errors.deadline_date[0].equals("не может быть пустым для заданной периодичности"));
    }

    @Test
    public void step13() {
        value2 = BasicCalcDeadlines.postCalcDeadlinesWithExecutorDeadlines(idAut1, "executor",
                "2023-01-10T06:49:44.546Z", "2024-01-15T06:49:44.546Z", once_every_two_weeks);
        Assert.assertNotNull(value2.data.executor_deadlines.length);
        Assert.assertNull(value2.getData().head_executor_deadlines);
        Assert.assertTrue(value2.data.executor_deadlines[0].equals("2023-01-24T06:49:44.546Z"));
        Assert.assertTrue(value2.data.executor_deadlines[1].equals("2023-02-07T06:49:44.546Z"));
    }

    @Test
    public void step14() {

        value3 = BasicCalcDeadlines.postCalcDeadlinesWithExecutorDeadlines(idAut1, "head_executor",
                "2023-01-10T06:49:44.546Z", "2024-01-15T06:49:44.546Z", once_every_two_weeks);
        Assert.assertNotNull(value3.data.executor_deadlines.length);
        Assert.assertNotNull(value3.getData().head_executor_deadlines);
        Assert.assertTrue(value3.data.executor_deadlines[0].equals("2023-01-17T06:49:44.546Z"));
        Assert.assertTrue(value3.data.executor_deadlines[1].equals("2023-01-31T06:49:44.546Z"));
        Assert.assertTrue(value3.data.head_executor_deadlines[0].equals("2023-01-24T06:49:44.546Z"));
        Assert.assertTrue(value3.data.head_executor_deadlines[1].equals("2023-02-07T06:49:44.546Z"));
    }

    @Test
    public void step15() {

        value4 = BasicCalcDeadlines.postCalcDeadlinesFull(idAut1, "head_executor",
                "2023-01-10T06:49:44.546Z", "2024-01-15T06:49:44.546Z", other, 20);
        Assert.assertNotNull(value4.data.executor_deadlines.length);
        Assert.assertNotNull(value4.getData().head_executor_deadlines);
        Assert.assertTrue(value4.data.executor_deadlines[0].equals("2023-01-20T06:49:44.546Z"));
        Assert.assertTrue(value4.data.executor_deadlines[1].equals("2023-02-09T06:49:44.546Z"));
        Assert.assertTrue(value4.data.head_executor_deadlines[0].equals("2023-01-30T06:49:44.546Z"));
        Assert.assertTrue(value4.data.head_executor_deadlines[1].equals("2023-02-19T06:49:44.546Z"));
    }

    @Test
    public void step16() {

        value5 = BasicCalcDeadlines.postCalcDeadlinesWithExecutorDeadlines(idAut1, "head_executor",
                "2021-01-10T06:49:44.546Z", "2024-01-15T06:49:44.546Z", once_every_six_months);
        Assert.assertNotNull(value5.data.executor_deadlines.length);
        Assert.assertNotNull(value5.data.head_executor_deadlines);
        Assert.assertTrue(value5.data.executor_deadlines[0].equals("2021-04-11T06:49:44.546Z"));
        Assert.assertTrue(value5.data.executor_deadlines[1].equals("2021-10-10T06:49:44.546Z"));
        Assert.assertTrue(value5.data.head_executor_deadlines[0].equals("2021-07-10T06:49:44.546Z"));
        Assert.assertTrue(value5.data.head_executor_deadlines[1].equals("2022-01-10T06:49:44.546Z"));
    }

    @Test
    public void step17() {
        value6 = BasicCalcDeadlines.postCalcDeadlinesWithExecutorDeadlines(idAut1, "head_executor",
                "2021-01-31T06:49:44.546Z", "2023-01-15T06:49:44.546Z", every_month);
        Assert.assertNotNull(value6.data.executor_deadlines.length);
        Assert.assertNotNull(value6.data.head_executor_deadlines);
        Assert.assertTrue(value6.data.executor_deadlines[0].equals("2021-02-14T06:49:44.546Z"));
        Assert.assertTrue(value6.data.executor_deadlines[1].equals("2021-03-16T06:49:44.546Z"));
        Assert.assertTrue(value6.data.head_executor_deadlines[0].equals("2021-02-28T06:49:44.546Z"));
        Assert.assertTrue(value6.data.head_executor_deadlines[1].equals("2021-03-31T06:49:44.546Z"));
    }

    @Test
    public void step18() {
        value7 = BasicCalcDeadlines.postCalcDeadlinesWithExecutorDeadlines(idAut1, "head_executor",
                "2021-01-10T06:49:44.546Z", "2021-01-15T06:49:44.546Z", every_day);
        Assert.assertNotNull(value7.data.executor_deadlines.length);
        Assert.assertNotNull(value7.data.head_executor_deadlines);
        Assert.assertEquals(value7.data.executor_deadlines[0], value7.data.head_executor_deadlines[0]);
        Assert.assertEquals(value7.data.executor_deadlines[1], value7.data.head_executor_deadlines[1]);
        Assert.assertEquals(value7.data.executor_deadlines[2], value7.data.head_executor_deadlines[2]);
        Assert.assertEquals(value7.data.executor_deadlines[3], value7.data.head_executor_deadlines[3]);
        Assert.assertEquals(value7.data.executor_deadlines[4], value7.data.head_executor_deadlines[4]);

    }

    @Test
    public void step19() {
        value8 = BasicCalcDeadlines.postCalcDeadlinesWithExecutorDeadlines(idAut1, "head_executor",
                "2021-01-10T06:49:44.546Z", "2021-01-13T06:49:44.546Z", one_time);

        Assert.assertTrue(value8.data.head_executor_deadlines[0].equals("2021-01-13T06:49:44.546Z"));
        Assert.assertTrue(value8.data.executor_deadlines[0].equals("2021-01-12T06:49:44.546Z"));

    }

    @Test
    public void step20() {
        errorNoRights = BasicCalcDeadlines.postCalcDeadlinesErrorNoRights(idAut2, "head_executor",
                "2021-01-10T06:49:44.546Z", "2021-01-13T06:49:44.546Z", one_time);
        Assert.assertTrue(errorNoRights.error.equals("Доступ к ресурсу запрещен"));
    }

}




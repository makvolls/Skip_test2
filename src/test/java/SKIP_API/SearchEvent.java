package SKIP_API;

import API.BasicEvents;
import API.DTO.EventsDto.RootListDto;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SearchEvent extends BasicEvents {
    public static String API_EVENT_GET = "http://api.skip.rtech.ru/v1/classifiers/events";

    RootListDto actual;


    @Test

    void getListEvents() {
        actual = getListEvents(1);

        Assert.assertNotEquals(actual.getData().length,0);
        Assert.assertTrue(actual.getData()[1].name.contains("тест112"));


    }




}

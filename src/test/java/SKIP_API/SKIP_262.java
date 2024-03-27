package SKIP_API;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SKIP_262 {
    int idAut1 = 1;
    String API_SETTINGS = "http://api.skip.rtech.ru/v1/settings/personal_settings";

    @Test
    public void step01() {
        JsonObject object=new JsonObject();
        object.addProperty("skip_notifications",true);
        object.addProperty("skip_banners",true);
        object.addProperty("notification_delay",1);
        object.addProperty("notification_period",1);
        Response response = RestAssured.given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(object.toString())
                .put(API_SETTINGS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        boolean skip_notifications=jsonPath.getBoolean("data.skip_notifications");
        boolean skip_banners=jsonPath.getBoolean("data.skip_banners");
        int notification_delay=jsonPath.getInt("data.notification_delay");
        int notification_period=jsonPath.getInt("data.notification_period");

        Assert.assertTrue(skip_notifications);
        Assert.assertTrue(skip_banners);
        Assert.assertTrue(notification_delay==1);
        Assert.assertTrue(notification_period==1);
    }

    @Test
    public void step02(){
        JsonObject object=new JsonObject();
        object.addProperty("skip_notifications",false);
        object.addProperty("skip_banners",false);
        object.addProperty("notification_delay",101);
        object.addProperty("notification_period",10432);
        Response response=RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(object.toString())
                .put(API_SETTINGS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath=response.jsonPath();
        boolean skip_notifications=jsonPath.getBoolean("data.skip_notifications");
        boolean skip_banners=jsonPath.getBoolean("data.skip_banners");
        int notification_delay=jsonPath.getInt("data.notification_delay");
        int notification_period=jsonPath.getInt("data.notification_period");

        Assert.assertTrue(!skip_notifications);
        Assert.assertTrue(!skip_banners);
        Assert.assertTrue(notification_delay==101);
        Assert.assertTrue(notification_period==10432);
    }
    @Test
    public void step03(){
        JsonObject object=new JsonObject();
        object.addProperty("skip_notifications",false);
        object.addProperty("skip_banners",false);
        object.addProperty("notification_delay","ыфвмвы");
        object.addProperty("notification_period","мвфымсв");
        Response response=RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(object.toString())
                .put(API_SETTINGS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath=response.jsonPath();
        String error1=jsonPath.getString("errors.notification_delay[0]");
        String error2=jsonPath.getString("errors.notification_period[0]");
        Assert.assertTrue(error1.equals("имеет неверное значение"));
        Assert.assertTrue(error2.equals("имеет неверное значение"));
    }
}

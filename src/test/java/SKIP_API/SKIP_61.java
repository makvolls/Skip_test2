package SKIP_API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SKIP_61 {
    private static String API_PROFILE = "http://api.skip.rtech.ru/v1/permissions/users/profile";
    String otherProvidersId = "525e9f767da3000002000001"; // Delete after testing - "525e9f767da3000002000001"
    String providerUserIdOne = "5273bd3cef93000033000001"; // Main provider user with id 1
    String providerUserIdTwo = "528f018123c700002c000001"; // Provider that doesn't have user with id 1
    String setCookie;

    @Test
    public void step01(){
        // Skip first step in the testcase because it's redundant
        Response response = given()
                .when()
                .header("Test-Authorization",2)
                .get(API_PROFILE)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String providerName = jsonPath.get("data.provider_name").toString();
        String otherProviders = jsonPath.get("data.other_providers[0].name").toString();
        otherProvidersId = jsonPath.get("data.other_providers[0].id").toString();

        //System.out.println(providerName);
        //System.out.println(otherProviders);
        //System.out.println(otherProvidersId);

        Assert.assertTrue(providerName.equals("Отдел делопроизводства и режима Департамента " +
                "информационных технологий, связи и защиты информации Министерства внутренних дел " +
                "Российской Федерации (ОДиР ДИТСиЗИ МВД России)"));
        Assert.assertTrue(otherProviders.equals("Департамент делопроизводства и работы с обращениями " +
                "граждан и организаций Министерства внутренних дел Российской Федерации (ДДО МВД России)"));

    }

    @Test
    public void step02(){
        Response response = given()
                .when()
                .params("provider_id",otherProvidersId)
                .header("Test-Authorization",2)
                .post(API_PROFILE + "/change_provider")
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        setCookie = response.getHeader("Set-Cookie");

        System.out.println("Set Cookie : " + setCookie);

        String providerName = jsonPath.get("data.provider_name").toString();
        String otherProviders = jsonPath.get("data.other_providers[0].name").toString();

        Assert.assertTrue(providerName.equals("Департамент делопроизводства и работы с обращениями " +
                "граждан и организаций Министерства внутренних дел Российской Федерации (ДДО МВД России)"));
        Assert.assertTrue(otherProviders.equals("Отдел делопроизводства и режима Департамента " +
                "информационных технологий, связи и защиты информации Министерства внутренних дел " +
                "Российской Федерации (ОДиР ДИТСиЗИ МВД России)"));
    }

    @Test
    public void step03(){
        Response response = given()
                .when()
                .header("Cookie",setCookie)
                //.header("Test-Authorization",2) // Delete header to get desirable result
                .get(API_PROFILE)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String providerName = jsonPath.get("data.provider_name").toString();
        String otherProviders = jsonPath.get("data.other_providers[0].name").toString();

        System.out.println(providerName);
        System.out.println(otherProviders);

        Assert.assertTrue(providerName.equals("Департамент делопроизводства и работы с обращениями " +
                "граждан и организаций Министерства внутренних дел Российской Федерации (ДДО МВД России)"));
        Assert.assertTrue(otherProviders.equals("Отдел делопроизводства и режима Департамента " +
                "информационных технологий, связи и защиты информации Министерства внутренних дел " +
                "Российской Федерации (ОДиР ДИТСиЗИ МВД России)"));
    }

    @Test
    public void step04(){
        Response response = given()
                .when()
                .params("provider_id",providerUserIdTwo)
                .header("Test-Authorization",2)
                .post(API_PROFILE + "/change_provider")
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String error = jsonPath.get("error");

        Assert.assertTrue(error.equals("Запись для Permissions::User с id =  не найдена"));
    }

    @Test
    public void step05(){
        Response response = given()
                .when()
                .params("provider_id",providerUserIdOne)
                .header("Test-Authorization",2)
                .post(API_PROFILE + "/change_provider")
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String providerName = jsonPath.get("data.provider_name").toString();
        String otherProviders = jsonPath.get("data.other_providers[0].name").toString();

        Assert.assertTrue(providerName.equals("Отдел делопроизводства и режима Департамента " +
                "информационных технологий, связи и защиты информации Министерства внутренних дел " +
                "Российской Федерации (ОДиР ДИТСиЗИ МВД России)"));
        Assert.assertTrue(otherProviders.equals("Департамент делопроизводства и работы с обращениями " +
                "граждан и организаций Министерства внутренних дел Российской Федерации (ДДО МВД России)"));
    }

    @Test
    public void step06(){
        Response response = given()
                .when()
                .header("Test-Authorization",2)
                .get(API_PROFILE)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String providerName = jsonPath.get("data.provider_name").toString();
        String otherProviders = jsonPath.get("data.other_providers[0].name").toString();

        Assert.assertTrue(providerName.equals("Отдел делопроизводства и режима Департамента " +
                "информационных технологий, связи и защиты информации Министерства внутренних дел " +
                "Российской Федерации (ОДиР ДИТСиЗИ МВД России)"));
        Assert.assertTrue(otherProviders.equals("Департамент делопроизводства и работы с обращениями " +
                "граждан и организаций Министерства внутренних дел Российской Федерации (ДДО МВД России)"));
    }
}

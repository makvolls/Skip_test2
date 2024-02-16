package SKIP_API;

import API.BasicProviders;
import API.DTO.ProvidersDTO.RootPermissions;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SKIP_11 {

  public static String API_PROVIDERS = "http://api.skip.rtech.ru/v1/permissions/providers";
  RootPermissions searchValue;
  int idAut1=1;

  @Test
  public void step01() {

    searchValue = BasicProviders.getProviders(1);
    Assert.assertNotNull(searchValue);

  }
  @Test
  public void step02() {
    Response response = given()
            .when()
            .header("Test-Authorization", idAut1)
            .params("name", "деп")
            .get(API_PROVIDERS)
            .then().log().all()
            .extract().response();

    JsonPath jsonPath = response.jsonPath();
    List<String> name = jsonPath.get("data.name");

    List<Integer> n = new ArrayList<>();

    for (int i = 0; i < name.size(); i++) {
      if (!name.get(i).contains("деп") && !name.get(i).contains("Деп"))
        n.add(i);
    }

    for (int i = 0; i < n.size(); i++) {
      Assert.assertTrue(name.get(n.get(i)).contains("деп") || name.get(n.get(i)).contains("Деп"));
    }


  }

  @Test
  public void step03() {
    Response response = given()
            .when()
            .header("Test-Authorization", idAut1)
            .params("name", "ДЕП")
            .get(API_PROVIDERS)
            .then().log().all()
            .extract().response();

    JsonPath jsonPath = response.jsonPath();
    List<String> name = jsonPath.get("data.name");

    List<Integer> n = new ArrayList<>();

    for (int i = 0; i < name.size(); i++) {
      if (!name.get(i).contains("деп") && !name.get(i).contains("Деп"))
        n.add(i);
    }

    for (int i = 0; i < n.size(); i++) {
      Assert.assertTrue(name.get(n.get(i)).contains("деп") || name.get(n.get(i)).contains("Деп"));
    }

  }

  @Test
  public void step04() {
    Response response = given()
            .when()
            .header("Test-Authorization", idAut1)
            .params("name", "Департамент делопроизводства и работы с " +
                    "обращениями граждан и организаций Министерства внутренних дел Российской Федерации (ДДО МВД России)")
            .get(API_PROVIDERS)
            .then().log().all()
            .extract().response();

    JsonPath jsonPath = response.jsonPath();
    List<String> name = jsonPath.get("data.name");


    Assert.assertTrue(name.contains("Департамент делопроизводства и работы с обращениями граждан и " +
            "организаций Министерства внутренних дел Российской Федерации (ДДО МВД России)"));

  }

  @Test
  public void step05() {
    Response response = given()
            .when()
            .header("Test-Authorization", idAut1)
            .params("name", "ДДО МВД России")
            .get(API_PROVIDERS)
            .then().log().all()
            .extract().response();

    JsonPath jsonPath = response.jsonPath();
    List<String> name = jsonPath.get("data.name");


    Assert.assertTrue(name.contains("Департамент делопроизводства " +
            "и работы с обращениями граждан и организаций Министерства внутренних дел " +
            "Российской Федерации (ДДО МВД России)"));

  }

  @Test
  public void step06() {
    Response response = given()
            .when()
            .header("Test-Authorization", idAut1)
            .params("id", "5273bd3cef93000033000001")
            .get(API_PROVIDERS)
            .then().log().all()
            .extract().response();

    JsonPath jsonPath = response.jsonPath();
    List<String> name = jsonPath.get("data.name");
    List<String> id = jsonPath.get("data.id");


    Assert.assertTrue(name.contains("Отдел делопроизводства и режима Департамента информационных технологий," +
            " связи и защиты информации Министерства внутренних дел Российской Федерации (ОДиР ДИТСиЗИ МВД России)"));
    Assert.assertTrue(id.contains("5273bd3cef93000033000001"));

  }

  @Test
  public void step07() {
    Response response = given()
            .when()
            .header("Test-Authorization", idAut1)
            .params("id", "1")
            .get(API_PROVIDERS)
            .then().log().all()
            .extract().response();

    JsonPath jsonPath = response.jsonPath();
    List<String> data = jsonPath.get("data");


    Assert.assertTrue(data.size() == 0);

  }

  @Test
  public void step08() {
    Response response = given()
            .when()
            .header("Test-Authorization", idAut1)
            .params("id", "12345678")
            .get(API_PROVIDERS)
            .then().log().all()
            .extract().response();

    JsonPath jsonPath = response.jsonPath();
    List<String> data = jsonPath.get("data");


    Assert.assertTrue(data.size() == 0);

  }
}
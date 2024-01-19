package SKIP_API;

import API.OSHS.CatalogOSHS.RootCatalogOSHS;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.javacrumbs.jsonunit.core.internal.JsonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SKIP_120 {

    private static String API_OSHS = "http://api.skip.rtech.ru/v1/oshs";
    String typeOfficial = "Oshs::Mvd::Official";
    String typeOrganization = "Oshs::Mvd::Organization";
    String typeEsdOrganization = "Oshs::Esd::Organization";

    @Test
    public void step01(){
        // Get request without any params
        Response response = given()
                .when()
                .header("Test-Authorization", 1)
                .get(API_OSHS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        // Create variables that will contain values of the parameters
        // which are contained in the parameter "meta"
        int page = jsonPath.get("meta.page");
        int items = jsonPath.get("meta.items");
        int pages = jsonPath.get("meta.pages");
        int count = jsonPath.get("meta.count");
        // Assert every value of parameter with default value
        Assert.assertTrue(page == 1);
        Assert.assertTrue(items == 10);
        Assert.assertTrue(pages == 1000);
        Assert.assertTrue(count == 10000);
    }

    @Test
    public void step02(){
        // Get request with params:
        // "query" - "Ивано И.А"
        // "types[]" - "Oshs::Mvd::Official"
        // "items" - "25"
        // "page" - "2"
        Response response = given()
                .when()
                .params("query","Ивано И.А")
                .params("types[]","Oshs::Mvd::Official")
                .params("items","25")
                .params("page","2")
                .header("Test-Authorization", 1)
                .get(API_OSHS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int page = jsonPath.get("meta.page");
        int items = jsonPath.get("meta.items");
        int pages = jsonPath.get("meta.pages");
        int count = jsonPath.get("meta.count");
        Assert.assertTrue(page == 2);
        Assert.assertTrue(items == 25);
        Assert.assertTrue(pages == 2);
        Assert.assertTrue(count == 43);

        // Create list that will contain all value parameter "type" of represented items in response body
        List<String> typeOfAllElements = jsonPath.get("data.type");
        // Using cycle "for" assert every element in list of value parameter "type" with default
        // value - "type"
        for (String typeOfElement : typeOfAllElements) {
            Assert.assertTrue(typeOfElement.equals(typeOfficial));
        }
        // Create list that will contain all value parameter "short_name_with_org_and_pos"
        // of represented items in response body
        List<String> shortNames = jsonPath.get("data.short_name_with_org_and_pos");
        System.out.println(shortNames);

        // Create a copy of the original list
        List<String> sortedShortNames = new ArrayList<>(shortNames);
        // Sort the copy
        Collections.sort(sortedShortNames);
        System.out.println(sortedShortNames);
        Assert.assertTrue(shortNames.equals(sortedShortNames));
    }

    @Test
    public void step03(){
        // Get request with parameters:
        // "query" - "колокольцев"
        // "types[]" - "Oshs::Mvd::Official"
        // "items" - "2"
        // "page" - "1"
        Response response = given()
                .when()
                .params("query","колокольцев")
                .params("types[]","Oshs::Mvd::Official")
                .params("items","2")
                .params("page","1")
                .header("Test-Authorization",1)
                .get(API_OSHS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        List<String> typeOfAllElements = jsonPath.get("data.type");

        for (String typeOfElement : typeOfAllElements) {
            Assert.assertTrue(typeOfElement.equals(typeOfficial));
        }

        List<String> namesOfElements = jsonPath.get("data.short_name_with_org_and_pos");

        //System.out.println(namesOfElements.get(0));
        //System.out.println(namesOfElements.get(1));
        Assert.assertTrue(namesOfElements.get(0).equals(
                "Колокольцев А.А. (УУПиПДН ОП №2 УМВД России по г.Пензе, участковый уполномоченный)"));
        Assert.assertTrue(namesOfElements.get(1).equals(
                "Колокольцев В.А. (Центральный аппарат МВД РФ, Министр)"));
    }

    @Test
    public void step04(){
        // Get request with parameters:
        // "query" - "волобуев М.С"
        // "types[]" - "Oshs::Mvd::Official"
        // "items" - "2"
        // "page" - "1"
        Response response = given()
                .when()
                .params("query","волобуев М.С")
                .params("types[]","Oshs::Mvd::Official")
                .params("items","2")
                .params("page","1")
                .header("Test-Authorization",1)
                .get(API_OSHS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        List<String> typeOfAllElements = jsonPath.get("data.type");

        for (String typeOfElement : typeOfAllElements) {
            Assert.assertTrue(typeOfElement.equals(typeOfficial));
        }

        List<String> namesOfAllElement = jsonPath.get("data.short_name_with_org_and_pos");
        Assert.assertTrue(namesOfAllElement.get(0).equals("Волобуев М.С. (Группа тестирования СЭД, ТестСкип123)"));
    }

    @Test
    public void step05(){
        // Get request with parameters:
        // "query" - "курск"
        // "types[]" - "Oshs::Mvd::Organization"
        // "items" - "10"
        // "page" - "1"
        Response response = given()
                .when()
                .params("query","курск")
                .params("types[]","Oshs::Mvd::Organization")
                .params("items","10")
                .params("page","1")
                .header("Test-Authorization",1)
                .get(API_OSHS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        int page = jsonPath.get("meta.page");
        int items = jsonPath.get("meta.items");
        int pages = jsonPath.get("meta.pages");
        int count = jsonPath.get("meta.count");
        Assert.assertTrue(page == 1);
        Assert.assertTrue(items == 10);
        Assert.assertTrue(pages == 37);
        Assert.assertTrue(count == 368);

        List<String> typeOfAllElement = jsonPath.get("data.type");

        for (String typeOfElement : typeOfAllElement) {
            Assert.assertTrue(typeOfElement.equals(typeOrganization));
        }

        List<String> namesOfAllElement = jsonPath.get("data.short_title");

        List<String> sortedNames = new ArrayList<>(namesOfAllElement);
        Collections.sort(sortedNames);

        Assert.assertTrue(namesOfAllElement.equals(sortedNames));
    }

    @Test
    public void step06(){
        // Get request with parameters:
        // "query" - "организация"
        // "types[]" - "Oshs::Esd::Organization"
        // "items" - "5"
        // "page" - "2"
        Response response = given()
                .when()
                .params("query","организация")
                .params("types[]","Oshs::Esd::Organization")
                .params("items","5")
                .params("page","2")
                .header("Test-Authorization",1)
                .get(API_OSHS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        int page = jsonPath.get("meta.page");
        int items = jsonPath.get("meta.items");
        int pages = jsonPath.get("meta.pages");
        int count = jsonPath.get("meta.count");
        Assert.assertTrue(page == 2);
        Assert.assertTrue(items == 5);
        Assert.assertTrue(pages == 64);
        Assert.assertTrue(count == 320);

        List<String> typeOfAllElements = jsonPath.get("data.type");

        for (String typeOfElement : typeOfAllElements) {
            Assert.assertTrue(typeOfElement.equals(typeEsdOrganization));
        }

        List<String> nameOfAllElement = jsonPath.get("data.short_title");

        List<String> sortedNames = new ArrayList<>(nameOfAllElement);
        Collections.sort(sortedNames);

        Assert.assertTrue(nameOfAllElement.equals(sortedNames));
    }

    @Test
    public void step07(){
        // Gey request with parameters:
        // "query" - "ивано"
        // "items" - "30" 100
        // "page" - "3"
        Response response = given()
                .when()
                .params("query","ивано")
                .params("items","30")
                .params("page","3")
                .header("Test-Authorization",1)
                .get(API_OSHS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        List<String> typeOfAllElements = jsonPath.get("data.type");

        boolean isFound = false;

        for (String typeOfElement : typeOfAllElements) {
            //System.out.println(typeOfElement);
            if (typeOfElement.equals("Oshs::Mvd::Organization")){
                isFound = true;
            }
        }
        Assert.assertTrue(isFound);

    }
}
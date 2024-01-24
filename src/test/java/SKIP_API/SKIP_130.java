package SKIP_API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class SKIP_130 {
    // int ps1;
    int ct1;
   // int ps2;
    int ct2;
 //   int ps3;
    int ct3;
 //   int ps4;
    int ct4;
 //   int ps5;
    int ct5;
  //  int ps6;
    int ct6;
  //  int ps7;
    int ct7;
  //  int ps8;
    int ct8;
 //   int ps9;
    int ct9;
 //   int ps10;
    int ct10;
 //   int ps11;
    int ct11;
 //   int ps12;
    int ct12;
 //   int ps13;
    int ct13;



    public static String API_DOCUMENTS = "http://api.skip.rtech.ru/v1/sed/documents";

    @Test
    public void step01() {
        Response response = given()
                .when()
                .header("Test-Authorization", 1)
                .get(API_DOCUMENTS)
                .then().log().all()
                .extract().response();


        JsonPath jsonPath = response.jsonPath();
        List<String> errors = jsonPath.get("errors.journal_type");
        Assert.assertTrue(errors.contains("не может быть пустым"));
    }

    @Test
    public void step02() {
        Response response = given()
                .when()
                .header("Test-Authorization", 1)
                .params("journal_type", "incoming_documents")
                .params("archive_year", "2014")
                .get(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int page = jsonPath.get("meta.page");
        int items = jsonPath.get("meta.items");
        int pages = jsonPath.get("meta.pages");
        int count = jsonPath.get("meta.count");
        Assert.assertTrue(page == 1);
        Assert.assertTrue(items == 10);
        Assert.assertNotNull(pages);
        Assert.assertNotNull(count);

      //  ps1 = pages;
        ct1 = count;

    }

    @Test
    public void step03() {
        Response response = given()
                .when()
                .header("Test-Authorization", 1)
                .params("journal_type", "incoming_documents")
                .params("archive_year", "2014")
                .params("provider_id", "525e9f767da3000002000001")
                .params("query", "а")
                .params("external_document_number", "1")
                .params("reg_number", "1")
                .get(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int page = jsonPath.get("meta.page");
        int items = jsonPath.get("meta.items");
        //int pages = jsonPath.get("meta.pages");
        int count = jsonPath.get("meta.count");
     //   ps2 = pages;
        ct2 = count;
        Assert.assertTrue(page == 1);
        Assert.assertTrue(items == 10);
  //      Assert.assertTrue(ps2 != ps1);
        Assert.assertTrue(ct2 != ct1);


    }

    @Test
    public void step04() {
        Response response = given()
                .when()
                .header("Test-Authorization", 1)
                .params("journal_type", "incoming_documents")
                .params("archive_year", "2026")
                .params("provider_id", "525e9f767da3000002000001")
                .params("query", "а")
                .params("external_document_number", "1")
                .params("reg_number", "1")
                .get(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String errors = jsonPath.get("error");
        Assert.assertTrue(errors.contains("the server responded with status 500"));


    }

    @Test
    public void step05() {
        Response response = given()
                .when()
                .header("Test-Authorization", 1)
                .params("journal_type", "incoming_documents")
                .params("query", "а")
                .params("external_document_number", "1")
                .params("reg_number", "1")
                .get(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int page = jsonPath.get("meta.page");
        int items = jsonPath.get("meta.items");
        int pages = jsonPath.get("meta.pages");
        int count = jsonPath.get("meta.count");
      //  ps3 = pages;
        ct3 = count;
        Assert.assertTrue(page == 1);
        Assert.assertTrue(items == 10);
     //   Assert.assertTrue(ps3 != ps1);
        Assert.assertTrue(ct3 != ct1);
      //  Assert.assertTrue(ps3 != ps2);
        Assert.assertTrue(ct3 != ct2);


    }

    @Test
    public void step06() {
        Response response = given()
                .when()
                .header("Test-Authorization", 1)
                .params("journal_type", "secret_incoming_documents")
                .params("archive_year", "2014")
                .params("provider_id", "525e9f767da3000002000001")
                .params("query", "а")
                .params("external_document_number", "1")
                .params("reg_number", "1")
                .get(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int page = jsonPath.get("meta.page");
        int items = jsonPath.get("meta.items");
        int pages = jsonPath.get("meta.pages");
        int count = jsonPath.get("meta.count");
     //   ps4 = pages;
        ct4 = count;
        Assert.assertTrue(page == 1);
        Assert.assertTrue(items == 10);
     //   Assert.assertTrue(ps4 != ps1);
        Assert.assertTrue(ct4 != ct1);
     //   Assert.assertTrue(ps4 != ps2);
        Assert.assertTrue(ct4 != ct2);
      //  Assert.assertTrue(ps4 != ps3);
        Assert.assertTrue(ct4 != ct3);


    }

    @Test
    public void step07() {
        Response response = given()
                .when()
                .header("Test-Authorization", 1)
                .params("journal_type", "attorney_letters")
                .params("archive_year", "2014")
                .params("provider_id", "525e9f767da3000002000001")
                .params("query", "а")
                .params("external_document_number", "1")
                .params("reg_number", "1")
                .get(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int page = jsonPath.get("meta.page");
        int items = jsonPath.get("meta.items");
        int pages = jsonPath.get("meta.pages");
        int count = jsonPath.get("meta.count");
        List<String> data= jsonPath.get("data");

        Assert.assertTrue(page == 1);
        Assert.assertTrue(items == 10);
        Assert.assertEquals(0,data.size());
        Assert.assertEquals(0,count);
        Assert.assertEquals(0,pages);
    }

    @Test
    public void step08() {
        Response response = given()
                .when()
                .header("Test-Authorization", 1)
                .params("journal_type", "secret_incoming_documents")
                .params("archive_year", "2014")
                .params("provider_id", "525e9f767da3000002000001")
                .params("reg_number", "1")
                .get(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int page = jsonPath.get("meta.page");
        int items = jsonPath.get("meta.items");
        int pages = jsonPath.get("meta.pages");
        int count = jsonPath.get("meta.count");
     //   ps5 = pages;
        ct5 = count;
        Assert.assertTrue(page == 1);
        Assert.assertTrue(items == 10);
      //  Assert.assertTrue(ps5 != ps1);
        Assert.assertTrue(ct5 != ct1);
      //  Assert.assertTrue(ps5 != ps2);
        Assert.assertTrue(ct5 != ct2);
       // Assert.assertTrue(ps5 != ps3);
        Assert.assertTrue(ct5 != ct3);
      //  Assert.assertTrue(ps5 != ps4);
        Assert.assertTrue(ct5 != ct4);


    }
    @Test
    public void step09() {
        Response response = given()
                .when()
                .header("Test-Authorization", 1)
                .params("journal_type", "outgoing_documents")
                .params("query", "а")
                .params("archive_year", "2014")
                .params("provider_id", "525e9f767da3000002000001")
                .params("reg_number", "1")
                .get(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int page = jsonPath.get("meta.page");
        int items = jsonPath.get("meta.items");
        int pages = jsonPath.get("meta.pages");
        int count = jsonPath.get("meta.count");
      //  ps6 = pages;
        ct6 = count;
        Assert.assertTrue(page == 1);
        Assert.assertTrue(items == 10);
     //   Assert.assertTrue(ps6 != ps1);
        Assert.assertTrue(ct6 != ct1);
      //  Assert.assertTrue(ps6 != ps2);
        Assert.assertTrue(ct6 != ct2);
      //  Assert.assertTrue(ps6 != ps3);
        Assert.assertTrue(ct6 != ct3);
       // Assert.assertTrue(ps6 != ps4);
        Assert.assertTrue(ct6 != ct4);
      //  Assert.assertTrue(ps6 != ps5);
        Assert.assertTrue(ct6 != ct5);


    }

    @Test
    public void step10() {
        Response response = given()
                .when()
                .header("Test-Authorization", 1)
                .params("journal_type", "secret_outgoing_documents")
                .params("query", "а")
                .params("archive_year", "2014")
                .params("provider_id", "525e9f767da3000002000001")
                .params("reg_number", "1")
                .get(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int page = jsonPath.get("meta.page");
        int items = jsonPath.get("meta.items");
        int pages = jsonPath.get("meta.pages");
        int count = jsonPath.get("meta.count");
    //    ps7 = pages;
        ct7 = count;
        Assert.assertTrue(page == 1);
        Assert.assertTrue(items == 10);
     //   Assert.assertTrue(ps7 != ps1);
        Assert.assertTrue(ct7 != ct1);
     //   Assert.assertTrue(ps7 != ps2);
        Assert.assertTrue(ct7 != ct2);
     //   Assert.assertTrue(ps7 != ps3);
        Assert.assertTrue(ct7 != ct3);
      //  Assert.assertTrue(ps7 != ps4);
        Assert.assertTrue(ct7 != ct4);
      //  Assert.assertTrue(ps7 != ps5);
        Assert.assertTrue(ct7 != ct5);
     //  Assert.assertTrue(ps7 != ps6);
        Assert.assertTrue(ct7 != ct6);


    }

    @Test
    public void step11() {
        Response response = given()
                .when()
                .header("Test-Authorization", 1)
                .params("journal_type", "orders")
                .params("archive_year", "2014")
                .params("provider_id", "525e9f767da3000002000001")
                .params("reg_number", "1")
                .get(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int page = jsonPath.get("meta.page");
        int items = jsonPath.get("meta.items");
        int pages = jsonPath.get("meta.pages");
        int count = jsonPath.get("meta.count");
      //  ps8 = pages;
        ct8 = count;
        Assert.assertTrue(page == 1);
        Assert.assertTrue(items == 10);
      //  Assert.assertTrue(ps8 != ps1);
        Assert.assertTrue(ct8 != ct1);
     //   Assert.assertTrue(ps8 != ps2);
        Assert.assertTrue(ct8 != ct2);
     //   Assert.assertTrue(ps8 != ps3);
        Assert.assertTrue(ct8 != ct3);
     //   Assert.assertTrue(ps8 != ps4);
        Assert.assertTrue(ct8 != ct4);
      //  Assert.assertTrue(ps8 != ps5);
        Assert.assertTrue(ct8 != ct5);
      //  Assert.assertTrue(ps8 != ps6);
        Assert.assertTrue(ct8 != ct6);
       // Assert.assertTrue(ps8 != ps7);
        Assert.assertTrue(ct8 != ct7);


    }

    @Test
    public void step12() {
        Response response = given()
                .when()
                .header("Test-Authorization", 1)
                .params("journal_type", "orders_ddo")
                .params("archive_year", "2014")
                .params("query", "а")
                .params("provider_id", "525e9f767da3000002000001")
                .params("reg_number", "1")
                .get(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int page = jsonPath.get("meta.page");
        int items = jsonPath.get("meta.items");
        int pages = jsonPath.get("meta.pages");
        int count = jsonPath.get("meta.count");
      //  ps9 = pages;
        ct9 = count;
        Assert.assertTrue(page == 1);
        Assert.assertTrue(items == 10);
      //  Assert.assertTrue(ps9 != ps1);
        Assert.assertTrue(ct9 != ct1);
     //   Assert.assertTrue(ps9 != ps2);
        Assert.assertTrue(ct9 != ct2);
      //  Assert.assertTrue(ps9 != ps3);
        Assert.assertTrue(ct9 != ct3);
       // Assert.assertTrue(ps9 != ps4);
        Assert.assertTrue(ct9 != ct4);
       // Assert.assertTrue(ps9 != ps5);
        Assert.assertTrue(ct9 != ct5);
      //  Assert.assertTrue(ps9 != ps6);
        Assert.assertTrue(ct9 != ct6);
      //  Assert.assertTrue(ps9 != ps7);
        Assert.assertTrue(ct9 != ct7);
      //  Assert.assertTrue(ps9 != ps8);
        Assert.assertTrue(ct9 != ct8);

    }

    @Test
    public void step13() {
        Response response = given()
                .when()
                .header("Test-Authorization", 1)
                .params("journal_type", "secret_orders")
                .params("archive_year", "2023")
                //.params("query", "а")
                .params("provider_id", "525e9f767da3000002000001")
                .params("reg_number", "1")
                .get(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int page = jsonPath.get("meta.page");
        int items = jsonPath.get("meta.items");
        int pages = jsonPath.get("meta.pages");
        int count = jsonPath.get("meta.count");
      //  ps10 = pages;
        ct10 = count;
        Assert.assertTrue(page == 1);
        Assert.assertTrue(items == 10);
      //  Assert.assertTrue(ps10 != ps1);
        Assert.assertTrue(ct10 != ct1);
      //  Assert.assertTrue(ps10 != ps2);
        Assert.assertTrue(ct10 != ct2);
      //  Assert.assertTrue(ps10 != ps3);
        Assert.assertTrue(ct10 != ct3);
       // Assert.assertTrue(ps10 != ps4);
        Assert.assertTrue(ct10 != ct4);
       // Assert.assertTrue(ps10 != ps5);
        Assert.assertTrue(ct10 != ct5);
      //  Assert.assertTrue(ps10 != ps6);
        Assert.assertTrue(ct10 != ct6);
     //   Assert.assertTrue(ps10 != ps7);
        Assert.assertTrue(ct10 != ct7);
     //   Assert.assertTrue(ps10 != ps8);
        Assert.assertTrue(ct10 != ct8);
     //   Assert.assertTrue(ps10 != ps9);
        Assert.assertTrue(ct10 != ct9);

    }
    @Test
    public void step14() {
        Response response = given()
                .when()
                .header("Test-Authorization", 1)
                .params("journal_type", "incoming_orders")
                .params("archive_year", "2023")
                .params("external_document_number", "1")
                //.params("query", "а")
                .params("provider_id", "525e9f767da3000002000001")
                .params("reg_number", "1")
                .get(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int page = jsonPath.get("meta.page");
        int items = jsonPath.get("meta.items");
        int pages = jsonPath.get("meta.pages");
        int count = jsonPath.get("meta.count");
    //    ps11 = pages;
        ct11 = count;
        Assert.assertTrue(page == 1);
        Assert.assertTrue(items == 10);
     //   Assert.assertTrue(ps11 != ps1);
        Assert.assertTrue(ct11 != ct1);
      //  Assert.assertTrue(ps11 != ps2);
        Assert.assertTrue(ct11 != ct2);
       // Assert.assertTrue(ps11 != ps3);
        Assert.assertTrue(ct11 != ct3);
       // Assert.assertTrue(ps11 != ps4);
        Assert.assertTrue(ct11 != ct4);
      //  Assert.assertTrue(ps11 != ps5);
        Assert.assertTrue(ct11 != ct5);
     //   Assert.assertTrue(ps11 != ps6);
        Assert.assertTrue(ct11 != ct6);
     //   Assert.assertTrue(ps11 != ps7);
        Assert.assertTrue(ct11 != ct7);
     //   Assert.assertTrue(ps11 != ps8);
        Assert.assertTrue(ct11 != ct8);
     //   Assert.assertTrue(ps11 != ps9);
        Assert.assertTrue(ct11 != ct9);
      //  Assert.assertTrue(ps11 != ps10);
        Assert.assertTrue(ct11 != ct10);

    }

    @Test
    public void step15() {
        Response response = given()
                .when()
                .header("Test-Authorization", 1)
                .params("journal_type", "secret_incoming_orders")
                .params("archive_year", "2023")
                .params("external_document_number", "1")
                //.params("query", "а")
                .params("provider_id", "525e9f767da3000002000001")
                .params("reg_number", "1")
                .get(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int page = jsonPath.get("meta.page");
        int items = jsonPath.get("meta.items");
        int pages = jsonPath.get("meta.pages");
        int count = jsonPath.get("meta.count");
      //  ps12 = pages;
        ct12 = count;
        Assert.assertTrue(page == 1);
        Assert.assertTrue(items == 10);
    //    Assert.assertTrue(ps12 != ps1);
        Assert.assertTrue(ct12 != ct1);
     //   Assert.assertTrue(ps12 != ps2);
        Assert.assertTrue(ct12 != ct2);
     //   Assert.assertTrue(ps12 != ps3);
        Assert.assertTrue(ct12 != ct3);
      //  Assert.assertTrue(ps12 != ps4);
        Assert.assertTrue(ct12 != ct4);
      //  Assert.assertTrue(ps12 != ps5);
        Assert.assertTrue(ct12 != ct5);
      //  Assert.assertTrue(ps12 != ps6);
        Assert.assertTrue(ct12 != ct6);
     //   Assert.assertTrue(ps12 != ps7);
        Assert.assertTrue(ct12 != ct7);
     //   Assert.assertTrue(ps12 != ps8);
        Assert.assertTrue(ct12 != ct8);
    //    Assert.assertTrue(ps12 != ps9);
        Assert.assertTrue(ct12 != ct9);
     //   Assert.assertTrue(ps12 != ps10);
        Assert.assertTrue(ct12 != ct10);
     //   Assert.assertTrue(ps12 != ps11);
        Assert.assertTrue(ct12 != ct11);

    }

    @Test
    public void step16() {
        Response response = given()
                .when()
                .header("Test-Authorization", 1)
                .params("journal_type", "citizen_requests")
                .params("archive_year", "2023")
                .params("external_document_number", "1")
                .params("query", "а")
                .params("provider_id", "525e9f767da3000002000001")
                .params("reg_number", "1")
                .get(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int page = jsonPath.get("meta.page");
        int items = jsonPath.get("meta.items");
        int pages = jsonPath.get("meta.pages");
        int count = jsonPath.get("meta.count");
      //  ps13 = pages;
        ct13 = count;
        Assert.assertTrue(page == 1);
        Assert.assertTrue(items == 10);
      //  Assert.assertTrue(ps13 != ps1);
        Assert.assertTrue(ct13 != ct1);
      //  Assert.assertTrue(ps13 != ps2);
        Assert.assertTrue(ct13 != ct2);
     //   Assert.assertTrue(ps13 != ps3);
        Assert.assertTrue(ct13 != ct3);
      //  Assert.assertTrue(ps13 != ps4);
        Assert.assertTrue(ct13 != ct4);
      //  Assert.assertTrue(ps13 != ps5);
        Assert.assertTrue(ct13 != ct5);
      //  Assert.assertTrue(ps13 != ps6);
        Assert.assertTrue(ct13 != ct6);
     //   Assert.assertTrue(ps13 != ps7);
        Assert.assertTrue(ct13 != ct7);
    //    Assert.assertTrue(ps13 != ps8);
        Assert.assertTrue(ct13 != ct8);
      //  Assert.assertTrue(ps13 != ps9);
        Assert.assertTrue(ct13 != ct9);
    //    Assert.assertTrue(ps13 != ps10);
        Assert.assertTrue(ct13 != ct10);
     //   Assert.assertTrue(ps13 != ps11);
        Assert.assertTrue(ct13 != ct11);
   //     Assert.assertTrue(ps13 != ps12);
        Assert.assertTrue(ct13 != ct12);

    }





}
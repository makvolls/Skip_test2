package SKIP_API;

import API.BasicOrganizations;
import API.BasicOshsMvdOfficials;
import API.DTO.OrganizationsDto.RootOrganizations;
import API.DTO.OshsMvdOfficialsDto.RootOshsMvdOfficials;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SKIP_63 {


    String API_OFF_NOT_FOUND = "http://api.skip.rtech.ru/v1/classifiers/officials";
    String API_ORG_NOT_FOUND = "http://api.skip.rtech.ru/v1/classifiers/organizations";

    RootOshsMvdOfficials actualListOfficials;
    RootOrganizations actualListOrganizations;

    @Test
    public void step01() {
        actualListOfficials = BasicOshsMvdOfficials.getOshsMvdOfficials(1);
        Assert.assertNotNull(actualListOfficials);
    }

    @Test
    public void step02() {
        actualListOrganizations = BasicOrganizations.getOrganizations(1);
        Assert.assertNotNull(actualListOrganizations);
    }

    @Test
    public void step03() {
        Response response = given()
                .when()
                .header("Test-Authorization", 1)
                .get(API_OFF_NOT_FOUND)
                .then().log().all()
                .extract().response();

        int statusCode = response.getStatusCode();
        Assert.assertEquals(404, statusCode);
    }

    @Test
    public void step04() {
        Response response = given()
                .when()
                .header("Test-Authorization", 1)
                .get(API_ORG_NOT_FOUND)
                .then().log().all()
                .extract().response();

        int statusCode2 = response.getStatusCode();
        Assert.assertEquals(404, statusCode2);
    }
}
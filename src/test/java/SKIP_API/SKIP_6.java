package SKIP_API;

import API.BasicUser;
import API.DTO.UserDto.GetUserRootDto;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class SKIP_6 {

    int idAut1 = 3;
    String officialId1 = "528f027454c8000041000001";
    String officialId2 = "1bace4af273695ed1000128";
    String organizationId1 = "528f0169d034000038000001";
    String organizationId2 = "51bae9fdf273690a4000017d";
    int role_id1 = 31;
    int role_id2 = 29;

    GetUserRootDto listValues;


    @Test
    public void step01() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .params("official_id", officialId1)
                .get(BasicUser.API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String data=jsonPath.getString("data");
        Assert.assertTrue(!data.isEmpty());
    }

    @Test
    public void step02() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .params("official_id", officialId1)
                .get(BasicUser.API_USER)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int count = jsonPath.get("meta.count");
        List<Integer> idRoles = jsonPath.get("data.roles.id[0]");
        List<Integer> idUser = jsonPath.get("data.id");
        List<String> full_name_roles = jsonPath.get("data.roles.full_name[0]");
        List<String> full_name = jsonPath.get("data.full_name");
        List<String> organization_short_title = jsonPath.get("data.organization_short_title");
        Assert.assertTrue(count == 1);
        Assert.assertTrue(idUser.contains(3));
        Assert.assertTrue(full_name.contains("Столярова Юлия Валентиновна"));
        Assert.assertTrue(organization_short_title.contains("ОДиР ГУВО МВД России"));
        Assert.assertTrue(idRoles.contains(31));
        Assert.assertTrue(full_name_roles.contains("Все права"));


    }

    @Test
    public void step03() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .params("official_id", officialId2)
                .get(BasicUser.API_USER)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        List<String> data = jsonPath.get("data");
        int count = jsonPath.get("meta.count");
        Assert.assertTrue(data.isEmpty());
        Assert.assertTrue(count == 0);
    }

    @Test
    public void step04() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .params("organization_id", organizationId1)
                .get(BasicUser.API_USER)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<Integer> idRoles = jsonPath.get("data.roles.id");
        List<Integer> idUser = jsonPath.get("data.id");
        List<String> full_name_roles = jsonPath.get("data.roles.full_name");
        List<String> full_name = jsonPath.get("data.full_name");
        List<String> organization_short_title = jsonPath.get("data.organization_short_title");
        Assert.assertTrue(idUser.contains(3));
        Assert.assertTrue(full_name.contains("Столярова Юлия Валентиновна"));
        Assert.assertTrue(organization_short_title.contains("ОДиР ГУВО МВД России"));
        Assert.assertTrue(idRoles.toString().contains("31"));
        Assert.assertTrue(full_name_roles.toString().contains("Все права"));
        int count = jsonPath.get("meta.count");
        Assert.assertTrue(count != 0);

    }

    @Test
    public void step05() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .params("organization_id", organizationId2)
                .get(BasicUser.API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<String> data = jsonPath.get("data");
        int count = jsonPath.get("meta.count");
        Assert.assertTrue(data.isEmpty());
        Assert.assertTrue(count == 0);
    }

    @Test
    public void step06() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .params("role_id", role_id1)
                .get(BasicUser.API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<Integer> idRoles = jsonPath.get("data.roles.id[0]");
        List<Integer> idUser = jsonPath.get("data.id");
        List<String> full_name_roles = jsonPath.get("data.roles.full_name[0]");
        List<String> full_name = jsonPath.get("data.full_name");
        List<String> organization_short_title = jsonPath.get("data.organization_short_title");
        Assert.assertTrue(idUser.contains(3));
        Assert.assertTrue(full_name.contains("Столярова Юлия Валентиновна"));
        Assert.assertTrue(organization_short_title.contains("ОДиР ГУВО МВД России"));
        Assert.assertTrue(idRoles.contains(31));
        Assert.assertTrue(full_name_roles.contains("Все права"));
        int count = jsonPath.get("meta.count");
        Assert.assertTrue(count != 0);


    }

    @Test
    public void step07() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .params("role_id", role_id2)
                .get(BasicUser.API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<String> data = jsonPath.get("data");
        int count = jsonPath.get("meta.count");
        Assert.assertTrue(data.isEmpty());
        Assert.assertTrue(count == 0);
    }

    @Test
    public void step08() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .params("last_name", "с")
                .get(BasicUser.API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<Integer> idRoles = jsonPath.get("data.roles.id[0]");
        List<Integer> idUser = jsonPath.get("data.id");
        List<String> full_name_roles = jsonPath.get("data.roles.full_name[0]");
        List<String> full_name = jsonPath.get("data.full_name");
        List<String> organization_short_title = jsonPath.get("data.organization_short_title");
        Assert.assertTrue(idUser.contains(3));
        Assert.assertTrue(full_name.contains("Столярова Юлия Валентиновна"));
        Assert.assertTrue(organization_short_title.contains("ОДиР ГУВО МВД России"));
        Assert.assertTrue(idRoles.contains(31));
        Assert.assertTrue(full_name_roles.contains("Все права"));
        int count = jsonPath.get("meta.count");
        Assert.assertTrue(count != 0);


    }

    @Test
    public void step09() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .params("last_name", "Столярова")
                .get(BasicUser.API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<Integer> idRoles = jsonPath.get("data.roles.id[0]");
        List<Integer> idUser = jsonPath.get("data.id");
        List<String> full_name_roles = jsonPath.get("data.roles.full_name[0]");
        List<String> full_name = jsonPath.get("data.full_name");
        List<String> organization_short_title = jsonPath.get("data.organization_short_title");
        Assert.assertTrue(idUser.contains(3));
        Assert.assertTrue(full_name.contains("Столярова Юлия Валентиновна"));
        Assert.assertTrue(organization_short_title.contains("ОДиР ГУВО МВД России"));
        Assert.assertTrue(idRoles.contains(31));
        Assert.assertTrue(full_name_roles.contains("Все права"));
        int count = jsonPath.get("meta.count");
        Assert.assertTrue(count != 0);


    }

    @Test
    public void step10() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .params("last_name", "Столярова Юлия Валентиновна")
                .get(BasicUser.API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<String> data = jsonPath.get("data");
        int count = jsonPath.get("meta.count");
        Assert.assertTrue(data.isEmpty());
        Assert.assertTrue(count == 0);
    }


    @Test
    public void step11() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .params("last_name", "Колокольцев")
                .get(BasicUser.API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<String> data = jsonPath.get("data");
        int count = jsonPath.get("meta.count");
        Assert.assertTrue(data.isEmpty());
        Assert.assertTrue(count == 0);
    }

    @Test
    public void step12() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .params("organization_title", "г")
                .get(BasicUser.API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<Integer> idRoles = jsonPath.get("data.roles.id");
        List<Integer> idUser = jsonPath.get("data.id");
        List<String> full_name_roles = jsonPath.get("data.roles.full_name");
        List<String> full_name = jsonPath.get("data.full_name");
        List<String> organization_short_title = jsonPath.get("data.organization_short_title");
        Assert.assertTrue(idUser.contains(3));
        Assert.assertTrue(full_name.contains("Столярова Юлия Валентиновна"));
        Assert.assertTrue(organization_short_title.contains("ОДиР ГУВО МВД России"));
        Assert.assertTrue(idRoles.toString().contains("31"));
        Assert.assertTrue(full_name_roles.toString().contains("Все права"));
        int count = jsonPath.get("meta.count");
        Assert.assertTrue(count != 0);


    }

    @Test
    public void step13() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .params("organization_title", "ОДиР ГУВО МВД России")
                .get(BasicUser.API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<Integer> idRoles = jsonPath.get("data.roles.id");
        List<Integer> idUser = jsonPath.get("data.id");
        List<String> full_name_roles = jsonPath.get("data.roles.full_name");
        List<String> full_name = jsonPath.get("data.full_name");
        List<String> organization_short_title = jsonPath.get("data.organization_short_title");
        Assert.assertTrue(idUser.contains(3));
        Assert.assertTrue(full_name.contains("Столярова Юлия Валентиновна"));
        Assert.assertTrue(organization_short_title.contains("ОДиР ГУВО МВД России"));
        Assert.assertTrue(idRoles.toString().contains("31"));
        Assert.assertTrue(full_name_roles.toString().contains("Все права"));
        int count = jsonPath.get("meta.count");
        Assert.assertTrue(count != 0);


    }

    @Test
    public void step14() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .params("organization_title", "ОДиР ГУВО МВД Россииккк")
                .get(BasicUser.API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<String> data = jsonPath.get("data");
        int count = jsonPath.get("meta.count");
        Assert.assertTrue(data.isEmpty());
        Assert.assertTrue(count == 0);
    }


    @Test
    public void step15() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .params("organization_title", "ДДО МВД России")
                .get(BasicUser.API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<String> data = jsonPath.get("data");
        int count = jsonPath.get("meta.count");
        Assert.assertTrue(data.isEmpty());
        Assert.assertTrue(count == 0);
    }

}
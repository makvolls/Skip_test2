package SKIP_API;

import API.BasicRoles;
import API.BasicUser;
import API.DTO.ErrorsDTO.RootError;
import API.DTO.RolesDto.RootResponseRolesDto;
import API.DTO.UserDto.RootResponseUserDto;
import API.DTO.UserDto.RootUserWithId;
import Utils.RandomGenerateText;
import autotest.core.util.Assistant;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;


public class SKIP_1 {
    

    RootUserWithId actual;
    RootError actual2;

    RootResponseRolesDto newRole;
    RootResponseUserDto newUser;
    RootResponseUserDto upUser;

    int idAut=1;
    int idUser;
    int idRole;

    String test1= RandomGenerateText.generateUniqeTextRussianString(7);




    @BeforeClass
    public void postUser() {

        newRole = BasicRoles.createRoles(idAut, test1, true, "context_search");
        idRole = newRole.data.id;

        Response response = given()
                .when()
                .header("Test-Authorization", idAut)
                .params("official_id", "5b3bf8a6b48f00020e000001")
                .get(BasicUser.API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<String> official_id = jsonPath.get("data");

        if (official_id.isEmpty()) {
            newUser = BasicUser.createUser(idAut, "5b3bf8a6b48f00020e000001", idRole);
            idUser = newUser.data.id;
        } else {

            Response response1 = given()
                    .when()
                    .header("Test-Authorization", idAut)
                    .params("official_id", "5b3bf8a6b48f00020e000001")
                    .get(BasicUser.API_USER)
                    .then().log().all()
                    .extract().response();
            JsonPath jsonPath1 = response1.jsonPath();
            idUser = jsonPath1.get("data[0].id");
            upUser = BasicUser.updateUser(1, idUser, idRole);
            idUser = upUser.data.id;

            Step1();
        }

    }

    @AfterClass
    public void delete() {
        BasicUser.updateUser(idAut, idUser, 5);
        BasicRoles.deleteRole(idAut, idRole);
        BasicUser.deleteUser(idAut, idUser);


    }

    @Test
    public void Step1() {
        actual = BasicUser.getUser(idAut, 1);
        Assert.assertTrue(actual.data.full_name.equals("Колокольцев Владимир Александрович"));
    }

    @Test
    public void Step2() {
        actual2 = BasicUser.getErrorUser(idUser, 1);
        Assert.assertTrue(actual2.error.equals("Доступ к ресурсу запрещен"));

    }


}

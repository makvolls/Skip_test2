package SKIP_API;

import API.BasicRoles;
import API.BasicUser;
import API.DTO.RolesDto.RootResponseRolesDto;
import API.DTO.UserDto.RootResponseUserDto;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SKIP_7 {


    RootResponseRolesDto createRolesGlobal, createRoles;
    RootResponseUserDto createUser;

    @BeforeTest
    public void createRoles(){
        //createRolesGlobal = BasicRoles.createRoles(3, "forTest7Global", true, "index_users");
        //createRoles = BasicRoles.createRoles(1, "forTest7", false, "index_users");

    }

    @Test
    public void Step01(){
        createUser = BasicUser.createUser(1, "528f027454c8000041000001", 275);//createRolesGlobal.data.id
        Assert.assertTrue(createUser.data.official_id.equals("528f027454c8000041000001"));
        Assert.assertTrue(createUser.data.provider_id.equals("525e9f767da3000002000001"));
        Assert.assertTrue(createUser.data.full_name.equals("Столярова Юлия Валентиновна"));
        Assert.assertTrue(createUser.data.position.equals("Заместитель начальника"));
        Assert.assertTrue(createUser.data.organization_short_title.equals("ОДиР ГУВО МВД России"));
        Assert.assertTrue(createUser.data.provider_name.equals("Департамент делопроизводства и работы с обращениями " +
                "граждан и организаций Министерства внутренних дел Российской Федерации (ДДО МВД России)"));
        Assert.assertTrue(createUser.data.roles[0].id==275);//createRolesGlobal.data.id
        Assert.assertTrue(createUser.data.roles[0].full_name.equals("forTest7Global (ОДиР ГУВО МВД России)"));
    }
    @Test
    public void Step02(){
        Map<String, String> user = new HashMap<>();
        user.put("official_id", "528f027454c8000041000001");
        user.put( "role_ids", "[275]");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(user)
                .post(BasicUser.API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();


    }

}

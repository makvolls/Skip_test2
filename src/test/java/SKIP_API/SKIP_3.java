package SKIP_API;

import API.BasicApi;
import API.BasicRoles;
import API.BasicUser;
import API.DTO.ErrorsDTO.RolesErrors.RootNameErrors;
import API.DTO.ErrorsDTO.RootError;
import API.DTO.RolesDto.RootResponseRolesDto;
import API.DTO.UserDto.RootResponseUserDto;
import autotest.core.util.Assistant;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class SKIP_3 {
    RootResponseRolesDto roles, roles2, roles3, roles4, roles5, roles6, roles7;
    RootNameErrors error;
    RootError error1, delete;
    RootResponseRolesDto newRole;
    RootResponseUserDto updateUser;
    RootResponseUserDto newUser;
    int idAut1 = 1;
    int idAut2 = 3;


    int idUser;
    int idRole;
    String name = "Автотест111";
    String name2 = "Автотест2";
    String name3 = "Автотест3";
    String test1= Assistant.generateUniqeTextRussianString(7);

    @BeforeClass
    public void postUser() {
        newRole = BasicRoles.createRoles(idAut1, test1, true, "context_search");
        idRole = newRole.data.id;
        Response response = given()
                .when()
                .header("Test-Authorization", idAut2)
                .params("official_id", "59d4035c21c30002d7000001")
                .get(BasicUser.API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<String> official_id = jsonPath.get("data");

        if (official_id.isEmpty()) {
            newUser = BasicUser.createUser(idAut2, "59d4035c21c30002d7000001", idRole);
            idUser = newUser.data.id;
        } else {

            Response response1 = given()
                    .when()
                    .header("Test-Authorization", idAut2)
                    .params("official_id", "59d4035c21c30002d7000001")
                    .get(BasicUser.API_USER)
                    .then().log().all()
                    .extract().response();
            JsonPath jsonPath1 = response1.jsonPath();
            idUser = jsonPath1.get("data[0].id");
            updateUser= BasicUser.updateUser(idAut2,idUser,idRole);
            idUser=updateUser.data.id;

            Step01();
        }
        updateUser= BasicUser.updateUser(idAut2,idUser,idRole);
        idUser=updateUser.data.id;

    }
    @AfterClass
    public void delete(){
        BasicUser.updateUser(idAut2, idUser, 5);
        BasicRoles.deleteRole(idAut1,idRole);
        BasicUser.deleteUser(idAut2,idUser);

    }

        @Test
    public void Step01(){
        roles = BasicRoles.createRoles(idAut1, name, true, "index_users");
        Assert.assertNotNull(roles.getData().id);
        Assert.assertNotNull(roles.getData().name);
        Assert.assertNotNull(roles.getData().full_name);
        Assert.assertNotNull(roles.getData().global);
        Assert.assertNotNull(roles.getData().provider_short_title);
        Assert.assertNotNull(roles.getData().rights_ids);
        Assert.assertNotNull(roles.getData().provider_id);

    }
    @Test
    public void Step02(){
        roles2 = BasicRoles.createRoles(idAut1, name, false, "index_users");
        Assert.assertTrue(!roles2.data.global);
    }

    @Test
    public void Step03(){
        error =  BasicRoles.createErrorRoles(idAut1, "", false, "index_users");
        Assert.assertTrue(error.getErrors().getName()[0].equals("не может быть пустым"));

    }

    @Test
    public void Step04(){
        error =  BasicRoles.createErrorRoles(idAut1, name, false, "");
        Assert.assertTrue(error.getErrors().getFull_name()[0].equals("уже существует"));
        Assert.assertTrue(error.getErrors().getRights_ids()[0].equals("имеет непредусмотренное значение"));


    }
    @Test
    public void Step05(){
        roles3 = BasicRoles.updateRole(idAut1, roles.getData().id, name2, false,
                "index_users" );
        Assert.assertTrue(roles3.data.name.equals(name2));
        Assert.assertTrue(roles3.data.full_name.equals(name2));
        Assert.assertTrue(roles3.data.provider_short_title.equals("ДДО МВД России"));
        Assert.assertTrue(roles3.data.rights_ids[0].equals("index_users"));
        Assert.assertTrue(!roles3.data.global);
        Assert.assertTrue(roles3.data.provider_id.equals("525e9f767da3000002000001"));


    }
    @Test
    public void Step06(){
        roles4 = BasicRoles.updateRole(idAut1, roles2.getData().id, name2, true,
                "index_users");
        Assert.assertTrue(roles4.data.name.equals(name2));
        Assert.assertTrue(roles4.data.full_name.equals(name2 + " (ДДО МВД России)"));
        Assert.assertTrue(roles4.data.provider_short_title.equals("ДДО МВД России"));
        Assert.assertTrue(roles4.data.rights_ids[0].equals("index_users"));
        Assert.assertTrue(roles4.data.global);
        Assert.assertTrue(roles4.data.provider_id.equals("525e9f767da3000002000001"));

    }
    @Test
    public void Step07(){
        roles5 = BasicRoles.updateRole(idAut1, roles2.getData().id, name2, true,
                "index_users", "setup_banners");
        Assert.assertTrue(roles5.data.name.equals(name2));
        Assert.assertTrue(roles5.data.full_name.equals(name2 + " (ДДО МВД России)"));
        Assert.assertTrue(roles5.data.provider_short_title.equals("ДДО МВД России"));
        Assert.assertTrue(roles5.data.rights_ids[0].contains("index_users"));
        Assert.assertTrue(roles5.data.rights_ids[1].contains("setup_banners"));

        Assert.assertTrue(roles5.data.global);
        Assert.assertTrue(roles5.data.provider_id.equals("525e9f767da3000002000001"));
    }
    @Test
    public void Step08(){
        roles6 = BasicRoles.createRoles(idAut1, name3, true, "index_users");
        Assert.assertTrue(roles6.data.name.equals(name3));
        Assert.assertTrue(roles6.data.full_name.equals(name3 + " (ДДО МВД России)"));
        Assert.assertTrue(roles6.data.provider_short_title.equals("ДДО МВД России"));
        Assert.assertTrue(roles6.data.rights_ids[0].equals("index_users"));
        Assert.assertTrue(roles6.data.global);
        Assert.assertTrue(roles6.data.provider_id.equals("525e9f767da3000002000001"));
    }
    @Test
    public void Step09(){
        roles7 = BasicRoles.updateRole(idAut1, roles6.getData().id, name3, false, "index_users");
        Assert.assertTrue(roles7.data.name.equals(name3));
        Assert.assertTrue(roles7.data.full_name.equals(name3));
        Assert.assertTrue(roles7.data.provider_short_title.equals("ДДО МВД России"));
        Assert.assertTrue(roles7.data.rights_ids[0].equals("index_users"));
        Assert.assertTrue(!roles7.data.global);
        Assert.assertTrue(roles7.data.provider_id.equals("525e9f767da3000002000001"));
    }
    @Test
    public void Step10(){
        error = BasicRoles.updateErrorRoles(idAut1, idRole, "ДДО1", false, "index_users");
        Assert.assertTrue(error.getErrors().global[0].equals("Данную роль используют пользователи в других провайдерах" +
                ", поэтому чек-бокс «Глобальная роль» снять нельзя"));
        // Error "full_name": "уже существует"

    }
    @Test
    public void Step11(){
        error1 = BasicRoles.updateErrorRolesNoAccess(idUser, idRole, name3, false, "index_users");
        Assert.assertTrue(error1.error.equals("Доступ к ресурсу запрещен"));
    }
    @Test
    public void Step12(){
        BasicRoles.deleteRole(idAut1, roles.getData().id);
        delete = BasicRoles.deleteRoleТotFound(1, roles.getData().id);
        Assert.assertTrue(delete.getError().equals("Запись для Permissions::Role с id = "+roles.getData().id+" не найдена"));

    }
    @Test
    public void Step13(){
        BasicRoles.deleteRole(idAut1, roles2.getData().id);
        delete = BasicRoles.deleteRoleТotFound(1, roles2.getData().id);
        Assert.assertTrue(delete.getError().equals("Запись для Permissions::Role с id = "+roles2.getData().id+" не найдена"));
    }
    @Test
    public void Step14(){
        BasicRoles.deleteRole(idAut1, roles6.getData().id);
        delete = BasicRoles.deleteRoleТotFound(1, roles6.getData().id);
        Assert.assertTrue(delete.getError().equals("Запись для Permissions::Role с id = "+roles6.getData().id+" не найдена"));
    }








}

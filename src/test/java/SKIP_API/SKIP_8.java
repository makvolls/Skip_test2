package SKIP_API;

import API.BasicRoles;
import API.BasicUser;
import API.DTO.ErrorsDTO.RootError;
import API.DTO.RolesDto.RootResponseRolesDto;
import API.DTO.RolesDto.RootRolesDto;
import API.DTO.UserDto.RootResponseUserDto;
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

public class SKIP_8 {

    int idUser;
    int idRole,idRole2;


    int idAut1=1;
    int idAut3=3;

    RootError error, checkDelete;
    RootResponseRolesDto upRole, createRole, searchRoles1;
    RootRolesDto searchRoles;
    RootResponseUserDto newUser;
    RootResponseUserDto upUser;
    RootResponseRolesDto newRole,newRole2;

    String name = RandomGenerateText.generateUniqeTextRussianString(5);
    String upName = RandomGenerateText.generateUniqeTextRussianString(6);
    String test1= RandomGenerateText.generateUniqeTextRussianString(6);
    String test2=RandomGenerateText.generateUniqeTextRussianString(6);
    String test3=RandomGenerateText.generateUniqeTextRussianString(6);
    String test4=RandomGenerateText.generateUniqeTextRussianString(6);
    String test5=RandomGenerateText.generateUniqeTextRussianString(6);


    @BeforeClass
    public void step00() {
        newRole = BasicRoles.createRoles(idAut3, test1, true,
                "show_classifiers");
        idRole = newRole.data.id;

        newRole2 = BasicRoles.createRoles(idAut3, test2, true,
                "show_classifiers");
        idRole2 = newRole2.data.id;


        Response response = given()
                .when()
                .header("Test-Authorization", idAut3)
                .params("official_id", "5b640f1482a9000012000001")
                .get(BasicUser.API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<String> official_id = jsonPath.get("data");

        if (official_id.isEmpty()) {
            newUser = BasicUser.createUser(idAut3, "5b640f1482a9000012000001", idRole);
            idUser = newUser.data.id;
        } else {

            Response response1 = given()
                    .when()
                    .header("Test-Authorization", idAut3)
                    .params("official_id", "5b640f1482a9000012000001")
                    .get(BasicUser.API_USER)
                    .then().log().all()
                    .extract().response();
            JsonPath jsonPath1 = response1.jsonPath();
            idUser = jsonPath1.get("data[0].id");
            upUser = BasicUser.updateUser(idAut1, idUser, idRole);
            idUser = upUser.data.id;

            Step01();

        }
        }
        @AfterClass
        public void delete() {

            BasicUser.updateUser(idAut3, idUser, 5);
            BasicRoles.deleteRole(idAut3, idRole);
            BasicUser.deleteUser(idAut3, idUser);
            BasicRoles.deleteRole(idAut3,idRole2);
    }

    @Test
    public void Step01(){
        error = BasicRoles.getNoAccessRoles(idUser);
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));
    }
    @Test
    public void Step02(){
        error = BasicRoles.getErrorRole(idUser, idRole2);
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));
    }
    @Test
    public void Step03(){
        error = BasicRoles.createNoAccessRoles(idUser, test3, true, "show_roles");
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));

    }
    @Test
    public void Step04(){
        error = BasicRoles.updateErrorRolesNoAccess(idUser, idRole2, test4, false,
                "index_users");
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));
    }
    @Test
    public void Step05(){
        error = BasicRoles.deleteRoleNoRights(idUser,idRole2);
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));
    }
    @Test
    public void Step06(){
        upRole = BasicRoles.updateRole(idAut3,idRole, test5, true, "index_roles");
        Assert.assertTrue(upRole.getData().name.equals(test5));
        Assert.assertTrue(upRole.getData().full_name.equals(test5+" (ОДиР ГУВО МВД России)"));
        Assert.assertTrue(upRole.getData().provider_short_title.equals("ОДиР ГУВО МВД России"));
        Assert.assertTrue(upRole.getData().global);
        Assert.assertTrue(upRole.getData().rights_ids[0].equals("index_roles"));
    }
    @Test
    public void Step07(){
        searchRoles = BasicRoles.getRoles(idUser);
        Assert.assertNotNull(searchRoles.data);
    }
    @Test
    public void Step08(){
        error = BasicRoles.getErrorRole(idUser, idRole2);
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));

        error = BasicRoles.createNoAccessRoles(idUser, test3, true, "show_roles");
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));

        error = BasicRoles.updateErrorRolesNoAccess(idUser, idRole2, test4, false,
                "index_users");
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));

        error = BasicRoles.deleteRoleNoRights(idUser,idRole2);
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));


    }
    @Test
    public void Step09(){
        upRole = BasicRoles.updateRole(idAut3, idRole, test5, true,
                "index_roles", "create_roles");
        Assert.assertTrue(upRole.getData().rights_ids[0].equals("index_roles"));
        Assert.assertTrue(upRole.getData().rights_ids[1].equals("create_roles"));

    }
    @Test
    public void Step10(){
        createRole = BasicRoles.createRoles(idAut3, name, true, "show_roles");
        Assert.assertNotNull(createRole.getData());
        Assert.assertTrue(createRole.getData().name.equals(name));
    }
    @Test
    public void Step11(){
        error = BasicRoles.getErrorRole(idUser, idRole);
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));

        error = BasicRoles.updateErrorRolesNoAccess(idUser, idRole, "Test roles1", false,
                "index_users");
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));

        error = BasicRoles.deleteRoleNoRights(idUser,idRole);
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));

    }
    @Test
    public void Step12(){
        upRole = BasicRoles.updateRole(idAut3, idRole, test4, true,
                "index_roles", "show_roles");
        Assert.assertTrue(upRole.getData().rights_ids[0].equals("index_roles"));
        Assert.assertTrue(upRole.getData().rights_ids[1].equals("show_roles"));
    }
    @Test
    public void Step13(){
        searchRoles1 = BasicRoles.getRole(idUser, idRole);
        Assert.assertNotNull(searchRoles1.getData());
    }
    @Test
    public void Step14(){
        error = BasicRoles.updateErrorRolesNoAccess(idUser, idRole, "Test roles1", false,
                "index_users");
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));

        error = BasicRoles.deleteRoleNoRights(idUser,idRole);
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));
    }
    @Test
    public void Step15(){
        upRole = BasicRoles.updateRole(idAut3, idRole, test4, true,
                "index_roles", "update_roles");
        Assert.assertTrue(upRole.getData().rights_ids[0].equals("index_roles"));
        Assert.assertTrue(upRole.getData().rights_ids[1].equals("update_roles"));
    }
    @Test
    public void Step16(){
        upRole = BasicRoles.updateRole(idUser, createRole.getData().id, upName, true,
                "index_users");
        Assert.assertTrue(upRole.getData().rights_ids[0].equals("index_users"));

    }
    @Test
    public void Step17(){
        error = BasicRoles.getErrorRole(idUser, idRole);
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));

        error = BasicRoles.createNoAccessRoles(idUser, test3, true, "show_roles");
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));

        error = BasicRoles.deleteRoleNoRights(idUser,idRole);
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));

    }
    @Test
    public void Step18(){
        upRole = BasicRoles.updateRole(idAut3, idRole, test5, true,
                "index_roles", "destroy_roles");
        Assert.assertTrue(upRole.getData().rights_ids[0].equals("index_roles"));
        Assert.assertTrue(upRole.getData().rights_ids[1].equals("destroy_roles"));
    }
    @Test
    public void Step19(){
        BasicRoles.deleteRole(idUser, createRole.getData().id);
        checkDelete = BasicRoles.deleteRoleТotFound(idUser, createRole.getData().id);
        Assert.assertTrue(checkDelete.error.equals("Запись для Permissions::Role с id = "+createRole.getData()
                .id+" не найдена"));
    }
    @Test
    public void Step20(){
        error = BasicRoles.getErrorRole(idUser, idRole);
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));

        error = BasicRoles.createNoAccessRoles(idUser, test3, true, "show_roles");
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));

        error = BasicRoles.updateErrorRolesNoAccess(idUser, idRole, "Test roles1", false,
                "index_users");
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));
    }
    @Test
    public void Step21(){
        upRole = BasicRoles.updateRole(idAut3, idRole, test5, true,
                "create_users");
        Assert.assertTrue(upRole.getData().rights_ids[0].equals("create_users"));
    }





}

package SKIP_API;

import API.BasicRoles;
import API.BasicUser;
import API.DTO.ErrorsDTO.RootError;
import API.DTO.RolesDto.RootResponseRolesDto;
import API.DTO.RolesDto.RootRolesDto;
import API.DTO.UserDto.RootResponseUserDto;
import Utils.RandomGenerateText;
import autotest.core.util.Assistant;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
    int idRole, idRole2;


    int idAut1 = 1;
    int idAut3 = 3;

    String name = RandomGenerateText.generateUniqeTextRussianString(5);
    String upName = RandomGenerateText.generateUniqeTextRussianString(6);
    String test1 = RandomGenerateText.generateUniqeTextRussianString(6);
    String test2 = RandomGenerateText.generateUniqeTextRussianString(6);
    String test3 = RandomGenerateText.generateUniqeTextRussianString(6);
    String test4 = RandomGenerateText.generateUniqeTextRussianString(6);
    String test5 = RandomGenerateText.generateUniqeTextRussianString(6);


    @BeforeClass
    public void step00() {

        JsonObject object1 = new JsonObject();

        object1.addProperty("name", test1);
        object1.addProperty("desc", "ASC");
        object1.addProperty("global", true);

        JsonArray rights_ids1 = new JsonArray();
        rights_ids1.add("show_classifiers");
        object1.add("rights_ids", rights_ids1);
        Response response1 = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut3)
                .body(object1.toString())
                .post(BasicRoles.API_ROLES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath1 = response1.jsonPath();
        int id1 = jsonPath1.getInt("data.id");
        idRole = id1;


        JsonObject object2 = new JsonObject();

        object2.addProperty("name", test2);
        object2.addProperty("desc", "ASC");
        object2.addProperty("global", true);

        JsonArray rights_ids2 = new JsonArray();
        rights_ids2.add("show_classifiers");
        object2.add("rights_ids", rights_ids2);
        Response response2 = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut3)
                .body(object2.toString())
                .post(BasicRoles.API_ROLES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath2 = response2.jsonPath();
        int id2 = jsonPath2.getInt("data.id");
        idRole2 = id2;


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

            JsonObject object = new JsonObject();
            object.addProperty("official_id", "5b640f1482a9000012000001");
            JsonArray array = new JsonArray();
            array.add(idRole);
            object.add("role_ids", array);

            Response response3 = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", idAut3)
                    .body(object.toString())
                    .post(BasicUser.API_USER)
                    .then().log().all()
                    .extract().response();
            JsonPath jsonPath3 = response3.jsonPath();
            int id = jsonPath3.getInt("data.id");
            idUser = id;

        } else {

            Response response4 = given()
                    .when()
                    .header("Test-Authorization", idAut3)
                    .params("official_id", "5b640f1482a9000012000001")
                    .get(BasicUser.API_USER)
                    .then().log().all()
                    .extract().response();
            JsonPath jsonPath4 = response4.jsonPath();
            idUser = jsonPath4.get("data[0].id");

            JsonObject object3 = new JsonObject();
            JsonArray array3 = new JsonArray();
            array3.add(idRole);
            object3.add("role_ids", array3);

            Response response5 = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", idAut3)
                    .body(object3.toString())
                    .put(BasicUser.API_USER + "/" + idUser)
                    .then().log().all()
                    .extract().response();
            JsonPath jsonPath5 = response5.jsonPath();
            int id5 = jsonPath5.getInt("data.id");
            idUser = id5;
        }
    }

    @AfterClass
    public void delete() {
        JsonObject object = new JsonObject();
        JsonArray array = new JsonArray();
        array.add(5);
        object.add("role_ids", array);

        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut3)
                .body(object.toString())
                .put(BasicUser.API_USER + "/" + idUser)
                .then().log().all()
                .extract().response();
        BasicRoles.deleteRole(idAut3, idRole);
        BasicUser.deleteUser(idAut3, idUser);
        BasicRoles.deleteRole(idAut3, idRole2);
    }

    @Test
    public void Step01() {

        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idUser)
                .get(BasicRoles.API_ROLES+"/"+idRole)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("error");
        Assert.assertTrue(error.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void Step02() {

        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idUser)
                .get(BasicRoles.API_ROLES + "/" + idRole2)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.getString("error");
        Assert.assertTrue(error.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void Step03() {
        JsonObject object1 = new JsonObject();

        object1.addProperty("name", test3);
        object1.addProperty("desc", "1");
        object1.addProperty("global", true);

        JsonArray rights_ids1 = new JsonArray();
        rights_ids1.add("show_roles");
        object1.add("rights_ids", rights_ids1);
        Response response1 = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idUser)
                .body(object1.toString())
                .post(BasicRoles.API_ROLES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response1.jsonPath();
        String error = jsonPath.getString("error");
        Assert.assertTrue(error.equals("Доступ к ресурсу запрещен"));
    }
    @Test
    public void Step04() {
        JsonObject object1 = new JsonObject();

        object1.addProperty("name", test3);
        object1.addProperty("desc", "1");
        object1.addProperty("global", true);

        JsonArray rights_ids1 = new JsonArray();
        rights_ids1.add("show_roles");
        object1.add("rights_ids", rights_ids1);
        Response response1 = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idUser)
                .body(object1.toString())
                .post(BasicRoles.API_ROLES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response1.jsonPath();
        String error = jsonPath.getString("error");
        Assert.assertTrue(error.equals("Доступ к ресурсу запрещен"));
    }
}

//    @Test
//    public void Step04(){
//        error = BasicRoles.updateErrorRolesNoAccess(idUser, idRole2, test4, false,
//                "index_users");
//        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));
//    }
//    @Test
//    public void Step05(){
//        error = BasicRoles.deleteRoleNoRights(idUser,idRole2);
//        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));
//    }
//    @Test
//    public void Step06(){
//        upRole = BasicRoles.updateRole(idAut3,idRole, test5, true, "index_roles");
//        Assert.assertTrue(upRole.getData().name.equals(test5));
//        Assert.assertTrue(upRole.getData().full_name.equals(test5+" (ОДиР ГУВО МВД России)"));
//        Assert.assertTrue(upRole.getData().provider_short_title.equals("ОДиР ГУВО МВД России"));
//        Assert.assertTrue(upRole.getData().global);
//        Assert.assertTrue(upRole.getData().rights_ids[0].equals("index_roles"));
//    }
//    @Test
//    public void Step07(){
//        searchRoles = BasicRoles.getRoles(idUser);
//        Assert.assertNotNull(searchRoles.data);
//    }
//    @Test
//    public void Step08(){
//        error = BasicRoles.getErrorRole(idUser, idRole2);
//        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));
//
//        error = BasicRoles.createNoAccessRoles(idUser, test3, true, "show_roles");
//        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));
//
//        error = BasicRoles.updateErrorRolesNoAccess(idUser, idRole2, test4, false,
//                "index_users");
//        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));
//
//        error = BasicRoles.deleteRoleNoRights(idUser,idRole2);
//        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));
//
//
//    }
//    @Test
//    public void Step09(){
//        upRole = BasicRoles.updateRole(idAut3, idRole, test5, true,
//                "index_roles", "create_roles");
//        Assert.assertTrue(upRole.getData().rights_ids[0].equals("index_roles"));
//        Assert.assertTrue(upRole.getData().rights_ids[1].equals("create_roles"));
//
//    }
//    @Test
//    public void Step10(){
//        createRole = BasicRoles.createRoles(idAut3, name, true, "show_roles");
//        Assert.assertNotNull(createRole.getData());
//        Assert.assertTrue(createRole.getData().name.equals(name));
//    }
//    @Test
//    public void Step11(){
//        error = BasicRoles.getErrorRole(idUser, idRole);
//        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));
//
//        error = BasicRoles.updateErrorRolesNoAccess(idUser, idRole, "Test roles1", false,
//                "index_users");
//        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));
//
//        error = BasicRoles.deleteRoleNoRights(idUser,idRole);
//        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));
//
//    }
//    @Test
//    public void Step12(){
//        upRole = BasicRoles.updateRole(idAut3, idRole, test4, true,
//                "index_roles", "show_roles");
//        Assert.assertTrue(upRole.getData().rights_ids[0].equals("index_roles"));
//        Assert.assertTrue(upRole.getData().rights_ids[1].equals("show_roles"));
//    }
//    @Test
//    public void Step13(){
//        searchRoles1 = BasicRoles.getRole(idUser, idRole);
//        Assert.assertNotNull(searchRoles1.getData());
//    }
//    @Test
//    public void Step14(){
//        error = BasicRoles.updateErrorRolesNoAccess(idUser, idRole, "Test roles1", false,
//                "index_users");
//        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));
//
//        error = BasicRoles.deleteRoleNoRights(idUser,idRole);
//        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));
//    }
//    @Test
//    public void Step15(){
//        upRole = BasicRoles.updateRole(idAut3, idRole, test4, true,
//                "index_roles", "update_roles");
//        Assert.assertTrue(upRole.getData().rights_ids[0].equals("index_roles"));
//        Assert.assertTrue(upRole.getData().rights_ids[1].equals("update_roles"));
//    }
//    @Test
//    public void Step16(){
//        upRole = BasicRoles.updateRole(idUser, createRole.getData().id, upName, true,
//                "index_users");
//        Assert.assertTrue(upRole.getData().rights_ids[0].equals("index_users"));
//
//    }
//    @Test
//    public void Step17(){
//        error = BasicRoles.getErrorRole(idUser, idRole);
//        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));
//
//        error = BasicRoles.createNoAccessRoles(idUser, test3, true, "show_roles");
//        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));
//
//        error = BasicRoles.deleteRoleNoRights(idUser,idRole);
//        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));
//
//    }
//    @Test
//    public void Step18(){
//        upRole = BasicRoles.updateRole(idAut3, idRole, test5, true,
//                "index_roles", "destroy_roles");
//        Assert.assertTrue(upRole.getData().rights_ids[0].equals("index_roles"));
//        Assert.assertTrue(upRole.getData().rights_ids[1].equals("destroy_roles"));
//    }
//    @Test
//    public void Step19(){
//        BasicRoles.deleteRole(idUser, createRole.getData().id);
//        checkDelete = BasicRoles.deleteRoleТotFound(idUser, createRole.getData().id);
//        Assert.assertTrue(checkDelete.error.equals("Запись для Permissions::Role с id = "+createRole.getData()
//                .id+" не найдена"));
//    }
//    @Test
//    public void Step20(){
//        error = BasicRoles.getErrorRole(idUser, idRole);
//        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));
//
//        error = BasicRoles.createNoAccessRoles(idUser, test3, true, "show_roles");
//        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));
//
//        error = BasicRoles.updateErrorRolesNoAccess(idUser, idRole, "Test roles1", false,
//                "index_users");
//        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));
//    }
//    @Test
//    public void Step21(){
//        upRole = BasicRoles.updateRole(idAut3, idRole, test5, true,
//                "create_users");
//        Assert.assertTrue(upRole.getData().rights_ids[0].equals("create_users"));
//    }
//
//
//
//
//
//}

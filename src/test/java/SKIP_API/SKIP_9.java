package SKIP_API;

import API.BasicProviders;
import API.BasicRoles;
import API.BasicUser;
import API.DTO.ErrorsDTO.RootError;
import API.DTO.ProvidersDTO.RootPermissions;
import API.DTO.ProvidersDTO.RootSearchProvider;
import API.DTO.RolesDto.RootResponseRolesDto;
import API.DTO.RolesDto.RootRolesDto;
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

public class SKIP_9 {
    RootError providerNoAccess;
    RootResponseRolesDto upRole;
    RootSearchProvider searchProvider, updateProvider;
    RootPermissions searchProviders;



    int idUser;
    int idRole,idRole2;


    int idAut1=1;
    int idAut3=3;

    RootResponseUserDto newUser;
    RootResponseUserDto upUser;
    RootResponseRolesDto newRole,newRole2;

    String test1=Assistant.generateUniqeTextRussianString(6);
    String test2=Assistant.generateUniqeTextRussianString(6);
    String test3=Assistant.generateUniqeTextRussianString(6);
    String idOfficial="5b640f1482a9000012000001";
    String idProvider="525e9f767da3000002000001";



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
                .params("official_id", idOfficial)
                .get(BasicUser.API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<String> official_id = jsonPath.get("data");

        if (official_id.isEmpty()) {
            newUser = BasicUser.createUser(idAut3,idOfficial, idRole);
            idUser = newUser.data.id;
        } else {

            Response response1 = given()
                    .when()
                    .header("Test-Authorization", idAut3)
                    .params("official_id", idOfficial)
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
        providerNoAccess = BasicProviders.getProvidersNoAccess(idUser);
        Assert.assertTrue(providerNoAccess.error.equals("Доступ к ресурсу запрещен"));
    }
    @Test
    public void Step02(){
        providerNoAccess = BasicProviders.getProviderNoAccess(idUser, idProvider);
        Assert.assertTrue(providerNoAccess.error.equals("Доступ к ресурсу запрещен"));

    }
    @Test
    public void Step03(){
        providerNoAccess = BasicProviders.updateProviderNoAccess(idUser, idProvider
                , true);
        Assert.assertTrue(providerNoAccess.error.equals("Доступ к ресурсу запрещен"));

    }
    @Test
    public void Step04(){
        upRole = BasicRoles.updateRole(idAut3, idRole, test3, true, "index_providers");
        Assert.assertTrue(upRole.getData().rights_ids[0].equals("index_providers"));
    }
    @Test
    public void Step05(){
        searchProviders = BasicProviders.getProviders(idUser);
        Assert.assertNotNull(searchProviders.data);
    }
    @Test
    public void Step06(){
        searchProvider = BasicProviders.getProvider(idUser, idProvider);
        Assert.assertNotNull(searchProvider.data);
        Assert.assertTrue(searchProvider.getData().id.equals(idProvider));

    }
    @Test
    public void Step07(){
        providerNoAccess = BasicProviders.updateProviderNoAccess(idUser, idProvider
                , true);
        Assert.assertTrue(providerNoAccess.error.equals("Доступ к ресурсу запрещен"));
    }
    @Test
    public void Step08(){
        upRole = BasicRoles.updateRole(idAut3, idRole, test3, true, "index_providers",
                "update_providers");
        Assert.assertTrue(upRole.getData().rights_ids[0].equals("index_providers"));
        Assert.assertTrue(upRole.getData().rights_ids[1].equals("update_providers"));
    }
    @Test
    public void Step09(){
        updateProvider = BasicProviders.updateProvider(idUser, idProvider, true);
        Assert.assertTrue(updateProvider.getData().training);

    }
    @Test
    public void Step10(){
        searchProviders = BasicProviders.getProviders(idUser);
        Assert.assertNotNull(searchProviders.data);

        searchProvider = BasicProviders.getProvider(idUser, idProvider);
        Assert.assertNotNull(searchProvider.data);
        Assert.assertTrue(searchProvider.getData().id.equals(idProvider));
    }
    @Test
    public void Step11(){
        updateProvider = BasicProviders.updateProvider(idUser, idProvider, false);
        Assert.assertTrue(!updateProvider.getData().training);
    }
    @Test
    public void Step12(){
        upRole = BasicRoles.updateRole(idAut3, idRole, test3, true, "update_providers");
        Assert.assertTrue(upRole.getData().rights_ids[0].equals("update_providers"));
    }
    @Test
    public void Step13(){
        providerNoAccess = BasicProviders.getProvidersNoAccess(idUser);
        Assert.assertTrue(providerNoAccess.error.equals("Доступ к ресурсу запрещен"));

        providerNoAccess = BasicProviders.getProviderNoAccess(idUser, idProvider);
        Assert.assertTrue(providerNoAccess.error.equals("Доступ к ресурсу запрещен"));

        providerNoAccess = BasicProviders.updateProviderNoAccess(idUser, idProvider
                , true);
        Assert.assertTrue(providerNoAccess.error.equals("Доступ к ресурсу запрещен"));
    }

}

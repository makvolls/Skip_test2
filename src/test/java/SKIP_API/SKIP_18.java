package SKIP_API;

import API.BasicRoles;
import API.BasicUser;
import API.DTO.ErrorsDTO.RootError;
import API.DTO.RolesDto.RootGetRolesWithId;
import API.DTO.RolesDto.RootResponseRolesDto;
import API.DTO.UserDto.RootResponseUserDto;
import API.DTO.UserDto.RootUserWithId;
import API.DTO.UserDto.RootUserWithIdCS;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SKIP_18 {

    public static String API_USER = "http://api.skip.rtech.ru/v1/permissions/users";

    int autId = 1;
    int autId2 = 6;
    int idRoles = 5;
    String full_name_roles;
    String full_name_user;
    String organization_short_title1;
    RootGetRolesWithId actualValue;
    RootUserWithIdCS updateValue;
    RootError errorListValue, errorListValue2, createError, updateError, deleteError, createError2,
            updateError2, deleteError2, updateError3, deleteError3, createError3, deleteError4, createError4, updateError4, deleteErrorUser;
    RootResponseRolesDto updateRole, updateRole2, updateRole3, updateRole4, updateRole5;
    RootUserWithId actualValueUser, actualValueUser2, actualValueUser3, actualValueUser4;
    RootResponseUserDto newValueUser, updateValueUser;


    @BeforeTest
    public void before() {
        //     BasicUser.deleteUser(autId,7);
        BasicRoles.updateRole(1, idRoles, "тест22", true, "show_protocol_of_works");
        ;
    }

    @Test
    public void step01() {
        actualValue = BasicRoles.getRolesWithId(autId, idRoles);
        Assert.assertEquals(idRoles, actualValue.data.id);
        Assert.assertTrue(actualValue.data.name.equals("тест22"));
        Assert.assertTrue(actualValue.data.full_name.equals("тест22 (ДДО МВД России)"));
        full_name_roles = actualValue.data.full_name;
        Assert.assertTrue(actualValue.data.provider_short_title.equals("ДДО МВД России"));
        Assert.assertTrue(actualValue.data.global);
        Assert.assertTrue(actualValue.data.provider_id.equals("525e9f767da3000002000001"));
    }

    @Test
    public void step02() {

        updateValue = BasicUser.updateRoleWithRole_ids(autId, 6, idRoles);
        Assert.assertEquals(idRoles, updateValue.data.roles[0].id);
        Assert.assertTrue(updateValue.data.roles[0].full_name.equals(full_name_roles));
        full_name_user = updateValue.data.full_name;
        organization_short_title1 = updateValue.data.organization_short_title;

    }

    @Test
    public void step03() {

        errorListValue = BasicUser.getUserError(autId2);
        Assert.assertTrue(errorListValue.error.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void step04() {

        errorListValue2 = BasicUser.getUserErrorWithId(autId2, 1);
        Assert.assertTrue(errorListValue2.error.equals("Доступ к ресурсу запрещен"));

    }

    @Test
    public void step05() {

        createError = BasicUser.createUserError(autId2, "528f027454c8000041000001", idRoles);
        Assert.assertTrue(createError.error.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void step06() {

        updateError = BasicUser.updateUserRoleErrorNoRights(autId2, 1, 29);
        Assert.assertTrue(updateError.error.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void step07() {

        deleteError = BasicUser.deletedUserNoRights(autId2, 6);
        Assert.assertTrue(deleteError.error.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void step08() {

        updateRole = BasicRoles.updateRole(1, idRoles, "тест22", true, "index_users");
        Assert.assertEquals(idRoles, updateRole.data.id);
        Assert.assertTrue(updateRole.data.name.equals("тест22"));
        Assert.assertTrue(updateRole.data.global);
        Assert.assertTrue(updateRole.data.rights_ids[0].equals("index_users"));
    }


    @Test
    public void step09() {

        Response response = given()
                .when()
                .header("Test-Authorization", autId2)
                .params("items", "100")
                .get(API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<?> data = jsonPath.getJsonObject("data"); // Замените на актуальный путь для получения данных о пользователях
        int index = 0;
        for (int i = 0; i < data.size(); i++) {
            if (((HashMap<?, ?>) data.get(i)).get("full_name").toString().contains("Ложкова Галина Николаевна")) {
                index = i;
                break;
            }
        }

        assert ((HashMap<?, ?>) data.get(index)).get("id").equals(6);
        assert ((HashMap<?, ?>) data.get(index)).get("full_name").equals("Ложкова Галина Николаевна");
        assert ((HashMap<?, ?>) data.get(index)).get("organization_short_title").equals("ОДиР ДИТСиЗИ МВД России");
        assert ((HashMap<?, ?>) data.get(index)).get("roles").toString().contains("id=5");
        // assert ((HashMap<?, ?>) data.get(index)).get("roles").toString().contains("full_name=С‚РµСЃС‚22");
        assert ((HashMap<?, ?>) ((ArrayList<?>) ((HashMap<?, ?>) data.get(index)).get("roles")).get(0)).get("full_name").equals("тест22 (ДДО МВД России)");


        assert ((HashMap<?, ?>) ((ArrayList<?>) ((HashMap<?, ?>) data.get(index)).get("roles")).get(0)).get("id").equals(5);

//        List<String> organization_short_title = jsonPath.getList("data.organization_short_title");
//         List<Integer> full_name_roles = jsonPath.get("data.roles.full_name");
//
//        List<String> idRoles = jsonPath.get("data.roles.id");
//
//        for (int i = 0; i < idRoles.size(); i++){
//            idRoles.get(i)
//        }
//
//
//        // Проверяем код ответа
//        assertEquals(200, response.getStatusCode());
//
//        // Извлекаем данные из ответа
//        // String responseBody = response.getBody().asString();
//        int userIdToFind = 6; // id пользователя, которого нужно найти
//
//        // int idRoles=5;
//        //   boolean isIdRoles=response.jsonPath().getList("data.roles.id").contains(idRoles);
//        //   assertEquals(true,isIdRoles);
//        // Проверяем, содержит ли массив данных пользователя с указанным id
//        boolean isUserFound = response.jsonPath().getList("data.id").contains(userIdToFind);
//        assertEquals(true, isUserFound);
//
//
//        // Получаем имя пользователя с указанным id
//        String userName = response.jsonPath().getString("data.find { it.id == " + userIdToFind + " }.full_name");
//
//        System.out.println("Найден пользователь: " + userName);
//
//        Assert.assertTrue(userName.equals("Ложкова Галина Николаевна"));
//        Assert.assertTrue(id.contains(6));
//        Assert.assertTrue(organization_short_title.contains("ОДиР ДИТСиЗИ МВД России"));
//          Assert.assertTrue(idRoles.contains(5));
//           Assert.assertTrue(full_name_roles.contains("С‚РµСЃС‚22 (ДДО МВД России)"));


    }

    @Test
    public void step10() {
        actualValueUser = BasicUser.getUser(autId2, 1);
        Assert.assertEquals(1, actualValueUser.data.id);
        Assert.assertTrue(actualValueUser.data.official_id.equals("51bace4af273695ed1000128"));
        Assert.assertTrue(actualValueUser.data.provider_id.equals("525e9f767da3000002000001"));
        Assert.assertTrue(actualValueUser.data.full_name.equals("Колокольцев Владимир Александрович"));
        Assert.assertTrue(actualValueUser.data.position.equals("Министр"));
        Assert.assertTrue(actualValueUser.data.organization_short_title.equals("Центральный аппарат МВД РФ"));
        Assert.assertTrue(actualValueUser.data.provider_name.equals("Департамент делопроизводства и работы с" +
                " обращениями граждан и организаций Министерства внутренних дел Российской Федерации (ДДО МВД России)"));
        Assert.assertTrue(actualValueUser.data.roles[0].full_name.equals("Все права"));
        Assert.assertEquals(29, actualValueUser.data.roles[0].id);

    }

    @Test
    public void step11() {

        createError2 = BasicUser.createUserError(autId2, "528f027454c8000041000001", 5);
        Assert.assertTrue(createError2.error.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void step12() {

        updateError2 = BasicUser.updateUserRoleErrorNoRights(autId2, 1, 29);
        Assert.assertTrue(updateError2.error.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void step13() {

        deleteError2 = BasicUser.deletedUserNoRights(autId2, 6);
        Assert.assertTrue(deleteError2.error.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void step14() {

        updateRole2 = BasicRoles.updateRole(1, idRoles, "тест22", true, "index_users",
                "create_users");
        Assert.assertEquals(idRoles, updateRole2.data.id);
        Assert.assertTrue(updateRole2.data.name.equals("тест22"));
        Assert.assertTrue(updateRole2.data.global);
        Assert.assertTrue(updateRole2.data.rights_ids[0].equals("index_users"));
        Assert.assertTrue(updateRole2.data.rights_ids[1].equals("create_users"));
    }

    @Test
    public void step15() {
        Response response = given()
                .when()
                .header("Test-Authorization", autId2)
                .params("items", "100")
                .get(API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<?> data = jsonPath.getJsonObject("data"); // Замените на актуальный путь для получения данных о пользователях
        int index = 0;
        for (int i = 0; i < data.size(); i++) {
            if (((HashMap<?, ?>) data.get(i)).get("full_name").toString().contains("Ложкова Галина Николаевна")) {
                index = i;
                break;
            }
        }

        assert ((HashMap<?, ?>) data.get(index)).get("id").equals(6);
        assert ((HashMap<?, ?>) data.get(index)).get("full_name").equals("Ложкова Галина Николаевна");
        assert ((HashMap<?, ?>) data.get(index)).get("organization_short_title").equals("ОДиР ДИТСиЗИ МВД России");
        assert ((HashMap<?, ?>) data.get(index)).get("roles").toString().contains("id=5");
        // assert ((HashMap<?, ?>) data.get(index)).get("roles").toString().contains("full_name=С‚РµСЃС‚22");
        assert ((HashMap<?, ?>) ((ArrayList<?>) ((HashMap<?, ?>) data.get(index)).get("roles")).get(0)).get("full_name").equals("тест22 (ДДО МВД России)");


        assert ((HashMap<?, ?>) ((ArrayList<?>) ((HashMap<?, ?>) data.get(index)).get("roles")).get(0)).get("id").equals(5);

    }

    @Test
    public void step16() {
        actualValueUser2 = BasicUser.getUser(autId2, 1);
        Assert.assertEquals(1, actualValueUser2.data.id);
        Assert.assertTrue(actualValueUser2.data.official_id.equals("51bace4af273695ed1000128"));
        Assert.assertTrue(actualValueUser2.data.provider_id.equals("525e9f767da3000002000001"));
        Assert.assertTrue(actualValueUser2.data.full_name.equals("Колокольцев Владимир Александрович"));
        Assert.assertTrue(actualValueUser2.data.position.equals("Министр"));
        Assert.assertTrue(actualValueUser2.data.organization_short_title.equals("Центральный аппарат МВД РФ"));
        Assert.assertTrue(actualValueUser2.data.provider_name.equals("Департамент делопроизводства и работы с" +
                " обращениями граждан и организаций Министерства внутренних дел Российской Федерации (ДДО МВД России)"));
        Assert.assertTrue(actualValueUser2.data.roles[0].full_name.equals("Все права"));
        Assert.assertEquals(29, actualValueUser2.data.roles[0].id);

    }

    @Test
    public void step17() {

        newValueUser = BasicUser.createUser(autId2, "528f027454c8000041000001", 5);
        Assert.assertEquals(7, newValueUser.data.id);
        Assert.assertTrue(newValueUser.data.official_id.equals("528f027454c8000041000001"));
        Assert.assertTrue(newValueUser.data.provider_id.equals("525e9f767da3000002000001"));
        Assert.assertTrue(newValueUser.data.full_name.equals("Столярова Юлия Валентиновна"));
        Assert.assertTrue(newValueUser.data.position.equals("Заместитель начальника"));
        Assert.assertTrue(newValueUser.data.organization_short_title.equals("ОДиР ГУВО МВД России"));
        Assert.assertTrue(newValueUser.data.provider_name.equals
                ("Департамент делопроизводства и работы с обращениями граждан и организаций Министерства внутренних дел" +
                        " Российской Федерации (ДДО МВД России)"));
        Assert.assertTrue(newValueUser.data.roles[0].full_name.equals("тест22 (ДДО МВД России)"));
        Assert.assertEquals(5, newValueUser.data.roles[0].id);
    }

    @Test
    public void step18() {


        updateError3 = BasicUser.updateUserRoleErrorNoRights(autId2, 1, 29);
        Assert.assertTrue(updateError3.error.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void step19() {

        deleteError3 = BasicUser.deletedUserNoRights(autId2, 6);
        Assert.assertTrue(deleteError3.error.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void step20() {


        updateRole3 = BasicRoles.updateRole(1, idRoles, "тест22", true, "index_users",
                "update_users");
        Assert.assertEquals(idRoles, updateRole3.data.id);
        Assert.assertTrue(updateRole3.data.name.equals("тест22"));
        Assert.assertTrue(updateRole3.data.global);
        Assert.assertTrue(updateRole3.data.rights_ids[0].equals("index_users"));
        Assert.assertTrue(updateRole3.data.rights_ids[1].equals("update_users"));

    }

    @Test
    public void step21() {
        Response response = given()
                .when()
                .header("Test-Authorization", autId2)
                .params("items", "100")
                .get(API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<?> data = jsonPath.getJsonObject("data"); // Замените на актуальный путь для получения данных о пользователях
        int index = 0;
        for (int i = 0; i < data.size(); i++) {
            if (((HashMap<?, ?>) data.get(i)).get("full_name").toString().contains("Ложкова Галина Николаевна")) {
                index = i;
                break;
            }
        }

        assert ((HashMap<?, ?>) data.get(index)).get("id").equals(6);
        assert ((HashMap<?, ?>) data.get(index)).get("full_name").equals("Ложкова Галина Николаевна");
        assert ((HashMap<?, ?>) data.get(index)).get("organization_short_title").equals("ОДиР ДИТСиЗИ МВД России");
        assert ((HashMap<?, ?>) data.get(index)).get("roles").toString().contains("id=5");
        // assert ((HashMap<?, ?>) data.get(index)).get("roles").toString().contains("full_name=С‚РµСЃС‚22");
        assert ((HashMap<?, ?>) ((ArrayList<?>) ((HashMap<?, ?>) data.get(index)).get("roles")).get(0)).get("full_name").equals("тест22 (ДДО МВД России)");


        assert ((HashMap<?, ?>) ((ArrayList<?>) ((HashMap<?, ?>) data.get(index)).get("roles")).get(0)).get("id").equals(5);

    }

    @Test
    public void step22() {
        actualValueUser3 = BasicUser.getUser(autId2, 1);
        Assert.assertEquals(1, actualValueUser3.data.id);
        Assert.assertTrue(actualValueUser3.data.official_id.equals("51bace4af273695ed1000128"));
        Assert.assertTrue(actualValueUser3.data.provider_id.equals("525e9f767da3000002000001"));
        Assert.assertTrue(actualValueUser3.data.full_name.equals("Колокольцев Владимир Александрович"));
        Assert.assertTrue(actualValueUser3.data.position.equals("Министр"));
        Assert.assertTrue(actualValueUser3.data.organization_short_title.equals("Центральный аппарат МВД РФ"));
        Assert.assertTrue(actualValueUser3.data.provider_name.equals("Департамент делопроизводства и работы с" +
                " обращениями граждан и организаций Министерства внутренних дел Российской Федерации (ДДО МВД России)"));
        Assert.assertTrue(actualValueUser3.data.roles[0].full_name.equals("Все права"));
        Assert.assertEquals(29, actualValueUser3.data.roles[0].id);

    }

    @Test
    public void step23() {

        createError3 = BasicUser.createUserError(autId2, "528f027454c8000041000001", idRoles);
        Assert.assertTrue(createError3.error.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void step24() {
        updateValueUser = BasicUser.updateUser(autId2, 1, 29);
        Assert.assertEquals(1, updateValueUser.data.id);
        Assert.assertTrue(updateValueUser.data.official_id.equals("51bace4af273695ed1000128"));
        Assert.assertTrue(updateValueUser.data.provider_id.equals("525e9f767da3000002000001"));
        Assert.assertTrue(updateValueUser.data.full_name.equals("Колокольцев Владимир Александрович"));
        Assert.assertTrue(updateValueUser.data.position.equals("Министр"));
        Assert.assertTrue(updateValueUser.data.organization_short_title.equals("Центральный аппарат МВД РФ"));
        Assert.assertTrue(updateValueUser.data.provider_name.equals("Департамент делопроизводства и работы с" +
                " обращениями граждан и организаций Министерства внутренних дел Российской Федерации (ДДО МВД России)"));
        Assert.assertTrue(updateValueUser.data.roles[0].full_name.equals("Все права"));
        Assert.assertEquals(29, updateValueUser.data.roles[0].id);
    }

    @Test
    public void step25() {
        deleteError4 = BasicUser.deletedUserNoRights(autId2, 6);
        Assert.assertTrue(deleteError4.error.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void step26() {


        updateRole4 = BasicRoles.updateRole(1, idRoles, "тест22", true, "index_users",
                "destroy_users");
        Assert.assertEquals(idRoles, updateRole4.data.id);
        Assert.assertTrue(updateRole4.data.name.equals("тест22"));
        Assert.assertTrue(updateRole4.data.global);
        Assert.assertTrue(updateRole4.data.rights_ids[0].equals("index_users"));
        Assert.assertTrue(updateRole4.data.rights_ids[1].equals("destroy_users"));

    }

    @Test
    public void step27() {
        Response response = given()
                .when()
                .header("Test-Authorization", autId2)
                .params("items", "100")
                .get(API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<?> data = jsonPath.getJsonObject("data"); // Замените на актуальный путь для получения данных о пользователях
        int index = 0;
        for (int i = 0; i < data.size(); i++) {
            if (((HashMap<?, ?>) data.get(i)).get("full_name").toString().contains("Ложкова Галина Николаевна")) {
                index = i;
                break;
            }
        }

        assert ((HashMap<?, ?>) data.get(index)).get("id").equals(6);
        assert ((HashMap<?, ?>) data.get(index)).get("full_name").equals("Ложкова Галина Николаевна");
        assert ((HashMap<?, ?>) data.get(index)).get("organization_short_title").equals("ОДиР ДИТСиЗИ МВД России");
        assert ((HashMap<?, ?>) data.get(index)).get("roles").toString().contains("id=5");
        // assert ((HashMap<?, ?>) data.get(index)).get("roles").toString().contains("full_name=С‚РµСЃС‚22");
        assert ((HashMap<?, ?>) ((ArrayList<?>) ((HashMap<?, ?>) data.get(index)).get("roles")).get(0)).get("full_name").equals("тест22 (ДДО МВД России)");


        assert ((HashMap<?, ?>) ((ArrayList<?>) ((HashMap<?, ?>) data.get(index)).get("roles")).get(0)).get("id").equals(5);

    }

    @Test
    public void step28() {

        actualValueUser4 = BasicUser.getUser(autId2, 1);
        Assert.assertEquals(1, actualValueUser4.data.id);
        Assert.assertTrue(actualValueUser4.data.official_id.equals("51bace4af273695ed1000128"));
        Assert.assertTrue(actualValueUser4.data.provider_id.equals("525e9f767da3000002000001"));
        Assert.assertTrue(actualValueUser4.data.full_name.equals("Колокольцев Владимир Александрович"));
        Assert.assertTrue(actualValueUser4.data.position.equals("Министр"));
        Assert.assertTrue(actualValueUser4.data.organization_short_title.equals("Центральный аппарат МВД РФ"));
        Assert.assertTrue(actualValueUser4.data.provider_name.equals("Департамент делопроизводства и работы с" +
                " обращениями граждан и организаций Министерства внутренних дел Российской Федерации (ДДО МВД России)"));
        Assert.assertTrue(actualValueUser4.data.roles[0].full_name.equals("Все права"));
        Assert.assertEquals(29, actualValueUser4.data.roles[0].id);

    }

    @Test
    public void step29() {

        createError4 = BasicUser.createUserError(autId2, "528f027454c8000041000001", idRoles);
        Assert.assertTrue(createError4.error.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void step30() {

        updateError4 = BasicUser.updateUserRoleErrorNoRights(autId2, 1, 29);
        Assert.assertTrue(updateError4.error.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void step31() {
        BasicUser.deleteUser(autId2, 7);
    }

    @Test
    public void step32() {
        deleteErrorUser = BasicUser.deletedUserCheck(autId2, 7);
    }

    @Test
    public void step33() {
        updateRole5 = BasicRoles.updateRole(1, idRoles, "тест22", true, "index_users");
        Assert.assertEquals(idRoles, updateRole5.data.id);
        Assert.assertTrue(updateRole5.data.name.equals("тест22"));
        Assert.assertTrue(updateRole5.data.global);
        Assert.assertTrue(updateRole5.data.rights_ids[0].equals("index_users"));

    }
}











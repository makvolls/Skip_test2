package SKIP_API;

import API.BasicEvents;
import API.BasicRoles;
import API.BasicUser;
import API.DTO.ErrorsDTO.RootError;
import API.DTO.EventsDto.RootEventsDto;
import API.DTO.RolesDto.RootResponseRolesDto;
import API.DTO.UserDto.RootResponseUserDto;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SKIP_62 {

    int arraySize;
    int arraySize2;
    int arraySize3;
    int arraySize4;
    int firstValue;
    int firstValue2;
    int idRoles;

    @AfterClass
    public void after() {

        BasicEvents.deleteEvents(1, newValue.data.id);
    }

    RootEventsDto newValue;
    RootResponseUserDto newValueUser;
    RootError errorValue;
    RootResponseRolesDto newValueRoles;
    RootResponseRolesDto actualValue;

    @Test
    public void step01() {

        newValue = BasicEvents.createEvents(1, "dsfer", "dfser", true, 3, 3);
        arraySize = newValue.data.event_states.length;
        Assert.assertEquals(1, arraySize);
        Assert.assertTrue(newValue.data.name.equals("dsfer"));
        Assert.assertTrue(newValue.data.short_name.equals("dfser"));
        Assert.assertTrue(newValue.data.excluded);
        Assert.assertEquals(3, newValue.data.event_states[0].id);
        Assert.assertTrue(newValue.data.event_states[0].name.equals("Тест"));
        // Assert.assertTrue(arraySize,1);
        firstValue = newValue.data.id;


    }

    @Test
    public void step02() {
        Response response = given()
                .when()
                .header("Test-Authorization", 1)
                .get(BasicEvents.API_EVENT)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<?> data = jsonPath.getJsonObject("data"); // Замените на актуальный путь для получения данных о пользователях
        int index = 0;
        for (int i = 0; i < data.size(); i++) {
            if (((HashMap<?, ?>) data.get(i)).get("id").toString().contains("firstValue")) {
                index = i;
                break;
            }
        }

        assert ((HashMap<?, ?>) data.get(index)).get("id").equals(firstValue);
        assert ((HashMap<?, ?>) data.get(index)).get("name").equals("dsfer");
        assert ((HashMap<?, ?>) data.get(index)).get("short_name").equals("dfser");
        assert ((HashMap<?, ?>) data.get(index)).get("excluded").toString().contains("true");
        assert ((HashMap<?, ?>) ((ArrayList<?>) ((HashMap<?, ?>) data.get(index)).get("event_states")).get(0)).get("name").equals("Тест");

        assert ((HashMap<?, ?>) ((ArrayList<?>) ((HashMap<?, ?>) data.get(index)).get("event_states")).get(0)).get("id").equals(3);

        Assert.assertEquals(2, ((HashMap<?, ?>) ((ArrayList<?>) ((HashMap<?, ?>) data.get(index)).get("event_states")).get((0))).size());


    }

    @Test
    public void step03() {
        newValueUser = BasicUser.createUser(1, "528f027454c8000041000001", 3, 3);
        arraySize2 = newValueUser.data.roles.length;
        Assert.assertEquals(1, arraySize2);
        Assert.assertEquals(7, newValueUser.data.id);
        Assert.assertTrue(newValueUser.data.full_name.equals("Столярова Юлия Валентиновна"));
        Assert.assertTrue(newValueUser.data.position.equals("Заместитель начальника"));
        Assert.assertTrue(newValueUser.data.organization_short_title.equals("ОДиР ГУВО МВД России"));
        Assert.assertEquals(3, newValueUser.data.roles[0].id);
        Assert.assertTrue(newValueUser.data.provider_name.equals("Департамент делопроизводства " +
                "и работы с обращениями граждан и организаций Министерства внутренних дел Российской Федерации (ДДО МВД России)"));

        Assert.assertTrue(newValueUser.data.roles[0].full_name.equals("тест11 (ОДиР ГУВО МВД России)"));
        Assert.assertTrue(newValueUser.data.official_id.equals("528f027454c8000041000001"));
        Assert.assertTrue(newValueUser.data.provider_id.equals("525e9f767da3000002000001"));
        firstValue2 = newValueUser.data.id;
    }

    @Test
    public void step04() {
        Response response = given()
                .when()
                .header("Test-Authorization", 1)
                .params("items", 1000)
                .get(BasicUser.API_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<?> data = jsonPath.getJsonObject("data"); // Замените на актуальный путь для получения данных о пользователях
        int index = 0;
        for (int i = 0; i < data.size(); i++) {
            if (((HashMap<?, ?>) data.get(i)).get("full_name").toString().contains("Столярова Юлия Валентиновна")) {
                index = i;
                break;
            }
        }

        assert ((HashMap<?, ?>) data.get(index)).get("id").equals(firstValue2);
        assert ((HashMap<?, ?>) ((ArrayList<?>) ((HashMap<?, ?>) data.get(index)).get("roles")).get(0)).get("full_name")
                .equals("тест11 (ОДиР ГУВО МВД России)");

        assert ((HashMap<?, ?>) ((ArrayList<?>) ((HashMap<?, ?>) data.get(index)).get("roles")).get(0)).get("id").equals(3);

        Assert.assertEquals(2, ((HashMap<?, ?>) ((ArrayList<?>) ((HashMap<?, ?>) data.get(index)).get("roles")).get((0))).size());


    }

    @Test
    public void step05() {
        BasicUser.deleteUser(1, newValueUser.data.id);
    }

    @Test
    public void step06() {
        errorValue = BasicUser.deletedUserCheck(1, 7);
        Assert.assertTrue(errorValue.error.equals("Запись для Permissions::User с id = 7 не найдена"));

    }

    @Test
    public void step07() {

        newValueRoles = BasicRoles.createRoles(1, "fdsg", true, "index_users", "index_users");
        Assert.assertTrue(newValueRoles.data.global);
        Assert.assertTrue(newValueRoles.data.name.equals("fdsg"));
        Assert.assertTrue(newValueRoles.data.rights_ids[0].equals("index_users"));
        arraySize3 = newValueRoles.data.rights_ids.length;
        Assert.assertEquals(1, arraySize3);
        Assert.assertTrue(newValueRoles.data.provider_id.equals("525e9f767da3000002000001"));
        idRoles = newValueRoles.data.id;
    }

    @Test
    public void step08() {

        actualValue = BasicRoles.getRole(1, idRoles);

        Assert.assertTrue(actualValue.data.global);
        Assert.assertTrue(actualValue.data.name.equals("fdsg"));
        Assert.assertTrue(actualValue.data.rights_ids[0].equals("index_users"));
        arraySize4 = actualValue.data.rights_ids.length;
        Assert.assertEquals(1, arraySize4);
        Assert.assertTrue(actualValue.data.provider_id.equals("525e9f767da3000002000001"));

    }

}

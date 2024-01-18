package SKIP_API;

import API.BasicEventStates;
import API.BasicEvents;
import API.DTO.ErrorsDTO.EventsErrorsDto.RootErrorESI;
import API.DTO.ErrorsDTO.RootError;
import API.DTO.EventStatesDto.RootElementEventStates;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SKIP_64 {
    int idES;
    String idES2;

    RootElementEventStates newValue;
    RootErrorESI newValueError;
    RootError errorDelete;

    @Test
    public void step01() {
        newValue = BasicEventStates.createEventStates(1, "mvolobuevs", true);
        Assert.assertTrue(newValue.data.name.equals("mvolobuevs"));
        Assert.assertTrue(newValue.data.excluded);
        idES = newValue.data.id;
        idES2 = Integer.toString(idES);
    }

    @Test
    public void step02() {
        BasicEventStates.deleteEventStateValue(1, idES);


    }

    @Test
    public void step03() {
        errorDelete = BasicEventStates.deletedESCheck(1, idES);
        Assert.assertTrue(errorDelete.error.equals("Запись для Classifiers::EventState с id = " + idES2 + " не найдена"));
    }

    @Test
    public void step04() {
        newValueError = BasicEvents.createErrESI(1, "dsgbdfss", "", false, idES);
        Assert.assertTrue(newValueError.errors.event_state_ids[0].equals("имеет непредусмотренное значение: " + idES2));

    }

    @Test
    public void step05() {
        Response response = given()
                .when()
                .header("Test-Authorization", 1)
                .get(BasicEvents.API_EVENT)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<String> name = jsonPath.get("data.name");

        List<Integer> n = new ArrayList<>();

        for (int i = 0; i < name.size(); i++) {
            if (!name.get(i).contains("dsgbdfss") && !name.get(i).contains("Dsgbdfss"))
                n.add(i);
        }

        for (int i = 0; i < n.size(); i++) {
            Assert.assertFalse(name.get(n.get(i)).contains("dsgbdfss") || name.get(n.get(i)).contains("Dsgbdfss"));
        }
    }

}
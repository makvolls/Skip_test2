package SKIP_API;

import API.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SKIP_42 {
    int id1;
    int id2;
    int id3;
    int id4;
    int id5;
    int id6;
    int id7;
    int idAut1 = 1;
    int idAut2 = 8;
    int id_classifiers, id_classifiers2, id_classifiers3,id_classifiers4,id_classifiers5,id_classifiers6,id_classifiers7;

    public static String API_DOC_TYPE = "http://api.skip.rtech.ru/v1/classifiers/document_types";
    public static String API_EVENT_STATES = "http://api.skip.rtech.ru/v1/classifiers/event_states";
    public static String API_EVENT = "http://api.skip.rtech.ru/v1/classifiers/events";
    public static String API_CONTROL_PERIOD = "http://api.skip.rtech.ru/v1/classifiers/control_period_states";
    public static String API_DEADLINE_BASES = "http://api.skip.rtech.ru/v1/classifiers/deadline_bases";
    public static String API_THEMES = "http://api.skip.rtech.ru/v1/classifiers/themes";
    public static String API_SECURITY_CLASSIFICATIONS = "http://api.skip.rtech.ru/v1/classifiers/security_classifications";

    @AfterClass
    public void delete() {
        BasicDocType.deleteDocType(idAut1, id1);
        BasicEventStates.deleteEventStateValue(idAut1, id2);
        BasicEvents.deleteEvents(idAut1,id3);
        BasicControlPeriod.deleteControlPeriod(idAut1,id4);
        BasicDeadlineBases.deleteDeadlineBasesValue(idAut1,id5);
        BasicThemes.deleteThemesValue(idAut1,id6);
        BasicSecurity.deleteSecurity(idAut1,id7);

    }


    @Test
    public void Step01() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .get(API_DOC_TYPE)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<String> data = jsonPath.get("data");
        int id = jsonPath.get("data[0].id");
        Assert.assertNotNull(data);
        id_classifiers = id;
        System.out.println(id_classifiers);
    }

    @Test
    public void Step02() {
        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", "bdfbfdfvrefrrrrsssss12");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(classifiers)
                .log().all()
                .post(API_DOC_TYPE)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.get("data.id");
        String name = jsonPath.get("data.name");
        String short_name = jsonPath.get("data.short_name");
        Boolean internal = jsonPath.get("data.internal");
        String genitive_name = jsonPath.get("data.genitive_name");
        int priority = jsonPath.get("data.priority");
        Boolean deleted = jsonPath.get("data.deleted");
        String provider_id = jsonPath.get("data.provider_id");
        Assert.assertNotNull(id);
        Assert.assertTrue(name.equals("bdfbfdfvrefrrrrsssss12"));
        Assert.assertTrue(short_name.equals(""));
        Assert.assertTrue(!internal);
        Assert.assertTrue(genitive_name.equals(""));
        Assert.assertTrue(priority == 1);
        Assert.assertTrue(!deleted);
        Assert.assertTrue(provider_id.equals("525e9f767da3000002000001"));
        id1 = id;


    }

    @Test
    public void Step03() {
        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", "bdfbfdfvrefrrrrssa");
        classifiers.put("excluded", "false");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut2)
                .body(classifiers)
                .log().all()
                .post(API_DOC_TYPE)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.get("error");
        Assert.assertTrue(error.contains("Доступ к ресурсу запрещен"));
    }

    @Test
    public void Step04() {
        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", "bdfbfdfvrefrrrrssa");
        classifiers.put("excluded", "false");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut2)
                .body(classifiers)
                .log().all()
                .put(API_DOC_TYPE + "/" + id_classifiers)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.get("error");
        Assert.assertTrue(error.contains("Доступ к ресурсу запрещен"));
    }


    @Test
    public void Step05() {
        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", "bdfbfdfvrefrrrrssaa");
        classifiers.put("excluded", "false");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(classifiers)
                .log().all()
                .put(API_DOC_TYPE + "/" + id_classifiers)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String name = jsonPath.get("data.name");
        Boolean excluded = jsonPath.get("data.excluded");
        Assert.assertTrue(name.equals("bdfbfdfvrefrrrrssaa"));
        Assert.assertTrue(!excluded);


    }

    @Test
    public void Step06() {
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .log().all()
                .put(API_DOC_TYPE + "/" + id_classifiers)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String name = jsonPath.get("data.name");
        Assert.assertTrue(name.equals("bdfbfdfvrefrrrrssaa"));
        Boolean excluded = jsonPath.get("data.excluded");
        Assert.assertTrue(!excluded);

    }

    @Test
    public void Step07() {

        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", "vdsfewrsa");
        classifiers.put("short_name", "fsevewsa");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(classifiers)
                .log().all()
                .put(API_DOC_TYPE + "/" + id_classifiers)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String name = jsonPath.get("data.name");
        String short_name = jsonPath.get("data.short_name");
        Assert.assertTrue(name.equals("vdsfewrsa"));
        Assert.assertTrue(short_name.equals("fsevewsa"));
    }

    @Test
    public void Step08() {

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .log().all()
                .put(API_DOC_TYPE + "/" + id_classifiers)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String name = jsonPath.get("data.name");
        String short_name = jsonPath.get("data.short_name");
        Assert.assertTrue(name.equals("vdsfewrsa"));
        Assert.assertTrue(short_name.equals("fsevewsa"));
    }

    @Test
    public void Step09() {

        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .get(API_EVENT_STATES)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<String> data = jsonPath.get("data");
        int id = jsonPath.get("data[0].id");
        Assert.assertNotNull(data);
        id_classifiers2 = id;
        System.out.println(id_classifiers2);

    }

    @Test
    public void Step10() {
        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", "bdfbfdfvrefrrrrsssss123");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(classifiers)
                .log().all()
                .post(API_EVENT_STATES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.get("data.id");
        String name = jsonPath.get("data.name");
        int priority = jsonPath.get("data.priority");
        Boolean deleted = jsonPath.get("data.deleted");
        Assert.assertNotNull(id);
        Assert.assertTrue(name.equals("bdfbfdfvrefrrrrsssss123"));
        Assert.assertTrue(priority == 1);
        Assert.assertTrue(!deleted);
        id2 = id;

    }

    @Test
    public void Step11() {
        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", "bdfbfdfvrefrrrrssa");
        classifiers.put("excluded", "false");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut2)
                .body(classifiers)
                .log().all()
                .post(API_EVENT_STATES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.get("error");
        Assert.assertTrue(error.contains("Доступ к ресурсу запрещен"));
    }

    @Test
    public void Step12() {
        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", "bdfbfdfvrefrrrrssa");
        classifiers.put("excluded", "false");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut2)
                .body(classifiers)
                .log().all()
                .put(API_EVENT_STATES + "/" + id_classifiers2)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.get("error");
        Assert.assertTrue(error.contains("Доступ к ресурсу запрещен"));
    }

    @Test
    public void Step13() {
        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", "bdfbfdfvrefrrrrssaa");
        classifiers.put("excluded", "false");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(classifiers)
                .log().all()
                .put(API_EVENT_STATES + "/" + id_classifiers2)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String name = jsonPath.get("data.name");
        Boolean excluded = jsonPath.get("data.excluded");
        Assert.assertTrue(name.equals("bdfbfdfvrefrrrrssaa"));
        Assert.assertTrue(!excluded);


    }

    @Test
    public void Step14() {
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .log().all()
                .put(API_EVENT_STATES + "/" + id_classifiers2)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String name = jsonPath.get("data.name");
        Assert.assertTrue(name.equals("bdfbfdfvrefrrrrssaa"));
        Boolean excluded = jsonPath.get("data.excluded");
        Assert.assertTrue(!excluded);

    }


    @Test
    public void Step15() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .get(API_EVENT)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<String> data = jsonPath.get("data");
        int id = jsonPath.get("data[0].id");
        Assert.assertNotNull(data);
        id_classifiers3 = id;
        System.out.println(id_classifiers3);
    }

    @Test
    public void Step16() {
        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", "bdfbfdfvrefrrrrsssss12345");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(classifiers)
                .log().all()
                .post(API_EVENT)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.get("data.id");
        String name = jsonPath.get("data.name");
        String short_name = jsonPath.get("data.short_name");
        int priority = jsonPath.get("data.priority");
        Boolean deleted = jsonPath.get("data.deleted");
        List<String> event_states=jsonPath.get("data.event_states");
        Assert.assertNotNull(id);
        Assert.assertTrue(name.equals("bdfbfdfvrefrrrrsssss12345"));
        Assert.assertTrue(short_name.equals(""));
        Assert.assertTrue(priority == 1);
        Assert.assertTrue(!deleted);
        Assert.assertTrue(event_states.isEmpty());

        id3 = id;


    }

    @Test
    public void Step17() {
        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", "bdfbfdfvrefrrrrssa");
        classifiers.put("excluded", "false");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut2)
                .body(classifiers)
                .log().all()
                .post(API_EVENT)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.get("error");
        Assert.assertTrue(error.contains("Доступ к ресурсу запрещен"));
    }

    @Test
    public void Step18() {
        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", "bdfbfdfvrefrrrrssa");
        classifiers.put("excluded", "false");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut2)
                .body(classifiers)
                .log().all()
                .put(API_EVENT + "/" + id_classifiers3)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.get("error");
        Assert.assertTrue(error.contains("Доступ к ресурсу запрещен"));
    }

    @Test
    public void Step19() {
        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", "bdfbfdfvrefrrrrssaa");
        classifiers.put("excluded", "false");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(classifiers)
                .log().all()
                .put(API_EVENT + "/" + id_classifiers3)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String name = jsonPath.get("data.name");
        Boolean excluded = jsonPath.get("data.excluded");
        Assert.assertTrue(name.equals("bdfbfdfvrefrrrrssaa"));
        Assert.assertTrue(!excluded);


    }

    @Test
    public void Step20() {
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .log().all()
                .put(API_EVENT + "/" + id_classifiers3)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String name = jsonPath.get("data.name");
        Assert.assertTrue(name.equals("bdfbfdfvrefrrrrssaa"));
        Boolean excluded = jsonPath.get("data.excluded");
        Assert.assertTrue(!excluded);

    }

    @Test
    public void Step21() {

        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", "vdsfewrsa");
        classifiers.put("short_name", "fsevewsa");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(classifiers)
                .log().all()
                .put(API_EVENT + "/" + id_classifiers3)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String name = jsonPath.get("data.name");
        String short_name = jsonPath.get("data.short_name");
        Assert.assertTrue(name.equals("vdsfewrsa"));
        Assert.assertTrue(short_name.equals("fsevewsa"));
    }

    @Test
    public void Step22() {

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .log().all()
                .put(API_EVENT + "/" + id_classifiers3)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String name = jsonPath.get("data.name");
        String short_name = jsonPath.get("data.short_name");
        Assert.assertTrue(name.equals("vdsfewrsa"));
        Assert.assertTrue(short_name.equals("fsevewsa"));
    }

    @Test
    public void Step23() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .get(API_CONTROL_PERIOD)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<String> data = jsonPath.get("data");
        int id = jsonPath.get("data[0].id");
        Assert.assertNotNull(data);
        id_classifiers4 = id;
        System.out.println(id_classifiers4);
    }

    @Test
    public void Step24() {
        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", "bdfbfdfvrefrrrrsssss123456");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(classifiers)
                .log().all()
                .post(API_CONTROL_PERIOD)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.get("data.id");
        String name = jsonPath.get("data.name");
        String short_name = jsonPath.get("data.short_name");
        int priority = jsonPath.get("data.priority");
        Boolean deleted = jsonPath.get("data.deleted");
        Assert.assertNotNull(id);
        Assert.assertTrue(name.equals("bdfbfdfvrefrrrrsssss123456"));
        Assert.assertTrue(short_name.equals(""));
        Assert.assertTrue(priority == 1);
        Assert.assertTrue(!deleted);

        id4 = id;


    }

    @Test
    public void Step25() {
        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", "bdfbfdfvrefrrrrssa");
        classifiers.put("excluded", "false");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut2)
                .body(classifiers)
                .log().all()
                .post(API_CONTROL_PERIOD)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.get("error");
        Assert.assertTrue(error.contains("Доступ к ресурсу запрещен"));
    }

    @Test
    public void Step26() {
        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", "bdfbfdfvrefrrrrssa");
        classifiers.put("excluded", "false");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut2)
                .body(classifiers)
                .log().all()
                .put(API_CONTROL_PERIOD + "/" + id_classifiers4)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.get("error");
        Assert.assertTrue(error.contains("Доступ к ресурсу запрещен"));
    }

    @Test
    public void Step27() {
        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", "bdfbfdfvrefrrrrssaa");
        classifiers.put("excluded", "false");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(classifiers)
                .log().all()
                .put(API_CONTROL_PERIOD + "/" + id_classifiers4)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String name = jsonPath.get("data.name");
        Boolean excluded = jsonPath.get("data.excluded");
        Assert.assertTrue(name.equals("bdfbfdfvrefrrrrssaa"));
        Assert.assertTrue(!excluded);


    }

    @Test
    public void Step28() {
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .log().all()
                .put(API_CONTROL_PERIOD + "/" + id_classifiers4)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String name = jsonPath.get("data.name");
        Assert.assertTrue(name.equals("bdfbfdfvrefrrrrssaa"));
        Boolean excluded = jsonPath.get("data.excluded");
        Assert.assertTrue(!excluded);

    }

    @Test
    public void Step29() {

        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", "vdsfewrsaa");
        classifiers.put("short_name", "fsevewsaa");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(classifiers)
                .log().all()
                .put(API_CONTROL_PERIOD + "/" + id_classifiers4)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String name = jsonPath.get("data.name");
        String short_name = jsonPath.get("data.short_name");
        Assert.assertTrue(name.equals("vdsfewrsaa"));
        Assert.assertTrue(short_name.equals("fsevewsaa"));
    }

    @Test
    public void Step30() {

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .log().all()
                .put(API_CONTROL_PERIOD + "/" + id_classifiers4)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String name = jsonPath.get("data.name");
        String short_name = jsonPath.get("data.short_name");
        Assert.assertTrue(name.equals("vdsfewrsaa"));
        Assert.assertTrue(short_name.equals("fsevewsaa"));
    }

    @Test
    public void Step31() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .get(API_DEADLINE_BASES)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<String> data = jsonPath.get("data");
        int id = jsonPath.get("data[0].id");
        Assert.assertNotNull(data);
        id_classifiers5 = id;
        System.out.println(id_classifiers5);
    }

    @Test
    public void Step32() {
        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", "bdfbfdfvrefrrrrsssss1234567");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(classifiers)
                .log().all()
                .post(API_DEADLINE_BASES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.get("data.id");
        String name = jsonPath.get("data.name");
        String short_name = jsonPath.get("data.short_name");
        int priority = jsonPath.get("data.priority");
        Boolean deleted = jsonPath.get("data.deleted");
        Assert.assertNotNull(id);
        Assert.assertTrue(name.equals("bdfbfdfvrefrrrrsssss1234567"));
        Assert.assertTrue(short_name.equals(""));
        Assert.assertTrue(priority == 1);
        Assert.assertTrue(!deleted);

        id5 = id;


    }

    @Test
    public void Step33() {
        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", "bdfbfdfvrefrrrrssa");
        classifiers.put("excluded", "false");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut2)
                .body(classifiers)
                .log().all()
                .post(API_DEADLINE_BASES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.get("error");
        Assert.assertTrue(error.contains("Доступ к ресурсу запрещен"));
    }

    @Test
    public void Step34() {
        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", "bdfbfdfvrefrrrrssa");
        classifiers.put("excluded", "false");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut2)
                .body(classifiers)
                .log().all()
                .put(API_DEADLINE_BASES + "/" + id_classifiers5)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.get("error");
        Assert.assertTrue(error.contains("Доступ к ресурсу запрещен"));
    }

    @Test
    public void Step35() {
        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", "bdfbfdfvrefrrrrssaa");
        classifiers.put("excluded", "false");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(classifiers)
                .log().all()
                .put(API_DEADLINE_BASES + "/" + id_classifiers5)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String name = jsonPath.get("data.name");
        Boolean excluded = jsonPath.get("data.excluded");
        Assert.assertTrue(name.equals("bdfbfdfvrefrrrrssaa"));
        Assert.assertTrue(!excluded);


    }

    @Test
    public void Step36() {
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .log().all()
                .put(API_DEADLINE_BASES + "/" + id_classifiers5)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String name = jsonPath.get("data.name");
        Assert.assertTrue(name.equals("bdfbfdfvrefrrrrssaa"));
        Boolean excluded = jsonPath.get("data.excluded");
        Assert.assertTrue(!excluded);

    }

    @Test
    public void Step37() {

        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", "vdsfewrsaas");
        classifiers.put("short_name", "fsevewsaas");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(classifiers)
                .log().all()
                .put(API_DEADLINE_BASES + "/" + id_classifiers5)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String name = jsonPath.get("data.name");
        String short_name = jsonPath.get("data.short_name");
        Assert.assertTrue(name.equals("vdsfewrsaas"));
        Assert.assertTrue(short_name.equals("fsevewsaas"));
    }

    @Test
    public void Step38() {

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .log().all()
                .put(API_DEADLINE_BASES + "/" + id_classifiers5)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String name = jsonPath.get("data.name");
        String short_name = jsonPath.get("data.short_name");
        Assert.assertTrue(name.equals("vdsfewrsaas"));
        Assert.assertTrue(short_name.equals("fsevewsaas"));
    }

    @Test
    public void Step39() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .get(API_THEMES)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<String> data = jsonPath.get("data");
        int id = jsonPath.get("data[0].id");
        Assert.assertNotNull(data);
        id_classifiers6 = id;
        System.out.println(id_classifiers6);
    }
    @Test
    public void Step40() {
        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", "bdfbfdfvrefrrrrsssss1234567");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(classifiers)
                .log().all()
                .post(API_THEMES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.get("data.id");
        String name = jsonPath.get("data.name");
        int priority = jsonPath.get("data.priority");
        Boolean deleted = jsonPath.get("data.deleted");
        Assert.assertNotNull(id);
        Assert.assertTrue(name.equals("bdfbfdfvrefrrrrsssss1234567"));
        Assert.assertTrue(priority == 1);
        Assert.assertTrue(!deleted);

        id6 = id;


    }

    @Test
    public void Step41() {
        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", "bdfbfdfvrefrrrrssaa");
        classifiers.put("excluded", "false");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(classifiers)
                .log().all()
                .put(API_THEMES + "/" + id_classifiers6)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String name = jsonPath.get("data.name");
        Boolean excluded = jsonPath.get("data.excluded");
        Assert.assertTrue(name.equals("bdfbfdfvrefrrrrssaa"));
        Assert.assertTrue(!excluded);


    }

    @Test
    public void Step42() {
        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", "bdfbfdfvrefrrrrssa");
        classifiers.put("excluded", "false");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut2)
                .body(classifiers)
                .log().all()
                .put(API_THEMES + "/" + id_classifiers6)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.get("error");
        Assert.assertTrue(error.contains("Доступ к ресурсу запрещен"));
    }

    @Test
    public void Step43() {
        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", "bdfbfdfvrefrrrrssaa");
        classifiers.put("excluded", "false");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(classifiers)
                .log().all()
                .put(API_THEMES + "/" + id_classifiers6)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String name = jsonPath.get("data.name");
        Boolean excluded = jsonPath.get("data.excluded");
        Assert.assertTrue(name.equals("bdfbfdfvrefrrrrssaa"));
        Assert.assertTrue(!excluded);


    }
    @Test
    public void Step44() {
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .log().all()
                .put(API_THEMES + "/" + id_classifiers6)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String name = jsonPath.get("data.name");
        Assert.assertTrue(name.equals("bdfbfdfvrefrrrrssaa"));
        Boolean excluded = jsonPath.get("data.excluded");
        Assert.assertTrue(!excluded);

    }

    @Test
    public void Step45() {
        Response response = given()
                .when()
                .header("Test-Authorization", idAut1)
                .get(API_SECURITY_CLASSIFICATIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<String> data = jsonPath.get("data");
        int id = jsonPath.get("data[0].id");
        Assert.assertNotNull(data);
        id_classifiers7 = id;
        System.out.println(id_classifiers7);
    }

    @Test
    public void Step46() {
        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", "bdfbfdfvrefrrrrsssss123");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(classifiers)
                .log().all()
                .post(API_SECURITY_CLASSIFICATIONS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.get("data.id");
        String name = jsonPath.get("data.name");
        String short_name = jsonPath.get("data.short_name");
        int priority = jsonPath.get("data.priority");
        Boolean deleted = jsonPath.get("data.deleted");
        Assert.assertNotNull(id);
        Assert.assertTrue(name.equals("bdfbfdfvrefrrrrsssss123"));
        Assert.assertTrue(short_name.equals(""));
        Assert.assertTrue(priority == 1);
        Assert.assertTrue(!deleted);
        id7 = id;

    }

    @Test
    public void Step47() {
        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", "bdfbfdfvrefrrrrssa");
        classifiers.put("excluded", "false");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut2)
                .body(classifiers)
                .log().all()
                .post(API_SECURITY_CLASSIFICATIONS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.get("error");
        Assert.assertTrue(error.contains("Доступ к ресурсу запрещен"));
    }

    @Test
    public void Step48() {
        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", "bdfbfdfvrefrrrrssa");
        classifiers.put("excluded", "false");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut2)
                .body(classifiers)
                .log().all()
                .put(API_SECURITY_CLASSIFICATIONS + "/" + id_classifiers7)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.get("error");
        Assert.assertTrue(error.contains("Доступ к ресурсу запрещен"));
    }


    @Test
    public void Step49() {
        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", "bdfbfdfvrefrrrrssaa");
        classifiers.put("excluded", "false");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(classifiers)
                .log().all()
                .put(API_SECURITY_CLASSIFICATIONS + "/" + id_classifiers7)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String name = jsonPath.get("data.name");
        Boolean excluded = jsonPath.get("data.excluded");
        Assert.assertTrue(name.equals("bdfbfdfvrefrrrrssaa"));
        Assert.assertTrue(!excluded);


    }

    @Test
    public void Step50() {
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .log().all()
                .put(API_SECURITY_CLASSIFICATIONS + "/" + id_classifiers7)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String name = jsonPath.get("data.name");
        Assert.assertTrue(name.equals("bdfbfdfvrefrrrrssaa"));
        Boolean excluded = jsonPath.get("data.excluded");
        Assert.assertTrue(!excluded);

    }

    @Test
    public void Step51() {

        Map<String, String> classifiers = new HashMap<>();
        classifiers.put("name", "vdsfewrsaaa");
        classifiers.put("short_name", "fsevewsaaa");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .body(classifiers)
                .log().all()
                .put(API_SECURITY_CLASSIFICATIONS + "/" + id_classifiers7)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String name = jsonPath.get("data.name");
        String short_name = jsonPath.get("data.short_name");
        Assert.assertTrue(name.equals("vdsfewrsaaa"));
        Assert.assertTrue(short_name.equals("fsevewsaaa"));
    }

    @Test
    public void Step52() {

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", idAut1)
                .log().all()
                .put(API_SECURITY_CLASSIFICATIONS + "/" + id_classifiers7)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String name = jsonPath.get("data.name");
        String short_name = jsonPath.get("data.short_name");
        Assert.assertTrue(name.equals("vdsfewrsaaa"));
        Assert.assertTrue(short_name.equals("fsevewsaaa"));
    }

}
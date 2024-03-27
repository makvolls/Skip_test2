package SKIP_API;

import Utils.RandomGenerateText;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SKIP_229 {

    // Endpoints:
    private static String API_CONSTRUCTOR_TABLES = "http://api.skip.rtech.ru/v1/constructor/tables";

    // Authorization parameters:
    int userIdOne = 1; // User that have all rights, role id = 29
    int userIdSix = 6; // User that have right - "show_roles"

    // Variables
    int idCreatedTable;
    int idCreatedTableRoles;
    int idFirstColumn;
    int idFirstField;
    int idSecondField;

    // Logger
    private static final Logger logger = Logger.getLogger(SKIP_229.class);

    // Random string generator
    String randomString = RandomGenerateText.generateUniqeTextRussianString(25);

    @BeforeTest(enabled = false)
    public void setup(){
        logger.info("Before test method is running.");
    }

    @AfterTest(enabled = false)
    public void tearDown(){
        logger.info("After test method is running.");
    }

    @Test
    public void step01(){
        try {
            JsonObject requestTables = new JsonObject();
            requestTables.addProperty("name", "Тест таблица " + randomString);
            requestTables.addProperty("row_type_id", "documents");
            requestTables.addProperty("only_officials", true);
            JsonArray official_ids = new JsonArray();
            official_ids.add("6572d99f14b4000001000001");
            official_ids.add("60b580365f25000008000001");
            official_ids.add("5b60ec5c6a38000008000001");
            requestTables.add("official_ids",official_ids);
            requestTables.addProperty("only_roles", false);
            requestTables.addProperty("only_provider", false);
            requestTables.addProperty("display_on_mainwindow", true);
            JsonArray columns = new JsonArray();
            JsonObject firstColumn = new JsonObject();
            firstColumn.addProperty("column_name","Наименование события");
            firstColumn.addProperty("separator_id", ",");
            JsonArray fieldsFirstColumn = new JsonArray();
            JsonObject fieldFirst = new JsonObject();
            fieldFirst.addProperty("add_name", "Имя");
            fieldFirst.addProperty("field_id", "document.skip_reg_number");
            fieldFirst.addProperty("bold", true);
            fieldFirst.addProperty("italics", false);
            fieldFirst.addProperty("underline", true);
            JsonObject fieldSecond = new JsonObject();
            fieldSecond.addProperty("add_name", "Id");
            fieldSecond.addProperty("field_id", "document.skip_reg_number");
            fieldSecond.addProperty("bold", false);
            fieldSecond.addProperty("italics", true);
            fieldSecond.addProperty("underline", true);
            fieldsFirstColumn.add(fieldFirst);
            fieldsFirstColumn.add(fieldSecond);
            firstColumn.add("fields", fieldsFirstColumn);
            JsonObject secondColumn = new JsonObject();
            secondColumn.addProperty("column_name","Описание события");
            secondColumn.addProperty("separator_id", ",");
            JsonArray fieldsSecondColumn = new JsonArray();
            JsonObject fieldThird = new JsonObject();
            fieldThird.addProperty("add_name", "Полное");
            fieldThird.addProperty("field_id", "document.skip_reg_number");
            fieldThird.addProperty("bold", false);
            fieldThird.addProperty("italics", false);
            fieldThird.addProperty("underline", false);
            JsonObject fieldFourth = new JsonObject();
            fieldFourth.addProperty("add_name", "Краткое");
            fieldFourth.addProperty("field_id", "document.skip_reg_number");
            fieldFourth.addProperty("bold", false);
            fieldFourth.addProperty("italics", true);
            fieldFourth.addProperty("underline", false);
            JsonObject fieldFifth = new JsonObject();
            fieldFifth.addProperty("add_name", "Комметарий");
            fieldFifth.addProperty("field_id", "document.skip_reg_number");
            fieldFifth.addProperty("bold", false);
            fieldFifth.addProperty("italics", false);
            fieldFifth.addProperty("underline", true);
            fieldsSecondColumn.add(fieldThird);
            fieldsSecondColumn.add(fieldFourth);
            fieldsSecondColumn.add(fieldFourth);
            secondColumn.add("fields",fieldsSecondColumn);
            columns.add(firstColumn);
            columns.add(secondColumn);
            requestTables.add("columns", columns);

            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .body(requestTables.toString())
                    .when()
                    .post(API_CONSTRUCTOR_TABLES)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            idCreatedTable = jsonPath.getInt("data.id");
            idFirstColumn = jsonPath.getInt("data.columns[0].id");
            idFirstField = jsonPath.getInt("data.columns[0].fields[0].id");
            idSecondField = jsonPath.getInt("data.columns[0].fields[1].id");
            logger.info("Created table id - " + idCreatedTable);
            logger.info("First column id - " + idFirstColumn);
            logger.info("First field in first column id - " + idFirstField);
            logger.info("Second field in first column id - " + idSecondField);
            String nameFirstColumn = jsonPath.getString("data.columns[0].column_name");
            String nameSecondColumn = jsonPath.getString("data.columns[1].column_name");
            Assert.assertEquals(nameFirstColumn, "Наименование события");
            Assert.assertEquals(nameSecondColumn, "Описание события");

            logger.info("Step 1 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step02(){
        try {
            JsonObject requestTables = new JsonObject();
            requestTables.addProperty("name", "");
            requestTables.addProperty("row_type_id", "deadlines");
            requestTables.addProperty("only_officials", false);
            requestTables.addProperty("only_roles", false);
            requestTables.addProperty("only_provider", false);
            requestTables.addProperty("display_on_mainwindow", true);
            JsonArray columns = new JsonArray();
            JsonObject firstColumn = new JsonObject();
            firstColumn.addProperty("column_name","Столбец тест");
            firstColumn.addProperty("separator_id", ",");
            JsonArray fieldsFirstColumn = new JsonArray();
            JsonObject fieldFirst = new JsonObject();
            fieldFirst.addProperty("add_name", "Поле тест");
            fieldFirst.addProperty("field_id", "document.skip_reg_number");
            fieldFirst.addProperty("bold", false);
            fieldFirst.addProperty("italics", false);
            fieldFirst.addProperty("underline", false);
            fieldsFirstColumn.add(fieldFirst);
            firstColumn.add("fields", fieldsFirstColumn);
            columns.add(firstColumn);
            requestTables.add("columns", columns);

            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .body(requestTables.toString())
                    .when()
                    .post(API_CONSTRUCTOR_TABLES)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            String errorMessage = jsonPath.getString("errors.name[0]");
            logger.info("Error message : " + errorMessage);
            Assert.assertEquals(errorMessage, "не может быть пустым");

            logger.info("Step 2 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step03(){
        try {
            JsonObject requestTables = new JsonObject();
            requestTables.addProperty("name", "Тест таблица");
            requestTables.addProperty("row_type_id", "documents");
            requestTables.addProperty("only_officials", false);
            requestTables.addProperty("only_roles", false);
            requestTables.addProperty("only_provider", false);
            requestTables.addProperty("display_on_mainwindow", true);
            JsonArray columns = new JsonArray();
            JsonObject firstColumn = new JsonObject();
            firstColumn.addProperty("column_name","Столбец тест");
            firstColumn.addProperty("separator_id", ",");
            JsonArray fieldsFirstColumn = new JsonArray();
            JsonObject fieldFirst = new JsonObject();
            fieldFirst.addProperty("add_name", "Поле тест");
            fieldFirst.addProperty("field_id", "document.skip_reg_number");
            fieldFirst.addProperty("bold", false);
            fieldFirst.addProperty("italics", false);
            fieldFirst.addProperty("underline", false);
            fieldsFirstColumn.add(fieldFirst);
            firstColumn.add("fields", fieldsFirstColumn);
            columns.add(firstColumn);
            requestTables.add("columns", columns);

            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .body(requestTables.toString())
                    .when()
                    .post(API_CONSTRUCTOR_TABLES)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            String errorMessage = jsonPath.getString("errors.name[0]");
            logger.info("Error message : " + errorMessage);
            Assert.assertEquals(errorMessage, "уже существует");

            logger.info("Step 3 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step04(){
        try {
            JsonObject requestTables = new JsonObject();
            requestTables.addProperty("name", randomString);
            requestTables.addProperty("row_type_id", "string");
            requestTables.addProperty("only_officials", false);
            requestTables.addProperty("only_roles", false);
            requestTables.addProperty("only_provider", false);
            requestTables.addProperty("display_on_mainwindow", true);
            JsonArray columns = new JsonArray();
            JsonObject firstColumn = new JsonObject();
            firstColumn.addProperty("column_name","Столбец тест");
            firstColumn.addProperty("separator_id", ",");
            JsonArray fieldsFirstColumn = new JsonArray();
            JsonObject fieldFirst = new JsonObject();
            fieldFirst.addProperty("add_name", "Поле тест");
            fieldFirst.addProperty("field_id", "document.skip_reg_number");
            fieldFirst.addProperty("bold", false);
            fieldFirst.addProperty("italics", false);
            fieldFirst.addProperty("underline", false);
            fieldsFirstColumn.add(fieldFirst);
            firstColumn.add("fields", fieldsFirstColumn);
            columns.add(firstColumn);
            requestTables.add("columns", columns);

            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .body(requestTables.toString())
                    .when()
                    .post(API_CONSTRUCTOR_TABLES)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            String errorMessage = jsonPath.getString("errors.row_type_id[0]");
            logger.info("Error message : " + errorMessage);
            Assert.assertEquals(errorMessage, "имеет непредусмотренное значение");

            logger.info("Step 4 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step05(){
        try {
            JsonObject requestTables = new JsonObject();
            requestTables.addProperty("name", randomString);
            requestTables.addProperty("row_type_id", "assignments");
            requestTables.addProperty("only_officials", true);
            JsonArray official_ids = new JsonArray();
            official_ids.add("54e35f6179fa007422000002");
            requestTables.add("official_ids",official_ids);
            requestTables.addProperty("only_roles", false);
            requestTables.addProperty("only_provider", false);
            requestTables.addProperty("display_on_mainwindow", true);
            JsonArray columns = new JsonArray();
            JsonObject firstColumn = new JsonObject();
            firstColumn.addProperty("column_name","Столбец тест");
            firstColumn.addProperty("separator_id", ",");
            JsonArray fieldsFirstColumn = new JsonArray();
            JsonObject fieldFirst = new JsonObject();
            fieldFirst.addProperty("add_name", "Поле тест");
            fieldFirst.addProperty("field_id", "document.skip_reg_number");
            fieldFirst.addProperty("bold", false);
            fieldFirst.addProperty("italics", false);
            fieldFirst.addProperty("underline", false);
            fieldsFirstColumn.add(fieldFirst);
            firstColumn.add("fields", fieldsFirstColumn);
            columns.add(firstColumn);
            requestTables.add("columns", columns);

            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .body(requestTables.toString())
                    .when()
                    .post(API_CONSTRUCTOR_TABLES)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            String errorMessage = jsonPath.getString("errors.officials[0]");
            logger.info("Error message : " + errorMessage);
            Assert.assertEquals(errorMessage, "имеет непредусмотренное значение");

            logger.info("Step 5 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step06(){
        try {
            JsonObject requestTables = new JsonObject();
            requestTables.addProperty("name", randomString);
            requestTables.addProperty("row_type_id", "assignments");
            requestTables.addProperty("only_officials", false);
            requestTables.addProperty("only_roles", true);
            JsonArray role_ids = new JsonArray();
            role_ids.add("10000");
            requestTables.add("role_ids", role_ids);
            requestTables.addProperty("only_provider", false);
            requestTables.addProperty("display_on_mainwindow", true);
            JsonArray columns = new JsonArray();
            JsonObject firstColumn = new JsonObject();
            firstColumn.addProperty("column_name","Столбец тест");
            firstColumn.addProperty("separator_id", ",");
            JsonArray fieldsFirstColumn = new JsonArray();
            JsonObject fieldFirst = new JsonObject();
            fieldFirst.addProperty("add_name", "Поле тест");
            fieldFirst.addProperty("field_id", "document.skip_reg_number");
            fieldFirst.addProperty("bold", false);
            fieldFirst.addProperty("italics", false);
            fieldFirst.addProperty("underline", false);
            fieldsFirstColumn.add(fieldFirst);
            firstColumn.add("fields", fieldsFirstColumn);
            columns.add(firstColumn);
            requestTables.add("columns", columns);

            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .body(requestTables.toString())
                    .when()
                    .post(API_CONSTRUCTOR_TABLES)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            String errorMessage = jsonPath.getString("errors.roles[0]");
            logger.info("Error message : " + errorMessage);
            Assert.assertEquals(errorMessage, "имеет непредусмотренное значение");

            logger.info("Step 6 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step07(){
        try {
            JsonObject requestTables = new JsonObject();
            requestTables.addProperty("name", randomString);
            requestTables.addProperty("row_type_id", "assignments");
            requestTables.addProperty("only_officials", false);
            requestTables.addProperty("only_roles", true);
            JsonArray role_ids = new JsonArray();
            role_ids.add("29");
            requestTables.add("role_ids", role_ids);
            requestTables.addProperty("only_provider", false);
            requestTables.addProperty("display_on_mainwindow", true);
            JsonArray columns = new JsonArray();
            JsonObject firstColumn = new JsonObject();
            firstColumn.addProperty("column_name","Столбец тест");
            firstColumn.addProperty("separator_id", "%");
            JsonArray fieldsFirstColumn = new JsonArray();
            JsonObject fieldFirst = new JsonObject();
            fieldFirst.addProperty("add_name", "Поле тест");
            fieldFirst.addProperty("field_id", "document.skip_reg_number");
            fieldFirst.addProperty("bold", false);
            fieldFirst.addProperty("italics", false);
            fieldFirst.addProperty("underline", false);
            fieldsFirstColumn.add(fieldFirst);
            firstColumn.add("fields", fieldsFirstColumn);
            columns.add(firstColumn);
            requestTables.add("columns", columns);

            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .body(requestTables.toString())
                    .when()
                    .post(API_CONSTRUCTOR_TABLES)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            String errorMessage = jsonPath.getString("errors.columns.0.separator_id[0]");
            logger.info("Error message : " + errorMessage);
            Assert.assertEquals(errorMessage, "имеет непредусмотренное значение");

            logger.info("Step 7 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step08(){
        try {
            JsonObject requestTables = new JsonObject();
            requestTables.addProperty("name", randomString);
            requestTables.addProperty("row_type_id", "assignments");
            requestTables.addProperty("only_officials", false);
            requestTables.addProperty("only_roles", true);
            JsonArray role_ids = new JsonArray();
            role_ids.add("5");
            requestTables.add("role_ids", role_ids);
            requestTables.addProperty("only_provider", false);
            requestTables.addProperty("display_on_mainwindow", true);
            JsonArray columns = new JsonArray();
            JsonObject firstColumn = new JsonObject();
            firstColumn.addProperty("column_name","Столбец тест");
            firstColumn.addProperty("separator_id", "_");
            JsonArray fieldsFirstColumn = new JsonArray();
            JsonObject fieldFirst = new JsonObject();
            fieldFirst.addProperty("add_name", "Поле тест");
            fieldFirst.addProperty("field_id", "1");
            fieldFirst.addProperty("bold", false);
            fieldFirst.addProperty("italics", false);
            fieldFirst.addProperty("underline", false);
            fieldsFirstColumn.add(fieldFirst);
            firstColumn.add("fields", fieldsFirstColumn);
            columns.add(firstColumn);
            requestTables.add("columns", columns);

            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .body(requestTables.toString())
                    .when()
                    .post(API_CONSTRUCTOR_TABLES)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            String errorMessage = jsonPath.getString("errors.columns.0.fields.0.field_id[0]");
            logger.info("Error message : " + errorMessage);
            Assert.assertEquals(errorMessage, "имеет непредусмотренное значение");

            logger.info("Step 8 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step09(){
        try {
            JsonObject requestTables = new JsonObject();
            requestTables.addProperty("name", randomString);
            requestTables.addProperty("row_type_id", "assignments");
            requestTables.addProperty("only_officials", false);
            requestTables.addProperty("only_roles", true);
            JsonArray role_ids = new JsonArray();
            role_ids.add("5");
            requestTables.add("role_ids", role_ids);
            requestTables.addProperty("only_provider", false);
            requestTables.addProperty("display_on_mainwindow", true);
            JsonArray columns = new JsonArray();
            JsonObject firstColumn = new JsonObject();
            firstColumn.addProperty("column_name","Столбец тест");
            firstColumn.addProperty("separator_id", "_");
            JsonArray fieldsFirstColumn = new JsonArray();
            JsonObject fieldFirst = new JsonObject();
            fieldFirst.addProperty("add_name", "Поле тест");
            fieldFirst.addProperty("field_id", "document.skip_reg_number");
            fieldFirst.addProperty("bold", false);
            fieldFirst.addProperty("italics", false);
            fieldFirst.addProperty("underline", false);
            fieldsFirstColumn.add(fieldFirst);
            firstColumn.add("fields", fieldsFirstColumn);
            columns.add(firstColumn);
            requestTables.add("columns", columns);

            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdSix)
                    .body(requestTables.toString())
                    .when()
                    .post(API_CONSTRUCTOR_TABLES)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            String errorMessage = jsonPath.getString("error");
            logger.info("Error message : " + errorMessage);
            Assert.assertEquals(errorMessage, "Доступ к ресурсу запрещен");

            logger.info("Step 9 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step10(){
        try {
            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .when()
                    .put(API_CONSTRUCTOR_TABLES + String.format("/%s", idCreatedTable))
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            String nameFirstColumn = jsonPath.getString("data.columns[0].column_name");
            String nameSecondColumn = jsonPath.getString("data.columns[1].column_name");
            Assert.assertEquals(nameFirstColumn, "Наименование события");
            Assert.assertEquals(nameSecondColumn, "Описание события");

            logger.info("Step 10 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step11(){
        try {
            JsonObject requestTables = new JsonObject();
            requestTables.addProperty("name", "");

            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .body(requestTables.toString())
                    .when()
                    .put(API_CONSTRUCTOR_TABLES + String.format("/%s", idCreatedTable))
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            String errorMessage = jsonPath.getString("errors.name[0]");
            logger.info("Error message : " + errorMessage);
            Assert.assertEquals(errorMessage, "не может быть пустым");

            logger.info("Step 11 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step12(){
        try {
            JsonObject requestTables = new JsonObject();
            requestTables.addProperty("name", "Тест таблица");

            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .body(requestTables.toString())
                    .when()
                    .put(API_CONSTRUCTOR_TABLES + String.format("/%s", idCreatedTable))
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            String errorMessage = jsonPath.getString("errors.name[0]");
            logger.info("Error message : " + errorMessage);
            Assert.assertEquals(errorMessage, "уже существует");

            logger.info("Step 12 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step13(){
        try {
            JsonObject requestTables = new JsonObject();
            requestTables.addProperty("row_type_id", "Тест");

            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .body(requestTables.toString())
                    .when()
                    .put(API_CONSTRUCTOR_TABLES + String.format("/%s", idCreatedTable))
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            String errorMessage = jsonPath.getString("errors.row_type_id[0]");
            logger.info("Error message : " + errorMessage);
            Assert.assertEquals(errorMessage, "имеет непредусмотренное значение");

            logger.info("Step 13 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step14(){
        try {
            JsonObject requestTables = new JsonObject();
            JsonArray official_ids = new JsonArray();
            official_ids.add("123");
            requestTables.add("official_ids",official_ids);

            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .body(requestTables.toString())
                    .when()
                    .put(API_CONSTRUCTOR_TABLES + String.format("/%s", idCreatedTable))
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            String errorMessage = jsonPath.getString("errors.officials[0]");
            logger.info("Error message : " + errorMessage);
            Assert.assertEquals(errorMessage, "имеет непредусмотренное значение");

            logger.info("Step 14 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step15(){
        try {
            JsonObject requestTables = new JsonObject();
            requestTables.addProperty("name", "Таблица ролей " + randomString);
            requestTables.addProperty("row_type_id", "documents");
            requestTables.addProperty("only_officials", false);
            requestTables.addProperty("only_roles", true);
            JsonArray role_ids = new JsonArray();
            role_ids.add(29);
            role_ids.add(5);
            requestTables.add("role_ids", role_ids);
            requestTables.addProperty("only_provider", false);
            requestTables.addProperty("display_on_mainwindow", true);
            JsonArray columns = new JsonArray();
            JsonObject firstColumn = new JsonObject();
            firstColumn.addProperty("column_name","");
            firstColumn.addProperty("separator_id", ",");
            JsonArray fieldsFirstColumn = new JsonArray();
            JsonObject fieldFirst = new JsonObject();
            fieldFirst.addProperty("add_name", "Имя");
            fieldFirst.addProperty("field_id", "document.skip_reg_number");
            fieldFirst.addProperty("bold", true);
            fieldFirst.addProperty("italics", false);
            fieldFirst.addProperty("underline", true);
            JsonObject fieldSecond = new JsonObject();
            fieldSecond.addProperty("add_name", "Id");
            fieldSecond.addProperty("field_id", "document.skip_reg_number");
            fieldSecond.addProperty("bold", false);
            fieldSecond.addProperty("italics", true);
            fieldSecond.addProperty("underline", true);
            fieldsFirstColumn.add(fieldFirst);
            fieldsFirstColumn.add(fieldSecond);
            firstColumn.add("fields", fieldsFirstColumn);
            columns.add(firstColumn);
            requestTables.add("columns", columns);

            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .body(requestTables.toString())
                    .when()
                    .post(API_CONSTRUCTOR_TABLES)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            idCreatedTableRoles = jsonPath.getInt("data.id");
            logger.info("Created table with roles id - " + idCreatedTableRoles);
            int idRoleFirst = jsonPath.getInt("data.roles[0].id");
            int idRoleSecond = jsonPath.getInt("data.roles[1].id");
            Assert.assertEquals(idRoleFirst,29);
            Assert.assertEquals(idRoleSecond, 5);
            logger.info("Roles ids : " + idRoleFirst + " and " + idRoleSecond);

            logger.info("Step 15 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step16(){
        try {
            JsonObject requestTables = new JsonObject();
            JsonArray role_ids = new JsonArray();
            role_ids.add("10000");
            role_ids.add("10001");
            role_ids.add("10002");
            requestTables.add("role_ids",role_ids);

            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .body(requestTables.toString())
                    .when()
                    .put(API_CONSTRUCTOR_TABLES + String.format("/%s", idCreatedTableRoles))
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            String errorMessage = jsonPath.getString("errors.roles[0]");
            logger.info("Error message : " + errorMessage);
            Assert.assertEquals(errorMessage, "имеет непредусмотренное значение");

            logger.info("Step 16 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step17(){
        try {
            JsonObject requestTables = new JsonObject();
            JsonArray columns = new JsonArray();
            JsonObject firstColumn = new JsonObject();
            firstColumn.addProperty("id",idFirstColumn);
            firstColumn.addProperty("separator_id", "%");
            columns.add(firstColumn);
            requestTables.add("columns", columns);

            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .body(requestTables.toString())
                    .when()
                    .put(API_CONSTRUCTOR_TABLES + String.format("/%s", idCreatedTable))
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            String errorMessage = jsonPath.getString("errors.columns.0.separator_id[0]");
            logger.info("Error message : " + errorMessage);
            Assert.assertEquals(errorMessage, "имеет непредусмотренное значение");

            logger.info("Step 17 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step18(){
        try {
            JsonObject requestTables = new JsonObject();
            JsonArray columns = new JsonArray();
            JsonObject firstColumn = new JsonObject();
            firstColumn.addProperty("id",idFirstColumn);
            JsonArray fields = new JsonArray();
            JsonObject firstField = new JsonObject();
            firstField.addProperty("id", idFirstField);
            firstField.addProperty("field_id", 2);
            fields.add(firstField);
            firstColumn.add("fields", fields);
            columns.add(firstColumn);
            requestTables.add("columns", columns);

            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .body(requestTables.toString())
                    .when()
                    .put(API_CONSTRUCTOR_TABLES + String.format("/%s", idCreatedTable))
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            String errorMessage = jsonPath.getString("errors.columns.0.fields.0.field_id[0]");
            logger.info("Error message : " + errorMessage);
            Assert.assertEquals(errorMessage, "имеет непредусмотренное значение");

            logger.info("Step 18 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step19(){
        try {
            JsonObject requestTables = new JsonObject();
            JsonArray columns = new JsonArray();
            JsonObject firstColumn = new JsonObject();
            firstColumn.addProperty("id",idFirstColumn);
            firstColumn.addProperty("column_name", "Измененное название");
            JsonArray fields = new JsonArray();
            JsonObject firstField = new JsonObject();
            firstField.addProperty("id", idFirstField);
            firstField.addProperty("add_name", "Новое имя");
            fields.add(firstField);
            firstColumn.add("fields", fields);
            columns.add(firstColumn);
            requestTables.add("columns", columns);

            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .body(requestTables.toString())
                    .when()
                    .put(API_CONSTRUCTOR_TABLES + String.format("/%s", idCreatedTable))
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            String columnName = jsonPath.getString("data.columns[1].column_name");
            String fieldName = jsonPath.getString("data.columns[1].fields[1].add_name");
            logger.info("Changed values : " + columnName + " and " + fieldName);
            Assert.assertEquals(columnName, "Измененное название");
            Assert.assertEquals(fieldName, "Новое имя");

            logger.info("Step 19 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step20(){
        try {
            JsonObject requestTables = new JsonObject();
            requestTables.addProperty("name", "Тест таблица " + randomString);
            requestTables.addProperty("row_type_id", "documents");
            requestTables.addProperty("only_officials", true);
            JsonArray official_ids = new JsonArray();
            official_ids.add("6572d99f14b4000001000001");
            official_ids.add("60b580365f25000008000001");
            official_ids.add("5b60ec5c6a38000008000001");
            requestTables.add("official_ids",official_ids);
            requestTables.addProperty("only_roles", false);
            requestTables.addProperty("only_provider", false);
            requestTables.addProperty("display_on_mainwindow", true);
            JsonArray columns = new JsonArray();
            JsonObject firstColumn = new JsonObject();
            firstColumn.addProperty("column_name","Наименование события");
            firstColumn.addProperty("separator_id", ",");
            JsonArray fieldsFirstColumn = new JsonArray();
            JsonObject fieldFirst = new JsonObject();
            fieldFirst.addProperty("add_name", "Имя");
            fieldFirst.addProperty("field_id", "document.skip_reg_number");
            fieldFirst.addProperty("bold", true);
            fieldFirst.addProperty("italics", false);
            fieldFirst.addProperty("underline", true);
            JsonObject fieldSecond = new JsonObject();
            fieldSecond.addProperty("add_name", "Id");
            fieldSecond.addProperty("field_id", "document.skip_reg_number");
            fieldSecond.addProperty("bold", false);
            fieldSecond.addProperty("italics", true);
            fieldSecond.addProperty("underline", true);
            fieldsFirstColumn.add(fieldFirst);
            fieldsFirstColumn.add(fieldSecond);
            firstColumn.add("fields", fieldsFirstColumn);
            JsonObject secondColumn = new JsonObject();
            secondColumn.addProperty("column_name","Описание события");
            secondColumn.addProperty("separator_id", ",");
            JsonArray fieldsSecondColumn = new JsonArray();
            JsonObject fieldThird = new JsonObject();
            fieldThird.addProperty("add_name", "Полное");
            fieldThird.addProperty("field_id", "document.skip_reg_number");
            fieldThird.addProperty("bold", false);
            fieldThird.addProperty("italics", false);
            fieldThird.addProperty("underline", false);
            JsonObject fieldFourth = new JsonObject();
            fieldFourth.addProperty("add_name", "Краткое");
            fieldFourth.addProperty("field_id", "document.skip_reg_number");
            fieldFourth.addProperty("bold", false);
            fieldFourth.addProperty("italics", true);
            fieldFourth.addProperty("underline", false);
            JsonObject fieldFifth = new JsonObject();
            fieldFifth.addProperty("add_name", "Комметарий");
            fieldFifth.addProperty("field_id", "document.skip_reg_number");
            fieldFifth.addProperty("bold", false);
            fieldFifth.addProperty("italics", false);
            fieldFifth.addProperty("underline", true);
            fieldsSecondColumn.add(fieldThird);
            fieldsSecondColumn.add(fieldFourth);
            fieldsSecondColumn.add(fieldFourth);
            secondColumn.add("fields",fieldsSecondColumn);
            columns.add(firstColumn);
            columns.add(secondColumn);
            requestTables.add("columns", columns);

            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdSix)
                    .body(requestTables.toString())
                    .when()
                    .put(API_CONSTRUCTOR_TABLES + String.format("/%s", idCreatedTable))
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            String errorMessage = jsonPath.getString("error");
            logger.info("Error message : " + errorMessage);
            Assert.assertEquals(errorMessage, "Доступ к ресурсу запрещен");

            logger.info("Step 20 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }
}

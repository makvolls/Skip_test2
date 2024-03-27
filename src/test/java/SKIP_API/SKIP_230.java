package SKIP_API;

import Utils.RandomGenerateText;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class SKIP_230 {

    // Endpoints:
    private static String API_CONSTRUCTOR_TABLES = "http://api.skip.rtech.ru/v1/constructor/tables";
    private static String API_CURRENT_USER = "http://api.skip.rtech.ru/v1/permissions/users/current";

    // Authorization parameters:
    int userIdOne = 1; // User that have all rights, role id = 29
    int userIdSix = 6; // User that have right - "show_roles"
    int userIdTwo = 2; // User from other provider

    // Variables
    int idCreatedTable;

    // Logger
    private static final Logger logger = Logger.getLogger(SKIP_230.class);

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
            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .when()
                    .get(API_CURRENT_USER)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            List<Integer> idsTable = jsonPath.getList("data.tables.id");
            for (int idTable: idsTable){
                logger.info("Table id from user request : " + idTable);
            }
            response.then().assertThat().body("data.tables", notNullValue());

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
            requestTables.addProperty("name", "тест задача " + randomString);
            requestTables.addProperty("row_type_id", "assignments");
            requestTables.addProperty("only_officials", false);
            requestTables.addProperty("only_roles", true);
            JsonArray role_ids = new JsonArray();
            role_ids.add("29");
            requestTables.add("role_ids", role_ids);
            requestTables.addProperty("only_provider", false);
            requestTables.addProperty("display_on_mainwindow", false);
            JsonArray columns = new JsonArray();
            JsonObject firstColumn = new JsonObject();
            firstColumn.addProperty("column_name","Столбец тест");
            firstColumn.addProperty("separator_id", "!");
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

            idCreatedTable = jsonPath.getInt("data.id");
            logger.info("Table's id : " + idCreatedTable);
            response.then().assertThat().body("data", notNullValue());

            logger.info("Step 2 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step03(){
        try {
            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .when()
                    .get(API_CURRENT_USER)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            List<Integer> idsTable = jsonPath.getList("data.tables.id");
            boolean isTableOnMainWindow = false;
            for (int idTable: idsTable){
                if (idTable == idCreatedTable) {
                    logger.info("Created table represented on main window.");
                    isTableOnMainWindow = true;
                }
            }
            response.then().assertThat().body("data.tables", notNullValue());
            Assert.assertFalse(isTableOnMainWindow);

            logger.info("Step 3 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step04(){
        try {
            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdSix)
                    .when()
                    .get(API_CURRENT_USER)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            List<Integer> idsTable = jsonPath.getList("data.tables.id");
            for (int idTable: idsTable){
                logger.info("Table id from user request : " + idTable);
            }
            response.then().assertThat().body("data.tables", notNullValue());

            logger.info("Step 4 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step05(){
        try {
            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdTwo)
                    .when()
                    .get(API_CURRENT_USER)
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            String tableValue = jsonPath.getString("data.tables");

            Assert.assertEquals(tableValue, "[]");

            logger.info("Step 5 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }
}

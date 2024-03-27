package SKIP_API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SKIP_231 {

    // Endpoints:
    private static String API_SED_DOCUMENTS = "http://api.skip.rtech.ru/v1/sed/documents";

    // Authorization parameters:
    int userIdOne = 1; // User that have all rights, role id = 29
    int userIdSix = 6; // User that have right - "show_roles"

    // UUID
    private static String uuidIncomingDocument = "02fd2cdcf69f37732177a21548cefc" +
            "3e3687a6ef80918d93a52853a27060b1b56c";
    private static String uuidOutgoingSecretDocument = "090198560d4a47096106a19a8a73cefad" +
            "06531829b0321e7c835f8bca259cadf8b";
    private static String uuidUrgencyNull = "09789b7d3ec4990f2bb513b93d991d68b33173ce1ced08bbe088bd632269da7dfe";

    // Logger
    private static final Logger logger = Logger.getLogger(SKIP_231.class);
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
                    .get(API_SED_DOCUMENTS + String.format("/%s", uuidIncomingDocument))
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            String sedSecurity = jsonPath.getString("data.sed_security.name");
            String urgency = jsonPath.getString("data.urgency.name");
            logger.info("Needed values : " + sedSecurity + " and " + urgency);
            Assert.assertEquals(sedSecurity, "Н/С");
            Assert.assertEquals(urgency, "Весьма срочно");

            logger.info("Step 1 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void step02(){
        try {
            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", userIdOne)
                    .when()
                    .get(API_SED_DOCUMENTS + String.format("/%s", uuidOutgoingSecretDocument))
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            String sedSecurity = jsonPath.getString("data.sed_security.name");
            String urgency = jsonPath.getString("data.urgency.name");
            logger.info("Needed values : " + sedSecurity + " and " + urgency);
            Assert.assertEquals(sedSecurity, "ДСП");
            Assert.assertEquals(urgency, "Оперативно");

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
                    .header("Test-Authorization", userIdSix)
                    .when()
                    .get(API_SED_DOCUMENTS + String.format("/%s", uuidOutgoingSecretDocument))
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            String sedSecurity = jsonPath.getString("data.sed_security.name");
            String urgency = jsonPath.getString("data.urgency.name");
            logger.info("Needed values : " + sedSecurity + " and " + urgency);
            Assert.assertEquals(sedSecurity, "ДСП");
            Assert.assertEquals(urgency, "Оперативно");

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
                    .header("Test-Authorization", userIdOne)
                    .when()
                    .get(API_SED_DOCUMENTS + String.format("/%s", uuidUrgencyNull))
                    .then().log().all().extract().response();
            JsonPath jsonPath = response.jsonPath();

            String sedSecurity = jsonPath.getString("data.sed_security.name");
            String urgency = jsonPath.getString("data.urgency.name");
            logger.info("Needed values : " + sedSecurity + " and " + urgency);
            Assert.assertEquals(sedSecurity, "С");
            Assert.assertNull(urgency);

            logger.info("Step 4 successfully completed!");
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            Assert.fail();
        }
    }
}

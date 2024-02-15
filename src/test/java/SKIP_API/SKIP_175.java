package SKIP_API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SKIP_175 {

    private static String API_DOCUMENTS_SED = "http://api.skip.rtech.ru/v1/sed/documents";
    int userIdOne = 1; // User that have all rights, role id = 29
    int userIdTwo = 2; // User from another provider

    // uids for checking
    String uidIncomingDocumentsAllFields = "02e273cb06011a3cad7c8a09deadb38c7a4ec77ca989c4a0f8ce53bc6f4d5e0328";
    String uidIncomingDocumentsRequiredFields = "02abb92f03dde0a503a058de94031c1712c14f3bde73062c72cd8d8a94371adbcc";
    String uidSecretIncomingDocumentsAllFields = "06db03fd52f6821a8091704f740706633436d40c3adceaadd430c6d6f24884d2bf";
    String uidSecretIncomingDocumentsRequiredFields = "067ff5dec65410b6efb57676933d982cdc2ce9dc7f79fb093806540bdf8858b96f";
    String uidOutgoingDocumentsAllFields = "05910eead4f5760b67a4575d766f825964f2139a2ece1d571ad62abb8a5c0e2dca";
    String uidOutgoingDocumentsRequiredFields = "0563bd3b2be801dda53ebf693a3214c03ca175b0f5cd344e8f81f206519fbc760f";
    String uidSecretOutgoingDocumentsAllFields = "09789b7d3ec4990f2bb513b93d991d68b33173ce1ced08bbe088bd632269da7dfe";
    String uidSecretOutgoingDocumentsRequiredFields = "0964e221c941d16258f077be12d9d0a878a3fca53169d2b8afb51dfe928334dbe2";
    String uidOrdersAllFields = "04d34ac42e66633c07815ccf70380bb791311a6df20466c9be8b430d994ded5afb";
    String uidOrdersRequiredFields = "0458c5d68d89b24eb0f999e74bd60468cdb28487d4135af6815ea14ce850fa7568";
    String uidSecretOrdersAllFields = "08b29e79d9d989f2766c2415cd9a93a39b7ec1975b06e1cb1ae7a76a7787ffba42";
    String uidSecretOrdersRequiredFields = "087cda8ae8cda7b7b767219264b1716062fb59eafd8a7d8ef20fb24704e72792a0";
    String uidOrdersDdoAllFields = "10d6f07642a7ed4aeef873346cc032639f468bf1944113384c5e4bdc48a0f7175e";
    String uidOrdersDdoRequiredFields = "10713df2707ed5d032e90881c010ee1c641e40ea1bf12e0c2f2c57ca2c99117702";
    String uidIncomingOrdersAllFields = "03564512d468ecebb29f5c9a2a573615ce68edd5e352395fc09237389317fcbfe2";
    String uidIncomingOrdersRequiredFields = "038b4053cb555a1043c1c519f68a983e04c1808d9996417203884830356e664637";
    String uidSecretIncomingOrdersAllFields = "07701efe745fd3954d266dd323e7ee0af307de61b51bb1eb71f572c2fa8fb59360";
    String uidSecretIncomingOrdersRequiredFields = "072dd7ac08237fc64aabdd10577ff5f8c23d69d53c246a5654d8938e7f2a809dac";
    String uidCitizenRequestsAllFields = "011276260b77d4ec949dd2eef5a40725e2b224c286003af90fc899fab74c779418";
    String uidCitizenRequestsRequiredFields = "0144a30342d5af4656d7e87054fca9516cf3711ab012eb426cf98f048fd68ac6e6";
    String uidAttorneyLetters = "11c716ec87b011d20fbcd91836d4137aa8d7eddcc97a49c0a5acd34f83de04a753";

    @Test
    public void step01(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .get(API_DOCUMENTS_SED + String.format("/%s",uidIncomingDocumentsAllFields))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String uid = jsonPath.getString("data.uid");
        String provider_id = jsonPath.getString("data.provider_id");
        String journal_type = jsonPath.getString("data.journal_type");
        String single_number = jsonPath.getString("data.single_number");
        String reg_number = jsonPath.getString("data.reg_number");
        String sed_document_date = jsonPath.getString("data.sed_document_date");
        String sed_security_stamp = jsonPath.getString("data.sed_security_stamp");
        String external_document_number = jsonPath.getString("data.external_document_number");
        String external_reg_date = jsonPath.getString("data.external_reg_date");
        String sed_document_type = jsonPath.getString("data.sed_document_type");
        String urgency = jsonPath.getString("data.urgency");
        String ddo_external_document_number = jsonPath.getString("data.ddo_external_document_number");
        String ddo_external_document_date = jsonPath.getString("data.ddo_external_document_date");
        String note = jsonPath.getString("data.note");
        String short_description = jsonPath.getString("data.short_description");
        String signer_name = jsonPath.getString("data.signer_name");
        String signer_id = jsonPath.getString("data.signer_id");
        String signer_type = jsonPath.getString("data.signer_type");
        String came_from_name = jsonPath.getString("data.came_from_name");
        String came_from_id = jsonPath.getString("data.came_from_id");
        String came_from_type = jsonPath.getString("data.came_from_type");
        String recipient_name = jsonPath.getString("data.recipient_name");
        String recipient_id = jsonPath.getString("data.recipient_id");
        String recipient_type = jsonPath.getString("data.recipient_type");
        String resolution = jsonPath.getString("data.resolution");

        Assert.assertNotNull(uid);
        Assert.assertNotNull(provider_id);
        Assert.assertNotNull(journal_type);
        Assert.assertNotNull(single_number);
        Assert.assertNotNull(reg_number);
        Assert.assertNotNull(sed_document_date);
        Assert.assertNotNull(sed_security_stamp);
        Assert.assertNotNull(external_document_number);
        Assert.assertNotNull(external_reg_date);
        Assert.assertNotNull(sed_document_type);
        Assert.assertNotNull(urgency);
        Assert.assertNotNull(ddo_external_document_number);
        Assert.assertNotNull(ddo_external_document_date);
        Assert.assertNotNull(note);
        Assert.assertNotNull(short_description);
        Assert.assertNotNull(signer_name);
        Assert.assertNotNull(signer_id);
        Assert.assertNotNull(signer_type);
        Assert.assertNotNull(came_from_name);
        Assert.assertNotNull(came_from_id);
        Assert.assertNotNull(came_from_type);
        Assert.assertNotNull(recipient_name);
        Assert.assertNotNull(recipient_id);
        Assert.assertNotNull(recipient_type);
        Assert.assertNotNull(resolution);
    }

    @Test
    public void step02(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .get(API_DOCUMENTS_SED + String.format("/%s",uidIncomingDocumentsRequiredFields))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String uid = jsonPath.getString("data.uid");
        String provider_id = jsonPath.getString("data.provider_id");
        String journal_type = jsonPath.getString("data.journal_type");
        String single_number = jsonPath.getString("data.single_number");
        String reg_number = jsonPath.getString("data.reg_number");
        String sed_document_date = jsonPath.getString("data.sed_document_date");
        String sed_security_stamp = jsonPath.getString("data.sed_security_stamp");
        String external_document_number = jsonPath.getString("data.external_document_number");
        String external_reg_date = jsonPath.getString("data.external_reg_date");
        String sed_document_type = jsonPath.getString("data.sed_document_type");
        String urgency = jsonPath.getString("data.urgency");
        String ddo_external_document_number = jsonPath.getString("data.ddo_external_document_number");
        String ddo_external_document_date = jsonPath.getString("data.ddo_external_document_date");
        String note = jsonPath.getString("data.note");
        String short_description = jsonPath.getString("data.short_description");
        String signer_name = jsonPath.getString("data.signer_name");
        String signer_id = jsonPath.getString("data.signer_id");
        String signer_type = jsonPath.getString("data.signer_type");
        String came_from_name = jsonPath.getString("data.came_from_name");
        String came_from_id = jsonPath.getString("data.came_from_id");
        String came_from_type = jsonPath.getString("data.came_from_type");
        String recipient_name = jsonPath.getString("data.recipient_name");
        String recipient_id = jsonPath.getString("data.recipient_id");
        String recipient_type = jsonPath.getString("data.recipient_type");
        String resolution = jsonPath.getString("data.resolution");

        Assert.assertNotNull(uid);
        Assert.assertNotNull(provider_id);
        Assert.assertNotNull(journal_type);
        Assert.assertNotNull(single_number);
        Assert.assertNotNull(reg_number);
        Assert.assertNotNull(sed_document_date);
        Assert.assertNotNull(sed_security_stamp);
        Assert.assertNotNull(external_document_number);
        Assert.assertNotNull(external_reg_date);
        Assert.assertNotNull(sed_document_type);
        Assert.assertNotNull(urgency);
        Assert.assertTrue(ddo_external_document_number.equals("")); // Equal null
        Assert.assertNull(ddo_external_document_date); // Equal null
        Assert.assertTrue(note.equals("")); // Equal null
        Assert.assertNotNull(short_description);
        Assert.assertNotNull(signer_name);
        Assert.assertNotNull(signer_id);
        Assert.assertNotNull(signer_type);
        Assert.assertNotNull(came_from_name);
        Assert.assertNotNull(came_from_id);
        Assert.assertNotNull(came_from_type);
        Assert.assertTrue(recipient_name.equals("")); // Equal null
        Assert.assertTrue(recipient_id.equals("")); // Equal null
        Assert.assertTrue(recipient_type.equals("")); // Equal null
        Assert.assertTrue(resolution.equals("")); // Equal null
    }

    @Test
    public void step03(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .get(API_DOCUMENTS_SED + String.format("/%s",uidSecretIncomingDocumentsAllFields))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String uid = jsonPath.getString("data.uid");
        String provider_id = jsonPath.getString("data.provider_id");
        String journal_type = jsonPath.getString("data.journal_type");
        String single_number = jsonPath.getString("data.single_number"); // null
        String reg_number = jsonPath.getString("data.reg_number");
        String sed_document_date = jsonPath.getString("data.sed_document_date");
        String sed_security_stamp = jsonPath.getString("data.sed_security_stamp");
        String external_document_number = jsonPath.getString("data.external_document_number");
        String external_reg_date = jsonPath.getString("data.external_reg_date");
        String sed_document_type = jsonPath.getString("data.sed_document_type");
        String urgency = jsonPath.getString("data.urgency");
        String ddo_external_document_number = jsonPath.getString("data.ddo_external_document_number");
        String ddo_external_document_date = jsonPath.getString("data.ddo_external_document_date");
        String note = jsonPath.getString("data.note");
        String short_description = jsonPath.getString("data.short_description");
        String signer_name = jsonPath.getString("data.signer_name");
        String signer_id = jsonPath.getString("data.signer_id");
        String signer_type = jsonPath.getString("data.signer_type");
        String came_from_name = jsonPath.getString("data.came_from_name");
        String came_from_id = jsonPath.getString("data.came_from_id");
        String came_from_type = jsonPath.getString("data.came_from_type");
        String recipient_name = jsonPath.getString("data.recipient_name");
        String recipient_id = jsonPath.getString("data.recipient_id");
        String recipient_type = jsonPath.getString("data.recipient_type");
        String resolution = jsonPath.getString("data.resolution");

        Assert.assertNotNull(uid);
        Assert.assertNotNull(provider_id);
        Assert.assertNotNull(journal_type);
        Assert.assertNull(single_number); // Equal null (!)
        Assert.assertNotNull(reg_number);
        Assert.assertNotNull(sed_document_date);
        Assert.assertNotNull(sed_security_stamp);
        Assert.assertNotNull(external_document_number);
        Assert.assertNotNull(external_reg_date);
        Assert.assertNotNull(sed_document_type);
        Assert.assertNotNull(urgency);
        Assert.assertNotNull(ddo_external_document_number);
        Assert.assertNotNull(ddo_external_document_date);
        Assert.assertNotNull(note);
        Assert.assertNotNull(short_description);
        Assert.assertNotNull(signer_name);
        Assert.assertNotNull(signer_id);
        Assert.assertNotNull(signer_type);
        Assert.assertNotNull(came_from_name);
        Assert.assertNotNull(came_from_id);
        Assert.assertNotNull(came_from_type);
        Assert.assertNotNull(recipient_name);
        Assert.assertNotNull(recipient_id);
        Assert.assertNotNull(recipient_type);
        Assert.assertNotNull(resolution);
    }

    @Test
    public void step04(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .get(API_DOCUMENTS_SED + String.format("/%s",uidSecretIncomingDocumentsRequiredFields))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String uid = jsonPath.getString("data.uid");
        String provider_id = jsonPath.getString("data.provider_id");
        String journal_type = jsonPath.getString("data.journal_type");
        String single_number = jsonPath.getString("data.single_number");
        String reg_number = jsonPath.getString("data.reg_number");
        String sed_document_date = jsonPath.getString("data.sed_document_date");
        String sed_security_stamp = jsonPath.getString("data.sed_security_stamp");
        String external_document_number = jsonPath.getString("data.external_document_number");
        String external_reg_date = jsonPath.getString("data.external_reg_date");
        String sed_document_type = jsonPath.getString("data.sed_document_type");
        String urgency = jsonPath.getString("data.urgency");
        String ddo_external_document_number = jsonPath.getString("data.ddo_external_document_number");
        String ddo_external_document_date = jsonPath.getString("data.ddo_external_document_date");
        String note = jsonPath.getString("data.note");
        String short_description = jsonPath.getString("data.short_description");
        String signer_name = jsonPath.getString("data.signer_name");
        String signer_id = jsonPath.getString("data.signer_id");
        String signer_type = jsonPath.getString("data.signer_type");
        String came_from_name = jsonPath.getString("data.came_from_name");
        String came_from_id = jsonPath.getString("data.came_from_id");
        String came_from_type = jsonPath.getString("data.came_from_type");
        String recipient_name = jsonPath.getString("data.recipient_name");
        String recipient_id = jsonPath.getString("data.recipient_id");
        String recipient_type = jsonPath.getString("data.recipient_type");
        String resolution = jsonPath.getString("data.resolution");

        Assert.assertNotNull(uid);
        Assert.assertNotNull(provider_id);
        Assert.assertNotNull(journal_type);
        Assert.assertNull(single_number); // Equal null (!)
        Assert.assertNotNull(reg_number);
        Assert.assertNotNull(sed_document_date);
        Assert.assertNotNull(sed_security_stamp);
        Assert.assertNotNull(external_document_number);
        Assert.assertNotNull(external_reg_date);
        Assert.assertNotNull(sed_document_type);
        Assert.assertNull(urgency); // Equal null
        Assert.assertTrue(ddo_external_document_number.equals("")); // Equal null
        Assert.assertNull(ddo_external_document_date); // Equal null
        Assert.assertTrue(note.equals("")); // Equal null
        Assert.assertNotNull(short_description);
        Assert.assertNotNull(signer_name);
        Assert.assertNotNull(signer_id);
        Assert.assertNotNull(signer_type);
        Assert.assertNotNull(came_from_name);
        Assert.assertNotNull(came_from_id);
        Assert.assertNotNull(came_from_type);
        Assert.assertTrue(recipient_name.equals("")); // Equal null
        Assert.assertTrue(recipient_id.equals("")); // Equal null
        Assert.assertTrue(recipient_type.equals("")); // Equal null
        Assert.assertTrue(resolution.equals(""));
    }

    @Test
    public void step05(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .get(API_DOCUMENTS_SED + String.format("/%s",uidOutgoingDocumentsAllFields))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String uid = jsonPath.getString("data.uid");
        String provider_id = jsonPath.getString("data.provider_id");
        String journal_type = jsonPath.getString("data.journal_type");
        String single_number = jsonPath.getString("data.single_number");
        String reg_number = jsonPath.getString("data.reg_number");
        String sed_document_date = jsonPath.getString("data.sed_document_date");
        String sed_security_stamp = jsonPath.getString("data.sed_security_stamp");
        String external_document_number = jsonPath.getString("data.external_document_number");
        String external_reg_date = jsonPath.getString("data.external_reg_date");
        String sed_document_type = jsonPath.getString("data.sed_document_type");
        String urgency = jsonPath.getString("data.urgency");
        String ddo_external_document_number = jsonPath.getString("data.ddo_external_document_number");
        String ddo_external_document_date = jsonPath.getString("data.ddo_external_document_date");
        String note = jsonPath.getString("data.note");
        String short_description = jsonPath.getString("data.short_description");
        String signer_name = jsonPath.getString("data.signer_name");
        String signer_id = jsonPath.getString("data.signer_id");
        String signer_type = jsonPath.getString("data.signer_type");
        String came_from_name = jsonPath.getString("data.came_from_name");
        String came_from_id = jsonPath.getString("data.came_from_id");
        String came_from_type = jsonPath.getString("data.came_from_type");
        String recipient_name = jsonPath.getString("data.recipient_name");
        String recipient_id = jsonPath.getString("data.recipient_id");
        String recipient_type = jsonPath.getString("data.recipient_type");
        String resolution = jsonPath.getString("data.resolution");

        Assert.assertNotNull(uid);
        Assert.assertNotNull(provider_id);
        Assert.assertNotNull(journal_type);
        Assert.assertNull(single_number); // Equal null
        Assert.assertNotNull(reg_number);
        Assert.assertNotNull(sed_document_date);
        Assert.assertNotNull(sed_security_stamp);
        Assert.assertNull(external_document_number); // Equal null
        Assert.assertNull(external_reg_date); // Equal null
        Assert.assertNotNull(sed_document_type);
        Assert.assertNotNull(urgency); // Equal null
        Assert.assertNull(ddo_external_document_number); // Equal null
        Assert.assertNull(ddo_external_document_date); // Equal null
        Assert.assertNotNull(note);
        Assert.assertNotNull(short_description);
        Assert.assertNotNull(signer_name);
        Assert.assertNotNull(signer_id);
        Assert.assertNotNull(signer_type);
        Assert.assertNull(came_from_name); // Equal null
        Assert.assertNull(came_from_id); // Equal null
        Assert.assertNull(came_from_type); // Equal null
        Assert.assertNull(recipient_name); // Equal null
        Assert.assertNull(recipient_id); // Equal null
        Assert.assertNull(recipient_type); // Equal null
        Assert.assertNotNull(resolution);
    }

    @Test
    public void step06(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .get(API_DOCUMENTS_SED + String.format("/%s",uidOutgoingDocumentsRequiredFields))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String uid = jsonPath.getString("data.uid");
        String provider_id = jsonPath.getString("data.provider_id");
        String journal_type = jsonPath.getString("data.journal_type");
        String single_number = jsonPath.getString("data.single_number");
        String reg_number = jsonPath.getString("data.reg_number");
        String sed_document_date = jsonPath.getString("data.sed_document_date");
        String sed_security_stamp = jsonPath.getString("data.sed_security_stamp");
        String external_document_number = jsonPath.getString("data.external_document_number");
        String external_reg_date = jsonPath.getString("data.external_reg_date");
        String sed_document_type = jsonPath.getString("data.sed_document_type");
        String urgency = jsonPath.getString("data.urgency");
        String ddo_external_document_number = jsonPath.getString("data.ddo_external_document_number");
        String ddo_external_document_date = jsonPath.getString("data.ddo_external_document_date");
        String note = jsonPath.getString("data.note");
        String short_description = jsonPath.getString("data.short_description");
        String signer_name = jsonPath.getString("data.signer_name");
        String signer_id = jsonPath.getString("data.signer_id");
        String signer_type = jsonPath.getString("data.signer_type");
        String came_from_name = jsonPath.getString("data.came_from_name");
        String came_from_id = jsonPath.getString("data.came_from_id");
        String came_from_type = jsonPath.getString("data.came_from_type");
        String recipient_name = jsonPath.getString("data.recipient_name");
        String recipient_id = jsonPath.getString("data.recipient_id");
        String recipient_type = jsonPath.getString("data.recipient_type");
        String resolution = jsonPath.getString("data.resolution");

        Assert.assertNotNull(uid);
        Assert.assertNotNull(provider_id);
        Assert.assertNotNull(journal_type);
        Assert.assertNull(single_number); // Equal null
        Assert.assertNotNull(reg_number);
        Assert.assertNotNull(sed_document_date);
        Assert.assertNotNull(sed_security_stamp);
        Assert.assertNull(external_document_number); // Equal null
        Assert.assertNull(external_reg_date); // Equal null
        Assert.assertNotNull(sed_document_type);
        Assert.assertNull(urgency); // Equal null
        Assert.assertNull(ddo_external_document_number); // Equal null
        Assert.assertNull(ddo_external_document_date); // Equal null
        Assert.assertTrue(note.equals("")); // Equal null
        Assert.assertNotNull(short_description);
        Assert.assertNull(signer_name); // Equal null
        Assert.assertNull(signer_id); // Equal null
        Assert.assertNull(signer_type); // Equal null
        Assert.assertNull(came_from_name); // Equal null
        Assert.assertNull(came_from_id); // Equal null
        Assert.assertNull(came_from_type); // Equal null
        Assert.assertNull(recipient_name); // Equal null
        Assert.assertNull(recipient_id); // Equal null
        Assert.assertNull(recipient_type); // Equal null
        Assert.assertTrue(resolution.equals("")); // Equal null
    }

    @Test
    public void step07(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .get(API_DOCUMENTS_SED + String.format("/%s",uidSecretOutgoingDocumentsAllFields))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String uid = jsonPath.getString("data.uid");
        String provider_id = jsonPath.getString("data.provider_id");
        String journal_type = jsonPath.getString("data.journal_type");
        String single_number = jsonPath.getString("data.single_number");
        String reg_number = jsonPath.getString("data.reg_number");
        String sed_document_date = jsonPath.getString("data.sed_document_date");
        String sed_security_stamp = jsonPath.getString("data.sed_security_stamp");
        String external_document_number = jsonPath.getString("data.external_document_number");
        String external_reg_date = jsonPath.getString("data.external_reg_date");
        String sed_document_type = jsonPath.getString("data.sed_document_type");
        String urgency = jsonPath.getString("data.urgency");
        String ddo_external_document_number = jsonPath.getString("data.ddo_external_document_number");
        String ddo_external_document_date = jsonPath.getString("data.ddo_external_document_date");
        String note = jsonPath.getString("data.note");
        String short_description = jsonPath.getString("data.short_description");
        String signer_name = jsonPath.getString("data.signer_name");
        String signer_id = jsonPath.getString("data.signer_id");
        String signer_type = jsonPath.getString("data.signer_type");
        String came_from_name = jsonPath.getString("data.came_from_name");
        String came_from_id = jsonPath.getString("data.came_from_id");
        String came_from_type = jsonPath.getString("data.came_from_type");
        String recipient_name = jsonPath.getString("data.recipient_name");
        String recipient_id = jsonPath.getString("data.recipient_id");
        String recipient_type = jsonPath.getString("data.recipient_type");
        String resolution = jsonPath.getString("data.resolution");

        Assert.assertNotNull(uid);
        Assert.assertNotNull(provider_id);
        Assert.assertNotNull(journal_type);
        Assert.assertNull(single_number); // Equal null
        Assert.assertNotNull(reg_number);
        Assert.assertNotNull(sed_document_date);
        Assert.assertNotNull(sed_security_stamp);
        Assert.assertNull(external_document_number); // Equal null
        Assert.assertNull(external_reg_date); // Equal null
        Assert.assertNotNull(sed_document_type);
        Assert.assertNotNull(urgency);
        Assert.assertNull(ddo_external_document_number); // Equal null
        Assert.assertNull(ddo_external_document_date); // Equal null
        Assert.assertNotNull(note);
        Assert.assertNotNull(short_description);
        Assert.assertNotNull(signer_name);
        Assert.assertNotNull(signer_id);
        Assert.assertNotNull(signer_type);
        Assert.assertNull(came_from_name); // Equal null
        Assert.assertNull(came_from_id); // Equal null
        Assert.assertNull(came_from_type); // Equal null
        Assert.assertNull(recipient_name); // Equal null
        Assert.assertNull(recipient_id); // Equal null
        Assert.assertNull(recipient_type); // Equal null
        Assert.assertNotNull(resolution);
    }

    @Test
    public void step08(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .get(API_DOCUMENTS_SED + String.format("/%s",uidSecretOutgoingDocumentsRequiredFields))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String uid = jsonPath.getString("data.uid");
        String provider_id = jsonPath.getString("data.provider_id");
        String journal_type = jsonPath.getString("data.journal_type");
        String single_number = jsonPath.getString("data.single_number");
        String reg_number = jsonPath.getString("data.reg_number");
        String sed_document_date = jsonPath.getString("data.sed_document_date");
        String sed_security_stamp = jsonPath.getString("data.sed_security_stamp");
        String external_document_number = jsonPath.getString("data.external_document_number");
        String external_reg_date = jsonPath.getString("data.external_reg_date");
        String sed_document_type = jsonPath.getString("data.sed_document_type");
        String urgency = jsonPath.getString("data.urgency");
        String ddo_external_document_number = jsonPath.getString("data.ddo_external_document_number");
        String ddo_external_document_date = jsonPath.getString("data.ddo_external_document_date");
        String note = jsonPath.getString("data.note");
        String short_description = jsonPath.getString("data.short_description");
        String signer_name = jsonPath.getString("data.signer_name");
        String signer_id = jsonPath.getString("data.signer_id");
        String signer_type = jsonPath.getString("data.signer_type");
        String came_from_name = jsonPath.getString("data.came_from_name");
        String came_from_id = jsonPath.getString("data.came_from_id");
        String came_from_type = jsonPath.getString("data.came_from_type");
        String recipient_name = jsonPath.getString("data.recipient_name");
        String recipient_id = jsonPath.getString("data.recipient_id");
        String recipient_type = jsonPath.getString("data.recipient_type");
        String resolution = jsonPath.getString("data.resolution");

        Assert.assertNotNull(uid);
        Assert.assertNotNull(provider_id);
        Assert.assertNotNull(journal_type);
        Assert.assertNull(single_number); // Equal null
        Assert.assertNotNull(reg_number);
        Assert.assertNotNull(sed_document_date);
        Assert.assertNotNull(sed_security_stamp);
        Assert.assertNull(external_document_number); // Equal null
        Assert.assertNull(external_reg_date); // Equal null
        Assert.assertNotNull(sed_document_type);
        Assert.assertNull(urgency); // Equal null
        Assert.assertNull(ddo_external_document_number); // Equal null
        Assert.assertNull(ddo_external_document_date); // Equal null
        Assert.assertTrue(note.equals("")); // Equal null
        Assert.assertNotNull(short_description);
        Assert.assertNull(signer_name); // Equal null
        Assert.assertNull(signer_id); // Equal null
        Assert.assertNull(signer_type); // Equal null
        Assert.assertNull(came_from_name); // Equal null
        Assert.assertNull(came_from_id); // Equal null
        Assert.assertNull(came_from_type); // Equal null
        Assert.assertNull(recipient_name); // Equal null
        Assert.assertNull(recipient_id); // Equal null
        Assert.assertNull(recipient_type); // Equal null
        Assert.assertTrue(resolution.equals("")); // Equal null
    }

    @Test
    public void step09(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .get(API_DOCUMENTS_SED + String.format("/%s",uidOrdersAllFields))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String uid = jsonPath.getString("data.uid");
        String provider_id = jsonPath.getString("data.provider_id");
        String journal_type = jsonPath.getString("data.journal_type");
        String single_number = jsonPath.getString("data.single_number");
        String reg_number = jsonPath.getString("data.reg_number");
        String sed_document_date = jsonPath.getString("data.sed_document_date");
        String sed_security_stamp = jsonPath.getString("data.sed_security_stamp");
        String external_document_number = jsonPath.getString("data.external_document_number");
        String external_reg_date = jsonPath.getString("data.external_reg_date");
        String sed_document_type = jsonPath.getString("data.sed_document_type");
        String urgency = jsonPath.getString("data.urgency");
        String ddo_external_document_number = jsonPath.getString("data.ddo_external_document_number");
        String ddo_external_document_date = jsonPath.getString("data.ddo_external_document_date");
        String note = jsonPath.getString("data.note");
        String short_description = jsonPath.getString("data.short_description");
        String signer_name = jsonPath.getString("data.signer_name");
        String signer_id = jsonPath.getString("data.signer_id");
        String signer_type = jsonPath.getString("data.signer_type");
        String came_from_name = jsonPath.getString("data.came_from_name");
        String came_from_id = jsonPath.getString("data.came_from_id");
        String came_from_type = jsonPath.getString("data.came_from_type");
        String recipient_name = jsonPath.getString("data.recipient_name");
        String recipient_id = jsonPath.getString("data.recipient_id");
        String recipient_type = jsonPath.getString("data.recipient_type");
        String resolution = jsonPath.getString("data.resolution");

        Assert.assertNotNull(uid);
        Assert.assertNotNull(provider_id);
        Assert.assertNotNull(journal_type);
        Assert.assertNull(single_number); // Equal null
        Assert.assertNotNull(reg_number);
        Assert.assertNotNull(sed_document_date);
        Assert.assertNotNull(sed_security_stamp);
        Assert.assertNotNull(external_document_number);
        Assert.assertNull(external_reg_date); // Equal null
        Assert.assertNotNull(sed_document_type);
        Assert.assertNull(urgency); // Equal null
        Assert.assertNull(ddo_external_document_number); // Equal null
        Assert.assertNull(ddo_external_document_date); // Equal null
        Assert.assertNotNull(note);
        Assert.assertNotNull(short_description);
        Assert.assertNotNull(signer_name);
        Assert.assertNotNull(signer_id);
        Assert.assertNotNull(signer_type);
        Assert.assertNull(came_from_name); // Equal null
        Assert.assertNull(came_from_id); // Equal null
        Assert.assertNull(came_from_type); // Equal null
        Assert.assertNull(recipient_name); // Equal null
        Assert.assertNull(recipient_id); // Equal null
        Assert.assertNull(recipient_type); // Equal null
        Assert.assertNotNull(resolution);
    }

    @Test
    public void step10(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .get(API_DOCUMENTS_SED + String.format("/%s",uidOrdersRequiredFields))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String uid = jsonPath.getString("data.uid");
        String provider_id = jsonPath.getString("data.provider_id");
        String journal_type = jsonPath.getString("data.journal_type");
        String single_number = jsonPath.getString("data.single_number");
        String reg_number = jsonPath.getString("data.reg_number");
        String sed_document_date = jsonPath.getString("data.sed_document_date");
        String sed_security_stamp = jsonPath.getString("data.sed_security_stamp");
        String external_document_number = jsonPath.getString("data.external_document_number");
        String external_reg_date = jsonPath.getString("data.external_reg_date");
        String sed_document_type = jsonPath.getString("data.sed_document_type");
        String urgency = jsonPath.getString("data.urgency");
        String ddo_external_document_number = jsonPath.getString("data.ddo_external_document_number");
        String ddo_external_document_date = jsonPath.getString("data.ddo_external_document_date");
        String note = jsonPath.getString("data.note");
        String short_description = jsonPath.getString("data.short_description");
        String signer_name = jsonPath.getString("data.signer_name");
        String signer_id = jsonPath.getString("data.signer_id");
        String signer_type = jsonPath.getString("data.signer_type");
        String came_from_name = jsonPath.getString("data.came_from_name");
        String came_from_id = jsonPath.getString("data.came_from_id");
        String came_from_type = jsonPath.getString("data.came_from_type");
        String recipient_name = jsonPath.getString("data.recipient_name");
        String recipient_id = jsonPath.getString("data.recipient_id");
        String recipient_type = jsonPath.getString("data.recipient_type");
        String resolution = jsonPath.getString("data.resolution");

        Assert.assertNotNull(uid);
        Assert.assertNotNull(provider_id);
        Assert.assertNotNull(journal_type);
        Assert.assertNull(single_number); // Equal null
        Assert.assertNotNull(reg_number);
        Assert.assertNotNull(sed_document_date);
        Assert.assertNotNull(sed_security_stamp);
        Assert.assertTrue(external_document_number.equals(""));
        Assert.assertNull(external_reg_date); // Equal null
        Assert.assertNotNull(sed_document_type);
        Assert.assertNull(urgency); // Equal null
        Assert.assertNull(ddo_external_document_number); // Equal null
        Assert.assertNull(ddo_external_document_date); // Equal null
        Assert.assertTrue(note.equals(""));
        Assert.assertTrue(short_description.equals(""));
        Assert.assertTrue(signer_name.equals(""));
        Assert.assertTrue(signer_id.equals(""));
        Assert.assertTrue(signer_type.equals(""));
        Assert.assertNull(came_from_name); // Equal null
        Assert.assertNull(came_from_id); // Equal null
        Assert.assertNull(came_from_type); // Equal null
        Assert.assertNull(recipient_name); // Equal null
        Assert.assertNull(recipient_id); // Equal null
        Assert.assertNull(recipient_type); // Equal null
        Assert.assertTrue(resolution.equals(""));
    }

    @Test
    public void step11(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .get(API_DOCUMENTS_SED + String.format("/%s",uidSecretOrdersAllFields))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String uid = jsonPath.getString("data.uid");
        String provider_id = jsonPath.getString("data.provider_id");
        String journal_type = jsonPath.getString("data.journal_type");
        String single_number = jsonPath.getString("data.single_number");
        String reg_number = jsonPath.getString("data.reg_number");
        String sed_document_date = jsonPath.getString("data.sed_document_date");
        String sed_security_stamp = jsonPath.getString("data.sed_security_stamp");
        String external_document_number = jsonPath.getString("data.external_document_number");
        String external_reg_date = jsonPath.getString("data.external_reg_date");
        String sed_document_type = jsonPath.getString("data.sed_document_type");
        String urgency = jsonPath.getString("data.urgency");
        String ddo_external_document_number = jsonPath.getString("data.ddo_external_document_number");
        String ddo_external_document_date = jsonPath.getString("data.ddo_external_document_date");
        String note = jsonPath.getString("data.note");
        String short_description = jsonPath.getString("data.short_description");
        String signer_name = jsonPath.getString("data.signer_name");
        String signer_id = jsonPath.getString("data.signer_id");
        String signer_type = jsonPath.getString("data.signer_type");
        String came_from_name = jsonPath.getString("data.came_from_name");
        String came_from_id = jsonPath.getString("data.came_from_id");
        String came_from_type = jsonPath.getString("data.came_from_type");
        String recipient_name = jsonPath.getString("data.recipient_name");
        String recipient_id = jsonPath.getString("data.recipient_id");
        String recipient_type = jsonPath.getString("data.recipient_type");
        String resolution = jsonPath.getString("data.resolution");

        Assert.assertNotNull(uid);
        Assert.assertNotNull(provider_id);
        Assert.assertNotNull(journal_type);
        Assert.assertNull(single_number); // Equal null
        Assert.assertNotNull(reg_number);
        Assert.assertNotNull(sed_document_date);
        Assert.assertNotNull(sed_security_stamp);
        Assert.assertNotNull(external_document_number);
        Assert.assertNull(external_reg_date); // Equal null
        Assert.assertNotNull(sed_document_type);
        Assert.assertNull(urgency); // Equal null
        Assert.assertNull(ddo_external_document_number); // Equal null
        Assert.assertNull(ddo_external_document_date); // Equal null
        Assert.assertNotNull(note);
        Assert.assertNotNull(short_description);
        Assert.assertNotNull(signer_name);
        Assert.assertNotNull(signer_id);
        Assert.assertNotNull(signer_type);
        Assert.assertNull(came_from_name); // Equal null
        Assert.assertNull(came_from_id); // Equal null
        Assert.assertNull(came_from_type); // Equal null
        Assert.assertNull(recipient_name); // Equal null
        Assert.assertNull(recipient_id); // Equal null
        Assert.assertNull(recipient_type); // Equal null
        Assert.assertNotNull(resolution);
    }

    @Test
    public void step12(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .get(API_DOCUMENTS_SED + String.format("/%s",uidSecretOrdersRequiredFields))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String uid = jsonPath.getString("data.uid");
        String provider_id = jsonPath.getString("data.provider_id");
        String journal_type = jsonPath.getString("data.journal_type");
        String single_number = jsonPath.getString("data.single_number");
        String reg_number = jsonPath.getString("data.reg_number");
        String sed_document_date = jsonPath.getString("data.sed_document_date");
        String sed_security_stamp = jsonPath.getString("data.sed_security_stamp");
        String external_document_number = jsonPath.getString("data.external_document_number");
        String external_reg_date = jsonPath.getString("data.external_reg_date");
        String sed_document_type = jsonPath.getString("data.sed_document_type");
        String urgency = jsonPath.getString("data.urgency");
        String ddo_external_document_number = jsonPath.getString("data.ddo_external_document_number");
        String ddo_external_document_date = jsonPath.getString("data.ddo_external_document_date");
        String note = jsonPath.getString("data.note");
        String short_description = jsonPath.getString("data.short_description");
        String signer_name = jsonPath.getString("data.signer_name");
        String signer_id = jsonPath.getString("data.signer_id");
        String signer_type = jsonPath.getString("data.signer_type");
        String came_from_name = jsonPath.getString("data.came_from_name");
        String came_from_id = jsonPath.getString("data.came_from_id");
        String came_from_type = jsonPath.getString("data.came_from_type");
        String recipient_name = jsonPath.getString("data.recipient_name");
        String recipient_id = jsonPath.getString("data.recipient_id");
        String recipient_type = jsonPath.getString("data.recipient_type");
        String resolution = jsonPath.getString("data.resolution");

        Assert.assertNotNull(uid);
        Assert.assertNotNull(provider_id);
        Assert.assertNotNull(journal_type);
        Assert.assertNull(single_number); // Equal null
        Assert.assertNotNull(reg_number);
        Assert.assertNotNull(sed_document_date);
        Assert.assertNotNull(sed_security_stamp);
        Assert.assertTrue(external_document_number.equals(""));
        Assert.assertNull(external_reg_date); // Equal null
        Assert.assertNotNull(sed_document_type);
        Assert.assertNull(urgency); // Equal null
        Assert.assertNull(ddo_external_document_number); // Equal null
        Assert.assertNull(ddo_external_document_date); // Equal null
        Assert.assertTrue(note.equals(""));
        Assert.assertTrue(short_description.equals(""));
        Assert.assertTrue(signer_name.equals(""));
        Assert.assertTrue(signer_id.equals(""));
        Assert.assertTrue(signer_type.equals(""));
        Assert.assertNull(came_from_name); // Equal null
        Assert.assertNull(came_from_id); // Equal null
        Assert.assertNull(came_from_type); // Equal null
        Assert.assertNull(recipient_name); // Equal null
        Assert.assertNull(recipient_id); // Equal null
        Assert.assertNull(recipient_type); // Equal null
        Assert.assertTrue(resolution.equals(""));
    }

    @Test
    public void step13(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .get(API_DOCUMENTS_SED + String.format("/%s",uidOrdersDdoAllFields))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String uid = jsonPath.getString("data.uid");
        String provider_id = jsonPath.getString("data.provider_id");
        String journal_type = jsonPath.getString("data.journal_type");
        String single_number = jsonPath.getString("data.single_number");
        String reg_number = jsonPath.getString("data.reg_number");
        String sed_document_date = jsonPath.getString("data.sed_document_date");
        String sed_security_stamp = jsonPath.getString("data.sed_security_stamp");
        String external_document_number = jsonPath.getString("data.external_document_number");
        String external_reg_date = jsonPath.getString("data.external_reg_date");
        String sed_document_type = jsonPath.getString("data.sed_document_type");
        String urgency = jsonPath.getString("data.urgency");
        String ddo_external_document_number = jsonPath.getString("data.ddo_external_document_number");
        String ddo_external_document_date = jsonPath.getString("data.ddo_external_document_date");
        String note = jsonPath.getString("data.note");
        String short_description = jsonPath.getString("data.short_description");
        String signer_name = jsonPath.getString("data.signer_name");
        String signer_id = jsonPath.getString("data.signer_id");
        String signer_type = jsonPath.getString("data.signer_type");
        String came_from_name = jsonPath.getString("data.came_from_name");
        String came_from_id = jsonPath.getString("data.came_from_id");
        String came_from_type = jsonPath.getString("data.came_from_type");
        String recipient_name = jsonPath.getString("data.recipient_name");
        String recipient_id = jsonPath.getString("data.recipient_id");
        String recipient_type = jsonPath.getString("data.recipient_type");
        String resolution = jsonPath.getString("data.resolution");

        Assert.assertNotNull(uid);
        Assert.assertNotNull(provider_id);
        Assert.assertNotNull(journal_type);
        Assert.assertNull(single_number); // Equal null
        Assert.assertNotNull(reg_number);
        Assert.assertNotNull(sed_document_date);
        Assert.assertNotNull(sed_security_stamp);
        Assert.assertNull(external_document_number); // Equal null
        Assert.assertNull(external_reg_date); // Equal null
        Assert.assertNotNull(sed_document_type);
        Assert.assertNull(urgency); // Equal null
        Assert.assertNull(ddo_external_document_number); // Equal null
        Assert.assertNull(ddo_external_document_date); // Equal null
        Assert.assertNotNull(note);
        Assert.assertNotNull(short_description);
        Assert.assertNotNull(signer_name);
        Assert.assertNotNull(signer_id);
        Assert.assertNotNull(signer_type);
        Assert.assertNull(came_from_name); // Equal null
        Assert.assertNull(came_from_id); // Equal null
        Assert.assertNull(came_from_type); // Equal null
        Assert.assertNull(recipient_name); // Equal null
        Assert.assertNull(recipient_id); // Equal null
        Assert.assertNull(recipient_type); // Equal null
        Assert.assertNotNull(resolution);
    }

    @Test
    public void step14(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .get(API_DOCUMENTS_SED + String.format("/%s",uidOrdersDdoRequiredFields))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String uid = jsonPath.getString("data.uid");
        String provider_id = jsonPath.getString("data.provider_id");
        String journal_type = jsonPath.getString("data.journal_type");
        String single_number = jsonPath.getString("data.single_number");
        String reg_number = jsonPath.getString("data.reg_number");
        String sed_document_date = jsonPath.getString("data.sed_document_date");
        String sed_security_stamp = jsonPath.getString("data.sed_security_stamp");
        String external_document_number = jsonPath.getString("data.external_document_number");
        String external_reg_date = jsonPath.getString("data.external_reg_date");
        String sed_document_type = jsonPath.getString("data.sed_document_type");
        String urgency = jsonPath.getString("data.urgency");
        String ddo_external_document_number = jsonPath.getString("data.ddo_external_document_number");
        String ddo_external_document_date = jsonPath.getString("data.ddo_external_document_date");
        String note = jsonPath.getString("data.note");
        String short_description = jsonPath.getString("data.short_description");
        String signer_name = jsonPath.getString("data.signer_name");
        String signer_id = jsonPath.getString("data.signer_id");
        String signer_type = jsonPath.getString("data.signer_type");
        String came_from_name = jsonPath.getString("data.came_from_name");
        String came_from_id = jsonPath.getString("data.came_from_id");
        String came_from_type = jsonPath.getString("data.came_from_type");
        String recipient_name = jsonPath.getString("data.recipient_name");
        String recipient_id = jsonPath.getString("data.recipient_id");
        String recipient_type = jsonPath.getString("data.recipient_type");
        String resolution = jsonPath.getString("data.resolution");

        Assert.assertNotNull(uid);
        Assert.assertNotNull(provider_id);
        Assert.assertNotNull(journal_type);
        Assert.assertNull(single_number); // Equal null
        Assert.assertNotNull(reg_number);
        Assert.assertNotNull(sed_document_date);
        Assert.assertNotNull(sed_security_stamp);
        Assert.assertNull(external_document_number); // Equal null
        Assert.assertNull(external_reg_date); // Equal null
        Assert.assertNotNull(sed_document_type);
        Assert.assertNull(urgency); // Equal null
        Assert.assertNull(ddo_external_document_number); // Equal null
        Assert.assertNull(ddo_external_document_date); // Equal null
        Assert.assertTrue(note.equals(""));
        Assert.assertTrue(short_description.equals(""));
        Assert.assertTrue(signer_name.equals(""));
        Assert.assertTrue(signer_id.equals(""));
        Assert.assertTrue(signer_type.equals(""));
        Assert.assertNull(came_from_name); // Equal null
        Assert.assertNull(came_from_id); // Equal null
        Assert.assertNull(came_from_type); // Equal null
        Assert.assertNull(recipient_name); // Equal null
        Assert.assertNull(recipient_id); // Equal null
        Assert.assertNull(recipient_type); // Equal null
        Assert.assertTrue(resolution.equals(""));
    }

    @Test
    public void step15(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .get(API_DOCUMENTS_SED + String.format("/%s",uidIncomingOrdersAllFields))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String uid = jsonPath.getString("data.uid");
        String provider_id = jsonPath.getString("data.provider_id");
        String journal_type = jsonPath.getString("data.journal_type");
        String single_number = jsonPath.getString("data.single_number");
        String reg_number = jsonPath.getString("data.reg_number");
        String sed_document_date = jsonPath.getString("data.sed_document_date");
        String sed_security_stamp = jsonPath.getString("data.sed_security_stamp");
        String external_document_number = jsonPath.getString("data.external_document_number");
        String external_reg_date = jsonPath.getString("data.external_reg_date");
        String sed_document_type = jsonPath.getString("data.sed_document_type");
        String urgency = jsonPath.getString("data.urgency");
        String ddo_external_document_number = jsonPath.getString("data.ddo_external_document_number");
        String ddo_external_document_date = jsonPath.getString("data.ddo_external_document_date");
        String note = jsonPath.getString("data.note");
        String short_description = jsonPath.getString("data.short_description");
        String signer_name = jsonPath.getString("data.signer_name");
        String signer_id = jsonPath.getString("data.signer_id");
        String signer_type = jsonPath.getString("data.signer_type");
        String came_from_name = jsonPath.getString("data.came_from_name");
        String came_from_id = jsonPath.getString("data.came_from_id");
        String came_from_type = jsonPath.getString("data.came_from_type");
        String recipient_name = jsonPath.getString("data.recipient_name");
        String recipient_id = jsonPath.getString("data.recipient_id");
        String recipient_type = jsonPath.getString("data.recipient_type");
        String resolution = jsonPath.getString("data.resolution");

        Assert.assertNotNull(uid);
        Assert.assertNotNull(provider_id);
        Assert.assertNotNull(journal_type);
        Assert.assertNull(single_number); // Equal null
        Assert.assertNotNull(reg_number);
        Assert.assertNotNull(sed_document_date);
        Assert.assertNotNull(sed_security_stamp);
        Assert.assertNotNull(external_document_number);
        Assert.assertNotNull(external_reg_date);
        Assert.assertNotNull(sed_document_type);
        Assert.assertNull(urgency); // Equal null
        Assert.assertNotNull(ddo_external_document_number);
        Assert.assertNotNull(ddo_external_document_date);
        Assert.assertNotNull(note);
        Assert.assertNotNull(short_description);
        Assert.assertNotNull(signer_name);
        Assert.assertNotNull(signer_id);
        Assert.assertNotNull(signer_type);
        Assert.assertNull(came_from_name); // Equal null
        Assert.assertNull(came_from_id); // Equal null
        Assert.assertNull(came_from_type); // Equal null
        Assert.assertNull(recipient_name); // Equal null
        Assert.assertNull(recipient_id); // Equal null
        Assert.assertNull(recipient_type); // Equal null
        Assert.assertNotNull(resolution);
    }

    @Test
    public void step16(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .get(API_DOCUMENTS_SED + String.format("/%s",uidIncomingOrdersRequiredFields))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String uid = jsonPath.getString("data.uid");
        String provider_id = jsonPath.getString("data.provider_id");
        String journal_type = jsonPath.getString("data.journal_type");
        String single_number = jsonPath.getString("data.single_number");
        String reg_number = jsonPath.getString("data.reg_number");
        String sed_document_date = jsonPath.getString("data.sed_document_date");
        String sed_security_stamp = jsonPath.getString("data.sed_security_stamp");
        String external_document_number = jsonPath.getString("data.external_document_number");
        String external_reg_date = jsonPath.getString("data.external_reg_date");
        String sed_document_type = jsonPath.getString("data.sed_document_type");
        String urgency = jsonPath.getString("data.urgency");
        String ddo_external_document_number = jsonPath.getString("data.ddo_external_document_number");
        String ddo_external_document_date = jsonPath.getString("data.ddo_external_document_date");
        String note = jsonPath.getString("data.note");
        String short_description = jsonPath.getString("data.short_description");
        String signer_name = jsonPath.getString("data.signer_name");
        String signer_id = jsonPath.getString("data.signer_id");
        String signer_type = jsonPath.getString("data.signer_type");
        String came_from_name = jsonPath.getString("data.came_from_name");
        String came_from_id = jsonPath.getString("data.came_from_id");
        String came_from_type = jsonPath.getString("data.came_from_type");
        String recipient_name = jsonPath.getString("data.recipient_name");
        String recipient_id = jsonPath.getString("data.recipient_id");
        String recipient_type = jsonPath.getString("data.recipient_type");
        String resolution = jsonPath.getString("data.resolution");

        Assert.assertNotNull(uid);
        Assert.assertNotNull(provider_id);
        Assert.assertNotNull(journal_type);
        Assert.assertNull(single_number); // Equal null
        Assert.assertNotNull(reg_number);
        Assert.assertNotNull(sed_document_date);
        Assert.assertNotNull(sed_security_stamp);
        Assert.assertNotNull(external_document_number);
        Assert.assertNotNull(external_reg_date);
        Assert.assertNotNull(sed_document_type);
        Assert.assertNull(urgency); // Equal null
        Assert.assertTrue(ddo_external_document_number.equals("")); // Equal null
        Assert.assertNull(ddo_external_document_date); // Equal null
        Assert.assertTrue(note.equals("")); // Equal null
        Assert.assertTrue(short_description.equals("")); // Equal null
        Assert.assertTrue(signer_name.equals("")); // Equal null
        Assert.assertTrue(signer_id.equals("")); // Equal null
        Assert.assertTrue(signer_type.equals("")); // Equal null
        Assert.assertNull(came_from_name); // Equal null
        Assert.assertNull(came_from_id); // Equal null
        Assert.assertNull(came_from_type); // Equal null
        Assert.assertNull(recipient_name); // Equal null
        Assert.assertNull(recipient_id); // Equal null
        Assert.assertNull(recipient_type); // Equal null
        Assert.assertTrue(resolution.equals("")); // Equal null
    }
    @Test
    public void step17(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .get(API_DOCUMENTS_SED + String.format("/%s",uidSecretIncomingOrdersAllFields))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String uid = jsonPath.getString("data.uid");
        String provider_id = jsonPath.getString("data.provider_id");
        String journal_type = jsonPath.getString("data.journal_type");
        String single_number = jsonPath.getString("data.single_number");
        String reg_number = jsonPath.getString("data.reg_number");
        String sed_document_date = jsonPath.getString("data.sed_document_date");
        String sed_security_stamp = jsonPath.getString("data.sed_security_stamp");
        String external_document_number = jsonPath.getString("data.external_document_number");
        String external_reg_date = jsonPath.getString("data.external_reg_date");
        String sed_document_type = jsonPath.getString("data.sed_document_type");
        String urgency = jsonPath.getString("data.urgency");
        String ddo_external_document_number = jsonPath.getString("data.ddo_external_document_number");
        String ddo_external_document_date = jsonPath.getString("data.ddo_external_document_date");
        String note = jsonPath.getString("data.note");
        String short_description = jsonPath.getString("data.short_description");
        String signer_name = jsonPath.getString("data.signer_name");
        String signer_id = jsonPath.getString("data.signer_id");
        String signer_type = jsonPath.getString("data.signer_type");
        String came_from_name = jsonPath.getString("data.came_from_name");
        String came_from_id = jsonPath.getString("data.came_from_id");
        String came_from_type = jsonPath.getString("data.came_from_type");
        String recipient_name = jsonPath.getString("data.recipient_name");
        String recipient_id = jsonPath.getString("data.recipient_id");
        String recipient_type = jsonPath.getString("data.recipient_type");
        String resolution = jsonPath.getString("data.resolution");

        Assert.assertNotNull(uid);
        Assert.assertNotNull(provider_id);
        Assert.assertNotNull(journal_type);
        Assert.assertNull(single_number); // Equal null
        Assert.assertNotNull(reg_number);
        Assert.assertNotNull(sed_document_date);
        Assert.assertNotNull(sed_security_stamp);
        Assert.assertNotNull(external_document_number);
        Assert.assertNotNull(external_reg_date);
        Assert.assertNotNull(sed_document_type);
        Assert.assertNull(urgency); // Equal null
        Assert.assertTrue(ddo_external_document_number.equals("")); // Equal null
        Assert.assertNull(ddo_external_document_date);  // Equal null
        Assert.assertNotNull(note);
        Assert.assertNotNull(short_description);
        Assert.assertNotNull(signer_name);
        Assert.assertNotNull(signer_id);
        Assert.assertNotNull(signer_type);
        Assert.assertNull(came_from_name); // Equal null
        Assert.assertNull(came_from_id); // Equal null
        Assert.assertNull(came_from_type); // Equal null
        Assert.assertNull(recipient_name); // Equal null
        Assert.assertNull(recipient_id); // Equal null
        Assert.assertNull(recipient_type); // Equal null
        Assert.assertNotNull(resolution);
    }

    @Test
    public void step18(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .get(API_DOCUMENTS_SED + String.format("/%s",uidSecretIncomingOrdersRequiredFields))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String uid = jsonPath.getString("data.uid");
        String provider_id = jsonPath.getString("data.provider_id");
        String journal_type = jsonPath.getString("data.journal_type");
        String single_number = jsonPath.getString("data.single_number");
        String reg_number = jsonPath.getString("data.reg_number");
        String sed_document_date = jsonPath.getString("data.sed_document_date");
        String sed_security_stamp = jsonPath.getString("data.sed_security_stamp");
        String external_document_number = jsonPath.getString("data.external_document_number");
        String external_reg_date = jsonPath.getString("data.external_reg_date");
        String sed_document_type = jsonPath.getString("data.sed_document_type");
        String urgency = jsonPath.getString("data.urgency");
        String ddo_external_document_number = jsonPath.getString("data.ddo_external_document_number");
        String ddo_external_document_date = jsonPath.getString("data.ddo_external_document_date");
        String note = jsonPath.getString("data.note");
        String short_description = jsonPath.getString("data.short_description");
        String signer_name = jsonPath.getString("data.signer_name");
        String signer_id = jsonPath.getString("data.signer_id");
        String signer_type = jsonPath.getString("data.signer_type");
        String came_from_name = jsonPath.getString("data.came_from_name");
        String came_from_id = jsonPath.getString("data.came_from_id");
        String came_from_type = jsonPath.getString("data.came_from_type");
        String recipient_name = jsonPath.getString("data.recipient_name");
        String recipient_id = jsonPath.getString("data.recipient_id");
        String recipient_type = jsonPath.getString("data.recipient_type");
        String resolution = jsonPath.getString("data.resolution");

        Assert.assertNotNull(uid);
        Assert.assertNotNull(provider_id);
        Assert.assertNotNull(journal_type);
        Assert.assertNull(single_number); // Equal null
        Assert.assertNotNull(reg_number);
        Assert.assertNotNull(sed_document_date);
        Assert.assertNotNull(sed_security_stamp);
        Assert.assertNotNull(external_document_number);
        Assert.assertNotNull(external_reg_date);
        Assert.assertNotNull(sed_document_type);
        Assert.assertNull(urgency); // Equal null
        Assert.assertTrue(ddo_external_document_number.equals("")); // Equal null
        Assert.assertNull(ddo_external_document_date); // Equal null
        Assert.assertTrue(note.equals("")); // Equal null
        Assert.assertTrue(short_description.equals("")); // Equal null
        Assert.assertTrue(signer_name.equals("")); // Equal null
        Assert.assertTrue(signer_id.equals("")); // Equal null
        Assert.assertTrue(signer_type.equals("")); // Equal null
        Assert.assertNull(came_from_name); // Equal null
        Assert.assertNull(came_from_id); // Equal null
        Assert.assertNull(came_from_type); // Equal null
        Assert.assertNull(recipient_name); // Equal null
        Assert.assertNull(recipient_id); // Equal null
        Assert.assertNull(recipient_type); // Equal null
        Assert.assertTrue(resolution.equals("")); // Equal null
    }

    @Test
    public void step19(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .get(API_DOCUMENTS_SED + String.format("/%s",uidCitizenRequestsAllFields))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String uid = jsonPath.getString("data.uid");
        String provider_id = jsonPath.getString("data.provider_id");
        String journal_type = jsonPath.getString("data.journal_type");
        String single_number = jsonPath.getString("data.single_number");
        String reg_number = jsonPath.getString("data.reg_number");
        String sed_document_date = jsonPath.getString("data.sed_document_date");
        String sed_security_stamp = jsonPath.getString("data.sed_security_stamp");
        String external_document_number = jsonPath.getString("data.external_document_number");
        String external_reg_date = jsonPath.getString("data.external_reg_date");
        String sed_document_type = jsonPath.getString("data.sed_document_type");
        String urgency = jsonPath.getString("data.urgency");
        String ddo_external_document_number = jsonPath.getString("data.ddo_external_document_number");
        String ddo_external_document_date = jsonPath.getString("data.ddo_external_document_date");
        String note = jsonPath.getString("data.note");
        String short_description = jsonPath.getString("data.short_description");
        String signer_name = jsonPath.getString("data.signer_name");
        String signer_id = jsonPath.getString("data.signer_id");
        String signer_type = jsonPath.getString("data.signer_type");
        String came_from_name = jsonPath.getString("data.came_from_name");
        String came_from_id = jsonPath.getString("data.came_from_id");
        String came_from_type = jsonPath.getString("data.came_from_type");
        String recipient_name = jsonPath.getString("data.recipient_name");
        String recipient_id = jsonPath.getString("data.recipient_id");
        String recipient_type = jsonPath.getString("data.recipient_type");
        String resolution = jsonPath.getString("data.resolution");

        Assert.assertNotNull(uid);
        Assert.assertNotNull(provider_id);
        Assert.assertNotNull(journal_type);
        Assert.assertNull(single_number); // Equal null
        Assert.assertNotNull(reg_number);
        Assert.assertNotNull(sed_document_date);
        Assert.assertNotNull(sed_security_stamp);
        Assert.assertNotNull(external_document_number);
        Assert.assertNotNull(external_reg_date);
        Assert.assertNull(sed_document_type);  // Equal null (!)
        Assert.assertNotNull(urgency);
        Assert.assertNull(ddo_external_document_number); // Equal null
        Assert.assertNull(ddo_external_document_date);  // Equal null
        Assert.assertNull(note);  // Equal null (!)
        Assert.assertNull(short_description);  // Equal null (!)
        Assert.assertNull(signer_name);  // Equal null
        Assert.assertNull(signer_id);  // Equal null
        Assert.assertNull(signer_type);  // Equal null
        Assert.assertNotNull(came_from_name);
        Assert.assertNotNull(came_from_id);
        Assert.assertNotNull(came_from_type);
        Assert.assertNotNull(recipient_name);
        Assert.assertNotNull(recipient_id);
        Assert.assertNotNull(recipient_type);
        Assert.assertNotNull(resolution);
    }

    @Test
    public void step20(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .get(API_DOCUMENTS_SED + String.format("/%s",uidCitizenRequestsRequiredFields))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String uid = jsonPath.getString("data.uid");
        String provider_id = jsonPath.getString("data.provider_id");
        String journal_type = jsonPath.getString("data.journal_type");
        String single_number = jsonPath.getString("data.single_number");
        String reg_number = jsonPath.getString("data.reg_number");
        String sed_document_date = jsonPath.getString("data.sed_document_date");
        String sed_security_stamp = jsonPath.getString("data.sed_security_stamp");
        String external_document_number = jsonPath.getString("data.external_document_number");
        String external_reg_date = jsonPath.getString("data.external_reg_date");
        String sed_document_type = jsonPath.getString("data.sed_document_type");
        String urgency = jsonPath.getString("data.urgency");
        String ddo_external_document_number = jsonPath.getString("data.ddo_external_document_number");
        String ddo_external_document_date = jsonPath.getString("data.ddo_external_document_date");
        String note = jsonPath.getString("data.note");
        String short_description = jsonPath.getString("data.short_description");
        String signer_name = jsonPath.getString("data.signer_name");
        String signer_id = jsonPath.getString("data.signer_id");
        String signer_type = jsonPath.getString("data.signer_type");
        String came_from_name = jsonPath.getString("data.came_from_name");
        String came_from_id = jsonPath.getString("data.came_from_id");
        String came_from_type = jsonPath.getString("data.came_from_type");
        String recipient_name = jsonPath.getString("data.recipient_name");
        String recipient_id = jsonPath.getString("data.recipient_id");
        String recipient_type = jsonPath.getString("data.recipient_type");
        String resolution = jsonPath.getString("data.resolution");

        Assert.assertNotNull(uid);
        Assert.assertNotNull(provider_id);
        Assert.assertNotNull(journal_type);
        Assert.assertNull(single_number); // Equal null
        Assert.assertNotNull(reg_number);
        Assert.assertNotNull(sed_document_date);
        Assert.assertNotNull(sed_security_stamp);
        Assert.assertTrue(external_document_number.equals("")); // Equal null
        Assert.assertNull(external_reg_date); // Equal null
        Assert.assertNull(sed_document_type); // Equal null
        Assert.assertNull(urgency); // Equal null
        Assert.assertNull(ddo_external_document_number); // Equal null
        Assert.assertNull(ddo_external_document_date); // Equal null
        Assert.assertNull(note); // Equal null
        Assert.assertNull(short_description); // Equal null
        Assert.assertNull(signer_name); // Equal null
        Assert.assertNull(signer_id); // Equal null
        Assert.assertNull(signer_type); // Equal null
        Assert.assertTrue(came_from_name.equals("  ")); // Equal null
        Assert.assertNull(came_from_id); // Equal null
        Assert.assertNull(came_from_type); // Equal null
        Assert.assertNotNull(recipient_name);
        Assert.assertNotNull(recipient_id);
        Assert.assertNotNull(recipient_type);
        Assert.assertTrue(resolution.equals("")); // Equal null
    }

    @Test
    public void step21(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdTwo)
                .get(API_DOCUMENTS_SED + String.format("/%s",uidCitizenRequestsRequiredFields))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("error");

        System.out.println("Error message : " + errorMessage);

        Assert.assertTrue(errorMessage.equals("the server responded with status 404"));
    }

    @Test
    public void step22(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .get(API_DOCUMENTS_SED +
                        String.format("/%s","dd36b95d945575e24776718625175f2e84dfb680fcec0aa00a5038aaf0ebf4c7"))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("error");

        System.out.println("Error message : " + errorMessage);

        Assert.assertTrue(errorMessage.equals("the server responded with status 404"));
    }

    @Test
    public void step23(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .get(API_DOCUMENTS_SED + String.format("/%s",uidAttorneyLetters))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("error");

        System.out.println("Error message : " + errorMessage);

        Assert.assertTrue(errorMessage.equals("the server responded with status 500"));
    }
}

package SKIP_API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.*;

import static io.restassured.RestAssured.given;

public class SKIP_129 {

    private static String API_USERS = "http://api.skip.rtech.ru/v1/permissions/users";
    private static String API_DOCUMENTS = "http://api.skip.rtech.ru/v1/documents";
    public static String API_PROVIDERS = "http://api.skip.rtech.ru/v1/permissions/providers";
    public static String API_DOCUMENT_EXECUTION_STATES = "http://api.skip.rtech.ru/v1/classifiers/document_execution_states";
    public static String API_DOC_TYPE = "http://api.skip.rtech.ru/v1/classifiers/document_types";
    public static String API_CONTROL_SUBJECTS = "http://api.skip.rtech.ru/v1/permissions/control_subjects";
    public int[] role_ids = {29};
    String providerIdUserTwentySeven = "659e9ae2dc94000006000001";

    @BeforeTest
    public void setup(){
        // Provider with id - "659e9ae2dc94000006000001" has a users with id = 27, 29
        // Get list of providers
        Response response = given()
                .when()
                .params("items","2000")
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .get(API_PROVIDERS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        // Store all provider's id in a string
        String providersId = jsonPath.getString("data.id");
//        System.out.println(providersId);
        // Create a boolean variable and check condition
        // that provider with users id = 27, 29 existed
        boolean providerIsFound = false;
        if (providersId.contains(providerIdUserTwentySeven)){
            providerIsFound = true;
        } else {
            System.out.println("Provider not found!");
        }
        // If provider existed try to check that user with id 27 existed
        if (providerIsFound) {
            Response responseUser = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", 29)
                    .get(API_USERS)
                    .then().log().all()
                    .extract().response();

            JsonPath jsonPathUser = responseUser.jsonPath();

            String dataUser = jsonPathUser.getString("data.id");
            // System.out.println(dataUser);
            if (dataUser.contains("27")){
                System.out.println("User with id 27 already existed!");
            } else {
                System.out.println("User with id 27 doesn't exist.");
                Map<String,Object> requestBody = new HashMap<>();
                requestBody.put("official_id","5b3e9c111ec7000027000001");
                requestBody.put("role_ids",role_ids);
                Response responseUserCreate = given()
                        .when()
                        .header("Content-Type", "application/json")
                        .header("Test-Authorization", 29)
                        .body(requestBody)
                        .post(API_USERS)
                        .then().log().all()
                        .extract().response();

                JsonPath jsonPathUserCreate = responseUserCreate.jsonPath();

                String dataUserCreate = jsonPathUserCreate.getString("data.id");
                System.out.println("User with id -" + dataUserCreate + "was created!");
            }
        }
        // Check that control subject with id 122 existed
        Response responseControlSubject = given()
                .when()
                .params("provider_id","525e9f767da3000002000001")
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .get(API_CONTROL_SUBJECTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathControlSubjects = responseControlSubject.jsonPath();

        String dataControlSubjects = jsonPathControlSubjects.getString("data.id");
        //System.out.println(dataControlSubjects);
        if (dataControlSubjects.contains("122")){
            System.out.println("Control subject with id = 122 already existed!");
        } else {
            System.out.println("Control subject with id = 122 doesn't exist.");
            String requestBody = String.format(
                    "{\"control_subjects\":[{\"name\": \"%s\",\"fax_number\":\"%s\",\"deleted\":%s,\"provider_id\":\"%s\"}]}"
                    , "!!!!$$$$78Auto", "4", false, "525e9f767da3000002000001");
            Response responseControlSubjectCreate = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", 1)
                    .body(requestBody)
                    .put(API_CONTROL_SUBJECTS)
                    .then().log().all()
                    .extract().response();

            JsonPath jsonPathControlSubjectsCreate = responseControlSubjectCreate.jsonPath();

        }
        // Check that element "document execution state" with id 2 existed
        Response responseDocumentExecutionState = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .get(API_DOCUMENT_EXECUTION_STATES)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathDocumentExecutionState = responseDocumentExecutionState.jsonPath();

        String dataDocumentExecutionState = jsonPathDocumentExecutionState.getString("data.id");
        //System.out.println(dataDocumentExecutionState);
        if (dataDocumentExecutionState.contains("2")){
            System.out.println("Document execution state with id = 2 already existed!");
        } else {
            System.out.println("Document execution state with id = 2 doesn't exist.");
            Map<String,String> requestBody = new HashMap<>();
            requestBody.put("name","7");
            requestBody.put("short_name","тест");
            requestBody.put("excluded","false");
            Response responseDocumentExecutionStateCreate = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", 1)
                    .body(requestBody)
                    .post(API_DOCUMENT_EXECUTION_STATES)
                    .then().log().all()
                    .extract().response();

            JsonPath jsonPathDocumentExecutionStateCreate = responseDocumentExecutionStateCreate.jsonPath();
        }
        // Check that element "document type" with id 2 existed
        Response responseDocumentType = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .get(API_DOC_TYPE)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathDocumentType = responseDocumentType.jsonPath();
        // Maybe I should create array of strings or integers
        List<Integer> idDocTypes = jsonPathDocumentType.getList("data.id");
        //System.out.println(idDocTypes);
        for (Integer element : idDocTypes) {
            System.out.println(element);
        }
        String dataDocumentType = jsonPathDocumentType.getString("data.id");
        System.out.println(dataDocumentType);
        if (dataDocumentType.equals("1")){
            System.out.println("Document type with id = 1 already existed!");
        } else {
            System.out.println("Document type with id = 1 doesn't existed!");
            Map<String,Object> requestBody = new HashMap<>();
            requestBody.put("name","ю");
            requestBody.put("short_name","ю");
            requestBody.put("internal", false);
            requestBody.put("genitive_name","ю");
            requestBody.put("excluded", false);
            Response responseDocumentTypeCreate = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", 1)
                    .body(requestBody)
                    .post(API_DOC_TYPE)
                    .then().log().all()
                    .extract().response();

            JsonPath jsonPathDocumentTypeCreate = responseDocumentTypeCreate.jsonPath();
//            String idNewElement = jsonPathDocumentTypeCreate.getString("data.id");
//            System.out.println(idNewElement);
        }
    }

    @Test
    public void step01(){
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("role_ids", Arrays.asList(29));
        requestBody.put("control_subject_id", "");
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBody)
                .put(API_USERS + "/1")
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String idUser = jsonPath.getString("data.id");
        String officialIdUser = jsonPath.getString("data.official_id");
        String providerIdUser = jsonPath.getString("data.provider_id");
        String fullNameUser = jsonPath.getString("data.full_name");
        String positionUser = jsonPath.getString("data.position");
        String organizationUser = jsonPath.getString("data.organization_short_title");
        String providerNameUser = jsonPath.getString("data.provider_name");
        String phoneUser = jsonPath.get("data.phone");
        String emailUser = jsonPath.getString("data.email");
        String rolesIdUser = jsonPath.getString("data.roles[0].id");
        String rolesNameUser = jsonPath.getString("data.roles[0].full_name");
        String controlSubjectUser = jsonPath.getString("data.control_subject");

        System.out.println("id - " + idUser);
        System.out.println("official id - " + officialIdUser);
        System.out.println("provider id - " + providerIdUser);
        System.out.println("full name - " + fullNameUser);
        System.out.println("position - " + positionUser);
        System.out.println("organization - " + organizationUser);
        System.out.println("provider name - " + providerNameUser);
        System.out.println("phone - " + phoneUser);
        System.out.println("email - " + emailUser);
        System.out.println("role id - " + rolesIdUser);
        System.out.println("role name - " + rolesNameUser);
        System.out.println("control subject - " + controlSubjectUser);

        Assert.assertTrue(idUser.equals("1"));
        Assert.assertTrue(officialIdUser.equals("51bace4af273695ed1000128"));
        Assert.assertTrue(providerIdUser.equals("525e9f767da3000002000001"));
        Assert.assertTrue(fullNameUser.equals("Колокольцев Владимир Александрович"));
        Assert.assertTrue(positionUser.equals("Министр"));
        Assert.assertTrue(organizationUser.equals("Центральный аппарат МВД РФ"));
        Assert.assertTrue(providerNameUser.equals("Департамент делопроизводства " +
                "и работы с обращениями граждан и организаций Министерства внутренних дел " +
                "Российской Федерации (ДДО МВД России)"));
        Assert.assertTrue(phoneUser.equals("(495)123-45-67 (123)"));
        Assert.assertTrue(emailUser.equals("dsa@qwerty.ru"));
        Assert.assertTrue(rolesIdUser.equals("29"));
        Assert.assertTrue(rolesNameUser.equals("Все права"));
        //Assert.assertTrue(controlSubjectUser.equals("null")); // Don't work
        Assert.assertNull(controlSubjectUser);
    }

    @Test
    public void step02(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_129_step02.json"))
                .post(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String skipRegDate = jsonPath.getString("data.skip_reg_date");
        String shortDescription = jsonPath.getString("data.short_description");
        String controlOfficialId = jsonPath.getString("data.control_official.id");
        String controlSubjectId = jsonPath.getString("data.control_subject.id");
        String documentName = jsonPath.getString("data.document_name");
        String documentExecutionStateId = jsonPath.getString("data.document_execution_state.id");
        String documentTypeId = jsonPath.getString("data.skip_document_type.id");
        String internal = jsonPath.getString("data.internal");
        String notSedDocument = jsonPath.getString("data.not_sed_document");
        String sedRegNumber = jsonPath.getString("data.sed_reg_number");
        String sedDocumentDate = jsonPath.getString("data.sed_document_date");
        String cameFromName = jsonPath.getString("data.came_from_name");
        String externalDocumentNumber = jsonPath.getString("data.external_document_number");
        String signerName = jsonPath.getString("data.signer_name");

        System.out.println("Skip reg date - " + skipRegDate);
        System.out.println("Short description - " + shortDescription);
        System.out.println("Control official id - " + controlOfficialId);
        System.out.println("Control subject id - " + controlSubjectId);
        System.out.println("Document name - " + documentName);
        System.out.println("Document execution state id - " + documentExecutionStateId);
        System.out.println("Document type id - " + documentTypeId);
        System.out.println("Internal - " + internal);
        System.out.println("Not sed document - " + notSedDocument);
        System.out.println("Sed reg number - " + sedRegNumber);
        System.out.println("Sed document date - " + sedDocumentDate);
        System.out.println("Came from name - " + cameFromName);
        System.out.println("External Document number - " + externalDocumentNumber);
        System.out.println("Signer name - " + signerName);

        Assert.assertTrue(skipRegDate.equals("2024-01-11T12:37:45.190Z"));
        Assert.assertTrue(shortDescription.equals("palenose"));
        Assert.assertTrue(controlOfficialId.equals("51bace4af273695ed1000128"));
        Assert.assertTrue(controlSubjectId.equals("122"));
        Assert.assertTrue(documentName.equals("sublex"));
        Assert.assertTrue(documentExecutionStateId.equals("2"));
        Assert.assertTrue(documentTypeId.equals("1"));
        Assert.assertTrue(internal.equals("true"));
        Assert.assertTrue(notSedDocument.equals("true"));
        Assert.assertTrue(sedRegNumber.equals("12.01.2024"));
        Assert.assertTrue(sedDocumentDate.equals("2024-01-11T12:37:45.190Z"));
        Assert.assertTrue(cameFromName.equals("purplese"));
        Assert.assertTrue(externalDocumentNumber.equals("Yellowse"));
        Assert.assertTrue(signerName.equals("mvolobse"));
    }

    @Test
    public void step03(){
        // Point 1
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_129_step03_point01.json"))
                .post(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String skipRegDate = jsonPath.getString("data.skip_reg_date");
        String shortDescription = jsonPath.getString("data.short_description");
        String controlOfficialId = jsonPath.getString("data.control_official.id");
        String controlSubjectId = jsonPath.getString("data.control_subject.id");
        String documentName = jsonPath.getString("data.document_name");
        String documentExecutionStateId = jsonPath.getString("data.document_execution_state.id");
        String documentTypeId = jsonPath.getString("data.skip_document_type.id");
        String internal = jsonPath.getString("data.internal");
        String notSedDocument = jsonPath.getString("data.not_sed_document");
        String sedRegNumber = jsonPath.getString("data.sed_reg_number");
        String sedDocumentDate = jsonPath.getString("data.sed_document_date");
        String cameFromName = jsonPath.getString("data.came_from_name");
        String externalDocumentNumber = jsonPath.getString("data.external_document_number");
        String signerName = jsonPath.getString("data.signer_name");

        System.out.println("Skip reg date - " + skipRegDate);
        System.out.println("Short description - " + shortDescription);
        System.out.println("Control official id - " + controlOfficialId);
        System.out.println("Control subject id - " + controlSubjectId);
        System.out.println("Document name - " + documentName);
        System.out.println("Document execution state id - " + documentExecutionStateId);
        System.out.println("Document type id - " + documentTypeId);
        System.out.println("Internal - " + internal);
        System.out.println("Not sed document - " + notSedDocument);
        System.out.println("Sed reg number - " + sedRegNumber);
        System.out.println("Sed document date - " + sedDocumentDate);
        System.out.println("Came from name - " + cameFromName);
        System.out.println("External Document number - " + externalDocumentNumber);
        System.out.println("Signer name - " + signerName);

        Assert.assertTrue(skipRegDate.equals("2024-01-11T12:37:45.190Z"));
        Assert.assertTrue(shortDescription.equals("pale"));
        Assert.assertTrue(controlOfficialId.equals("51bace4af273695ed1000128"));
        Assert.assertTrue(controlSubjectId.equals("122"));
        Assert.assertTrue(documentName.equals("sublex"));
        Assert.assertTrue(documentExecutionStateId.equals("2"));
        Assert.assertTrue(documentTypeId.equals("1"));
        Assert.assertTrue(internal.equals("true"));
        Assert.assertTrue(notSedDocument.equals("true"));
        Assert.assertTrue(sedRegNumber.equals("12.01.2024"));
        Assert.assertTrue(sedDocumentDate.equals("2024-01-11T12:37:45.190Z"));
        Assert.assertTrue(cameFromName.equals("purp"));
        Assert.assertTrue(externalDocumentNumber.equals("Yell"));
        Assert.assertTrue(signerName.equals("mvol"));

        // Point 2
        Response responseSecond = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_129_step03_point02.json"))
                .post(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathSecond = responseSecond.jsonPath();

        String skipRegDateSecond = jsonPathSecond.getString("data.skip_reg_date");
        String shortDescriptionSecond = jsonPathSecond.getString("data.short_description");
        String controlOfficialIdSecond = jsonPathSecond.getString("data.control_official.id");
        String controlSubjectIdSecond = jsonPathSecond.getString("data.control_subject.id");
        String documentNameSecond = jsonPathSecond.getString("data.document_name");
        String documentExecutionStateIdSecond = jsonPathSecond.getString("data.document_execution_state.id");
        String documentTypeIdSecond = jsonPathSecond.getString("data.skip_document_type.id");
        String internalSecond = jsonPathSecond.getString("data.internal");
        String notSedDocumentSecond = jsonPathSecond.getString("data.not_sed_document");
        String sedRegNumberSecond = jsonPathSecond.getString("data.sed_reg_number");
        String sedDocumentDateSecond = jsonPathSecond.getString("data.sed_document_date");
        String cameFromNameSecond = jsonPathSecond.getString("data.came_from_name");
        String externalDocumentNumberSecond = jsonPathSecond.getString("data.external_document_number");
        String signerNameSecond = jsonPathSecond.getString("data.signer_name");

        System.out.println("Skip reg date - " + skipRegDateSecond);
        System.out.println("Short description - " + shortDescriptionSecond);
        System.out.println("Control official id - " + controlOfficialIdSecond);
        System.out.println("Control subject id - " + controlSubjectIdSecond);
        System.out.println("Document name - " + documentNameSecond);
        System.out.println("Document execution state id - " + documentExecutionStateIdSecond);
        System.out.println("Document type id - " + documentTypeIdSecond);
        System.out.println("Internal - " + internalSecond);
        System.out.println("Not sed document - " + notSedDocumentSecond);
        System.out.println("Sed reg number - " + sedRegNumberSecond);
        System.out.println("Sed document date - " + sedDocumentDateSecond);
        System.out.println("Came from name - " + cameFromNameSecond);
        System.out.println("External Document number - " + externalDocumentNumberSecond);
        System.out.println("Signer name - " + signerNameSecond);

        Assert.assertTrue(skipRegDateSecond.equals("2024-01-11T12:37:45.190Z"));
        Assert.assertTrue(shortDescriptionSecond.equals("pa"));
        Assert.assertTrue(controlOfficialIdSecond.equals("51bace4af273695ed1000128"));
        Assert.assertTrue(controlSubjectIdSecond.equals("122"));
        Assert.assertTrue(documentNameSecond.equals("sublex"));
        Assert.assertTrue(documentExecutionStateIdSecond.equals("2"));
        Assert.assertTrue(documentTypeIdSecond.equals("1"));
        Assert.assertTrue(internalSecond.equals("true"));
        Assert.assertTrue(notSedDocumentSecond.equals("true"));
        Assert.assertTrue(sedRegNumberSecond.equals("12.01.2024"));
        Assert.assertTrue(sedDocumentDateSecond.equals("2024-01-11T12:37:45.190Z"));
        Assert.assertTrue(cameFromNameSecond.equals("pu"));
        Assert.assertTrue(externalDocumentNumberSecond.equals("Ye"));
        Assert.assertTrue(signerNameSecond.equals("mv"));
    }

    @Test
    public void step04(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                //.body(new File("src/test/java/SKIP_API/json_files/SKIP_129_step03_point01.json"))
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();


        List<Object> data = jsonPath.getList("data");
        int count = data.size();
//        System.out.println("Count of element: " + count);
        Assert.assertTrue(count == 10);

        List<Object> SedRegNumbers = jsonPath.getList("data.sed_reg_number");
//        System.out.println(SedRegNumbers);
        ArrayList<Object> SedRegNumbersSorted = new ArrayList<>(SedRegNumbers);
        Collections.sort(SedRegNumbersSorted,Collections.reverseOrder());
//        System.out.println(SedRegNumbersSorted);
        Assert.assertTrue(SedRegNumbers.equals(SedRegNumbersSorted));
    }

    @Test
    public void step05(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        // Create a list contains all elements from "sed_reg_number" parameter
//        List<Object> SedRegNumbers = jsonPath.getList("data.sed_reg_number");
//        // Check output in the console
//        //System.out.println(SedRegNumbers);
//        // Create empty new array list that'll contain not null elements
//        ArrayList<Object> SedRegNumbersWithoutNull = new ArrayList<>();
//        // Using loop "for" go through each element
//        for (Object number : SedRegNumbers) {
//            // If element is not null add it to new array
//            if (number != null) {
//                SedRegNumbersWithoutNull.add(number);
//            }
//        }
//        // Check new array in the console
//        System.out.println(SedRegNumbersWithoutNull);
//        // Create new array that will be sorted
//        ArrayList<Object> SedRegNumbersSorted = new ArrayList<>(SedRegNumbersWithoutNull);

        List<String> SedRegNumbers = jsonPath.getList("data.sed_reg_number");
        //System.out.println(SedRegNumbers);
        ArrayList<String> SedRegNumbersWithoutNull = new ArrayList<>();
        for (String number : SedRegNumbers) {
            // If element is not null add it to new array
            if (number != null) {
                SedRegNumbersWithoutNull.add(number);
            }
        }
        System.out.println(SedRegNumbersWithoutNull);
        ArrayList<String> SedRegNumbersSorted = new ArrayList<>(SedRegNumbersWithoutNull);
        Collections.sort(SedRegNumbersSorted, Collections.reverseOrder());
        System.out.println(SedRegNumbersSorted);
        Assert.assertTrue(SedRegNumbersWithoutNull.equals(SedRegNumbersSorted));



        // Create a list contains all elements from "data" parameter
        List<Object> data = jsonPath.getList("data");
        // Create new integer variable that store count of the elements
        int count = data.size();
        // Check count of the elements in the console
        System.out.println("Count of element: " + count);
        Assert.assertNotNull(count);
    }

    @Test
    public void step06(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","palenose")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String shortDescription = jsonPath.getString("data.short_description");
        System.out.println("Short description - " + shortDescription);
        Assert.assertTrue(shortDescription.contains("palenose"));
    }

    @Test
    public void step07(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","palenosejk")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String shortDescription = jsonPath.getString("data.short_description");
        System.out.println("Short description - " + shortDescription);
        Assert.assertTrue(shortDescription.contains("palenose"));
    }

    @Test
    public void step08(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","palenore")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String shortDescription = jsonPath.getString("data.short_description");
        System.out.println("Short description - " + shortDescription);
        Assert.assertTrue(shortDescription.contains("palenose"));
    }

    @Test
    public void step09(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","jkpalenose")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String shortDescription = jsonPath.getString("data.short_description");
        System.out.println("Short description - " + shortDescription);
        Assert.assertTrue(shortDescription.contains("palenose"));
    }

    @Test
    public void step10(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","palejknose")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String shortDescription = jsonPath.getString("data.short_description");
        System.out.println("Short description - " + shortDescription);
        Assert.assertTrue(shortDescription.contains("palenose"));
    }

    @Test
    public void step11(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","jklenose")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String shortDescription = jsonPath.getString("data.short_description");
        System.out.println("Short description - " + shortDescription);
        Assert.assertTrue(shortDescription.contains("palenose"));
    }

    @Test
    public void step12(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","paljkose")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String shortDescription = jsonPath.getString("data.short_description");
        System.out.println("Short description - " + shortDescription);
        Assert.assertTrue(shortDescription.contains("palenose"));
    }

    @Test
    public void step13(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","palenoseaaa")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String data = jsonPath.getString("data[]");
        //System.out.println("Data - " + data);
        if (data.equals("[]")){
            data = null;
        }
        System.out.println(data);
        //Assert.assertTrue(data.equals("[]"));
        Assert.assertNull(data);
    }

    @Test
    public void step14(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","aaaenose")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String data = jsonPath.getString("data[]");
        if (data.equals("[]")){
            data = null;
        }
        Assert.assertNull(data);
    }

    @Test
    public void step15(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","palenose   ")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String shortDescription = jsonPath.getString("data.short_description");
        System.out.println("Short description - " + shortDescription);
        Assert.assertTrue(shortDescription.contains("palenose"));
    }

    @Test
    public void step16(){
        // 6 step - Just default value of "short_description"
        Response responseDefault = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","purplese")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathDefault = responseDefault.jsonPath();

        String cameFromNameDefault = jsonPathDefault.getString("data.came_from_name");
//        System.out.println("Came from name - " + cameFromNameDefault);
        Assert.assertTrue(cameFromNameDefault.contains("purplese"));

        // 7 step - add two letters to the end
        Response responseTwoAtTheEnd = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","purplesejk")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathTwoAtTheEnd = responseTwoAtTheEnd.jsonPath();

        String cameFromNameTwoAtTheEnd = jsonPathTwoAtTheEnd.getString("data.came_from_name");
//        System.out.println("Came from name - " + cameFromNameTwoAtTheEnd);
        Assert.assertTrue(cameFromNameTwoAtTheEnd.contains("purplese"));
        // 8 step - change two letters into the end
        Response responseTwoIntoTheEnd = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","purplejk")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathTwoIntoTheEnd = responseTwoIntoTheEnd.jsonPath();

        String cameFromNameTwoIntoTheEnd = jsonPathTwoIntoTheEnd.getString("data.came_from_name");
        //System.out.println("Came from name - " + cameFromNameTwoIntoTheEnd);
        Assert.assertTrue(cameFromNameTwoIntoTheEnd.contains("purplese"));
        // 9 step - add two letters in the beginning
        Response responseTwoAtTheBeginning = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","jkpurplese")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathTwoAtTheBeginning = responseTwoAtTheBeginning.jsonPath();

        String cameFromNameTwoAtTheBeginning = jsonPathTwoAtTheBeginning.getString("data.came_from_name");
        //System.out.println("Came from name - " + cameFromNameTwoAtTheBeginning);
        Assert.assertTrue(cameFromNameTwoAtTheBeginning.contains("purplese"));
        // 10 step - add two letters in the middle
        Response responseTwoAddInTheMiddle = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","purpjklese")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathTwoAddInTheMiddle = responseTwoAddInTheMiddle.jsonPath();

        String cameFromNameTwoAddInTheMiddle = jsonPathTwoAddInTheMiddle.getString("data.came_from_name");
        //System.out.println("Came from name - " + cameFromNameTwoAddInTheMiddle);
        Assert.assertTrue(cameFromNameTwoAddInTheMiddle.contains("purplese"));
        // 11 step - change two letters in the beginning
        Response responseTwoIntoTheBeginning = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","jkrplese")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathTwoIntoTheBeginning = responseTwoIntoTheBeginning.jsonPath();

        String cameFromNameTwoIntoTheBeginning = jsonPathTwoIntoTheBeginning.getString("data.came_from_name");
        //System.out.println("Came from name - " + cameFromNameTwoIntoTheBeginning);
        Assert.assertTrue(cameFromNameTwoIntoTheBeginning.contains("purplese"));
        // 12 step - change two letters in the middle
        Response responseTwoChangeInTheMiddle = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","purjkese")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathTwoChangeInTheMiddle = responseTwoChangeInTheMiddle.jsonPath();

        String cameFromNameTwoChangeInTheMiddle = jsonPathTwoChangeInTheMiddle.getString("data.came_from_name");
        //System.out.println("Came from name - " + cameFromNameTwoChangeInTheMiddle);
        Assert.assertTrue(cameFromNameTwoChangeInTheMiddle.contains("purplese"));
        // 13 step - add three letters to the end
        Response responseThreeAtTheEnd = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","purplesejkk")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathThreeAtTheEnd = responseThreeAtTheEnd.jsonPath();

        String dataThreeAtTheEnd = jsonPathThreeAtTheEnd.getString("data[]");
        //System.out.println("Data - " + data);
        if (dataThreeAtTheEnd.equals("[]")){
            dataThreeAtTheEnd = null;
        }
        //System.out.println(dataThreeAtTheEnd);
        //Assert.assertTrue(data.equals("[]"));
        Assert.assertNull(dataThreeAtTheEnd);
        // 14 step - change three letters in the end
        Response responseThreeIntoTheEnd = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","purpljkk")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathThreeIntoTheEnd = responseThreeIntoTheEnd.jsonPath();
        String dataThreeIntoTheEnd = jsonPathThreeIntoTheEnd.getString("data[]");
        if (dataThreeIntoTheEnd.equals("[]")){
            dataThreeIntoTheEnd = null;
        }
        Assert.assertNull(dataThreeIntoTheEnd);
    }

    @Test
    public void step17(){
        // 6 step - Just default value of "short_description"
        Response responseDefault = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","mvolobse")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathDefault = responseDefault.jsonPath();

        String signerNameDefault = jsonPathDefault.getString("data.signer_name");
//        System.out.println("Signer name - " + signerNameDefault);
        Assert.assertTrue(signerNameDefault.contains("mvolobse"));
        // 7 step - add two letters to the end
        Response responseTwoAtTheEnd = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","mvolobsejk")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathTwoAtTheEnd = responseTwoAtTheEnd.jsonPath();

        String signerNameTwoAtTheEnd = jsonPathTwoAtTheEnd.getString("data.signer_name");
//        System.out.println("Signer name - " + signerNameTwoAtTheEnd);
        Assert.assertTrue(signerNameTwoAtTheEnd.contains("mvolobse"));
        // 8 step - change two letters in the end
        Response responseTwoIntoTheEnd = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","mvolobjk")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathTwoIntoTheEnd = responseTwoIntoTheEnd.jsonPath();

        String signerNameTwoIntoTheEnd = jsonPathTwoIntoTheEnd.getString("data.signer_name");
        //System.out.println("Signer name - " + signerNameTwoIntoTheEnd);
        Assert.assertTrue(signerNameTwoIntoTheEnd.contains("mvolobse"));
        // 9 step - add two letters in the beginning
        Response responseTwoAtTheBeginning = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","jkmvolobse")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathTwoAtTheBeginning = responseTwoAtTheBeginning.jsonPath();

        String signerNameTwoAtTheBeginning = jsonPathTwoAtTheBeginning.getString("data.signer_name");
        //System.out.println("Signer name - " + signerNameTwoAtTheBeginning);
        Assert.assertTrue(signerNameTwoAtTheBeginning.contains("mvolobse"));
        // 10 step - add two letters in the middle
        Response responseTwoAddInTheMiddle = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","mvoljkobse")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathTwoAddInTheMiddle = responseTwoAddInTheMiddle.jsonPath();

        String signerNameTwoAddInTheMiddle = jsonPathTwoAddInTheMiddle.getString("data.signer_name");
        //System.out.println("Signer name - " + signerNameTwoAddInTheMiddle);
        Assert.assertTrue(signerNameTwoAddInTheMiddle.contains("mvolobse"));
        // 11 step - change two letters in the beginning
        Response responseTwoIntoTheBeginning = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","jkolobse")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathTwoIntoTheBeginning = responseTwoIntoTheBeginning.jsonPath();

        String signerNameTwoIntoTheBeginning = jsonPathTwoIntoTheBeginning.getString("data.signer_name");
        //System.out.println("Signer name - " + signerNameTwoIntoTheBeginning);
        Assert.assertTrue(signerNameTwoIntoTheBeginning.contains("mvolobse"));
        // 12 step - change two letters in the middle
        Response responseTwoChangeInTheMiddle = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","mvojkbse")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathTwoChangeInTheMiddle = responseTwoChangeInTheMiddle.jsonPath();

        String signerNameTwoChangeInTheMiddle = jsonPathTwoChangeInTheMiddle.getString("data.signer_name");
        //System.out.println("Signer name - " + signerNameTwoChangeInTheMiddle);
        Assert.assertTrue(signerNameTwoChangeInTheMiddle.contains("mvolobse"));
        // 13 step - add three letters to the end
        Response responseThreeAtTheEnd = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","mvolobsejkk")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathThreeAtTheEnd = responseThreeAtTheEnd.jsonPath();

        String dataThreeAtTheEnd = jsonPathThreeAtTheEnd.getString("data[]");
        if (dataThreeAtTheEnd.equals("[]")){
            dataThreeAtTheEnd = null;
        }
        Assert.assertNull(dataThreeAtTheEnd);
        // 14 step - change three letters in the end
        Response responseThreeIntoTheEnd = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","mvolojkk")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathThreeIntoTheEnd = responseThreeIntoTheEnd.jsonPath();
        String dataThreeIntoTheEnd = jsonPathThreeIntoTheEnd.getString("data[]");
        if (dataThreeIntoTheEnd.equals("[]")){
            dataThreeIntoTheEnd = null;
        }
        Assert.assertNull(dataThreeIntoTheEnd);
    }

    @Test
    public void step18(){
        Response responseDefault = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","12.01.2024")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathDefault = responseDefault.jsonPath();

        String sedRegNumberDefault = jsonPathDefault.getString("data.sed_reg_number");
//        System.out.println("Sed reg number - " + sedRegNumberDefault);
        Assert.assertTrue(sedRegNumberDefault.contains("12.01.2024"));
    }

    @Test
    public void step19(){
        Response responseDefault = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","Yellowse")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathDefault = responseDefault.jsonPath();

        String externalDocumentNumberDefault = jsonPathDefault.getString("data.external_document_number");
//        System.out.println("External document number - " + externalDocumentNumberDefault);
        Assert.assertTrue(externalDocumentNumberDefault.contains("Yellowse"));
    }

    @Test
    public void step20(){
        Response responseDefault = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","11.01.2024")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathDefault = responseDefault.jsonPath();

        String skipRegDateDefault = jsonPathDefault.getString("data.skip_reg_date");
//        System.out.println("Skip reg date - " + skipRegDateDefault);
        Assert.assertTrue(skipRegDateDefault.contains("2024-01-11T12:37:45.190Z"));
    }

    @Test
    public void step21(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","pale")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String shortDescription = jsonPath.getString("data.short_description");
//        System.out.println("Short description - " + shortDescription);
        Assert.assertTrue(shortDescription.contains("pale"));
    }

    @Test
    public void step22(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","palejk")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String shortDescription = jsonPath.getString("data.short_description");
//        System.out.println("Short description - " + shortDescription);
        Assert.assertTrue(shortDescription.contains("pale"));
    }

    @Test
    public void step23(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","palj")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String shortDescription = jsonPath.getString("data.short_description");
//        System.out.println("Short description - " + shortDescription);
        Assert.assertTrue(shortDescription.contains("pale"));
    }

    @Test
    public void step24(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","jkpale")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String shortDescription = jsonPath.getString("data.short_description");
//        System.out.println("Short description - " + shortDescription);
        Assert.assertTrue(shortDescription.contains("pale"));
    }

    @Test
    public void step25(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","pa12le")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String shortDescription = jsonPath.getString("data.short_description");
//        System.out.println("Short description - " + shortDescription);
        Assert.assertTrue(shortDescription.contains("pale"));
    }

    @Test
    public void step26(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","jale")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String shortDescription = jsonPath.getString("data.short_description");
//        System.out.println("Short description - " + shortDescription);
        Assert.assertTrue(shortDescription.contains("pale"));
    }

    @Test
    public void step27(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","pjle")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String shortDescription = jsonPath.getString("data.short_description");
//        System.out.println("Short description - " + shortDescription);
        Assert.assertTrue(shortDescription.contains("pale"));
    }

    @Test
    public void step28(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","palejjk")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String data = jsonPath.getString("data[]");
        if (data.equals("[]")){
            data = null;
        }
        Assert.assertNull(data);
    }

    @Test
    public void step29(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","jjle")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String data = jsonPath.getString("data[]");
        if (data.equals("[]")){
            data = null;
        }
        Assert.assertNull(data);
    }

    @Test
    public void step30(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","le")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String data = jsonPath.getString("data[]");
        if (data.equals("[]")){
            data = null;
        }
        Assert.assertNull(data);
    }

    @Test
    public void step31(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","12.01.2024f")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String data = jsonPath.getString("data[]");
        if (data.equals("[]")){
            data = null;
        }
        Assert.assertNull(data);
    }

    @Test
    public void step32(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","Yellf")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String data = jsonPath.getString("data[]");
        if (data.equals("[]")){
            data = null;
        }
        Assert.assertNull(data);
    }

    @Test
    public void step33(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","11.01.2024f")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String data = jsonPath.getString("data[]");
        if (data.equals("[]")){
            data = null;
        }
        Assert.assertNull(data);
    }

    @Test
    public void step34(){
        // step 21 - default value
        Response responseDefault = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","purp")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathDefault = responseDefault.jsonPath();

        String cameFromNameDefault = jsonPathDefault.getString("data.came_from_name");
//        System.out.println("Came from name - " + cameFromNameDefault);
        Assert.assertTrue(cameFromNameDefault.contains("purp"));
        // step 22 - add two letters to the end
        Response responseAddTwoAtTheEnd = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","purpjk")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathAddTwoAtTheEnd = responseAddTwoAtTheEnd.jsonPath();

        String cameFromNameAddTwoAtTheEnd = jsonPathAddTwoAtTheEnd.getString("data.came_from_name");
//        System.out.println("Came from name - " + cameFromNameAddTwoAtTheEnd);
        Assert.assertTrue(cameFromNameAddTwoAtTheEnd.contains("purp"));
        // step 23 - change one last letter
        Response responseChangeOneLast = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","purj")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathChangeOneLast = responseChangeOneLast.jsonPath();

        String cameFromNameChangeOneLast = jsonPathChangeOneLast.getString("data.came_from_name");
//        System.out.println("Came from name - " + cameFromNameChangeOneLast);
        Assert.assertTrue(cameFromNameChangeOneLast.contains("purp"));
        // step 24 - add two letters at the beginning
        Response responseAddTwoAtStart = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","jkpurp")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathAddTwoAtStart = responseAddTwoAtStart.jsonPath();

        String cameFromNameAddTwoAtStart = jsonPathAddTwoAtStart.getString("data.came_from_name");
//        System.out.println("Came from name - " + cameFromNameAddTwoAtStart);
        Assert.assertTrue(cameFromNameAddTwoAtStart.contains("purp"));
        // step 25 - add two letters in the middle
        Response responseAddTwoInTheMiddle = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","pujkrp")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathAddTwoInTheMiddle = responseAddTwoInTheMiddle.jsonPath();

        String cameFromNameAddTwoInTheMiddle = jsonPathAddTwoInTheMiddle.getString("data.came_from_name");
//        System.out.println("Came from name - " + cameFromNameAddTwoInTheMiddle);
        Assert.assertTrue(cameFromNameAddTwoInTheMiddle.contains("purp"));
        // step 26 - change one first letter
        Response responseChangeOneFirst = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","jurp")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathChangeOneFirst = responseChangeOneFirst.jsonPath();

        String cameFromNameChangeOneFirst = jsonPathChangeOneFirst.getString("data.came_from_name");
//        System.out.println("Came from name - " + cameFromNameChangeOneFirst);
        Assert.assertTrue(cameFromNameChangeOneFirst.contains("purp"));
        // step 27 - change letter in the middle
        Response responseChangeOneInTheMiddle = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","perp")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathChangeOneInTheMiddle = responseChangeOneInTheMiddle.jsonPath();

        String cameFromNameChangeOneInTheMiddle = jsonPathChangeOneInTheMiddle.getString("data.came_from_name");
//        System.out.println("Came from name - " + cameFromNameChangeOneInTheMiddle);
        Assert.assertTrue(cameFromNameChangeOneInTheMiddle.contains("purp"));
        // step 28 - add three letters
        Response responseAddThreeLetters = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","purpjkk")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

            JsonPath jsonPathAddThreeLetters = responseAddThreeLetters.jsonPath();

        String dataAddThreeLetters = jsonPathAddThreeLetters.getString("data[]");
        if (dataAddThreeLetters.equals("[]")){
            dataAddThreeLetters = null;
        }
        Assert.assertNull(dataAddThreeLetters);
        // step 29 - change two first letters
        Response responseChangeTwoFirstLetters = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","kjrp")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathChangeTwoFirstLetters = responseChangeTwoFirstLetters.jsonPath();

        String dataChangeTwoFirstLetters = jsonPathChangeTwoFirstLetters.getString("data[]");
        if (dataChangeTwoFirstLetters.equals("[]")){
            dataChangeTwoFirstLetters = null;
        }
        Assert.assertNull(dataChangeTwoFirstLetters);
        // step 30 - delete two first letters
        Response responseDeleteTwoFirstLetters = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","rp")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathDeleteTwoFirstLetters = responseDeleteTwoFirstLetters.jsonPath();

        String dataDeleteTwoFirstLetters = jsonPathDeleteTwoFirstLetters.getString("data[]");
        if (dataDeleteTwoFirstLetters.equals("[]")){
            dataDeleteTwoFirstLetters = null;
        }
        Assert.assertNull(dataDeleteTwoFirstLetters);
    }

    @Test
    public void step35(){
        // step 21 - default value
        Response responseDefault = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","mvol")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathDefault = responseDefault.jsonPath();

        String signerNameDefault = jsonPathDefault.getString("data.signer_name");
//        System.out.println("Signer name - " + signerNameDefault);
        Assert.assertTrue(signerNameDefault.contains("mvol"));
        // step 22 - add two letters to the end
        Response responseAddTwoAtTheEnd = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","mvoljk")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathAddTwoAtTheEnd = responseAddTwoAtTheEnd.jsonPath();

        String signerNameAddTwoAtTheEnd = jsonPathAddTwoAtTheEnd.getString("data.signer_name");
//        System.out.println("Signer name - " + signerNameAddTwoAtTheEnd);
        Assert.assertTrue(signerNameAddTwoAtTheEnd.contains("mvol"));
        // step 23 - change one last letter
        Response responseChangeOneLast = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","mvoj")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathChangeOneLast = responseChangeOneLast.jsonPath();

        String signerNameChangeOneLast = jsonPathChangeOneLast.getString("data.signer_name");
//        System.out.println("Signer name - " + signerNameChangeOneLast);
        Assert.assertTrue(signerNameChangeOneLast.contains("mvol"));
        // step 24 - add two letters at the beginning
        Response responseAddTwoAtStart = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","jkmvol")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathAddTwoAtStart = responseAddTwoAtStart.jsonPath();

        String signerNameAddTwoAtStart = jsonPathAddTwoAtStart.getString("data.signer_name");
//        System.out.println("Signer name - " + signerNameAddTwoAtStart);
        Assert.assertTrue(signerNameAddTwoAtStart.contains("mvol"));
        // step 25 - add two letters in the middle
        Response responseAddTwoInTheMiddle = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","mvjkol")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathAddTwoInTheMiddle = responseAddTwoInTheMiddle.jsonPath();

        String signerNameAddTwoInTheMiddle = jsonPathAddTwoInTheMiddle.getString("data.signer_name");
//        System.out.println("Signer name - " + signerNameAddTwoInTheMiddle);
        Assert.assertTrue(signerNameAddTwoInTheMiddle.contains("mvol"));
        // step 26 - change one first letter
        Response responseChangeOneFirst = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","jvol")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathChangeOneFirst = responseChangeOneFirst.jsonPath();

        String signerNameChangeOneFirst = jsonPathChangeOneFirst.getString("data.signer_name");
//        System.out.println("Signer name - " + signerNameChangeOneFirst);
        Assert.assertTrue(signerNameChangeOneFirst.contains("mvol"));
        // step 27 - change letter in the middle
        Response responseChangeOneInTheMiddle = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","mjol")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathChangeOneInTheMiddle = responseChangeOneInTheMiddle.jsonPath();

        String signerNameChangeOneInTheMiddle = jsonPathChangeOneInTheMiddle.getString("data.signer_name");
//        System.out.println("Signer name - " + signerNameChangeOneInTheMiddle);
        Assert.assertTrue(signerNameChangeOneInTheMiddle.contains("mvol"));
        // step 28 - add three letters
        Response responseAddThreeLetters = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","mvoljkk")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathAddThreeLetters = responseAddThreeLetters.jsonPath();

        String dataAddThreeLetters = jsonPathAddThreeLetters.getString("data[]");
        if (dataAddThreeLetters.equals("[]")){
            dataAddThreeLetters = null;
        }
        Assert.assertNull(dataAddThreeLetters);
        // step 29 - change two first letters
        Response responseChangeTwoFirstLetters = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","jkol")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathChangeTwoFirstLetters = responseChangeTwoFirstLetters.jsonPath();

        String dataChangeTwoFirstLetters = jsonPathChangeTwoFirstLetters.getString("data[]");
        if (dataChangeTwoFirstLetters.equals("[]")){
            dataChangeTwoFirstLetters = null;
        }
        Assert.assertNull(dataChangeTwoFirstLetters);
        // step 30 - delete two first letters
        Response responseDeleteTwoFirstLetters = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","ol")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathDeleteTwoFirstLetters = responseDeleteTwoFirstLetters.jsonPath();

        String dataDeleteTwoFirstLetters = jsonPathDeleteTwoFirstLetters.getString("data[]");
        if (dataDeleteTwoFirstLetters.equals("[]")){
            dataDeleteTwoFirstLetters = null;
        }
        Assert.assertNull(dataDeleteTwoFirstLetters);
    }

    @Test
    public void step36(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","pa")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String shortDescription = jsonPath.getString("data.short_description");
//        System.out.println("Short description - " + shortDescription);
        Assert.assertTrue(shortDescription.contains("pa"));
    }

    @Test
    public void step37(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","paj")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String shortDescription = jsonPath.getString("data.short_description");
        Assert.assertTrue(shortDescription.equals("[pa]"));
        // System.out.println(shortDescription);
    }

    @Test
    public void step38(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","pq")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String data = jsonPath.getString("data[]");
        if (data.equals("[]")){
            data = null;
        }
        Assert.assertNull(data);
    }

    @Test
    public void step39(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","jpa")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String shortDescription = jsonPath.getString("data.short_description");
        // System.out.println(shortDescription);
        Assert.assertTrue(shortDescription.equals("[pa]"));
    }

    @Test
    public void step40(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","p1a")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String shortDescription = jsonPath.getString("data.short_description");
        // System.out.println(shortDescription);
        Assert.assertTrue(shortDescription.equals("[pa]"));
    }

    @Test
    public void step41(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","ra")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String data = jsonPath.getString("data[]");
        if (data.equals("[]")){
            data = null;
        }
        Assert.assertNull(data);
    }

    @Test
    public void step42(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","pare")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String data = jsonPath.getString("data[]");
        if (data.equals("[]")){
            data = null;
        }
        Assert.assertNull(data);
    }

    @Test
    public void step43(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","12.01.2029")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String data = jsonPath.getString("data[]");
        if (data.equals("[]")){
            data = null;
        }
        Assert.assertNull(data);
    }

    @Test
    public void step44(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","Y9")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String data = jsonPath.getString("data[]");
        if (data.equals("[]")){
            data = null;
        }
        Assert.assertNull(data);
    }

    @Test
    public void step45(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","11.01.2029")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String data = jsonPath.getString("data[]");
        if (data.equals("[]")){
            data = null;
        }
        Assert.assertNull(data);
    }

    @Test
    public void step46(){
        // step 36 - Default value
        Response responseDefault = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","pu")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathDefault = responseDefault.jsonPath();

        String cameFromNameDefault = jsonPathDefault.getString("data.came_from_name");
//        System.out.println("Came from name - " + cameFromNameDefault);
        Assert.assertTrue(cameFromNameDefault.contains("pu"));
        // step 37 - Add one letter at the end
        Response responseAddOneAtTheEnd = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","puq")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathAddOneAtTheEnd = responseAddOneAtTheEnd.jsonPath();

        String cameFromNameAddOneAtTheEnd = jsonPathAddOneAtTheEnd.getString("data.came_from_name");
//        System.out.println("Came from name - " + cameFromNameAddOneAtTheEnd);
        Assert.assertTrue(cameFromNameAddOneAtTheEnd.contains("pu"));
        // step 38 - Change one letter in the end
        Response responseChangeOneAtInEnd = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","pq")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathChangeOneAtInEnd = responseChangeOneAtInEnd.jsonPath();

        String dataChangeOneAtInEnd = jsonPathChangeOneAtInEnd.getString("data[]");
        if (dataChangeOneAtInEnd.equals("[]")){
            dataChangeOneAtInEnd = null;
        }
        Assert.assertNull(dataChangeOneAtInEnd);
        // step 39 - Add one letter at the beginning
        Response responseAddOneAtTheStart = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","qpu")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathAddOneAtTheStart = responseAddOneAtTheStart.jsonPath();

        String cameFromNameAddOneAtTheStart = jsonPathAddOneAtTheStart.getString("data.came_from_name");
//        System.out.println("Came from name - " + cameFromNameAddOneAtTheStart);
        Assert.assertTrue(cameFromNameAddOneAtTheStart.contains("pu"));
        // step 40 - Add one integer in the middle
        Response responseAddOneInTheMiddle = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","p1u")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathAddOneInTheMiddle = responseAddOneInTheMiddle.jsonPath();

        String cameFromNameAddOneInTheMiddle = jsonPathAddOneInTheMiddle.getString("data.came_from_name");
//        System.out.println("Came from name - " + cameFromNameAddOneInTheMiddle);
        Assert.assertTrue(cameFromNameAddOneInTheMiddle.contains("pu"));
        // step 41 - Change one first letter
        Response responseChangeOneFirst = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","qu")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathChangeOneFirst = responseChangeOneFirst.jsonPath();

        String dataChangeOneFirst = jsonPathChangeOneFirst.getString("data[]");
        if (dataChangeOneFirst.equals("[]")){
            dataChangeOneFirst = null;
        }
        Assert.assertNull(dataChangeOneFirst);
        // step 42 - Add two letters/integers
        Response responseAddTwo = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","puqq")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathAddTwo = responseAddTwo.jsonPath();

        String dataAddTwo = jsonPathAddTwo.getString("data[]");
        if (dataAddTwo.equals("[]")){
            dataAddTwo = null;
        }
        Assert.assertNull(dataAddTwo);
    }

    @Test
    public void step47(){
        // step 36 - Default value
        Response responseDefault = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","mv")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathDefault = responseDefault.jsonPath();

        String signerNameDefault = jsonPathDefault.getString("data.signer_name");
//        System.out.println("Signer name - " + signerNameDefault);
        Assert.assertTrue(signerNameDefault.contains("mv"));
        // step 37 - Add one letter at the end
        Response responseAddOneAtTheEnd = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","mvq")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathAddOneAtTheEnd = responseAddOneAtTheEnd.jsonPath();

        String signerNameAddOneAtTheEnd = jsonPathAddOneAtTheEnd.getString("data.signer_name");
//        System.out.println("Signer name - " + signerNameAddOneAtTheEnd);
        Assert.assertTrue(signerNameAddOneAtTheEnd.contains("mv"));
        // step 38 - Change one letter in the end
        Response responseChangeOneAtInEnd = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","mq")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathChangeOneAtInEnd = responseChangeOneAtInEnd.jsonPath();

        String dataChangeOneAtInEnd = jsonPathChangeOneAtInEnd.getString("data[]");
        if (dataChangeOneAtInEnd.equals("[]")){
            dataChangeOneAtInEnd = null;
        }
        Assert.assertNull(dataChangeOneAtInEnd);
        // step 39 - Add one letter at the beginning
        Response responseAddOneAtTheStart = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","qmv")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathAddOneAtTheStart = responseAddOneAtTheStart.jsonPath();

        String signerNameAddOneAtTheStart = jsonPathAddOneAtTheStart.getString("data.signer_name");
//        System.out.println("Signer name - " + signerNameAddOneAtTheStart);
        Assert.assertTrue(signerNameAddOneAtTheStart.contains("mv"));
        // step 40 - Add one integer in the middle
        Response responseAddOneInTheMiddle = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","m1v")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathAddOneInTheMiddle = responseAddOneInTheMiddle.jsonPath();

        String signerNameAddOneInTheMiddle = jsonPathAddOneInTheMiddle.getString("data.signer_name");
//        System.out.println("Signer name - " + signerNameAddOneInTheMiddle);
        Assert.assertTrue(signerNameAddOneInTheMiddle.contains("mv"));
        // step 41 - Change one first letter
        Response responseChangeOneFirst = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","qv")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathChangeOneFirst = responseChangeOneFirst.jsonPath();

        String dataChangeOneFirst = jsonPathChangeOneFirst.getString("data[]");
        if (dataChangeOneFirst.equals("[]")){
            dataChangeOneFirst = null;
        }
        Assert.assertNull(dataChangeOneFirst);
        // step 42 - Add two letters/integers
        Response responseAddTwo = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("query","mvqq")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathAddTwo = responseAddTwo.jsonPath();

        String dataAddTwo = jsonPathAddTwo.getString("data[]");
        if (dataAddTwo.equals("[]")){
            dataAddTwo = null;
        }
        Assert.assertNull(dataAddTwo);
    }

    @Test
    public void step48(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("sed_reg_number","12.01.2024")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String sedRegNumber = jsonPath.getString("data.sed_reg_number");
        //System.out.println(sedRegNumber);
        Assert.assertTrue(sedRegNumber.contains("12.01.2024"));
    }

    @Test
    public void step49(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("sed_reg_number","1")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String sedRegNumber = jsonPath.getString("data.sed_reg_number");
        System.out.println(sedRegNumber);
        Assert.assertTrue(sedRegNumber.contains("12.01.2024"));
    }

    @Test
    public void step50(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("external_document_number","12.01.2024")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String data = jsonPath.getString("data[]");
        if (data.equals("[]")){
            data = null;
        }
        Assert.assertNull(data);
    }

    @Test
    public void step51(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .params("items","1000")
                .params("external_document_number","1")
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String data = jsonPath.getString("data[]");
        if (data.equals("[]")){
            data = null;
        }
        Assert.assertNull(data);
    }

    @Test
    public void step52(){
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("role_ids", Arrays.asList(29));
        requestBody.put("control_subject_id", 122);
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBody)
                .put(API_USERS + "/1")
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String idUser = jsonPath.getString("data.id");
        String officialIdUser = jsonPath.getString("data.official_id");
        String providerIdUser = jsonPath.getString("data.provider_id");
        String fullNameUser = jsonPath.getString("data.full_name");
        String positionUser = jsonPath.getString("data.position");
        String organizationUser = jsonPath.getString("data.organization_short_title");
        String providerNameUser = jsonPath.getString("data.provider_name");
        String phoneUser = jsonPath.get("data.phone");
        String emailUser = jsonPath.getString("data.email");
        String rolesIdUser = jsonPath.getString("data.roles[0].id");
        String rolesNameUser = jsonPath.getString("data.roles[0].full_name");
        String controlSubjectUser = jsonPath.getString("data.control_subject.id");

        System.out.println("id - " + idUser);
        System.out.println("official id - " + officialIdUser);
        System.out.println("provider id - " + providerIdUser);
        System.out.println("full name - " + fullNameUser);
        System.out.println("position - " + positionUser);
        System.out.println("organization - " + organizationUser);
        System.out.println("provider name - " + providerNameUser);
        System.out.println("phone - " + phoneUser);
        System.out.println("email - " + emailUser);
        System.out.println("role id - " + rolesIdUser);
        System.out.println("role name - " + rolesNameUser);
        System.out.println("control subject id - " + controlSubjectUser);

        Assert.assertTrue(idUser.equals("1"));
        Assert.assertTrue(officialIdUser.equals("51bace4af273695ed1000128"));
        Assert.assertTrue(providerIdUser.equals("525e9f767da3000002000001"));
        Assert.assertTrue(fullNameUser.equals("Колокольцев Владимир Александрович"));
        Assert.assertTrue(positionUser.equals("Министр"));
        Assert.assertTrue(organizationUser.equals("Центральный аппарат МВД РФ"));
        Assert.assertTrue(providerNameUser.equals("Департамент делопроизводства " +
                "и работы с обращениями граждан и организаций Министерства внутренних дел " +
                "Российской Федерации (ДДО МВД России)"));
        Assert.assertTrue(phoneUser.equals("(495)123-45-67 (123)"));
        Assert.assertTrue(emailUser.equals("dsa@qwerty.ru"));
        Assert.assertTrue(rolesIdUser.equals("29"));
        Assert.assertTrue(rolesNameUser.equals("Все права"));
        Assert.assertTrue(controlSubjectUser.equals("122"));
    }

    @Test
    public void step53(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();


        List<Object> data = jsonPath.getList("data");
        int count = data.size();
//        System.out.println("Count of element: " + count);
        Assert.assertTrue(count == 10);

        List<Object> SedRegNumbers = jsonPath.getList("data.sed_reg_number");
//        System.out.println(SedRegNumbers);
        ArrayList<Object> SedRegNumbersSorted = new ArrayList<>(SedRegNumbers);
        Collections.sort(SedRegNumbersSorted,Collections.reverseOrder());
//        System.out.println(SedRegNumbersSorted);
        Assert.assertTrue(SedRegNumbers.equals(SedRegNumbersSorted));
    }

    @Test
    public void step54(){
        Response response = given()
                .when()
                .params("page","2")
                .params("items","10")
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        List<Object> data = jsonPath.getList("data");
        int count = data.size();
//        System.out.println("Count of element: " + count);
        Assert.assertNotNull(count);
    }

    @Test
    public void step55(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 27)
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String data = jsonPath.getString("data[]");
        if (data.equals("[]")){
            data = null;
        }
        Assert.assertNull(data);
    }

    @Test
    public void step56(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 2)
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        List<Object> data = jsonPath.getList("data");
        int count = data.size();
//        System.out.println("Count of element: " + count);
        Assert.assertNotNull(count);
    }

    @Test
    public void step57(){
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 2)
                .body(new File("src/test/java/SKIP_API/json_files/SKIP_129_step02.json"))
                .post(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String skipRegDate = jsonPath.getString("data.skip_reg_date");
        String shortDescription = jsonPath.getString("data.short_description");
        String controlOfficialId = jsonPath.getString("data.control_official.id");
        String controlSubjectId = jsonPath.getString("data.control_subject.id");
        String documentName = jsonPath.getString("data.document_name");
        String documentExecutionStateId = jsonPath.getString("data.document_execution_state.id");
        String documentTypeId = jsonPath.getString("data.skip_document_type.id");
        String internal = jsonPath.getString("data.internal");
        String notSedDocument = jsonPath.getString("data.not_sed_document");
        String sedRegNumber = jsonPath.getString("data.sed_reg_number");
        String sedDocumentDate = jsonPath.getString("data.sed_document_date");
        String cameFromName = jsonPath.getString("data.came_from_name");
        String externalDocumentNumber = jsonPath.getString("data.external_document_number");
        String signerName = jsonPath.getString("data.signer_name");

//        System.out.println("Skip reg date - " + skipRegDate);
//        System.out.println("Short description - " + shortDescription);
//        System.out.println("Control official id - " + controlOfficialId);
//        System.out.println("Control subject id - " + controlSubjectId);
//        System.out.println("Document name - " + documentName);
//        System.out.println("Document execution state id - " + documentExecutionStateId);
//        System.out.println("Document type id - " + documentTypeId);
//        System.out.println("Internal - " + internal);
//        System.out.println("Not sed document - " + notSedDocument);
//        System.out.println("Sed reg number - " + sedRegNumber);
//        System.out.println("Sed document date - " + sedDocumentDate);
//        System.out.println("Came from name - " + cameFromName);
//        System.out.println("External Document number - " + externalDocumentNumber);
//        System.out.println("Signer name - " + signerName);

        Assert.assertTrue(skipRegDate.equals("2024-01-11T12:37:45.190Z"));
        Assert.assertTrue(shortDescription.equals("palenose"));
        Assert.assertTrue(controlOfficialId.equals("51bace4af273695ed1000128"));
        Assert.assertTrue(controlSubjectId.equals("122"));
        Assert.assertTrue(documentName.equals("sublex"));
        Assert.assertTrue(documentExecutionStateId.equals("2"));
        Assert.assertTrue(documentTypeId.equals("1"));
        Assert.assertTrue(internal.equals("true"));
        Assert.assertTrue(notSedDocument.equals("true"));
        Assert.assertTrue(sedRegNumber.equals("12.01.2024"));
        Assert.assertTrue(sedDocumentDate.equals("2024-01-11T12:37:45.190Z"));
        Assert.assertTrue(cameFromName.equals("purplese"));
        Assert.assertTrue(externalDocumentNumber.equals("Yellowse"));
        Assert.assertTrue(signerName.equals("mvolobse"));

        Response responseGet = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 2)
                .get(API_DOCUMENTS )
                .then().log().all()
                .extract().response();

        JsonPath jsonPathGet = responseGet.jsonPath();

        List<Object> data = jsonPathGet.getList("data");
        int count = data.size();
        System.out.println("Count of element: " + count);
        Assert.assertNotNull(count);
    }
}
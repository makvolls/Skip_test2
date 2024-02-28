package SKIP_API;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.*;

import static io.restassured.RestAssured.given;

public class SKIP_208 {
    // Endpoints:
    private static String API_EXECUTOR_DEADLINES = "http://api.skip.rtech.ru/v1/assignments/executor_deadlines";
    private static String API_USER = "http://api.skip.rtech.ru/v1/permissions/users";
    private static String API_DOCUMENTS = "http://api.skip.rtech.ru/v1/documents";
    private static String API_DEADLINE_BASES = "http://api.skip.rtech.ru/v1/classifiers/deadline_bases";
    private static String API_CONTROL_SUBJECTS = "http://api.skip.rtech.ru/v1/permissions/control_subjects";
    private static String API_DOC_TYPE = "http://api.skip.rtech.ru/v1/classifiers/document_types";
    private static String API_DOCUMENT_EXECUTION_STATES = "http://api.skip.rtech.ru" +
            "/v1/classifiers/document_execution_states";
    private static String API_PERIODICITY = "http://api.skip.rtech.ru/v1/classifiers/periodicity";
    private static String API_OSHS_MVD_OFFICIALS="http://api.skip.rtech.ru/v1/oshs/mvd/officials";
    private static String API_ASSIGNMENT_EXECUTION_STATES = "http://api.skip.rtech.ru/v1/" +
            "classifiers/assignment_execution_states";

    // Authorization parameters:
    int userIdOne = 1; // User that have all rights, role id = 29
    int userIdSix = 6; // User that have right - "show_roles"

    // Value for keys:
    int idCreatedExecutorDeadlineCalculation;
    String deadlineDate = "2024-03-01T09:29:10.020Z";
    // Values for document
    int idControlSubject; // Created control subject value
    int idDocumentExecutionState; // Created document execution state value
    int idDocumentType; // Created document type value
    int idDeadlineBases; // Created deadline bases values.
    String idRandomOfficial; // Random official
    int idAssignmentExecutionState; // Created assignment execution state

    // Values:
    // Document SED:
    String uidDocumentSED = "02abb92f03dde0a503a058de94031c1712c14f3bde73062c72cd8d8a94371adbcc";
    String urlDocumentSED = "http://incoming-documents.sed2.rtech.ru/cards/65bb52f0d8fe8d8db3000003";
    String regDateDocumentSED = "23.02.2024";
    String providerIdDocumentSED = "525e9f767da3000002000001";
    String regNumberDocumentSED = "111/65";

    // Document SKIP:
    String documentIdSKIP = "106";

    // Name for created element
    String nameTemplate = "SKIP_208_Autotest";
    String shortNameTemplate = "SKIP_208";
    String uuidFirst;
    String uuidSecond;

    // Files
    File textFile = new File("C:\\Users\\mkalugin\\Desktop\\files for test with files\\text txt.txt");
    File zeroBytesFile = new File("C:\\Users\\mkalugin\\Desktop\\files for test with files\\0bytes.bin");
    File fourHundredMegaBytesFile = new File(
            "C:\\Users\\mkalugin\\Desktop\\files for test with files\\400MB.bin");
    File executableFormatFile =  new File(
            "C:\\Users\\mkalugin\\Desktop\\files for test with files\\wmpshare.exe");

    @BeforeTest
    public void setup(){
        // Random uuid for creation deadline
        uuidFirst = UUID.randomUUID().toString();
        uuidSecond = UUID.randomUUID().toString();
        System.out.println("Created random uuid first value - " + uuidFirst);
        System.out.println("Created random uuid second value - " + uuidSecond);

        // Check that user with id 6 has role with id = 5
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .get(API_USER + "/6")
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String roleId = jsonPath.getString("data.roles[0].id");

        if (roleId.equals("5")){
            System.out.println("User has role id - 5");
        } else {
            System.out.println("User has role id - " + roleId);
            // Change role id
            String requestBody = "{\"role_ids\": [5]}";
            Response responseChangeRoleId = given()
                    .when()
                    .header("Content-Type", "application/json")
                    .header("Test-Authorization", 1)
                    .body(requestBody)
                    .put(API_USER + "/6")
                    .then().log().all()
                    .extract().response();

            JsonPath jsonPathChangeRoleId = responseChangeRoleId.jsonPath();

            String roleIdUpdated = jsonPathChangeRoleId.getString("data.roles[0].id");

            System.out.println("New role id - " + roleIdUpdated);
        }

        // Create and check values for creation of document

        // Create control_subject
        String requestBody = String.format(
                "{\"control_subjects\":[{\"name\": \"%s\",\"fax_number\":\"%s\",\"deleted\":%s,\"provider_id\":\"%s\"}]}"
                , nameTemplate, "1", false, "525e9f767da3000002000001");
        Response responseControlSubjectCreate = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBody)
                .put(API_CONTROL_SUBJECTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathControlSubjectsCreate = responseControlSubjectCreate.jsonPath();

        int id = jsonPathControlSubjectsCreate.getInt("data.find { it.name == '" + nameTemplate + "' }.id");
        System.out.println("Created element's id - " + id);
        idControlSubject = id;

        // Create document execution state
        Map<String, String> requestBodyDocumentExSt = new HashMap<>();
        requestBodyDocumentExSt.put("name", nameTemplate);
        requestBodyDocumentExSt.put("short_name", shortNameTemplate);
        requestBodyDocumentExSt.put("excluded", "false");
        Response responseDocumentExecutionStateCreate = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBodyDocumentExSt)
                .post(API_DOCUMENT_EXECUTION_STATES)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathDocumentExecutionStateCreate = responseDocumentExecutionStateCreate.jsonPath();
        idDocumentExecutionState = jsonPathDocumentExecutionStateCreate.getInt("data.id");
        System.out.println("Id document ex state - " + idDocumentExecutionState);

        // Create document type
        Map<String,Object> requestBodyDocumentType = new HashMap<>();
        requestBodyDocumentType.put("name",nameTemplate);
        requestBodyDocumentType.put("short_name",shortNameTemplate);
        requestBodyDocumentType.put("internal", false);
        requestBodyDocumentType.put("genitive_name",shortNameTemplate);
        requestBodyDocumentType.put("excluded", true);
        Response responseDocumentTypeCreate = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBodyDocumentType)
                .post(API_DOC_TYPE)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathDocumentTypeCreate = responseDocumentTypeCreate.jsonPath();
        idDocumentType = jsonPathDocumentTypeCreate.getInt("data.id");
        System.out.println("Created document type id - " + idDocumentType);

        // Check periodicity
        Response responsePeriodicity = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .get(API_PERIODICITY)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathPeriodicity = responsePeriodicity.jsonPath();
        List<String> namesPeriodicity = jsonPathPeriodicity.getList("data.name");
        for (String namePeriodicity: namesPeriodicity) {
            if (namePeriodicity.equals("Разовая")){
                System.out.println("Periodicity with name - 'Разовая' is existed. It has id - 44.");
            }
            if (namePeriodicity.equals("Раз в год")){
                System.out.println("Periodicity with name - 'Раз в год' is existed. It has id - 43.");
            }
            if (namePeriodicity.equals("Раз в полгода")){
                System.out.println("Periodicity with name - 'Раз в полгода' is existed. It has id - 42.");
            }
            if (namePeriodicity.equals("Раз в квартал")){
                System.out.println("Periodicity with name - 'Раз в квартал' is existed. It has id - 41.");
            }
            if (namePeriodicity.equals("Каждый месяц")){
                System.out.println("Periodicity with name - 'Каждый месяц' is existed. It has id - 40.");
            }
            if (namePeriodicity.equals("Каждую декаду")){
                System.out.println("Periodicity with name - 'Каждую декаду' is existed. It has id - 39.");
            }
            if (namePeriodicity.equals("Раз в две недели")){
                System.out.println("Periodicity with name - 'Раз в две недели' is existed. It has id - 38.");
            }
            if (namePeriodicity.equals("Каждую неделю")){
                System.out.println("Periodicity with name - 'Каждую неделю' is existed. It has id - 37.");
            }
            if (namePeriodicity.equals("Раз в два дня")){
                System.out.println("Periodicity with name - 'Раз в два дня' is existed. It has id - 36.");
            }
            if (namePeriodicity.equals("Каждый день")){
                System.out.println("Periodicity with name - 'Каждый день' is existed. It has id - 35.");
            }
        }

        // Create deadline bases
        Map<String, Object> requestBodyDeadlineBasesFirst = new HashMap<>();
        requestBodyDeadlineBasesFirst.put("name",nameTemplate);
        requestBodyDeadlineBasesFirst.put("short_name",shortNameTemplate);
        requestBodyDeadlineBasesFirst.put("note","1");
        requestBodyDeadlineBasesFirst.put("duration_id","3_days");
        requestBodyDeadlineBasesFirst.put("deadline_only",false);
        requestBodyDeadlineBasesFirst.put("excluded",false);
        Response responseDeadlineBasesCreateFirst = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBodyDeadlineBasesFirst)
                .post(API_DEADLINE_BASES)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathDeadlineBasesCreateFirst = responseDeadlineBasesCreateFirst.jsonPath();
        idDeadlineBases = jsonPathDeadlineBasesCreateFirst.getInt("data.id");
        System.out.println("Deadline bases first element id - " + idDeadlineBases);

        // Random official from officials_oshs_mvd
        Response responseOfficials = given()
                .when()
                .params("items","100")
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .get(API_OSHS_MVD_OFFICIALS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathOfficials = responseOfficials.jsonPath();

        List<String> idOfficials = jsonPathOfficials.getList("data.id");
        Random randomOff = new Random();
        String randomIdOff = idOfficials.get(randomOff.nextInt(idOfficials.size()));
        System.out.println("Random id : " + randomIdOff);
        idRandomOfficial = randomIdOff;

        // Create assignment execution state
        Map<String, Object> requestBodyAssignmentExecutionState = new HashMap<>();
        requestBodyAssignmentExecutionState.put("name",nameTemplate);
        requestBodyAssignmentExecutionState.put("short_name",shortNameTemplate);
        requestBodyAssignmentExecutionState.put("excluded",false);
        Response responseAssignmentExecutionState = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBodyAssignmentExecutionState)
                .post(API_ASSIGNMENT_EXECUTION_STATES)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathAssignmentExecutionState = responseAssignmentExecutionState.jsonPath();
        idAssignmentExecutionState = jsonPathAssignmentExecutionState.getInt("data.id");
        System.out.println("Assignment execution state id - " + idAssignmentExecutionState);

        // Create document with executor_deadline_calculation
        String requestBodyDocument = "{\n" +
                "    \"skip_reg_date\": \"2024-01-30T06:06:31.485Z\",\n" +
                "    \"control_official_id\": \"" + idRandomOfficial + "\",\n" +
                "    \"control_subject_id\": " + idControlSubject + ",\n" +
                "    \"document_name\": \"Тест 348\",\n" +
                "    \"document_execution_state_id\": " + idDocumentExecutionState + ",\n" +
                "    \"skip_document_type_id\": " + idDocumentType + ",\n" +
                "    \"internal\": false,\n" +
                "    \"short_description\": \"348\",\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"number\": \"1\",\n" +
                "            \"content\": \"content\",\n" +
                "            \"executor_deadline_calculation\": {\n" +
                "                \"periodicity_id\": " + 36 + ",\n" +
                "                \"start_date\": \"2024-03-01T09:29:10.020Z\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBases + ",\n" +
                "                \"execution_state_id\": " + idAssignmentExecutionState + ",\n" +
                "                \"deadline_date\": \"2024-03-21T09:29:10.020Z\",\n" +
                "                \"deadlines\": [\n" +
                "                    {\n" +
                "                        \"id\": \"" + uuidFirst + "\",\n" +
                "                        \"deadline_date\": \"2024-03-10T09:29:10.020Z\",\n" +
                "                        \"deadline_basis_id\": " + idDeadlineBases + ",\n" +
                "                        \"description\": \"desc\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"id\": \"" + uuidSecond + "\",\n" +
                "                        \"deadline_date\": \"2024-03-12T09:29:10.020Z\",\n" +
                "                        \"deadline_basis_id\": " + idDeadlineBases + ",\n" +
                "                        \"description\": \"desc\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        Response responseCreateDocument = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", userIdOne)
                .body(requestBodyDocument)
                .post(API_DOCUMENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPathCreateDocument = responseCreateDocument.jsonPath();

        idCreatedExecutorDeadlineCalculation = jsonPathCreateDocument.
                getInt("data.assignments[0].executor_deadline_calculation.id");
        System.out.println("Created executor deadline calculation id - " + idCreatedExecutorDeadlineCalculation);
    }

    @AfterTest
    public void tearDown(){
        // Delete control_subject_id
        String requestBodyCS = "{\n" +
                "  \"control_subjects\": [\n" +
                "    {\n" +
                "      \"id\": " + idControlSubject + ",\n" +
                "      \"provider_id\": \"525e9f767da3000002000001\",\n" +
                "      \"deleted\": true\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        Response responseControlSubjectDelete = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBodyCS)
                .put(API_CONTROL_SUBJECTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathControlSubjectsDelete = responseControlSubjectDelete.jsonPath();

        // Delete document execution state
        Response responseDESDeleteFirst = (Response) given()
                .header("Test-Authorization", 1)
                .delete(API_DOCUMENT_EXECUTION_STATES + String.format("/%s",idDocumentExecutionState))
                .then().log().all()
                .extract();

        //JsonPath jsonPathDESDeleteFirst = responseDESDeleteFirst.jsonPath();

        // Delete document type
        Response responseDocumentType = (Response) given()
                .header("Test-Authorization", 1)
                .delete(API_DOC_TYPE + String.format("/%s",idDocumentType))
                .then().log().all()
                .extract();

        // JsonPath jsonPathDocumentType = responseDocumentType.jsonPath();

        // Delete deadline_basis_id
        Response responseDeadlineBaseDeleteFirst = (Response) given()
                .header("Test-Authorization", 1)
                .delete(API_DEADLINE_BASES + String.format("/%s", idDeadlineBases))
                .then().log().all()
                .extract();

        // JsonPath jsonPathDeadlineBaseDeleteFirst = responseDeadlineBaseDeleteFirst.jsonPath();

        // Delete assignment execution state
        Response responseAssignmentExSt = (Response) given()
                .header("Test-Authorization", 1)
                .delete(API_ASSIGNMENT_EXECUTION_STATES + String.format("/%s",idAssignmentExecutionState))
                .then().log().all()
                .extract();

        //JsonPath jsonPathAssignmentExST = responseAssignmentExSt.jsonPath();
    }

    @Test
    public void step01(){
        Response response = given()
                .when()
                .multiPart("executor_deadline_calculation_id", idCreatedExecutorDeadlineCalculation)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDeadlineBases)
                .multiPart("document_name","name")
                .multiPart("document_type","Sed::Document")
                .multiPart("sed_document[uid]", uidDocumentSED)
                .multiPart("sed_document[url]",urlDocumentSED)
                .multiPart("sed_document[reg_date]",regDateDocumentSED)
                .multiPart("sed_document[provider_id]",providerIdDocumentSED)
                .multiPart("sed_document[reg_number]",regNumberDocumentSED)
                .multiPart("content","content")
                .multiPart("images[][original_file]",textFile)
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .post(API_EXECUTOR_DEADLINES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String deadline_id = jsonPath.getString("data.deadline.id");
        String deadline_date = jsonPath.getString("data.deadline.deadline_date");
        String content = jsonPath.getString("data.deadline.content");
        String document_name = jsonPath.getString("data.deadline.document_name");
        String document_type = jsonPath.getString("data.deadline.document_type");
        String document_id = jsonPath.getString("data.deadline.document_id");
        String document_url = jsonPath.getString("data.deadline.document_url");
        int deadline_basis_id = jsonPath.getInt("data.deadline.deadline_basis.id");
        String images_name = jsonPath.getString("data.deadline.images[0].full_name");

        System.out.println("Deadline id - " + deadline_id);
        System.out.println("Deadline date - " + deadline_date);
        System.out.println("Content - " + content);
        System.out.println("Document name - " + document_name);
        System.out.println("Document type - " + document_type);
        System.out.println("Document id - " + document_id);
        System.out.println("Document url - " + document_url);
        System.out.println("Deadline basis id - " + deadline_basis_id);
        System.out.println("Image's name - " + images_name);

        Assert.assertTrue(deadline_date.equals(deadlineDate));
        Assert.assertTrue(content.equals("content"));
        Assert.assertTrue(document_name.equals("name"));
        Assert.assertTrue(document_type.equals("Sed::Document"));
        // Document with uid = "02abb92f03dde0a503a058de94031c1712c14f3bde73062c72cd8d8a94371adbcc" has id = 29
        Assert.assertTrue(document_id.equals("29"));
        Assert.assertTrue(document_url.equals(urlDocumentSED));
        Assert.assertTrue(deadline_basis_id == idDeadlineBases);
        Assert.assertTrue(images_name.equals("text txt.txt"));
    }

    @Test
    public void step02(){
        Response response = given()
                .when()
                .multiPart("executor_deadline_calculation_id", idCreatedExecutorDeadlineCalculation)
                .multiPart("deadline_date", deadlineDate)
                .multiPart("deadline_basis_id", idDeadlineBases)
                .multiPart("document_name","name")
                .multiPart("document_type","Documents::Document")
                .multiPart("document_id",documentIdSKIP)
                .multiPart("content","content")
                .multiPart("images[][original_file]",textFile)
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .post(API_EXECUTOR_DEADLINES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.deadline_date[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("уже существует"));
    }

    @Test
    public void step03(){
        Response response = given()
                .when()
                .multiPart("executor_deadline_calculation_id", idCreatedExecutorDeadlineCalculation)
                .multiPart("deadline_date", "2024-03-02T09:29:10.020Z")
                .multiPart("deadline_basis_id", idDeadlineBases)
                .multiPart("document_name","name")
                .multiPart("document_type","Documents::Document")
                .multiPart("document_id",documentIdSKIP)
                .multiPart("content","content")
                .multiPart("images[][original_file]",textFile)
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .post(API_EXECUTOR_DEADLINES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String deadline_date = jsonPath.getString("data.deadline.deadline_date");
        String content = jsonPath.getString("data.deadline.content");
        String document_name = jsonPath.getString("data.deadline.document_name");
        String document_type = jsonPath.getString("data.deadline.document_type");
        String document_id = jsonPath.getString("data.deadline.document_id");
        String document_url = jsonPath.getString("data.deadline.document_url");
        int deadline_basis_id = jsonPath.getInt("data.deadline.deadline_basis.id");
        String images_name = jsonPath.getString("data.deadline.images[0].full_name");

        System.out.println("Deadline date - " + deadline_date);
        System.out.println("Content - " + content);
        System.out.println("Document name - " + document_name);
        System.out.println("Document type - " + document_type);
        System.out.println("Document id - " + document_id);
        System.out.println("Document url - " + document_url);
        System.out.println("Deadline basis id - " + deadline_basis_id);
        System.out.println("Image's name - " + images_name);

        Assert.assertTrue(deadline_date.equals("2024-03-02T09:29:10.020Z"));
        Assert.assertTrue(content.equals("content"));
        Assert.assertTrue(document_name.equals("name"));
        Assert.assertTrue(document_type.equals("Documents::Document"));
        Assert.assertTrue(document_id.equals(documentIdSKIP));
        Assert.assertTrue(document_url.equals("http://skip.rtech.ru/document/106"));
        Assert.assertTrue(deadline_basis_id == idDeadlineBases);
        Assert.assertTrue(images_name.equals("text txt.txt"));
    }

    @Test
    public void step04(){
        Response response = given()
                .when()
                .multiPart("executor_deadline_calculation_id", idCreatedExecutorDeadlineCalculation)
                .multiPart("deadline_date", "2024-03-02T09:29:10.020Z")
                .multiPart("deadline_basis_id", idDeadlineBases)
                .multiPart("document_name","name")
                .multiPart("document_type","Documents::Document")
                .multiPart("document_id",documentIdSKIP)
                .multiPart("content","content")
                .multiPart("images[][original_file]",textFile)
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .post(API_EXECUTOR_DEADLINES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.deadline_date[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("уже существует"));
    }

    @Test
    public void step05(){
        Response response = given()
                .when()
                .multiPart("deadline_date", "2024-03-02T09:29:10.020Z")
                .multiPart("deadline_basis_id", idDeadlineBases)
                .multiPart("document_name","name")
                .multiPart("document_type","Documents::Document")
                .multiPart("document_id",documentIdSKIP)
                .multiPart("content","content")
                .multiPart("images[][original_file]",textFile)
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .post(API_EXECUTOR_DEADLINES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.executor_deadline_calculation_id[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("не может быть пустым"));
    }

    @Test
    public void step06(){
        Response response = given()
                .when()
                .multiPart("executor_deadline_calculation_id", "10000")
                .multiPart("deadline_date", "2024-03-02T09:29:10.020Z")
                .multiPart("deadline_basis_id", idDeadlineBases)
                .multiPart("document_name","name")
                .multiPart("document_type","Documents::Document")
                .multiPart("document_id",documentIdSKIP)
                .multiPart("content","content")
                .multiPart("images[][original_file]",textFile)
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .post(API_EXECUTOR_DEADLINES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.executor_deadline_calculation_id[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step07(){
        Response response = given()
                .when()
                .multiPart("executor_deadline_calculation_id", idCreatedExecutorDeadlineCalculation)
                .multiPart("deadline_basis_id", idDeadlineBases)
                .multiPart("document_name","name")
                .multiPart("document_type","Documents::Document")
                .multiPart("document_id",documentIdSKIP)
                .multiPart("content","content")
                .multiPart("images[][original_file]",textFile)
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .post(API_EXECUTOR_DEADLINES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.deadline_date[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("не может быть пустым"));
    }

    @Test
    public void step08(){
        Response response = given()
                .when()
                .multiPart("executor_deadline_calculation_id", idCreatedExecutorDeadlineCalculation)
                .multiPart("deadline_date", "1")
                .multiPart("deadline_basis_id", idDeadlineBases)
                .multiPart("document_name","name")
                .multiPart("document_type","Documents::Document")
                .multiPart("document_id",documentIdSKIP)
                .multiPart("content","content")
                .multiPart("images[][original_file]",textFile)
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .post(API_EXECUTOR_DEADLINES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.deadline_date[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет неверное значение"));
    }

    @Test
    public void step09(){
        Response response = given()
                .when()
                .multiPart("executor_deadline_calculation_id", idCreatedExecutorDeadlineCalculation)
                .multiPart("deadline_date", "2024-03-12T09:29:10.020Z")
                .multiPart("deadline_basis_id", idDeadlineBases)
                .multiPart("document_name","name")
                .multiPart("document_type","Documents::Document")
                .multiPart("document_id",documentIdSKIP)
                .multiPart("content","content")
                .multiPart("images[][original_file]",textFile)
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .post(API_EXECUTOR_DEADLINES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.deadline_date[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("уже существует"));
    }

    @Test
    public void step10(){
        Response response = given()
                .when()
                .multiPart("executor_deadline_calculation_id", idCreatedExecutorDeadlineCalculation)
                .multiPart("deadline_date", "2024-03-13T09:29:10.020Z")
                .multiPart("document_name","name")
                .multiPart("document_type","Documents::Document")
                .multiPart("document_id",documentIdSKIP)
                .multiPart("content","content")
                .multiPart("images[][original_file]",textFile)
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .post(API_EXECUTOR_DEADLINES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.deadline_basis_id[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("не может быть пустым"));
    }

    @Test
    public void step11(){
        Response response = given()
                .when()
                .multiPart("executor_deadline_calculation_id", idCreatedExecutorDeadlineCalculation)
                .multiPart("deadline_date", "2024-03-13T09:29:10.020Z")
                .multiPart("deadline_basis_id", "10000")
                .multiPart("document_name","name")
                .multiPart("document_type","Documents::Document")
                .multiPart("document_id",documentIdSKIP)
                .multiPart("content","content")
                .multiPart("images[][original_file]",textFile)
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .post(API_EXECUTOR_DEADLINES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.deadline_basis_id[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step12(){
        Response response = given()
                .when()
                .multiPart("executor_deadline_calculation_id", idCreatedExecutorDeadlineCalculation)
                .multiPart("deadline_date", "2024-03-13T09:29:10.020Z")
                .multiPart("deadline_basis_id", idDeadlineBases)
                .multiPart("document_name","name")
                .multiPart("document_type","Documents::Document")
                .multiPart("document_id",documentIdSKIP)
                .multiPart("content","content")
                .multiPart("images[][original_file]",zeroBytesFile)
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .post(API_EXECUTOR_DEADLINES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.images.0.original_file[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("Файл размером 0 байт не может быть добавлен"));
    }

    @Test
    public void step13(){
//        Response response = given()
//                .when()
//                .multiPart("executor_deadline_calculation_id", idCreatedExecutorDeadlineCalculation)
//                .multiPart("deadline_date", "2024-03-13T09:29:10.020Z")
//                .multiPart("deadline_basis_id", idDeadlineBases)
//                .multiPart("document_name","name")
//                .multiPart("document_type","Documents::Document")
//                .multiPart("document_id",documentIdSKIP)
//                .multiPart("content","content")
//                .multiPart("images[][original_file]",fourHundredMegaBytesFile)
//                .contentType(ContentType.MULTIPART)
//                .header("Test-Authorization", userIdOne)
//                .post(API_EXECUTOR_DEADLINES)
//                .then().log().all()
//                .extract().response();
//        JsonPath jsonPath = response.jsonPath();
//
//        String errorMessage = jsonPath.getString("errors.images.0.original_file[0]");
//
//        System.out.println("Error message - " + errorMessage);
//
//        Assert.assertTrue(errorMessage.equals("Превышен максимальный размер загружаемого файла (300 МБ)"));
    }

    @Test
    public void step14(){
        Response response = given()
                .when()
                .multiPart("executor_deadline_calculation_id", idCreatedExecutorDeadlineCalculation)
                .multiPart("deadline_date", "2024-03-13T09:29:10.020Z")
                .multiPart("deadline_basis_id", idDeadlineBases)
                .multiPart("document_name","name")
                .multiPart("document_type","Documents::Document")
                .multiPart("document_id",documentIdSKIP)
                .multiPart("content","content")
                .multiPart("images[][original_file]",executableFormatFile)
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .post(API_EXECUTOR_DEADLINES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.images.0.original_file[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.
                equals("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));
    }

    @Test
    public void step15(){
        Response response = given()
                .when()
                .multiPart("executor_deadline_calculation_id", idCreatedExecutorDeadlineCalculation)
                .multiPart("deadline_date", "2024-03-13T09:29:10.020Z")
                .multiPart("deadline_basis_id", idDeadlineBases)
                .multiPart("document_name","name")
                .multiPart("document_type","Documents::Document")
                .multiPart("document_id",documentIdSKIP)
                .multiPart("content","content")
                .multiPart("images[][original_file]",executableFormatFile)
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdSix)
                .post(API_EXECUTOR_DEADLINES)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("error");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("Доступ к ресурсу запрещен"));
    }
}

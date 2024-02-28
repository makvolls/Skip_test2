package SKIP_API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.*;

import static io.restassured.RestAssured.given;

public class SKIP_205 {
    // Endpoints:
    private static String API_EXECUTION_EVENTS = "http://api.skip.rtech.ru/v1/executions/events";
    private static String API_USER = "http://api.skip.rtech.ru/v1/permissions/users";
    private static String API_DOCUMENTS = "http://api.skip.rtech.ru/v1/documents";
    private static String API_EVENTS = "http://api.skip.rtech.ru/v1/classifiers/events";
    private static String API_EVENT_STATES = "http://api.skip.rtech.ru/v1/classifiers/event_states";
    private static String API_DEADLINE_BASES = "http://api.skip.rtech.ru/v1/classifiers/deadline_bases";
    private static String API_CONTROL_PERIOD = "http://api.skip.rtech.ru/v1/classifiers/control_period_states";
    private static String API_CONTROL_SUBJECTS = "http://api.skip.rtech.ru/v1/permissions/control_subjects";
    private static String API_DOC_TYPE = "http://api.skip.rtech.ru/v1/classifiers/document_types";
    private static String API_DOCUMENT_EXECUTION_STATES = "http://api.skip.rtech.ru" +
            "/v1/classifiers/document_execution_states";
    private static String API_PERIODICITY = "http://api.skip.rtech.ru/v1/classifiers/periodicity";
    private static String API_OSHS_MVD_OFFICIALS="http://api.skip.rtech.ru/v1/oshs/mvd/officials";
    private static String API_ORGANIZATIONS="http://api.skip.rtech.ru/v1/oshs/mvd/organizations";
    // Authorization parameters:
    int userIdOne = 1; // User that have all rights, role id = 29
    int userIdSix = 6; // User that have right - "show_roles"
    // Value for keys:
    int idCreatedExecution; // Created execution id in before test
    int idCreatedEvent; // Created event value
    int idCreatedState; // Created event state value
    int idCreatedControlState; // Created control period state value
    // Values for document
    int idControlSubject; // Created control subject value
    int idDocumentExecutionState; // Created document execution state value
    int idDocumentType; // Created document type value
    int idDeadlineBases; // Created deadline bases values. Also used as value for request body in events
    String idRandomOfficial; // Random official
    String idRandomMvdOrganization; // Random organization
    // Values for GET,PUT,DELETE requests
    int idCreatedExecutionEventSKIP; // Created event with SKIP document
    int idCreatedExecutionEventSED; // Created event with SED document
    int idCreatedImage; // Created image in step 26
    // Data from documents:
    private static String documentSED = "http://incoming-documents.sed2.rtech.ru/cards/65bb52f0d8fe8d8db3000003";
    private static String documentSKIP = "http://api.skip.rtech.ru/v1/documents/106";

    // Values:
    // Document SED:
    String uidDocumentSED = "02abb92f03dde0a503a058de94031c1712c14f3bde73062c72cd8d8a94371adbcc";
    String urlDocumentSED = "http://incoming-documents.sed2.rtech.ru/cards/65bb52f0d8fe8d8db3000003";
    String regDateDocumentSED = "23.02.2024";
    String providerIdDocumentSED = "525e9f767da3000002000001";
    String regNumberDocumentSED = "111/65";
    // Document SED values for check changes:
    String uidDocumentSEDSecondDocument = "01ddab5a40b066395ff580f63cb5a687a444b6ad8641c5e82d81935bd1205d6bcc";
    String urlDocumentSEDSecondDocument = "http://citizen-requests.sed2.rtech.ru/cards/65a109aa5a85000030000001";
    String regDateDocumentSEDSecondDocument = "19.04.2024";
    String providerIdDocumentSEDSecondDocument = "525e9f767da3000002000001";
    String regNumberDocumentSEDSecondDocument = "3/247700035165";
    // Document SKIP:
    String documentIdSKIP = "106";
    String documentUrlSKIP = "http://skip.rtech.ru/document/106";
    // Name for created element
    String nameTemplate = "SKIP_205_Autotest";
    String shortNameTemplate = "SKIP_205";
    String uuid; // Uuid that will be used un this testcase


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
        uuid = UUID.randomUUID().toString();
        System.out.println("Created random uuid - " + uuid);

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
            if (namePeriodicity.equals("Раз в две недели")){ // used this in testcase
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

        // Random organization from organizations_oshs_mvd
        Response responseOrganizationMvd = given()
                .when()
                .params("items","100")
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .get(API_ORGANIZATIONS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathOrganizationMvd = responseOrganizationMvd.jsonPath();
        List<String> idOrganizationMvd = jsonPathOrganizationMvd.getList("data.id");
        Random randomOrgMvd = new Random();
        String randomIdOrgMvd = idOrganizationMvd.get(randomOrgMvd.nextInt(idOrganizationMvd.size()));
        System.out.println("Random mvd id - " + randomIdOrgMvd);
        idRandomMvdOrganization = randomIdOrgMvd;

        // Create document with execution and deadline
        String requestBodyDocument = "{\n" +
                "    \"skip_reg_date\": \"2024-01-17T06:06:31.485Z\",\n" +
                "    \"control_official_id\": \"" + idRandomOfficial + "\",\n" +
                "    \"control_subject_id\": " + idControlSubject + ",\n" +
                "    \"document_name\": \"Тест 404\",\n" +
                "    \"document_execution_state_id\": " + idDocumentExecutionState + ",\n" +
                "    \"skip_document_type_id\": " + idDocumentType + ",\n" +
                "    \"internal\": false,\n" +
                "    \"short_description\": \"404\",\n" +
                "    \"assignments\": [\n" +
                "        {\n" +
                "            \"number\": \"1\",\n" +
                "            \"content\": \"content\",\n" +
                "            \"executor_deadline_calculation\": {\n" +
                "                \"periodicity_id\": " + 35 + ",\n" +
                "                \"start_date\": \"2024-02-16T13:44:34.154Z\",\n" +
                "                \"deadline_basis_id\": " + idDeadlineBases + ",\n" +
                "                \"execution_state_id\": " + idDocumentExecutionState + ",\n" +
                "                \"deadline_date\": \"2024-02-19T13:44:34.154Z\",\n" +
                "                \"periodicity_day\": 5,\n" +
                "                \"note\": \"string\",\n" +
                "                \"deadlines\": [\n" +
                "                    {\n" +
                "                        \"id\": \"" + uuid + "\",\n" +
                "                        \"deadline_date\": \"2024-02-16T13:44:34.154Z\",\n" +
                "                        \"deadline_basis_id\": " + idDeadlineBases + ",\n" +
                "                        \"description\": \"string\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"executors\": [\n" +
                "                {\n" +
                "                    \"organization_id\": \"" + idRandomMvdOrganization + "\",\n" +
                "                    \"executions\": [\n" +
                "                        {\n" +
                "                            \"deadline_id\": \"" + uuid + "\",\n" +
                "                            \"deadline_basis_id\": " + idDeadlineBases + ",\n" +
                "                            \"note\": \"string\",\n" +
                "                            \"actual_info\": \"string\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            ]\n" +
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

        idCreatedExecution = jsonPathCreateDocument.getInt("data.assignments[0].executors[0].executions[0].id");
        System.out.println("Created execution for this test: " + idCreatedExecution);

        // Check that event with id = 149, 150, 151 existed and create new event
        // Check that event_state with id = 48 is existed
        Response responseEventStateCheck = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .get(API_EVENT_STATES)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathEventStateCheck = responseEventStateCheck.jsonPath();

        List<Integer> eventStateIds = jsonPathEventStateCheck.getList("data.id");

        System.out.println("Event state ids: " + eventStateIds);
        for (int eventStateId: eventStateIds){
            if (eventStateId == 48){
                System.out.println("Event state with id = 48 is existed!");
            }
        }

        // Create new events
        Map<String,Object> requestBodyEvents = new HashMap<>();
        requestBodyEvents.put("name",nameTemplate);
        requestBodyEvents.put("short_name",shortNameTemplate);
        List<Integer> event_state_ids_value = Collections.singletonList(48);
        requestBodyEvents.put("event_state_ids",event_state_ids_value);
        requestBodyEvents.put("excluded", true);
        Response responseEventsCreate = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBodyEvents)
                .post(API_EVENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathEventsCreate = responseEventsCreate.jsonPath();
        idCreatedEvent = jsonPathEventsCreate.getInt("data.id");
        System.out.println("Created document type id - " + idCreatedEvent);

        // Check that element with id = 149, 150, 151
        Response responseEventsList = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .get(API_EVENTS)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathEventsList = responseEventsList.jsonPath();

        List<Integer> eventsIds = jsonPathEventsList.getList("data.id");

        System.out.println("Events ids : " + eventsIds);

        for (int eventId: eventsIds){
            if (eventId == 149){
                System.out.println("Event with id = 149 represented in list.");
            }
            if (eventId == 150){
                System.out.println("Event with id = 150 represented in list.");
            }
            if (eventId == 151){
                System.out.println("Event with id = 151 represented in list.");
            }
        }
        // Create state_id - event_state
        Map<String,Object> requestBodyEventState = new HashMap<>();
        requestBodyEventState.put("name",nameTemplate);
        requestBodyEventState.put("excluded", true);
        Response responseEventStateCreate = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBodyEventState)
                .post(API_EVENT_STATES)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathEventStateCreate = responseEventStateCreate.jsonPath();
        idCreatedState = jsonPathEventStateCreate.getInt("data.id");
        System.out.println("Created event state id - " + idCreatedState);

        // Create control_state_id. Check that id = 62 existed
        // Create control_period_state
        Map<String,Object> requestBodyControlState = new HashMap<>();
        requestBodyControlState.put("name",nameTemplate);
        requestBodyControlState.put("short_name", shortNameTemplate);
        requestBodyControlState.put("excluded", true);
        Response responseControlState = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .body(requestBodyControlState)
                .post(API_CONTROL_PERIOD)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathControlState = responseControlState.jsonPath();
        idCreatedControlState = jsonPathControlState.getInt("data.id");
        System.out.println("Created control state id - " + idCreatedControlState);

        // Check that control state with id = 62 is existed
        Response responseControlStateCheck = given()
                .when()
                .header("Content-Type", "application/json")
                .header("Test-Authorization", 1)
                .get(API_CONTROL_PERIOD)
                .then().log().all()
                .extract().response();

        JsonPath jsonPathControlStateCheck = responseControlStateCheck.jsonPath();

        List<Integer> controlStateIds = jsonPathControlStateCheck.getList("data.id");

        System.out.println("Control state ids : " + controlStateIds);
        for (int controlStateId: controlStateIds){
            if (controlStateId == 62){
                System.out.println("Control state with id = 62 is existed!");
            }
        }
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

        // Delete created event_id
        Response responseEvents = (Response) given()
                .header("Test-Authorization", 1)
                .delete(API_EVENTS + String.format("/%s",idCreatedEvent))
                .then().log().all()
                .extract();

        // JsonPath jsonPathEvents = responseEvents.jsonPath();

        // Delete created state_id
        Response responseEventState = (Response) given()
                .header("Test-Authorization", 1)
                .delete(API_EVENT_STATES + String.format("/%s",idCreatedState))
                .then().log().all()
                .extract();

        // JsonPath jsonPathEventState = responseEventState.jsonPath();

        // Delete created control_state_id
        Response responseControlState = (Response) given()
                .header("Test-Authorization", 1)
                .delete(API_CONTROL_PERIOD + String.format("/%s",idCreatedControlState))
                .then().log().all()
                .extract();

        // JsonPath jsonPathControlState = responseControlState.jsonPath();
    }

    @Test
    public void step01(){
        Response response = given()
                .when()
                .multiPart("execution_id", idCreatedExecution)
                .multiPart("event_id", idCreatedEvent)
                .multiPart("state_id", idCreatedState)
                .multiPart("sed_document[uid]", uidDocumentSED)
                .multiPart("sed_document[url]",urlDocumentSED)
                .multiPart("sed_document[reg_date]",regDateDocumentSED)
                .multiPart("sed_document[provider_id]",providerIdDocumentSED)
                .multiPart("sed_document[reg_number]",regNumberDocumentSED)
                .multiPart("document_id","1")
                .multiPart("document_type","Sed::Document")
                .multiPart("images[][original_file]",textFile)
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .post(API_EXECUTION_EVENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        idCreatedExecutionEventSED = jsonPath.getInt("data.id");
        String deadline_date = jsonPath.getString("data.deadline_date");
        String document_type = jsonPath.getString("data.document_type");
        String document_id = jsonPath.getString("data.document_id");
        String document_url = jsonPath.getString("data.document_url");
        String updated_at = jsonPath.getString("data.updated_at");
        String updated_by_id = jsonPath.getString("data.updated_by.id");
        String updated_by_name = jsonPath.getString("data.updated_by.name");
        String updated_by_position = jsonPath.getString("data.updated_by.position");
        int state = jsonPath.getInt("data.state.id");
        int event = jsonPath.getInt("data.event.id");
        String imageNameFirstElement = jsonPath.getString("data.images[0].full_name");

        System.out.println("Id created execution event - " + idCreatedExecutionEventSED);
        System.out.println("Deadline date - " + deadline_date);
        System.out.println("Document type - " + document_type);
        System.out.println("Document id - " + document_id);
        System.out.println("Document url - " + document_url);
        System.out.println("Updated at - " + updated_at);
        System.out.println("Updated by id - " + updated_by_id);
        System.out.println("Updated by name - " + updated_by_name);
        System.out.println("Updated by position - " + updated_by_position);
        System.out.println("State - " + state);
        System.out.println("Event - " + event);
        System.out.println("Image name first element - " + imageNameFirstElement);

        Assert.assertTrue(deadline_date.equals("2024-02-16T13:44:34.154Z"));
        Assert.assertTrue(document_type.equals("Sed::Document"));
        Assert.assertTrue(document_id.equals("29"));
        Assert.assertTrue(document_url.equals(urlDocumentSED));
        Assert.assertTrue(updated_by_id.equals("51bace4af273695ed1000128"));
        Assert.assertTrue(updated_by_name.equals("Колокольцев В.А."));
        Assert.assertTrue(updated_by_position.equals("Министр"));
        Assert.assertTrue(state == idCreatedState);
        Assert.assertTrue(event == idCreatedEvent);
        Assert.assertTrue(imageNameFirstElement.equals("text txt.txt"));
    }

    @Test
    public void step02(){
        Response response = given()
                .when()
                .multiPart("execution_id", idCreatedExecution)
                .multiPart("event_id", idCreatedEvent)
                .multiPart("state_id", idCreatedState)
                .multiPart("document_id",documentIdSKIP)
                .multiPart("document_type","Documents::Document")
                .multiPart("images[][original_file]",textFile)
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .post(API_EXECUTION_EVENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String deadline_date = jsonPath.getString("data.deadline_date");
        String document_type = jsonPath.getString("data.document_type");
        String document_id = jsonPath.getString("data.document_id");
        String document_url = jsonPath.getString("data.document_url");
        String updated_at = jsonPath.getString("data.updated_at");
        String updated_by_id = jsonPath.getString("data.updated_by.id");
        String updated_by_name = jsonPath.getString("data.updated_by.name");
        String updated_by_position = jsonPath.getString("data.updated_by.position");
        int state = jsonPath.getInt("data.state.id");
        int event = jsonPath.getInt("data.event.id");
        String imageNameFirstElement = jsonPath.getString("data.images[0].full_name");

        System.out.println("Deadline date - " + deadline_date);
        System.out.println("Document type - " + document_type);
        System.out.println("Document id - " + document_id);
        System.out.println("Document url - " + document_url);
        System.out.println("Updated at - " + updated_at);
        System.out.println("Updated by id - " + updated_by_id);
        System.out.println("Updated by name - " + updated_by_name);
        System.out.println("Updated by position - " + updated_by_position);
        System.out.println("State - " + state);
        System.out.println("Event - " + event);
        System.out.println("Image name first element - " + imageNameFirstElement);

        Assert.assertTrue(deadline_date.equals("2024-02-16T13:44:34.154Z"));
        Assert.assertTrue(document_type.equals("Documents::Document"));
        Assert.assertTrue(document_id.equals(documentIdSKIP));
        Assert.assertTrue(document_url.equals(documentUrlSKIP));
        Assert.assertTrue(updated_by_id.equals("51bace4af273695ed1000128"));
        Assert.assertTrue(updated_by_name.equals("Колокольцев В.А."));
        Assert.assertTrue(updated_by_position.equals("Министр"));
        Assert.assertTrue(state == idCreatedState);
        Assert.assertTrue(event == idCreatedEvent);
        Assert.assertTrue(imageNameFirstElement.equals("text txt.txt"));
    }

    @Test
    public void step03(){
        Response response = given()
                .when()
                .multiPart("execution_id", idCreatedExecution)
                .multiPart("event_id", idCreatedEvent)
                .multiPart("state_id", idCreatedState)
                .multiPart("document_id",documentIdSKIP)
                .multiPart("document_type","Documents::Document")
                .multiPart("images[][original_file]",textFile)
                .multiPart("control_state_id",idCreatedControlState)
                .multiPart("new_deadline_date","2024-11-13T00:00:00.000Z")
                .multiPart("content","content")
                .multiPart("note","note")
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .post(API_EXECUTION_EVENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        idCreatedExecutionEventSKIP = jsonPath.getInt("data.id");
        int control_state_id = jsonPath.getInt("data.control_state.id");
        String new_deadline_date = jsonPath.getString("data.new_deadline_date");
        String content = jsonPath.getString("data.content");
        String note = jsonPath.getString("data.note");

        System.out.println("Id created execution event - " + idCreatedExecutionEventSKIP);
        System.out.println("Control state id - " + control_state_id);
        System.out.println("New deadline date - " + new_deadline_date);
        System.out.println("Content - " + content);
        System.out.println("Note - " + note);

        Assert.assertTrue(control_state_id == idCreatedControlState);
        Assert.assertTrue(new_deadline_date.equals("2024-11-13T00:00:00.000Z"));
        Assert.assertTrue(content.equals("content"));
        Assert.assertTrue(note.equals("note"));
    }

    @Test
    public void step04(){
        Response response = given()
                .when()
                .multiPart("event_id", idCreatedEvent)
                .multiPart("state_id", idCreatedState)
                .multiPart("document_id",documentIdSKIP)
                .multiPart("document_type","Documents::Document")
                .multiPart("control_state_id",idCreatedControlState)
                .multiPart("new_deadline_date","2024-11-13T00:00:00.000Z")
                .multiPart("content","content")
                .multiPart("note","note")
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .post(API_EXECUTION_EVENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.execution_id[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("не может быть пустым"));
    }

    @Test
    public void step05(){
        Response response = given()
                .when()
                .multiPart("execution_id", "10000")
                .multiPart("event_id", idCreatedEvent)
                .multiPart("state_id", idCreatedState)
                .multiPart("document_id",documentIdSKIP)
                .multiPart("document_type","Documents::Document")
                .multiPart("control_state_id",idCreatedControlState)
                .multiPart("new_deadline_date","2024-11-13T00:00:00.000Z")
                .multiPart("content","content")
                .multiPart("note","note")
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .post(API_EXECUTION_EVENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.execution_id[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step06(){
        Response response = given()
                .when()
                .multiPart("execution_id", idCreatedExecution)
                .multiPart("state_id", idCreatedState)
                .multiPart("document_id",documentIdSKIP)
                .multiPart("document_type","Documents::Document")
                .multiPart("control_state_id",idCreatedControlState)
                .multiPart("new_deadline_date","2024-11-13T00:00:00.000Z")
                .multiPart("content","content")
                .multiPart("note","note")
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .post(API_EXECUTION_EVENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.event_id[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("не может быть пустым"));
    }

    @Test
    public void step07(){
        Response response = given()
                .when()
                .multiPart("execution_id", idCreatedExecution)
                .multiPart("event_id", "10000")
                .multiPart("state_id", idCreatedState)
                .multiPart("document_id",documentIdSKIP)
                .multiPart("document_type","Documents::Document")
                .multiPart("control_state_id",idCreatedControlState)
                .multiPart("new_deadline_date","2024-11-13T00:00:00.000Z")
                .multiPart("content","content")
                .multiPart("note","note")
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .post(API_EXECUTION_EVENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.event_id[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step08(){
        Response response = given()
                .when()
                .multiPart("execution_id", idCreatedExecution)
                .multiPart("event_id", idCreatedEvent)
                .multiPart("document_id",documentIdSKIP)
                .multiPart("document_type","Documents::Document")
                .multiPart("control_state_id",idCreatedControlState)
                .multiPart("new_deadline_date","2024-11-13T00:00:00.000Z")
                .multiPart("content","content")
                .multiPart("note","note")
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .post(API_EXECUTION_EVENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.state_id[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("не может быть пустым"));
    }

    @Test
    public void step09(){
        Response response = given()
                .when()
                .multiPart("execution_id", idCreatedExecution)
                .multiPart("event_id", idCreatedEvent)
                .multiPart("state_id", "10000")
                .multiPart("document_id",documentIdSKIP)
                .multiPart("document_type","Documents::Document")
                .multiPart("control_state_id",idCreatedControlState)
                .multiPart("new_deadline_date","2024-11-13T00:00:00.000Z")
                .multiPart("content","content")
                .multiPart("note","note")
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .post(API_EXECUTION_EVENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.state_id[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step10(){
        Response response = given()
                .when()
                .multiPart("execution_id", idCreatedExecution)
                .multiPart("event_id", "149")
                .multiPart("state_id", idCreatedState)
                .multiPart("document_id",documentIdSKIP)
                .multiPart("document_type","Documents::Document")
                .multiPart("new_deadline_date","2024-11-13T00:00:00.000Z")
                .multiPart("content","content")
                .multiPart("note","note")
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .post(API_EXECUTION_EVENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.control_state_id[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("не может быть пустым"));
    }

    @Test
    public void step11(){
        Response response = given()
                .when()
                .multiPart("execution_id", idCreatedExecution)
                .multiPart("event_id", "149")
                .multiPart("state_id", idCreatedState)
                .multiPart("document_id",documentIdSKIP)
                .multiPart("document_type","Documents::Document")
                .multiPart("control_state_id","10000")
                .multiPart("new_deadline_date","2024-11-13T00:00:00.000Z")
                .multiPart("content","content")
                .multiPart("note","note")
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .post(API_EXECUTION_EVENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.control_state_id[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step12(){
        Response response = given()
                .when()
                .multiPart("execution_id", idCreatedExecution)
                .multiPart("event_id", "149")
                .multiPart("state_id", idCreatedState)
                .multiPart("document_id",documentIdSKIP)
                .multiPart("document_type","Documents::Document")
                .multiPart("control_state_id",idCreatedControlState)
                .multiPart("new_deadline_date","2024-11-13T00:00:00.000Z")
                .multiPart("content","content")
                .multiPart("note","note")
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .post(API_EXECUTION_EVENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        int control_state_id = jsonPath.getInt("data.control_state.id");

        System.out.println("Control state id - " + control_state_id);

        Assert.assertTrue(control_state_id == idCreatedControlState);
    }

    @Test
    public void step13(){
        Response response = given()
                .when()
                .multiPart("execution_id", idCreatedExecution)
                .multiPart("event_id", "149")
                .multiPart("state_id", idCreatedState)
                .multiPart("document_id",documentIdSKIP)
                .multiPart("document_type","Documents::Document")
                .multiPart("control_state_id",idCreatedControlState)
                .multiPart("new_deadline_date","2024-11-13T00:00:00.000Z")
                .multiPart("basis_id",idDeadlineBases)
                .multiPart("content","content")
                .multiPart("note","note")
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .post(API_EXECUTION_EVENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        int basis_id = jsonPath.getInt("data.basis.id");

        System.out.println("Basis id - " + basis_id);

        Assert.assertTrue(basis_id == idDeadlineBases);
    }

    @Test
    public void step14(){
        Response response = given()
                .when()
                .multiPart("execution_id", idCreatedExecution)
                .multiPart("event_id", "149")
                .multiPart("state_id", idCreatedState)
                .multiPart("document_id",documentIdSKIP)
                .multiPart("document_type","Documents::Document")
                .multiPart("control_state_id",idCreatedControlState)
                .multiPart("new_deadline_date","2024-11-13T00:00:00.000Z")
                .multiPart("basis_id","1000")
                .multiPart("content","content")
                .multiPart("note","note")
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .post(API_EXECUTION_EVENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.basis_id[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step15(){
        Response response = given()
                .when()
                .multiPart("execution_id", idCreatedExecution)
                .multiPart("event_id", "150")
                .multiPart("state_id", idCreatedState)
                .multiPart("document_id",documentIdSKIP)
                .multiPart("document_type","Documents::Document")
                .multiPart("control_state_id",idCreatedControlState)
                .multiPart("basis_id",idDeadlineBases)
                .multiPart("content","content")
                .multiPart("note","note")
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .post(API_EXECUTION_EVENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.new_deadline_date[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("не может быть пустым"));
    }

    @Test
    public void step16(){
        Response response = given()
                .when()
                .multiPart("execution_id", idCreatedExecution)
                .multiPart("event_id", "151")
                .multiPart("state_id", idCreatedState)
                .multiPart("document_id",documentIdSKIP)
                .multiPart("document_type","Documents::Document")
                .multiPart("control_state_id",idCreatedControlState)
                .multiPart("basis_id",idDeadlineBases)
                .multiPart("content","content")
                .multiPart("note","note")
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .post(API_EXECUTION_EVENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.new_deadline_date[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("не может быть пустым"));
    }

    @Test
    public void step17(){
        Response response = given()
                .when()
                .multiPart("execution_id", idCreatedExecution)
                .multiPart("event_id", "151")
                .multiPart("state_id", idCreatedState)
                .multiPart("document_id",documentIdSKIP)
                .multiPart("document_type","Documents::Document")
                .multiPart("control_state_id",idCreatedControlState)
                .multiPart("new_deadline_date","1")
                .multiPart("basis_id",idDeadlineBases)
                .multiPart("content","content")
                .multiPart("note","note")
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .post(API_EXECUTION_EVENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.new_deadline_date[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет неверное значение"));
    }

    @Test
    public void step18(){
        Response responseSecond = given()
                .when()
                .multiPart("execution_id", "30")
                .multiPart("event_id", "151")
                .multiPart("state_id", idCreatedState)
                .multiPart("document_id",documentIdSKIP)
                .multiPart("document_type","Documents::Document")
                .multiPart("control_state_id",idCreatedControlState)
                .multiPart("new_deadline_date","2024-11-11T00:00:00.000Z")
                .multiPart("basis_id",idDeadlineBases)
                .multiPart("content","content")
                .multiPart("note","note")
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .post(API_EXECUTION_EVENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPathSecond = responseSecond.jsonPath();

        String errorMessage = jsonPathSecond.getString("errors.new_deadline_date[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("уже существует"));
    }

    @Test
    public void step19(){
        Response response = given()
                .when()
                .multiPart("execution_id", idCreatedExecution)
                .multiPart("event_id", "151")
                .multiPart("state_id", idCreatedState)
                .multiPart("document_id",documentIdSKIP)
                .multiPart("document_type","Documents::Document")
                .multiPart("control_state_id",idCreatedControlState)
                .multiPart("new_deadline_date","2024-11-12T00:00:00.000Z")
                .multiPart("basis_id",idDeadlineBases)
                .multiPart("images[][original_file]",zeroBytesFile)
                .multiPart("content","content")
                .multiPart("note","note")
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .post(API_EXECUTION_EVENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.images.0.original_file[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("Файл размером 0 байт не может быть добавлен"));
    }

    @Test
    public void step20(){
//        Response response = given()
//                .when()
//                .multiPart("execution_id", idCreatedExecution)
//                .multiPart("event_id", "151")
//                .multiPart("state_id", idCreatedState)
//                .multiPart("document_id",documentIdSKIP)
//                .multiPart("document_type","Documents::Document")
//                .multiPart("control_state_id",idCreatedControlState)
//                .multiPart("new_deadline_date","2024-11-12T00:00:00.000Z")
//                .multiPart("basis_id",idDeadlineBases)
//                .multiPart("images[][original_file]",fourHundredMegaBytesFile)
//                .multiPart("content","content")
//                .multiPart("note","note")
//                .contentType(ContentType.MULTIPART)
//                .header("Test-Authorization", userIdOne)
//                .post(API_EXECUTION_EVENTS)
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
    public void step21(){
        Response response = given()
                .when()
                .multiPart("execution_id", idCreatedExecution)
                .multiPart("event_id", "151")
                .multiPart("state_id", idCreatedState)
                .multiPart("document_id",documentIdSKIP)
                .multiPart("document_type","Documents::Document")
                .multiPart("control_state_id",idCreatedControlState)
                .multiPart("new_deadline_date","2024-11-12T00:00:00.000Z")
                .multiPart("basis_id",idDeadlineBases)
                .multiPart("images[][original_file]",executableFormatFile)
                .multiPart("content","content")
                .multiPart("note","note")
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .post(API_EXECUTION_EVENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.images.0.original_file[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.
                equals("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));
    }

    @Test
    public void step22(){
        Response response = given()
                .when()
                .multiPart("execution_id", idCreatedExecution)
                .multiPart("event_id", "151")
                .multiPart("state_id", idCreatedState)
                .multiPart("document_id",documentIdSKIP)
                .multiPart("document_type","Documents::Document")
                .multiPart("control_state_id",idCreatedControlState)
                .multiPart("new_deadline_date","2024-11-12T00:00:00.000Z")
                .multiPart("basis_id",idDeadlineBases)
                .multiPart("images[][original_file]",executableFormatFile)
                .multiPart("content","content")
                .multiPart("note","note")
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdSix)
                .post(API_EXECUTION_EVENTS)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("error");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void step23(){
        Response response = given()
                .when()
                .header("Test-Authorization", userIdOne)
                .get(API_EXECUTION_EVENTS + String.format("/%s", idCreatedExecutionEventSKIP))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        int control_state_id = jsonPath.getInt("data.control_state.id");
        String new_deadline_date = jsonPath.getString("data.new_deadline_date");
        String content = jsonPath.getString("data.content");
        String note = jsonPath.getString("data.note");

        System.out.println("Control state id - " + control_state_id);
        System.out.println("New deadline date - " + new_deadline_date);
        System.out.println("Content - " + content);
        System.out.println("Note - " + note);

        Assert.assertTrue(control_state_id == idCreatedControlState);
        Assert.assertTrue(new_deadline_date.equals("2024-11-13T00:00:00.000Z"));
        Assert.assertTrue(content.equals("content"));
        Assert.assertTrue(note.equals("note"));
    }

    @Test
    public void step24(){
        Response response = given()
                .when()
                .header("Test-Authorization", userIdSix)
                .get(API_EXECUTION_EVENTS + String.format("/%s", idCreatedExecutionEventSKIP))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("error");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void step25(){
        Response response = given()
                .when()
                .header("Test-Authorization", userIdOne)
                .put(API_EXECUTION_EVENTS + String.format("/%s", idCreatedExecutionEventSKIP))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        int control_state_id = jsonPath.getInt("data.control_state.id");
        String new_deadline_date = jsonPath.getString("data.new_deadline_date");
        String content = jsonPath.getString("data.content");
        String note = jsonPath.getString("data.note");

        System.out.println("Control state id - " + control_state_id);
        System.out.println("New deadline date - " + new_deadline_date);
        System.out.println("Content - " + content);
        System.out.println("Note - " + note);

        Assert.assertTrue(control_state_id == idCreatedControlState);
        Assert.assertTrue(new_deadline_date.equals("2024-11-13T00:00:00.000Z"));
        Assert.assertTrue(content.equals("content"));
        Assert.assertTrue(note.equals("note"));
    }

    @Test
    public void step26(){
        Response response = given()
                .when()
                .multiPart("images[][id]", "")
                .multiPart("images[][original_file]",textFile)
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .put(API_EXECUTION_EVENTS + String.format("/%s", idCreatedExecutionEventSKIP))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        idCreatedImage = jsonPath.getInt("data.images[0].id");
        String imageName = jsonPath.getString("data.images[0].full_name");

        System.out.println("Created image id - " + idCreatedImage);
        System.out.println("Image name - " + imageName);

        Assert.assertTrue(imageName.equals("text txt.txt"));
    }

    @Test
    public void step27(){
        Response response = given()
                .when()
                .multiPart("images[][id]", idCreatedImage)
                .multiPart("images[][original_file]",textFile)
                .multiPart("images[][deleted]","true")
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .put(API_EXECUTION_EVENTS + String.format("/%s", idCreatedExecutionEventSKIP))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        List<Integer> imagesIds = jsonPath.getList("data.images.id");

        System.out.println("Images ids: " + imagesIds);
        boolean isDeleted = false;
        for (int image: imagesIds){
            if (image == idCreatedImage){
                System.out.println(String.format("Element with id %s existed!", idCreatedImage));
            } else {
                System.out.println(String.format("Element with id %s is deleted!", idCreatedImage));
                isDeleted = true;
                break;
            }
        }
        Assert.assertTrue(isDeleted);
    }

    @Test
    public void step28(){
        Response response = given()
                .when()
                .multiPart("sed_document[uid]", uidDocumentSEDSecondDocument)
                .multiPart("sed_document[url]",urlDocumentSEDSecondDocument)
                .multiPart("sed_document[reg_date]",regDateDocumentSEDSecondDocument)
                .multiPart("sed_document[provider_id]",providerIdDocumentSEDSecondDocument)
                .multiPart("sed_document[reg_number]",regNumberDocumentSEDSecondDocument)
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .put(API_EXECUTION_EVENTS + String.format("/%s", idCreatedExecutionEventSED))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String document_url = jsonPath.getString("data.document_url");
        int document_id = jsonPath.getInt("data.document_id");

        System.out.println("Document url - " + document_url);
        System.out.println("Document id - " + document_id);

        // Document with uid - "01ddab5a40b066395ff580f63cb5a687a444b6ad8641c5e82d81935bd1205d6bcc" has id = 30
        boolean isChanged = false;
        if (document_id == 30){
            isChanged = true;
        }
        Assert.assertTrue(document_url.equals(urlDocumentSEDSecondDocument));
        Assert.assertTrue(isChanged);
    }

    @Test
    public void step29(){
        Response response = given()
                .when()
                .multiPart("state_id","")
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .put(API_EXECUTION_EVENTS + String.format("/%s", idCreatedExecutionEventSKIP))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.state_id[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("не может быть пустым"));
    }

    @Test
    public void step30(){
        Response response = given()
                .when()
                .multiPart("state_id","10000")
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .put(API_EXECUTION_EVENTS + String.format("/%s", idCreatedExecutionEventSKIP))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.state_id[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step31(){
        Response response = given()
                .when()
                .multiPart("basis_id","10000")
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .put(API_EXECUTION_EVENTS + String.format("/%s", idCreatedExecutionEventSKIP))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.basis_id[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("имеет непредусмотренное значение"));
    }

    @Test
    public void step32(){
        Response response = given()
                .when()
                .multiPart("images[][id]","")
                .multiPart("images[][original_file]",zeroBytesFile)
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .put(API_EXECUTION_EVENTS + String.format("/%s", idCreatedExecutionEventSKIP))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.images.0.original_file[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("Файл размером 0 байт не может быть добавлен"));
    }

    @Test
    public void step33(){
//        Response response = given()
//                .when()
//                .multiPart("images[][id]","")
//                .multiPart("images[][original_file]",fourHundredMegaBytesFile)
//                .contentType(ContentType.MULTIPART)
//                .header("Test-Authorization", userIdOne)
//                .put(API_EXECUTION_EVENTS + String.format("/%s", idCreatedExecutionEventSKIP))
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
    public void step34(){
        Response response = given()
                .when()
                .multiPart("images[][id]","")
                .multiPart("images[][original_file]",executableFormatFile)
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdOne)
                .put(API_EXECUTION_EVENTS + String.format("/%s", idCreatedExecutionEventSKIP))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("errors.images.0.original_file[0]");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.
                equals("Расширение загружаемого файла принадлежит к списку недопустимых форматов"));
    }

    @Test
    public void step35(){
        Response response = given()
                .when()
                .multiPart("images[][id]","")
                .multiPart("images[][original_file]",executableFormatFile)
                .contentType(ContentType.MULTIPART)
                .header("Test-Authorization", userIdSix)
                .put(API_EXECUTION_EVENTS + String.format("/%s", idCreatedExecutionEventSKIP))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("error");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void step36(){
        Response response = given()
                .when()
                .header("Test-Authorization", userIdSix)
                .delete(API_EXECUTION_EVENTS + String.format("/%s", idCreatedExecutionEventSKIP))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("error");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void step37(){
        Response response = given()
                .when()
                .header("Test-Authorization", userIdOne)
                .delete(API_EXECUTION_EVENTS + String.format("/%s", idCreatedExecutionEventSKIP))
                .then().log().all()
                .extract().response();
        int statusCode = response.getStatusCode();

        System.out.println("Status code: " + statusCode);

        Assert.assertTrue(statusCode == 204);
    }

    @Test
    public void step38(){
        Response response = given()
                .when()
                .header("Test-Authorization", userIdOne)
                .delete(API_EXECUTION_EVENTS + String.format("/%s", idCreatedExecutionEventSKIP))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        String errorMessage = jsonPath.getString("error");

        System.out.println("Error message - " + errorMessage);

        Assert.assertTrue(errorMessage.equals(String.
                format("Запись для Executions::Event с id = %s не найдена", idCreatedExecutionEventSKIP)));
    }

}

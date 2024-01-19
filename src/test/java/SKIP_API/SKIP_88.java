package SKIP_API;

import API.BasicApi;
import API.BasicControlSubjects;
import API.BasicUser;
import API.DTO.ControlSubjects.DatumControlSubjects;
import API.DTO.ControlSubjects.RootControlSubjects;
import API.DTO.ErrorsDTO.RootError;
import API.DTO.UserDto.*;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class SKIP_88 {
    RootControlSubjects controlSubjectsList;
    RootControlSubjects controlSubjectsListWithNewElement;
    RootControlSubjects controlSubjectsListWithChangedElement;
    RootResponseUserDto createUserIdThirteen;
    RootUserWithId userIdThirteen;
    RootResponseUserDto userThirteenCS;
    RootUsersList listOfUsers;
    RootError errorMessage;
    String provider_idUserOne = "525e9f767da3000002000001";
    String nameNewElement = "Test for SKIP-88"; // Need to mention it in testcase
    int idNewElement = 136;
    int idFirstElement;
    int idCreatedElement = 145;
    int idUser = 13;
    String official_id_thirteen_user = "6572d99f14b4000001000001";
    public int[] role_ids = {5};


    @BeforeTest
    public void setup(){
        // Need to check that user is existed, in case when user doesn't exist - create user
        // Get list of users to make sure that user with id 13 is existed
        listOfUsers = BasicUser.getUsers(1);
        // Create an empty list of integers
        List<Integer> idList = new ArrayList<>();
        for (DataUsersList element : listOfUsers.data) {
            // Add id each element from response body to list of integers
            idList.add(element.id);
        }
        // Check if response body has element with id 13 - output to the console that this user is already existed
        if (idList.contains(13)) {
            System.out.println("User with id 13 is existed!");
        } else { // Otherwise - output corresponding message to the console and call method to create user with id 13
            System.out.println("User with id 13 does not exist! You should create this user!");
            createUserIdThirteen = BasicUser.createUser(1, official_id_thirteen_user, role_ids);
        }
        // Create new control subject (136 in that case)
        // Check if CS with id = 136 pass creating
        controlSubjectsList = BasicControlSubjects.getControlSubjects(1);
        List<Integer> idCSList = new ArrayList<>();
        for (DatumControlSubjects element : controlSubjectsList.data) {
            idCSList.add(element.id);
        }
        if (idCSList.contains(136)){
            System.out.println("Control subject with id = 136 already existed!");
        } else {
            System.out.println("Control subject with id = 136 doesn't exist.");
            controlSubjectsListWithNewElement = BasicControlSubjects.createControlSubjectAndGetList
                    (1,nameNewElement,"123",provider_idUserOne,false);
            for (DatumControlSubjects element : controlSubjectsListWithNewElement.data){
                if (element.name.equals(nameNewElement)){
                    // Get id created control subject element
                    idNewElement = element.id;
                }
            }
        }
//        controlSubjectsListWithNewElement = BasicControlSubjects.createControlSubjectAndGetList
//                (1,nameNewElement,"123",provider_idUserOne,false);

        // Assign new control subject user with id 13
        userThirteenCS = BasicUser.updateUserControlSubjectsId(1,idUser,role_ids,idNewElement);
    }

    @Test
    public void Step01(){
        // In this step get list of control subjects and remember first element of this list - (122 - in this case)
        controlSubjectsList = BasicControlSubjects.getControlSubjects(1);
        idFirstElement = controlSubjectsList.data[0].id;
        Assert.assertNotNull(controlSubjectsList.data[0].name);
        Assert.assertNotNull(controlSubjectsList.data[0].id);
        Assert.assertNotNull(controlSubjectsList.data[0].fax_number);
        Assert.assertNotNull(controlSubjectsList.data[0].deleted);
        Assert.assertNotNull(controlSubjectsList.data[0].provider_id);
    }

    @Test
    public void Step02(){
        userIdThirteen = BasicUser.getUser(1,idUser);
        Assert.assertTrue(userIdThirteen.data.control_subject.id == idNewElement);
        Assert.assertTrue(userIdThirteen.data.control_subject.name.equals(nameNewElement));
    }

    @Test
    public void Step03(){
        controlSubjectsList = BasicControlSubjects.getControlSubjects(1);
        List<Integer> idList = new ArrayList<>();
        for (DatumControlSubjects element : controlSubjectsList.data){
            idList.add(element.id);
        }
        System.out.println(idList);
        if (idList.contains(122)) {
            System.out.println("Control subjects with id 122 is existed!");
        } else { // Need to check this
            System.out.println(
                    "Control subject with id 122 is not existed! You should create element with name - '!!!!$$$$78Auto'");
            controlSubjectsListWithNewElement = BasicControlSubjects.createControlSubjectAndGetList
                    (1,"!!!!$$$$78Auto","4",provider_idUserOne,false);
        }


    }

    @Test
    public void Step04(){
        // Error message: "субъект контроля был обнаружен у пользователей"
        String requestBody = String
                .format("{\"control_subjects\":[{\"id\": %s,\"name\": \"%s\"," +
                                "\"fax_number\":\"%s\",\"deleted\":%s,\"provider_id\":\"%s\"}]}", 122,
                        "name", "fax_number", true, provider_idUserOne);
        String response = BasicApi.putControlSubjectsAndGetError(
                "http://api.skip.rtech.ru/v1/permissions/control_subjects",1,requestBody).
                jsonPath().getString("errors.control_subjects.0.base");
        System.out.println(response);
        Assert.assertTrue(response.equals("[субъект контроля был обнаружен у пользователей]"));
    }

    @Test
    public void Step05(){
        controlSubjectsListWithNewElement = BasicControlSubjects.createControlSubjectAndGetList
                (1,"тестикс","тес1",provider_idUserOne,false);

        boolean isFound = false;
        for (DatumControlSubjects element : controlSubjectsListWithNewElement.data) {
            if (element.name.equals("тестикс")){
                idCreatedElement = element.id;
                isFound = true;
            }
        }
        // System.out.println(idCreatedElement);
        Assert.assertTrue(isFound);
    }

    @Test
    public void Step06(){
        errorMessage = BasicControlSubjects.putErrorMessageWithId(
                6,idCreatedElement,"тестикс","тес1",provider_idUserOne, true);
        //System.out.println(errorMessage.error);
        Assert.assertTrue(errorMessage.error.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void Step07(){
        controlSubjectsListWithChangedElement = BasicControlSubjects.
                deleteControlSubjectElementAndGetList(1,idCreatedElement, provider_idUserOne);

        boolean isDeleted = true;

        for (DatumControlSubjects element : controlSubjectsListWithChangedElement.data){
            if (element.id == idCreatedElement) {
                isDeleted = false;
            }
        }
        Assert.assertTrue(isDeleted);
    }

    @AfterTest
    public void tearDown(){
        // Assign value control_subject_id to null
        userThirteenCS = BasicUser.updateUserControlSubjectNull(1,idUser, role_ids,"");
        // Delete control subject that was created in setup step
        controlSubjectsListWithChangedElement = BasicControlSubjects.deleteControlSubjectElementAndGetList
                (1, idNewElement,provider_idUserOne);
    }

}

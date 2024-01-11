package SKIP_API;

import API.BasicControlSubjects;
import API.BasicUser;
import API.DTO.ControlSubjects.DatumControlSubjects;
import API.DTO.ControlSubjects.RootControlSubjects;
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
    String provider_idUserOne = "525e9f767da3000002000001";
    String nameNewElement = "Test for SKIP-88"; // Need to mention it in testcase
    int idNewElement;
    int idFirstElement;
    int idUser = 13;
    String official_id_fourteen_user = "5a7acf778f8c000016000001"; // ?
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
            System.out.println("User with id 13 is not existed! You should create this user!");
            createUserIdThirteen = BasicUser.createUser(1, official_id_thirteen_user, role_ids);
        }
        // Create new control subject (136 in that case)
        controlSubjectsListWithNewElement = BasicControlSubjects.createControlSubjectAndGetList
                (1,nameNewElement,"123",provider_idUserOne,false);
        for (DatumControlSubjects element : controlSubjectsListWithNewElement.data){
            if (element.name.equals(nameNewElement)){
                // Get id created control subject element
                idNewElement = element.id;
            }
        }
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
        if (idList.contains(53)) {
            System.out.println("Control subjects with id 53 is existed!");
        } else { // Need to check this
            System.out.println("Control subject with id 53 is not existed! You should create element with name - 'Абвгд'");
            controlSubjectsListWithNewElement = BasicControlSubjects.createControlSubjectAndGetList
                    (1,"Абвгд","string",provider_idUserOne,false);
        }
    }

    @AfterTest
    public void tearDown(){
        // Assign value control_subject_id to null
        userThirteenCS = BasicUser.updateUserControlSubjectNull(1,idUser, role_ids,"");
        // Delete control subject that was created in setup step
        controlSubjectsListWithChangedElement = BasicControlSubjects.deleteControlSubjectElementAndGetList
                (1, idNewElement,provider_idUserOne);
    }

//    @Test
//    public void testBeforeTest(){
//        // Need to check that user is existed, in case when user doesn't exist - create user
//
//        controlSubjectsListWithNewElement = BasicControlSubjects.createControlSubjectAndGetList
//                (1,nameNewElement,"123",provider_idUserOne,false);
//        for (DatumControlSubjects element : controlSubjectsListWithNewElement.data) {
//            if (element.name.equals(nameNewElement)) {
//                // Get id created control subject element
//                idNewElement = element.id;
//            }
//        }
//        System.out.println(idNewElement);
//    }
//
//    @Test
//    public void testtest(){
//        // Assign user with id 13 parameter value "control_subject_id" to null
//        userThirteenCS = BasicUser.updateUserControlSubjectNull(1, idUser, role_ids,"");
//        // Delete parameter value "control_subject" that was created in setup step
//        controlSubjectsListWithChangedElement = BasicControlSubjects.deleteControlSubjectElementAndGetList
//                (1,idNewElement, provider_idUserOne);
//    }


}

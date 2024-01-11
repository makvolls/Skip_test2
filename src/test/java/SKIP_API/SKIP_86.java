package SKIP_API;

import API.BasicControlSubjects;
import API.BasicUser;
import API.DTO.ControlSubjects.RootControlSubjects;
import API.DTO.ErrorsDTO.ControlSubjects.RootUnintendedValue;
import API.DTO.ErrorsDTO.ErrorNotException;
import API.DTO.ErrorsDTO.RootError;
import API.DTO.UserDto.RootResponseUserDto;
import API.DTO.UserDto.RootUserWithId;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SKIP_86 {
    RootControlSubjects controlSubjectsList;
    RootUserWithId userIdThirteen;
    RootResponseUserDto userThirteenCS;
    RootResponseUserDto userFourteenCS;
    ErrorNotException errorMessage;
    RootUnintendedValue errorMessageValue;
    RootResponseUserDto newUserWithCS;
    RootError errorMessageDenied;
    public int idFirstElement;
    public int idSecondElement;
    public int[] role_ids = {5};
    public int[] role_ids_all_rights = {30};
    public int idNewUser;
    int idUser = 13;
    String official_id = "5a7acf778f8c000016000001";

    @BeforeTest
    public void beforeTest(){
        userThirteenCS = BasicUser.updateUserControlSubjectNull(1,idUser, role_ids,"");
    }


    @Test
    public void Step01(){
        controlSubjectsList = BasicControlSubjects.getControlSubjects(1);
        Assert.assertNotNull(controlSubjectsList.data[0]);
        Assert.assertNotNull(controlSubjectsList.data[1]);
        idFirstElement = controlSubjectsList.data[0].id;
        idSecondElement = controlSubjectsList.data[1].id;
    }

    @Test
    public void Step02(){
        userIdThirteen = BasicUser.getUser(1,idUser);
        Assert.assertNull(userIdThirteen.data.control_subject);
    }

    @Test
    public void Step03(){
        userThirteenCS = BasicUser.updateUserControlSubjectsId(1,idUser,role_ids,idFirstElement);
        Assert.assertTrue(userThirteenCS.data.control_subject.id == idFirstElement);
    }

    @Test
    public void Step04(){
        int idNotExist = 1000;
        errorMessage = BasicUser.updateUserCSAndGetError(1,idUser,role_ids,idNotExist);
        Assert.assertTrue(errorMessage.error.contains(String.format("Key (control_subject_id)=(%s)",idNotExist)));
    }

    @Test
    public void Step05(){
        errorMessageValue = BasicUser.updateUserOtherProvider(2,2,role_ids_all_rights,idFirstElement);
        Assert.assertTrue(errorMessageValue.errors.control_subject_id[0].equals(String.format(
                "имеет непредусмотренное значение: %s", idFirstElement)));
    }

    @Test
    public void Step06(){
        newUserWithCS = BasicUser.createUserWithCS(1,official_id, role_ids, idFirstElement);
        Assert.assertTrue(newUserWithCS.data.official_id.equals(official_id));
        Assert.assertTrue(newUserWithCS.data.control_subject.id == idFirstElement);
        idNewUser = newUserWithCS.data.id;
    }

    @Test
    public void Step07(){
        userFourteenCS = BasicUser.updateUserControlSubjectsId(1,idNewUser,role_ids,idSecondElement);
        Assert.assertTrue(userFourteenCS.data.control_subject.id == idSecondElement);
    }

    @Test
    public void Step08(){
        BasicUser.deleteUserIntTest(1,idNewUser);
        errorMessageDenied = BasicUser.deletedUserCheck(1,14);
        Assert.assertTrue(errorMessageDenied.error.equals(
                String.format("Запись для Permissions::User с id = %s не найдена", 14)));
    }

    @Test
    public void Step09(){
        errorMessageDenied = BasicUser.createUserAndGetError(6,official_id, role_ids, idFirstElement);
        Assert.assertTrue(errorMessageDenied.error.equals("Доступ к ресурсу запрещен"));
    }



}

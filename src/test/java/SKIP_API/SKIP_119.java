package SKIP_API;

import API.BasicControlSubjects;
import API.BasicUser;
import API.DTO.ControlSubjects.RootControlSubjects;
import API.DTO.ErrorsDTO.ControlSubjects.RootUnintendedValue;
import API.DTO.ErrorsDTO.RootError;
import API.DTO.UserDto.RootResponseUserDto;
import API.DTO.UserDto.RootUserWithId;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SKIP_119 {
    RootControlSubjects controlSubjectsList;
    RootResponseUserDto userThirteenCS;
    RootUserWithId userIdThirteen;
    RootUnintendedValue errorMessage;
    RootError errorMessageDenied;
    public int idFirstElement;
    public int idSecondElement;
    int idUser = 13;
    public int[] role_ids = {5};
    public int[] control_subject_id = {122, 120};

    @BeforeTest
    public void beforeTest(){
        userThirteenCS = BasicUser.updateUserControlSubjectNull(1, idUser, role_ids,"");
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
        errorMessage = BasicUser.updateUserSeveralIds(1, idUser,role_ids,control_subject_id);
        Assert.assertTrue(errorMessage.errors.control_subject_id[0].equals("имеет неверное значение"));
    }

    @Test
    public void Step05(){
        userThirteenCS = BasicUser.updateUserControlSubjectNull(1, idUser, role_ids,"");
        Assert.assertNull(userThirteenCS.data.control_subject);
    }

    @Test
    public void Step06(){
        userThirteenCS = BasicUser.updateUserRoleArray(1, idUser, role_ids);
        Assert.assertNull(userThirteenCS.data.control_subject);
        Assert.assertEquals(userThirteenCS.data.roles[0].id,role_ids[0]);
    }

    @Test
    public void Step07(){
        errorMessageDenied = BasicUser.updateUserRoleError(6,idUser,role_ids);
        Assert.assertTrue(errorMessageDenied.error.equals("Доступ к ресурсу запрещен"));
    }
}

package SKIP_API;

import API.BasicUser;
import API.DTO.ErrorsDTO.ControlSubjects.RootUnintendedValue;
import API.DTO.UserDto.RootResponseUserDto;
import API.DTO.UserDto.RootUserDto;
import API.DTO.UserDto.RootUserWithId;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SKIP_114 {
    RootUserWithId userData;
    RootUserDto usersData;
    RootResponseUserDto userDataThirty;
    RootUnintendedValue userDataError;
    public int[] role_ids = {5};
    int idControlSubjectFirstValue = 120;
    int idControlSubjectSecondValue = 122;
    int idUser = 13;

    @BeforeTest
    public void beforeTest(){
        userDataThirty = BasicUser.updateUserControlSubjectsId(1,idUser,role_ids,idControlSubjectFirstValue);
    }

    @Test
    public void Step01(){
        userData = BasicUser.getUser(1,idUser);
        Assert.assertNotNull(userData.data.control_subject);
    }

    @Test
    public void Step02(){
        usersData = BasicUser.getAndSearchCS(1,idControlSubjectFirstValue);
        Assert.assertTrue(userData.data.id == idUser);
    }

    @Test
    public void Step03(){
        userDataThirty = BasicUser.updateUserControlSubjectsId(1,idUser,role_ids,idControlSubjectSecondValue);
        Assert.assertTrue(userDataThirty.data.control_subject.id == idControlSubjectSecondValue);
    }

    @Test
    public void Step04(){
        usersData = BasicUser.getAndSearchCS(1,idControlSubjectSecondValue);
        Assert.assertTrue(userData.data.id == idUser);
    }

    @Test
    public void Step05(){
        userDataError = BasicUser.searchCSAndGetError(1,idControlSubjectFirstValue, idControlSubjectSecondValue);
        Assert.assertTrue(userDataError.errors.control_subject_id[0].equals("имеет неверное значение"));
    }

    @Test
    public void Step06(){
        usersData = BasicUser.getAndSearchCS(1,100);
        Assert.assertTrue(usersData.meta.count == 0);
    }

    @Test
    public void Step07(){
        userDataThirty = BasicUser.updateUserControlSubjectsId(1,idUser,role_ids,idControlSubjectFirstValue);
        Assert.assertTrue(userDataThirty.data.control_subject.id == idControlSubjectFirstValue);
    }

    @Test
    public void Step08(){
        usersData = BasicUser.getAndSearchCS(1,idControlSubjectFirstValue);
        Assert.assertTrue(userData.data.id == idUser);
    }
}

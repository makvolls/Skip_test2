package SKIP_API;

import BasicPageTestApi.BasicApiTest;
;import DTO.ControlPeriodStatesDto.RootControlDto;
import DTO.DirectoryDto.RootDirectoryDto;
import DTO.RolesDto.DataResponseRolesDto;
import DTO.RolesDto.RootCreateRolesDto;
import DTO.RolesDto.RootResponseRolesDto;
import DTO.RolesDto.RootRolesDto;
import DTO.SecurityClassificationsDto.RootSecurityDto;
import DTO.UserDto.RootResponseUserDto;
import DTO.UserDto.RootUserDto;
import org.testng.Assert;
import org.testng.annotations.Test;



public class SKIP_1 extends BasicApiTest {


    RootUserDto actual;
    RootDirectoryDto actual2;
    RootControlDto actual3;
    RootSecurityDto actual4;

    String name;
    RootResponseRolesDto actual5;

    RootResponseRolesDto actual6;
    RootResponseUserDto actual7, actual8;


    @Test
    void getUser() {
        actual = getUsers(1);
        Assert.assertTrue(actual.data[3].full_name.contains("Разникова Ангелина Николаевна"));
    }

    @Test
    public void updateUser(){
        actual8 = updateUser(1, actual.data[3].id, 80);
        actual = getUsers(1);
        Assert.assertTrue(actual.data[3].roles[0].equals(actual.data[3].id = 80));
    }

    @Test
    public void createUser(){
        actual7 = createUser(1, "528f027454c8000041000001", 5);
        Assert.assertTrue(actual7.getData().full_name.equals("Столярова Юлия Валентиновна"));
    }

    @Test
    public void getClassifiers(){
        actual2 = getClassifiers(1);
        actual2.data[2].name.contains("Основание срока исполнения поручения");
    }

    @Test
    public void getFirstId(){
        actual = getUsers(1);
        Assert.assertTrue(actual.data[0].full_name.contains("Колокольцев Владимир Александрович"));
    }

    @Test
    public void getContolPeriodTest(){
        actual3 = getControlPeriod(1, "name", "desc");
        actual3.data[3].name.contains("ф");
    }

    @Test
    public void getSecurityTest(){
        actual4 = getSecurity(1);
        name = actual4.data[2].getName();
    }

    @Test
    public void createRole(){
        actual5 = createRoles(1,"test30011", true, "simple_search", "index_users");
        Assert.assertTrue(actual5.getData().name.equals("test3001"));
    }

    @Test
    public void updateRole(){
        actual6 = updateRole(1,124, "Удалить", true, "show_roles");
        Assert.assertTrue(actual6.getData().name.contains("Удалить"));
    }

    @Test
    public void deleteRole(){
        deleteRole(1, 134);
    }

    @Test
    public void createAndUpdateUser(){
        actual7 = createUser(1, "528f027454c8000041000001", 5);
        Assert.assertTrue(actual7.getData().full_name.equals("Столярова Юлия Валентиновна"));
        actual8 = updateUser(1, actual7.data.id, 7);
    }

    @Test
    public void getControlPeriodTest(){
        actual3 = getControlPeriod(1,"name","asc");
        Assert.assertTrue(actual3.data[0].name.contains("!"));
        Assert.assertTrue(actual3.data[0].short_name.contains("?"));
        Assert.assertTrue(actual3.data[1].name.contains("="));
        Assert.assertTrue(actual3.data[1].short_name.contains(" "));
        Assert.assertTrue(actual3.data[2].name.contains("123"));
        Assert.assertTrue(actual3.data[2].short_name.contains("2"));
    }

}

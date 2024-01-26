package SKIP_API;

import API.BasicRoles;
import API.DTO.ErrorsDTO.RootError;
import API.DTO.RolesDto.RootResponseRolesDto;
import autotest.core.util.Assistant;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SKIP_5 {

    RootResponseRolesDto role;
    RootError error, noResult;
    RootResponseRolesDto newValue, newValue2, newValue3, newValue4;
    int idAut1 = 1;
    int idAut2 = 3;
    int id1, id2, id3, id4;
    String name = Assistant.generateUniqeTextRussianString(7);
    String name2 = Assistant.generateUniqeTextRussianString(7);
    String name3 = Assistant.generateUniqeTextRussianString(7);
    String name4 = Assistant.generateUniqeTextRussianString(7);

    @BeforeClass
    public void newRoles() {

        newValue = BasicRoles.createRoles(idAut2, name, true, "create_users");
        id1 = newValue.data.id;
        newValue2 = BasicRoles.createRoles(idAut2, name2, true, "update_users");
        id2 = newValue2.data.id;
        newValue3 = BasicRoles.createRoles(idAut1, name3, false, "create_users");
        id3 = newValue3.data.id;
        newValue4 = BasicRoles.createRoles(idAut2, name4, false, "create_users");
        id4 = newValue4.data.id;
        BasicRoles.deleteRole(idAut2, id4);


    }

    @AfterClass
    public void delete() {
        BasicRoles.deleteRole(idAut2, id1);
        BasicRoles.deleteRole(idAut2, id2);
        BasicRoles.deleteRole(idAut1, id3);
    }


    @Test
    public void Step01() {
        role = BasicRoles.getRole(idAut2, id1);
        Assert.assertTrue(role.getData().name.equals(name));
    }

    @Test
    public void Step02() {
        role = BasicRoles.getRole(idAut2, id2);
        Assert.assertTrue(role.getData().name.equals(name2));
    }

    @Test
    public void Step03() {
        error = BasicRoles.getErrorRole(idAut2, id3);
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void Step04() {
        noResult = BasicRoles.getNoResult(idAut2, id4);
        Assert.assertTrue(noResult.getError().equals("Запись для Permissions::Role с id = " + id4 + " не найдена"));
    }

    @Test
    public void Step05() {
        role = BasicRoles.getRole(idAut1, id1);
        Assert.assertTrue(role.getData().name.equals(name));
    }

}

package SKIP_API;

import API.BasicRoles;
import API.DTO.ErrorsDTO.RootError;
import API.DTO.RolesDto.RootResponseRolesDto;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SKIP_5 {

    RootResponseRolesDto role;
    RootError error, noResult;


    @Test
    public void Step01(){
        role = BasicRoles.getRole(3, 3);
        Assert.assertTrue(role.getData().name.equals("тест11"));
    }
    @Test
    public void Step02(){
        role = BasicRoles.getRole(3, 4);
        Assert.assertTrue(role.getData().name.equals("тест123"));
    }
    @Test
    public void Step03(){
        error = BasicRoles.getErrorRole(3, 29);
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));
    }
    @Test
    public void Step04(){
        noResult = BasicRoles.getNoResult(3, 1);
        Assert.assertTrue(noResult.getError().equals("Запись для Permissions::Role с id = 1 не найдена"));
    }
    @Test
    public void Step05(){
        role = BasicRoles.getRole(1, 3);
        Assert.assertTrue(role.getData().name.equals("тест11"));
    }

}

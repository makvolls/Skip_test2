package SKIP_API;

import API.BasicRoles;
import API.BasicUser;
import API.DTO.ErrorsDTO.RootError;
import API.DTO.RolesDto.RootResponseRolesDto;
import API.DTO.UserDto.RootResponseUserDto;
import API.DTO.UserDto.RootUserDto;
import API.DTO.UserDto.RootUserWithId;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SKIP_14 {
    int id1;

    RootResponseRolesDto newValue;
    RootResponseUserDto updateValue;

    RootError errorDelete;

    RootUserWithId actualUser;






    @Test
    public void step01(){
newValue= BasicRoles.createRoles(1,"testik1123456789",true,"show_roles");
        Assert.assertTrue(newValue.data.name.equals("testik1123456789"));
        Assert.assertTrue(newValue.data.global);
        Assert.assertTrue(newValue.data.rights_ids[0].equals("show_roles"));
        id1=newValue.data.id;


    }
    @Test
    public void step02(){
updateValue= BasicUser.updateUser(1,1,29,id1);
        Assert.assertEquals(29,updateValue.data.roles[0].getId());
        Assert.assertEquals(id1,updateValue.data.roles[1].id);
    }

    @Test
    public void step03(){

        errorDelete=BasicRoles.deleteRoleТotFound(1,11234);
        Assert.assertTrue(errorDelete.error.equals("Запись для Permissions::Role с id = 11234 не найдена"));
    }

    @Test
    public void step04(){
BasicRoles.deleteRoleWithParams(1,id1);
    }

    @Test
    public void step05(){
actualUser=BasicUser.getUser(1,1);
Assert.assertEquals(29,actualUser.data.roles[0].id);
        Assert.assertEquals(32,actualUser.data.roles[1].id);
    }

}

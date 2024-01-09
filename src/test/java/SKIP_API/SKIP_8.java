package SKIP_API;

import API.BasicRoles;
import API.DTO.ErrorsDTO.RootError;
import API.DTO.RolesDto.RootResponseRolesDto;
import API.DTO.RolesDto.RootRolesDto;
import API.DTO.UserDto.RootResponseUserDto;
import autotest.core.util.Assistant;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.internal.reflect.InjectableParameter;

public class SKIP_8 {

    RootError error, checkDelete;
    RootResponseRolesDto upRole, createRole, searchRoles1;
    RootRolesDto searchRoles;

    String name = Assistant.generateUniqeTextRussianString(5);
    String upName = Assistant.generateUniqeTextRussianString(6);


    @Test
    public void Step01(){
        error = BasicRoles.getNoAccessRoles(6);
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));
    }
    @Test
    public void Step02(){
        error = BasicRoles.getErrorRole(6, 3);
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));
    }
    @Test
    public void Step03(){
        error = BasicRoles.createNoAccessRoles(6, "Test role3", true, "show_roles");
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));

    }
    @Test
    public void Step04(){
        error = BasicRoles.updateErrorRolesNoAccess(6, 3, "Test roles1", false,
                "index_users");
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));
    }
    @Test
    public void Step05(){
        error = BasicRoles.deleteRoleТotFound(6,3);
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));
    }
    @Test
    public void Step06(){
        upRole = BasicRoles.updateRole(3,3, "тест11", true, "index_roles");
        Assert.assertTrue(upRole.getData().name.equals("тест11"));
        Assert.assertTrue(upRole.getData().full_name.equals("тест11 (ОДиР ГУВО МВД России)"));
        Assert.assertTrue(upRole.getData().provider_short_title.equals("ОДиР ГУВО МВД России"));
        Assert.assertTrue(upRole.getData().global);
        Assert.assertTrue(upRole.getData().rights_ids[0].equals("index_roles"));
    }
    @Test
    public void Step07(){
        searchRoles = BasicRoles.getRoles(6);
        Assert.assertNotNull(searchRoles.data);
    }
    @Test
    public void Step08(){
        error = BasicRoles.getErrorRole(6, 3);
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));

        error = BasicRoles.createNoAccessRoles(6, "Test role3", true, "show_roles");
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));

        error = BasicRoles.updateErrorRolesNoAccess(6, 3, "Test roles1", false,
                "index_users");
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));

        error = BasicRoles.deleteRoleТotFound(6,3);
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));


    }
    @Test
    public void Step09(){
        upRole = BasicRoles.updateRole(3, 3, "тест11", true,
                "index_roles", "create_roles");
        Assert.assertTrue(upRole.getData().rights_ids[0].equals("index_roles"));
        Assert.assertTrue(upRole.getData().rights_ids[1].equals("create_roles"));

    }
    @Test
    public void Step10(){
        createRole = BasicRoles.createRoles(6, name, true, "show_roles");
        Assert.assertNotNull(createRole.getData());
        Assert.assertTrue(createRole.getData().name.equals(name));
    }
    @Test
    public void Step11(){
        error = BasicRoles.getErrorRole(6, 3);
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));

        error = BasicRoles.updateErrorRolesNoAccess(6, 3, "Test roles1", false,
                "index_users");
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));

        error = BasicRoles.deleteRoleТotFound(6,3);
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));

    }
    @Test
    public void Step12(){
        upRole = BasicRoles.updateRole(3, 3, "тест11", true,
                "index_roles", "show_roles");
        Assert.assertTrue(upRole.getData().rights_ids[0].equals("index_roles"));
        Assert.assertTrue(upRole.getData().rights_ids[1].equals("show_roles"));
    }
    @Test
    public void Step13(){
        searchRoles1 = BasicRoles.getRole(6, 3);
        Assert.assertNotNull(searchRoles1.getData());
    }
    @Test
    public void Step14(){
        error = BasicRoles.updateErrorRolesNoAccess(6, 3, "Test roles1", false,
                "index_users");
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));

        error = BasicRoles.deleteRoleТotFound(6,3);
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));
    }
    @Test
    public void Step15(){
        upRole = BasicRoles.updateRole(3, 3, "тест11", true,
                "index_roles", "update_roles");
        Assert.assertTrue(upRole.getData().rights_ids[0].equals("index_roles"));
        Assert.assertTrue(upRole.getData().rights_ids[1].equals("update_roles"));
    }
    @Test
    public void Step16(){
        upRole = BasicRoles.updateRole(6, createRole.getData().id, upName, true,
                "index_users");
        Assert.assertTrue(upRole.getData().rights_ids[0].equals("index_users"));

    }
    @Test
    public void Step17(){
        error = BasicRoles.getErrorRole(6, 3);
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));

        error = BasicRoles.createNoAccessRoles(6, "Test role3", true, "show_roles");
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));

        error = BasicRoles.deleteRoleТotFound(6,3);
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));

    }
    @Test
    public void Step18(){
        upRole = BasicRoles.updateRole(3, 3, "тест11", true,
                "index_roles", "destroy_roles");
        Assert.assertTrue(upRole.getData().rights_ids[0].equals("index_roles"));
        Assert.assertTrue(upRole.getData().rights_ids[1].equals("destroy_roles"));
    }
    @Test
    public void Step19(){
        BasicRoles.deleteRole(6, createRole.getData().id);
        checkDelete = BasicRoles.deleteRoleТotFound(6, createRole.getData().id);
        Assert.assertTrue(checkDelete.error.equals("Запись для Permissions::Role с id = "+createRole.getData()
                .id+" не найдена"));
    }
    @Test
    public void Step20(){
        error = BasicRoles.getErrorRole(6, 3);
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));

        error = BasicRoles.createNoAccessRoles(6, "Test role3", true, "show_roles");
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));

        error = BasicRoles.updateErrorRolesNoAccess(6, 3, "Test roles1", false,
                "index_users");
        Assert.assertTrue(error.error.equals("Доступ к ресурсу запрещен"));
    }
    @Test
    public void Step21(){
        upRole = BasicRoles.updateRole(3, 3, "тест11", true,
                "create_users");
        Assert.assertTrue(upRole.getData().rights_ids[0].equals("create_users"));
    }





}

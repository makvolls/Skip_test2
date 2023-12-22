package SKIP_API;

import API.*;
import API.DTO.DirectoryDto.RootDirectoryDto;
import API.DTO.RolesDto.RootResponseRolesDto;
import API.DTO.UserDto.RootUserWithId;
import org.testng.annotations.Test;

public class GetUsers extends BasicApi {



    RootUserWithId actualUserId5New;
    RootResponseRolesDto actualUserId5;
    RootDirectoryDto actualUserId4;
    RootResponseRolesDto ac;

    @Test
    public void steps(){
        BasicUser.getUser(1, 1);

        BasicProviders.getProviders(1);
        BasicEvents.getListEvents(1);
        BasicControlPeriod.getControlPeriod(1, "short_name", "asc");

        BasicRoles.getRoles(1);
       ac = BasicRoles.createRoles(1, "туту111111", true, "update_users");
        BasicRoles.updateRole(1, ac.getData().id, "тата", true, "update_users");
         //actualUserId5 = createRoles1(1, "gggg5", true, "update_users");

        //actualUserId4 = getClassifiers(1);


        /**
        * Step 1:
         * Authorize as user with id 5
         * Get response with userdata with id 5
         * Save it in variable actualUserId5
         * **/
        //actualUserId5New = getUser(5,5);
        //actualUserId5 = getUser(5,5);
        //String full_name = "Проверка Авторизации СУДИС";
        //Assert.assertTrue(actualUserId5.data[0].full_name.contains(full_name));
        //System.out.println(actualUserId5);


        /**
         * Step 2:
         * Get response with userdata with id 4
         * Save it in variable actualUserId4
         * **/
        //actualUserId4 = getUser(5,4);
        //String error = "Доступ к ресурсу запрещен";
        //Assert.assertEquals(actualUserId4.getData().);

        /**
         * Step 3:
         * Can't doing this step here
         * **/
    }
}

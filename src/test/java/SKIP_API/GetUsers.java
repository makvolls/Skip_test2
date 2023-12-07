package SKIP_API;

import BasicPageTestApi.BasicApiTest;
import DTO.UserDto.RootUserDto;
import DTO.UserDto.RootUserWithId;
import org.testng.annotations.Test;

public class GetUsers extends BasicApiTest {

    RootUserWithId actualUserId5New;
    RootUserDto actualUserId5;
    RootUserDto actualUserId4;

    @Test
    public void steps(){
        /**
        * Step 1:
         * Authorize as user with id 5
         * Get response with userdata with id 5
         * Save it in variable actualUserId5
         * **/
        actualUserId5New = getUser(5,5);
        //actualUserId5 = getUser(5,5);
        String full_name = "Проверка Авторизации СУДИС";
        //Assert.assertTrue(actualUserId5.data[0].full_name.contains(full_name));
        //System.out.println(actualUserId5);


        /**
         * Step 2:
         * Get response with userdata with id 4
         * Save it in variable actualUserId4
         * **/
        //actualUserId4 = getUser(5,4);
        String error = "Доступ к ресурсу запрещен";
        //Assert.assertEquals(actualUserId4.getData().);

        /**
         * Step 3:
         * Can't doing this step here
         * **/
    }
}

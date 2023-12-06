package SKIP_API;

import BasicPageTestApi.BasicApiTest;
import DTO.RolesDto.RootRolesDto;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchRoles extends BasicApiTest {

    RootRolesDto actualRoles;

    @Test
    public void steps(){
        /**
         * Step 1:
         * Authorize as user with id 5
         * Get response with list of roles
         * Remember the last role in the list
         * **/
        actualRoles = getRoles(5);
        String full_nameFromLastRole = "Test roles666666";
        String full_nameFromFirstRole = " Для теста777";
        Assert.assertTrue(actualRoles.getData()[0].full_name.contains(full_nameFromFirstRole));
        Assert.assertTrue(actualRoles.getData()[9].full_name.contains(full_nameFromLastRole));

        /**
         * Step 2:
         * In field full_name put data from step 1
         * For example: "тест11
         * "**/


        /**
         * Step 3:
         * In field full_name put data from step 1
         * in uppercase
         * for example: "ТЕСТ11
         * "**/

        /**
         * Step 4:
         * In field full_name put data:
         * "тест11 (ОДиР ГУВО МВД России"
         * **/

        /**
         * Step 5:
         * In field excluded_id put id
         * the last role from the list
         * **/

    }
}

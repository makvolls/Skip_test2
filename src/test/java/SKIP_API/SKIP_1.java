package SKIP_API;

import BasicPageTestApi.BasicApiTest;
;import DTO.ControlPeriodStatesDto.RootControlDto;
import DTO.DirectoryDto.RootDirectoryDto;
import DTO.SecurityClassificationsDto.RootSecurityDto;
import DTO.UserDto.RootUserDto;
import org.testng.Assert;
import org.testng.annotations.Test;



public class SKIP_1 extends BasicApiTest {


    RootUserDto actual;
    RootDirectoryDto actual2;
    RootControlDto actual3;
    RootSecurityDto actual4;

    String name;

    @Test

    void getUser() {
        actual4 = getSecurity(1);
        name = actual4.data[2].getName();

        actual3 = getControlPeriod(1, "name", "desc");
        actual3.data[3].name.contains("ф");

       actual2 = getClassifiers(1);
       actual2.data[2].name.contains("Основание срока исполнения поручения");



        actual = getUser(1, 2);

      Assert.assertTrue(actual.getData().full_name.contains("Колокольцев Владимир Александрович"));



    }
}

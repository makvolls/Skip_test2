package SKIP_API;

import API.BasicUser;
import API.DTO.ErrorsDTO.RootError;
import API.DTO.UserDto.RootUserWithId;
import org.testng.Assert;
import org.testng.annotations.Test;



public class SKIP_1 {

    RootUserWithId actual;
    RootError actual2;

    @Test
    public void Step1(){
       actual = BasicUser.getUser(1, 1);
       Assert.assertTrue(actual.data.full_name.equals("Колокольцев Владимир Александрович"));
    }

    @Test
    public void Step2(){
        actual2 = BasicUser.getErrorUser(6, 1);
        Assert.assertTrue(actual2.error.equals("Доступ к ресурсу запрещен"));

    }





}

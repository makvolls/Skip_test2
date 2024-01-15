package SKIP_API;

import API.BasicUser;
import API.DTO.UserDto.RootGetUserCurrent;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SKIP_17 {

    RootGetUserCurrent actualValue;

    @Test
    public void step01(){
        actualValue= BasicUser.getUserCurrent(5);
        Assert.assertEquals(5,actualValue.data.id);
        Assert.assertTrue(actualValue.data.short_name.equals("Проверка А.С."));
        Assert.assertNotNull(actualValue.data.rights_ids.length);
    }
}

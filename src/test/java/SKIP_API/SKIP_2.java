package SKIP_API;

import API.BasicRoles;
import API.DTO.RolesDto.DatumRolesDto;
import API.DTO.RolesDto.RootResponseRolesDto;
import API.DTO.RolesDto.RootRolesDto;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SKIP_2 {
    RootRolesDto actual;
    RootRolesDto actual2;
    String id;

    @Test
    public void Step1() {
        actual = BasicRoles.getRoles(1);
        Assert.assertTrue(actual.getData().equals(actual.data));
    }


    @Test
    public void Step2() {

        actual2 = BasicRoles.getRolesParams(1, actual.getData()[0].full_name.substring(0, 13), "");
        Assert.assertTrue(actual2.getData()[0].full_name.equals(actual.getData()[0].full_name));

    }


    @Test
    public void Step3() {

        actual2 = BasicRoles.getRolesParams(1, actual.getData()[0].full_name.toUpperCase().substring(0, 13), "");
        Assert.assertTrue(actual2.getData()[0].full_name.equals(actual.getData()[0].full_name));

    }

    @Test
    public void Step4() {

        actual2 = BasicRoles.getRolesParams(1, actual.getData()[0].full_name, "");
        Assert.assertTrue(actual2.getData()[0].full_name.equals(actual.getData()[0].full_name));

    }

    @Test
    public void Step5() {
        actual = BasicRoles.getRoles(1);
        actual2 = BasicRoles.getRolesParams(1, "", actual.getData()[9].id);
        id = actual.getData()[9].id;

        for (int i = 0; i < actual2.getData().length; i++) {
            Assert.assertTrue(actual2.getData()[i].id != id);
        }


    }

}

package SKIP_API;

import API.BasicControlSubjects;
import API.DTO.ControlSubjects.DatumControlSubjects;
import API.DTO.ControlSubjects.RootControlSubjects;
import API.DTO.ErrorsDTO.RootError;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SKIP_77 {
    RootControlSubjects controlSubjectsList;
    RootError errorMessageNotEnoughRights;


    @Test
    public void Step01(){
        controlSubjectsList = BasicControlSubjects.getControlSubjects(1);
        //Assert.assertTrue(controlSubjectsList.data[0].name.equals("Testing25"));
        Assert.assertNotNull(controlSubjectsList.getData()[0].name);
        Assert.assertNotNull(controlSubjectsList.getData()[0].fax_number);
        Assert.assertNotNull(controlSubjectsList.getData()[0].id);
        Assert.assertNotNull(controlSubjectsList.getData()[0].provider_id);
        Assert.assertNotNull(controlSubjectsList.getData()[0].deleted);

        for (DatumControlSubjects element : controlSubjectsList.data) {
            String allNames = element.name;
            if (allNames.equals("!!!!$$78Auto")){
                break;
            }
            System.out.println(allNames);
            //Assert.assertTrue(allNames.equals("!!!!$$78Auto"));
            //System.out.println(new ArrayList<String>(Arrays.asList(allNames)));
            //System.out.println(allNames);

        }


    }

    @Test
    public void Step02(){
        errorMessageNotEnoughRights = BasicControlSubjects.getErrorMessage(6);
        //System.out.println(errorMessageNotEnoughRights.error);
        Assert.assertTrue(errorMessageNotEnoughRights.error.equals("Доступ к ресурсу запрещен"));
    }
}

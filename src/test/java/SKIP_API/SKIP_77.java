package SKIP_API;

import API.BasicControlSubjects;
import API.DTO.ControlSubjects.DatumControlSubjects;
import API.DTO.ControlSubjects.RootControlSubjects;
import API.DTO.ErrorsDTO.RootError;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;


public class SKIP_77 {
    RootControlSubjects controlSubjectsList;
    RootError errorMessageNotEnoughRights;


    @Test
    public void Step01(){
        controlSubjectsList = BasicControlSubjects.getControlSubjects(1);
        ArrayList<String> namesList = new ArrayList<String>();
        for (DatumControlSubjects element : controlSubjectsList.data) {
            namesList.add(element.name);
        }
        Comparator<String> controlSubjectComparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if (s1.contains("!!!!$$$$"))
                    return 1;
                else if (s1.contains("Т"))
                    return 1;
                else if (s2.contains("я"))
                    return -1;
                else
                    return s1.compareTo(s2);
            }
        };
        ArrayList<String> checkOrderList = new ArrayList<>();
        checkOrderList.addAll(namesList);
        Collections.sort(checkOrderList, controlSubjectComparator);
        //Assert.assertTrue(namesList.equals(checkOrderList));
    }

    @Test
    public void Step02(){
        errorMessageNotEnoughRights = BasicControlSubjects.getErrorMessage(6);
        Assert.assertTrue(errorMessageNotEnoughRights.error.equals("Доступ к ресурсу запрещен"));
    }
}

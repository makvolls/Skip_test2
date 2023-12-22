package SKIP_API;

import API.BasicApi;
import API.BasicControlSubjects;
import API.DTO.ControlSubjects.RootControlSubjects;
import API.DTO.ControlSubjects.RootPutRequestControlSubjects;
import org.testng.Assert;
import org.testng.annotations.Test;



public class SKIP_78 {
    RootControlSubjects controlSubjectsListWithNewElement;

    String provider_idUserOne = "525e9f767da3000002000001";

    @Test
    public void Step01(){
//        controlSubjectsListWithNewElement = BasicControlSubjects.createControlSubjects
//                (1,"Тест Задание 270","8-888-888"
//                        ,false, "525e9f767da3000002000001");
//        controlSubjectsWithNewElementArray = BasicControlSubjects.createControlSubjectsArray
//                (1, "Тест Задание 270","8-888-888",false,
//                        "525e9f767da3000002000001");
//        controlSubjectsListWithNewElement = BasicControlSubjects.createControlSubjectsAndShowList(
//               1, "Тест Задание 270",
//                "8-888-888","525e9f767da3000002000001", false);
//        controlSubjectsListWithNewElement = BasicControlSubjects.createStrictSetControlSubject(1);
        // It's working!
//        controlSubjectsListWithNewElement = BasicControlSubjects.createControlSubjectsAndShowList(1,"!!!78Auto","2",provider_idUserOne, false);
//        System.out.println(controlSubjectsListWithNewElement.data[0].name.equals("!!!78Auto"));
        controlSubjectsListWithNewElement = BasicControlSubjects.createControlSubjectAndGetList(
                1, "!!!!$$78Auto","3",provider_idUserOne,false);
        Assert.assertTrue(controlSubjectsListWithNewElement.data[0].name.equals("!!!!$$78Auto"));
        // Try for loop
        String needToFound = "!!!!$$78Auto";


    }
}

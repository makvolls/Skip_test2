package SKIP_API;

import API.BasicApi;
import API.BasicControlSubjects;
import API.BasicRoles;
import API.DTO.ControlSubjects.DatumControlSubjects;
import API.DTO.ControlSubjects.RootControlSubjects;
import API.DTO.ErrorsDTO.RootError;
import API.DTO.RolesDto.RootResponseRolesDto;
import Utils.RandomStringGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;
import sun.security.util.Resources_zh_CN;

import java.util.ArrayList;


public class SKIP_78 {
    RootControlSubjects controlSubjectsListWithNewElement; // Step 1,10, 14,18
    RootControlSubjects controlSubjectsListWithChangedElement; // Step 4,5,11,15,16
    RootControlSubjects controlSubjectsList; // Step 5
    RootError controlSubjectsWithNewElement; // Step 6
    RootError controlSubjectTryChangeElement; // Step 7,12
    RootResponseRolesDto roleFiveUserIdSix; // Step 9,13,17

    String provider_idUserOne = "525e9f767da3000002000001";
    int idToRemember = 0; // Step 1,4,5, 10, 11, 12, 14, 15, 16, 18(?)
    int FirstElementIdStepFive = 0; // Step 5

    @Test
    public void Step01(){
        controlSubjectsListWithNewElement = BasicControlSubjects.createControlSubjectAndGetList(
                1, "Тест Задание 270","8-888-888",provider_idUserOne,false);
        String nameToCheck = "Тест Задание 270";
        String faxNumberToCheck = "8-888-888";
        ArrayList<String> namesList = new ArrayList<String>();
        for (DatumControlSubjects element : controlSubjectsListWithNewElement.data) {
            namesList.add(element.name);
            if (element.name.equals(nameToCheck) && (element.fax_number.equals(faxNumberToCheck))){
                idToRemember = element.id;
            }
        }
        boolean containsElement = namesList.contains(nameToCheck);
        Assert.assertTrue(containsElement);

    }

    @Test
    public void Step02(){
        // Error message : "уже существует"
        String requestBody = String.format(
                "{\"control_subjects\":[{\"name\": \"%s\",\"fax_number\":\"%s\",\"deleted\":%s,\"provider_id\":\"%s\"}]}"
                , "Тест Задание 270", "8-888-888", false, provider_idUserOne);
        String response = BasicApi.putControlSubjectsAndGetError(
                "http://api.skip.rtech.ru/v1/permissions/control_subjects"
                ,1, requestBody).jsonPath().getString("errors.control_subjects.0.name");
        Assert.assertTrue(response.equals("[уже существует]"));
    }

    @Test
    public void Step03(){
        // Error message: "не может быть пустым"
        String requestBody = String.format(
                "{\"control_subjects\":[{\"name\": \"%s\",\"fax_number\":\"%s\",\"deleted\":%s,\"provider_id\":\"%s\"}]}"
                , "", "8-888-888", false, provider_idUserOne);
        String response = BasicApi.putControlSubjectsAndGetError(
                "http://api.skip.rtech.ru/v1/permissions/control_subjects"
                ,1, requestBody).jsonPath().getString("errors.control_subjects.0.name");
        Assert.assertTrue(response.equals("[не может быть пустым]"));
    }

    @Test
    public void Step04(){
        int idValue = idToRemember;
        controlSubjectsListWithChangedElement = BasicControlSubjects.changeControlSubjectsAndGetList(
                1,115,"Тест Задание 270 Тест PUT Test",
                "9-999-999",provider_idUserOne,false);
        String nameForCheck = "Тест Задание 270 Тест PUT Test";
        String faxNumberForCheck = "9-999-999";
        boolean isFound = false;
        for (DatumControlSubjects element : controlSubjectsListWithChangedElement.data){
            if (element.name.equals(nameForCheck) && element.fax_number.equals(faxNumberForCheck)){
                isFound = true;
            }
        }
        Assert.assertTrue(isFound);
    }

    @Test
    public void Step05(){
        int idValue = idToRemember;
        controlSubjectsListWithChangedElement = BasicControlSubjects.deleteControlSubjectElementAndGetList(
                1,125,provider_idUserOne);
        int idForCheck = 125;
        boolean isDeleted = true;
        for (DatumControlSubjects element : controlSubjectsListWithChangedElement.data){
            if (element.id == idForCheck){
                isDeleted = false;
            }
        }
        Assert.assertTrue(isDeleted);

        controlSubjectsList = BasicControlSubjects.getControlSubjects(1);
        FirstElementIdStepFive  = controlSubjectsListWithChangedElement.data[0].id; // 122
    }

    @Test
    public void Step06(){
        controlSubjectsWithNewElement = BasicControlSubjects.putErrorMessageWithoutId(
                6,"Тест Задание 270 Пользователь 6","1",provider_idUserOne,false);
        Assert.assertTrue(controlSubjectsWithNewElement.error.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void Step07(){
        int idValue = FirstElementIdStepFive; // 122 , insert to method parameter
        controlSubjectTryChangeElement = BasicControlSubjects.putErrorMessageWithId(
                6,122,"Тест Задание 270 Пользователь 6",
                "1",provider_idUserOne, false);
        Assert.assertTrue(controlSubjectTryChangeElement.error.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void Step08(){
        int idValue = FirstElementIdStepFive; // 122 , insert to method parameter
        controlSubjectTryChangeElement = BasicControlSubjects.putErrorMessageWithId(
                6,122,"Тест Задание 270 Пользователь 6",
                "1",provider_idUserOne, true);
        Assert.assertTrue(controlSubjectTryChangeElement.error.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void Step09(){
        roleFiveUserIdSix = BasicRoles.updateRole(1,5,
                "тест22",true, "show_roles", "update_providers_control_subjects");
        Assert.assertTrue(roleFiveUserIdSix.data.name.equals("тест22"));
        Assert.assertTrue(roleFiveUserIdSix.data.full_name.equals("тест22 (ДДО МВД России)"));
        Assert.assertTrue(roleFiveUserIdSix.data.provider_short_title.equals("ДДО МВД России"));
        Assert.assertTrue(roleFiveUserIdSix.data.rights_ids[0].equals("show_roles"));
        Assert.assertTrue(roleFiveUserIdSix.data.rights_ids[1].equals("update_providers_control_subjects"));
        Assert.assertTrue(roleFiveUserIdSix.data.global);
        Assert.assertTrue(roleFiveUserIdSix.data.provider_id.equals("525e9f767da3000002000001"));
    }

    @Test
    public void Step10(){
        controlSubjectsListWithNewElement = BasicControlSubjects.createControlSubjectAndGetList(
                6,"Тест Задание 270 Пользователь 6","1",provider_idUserOne, false);
        String needToFound = "Тест Задание 270 Пользователь 6";
        ArrayList<String> namesList = new ArrayList<String>();
        for (DatumControlSubjects element : controlSubjectsListWithNewElement.data) {
            namesList.add(element.name);
            if (element.name.equals(needToFound)){
                idToRemember = element.id;
            }
        }
        boolean containsElement = namesList.contains(needToFound);
        Assert.assertTrue(containsElement);
    }

    @Test
    public void Step11(){
        controlSubjectsListWithChangedElement = BasicControlSubjects.changeControlSubjectsAndGetList(
                6,123,"Тест Задание 270 Пользователь 6 PUT Test",
                "2", provider_idUserOne, false);
        String nameForCheck = "Тест Задание 270 Пользователь 6 PUT Test";
        String faxNumberForCheck = "2";
        boolean isFound = false;
        for (DatumControlSubjects element : controlSubjectsListWithChangedElement.data){
            if (element.name.equals(nameForCheck) && element.fax_number.equals(faxNumberForCheck)){
                isFound = true;
            }
        }
        Assert.assertTrue(isFound);
    }

    @Test
    public void Step12(){
        int idValue = idToRemember; // 123 , insert to method parameter
        controlSubjectTryChangeElement = BasicControlSubjects.putErrorMessageWithId(
                6,123,"Тест Задание 270 Пользователь 6 PUT Test",
                "2",provider_idUserOne, true);
        Assert.assertTrue(controlSubjectTryChangeElement.error.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void Step13(){
        roleFiveUserIdSix = BasicRoles.updateRole(1,5,
                "тест22",true,
                "show_roles", "update_providers_control_subjects", "destroy_providers_control_subjects");
        Assert.assertTrue(roleFiveUserIdSix.data.name.equals("тест22"));
        Assert.assertTrue(roleFiveUserIdSix.data.full_name.equals("тест22 (ДДО МВД России)"));
        Assert.assertTrue(roleFiveUserIdSix.data.provider_short_title.equals("ДДО МВД России"));
        Assert.assertTrue(roleFiveUserIdSix.data.rights_ids[0].equals("show_roles"));
        Assert.assertTrue(roleFiveUserIdSix.data.rights_ids[1].equals("update_providers_control_subjects"));
        Assert.assertTrue(roleFiveUserIdSix.data.rights_ids[2].equals("destroy_providers_control_subjects"));
        Assert.assertTrue(roleFiveUserIdSix.data.global);
        Assert.assertTrue(roleFiveUserIdSix.data.provider_id.equals("525e9f767da3000002000001"));
    }

    @Test
    public void Step14(){
        controlSubjectsListWithNewElement = BasicControlSubjects.createControlSubjectAndGetList(
                6,"Тест Задание 270 Пользователь 6 14 Step","1",provider_idUserOne, false);
        String needToFound = "Тест Задание 270 Пользователь 6 14 Step";
        ArrayList<String> namesList = new ArrayList<String>();
        for (DatumControlSubjects element : controlSubjectsListWithNewElement.data) {
            namesList.add(element.name);
            if (element.name.equals(needToFound)){
                idToRemember = element.id;
            }
        }
        boolean containsElement = namesList.contains(needToFound);
        Assert.assertTrue(containsElement);
    }

    @Test
    public void Step15(){
        controlSubjectsListWithChangedElement = BasicControlSubjects.changeControlSubjectsAndGetList(
                6,124,"Тест Задание 270 Пользователь 6 15 Step",
                "2",provider_idUserOne,false);
        String nameForCheck = "Тест Задание 270 Пользователь 6 15 Step";
        String faxNumberForCheck = "2";
        boolean isFound = false;
        for (DatumControlSubjects element : controlSubjectsListWithChangedElement.data){
            if (element.name.equals(nameForCheck) && element.fax_number.equals(faxNumberForCheck)){
                isFound = true;
            }
        }
        Assert.assertTrue(isFound);
    }

    @Test
    public void Step16(){
        controlSubjectsListWithChangedElement = BasicControlSubjects.deleteControlSubjectElementAndGetList(
                1,124,provider_idUserOne);
        int idForCheck = 124;
        boolean isDeleted = true;
        for (DatumControlSubjects element : controlSubjectsListWithChangedElement.data){
            if (element.id == idForCheck){
                isDeleted = false;
            }
        }
        Assert.assertTrue(isDeleted);
    }

    @Test
    public void Step17(){
        roleFiveUserIdSix = BasicRoles.updateRole(1,5,
                "тест22",true,"show_roles");
        Assert.assertTrue(roleFiveUserIdSix.data.name.equals("тест22"));
        Assert.assertTrue(roleFiveUserIdSix.data.full_name.equals("тест22 (ДДО МВД России)"));
        Assert.assertTrue(roleFiveUserIdSix.data.provider_short_title.equals("ДДО МВД России"));
        Assert.assertTrue(roleFiveUserIdSix.data.rights_ids[0].equals("show_roles"));
        Assert.assertTrue(roleFiveUserIdSix.data.global);
        Assert.assertTrue(roleFiveUserIdSix.data.provider_id.equals("525e9f767da3000002000001"));
    }

    @Test
    public void Step18(){
        String needToFound = "Тест Задание 270 Пользователь 6 14 Step";
        controlSubjectsListWithNewElement = BasicControlSubjects.
                createControlSubjectAndGetList(1,"Тест Задание 270 Пользователь 6 14 Step"
                        ,"1",provider_idUserOne, false);
        ArrayList<String> namesList = new ArrayList<String>();
        for (DatumControlSubjects element : controlSubjectsListWithNewElement.data) {
            namesList.add(element.name);
            if (element.name.equals(needToFound)) {
                int IdValueToCheck = element.id;
                Assert.assertTrue(IdValueToCheck == 125); // == idToRemember

            }
        }
    }


}

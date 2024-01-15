package SKIP_API;

import API.BasicDocType;
import API.DTO.DocTypeDto.RootDocTypePutDto;
import API.DTO.ErrorsDTO.RootErrorNoRights;
import API.DTO.ErrorsDTO.RootNameError;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SKIP_23 {
    RootDocTypePutDto updateValue, updateValue2, updateValue3,updateValue4,updateValue5;
    RootNameError updateValue6;
    RootErrorNoRights updateValue7;
    @Test
    public void step01() {
        updateValue = BasicDocType.updateDocType(1, 1, "ю", "", true,
                "", true);
        Assert.assertTrue(updateValue.getData().getName().equals("ю"));
    }

    @Test
    public void step02() {
        updateValue2 = BasicDocType.updateDocType(1, 1, "ю", "ю", true,
                "", true);
        Assert.assertTrue(updateValue2.getData().getShort_name().equals("ю"));
    }

    @Test
    public void step03() {
        updateValue3 = BasicDocType.updateDocType(1, 1, "ю", "ю", true,
                "ю", true);
        Assert.assertTrue(updateValue3.getData().getGenitive_name().equals("ю"));

    }

    @Test
    public void step04(){
        updateValue4 = BasicDocType.updateDocType(1, 1, "ю", "ю", false,
                "ю", true);
        Assert.assertTrue(!updateValue4.data.internal);
    }

    @Test
    public void step05(){
        updateValue5= BasicDocType.updateDocType(1, 1, "ю", "ю", false,
                "ю", false);
        Assert.assertTrue(!updateValue5.data.excluded);
    }
    @Test
    public void step06(){
        updateValue6=BasicDocType.updateNameErrorDt(1,1,"","",true,"",
                true);
Assert.assertTrue(updateValue6.errors.getName()[0].equals("не может быть пустым"));
}

@Test
    public void step07(){
        updateValue7= BasicDocType.newDocTypeAccessPutError(6,1,"u","u",true,"",
                true);
        Assert.assertTrue(updateValue7.error.equals("Доступ к ресурсу запрещен"));
}

}
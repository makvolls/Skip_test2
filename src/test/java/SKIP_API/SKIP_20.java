package SKIP_API;

import API.BasicDocType;
import API.DTO.DocTypeDto.RootDocTypePostDto;
import API.DTO.ErrorsDTO.RootErrorNameShortName;
import API.DTO.ErrorsDTO.RootErrorNoRights;
import API.DTO.ErrorsDTO.RootNameError;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class SKIP_20 {

    @AfterClass
            public void delete(){
        BasicDocType.deleteDocType(1, newDocType.getData().id);
        BasicDocType.deleteDocType(1, newDocType2.getData().id);
        BasicDocType.deleteDocType(1, newDocType3.getData().id);
        BasicDocType.deleteDocType(1, newDocType4.getData().id);
        BasicDocType.deleteDocType(1, newDocType5.getData().id);
        BasicDocType.deleteDocType(1, newDocType6.getData().id);
        BasicDocType.deleteDocType(1, newDocType7.getData().id);
    }

    RootNameError docTypeNameError;
    RootDocTypePostDto newDocType, newDocType2, newDocType3, newDocType4, newDocType5,newDocType6,newDocType7;
    RootErrorNameShortName docTypeError2;
    RootErrorNoRights docTypeError3;

    @Test
    public void step01() {
        docTypeNameError = BasicDocType.postErrorName(1, "", "", true, "",
                true);
        Assert.assertTrue(docTypeNameError.getErrors().getName()[0].equals("не может быть пустым"));
    }

    @Test
    public void step02() {
        newDocType = BasicDocType.newDocType(1, "я", "", true, "", true);
        Assert.assertTrue(newDocType.data.name.equals("я"));
    }

    @Test
    public void step03() {
        newDocType2 = BasicDocType.newDocType(1, "правительственный", "", true,
                "", true);
        Assert.assertTrue(newDocType2.data.name.equals("правительственный"));
    }

    @Test
    public void step04() {
        newDocType3 = BasicDocType.newDocType(1, "ПРАВИТЕЛЬСТВЕННЫЙ", "", true
                , "", true);
        Assert.assertTrue(newDocType3.data.name.equals("ПРАВИТЕЛЬСТВЕННЫЙ"));
    }

    @Test
    public void step05(){
        newDocType4 = BasicDocType.newDocType(1, "Правительственая документация", "Прав док",
                true,
                "Правительственную документацию", true);
        Assert.assertTrue(newDocType4.data.name.equals("Правительственая документация"));
        Assert.assertTrue(newDocType4.data.short_name.equals("Прав док"));
        Assert.assertTrue(newDocType4.data.genitive_name.equals("Правительственную документацию"));
    }
    @Test
    public void step06() {
        newDocType5 = BasicDocType.newDocType(1, "dgацу", "gfdац", false,
                "gfdау", true);
        Assert.assertTrue(!newDocType5.data.internal);
    }

    @Test
    public void step07(){
        newDocType6 = BasicDocType.newDocType(1, "dghr", "gfdhr", true,
                "gfdhr", false);
        Assert.assertTrue(!newDocType6.data.excluded);
    }

    @Test
    public void step08(){
        newDocType7 = BasicDocType.newDocType(1, "л", "л", true,
                "л", true);
        Assert.assertTrue(newDocType7.data.name.equals("л"));
        Assert.assertTrue(newDocType7.data.short_name.equals("л"));
        Assert.assertTrue(newDocType7.data.genitive_name.equals("л"));

    }

   @Test
    public void step09(){
       docTypeError2=BasicDocType.newDocTypeError(1, "л", "л", true,
               "л", true);
       Assert.assertTrue(docTypeError2.getErrors().getName()[0].equals("уже существует"));
       Assert.assertTrue(docTypeError2.getErrors().getShort_name()[0].equals("уже существует"));
   }

   @Test
    public void step10(){
     docTypeError3=BasicDocType.newDocTypeNoRightsError (6,"gnf", "лngf", true,
             "nfgл", true);
     Assert.assertTrue(docTypeError3.error.equals("Доступ к ресурсу запрещен"));
   }

}

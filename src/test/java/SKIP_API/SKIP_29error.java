package SKIP_API;

import API.BasicThemes;
import API.DTO.ErrorsDTO.RootErrorNoRights;
import API.DTO.ErrorsDTO.RootNameError;
import API.DTO.ThemesDto.RootElementThemes;
import API.DTO.ThemesDto.RootThemes;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class SKIP_29error {
    String name;
    int id,id2;
    RootElementThemes newThemes,newThemes2,updateThemes,updateThemes2,updateThemes3;
    RootNameError createErThemes,createErThemes2,createErThemes3,createErThemes4;
    RootErrorNoRights createErRightThemes,updateErRightsThemes;
    RootThemes listThemes;

   @AfterClass
    public void deleteThemes(){
        BasicThemes.deleteThemesValue(1,newThemes.getData().id);
        BasicThemes.deleteThemesValue(1,newThemes2.getData().id);
    }


    @Test
    public void step01(){
        newThemes= BasicThemes.createThemes(1,"Tests1",true);
        Assert.assertTrue(newThemes.data.name.equals("Tests1"));
        Assert.assertTrue(newThemes.data.excluded);
        id = newThemes.data.getId();
    }

    @Test
    public void step02(){
        createErThemes=BasicThemes.createErrThemes(1,"Tests1",true);
        Assert.assertTrue(createErThemes.errors.name[0].equals("уже существует"));
    }

    @Test
    public void step03(){
        createErThemes2=BasicThemes.createErrThemes(1,"",true);
        Assert.assertTrue(createErThemes2.errors.name[0].equals("не может быть пустым"));
    }

    @Test
    public void step04(){

        newThemes2=BasicThemes.createThemes(1,"q",false);
        Assert.assertTrue(newThemes2.data.name.equals("q"));
        Assert.assertTrue(!newThemes2.data.excluded);
        name=newThemes2.data.getName();
        id2=newThemes2.data.getId();

    }

    @Test
    public void step05(){
createErRightThemes=BasicThemes.createThemesErRightsValue(6,"ТЕСТ",true);
Assert.assertTrue(createErRightThemes.error.equals("Доступ к ресурсу запрещен"));

    }

    @Test
    public void step06(){

        updateThemes=BasicThemes.updateThemesValue(1,id,"newTest1",true);
        Assert.assertTrue(updateThemes.data.excluded);
        Assert.assertTrue(updateThemes.data.name.equals("newTest1"));
    }

    @Test
    public void step07(){
        updateThemes2=BasicThemes.updateThemesValue(1,id,"4",true);
        Assert.assertTrue(updateThemes2.data.excluded);
        Assert.assertTrue(updateThemes2.data.name.equals("1"));
    }

    @Test
    public void step08(){
        createErThemes3=BasicThemes.updateThemesErNameValue(1,id,"",true);
        Assert.assertTrue(createErThemes3.errors.name[0].equals("не может быть пустым"));
    }

    @Test
    public void step09(){
        createErThemes4=BasicThemes.updateThemesErNameValue(1,id,name,true);
        Assert.assertTrue(createErThemes4.errors.name[0].equals("уже существует"));
    }

    @Test
    public void step10(){
        updateThemes3=BasicThemes.updateThemesValue(1,id,"fdvdas123",false);
        Assert.assertTrue(updateThemes3.data.name.equals("fdvdas123"));
        Assert.assertTrue(!updateThemes3.data.excluded);
    }
@Test
    public void step11(){
        updateErRightsThemes=BasicThemes.updateThemesErRightsValue(6,id,"fdvdas123",false);
        Assert.assertTrue(updateErRightsThemes.error.equals("Доступ к ресурсу запрещен"));
}

@Test
    public void step12(){
        listThemes=BasicThemes.getThemesList(1);

}

}

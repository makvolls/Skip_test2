package SKIP_API;

import API.BasicUser;
import API.DTO.ErrorsDTO.UserErrors.UserErrorUnauthorized;
import API.DTO.UserDto.RootGetRequestUser;
import API.DTO.UserDto.RootResponseUserDto;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SKIP_16 {

   int idAut=1;
   int idAut2=3;
   int idAut3=7;


    String officialId="528f027454c8000041000001";
    String providerId1="525e9f767da3000002000001";
    String providerId2="528f018123c700002c000001";
    String name="Столярова Юлия Валентиновна";
    String position="Заместитель начальника";
    RootResponseUserDto newValue;
    RootGetRequestUser actualUserProfile,actualUserProfile2,actualUserProfile3;
    UserErrorUnauthorized errorUnauthorized;


    @Test
    public void step01(){
        newValue= BasicUser.createUser(idAut,officialId,5);
        Assert.assertTrue(newValue.data.official_id.equals(officialId));
        Assert.assertEquals(5,newValue.data.roles[0].id);
        Assert.assertTrue(newValue.data.provider_id.equals(providerId1));
        Assert.assertTrue(newValue.data.full_name.equals(name));
        Assert.assertTrue(newValue.data.organization_short_title.equals("ОДиР ГУВО МВД России"));
        Assert.assertTrue(newValue.data.position.equals(position));
        Assert.assertTrue(newValue.data.provider_name.equals("Департамент делопроизводства и работы с обращениями " +
                "граждан и организаций Министерства внутренних дел Российской Федерации (ДДО МВД России)"));
    }

    @Test
    public void step02(){

        actualUserProfile=BasicUser.getUserProfile(idAut2);
        Assert.assertTrue(actualUserProfile.data.official_id.equals(officialId));
        Assert.assertEquals(idAut2,actualUserProfile.data.id);
        Assert.assertTrue(actualUserProfile.data.other_providers[0].id.equals(providerId1));
        Assert.assertTrue(actualUserProfile.data.provider_id.equals(providerId2));
        Assert.assertTrue(actualUserProfile.data.full_name.equals(name));
        Assert.assertTrue(actualUserProfile.data.position.equals(position));
        Assert.assertTrue(actualUserProfile.data.provider_name.equals("Отдел делопроизводства и режима ГУВО МВД России (ОДиР ГУВО)"));
        Assert.assertTrue(actualUserProfile.data.organization_full_title.equals("Отдел делопроизводства и режима Главного" +
                " управления вневедомственной охраны Министерства внутренних дел Российской Федерации"));
    }

    @Test
    public void step03(){
        actualUserProfile2=BasicUser.getUserProfile(idAut3);
        Assert.assertTrue(actualUserProfile2.data.official_id.equals(officialId));
        Assert.assertEquals(idAut3,actualUserProfile2.data.id);
        Assert.assertTrue(actualUserProfile2.data.other_providers[0].id.equals(providerId2));
        Assert.assertTrue(actualUserProfile2.data.provider_id.equals(providerId1));
        Assert.assertTrue(actualUserProfile2.data.full_name.equals(name));
        Assert.assertTrue(actualUserProfile2.data.position.equals(position));
        Assert.assertTrue(actualUserProfile2.data.provider_name.equals("Департамент делопроизводства и работы с" +
                " обращениями граждан и организаций Министерства внутренних дел Российской Федерации (ДДО МВД России)"));
        Assert.assertTrue(actualUserProfile2.data.organization_full_title.equals("Отдел делопроизводства и режима Главного" +
                " управления вневедомственной охраны Министерства внутренних дел Российской Федерации"));
    }
    @Test
    public void step04(){
        BasicUser.deleteUser(idAut,7);
    }

    @Test
    public void step05(){
        actualUserProfile3=BasicUser.getUserProfile(idAut2);
        Assert.assertTrue(actualUserProfile3.data.other_providers[0].id!=providerId1);
        Assert.assertTrue(actualUserProfile3.data.official_id.equals(officialId));
        Assert.assertEquals(idAut2,actualUserProfile3.data.id);
        Assert.assertTrue(actualUserProfile3.data.provider_id.equals(providerId2));
        Assert.assertTrue(actualUserProfile3.data.full_name.equals(name));
        Assert.assertTrue(actualUserProfile3.data.position.equals(position));
        Assert.assertTrue(actualUserProfile3.data.provider_name.equals("Отдел делопроизводства и режима ГУВО МВД России (ОДиР ГУВО)"));
        Assert.assertTrue(actualUserProfile3.data.organization_full_title.equals("Отдел делопроизводства и режима Главного" +
                " управления вневедомственной охраны Министерства внутренних дел Российской Федерации"));

    }

    @Test

    public void step06(){

        errorUnauthorized=BasicUser.getErrorUserUnauthorized(idAut3);
        Assert.assertTrue(errorUnauthorized.error.equals("Unauthorized"));
        Assert.assertTrue(errorUnauthorized.redirect_url.equals("http://api.skip.rtech.ru/v1/auth/sign_in"));
    }



    }


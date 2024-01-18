package SKIP_API;

import API.BasicProviders;
import API.DTO.ErrorsDTO.RootError;
import API.DTO.ProvidersDTO.RootSearchProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SKIP_73 {

   String idProv = "525e9f767da3000002000001";
   String idProv2= "5273bd3cef93000033000001";
   int idAut1=1;
   int idAut2=6;

    RootSearchProvider actualProvider,actualProvider2;
    RootError errorActualProvider,errorActualProvider2;

    @Test
    public void step01(){
        actualProvider= BasicProviders.getProvider(idAut1,idProv);
        Assert.assertTrue(actualProvider.data.id.equals(idProv));
        Assert.assertTrue(actualProvider.data.name.equals("Департамент делопроизводства и работы с обращениями граждан" +
                " и организаций Министерства внутренних дел Российской Федерации (ДДО МВД России)"));
        Assert.assertTrue(actualProvider.data.active);
        Assert.assertTrue(!actualProvider.data.training);
        Assert.assertTrue(actualProvider.data.code.equals("00000"));
        Assert.assertTrue(!actualProvider.data.archive);
        Assert.assertTrue(actualProvider.data.ddo_short_title.equals("ДДО МВД России"));
        Assert.assertTrue(actualProvider.data.organization_short_title.equals("МВД России"));
        Assert.assertTrue(actualProvider.data.in_organization_name.equals("МВД России"));
        Assert.assertNotNull(actualProvider.data.control_subjects);
    }

    @Test
    public void step02(){
        actualProvider2=BasicProviders.getProvider(idAut1,idProv2);
        Assert.assertTrue(actualProvider2.data.id.equals(idProv2));
        Assert.assertTrue(actualProvider2.data.name.equals("Отдел делопроизводства и режима Департамента информационных" +
                " технологий, связи и защиты информации Министерства внутренних дел" +
                " Российской Федерации (ОДиР ДИТСиЗИ МВД России)"));
        Assert.assertTrue(actualProvider2.data.active);
        Assert.assertTrue(!actualProvider2.data.training);
        Assert.assertTrue(actualProvider2.data.code.equals("00001"));
        Assert.assertTrue(!actualProvider2.data.archive);
        Assert.assertTrue(actualProvider2.data.ddo_short_title.equals("ОДиР ДИТСиЗИ МВД России"));
        Assert.assertTrue(actualProvider2.data.organization_short_title.equals("ДИТСиЗИ МВД России"));
        Assert.assertTrue(actualProvider2.data.in_organization_name.equals("Центральный аппарат МВД РФ"));
        Assert.assertNotNull(actualProvider2.data.control_subjects);
    }
    @Test

    public void step03(){

        errorActualProvider=BasicProviders.getProviderNotFound(idAut1,"12345678");
        Assert.assertTrue(errorActualProvider.error.equals("Запись для Permissions::Provider с id = 12345678 не найдена"));

    }

    @Test

    public void step04(){
        errorActualProvider2=BasicProviders.getProviderNoAccess(idAut2,idProv);
        Assert.assertTrue(errorActualProvider2.error.equals("Доступ к ресурсу запрещен"));
    }


}

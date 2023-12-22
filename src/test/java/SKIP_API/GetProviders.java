package SKIP_API;

import API.BasicProviders;
import API.DTO.ProvidersDTO.RootPermissions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetProviders extends BasicProviders {
    RootPermissions actualProviders;

    @Test
    public void getProvidersList(){
        actualProviders = getProviders(1);
        Assert.assertTrue(actualProviders.data[0].name.contains("Департамент делопроизводства и работы с обращениями граждан и организаций Министерства внутренних дел Российской Федерации (ДДО МВД России)"));
    }
}

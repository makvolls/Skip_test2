package SKIP_API;

import API.BasicProviders;
import API.DTO.ErrorsDTO.RootError;
import API.DTO.ProvidersDTO.RootPermissions;
import API.DTO.ProvidersDTO.RootSearchProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SKIP_12 {

    RootSearchProvider searchProvider;
    RootError searchErrProvider;



    @Test
    public void step01(){

        searchProvider= BasicProviders.getProvider(3,"525e9f767da3000002000001");
        Assert.assertTrue(searchProvider.data.id.equals("525e9f767da3000002000001"));
    }

    @Test
    public void step02(){
        searchErrProvider=BasicProviders.getProviderNoAccess(1,"1213124213");
        Assert.assertTrue(searchErrProvider.error.equals("Запись для Permissions::Provider с id = 1213124213 не найдена"));

    }
}

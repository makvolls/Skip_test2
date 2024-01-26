package SKIP_API;

import API.BasicProviders;
import API.BasicRoles;
import API.DTO.ErrorsDTO.RootError;
import API.DTO.ProvidersDTO.RootPermissions;
import API.DTO.ProvidersDTO.RootSearchProvider;
import API.DTO.RolesDto.RootResponseRolesDto;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SKIP_9 {
    RootError providerNoAccess;
    RootResponseRolesDto upRole;
    RootSearchProvider searchProvider, updateProvider;
    RootPermissions searchProviders;

    @Test
    public void Step01(){
        providerNoAccess = BasicProviders.getProvidersNoAccess(6);
        Assert.assertTrue(providerNoAccess.error.equals("Доступ к ресурсу запрещен"));
    }
    @Test
    public void Step02(){
        providerNoAccess = BasicProviders.getProviderNoAccess(6, "525e9f767da3000002000001");
        Assert.assertTrue(providerNoAccess.error.equals("Доступ к ресурсу запрещен"));

    }
    @Test
    public void Step03(){
        providerNoAccess = BasicProviders.updateProviderNoAccess(6, "525e9f767da3000002000001"
                , true);
        Assert.assertTrue(providerNoAccess.error.equals("Доступ к ресурсу запрещен"));

    }
    @Test
    public void Step04(){
        upRole = BasicRoles.updateRole(3, 3, "тест11", true, "index_providers");
        Assert.assertTrue(upRole.getData().rights_ids[0].equals("index_providers"));
    }
    @Test
    public void Step05(){
        searchProviders = BasicProviders.getProviders(5);
        Assert.assertNotNull(searchProviders.data);
    }
    @Test
    public void Step06(){
        searchProvider = BasicProviders.getProvider(5, "525e9f767da3000002000001");
        Assert.assertNotNull(searchProvider.data);
        Assert.assertTrue(searchProvider.getData().id.equals("525e9f767da3000002000001"));

    }
    @Test
    public void Step07(){
        providerNoAccess = BasicProviders.updateProviderNoAccess(6, "525e9f767da3000002000001"
                , true);
        Assert.assertTrue(providerNoAccess.error.equals("Доступ к ресурсу запрещен"));
    }
    @Test
    public void Step08(){
        upRole = BasicRoles.updateRole(3, 3, "тест11", true, "index_providers",
                "update_providers");
        Assert.assertTrue(upRole.getData().rights_ids[0].equals("index_providers"));
        Assert.assertTrue(upRole.getData().rights_ids[1].equals("update_providers"));
    }
    @Test
    public void Step09(){
        updateProvider = BasicProviders.updateProvider(5, "525e9f767da3000002000001", true);
        Assert.assertTrue(updateProvider.getData().training);

    }
    @Test
    public void Step10(){
        searchProviders = BasicProviders.getProviders(5);
        Assert.assertNotNull(searchProviders.data);

        searchProvider = BasicProviders.getProvider(5, "525e9f767da3000002000001");
        Assert.assertNotNull(searchProvider.data);
        Assert.assertTrue(searchProvider.getData().id.equals("525e9f767da3000002000001"));
    }
    @Test
    public void Step11(){
        updateProvider = BasicProviders.updateProvider(5, "525e9f767da3000002000001", false);
        Assert.assertTrue(!updateProvider.getData().training);
    }
    @Test
    public void Step12(){
        upRole = BasicRoles.updateRole(3, 3, "тест11", true, "update_providers");
        Assert.assertTrue(upRole.getData().rights_ids[0].equals("update_providers"));
    }
    @Test
    public void Step13(){
        providerNoAccess = BasicProviders.getProvidersNoAccess(6);
        Assert.assertTrue(providerNoAccess.error.equals("Доступ к ресурсу запрещен"));

        providerNoAccess = BasicProviders.getProviderNoAccess(6, "525e9f767da3000002000001");
        Assert.assertTrue(providerNoAccess.error.equals("Доступ к ресурсу запрещен"));

        providerNoAccess = BasicProviders.updateProviderNoAccess(6, "525e9f767da3000002000001"
                , true);
        Assert.assertTrue(providerNoAccess.error.equals("Доступ к ресурсу запрещен"));
    }

}

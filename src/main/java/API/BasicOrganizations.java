package API;

import API.DTO.OrganizationsDto.RootOrganizations;
import API.DTO.OshsMvdOfficialsDto.RootOshsMvdOfficials;

public class BasicOrganizations {

   public static String API_ORGANIZATIONS="http://api.skip.rtech.ru/v1/oshs/mvd/organizations";

    public static RootOrganizations getOrganizations(int idAut){
        RootOrganizations listOrganizations=BasicApi.get(API_ORGANIZATIONS,idAut).as(RootOrganizations.class);
        return listOrganizations;
    }
    }


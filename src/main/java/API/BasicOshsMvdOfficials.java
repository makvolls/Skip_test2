package API;

import API.DTO.OshsMvdOfficialsDto.RootOshsMvdOfficials;

public class BasicOshsMvdOfficials {

    public static String API_OSHS_MVD_OFFICIALS="http://api.skip.rtech.ru/v1/oshs/mvd/officials";

  public static RootOshsMvdOfficials getOshsMvdOfficials(int idAut){
      RootOshsMvdOfficials listOshSMvdOddicials=BasicApi.get(API_OSHS_MVD_OFFICIALS,idAut).as(RootOshsMvdOfficials.class);
      return listOshSMvdOddicials;
  }


}

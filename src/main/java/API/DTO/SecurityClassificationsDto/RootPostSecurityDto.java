package API.DTO.SecurityClassificationsDto;

public class RootPostSecurityDto {
    public DatumPostSecurityDto getData() {
        return data;
    }

    public RootPostSecurityDto(DatumPostSecurityDto data) {
        this.data = data;
    }

    public void setData(DatumPostSecurityDto data) {
        this.data = data;
    }

    public DatumPostSecurityDto data;
}

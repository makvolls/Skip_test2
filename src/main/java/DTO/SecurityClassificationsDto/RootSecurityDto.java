package DTO.SecurityClassificationsDto;

public class RootSecurityDto {
    public DatumSecurityDto[] data;

    public DatumSecurityDto[] getData() {
        return data;
    }

    public RootSecurityDto(DatumSecurityDto[] data) {
        this.data = data;
    }

    public void setData(DatumSecurityDto[] data) {
        this.data = data;
    }
}

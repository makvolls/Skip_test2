package API.DTO.ControlPeriodStatesDto;

public class RootPutCpsDto {
    public DatumPostControlDto data;

    public RootPutCpsDto(DatumPostControlDto data) {
        this.data = data;
    }

    public DatumPostControlDto getData() {
        return data;
    }

    public void setData(DatumPostControlDto data) {
        this.data = data;
    }
}

package API.DTO.ControlPeriodStatesDto;

public class RootPostControlDto {
    public DatumPostControlDto data;

    public DatumPostControlDto getData() {
        return data;
    }

    public RootPostControlDto(DatumPostControlDto data) {
        this.data = data;
    }

    public void setData(DatumPostControlDto data) {
        this.data = data;
    }
}

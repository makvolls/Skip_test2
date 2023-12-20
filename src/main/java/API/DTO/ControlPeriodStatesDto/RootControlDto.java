package API.DTO.ControlPeriodStatesDto;

public class RootControlDto {
    public DatumControlDto[] data;

    public DatumControlDto[] getData() {
        return data;
    }

    public RootControlDto(DatumControlDto[] data) {
        this.data = data;
    }

    public void setData(DatumControlDto[] data) {
        this.data = data;
    }
}

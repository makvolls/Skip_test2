package API.DTO.EventsDto;

public class RootEventsDto {
    public RootEventsDto(DatumEventsDto data) {
        this.data = data;
    }

    public DatumEventsDto getData() {
        return data;
    }

    public void setData(DatumEventsDto data) {
        this.data = data;
    }

    public DatumEventsDto data;
}

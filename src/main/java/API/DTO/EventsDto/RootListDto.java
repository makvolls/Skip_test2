package API.DTO.EventsDto;

public class RootListDto {
    public RootListDto(ResponseSearchDto[] data) {
        this.data = data;
    }

    public ResponseSearchDto[] getData() {
        return data;
    }

    public void setData(ResponseSearchDto[] data) {
        this.data = data;
    }

    public ResponseSearchDto[] data;
}

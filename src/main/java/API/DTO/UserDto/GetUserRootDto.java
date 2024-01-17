package API.DTO.UserDto;

public class GetUserRootDto {
    public Meta meta;

    public Meta getMeta() {
        return meta;
    }

    public GetUserRootDto(Meta meta, GetUserDatumDto[] data) {
        this.meta = meta;
        this.data = data;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public GetUserDatumDto[] getData() {
        return data;
    }

    public void setData(GetUserDatumDto[] data) {
        this.data = data;
    }

    public GetUserDatumDto[] data;
}

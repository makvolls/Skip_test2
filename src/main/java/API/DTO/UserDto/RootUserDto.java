package API.DTO.UserDto;

public class RootUserDto {
    public DataUserDto[] getData() {
        return data;
    }

    public void setData(DataUserDto[] data) {
        this.data = data;
    }

    public DataUserDto[] data;

    public RootUserDto(DataUserDto[] data, Meta meta) {
        this.data = data;
        this.meta = meta;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Meta meta;
}

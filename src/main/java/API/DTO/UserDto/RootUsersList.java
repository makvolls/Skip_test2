package API.DTO.UserDto;

public class RootUsersList {
    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public DataUsersList[] getData() {
        return data;
    }

    public void setData(DataUsersList[] data) {
        this.data = data;
    }

    public RootUsersList(Meta meta, DataUsersList[] data) {
        this.meta = meta;
        this.data = data;
    }

    public Meta meta;
    public DataUsersList[] data;
}

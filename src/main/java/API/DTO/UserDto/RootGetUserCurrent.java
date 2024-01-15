package API.DTO.UserDto;

public class RootGetUserCurrent {

    public DatumGetUserCurrent data;

    public DatumGetUserCurrent getData() {
        return data;
    }

    public RootGetUserCurrent(DatumGetUserCurrent data) {
        this.data = data;
    }

    public void setData(DatumGetUserCurrent data) {
        this.data = data;
    }
}

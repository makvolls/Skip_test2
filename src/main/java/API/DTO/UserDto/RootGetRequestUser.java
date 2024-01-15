package API.DTO.UserDto;

public class RootGetRequestUser {
    public DataGetUserDto getData() {
        return data;
    }

    public RootGetRequestUser(DataGetUserDto data) {
        this.data = data;
    }

    public void setData(DataGetUserDto data) {
        this.data = data;
    }

    public DataGetUserDto data;
}

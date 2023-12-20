package API.DTO.UserDto;

public class RootUserWithId {
    public DataUserDto getData() {
        return data;
    }

    public void setData(DataUserDto data) {
        this.data = data;
    }

    public RootUserWithId(DataUserDto data) {
        this.data = data;
    }

    public DataUserDto data;
}

package API.DTO.UserDto;

public class RootUserWithIdCS {
    public DataUserCS getData() {
        return data;
    }

    public void setData(DataUserCS data) {
        this.data = data;
    }

    public RootUserWithIdCS(DataUserCS data) {
        this.data = data;
    }

    public DataUserCS data;
}

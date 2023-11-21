package DTO.UserDto;

public class RootUserDto {
    public DataUserDto getData() {
        return data;
    }

    public void setData(DataUserDto data) {
        this.data = data;
    }

    public DataUserDto data;

    public RootUserDto(DataUserDto data) {
        this.data = data;
    }
}

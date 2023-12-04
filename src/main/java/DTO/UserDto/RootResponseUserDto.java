package DTO.UserDto;

public class RootResponseUserDto {
    public DataResponseUserDto getData() {
        return data;
    }

    public void setData(DataResponseUserDto data) {
        this.data = data;
    }

    public RootResponseUserDto(DataResponseUserDto data) {
        this.data = data;
    }

    public DataResponseUserDto data;

}

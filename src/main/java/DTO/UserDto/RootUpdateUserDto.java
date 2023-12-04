package DTO.UserDto;

public class RootUpdateUserDto {
    public int[] getRole_ids() {
        return role_ids;
    }

    public RootUpdateUserDto(int[] role_ids) {
        this.role_ids = role_ids;
    }

    public void setRole_ids(int[] role_ids) {
        this.role_ids = role_ids;
    }

    public int[] role_ids;

}

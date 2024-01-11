package API.DTO.UserDto;

public class RootCreateUserDto {
    public String getOfficial_id() {
        return official_id;
    }

    public void setOfficial_id(String official_id) {
        this.official_id = official_id;
    }

    public int[] getRole_ids() {
        return role_ids;
    }

    public RootCreateUserDto(String official_id, int[] role_ids) {
        this.official_id = official_id;
        this.role_ids = role_ids;
    }

    public void setRole_ids(int[] role_ids) {
        this.role_ids = role_ids;
    }

    public String official_id;
    public int[] role_ids;

}

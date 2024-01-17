package API.DTO.UserDto;

public class GetUserRoleDto {
    public int getId() {
        return id;
    }

    public GetUserRoleDto(int id, String full_name) {
        this.id = id;
        this.full_name = full_name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int id;
    public String full_name;
}

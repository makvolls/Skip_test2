package API.DTO.UserDto;

public class GetUserDatumDto {
    public int id;
    public String full_name;
    public String organization_short_title;

    public int getId() {
        return id;
    }

    public GetUserDatumDto(int id, String full_name, String organization_short_title, GetUserRoleDto[] roles) {
        this.id = id;
        this.full_name = full_name;
        this.organization_short_title = organization_short_title;
        this.roles = roles;
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

    public String getOrganization_short_title() {
        return organization_short_title;
    }

    public void setOrganization_short_title(String organization_short_title) {
        this.organization_short_title = organization_short_title;
    }

    public GetUserRoleDto[] getRoles() {
        return roles;
    }

    public void setRoles(GetUserRoleDto[] roles) {
        this.roles = roles;
    }

    public GetUserRoleDto[] roles;
}

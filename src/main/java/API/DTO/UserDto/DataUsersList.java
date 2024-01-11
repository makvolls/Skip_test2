package API.DTO.UserDto;

public class DataUsersList {
    public int getId() {
        return id;
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

    public RoleUserDto[] getRoles() {
        return roles;
    }

    public void setRoles(RoleUserDto[] roles) {
        this.roles = roles;
    }

    public DataUsersList(int id, String full_name, String organization_short_title, RoleUserDto[] roles) {
        this.id = id;
        this.full_name = full_name;
        this.organization_short_title = organization_short_title;
        this.roles = roles;
    }

    public int id;
    public String full_name;
    public String organization_short_title;
    public RoleUserDto[] roles;
}

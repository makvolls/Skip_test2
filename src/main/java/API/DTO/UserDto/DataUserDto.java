package API.DTO.UserDto;

public class DataUserDto {


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOfficial_id() {
        return official_id;
    }

    public void setOfficial_id(String official_id) {
        this.official_id = official_id;
    }

    public String getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(String provider_id) {
        this.provider_id = provider_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOrganization_short_title() {
        return organization_short_title;
    }

    public void setOrganization_short_title(String organization_short_title) {
        this.organization_short_title = organization_short_title;
    }

    public String getProvider_name() {
        return provider_name;
    }

    public void setProvider_name(String provider_name) {
        this.provider_name = provider_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleUserDto[] getRoles() {
        return roles;
    }

    public void setRoles(RoleUserDto[] roles) {
        this.roles = roles;
    }

    public ControlSubjectsUser getControl_subject() {
        return control_subject;
    }

    public void setControl_subject(ControlSubjectsUser control_subject) {
        this.control_subject = control_subject;
    }

    public DataUserDto(int id, String official_id, String provider_id,
                       String full_name, String position, String organization_short_title,
                       String provider_name, String phone, String email, RoleUserDto[] roles,
                       ControlSubjectsUser control_subject) {
        this.id = id;
        this.official_id = official_id;
        this.provider_id = provider_id;
        this.full_name = full_name;
        this.position = position;
        this.organization_short_title = organization_short_title;
        this.provider_name = provider_name;
        this.phone = phone;
        this.email = email;
        this.roles = roles;
        this.control_subject = control_subject;
    }

    public int id;
    public String official_id;
    public String provider_id;
    public String full_name;
    public String position;
    public String organization_short_title;
    public String provider_name;
    public String phone;
    public String email;
    public RoleUserDto[] roles;
    public ControlSubjectsUser control_subject;
}

package API.DTO.UserDto;

public class DataGetUserDto {
    public int id;
    public String official_id;
    public String provider_id;
    public String full_name;
    public String organization_full_title;
    public String position;
    public String provider_name;

    public DataGetUserDto(int id, String official_id, String provider_id, String full_name, String organization_full_title,
                          String position, String provider_name, RoleUserDto[] roles, OtherProviderUserDto[] other_providers) {
        this.id = id;
        this.official_id = official_id;
        this.provider_id = provider_id;
        this.full_name = full_name;
        this.organization_full_title = organization_full_title;
        this.position = position;
        this.provider_name = provider_name;
        this.roles = roles;
        this.other_providers = other_providers;
    }

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

    public String getOrganization_full_title() {
        return organization_full_title;
    }

    public void setOrganization_full_title(String organization_full_title) {
        this.organization_full_title = organization_full_title;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getProvider_name() {
        return provider_name;
    }

    public void setProvider_name(String provider_name) {
        this.provider_name = provider_name;
    }

    public RoleUserDto[] getRoles() {
        return roles;
    }

    public void setRoles(RoleUserDto[] roles) {
        this.roles = roles;
    }

    public OtherProviderUserDto[] getOther_providers() {
        return other_providers;
    }

    public void setOther_providers(OtherProviderUserDto[] other_providers) {
        this.other_providers = other_providers;
    }

    public RoleUserDto[] roles;
    public OtherProviderUserDto[] other_providers;
}

package DTO.RolesDto;

public class DataResponseRolesDto {
    public int id;
    public String name;
    public String full_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public DataResponseRolesDto(int id, String name, String full_name, String provider_short_title, boolean global, String[] rights_ids, String provider_id) {
        this.id = id;
        this.name = name;
        this.full_name = full_name;
        this.provider_short_title = provider_short_title;
        this.global = global;
        this.rights_ids = rights_ids;
        this.provider_id = provider_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getProvider_short_title() {
        return provider_short_title;
    }

    public void setProvider_short_title(String provider_short_title) {
        this.provider_short_title = provider_short_title;
    }

    public boolean isGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }

    public String[] getRights_ids() {
        return rights_ids;
    }

    public void setRights_ids(String[] rights_ids) {
        this.rights_ids = rights_ids;
    }

    public String getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(String provider_id) {
        this.provider_id = provider_id;
    }

    public String provider_short_title;
    public boolean global;
    public String[] rights_ids;
    public String provider_id;
}

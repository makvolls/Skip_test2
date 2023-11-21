package DTO.RolesDto;

public class DatumRolesDto {
    public int id;
    public String name;

    public int getId() {
        return id;
    }

    public DatumRolesDto(int id, String name, String full_name, String provider_short_title, boolean global, String provider_id) {
        this.id = id;
        this.name = name;
        this.full_name = full_name;
        this.provider_short_title = provider_short_title;
        this.global = global;
        this.provider_id = provider_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
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

    public String getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(String provider_id) {
        this.provider_id = provider_id;
    }

    public String full_name;
    public String provider_short_title;
    public boolean global;
    public String provider_id;
}

package DTO.ProvidersDTO;

public class DatumPermissions {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public DatumPermissions(String id, String name, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public String id;
    public String name;
    public boolean active;
}

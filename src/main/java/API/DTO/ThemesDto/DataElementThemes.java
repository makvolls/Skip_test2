package API.DTO.ThemesDto;

public class DataElementThemes {
    public int getId() {
        return id;
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isExcluded() {
        return excluded;
    }

    public void setExcluded(boolean excluded) {
        this.excluded = excluded;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(String provider_id) {
        this.provider_id = provider_id;
    }

    public DataElementThemes(int id, String name, int priority, boolean excluded, boolean deleted, String provider_id) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.excluded = excluded;
        this.deleted = deleted;
        this.provider_id = provider_id;
    }

    public int id;
    public String name;
    public int priority;
    public boolean excluded;
    public boolean deleted;
    public String provider_id;
}

package API.DTO.SecurityClassificationsDto;

public class DatumPostSecurityDto {
    public int id;
    public String name;
    public String short_name;

    public int getId() {
        return id;
    }

    public DatumPostSecurityDto(int id, String name, String short_name, boolean excluded, boolean deleted, int priority) {
        this.id = id;
        this.name = name;
        this.short_name = short_name;
        this.excluded = excluded;
        this.deleted = deleted;
        this.priority = priority;
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

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean excluded;
    public boolean deleted;
    public int priority;
}

package DTO.UrgenciesDTO;

public class DatumUrgencies {
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

    public DatumUrgencies(int id, String name, int priority, boolean excluded, boolean deleted) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.excluded = excluded;
        this.deleted = deleted;
    }

    public int id;
    public String name;
    public int priority;
    public boolean excluded;
    public boolean deleted;
}

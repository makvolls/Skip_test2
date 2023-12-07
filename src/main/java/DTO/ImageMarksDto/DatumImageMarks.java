package DTO.ImageMarksDto;

public class DatumImageMarks {
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

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isExcluded() {
        return excluded;
    }

    public void setExcluded(boolean excluded) {
        this.excluded = excluded;
    }

    public DatumImageMarks(int id, String name, String short_name, int priority, boolean deleted, boolean excluded) {
        this.id = id;
        this.name = name;
        this.short_name = short_name;
        this.priority = priority;
        this.deleted = deleted;
        this.excluded = excluded;
    }

    public int id;
    public String name;
    public String short_name;
    public int priority;
    public boolean deleted;
    public boolean excluded;
}

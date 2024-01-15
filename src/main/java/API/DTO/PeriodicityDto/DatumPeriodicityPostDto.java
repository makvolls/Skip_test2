package API.DTO.PeriodicityDto;

public class DatumPeriodicityPostDto {
    public int id;
    public String name;
    public int priority;

    public int getId() {
        return id;
    }

    public DatumPeriodicityPostDto(int id, String name, int priority, boolean deleted, boolean excluded) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.deleted = deleted;
        this.excluded = excluded;
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

    public boolean deleted;
    public boolean excluded;
}

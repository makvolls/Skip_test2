package DTO.DeadlineBasesDto;

public class DatumDeadlineBases {
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public DurationDeadlineBases getDuration() {
        return duration;
    }

    public void setDuration(DurationDeadlineBases duration) {
        this.duration = duration;
    }

    public boolean isDeadline_only() {
        return deadline_only;
    }

    public void setDeadline_only(boolean deadline_only) {
        this.deadline_only = deadline_only;
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

    public String getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(String provider_id) {
        this.provider_id = provider_id;
    }

    public DatumDeadlineBases(int id, String name, String short_name, String note, DurationDeadlineBases duration, boolean deadline_only, boolean excluded, boolean deleted, int priority, String provider_id) {
        this.id = id;
        this.name = name;
        this.short_name = short_name;
        this.note = note;
        this.duration = duration;
        this.deadline_only = deadline_only;
        this.excluded = excluded;
        this.deleted = deleted;
        this.priority = priority;
        this.provider_id = provider_id;
    }

    public int id;
    public String name;
    public String short_name;
    public String note;
    public DurationDeadlineBases duration;
    public boolean deadline_only;
    public boolean excluded;
    public boolean deleted;
    public int priority;
    public String provider_id;
}

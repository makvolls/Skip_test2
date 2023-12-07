package DTO.DeadlineBasesDto;

public class CreateValueDeadlineBases {
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

    public String getDuration_id() {
        return duration_id;
    }

    public void setDuration_id(String duration_id) {
        this.duration_id = duration_id;
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

    public CreateValueDeadlineBases(String name, String short_name, String note, String duration_id, boolean deadline_only, boolean excluded) {
        this.name = name;
        this.short_name = short_name;
        this.note = note;
        this.duration_id = duration_id;
        this.deadline_only = deadline_only;
        this.excluded = excluded;
    }

    public String name;
    public String short_name;
    public String note;
    public String duration_id;
    public boolean deadline_only;
    public boolean excluded;
}

package DTO.ImageMarksDto;

public class CreateValueImageMarks {
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

    public CreateValueImageMarks(String name, String short_name, boolean excluded) {
        this.name = name;
        this.short_name = short_name;
        this.excluded = excluded;
    }

    public String name;
    public String short_name;
    public boolean excluded;
}

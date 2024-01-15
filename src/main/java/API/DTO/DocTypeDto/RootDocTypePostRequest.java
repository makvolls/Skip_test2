package API.DTO.DocTypeDto;

public class RootDocTypePostRequest {
    public String name;
    public String short_name;

    public String getName() {
        return name;
    }

    public RootDocTypePostRequest(String name, String short_name, boolean internal, String genitive_name, boolean excluded) {
        this.name = name;
        this.short_name = short_name;
        this.internal = internal;
        this.genitive_name = genitive_name;
        this.excluded = excluded;
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

    public boolean isInternal() {
        return internal;
    }

    public void setInternal(boolean internal) {
        this.internal = internal;
    }

    public String getGenitive_name() {
        return genitive_name;
    }

    public void setGenitive_name(String genitive_name) {
        this.genitive_name = genitive_name;
    }

    public boolean isExcluded() {
        return excluded;
    }

    public void setExcluded(boolean excluded) {
        this.excluded = excluded;
    }

    public boolean internal;
    public String genitive_name;
    public boolean excluded;
}

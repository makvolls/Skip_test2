package API.DTO.DocTypeDto;

public class DatumDocTypePostDto {
    public int id;
    public String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DatumDocTypePostDto(String name, String short_name, boolean internal, String genitive_name, boolean deleted, boolean excluded) {
        this.name = name;
        this.short_name = short_name;
        this.internal = internal;
        this.genitive_name = genitive_name;
        this.deleted = deleted;
        this.excluded = excluded;
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

    public String getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(String provider_id) {
        this.provider_id = provider_id;
    }

    public String short_name;
    public boolean internal;
    public String genitive_name;
    public int priority;
    public boolean deleted;
    public boolean excluded;
    public String provider_id;
}

package API.DTO.SecurityClassificationsDto;

public class RequestSecurityClassificationDto {
    public String name;
    public String short_name;

    public String getName() {
        return name;
    }

    public RequestSecurityClassificationDto(String name, String short_name, boolean excluded) {
        this.name = name;
        this.short_name = short_name;
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

    public boolean isExcluded() {
        return excluded;
    }

    public void setExcluded(boolean excluded) {
        this.excluded = excluded;
    }

    public boolean excluded;
}

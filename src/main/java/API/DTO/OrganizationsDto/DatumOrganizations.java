package API.DTO.OrganizationsDto;

public class DatumOrganizations {
    public String id;
    public String full_title;

    public String getId() {
        return id;
    }

    public DatumOrganizations(String id, String full_title, String short_title) {
        this.id = id;
        this.full_title = full_title;
        this.short_title = short_title;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFull_title() {
        return full_title;
    }

    public void setFull_title(String full_title) {
        this.full_title = full_title;
    }

    public String getShort_title() {
        return short_title;
    }

    public void setShort_title(String short_title) {
        this.short_title = short_title;
    }

    public String short_title;
}

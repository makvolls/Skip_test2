package API.DTO.DocumentsDto;

public class ControlOfficialsDocumentDto {
    public String id;
    public String last_name;

    public ControlOfficialsDocumentDto(String id, String last_name, String short_name_with_org_and_pos,
                                       String organization_short_title) {
        this.id = id;
        this.last_name = last_name;
        this.short_name_with_org_and_pos = short_name_with_org_and_pos;
        this.organization_short_title = organization_short_title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getShort_name_with_org_and_pos() {
        return short_name_with_org_and_pos;
    }

    public void setShort_name_with_org_and_pos(String short_name_with_org_and_pos) {
        this.short_name_with_org_and_pos = short_name_with_org_and_pos;
    }

    public String getOrganization_short_title() {
        return organization_short_title;
    }

    public void setOrganization_short_title(String organization_short_title) {
        this.organization_short_title = organization_short_title;
    }

    public String short_name_with_org_and_pos;
    public String organization_short_title;
}

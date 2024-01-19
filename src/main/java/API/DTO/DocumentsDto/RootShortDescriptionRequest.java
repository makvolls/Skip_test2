package API.DTO.DocumentsDto;

public class RootShortDescriptionRequest {
    public String short_description;

    public String getShort_description() {
        return short_description;
    }

    public RootShortDescriptionRequest(String short_description) {
        this.short_description = short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }
}

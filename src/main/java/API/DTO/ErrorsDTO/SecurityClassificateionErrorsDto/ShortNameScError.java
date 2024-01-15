package API.DTO.ErrorsDTO.SecurityClassificateionErrorsDto;

public class ShortNameScError {
    public String[] getShort_name() {
        return short_name;
    }

    public void setShort_name(String[] short_name) {
        this.short_name = short_name;
    }

    public ShortNameScError(String[] short_name) {
        this.short_name = short_name;
    }

    public String[] short_name;
}

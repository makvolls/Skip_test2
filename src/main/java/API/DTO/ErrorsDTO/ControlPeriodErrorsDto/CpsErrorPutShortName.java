package API.DTO.ErrorsDTO.ControlPeriodErrorsDto;

public class CpsErrorPutShortName {
    public String[] getShort_name() {
        return short_name;
    }

    public CpsErrorPutShortName(String[] short_name) {
        this.short_name = short_name;
    }

    public void setShort_name(String[] short_name) {
        this.short_name = short_name;
    }

    public String[] short_name;
}

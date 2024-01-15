package API.DTO.ErrorsDTO.ControlPeriodErrorsDto;

public class CpsErrorPutNs {
    public String[] name;

    public CpsErrorPutNs(String[] name, String[] short_name) {
        this.name = name;
        this.short_name = short_name;
    }

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public String[] getShort_name() {
        return short_name;
    }

    public void setShort_name(String[] short_name) {
        this.short_name = short_name;
    }

    public String[] short_name;
}

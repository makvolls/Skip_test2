package API.DTO.ErrorsDTO.ControlPeriodErrorsDto;

public class RootCprErrorPutShortName {
    public CpsErrorPutShortName getErrors() {
        return errors;
    }

    public RootCprErrorPutShortName(CpsErrorPutShortName errors) {
        this.errors = errors;
    }

    public void setErrors(CpsErrorPutShortName errors) {
        this.errors = errors;
    }

    public CpsErrorPutShortName errors;
}

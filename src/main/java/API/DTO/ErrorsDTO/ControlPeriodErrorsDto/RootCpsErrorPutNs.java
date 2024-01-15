package API.DTO.ErrorsDTO.ControlPeriodErrorsDto;

public class RootCpsErrorPutNs {
    public CpsErrorPutNs getErrors() {
        return errors;
    }

    public void setErrors(CpsErrorPutNs errors) {
        this.errors = errors;
    }

    public RootCpsErrorPutNs(CpsErrorPutNs errors) {
        this.errors = errors;
    }

    public CpsErrorPutNs errors;
}

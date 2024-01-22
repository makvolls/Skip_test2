package API.DTO.ErrorsDTO.CalcDeadlinesErrors;

public class RootCdStartDateError {
    public DatumCdStartDateError getErrors() {
        return errors;
    }

    public RootCdStartDateError(DatumCdStartDateError errors) {
        this.errors = errors;
    }

    public void setErrors(DatumCdStartDateError errors) {
        this.errors = errors;
    }

    public DatumCdStartDateError errors;
}

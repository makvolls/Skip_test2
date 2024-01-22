package API.DTO.ErrorsDTO.CalcDeadlinesErrors;

public class RootStartDateDeriodicityOdError {
    public DatumStartDatePeriodicityIdError getErrors() {
        return errors;
    }

    public RootStartDateDeriodicityOdError(DatumStartDatePeriodicityIdError errors) {
        this.errors = errors;
    }

    public void setErrors(DatumStartDatePeriodicityIdError errors) {
        this.errors = errors;
    }

    public DatumStartDatePeriodicityIdError errors;
}

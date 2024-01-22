package API.DTO.ErrorsDTO.CalcDeadlinesErrors;

public class RootPeriodInDaysError {
    public DatumPeriodInDaysError getErrors() {
        return errors;
    }

    public void setErrors(DatumPeriodInDaysError errors) {
        this.errors = errors;
    }

    public RootPeriodInDaysError(DatumPeriodInDaysError errors) {
        this.errors = errors;
    }

    public DatumPeriodInDaysError errors;
}

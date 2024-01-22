package API.DTO.ErrorsDTO.CalcDeadlinesErrors;

public class RootPeriodicityIdPeriodInDaysError {
    public DatumPeriodicityIdPeriodInDaysError errors;

    public RootPeriodicityIdPeriodInDaysError(DatumPeriodicityIdPeriodInDaysError errors) {
        this.errors = errors;
    }

    public DatumPeriodicityIdPeriodInDaysError getErrors() {
        return errors;
    }

    public void setErrors(DatumPeriodicityIdPeriodInDaysError errors) {
        this.errors = errors;
    }
}

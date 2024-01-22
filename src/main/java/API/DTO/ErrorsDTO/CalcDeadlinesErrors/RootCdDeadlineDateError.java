package API.DTO.ErrorsDTO.CalcDeadlinesErrors;

public class RootCdDeadlineDateError {
    public DatumCdDeadlineDateError getErrors() {
        return errors;
    }

    public RootCdDeadlineDateError(DatumCdDeadlineDateError errors) {
        this.errors = errors;
    }

    public void setErrors(DatumCdDeadlineDateError errors) {
        this.errors = errors;
    }

    public DatumCdDeadlineDateError errors;
}

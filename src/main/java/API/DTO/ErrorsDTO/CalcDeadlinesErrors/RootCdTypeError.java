package API.DTO.ErrorsDTO.CalcDeadlinesErrors;

public class RootCdTypeError {
    public DatumCdTypeError getErrors() {
        return errors;
    }

    public RootCdTypeError(DatumCdTypeError errors) {
        this.errors = errors;
    }

    public void setErrors(DatumCdTypeError errors) {
        this.errors = errors;
    }

    public DatumCdTypeError errors;
}

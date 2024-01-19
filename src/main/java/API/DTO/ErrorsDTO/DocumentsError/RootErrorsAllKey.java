package API.DTO.ErrorsDTO.DocumentsError;

public class RootErrorsAllKey {
    public ErrorsAllKey errors;

    public RootErrorsAllKey(ErrorsAllKey errors) {
        this.errors = errors;
    }

    public ErrorsAllKey getErrors() {
        return errors;
    }

    public void setErrors(ErrorsAllKey errors) {
        this.errors = errors;
    }
}

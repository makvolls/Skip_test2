package API.DTO.ErrorsDTO.DocumentsError;

public class RootErrorsShortDescriptions {
    public ErrorsShortDescription errors;

    public ErrorsShortDescription getErrors() {
        return errors;
    }

    public RootErrorsShortDescriptions(ErrorsShortDescription errors) {
        this.errors = errors;
    }

    public void setErrors(ErrorsShortDescription errors) {
        this.errors = errors;
    }
}

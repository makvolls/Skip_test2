package API.DTO.ErrorsDTO.ControlSubjects;

public class RootUnintendedValue {
    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
    }

    public RootUnintendedValue(Errors errors) {
        this.errors = errors;
    }

    public Errors errors;
}

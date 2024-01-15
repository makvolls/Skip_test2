package API.DTO.ErrorsDTO.EventStateErrorsDto;

public class RootEventStateErrorName {
    public EventStateErrorsNameDto getErrors() {
        return errors;
    }

    public RootEventStateErrorName(EventStateErrorsNameDto errors) {
        this.errors = errors;
    }

    public void setErrors(EventStateErrorsNameDto errors) {
        this.errors = errors;
    }

    public EventStateErrorsNameDto errors;
}

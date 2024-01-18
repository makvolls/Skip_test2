package API.DTO.ErrorsDTO.EventsErrorsDto;

public class RootErrorESI {
    public ErrorsEventsESI getErrors() {
        return errors;
    }

    public RootErrorESI(ErrorsEventsESI errors) {
        this.errors = errors;
    }

    public void setErrors(ErrorsEventsESI errors) {
        this.errors = errors;
    }

    public ErrorsEventsESI errors;
}

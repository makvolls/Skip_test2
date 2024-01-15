package API.DTO.ErrorsDTO.EventsErrorsDto;

public class RootEventStateError {
    public EventStatesErrorNoList getErrors() {
        return errors;
    }

    public void setErrors(EventStatesErrorNoList errors) {
        this.errors = errors;
    }

    public RootEventStateError(EventStatesErrorNoList errors) {
        this.errors = errors;
    }

    public EventStatesErrorNoList errors;
}

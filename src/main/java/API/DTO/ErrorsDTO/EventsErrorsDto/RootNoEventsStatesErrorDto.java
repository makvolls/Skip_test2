package API.DTO.ErrorsDTO.EventsErrorsDto;

public class RootNoEventsStatesErrorDto {
    public String error;

    public String getError() {
        return error;
    }

    public RootNoEventsStatesErrorDto(String error) {
        this.error = error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

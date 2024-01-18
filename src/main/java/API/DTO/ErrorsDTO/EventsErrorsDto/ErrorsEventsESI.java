package API.DTO.ErrorsDTO.EventsErrorsDto;

public class ErrorsEventsESI {
    public String[] getEvent_state_ids() {
        return event_state_ids;
    }

    public ErrorsEventsESI(String[] event_state_ids) {
        this.event_state_ids = event_state_ids;
    }

    public void setEvent_state_ids(String[] event_state_ids) {
        this.event_state_ids = event_state_ids;
    }

    public String[] event_state_ids;
}

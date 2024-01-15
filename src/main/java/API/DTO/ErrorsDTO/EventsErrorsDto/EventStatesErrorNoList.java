package API.DTO.ErrorsDTO.EventsErrorsDto;

public class EventStatesErrorNoList {
    public String[] event_state_ids;

    public EventStatesErrorNoList(String[] event_state_ids) {
        this.event_state_ids = event_state_ids;
    }

    public String[] getEvent_state_ids() {
        return event_state_ids;
    }

    public void setEvent_state_ids(String[] event_state_ids) {
        this.event_state_ids = event_state_ids;
    }
}

package API.DTO.EventsDto;

public class RequestEventsDto {
    public String name;
    public String short_name;
    public boolean excluded;
    public Object[] event_state_ids;

    public RequestEventsDto(String name, String short_name, boolean excluded, Object[] event_state_ids) {
        this.name = name;
        this.short_name = short_name;
        this.excluded = excluded;
        this.event_state_ids = event_state_ids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public boolean isExcluded() {
        return excluded;
    }

    public void setExcluded(boolean excluded) {
        this.excluded = excluded;
    }

    public Object[] getEvent_state_ids() {
        return event_state_ids;
    }

    public void setEvent_state_ids(Object[] event_state_ids) {
        this.event_state_ids = event_state_ids;
    }
}

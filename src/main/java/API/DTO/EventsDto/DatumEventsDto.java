package API.DTO.EventsDto;

import API.DTO.EventStatesDto.EventStateDto;

public class DatumEventsDto {
    public int id;
    public String name;

    public DatumEventsDto(int id, String name, String short_name, int priority, boolean excluded, boolean deleted, String provider_id,
                          EventStateDto[] event_states) {
        this.id = id;
        this.name = name;
        this.short_name = short_name;
        this.priority = priority;
        this.excluded = excluded;
        this.deleted = deleted;
        this.provider_id = provider_id;
        this.event_states = event_states;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isExcluded() {
        return excluded;
    }

    public void setExcluded(boolean excluded) {
        this.excluded = excluded;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(String provider_id) {
        this.provider_id = provider_id;
    }

    public EventStateDto[] getEvent_states() {
        return event_states;
    }

    public void setEvent_states(EventStateDto[] event_states) {
        this.event_states = event_states;
    }

    public String short_name;
    public int priority;
    public boolean excluded;
    public boolean deleted;
    public String provider_id;
    public EventStateDto[] event_states;
}

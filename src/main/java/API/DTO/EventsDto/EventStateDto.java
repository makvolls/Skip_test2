package API.DTO.EventsDto;

public class EventStateDto {
    public int id;

    public int getId() {
        return id;
    }

    public EventStateDto(int id, String name) {
        this.id = id;
        this.name = name;
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

    public String name;
}

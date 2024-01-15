package API.DTO.ErrorsDTO.EventStateErrorsDto;

public class EventStateErrorsNameDto {
    public String[] name;

    public String[] getName() {
        return name;
    }

    public EventStateErrorsNameDto(String[] name) {
        this.name = name;
    }

    public void setName(String[] name) {
        this.name = name;
    }
}

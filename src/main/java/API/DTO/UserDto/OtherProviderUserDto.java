package API.DTO.UserDto;

public class OtherProviderUserDto {
    public String id;
    public String name;

    public String getId() {
        return id;
    }

    public OtherProviderUserDto(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

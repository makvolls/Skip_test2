package API.DTO.ImageMarksDto;

public class CreateWithNameImageMarks {
    public String getName() {
        return name;
    }

    public CreateWithNameImageMarks(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String name;

}

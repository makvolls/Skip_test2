package API.DTO.DocumentsDto;

public class SkipDocumentTypeDocumentDto {
    public int getId() {
        return id;
    }

    public SkipDocumentTypeDocumentDto(int id, String name, boolean excluded) {
        this.id = id;
        this.name = name;
        this.excluded = excluded;
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

    public boolean isExcluded() {
        return excluded;
    }

    public void setExcluded(boolean excluded) {
        this.excluded = excluded;
    }

    public int id;
    public String name;
    public boolean excluded;
}

package API.DTO.DocumentsDto;

public class UrgencyDocumentDto {
    public int getId() {
        return id;
    }

    public UrgencyDocumentDto(int id, String name, boolean deleted) {
        this.id = id;
        this.name = name;
        this.deleted = deleted;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int id;
    public String name;
    public boolean deleted;
}

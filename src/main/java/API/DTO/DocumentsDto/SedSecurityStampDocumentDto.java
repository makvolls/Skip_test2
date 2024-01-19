package API.DTO.DocumentsDto;

public class SedSecurityStampDocumentDto {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SedSecurityStampDocumentDto(int id, String name, boolean deleted) {
        this.id = id;
        this.name = name;
        this.deleted = deleted;
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

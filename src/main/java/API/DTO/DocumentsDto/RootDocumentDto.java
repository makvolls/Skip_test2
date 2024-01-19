package API.DTO.DocumentsDto;

public class RootDocumentDto {
    public DatumDocumentDto data;

    public DatumDocumentDto getData() {
        return data;
    }

    public RootDocumentDto(DatumDocumentDto data) {
        this.data = data;
    }

    public void setData(DatumDocumentDto data) {
        this.data = data;
    }
}

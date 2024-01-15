package API.DTO.DocTypeDto;

public class RootDocTypePutDto {
    public DatumDocTypePostDto data;

    public RootDocTypePutDto(DatumDocTypePostDto data) {
        this.data = data;
    }

    public DatumDocTypePostDto getData() {
        return data;
    }

    public void setData(DatumDocTypePostDto data) {
        this.data = data;
    }
}

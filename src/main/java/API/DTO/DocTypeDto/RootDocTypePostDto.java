package API.DTO.DocTypeDto;

public class RootDocTypePostDto {
    public DatumDocTypePostDto getData() {
        return data;
    }

    public void setData(DatumDocTypePostDto data) {
        this.data = data;
    }

    public RootDocTypePostDto(DatumDocTypePostDto data) {
        this.data = data;
    }

    public DatumDocTypePostDto data;
}

package DTO.DirectoryDto;

public class RootDirectoryDto {
    public DatumDirectoryDto[] getData() {
        return data;
    }

    public void setData(DatumDirectoryDto[] data) {
        this.data = data;
    }

    public DatumDirectoryDto[] data;

    public RootDirectoryDto(DatumDirectoryDto[] data) {
        this.data = data;
    }
}

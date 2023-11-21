package DTO.DirectoryDto;

public class DatumDirectoryDto {
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public DatumDirectoryDto(String name, String type, String path) {
        this.name = name;
        this.type = type;
        this.path = path;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String type;
    public String path;
}

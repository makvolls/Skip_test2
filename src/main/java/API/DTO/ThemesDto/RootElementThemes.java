package API.DTO.ThemesDto;

public class RootElementThemes {
    public DataElementThemes getData() {
        return data;
    }

    public void setData(DataElementThemes data) {
        this.data = data;
    }

    public RootElementThemes(DataElementThemes data) {
        this.data = data;
    }

    public DataElementThemes data;
}

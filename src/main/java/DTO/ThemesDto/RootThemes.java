package DTO.ThemesDto;

public class RootThemes {
    public DatumThemes[] getData() {
        return data;
    }

    public void setData(DatumThemes[] data) {
        this.data = data;
    }

    public RootThemes(DatumThemes[] data) {
        this.data = data;
    }

    public DatumThemes[] data;
}

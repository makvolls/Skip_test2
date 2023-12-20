package API.DTO.ImageMarksDto;

public class RootImageMarks {
    public DatumImageMarks[] getData() {
        return data;
    }

    public void setData(DatumImageMarks[] data) {
        this.data = data;
    }

    public RootImageMarks(DatumImageMarks[] data) {
        this.data = data;
    }

    public DatumImageMarks[] data;
}

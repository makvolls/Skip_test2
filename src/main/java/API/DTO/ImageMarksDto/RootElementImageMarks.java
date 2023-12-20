package API.DTO.ImageMarksDto;

public class RootElementImageMarks {
    public DataElementImageMarks getData() {
        return data;
    }

    public void setData(DataElementImageMarks data) {
        this.data = data;
    }

    public RootElementImageMarks(DataElementImageMarks data) {
        this.data = data;
    }

    public DataElementImageMarks data;
}

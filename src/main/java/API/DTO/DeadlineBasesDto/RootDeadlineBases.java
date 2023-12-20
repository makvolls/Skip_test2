package API.DTO.DeadlineBasesDto;

public class RootDeadlineBases {
    public DatumDeadlineBases[] getData() {
        return data;
    }

    public void setData(DatumDeadlineBases[] data) {
        this.data = data;
    }

    public RootDeadlineBases(DatumDeadlineBases[] data) {
        this.data = data;
    }

    public DatumDeadlineBases[] data;
}

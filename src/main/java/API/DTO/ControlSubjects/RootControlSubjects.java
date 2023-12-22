package API.DTO.ControlSubjects;

public class RootControlSubjects {
    public DatumControlSubjects[] getData() {
        return data;
    }

    public void setData(DatumControlSubjects[] data) {
        this.data = data;
    }

    public RootControlSubjects(DatumControlSubjects[] data) {
        this.data = data;
    }

    public DatumControlSubjects[] data;
}

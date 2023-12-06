package DTO.UrgenciesDTO;

public class RootUrgencies {
    public DatumUrgencies[] getData() {
        return data;
    }

    public void setData(DatumUrgencies[] data) {
        this.data = data;
    }

    public RootUrgencies(DatumUrgencies[] data) {
        this.data = data;
    }

    public DatumUrgencies[] data;
}

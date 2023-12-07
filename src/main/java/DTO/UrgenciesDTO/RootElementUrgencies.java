package DTO.UrgenciesDTO;

public class RootElementUrgencies {
    public DataElementUrgencies getData() {
        return data;
    }

    public void setData(DataElementUrgencies data) {
        this.data = data;
    }

    public RootElementUrgencies(DataElementUrgencies data) {
        this.data = data;
    }

    public DataElementUrgencies data;
}

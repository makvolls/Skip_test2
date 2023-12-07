package DTO.DeadlineBasesDto;

public class RootElementDeadlineBases {
    public DataElementDeadlineBases getData() {
        return data;
    }

    public void setData(DataElementDeadlineBases data) {
        this.data = data;
    }

    public RootElementDeadlineBases(DataElementDeadlineBases data) {
        this.data = data;
    }

    public DataElementDeadlineBases data;
}

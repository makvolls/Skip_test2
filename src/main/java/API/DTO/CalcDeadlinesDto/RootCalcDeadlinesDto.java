package API.DTO.CalcDeadlinesDto;

public class RootCalcDeadlinesDto {
    public DatumCalcDeadlinesDto getData() {
        return data;
    }

    public RootCalcDeadlinesDto(DatumCalcDeadlinesDto data) {
        this.data = data;
    }

    public void setData(DatumCalcDeadlinesDto data) {
        this.data = data;
    }

    public DatumCalcDeadlinesDto data;
}

package API.DTO.PeriodicityDto;

public class RootPeriodicityGet {

    public DatumPeriodicityGet[] data;

    public DatumPeriodicityGet[] getData() {
        return data;
    }

    public RootPeriodicityGet(DatumPeriodicityGet[] data) {
        this.data = data;
    }

    public void setData(DatumPeriodicityGet[] data) {
        this.data = data;
    }
}

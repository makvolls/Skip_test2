package API.DTO.PeriodicityDto;

public class RootPerWithDataPut {
    public DatumPeriodicityPut getData() {
        return data;
    }

    public RootPerWithDataPut(DatumPeriodicityPut data) {
        this.data = data;
    }

    public void setData(DatumPeriodicityPut data) {
        this.data = data;
    }

    public DatumPeriodicityPut data;
    }

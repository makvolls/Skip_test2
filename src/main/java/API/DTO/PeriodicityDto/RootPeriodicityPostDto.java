package API.DTO.PeriodicityDto;

public class RootPeriodicityPostDto {
    public DatumPeriodicityPostDto getData() {
        return data;
    }

    public void setData(DatumPeriodicityPostDto data) {
        this.data = data;
    }

    public RootPeriodicityPostDto(DatumPeriodicityPostDto data) {
        this.data = data;
    }

    public DatumPeriodicityPostDto data;
}

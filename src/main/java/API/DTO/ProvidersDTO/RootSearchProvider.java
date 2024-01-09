package API.DTO.ProvidersDTO;

public class RootSearchProvider {
    public DataSearchProvider data;

    public DataSearchProvider getData() {
        return data;
    }

    public RootSearchProvider(DataSearchProvider data) {
        this.data = data;
    }

    public void setData(DataSearchProvider data) {
        this.data = data;
    }
}

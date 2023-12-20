package API.DTO.RolesDto;

public class RootResponseRolesDto {
    public DataResponseRolesDto getData() {
        return data;
    }

    public RootResponseRolesDto(DataResponseRolesDto data) {
        this.data = data;
    }

    public void setData(DataResponseRolesDto data) {
        this.data = data;
    }

    public DataResponseRolesDto data;
}

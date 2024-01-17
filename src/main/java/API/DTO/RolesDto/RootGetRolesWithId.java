package API.DTO.RolesDto;

public class RootGetRolesWithId {
    public DatumGetRolesWithId getData() {
        return data;
    }

    public void setData(DatumGetRolesWithId data) {
        this.data = data;
    }

    public RootGetRolesWithId(DatumGetRolesWithId data) {
        this.data = data;
    }

    public DatumGetRolesWithId data;
}

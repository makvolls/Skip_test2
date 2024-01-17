package API.DTO.RolesDto;

public class RootPutWithRole_ids {
    public int[] role_ids;

    public RootPutWithRole_ids(int[] role_ids) {
        this.role_ids = role_ids;
    }

    public int[] getRole_ids() {
        return role_ids;
    }

    public void setRole_ids(int[] role_ids) {
        this.role_ids = role_ids;
    }
}

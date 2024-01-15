package API.DTO.UserDto;

public class DatumGetUserCurrent {
    public int id;
    public String short_name;

    public int getId() {
        return id;
    }

    public DatumGetUserCurrent(int id, String short_name, String[] rights_ids) {
        this.id = id;
        this.short_name = short_name;
        this.rights_ids = rights_ids;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String[] getRights_ids() {
        return rights_ids;
    }

    public void setRights_ids(String[] rights_ids) {
        this.rights_ids = rights_ids;
    }

    public String[] rights_ids;
}

package API.DTO.RolesDto;

public class RootCreateRolesDto {
    public String name;

    public RootCreateRolesDto(String name, String desc, boolean global, String[] rights_ids) {
        this.name = name;
        this.desc = desc;
        this.global = global;
        this.rights_ids = rights_ids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }

    public String[] getRights_ids() {
        return rights_ids;
    }

    public void setRights_ids(String[] rights_ids) {
        this.rights_ids = rights_ids;
    }

    public String desc;
    public boolean global;
    public String[] rights_ids;
}

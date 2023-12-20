package API.DTO.ErrorsDTO.RolesErrors;

public class NameErrors {
    public String[] name;

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }


    public String[] getFull_name() {
        return full_name;
    }

    public void setFull_name(String[] full_name) {
        this.full_name = full_name;
    }

    public String[] getRights_ids() {
        return rights_ids;
    }

    public void setRights_ids(String[] rights_ids) {
        this.rights_ids = rights_ids;
    }

    public String[] full_name;
    public String[] rights_ids;
    public String[] global;

    public String[] getGlobal() {
        return global;
    }

    public NameErrors(String[] name, String[] full_name, String[] rights_ids, String[] global) {
        this.name = name;
        this.full_name = full_name;
        this.rights_ids = rights_ids;
        this.global = global;
    }

    public void setGlobal(String[] global) {
        this.global = global;
    }
}

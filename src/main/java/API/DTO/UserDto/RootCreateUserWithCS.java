package API.DTO.UserDto;

public class RootCreateUserWithCS {
    public String getOfficial_id() {
        return official_id;
    }

    public void setOfficial_id(String official_id) {
        this.official_id = official_id;
    }

    public int[] getRole_ids() {
        return role_ids;
    }

    public void setRole_ids(int[] role_ids) {
        this.role_ids = role_ids;
    }

    public int getControl_subject_id() {
        return control_subject_id;
    }

    public void setControl_subject_id(int control_subject_id) {
        this.control_subject_id = control_subject_id;
    }

    public RootCreateUserWithCS(String official_id, int[] role_ids, int control_subject_id) {
        this.official_id = official_id;
        this.role_ids = role_ids;
        this.control_subject_id = control_subject_id;
    }

    public String official_id;
    public int[] role_ids;
    public int control_subject_id;
}

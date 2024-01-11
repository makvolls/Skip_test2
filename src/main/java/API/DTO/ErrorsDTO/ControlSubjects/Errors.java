package API.DTO.ErrorsDTO.ControlSubjects;

public class Errors {
    public String[] getControl_subject_id() {
        return control_subject_id;
    }

    public void setControl_subject_id(String[] control_subject_id) {
        this.control_subject_id = control_subject_id;
    }

    public Errors(String[] control_subject_id) {
        this.control_subject_id = control_subject_id;
    }

    public String[] control_subject_id;
}

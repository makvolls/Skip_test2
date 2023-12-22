package API.DTO.ControlSubjects;

public class RootPutRequestControlSubjects {
    public CreateControlSubjects[] getControl_subjects() {
        return control_subjects;
    }

    public void setControl_subjects(CreateControlSubjects[] control_subjects) {
        this.control_subjects = control_subjects;
    }

    public RootPutRequestControlSubjects(CreateControlSubjects[] control_subjects) {
        this.control_subjects = control_subjects;
    }

    public CreateControlSubjects[] control_subjects;
}

package API.DTO.AssignmentExecutionStatesDto;

public class RootAssignmentExecutionStates {
    public DatumAssignmentExecutionStates[] getData() {
        return data;
    }

    public void setData(DatumAssignmentExecutionStates[] data) {
        this.data = data;
    }

    public RootAssignmentExecutionStates(DatumAssignmentExecutionStates[] data) {
        this.data = data;
    }

    public DatumAssignmentExecutionStates[] data;
}

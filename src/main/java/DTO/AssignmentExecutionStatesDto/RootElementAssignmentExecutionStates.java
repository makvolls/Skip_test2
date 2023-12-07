package DTO.AssignmentExecutionStatesDto;


public class RootElementAssignmentExecutionStates {
    public DataElementAssignmentExecutionStates getData() {
        return data;
    }

    public void setData(DataElementAssignmentExecutionStates data) {
        this.data = data;
    }

    public RootElementAssignmentExecutionStates(DataElementAssignmentExecutionStates data) {
        this.data = data;
    }

    public DataElementAssignmentExecutionStates data;
}

package API.DTO.DocumentExecutionStatesDto;

public class RootElementDocumentExecutionStates {
    public DataElementDocumentExecutionStates getData() {
        return data;
    }

    public void setData(DataElementDocumentExecutionStates data) {
        this.data = data;
    }

    public RootElementDocumentExecutionStates(DataElementDocumentExecutionStates data) {
        this.data = data;
    }

    public DataElementDocumentExecutionStates data;
}

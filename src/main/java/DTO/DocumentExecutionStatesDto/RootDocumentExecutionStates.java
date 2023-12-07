package DTO.DocumentExecutionStatesDto;

public class RootDocumentExecutionStates {
    public DatumDocumentExecutionStates[] getData() {
        return data;
    }

    public void setData(DatumDocumentExecutionStates[] data) {
        this.data = data;
    }

    public RootDocumentExecutionStates(DatumDocumentExecutionStates[] data) {
        this.data = data;
    }

    public DatumDocumentExecutionStates[] data;

}

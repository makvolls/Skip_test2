package API.DTO.ErrorsDTO;

public class RootError {
    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public RootError(Object error) {
        this.error = error;
    }

    public Object error;
}

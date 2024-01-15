package API.DTO.ErrorsDTO;

public class RootErrorNoRights {
    public String error;

    public String getError() {
        return error;
    }

    public RootErrorNoRights(String error) {
        this.error = error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

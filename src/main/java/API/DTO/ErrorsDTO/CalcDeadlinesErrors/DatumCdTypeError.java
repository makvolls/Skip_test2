package API.DTO.ErrorsDTO.CalcDeadlinesErrors;

public class DatumCdTypeError {
    public String[] getType() {
        return type;
    }

    public DatumCdTypeError(String[] type) {
        this.type = type;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    public String[] type;
}

package API.DTO.ErrorsDTO.PeriodicityErrors;

public class PeriodicityNameError {
    public String[] name;

    public String[] getName() {
        return name;
    }

    public PeriodicityNameError(String[] name) {
        this.name = name;
    }

    public void setName(String[] name) {
        this.name = name;
    }
}

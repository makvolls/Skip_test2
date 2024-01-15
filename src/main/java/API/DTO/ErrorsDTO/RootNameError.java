package API.DTO.ErrorsDTO;

import API.DTO.ErrorsDTO.PeriodicityErrors.PeriodicityNameError;

public class RootNameError {
    public PeriodicityNameError errors;

    public RootNameError(PeriodicityNameError errors) {
        this.errors = errors;
    }

    public PeriodicityNameError getErrors() {
        return errors;
    }

    public void setErrors(PeriodicityNameError errors) {
        this.errors = errors;
    }
}

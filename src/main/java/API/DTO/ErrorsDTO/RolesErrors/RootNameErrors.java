package API.DTO.ErrorsDTO.RolesErrors;

import API.DTO.ErrorsDTO.RolesErrors.NameErrors;

public class RootNameErrors {
    public NameErrors getErrors() {
        return errors;
    }

    public void setErrors(NameErrors errors) {
        this.errors = errors;
    }

    public RootNameErrors(NameErrors errors) {
        this.errors = errors;
    }

    public NameErrors errors;
}

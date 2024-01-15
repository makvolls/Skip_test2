package API.DTO.ErrorsDTO;

import API.DTO.ErrorsDTO.SecurityClassificateionErrorsDto.ShortNameScError;

public class RootShortNameError {
    public ShortNameScError getErrors() {
        return errors;
    }

    public RootShortNameError(ShortNameScError errors) {
        this.errors = errors;
    }

    public void setErrors(ShortNameScError errors) {
        this.errors = errors;
    }

    public ShortNameScError errors;
}

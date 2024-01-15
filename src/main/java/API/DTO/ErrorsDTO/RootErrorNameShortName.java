package API.DTO.ErrorsDTO;

import API.DTO.ErrorsDTO.EventsErrorsDto.ErrorsNameShortName;

public class RootErrorNameShortName {
    public ErrorsNameShortName getErrors() {
        return errors;
    }

    public RootErrorNameShortName(ErrorsNameShortName errors) {
        this.errors = errors;
    }

    public void setErrors(ErrorsNameShortName errors) {
        this.errors = errors;
    }

    public ErrorsNameShortName errors;
}

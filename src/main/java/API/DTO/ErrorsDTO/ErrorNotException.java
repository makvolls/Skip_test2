package API.DTO.ErrorsDTO;

public class ErrorNotException {
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String[] getBacktrace() {
        return backtrace;
    }

    public void setBacktrace(String[] backtrace) {
        this.backtrace = backtrace;
    }

    public ErrorNotException(String error, String[] backtrace) {
        this.error = error;
        this.backtrace = backtrace;
    }

    public String error;
    public String[] backtrace;
}

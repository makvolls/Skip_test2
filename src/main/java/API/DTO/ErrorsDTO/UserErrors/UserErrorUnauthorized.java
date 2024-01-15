package API.DTO.ErrorsDTO.UserErrors;

public class UserErrorUnauthorized {

    public String error;
    public String redirect_url;

    public UserErrorUnauthorized(String error, String redirect_url) {
        this.error = error;
        this.redirect_url = redirect_url;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getRedirect_url() {
        return redirect_url;
    }

    public void setRedirect_url(String redirect_url) {
        this.redirect_url = redirect_url;
    }
}

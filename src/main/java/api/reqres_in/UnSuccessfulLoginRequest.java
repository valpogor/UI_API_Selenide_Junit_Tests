package api.reqres_in;

public class UnSuccessfulLoginRequest {

    private String email;

    public UnSuccessfulLoginRequest(String email) {
        this.email = email;
    }

    private UnSuccessfulLoginRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

package api.reqres_in;

import org.junit.Assert;
import static org.hamcrest.Matchers.equalTo;
import static api.setup.RequestSpecs.request;
import static utils.PropertyReader.*;

public class SuccessfulLoginRequest {

    private String email;
    private String password;

    public SuccessfulLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    private SuccessfulLoginRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

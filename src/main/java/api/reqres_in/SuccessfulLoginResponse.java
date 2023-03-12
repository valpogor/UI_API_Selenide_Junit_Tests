package api.reqres_in;

public class SuccessfulLoginResponse {
    private static String token;

    public SuccessfulLoginResponse(String token) {
        this.token = token;
    }

    private SuccessfulLoginResponse() {
    }

    public static String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}

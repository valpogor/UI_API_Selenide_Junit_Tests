package api.reqres_in;

public class UnSuccessfulLoginResponse {
    private static String error;
    public UnSuccessfulLoginResponse(String error) {
        this.error = error;
    }
    private UnSuccessfulLoginResponse() {}
    public static String getError() {return error;}
    public void setError(String error) {this.error = error;}
}

package api.setup;

import api.reqres_in.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.*;
import io.restassured.http.ContentType;
import io.restassured.specification.*;
import org.junit.Assert;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static utils.PropertyReader.*;

public class RequestSpecs {

    private static final long responseTime = 5000;
    public static RequestSpecification request(int status){
        RequestSpecs.installSpecifications(RequestSpecs.requestSpecification(baseUrl),
                RequestSpecs.responseSpecification(status));
        return given()
                .header("Content-type", "application/json")
                .log().uri();
    }

    public static RequestSpecification requestSpecification(String baseURL) {
        return new RequestSpecBuilder()
                .setBaseUri(baseURL)
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .build();
    }

    public static ResponseSpecification responseSpecification(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .expectResponseTime(lessThanOrEqualTo(responseTime))
                .build();
    }

    public static void installSpecifications(RequestSpecification requestSpec, ResponseSpecification responseSpec) {
        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;
    }

    public static void login(String email, String password, int status){
        SuccessfulLoginRequest loginSuccessfulRequest = new SuccessfulLoginRequest(email, password);
        if (status==200){
            request(status)
                    .body(loginSuccessfulRequest)
                    .post("api/login")
                    .then()
                    .log().status()
                    .log().body().extract().as(SuccessfulLoginResponse.class);
        }
        else if(status == 400) {
            request(status)
                    .body(loginSuccessfulRequest).when()
                    .post("api/login")
                    .then()
                    .log().status()
                    .log().body().body("error", equalTo(errorMsg));
        }
    }
    public static void register(String email, String job, int status){
        SuccessfulRegisterRequest successfulRegisterRequest = new SuccessfulRegisterRequest(email, job);
        if (status==200){
            Integer id = 4;
            SuccessfulRegisterResponse successfulRegisterResponse = request(status)
                    .body(successfulRegisterRequest)
                    .post("api/register")
                    .then()
                    .log().status()
                    .log().body().extract().as(SuccessfulRegisterResponse.class);
            Assert.assertEquals(successfulRegisterResponse.getId(), id);
            Assert.assertEquals(successfulRegisterResponse.getToken(), tok);
        }
        else if(status == 400) {
            request(status)
                    .body(successfulRegisterRequest).when()
                    .post("api/register")
                    .then()
                    .log().status()
                    .log().body().body("error", equalTo(errorMsg));
        }
    }
}

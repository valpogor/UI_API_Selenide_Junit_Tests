package tests.api;

import api.reqres_in.pojo_request.login_request.SuccessfulLoginRequest;
import api.reqres_in.pojo_request.login_request.UnSuccessfulLoginRequest;
import api.reqres_in.pojo_response.login_response.SuccessfulLoginResponse;
import api.setup.Specifications;
import io.qameta.allure.*;
import org.junit.Assert;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTests {
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(1)
    @DisplayName("successfulLogin")
    public void successfulLogin() {
        Specifications.installSpecifications(Specifications.requestSpecification("https://reqres.in/"), Specifications.responseSpecification(200));
        SuccessfulLoginRequest loginSuccessfulRequest = new SuccessfulLoginRequest("eve.holt@reqres.in", "cityslicka");
        String token = "QpwL5tke4Pnpja7X4";
        SuccessfulLoginResponse loginSuccessfulResponse = given()
                .body(loginSuccessfulRequest)
                .when()
                .post("api/login")
                .then()
                .log().status()
                .log().body()
                .extract().as(SuccessfulLoginResponse.class);
        Assert.assertEquals(loginSuccessfulResponse.getToken(), token);
    }

    @Severity(SeverityLevel.MINOR)
    @Test
    @Order(2)
    @DisplayName("unSuccessfulLogin")
    public void unSuccessfulLogin() {
        Specifications.installSpecifications(Specifications.requestSpecification("https://reqres.in/"), Specifications.responseSpecification(400));
        UnSuccessfulLoginRequest unSuccessfulLoginRequest = new UnSuccessfulLoginRequest("peter@klaven");
        String errorText = "Missing password";
        given()
                .body(unSuccessfulLoginRequest)
                .when()
                .post("api/login")
                .then()
                .log().status()
                .log().body()
                .body("error", equalTo(errorText));
    }
}

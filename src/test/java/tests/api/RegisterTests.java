package tests.api;

import api.reqres_in.pojo_request.register_request.SuccessfulRegisterRequest;
import api.reqres_in.pojo_request.register_request.UnSuccessfulRegisterRequest;
import api.reqres_in.pojo_response.register_response.SuccessfulRegisterResponse;
import api.setup.Specifications;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RegisterTests {
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(1)
    @DisplayName("successfulRegister")
    public void successfulRegister() {
        Specifications.installSpecifications(Specifications.requestSpecification("https://reqres.in/"), Specifications.responseSpecification(200));
        SuccessfulRegisterRequest successfulRegisterRequest = new SuccessfulRegisterRequest("eve.holt@reqres.in", "pistol");
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
        SuccessfulRegisterResponse successfulRegisterResponse = given()
                .body(successfulRegisterRequest)
                .when()
                .post("api/register")
                .then()
                .log().status()
                .log().body()
                .extract().as(SuccessfulRegisterResponse.class);
        Assert.assertEquals(successfulRegisterResponse.getId(), id);
        Assert.assertEquals(successfulRegisterResponse.getToken(), token);
    }

    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(2)
    @DisplayName("unSuccessfulRegister")
    public void unSuccessfulRegister() {
        Specifications.installSpecifications(Specifications.requestSpecification("https://reqres.in/"), Specifications.responseSpecification(400));
        UnSuccessfulRegisterRequest unSuccessfulRegisterRequest = new UnSuccessfulRegisterRequest("sydney@fife");
        String errorText = "Missing password";
        given()
                .body(unSuccessfulRegisterRequest)
                .when()
                .post("api/register")
                .then()
                .log().status()
                .log().body()
                .assertThat()
                .body("error", equalTo(errorText));
    }
}

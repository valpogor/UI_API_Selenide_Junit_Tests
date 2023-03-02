package tests.api;

import api.reqres_in.pojo_request.user_request.CreateUserRequest;
import api.reqres_in.pojo_response.user_response.CreatedUserResponse;
import api.setup.Specifications;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import static io.restassured.RestAssured.given;
public class UserTests {

    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(1)
    @DisplayName("createdUserTests")
    public void createdUserTests() {
        Specifications.installSpecifications(Specifications.requestSpecification("https://reqres.in/"), Specifications.responseSpecification(201));
        CreateUserRequest user1 = new CreateUserRequest("morpheus", "leader");
        CreatedUserResponse createdUser = given()
                .body(user1)
                .when()
                .post("api/users")
                .then()
                .log().status()
                .log().body()
                .extract().body().as(CreatedUserResponse.class);
        String timeRegex = "\\..+";
        String currentTime = Clock.systemUTC().instant().toString().replaceAll(timeRegex, "");
        Assert.assertNotEquals(createdUser.getCreatedAt().replaceAll(timeRegex, ""), currentTime);
    }
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(2)
    @DisplayName("updatedUserTests")
    public void updatedUserTests() {
        Specifications.installSpecifications(Specifications.requestSpecification("https://reqres.in/"), Specifications.responseSpecification(200));
        CreateUserRequest user1 = new CreateUserRequest("morpheus", "zion resident");
        given()
                .body(user1)
                .when()
                .put("api/users/2")
                .then()
                .log().status()
                .log().body()
                .assertThat()
                .body("name", Matchers.equalTo("morpheus"))
                .body("job", Matchers.equalTo("zion resident"));
    }

}

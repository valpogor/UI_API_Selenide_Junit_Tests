package tests.api;

import api.reqres_in.*;
import io.qameta.allure.*;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import java.time.Clock;
import static api.setup.RequestSpecs.*;

public class UserTests {

    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(1)
    @DisplayName("create User Tests")
    @Description("Tests for create User functionality")
    public void createdUserTests() {
        CreateUserRequest user1 = new CreateUserRequest("morpheus", "leader");
        CreatedUserResponse createdUser = request(201)
                .body(user1)
                .when()
                .post("api/users")
                .then()
                .log().status()
                .log().body()
                .extract().body().as(CreatedUserResponse.class);
        String timeRegex = "\\..+";
        String currentTime = Clock.systemUTC().instant().toString().replaceAll(timeRegex, "");
        Assert.assertEquals(createdUser.getCreatedAt().replaceAll(timeRegex, ""), currentTime);
    }
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(2)
    @DisplayName("update User Tests")
    @Description("Tests for update User functionality")
    public void updatedUserTests() {
        CreateUserRequest user1 = new CreateUserRequest("morpheus", "zion resident");
        request(200)
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

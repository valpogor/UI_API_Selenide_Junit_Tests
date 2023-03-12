package tests.api;

import api.reqres_in.UserdataResponse;
import io.qameta.allure.*;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import java.util.List;
import static api.util.JsonToStringConverter.convertJsonToString;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static api.setup.RequestSpecs.*;

public class UserdataTests {
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(1)
    @DisplayName("user List Json Scheme Validation")
    @Description("Tests for user List Json Scheme Validation")
    public void userListJsonSchemeValidation() {
        request(200)
            .when()
            .get("api/users?page=2")
            .then()
            .log().status()
            .log().body()
            .body(matchesJsonSchemaInClasspath("reqres/json_scheme/userdata_list.json"));
    }
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(2)
    @DisplayName("single User Json Scheme Validation")
    @Description("Tests for dingle user Json Scheme Validation")
    public void singleUserJsonSchemeValidation() {
        request(200)
            .when()
            .get("api/users/2")
            .then()
            .log().status()
            .log().body()
            .body(matchesJsonSchemaInClasspath("reqres/json_scheme/single_user.json"));
    }

    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(3)
    @DisplayName("single User Test")
    @Description("Tests for single user ")
    public void singleUserTest() {
        request(200)
                .when()
                .get("api/users/2")
                .then()
                .log().status()
                .log().body()
                .assertThat()
                .body("data.size()", equalTo(5));
    }

    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(5)
    @DisplayName("user data List Tests")
    @Description("Tests for user data List ")
    public void userdataListTests() {
        List<UserdataResponse> users = request(200)
                .when()
                .get("api/users?page=2")
                .then()
                .log().status()
                .log().body()
                .extract().body().jsonPath().getList("data", UserdataResponse.class);
        users.forEach(x -> Assert.assertTrue(x.getEmail().matches("\\w+\\.\\w+@reqres\\.in")));
        users.forEach(x -> Assert.assertTrue(x.getAvatar().matches("https://reqres.in/img/faces/\\d+-image\\.\\w+")));
        users.forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
    }
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(6)
    @DisplayName("userdata Equity Tests")
    @Description("Tests for user data Equity")
    public void userdataEquityTests() {
        String userDataPage1 = request(200)
                .when()
                .get("api/users?page=1")
                .then()
                .log().status()
                .extract().body().asString().replace(" ", "");

        String userDataPage2 = request(200)
                .when()
                .get("api/users?page=2")
                .then()
                .log().status()
                .extract().body().asString().replace(" ", "");

        Assert.assertEquals(userDataPage1, convertJsonToString("src/test/resources/reqres/test_data/td1.json"));
        Assert.assertEquals(userDataPage2, convertJsonToString("src/test/resources/reqres/test_data/td2.json"));
    }
}
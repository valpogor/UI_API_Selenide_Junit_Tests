package tests.api;

import api.reqres_in.pojo_response.userdata_response.UserdataResponse;
import api.setup.Specifications;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;
import static api.util.JsonToStringConverter.convertJsonToString;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class UserdataTests {
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(1)
    @DisplayName("userListJsonSchemeValidation")
    public void userListJsonSchemeValidation() {
        Specifications.installSpecifications(Specifications.requestSpecification("https://reqres.in/"), Specifications.responseSpecification(200));
        given()
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
    @DisplayName("singleUserJsonSchemeValidation")
    public void singleUserJsonSchemeValidation() {
        Specifications.installSpecifications(Specifications.requestSpecification("https://reqres.in/"), Specifications.responseSpecification(200));
        given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .body(matchesJsonSchemaInClasspath("reqres/json_scheme/single_user.json"));
    }

    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(3)
    @DisplayName("singleUserTest")
    public void singleUserTest() {
        Specifications.installSpecifications(Specifications.requestSpecification("https://reqres.in/"), Specifications.responseSpecification(200));
        given()
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
    @DisplayName("userdataListTests")
    public void userdataListTests() {
        Specifications.installSpecifications(Specifications.requestSpecification("https://reqres.in/"), Specifications.responseSpecification(200));
        List<UserdataResponse> users = given()
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
    @DisplayName("userdataEquityTests")
    public void userdataEquityTests() {
        Specifications.installSpecifications(Specifications.requestSpecification("https://reqres.in/"), Specifications.responseSpecification(200));
        String userDataPage1 = given()
                .when()
                .get("api/users?page=1")
                .then()
                .log().status()
                .extract().body().asString().replace(" ", "");

        String userDataPage2 = given()
                .when()
                .get("api/users?page=2")
                .then()
                .log().status()
                .extract().body().asString().replace(" ", "");

        Assert.assertEquals(userDataPage1, convertJsonToString("src/test/resources/reqres/test_data/td1.json"));
        Assert.assertEquals(userDataPage2, convertJsonToString("src/test/resources/reqres/test_data/td2.json"));
    }
}
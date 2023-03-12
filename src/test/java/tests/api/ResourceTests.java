package tests.api;

import api.reqres_in.ListResourceResponse;
import io.qameta.allure.*;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import java.util.List;
import java.util.stream.Collectors;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static api.setup.RequestSpecs.*;

public class ResourceTests {

    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(1)
    @DisplayName("listResourceJsonSchemeValidation")
    @Description("Tests list Resource Json Scheme Validation")
    public void listResourceJsonSchemeValidation() {
        request(200)
                .get("api/unknown")
                .then()
                .log().status()
                .log().body()
                .body(matchesJsonSchemaInClasspath("reqres/json_scheme/list_resource.json"));
    }

    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(2)
    @DisplayName("list Resource Tests")
    @Description("Tests for list Resource")
    public void listResourceTests() {
        List<ListResourceResponse> resourceList = request(200)
                .when()
                .get("api/unknown")
                .then()
                .log().status()
                .log().body()
                .extract().body().jsonPath().getList("data", ListResourceResponse.class);

        resourceList.forEach(x -> Assert.assertTrue(x.getColor().matches("#\\w{6}")));
        resourceList.forEach(x -> Assert.assertTrue(x.getPantone_value().matches("\\d{2}-\\d{4}")));
        List<Integer> years = resourceList.stream().map(ListResourceResponse::getYear).collect(Collectors.toList());
        List<Integer> sortedYears = years.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(years, sortedYears);
    }
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(3)
    @DisplayName("delete User Test")
    @Description("Tests for delete user ")
    public void deleteUserTest() {
            request(204)
                .when()
                .delete("api/users/2")
                .then()
                .log().status();
    }
}

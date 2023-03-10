package api.util;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class RequestSpecs {
    public static RequestSpecification request(){
        return given()
               .header("x-api-key", "")
                .header("Content-type", "application/json")
                .baseUri("baseUrl")
                .log().uri();
    }
}

package api.com.restful_booker.tests;

import api.com.restful_booker.utils.EndPoints;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static io.restassured.RestAssured.*;

public class BaseTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.requestSpecification = given().contentType("application/json");
    }

    @BeforeEach
    public void healthCheck() {
        given().log().all()
                .when()
                    .get(EndPoints.healthCheck)
                .then()
                    .statusCode(201);
    }
}

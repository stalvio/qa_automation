package api.com.restful_booker.tests;

import api.com.restful_booker.utils.EndPoints;
import io.restassured.RestAssured;
import org.testng.annotations.*;

import static io.restassured.RestAssured.*;

public class BaseTest {

    @BeforeSuite
    public static void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.requestSpecification = given().contentType("application/json");
        given().contentType("application/json")
                .accept("application/json");
    }

    @BeforeMethod
    public void healthCheck() {
        given().log().all()
                .when()
                    .get(EndPoints.healthCheck)
                .then()
                    .statusCode(201);
    }
}

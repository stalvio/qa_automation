package api.com.restful_booker.utils;

import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiTestHelper {

    public static int getSingleBookingByIdResponseCode(Number id) {
        return  given().
                when().
                    get("/booking/"+ id).
                then().
                    extract().response().statusCode();
    }
}

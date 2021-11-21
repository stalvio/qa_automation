package api.com.restful_booker.utils;

import api.com.restful_booker.models.AuthDto;

import static io.restassured.RestAssured.given;

public class TokenGenerator {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "password123";

    public static String getToken() {

        String token = given().contentType("application/json")
                .when()
                .body(new AuthDto(USERNAME, PASSWORD))
                .post("https://restful-booker.herokuapp.com/auth")
                .then()
                .statusCode(200)
                .extract()
                .body().jsonPath().getString("token");
        return token;
    }
}

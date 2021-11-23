package api.com.restful_booker.utils;

import api.com.restful_booker.models.BookingDto;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiTestHelper {

    public static Response getSingleBookingByIdResponse(Number id) {
        return  given().
                when().
                    get("/booking/"+ id).
                then().
                    extract().response();
    }

    public static String createBookingAndGetItsId(BookingDto newBookingRequestDto) {

        String newBookingId = given()
                .contentType("application/json")
                .when()
                .body(newBookingRequestDto)
                .post(EndPoints.createBooking)
                .then()
                .statusCode(200).extract().response().jsonPath().getString("bookingid");
        return newBookingId;
    }
}

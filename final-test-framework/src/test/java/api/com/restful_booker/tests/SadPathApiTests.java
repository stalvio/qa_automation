package api.com.restful_booker.tests;

import api.com.restful_booker.models.*;
import api.com.restful_booker.utils.EndPoints;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static api.com.restful_booker.utils.ApiTestHelper.getSingleBookingByIdResponse;
import static api.com.restful_booker.utils.ResponseParser.getBookingObject;
import static io.restassured.RestAssured.*;

public class SadPathApiTests extends BaseTest {

    private Response response;
    private static final int internal_server_error_status_code = 500;
    private static final String invalidToken = "invalidToken!007";

    private InvalidBookingDto invalidBooking = new InvalidBookingDto(333, true,
            new BookingDates("2020-01-01", "2021-01-01"),
            "Dinner");

    private int bookingIdToUpdate = 10;
    private int bookingIdToDelete = 5;

    @Test
    public void bookingCanNotBeCreatedWithInvalidBody() {
        response = given()
                .contentType("application/json")
                .when()
                .body(invalidBooking)
                .post(EndPoints.createBooking)
                .then().extract().response();

        Assertions.assertEquals(internal_server_error_status_code, response.statusCode());
    }

    @Test
    public void bookingCanNotBeDeletedWithInvalidToken() {
        response = getSingleBookingByIdResponse(bookingIdToDelete);
        BookingDto bookingDtoBefore = getBookingObject(response);

        given()
                .header("Cookie", "token=" + invalidToken)
                .pathParam("id", bookingIdToDelete)
                .when()
                .delete(EndPoints.deleteBooking)
                .then()
                .statusCode(403);

        response = getSingleBookingByIdResponse(bookingIdToDelete);
        BookingDto bookingDtoAfter = getBookingObject(response);

        Assertions.assertTrue(bookingDtoBefore.equals(bookingDtoAfter));
    }

    @Test
    public void bookingCanNotBeDeletedWithoutToken() {
        response = getSingleBookingByIdResponse(bookingIdToDelete);
        BookingDto bookingDtoBefore = getBookingObject(response);

        given()
                .pathParam("id", bookingIdToDelete)
                .when()
                .delete(EndPoints.deleteBooking)
                .then()
                .statusCode(403);

        response = getSingleBookingByIdResponse(bookingIdToDelete);
        BookingDto bookingDtoAfter = getBookingObject(response);

        Assertions.assertTrue(bookingDtoBefore.equals(bookingDtoAfter));
    }

    @Test
    public void bookingCanNotBeUpdatedWithInvalidToken() {
        response = getSingleBookingByIdResponse(bookingIdToUpdate);
        BookingDto bookingDtoBefore = getBookingObject(response);

        given()
                .header("Cookie", "token=" + invalidToken)
                .pathParam("id", bookingIdToUpdate)
                .when()
                .put(EndPoints.updateBooking)
                .then()
                .statusCode(403);

        response = getSingleBookingByIdResponse(bookingIdToUpdate);
        BookingDto bookingDtoAfter = getBookingObject(response);

        Assertions.assertTrue(bookingDtoBefore.equals(bookingDtoAfter));
    }

    @Test
    public void bookingCanNotBePartlyUpdatedWithoutToken() {
        response = getSingleBookingByIdResponse(bookingIdToUpdate);
        BookingDto bookingDtoBefore = getBookingObject(response);

        given()
                .pathParam("id", bookingIdToUpdate)
                .when()
                .patch(EndPoints.updateBooking)
                .then()
                .statusCode(403);

        response = getSingleBookingByIdResponse(bookingIdToUpdate);
        BookingDto bookingDtoAfter = getBookingObject(response);

        Assertions.assertTrue(bookingDtoBefore.equals(bookingDtoAfter));
    }
}
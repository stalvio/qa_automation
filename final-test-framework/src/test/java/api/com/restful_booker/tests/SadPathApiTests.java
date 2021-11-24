package api.com.restful_booker.tests;

import api.com.restful_booker.models.*;
import api.com.restful_booker.utils.EndPoints;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static api.com.restful_booker.utils.ApiTestHelper.getSingleBookingByIdResponse;
import static api.com.restful_booker.utils.ResponseParser.parseResponseInToBookingObject;
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

    @Description("Booking can not be created without mandatory keys('firstname' and 'lastname') in body request." +
            " 500 error is in response")
    @Test
    public void bookingCanNotBeCreatedWithInvalidBody() {
        response = given()
                .contentType("application/json")
                .when()
                .body(invalidBooking)
                .post(EndPoints.createBooking)
                .then().extract().response();

        Assert.assertEquals(internal_server_error_status_code, response.statusCode());
    }

    @Description("Booking can not be deleted with invalid token")
    @Test
    public void bookingCanNotBeDeletedWithInvalidToken() {
        response = getSingleBookingByIdResponse(bookingIdToDelete);
        BookingDto bookingDtoBefore = parseResponseInToBookingObject(response);

        given()
                .header("Cookie", "token=" + invalidToken)
                .pathParam("id", bookingIdToDelete)
                .when()
                .delete(EndPoints.deleteBooking)
                .then()
                .statusCode(403);

        response = getSingleBookingByIdResponse(bookingIdToDelete);
        BookingDto bookingDtoAfter = parseResponseInToBookingObject(response);

        Assert.assertTrue(bookingDtoBefore.equals(bookingDtoAfter));
    }

    @Description("Booking can not be deleted without token")
    @Test
    public void bookingCanNotBeDeletedWithoutToken() {
        response = getSingleBookingByIdResponse(bookingIdToDelete);
        BookingDto bookingDtoBefore = parseResponseInToBookingObject(response);

        given()
                .pathParam("id", bookingIdToDelete)
                .when()
                .delete(EndPoints.deleteBooking)
                .then()
                .statusCode(403);

        response = getSingleBookingByIdResponse(bookingIdToDelete);
        BookingDto bookingDtoAfter = parseResponseInToBookingObject(response);

        Assert.assertTrue(bookingDtoBefore.equals(bookingDtoAfter));
    }

    @Description("Booking can not be fully updated with invalid token")
    @Test
    public void bookingCanNotBeUpdatedWithInvalidToken() {
        response = getSingleBookingByIdResponse(bookingIdToUpdate);
        BookingDto bookingDtoBefore = parseResponseInToBookingObject(response);

        given()
                .header("Cookie", "token=" + invalidToken)
                .pathParam("id", bookingIdToUpdate)
                .when()
                .put(EndPoints.updateBooking)
                .then()
                .statusCode(403);

        response = getSingleBookingByIdResponse(bookingIdToUpdate);
        BookingDto bookingDtoAfter = parseResponseInToBookingObject(response);

        Assert.assertTrue(bookingDtoBefore.equals(bookingDtoAfter));
    }

    @Description("Booking can not be partially updated without token")
    @Test
    public void bookingCanNotBePartlyUpdatedWithoutToken() {
        response = getSingleBookingByIdResponse(bookingIdToUpdate);
        BookingDto bookingDtoBefore = parseResponseInToBookingObject(response);

        given()
                .pathParam("id", bookingIdToUpdate)
                .when()
                .patch(EndPoints.updateBooking)
                .then()
                .statusCode(403);

        response = getSingleBookingByIdResponse(bookingIdToUpdate);
        BookingDto bookingDtoAfter = parseResponseInToBookingObject(response);

        Assert.assertTrue(bookingDtoBefore.equals(bookingDtoAfter));
    }
}
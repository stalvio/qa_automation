package api.com.restful_booker.tests;

import api.com.restful_booker.models.*;
import api.com.restful_booker.utils.EndPoints;
import api.com.restful_booker.utils.ResponseParser;
import api.com.restful_booker.utils.TokenGenerator;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static api.com.restful_booker.utils.ApiTestHelper.createBookingAndGetItsId;
import static api.com.restful_booker.utils.ApiTestHelper.getSingleBookingByIdResponse;
import static api.com.restful_booker.utils.ResponseParser.getBookingObject;
import static io.restassured.RestAssured.*;

public class HappyPathApiTests extends BaseTest {

    private Response response;
    private static final String valid_username = "admin";
    private static final String valid_password = "password123";
    private static final int ok_status_code = 200;
    private static final int not_found_status_code = 404;
    private static final String invalidToken = "invalidToken!007";

    private BookingDto bookingToCreate = new BookingDto("Stalvio", "Neto", 333, true,
            new BookingDates("2020-01-01", "2021-01-01"),
            "Dinner");
    private BookingDto bookingToUpdate = new BookingDto("Stalvio", "Neto", 333, true,
            new BookingDates("2020-01-01", "2021-01-01"), "Super");
    private int bookingIdToUpdate = 10;
    private PartialBookingDto partialBookingToUpdate = new PartialBookingDto("Jayden", "Molse");
    private int bookingIdToDelete = 5;


    @Description("SingUp is successful with valid credential and the token is returned")
    @Test
    public void registrationSuccessful() {

         response = given().contentType("application/json")
                .when()
                .body(new AuthDto(valid_username, valid_password))
                .post("/auth")
                .then()
                .statusCode(200).extract().response();

        Assertions.assertNotNull(response.jsonPath().getString("token"));
    }

    @Description("Verify that all the returned, from 'getBookingIds' GET request, ids are valid")
    @Test
    public void allBookingIdsInResponseAreValid() {

         response = given().
                when().
                get(EndPoints.getBookingIds).
                then().
                statusCode(200).
                and().
                extract().response();

        List<Number> bookingIds = response.jsonPath().getList("bookingid");

        for (Number id : bookingIds) {
            Assertions.assertEquals(ok_status_code, getSingleBookingByIdResponse(id).statusCode());
        }
    }

    @Description("Verify that status code is 200 OK for 'getBooking' GET request using a valid ID")
    @Test
    public void singleBookingCanBeGetById() {
        given()
                .pathParam("id", "1")
                .when()
                .get(EndPoints.getBooking)
                .then()
                .statusCode(200);
    }

    @Description("Created booking can be received using ID. Booking data is verified")
    @Test
    public void createdBookingCanBeGetById() {
        String newBookingId = createBookingAndGetItsId(bookingToCreate);

         response = given()
                .pathParam("id", newBookingId)
                .when()
                .get(EndPoints.getBooking)
                .then()
                .statusCode(200)
                .extract().response();

        BookingDto createdBooking = getBookingObject(response);

        Assertions.assertTrue(bookingToCreate.equals(createdBooking));
    }

    @Description("Existing booking can be fully updated ID using a valid token")
    @Test
    public void bookingCanBeFullyUpdatedWithValidToken() {

         response = given()
                .header("Cookie", "token=" + TokenGenerator.getToken())
                .pathParam("id", bookingIdToUpdate)
                .when()
                .body(bookingToUpdate)
                .put(EndPoints.updateBooking)
                .then()
                .statusCode(200)
                .extract().response();
        BookingDto updatedBooking = getBookingObject(response);

        Assertions.assertTrue(bookingToUpdate.equals(updatedBooking));
    }

    @Description("Existing booking can be partially updated ID using a valid token")
    @Test
    public void bookingCanBePartiallyUpdatedWithValidToken() {

         response = given()
                .header("Cookie", "token=" + TokenGenerator.getToken())
                .pathParam("id", bookingIdToUpdate)
                .when()
                .body(partialBookingToUpdate)
                .patch(EndPoints.partialUpdateBooking)
                .then()
                .statusCode(200)
                .extract().response();

        PartialBookingDto partialUpdatedBooking = ResponseParser.getPartialBookingObject(response);

        Assertions.assertTrue(partialBookingToUpdate.equals(partialUpdatedBooking));
    }

    @Description("Existing booking can be deleted ID using a valid token")
    @Test
    public void bookingCanBeDeletedWithValidToken() {

        given()
                .header("Cookie", "token=" + TokenGenerator.getToken())
                .pathParam("id", bookingIdToDelete)
                .when()
                .delete(EndPoints.deleteBooking)
                .then()
                .statusCode(201);

        Assertions.assertEquals(not_found_status_code, getSingleBookingByIdResponse(bookingIdToDelete).statusCode());
    }
}

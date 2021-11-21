package api.com.restful_booker.tests;

import api.com.restful_booker.models.AuthDto;
import api.com.restful_booker.models.BookingDates;
import api.com.restful_booker.models.BookingRequestDto;
import api.com.restful_booker.models.PartialBookingRequestDto;
import api.com.restful_booker.utils.ApiTestHelper;
import api.com.restful_booker.utils.EndPoints;
import api.com.restful_booker.utils.TokenGenerator;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;

public class RestfulBookerApiTests extends BaseTest {

    private static final String valid_username = "admin";
    private static final String valid_password = "password123";
    private static final int ok_status_code = 200;
    private BookingRequestDto newBookingRequestBody;

    @Description("SingUp is succesfull. Token is returned")
    @Test
    public void registrationSuccessful() {

        Response response = given().contentType("application/json")
                .when()
                    .body(new AuthDto(valid_username, valid_password))
                    .post("/auth")
                .then()
                    .statusCode(200).extract().response();

        Assertions.assertNotNull(response.jsonPath().getString("token"));
    }

    @Test
    public void getBookingIdsResponseContainsOnlyValidIds() {

        Response response = given().
                when().
                    get(EndPoints.getBookingIds).
                then().
                    statusCode(200).
                and().
                    extract().response();

        List<Number> bookingIds = response.jsonPath().getList("bookingid");

        for(Number id: bookingIds) {
            Assertions.assertEquals(ok_status_code, ApiTestHelper.getSingleBookingByIdResponseCode(id));
        }
    }

    @Test
    public void getSingleBookingById() {
        given().log().all()
                .pathParam("id", "1")
                .when()
                    .get(EndPoints.getBooking)
                .then()
                    .statusCode(200)
                .and()
                    .body("firstname", containsString("Eric"))
                .and()
                    .body("lastname", containsString("Smith"));
    }

    @Test
    public void createdBookingCanBeGetById() {
        newBookingRequestBody = new BookingRequestDto("Stalvio", "Neto", 333, true,
                new BookingDates("2020-01-01", "2021-01-01"), "Dinner");

        String newBookingId = createBookingAndGetItsId(newBookingRequestBody);

        Response response = given()
                .pathParam("id", newBookingId)
                .when()
                    .get(EndPoints.getBooking)
                .then()
                    .statusCode(200).log().all()
                    .extract().response();
        Assertions.assertTrue(response.jsonPath().getString("firstname").equals(newBookingRequestBody.getFirstname()));
        Assertions.assertTrue(response.jsonPath().getInt("totalprice") == (int)(newBookingRequestBody.getTotalprice()));
    }

    @Test
    public void fullUpdateBookingById() {
        String token = TokenGenerator.getToken();

                given().log().all()
                    .contentType("application/json")
                        .accept("application/json")
                        .header("Cookie", "token=" + token)
                        .pathParam("id", "15")
                .when()
                        .body(new BookingRequestDto("Stalvio", "Neto", 333, true,
                                new BookingDates("2020-01-01", "2021-01-01"), "Super"))
                        .put(EndPoints.updateBooking)
                        .then().log().all();
    }

    @Test
    public void partialUpdateBookingById() {
        String token = TokenGenerator.getToken();

        given().log().all()
                .contentType("application/json")
                .accept("application/json")
                .header("Cookie", "token=" + token)
                .pathParam("id", "15")
                .when()
                .body(new PartialBookingRequestDto("Jayden", "Molse"))
                .patch(EndPoints.partialUpdateBooking)
                .then().log().all();
    }

    @Test
    public void deleteBookingByID() {
        String token = TokenGenerator.getToken();

        given().log().all()
                .contentType("application/json")
                .accept("application/json")
                .header("Cookie", "token=" + token)
                .pathParam("id", "23")
                .when()
                .delete(EndPoints.deleteBooking)
                .then().log().all();
    }

    private String createBookingAndGetItsId(BookingRequestDto newBookingRequestDto) {

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

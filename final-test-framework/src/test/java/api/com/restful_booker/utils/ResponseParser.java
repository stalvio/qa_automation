package api.com.restful_booker.utils;

import api.com.restful_booker.models.BookingDto;
import api.com.restful_booker.models.PartialBookingDto;
import com.google.gson.Gson;
import io.restassured.response.Response;

public class ResponseParser {

    public static BookingDto getBookingObject(Response response) {
        String jsonString = response.asString();
        Gson g = new Gson();
        return g.fromJson(jsonString, BookingDto.class);
    }

    public static PartialBookingDto getPartialBookingObject(Response response) {
        String jsonString = response.asString();
        Gson g = new Gson();
        return g.fromJson(jsonString, PartialBookingDto.class);
    }
}

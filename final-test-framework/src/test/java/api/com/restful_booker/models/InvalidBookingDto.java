package api.com.restful_booker.models;

public class InvalidBookingDto {

    public Integer totalprice;
    public boolean depositpaid;
    public BookingDates bookingdates;
    public String additionalneeds;

    public InvalidBookingDto(int totalprice, boolean depositpaid, BookingDates bookingdates, String additionalneeds) {
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
        this.additionalneeds = additionalneeds;
    }
}

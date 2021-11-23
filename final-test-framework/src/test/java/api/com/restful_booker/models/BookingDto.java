package api.com.restful_booker.models;

import java.util.Objects;

public class BookingDto {

    public String firstname;
    public String lastname;
    public Integer totalprice;
    public boolean depositpaid;
    public BookingDates bookingdates;
    public String additionalneeds;

    public BookingDto(String firstname, String lastname, int totalprice, boolean depositpaid, BookingDates bookingdates, String additionalneeds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
        this.additionalneeds = additionalneeds;
    }

    @Override
    public String toString() {
        return "BookingRequestDto{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", totalprice=" + totalprice +
                ", depositpaid=" + depositpaid +
                ", bookingdates=" + bookingdates +
                ", additionalneeds='" + additionalneeds + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingDto that = (BookingDto) o;
        return depositpaid == that.depositpaid && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname) && Objects.equals(totalprice, that.totalprice) && Objects.equals(bookingdates, that.bookingdates) && Objects.equals(additionalneeds, that.additionalneeds);
    }
}





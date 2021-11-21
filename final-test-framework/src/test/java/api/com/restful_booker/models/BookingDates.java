package api.com.restful_booker.models;

import java.util.Date;

public class BookingDates {

        public String  checkin;
        public String checkout;

        public BookingDates(String checkin, String checkout) {
            this.checkin = checkin;
            this.checkout = checkout;
        }


}

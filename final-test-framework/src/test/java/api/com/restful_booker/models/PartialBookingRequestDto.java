package api.com.restful_booker.models;

public class PartialBookingRequestDto {

    private String firstname;
    private String lastname;

    public PartialBookingRequestDto(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}

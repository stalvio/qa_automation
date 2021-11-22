package api.com.restful_booker.models;

import java.util.Objects;

public class PartialBookingDto {

    private String firstname;
    private String lastname;

    public PartialBookingDto(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartialBookingDto that = (PartialBookingDto) o;
        return Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname);
    }
}

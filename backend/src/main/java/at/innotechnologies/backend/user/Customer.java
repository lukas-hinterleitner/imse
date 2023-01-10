package at.innotechnologies.backend.user;

import java.time.LocalDate;

public interface Customer extends User {
    void setRegistrationDate(LocalDate registrationDate);
    void setPhoneNumber(String phoneNumber);

    LocalDate getRegistrationDate();
    String getPhoneNumber();
}

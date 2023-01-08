package at.innotechnologies.backend.user;

import java.time.LocalDate;

public interface Employee extends User {
    LocalDate hiringDate = null;
    Double salary = null;

    LocalDate getHiringDate();
    Double getSalary();

    void setHiringDate(LocalDate hiringDate);
    void setSalary(Double salary);
}

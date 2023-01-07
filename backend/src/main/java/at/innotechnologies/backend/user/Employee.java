package at.innotechnologies.backend.user;

import at.innotechnologies.backend.library.Library;

import javax.persistence.Column;
import java.time.LocalDate;

public interface Employee extends User {
    LocalDate hiringDate = null;
    Double salary = null;
    Library library = null;

    LocalDate getHiringDate();
    Double getSalary();
    Library getLibrary();

    void setHiringDate(LocalDate hiringDate);
    void setSalary(Double salary);
    void setLibrary(Library library);
}

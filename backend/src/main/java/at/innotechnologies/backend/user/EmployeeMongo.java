package at.innotechnologies.backend.user;

import at.innotechnologies.backend.library.Library;
import at.innotechnologies.backend.library.LibraryMongo;
import lombok.*;

import javax.persistence.Column;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class EmployeeMongo extends UserMongo implements Employee {
    private LocalDate hiringDate;

    private Double salary;

    private LibraryMongo library;

    @Override
    public void setLibrary(Library library) {
        this.library = (LibraryMongo) library;
    }
}

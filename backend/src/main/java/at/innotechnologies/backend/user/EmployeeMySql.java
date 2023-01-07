package at.innotechnologies.backend.user;

import at.innotechnologies.backend.library.Library;
import at.innotechnologies.backend.library.LibraryMySql;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
@Data
@EqualsAndHashCode
@ToString
public class EmployeeMySql extends UserMySql implements Employee {
    @Column(nullable = false)
    private LocalDate hiringDate;

    @Column(nullable = false)
    private Double salary;

    @JsonIgnore
    @ManyToOne(targetEntity = LibraryMySql.class, optional = false)
    @JoinColumn(name = "libraryId", referencedColumnName = "id")
    private LibraryMySql library;

    @Override
    public void setLibrary(Library library) {
        this.library = (LibraryMySql) library;
    }
}

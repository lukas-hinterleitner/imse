package at.innotechnologies.backend.user;

import at.innotechnologies.backend.library.Library;
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
public class Employee extends User {
    @Column(nullable = false)
    private LocalDate hiringDate;

    @Column(nullable = false)
    private Double salary;

    @JsonIgnore
    @ManyToOne(targetEntity = Library.class, optional = false)
    @JoinColumn(name = "libraryId", referencedColumnName = "id")
    private Library library;
}

package at.innotechnologies.backend.library;

import at.innotechnologies.backend.user.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "library")
@Data
@EqualsAndHashCode
@ToString
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String addressStreet;

    @Column(nullable = false)
    private String addressCity;

    @JsonIgnore
    @OneToMany(targetEntity = Room.class, mappedBy = "library")
    private List<Room> rooms;

    @JsonIgnore
    @OneToMany(targetEntity = Employee.class, mappedBy = "library")
    private List<Employee> employees;
}

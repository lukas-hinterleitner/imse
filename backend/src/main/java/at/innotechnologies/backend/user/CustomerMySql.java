package at.innotechnologies.backend.user;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
@Data
@EqualsAndHashCode
@ToString
public class CustomerMySql extends UserMySql implements Customer {
    @Column(nullable = false)
    private LocalDate registrationDate;

    @Column(nullable = false)
    private String phoneNumber;
}

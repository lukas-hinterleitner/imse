package at.innotechnologies.backend.user;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class CustomerMongo extends UserMongo implements Customer {
    private LocalDate registrationDate;

    private String phoneNumber;
}

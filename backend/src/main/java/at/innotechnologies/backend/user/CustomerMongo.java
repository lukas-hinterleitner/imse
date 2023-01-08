package at.innotechnologies.backend.user;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Document(collection = "customers")
public class CustomerMongo extends UserMongo implements Customer {
    private LocalDate registrationDate;

    private String phoneNumber;
}

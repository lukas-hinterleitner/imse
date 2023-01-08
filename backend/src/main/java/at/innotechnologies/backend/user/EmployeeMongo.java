package at.innotechnologies.backend.user;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Document(collection = "employees")
public class EmployeeMongo extends UserMongo implements Employee {

    @Field
    private LocalDate hiringDate;

    @Field
    private Double salary;
}

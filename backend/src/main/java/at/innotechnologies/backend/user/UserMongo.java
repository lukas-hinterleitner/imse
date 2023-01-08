package at.innotechnologies.backend.user;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class UserMongo implements User {

    @Id
    private String id = UUID.randomUUID().toString();

    @Field
    private String name;

    @Field
    private String email;
}

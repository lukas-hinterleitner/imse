package at.innotechnologies.backend.user;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class UserMongo implements User {
    private Integer id;
    private String name;
    private String email;
}

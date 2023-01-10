package at.innotechnologies.backend.book;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Document(collection = "books")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class BookMongo implements Book {

    @Id
    @EqualsAndHashCode.Include
    private String id = UUID.randomUUID().toString();

    @Field
    private String name;

    @Field
    private Integer amountPages;

}

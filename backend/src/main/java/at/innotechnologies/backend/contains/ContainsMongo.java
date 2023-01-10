package at.innotechnologies.backend.contains;

import at.innotechnologies.backend.book.Book;
import at.innotechnologies.backend.book.BookMongo;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class ContainsMongo implements Contains {

    @Id
    @EqualsAndHashCode.Include
    private String id = UUID.randomUUID().toString();

    @DocumentReference
    private BookMongo book;

    @Field
    @EqualsAndHashCode.Include
    private LocalDate deliveryDate;

    @Field
    @EqualsAndHashCode.Include
    private Integer quantity;

    @Override
    public void setBook(Book book) {
        this.book = (BookMongo) book;
    }
}

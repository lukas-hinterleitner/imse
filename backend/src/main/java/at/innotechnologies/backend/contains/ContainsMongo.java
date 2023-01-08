package at.innotechnologies.backend.contains;

import at.innotechnologies.backend.book.Book;
import at.innotechnologies.backend.book.BookMongo;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class ContainsMongo implements Contains {

    @Id
    private String id = UUID.randomUUID().toString();

    @DBRef
    private BookMongo book;

    @Field
    private LocalDate deliveryDate;

    @Field
    private Integer quantity;

    @Override
    public void setBook(Book book) {
        this.book = (BookMongo) book;
    }
}

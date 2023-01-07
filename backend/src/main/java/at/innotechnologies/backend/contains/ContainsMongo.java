package at.innotechnologies.backend.contains;

import at.innotechnologies.backend.book.Book;
import at.innotechnologies.backend.book.BookMongo;
import at.innotechnologies.backend.library.Room;
import at.innotechnologies.backend.library.RoomMySql;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Document(collection = "contains")
public class ContainsMongo implements Contains {
    private Integer id;

    private BookMongo book;
    private RoomMySql room;
    private LocalDate deliveryDate;

    private Integer quantity;

    @Override
    public void setBook(Book book) {
        this.book = (BookMongo) book;
    }

    @Override
    public void setRoom(Room room) {
        this.room = (RoomMySql) room;
    }
}

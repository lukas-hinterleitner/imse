package at.innotechnologies.backend.borrow;

import at.innotechnologies.backend.book.Book;
import at.innotechnologies.backend.book.BookMongo;
import at.innotechnologies.backend.library.Room;
import at.innotechnologies.backend.library.RoomMongo;
import at.innotechnologies.backend.user.User;
import at.innotechnologies.backend.user.UserMongo;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Document(collection = "borrows")
public class BorrowsMongo implements Borrows {

    @Id
    private String id;

    @Field
    private UserMongo user;

    @DBRef
    private BookMongo book;

    @DBRef
    private RoomMongo room;

    @Field
    private LocalDate startDate;

    @Field
    private LocalDate endDate;

    @Override
    public void setBook(Book book) {
        this.book = (BookMongo) book;
    }

    @Override
    public void setUser(User user) {
        this.user = (UserMongo) user;
    }

    @Override
    public void setRoom(Room room) {
        this.room = (RoomMongo) room;
    }
}

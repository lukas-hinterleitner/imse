package at.innotechnologies.backend.borrow;

import at.innotechnologies.backend.book.Book;
import at.innotechnologies.backend.library.Room;
import at.innotechnologies.backend.library.RoomMySql;
import at.innotechnologies.backend.user.UserMySql;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Document(collection = "borrows")
public class BorrowsMongo implements Borrows {
    private Integer id;

    private UserMySql user;

    private Book book;

    private RoomMySql room;

    private LocalDate startDate;

    private LocalDate endDate;

    @Override
    public void setRoom(Room room) {
        this.room = (RoomMySql) room;
    }
}

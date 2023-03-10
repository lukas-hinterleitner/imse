package at.innotechnologies.backend.borrow;

import at.innotechnologies.backend.book.Book;
import at.innotechnologies.backend.book.BookMySql;
import at.innotechnologies.backend.library.Room;
import at.innotechnologies.backend.library.RoomMySql;
import at.innotechnologies.backend.user.User;
import at.innotechnologies.backend.user.UserMySql;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "borrows")
@Data
@EqualsAndHashCode
@ToString
public class BorrowsMySql implements Borrows {
    @Id
    private String id = UUID.randomUUID().toString();

    @ManyToOne(targetEntity = UserMySql.class)
    @JoinColumn(name = "userId", nullable = false, updatable = false)
    private UserMySql user;

    @ManyToOne(targetEntity = BookMySql.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "bookId", nullable = false, updatable = false)
    private BookMySql book;

    @ManyToOne(targetEntity = RoomMySql.class)
    @JoinColumns({
            @JoinColumn(name = "roomId", nullable = false, updatable = false),
            @JoinColumn(name = "libraryId", nullable = false, updatable = false)
    })
    private RoomMySql room;

    @Column(nullable = false, updatable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Override
    public void setBook(Book book) {
        this.book = (BookMySql) book;
    }

    @Override
    public void setUser(User user) {
        this.user = (UserMySql) user;
    }

    @Override
    public void setRoom(Room room) {
        this.room = (RoomMySql) room;
    }
}

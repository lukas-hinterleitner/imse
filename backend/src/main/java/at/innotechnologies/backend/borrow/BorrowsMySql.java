package at.innotechnologies.backend.borrow;

import at.innotechnologies.backend.book.Book;
import at.innotechnologies.backend.book.BookMySql;
import at.innotechnologies.backend.library.Room;
import at.innotechnologies.backend.library.RoomMySql;
import at.innotechnologies.backend.user.UserMySql;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "borrows")
@Data
@EqualsAndHashCode
@ToString
public class BorrowsMySql implements Borrows {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = UserMySql.class)
    @JoinColumn(name = "userId", nullable = false, updatable = false)
    private UserMySql user;

    @ManyToOne(targetEntity = BookMySql.class)
    @JoinColumn(name = "bookId", nullable = false, updatable = false)
    private Book book;

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
    public void setRoom(Room room) {
        this.room = (RoomMySql) room;
    }
}

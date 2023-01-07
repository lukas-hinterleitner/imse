package at.innotechnologies.backend.borrow;

import at.innotechnologies.backend.book.Book;
import at.innotechnologies.backend.library.Room;
import at.innotechnologies.backend.user.User;
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
public class Borrows {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "userId", nullable = false, updatable = false)
    private User user;

    @ManyToOne(targetEntity = Book.class)
    @JoinColumn(name = "bookId", nullable = false, updatable = false)
    private Book book;

    @ManyToOne(targetEntity = Room.class)
    @JoinColumns({
            @JoinColumn(name = "roomId", nullable = false, updatable = false),
            @JoinColumn(name = "libraryId", nullable = false, updatable = false)
    })
    private Room room;

    @Column(nullable = false, updatable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;
}

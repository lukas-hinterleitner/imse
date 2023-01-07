package at.innotechnologies.backend.contains;

import at.innotechnologies.backend.book.Book;
import at.innotechnologies.backend.library.Room;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contains")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Contains {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @EqualsAndHashCode.Exclude
    @ManyToOne(targetEntity = Book.class)
    @JoinColumn(name = "bookId", nullable = false, updatable = false)
    private Book book;

    @EqualsAndHashCode.Exclude
    @ManyToOne(targetEntity = Room.class)
    @JoinColumns({
            @JoinColumn(name = "roomId", nullable = false, updatable = false),
            @JoinColumn(name = "libraryId", nullable = false, updatable = false)
    })
    private Room room;

    @Column
    private LocalDate deliveryDate;

    @Column
    private Integer quantity;
}

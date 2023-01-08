package at.innotechnologies.backend.contains;

import at.innotechnologies.backend.book.Book;
import at.innotechnologies.backend.book.BookMySql;
import at.innotechnologies.backend.library.RoomMySql;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contains")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class ContainsMySql implements Contains {

    @EqualsAndHashCode.Include
    @Id
    private String id = UUID.randomUUID().toString();

    @EqualsAndHashCode.Exclude
    @ManyToOne(targetEntity = BookMySql.class)
    @JoinColumn(name = "bookId", nullable = false, updatable = false)
    private BookMySql book;

    @EqualsAndHashCode.Exclude
    @ManyToOne(targetEntity = RoomMySql.class)
    @JoinColumns({
            @JoinColumn(name = "roomId", nullable = false, updatable = false),
            @JoinColumn(name = "libraryId", nullable = false, updatable = false)
    })
    private RoomMySql room;

    @Column
    private LocalDate deliveryDate;

    @Column
    private Integer quantity;

    @Override
    public void setBook(Book book) {
        this.book = (BookMySql) book;
    }
}

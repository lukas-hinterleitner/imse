package at.innotechnologies.backend.library;

import at.innotechnologies.backend.book.Book;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "room")
@Data
@EqualsAndHashCode
@ToString
public class Room {
    @EmbeddedId
    private RoomPrimaryKey roomPrimaryKey = new RoomPrimaryKey();

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer capacity;

    @ManyToOne
    @MapsId("libraryId")
    @JoinColumn(name = "libraryId", referencedColumnName = "id")
    private Library library;

    @ManyToMany(targetEntity = Book.class)
    @JoinTable(
            name = "contains",
            joinColumns = {
                    @JoinColumn(name = "roomId"),
                    @JoinColumn(name = "libraryId")
            },
            inverseJoinColumns = @JoinColumn(name = "bookId"))
    private List<Book> books = new ArrayList<>();
}

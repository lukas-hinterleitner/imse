package at.innotechnologies.backend.library;

import at.innotechnologies.backend.book.Book;
import at.innotechnologies.backend.borrow.Borrows;
import at.innotechnologies.backend.contains.Contains;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "room")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Room {

    @EqualsAndHashCode.Include
    @EmbeddedId
    private RoomPrimaryKey roomPrimaryKey = new RoomPrimaryKey();

    @EqualsAndHashCode.Include
    @Column(nullable = false)
    private String name;

    @EqualsAndHashCode.Include
    @Column(nullable = false)
    private Integer capacity;

    @ManyToOne
    @MapsId("libraryId")
    @JoinColumn(name = "libraryId", referencedColumnName = "id")
    private Library library;

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(targetEntity = Contains.class, mappedBy = "room")
    private Set<Contains> contains = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(targetEntity = Borrows.class, mappedBy = "room")
    private Set<Borrows> borrowed = new HashSet<>();
}

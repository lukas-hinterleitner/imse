package at.innotechnologies.backend.book;

import at.innotechnologies.backend.library.Room;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
@Data
@EqualsAndHashCode
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer amountPages;

    @JsonIgnore
    @ManyToMany(mappedBy = "books")
    private List<Room> rooms = new ArrayList<>();
}

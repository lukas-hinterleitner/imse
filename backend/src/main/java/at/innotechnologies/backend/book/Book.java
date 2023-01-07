package at.innotechnologies.backend.book;

import at.innotechnologies.backend.borrow.Borrows;
import at.innotechnologies.backend.contains.Contains;
import at.innotechnologies.backend.library.Room;
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
@Table(name = "book")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Book {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer amountPages;

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(targetEntity = Contains.class, mappedBy = "book")
    private Set<Contains> contains = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(targetEntity = Borrows.class, mappedBy = "book")
    private Set<Borrows> borrowed = new HashSet<>();
}

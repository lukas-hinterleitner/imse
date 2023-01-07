package at.innotechnologies.backend.user;

import at.innotechnologies.backend.book.Book;
import at.innotechnologies.backend.borrow.Borrows;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(targetEntity = Borrows.class, mappedBy = "user")
    private Set<Borrows> borrowed = new HashSet<>();
}

package at.innotechnologies.backend.book;

import at.innotechnologies.backend.borrow.Borrows;
import at.innotechnologies.backend.borrow.BorrowsMySql;
import at.innotechnologies.backend.contains.Contains;
import at.innotechnologies.backend.contains.ContainsMySql;
import at.innotechnologies.backend.contains.ContainsRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class BookMySql implements Book {
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
    @OneToMany(targetEntity = ContainsMySql.class, mappedBy = "book")
    private Set<ContainsMySql> contains = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(targetEntity = BorrowsMySql.class, mappedBy = "book")
    private Set<BorrowsMySql> borrowed = new HashSet<>();

    @Override
    public Set<Borrows> getBorrowed() {
        return this.borrowed.stream().map(borrowsMySql -> (Borrows) borrowsMySql).collect(Collectors.toSet());
    }

    @Override
    public Set<Contains> getContains() {
        return this.contains.stream().map(containsMySql -> (Contains) containsMySql).collect(Collectors.toSet());
    }
}

package at.innotechnologies.backend.book;

import at.innotechnologies.backend.borrow.BorrowsMySql;
import at.innotechnologies.backend.contains.ContainsMySql;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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
    private String id = UUID.randomUUID().toString();

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
}

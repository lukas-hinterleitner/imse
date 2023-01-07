package at.innotechnologies.backend.book;

import at.innotechnologies.backend.borrow.Borrows;
import at.innotechnologies.backend.borrow.BorrowsMongo;
import at.innotechnologies.backend.contains.Contains;
import at.innotechnologies.backend.contains.ContainsMongo;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Document(collection = "books")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class BookMongo implements Book {
    private Integer id;
    private String name;
    private Integer amountPages;

    private Set<ContainsMongo> contains = new HashSet<>();
    private Set<BorrowsMongo> borrowed = new HashSet<>();

    @Override
    public Set<Contains> getContains() {
        return this.contains.stream().map(containsMongo -> (Contains) containsMongo).collect(Collectors.toSet());
    }

    @Override
    public Set<Borrows> getBorrowed() {
        return this.borrowed.stream().map(borrowsMongo -> (Borrows) borrowsMongo).collect(Collectors.toSet());
    }


}

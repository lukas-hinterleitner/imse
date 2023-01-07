package at.innotechnologies.backend.library;

import at.innotechnologies.backend.borrow.BorrowsMongo;
import at.innotechnologies.backend.borrow.BorrowsMySql;
import at.innotechnologies.backend.contains.Contains;
import at.innotechnologies.backend.contains.ContainsMongo;
import at.innotechnologies.backend.contains.ContainsMySql;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class RoomMongo implements Room {

    @Id
    private Integer id;

    @EqualsAndHashCode.Include
    private String name;

    private Integer capacity;

    private LibraryMongo library;

    @EqualsAndHashCode.Exclude
    private Set<ContainsMongo> contains = new HashSet<>();

    @EqualsAndHashCode.Exclude
    private Set<BorrowsMongo> borrowed = new HashSet<>();

    @Override
    public void setLibrary(Library library) {
        this.library = (LibraryMongo) library;
    }

    @Override
    public Set<Contains> getContains() {
        return this.contains.stream().map(containsMongo -> (Contains) containsMongo).collect(Collectors.toSet());
    }
}

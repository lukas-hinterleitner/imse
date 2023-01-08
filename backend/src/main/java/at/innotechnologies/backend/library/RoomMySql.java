package at.innotechnologies.backend.library;

import at.innotechnologies.backend.borrow.BorrowsMySql;
import at.innotechnologies.backend.contains.Contains;
import at.innotechnologies.backend.contains.ContainsMySql;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "room")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class RoomMySql implements Room {

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
    private LibraryMySql library;

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(targetEntity = ContainsMySql.class, mappedBy = "room")
    private Set<ContainsMySql> contains = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(targetEntity = BorrowsMySql.class, mappedBy = "room")
    private Set<BorrowsMySql> borrowed = new HashSet<>();

    @Override
    public Set<Contains> getContains() {
        return this.contains.stream().map(containsMySql -> (Contains) containsMySql).collect(Collectors.toSet());
    }

    @Override
    public String getId() {
        return this.roomPrimaryKey.getRoomId();
    }
}

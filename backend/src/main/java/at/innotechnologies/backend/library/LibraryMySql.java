package at.innotechnologies.backend.library;

import at.innotechnologies.backend.user.EmployeeMySql;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "library")
@Data
@EqualsAndHashCode
@ToString
public class LibraryMySql implements Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String addressStreet;

    @Column(nullable = false)
    private String addressCity;

    @JsonIgnore
    @OneToMany(targetEntity = RoomMySql.class, mappedBy = "library")
    private List<RoomMySql> rooms = new ArrayList<>();

    @JsonIgnore
    @OneToMany(targetEntity = EmployeeMySql.class, mappedBy = "library")
    private List<EmployeeMySql> employees = new ArrayList<>();

    public void addRoom(Room room) {
        this.rooms.add((RoomMySql) room);
        room.setLibrary(this);
    }

    @Override
    public List<Room> getRooms() {
        return this.rooms.stream().map(roomMySql -> (Room) roomMySql).collect(Collectors.toList());
    }
}

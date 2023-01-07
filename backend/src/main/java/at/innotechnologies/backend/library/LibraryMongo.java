package at.innotechnologies.backend.library;

import at.innotechnologies.backend.user.EmployeeMySql;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Document(collection = "libraries")
public class LibraryMongo implements Library {

    @Id
    private Integer id;

    private String name;

    private String addressStreet;

    private String addressCity;

    private List<RoomMongo> rooms = new ArrayList<>();

    private List<EmployeeMySql> employees = new ArrayList<>();

    public void addRoom(Room room) {
        this.rooms.add((RoomMongo) room);
        room.setLibrary(this);
    }

    @Override
    public List<Room> getRooms() {
        return this.rooms.stream().map(roomMongo -> (Room) roomMongo).collect(Collectors.toList());
    }
}

package at.innotechnologies.backend.library;

import at.innotechnologies.backend.user.Employee;
import at.innotechnologies.backend.user.EmployeeMongo;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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
    private String id;

    @Field
    private String name;

    @Field
    private String addressStreet;

    @Field
    private String addressCity;

    @Field
    private List<RoomMongo> rooms = new ArrayList<>();

    @Field
    private List<EmployeeMongo> employees = new ArrayList<>();

    public void addRoom(Room room) {
        this.rooms.add((RoomMongo) room);
    }

    @Override
    public List<Room> getRooms() {
        return this.rooms.stream().map(roomMongo -> (Room) roomMongo).collect(Collectors.toList());
    }

    @Override
    public List<Employee> getEmployees() {
        return this.employees.stream().map(employeeMongo -> (Employee) employeeMongo).collect(Collectors.toList());
    }
}

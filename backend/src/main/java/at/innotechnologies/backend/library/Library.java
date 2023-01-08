package at.innotechnologies.backend.library;

import at.innotechnologies.backend.user.Employee;

import java.util.ArrayList;
import java.util.List;

public interface Library {
    String id = null;
    String name = null;
    String addressStreet = null;
    String addressCity = null;
    List<Room> rooms = new ArrayList<>();
    List<Employee> employees = new ArrayList<>();

    String getId();

    List<Room> getRooms();
    String getName();
    String getAddressStreet();
    String getAddressCity();
    List<Employee> getEmployees();

    void setName(String name);
    void setAddressStreet(String addressStreet);
    void setAddressCity(String addressStreet);

    void addRoom(Room room);
}

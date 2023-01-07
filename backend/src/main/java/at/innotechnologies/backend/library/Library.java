package at.innotechnologies.backend.library;

import at.innotechnologies.backend.user.EmployeeMySql;

import java.util.ArrayList;
import java.util.List;

public interface Library {
    Integer id = null;
    String name = null;
    String addressStreet = null;
    String addressCity = null;
    List<Room> rooms = new ArrayList<>();
    List<EmployeeMySql> employees = new ArrayList<>();

    Integer getId();

    List<Room> getRooms();

    void setName(String name);
    void setAddressStreet(String addressStreet);
    void setAddressCity(String addressStreet);

    void addRoom(Room room);
}

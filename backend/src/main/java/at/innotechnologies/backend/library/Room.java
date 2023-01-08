package at.innotechnologies.backend.library;

import at.innotechnologies.backend.contains.Contains;

import java.util.HashSet;
import java.util.Set;

public interface Room {

    RoomPrimaryKey roomPrimaryKey = new RoomPrimaryKey();
    String name = null;
    Integer capacity = null;
    Set<Contains> contains = new HashSet<>();

    void setName(String name);
    void setCapacity(Integer capacity);

    Set<Contains> getContains();
    String getName();
    Integer getCapacity();
    String getId();
}

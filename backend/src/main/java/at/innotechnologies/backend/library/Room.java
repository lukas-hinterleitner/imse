package at.innotechnologies.backend.library;

import at.innotechnologies.backend.contains.Contains;

import java.util.Set;

public interface Room {
    void setName(String name);
    void setCapacity(Integer capacity);

    Set<Contains> getContains();
    String getName();
    Integer getCapacity();
    String getId();

    void addContains(Contains contains);
}

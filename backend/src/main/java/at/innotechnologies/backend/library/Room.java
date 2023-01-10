package at.innotechnologies.backend.library;

import at.innotechnologies.backend.contains.Contains;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

public interface Room {
    void setName(String name);
    void setCapacity(Integer capacity);

    @JsonIgnore
    Set<Contains> getContains();
    String getName();
    Integer getCapacity();
    String getId();
}

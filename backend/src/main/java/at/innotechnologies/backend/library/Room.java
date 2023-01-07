package at.innotechnologies.backend.library;

import at.innotechnologies.backend.borrow.Borrows;
import at.innotechnologies.backend.borrow.BorrowsMySql;
import at.innotechnologies.backend.contains.Contains;
import at.innotechnologies.backend.contains.ContainsMySql;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public interface Room {

    RoomPrimaryKey roomPrimaryKey = new RoomPrimaryKey();
    String name = null;
    Integer capacity = null;
    Library library = null;
    Set<Contains> contains = new HashSet<>();
    Set<Borrows> borrowed = new HashSet<>();

    void setName(String name);
    void setLibrary(Library library);
    void setCapacity(Integer capacity);

    Set<Contains> getContains();
}

package at.innotechnologies.backend.contains;

import at.innotechnologies.backend.book.Book;
import at.innotechnologies.backend.library.Room;
import at.innotechnologies.backend.library.RoomMySql;

import java.time.LocalDate;

public interface Contains {
    Integer id = null;
    Book book = null;
    RoomMySql room = null;
    LocalDate deliveryDate = LocalDate.now();
    Integer quantity = 0;

    void setBook(Book book);
    void setRoom(Room room);
    void setId(Integer id);
    void setQuantity(Integer quantity);
    void setDeliveryDate(LocalDate deliveryDate);

    Book getBook();
    Integer getQuantity();
}

package at.innotechnologies.backend.borrow;

import at.innotechnologies.backend.book.Book;
import at.innotechnologies.backend.library.Room;
import at.innotechnologies.backend.library.RoomMySql;
import at.innotechnologies.backend.user.UserMySql;

import java.time.LocalDate;

public interface Borrows {
    Book book = null;
    UserMySql user = null;
    RoomMySql room = null;
    LocalDate startDate = LocalDate.now();
    LocalDate endDate = LocalDate.now().plusMonths(2);

    Book getBook();
    UserMySql getUser();
    RoomMySql getRoom();
    LocalDate getStartDate();
    LocalDate getEndDate();

    void setBook(Book book);
    void setUser(UserMySql user);
    void setRoom(Room room);
    void setStartDate(LocalDate startDate);
    void setEndDate(LocalDate endDate);
}

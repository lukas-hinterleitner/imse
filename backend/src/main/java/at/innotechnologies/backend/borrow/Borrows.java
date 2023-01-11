package at.innotechnologies.backend.borrow;

import at.innotechnologies.backend.book.Book;
import at.innotechnologies.backend.library.Room;
import at.innotechnologies.backend.user.User;

import java.time.LocalDate;

public interface Borrows {
    Book getBook();
    User getUser();
    LocalDate getStartDate();
    LocalDate getEndDate();
    String getId();

    void setBook(Book book);
    void setUser(User user);
    void setRoom(Room room);
    void setStartDate(LocalDate startDate);
    void setEndDate(LocalDate endDate);
    void setId(String id);
}

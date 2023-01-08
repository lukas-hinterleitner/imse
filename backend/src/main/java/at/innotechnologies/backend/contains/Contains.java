package at.innotechnologies.backend.contains;

import at.innotechnologies.backend.book.Book;

import java.time.LocalDate;

public interface Contains {
    String id = null;
    Book book = null;
    LocalDate deliveryDate = LocalDate.now();
    Integer quantity = 0;

    void setBook(Book book);
    void setId(String id);
    void setQuantity(Integer quantity);
    void setDeliveryDate(LocalDate deliveryDate);

    String getId();
    Book getBook();
    Integer getQuantity();
    LocalDate getDeliveryDate();
}

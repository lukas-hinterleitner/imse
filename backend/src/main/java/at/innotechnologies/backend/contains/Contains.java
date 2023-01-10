package at.innotechnologies.backend.contains;

import at.innotechnologies.backend.book.Book;

import java.time.LocalDate;

public interface Contains {
    void setBook(Book book);
    void setId(String id);
    void setQuantity(Integer quantity);
    void setDeliveryDate(LocalDate deliveryDate);

    String getId();
    Book getBook();
    Integer getQuantity();
    LocalDate getDeliveryDate();
}

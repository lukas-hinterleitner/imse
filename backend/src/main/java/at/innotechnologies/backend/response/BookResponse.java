package at.innotechnologies.backend.response;

import at.innotechnologies.backend.book.Book;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BookResponse {
    private Book book;

    private Integer quantity;
}

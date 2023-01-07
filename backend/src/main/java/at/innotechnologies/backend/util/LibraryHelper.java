package at.innotechnologies.backend.util;

import at.innotechnologies.backend.book.Book;
import at.innotechnologies.backend.contains.Contains;
import at.innotechnologies.backend.contains.ContainsRepository;
import at.innotechnologies.backend.library.Library;
import at.innotechnologies.backend.library.LibraryRepository;
import at.innotechnologies.backend.library.Room;
import at.innotechnologies.backend.response.BookResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LibraryHelper {
    @NonNull
    private ContainsRepository containsRepository;

    @NonNull
    private LibraryRepository libraryRepository;

    public void addBookToRoom(Room room, Book book, int quantity) {
        Contains contains = new Contains();

        contains.setBook(book);
        contains.setRoom(room);
        contains.setQuantity(quantity);
        contains.setDeliveryDate(LocalDate.now());

        room.getContains().add(contains);
        book.getContains().add(contains);

        containsRepository.save(contains);
    }

    public List<BookResponse> getBooksForLibrary(Integer libraryId) {
        Library library = libraryRepository.findById(libraryId).orElseThrow();

        final List<BookResponse> bookResponses = new ArrayList<>();

        Map<Book, Integer> books = new HashMap<>();

        for (Room room: library.getRooms()) {
            for (Contains contains: room.getContains()) {
                books.put(contains.getBook(), contains.getQuantity() + books.getOrDefault(contains.getBook(), 0));
            }
        }

        books.keySet().forEach(book -> bookResponses.add(new BookResponse(book, books.get(book))));

        return bookResponses;
    }
}

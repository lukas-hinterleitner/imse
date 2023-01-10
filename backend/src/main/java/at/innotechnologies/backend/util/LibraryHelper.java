package at.innotechnologies.backend.util;

import at.innotechnologies.backend.book.Book;
import at.innotechnologies.backend.contains.Contains;
import at.innotechnologies.backend.contains.ContainsMySql;
import at.innotechnologies.backend.contains.ContainsRepository;
import at.innotechnologies.backend.library.Library;
import at.innotechnologies.backend.library.LibraryRepository;
import at.innotechnologies.backend.library.Room;
import at.innotechnologies.backend.library.RoomMySql;
import at.innotechnologies.backend.response.BookResponse;
import com.github.javafaker.Faker;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class LibraryHelper {
    @NonNull
    private ContainsRepository containsRepository;

    @NonNull
    private LibraryRepository libraryRepository;

    private LocalDate getRandomLocalDateBetween(LocalDate first, LocalDate last) {
        long randomDay = ThreadLocalRandom.current().nextLong(first.toEpochDay(), last.toEpochDay());
        return LocalDate.ofEpochDay(randomDay);
    }

    public void addBookToRoom(Room room, Book book, int quantity) {
        Contains contains = new ContainsMySql();

        contains.setBook(book);

        if (!Migration.migrationInitialized) {
            ((ContainsMySql)contains).setRoom((RoomMySql) room);
        }

        contains.setQuantity(quantity);
        contains.setDeliveryDate(getRandomLocalDateBetween(LocalDate.of(2022, 12, 1), LocalDate.of(2023, 1, 30)));

        room.getContains().add(contains);

        containsRepository.save(contains);
    }

    public List<BookResponse> getBooksForLibrary(String libraryId) {
        final Library library = libraryRepository.findById(libraryId).orElseThrow();
        final List<BookResponse> bookResponses = new ArrayList<>();

        final Map<Book, Integer> books = new HashMap<>();

        for (Room room: library.getRooms()) {
            for (Contains contains: room.getContains()) {
                books.put(contains.getBook(), contains.getQuantity() + books.getOrDefault(contains.getBook(), 0));
            }
        }

        books.keySet().forEach(book -> bookResponses.add(new BookResponse(book, books.get(book))));

        return bookResponses;
    }
}

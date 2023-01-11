package at.innotechnologies.backend.book;

import at.innotechnologies.backend.contains.Contains;
import at.innotechnologies.backend.contains.ContainsMongo;
import at.innotechnologies.backend.contains.ContainsMySql;
import at.innotechnologies.backend.contains.ContainsRepository;
import at.innotechnologies.backend.library.Library;
import at.innotechnologies.backend.library.LibraryRepository;
import at.innotechnologies.backend.library.Room;
import at.innotechnologies.backend.library.RoomMySql;
import at.innotechnologies.backend.payload.request.ExistingBook;
import at.innotechnologies.backend.payload.request.NewBook;
import at.innotechnologies.backend.util.Migration;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {
    @NonNull
    private BookRepository bookRepository;

    @NonNull
    private LibraryRepository libraryRepository;

    @NonNull
    private ContainsRepository containsRepository;

    public Optional<Book> getBookByName(String name) {
        return bookRepository.findByName(name);
    }

    @Transactional
    public Book addNewBook(NewBook newBook) {
        final Library library = libraryRepository.findById(newBook.getLibraryId()).orElseThrow();
        final Room room = library.getRooms().stream().filter(room1 -> room1.getId().equals(newBook.getRoomId())).findFirst().orElseThrow();

        Book book = Migration.migrationInitialized
                ? new BookMongo()
                : new BookMySql();

        book.setName(newBook.getName());
        book.setAmountPages(newBook.getAmountPages());

        book = bookRepository.save(book);

        final Contains contains = Migration.migrationInitialized
                ? new ContainsMongo()
                : new ContainsMySql();

        contains.setQuantity(newBook.getQuantity());
        contains.setBook(book);
        contains.setDeliveryDate(LocalDate.now());

        room.addContains(contains);

        if (Migration.migrationInitialized) {
            libraryRepository.save(library);
        } else {
            assert contains instanceof ContainsMySql;
            ((ContainsMySql) contains).setRoom((RoomMySql) room);
            containsRepository.save(contains);
        }

        return book;
    }

    @Transactional
    public Book updateBookQuantity(ExistingBook existingBook, String libraryId) {
        final Library library = libraryRepository.findById(libraryId).orElseThrow();
        final Book book = bookRepository.findById(existingBook.getBookId()).orElseThrow();

        final Contains contains = library.getRooms().stream().map(Room::getContains).flatMap(Set::stream).filter(contains1 -> contains1.getBook().equals(book)).findFirst().orElseThrow();
        contains.setQuantity(contains.getQuantity() + existingBook.getQuantity());

        if (Migration.migrationInitialized) {
            libraryRepository.save(library);
        } else {
            containsRepository.save(contains);
        }

        return book;
    }
}

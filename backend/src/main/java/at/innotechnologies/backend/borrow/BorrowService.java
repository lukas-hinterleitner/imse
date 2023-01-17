package at.innotechnologies.backend.borrow;

import at.innotechnologies.backend.book.Book;
import at.innotechnologies.backend.book.BookRepository;
import at.innotechnologies.backend.contains.Contains;
import at.innotechnologies.backend.contains.ContainsRepository;
import at.innotechnologies.backend.library.Library;
import at.innotechnologies.backend.library.LibraryRepository;
import at.innotechnologies.backend.library.Room;
import at.innotechnologies.backend.payload.response.BookResponse;
import at.innotechnologies.backend.payload.response.report.arostegui.LibraryResponse;
import at.innotechnologies.backend.user.User;
import at.innotechnologies.backend.user.UserRepository;
import at.innotechnologies.backend.util.LibraryHelper;
import at.innotechnologies.backend.util.Migration;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BorrowService {
    @NonNull
    private BorrowsRepository borrowsRepository;

    @NonNull
    private LibraryRepository libraryRepository;

    @NonNull
    private BookRepository bookRepository;

    @NonNull
    private UserRepository userRepository;

    @NonNull
    private ContainsRepository containsRepository;

    @NonNull
    private LibraryHelper libraryHelper;

    private Library getLibraryFromBorrows(List<Library> libraries, Borrows borrows) {
        if (Migration.migrationInitialized) {
            return libraries.stream().filter(library -> library.getRooms().stream().anyMatch(room1 -> room1.getId().equals(((BorrowsMongo)borrows).getRoom()))).findFirst().orElseThrow();
        } else {
            return libraries.stream().filter(library -> library.getRooms().stream().anyMatch(room1 -> room1.getId().equals(((BorrowsMySql)borrows).getRoom().getId()))).findFirst().orElseThrow();
        }
    }

    @Transactional
    public List<BookResponse> borrowBook(BorrowBookPayload borrowBookPayload) {
        final Library library = libraryRepository.findById(borrowBookPayload.getLibraryId()).orElseThrow();
        final Book book = bookRepository.findById(borrowBookPayload.getBookId()).orElseThrow();

        // find room where the book is located
        final Room room = library.getRooms().stream().filter(r -> r.getContains().stream().anyMatch(contains -> contains.getBook().equals(book))).findFirst().orElseThrow();
        final User user = userRepository.findById(borrowBookPayload.getUserId()).orElseThrow();

        final Borrows borrows = Migration.migrationInitialized
            ? new BorrowsMongo()
            : new BorrowsMySql();

        borrows.setBook(book);
        borrows.setUser(user);
        borrows.setRoom(room);

        borrows.setStartDate(LocalDate.now());
        borrows.setEndDate(LocalDate.now().plusMonths(2));

        // update stock
        Contains contains = room.getContains().stream().filter(c -> c.getBook().equals(book)).findFirst().orElseThrow();

        if (contains.getQuantity() > 0) {
            contains.setQuantity(contains.getQuantity() - 1);
        }

        if (!Migration.migrationInitialized)
            containsRepository.save(contains);
        else
            libraryRepository.save(library);

        borrowsRepository.save(borrows);

        return libraryHelper.getBooksForLibrary(library.getId());
    }

    @Transactional(readOnly = true)
    public List<LibraryResponse> reportArostegui() {
        final List<Borrows> borrowedBooks = borrowsRepository.findAll().stream().filter(borrows -> borrows.getBook().getAmountPages() > 200).toList();
        final List<Library> libraries = libraryRepository.findAll();

        final Map<Library, Map<Book, Integer>> booksBorrowedPerLibrary = new HashMap<>();

        for (final Borrows borrows: borrowedBooks) {
            final Library library = getLibraryFromBorrows(libraries, borrows);

            final Map<Book, Integer> alreadyCountedBooks = booksBorrowedPerLibrary.getOrDefault(library, new HashMap<>());
            final Integer quantity = alreadyCountedBooks.getOrDefault(borrows.getBook(), 0) + 1;

            alreadyCountedBooks.put(borrows.getBook(), quantity);
            booksBorrowedPerLibrary.put(library, alreadyCountedBooks);
        }

        return booksBorrowedPerLibrary.keySet().stream().map(library -> {
            final Map<Book, Integer> booksAndQuantities = booksBorrowedPerLibrary.get(library);

            final List<BookResponse> books = booksAndQuantities
                    .keySet()
                    .stream()
                    .map(book -> new BookResponse(book, booksAndQuantities.getOrDefault(book, 0)))
                    .sorted(Comparator.comparing(bookResponse -> bookResponse.getBook().getName()))
                    .toList();

            return new LibraryResponse(library.getName(), books);
        }).sorted(Comparator.comparing(LibraryResponse::getName)).toList();
    }
}

package at.innotechnologies.backend.borrow;

import at.innotechnologies.backend.book.Book;
import at.innotechnologies.backend.book.BookRepository;
import at.innotechnologies.backend.contains.Contains;
import at.innotechnologies.backend.contains.ContainsRepository;
import at.innotechnologies.backend.library.Library;
import at.innotechnologies.backend.library.LibraryRepository;
import at.innotechnologies.backend.library.Room;
import at.innotechnologies.backend.response.BookResponse;
import at.innotechnologies.backend.user.User;
import at.innotechnologies.backend.user.UserRepository;
import at.innotechnologies.backend.util.LibraryHelper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

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

    @Transactional
    public List<BookResponse> borrowBook(BorrowBookPayload borrowBookPayload) {
        final Library library = libraryRepository.findById(borrowBookPayload.getLibraryId()).orElseThrow();
        final Book book = bookRepository.findById(borrowBookPayload.getBookId()).orElseThrow();

        // find room where the book is located
        final Room room = library.getRooms().stream().filter(r -> r.getContains().stream().anyMatch(contains -> contains.getBook().equals(book))).findFirst().orElseThrow();
        final User user = userRepository.findById(borrowBookPayload.getUserId()).orElseThrow();

        final Borrows borrows = new Borrows();

        borrows.setBook(book);
        borrows.setUser(user);
        borrows.setRoom(room);
        borrows.setStartDate(LocalDate.now());
        borrows.setEndDate(LocalDate.now().plusMonths(2));

        borrowsRepository.save(borrows);

        // update stock
        Contains contains = room.getContains().stream().filter(c -> c.getBook().equals(book)).findFirst().orElseThrow();

        if (contains.getQuantity() > 0) {
            contains.setQuantity(contains.getQuantity() - 1);
        }

        containsRepository.save(contains);

        return libraryHelper.getBooksForLibrary(library.getId());
    }
}

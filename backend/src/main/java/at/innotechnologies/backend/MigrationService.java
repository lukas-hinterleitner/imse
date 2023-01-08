package at.innotechnologies.backend;

import at.innotechnologies.backend.book.Book;
import at.innotechnologies.backend.book.BookRepository;
import at.innotechnologies.backend.borrow.BorrowsRepository;
import at.innotechnologies.backend.contains.ContainsRepository;
import at.innotechnologies.backend.library.Library;
import at.innotechnologies.backend.library.LibraryRepository;
import at.innotechnologies.backend.library.RoomRepository;
import at.innotechnologies.backend.user.User;
import at.innotechnologies.backend.user.UserRepository;
import at.innotechnologies.backend.util.Migration;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MigrationService {
    @NonNull
    private BookRepository bookRepository;

    @NonNull
    private LibraryRepository libraryRepository;

    @NonNull
    private RoomRepository roomRepository;

    @NonNull
    private UserRepository userRepository;

    @NonNull
    private ContainsRepository containsRepository;

    @NonNull
    private BorrowsRepository borrowsRepository;

    @Transactional
    public void migrate() {
        List<Book> books = bookRepository.findAll();
        List<User> users = userRepository.findAll();
        List<Library> libraries = libraryRepository.findAll();

        Migration.migrationInitialized = true;

        bookRepository.saveAll(books);
        userRepository.saveAll(users);
        libraryRepository.saveAll(libraries);
    }
}

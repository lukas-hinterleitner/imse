package at.innotechnologies.backend.library;

import at.innotechnologies.backend.book.Book;
import at.innotechnologies.backend.contains.Contains;
import at.innotechnologies.backend.response.BookResponse;
import at.innotechnologies.backend.response.report.hinterleitner.LibraryResponse;
import at.innotechnologies.backend.response.report.hinterleitner.RoomResponse;
import at.innotechnologies.backend.user.Customer;
import at.innotechnologies.backend.user.User;
import at.innotechnologies.backend.user.UserRepository;
import at.innotechnologies.backend.util.LibraryHelper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class LibraryService {
    @NonNull
    private LibraryRepository libraryRepository;

    @NonNull
    private RoomRepository roomRepository;

    @NonNull
    private UserRepository userRepository;

    @NonNull
    private LibraryHelper libraryHelper;

    public Library getRandomLibrary(String userId) {
        final List<Library> libraries = libraryRepository.findAll();
        final User user = userRepository.findById(userId).orElseThrow();

        if (user instanceof Customer) {
            return libraries.get(new Random().nextInt(libraries.size()));
        } else {
            return libraries.stream().filter(library -> library.getEmployees().stream().anyMatch(employee -> employee.getId().equals(userId))).findFirst().orElseThrow();
        }
    }

    public List<BookResponse> getBooksForLibrary(String libraryId) {
        return libraryHelper.getBooksForLibrary(libraryId);
    }

    public List<LibraryResponse> reportHinterleitner() {
        final List<Library> libraries = libraryRepository.findAll();

        final List<LibraryResponse> libraryResponses = new ArrayList<>();

        for (Library library: libraries) {
            final LibraryResponse libraryResponse = new LibraryResponse();
            libraryResponse.setName(library.getName());

            for (Room room: library.getRooms()) {
                final RoomResponse roomResponse = new RoomResponse();
                roomResponse.setName(room.getName());

                final List<Contains> sortedBooks = room.getContains()
                        .stream()
                        .filter(contains -> contains.getDeliveryDate().getMonthValue() == 12)
                        .sorted(Comparator.comparing(contains -> contains.getBook().getName()))
                        .toList();

                for (Contains contains: sortedBooks) {
                    final BookResponse bookResponse = new BookResponse(contains.getBook(), contains.getQuantity());
                    roomResponse.getBooks().add(bookResponse);
                }

                libraryResponse.getRooms().add(roomResponse);
            }

            libraryResponses.add(libraryResponse);
        }

        return libraryResponses;
    }
}

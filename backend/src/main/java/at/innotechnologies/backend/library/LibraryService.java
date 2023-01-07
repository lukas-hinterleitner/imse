package at.innotechnologies.backend.library;

import at.innotechnologies.backend.response.BookResponse;
import at.innotechnologies.backend.util.LibraryHelper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
public class LibraryService {
    @NonNull
    private LibraryRepository libraryRepository;

    @NonNull
    private RoomRepository roomRepository;

    @NonNull
    private LibraryHelper libraryHelper;

    public Library getRandomLibrary() {
        final List<Library> libraries = libraryRepository.findAll();

        return libraries.get(new Random().nextInt(libraries.size()));
    }

    public List<BookResponse> getBooksForLibrary(Integer libraryId) {
        return libraryHelper.getBooksForLibrary(libraryId);
    }
}

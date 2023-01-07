package at.innotechnologies.backend.library;

import at.innotechnologies.backend.response.BookResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/library")
@Validated
@RequiredArgsConstructor
public class LibraryController {
    @NonNull
    private LibraryService libraryService;

    @GetMapping("/random")
    public LibraryMySql getRandomLibrary() {
        return libraryService.getRandomLibrary();
    }

    @GetMapping("{id}/books")
    public List<BookResponse> getBookForLibrary(@PathVariable Integer id) {
        return libraryService.getBooksForLibrary(id);
    }
}

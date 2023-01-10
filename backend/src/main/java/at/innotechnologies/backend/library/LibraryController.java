package at.innotechnologies.backend.library;

import at.innotechnologies.backend.response.BookResponse;
import at.innotechnologies.backend.response.report.hinterleitner.LibraryResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/library")
@Validated
@RequiredArgsConstructor
public class LibraryController {
    @NonNull
    private LibraryService libraryService;

    @GetMapping("/random")
    public ResponseEntity<Library> getRandomLibrary(@NotNull @RequestParam("userId") String userId) {
        return ResponseEntity.ok(libraryService.getRandomLibrary(userId));
    }

    @GetMapping("{id}/books")
    public ResponseEntity<List<BookResponse>> getBookForLibrary(@PathVariable String id) {
        return ResponseEntity.ok(libraryService.getBooksForLibrary(id));
    }

    @GetMapping("/report-hinterleitner")
    public ResponseEntity<List<LibraryResponse>> reportHinterleitner() {
        return ResponseEntity.ok(libraryService.reportHinterleitner());
    }
}

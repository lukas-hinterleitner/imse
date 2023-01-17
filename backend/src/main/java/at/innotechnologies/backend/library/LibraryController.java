package at.innotechnologies.backend.library;

import at.innotechnologies.backend.payload.response.BookResponse;
import at.innotechnologies.backend.payload.response.report.hinterleitner.LibraryResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

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

    @GetMapping("{id}/rooms")
    public ResponseEntity<Set<Room>> getRoomsForLibrary(@NotNull @PathVariable("id") String id) {
        return ResponseEntity.ok(libraryService.getRoomsForLibrary(id));
    }
}

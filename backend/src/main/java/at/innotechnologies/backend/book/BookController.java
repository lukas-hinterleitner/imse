package at.innotechnologies.backend.book;

import at.innotechnologies.backend.payload.request.ExistingBook;
import at.innotechnologies.backend.payload.request.NewBook;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/books")
@Validated
@RequiredArgsConstructor
public class BookController {
    @NonNull
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book> addNewBooks(@Valid @RequestBody NewBook newBook) {
        return ResponseEntity.ok(bookService.addNewBook(newBook));
    }

    @PutMapping
    public ResponseEntity<Book> changeBookQuantity(@Valid @RequestBody ExistingBook existingBook,
                                                   @RequestParam("libraryId") String libraryId) {
        return ResponseEntity.ok(bookService.updateBookQuantity(existingBook, libraryId));
    }

    @GetMapping
    public ResponseEntity<Book> getBookByName(@NotNull @RequestParam("name") String bookName) {
        return ResponseEntity.of(bookService.getBookByName(bookName));
    }
}

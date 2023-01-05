package at.innotechnologies.backend.book;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@Validated
@RequiredArgsConstructor
public class BookController {
    @NonNull
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book> addNewBooks() {
        return ResponseEntity.ok(null);
    }

    @GetMapping
    public ResponseEntity<Book> getBooks() {
        return ResponseEntity.ok(null);
    }
}

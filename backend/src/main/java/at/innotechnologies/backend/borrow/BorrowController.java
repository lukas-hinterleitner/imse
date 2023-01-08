package at.innotechnologies.backend.borrow;

import at.innotechnologies.backend.response.BookResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/borrow")
@Validated
@RequiredArgsConstructor
public class BorrowController {
    @NonNull
    private BorrowService borrowService;

    @PostMapping
    public ResponseEntity<List<BookResponse>> borrowBook(@Valid @RequestBody BorrowBookPayload borrowBookPayload) {
        return ResponseEntity.ok(borrowService.borrowBook(borrowBookPayload));
    }
}

package at.innotechnologies.backend.borrow;

import at.innotechnologies.backend.payload.response.BookResponse;
import at.innotechnologies.backend.payload.response.report.arostegui.LibraryResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/report-arogestui")
    public ResponseEntity<List<LibraryResponse>> reportArostegui() {
        return ResponseEntity.ok(borrowService.reportArostegui());
    }
}

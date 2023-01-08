package at.innotechnologies.backend.borrow;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString
public class BorrowBookPayload {
    @NotNull
    private String libraryId;

    @NotNull
    private String userId;

    @NotNull
    private String bookId;
}

package at.innotechnologies.backend.borrow;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@ToString
public class BorrowBookPayload {
    @NotNull
    @Positive
    private Integer libraryId;

    @NotNull
    @Positive
    private Integer userId;

    @NotNull
    @Positive
    private Integer bookId;
}

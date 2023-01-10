package at.innotechnologies.backend.payload.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
public class ExistingBook {
    @NotEmpty
    private String bookId;

    @Positive
    private Integer quantity;
}

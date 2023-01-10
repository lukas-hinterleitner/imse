package at.innotechnologies.backend.payload.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
public class NewBook {
    @NotEmpty
    private String name;

    @Positive
    private Integer quantity;

    @Positive
    private Integer amountPages;

    @NotEmpty
    private String roomId;

    @NotEmpty
    private String libraryId;
}

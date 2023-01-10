package at.innotechnologies.backend.payload.response.report.arostegui;

import at.innotechnologies.backend.payload.response.BookResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LibraryResponse {
    private String name;
    private List<BookResponse> books;
}

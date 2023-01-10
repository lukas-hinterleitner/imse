package at.innotechnologies.backend.payload.response.report.hinterleitner;

import at.innotechnologies.backend.payload.response.BookResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RoomResponse {
    private String name;
    private List<BookResponse> books = new ArrayList<>();
}

package at.innotechnologies.backend.payload.response.report.hinterleitner;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LibraryResponse {
    private String name;
    private List<RoomResponse> rooms = new ArrayList<>();
}

package at.innotechnologies.backend.library;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library")
@Validated
@RequiredArgsConstructor
public class LibraryController {
    @NonNull
    private LibraryService libraryService;

    @GetMapping("/random")
    public Library getRandomLibrary() {
        return libraryService.getRandomLibrary();
    }
}

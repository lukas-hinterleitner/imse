package at.innotechnologies.backend;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
    @GetMapping
    public ResponseEntity<String> applicationRoot() {
        return ResponseEntity.ok("Backend up");
    }
}

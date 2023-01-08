package at.innotechnologies.backend;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RootController {
    @NonNull
    private MigrationService migrationService;

    @GetMapping
    public ResponseEntity<String> applicationRoot() {
        return ResponseEntity.ok("Backend up");
    }

    @GetMapping("/migrate")
    public ResponseEntity<Boolean> migrate() {
        migrationService.migrate();
        return ResponseEntity.ok(true);
    }
}

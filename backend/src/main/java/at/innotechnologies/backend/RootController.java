package at.innotechnologies.backend;

import at.innotechnologies.backend.util.DBInitializer;
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

    @NonNull
    private DBInitializer dbInitializer;

    @GetMapping
    public ResponseEntity<String> applicationRoot() {
        return ResponseEntity.ok("Backend up. wuhu");
    }

    @GetMapping("/import")
    public ResponseEntity<Boolean> importData() {
        dbInitializer.importData();

        return ResponseEntity.ok(true);
    }

    @GetMapping("/migrate")
    public ResponseEntity<Boolean> migrate() {
        migrationService.migrate();
        return ResponseEntity.ok(true);
    }
}

package at.innotechnologies.backend;

import at.innotechnologies.backend.util.Migration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
    @GetMapping
    public ResponseEntity<String> applicationRoot() {
        return ResponseEntity.ok("Backend up");
    }

    @GetMapping("/migrate")
    public ResponseEntity<Boolean> migrate() {
        System.out.println(Migration.migrationFinished);

        // todo: migration from mySql to mongodb

        Migration.migrationFinished = true;
        return ResponseEntity.ok(true);
    }
}

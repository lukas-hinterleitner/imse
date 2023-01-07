package at.innotechnologies.backend.user;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/users")
@Validated
@RequiredArgsConstructor
public class UserController {
    @NonNull
    private UserService userService;

    @GetMapping("/login")
    public ResponseEntity<UserMySql> login(@NotEmpty @RequestParam("email") String email) {
        return ResponseEntity.ok(userService.login(email));
    }

    @PostMapping
    public ResponseEntity<UserMySql> register(@Valid @RequestBody UserCreationPayload payload) {
        return ResponseEntity.ok(userService.register(payload));
    }
}

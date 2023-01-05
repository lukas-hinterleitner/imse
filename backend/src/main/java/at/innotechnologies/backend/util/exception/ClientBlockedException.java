package at.innotechnologies.backend.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
public class ClientBlockedException extends RuntimeException {
    private static final String message = "Blocked due to too much requests. nice try :)";

    public ClientBlockedException(String message) {
        super(message);
    }

    public ClientBlockedException() {
        this(message);
    }
}

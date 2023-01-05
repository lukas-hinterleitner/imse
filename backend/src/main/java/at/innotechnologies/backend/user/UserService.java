package at.innotechnologies.backend.user;

import at.innotechnologies.backend.util.exception.AlreadyExistsException;
import at.innotechnologies.backend.util.exception.ResourceNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    @NonNull
    private UserRepository userRepository;

    public User login(String email) {
        return userRepository.findByEmail(email.toLowerCase())
                .orElseThrow(() -> new ResourceNotFoundException("user not found"));
    }

    public User register(UserCreationPayload payload) {
        userRepository.findByEmail(payload.getEmail().toLowerCase()).ifPresent(user -> {
            throw new AlreadyExistsException("user already exists");
        });

        final Customer customer = new Customer();
        customer.setEmail(payload.getEmail());
        customer.setName(payload.getName());
        customer.setPhoneNumber(payload.getPhoneNumber());
        customer.setRegistrationDate(LocalDate.now());

        return userRepository.save(customer);
    }
}
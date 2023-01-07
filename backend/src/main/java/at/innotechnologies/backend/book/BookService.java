package at.innotechnologies.backend.book;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {
    @NonNull
    private BookRepositoryMySql bookRepository;
}

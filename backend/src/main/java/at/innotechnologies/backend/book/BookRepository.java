package at.innotechnologies.backend.book;

import at.innotechnologies.backend.util.Migration;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookRepository {
    @NonNull
    private BookRepositoryMySql bookRepositoryMySql;

    @NonNull
    private BookRepositoryMongo bookRepositoryMongo;


    public List<Book> findAll() {
        if (Migration.migrationFinished) {
            return bookRepositoryMongo.findAll().stream().map(bookMongo -> (Book)bookMongo).collect(Collectors.toList());
        } else {
            return bookRepositoryMySql.findAll().stream().map(bookMySql -> (Book)bookMySql).collect(Collectors.toList());
        }
    }

    public Book save(Book book) {
        if (Migration.migrationFinished) {
            return bookRepositoryMongo.save((BookMongo) book);
        } else {
            return bookRepositoryMySql.save((BookMySql) book);
        }
    }

    public Optional<Book> findById(Integer id) {
        if (Migration.migrationFinished) {
            return bookRepositoryMongo.findById(id).map(bookMongo -> bookMongo);
        } else {
            return bookRepositoryMySql.findById(id).map(bookMySql -> bookMySql);
        }
    }
}

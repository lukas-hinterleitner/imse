package at.innotechnologies.backend.book;

import at.innotechnologies.backend.util.Migration;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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
        if (Migration.migrationInitialized) {
            return bookRepositoryMongo.findAll().stream().map(bookMongo -> (Book)bookMongo).collect(Collectors.toList());
        } else {
            return bookRepositoryMySql.findAll().stream().map(bookMySql -> (Book)bookMySql).collect(Collectors.toList());
        }
    }

    public Book save(Book book) {
        if (Migration.migrationInitialized) {
            if (book instanceof BookMongo bookMongo) {
                return bookRepositoryMongo.save(bookMongo);
            } else {
                return bookRepositoryMongo.save(Migration.bookToMongo(book));
            }
        } else {
            return bookRepositoryMySql.save((BookMySql) book);
        }
    }

    public List<Book> saveAll(List<Book> books) {
        return books.stream().map(this::save).collect(Collectors.toList());
    }

    public Optional<Book> findById(String id) {
        if (Migration.migrationInitialized) {
            return bookRepositoryMongo.findById(id).map(bookMongo -> bookMongo);
        } else {
            return bookRepositoryMySql.findById(id).map(bookMySql -> bookMySql);
        }
    }

    public Optional<Book> findByName(String name) {
        if (Migration.migrationInitialized) {
            return bookRepositoryMongo.findByName(name).map(bookMongo -> bookMongo);
        } else {
            return bookRepositoryMySql.findByName(name).map(bookMySql -> bookMySql);
        }
    }

}

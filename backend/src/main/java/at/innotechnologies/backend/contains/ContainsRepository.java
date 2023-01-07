package at.innotechnologies.backend.contains;

import at.innotechnologies.backend.book.Book;
import at.innotechnologies.backend.book.BookMongo;
import at.innotechnologies.backend.book.BookMySql;
import at.innotechnologies.backend.util.Migration;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContainsRepository {
    @NonNull
    private ContainsRepositoryMySql containsRepositoryMySql;

    @NonNull
    private ContainsRepositoryMongo containsRepositoryMongo;

    public Contains save(Contains contains) {
        if (Migration.migrationFinished) {
            return containsRepositoryMongo.save((ContainsMongo) contains);
        } else {
            return containsRepositoryMySql.save((ContainsMySql) contains);
        }
    }
}

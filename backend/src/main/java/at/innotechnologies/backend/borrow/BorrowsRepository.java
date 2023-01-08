package at.innotechnologies.backend.borrow;

import at.innotechnologies.backend.util.Migration;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BorrowsRepository {
    @NonNull
    private BorrowsRepositoryMySql borrowsRepositoryMySql;

    @NonNull
    private BorrowsRepositoryMongo borrowsRepositoryMongo;

    public Borrows save(Borrows borrows) {
        if (Migration.migrationInitialized) {
            return borrowsRepositoryMongo.save((BorrowsMongo) borrows);
        } else {
            return borrowsRepositoryMySql.save((BorrowsMySql) borrows);
        }
    }
}

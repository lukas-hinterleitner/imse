package at.innotechnologies.backend.borrow;

import at.innotechnologies.backend.util.Migration;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BorrowsRepository {
    @NonNull
    private BorrowsRepositoryMySql borrowsRepositoryMySql;

    @NonNull
    private BorrowsRepositoryMongo borrowsRepositoryMongo;

    public Borrows save(Borrows borrows) {
        if (Migration.migrationInitialized) {
            if (borrows instanceof BorrowsMongo borrowsMongo) {
                return borrowsRepositoryMongo.save(borrowsMongo);
            } else {
                return borrowsRepositoryMongo.save(Migration.borrowsToMongo(borrows));
            }
        } else {
            return borrowsRepositoryMySql.save((BorrowsMySql) borrows);
        }
    }

    public List<Borrows> findAll() {
        if (Migration.migrationInitialized) {
            return new ArrayList<>(borrowsRepositoryMongo.findAll());
        } else {
            return new ArrayList<>(borrowsRepositoryMySql.findAll());
        }
    }

    public List<Borrows> saveAll(List<Borrows> borrows) {
        return borrows.stream().map(this::save).collect(Collectors.toList());
    }
}

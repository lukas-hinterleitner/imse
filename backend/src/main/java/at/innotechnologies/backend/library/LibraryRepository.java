package at.innotechnologies.backend.library;

import at.innotechnologies.backend.util.Migration;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LibraryRepository {
    @NonNull
    private LibraryRepositoryMongo libraryRepositoryMongo;

    @NonNull
    private LibraryRepositoryMySql libraryRepositoryMySql;

    public List<Library> findAll() {
        if (Migration.migrationFinished) {
            return libraryRepositoryMongo.findAll().stream().map(libraryMongo -> (Library) libraryMongo).collect(Collectors.toList());
        } else {
            return libraryRepositoryMySql.findAll().stream().map(libraryMongo -> (Library) libraryMongo).collect(Collectors.toList());
        }
    }

    public Library save(Library library) {
        if (Migration.migrationFinished) {
            return libraryRepositoryMongo.save((LibraryMongo) library);
        } else {
            return libraryRepositoryMySql.save((LibraryMySql) library);
        }
    }

    public List<Library> saveAll(List<Library> libraries) {
        libraries.forEach(this::save);

        return libraries;
    }

    public Optional<Library> findById(Integer id) {
        if (Migration.migrationFinished) {
            return libraryRepositoryMongo.findById(id).map(libraryMongo -> libraryMongo);
        } else {
            return libraryRepositoryMySql.findById(id).map(libraryMySql -> libraryMySql);
        }
    }
}

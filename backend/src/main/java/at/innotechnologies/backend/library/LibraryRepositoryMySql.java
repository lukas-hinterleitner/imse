package at.innotechnologies.backend.library;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepositoryMySql extends JpaRepository<LibraryMySql, String> {
}

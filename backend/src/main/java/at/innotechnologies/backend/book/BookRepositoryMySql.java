package at.innotechnologies.backend.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepositoryMySql extends JpaRepository<BookMySql, String> {
    Optional<BookMySql> findByName(String name);
}

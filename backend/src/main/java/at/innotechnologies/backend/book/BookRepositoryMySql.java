package at.innotechnologies.backend.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepositoryMySql extends JpaRepository<BookMySql, String> {

}

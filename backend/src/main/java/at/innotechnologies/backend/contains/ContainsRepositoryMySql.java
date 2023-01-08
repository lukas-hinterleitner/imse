package at.innotechnologies.backend.contains;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContainsRepositoryMySql extends JpaRepository<ContainsMySql, String> {
}

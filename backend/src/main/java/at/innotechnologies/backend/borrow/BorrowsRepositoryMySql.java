package at.innotechnologies.backend.borrow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowsRepositoryMySql extends JpaRepository<BorrowsMySql, Integer> {
}

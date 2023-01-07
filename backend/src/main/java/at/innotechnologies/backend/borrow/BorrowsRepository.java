package at.innotechnologies.backend.borrow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowsRepository extends JpaRepository<Borrows, Integer> {
}

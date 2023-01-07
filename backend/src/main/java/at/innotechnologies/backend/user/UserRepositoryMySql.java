package at.innotechnologies.backend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryMySql extends JpaRepository<UserMySql, Integer> {
    Optional<UserMySql> findByEmail(String email);
}

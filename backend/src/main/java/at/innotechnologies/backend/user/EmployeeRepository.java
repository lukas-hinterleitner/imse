package at.innotechnologies.backend.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends MongoRepository<EmployeeMongo, String> {
    Optional<EmployeeMongo> findByEmail(String email);
}

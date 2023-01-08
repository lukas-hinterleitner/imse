package at.innotechnologies.backend.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerMongo, String> {
    Optional<CustomerMongo> findByEmail(String email);
}

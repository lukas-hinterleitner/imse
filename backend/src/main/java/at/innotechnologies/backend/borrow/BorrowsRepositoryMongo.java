package at.innotechnologies.backend.borrow;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowsRepositoryMongo extends MongoRepository<BorrowsMongo, String> {
}

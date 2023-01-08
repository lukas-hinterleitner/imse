package at.innotechnologies.backend.contains;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContainsRepositoryMongo extends MongoRepository<ContainsMongo, String> {
}

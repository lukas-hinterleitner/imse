package at.innotechnologies.backend.book;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepositoryMongo extends MongoRepository<BookMongo, String> {
}



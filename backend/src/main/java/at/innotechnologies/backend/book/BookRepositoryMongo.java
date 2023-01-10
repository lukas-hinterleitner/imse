package at.innotechnologies.backend.book;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepositoryMongo extends MongoRepository<BookMongo, String> {
    Optional<BookMongo> findByName(String name);
}



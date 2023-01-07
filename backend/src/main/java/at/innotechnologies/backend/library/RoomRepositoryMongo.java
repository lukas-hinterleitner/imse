package at.innotechnologies.backend.library;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepositoryMongo extends MongoRepository<RoomMongo, Integer> {
}
